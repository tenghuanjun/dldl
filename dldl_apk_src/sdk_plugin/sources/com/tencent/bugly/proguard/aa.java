package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.SystemClock;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class aa implements Runnable {
    private final Handler a;
    private final String b;
    private long c;
    private final long d;
    private boolean e = true;
    private long f;

    aa(Handler handler, String str, long j) {
        this.a = handler;
        this.b = str;
        this.c = j;
        this.d = j;
    }

    public final void a() {
        if (!this.e) {
            x.d("scheduleCheckBlock fail as %s thread is blocked.", this.b);
            return;
        }
        this.e = false;
        this.f = SystemClock.uptimeMillis();
        this.a.postAtFrontOfQueue(this);
    }

    public final boolean b() {
        x.c("%s thread waitTime:%d", this.b, Long.valueOf(this.c));
        return !this.e && SystemClock.uptimeMillis() > this.f + this.c;
    }

    public final int c() {
        if (this.e) {
            return 0;
        }
        return SystemClock.uptimeMillis() - this.f < this.c ? 1 : 3;
    }

    public final Thread d() {
        return this.a.getLooper().getThread();
    }

    public final String e() {
        return this.b;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e = true;
        this.c = this.d;
    }

    public final void a(long j) {
        this.c = LongCompanionObject.MAX_VALUE;
    }

    public final void f() {
        this.c = this.d;
    }
}
