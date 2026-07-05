package com.sqwan.common.util;

import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class JsonMap {
    private JSONObject json;

    public JsonMap put(String str, Object obj) {
        if (this.json == null) {
            this.json = new JSONObject();
        }
        try {
            this.json.put(str, obj);
        } catch (Exception unused) {
        }
        return this;
    }

    public String toString() {
        JSONObject jSONObject = this.json;
        return jSONObject != null ? jSONObject.toString() : "";
    }
}
