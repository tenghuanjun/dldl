package cn.thinkingdata.android.utils;

import android.util.Log;
import com.youme.imsdk.YIMConstInfo;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TDLog {
    static volatile boolean mEnableLog;
    static volatile boolean mEnableLogInner;

    public static void d(String str, String str2) {
        if (mEnableLog) {
            Log.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }

    public static boolean getEnableLog() {
        return mEnableLog;
    }

    public static void i(String str, String str2) {
        if (mEnableLog) {
            if (str2.length() > 4000) {
                largeLog(str, str2);
            } else {
                Log.i(str, str2);
            }
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (mEnableLog) {
            Log.i(str, str2, th);
        }
    }

    public static void i(String str, Throwable th) {
        if (mEnableLog) {
            try {
                Log.i(str, "", th);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void largeLog(String str, String str2) {
        while (str2.length() > 4000) {
            Log.i(str, str2.substring(0, YIMConstInfo.Errorcode.IsWaitingSend) + "");
            str2 = str2.substring(YIMConstInfo.Errorcode.IsWaitingSend) + "";
        }
        Log.i(str, str2);
    }

    public static void setEnableLog(boolean z) {
        if (mEnableLogInner) {
            z = true;
        }
        mEnableLog = z;
    }

    public static void setEnableLogInner(boolean z) {
        mEnableLogInner = z;
    }

    public static void v(String str, String str2) {
        if (mEnableLog) {
            Log.v(str, str2);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (mEnableLog) {
            Log.v(str, str2, th);
        }
    }

    public static void w(String str, String str2) {
        if (mEnableLog) {
            Log.w(str, str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (mEnableLog) {
            Log.w(str, str2, th);
        }
    }
}
