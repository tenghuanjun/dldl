package com.sq.sdk.tool.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseApp extends Application {
    private static Handler sHandler;
    private static Handler sWorker;
    private static HandlerThread sWorkerThread;

    static {
        HandlerThread handlerThread = new HandlerThread("worker");
        sWorkerThread = handlerThread;
        handlerThread.start();
        sWorker = new Handler(sWorkerThread.getLooper());
        sHandler = new Handler(Looper.getMainLooper());
    }

    @Override // android.app.Application
    public void onCreate() {
        try {
            Class.forName("android.os.AsyncTask");
        } catch (Throwable unused) {
        }
        super.onCreate();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        if (intent != null) {
            intent.addFlags(268435456);
        }
        super.startActivity(intent);
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        SqThreadHelper.setUp(this, sWorker, sHandler);
    }
}
