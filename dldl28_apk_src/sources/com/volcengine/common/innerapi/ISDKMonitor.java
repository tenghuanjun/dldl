package com.volcengine.common.innerapi;

import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public interface ISDKMonitor {
    void init(int i, Map<String, String> map);

    void monitorCommonLog(String str, JSONObject jSONObject);

    void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);
}
