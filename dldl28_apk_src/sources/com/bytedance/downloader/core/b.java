package com.bytedance.downloader.core;

/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f352a;
    private final long b;
    private final long c;
    private long d;

    public b(int i, long j, long j2) {
        this(i, j, j2, j - 1);
    }

    public b(int i, long j, long j2, long j3) {
        this.f352a = i;
        this.b = j;
        this.c = j2;
        this.d = j3;
    }

    public final int a() {
        return this.f352a;
    }

    public final void a(long j) {
        this.d = j;
    }

    public final long b() {
        return this.b;
    }

    public final long c() {
        return this.c;
    }

    public final long d() {
        return this.d;
    }

    public final long e() {
        return (this.c - this.b) + 1;
    }

    public final long f() {
        return (this.d - this.b) + 1;
    }

    public final long g() {
        return this.c - this.d;
    }
}
