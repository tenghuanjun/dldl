package com.volcengine.j;

import android.app.ActivityManager;
import android.app.Application;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.List;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class b {
    static final /* synthetic */ boolean a = true;

    private static Application a() {
        return g.a();
    }

    public static String b() {
        String strD = d();
        if (!TextUtils.isEmpty(strD)) {
            return strD;
        }
        String strC = c();
        return !TextUtils.isEmpty(strC) ? strC : e();
    }

    private static String c() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        String str;
        ActivityManager activityManager = (ActivityManager) a().getSystemService("activity");
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null && runningAppProcesses.size() != 0) {
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid && (str = runningAppProcessInfo.processName) != null) {
                    return str;
                }
            }
        }
        return "";
    }

    private static String d() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/" + Process.myPid() + "/cmdline")));
            String strTrim = bufferedReader.readLine().trim();
            bufferedReader.close();
            return strTrim;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String e() {
        try {
            Application applicationA = a();
            Field field = applicationA.getClass().getField("mLoadedApk");
            field.setAccessible(true);
            Object obj = field.get(applicationA);
            boolean z = a;
            if (!z && obj == null) {
                throw new AssertionError();
            }
            Field declaredField = obj.getClass().getDeclaredField("mActivityThread");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            if (!z && obj2 == null) {
                throw new AssertionError();
            }
            return (String) obj2.getClass().getDeclaredMethod("getProcessName", null).invoke(obj2, null);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean f() {
        try {
            return a().getPackageName().equals(b());
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static String g() {
        return f() ? "mainprocess" : "childprocess";
    }
}
