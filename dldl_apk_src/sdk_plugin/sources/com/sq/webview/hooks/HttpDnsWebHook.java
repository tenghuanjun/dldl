package com.sq.webview.hooks;

import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.sq.tools.network.ContentType;
import com.sq.tools.network.httpdns.SqHttpDns;
import com.sq.tools.network.httpdns.util.HttpDnsUtil;
import com.sq.webview.SimpleWebHook;
import com.sq.webview.report.WebErrorReporter;
import com.sq.webview.report.WebEventReporter;
import com.sq.webview.util.IpDns;
import com.sq.webview.util.NetworkStatus;
import com.sq.webview.util.OkHttpEventListener;
import com.sq.webview.util.SniSSLSocketFactory;
import com.sq.webview.util.WebLogUtil;
import com.sq.webview.util.WebUtils;
import com.sqwan.common.track.SqTrackNetKey;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.net.ssl.SSLException;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsWebHook extends SimpleWebHook {
    private static final int ERROR_CONNECTION = 6018;
    private static final int ERROR_HOST = 6002;
    private static final int ERROR_NETWORK = 6019;
    private static final int ERROR_SSL = 6011;
    private static final int ERROR_TIMEOUT = 6003;
    private static final int ERROR_UNKNOWN = 6100;
    private static final int ERROR_URL = 6001;
    private static final String EVENT_REQUEST_FINISH = "sdk_webview_request_finish";
    private static final String KEY_ERROR_CODE = "error_code";
    private static final String KEY_MSG = "msg";
    private static final String KEY_MSG_TITLE = "msg_title";
    private static final int MAX_ERROR_COUNT = 5;
    private static final String TAG = "【WEB_VIEW DNS】";
    private boolean mEnable;
    private final AtomicInteger mErrorCount = new AtomicInteger();
    private final AtomicLong mInterceptCount = new AtomicLong();
    private volatile long mLastErrorTime;
    private volatile OkHttpClient mOkHttpClient;
    private volatile SqHttpDns mSqHttpDns;
    private final WebErrorReporter mWebErrorReporter;
    private final WebEventReporter mWebEventReporter;

    private boolean needRedirect(int code) {
        return code >= 300 && code < 400;
    }

    public HttpDnsWebHook(WebEventReporter webEventReporter, WebErrorReporter webErrorReporter) {
        this.mWebErrorReporter = webErrorReporter;
        this.mWebEventReporter = webEventReporter;
        try {
            this.mSqHttpDns = SqHttpDns.getInstance();
            this.mEnable = true;
        } catch (Throwable unused) {
            this.mSqHttpDns = null;
            this.mEnable = false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00c9  */
    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.webkit.WebResourceResponse shouldInterceptRequest(android.webkit.WebView r8, android.webkit.WebResourceRequest r9) {
        /*
            Method dump skipped, instruction units count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.webview.hooks.HttpDnsWebHook.shouldInterceptRequest(android.webkit.WebView, android.webkit.WebResourceRequest):android.webkit.WebResourceResponse");
    }

    private WebResourceResponse interceptGetRequest(WebResourceRequest request) throws UnsupportedDnsException, IOException {
        String string = request.getUrl().toString();
        HttpUrl httpUrl = HttpUrl.parse(string);
        if (httpUrl == null) {
            throw new MalformedURLException("URL(" + string + ")解析异常");
        }
        this.mInterceptCount.incrementAndGet();
        Map<String, String> requestHeaders = request.getRequestHeaders();
        ArrayList arrayList = new ArrayList();
        try {
            try {
                return buildResponse(recursiveRequest(request, httpUrl, requestHeaders, false, arrayList));
            } catch (Exception e) {
                NetworkStatus networkStatus = arrayList.get(arrayList.size() - 1);
                if (networkStatus != null && networkStatus.error == null) {
                    networkStatus.error = e;
                }
                throw e;
            }
        } finally {
            checkRecord(arrayList);
            report(httpUrl, arrayList);
        }
    }

    private Response recursiveRequest(WebResourceRequest request, HttpUrl originalUrl, Map<String, String> headers, boolean disableDns, List<NetworkStatus> record) throws UnsupportedDnsException, IOException {
        HttpUrl httpUrl;
        HttpUrl httpUrl2;
        HttpUrl httpUrlBuild;
        String ipByHost;
        NetworkStatus networkStatus = new NetworkStatus(originalUrl);
        record.add(networkStatus);
        if (HttpDnsUtil.isIpAddress(originalUrl.host()) || disableDns) {
            httpUrl = originalUrl;
        } else {
            SqHttpDns sqHttpDns = this.mSqHttpDns;
            if (sqHttpDns == null || (ipByHost = sqHttpDns.getIpByHost(originalUrl.host(), null, sqHttpDns.isWebViewIpV6Enable())) == null || ipByHost.isEmpty()) {
                httpUrlBuild = originalUrl;
            } else {
                httpUrlBuild = originalUrl.newBuilder().host(ipByHost).build();
                networkStatus.useLocalDns = true;
                WebLogUtil.v(TAG, "dns解析成功, 替换 " + originalUrl + " -> " + httpUrlBuild);
            }
            httpUrl = httpUrlBuild;
        }
        try {
            Response responseRequestUrl = requestUrl(originalUrl, httpUrl, headers, networkStatus);
            int iCode = responseRequestUrl.code();
            networkStatus.httpStatus = iCode;
            if (needRedirect(iCode)) {
                if (Build.VERSION.SDK_INT < 21) {
                    throw new UnsupportedDnsException("低版本不支持重定向, 放弃拦截");
                }
                if (request.isForMainFrame()) {
                    throw new UnsupportedDnsException("主页面不支持重定向, 放弃拦截");
                }
                if (containCookie(headers)) {
                    throw new UnsupportedDnsException("原有报头中含有cookie, 放弃拦截");
                }
                String locationFromHeader = getLocationFromHeader(responseRequestUrl);
                if (locationFromHeader == null || locationFromHeader.isEmpty()) {
                    throw new UnsupportedDnsException("无法获取location信息, 让浏览器获取, 放弃拦截");
                }
                HttpUrl httpUrl3 = HttpUrl.parse(locationFromHeader);
                if (httpUrl3 == null) {
                    httpUrl2 = HttpUrl.parse(originalUrl.scheme() + "://" + originalUrl.host() + locationFromHeader);
                } else {
                    httpUrl2 = httpUrl3;
                }
                if (httpUrl2 != null) {
                    WebLogUtil.w(TAG, "code: " + iCode + ", 重定向 " + responseRequestUrl.request().url() + " 到 " + httpUrl2);
                    return recursiveRequest(request, httpUrl2, headers, disableDns, record);
                }
                throw new MalformedURLException("重定向Location(" + locationFromHeader + ")解析异常");
            }
            if (400 > iCode || iCode > 599 || !hasDnsOccurred(originalUrl, httpUrl)) {
                return responseRequestUrl;
            }
            WebLogUtil.w(TAG, httpUrl + " 请求失败, status=" + iCode + ", 降级到原域");
            WebErrorReporter webErrorReporter = this.mWebErrorReporter;
            if (webErrorReporter != null) {
                webErrorReporter.report(new Exception("http status code = " + iCode), WebErrorReporter.ExceptionType.WEB_DNS_ORIGIN_ERROR, buildData(originalUrl, httpUrl));
            }
            return recursiveRequest(request, originalUrl, headers, true, record);
        } catch (IOException e) {
            networkStatus.error = e;
            if (e instanceof SocketTimeoutException) {
                throw new UnsupportedDnsException("请求超时");
            }
            if (hasDnsOccurred(originalUrl, httpUrl)) {
                WebLogUtil.w(TAG, httpUrl + " 请求异常降级到原域: " + e);
                WebErrorReporter webErrorReporter2 = this.mWebErrorReporter;
                if (webErrorReporter2 != null) {
                    webErrorReporter2.report(e, WebErrorReporter.ExceptionType.WEB_DNS_ORIGIN_ERROR, buildData(originalUrl, httpUrl));
                }
                return recursiveRequest(request, originalUrl, headers, true, record);
            }
            throw e;
        }
    }

    private void checkRecord(List<NetworkStatus> record) {
        if (record == null || record.isEmpty()) {
            return;
        }
        boolean z = false;
        boolean z2 = false;
        for (NetworkStatus networkStatus : record) {
            if (networkStatus != null) {
                if (networkStatus.useLocalDns && (networkStatus.error instanceof SocketTimeoutException)) {
                    recordError();
                    return;
                } else if (networkStatus.useLocalDns && !networkStatus.isSuccess()) {
                    z = true;
                } else if (!networkStatus.useLocalDns && networkStatus.isSuccess()) {
                    z2 = true;
                }
            }
        }
        if (z && z2) {
            recordError();
        }
    }

    private Response requestUrl(HttpUrl originUrl, HttpUrl connectUrl, Map<String, String> headers, NetworkStatus status) throws IOException {
        if (this.mOkHttpClient == null) {
            synchronized (HttpDnsWebHook.class) {
                if (this.mOkHttpClient == null) {
                    this.mOkHttpClient = new OkHttpClient.Builder().followRedirects(false).followSslRedirects(false).eventListener(new OkHttpEventListener()).connectTimeout(5L, TimeUnit.SECONDS).readTimeout(5L, TimeUnit.SECONDS).writeTimeout(5L, TimeUnit.SECONDS).build();
                }
            }
        }
        OkHttpClient okHttpClientBuild = this.mOkHttpClient;
        status.url = connectUrl;
        Request.Builder builderUrl = new Request.Builder().tag(NetworkStatus.class, status).url(connectUrl);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builderUrl.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (!HttpDnsUtil.isIpAddress(originUrl.host()) && HttpDnsUtil.isIpAddress(connectUrl.host())) {
            builderUrl.addHeader("Host", originUrl.host());
            try {
                okHttpClientBuild = okHttpClientBuild.newBuilder().dns(IpDns.get(originUrl, connectUrl)).build();
                builderUrl.url(originUrl);
            } catch (Throwable unused) {
                okHttpClientBuild = okHttpClientBuild.newBuilder().sslSocketFactory(new SniSSLSocketFactory(originUrl.toString()), SniSSLSocketFactory.platformTrustManager()).hostnameVerifier(new SniSSLSocketFactory.SniHostnameVerifier(originUrl.toString(), okHttpClientBuild.hostnameVerifier())).build();
            }
        }
        return okHttpClientBuild.newCall(builderUrl.build()).execute();
    }

    private WebResourceResponse buildResponse(Response okhttpResponse) throws UnsupportedDnsException {
        if (okhttpResponse.body() == null) {
            throw new UnsupportedDnsException(okhttpResponse.request().url() + " 响应内容为空");
        }
        MediaType mediaTypeContentType = okhttpResponse.body().contentType();
        String str = mediaTypeContentType == null ? null : mediaTypeContentType.type() + "/" + mediaTypeContentType.subtype();
        if (mediaTypeContentType == null || TextUtils.isEmpty(str)) {
            throw new UnsupportedDnsException("不支持解析无Mime类型的响应, content-type=" + mediaTypeContentType);
        }
        Charset charset = mediaTypeContentType.charset();
        if (charset == null && !isBinaryRes(str) && !isText(str) && !isJs(str) && !isJson(str)) {
            throw new UnsupportedDnsException("资源 " + str + " 没有指定charset, content-type=" + mediaTypeContentType);
        }
        int iCode = okhttpResponse.code();
        WebResourceResponse webResourceResponse = new WebResourceResponse(str, charset == null ? "utf-8" : charset.toString(), okhttpResponse.body().byteStream());
        webResourceResponse.setStatusCodeAndReasonPhrase(iCode, String.valueOf(iCode));
        Headers headers = okhttpResponse.headers();
        HashMap map = new HashMap();
        for (String str2 : headers.names()) {
            map.put(str2, headers.get(str2));
        }
        webResourceResponse.setResponseHeaders(map);
        return webResourceResponse;
    }

    private WebResourceResponse buildErrorResponse() {
        WebResourceResponse webResourceResponse = new WebResourceResponse("", "utf-8", null);
        webResourceResponse.setStatusCodeAndReasonPhrase(400, "400");
        return webResourceResponse;
    }

    private boolean isBinaryRes(String mime) {
        return mime.startsWith("image") || mime.startsWith("audio") || mime.startsWith("video");
    }

    private boolean isText(String mime) {
        return mime.startsWith("text");
    }

    private boolean isJs(String mime) {
        return mime.equalsIgnoreCase(FastJsonJsonView.DEFAULT_JSONP_CONTENT_TYPE) || mime.equalsIgnoreCase("application/x-javascript");
    }

    private boolean isJson(String mime) {
        return mime.equalsIgnoreCase(ContentType.JSON);
    }

    private boolean containCookie(Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
            while (it.hasNext()) {
                if (it.next().getKey().contains("Cookie")) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getLocationFromHeader(Response response) {
        String strHeader = response.header("Location");
        return strHeader == null ? response.header("location") : strHeader;
    }

    private boolean hasDnsOccurred(HttpUrl origin, HttpUrl real) {
        return (origin == null || real == null || HttpDnsUtil.isIpAddress(origin.host()) || !HttpDnsUtil.isIpAddress(real.host())) ? false : true;
    }

    private void recordError() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.mLastErrorTime <= 0 || jElapsedRealtime - this.mLastErrorTime <= TimeUnit.SECONDS.toMillis(30L)) {
            this.mErrorCount.incrementAndGet();
        } else {
            this.mErrorCount.set(1);
        }
        this.mLastErrorTime = jElapsedRealtime;
        int i = this.mErrorCount.get();
        if (i >= 5 && this.mSqHttpDns != null) {
            synchronized (this.mErrorCount) {
                if (this.mSqHttpDns == null) {
                    return;
                }
                WebLogUtil.w(TAG, "IP请求失败次数" + i + ", 禁用DNS");
                this.mSqHttpDns = null;
                if (this.mWebErrorReporter != null) {
                    HashMap map = new HashMap();
                    map.put("error_count", Integer.valueOf(i));
                    map.put("intercept_count", Long.valueOf(this.mInterceptCount.get()));
                    this.mWebErrorReporter.report(new Exception(), WebErrorReporter.ExceptionType.WEB_DNS_ORIGIN_ERROR, map);
                }
            }
        }
    }

    private Map<String, Object> buildData(WebResourceRequest request) {
        HashMap map = new HashMap();
        map.put("ourl", request.getUrl().toString());
        map.put("host", request.getUrl().getHost());
        return map;
    }

    private Map<String, Object> buildData(HttpUrl originUrl, HttpUrl realUrl) {
        HashMap map = new HashMap();
        map.put("ourl", originUrl);
        map.put("host", originUrl.host());
        map.put("rurl", realUrl);
        map.put("rhost", realUrl.host());
        return map;
    }

    private static class UnsupportedDnsException extends Exception {
        public UnsupportedDnsException(String message) {
            super(message);
        }
    }

    private void report(HttpUrl originUrl, List<NetworkStatus> networkStatuses) {
        if (this.mWebEventReporter == null || !SqHttpDns.getInstance().reportWebviewNetStat() || WebUtils.isInHttpDnsBlacklist(originUrl.toString())) {
            return;
        }
        int i = 0;
        while (i < networkStatuses.size()) {
            NetworkStatus networkStatus = networkStatuses.get(i);
            HashMap map = new HashMap();
            addNetworkStatusParams(map, networkStatus);
            int i2 = i + 1;
            map.put(SqTrackNetKey.currentRequestCount, Integer.valueOf(i2));
            map.put("is_last_request", Boolean.valueOf(i == networkStatuses.size() - 1));
            if (HttpDnsUtil.isIpAddress(originUrl.host())) {
                map.put("domain", "");
            } else {
                map.put("domain", originUrl.host());
            }
            this.mWebEventReporter.report(EVENT_REQUEST_FINISH, map);
            i = i2;
        }
        this.mWebEventReporter.flush();
    }

    private void addNetworkStatusParams(Map<String, Object> params, NetworkStatus networkStatus) {
        params.put("uri", networkStatus.path());
        params.put("protocol", networkStatus.scheme());
        params.put(SqTrackNetKey.code, Integer.valueOf(networkStatus.httpStatus));
        params.put("cost", Long.valueOf(networkStatus.callCost()));
        params.put("dns_cost", Long.valueOf(networkStatus.dnsCost()));
        params.put("ssl_cost", Long.valueOf(networkStatus.sslCost()));
        params.put("tcp_cost", Long.valueOf(networkStatus.connectCost()));
        params.put("write_cost", Long.valueOf(networkStatus.requestCost()));
        params.put("read_cost", Long.valueOf(networkStatus.responseCost()));
        params.put(SqTrackNetKey.dnsOccur, Boolean.valueOf(networkStatus.useLocalDns));
        params.put("server_ip", networkStatus.serverIp());
        params.put(SqTrackNetKey.isSuccess, Boolean.valueOf(networkStatus.isSuccess()));
        if (networkStatus.isSuccess()) {
            params.put("msg", "请求成功");
            params.put(KEY_ERROR_CODE, 0);
            params.put(KEY_MSG_TITLE, "请求成功");
        } else {
            Exception exc = networkStatus.error;
            params.put("msg", exc == null ? "NullError" : exc.toString());
            params.put(KEY_ERROR_CODE, Integer.valueOf(errorCodeOf(networkStatus)));
            params.put(KEY_MSG_TITLE, exc != null ? exc.getClass().getSimpleName() : "NullError");
        }
    }

    private int errorCodeOf(NetworkStatus networkStatus) {
        if (networkStatus.isSuccess()) {
            return 0;
        }
        Exception exc = networkStatus.error;
        if (exc == null) {
            return networkStatus.httpStatus;
        }
        if (exc instanceof MalformedURLException) {
            return 6001;
        }
        if ((exc instanceof UnknownHostException) || (exc instanceof NoRouteToHostException)) {
            return 6002;
        }
        if (exc instanceof SocketTimeoutException) {
            return 6003;
        }
        if (exc instanceof ConnectException) {
            return 6018;
        }
        if (exc instanceof SSLException) {
            return 6011;
        }
        return exc instanceof SocketException ? 6019 : 6100;
    }
}
