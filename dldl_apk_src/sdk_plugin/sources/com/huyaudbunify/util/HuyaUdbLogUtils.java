package com.huyaudbunify.util;

import android.util.Log;
import com.google.gson.Gson;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaUdbLogUtils {
    public static void e(String str, Object obj) {
        if (!HuyaDeveloperUtils.getInstance().isDeveloper() || HuyaDeveloperUtils.getInstance().isForbidLog() || obj == null) {
            return;
        }
        Log.e("udb-android", str + ":" + new Gson().toJson(obj));
    }

    public static void eString(String str, String str2) {
        if (!HuyaDeveloperUtils.getInstance().isDeveloper() || HuyaDeveloperUtils.getInstance().isForbidLog()) {
            return;
        }
        Log.e("udb-android", str + ":" + str2);
    }
}
