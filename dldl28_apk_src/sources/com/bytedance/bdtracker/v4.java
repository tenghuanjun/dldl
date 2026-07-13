package com.bytedance.bdtracker;

/* JADX INFO: loaded from: classes2.dex */
public abstract class v4<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile T f332a;

    public abstract T a(Object... objArr);

    public final T b(Object... objArr) {
        if (this.f332a == null) {
            synchronized (this) {
                if (this.f332a == null) {
                    this.f332a = a(objArr);
                }
            }
        }
        return this.f332a;
    }
}
