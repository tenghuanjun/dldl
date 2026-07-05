package com.sy37sdk.views.webview;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Deprecated
public class AsyncHandler {
    private static Handler handler;
    private static HandlerThread handlerThread = new HandlerThread("HandlerThread");

    static {
        init();
    }

    public static void destroy() {
        HandlerThread handlerThread2 = handlerThread;
        if (handlerThread2 != null) {
            handlerThread2.quit();
            handlerThread = null;
            handler = null;
        }
    }

    public static void init() {
        synchronized (handlerThread) {
            handlerThread.start();
            try {
                handlerThread.wait();
                handler = new Handler(handlerThread.getLooper());
            } catch (InterruptedException unused) {
            }
        }
    }

    public static void post(Runnable runnable) {
        Handler handler2 = handler;
        if (handler2 != null) {
            handler2.post(runnable);
        }
    }

    public static void postDelayed(Runnable runnable, long j) {
        Handler handler2 = handler;
        if (handler2 != null) {
            handler2.postDelayed(runnable, j);
        }
    }

    public static void removeCallbacks(Runnable runnable) {
        Handler handler2 = handler;
        if (handler2 != null) {
            handler2.removeCallbacks(runnable);
        }
    }
}
