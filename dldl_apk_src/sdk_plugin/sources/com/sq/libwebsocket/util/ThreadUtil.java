package com.sq.libwebsocket.util;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ThreadUtil {
    private static Handler sMainHandler;

    public static boolean checkMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void runOnMainThread(Runnable runnable) {
        checkMainHandlerIsNull();
        sMainHandler.post(runnable);
    }

    private static void checkMainHandlerIsNull() {
        if (sMainHandler == null) {
            sMainHandler = new Handler(Looper.getMainLooper());
        }
    }

    public static void removeOnMainThread(Runnable runnable) {
        Handler handler = sMainHandler;
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
