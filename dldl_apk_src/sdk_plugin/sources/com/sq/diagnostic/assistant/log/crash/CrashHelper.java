package com.sq.diagnostic.assistant.log.crash;

import android.util.Log;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sq.diagnostic.assistant.log.SQLogUtils;
import java.lang.Thread;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CrashHelper implements Thread.UncaughtExceptionHandler {
    private static volatile CrashHelper instance;
    private final String CRASH_TAG = "crash";
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    public static CrashHelper getInstance() {
        if (instance == null) {
            synchronized (CrashHelper.class) {
                if (instance == null) {
                    synchronized (CrashHelper.class) {
                        instance = new CrashHelper();
                    }
                }
            }
        }
        return instance;
    }

    public void init() {
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        SQLogUtils.e("crash", "thread :" + thread.getName() + ShellAdbUtils.COMMAND_LINE_END + Log.getStackTraceString(th));
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefaultHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
