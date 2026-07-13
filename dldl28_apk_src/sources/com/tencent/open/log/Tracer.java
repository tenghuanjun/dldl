package com.tencent.open.log;

import com.tencent.open.log.d;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
public abstract class Tracer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile int f1068a;
    private volatile boolean b;
    private g c;

    protected abstract void doTrace(int i, Thread thread, long j, String str, String str2, Throwable th);

    public Tracer() {
        this(c.f1071a, true, g.f1075a);
    }

    public Tracer(int i, boolean z, g gVar) {
        this.f1068a = c.f1071a;
        this.b = true;
        this.c = g.f1075a;
        a(i);
        a(z);
        a(gVar);
    }

    public void a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        if (d() && d.a.a(this.f1068a, i)) {
            doTrace(i, thread, j, str, str2, th);
        }
    }

    public void a(int i) {
        this.f1068a = i;
    }

    public boolean d() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public g e() {
        return this.c;
    }

    public void a(g gVar) {
        this.c = gVar;
    }
}
