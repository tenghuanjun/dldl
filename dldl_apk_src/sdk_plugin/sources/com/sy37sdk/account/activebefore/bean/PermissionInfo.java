package com.sy37sdk.account.activebefore.bean;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PermissionInfo {
    private String is_necessary = "0";

    public boolean isNecessary() {
        return "1".equals(this.is_necessary);
    }

    public void parse(String str) {
        try {
            this.is_necessary = new JSONObject(str).optString("is_necessary");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
