package com.bytedance.bdtracker;

import android.os.Build;
import android.text.TextUtils;
import com.bytedance.applog.log.LoggerImpl;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes2.dex */
public class o4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final CharSequence f302a = "amigo";
    public static final CharSequence b = "funtouch";
    public static final y3<Boolean> c = new a();

    public static class a extends y3<Boolean> {
        @Override // com.bytedance.bdtracker.y3
        public Boolean a(Object[] objArr) {
            try {
                Class<?> cls = Class.forName("com.huawei.system.BuildEx");
                return Boolean.valueOf("harmony".equals(cls.getMethod("getOsBrand", null).invoke(cls, null)));
            } catch (Throwable unused) {
                return false;
            }
        }
    }

    public static String a() {
        String str = Build.MANUFACTURER;
        return str == null ? "" : str.trim();
    }

    public static String a(String str) {
        BufferedReader bufferedReader;
        String strA = q4.a(str);
        if (!TextUtils.isEmpty(strA)) {
            return strA;
        }
        String line = "";
        if (!TextUtils.isEmpty(str)) {
            try {
                Process processExec = Runtime.getRuntime().exec("getprop " + str);
                bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()), 1024);
                try {
                    line = bufferedReader.readLine();
                    processExec.destroy();
                } catch (Throwable th) {
                    th = th;
                    try {
                        LoggerImpl.global().error("getSysPropByExec error", th, new Object[0]);
                    } finally {
                        n0.a((Closeable) bufferedReader);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        }
        return line;
    }

    public static boolean b() {
        String str = Build.MANUFACTURER;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.toLowerCase().contains("oppo") || str.toLowerCase().contains("realme");
    }

    public static boolean c() {
        return (!TextUtils.isEmpty(Build.DISPLAY) && Build.DISPLAY.contains("Flyme")) || "flyme".equals(Build.USER);
    }

    public static boolean d() {
        return (!TextUtils.isEmpty(Build.BRAND) && Build.BRAND.toLowerCase().startsWith("honor")) || (!TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase().startsWith("honor"));
    }

    public static boolean e() {
        try {
            return Class.forName("miui.os.Build").getName().length() > 0;
        } catch (Throwable unused) {
            return false;
        }
    }
}
