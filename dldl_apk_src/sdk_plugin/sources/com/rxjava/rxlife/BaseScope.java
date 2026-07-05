package com.rxjava.rxlife;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseScope implements Scope, GenericLifecycleObserver {
    private CompositeDisposable mDisposables;

    @Override // com.rxjava.rxlife.Scope
    public void onScopeEnd() {
    }

    public BaseScope(LifecycleOwner lifecycleOwner) {
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    @Override // com.rxjava.rxlife.Scope
    public void onScopeStart(Disposable disposable) {
        addDisposable(disposable);
    }

    private void addDisposable(Disposable disposable) {
        CompositeDisposable compositeDisposable = this.mDisposables;
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
            this.mDisposables = compositeDisposable;
        }
        compositeDisposable.add(disposable);
    }

    private void dispose() {
        CompositeDisposable compositeDisposable = this.mDisposables;
        if (compositeDisposable == null) {
            return;
        }
        compositeDisposable.dispose();
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            lifecycleOwner.getLifecycle().removeObserver(this);
            dispose();
        }
    }
}
