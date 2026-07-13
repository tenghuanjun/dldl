package com.bytedance.applog.log;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsSingleton<T> {
    private volatile T mInstance;

    protected abstract T create(Object... objArr);

    public final T get(Object... objArr) {
        if (this.mInstance == null) {
            synchronized (this) {
                if (this.mInstance == null) {
                    this.mInstance = create(objArr);
                }
            }
        }
        return this.mInstance;
    }
}
