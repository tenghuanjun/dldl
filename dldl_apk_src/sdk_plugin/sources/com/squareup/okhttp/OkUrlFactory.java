package com.squareup.okhttp;

import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.huc.CacheAdapter;
import com.squareup.okhttp.internal.huc.HttpURLConnectionImpl;
import com.squareup.okhttp.internal.huc.HttpsURLConnectionImpl;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ResponseCache;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class OkUrlFactory implements URLStreamHandlerFactory, Cloneable {
    private final OkHttpClient client;

    public OkUrlFactory(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    public OkHttpClient client() {
        return this.client;
    }

    OkUrlFactory setResponseCache(ResponseCache responseCache) {
        this.client.setInternalCache(responseCache != null ? new CacheAdapter(responseCache) : null);
        return this;
    }

    ResponseCache getResponseCache() {
        InternalCache internalCache = this.client.internalCache();
        if (internalCache instanceof CacheAdapter) {
            return ((CacheAdapter) internalCache).getDelegate();
        }
        return null;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public OkUrlFactory m29clone() {
        return new OkUrlFactory(this.client.m28clone());
    }

    public HttpURLConnection open(URL url) {
        return open(url, this.client.getProxy());
    }

    HttpURLConnection open(URL url, Proxy proxy) {
        String protocol = url.getProtocol();
        OkHttpClient okHttpClientCopyWithDefaults = this.client.copyWithDefaults();
        okHttpClientCopyWithDefaults.setProxy(proxy);
        if (protocol.equals(IDataSource.SCHEME_HTTP_TAG)) {
            return new HttpURLConnectionImpl(url, okHttpClientCopyWithDefaults);
        }
        if (protocol.equals(IDataSource.SCHEME_HTTPS_TAG)) {
            return new HttpsURLConnectionImpl(url, okHttpClientCopyWithDefaults);
        }
        throw new IllegalArgumentException("Unexpected protocol: " + protocol);
    }

    @Override // java.net.URLStreamHandlerFactory
    public URLStreamHandler createURLStreamHandler(final String str) {
        if (str.equals(IDataSource.SCHEME_HTTP_TAG) || str.equals(IDataSource.SCHEME_HTTPS_TAG)) {
            return new URLStreamHandler() { // from class: com.squareup.okhttp.OkUrlFactory.1
                @Override // java.net.URLStreamHandler
                protected URLConnection openConnection(URL url) {
                    return OkUrlFactory.this.open(url);
                }

                @Override // java.net.URLStreamHandler
                protected URLConnection openConnection(URL url, Proxy proxy) {
                    return OkUrlFactory.this.open(url, proxy);
                }

                @Override // java.net.URLStreamHandler
                protected int getDefaultPort() {
                    if (str.equals(IDataSource.SCHEME_HTTP_TAG)) {
                        return 80;
                    }
                    if (str.equals(IDataSource.SCHEME_HTTPS_TAG)) {
                        return 443;
                    }
                    throw new AssertionError();
                }
            };
        }
        return null;
    }
}
