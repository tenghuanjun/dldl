package com.squareup.okhttp.internal.huc;

import com.squareup.okhttp.Handshake;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.OkHeaders;
import com.squareup.okhttp.internal.http.StatusLine;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CacheResponse;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SecureCacheResponse;
import java.net.URI;
import java.net.URLConnection;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okio.BufferedSource;
import okio.Okio;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class JavaApiConverter {
    private JavaApiConverter() {
    }

    public static Response createOkResponse(URI uri, URLConnection uRLConnection) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
        Response.Builder builder = new Response.Builder();
        Certificate[] serverCertificates = null;
        builder.request(createOkRequest(uri, httpURLConnection.getRequestMethod(), null));
        StatusLine statusLine = StatusLine.parse(extractStatusLine(httpURLConnection));
        builder.protocol(statusLine.protocol);
        builder.code(statusLine.code);
        builder.message(statusLine.message);
        Headers headersExtractOkResponseHeaders = extractOkResponseHeaders(httpURLConnection);
        builder.headers(headersExtractOkResponseHeaders);
        builder.body(createOkBody(headersExtractOkResponseHeaders, uRLConnection.getInputStream()));
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            try {
                serverCertificates = httpsURLConnection.getServerCertificates();
            } catch (SSLPeerUnverifiedException unused) {
            }
            builder.handshake(Handshake.get(httpsURLConnection.getCipherSuite(), nullSafeImmutableList(serverCertificates), nullSafeImmutableList(httpsURLConnection.getLocalCertificates())));
        }
        return builder.build();
    }

    static Response createOkResponse(Request request, CacheResponse cacheResponse) throws IOException {
        List<Certificate> listEmptyList;
        Response.Builder builder = new Response.Builder();
        builder.request(request);
        StatusLine statusLine = StatusLine.parse(extractStatusLine(cacheResponse));
        builder.protocol(statusLine.protocol);
        builder.code(statusLine.code);
        builder.message(statusLine.message);
        Headers headersExtractOkHeaders = extractOkHeaders(cacheResponse);
        builder.headers(headersExtractOkHeaders);
        builder.body(createOkBody(headersExtractOkHeaders, cacheResponse.getBody()));
        if (cacheResponse instanceof SecureCacheResponse) {
            SecureCacheResponse secureCacheResponse = (SecureCacheResponse) cacheResponse;
            try {
                listEmptyList = secureCacheResponse.getServerCertificateChain();
            } catch (SSLPeerUnverifiedException unused) {
                listEmptyList = Collections.emptyList();
            }
            List<Certificate> localCertificateChain = secureCacheResponse.getLocalCertificateChain();
            if (localCertificateChain == null) {
                localCertificateChain = Collections.emptyList();
            }
            builder.handshake(Handshake.get(secureCacheResponse.getCipherSuite(), listEmptyList, localCertificateChain));
        }
        return builder.build();
    }

    public static Request createOkRequest(URI uri, String str, Map<String, List<String>> map) {
        Request.Builder builderMethod = new Request.Builder().url(uri.toString()).method(str, null);
        if (map != null) {
            builderMethod.headers(extractOkHeaders(map));
        }
        return builderMethod.build();
    }

    public static CacheResponse createJavaCacheResponse(final Response response) {
        final Headers headers = response.headers();
        final ResponseBody responseBodyBody = response.body();
        if (response.request().isHttps()) {
            final Handshake handshake = response.handshake();
            return new SecureCacheResponse() { // from class: com.squareup.okhttp.internal.huc.JavaApiConverter.1
                @Override // java.net.SecureCacheResponse
                public String getCipherSuite() {
                    Handshake handshake2 = handshake;
                    if (handshake2 != null) {
                        return handshake2.cipherSuite();
                    }
                    return null;
                }

                @Override // java.net.SecureCacheResponse
                public List<Certificate> getLocalCertificateChain() {
                    Handshake handshake2 = handshake;
                    if (handshake2 == null) {
                        return null;
                    }
                    List<Certificate> listLocalCertificates = handshake2.localCertificates();
                    if (listLocalCertificates.size() > 0) {
                        return listLocalCertificates;
                    }
                    return null;
                }

                @Override // java.net.SecureCacheResponse
                public List<Certificate> getServerCertificateChain() throws SSLPeerUnverifiedException {
                    Handshake handshake2 = handshake;
                    if (handshake2 == null) {
                        return null;
                    }
                    List<Certificate> listPeerCertificates = handshake2.peerCertificates();
                    if (listPeerCertificates.size() > 0) {
                        return listPeerCertificates;
                    }
                    return null;
                }

                @Override // java.net.SecureCacheResponse
                public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
                    Handshake handshake2 = handshake;
                    if (handshake2 == null) {
                        return null;
                    }
                    return handshake2.peerPrincipal();
                }

                @Override // java.net.SecureCacheResponse
                public Principal getLocalPrincipal() {
                    Handshake handshake2 = handshake;
                    if (handshake2 == null) {
                        return null;
                    }
                    return handshake2.localPrincipal();
                }

                @Override // java.net.CacheResponse
                public Map<String, List<String>> getHeaders() throws IOException {
                    return OkHeaders.toMultimap(headers, StatusLine.get(response).toString());
                }

                @Override // java.net.CacheResponse
                public InputStream getBody() throws IOException {
                    ResponseBody responseBody = responseBodyBody;
                    if (responseBody == null) {
                        return null;
                    }
                    return responseBody.byteStream();
                }
            };
        }
        return new CacheResponse() { // from class: com.squareup.okhttp.internal.huc.JavaApiConverter.2
            @Override // java.net.CacheResponse
            public Map<String, List<String>> getHeaders() throws IOException {
                return OkHeaders.toMultimap(headers, StatusLine.get(response).toString());
            }

            @Override // java.net.CacheResponse
            public InputStream getBody() throws IOException {
                ResponseBody responseBody = responseBodyBody;
                if (responseBody == null) {
                    return null;
                }
                return responseBody.byteStream();
            }
        };
    }

    static HttpURLConnection createJavaUrlConnection(Response response) {
        if (response.request().isHttps()) {
            return new CacheHttpsURLConnection(new CacheHttpURLConnection(response));
        }
        return new CacheHttpURLConnection(response);
    }

    static Map<String, List<String>> extractJavaHeaders(Request request) {
        return OkHeaders.toMultimap(request.headers(), null);
    }

    private static Headers extractOkHeaders(CacheResponse cacheResponse) throws IOException {
        return extractOkHeaders(cacheResponse.getHeaders());
    }

    private static Headers extractOkResponseHeaders(HttpURLConnection httpURLConnection) {
        return extractOkHeaders(httpURLConnection.getHeaderFields());
    }

    static Headers extractOkHeaders(Map<String, List<String>> map) {
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    builder.add(key, it.next());
                }
            }
        }
        return builder.build();
    }

    private static String extractStatusLine(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getHeaderField((String) null);
    }

    private static String extractStatusLine(CacheResponse cacheResponse) throws IOException {
        return extractStatusLine(cacheResponse.getHeaders());
    }

    static String extractStatusLine(Map<String, List<String>> map) {
        List<String> list = map.get(null);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    private static ResponseBody createOkBody(final Headers headers, InputStream inputStream) {
        final BufferedSource bufferedSourceBuffer = Okio.buffer(Okio.source(inputStream));
        return new ResponseBody() { // from class: com.squareup.okhttp.internal.huc.JavaApiConverter.3
            @Override // com.squareup.okhttp.ResponseBody
            public MediaType contentType() {
                String str = headers.get("Content-Type");
                if (str == null) {
                    return null;
                }
                return MediaType.parse(str);
            }

            @Override // com.squareup.okhttp.ResponseBody
            public long contentLength() {
                return OkHeaders.contentLength(headers);
            }

            @Override // com.squareup.okhttp.ResponseBody
            public BufferedSource source() {
                return bufferedSourceBuffer;
            }
        };
    }

    private static final class CacheHttpURLConnection extends HttpURLConnection {
        private final Request request;
        private final Response response;

        @Override // java.net.URLConnection
        public boolean getAllowUserInteraction() {
            return false;
        }

        @Override // java.net.URLConnection
        public int getConnectTimeout() {
            return 0;
        }

        @Override // java.net.URLConnection
        public boolean getDoInput() {
            return true;
        }

        @Override // java.net.HttpURLConnection
        public InputStream getErrorStream() {
            return null;
        }

        @Override // java.net.URLConnection
        public long getIfModifiedSince() {
            return 0L;
        }

        @Override // java.net.URLConnection
        public int getReadTimeout() {
            return 0;
        }

        @Override // java.net.HttpURLConnection
        public boolean usingProxy() {
            return false;
        }

        public CacheHttpURLConnection(Response response) {
            super(response.request().url());
            this.request = response.request();
            this.response = response;
            this.connected = true;
            this.doOutput = response.body() == null;
            this.method = this.request.method();
        }

        @Override // java.net.URLConnection
        public void connect() throws IOException {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.HttpURLConnection
        public void disconnect() {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public void setRequestProperty(String str, String str2) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public void addRequestProperty(String str, String str2) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public String getRequestProperty(String str) {
            return this.request.header(str);
        }

        @Override // java.net.URLConnection
        public Map<String, List<String>> getRequestProperties() {
            throw JavaApiConverter.throwRequestHeaderAccessException();
        }

        @Override // java.net.HttpURLConnection
        public void setFixedLengthStreamingMode(int i) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.HttpURLConnection
        public void setFixedLengthStreamingMode(long j) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.HttpURLConnection
        public void setChunkedStreamingMode(int i) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.HttpURLConnection
        public void setInstanceFollowRedirects(boolean z) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.HttpURLConnection
        public boolean getInstanceFollowRedirects() {
            return super.getInstanceFollowRedirects();
        }

        @Override // java.net.HttpURLConnection
        public void setRequestMethod(String str) throws ProtocolException {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.HttpURLConnection
        public String getRequestMethod() {
            return this.request.method();
        }

        @Override // java.net.HttpURLConnection, java.net.URLConnection
        public String getHeaderFieldKey(int i) {
            if (i >= 0) {
                if (i == 0) {
                    return null;
                }
                return this.response.headers().name(i - 1);
            }
            throw new IllegalArgumentException("Invalid header index: " + i);
        }

        @Override // java.net.HttpURLConnection, java.net.URLConnection
        public String getHeaderField(int i) {
            if (i >= 0) {
                if (i == 0) {
                    return StatusLine.get(this.response).toString();
                }
                return this.response.headers().value(i - 1);
            }
            throw new IllegalArgumentException("Invalid header index: " + i);
        }

        @Override // java.net.URLConnection
        public String getHeaderField(String str) {
            return str == null ? StatusLine.get(this.response).toString() : this.response.headers().get(str);
        }

        @Override // java.net.URLConnection
        public Map<String, List<String>> getHeaderFields() {
            return OkHeaders.toMultimap(this.response.headers(), StatusLine.get(this.response).toString());
        }

        @Override // java.net.HttpURLConnection
        public int getResponseCode() throws IOException {
            return this.response.code();
        }

        @Override // java.net.HttpURLConnection
        public String getResponseMessage() throws IOException {
            return this.response.message();
        }

        @Override // java.net.URLConnection
        public void setConnectTimeout(int i) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public void setReadTimeout(int i) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public Object getContent() throws IOException {
            throw JavaApiConverter.throwResponseBodyAccessException();
        }

        @Override // java.net.URLConnection
        public Object getContent(Class[] clsArr) throws IOException {
            throw JavaApiConverter.throwResponseBodyAccessException();
        }

        @Override // java.net.URLConnection
        public InputStream getInputStream() throws IOException {
            throw JavaApiConverter.throwResponseBodyAccessException();
        }

        @Override // java.net.URLConnection
        public OutputStream getOutputStream() throws IOException {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public void setDoInput(boolean z) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public void setDoOutput(boolean z) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public boolean getDoOutput() {
            return this.request.body() != null;
        }

        @Override // java.net.URLConnection
        public void setAllowUserInteraction(boolean z) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public void setUseCaches(boolean z) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public boolean getUseCaches() {
            return super.getUseCaches();
        }

        @Override // java.net.URLConnection
        public void setIfModifiedSince(long j) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // java.net.URLConnection
        public boolean getDefaultUseCaches() {
            return super.getDefaultUseCaches();
        }

        @Override // java.net.URLConnection
        public void setDefaultUseCaches(boolean z) {
            super.setDefaultUseCaches(z);
        }
    }

    private static final class CacheHttpsURLConnection extends DelegatingHttpsURLConnection {
        private final CacheHttpURLConnection delegate;

        public CacheHttpsURLConnection(CacheHttpURLConnection cacheHttpURLConnection) {
            super(cacheHttpURLConnection);
            this.delegate = cacheHttpURLConnection;
        }

        @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
        protected Handshake handshake() {
            return this.delegate.response.handshake();
        }

        @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection, javax.net.ssl.HttpsURLConnection
        public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection, javax.net.ssl.HttpsURLConnection
        public HostnameVerifier getHostnameVerifier() {
            throw JavaApiConverter.throwRequestSslAccessException();
        }

        @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection, javax.net.ssl.HttpsURLConnection
        public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
            throw JavaApiConverter.throwRequestModificationException();
        }

        @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection, javax.net.ssl.HttpsURLConnection
        public SSLSocketFactory getSSLSocketFactory() {
            throw JavaApiConverter.throwRequestSslAccessException();
        }

        @Override // java.net.URLConnection
        public long getContentLengthLong() {
            return this.delegate.getContentLengthLong();
        }

        @Override // java.net.HttpURLConnection
        public void setFixedLengthStreamingMode(long j) {
            this.delegate.setFixedLengthStreamingMode(j);
        }

        @Override // java.net.URLConnection
        public long getHeaderFieldLong(String str, long j) {
            return this.delegate.getHeaderFieldLong(str, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RuntimeException throwRequestModificationException() {
        throw new UnsupportedOperationException("ResponseCache cannot modify the request.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RuntimeException throwRequestHeaderAccessException() {
        throw new UnsupportedOperationException("ResponseCache cannot access request headers");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RuntimeException throwRequestSslAccessException() {
        throw new UnsupportedOperationException("ResponseCache cannot access SSL internals");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RuntimeException throwResponseBodyAccessException() {
        throw new UnsupportedOperationException("ResponseCache cannot access the response body.");
    }

    private static <T> List<T> nullSafeImmutableList(T[] tArr) {
        return tArr == null ? Collections.emptyList() : Util.immutableList(tArr);
    }
}
