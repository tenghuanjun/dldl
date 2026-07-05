package com.alicom.tools.serialization;

import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public interface JSONer {
    void fromJson(JSONObject jSONObject);

    JSONObject toJson();
}
