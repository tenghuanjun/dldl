package com.sq.tool.sqtools.detector;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TokenHelper {
    private static final String DEV_TOKEN = "devices_token";
    private static final String SQ_PREFS = "sq_prefs";

    public static boolean checkToken(Context context) {
        String token = getToken(context);
        if (token.isEmpty()) {
            return false;
        }
        try {
            long jConvert = TimeUnit.DAYS.convert(Math.abs(System.currentTimeMillis() - Long.parseLong(token.substring(0, 11), 16)), TimeUnit.MILLISECONDS);
            return jConvert < 3 || jConvert == 3;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean checkTokenExpired(Context context) {
        String token = getToken(context);
        if (token.isEmpty()) {
            return false;
        }
        return TimeUnit.DAYS.convert(Math.abs(System.currentTimeMillis() - Long.parseLong(token.substring(0, 11), 16)), TimeUnit.MILLISECONDS) < 5;
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(DEV_TOKEN, "");
    }

    public static void setToken(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(DEV_TOKEN, str);
        editorEdit.apply();
    }
}
