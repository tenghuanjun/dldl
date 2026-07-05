package com.sq.tool.sqtools.detector.log;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LogTools {
    static String TAG = "device_tools";

    public static void sendLog(String str) {
        Log.i(TAG, str);
    }
}
