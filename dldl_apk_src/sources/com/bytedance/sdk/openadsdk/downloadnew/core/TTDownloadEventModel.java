package com.bytedance.sdk.openadsdk.downloadnew.core;

import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class TTDownloadEventModel {
    private String a;
    private String b;
    private JSONObject c;
    private JSONObject d;

    public static TTDownloadEventModel builder() {
        return new TTDownloadEventModel();
    }

    public TTDownloadEventModel setMaterialMeta(JSONObject jSONObject) {
        this.d = jSONObject;
        return this;
    }

    public TTDownloadEventModel setTag(String str) {
        this.a = str;
        return this;
    }

    public TTDownloadEventModel setLabel(String str) {
        this.b = str;
        return this;
    }

    public TTDownloadEventModel setExtJson(JSONObject jSONObject) {
        this.c = jSONObject;
        return this;
    }

    public String getTag() {
        return this.a;
    }

    public String getLabel() {
        return this.b;
    }

    public JSONObject getExtJson() {
        return this.c;
    }

    public JSONObject getMaterialMeta() {
        return this.d;
    }
}
