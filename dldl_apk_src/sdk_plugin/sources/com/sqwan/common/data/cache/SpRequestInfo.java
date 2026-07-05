package com.sqwan.common.data.cache;

import android.content.Context;
import android.content.SharedPreferences;
import com.sqwan.common.net.base.RequestUtil;
import com.sqwan.common.util.RandomUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SpRequestInfo {
    private static final String REQUEST_LIVE_ID = "request_live_id";
    private static final String REQUEST_PREF = "request_prefs";

    public static void setRequestLiveId(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(REQUEST_PREF, 0).edit();
        editorEdit.putString(REQUEST_LIVE_ID, RequestUtil.generateRequestLiveId(System.currentTimeMillis(), RandomUtils.randomData(16)));
        editorEdit.apply();
    }

    public static String getRequestLiveId(Context context) {
        return context.getSharedPreferences(REQUEST_PREF, 0).getString(REQUEST_LIVE_ID, "");
    }
}
