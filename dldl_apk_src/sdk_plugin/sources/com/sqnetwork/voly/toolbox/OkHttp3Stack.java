package com.sqnetwork.voly.toolbox;

import com.sq.tools.network.ContentType;
import com.sqnetwork.voly.AuthFailureError;
import com.sqnetwork.voly.Header;
import com.sqnetwork.voly.toolbox.FormBody;
import com.sqnetwork.voly.toolbox.SniSSLSocketFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class OkHttp3Stack extends BaseHttpStack {
    private final OkHttpClient mOkHttpClient;

    public OkHttp3Stack(OkHttpClient client) {
        if (client == null) {
            this.mOkHttpClient = new OkHttpClient();
        } else {
            this.mOkHttpClient = client;
        }
    }

    private static void setConnectionParametersForRequest(Request.Builder builder, com.sqnetwork.voly.Request<?> request) throws AuthFailureError {
        switch (request.getMethod()) {
            case -1:
                byte[] body = request.getBody();
                if (body != null) {
                    builder.post(RequestBody.create(MediaType.parse(request.getBodyContentType()), body));
                    return;
                }
                return;
            case 0:
                builder.get();
                return;
            case 1:
                builder.post(createRequestBody(request));
                return;
            case 2:
                builder.put(createRequestBody(request));
                return;
            case 3:
                builder.delete(createRequestBody(request));
                return;
            case 4:
                builder.head();
                return;
            case 5:
                builder.method("OPTIONS", null);
                return;
            case 6:
                builder.method("TRACE", null);
                return;
            case 7:
                builder.patch(createRequestBody(request));
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static RequestBody createRequestBody(com.sqnetwork.voly.Request<?> r) throws AuthFailureError {
        byte[] body = r.getBody();
        if (body == null) {
            return null;
        }
        if (r.getBodyContentType().startsWith(ContentType.JSON)) {
            return new JsonBody(MediaType.parse(r.getBodyContentType()), body);
        }
        if (r.getBodyContentType().startsWith(ContentType.FORM)) {
            FormBody.Builder builder = new FormBody.Builder();
            Map<String, String> params = r.getParams();
            if (params == null || params.isEmpty()) {
                return RequestBody.create(MediaType.parse(r.getBodyContentType()), body);
            }
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    throw new IllegalArgumentException(String.format("Request#getParams() or Request#getPostParams() returned a map containing a null key or value: (%s, %s). All keys and values must be non-null.", entry.getKey(), entry.getValue()));
                }
                builder.add(entry.getKey(), entry.getValue());
            }
            return builder.build();
        }
        return RequestBody.create(MediaType.parse(r.getBodyContentType()), body);
    }

    @Override // com.sqnetwork.voly.toolbox.BaseHttpStack
    public HttpResponse executeRequest(com.sqnetwork.voly.Request<?> request, Map<String, String> additionalHeaders) throws AuthFailureError, IOException {
        OkHttpClient.Builder builderHostnameVerifier;
        String realUrl = request.getRealUrl();
        if (request.isIgnoreSSL()) {
            builderHostnameVerifier = SSLIgnore.ignoreSSL(this.mOkHttpClient.newBuilder());
        } else if (request.hasHostNameChangeToIp()) {
            try {
                HttpUrl httpUrl = HttpUrl.get(request.getUrl());
                HttpUrl httpUrl2 = HttpUrl.get(request.getRealUrl());
                request.addMarker("set local dns: " + httpUrl.host() + " -> " + httpUrl2.host());
                OkHttpClient.Builder builderDns = this.mOkHttpClient.newBuilder().dns(IpDns.get(httpUrl, httpUrl2));
                realUrl = httpUrl2.newBuilder().host(httpUrl.host()).build().toString();
                builderHostnameVerifier = builderDns;
            } catch (Throwable unused) {
                request.addMarker("set sni");
                builderHostnameVerifier = this.mOkHttpClient.newBuilder().sslSocketFactory(new SniSSLSocketFactory(request.getUrl()), Util.platformTrustManager()).hostnameVerifier(new SniSSLSocketFactory.SniHostnameVerifier(request.getUrl(), this.mOkHttpClient.hostnameVerifier()));
            }
        } else {
            builderHostnameVerifier = this.mOkHttpClient.newBuilder();
        }
        builderHostnameVerifier.connectTimeout(request.getTimeoutMs(), TimeUnit.MILLISECONDS);
        builderHostnameVerifier.readTimeout(request.getReadTimeoutMs(), TimeUnit.MILLISECONDS);
        builderHostnameVerifier.writeTimeout(request.getWriteTimeoutMs(), TimeUnit.MILLISECONDS);
        Request.Builder builder = new Request.Builder();
        Map<String, String> params = request.getParams();
        try {
            HttpUrl httpUrl3 = HttpUrl.get(realUrl);
            if (request.getMethod() == 0 && params != null && !params.isEmpty()) {
                HttpUrl.Builder builderNewBuilder = httpUrl3.newBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builderNewBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                }
                builder.url(builderNewBuilder.build());
            } else {
                builder.url(httpUrl3);
            }
            Map<String, String> headers = request.getHeaders();
            for (String str : headers.keySet()) {
                String str2 = headers.get(str);
                if (str != null && !str.isEmpty() && str2 != null && !str2.isEmpty()) {
                    builder.addHeader(str, str2);
                }
            }
            for (String str3 : additionalHeaders.keySet()) {
                String str4 = additionalHeaders.get(str3);
                if (str3 != null && !str3.isEmpty() && str4 != null && !str4.isEmpty()) {
                    builder.addHeader(str3, str4);
                }
            }
            setConnectionParametersForRequest(builder, request);
            Response responseExecute = builderHostnameVerifier.build().newCall(builder.tag(com.sqnetwork.voly.Request.class, request).build()).execute();
            int iCode = responseExecute.code();
            ResponseBody responseBodyBody = responseExecute.body();
            return new HttpResponse(iCode, mapHeaders(responseExecute.headers()), responseBodyBody == null ? 0 : (int) responseBodyBody.contentLength(), responseBodyBody == null ? null : responseBodyBody.byteStream());
        } catch (Exception e) {
            throw new MalformedURLException(e.getMessage());
        }
    }

    private List<Header> mapHeaders(Headers responseHeaders) {
        ArrayList arrayList = new ArrayList();
        int size = responseHeaders.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new Header(responseHeaders.name(i), responseHeaders.value(i)));
        }
        return arrayList;
    }
}
