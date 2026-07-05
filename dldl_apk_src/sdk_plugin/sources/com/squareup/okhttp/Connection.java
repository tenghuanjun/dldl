package com.squareup.okhttp;

import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.http.HttpConnection;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.HttpTransport;
import com.squareup.okhttp.internal.http.OkHeaders;
import com.squareup.okhttp.internal.http.SpdyTransport;
import com.squareup.okhttp.internal.http.Transport;
import com.squareup.okhttp.internal.spdy.SpdyConnection;
import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;
import java.net.URL;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class Connection {
    private Handshake handshake;
    private HttpConnection httpConnection;
    private long idleStartTimeNs;
    private Object owner;
    private final ConnectionPool pool;
    private int recycleCount;
    private final Route route;
    private Socket socket;
    private SpdyConnection spdyConnection;
    private boolean connected = false;
    private Protocol protocol = Protocol.HTTP_1_1;

    public Connection(ConnectionPool connectionPool, Route route) {
        this.pool = connectionPool;
        this.route = route;
    }

    Object getOwner() {
        Object obj;
        synchronized (this.pool) {
            obj = this.owner;
        }
        return obj;
    }

    void setOwner(Object obj) {
        if (isSpdy()) {
            return;
        }
        synchronized (this.pool) {
            if (this.owner != null) {
                throw new IllegalStateException("Connection already has an owner!");
            }
            this.owner = obj;
        }
    }

    boolean clearOwner() {
        synchronized (this.pool) {
            if (this.owner == null) {
                return false;
            }
            this.owner = null;
            return true;
        }
    }

    void closeIfOwnedBy(Object obj) throws IOException {
        if (isSpdy()) {
            throw new IllegalStateException();
        }
        synchronized (this.pool) {
            if (this.owner != obj) {
                return;
            }
            this.owner = null;
            this.socket.close();
        }
    }

    void connect(int i, int i2, int i3, Request request) throws IOException {
        if (this.connected) {
            throw new IllegalStateException("already connected");
        }
        if (this.route.proxy.type() != Proxy.Type.HTTP) {
            this.socket = new Socket(this.route.proxy);
        } else {
            this.socket = this.route.address.socketFactory.createSocket();
        }
        this.socket.setSoTimeout(i2);
        Platform.get().connectSocket(this.socket, this.route.inetSocketAddress, i);
        if (this.route.address.sslSocketFactory != null) {
            upgradeToTls(request, i2, i3);
        } else {
            this.httpConnection = new HttpConnection(this.pool, this, this.socket);
        }
        this.connected = true;
    }

    private void upgradeToTls(Request request, int i, int i2) throws IOException {
        String selectedProtocol;
        Platform platform = Platform.get();
        if (request != null) {
            makeTunnel(request, i, i2);
        }
        Socket socketCreateSocket = this.route.address.sslSocketFactory.createSocket(this.socket, this.route.address.uriHost, this.route.address.uriPort, true);
        this.socket = socketCreateSocket;
        SSLSocket sSLSocket = (SSLSocket) socketCreateSocket;
        platform.configureTls(sSLSocket, this.route.address.uriHost, this.route.tlsVersion);
        boolean zSupportsNpn = this.route.supportsNpn();
        if (zSupportsNpn) {
            platform.setProtocols(sSLSocket, this.route.address.protocols);
        }
        sSLSocket.startHandshake();
        if (!this.route.address.hostnameVerifier.verify(this.route.address.uriHost, sSLSocket.getSession())) {
            throw new IOException("Hostname '" + this.route.address.uriHost + "' was not verified");
        }
        this.handshake = Handshake.get(sSLSocket.getSession());
        if (zSupportsNpn && (selectedProtocol = platform.getSelectedProtocol(sSLSocket)) != null) {
            this.protocol = Protocol.get(selectedProtocol);
        }
        if (this.protocol == Protocol.SPDY_3 || this.protocol == Protocol.HTTP_2) {
            sSLSocket.setSoTimeout(0);
            SpdyConnection spdyConnectionBuild = new SpdyConnection.Builder(this.route.address.getUriHost(), true, this.socket).protocol(this.protocol).build();
            this.spdyConnection = spdyConnectionBuild;
            spdyConnectionBuild.sendConnectionPreface();
            return;
        }
        this.httpConnection = new HttpConnection(this.pool, this, this.socket);
    }

    boolean isConnected() {
        return this.connected;
    }

    public Route getRoute() {
        return this.route;
    }

    public Socket getSocket() {
        return this.socket;
    }

    boolean isAlive() {
        return (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) ? false : true;
    }

    boolean isReadable() {
        HttpConnection httpConnection = this.httpConnection;
        if (httpConnection != null) {
            return httpConnection.isReadable();
        }
        return true;
    }

    void resetIdleStartTime() {
        if (this.spdyConnection != null) {
            throw new IllegalStateException("spdyConnection != null");
        }
        this.idleStartTimeNs = System.nanoTime();
    }

    boolean isIdle() {
        SpdyConnection spdyConnection = this.spdyConnection;
        return spdyConnection == null || spdyConnection.isIdle();
    }

    boolean isExpired(long j) {
        return getIdleStartTimeNs() < System.nanoTime() - j;
    }

    long getIdleStartTimeNs() {
        SpdyConnection spdyConnection = this.spdyConnection;
        return spdyConnection == null ? this.idleStartTimeNs : spdyConnection.getIdleStartTimeNs();
    }

    public Handshake getHandshake() {
        return this.handshake;
    }

    Transport newTransport(HttpEngine httpEngine) throws IOException {
        return this.spdyConnection != null ? new SpdyTransport(httpEngine, this.spdyConnection) : new HttpTransport(httpEngine, this.httpConnection);
    }

    boolean isSpdy() {
        return this.spdyConnection != null;
    }

    public Protocol getProtocol() {
        return this.protocol;
    }

    void setProtocol(Protocol protocol) {
        if (protocol == null) {
            throw new IllegalArgumentException("protocol == null");
        }
        this.protocol = protocol;
    }

    void setTimeouts(int i, int i2) throws IOException {
        if (!this.connected) {
            throw new IllegalStateException("setTimeouts - not connected");
        }
        if (this.httpConnection != null) {
            this.socket.setSoTimeout(i);
            this.httpConnection.setTimeouts(i, i2);
        }
    }

    void incrementRecycleCount() {
        this.recycleCount++;
    }

    int recycleCount() {
        return this.recycleCount;
    }

    private void makeTunnel(Request request, int i, int i2) throws IOException {
        HttpConnection httpConnection = new HttpConnection(this.pool, this, this.socket);
        httpConnection.setTimeouts(i, i2);
        URL url = request.url();
        String str = "CONNECT " + url.getHost() + ":" + url.getPort() + " HTTP/1.1";
        do {
            httpConnection.writeRequest(request.headers(), str);
            httpConnection.flush();
            Response responseBuild = httpConnection.readResponse().request(request).build();
            httpConnection.emptyResponseBody();
            int iCode = responseBuild.code();
            if (iCode == 200) {
                if (httpConnection.bufferSize() > 0) {
                    throw new IOException("TLS tunnel buffered too many bytes!");
                }
                return;
            } else if (iCode == 407) {
                request = OkHeaders.processAuthHeader(this.route.address.authenticator, responseBuild, this.route.proxy);
            } else {
                throw new IOException("Unexpected response code for CONNECT: " + responseBuild.code());
            }
        } while (request != null);
        throw new IOException("Failed to authenticate with proxy");
    }
}
