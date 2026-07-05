package com.tencent.mars.xlog;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class Log2 {
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_NONE = 6;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 3;
    private static final String TAG = "mars.xlog.log";
    private static LogImp debugLog = null;
    private static int level = 6;
    private static LogImp logImp;
    public static Context toastSupportContext;

    public interface LogImp {
        void appenderClose();

        void appenderFlush(boolean z);

        int getLogLevel();

        void logD(String str, String str2, String str3, int i, int i2, long j, long j2, String str4);

        void logE(String str, String str2, String str3, int i, int i2, long j, long j2, String str4);

        void logF(String str, String str2, String str3, int i, int i2, long j, long j2, String str4);

        void logI(String str, String str2, String str3, int i, int i2, long j, long j2, String str4);

        void logV(String str, String str2, String str3, int i, int i2, long j, long j2, String str4);

        void logW(String str, String str2, String str3, int i, int i2, long j, long j2, String str4);
    }

    static {
        LogImp logImp2 = new LogImp() { // from class: com.tencent.mars.xlog.Log2.1
            private Handler handler = new Handler(Looper.getMainLooper());

            @Override // com.tencent.mars.xlog.Log2.LogImp
            public void appenderClose() {
            }

            @Override // com.tencent.mars.xlog.Log2.LogImp
            public void appenderFlush(boolean z) {
            }

            @Override // com.tencent.mars.xlog.Log2.LogImp
            public void logV(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
                if (Log2.level <= 0) {
                    android.util.Log.v(str, str4);
                }
            }

            @Override // com.tencent.mars.xlog.Log2.LogImp
            public void logI(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
                if (Log2.level <= 2) {
                    android.util.Log.i(str, str4);
                }
            }

            @Override // com.tencent.mars.xlog.Log2.LogImp
            public void logD(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
                if (Log2.level <= 1) {
                    android.util.Log.d(str, str4);
                }
            }

            @Override // com.tencent.mars.xlog.Log2.LogImp
            public void logW(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
                if (Log2.level <= 3) {
                    android.util.Log.w(str, str4);
                }
            }

            @Override // com.tencent.mars.xlog.Log2.LogImp
            public void logE(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
                if (Log2.level <= 4) {
                    android.util.Log.e(str, str4);
                }
            }

            @Override // com.tencent.mars.xlog.Log2.LogImp
            public void logF(String str, String str2, String str3, int i, int i2, long j, long j2, final String str4) {
                if (Log2.level > 5) {
                    return;
                }
                android.util.Log.e(str, str4);
                if (Log2.toastSupportContext != null) {
                    this.handler.post(new Runnable() { // from class: com.tencent.mars.xlog.Log2.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Toast.makeText(Log2.toastSupportContext, str4, 1).show();
                        }
                    });
                }
            }

            @Override // com.tencent.mars.xlog.Log2.LogImp
            public int getLogLevel() {
                return Log2.level;
            }
        };
        debugLog = logImp2;
        logImp = logImp2;
    }

    public static void setLogImp(LogImp logImp2) {
        logImp = logImp2;
    }

    public static LogImp getImpl() {
        return logImp;
    }

    public static void appenderClose() {
        LogImp logImp2 = logImp;
        if (logImp2 != null) {
            logImp2.appenderClose();
        }
    }

    public static void appenderFlush(boolean z) {
        LogImp logImp2 = logImp;
        if (logImp2 != null) {
            logImp2.appenderFlush(z);
        }
    }

    public static int getLogLevel() {
        LogImp logImp2 = logImp;
        if (logImp2 != null) {
            return logImp2.getLogLevel();
        }
        return 6;
    }

    public static void setLevel(int i, boolean z) {
        level = i;
        android.util.Log.w(TAG, "new log level: " + i);
    }

    public static void f(String str, String str2) {
        f(str, str2, (Object[]) null);
    }

    public static void e(String str, String str2) {
        e(str, str2, (Object[]) null);
    }

    public static void w(String str, String str2) {
        w(str, str2, (Object[]) null);
    }

    public static void i(String str, String str2) {
        i(str, str2, (Object[]) null);
    }

    public static void d(String str, String str2) {
        d(str, str2, (Object[]) null);
    }

    public static void v(String str, String str2) {
        v(str, str2, (Object[]) null);
    }

    public static void f(String str, String str2, Object... objArr) {
        if (logImp != null) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            logImp.logF(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (logImp != null) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            if (str2 == null) {
                str2 = "";
            }
            logImp.logE(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2);
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        if (logImp != null) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            if (str2 == null) {
                str2 = "";
            }
            logImp.logW(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2);
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        if (logImp != null) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            if (str2 == null) {
                str2 = "";
            }
            logImp.logI(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2);
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (logImp != null) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            if (str2 == null) {
                str2 = "";
            }
            logImp.logD(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2);
        }
    }

    public static void v(String str, String str2, Object... objArr) {
        if (logImp != null) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            if (str2 == null) {
                str2 = "";
            }
            logImp.logV(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2);
        }
    }

    public static void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
        if (logImp != null) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            if (str2 == null) {
                str2 = "";
            }
            logImp.logE(str, "", "", 0, Process.myPid(), Thread.currentThread().getId(), Looper.getMainLooper().getThread().getId(), str2 + "  " + android.util.Log.getStackTraceString(th));
        }
    }
}
