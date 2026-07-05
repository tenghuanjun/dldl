package com.huya.mtp.utils;

import android.os.PowerManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WakeLockHelper {
    private static WakeLockHelper INSTANCE = new WakeLockHelper();
    private static final String TAG = WakeLockHelper.class.getSimpleName();
    private int mSendMsgCount;
    private PowerManager.WakeLock mWakeLock;

    public static WakeLockHelper getInstance() {
        return INSTANCE;
    }

    public synchronized void acquireWakeLock() {
    }

    public synchronized void releaseWakeLock() {
    }
}
