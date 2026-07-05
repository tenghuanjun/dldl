package com.duowan.monitor.utility;

import android.util.Log;
import com.duowan.monitor.core.ILog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class MonitorLog {
    private static ILog sLog;
    private static boolean sTest;

    public static void setTest(boolean z) {
        sTest = z;
    }

    public static void setLog(ILog iLog) {
        sLog = iLog;
    }

    public static void d(String str, String str2) {
        d(str, str2, null);
    }

    public static void d(String str, String str2, Throwable th) {
        ILog iLog = sLog;
        if (iLog != null) {
            iLog.d(str, str2, th);
        }
        if (sTest) {
            Log.d(str, str2, th);
        }
    }

    public static void e(String str, String str2) {
        e(str, str2, null);
    }

    public static void e(String str, String str2, Throwable th) {
        ILog iLog = sLog;
        if (iLog != null) {
            iLog.e(str, str2, th);
        }
        if (sTest) {
            Log.e(str, str2, th);
        }
    }
}
