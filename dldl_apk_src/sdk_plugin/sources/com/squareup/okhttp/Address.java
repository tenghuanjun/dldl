package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class Address {
    final Authenticator authenticator;
    final HostnameVerifier hostnameVerifier;
    final List<Protocol> protocols;
    final Proxy proxy;
    final SocketFactory socketFactory;
    final SSLSocketFactory sslSocketFactory;
    final String uriHost;
    final int uriPort;

    public Address(String str, int i, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, Authenticator authenticator, Proxy proxy, List<Protocol> list) throws UnknownHostException {
        if (str == null) {
            throw new NullPointerException("uriHost == null");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("uriPort <= 0: " + i);
        }
        if (authenticator == null) {
            throw new IllegalArgumentException("authenticator == null");
        }
        if (list == null) {
            throw new IllegalArgumentException("protocols == null");
        }
        this.proxy = proxy;
        this.uriHost = str;
        this.uriPort = i;
        this.socketFactory = socketFactory;
        this.sslSocketFactory = sSLSocketFactory;
        this.hostnameVerifier = hostnameVerifier;
        this.authenticator = authenticator;
        this.protocols = Util.immutableList(list);
    }

    public String getUriHost() {
        return this.uriHost;
    }

    public int getUriPort() {
        return this.uriPort;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    public Authenticator getAuthenticator() {
        return this.authenticator;
    }

    public List<Protocol> getProtocols() {
        return this.protocols;
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Address)) {
            return false;
        }
        Address address = (Address) obj;
        return Util.equal(this.proxy, address.proxy) && this.uriHost.equals(address.uriHost) && this.uriPort == address.uriPort && Util.equal(this.sslSocketFactory, address.sslSocketFactory) && Util.equal(this.hostnameVerifier, address.hostnameVerifier) && Util.equal(this.authenticator, address.authenticator) && Util.equal(this.protocols, address.protocols);
    }

    public int hashCode() {
        int iHashCode = (((527 + this.uriHost.hashCode()) * 31) + this.uriPort) * 31;
        SSLSocketFactory sSLSocketFactory = this.sslSocketFactory;
        int iHashCode2 = (iHashCode + (sSLSocketFactory != null ? sSLSocketFactory.hashCode() : 0)) * 31;
        HostnameVerifier hostnameVerifier = this.hostnameVerifier;
        int iHashCode3 = (((iHashCode2 + (hostnameVerifier != null ? hostnameVerifier.hashCode() : 0)) * 31) + this.authenticator.hashCode()) * 31;
        Proxy proxy = this.proxy;
        return ((iHashCode3 + (proxy != null ? proxy.hashCode() : 0)) * 31) + this.protocols.hashCode();
    }
}
