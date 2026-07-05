package com.duowan.live.one.util;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class SafeRunnable<T> implements Runnable {
    protected WeakReference<T> mWrapper;

    public abstract void runImpl();

    protected SafeRunnable(T t) {
        this.mWrapper = new WeakReference<>(t);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.mWrapper.get() == null) {
            return;
        }
        runImpl();
    }

    public T wrapper() {
        return this.mWrapper.get();
    }
}
