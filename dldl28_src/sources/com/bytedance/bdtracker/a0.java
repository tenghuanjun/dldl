package com.bytedance.bdtracker;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f205a;
    public volatile boolean b;
    public long c;
    public boolean d;
    public final c0 e;
    public final d f;

    public a0(c0 c0Var) {
        this.e = c0Var;
        this.f = c0Var.d;
    }

    public final long a() {
        long jB = b();
        if (jB > System.currentTimeMillis()) {
            return jB;
        }
        this.e.d.D.debug("The worker:{} start to work...", d());
        try {
            boolean zC = c();
            this.c = System.currentTimeMillis();
            if (zC) {
                this.f205a = 0;
            } else {
                this.f205a++;
            }
            this.e.d.D.debug("The worker:{} worked:{}.", d(), zC ? "success" : "failed");
        } catch (Throwable th) {
            try {
                this.e.d.D.error("Work do failed.", th, new Object[0]);
                this.c = System.currentTimeMillis();
                this.f205a++;
                this.e.d.D.debug("The worker:{} worked:{}.", d(), "failed");
            } catch (Throwable th2) {
                this.c = System.currentTimeMillis();
                this.f205a++;
                this.e.d.D.debug("The worker:{} worked:{}.", d(), "failed");
                throw th2;
            }
        }
        return b();
    }

    public final long b() {
        long jG;
        long jCurrentTimeMillis;
        if (!f() || i4.b(this.e.b(), this.e.n.c()).a()) {
            if (this.b) {
                jG = 0;
                this.c = 0L;
                this.b = false;
            } else {
                int i = this.f205a;
                if (i > 0) {
                    long[] jArrE = e();
                    jG = jArrE[(i - 1) % jArrE.length];
                } else {
                    jG = g();
                }
            }
            jCurrentTimeMillis = this.c;
        } else {
            this.e.d.D.debug("Check work time is not net available.", new Object[0]);
            jCurrentTimeMillis = System.currentTimeMillis();
            jG = 5000;
        }
        return jCurrentTimeMillis + jG;
    }

    public abstract boolean c();

    public abstract String d();

    public abstract long[] e();

    public abstract boolean f();

    public abstract long g();

    public a0(c0 c0Var, long j) {
        this.e = c0Var;
        this.f = c0Var.d;
        this.c = j;
    }
}
