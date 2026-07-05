package com.sq.webview.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SpUtil {
    private static final String WEB_SP = "webview_prefs";
    private static SharedPreferences sps;

    private static SharedPreferences getSps(Context context) {
        if (sps == null) {
            sps = context.getSharedPreferences(WEB_SP, 0);
        }
        return sps;
    }

    public static void putShareString(String key, String value, Context context) {
        if (TextUtils.isEmpty(value)) {
            return;
        }
        SharedPreferences.Editor editorEdit = getSps(context).edit();
        editorEdit.putString(key, value);
        editorEdit.apply();
    }

    public static String getShareString(String key, Context context) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        return getSps(context).getString(key, null);
    }
}
