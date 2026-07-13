package com.bytedance.http.b;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
public final class h extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f396a;
    private final URI b;
    private final Map c;
    private SSLSocketFactory d;
    private int e;
    private Socket f;
    private volatile boolean g;

    public h(URI uri) {
        this(uri, null);
    }

    public h(URI uri, SSLSocketFactory sSLSocketFactory) {
        this.g = false;
        this.b = uri;
        this.d = sSLSocketFactory;
        this.f396a = new Object();
        this.c = new HashMap();
    }

    private void h() {
        synchronized (this.f396a) {
            if (this.g) {
                throw new CancellationException("Canceled");
            }
        }
    }

    @Override // com.bytedance.http.b.a
    public final Map a() {
        h();
        return this.c;
    }

    @Override // com.bytedance.http.b.a
    public final void a(int i) {
        h();
        this.e = i;
    }

    @Override // com.bytedance.http.b.a
    public final void a(String str, String str2) {
        h();
        this.c.put(str, str2);
    }

    @Override // com.bytedance.http.b.a
    public final void b() {
        Socket socket;
        InetSocketAddress inetSocketAddress;
        int i;
        Socket socket2;
        InetSocketAddress inetSocketAddress2;
        int i2;
        h();
        synchronized (this.f396a) {
            if (d()) {
                return;
            }
            String scheme = this.b.getScheme();
            int port = this.b.getPort();
            if (scheme == null) {
                throw new IllegalArgumentException("no protocol");
            }
            if (scheme.equals("ws")) {
                Socket socketCreateSocket = SocketFactory.getDefault().createSocket();
                this.f = socketCreateSocket;
                socketCreateSocket.setSoTimeout(0);
                if (port != -1) {
                    socket2 = this.f;
                    inetSocketAddress2 = new InetSocketAddress(this.b.getHost(), port);
                    i2 = this.e;
                    socket2.connect(inetSocketAddress2, i2);
                } else {
                    socket = this.f;
                    inetSocketAddress = new InetSocketAddress(this.b.getHost(), 80);
                    i = this.e;
                    socket.connect(inetSocketAddress, i);
                }
            } else {
                if (!scheme.equals("wss")) {
                    throw new IllegalArgumentException("unsupported protocol: " + scheme);
                }
                if (this.d == null) {
                    this.d = (SSLSocketFactory) SSLSocketFactory.getDefault();
                }
                Socket socketCreateSocket2 = this.d.createSocket();
                this.f = socketCreateSocket2;
                socketCreateSocket2.setSoTimeout(0);
                if (port != -1) {
                    socket2 = this.f;
                    inetSocketAddress2 = new InetSocketAddress(this.b.getHost(), port);
                    i2 = this.e;
                    socket2.connect(inetSocketAddress2, i2);
                } else {
                    socket = this.f;
                    inetSocketAddress = new InetSocketAddress(this.b.getHost(), 443);
                    i = this.e;
                    socket.connect(inetSocketAddress, i);
                }
            }
        }
    }

    @Override // com.bytedance.http.b.a
    public final void c() {
        synchronized (this.f396a) {
            try {
                try {
                    this.g = true;
                    if (d()) {
                        this.f.close();
                    }
                } catch (IOException e) {
                    c.c(e.getMessage());
                }
            } finally {
                this.f = null;
            }
        }
    }

    @Override // com.bytedance.http.b.a
    public final boolean d() {
        Socket socket = this.f;
        return socket != null && socket.isConnected();
    }

    @Override // com.bytedance.http.b.a
    public final InputStream e() {
        h();
        Socket socket = this.f;
        if (socket == null) {
            return null;
        }
        return socket.getInputStream();
    }

    @Override // com.bytedance.http.b.a
    public final OutputStream g() {
        h();
        Socket socket = this.f;
        if (socket == null) {
            return null;
        }
        return socket.getOutputStream();
    }
}
