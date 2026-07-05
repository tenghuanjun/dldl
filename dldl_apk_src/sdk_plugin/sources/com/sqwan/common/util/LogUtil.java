package com.sqwan.common.util;

import com.sq.tool.logger.SQLog;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LogUtil {
    public static String mTag = "sqsdk";

    private static String wrapLogMsg(String str) {
        return str == null ? AbstractJsonLexerKt.NULL : str;
    }

    public static void setTag(String str) {
        mTag = str;
    }

    public static void v(String str) {
        v(mTag, str);
    }

    public static void e(String str) {
        e(mTag, str);
    }

    public static void i(String str) {
        i(mTag, str);
    }

    public static void d(String str) {
        d(mTag, str);
    }

    public static void w(String str) {
        w(mTag, str);
    }

    public static void v(String str, String str2) {
        SQLog.vt(str, wrapLogMsg(str2));
    }

    public static void e(String str, String str2) {
        SQLog.et(str, wrapLogMsg(str2));
    }

    public static void i(String str, String str2) {
        SQLog.it(str, wrapLogMsg(str2));
    }

    public static void d(String str, String str2) {
        SQLog.dt(str, wrapLogMsg(str2));
    }

    public static void w(String str, String str2) {
        SQLog.wt(str, wrapLogMsg(str2));
    }

    public static void e(String str, Throwable th) {
        e(mTag, str, th);
    }

    public static void v(String str, String str2, Throwable th) {
        SQLog.vt(str, wrapLogMsg(str2), th);
    }

    public static void e(String str, String str2, Throwable th) {
        SQLog.et(str, wrapLogMsg(str2), th);
    }

    public static void i(String str, String str2, Throwable th) {
        SQLog.it(str, wrapLogMsg(str2), th);
    }

    public static void d(String str, String str2, Throwable th) {
        SQLog.dt(str, wrapLogMsg(str2), th);
    }

    public static void w(String str, String str2, Throwable th) {
        SQLog.wt(str, wrapLogMsg(str2), th);
    }
}
