package com.huya.mtp.hyns;

import java.lang.Throwable;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class AsyncToSync<Req, Rsp, Error extends Throwable> {
    private CountDownLatch mCountDownLatch = new CountDownLatch(1);
    private volatile Error mError;
    private volatile Rsp mRsp;

    public abstract void executeAsync(Req req);

    /* JADX INFO: Thrown type has an unknown type hierarchy: Error extends java.lang.Throwable */
    public Rsp execute(Req req) throws Throwable {
        executeAsync(req);
        waitingFor();
        if (this.mError != null) {
            throw this.mError;
        }
        return this.mRsp;
    }

    public void onTaskSucceed(Rsp rsp) {
        this.mRsp = rsp;
        this.mError = null;
        awake();
    }

    public void onTaskFailed(Error error) {
        this.mRsp = null;
        this.mError = error;
        awake();
    }

    public void onTaskCancelled() {
        this.mRsp = null;
        this.mError = null;
        awake();
    }

    private void waitingFor() {
        try {
            this.mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void awake() {
        this.mCountDownLatch.countDown();
    }
}
