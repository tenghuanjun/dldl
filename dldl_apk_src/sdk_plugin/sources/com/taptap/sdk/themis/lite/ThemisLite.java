package com.taptap.sdk.themis.lite;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ThemisLite {
    private static final Object lock = new Object();
    public static Context m_ctx = null;
    public static String version = "1.0.7";

    private static native void Z8f7JxQk(String str, boolean z, boolean z2, long j);

    private static native void vQe8bYsT(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public static native String xJ3kLm9Q();

    /* JADX INFO: Access modifiers changed from: private */
    public static native String zXq3tVwP();

    public static void Register(Context context) {
        m_ctx = context;
    }

    private static void CallbackRunner(final ThemisLiteCallback themisLiteCallback, long j) {
        if (themisLiteCallback == null) {
            return;
        }
        int i = (j > 100L ? 1 : (j == 100L ? 0 : -1));
        new Thread(new Runnable() { // from class: com.taptap.sdk.themis.lite.ThemisLite.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (ThemisLite.lock) {
                    try {
                        LogUtil.debug(Base64Util.decode("Q2FsbGJhY2tSdW5uZXIgb24gaWY6"));
                        themisLiteCallback.getThemisTapaid(ThemisLite.xJ3kLm9Q());
                        themisLiteCallback.getThemisOneIDData(ThemisLite.zXq3tVwP());
                    } catch (Exception e) {
                        LogUtil.debug(Base64Util.decode("Q2FsbGJhY2tSdW5uZXI6") + e.getMessage());
                    }
                }
            }
        }).start();
    }

    public static void WakeUpCallback() {
        synchronized (lock) {
            lock.notify();
        }
    }

    public static void InitThemis_v2(String str, ThemisLiteCallback themisLiteCallback, long j, String str2, boolean z, boolean z2) {
        if (themisLiteCallback == null) {
            return;
        }
        try {
            LogUtil.info(Base64Util.decode("c3RhcnQgaW5pdCBUaGVtaXNMaXRlIGJ5IEphdmEsIFZlcnNpb246IDEuMC43"));
            System.loadLibrary("themis");
            Z8f7JxQk(str, z, z2, j);
            vQe8bYsT(str2);
            CallbackRunner(themisLiteCallback, j);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
