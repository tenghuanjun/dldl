package com.igexin.b.a.d;

import android.os.PowerManager;
import com.igexin.b.a.d.a.d;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public abstract class f extends b implements com.igexin.b.a.d.a.a, com.igexin.b.a.d.a.f {
    protected static g H;
    public int A;
    public int B;
    public int C;
    public int D;
    public Exception E;
    public Object F;
    public com.igexin.b.a.d.a.g G;
    protected final ReentrantLock I;
    protected final Condition J;
    protected Thread K;
    protected volatile boolean L;
    PowerManager.WakeLock M;
    int N;
    protected com.igexin.b.a.d.a.d O;
    private byte a;
    protected volatile boolean m;
    protected volatile boolean n;
    protected volatile boolean o;
    protected volatile boolean p;
    protected volatile boolean q;
    protected volatile boolean r;
    protected volatile boolean s;
    protected volatile boolean t;
    protected volatile boolean u;
    protected volatile boolean v;
    protected volatile long w;
    volatile int x;
    public long z;

    public f(int i) {
        this(i, (byte) 0);
    }

    private f(int i, byte b) {
        this.C = i;
        this.O = null;
        this.I = new ReentrantLock();
        this.J = this.I.newCondition();
    }

    private int A() {
        return this.a & 15;
    }

    private boolean B() {
        byte b = this.a;
        return (b >> 4) > (b & 15);
    }

    private Thread C() {
        return this.K;
    }

    private static void D() throws Exception {
    }

    private void E() {
        this.n = true;
    }

    private Object F() {
        return this.F;
    }

    private com.igexin.b.a.d.a.d G() {
        return this.O;
    }

    private void a(int i, TimeUnit timeUnit) {
        this.v = false;
        this.E = null;
        this.w = 0L;
        byte b = this.a;
        this.a = (byte) (b + ((b & 15) < 15 ? (byte) 1 : (byte) 0));
        this.m = false;
        this.q = false;
        this.t = false;
        a(i, timeUnit);
    }

    private void a(long j) {
        this.z = j;
    }

    private void a(PowerManager.WakeLock wakeLock) {
        this.M = wakeLock;
    }

    private boolean a(Object obj) {
        if (!this.m) {
            return false;
        }
        this.q = false;
        this.n = false;
        this.m = false;
        this.F = obj;
        return true;
    }

    private void b(int i) {
        if (i != this.D) {
            this.D = i;
            H.s.b(this);
        }
    }

    private void b(Object obj) {
        this.F = obj;
    }

    private ReentrantLock g() {
        ReentrantLock reentrantLock = this.I;
        if (reentrantLock != null) {
            return reentrantLock;
        }
        throw new NullPointerException();
    }

    private PowerManager.WakeLock h() {
        return this.M;
    }

    private void p() {
        this.z = System.currentTimeMillis();
    }

    private boolean q() {
        return this.v;
    }

    private int r() {
        this.N = a(TimeUnit.MILLISECONDS) > 0 ? this.N | DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25 : this.N & 1090519038;
        return this.N;
    }

    private void s() {
        this.N++;
        this.N &= 1090519038;
    }

    private long t() {
        return this.w - System.currentTimeMillis();
    }

    private boolean u() {
        return this.q;
    }

    private boolean v() {
        return this.u;
    }

    private boolean w() {
        return this.m;
    }

    private boolean x() {
        return this.s;
    }

    private boolean y() {
        return this.t;
    }

    private void z() {
        this.v = false;
        this.E = null;
        this.w = 0L;
        byte b = this.a;
        this.a = (byte) (b + ((b & 15) < 15 ? (byte) 1 : (byte) 0));
        this.m = false;
        this.q = false;
        this.t = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(long r5, java.util.concurrent.TimeUnit r7) {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 <= 0) goto L26
            com.igexin.b.a.d.g r1 = com.igexin.b.a.d.f.H
            com.igexin.b.a.d.e<com.igexin.b.a.d.f> r1 = r1.s
            int r1 = r1.a(r4, r5, r7)
            if (r1 == r0) goto L27
            switch(r1) {
                case -2: goto L24;
                case -1: goto L15;
                default: goto L14;
            }
        L14:
            goto L26
        L15:
            long r0 = java.lang.System.currentTimeMillis()
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r2 = r2.convert(r5, r7)
            long r0 = r0 + r2
            r4.w = r0
            r0 = -1
            goto L27
        L24:
            r0 = -2
            goto L27
        L26:
            r0 = 0
        L27:
            java.lang.Class r1 = r4.getClass()
            r1.getSimpleName()
            r4.hashCode()
            long r1 = r4.w
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS
            r1.convert(r5, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.b.a.d.f.a(long, java.util.concurrent.TimeUnit):int");
    }

    public final long a(TimeUnit timeUnit) {
        return timeUnit.convert(t(), TimeUnit.MILLISECONDS);
    }

    @Override // com.igexin.b.a.d.a.a
    public void a() {
        this.F = null;
        this.E = null;
        this.K = null;
    }

    public final void a(int i) {
        this.a = (byte) (this.a & 15);
        this.a = (byte) (((i & 15) << 4) | this.a);
    }

    public final void a(int i, com.igexin.b.a.d.a.g gVar) {
        if (i < 0) {
            throw new IllegalArgumentException("second must > 0");
        }
        this.B = i;
        this.G = gVar;
    }

    public final void a(com.igexin.b.a.d.a.d dVar) {
        this.O = dVar;
    }

    protected final void a(f fVar) {
        this.C = fVar.C;
        this.a = (byte) (fVar.a & 240);
        this.A = fVar.A;
        this.D = fVar.D;
        this.O = fVar.O;
        this.B = fVar.B;
        this.G = fVar.G;
    }

    public void b_() throws Exception {
        this.K = Thread.currentThread();
        this.q = true;
        getClass().getName();
        hashCode();
        this.K.getName();
    }

    public void d() {
        this.t = true;
    }

    @Override // com.igexin.b.a.d.a.f
    public void d_() {
        if (this.m || this.n) {
            a();
        }
    }

    public abstract void e();

    protected abstract void f();

    public final void k() {
        this.m = true;
    }

    public final boolean l() {
        return this.o;
    }

    public final boolean m() {
        return this.n;
    }

    protected final void n() {
        if (!this.p && !this.r && !this.s) {
            this.m = true;
            this.q = false;
        } else if (this.r && !this.m) {
            this.q = false;
        } else {
            if (!this.p || this.o || this.m) {
                return;
            }
            this.q = false;
        }
    }

    protected final void o() {
        if (this.O != null) {
            int i = d.a.a;
        }
    }
}
