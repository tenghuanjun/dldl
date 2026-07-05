package com.huya.hysignal.core;

import com.tencent.mars.stn.StnLogic;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
final class RealCall implements Call {
    private Callback callback;
    private boolean canceled;
    private boolean executed;
    private boolean handled;
    private HyMars mHyMars;
    private final com.huya.mtp.hyns.api.Request request;
    private final StnLogic.Task task;

    private RealCall(ArrayList<String> arrayList, com.huya.mtp.hyns.api.Request request, HyMars hyMars) {
        this.request = request;
        StnLogic.Task task = new StnLogic.Task(request.channel(), request.cmdId(), request.cgi(), request.traceId(), arrayList);
        this.task = task;
        task.retryCount = request.retryCount();
        this.task.limitFlow = request.limitFlow();
        this.task.limitFrequency = request.limitFrequency();
        this.task.networkStatusSensitive = request.networkStatusSensitive();
        this.task.encrypt = request.isEncrypt();
        this.task.priority = request.priority();
        this.task.totalTimeout = request.totalTimeout();
        this.mHyMars = hyMars;
    }

    static RealCall newRealCall(ArrayList<String> arrayList, com.huya.mtp.hyns.api.Request request, HyMars hyMars) {
        return new RealCall(arrayList, request, hyMars);
    }

    @Override // com.huya.hysignal.core.Call
    public com.huya.mtp.hyns.api.Request request() {
        return this.request;
    }

    @Override // com.huya.hysignal.core.Call
    public Response execute() throws Exception {
        final Response[] responseArr = new Response[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        enqueue(new Callback() { // from class: com.huya.hysignal.core.RealCall.1
            @Override // com.huya.hysignal.core.Callback
            public void onResponse(byte[] bArr, HySignalError hySignalError) {
                responseArr[0] = new Response(bArr, hySignalError);
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        return responseArr[0];
    }

    @Override // com.huya.hysignal.core.Call
    public void enqueue(Callback callback) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
            this.callback = callback;
            if (this.canceled) {
                this.mHyMars.cancelCallback(this);
            } else {
                this.mHyMars.startTask(this.task, this);
            }
        }
    }

    @Override // com.huya.hysignal.core.Call
    public void cancel() {
        synchronized (this) {
            this.canceled = true;
            if (this.executed) {
                this.mHyMars.stopTask(this.task);
            }
        }
    }

    void handleCallback(byte[] bArr, int i, int i2) {
        if (this.handled) {
            return;
        }
        this.handled = true;
        this.callback.onResponse(bArr, new HySignalError(i, i2));
    }
}
