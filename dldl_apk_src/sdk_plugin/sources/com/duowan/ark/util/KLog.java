package com.duowan.ark.util;

import android.os.Environment;
import android.os.Process;
import android.util.Log;
import com.snail.antifake.deviceid.ShellAdbUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class KLog {
    private static final String DEBUG_TAG = "ARK_DEBUG";
    public static int LOG_LEVEL = 4;
    public static String TAG = "kiwi";
    public static boolean sLineNumEnabled;
    public static int sPid;
    public static boolean isStoreExist = Environment.getExternalStorageState().equalsIgnoreCase("mounted");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");
    public static long sPauseDelay = LongCompanionObject.MAX_VALUE;
    private static volatile int pauseLogState = 0;
    private static long sPauseStartedTime = 0;
    public static boolean mLogEnable = true;
    private static long msMaxWait = 3000;

    public static void setIsStorageExist(boolean z) {
        isStoreExist = z;
    }

    public static void setTag(String str) {
        TAG = str;
    }

    public static boolean isLogEnable() {
        return mLogEnable;
    }

    public static void setLogEnable(boolean z) {
        mLogEnable = z;
    }

    public static void setLogLevel(int i) {
        LOG_LEVEL = i;
    }

    public static int getLogLevel() {
        return LOG_LEVEL;
    }

    public static boolean isLogLevelEnabled(int i) {
        return LOG_LEVEL <= i && isLogEnable();
    }

    public static void setSysLogEnabled(boolean z) {
        LogProxy.setSysLogEnabled(z);
    }

    public static void pause() {
        Log.i(DEBUG_TAG, "pause log");
        pauseLogState++;
        sPauseStartedTime = System.currentTimeMillis();
    }

    public static void resume() {
        Log.i(DEBUG_TAG, "resume log");
        if (pauseLogState > 0) {
            pauseLogState--;
        }
    }

    public static void verbose(String str) {
        if (isLogLevelEnabled(2)) {
            doLog(2, null, str, null, true);
        }
    }

    public static void verbose(Object obj, String str) {
        if (isLogLevelEnabled(2)) {
            doLog(2, obj, str, null, sLineNumEnabled);
        }
    }

    public static void verbose(Object obj, String str, Object... objArr) {
        if (isLogLevelEnabled(2)) {
            doLog(2, obj, String.format(str, objArr), null, sLineNumEnabled);
        }
    }

    public static void verbose(Object obj, String str, Throwable th) {
        if (isLogLevelEnabled(2)) {
            doLog(2, obj, str, th, true);
        }
    }

    public static void verbose(Object obj, Throwable th) {
        if (isLogLevelEnabled(2)) {
            doLog(2, obj, "Exception occurs at", th, true);
        }
    }

    public static void debug(String str) {
        if (isLogLevelEnabled(3)) {
            doLog(3, null, str, null, true);
        }
    }

    public static void debug(Object obj, String str) {
        if (isLogLevelEnabled(3)) {
            doLog(3, obj, str, null, sLineNumEnabled);
        }
    }

    public static void debug(Object obj, String str, Object... objArr) {
        if (isLogLevelEnabled(3)) {
            doLog(3, obj, String.format(str, objArr), null, sLineNumEnabled);
        }
    }

    public static void debug(Object obj, String str, Throwable th) {
        if (isLogLevelEnabled(3)) {
            doLog(3, obj, str, th, true);
        }
    }

    public static void debug(Object obj, Throwable th) {
        if (isLogLevelEnabled(3)) {
            doLog(3, obj, "Exception occurs at", th, true);
        }
    }

    public static void info(String str) {
        if (isLogLevelEnabled(4)) {
            doLog(4, null, str, null, true);
        }
    }

    public static void info(Object obj, String str) {
        if (isLogLevelEnabled(4)) {
            doLog(4, obj, str, null, sLineNumEnabled);
        }
    }

    public static void info(Object obj, String str, Object... objArr) {
        if (isLogLevelEnabled(4)) {
            doLog(4, obj, String.format(str, objArr), null, sLineNumEnabled);
        }
    }

    public static void info(Object obj, String str, Throwable th) {
        if (isLogLevelEnabled(4)) {
            doLog(4, obj, str, th, true);
        }
    }

    public static void info(Object obj, Throwable th) {
        if (isLogLevelEnabled(4)) {
            doLog(4, obj, "Exception occurs at", th, true);
        }
    }

    public static void warn(String str) {
        if (isLogLevelEnabled(5)) {
            doLog(5, null, str, null, sLineNumEnabled);
        }
    }

    public static void warn(Object obj, String str) {
        if (isLogLevelEnabled(5)) {
            doLog(5, obj, str, null, sLineNumEnabled);
        }
    }

    public static void warn(Object obj, String str, Object... objArr) {
        if (isLogLevelEnabled(5)) {
            doLog(5, obj, String.format(str, objArr), null, sLineNumEnabled);
        }
    }

    public static void warn(Object obj, String str, Throwable th) {
        if (isLogLevelEnabled(5)) {
            doLog(5, obj, str, th, true);
        }
    }

    public static void warn(Object obj, Throwable th) {
        if (isLogLevelEnabled(5)) {
            doLog(5, obj, "Exception occurs at", th, true);
        }
    }

    public static void error(String str) {
        if (isLogLevelEnabled(6)) {
            doLog(6, null, str, null, sLineNumEnabled);
        }
    }

    public static void error(Object obj, String str) {
        if (isLogLevelEnabled(6)) {
            doLog(6, obj, str, null, sLineNumEnabled);
        }
    }

    public static void error(Object obj, String str, Object... objArr) {
        if (isLogLevelEnabled(6)) {
            doLog(6, obj, String.format(str, objArr), null, sLineNumEnabled);
        }
    }

    public static void error(Object obj, String str, Throwable th) {
        if (isLogLevelEnabled(6)) {
            doLog(6, obj, str, th, true);
        }
    }

    public static void error(Object obj, Throwable th) {
        if (isLogLevelEnabled(6)) {
            doLog(6, obj, "Exception occurs at", th, true);
        }
    }

    public static void log(int i, Object obj, String str, Throwable th, boolean z) {
        if (isLogLevelEnabled(i)) {
            doLog(i, obj, str, th, z);
        }
    }

    private static void doLog(final int i, Object obj, String str, Throwable th, boolean z) {
        final String logInfo;
        if (pauseLogState > 0 && System.currentTimeMillis() - sPauseStartedTime > sPauseDelay) {
            pauseLogState = 0;
        }
        if (pauseLogState <= 0) {
            logInfo = getLogInfo(obj, str, th, z);
        } else if (z) {
            logInfo = getLogInfo(obj, str, th);
        } else {
            logInfo = getLogInfo(obj, str, th, false);
        }
        LogProxy.getDispatcher().post(new Runnable() { // from class: com.duowan.ark.util.KLog.1
            @Override // java.lang.Runnable
            public void run() {
                if (KLog.pauseLogState > 0) {
                    LogProxy.offer(new LogInfo(i, String.format("[%s]%s", KLog.TIME_FORMAT.format(new Date()), logInfo)));
                    return;
                }
                while (!LogProxy.isCacheEmpty()) {
                    KLog.logByLevel(LogProxy.poll());
                }
                KLog.logByLevel(i, logInfo);
            }
        });
    }

    public static void logEmptyMsg() {
        LogProxy.getDispatcher().post(new Runnable() { // from class: com.duowan.ark.util.KLog.2
            @Override // java.lang.Runnable
            public void run() {
                KLog.logByLevel(3, "test");
            }
        });
    }

    public static void flushToDisk() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        LogProxy.getDispatcher().post(new Runnable() { // from class: com.duowan.ark.util.KLog.3
            @Override // java.lang.Runnable
            public void run() {
                while (!LogProxy.isCacheEmpty()) {
                    KLog.logByLevel(LogProxy.poll());
                }
                LogProxy.flushToDisk();
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(msMaxWait, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.e("XLogProxy", "flushToDisk error ", e);
        }
        Log.e("XLogProxy", "flushToDisk finish");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logByLevel(int i, String str) {
        LogProxy.logByLevel(i, str, TAG, LogProxy.getLogName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logByLevel(LogInfo logInfo) {
        LogProxy.logByLevel(logInfo.logLevel, logInfo.msg, TAG, LogProxy.getLogName());
    }

    private static String getLogInfo(Object obj, String str, Throwable th) {
        StringBuilder sb = new StringBuilder(64);
        sb.append(str);
        sb.append("(P:");
        sb.append(sPid);
        sb.append(")");
        sb.append("(T:");
        sb.append(Thread.currentThread().getName());
        sb.append("-");
        sb.append(Process.myTid());
        sb.append(")");
        sb.append("(C:");
        sb.append(objClassName(obj));
        sb.append(")");
        if (th != null) {
            sb.append('\n');
            sb.append(Log.getStackTraceString(th));
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getLogInfo(java.lang.Object r8, java.lang.String r9, java.lang.Throwable r10, boolean r11) {
        /*
            if (r11 == 0) goto L20
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            java.lang.StackTraceElement[] r0 = r0.getStackTrace()
            r1 = 0
            if (r0 == 0) goto L13
            int r2 = r0.length
            r3 = 5
            if (r2 <= r3) goto L13
            r1 = r0[r3]
        L13:
            if (r1 == 0) goto L20
            java.lang.String r0 = r1.getFileName()
            int r1 = r1.getLineNumber()
            r3 = r0
            r4 = r1
            goto L25
        L20:
            java.lang.String r0 = ""
            r1 = 0
            r3 = r0
            r4 = 0
        L25:
            r2 = r8
            r5 = r9
            r6 = r10
            r7 = r11
            java.lang.String r8 = msgForTextLog(r2, r3, r4, r5, r6, r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.ark.util.KLog.getLogInfo(java.lang.Object, java.lang.String, java.lang.Throwable, boolean):java.lang.String");
    }

    private static String msgForTextLog(Object obj, String str, int i, String str2, Throwable th, boolean z) {
        StringBuilder sb = new StringBuilder(64);
        sb.append(str2);
        if (sPid == 0) {
            sPid = Process.myPid();
        }
        sb.append("(P:");
        sb.append(sPid);
        sb.append(")");
        sb.append("(T:");
        sb.append(Thread.currentThread().getName());
        sb.append("-");
        sb.append(Process.myTid());
        sb.append(")");
        sb.append("(C:");
        sb.append(obj);
        sb.append(")");
        if (z) {
            sb.append("at (");
            sb.append(str);
            sb.append(":");
            sb.append(i);
            sb.append(")");
        }
        if (th != null) {
            sb.append('\n');
            sb.append(Log.getStackTraceString(th));
        }
        return sb.toString();
    }

    private static String objClassName(Object obj) {
        if (obj == null) {
            return "Global";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Tags) {
            return obj.toString();
        }
        if (obj instanceof Class) {
            return ((Class) obj).getSimpleName();
        }
        return obj.getClass().getSimpleName();
    }

    public static void uncaughtException(Throwable th) {
        if (isStoreExist) {
            StringWriter stringWriter = new StringWriter();
            stringWriter.write(ShellAdbUtils.COMMAND_LINE_END);
            th.printStackTrace(new PrintWriter(stringWriter));
            try {
                LogProxy.uncaughtException(stringWriter.toString(), TAG, LogProxy.getUELogName());
                LogProxy.flushToDisk();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
