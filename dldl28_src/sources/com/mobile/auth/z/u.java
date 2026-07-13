package com.mobile.auth.z;

import com.mobile.auth.gatewayauth.ExceptionProcessor;

/* JADX INFO: loaded from: classes3.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f835a = "123.125.99.31";
    public static int b = 0;
    public static String c = "0";
    public static c d = null;
    private static String e = "";
    private static String f = "";
    private static String g = "";
    private static int h = 5;
    private static int i = -1;
    private static String j = "";
    private static String k = "";
    private static String l = "";

    public static String a() {
        try {
            return "https://" + e.d() + "/unicomAuth/android/v3.0/qc?";
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static void a(int i2) {
        try {
            b = i2;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void a(String str) {
        try {
            c = str;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static boolean a(String str, String str2) {
        try {
            t.c("\nhostname : " + str + "\nsubjectName : " + str2);
            if (str.endsWith(".10010.com") && str2.startsWith("CN=10010.com")) {
                return true;
            }
            if (str.equals("auth.wosms.cn") && str2.startsWith("CN=auth.wosms.cn")) {
                return true;
            }
            if (str.equals("msv6.wosms.cn") && str2.startsWith("CN=msv6.wosms.cn")) {
                return true;
            }
            if (str.equals("ali.wosms.cn") && str2.startsWith("CN=ali.wosms.cn")) {
                return true;
            }
            if (str.equals("test.wosms.cn") && str2.startsWith("CN=test.wosms.cn")) {
                return true;
            }
            if (str.equals("m.zzx.cnklog.com") && str2.startsWith("CN=m.zzx.cnklog.com")) {
                return true;
            }
            if (str.equals("id6.me") && str2.startsWith("CN=*.id6.me")) {
                return true;
            }
            if (str.equals("cert.cmpassport.com")) {
                if (str2.startsWith("CN=*.cmpassport.com")) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    public static String b() {
        try {
            return c;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static void b(int i2) {
        try {
            h = i2;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void b(String str) {
        try {
            e = str;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static String c() {
        try {
            return e;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static void c(int i2) {
        try {
            i = i2;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static void c(String str) {
        try {
            f = str;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static String d() {
        try {
            return f;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static void d(String str) {
        try {
            t.c("APN:".concat(String.valueOf(str)));
            g = str;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static String e() {
        try {
            String strA = i.a();
            f = strA;
            return strA;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static String e(String str) {
        try {
            if (!"cmnet".equals(str) && !"cmwap".equals(str)) {
                if (!"3gwap".equals(str) && !"uniwap".equals(str) && !"3gnet".equals(str) && !"uninet".equals(str)) {
                    if (!"ctnet".equals(str)) {
                        if (!"ctwap".equals(str)) {
                            return "0";
                        }
                    }
                    return "2";
                }
                return "3";
            }
            return "1";
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static String f() {
        try {
            return g;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static void f(String str) {
        try {
            j = str;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public static int g() {
        try {
            return h;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return -1;
        }
    }

    public static int h() {
        try {
            return i;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return -1;
        }
    }
}
