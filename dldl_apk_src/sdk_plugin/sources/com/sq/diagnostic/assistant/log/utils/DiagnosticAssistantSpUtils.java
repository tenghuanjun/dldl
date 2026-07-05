package com.sq.diagnostic.assistant.log.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DiagnosticAssistantSpUtils {
    public static final String SP_CACHE_HELP_DOC_URL = "help_doc_url";
    public static final String SP_CACHE_JSON_KEY = "ping_cache_json";
    private static final String SP_FILE_NAME = "diagnostic_assistant_config";

    public static String getString(Context context, String str, String str2) {
        return getSharedPreferences(context).getString(str, str2);
    }

    public static void putString(Context context, String str, String str2) {
        getSharedPreferences(context).edit().putString(str, str2).apply();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext().getSharedPreferences(SP_FILE_NAME, 0);
    }
}
