package com.sy37sdk.account.activebefore;

import android.text.TextUtils;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UserProtocolInfo extends ActiveBeforeBaseInfo {
    public String userprotol = "";
    public String privacyprotol = "";

    @Override // com.sy37sdk.account.activebefore.ActiveBeforeBaseInfo
    public void parse(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("url_protocol");
            String strOptString2 = jSONObject.optString("url_policy");
            if (!TextUtils.isEmpty(strOptString)) {
                this.userprotol = strOptString + "&isAgree=true";
            }
            if (TextUtils.isEmpty(strOptString2)) {
                return;
            }
            this.privacyprotol = strOptString2 + "&isAgree=true";
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "UserProtocolInfo{userprotol='" + this.userprotol + "', privacyprotol='" + this.privacyprotol + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
