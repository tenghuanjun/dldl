package com.sqwan.common.cache;

import android.content.Context;
import android.text.TextUtils;
import com.sqwan.common.util.SpUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DeviceCacheHelper {
    private static final String SQ_PREFS = "sq_prefs";

    public static void save(Context context, String str, String str2) throws Throwable {
        save(context, str, str2, true);
    }

    public static void save(Context context, String str, String str2, boolean z) throws Throwable {
        saveToSp(context, str, str2);
        if (z) {
            saveToSd(context, str, str2);
        }
    }

    public static String get(Context context, String str) {
        return getFromSp(context, str, true);
    }

    public static String get(Context context, String str, boolean z) {
        return getFromSp(context, str, z);
    }

    private static void saveToSp(Context context, String str, String str2) {
        new SpUtils(context, SQ_PREFS).put(str, str2);
    }

    private static String getFromSp(Context context, String str, boolean z) {
        String string = new SpUtils(context, SQ_PREFS).getString(str);
        if (string == null && z) {
            string = getFromSd(context, str);
            if (!TextUtils.isEmpty(string)) {
                saveToSp(context, str, string);
            }
        }
        return string;
    }

    private static void saveToSd(Context context, String str, String str2) throws Throwable {
        DeviceSdCache.saveValue(context, str, str2);
    }

    private static String getFromSd(Context context, String str) {
        return DeviceSdCache.getValue(context, str);
    }
}
