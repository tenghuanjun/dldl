package com.bytedance.applog;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface IPullAbTestConfigCallback {
    void onRemoteConfig(JSONObject jSONObject);

    void onThrottle(long j);

    void onTimeoutError();
}
