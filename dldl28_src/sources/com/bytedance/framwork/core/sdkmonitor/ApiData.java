package com.bytedance.framwork.core.sdkmonitor;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ApiData {
    public String apiType;
    public long duration;
    public JSONObject extJson;
    public String sendIp;
    public long sendTime;
    public String sendUrl;
    public int status;
    public String traceCode;

    public ApiData(String str, long j, long j2, String str2, String str3, String str4, int i, JSONObject jSONObject) {
        this.apiType = str;
        this.duration = j;
        this.sendTime = j2;
        this.sendUrl = str2;
        this.sendIp = str3;
        this.traceCode = str4;
        this.status = i;
        this.extJson = jSONObject;
    }
}
