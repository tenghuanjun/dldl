package cn.thinkingdata.android.utils;

import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class c {
    private static String a(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls, str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean a() {
        return c() || b();
    }

    private static boolean b() {
        String strA = a("ro.product.cpu.abi");
        return (strA == null || TextUtils.isEmpty(strA) || !strA.contains("x86")) ? false : true;
    }

    private static boolean c() {
        return "1".equals(a("ro.kernel.qemu"));
    }
}
