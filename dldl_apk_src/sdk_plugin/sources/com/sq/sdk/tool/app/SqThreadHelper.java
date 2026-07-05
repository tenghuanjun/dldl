package com.sq.sdk.tool.app;

import android.content.Context;
import android.os.Handler;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqThreadHelper {
    private static Context sContext;
    private static Handler sHandler;
    private static Handler sWorker;

    public static void setUp(Context context, Handler handler, Handler handler2) {
        sContext = context;
        sWorker = handler;
        sHandler = handler2;
    }

    public static void postThread(Runnable runnable) {
        Handler handler = sWorker;
        if (handler != null) {
            handler.post(runnable);
            return;
        }
        throw new RuntimeException("call setUp first");
    }

    public static void postThreadDelayed(Runnable runnable, long j) {
        Handler handler = sWorker;
        if (handler != null) {
            handler.postDelayed(runnable, j);
            return;
        }
        throw new RuntimeException("call setUp first");
    }

    public static void postRunInUiThread(Runnable runnable) {
        Handler handler = sHandler;
        if (handler != null) {
            handler.post(runnable);
            return;
        }
        throw new RuntimeException("call setUp first");
    }

    public static void postUiThreadDelayed(Runnable runnable, long j) {
        Handler handler = sHandler;
        if (handler != null) {
            handler.postDelayed(runnable, j);
            return;
        }
        throw new RuntimeException("call setUp first");
    }

    public static void removeFromUiThread(Runnable runnable) {
        sHandler.removeCallbacks(runnable);
    }

    public static Context getContext() {
        Context context = sContext;
        if (context != null) {
            return context;
        }
        throw new RuntimeException("call setUp first");
    }
}
