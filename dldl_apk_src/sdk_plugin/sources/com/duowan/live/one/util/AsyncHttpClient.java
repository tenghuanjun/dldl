package com.duowan.live.one.util;

import android.os.Looper;
import com.duowan.auk.http.HttpClient;
import com.duowan.auk.http.HttpTask;
import com.duowan.auk.util.L;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AsyncHttpClient {
    private static final String CHARSET = "UTF-8";
    private static final String DOUBLE_LINE = "--";
    private static final String LINE_FEED = "\r\n";
    private static int sDefaultConnectTimeout = 3000;
    private static int sDefaultReadTimeout = 6000;

    public interface AsyncHttpResponseHandler {
        void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Throwable th);

        void onSuccess(int i, Map<String, List<String>> map, byte[] bArr);
    }

    @Deprecated
    public HttpTask get(String str, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        return get(str, null, asyncHttpResponseHandler);
    }

    @Deprecated
    public HttpTask get(String str, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        return get(str, requestParams, asyncHttpResponseHandler, sDefaultConnectTimeout, sDefaultReadTimeout);
    }

    @Deprecated
    public HttpTask get(String str, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler, int i, int i2) {
        return HttpClient.get(str, toNewHttpParams(true, requestParams, i2), toNewHttpHandler(asyncHttpResponseHandler));
    }

    @Deprecated
    public HttpTask post(String str, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        return post(str, requestParams, asyncHttpResponseHandler, sDefaultConnectTimeout, sDefaultReadTimeout);
    }

    @Deprecated
    public HttpTask post(String str, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler, int i, int i2) {
        return HttpClient.post(str, toNewHttpParams(false, requestParams, i2), toNewHttpHandler(asyncHttpResponseHandler));
    }

    private HttpClient.RequestParams toNewHttpParams(boolean z, RequestParams requestParams, int i) {
        HttpClient.RequestParams requestParams2 = new HttpClient.RequestParams();
        if (requestParams == null) {
            return requestParams2;
        }
        requestParams2.setTimeout(i);
        if (z) {
            for (Map.Entry<String, String> entry : requestParams.getUrlParams().entrySet()) {
                requestParams2.putUrlParam(entry.getKey(), entry.getValue());
            }
        } else {
            String str = "===" + System.currentTimeMillis() + "===";
            requestParams2.setBodyContentType("multipart/form-data; boundary=" + str);
            try {
                requestParams2.putBody(toBody(requestParams, str));
            } catch (Exception e) {
                L.error(AsyncHttpClient.class, "get body fail: %s", e);
            }
        }
        requestParams2.setCacheKey(requestParams.getCacheKey());
        return requestParams2;
    }

    private byte[] toBody(RequestParams requestParams, String str) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter((Writer) new OutputStreamWriter(byteArrayOutputStream, "UTF-8"), true);
        Map<String, String> urlParams = requestParams.getUrlParams();
        for (String str2 : urlParams.keySet()) {
            addUrlParams(printWriter, str2, urlParams.get(str2), str);
        }
        Map<String, StreamWrapper> streamParams = requestParams.getStreamParams();
        for (String str3 : streamParams.keySet()) {
            addSteamParams(printWriter, byteArrayOutputStream, str3, streamParams.get(str3), str);
        }
        printWriter.append(LINE_FEED).flush();
        printWriter.append(DOUBLE_LINE).append((CharSequence) str).append(DOUBLE_LINE).append(LINE_FEED).close();
        return byteArrayOutputStream.toByteArray();
    }

    private void addUrlParams(PrintWriter printWriter, String str, String str2, String str3) {
        printWriter.append(DOUBLE_LINE).append((CharSequence) str3).append(LINE_FEED).append("Content-Disposition: form-data; name=\"").append((CharSequence) str).append("\"").append(LINE_FEED).append("Content-Type: text/plain; charset=").append("UTF-8").append(LINE_FEED).append(LINE_FEED).append((CharSequence) str2).append(LINE_FEED).flush();
    }

    private void addSteamParams(PrintWriter printWriter, OutputStream outputStream, String str, StreamWrapper streamWrapper, String str2) throws Exception {
        printWriter.append(DOUBLE_LINE).append((CharSequence) str2).append(LINE_FEED).append("Content-Disposition: form-data; name=\"").append((CharSequence) str).append("\"; filename=\"").append((CharSequence) streamWrapper.name).append("\"").append(LINE_FEED).append("Content-Type: ").append((CharSequence) streamWrapper.contentType).append(LINE_FEED).append(LINE_FEED).flush();
        InputStream inputStream = streamWrapper.inputStream;
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                outputStream.write(bArr, 0, i);
            } else {
                outputStream.flush();
                inputStream.close();
                printWriter.flush();
                return;
            }
        }
    }

    private HttpClient.HttpHandler toNewHttpHandler(final AsyncHttpResponseHandler asyncHttpResponseHandler) {
        if (asyncHttpResponseHandler == null) {
            return null;
        }
        return new HttpClient.HttpHandler() { // from class: com.duowan.live.one.util.AsyncHttpClient.1
            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onSuccess(int i, Map<String, List<String>> map, byte[] bArr) {
                asyncHttpResponseHandler.onSuccess(i, map, bArr);
            }

            @Override // com.duowan.auk.http.HttpClient.HttpHandler
            public void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Exception exc) {
                asyncHttpResponseHandler.onFailure(i, map, bArr, exc);
            }
        };
    }

    private void confirmLooperPrepared() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
    }

    public static class RequestParams {
        private String mCacheKey;
        private String mHostUrl = "";
        private String mHostIp = "";
        private Map<String, String> mUrlParams = new HashMap();
        private Map<String, StreamWrapper> mStreamParams = new HashMap();

        @Deprecated
        public void setHost(String str, String str2) {
            this.mHostUrl = str;
            this.mHostIp = str2;
        }

        public String getHostUrl() {
            return this.mHostUrl;
        }

        public String getHostIp() {
            return this.mHostIp;
        }

        public Map<String, String> getUrlParams() {
            return this.mUrlParams;
        }

        public Map<String, StreamWrapper> getStreamParams() {
            return this.mStreamParams;
        }

        public String getCacheKey() {
            return this.mCacheKey;
        }

        public void put(String str, String str2) {
            this.mUrlParams.put(str, str2);
        }

        public void put(String str, File file) throws FileNotFoundException {
            if (file == null) {
                return;
            }
            String name = file.getName();
            put(str, new FileInputStream(file), name, URLConnection.guessContentTypeFromName(name));
        }

        public void put(String str, InputStream inputStream, String str2, String str3) {
            this.mStreamParams.put(str, new StreamWrapper(inputStream, str2, str3));
        }

        public void setCacheKey(String str) {
            this.mCacheKey = str;
        }
    }

    public static class StreamWrapper {
        public String contentType;
        public InputStream inputStream;
        public String name;

        public StreamWrapper(InputStream inputStream, String str, String str2) {
            this.inputStream = inputStream;
            this.name = str;
            this.contentType = str2;
        }
    }
}
