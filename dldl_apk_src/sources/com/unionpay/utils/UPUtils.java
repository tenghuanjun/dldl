package com.unionpay.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import java.security.MessageDigest;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class UPUtils {
    public static String a(int i) {
        try {
            return a(forUrl(i, Build.VERSION.SDK_INT >= 23));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String a(int i, String str) {
        try {
            return a(forConfig(i, str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String a(Context context, String str) {
        if (context == null) {
            return null;
        }
        String strB = b(context.getSharedPreferences("UnionPayPluginEx.pref", 0).getString(str, ""), ("0000000023456789abcdef12123456786789abcd").substring(0, 32));
        return (strB != null && strB.endsWith("00000000")) ? strB.substring(0, strB.length() - 8) : "";
    }

    public static String a(String str) {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.reset();
            messageDigest.update(bytes);
            return a.a(messageDigest.digest());
        } catch (Exception unused) {
            return null;
        }
    }

    private static String a(String str, String str2) {
        try {
            return a.a(d.a(a.a(str2), str.getBytes("utf-8")));
        } catch (Exception unused) {
            return "";
        }
    }

    private static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        try {
            return new String(bArr, "utf-8");
        } catch (Exception unused) {
            j.c("uppay", "convert byteMsg to utf-8 String error!!!!");
            return "";
        }
    }

    public static void a(Context context, String str, String str2) {
        if (context != null) {
            String strA = a(str + "00000000", ("0000000023456789abcdef12123456786789abcd").substring(0, 32));
            if (strA == null) {
                strA = "";
            }
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("UnionPayPluginEx.pref", 0).edit();
            editorEdit.putString(str2, strA);
            editorEdit.commit();
        }
    }

    public static String b(int i) {
        try {
            return a(forScanUrl(i, Build.VERSION.SDK_INT >= 23));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String b(int i, String str) {
        try {
            return a(forWap(i, str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static String b(String str, String str2) {
        try {
            return new String(d.b(a.a(str2), a.a(str)), "utf-8").trim();
        } catch (Exception unused) {
            return "";
        }
    }

    public static void b(Context context, String str) {
        if (context != null) {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("UnionPayPluginEx.pref", 0).edit();
            editorEdit.remove(str);
            editorEdit.commit();
        }
    }

    public static String c(int i) {
        try {
            return a(forCallingAppUrl(i, Build.VERSION.SDK_INT >= 23));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String d(int i) {
        try {
            return a(forDirectAppsUrl(i, Build.VERSION.SDK_INT >= 23));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static native byte[] forCallingAppUrl(int i, boolean z);

    public static native byte[] forConfig(int i, String str);

    public static native byte[] forDirectAppsUrl(int i, boolean z);

    public static native byte[] forScanUrl(int i, boolean z);

    public static native byte[] forUrl(int i, boolean z);

    public static native byte[] forWap(int i, String str);

    public static native String getIssuer(int i);

    public static native String getSubject(int i);

    public static native String getTalkingDataIdForAssist(int i);
}
