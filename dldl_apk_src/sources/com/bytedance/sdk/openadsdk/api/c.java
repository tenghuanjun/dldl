package com.bytedance.sdk.openadsdk.api;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class c {
    private static boolean a = false;
    private static int b = 4;

    public static void a(int i) {
        b = i;
    }

    public static void a() {
        a = true;
        a(3);
    }

    public static void a(String str, String str2) {
        if (a && str2 != null && b <= 2) {
            Log.v(str, str2);
        }
    }

    public static void b(String str, String str2) {
        if (a && str2 != null && b <= 3) {
            Log.d(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (a) {
            if (!(str2 == null && th == null) && b <= 3) {
                Log.d(str, str2, th);
            }
        }
    }

    public static void a(String str, Object... objArr) {
        if (a && objArr != null && b <= 3) {
            Log.d(str, a(objArr));
        }
    }

    public static void c(String str, String str2) {
        if (a && str2 != null && b <= 4) {
            Log.i(str, str2);
        }
    }

    public static void a(String str) {
        if (a) {
            d("TTLogger", str);
        }
    }

    public static void d(String str, String str2) {
        if (a && str2 != null && b <= 5) {
            Log.w(str, str2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (a) {
            if (!(str2 == null && th == null) && b <= 5) {
                Log.w(str, str2, th);
            }
        }
    }

    public static void b(String str, Object... objArr) {
        if (a && objArr != null && b <= 5) {
            Log.v(str, a(objArr));
        }
    }

    public static void e(String str, String str2) {
        if (a && str2 != null && b <= 6) {
            Log.e(str, str2);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (a) {
            if (!(str2 == null && th == null) && b <= 6) {
                Log.e(str, str2, th);
            }
        }
    }

    private static String a(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : objArr) {
            if (obj != null) {
                sb.append(obj.toString());
            } else {
                sb.append(" null ");
            }
            sb.append(" ");
        }
        return sb.toString();
    }
}
