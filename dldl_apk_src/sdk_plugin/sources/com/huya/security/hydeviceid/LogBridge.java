package com.huya.security.hydeviceid;

import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LogBridge {
    public static void write(int i, String str) {
        try {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[r0.length - 1];
            NativeBridge.writeLog(i, stackTraceElement.getFileName(), stackTraceElement.getLineNumber(), str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getStackTraceAsString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.getBuffer().toString();
    }
}
