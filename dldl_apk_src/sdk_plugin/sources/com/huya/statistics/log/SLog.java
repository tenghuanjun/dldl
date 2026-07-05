package com.huya.statistics.log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SLog {
    private static IL mL = new LogImp();

    public static void setLogImp(IL il) {
        mL = il;
    }

    public static void verbose(Object obj, String str, Object... objArr) {
        mL.verbose(obj, str, objArr);
    }

    public static void debug(Object obj, String str, Object... objArr) {
        mL.debug(obj, str, objArr);
    }

    public static void info(Object obj, String str, Object... objArr) {
        mL.info(obj, str, objArr);
    }

    public static void warn(Object obj, String str, Object... objArr) {
        mL.warn(obj, str, objArr);
    }

    public static void error(Object obj, String str, Object... objArr) {
        mL.error(obj, str, objArr);
    }
}
