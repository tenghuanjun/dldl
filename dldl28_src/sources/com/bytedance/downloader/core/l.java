package com.bytedance.downloader.core;

/* JADX INFO: loaded from: classes2.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f360a = System.currentTimeMillis();
    private long b = 0;
    private long c = 0;

    public final synchronized long a() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.f360a;
        if (jCurrentTimeMillis > 1000 || this.b == 0) {
            if (jCurrentTimeMillis >= 500) {
                this.b = (long) ((this.c / jCurrentTimeMillis) * 1000.0f);
                this.f360a = System.currentTimeMillis();
                this.c = 0L;
            }
        } else if (jCurrentTimeMillis < 0) {
            this.b = 0L;
        }
        return this.b;
    }

    public final synchronized void a(long j) {
        this.c += j;
        if (this.f360a == 0) {
            this.f360a = System.currentTimeMillis();
        }
    }
}
