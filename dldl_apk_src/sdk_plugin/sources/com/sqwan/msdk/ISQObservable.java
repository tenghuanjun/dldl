package com.sqwan.msdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ISQObservable<S, F> implements Runnable {
    private static ExecutorService sExecutorService = Executors.newSingleThreadExecutor();
    private ISQObserver<S, F> mObserver;

    @Override // java.lang.Runnable
    public void run() {
    }

    public ISQObservable startHandle() {
        sExecutorService.execute(this);
        return this;
    }

    public ISQObservable registerObserver(ISQObserver<S, F> iSQObserver) {
        this.mObserver = iSQObserver;
        return this;
    }

    public void unRegisterObserver() {
        this.mObserver = null;
    }

    public void handleSuccess(S s) {
        ISQObserver<S, F> iSQObserver = this.mObserver;
        if (iSQObserver != null) {
            iSQObserver.onSuccess(s);
        }
    }

    public void handleFail(F f) {
        ISQObserver<S, F> iSQObserver = this.mObserver;
        if (iSQObserver != null) {
            iSQObserver.onFail(f);
        }
    }
}
