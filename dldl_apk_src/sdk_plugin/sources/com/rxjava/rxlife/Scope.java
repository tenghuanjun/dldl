package com.rxjava.rxlife;

import io.reactivex.disposables.Disposable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface Scope {
    void onScopeEnd();

    void onScopeStart(Disposable disposable);
}
