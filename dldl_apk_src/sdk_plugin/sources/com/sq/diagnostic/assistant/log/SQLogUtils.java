package com.sq.diagnostic.assistant.log;

import android.util.Log;
import com.duowan.live.one.util.LogUtils;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sq.diagnostic.assistant.DiagnosticAssistant;
import com.sq.tool.logger.SQLog;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SQLogUtils {
    private SQLogUtils() {
    }

    public static void w(String str, String str2) {
        if (DiagnosticAssistant.getInstance().isInitSuccess()) {
            SQLog.log(5, str, str2, null);
        } else {
            Log.w(str, generateMsg(str2));
        }
    }

    public static void w(String str, String str2, Throwable th) {
        w(str, generateMsg(wrapLogThrowable(str2, th)));
    }

    public static void i(String str, String str2) {
        if (DiagnosticAssistant.getInstance().isInitSuccess()) {
            SQLog.log(4, str, str2, null);
        } else {
            Log.i(str, generateMsg(str2));
        }
    }

    public static void i(String str, String str2, Throwable th) {
        i(str, generateMsg(wrapLogThrowable(str2, th)));
    }

    public static void e(String str, String str2) {
        if (DiagnosticAssistant.getInstance().isInitSuccess()) {
            SQLog.log(6, str, str2, null);
        } else {
            Log.e(str, generateMsg(str2));
        }
    }

    public static void e(String str, String str2, Throwable th) {
        e(str, generateMsg(wrapLogThrowable(str2, th)));
    }

    public static void d(String str, String str2) {
        if (DiagnosticAssistant.getInstance().isInitSuccess()) {
            SQLog.log(3, str, str2, null);
        } else {
            Log.d(str, generateMsg(str2));
        }
    }

    public static void d(String str, String str2, Throwable th) {
        d(str, generateMsg(wrapLogThrowable(str2, th)));
    }

    public static void v(String str, String str2) {
        if (DiagnosticAssistant.getInstance().isInitSuccess()) {
            SQLog.log(2, str, str2, null);
        } else {
            Log.v(str, generateMsg(str2));
        }
    }

    public static void v(String str, String str2, Throwable th) {
        v(str, generateMsg(wrapLogThrowable(str2, th)));
    }

    private static StackTraceElement getStackTraceElement() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null || stackTrace.length < 6) {
            return null;
        }
        for (int i = 5; i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();
            if (className != null && !className.endsWith("LogUtil") && !className.endsWith(LogUtils.TAG)) {
                return stackTrace[i];
            }
        }
        return null;
    }

    private static String generateMsg(String str) {
        StackTraceElement stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            return str;
        }
        return "[" + stackTraceElement + "]:" + str;
    }

    private static String wrapLogThrowable(String str, Throwable th) {
        if (th == null) {
            return str;
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        Throwable cause = th.getCause();
        if (cause != null) {
            cause.printStackTrace(printWriter);
        }
        return str + ShellAdbUtils.COMMAND_LINE_END + stringWriter;
    }
}
