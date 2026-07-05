package com.igexin.b.a.b.a.a;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import com.igexin.push.c.c;
import com.igexin.push.core.d;
import com.igexin.push.f.n;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d {
    static final Object i = new Object();
    private static final String m = "GS-M";
    Socket a;
    e b;
    g c;
    b d;
    boolean e;
    protected Lock f;
    protected Condition g;
    final List<f> h;
    protected ConcurrentLinkedQueue<f> j;
    long k;
    final Comparator<f> l;
    private com.igexin.b.a.b.d n;
    private final AtomicBoolean o;
    private final Handler p;

    /* JADX INFO: renamed from: com.igexin.b.a.b.a.a.d$1, reason: invalid class name */
    final class AnonymousClass1 implements com.igexin.b.a.b.a.a.a.d {
        AnonymousClass1() {
        }

        @Override // com.igexin.b.a.b.a.a.a.a
        public final void a() {
            d.this.p.sendEmptyMessage(j.f - 1);
        }

        @Override // com.igexin.b.a.b.a.a.a.d
        public final void a(Exception exc) {
            com.igexin.b.a.c.a.a("GS-M|c ex = " + exc.toString(), new Object[0]);
            d.this.k();
        }

        @Override // com.igexin.b.a.b.a.a.a.d
        public final void a(Socket socket) {
            Message messageObtain = Message.obtain();
            messageObtain.obj = socket;
            messageObtain.what = j.c - 1;
            d.this.p.sendMessage(messageObtain);
        }

        @Override // com.igexin.b.a.b.a.a.a.d
        public final void b() {
            d.this.p.sendEmptyMessage(j.e - 1);
        }
    }

    /* JADX INFO: renamed from: com.igexin.b.a.b.a.a.d$2, reason: invalid class name */
    final class AnonymousClass2 implements com.igexin.b.a.b.a.a.a.b {
        AnonymousClass2() {
        }

        @Override // com.igexin.b.a.b.a.a.a.a
        public final void a() {
            d.this.p.sendEmptyMessage(j.f - 1);
        }

        @Override // com.igexin.b.a.b.a.a.a.b
        public final void a(Exception exc) {
            com.igexin.b.a.c.a.a("GS-M|r ex = " + exc.toString(), new Object[0]);
            if (exc.getMessage() == null || !exc.getMessage().equals("end of stream")) {
                d.this.k();
            } else {
                c.b.a.b();
            }
        }

        @Override // com.igexin.b.a.b.a.a.a.b
        public final void b() {
        }
    }

    /* JADX INFO: renamed from: com.igexin.b.a.b.a.a.d$3, reason: invalid class name */
    final class AnonymousClass3 implements com.igexin.b.a.b.a.a.a.c {
        AnonymousClass3() {
        }

        @Override // com.igexin.b.a.b.a.a.a.a
        public final void a() {
            d.this.p.sendEmptyMessage(j.f - 1);
        }

        @Override // com.igexin.b.a.b.a.a.a.c
        public final void a(f fVar) {
            if (n.l()) {
                return;
            }
            d dVar = d.this;
            if (fVar.B <= 0 || fVar.G == null) {
                fVar.k();
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            fVar.z = jCurrentTimeMillis;
            synchronized (d.i) {
                dVar.h.add(fVar);
                Collections.sort(dVar.h, dVar.l);
                fVar.d.getClass().getSimpleName();
                dVar.k = TimeUnit.SECONDS.toMillis(dVar.h.get(0).B);
                if (dVar.k > 0 && dVar.h.size() == 1) {
                    long j = com.igexin.b.a.d.g.D;
                    com.igexin.b.a.c.a.a("GS-M|add : " + fVar.toString() + " --- " + fVar.d.getClass().getName() + " set alarm delay = " + (dVar.k + com.igexin.b.a.d.g.D), new Object[0]);
                    com.igexin.b.a.b.e.a().b(jCurrentTimeMillis + dVar.k + com.igexin.b.a.d.g.D);
                }
                dVar.h.size();
            }
        }

        @Override // com.igexin.b.a.b.a.a.a.c
        public final void a(Exception exc) {
            com.igexin.b.a.c.a.a("GS-M|w ex = " + exc.toString(), new Object[0]);
            d.this.k();
        }
    }

    static class a {
        private static final d a = new d(0);

        private a() {
        }
    }

    private d() {
        this.o = new AtomicBoolean(false);
        this.f = new ReentrantLock();
        this.g = this.f.newCondition();
        this.h = new ArrayList();
        this.j = new ConcurrentLinkedQueue<>();
        this.l = new Comparator<f>() { // from class: com.igexin.b.a.b.a.a.d.4
            private static int a(f fVar, f fVar2) {
                if (fVar == null) {
                    return 1;
                }
                if (fVar2 == null) {
                    return -1;
                }
                if (Build.VERSION.SDK_INT >= 19) {
                    return Long.compare(((long) fVar.B) + fVar.z, ((long) fVar2.B) + fVar2.z);
                }
                if (((long) fVar.B) + fVar.z > ((long) fVar2.B) + fVar2.z) {
                    return 1;
                }
                return ((long) fVar.B) + fVar.z < ((long) fVar2.B) + fVar2.z ? -1 : 0;
            }

            @Override // java.util.Comparator
            public final /* bridge */ /* synthetic */ int compare(f fVar, f fVar2) {
                f fVar3 = fVar;
                f fVar4 = fVar2;
                if (fVar3 == null) {
                    return 1;
                }
                if (fVar4 == null) {
                    return -1;
                }
                if (Build.VERSION.SDK_INT >= 19) {
                    return Long.compare(((long) fVar3.B) + fVar3.z, ((long) fVar4.B) + fVar4.z);
                }
                if (((long) fVar3.B) + fVar3.z > ((long) fVar4.B) + fVar4.z) {
                    return 1;
                }
                return ((long) fVar3.B) + fVar3.z < ((long) fVar4.B) + fVar4.z ? -1 : 0;
            }
        };
        this.p = d.a.a.f;
    }

    /* synthetic */ d(byte b) {
        this();
    }

    public static d a() {
        return a.a;
    }

    private void b(f fVar) {
        if (fVar.B <= 0 || fVar.G == null) {
            fVar.k();
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        fVar.z = jCurrentTimeMillis;
        synchronized (i) {
            this.h.add(fVar);
            Collections.sort(this.h, this.l);
            fVar.d.getClass().getSimpleName();
            this.k = TimeUnit.SECONDS.toMillis(this.h.get(0).B);
            if (this.k > 0 && this.h.size() == 1) {
                long j = com.igexin.b.a.d.g.D;
                com.igexin.b.a.c.a.a("GS-M|add : " + fVar.toString() + " --- " + fVar.d.getClass().getName() + " set alarm delay = " + (this.k + com.igexin.b.a.d.g.D), new Object[0]);
                com.igexin.b.a.b.e.a().b(jCurrentTimeMillis + this.k + com.igexin.b.a.d.g.D);
            }
            this.h.size();
        }
    }

    private void b(Socket socket) throws Exception {
        this.b = new e(new h(socket.getInputStream()), this.n);
        this.b.k = new AnonymousClass2();
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) this.b, true);
    }

    private void c(Socket socket) throws Exception {
        this.c = new g(new i(socket.getOutputStream()), this.n);
        this.c.j = new AnonymousClass3();
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) this.c, true);
    }

    private static void j() {
        com.igexin.push.core.d unused = d.a.a;
        com.igexin.push.d.a.a(j.a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.o.getAndSet(true)) {
            return;
        }
        this.p.sendEmptyMessage(j.a - 1);
    }

    private void l() {
        i();
        if ((this.d == null && this.c == null && this.b == null) || h()) {
            b();
        } else {
            g();
        }
    }

    private void m() {
        Socket socket = this.a;
        boolean z = (socket == null || socket.isClosed()) ? false : true;
        if (!z && this.d == null) {
            com.igexin.b.a.c.a.a("GS-M|disconnect = true, reconnect", new Object[0]);
            this.d = new b(new AnonymousClass1());
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) this.d, true);
        } else {
            com.igexin.b.a.c.a.a("GS-Mstart connect, isConnected = " + z + ", ctask = " + this.d, new Object[0]);
        }
    }

    private void n() {
        g gVar = this.c;
        if (gVar != null) {
            gVar.l = null;
            this.c = null;
        }
        e eVar = this.b;
        if (eVar != null) {
            eVar.j = null;
            this.b = null;
        }
        this.d = null;
        this.a = null;
    }

    private void o() {
        if (!h() || this.e) {
            return;
        }
        b();
        this.e = true;
    }

    private boolean p() {
        Socket socket = this.a;
        return (socket == null || socket.isClosed()) ? false : true;
    }

    final void a(f fVar) {
        try {
            try {
                this.f.lock();
                this.j.offer(fVar);
                this.g.signalAll();
                try {
                    this.f.unlock();
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
            }
        } catch (Exception unused3) {
            this.f.unlock();
        } catch (Throwable th) {
            try {
                this.f.unlock();
            } catch (Exception unused4) {
            }
            throw th;
        }
    }

    public final void a(com.igexin.b.a.b.d dVar) {
        this.n = dVar;
        e eVar = this.b;
        if (eVar != null) {
            eVar.l = dVar;
        }
        g gVar = this.c;
        if (gVar != null) {
            gVar.k = dVar;
        }
    }

    public final void a(String str) {
        boolean z;
        if (n.l()) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (i) {
            com.igexin.b.a.c.a.a("GS-M|" + str + " -- resp", new Object[0]);
            Iterator<f> it = this.h.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                f next = it.next();
                if (next.G.a(jCurrentTimeMillis, next)) {
                    next.k();
                    next.G.c();
                    z = true;
                    it.remove();
                    break;
                }
                long jB = next.G.b(jCurrentTimeMillis, next);
                if (this.k < 0 || this.k > jB) {
                    this.k = jB;
                }
            }
            com.igexin.b.a.b.e.a().d();
            if (z) {
                com.igexin.b.a.c.a.a("GS-M|time out", new Object[0]);
                e();
                return;
            }
            if (this.h.size() > 0) {
                f fVar = this.h.get(0);
                fVar.k();
                com.igexin.b.a.b.e.a().a((Object) fVar);
                this.h.remove(fVar);
                fVar.d.getClass().getSimpleName();
                com.igexin.b.a.c.a.a("GS-M|remove : " + fVar.toString() + " -- " + fVar.d.getClass().getSimpleName(), new Object[0]);
            }
            int size = this.h.size();
            com.igexin.b.a.c.a.a("GS-M|r, size = ".concat(String.valueOf(size)), new Object[0]);
            if (size > 0 && this.k > 0) {
                com.igexin.b.a.c.a.a("GS-M|set alarm = " + this.k, new Object[0]);
                com.igexin.b.a.b.e.a().b(jCurrentTimeMillis + this.k + com.igexin.b.a.d.g.D);
            }
        }
    }

    final void a(Socket socket) {
        try {
            if (this.d.g()) {
                return;
            }
            this.a = socket;
            this.b = new e(new h(socket.getInputStream()), this.n);
            this.b.k = new AnonymousClass2();
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) this.b, true);
            this.c = new g(new i(socket.getOutputStream()), this.n);
            this.c.j = new AnonymousClass3();
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) this.c, true);
        } catch (Exception e) {
            com.igexin.b.a.c.a.a("GS-M|" + e.toString(), new Object[0]);
            k();
        }
    }

    public final void b() {
        this.o.set(false);
        com.igexin.push.core.d unused = d.a.a;
        com.igexin.push.d.a.a(j.b);
    }

    public final synchronized void c() {
        this.p.sendEmptyMessage(j.g - 1);
    }

    final synchronized void d() {
        this.p.sendEmptyMessage(j.d - 1);
        this.e = false;
    }

    public final synchronized void e() {
        com.igexin.b.a.c.a.a("GS-M|alarm timeout disconnect", new Object[0]);
        k();
    }

    public final synchronized void f() {
        com.igexin.b.a.c.a.a("GS-M|redirect disconnect", new Object[0]);
        k();
    }

    final void g() {
        com.igexin.b.a.c.a.a("GS-M|disconnect", new Object[0]);
        b bVar = this.d;
        if (bVar != null) {
            bVar.c_();
        }
        g gVar = this.c;
        if (gVar != null) {
            gVar.c_();
        }
        e eVar = this.b;
        if (eVar != null) {
            eVar.c_();
        }
        Socket socket = this.a;
        if (socket != null) {
            try {
                if (socket.isClosed()) {
                    return;
                }
                this.a.close();
            } catch (Exception unused) {
            }
        }
    }

    final boolean h() {
        b bVar = this.d;
        if (bVar != null && !bVar.f) {
            return false;
        }
        e eVar = this.b;
        if (eVar != null && !eVar.f) {
            return false;
        }
        g gVar = this.c;
        if (gVar != null && !gVar.f) {
            return false;
        }
        n();
        return true;
    }

    final void i() {
        if (!n.l()) {
            com.igexin.b.a.b.e.a().d();
            com.igexin.b.a.c.a.a("GS-M|cancel alrm", new Object[0]);
            synchronized (i) {
                if (!this.h.isEmpty()) {
                    Iterator<f> it = this.h.iterator();
                    while (it.hasNext()) {
                        it.next().k();
                    }
                    this.h.clear();
                }
            }
        }
        if (this.j.isEmpty()) {
            return;
        }
        Iterator<f> it2 = this.j.iterator();
        while (it2.hasNext()) {
            it2.next().k();
        }
        this.j.clear();
    }
}
