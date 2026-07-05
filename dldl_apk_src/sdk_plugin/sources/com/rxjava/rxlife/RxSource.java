package com.rxjava.rxlife;

import io.reactivex.disposables.Disposable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
abstract class RxSource<E> {
    boolean onMain;
    Scope scope;

    public abstract Disposable subscribe();

    public abstract void subscribe(E e);

    RxSource(Scope scope, boolean z) {
        this.scope = scope;
        this.onMain = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <O extends E> O subscribeWith(O o) {
        subscribe(o);
        return o;
    }
}
