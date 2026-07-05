package com.huya.mtp.hyns;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSCallSyncExecutor<T> {
    private CountDownLatch mCountDownLatch = new CountDownLatch(1);
    private NSCall<T> mExecutor;
    private volatile NSResponse<T> mHttpResult;
    private NSException mNSException;

    public NSCallSyncExecutor(NSCall<T> nSCall) {
        this.mExecutor = nSCall;
    }

    public NSResponse<T> read(NSSettings nSSettings) throws NSException {
        this.mExecutor.enqueue(new NSCallback<T>() { // from class: com.huya.mtp.hyns.NSCallSyncExecutor.1
            @Override // com.huya.mtp.hyns.NSCallback
            public void onResponse(NSResponse<T> nSResponse) {
                NSCallSyncExecutor.this.mHttpResult = nSResponse;
                NSCallSyncExecutor.this.mNSException = null;
                NSCallSyncExecutor.this.deliverResult();
            }

            @Override // com.huya.mtp.hyns.NSCallback
            public void onError(NSException nSException) {
                NSCallSyncExecutor.this.mHttpResult = null;
                NSCallSyncExecutor.this.mNSException = nSException;
                NSCallSyncExecutor.this.deliverResult();
            }

            @Override // com.huya.mtp.hyns.NSCallback
            public void onCancelled() {
                NSCallSyncExecutor.this.mHttpResult = null;
                NSCallSyncExecutor.this.mNSException = null;
                NSCallSyncExecutor.this.deliverResult();
            }
        }, nSSettings);
        waitingForResult();
        NSException nSException = this.mNSException;
        if (nSException != null) {
            throw nSException;
        }
        return this.mHttpResult;
    }

    private void waitingForResult() {
        try {
            this.mCountDownLatch.await();
        } catch (InterruptedException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deliverResult() {
        this.mCountDownLatch.countDown();
    }
}
