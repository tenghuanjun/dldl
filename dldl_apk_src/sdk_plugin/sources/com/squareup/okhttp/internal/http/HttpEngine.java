package com.squareup.okhttp.internal.http;

import com.huya.mtp.hyns.NSFunction;
import com.squareup.okhttp.Address;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.Route;
import com.squareup.okhttp.internal.Dns;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.CacheStrategy;
import com.sy37sdk.order.third.douyin.DouYinPayWay;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheRequest;
import java.net.CookieHandler;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.util.Date;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocketFactory;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class HttpEngine {
    private static final ResponseBody EMPTY_BODY = new ResponseBody() { // from class: com.squareup.okhttp.internal.http.HttpEngine.1
        @Override // com.squareup.okhttp.ResponseBody
        public long contentLength() {
            return 0L;
        }

        @Override // com.squareup.okhttp.ResponseBody
        public MediaType contentType() {
            return null;
        }

        @Override // com.squareup.okhttp.ResponseBody
        public BufferedSource source() {
            return new Buffer();
        }
    };
    public static final int MAX_REDIRECTS = 20;
    public final boolean bufferRequestBody;
    private BufferedSink bufferedRequestBody;
    private Response cacheResponse;
    private CacheStrategy cacheStrategy;
    final OkHttpClient client;
    private Connection connection;
    private Request networkRequest;
    private Response networkResponse;
    private final Response priorResponse;
    private Sink requestBodyOut;
    private BufferedSource responseBody;
    private InputStream responseBodyBytes;
    private Source responseTransferSource;
    private Route route;
    private RouteSelector routeSelector;
    long sentRequestMillis = -1;
    private CacheRequest storeRequest;
    private boolean transparentGzip;
    private Transport transport;
    private final Request userRequest;
    private Response userResponse;

    public HttpEngine(OkHttpClient okHttpClient, Request request, boolean z, Connection connection, RouteSelector routeSelector, RetryableSink retryableSink, Response response) {
        this.client = okHttpClient;
        this.userRequest = request;
        this.bufferRequestBody = z;
        this.connection = connection;
        this.routeSelector = routeSelector;
        this.requestBodyOut = retryableSink;
        this.priorResponse = response;
        if (connection != null) {
            Internal.instance.setOwner(connection, this);
            this.route = connection.getRoute();
        } else {
            this.route = null;
        }
    }

    public void sendRequest() throws IOException {
        if (this.cacheStrategy != null) {
            return;
        }
        if (this.transport != null) {
            throw new IllegalStateException();
        }
        Request requestNetworkRequest = networkRequest(this.userRequest);
        InternalCache internalCache = Internal.instance.internalCache(this.client);
        Response response = internalCache != null ? internalCache.get(requestNetworkRequest) : null;
        CacheStrategy cacheStrategy = new CacheStrategy.Factory(System.currentTimeMillis(), requestNetworkRequest, response).get();
        this.cacheStrategy = cacheStrategy;
        this.networkRequest = cacheStrategy.networkRequest;
        this.cacheResponse = this.cacheStrategy.cacheResponse;
        if (internalCache != null) {
            internalCache.trackResponse(this.cacheStrategy);
        }
        if (response != null && this.cacheResponse == null) {
            Util.closeQuietly(response.body());
        }
        Request request = this.networkRequest;
        if (request != null) {
            if (this.connection == null) {
                connect(request);
            }
            if (Internal.instance.getOwner(this.connection) != this && !Internal.instance.isSpdy(this.connection)) {
                throw new AssertionError();
            }
            this.transport = Internal.instance.newTransport(this.connection, this);
            if (hasRequestBody() && this.requestBodyOut == null) {
                this.requestBodyOut = this.transport.createRequestBody(requestNetworkRequest);
                return;
            }
            return;
        }
        if (this.connection != null) {
            Internal.instance.recycle(this.client.getConnectionPool(), this.connection);
            this.connection = null;
        }
        Response response2 = this.cacheResponse;
        if (response2 != null) {
            this.userResponse = response2.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).cacheResponse(stripBody(this.cacheResponse)).build();
        } else {
            this.userResponse = new Response.Builder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(EMPTY_BODY).build();
        }
        if (this.userResponse.body() != null) {
            initContentStream(this.userResponse.body().source());
        }
    }

    private static Response stripBody(Response response) {
        return (response == null || response.body() == null) ? response : response.newBuilder().body(null).build();
    }

    private void connect(Request request) throws IOException {
        SSLSocketFactory sslSocketFactory;
        HostnameVerifier hostnameVerifier;
        if (this.connection != null) {
            throw new IllegalStateException();
        }
        if (this.routeSelector == null) {
            String host = request.url().getHost();
            if (host == null || host.length() == 0) {
                throw new UnknownHostException(request.url().toString());
            }
            if (request.isHttps()) {
                sslSocketFactory = this.client.getSslSocketFactory();
                hostnameVerifier = this.client.getHostnameVerifier();
            } else {
                sslSocketFactory = null;
                hostnameVerifier = null;
            }
            this.routeSelector = new RouteSelector(new Address(host, Util.getEffectivePort(request.url()), this.client.getSocketFactory(), sslSocketFactory, hostnameVerifier, this.client.getAuthenticator(), this.client.getProxy(), this.client.getProtocols()), request.uri(), this.client.getProxySelector(), this.client.getConnectionPool(), Dns.DEFAULT, Internal.instance.routeDatabase(this.client));
        }
        this.connection = this.routeSelector.next(request.method());
        Internal.instance.setOwner(this.connection, this);
        if (!Internal.instance.isConnected(this.connection)) {
            Internal.instance.connect(this.connection, this.client.getConnectTimeout(), this.client.getReadTimeout(), this.client.getWriteTimeout(), tunnelRequest(this.connection, request));
            if (Internal.instance.isSpdy(this.connection)) {
                Internal.instance.share(this.client.getConnectionPool(), this.connection);
            }
            Internal.instance.routeDatabase(this.client).connected(this.connection.getRoute());
        }
        Internal.instance.setTimeouts(this.connection, this.client.getReadTimeout(), this.client.getWriteTimeout());
        this.route = this.connection.getRoute();
    }

    public void writingRequestHeaders() {
        if (this.sentRequestMillis != -1) {
            throw new IllegalStateException();
        }
        this.sentRequestMillis = System.currentTimeMillis();
    }

    boolean hasRequestBody() {
        return HttpMethod.hasRequestBody(this.userRequest.method()) && !Util.emptySink().equals(this.requestBodyOut);
    }

    public Sink getRequestBody() {
        if (this.cacheStrategy == null) {
            throw new IllegalStateException();
        }
        return this.requestBodyOut;
    }

    public BufferedSink getBufferedRequestBody() {
        BufferedSink bufferedSink = this.bufferedRequestBody;
        if (bufferedSink != null) {
            return bufferedSink;
        }
        Sink requestBody = getRequestBody();
        if (requestBody == null) {
            return null;
        }
        BufferedSink bufferedSinkBuffer = Okio.buffer(requestBody);
        this.bufferedRequestBody = bufferedSinkBuffer;
        return bufferedSinkBuffer;
    }

    public boolean hasResponse() {
        return this.userResponse != null;
    }

    public Request getRequest() {
        return this.userRequest;
    }

    public Response getResponse() {
        Response response = this.userResponse;
        if (response != null) {
            return response;
        }
        throw new IllegalStateException();
    }

    public BufferedSource getResponseBody() {
        if (this.userResponse == null) {
            throw new IllegalStateException();
        }
        return this.responseBody;
    }

    public InputStream getResponseBodyBytes() {
        InputStream inputStream = this.responseBodyBytes;
        if (inputStream != null) {
            return inputStream;
        }
        InputStream inputStream2 = Okio.buffer(getResponseBody()).inputStream();
        this.responseBodyBytes = inputStream2;
        return inputStream2;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public HttpEngine recover(IOException iOException, Sink sink) {
        Connection connection;
        RouteSelector routeSelector = this.routeSelector;
        if (routeSelector != null && (connection = this.connection) != null) {
            routeSelector.connectFailed(connection, iOException);
        }
        boolean z = sink == null || (sink instanceof RetryableSink);
        if (this.routeSelector == null && this.connection == null) {
            return null;
        }
        RouteSelector routeSelector2 = this.routeSelector;
        if ((routeSelector2 == null || routeSelector2.hasNext()) && isRecoverable(iOException) && z) {
            return new HttpEngine(this.client, this.userRequest, this.bufferRequestBody, close(), this.routeSelector, (RetryableSink) sink, this.priorResponse);
        }
        return null;
    }

    public HttpEngine recover(IOException iOException) {
        return recover(iOException, this.requestBodyOut);
    }

    private boolean isRecoverable(IOException iOException) {
        return (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof ProtocolException)) ? false : true;
    }

    public Route getRoute() {
        return this.route;
    }

    private void maybeCache() throws IOException {
        InternalCache internalCache = Internal.instance.internalCache(this.client);
        if (internalCache == null) {
            return;
        }
        if (!CacheStrategy.isCacheable(this.userResponse, this.networkRequest)) {
            if (HttpMethod.invalidatesCache(this.networkRequest.method())) {
                try {
                    internalCache.remove(this.networkRequest);
                    return;
                } catch (IOException unused) {
                    return;
                }
            }
            return;
        }
        this.storeRequest = internalCache.put(stripBody(this.userResponse));
    }

    public void releaseConnection() throws IOException {
        Transport transport = this.transport;
        if (transport != null && this.connection != null) {
            transport.releaseConnectionOnIdle();
        }
        this.connection = null;
    }

    public void disconnect() {
        Transport transport = this.transport;
        if (transport != null) {
            try {
                transport.disconnect(this);
            } catch (IOException unused) {
            }
        }
    }

    public Connection close() {
        BufferedSink bufferedSink = this.bufferedRequestBody;
        if (bufferedSink != null) {
            Util.closeQuietly(bufferedSink);
        } else {
            Sink sink = this.requestBodyOut;
            if (sink != null) {
                Util.closeQuietly(sink);
            }
        }
        BufferedSource bufferedSource = this.responseBody;
        if (bufferedSource == null) {
            Connection connection = this.connection;
            if (connection != null) {
                Util.closeQuietly(connection.getSocket());
            }
            this.connection = null;
            return null;
        }
        Util.closeQuietly(bufferedSource);
        Util.closeQuietly(this.responseBodyBytes);
        Transport transport = this.transport;
        if (transport != null && this.connection != null && !transport.canReuseConnection()) {
            Util.closeQuietly(this.connection.getSocket());
            this.connection = null;
            return null;
        }
        if (this.connection != null && !Internal.instance.clearOwner(this.connection)) {
            this.connection = null;
        }
        Connection connection2 = this.connection;
        this.connection = null;
        return connection2;
    }

    private void initContentStream(Source source) throws IOException {
        this.responseTransferSource = source;
        if (this.transparentGzip && NSFunction.GZIP.equalsIgnoreCase(this.userResponse.header(NSFunction.CONTENT_ENCODING))) {
            this.userResponse = this.userResponse.newBuilder().removeHeader(NSFunction.CONTENT_ENCODING).removeHeader("Content-Length").build();
            this.responseBody = Okio.buffer(new GzipSource(source));
        } else {
            this.responseBody = Okio.buffer(source);
        }
    }

    public boolean hasResponseBody() {
        if (this.userRequest.method().equals("HEAD")) {
            return false;
        }
        int iCode = this.userResponse.code();
        return (((iCode >= 100 && iCode < 200) || iCode == 204 || iCode == 304) && OkHeaders.contentLength(this.networkResponse) == -1 && !"chunked".equalsIgnoreCase(this.networkResponse.header("Transfer-Encoding"))) ? false : true;
    }

    private Request networkRequest(Request request) throws IOException {
        Request.Builder builderNewBuilder = request.newBuilder();
        if (request.header("Host") == null) {
            builderNewBuilder.header("Host", hostHeader(request.url()));
        }
        Connection connection = this.connection;
        if ((connection == null || connection.getProtocol() != Protocol.HTTP_1_0) && request.header("Connection") == null) {
            builderNewBuilder.header("Connection", "Keep-Alive");
        }
        if (request.header(NSFunction.ACCEPT_ENCODING) == null) {
            this.transparentGzip = true;
            builderNewBuilder.header(NSFunction.ACCEPT_ENCODING, NSFunction.GZIP);
        }
        CookieHandler cookieHandler = this.client.getCookieHandler();
        if (cookieHandler != null) {
            OkHeaders.addCookies(builderNewBuilder, cookieHandler.get(request.uri(), OkHeaders.toMultimap(builderNewBuilder.build().headers(), null)));
        }
        return builderNewBuilder.build();
    }

    public static String hostHeader(URL url) {
        if (Util.getEffectivePort(url) == Util.getDefaultPort(url.getProtocol())) {
            return url.getHost();
        }
        return url.getHost() + ":" + url.getPort();
    }

    public void readResponse() throws IOException {
        if (this.userResponse != null) {
            return;
        }
        if (this.networkRequest == null && this.cacheResponse == null) {
            throw new IllegalStateException("call sendRequest() first!");
        }
        if (this.networkRequest == null) {
            return;
        }
        BufferedSink bufferedSink = this.bufferedRequestBody;
        if (bufferedSink != null && bufferedSink.buffer().size() > 0) {
            this.bufferedRequestBody.flush();
        }
        if (this.sentRequestMillis == -1) {
            if (OkHeaders.contentLength(this.networkRequest) == -1) {
                Sink sink = this.requestBodyOut;
                if (sink instanceof RetryableSink) {
                    this.networkRequest = this.networkRequest.newBuilder().header("Content-Length", Long.toString(((RetryableSink) sink).contentLength())).build();
                }
            }
            this.transport.writeRequestHeaders(this.networkRequest);
        }
        Sink sink2 = this.requestBodyOut;
        if (sink2 != null) {
            BufferedSink bufferedSink2 = this.bufferedRequestBody;
            if (bufferedSink2 != null) {
                bufferedSink2.close();
            } else {
                sink2.close();
            }
            if ((this.requestBodyOut instanceof RetryableSink) && !Util.emptySink().equals(this.requestBodyOut)) {
                this.transport.writeRequestBody((RetryableSink) this.requestBodyOut);
            }
        }
        this.transport.flushRequest();
        this.networkResponse = this.transport.readResponseHeaders().request(this.networkRequest).handshake(this.connection.getHandshake()).header(OkHeaders.SENT_MILLIS, Long.toString(this.sentRequestMillis)).header(OkHeaders.RECEIVED_MILLIS, Long.toString(System.currentTimeMillis())).build();
        Internal.instance.setProtocol(this.connection, this.networkResponse.protocol());
        receiveHeaders(this.networkResponse.headers());
        Response response = this.cacheResponse;
        if (response != null) {
            if (validate(response, this.networkResponse)) {
                this.userResponse = this.cacheResponse.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).headers(combine(this.cacheResponse.headers(), this.networkResponse.headers())).cacheResponse(stripBody(this.cacheResponse)).networkResponse(stripBody(this.networkResponse)).build();
                this.transport.emptyTransferStream();
                releaseConnection();
                InternalCache internalCache = Internal.instance.internalCache(this.client);
                internalCache.trackConditionalCacheHit();
                internalCache.update(this.cacheResponse, stripBody(this.userResponse));
                if (this.cacheResponse.body() != null) {
                    initContentStream(this.cacheResponse.body().source());
                    return;
                }
                return;
            }
            Util.closeQuietly(this.cacheResponse.body());
        }
        this.userResponse = this.networkResponse.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).cacheResponse(stripBody(this.cacheResponse)).networkResponse(stripBody(this.networkResponse)).build();
        if (!hasResponseBody()) {
            Source transferStream = this.transport.getTransferStream(this.storeRequest);
            this.responseTransferSource = transferStream;
            this.responseBody = Okio.buffer(transferStream);
        } else {
            maybeCache();
            initContentStream(this.transport.getTransferStream(this.storeRequest));
        }
    }

    private static boolean validate(Response response, Response response2) {
        Date date;
        if (response2.code() == 304) {
            return true;
        }
        Date date2 = response.headers().getDate("Last-Modified");
        return (date2 == null || (date = response2.headers().getDate("Last-Modified")) == null || date.getTime() >= date2.getTime()) ? false : true;
    }

    private static Headers combine(Headers headers, Headers headers2) throws IOException {
        Headers.Builder builder = new Headers.Builder();
        for (int i = 0; i < headers.size(); i++) {
            String strName = headers.name(i);
            String strValue = headers.value(i);
            if ((!"Warning".equals(strName) || !strValue.startsWith("1")) && (!OkHeaders.isEndToEnd(strName) || headers2.get(strName) == null)) {
                builder.add(strName, strValue);
            }
        }
        for (int i2 = 0; i2 < headers2.size(); i2++) {
            String strName2 = headers2.name(i2);
            if (OkHeaders.isEndToEnd(strName2)) {
                builder.add(strName2, headers2.value(i2));
            }
        }
        return builder.build();
    }

    private Request tunnelRequest(Connection connection, Request request) throws IOException {
        String str;
        if (!connection.getRoute().requiresTunnel()) {
            return null;
        }
        String host = request.url().getHost();
        int effectivePort = Util.getEffectivePort(request.url());
        if (effectivePort == Util.getDefaultPort(IDataSource.SCHEME_HTTPS_TAG)) {
            str = host;
        } else {
            str = host + ":" + effectivePort;
        }
        Request.Builder builderHeader = new Request.Builder().url(new URL(IDataSource.SCHEME_HTTPS_TAG, host, effectivePort, "/")).header("Host", str).header("Proxy-Connection", "Keep-Alive");
        String strHeader = request.header("User-Agent");
        if (strHeader != null) {
            builderHeader.header("User-Agent", strHeader);
        }
        String strHeader2 = request.header("Proxy-Authorization");
        if (strHeader2 != null) {
            builderHeader.header("Proxy-Authorization", strHeader2);
        }
        return builderHeader.build();
    }

    public void receiveHeaders(Headers headers) throws IOException {
        CookieHandler cookieHandler = this.client.getCookieHandler();
        if (cookieHandler != null) {
            cookieHandler.put(this.userRequest.uri(), OkHeaders.toMultimap(headers, null));
        }
    }

    public Request followUpRequest() throws IOException {
        if (this.userResponse == null) {
            throw new IllegalStateException();
        }
        Proxy proxy = getRoute() != null ? getRoute().getProxy() : this.client.getProxy();
        int iCode = this.userResponse.code();
        if (iCode != 307) {
            if (iCode != 401) {
                if (iCode != 407) {
                    switch (iCode) {
                        case 300:
                        case DouYinPayWay.ERROR_PARAM /* 301 */:
                        case DouYinPayWay.ERROR_ORDER_ID /* 302 */:
                        case DouYinPayWay.ERROR_NO_ORDER /* 303 */:
                            break;
                        default:
                            return null;
                    }
                } else if (proxy.type() != Proxy.Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
            }
            return OkHeaders.processAuthHeader(this.client.getAuthenticator(), this.userResponse, proxy);
        }
        if (!this.userRequest.method().equals("GET") && !this.userRequest.method().equals("HEAD")) {
            return null;
        }
        String strHeader = this.userResponse.header("Location");
        if (strHeader == null) {
            return null;
        }
        URL url = new URL(this.userRequest.url(), strHeader);
        if (!url.getProtocol().equals(IDataSource.SCHEME_HTTPS_TAG) && !url.getProtocol().equals(IDataSource.SCHEME_HTTP_TAG)) {
            return null;
        }
        if (!url.getProtocol().equals(this.userRequest.url().getProtocol()) && !this.client.getFollowSslRedirects()) {
            return null;
        }
        Request.Builder builderNewBuilder = this.userRequest.newBuilder();
        if (HttpMethod.hasRequestBody(this.userRequest.method())) {
            builderNewBuilder.method("GET", null);
            builderNewBuilder.removeHeader("Transfer-Encoding");
            builderNewBuilder.removeHeader("Content-Length");
            builderNewBuilder.removeHeader("Content-Type");
        }
        if (!sameConnection(url)) {
            builderNewBuilder.removeHeader("Authorization");
        }
        return builderNewBuilder.url(url).build();
    }

    public boolean sameConnection(URL url) {
        URL url2 = this.userRequest.url();
        return url2.getHost().equals(url.getHost()) && Util.getEffectivePort(url2) == Util.getEffectivePort(url) && url2.getProtocol().equals(url.getProtocol());
    }
}
