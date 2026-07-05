package com.tencent.mars.comm;

import android.content.Context;
import android.os.PowerManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class WakerLock {
    private static final String TAG = "HuyaNS:WakerLock";
    public static boolean useWakeLock = true;
    private PowerManager.WakeLock wakeLock;

    public WakerLock(Context context) {
        this.wakeLock = null;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager != null) {
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, TAG);
            this.wakeLock = wakeLockNewWakeLock;
            wakeLockNewWakeLock.setReferenceCounted(false);
        }
    }

    protected void finalize() throws Throwable {
        unLock();
    }

    public void lock(long j) {
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (wakeLock == null || !useWakeLock) {
            return;
        }
        try {
            wakeLock.acquire(j);
        } catch (Exception unused) {
        }
    }

    public void unLock() {
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        try {
            this.wakeLock.release();
        } catch (Exception unused) {
        }
    }

    public boolean isLocking() {
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (wakeLock != null) {
            return wakeLock.isHeld();
        }
        return false;
    }
}
