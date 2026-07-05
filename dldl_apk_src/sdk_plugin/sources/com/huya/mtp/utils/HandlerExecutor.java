package com.huya.mtp.utils;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HandlerExecutor implements Executor {
    private Handler mHandler;

    public HandlerExecutor(String str) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
    }

    public HandlerExecutor(Handler handler) {
        this.mHandler = handler;
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.mHandler.post(runnable);
    }
}
