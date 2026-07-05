package com.alipay.face.photinus;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class PhotinusHandler {
    private static PhotinusHandler mInstance = new PhotinusHandler();
    private Handler photinusHandler;

    private PhotinusHandler() {
        HandlerThread handlerThread = new HandlerThread(PhotinusHandler.class.getSimpleName());
        handlerThread.start();
        this.photinusHandler = new Handler(handlerThread.getLooper());
    }

    public static PhotinusHandler getInstance() {
        return mInstance;
    }

    public void postTask(Runnable runnable) {
        this.photinusHandler.post(runnable);
    }
}
