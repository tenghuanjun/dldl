package com.rxjava.rxlife;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import io.reactivex.disposables.Disposable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class LifecycleScope implements Scope, GenericLifecycleObserver {
    private Disposable disposable;
    private final Lifecycle.Event event;
    private final Lifecycle lifecycle;

    private LifecycleScope(Lifecycle lifecycle, Lifecycle.Event event) {
        this.lifecycle = lifecycle;
        this.event = event;
    }

    static LifecycleScope from(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        return new LifecycleScope(lifecycleOwner.getLifecycle(), event);
    }

    @Override // com.rxjava.rxlife.Scope
    public void onScopeStart(Disposable disposable) {
        this.disposable = disposable;
        onScopeEnd();
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle == null) {
            throw new NullPointerException("lifecycle is null");
        }
        lifecycle.addObserver(this);
    }

    @Override // com.rxjava.rxlife.Scope
    public void onScopeEnd() {
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle == null) {
            throw new NullPointerException("lifecycle is null");
        }
        lifecycle.removeObserver(this);
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event.equals(this.event)) {
            this.disposable.dispose();
            lifecycleOwner.getLifecycle().removeObserver(this);
        }
    }
}
