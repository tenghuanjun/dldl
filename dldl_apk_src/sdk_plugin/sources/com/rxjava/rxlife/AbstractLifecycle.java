package com.rxjava.rxlife;

import android.os.Looper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class AbstractLifecycle<T> extends AtomicReference<T> implements Disposable {
    private boolean isAddObserver;
    private final Object mObject = new Object();
    private Scope scope;

    public AbstractLifecycle(Scope scope) {
        this.scope = scope;
    }

    protected final void addObserver() throws Exception {
        if (isMainThread() || !(this.scope instanceof LifecycleScope)) {
            addObserverOnMain();
            return;
        }
        final Object obj = this.mObject;
        AndroidSchedulers.mainThread().scheduleDirect(new Runnable() { // from class: com.rxjava.rxlife.AbstractLifecycle.1
            @Override // java.lang.Runnable
            public void run() {
                AbstractLifecycle.this.addObserverOnMain();
                synchronized (obj) {
                    AbstractLifecycle.this.isAddObserver = true;
                    obj.notifyAll();
                }
            }
        });
        synchronized (obj) {
            while (!this.isAddObserver) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addObserverOnMain() {
        this.scope.onScopeStart(this);
    }

    final void removeObserver() {
        if (isMainThread() || !(this.scope instanceof LifecycleScope)) {
            this.scope.onScopeEnd();
        } else {
            AndroidSchedulers.mainThread().scheduleDirect(new Runnable() { // from class: com.rxjava.rxlife.AbstractLifecycle.2
                @Override // java.lang.Runnable
                public void run() {
                    AbstractLifecycle.this.removeObserver();
                }
            });
        }
    }

    private boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
}
