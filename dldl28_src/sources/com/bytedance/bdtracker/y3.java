package com.bytedance.bdtracker;

/* JADX INFO: loaded from: classes2.dex */
public abstract class y3<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile T f340a;

    public abstract T a(Object... objArr);

    public final T b(Object... objArr) {
        if (this.f340a == null) {
            synchronized (this) {
                if (this.f340a == null) {
                    this.f340a = a(objArr);
                }
            }
        }
        return this.f340a;
    }
}
