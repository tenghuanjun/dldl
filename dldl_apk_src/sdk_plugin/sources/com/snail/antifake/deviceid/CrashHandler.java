package com.snail.antifake.deviceid;

import android.app.ActivityManager;
import android.app.Application;
import android.os.Process;
import java.lang.Thread;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private Application mApplication;

    public CrashHandler(Application application) {
        this.mApplication = application;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Process.killProcess(Process.myPid());
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.mApplication.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == Process.myPid()) {
                if (this.mApplication.getPackageName().equals(runningAppProcessInfo.processName)) {
                    return;
                }
                Process.killProcess(Process.myPid());
                return;
            }
        }
    }
}
