package com.bytedance.applog;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface ISessionObserver {
    void onSessionBatchEvent(long j, String str, JSONObject jSONObject);

    void onSessionStart(long j, String str);

    void onSessionTerminate(long j, String str, JSONObject jSONObject);
}
