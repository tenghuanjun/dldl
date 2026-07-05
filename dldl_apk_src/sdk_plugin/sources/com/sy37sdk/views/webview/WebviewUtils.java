package com.sy37sdk.views.webview;

import android.content.Context;
import android.content.SharedPreferences;
import com.huya.mtp.hyns.report.NSPushReporter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Deprecated
public class WebviewUtils {
    public static final String PreName = "sq_webview_pf";

    public static void setUrlLoadCount(Context context, String str, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("sq_webview_pf", 0).edit();
        editorEdit.putInt(NSPushReporter.NS_PUSH_COUNT + str, i);
        editorEdit.commit();
    }

    public static int getUrlLoadCount(Context context, String str) {
        return context.getSharedPreferences("sq_webview_pf", 0).getInt(NSPushReporter.NS_PUSH_COUNT + str, 1);
    }

    public static void setUrlLoadTime(Context context, String str, long j) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("sq_webview_pf", 0).edit();
        editorEdit.putLong("time" + str, j);
        editorEdit.commit();
    }

    public static long getUrlLoadTime(Context context, String str) {
        return context.getSharedPreferences("sq_webview_pf", 0).getLong("time" + str, 1L);
    }

    public static boolean isLoadOneTime(Context context, String str, long j) {
        long jCurrentTimeMillis = System.currentTimeMillis() - getUrlLoadTime(context, str);
        System.out.println("webview loading time=" + jCurrentTimeMillis);
        return jCurrentTimeMillis <= j && jCurrentTimeMillis > 0;
    }

    public static void clearWebviewPrefs(Context context) {
        context.getSharedPreferences("sq_webview_pf", 0).edit().clear().commit();
    }
}
