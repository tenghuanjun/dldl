package com.huya.force.log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ForceLog {
    private static final String TAG = "Force";
    private static volatile ForceLog mInstance;
    private ILog mLog = new AndroidLog();

    enum LogLevel {
        Level_None,
        Level_Error,
        Level_Warning,
        Level_Info,
        Level_Debug,
        Level_All
    }

    private native void setLogCallback(Object obj);

    static {
        System.loadLibrary("hyutils");
    }

    public static ForceLog instance() {
        if (mInstance == null) {
            synchronized (ForceLog.class) {
                if (mInstance == null) {
                    mInstance = new ForceLog();
                }
            }
        }
        return mInstance;
    }

    private ForceLog() {
        setLogCallback(this);
    }

    public void setLog(ILog iLog) {
        this.mLog = iLog;
        setLogCallback(this);
    }

    public static void info(String str, String str2) {
        if (instance().mLog == null) {
            return;
        }
        instance().mLog.info(str, str2);
    }

    public static void error(String str, String str2) {
        if (instance().mLog == null) {
            return;
        }
        instance().mLog.error(str, str2);
    }

    public static void debug(String str, String str2) {
        if (instance().mLog == null) {
            return;
        }
        instance().mLog.debug(str, str2);
    }

    public static void warn(String str, String str2) {
        if (instance().mLog == null) {
            return;
        }
        instance().mLog.warn(str, str2);
    }

    public void onLogCallback(int i, String str) {
        if (this.mLog == null) {
            return;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$huya$force$log$ForceLog$LogLevel[LogLevel.values()[i].ordinal()];
        if (i2 == 1) {
            this.mLog.debug(TAG, str);
            return;
        }
        if (i2 == 2) {
            this.mLog.info(TAG, str);
        } else if (i2 == 3) {
            this.mLog.warn(TAG, str);
        } else {
            if (i2 != 4) {
                return;
            }
            this.mLog.error(TAG, str);
        }
    }

    /* JADX INFO: renamed from: com.huya.force.log.ForceLog$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$huya$force$log$ForceLog$LogLevel;

        static {
            int[] iArr = new int[LogLevel.values().length];
            $SwitchMap$com$huya$force$log$ForceLog$LogLevel = iArr;
            try {
                iArr[LogLevel.Level_Debug.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huya$force$log$ForceLog$LogLevel[LogLevel.Level_Info.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$huya$force$log$ForceLog$LogLevel[LogLevel.Level_Warning.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$huya$force$log$ForceLog$LogLevel[LogLevel.Level_Error.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
