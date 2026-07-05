package com.sy37sdk.account.activebefore;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PermissionInfo extends ActiveBeforeBaseInfo {
    public static final String DESC_MIDDLE_PERMISSION = "1";
    public static final String DESC_NO_REQUEST_PERMISSION = "0";
    public static final String DESC_REQUEST_PERMISSION = "2";
    private String is_necessary = "0";
    private String sceneDesc = "0";

    @Deprecated
    public boolean isNecessary() {
        return "1".equals(this.is_necessary);
    }

    public String getSceneDesc() {
        return this.sceneDesc;
    }

    @Override // com.sy37sdk.account.activebefore.ActiveBeforeBaseInfo
    public void parse(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.is_necessary = jSONObject.optString("is_necessary");
            this.sceneDesc = jSONObject.optString("scene_desc");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "PermissionInfo{is_necessary='" + this.is_necessary + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
