package com.bytedance.framwork.core.sdkmonitor;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class ServiceMonitorData {
    public JSONObject category;
    JSONObject extrJson;
    public JSONObject metric;
    String serviceName;
    int status;
    long timestamp;
    JSONObject value;

    public ServiceMonitorData(String str, int i, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, long j) {
        this.serviceName = str;
        this.status = i;
        this.value = jSONObject;
        this.category = jSONObject2;
        this.metric = jSONObject3;
        this.extrJson = jSONObject4;
        this.timestamp = j;
    }
}
