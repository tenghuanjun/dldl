package com.ss.android.socialbase.downloader.monitor;

import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface InnerEventListener {
    void onEvent(int i, String str, JSONObject jSONObject);

    void onUnityEvent(int i, String str, JSONObject jSONObject);
}
