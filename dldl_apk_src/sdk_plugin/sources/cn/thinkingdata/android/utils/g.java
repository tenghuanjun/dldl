package cn.thinkingdata.android.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class g {
    private static String a = "";
    public static List<ActivityManager.RunningAppProcessInfo> b;

    private static String a() {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, new Object[0]);
            if (objInvoke instanceof String) {
                return (String) objInvoke;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "";
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String strB = b();
        a = strB;
        if (!TextUtils.isEmpty(strB)) {
            return a;
        }
        String strA = a();
        a = strA;
        if (!TextUtils.isEmpty(strA)) {
            return a;
        }
        String strB2 = b(context);
        a = strB2;
        return strB2;
    }

    private static String b() {
        return Build.VERSION.SDK_INT >= 28 ? Application.getProcessName() : "";
    }

    private static String b(Context context) {
        int iMyPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return "";
        }
        if (b == null) {
            b = activityManager.getRunningAppProcesses();
        }
        List<ActivityManager.RunningAppProcessInfo> list = b;
        if (list == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : list) {
            if (runningAppProcessInfo.pid == iMyPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }
}
