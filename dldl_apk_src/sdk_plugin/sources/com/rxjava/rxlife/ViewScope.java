package com.rxjava.rxlife;

import android.os.Build;
import android.view.View;
import io.reactivex.disposables.Disposable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class ViewScope implements Scope, View.OnAttachStateChangeListener {
    private Disposable disposable;
    private boolean ignoreAttach;
    private final View view;

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    private ViewScope(View view, boolean z) {
        this.view = view;
        this.ignoreAttach = z;
    }

    static ViewScope from(View view, boolean z) {
        return new ViewScope(view, z);
    }

    @Override // com.rxjava.rxlife.Scope
    public void onScopeStart(Disposable disposable) {
        this.disposable = disposable;
        View view = this.view;
        if (view == null) {
            throw new NullPointerException("view is null");
        }
        if (!((Build.VERSION.SDK_INT >= 19 && view.isAttachedToWindow()) || view.getWindowToken() != null) && !this.ignoreAttach) {
            throw new OutsideScopeException("View is not attached!");
        }
        view.addOnAttachStateChangeListener(this);
    }

    @Override // com.rxjava.rxlife.Scope
    public void onScopeEnd() {
        View view = this.view;
        if (view == null) {
            return;
        }
        view.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.disposable.dispose();
        view.removeOnAttachStateChangeListener(this);
    }
}
