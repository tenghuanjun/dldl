package com.igexin.push.b;

import com.igexin.push.b.b;
import com.igexin.push.config.SDKUrlConfig;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class e {
    static ThreadPoolExecutor a = null;
    private static final String f = "DT_DetectRunTask";
    private static final long g = 60;
    Future<d> b;
    d c;
    i d;
    boolean e;

    /* JADX INFO: renamed from: com.igexin.push.b.e$1, reason: invalid class name */
    final class AnonymousClass1 implements Callable<d> {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public d call() throws Throwable {
            Socket socket;
            Thread.currentThread().hashCode();
            e.a.getActiveCount();
            if (!Thread.currentThread().isInterrupted()) {
                Socket socket2 = null;
                try {
                    try {
                    } catch (Exception e) {
                        e = e;
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        Thread.currentThread().getName();
                        Thread.currentThread().hashCode();
                        e.a.getActiveCount();
                        return null;
                    }
                    synchronized (i.class) {
                    }
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    String[] strArrA = com.igexin.b.a.b.g.a(e.this.c.a());
                    socket = new Socket();
                    try {
                        socket.connect(new InetSocketAddress(strArrA[1], e.this.c.b), b.b);
                        long jCurrentTimeMillis2 = System.currentTimeMillis();
                        e.this.c.a("socket://" + socket.getInetAddress().getHostAddress() + ":" + e.this.c.b, jCurrentTimeMillis2 - jCurrentTimeMillis, jCurrentTimeMillis2);
                        e.this.c();
                        com.igexin.b.a.c.a.a("DT_DetectRunTask|detect " + e.this.c() + "|time = " + e.this.c.c(), new Object[0]);
                    } catch (Exception e2) {
                        e = e2;
                        socket2 = socket;
                        e.this.c();
                        com.igexin.b.a.c.a.a("DT_DetectRunTask|detect " + e.this.c() + "thread -->" + e.toString(), new Object[0]);
                        synchronized (i.class) {
                            if (e.this.d != null) {
                                e.this.c.b();
                                e.this.d.a(b.a.c, e.this.c);
                            }
                            if (socket2 != null && !socket2.isClosed()) {
                                socket2.close();
                            }
                            Thread.currentThread().hashCode();
                            e.a.getActiveCount();
                            return e.this.c;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (socket != null && !socket.isClosed()) {
                            try {
                                socket.close();
                            } catch (Exception unused) {
                            }
                        }
                        Thread.currentThread().hashCode();
                        e.a.getActiveCount();
                        throw th;
                    }
                    synchronized (i.class) {
                        if (e.this.d != null && !Thread.currentThread().isInterrupted()) {
                            e.this.d.a(b.a.a, e.this.c);
                        }
                        if (!socket.isClosed()) {
                            socket.close();
                        }
                        Thread.currentThread().hashCode();
                        e.a.getActiveCount();
                    }
                    Thread.currentThread().hashCode();
                    e.a.getActiveCount();
                } catch (Throwable th2) {
                    th = th2;
                    socket = socket2;
                }
            }
            return e.this.c;
        }
    }

    private void a(d dVar) {
        this.c = dVar;
    }

    private void a(boolean z) {
        this.e = z;
    }

    private static void d() {
        ThreadPoolExecutor threadPoolExecutor;
        if (SDKUrlConfig.getXfrAddress().length != 1 || (threadPoolExecutor = a) == null) {
            return;
        }
        try {
            threadPoolExecutor.shutdownNow();
            a = null;
        } catch (Throwable unused) {
        }
    }

    private d e() {
        return this.c;
    }

    private void f() {
        synchronized (i.class) {
            if (this.d != null) {
                if (a == null) {
                    a = new ThreadPoolExecutor(0, 12, g, TimeUnit.SECONDS, new SynchronousQueue());
                }
                this.b = a.submit(new AnonymousClass1());
            }
        }
    }

    private void g() {
        if (a == null) {
            a = new ThreadPoolExecutor(0, 12, g, TimeUnit.SECONDS, new SynchronousQueue());
        }
        this.b = a.submit(new AnonymousClass1());
    }

    private void h() {
        try {
            if (this.b == null || this.b.isCancelled() || this.b.isDone()) {
                return;
            }
            this.b.cancel(true);
            this.b = null;
        } catch (Exception unused) {
        }
    }

    public final void a() {
        c();
        com.igexin.b.a.c.a.a("DT_DetectRunTask|stop " + c() + " task", new Object[0]);
        h();
    }

    public final void a(i iVar) {
        synchronized (i.class) {
            this.d = iVar;
        }
    }

    public final void b() {
        a((i) null);
        h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String c() {
        return this.c.a() + "|" + this.c.a;
    }
}
