package com.huya.hysignal.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class ThreadManager {
    private static Handler sRegisterHandler;
    private static Handler sRequestHandler;
    private static Handler sResponseHandler;
    private static HandlerThread sRequestThread = new HandlerThread("HySignalWrapperRequestThread");
    private static HandlerThread sResponseThread = new HandlerThread("HySignalWrapperDispatchThread");
    private static HandlerThread sRegisterThread = new HandlerThread("HySignalRegister-SynRequestThread");

    static {
        sRequestThread.start();
        sResponseThread.start();
        sRegisterThread.start();
        sRequestHandler = new Handler(sRequestThread.getLooper());
        sResponseHandler = new Handler(sResponseThread.getLooper());
        sRegisterHandler = new Handler(sRegisterThread.getLooper());
    }

    public static void deliverOnRequestThread(Runnable runnable) {
        if (sRequestHandler.getLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            sRequestHandler.post(runnable);
        }
    }

    public static void deliverOnResponseThread(Runnable runnable) {
        if (sResponseHandler.getLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            sResponseHandler.post(runnable);
        }
    }

    public static void deliverOnRegisterThread(Runnable runnable) {
        if (sRegisterHandler.getLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            sRegisterHandler.post(runnable);
        }
    }
}
