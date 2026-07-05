package com.squareup.okhttp;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.RouteDatabase;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.AuthenticatorAdapter;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.Transport;
import com.squareup.okhttp.internal.tls.OkHostnameVerifier;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class OkHttpClient implements Cloneable {
    private static SSLSocketFactory defaultSslSocketFactory;
    private Authenticator authenticator;
    private Cache cache;
    private int connectTimeout;
    private ConnectionPool connectionPool;
    private CookieHandler cookieHandler;
    private HostnameVerifier hostnameVerifier;
    private InternalCache internalCache;
    private List<Protocol> protocols;
    private Proxy proxy;
    private ProxySelector proxySelector;
    private int readTimeout;
    private SocketFactory socketFactory;
    private SSLSocketFactory sslSocketFactory;
    private int writeTimeout;
    private boolean followSslRedirects = true;
    private final RouteDatabase routeDatabase = new RouteDatabase();
    private Dispatcher dispatcher = new Dispatcher();

    static {
        Internal.instance = new Internal() { // from class: com.squareup.okhttp.OkHttpClient.1
            @Override // com.squareup.okhttp.internal.Internal
            public Transport newTransport(Connection connection, HttpEngine httpEngine) throws IOException {
                return connection.newTransport(httpEngine);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public boolean clearOwner(Connection connection) {
                return connection.clearOwner();
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void closeIfOwnedBy(Connection connection, Object obj) throws IOException {
                connection.closeIfOwnedBy(obj);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public int recycleCount(Connection connection) {
                return connection.recycleCount();
            }

            @Override // com.squareup.okhttp.internal.Internal
            public Object getOwner(Connection connection) {
                return connection.getOwner();
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void setProtocol(Connection connection, Protocol protocol) {
                connection.setProtocol(protocol);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void setOwner(Connection connection, HttpEngine httpEngine) {
                connection.setOwner(httpEngine);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void connect(Connection connection, int i, int i2, int i3, Request request) throws IOException {
                connection.connect(i, i2, i3, request);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public boolean isConnected(Connection connection) {
                return connection.isConnected();
            }

            @Override // com.squareup.okhttp.internal.Internal
            public boolean isSpdy(Connection connection) {
                return connection.isSpdy();
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void setTimeouts(Connection connection, int i, int i2) throws IOException {
                connection.setTimeouts(i, i2);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public boolean isReadable(Connection connection) {
                return connection.isReadable();
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void addLine(Headers.Builder builder, String str) {
                builder.addLine(str);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void setCache(OkHttpClient okHttpClient, InternalCache internalCache) {
                okHttpClient.setInternalCache(internalCache);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public InternalCache internalCache(OkHttpClient okHttpClient) {
                return okHttpClient.internalCache();
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void recycle(ConnectionPool connectionPool, Connection connection) {
                connectionPool.recycle(connection);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void share(ConnectionPool connectionPool, Connection connection) {
                connectionPool.share(connection);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public RouteDatabase routeDatabase(OkHttpClient okHttpClient) {
                return okHttpClient.routeDatabase;
            }
        };
    }

    public void setConnectTimeout(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0");
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        long millis = timeUnit.toMillis(j);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException("Timeout too large.");
        }
        this.connectTimeout = (int) millis;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setReadTimeout(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0");
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        long millis = timeUnit.toMillis(j);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException("Timeout too large.");
        }
        this.readTimeout = (int) millis;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public void setWriteTimeout(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0");
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        long millis = timeUnit.toMillis(j);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException("Timeout too large.");
        }
        this.writeTimeout = (int) millis;
    }

    public int getWriteTimeout() {
        return this.writeTimeout;
    }

    public OkHttpClient setProxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public OkHttpClient setProxySelector(ProxySelector proxySelector) {
        this.proxySelector = proxySelector;
        return this;
    }

    public ProxySelector getProxySelector() {
        return this.proxySelector;
    }

    public OkHttpClient setCookieHandler(CookieHandler cookieHandler) {
        this.cookieHandler = cookieHandler;
        return this;
    }

    public CookieHandler getCookieHandler() {
        return this.cookieHandler;
    }

    void setInternalCache(InternalCache internalCache) {
        this.internalCache = internalCache;
        this.cache = null;
    }

    InternalCache internalCache() {
        return this.internalCache;
    }

    public OkHttpClient setCache(Cache cache) {
        this.cache = cache;
        this.internalCache = cache != null ? cache.internalCache : null;
        return this;
    }

    public Cache getCache() {
        return this.cache;
    }

    public OkHttpClient setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
        return this;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public OkHttpClient setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.sslSocketFactory = sSLSocketFactory;
        return this;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    public OkHttpClient setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    public OkHttpClient setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
        return this;
    }

    public Authenticator getAuthenticator() {
        return this.authenticator;
    }

    public OkHttpClient setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        return this;
    }

    public ConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    public OkHttpClient setFollowSslRedirects(boolean z) {
        this.followSslRedirects = z;
        return this;
    }

    public boolean getFollowSslRedirects() {
        return this.followSslRedirects;
    }

    RouteDatabase getRoutesDatabase() {
        return this.routeDatabase;
    }

    public OkHttpClient setDispatcher(Dispatcher dispatcher) {
        if (dispatcher == null) {
            throw new IllegalArgumentException("dispatcher == null");
        }
        this.dispatcher = dispatcher;
        return this;
    }

    public Dispatcher getDispatcher() {
        return this.dispatcher;
    }

    public OkHttpClient setProtocols(List<Protocol> list) {
        List listImmutableList = Util.immutableList(list);
        if (!listImmutableList.contains(Protocol.HTTP_1_1)) {
            throw new IllegalArgumentException("protocols doesn't contain http/1.1: " + listImmutableList);
        }
        if (listImmutableList.contains(null)) {
            throw new IllegalArgumentException("protocols must not contain null");
        }
        this.protocols = Util.immutableList(listImmutableList);
        return this;
    }

    public List<Protocol> getProtocols() {
        return this.protocols;
    }

    public Call newCall(Request request) {
        return new Call(copyWithDefaults(), this.dispatcher, request);
    }

    public OkHttpClient cancel(Object obj) {
        this.dispatcher.cancel(obj);
        return this;
    }

    OkHttpClient copyWithDefaults() {
        OkHttpClient okHttpClientM28clone = m28clone();
        if (okHttpClientM28clone.proxySelector == null) {
            okHttpClientM28clone.proxySelector = ProxySelector.getDefault();
        }
        if (okHttpClientM28clone.cookieHandler == null) {
            okHttpClientM28clone.cookieHandler = CookieHandler.getDefault();
        }
        if (okHttpClientM28clone.socketFactory == null) {
            okHttpClientM28clone.socketFactory = SocketFactory.getDefault();
        }
        if (okHttpClientM28clone.sslSocketFactory == null) {
            okHttpClientM28clone.sslSocketFactory = getDefaultSSLSocketFactory();
        }
        if (okHttpClientM28clone.hostnameVerifier == null) {
            okHttpClientM28clone.hostnameVerifier = OkHostnameVerifier.INSTANCE;
        }
        if (okHttpClientM28clone.authenticator == null) {
            okHttpClientM28clone.authenticator = AuthenticatorAdapter.INSTANCE;
        }
        if (okHttpClientM28clone.connectionPool == null) {
            okHttpClientM28clone.connectionPool = ConnectionPool.getDefault();
        }
        if (okHttpClientM28clone.protocols == null) {
            okHttpClientM28clone.protocols = Util.immutableList(Protocol.HTTP_2, Protocol.SPDY_3, Protocol.HTTP_1_1);
        }
        return okHttpClientM28clone;
    }

    private synchronized SSLSocketFactory getDefaultSSLSocketFactory() {
        if (defaultSslSocketFactory == null) {
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, null, null);
                defaultSslSocketFactory = sSLContext.getSocketFactory();
            } catch (GeneralSecurityException unused) {
                throw new AssertionError();
            }
        }
        return defaultSslSocketFactory;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public OkHttpClient m28clone() {
        try {
            return (OkHttpClient) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }
}
