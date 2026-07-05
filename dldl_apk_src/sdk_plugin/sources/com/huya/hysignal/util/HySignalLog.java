package com.huya.hysignal.util;

import com.tencent.mars.xlog.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class HySignalLog {
    public static void verbose(String str) {
        try {
            Log.v("", str);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void verbose(String str, String str2) {
        try {
            Log.v(str, str2);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void verbose(String str, String str2, Object... objArr) {
        try {
            Log.v(str, str2, objArr);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void verbose(String str, String str2, Throwable th) {
        try {
            Log.v(str, str2, th);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void debug(String str) {
        try {
            Log.d("", str);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void debug(String str, String str2) {
        try {
            Log.d(str, str2);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void debug(String str, String str2, Object... objArr) {
        try {
            Log.d(str, str2, objArr);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void debug(String str, String str2, Throwable th) {
        try {
            Log.d(str, str2, th);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void info(String str) {
        try {
            Log.i("", str);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void info(String str, String str2) {
        try {
            Log.i(str, str2);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void info(String str, String str2, Object... objArr) {
        try {
            Log.i(str, str2, objArr);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void info(String str, String str2, Throwable th) {
        try {
            Log.i(str, str2, th);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void warn(String str) {
        try {
            Log.w("", str);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void warn(String str, String str2) {
        try {
            Log.w(str, str2);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void warn(String str, String str2, Object... objArr) {
        Log.w(str, str2, objArr);
    }

    public static void warn(String str, String str2, Throwable th) {
        try {
            Log.w(str, str2, th);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void error(String str) {
        try {
            Log.e("", str);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void error(String str, String str2) {
        try {
            Log.e(str, str2);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void error(String str, String str2, Object... objArr) {
        try {
            Log.e(str, str2, objArr);
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public static void error(String str, String str2, Throwable th) {
        try {
            Log.e(str, str2, th);
        } catch (UnsatisfiedLinkError unused) {
        }
    }
}
