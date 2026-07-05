package com.huya.berry.sdklive.living.messageboard;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BaseHandlerApp {
    private static final String TAG = "BaseHandlerApp";
    public static Handler gStartupHandler;
    public static HandlerThread gStartupThread = new HandlerThread("GlobalStartupThread");
    public static final Handler gMainHandler = new Handler(Looper.getMainLooper());

    static {
        gStartupThread.start();
        gStartupHandler = new Handler(gStartupThread.getLooper());
    }

    public static void runAsync(Runnable runnable) {
        gMainHandler.post(runnable);
    }

    public static void runAsyncDelayed(Runnable runnable, long j) {
        gMainHandler.postDelayed(runnable, j);
    }

    public static void removeRunAsync(Runnable runnable) {
        gMainHandler.removeCallbacks(runnable);
    }

    public static View inflate(Context context, int i) {
        return inflate(context, i, (ViewGroup) null);
    }

    public static View inflate(Context context, int i, ViewGroup viewGroup) {
        return inflate(context, i, viewGroup, viewGroup != null);
    }

    public static View inflate(Context context, int i, ViewGroup viewGroup, boolean z) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (layoutInflater == null) {
            throw new AssertionError("LayoutInflater not found.");
        }
        return layoutInflater.inflate(i, viewGroup, z);
    }
}
