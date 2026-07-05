package com.squareup.okhttp;

import com.squareup.okhttp.internal.http.RouteSelector;
import java.net.InetSocketAddress;
import java.net.Proxy;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class Route {
    final Address address;
    final InetSocketAddress inetSocketAddress;
    final Proxy proxy;
    final String tlsVersion;

    public Route(Address address, Proxy proxy, InetSocketAddress inetSocketAddress, String str) {
        if (address == null) {
            throw new NullPointerException("address == null");
        }
        if (proxy == null) {
            throw new NullPointerException("proxy == null");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        }
        if (str == null) {
            throw new NullPointerException("tlsVersion == null");
        }
        this.address = address;
        this.proxy = proxy;
        this.inetSocketAddress = inetSocketAddress;
        this.tlsVersion = str;
    }

    public Address getAddress() {
        return this.address;
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public InetSocketAddress getSocketAddress() {
        return this.inetSocketAddress;
    }

    public String getTlsVersion() {
        return this.tlsVersion;
    }

    boolean supportsNpn() {
        return !this.tlsVersion.equals(RouteSelector.SSL_V3);
    }

    public boolean requiresTunnel() {
        return this.address.sslSocketFactory != null && this.proxy.type() == Proxy.Type.HTTP;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Route)) {
            return false;
        }
        Route route = (Route) obj;
        return this.address.equals(route.address) && this.proxy.equals(route.proxy) && this.inetSocketAddress.equals(route.inetSocketAddress) && this.tlsVersion.equals(route.tlsVersion);
    }

    public int hashCode() {
        return ((((((527 + this.address.hashCode()) * 31) + this.proxy.hashCode()) * 31) + this.inetSocketAddress.hashCode()) * 31) + this.tlsVersion.hashCode();
    }
}
