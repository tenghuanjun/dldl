package com.tencent.mars.xlog;

import com.tencent.mars.xlog.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class Xlog implements Log.LogImp {
    public static final int AppednerModeAsync = 0;
    public static final int AppednerModeSync = 1;
    public static final int LEVEL_ALL = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_NONE = 6;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 3;
    private static final String TAG = "Xlog";
    public static boolean loadLibSuccess;

    public static native void appenderOpen(int i, int i2, String str, String str2, String str3, String str4);

    private static String decryptTag(String str) {
        return str;
    }

    public static native void logWrite(XLoggerInfo xLoggerInfo, String str);

    public static native void logWrite2(int i, String str, String str2, String str3, int i2, int i3, long j, long j2, String str4);

    public static native void setAppenderMode(int i);

    public static native void setConsoleLogOpen(boolean z);

    public static native void setErrLogOpen(boolean z);

    public static native void setLogLevel(int i);

    public static native void setMaxFileCount(int i);

    public static native void setMaxFileSize(long j);

    @Override // com.tencent.mars.xlog.Log.LogImp
    public native void appenderClose();

    @Override // com.tencent.mars.xlog.Log.LogImp
    public native void appenderFlush(boolean z);

    @Override // com.tencent.mars.xlog.Log.LogImp
    public native int getLogLevel();

    static class XLoggerInfo {
        public String filename;
        public String funcname;
        public int level;
        public int line;
        public long maintid;
        public long pid;
        public String tag;
        public long tid;

        XLoggerInfo() {
        }
    }

    static {
        loadLibrary();
    }

    private static void loadLibrary() {
        for (int i = 0; i < 3; i++) {
            try {
                System.loadLibrary("stlport_shared");
                System.loadLibrary("marsxlog");
                loadLibSuccess = true;
                return;
            } catch (Exception e) {
                loadLibSuccess = false;
                android.util.Log.e(TAG, "loadLibrary error,", e);
            }
        }
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void logV(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
        logWrite2(0, decryptTag(str), str2, str3, i, i2, j, j2, str4);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void logD(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
        logWrite2(1, decryptTag(str), str2, str3, i, i2, j, j2, str4);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void logI(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
        logWrite2(2, decryptTag(str), str2, str3, i, i2, j, j2, str4);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void logW(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
        logWrite2(3, decryptTag(str), str2, str3, i, i2, j, j2, str4);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void logE(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
        logWrite2(4, decryptTag(str), str2, str3, i, i2, j, j2, str4);
    }

    @Override // com.tencent.mars.xlog.Log.LogImp
    public void logF(String str, String str2, String str3, int i, int i2, long j, long j2, String str4) {
        logWrite2(5, decryptTag(str), str2, str3, i, i2, j, j2, str4);
    }
}
