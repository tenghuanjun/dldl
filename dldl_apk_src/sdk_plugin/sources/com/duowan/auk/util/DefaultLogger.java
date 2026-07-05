package com.duowan.auk.util;

import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import com.snail.antifake.deviceid.ShellAdbUtils;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DefaultLogger implements ILogger {
    public static int LOG_LEVEL = 4;
    public static boolean isStoreExist = "mounted".equalsIgnoreCase(Environment.getExternalStorageState());
    private static boolean mLogEnable = true;

    @Override // com.duowan.auk.util.ILogger
    public void setMaxFileCount(int i) {
    }

    @Override // com.duowan.auk.util.ILogger
    public void setMaxFileSize(int i) {
    }

    @Override // com.duowan.auk.util.ILogger
    public void setSysLogEnabled(boolean z) {
    }

    @Override // com.duowan.auk.util.ILogger
    public void setWrapperCount(int i) {
    }

    private static void logToFile(String str) {
        if (isStoreExist) {
            LogToES.writeLogToFile(LogToES.sLogPath, "logs.txt", str);
        }
    }

    private static void logToFile(String str, Throwable th) {
        if (isStoreExist) {
            StringWriter stringWriter = new StringWriter();
            stringWriter.write(str);
            stringWriter.write(ShellAdbUtils.COMMAND_LINE_END);
            th.printStackTrace(new PrintWriter(stringWriter));
            LogToES.writeLogToFile(LogToES.sLogPath, "logs.txt", stringWriter.toString());
        }
    }

    private static String msgForException(Object obj, String str, String str2, int i) {
        StringBuilder sb = new StringBuilder();
        if (obj instanceof String) {
            sb.append((String) obj);
        } else {
            sb.append(obj.getClass().getSimpleName());
        }
        sb.append(" Exception occurs at ");
        sb.append("(P:");
        sb.append(Process.myPid());
        sb.append(")");
        sb.append("(T:");
        sb.append(Thread.currentThread().getId());
        sb.append(") at ");
        sb.append(str);
        sb.append(" (");
        sb.append(str2);
        sb.append(":");
        sb.append(i);
        sb.append(")");
        return sb.toString();
    }

    private static String msgForTextLog(Object obj, String str, int i, String str2) {
        return str2 + "(P:" + Process.myPid() + ")(T:" + Thread.currentThread().getId() + ")(C:" + objClassName(obj) + ")at (" + str + ":" + i + ")";
    }

    private static String objClassName(Object obj) {
        if (obj == null) {
            return "Global";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return obj.getClass().getSimpleName();
    }

    private static int getCallerLineNumber() {
        return Thread.currentThread().getStackTrace()[4].getLineNumber();
    }

    private static String getCallerFilename() {
        return Thread.currentThread().getStackTrace()[4].getFileName();
    }

    private static String getCallerMethodName() {
        return Thread.currentThread().getStackTrace()[4].getMethodName();
    }

    @Override // com.duowan.auk.util.ILogger
    public void init(Context context, String str, boolean z) {
        LogToES.sLogPath = str;
    }

    @Override // com.duowan.auk.util.ILogger
    public void setLogLevel(int i) {
        LOG_LEVEL = i;
    }

    @Override // com.duowan.auk.util.ILogger
    public boolean isLogEnable() {
        return mLogEnable;
    }

    @Override // com.duowan.auk.util.ILogger
    public void setLogEnable(boolean z) {
        mLogEnable = z;
    }

    @Override // com.duowan.auk.util.ILogger
    public void verbose(Object obj, String str, Object... objArr) {
        if (isLogLevelEnabled(2)) {
            String str2 = String.format(str, objArr);
            verbose(msgForTextLog(obj, getCallerFilename(), getCallerLineNumber(), str2));
        }
    }

    @Override // com.duowan.auk.util.ILogger
    public void verbose(Object obj, String str) {
        if (isLogLevelEnabled(2)) {
            verbose(msgForTextLog(obj, getCallerFilename(), getCallerLineNumber(), str));
        }
    }

    @Override // com.duowan.auk.util.ILogger
    public void verbose(String str) {
        Log.v(L.TAG, str);
    }

    @Override // com.duowan.auk.util.ILogger
    public void debug(Object obj, String str, Object... objArr) {
        if (isLogLevelEnabled(3)) {
            String str2 = String.format(str, objArr);
            debug(msgForTextLog(obj, getCallerFilename(), getCallerLineNumber(), str2));
        }
    }

    @Override // com.duowan.auk.util.ILogger
    public void debug(Object obj, String str) {
        if (isLogLevelEnabled(3)) {
            debug(msgForTextLog(obj, getCallerFilename(), getCallerLineNumber(), str));
        }
    }

    @Override // com.duowan.auk.util.ILogger
    public void debug(Object obj, Throwable th) {
        if (isLogLevelEnabled(3)) {
            int callerLineNumber = getCallerLineNumber();
            debug(msgForException(obj, getCallerMethodName(), getCallerFilename(), callerLineNumber), th);
        }
    }

    @Override // com.duowan.auk.util.ILogger
    public void debug(String str) {
        Log.d(L.TAG, str);
        logToFile("DEBUG: " + str);
    }

    @Override // com.duowan.auk.util.ILogger
    public void debug(String str, Throwable th) {
        Log.d(L.TAG, str, th);
        logToFile("DEBUG: " + str, th);
    }

    @Override // com.duowan.auk.util.ILogger
    public void info(Object obj, String str) {
        if (isLogLevelEnabled(4)) {
            info(msgForTextLog(obj, getCallerFilename(), getCallerLineNumber(), str));
        }
    }

    @Override // com.duowan.auk.util.ILogger
    public void info(Object obj, String str, Object... objArr) {
        if (isLogLevelEnabled(4)) {
            String str2 = String.format(str, objArr);
            info(msgForTextLog(obj, getCallerFilename(), getCallerLineNumber(), str2));
        }
    }

    @Override // com.duowan.auk.util.ILogger
    public void info(String str) {
        Log.i(L.TAG, str);
        logToFile("INFO: " + str);
    }

    @Override // com.duowan.auk.util.ILogger
    public void warn(Object obj, String str, Object... objArr) {
        if (isLogLevelEnabled(5)) {
            String str2 = String.format(str, objArr);
            warn(msgForTextLog(obj, getCallerFilename(), getCallerLineNumber(), str2));
        }
    }

    @Override // com.duowan.auk.util.ILogger
    public void warn(Object obj, String str) {
        if (isLogLevelEnabled(5)) {
            warn(msgForTextLog(obj, getCallerFilename(), getCallerLineNumber(), str));
        }
    }

    @Override // com.duowan.auk.util.ILogger
    public void warn(String str) {
        Log.w(L.TAG, str);
        logToFile("WARN: " + str);
    }

    @Override // com.duowan.auk.util.ILogger
    public void error(Object obj, String str, Object... objArr) {
        if (LOG_LEVEL > 6) {
            return;
        }
        String str2 = String.format(str, objArr);
        error(msgForTextLog(obj, getCallerFilename(), getCallerLineNumber(), str2));
    }

    @Override // com.duowan.auk.util.ILogger
    public void error(Object obj, String str) {
        if (LOG_LEVEL > 6) {
            return;
        }
        error(msgForTextLog(obj, getCallerFilename(), getCallerLineNumber(), str));
    }

    @Override // com.duowan.auk.util.ILogger
    public void error(Object obj, Throwable th) {
        if (LOG_LEVEL > 6) {
            return;
        }
        int callerLineNumber = getCallerLineNumber();
        error(msgForException(obj, getCallerMethodName(), getCallerFilename(), callerLineNumber), th);
    }

    @Override // com.duowan.auk.util.ILogger
    public void error(String str) {
        Log.e(L.TAG, str);
        logToFile("ERROR: " + str);
    }

    @Override // com.duowan.auk.util.ILogger
    public void error(String str, Throwable th) {
        Log.e(L.TAG, str, th);
        logToFile("ERROR: " + str, th);
    }

    @Override // com.duowan.auk.util.ILogger
    public boolean isLogLevelEnabled(int i) {
        return mLogEnable;
    }

    @Override // com.duowan.auk.util.ILogger
    public void flushToDisk() {
        LogToES.flushToDisk();
    }

    @Override // com.duowan.auk.util.ILogger
    public void uncaughtException(Throwable th) {
        if (isStoreExist) {
            StringWriter stringWriter = new StringWriter();
            stringWriter.write(ShellAdbUtils.COMMAND_LINE_END);
            th.printStackTrace(new PrintWriter(stringWriter));
            try {
                LogToES.writeLogToFileReal(L.sLogPath, "uncaught_exception.txt", stringWriter.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogToES.flushToDisk();
        }
    }
}
