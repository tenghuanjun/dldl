package com.mobile.auth.e;

import android.util.Log;
import com.igexin.assist.sdk.AssistPushConsts;
import com.unionpay.tsmservice.mini.data.Constant;
import layaair.game.conch.LayaConch5;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
class h {
    public static JSONObject a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constant.KEY_RESULT_CODE, str);
            jSONObject.put("resultDesc", str2);
        } catch (JSONException e) {
            Log.e("AuthnResult", "JSONException", e);
        }
        return jSONObject;
    }

    static JSONObject a(String str, String str2, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
        String str3;
        String str4;
        String str5;
        JSONObject jSONObject2 = new JSONObject();
        try {
            int i = Integer.parseInt(aVar.a("authtype", "0"));
            int iA = aVar.a("networkType");
            if (i == 2) {
                str3 = "7";
                str4 = "短信验证码";
            } else if (i != 3) {
                if (i != 4) {
                    str3 = "0";
                    str4 = "其他";
                } else {
                    str3 = "3";
                    str4 = "短信上行";
                }
            } else if (iA == 3) {
                str4 = "WIFI下网关鉴权";
                str3 = "1";
            } else {
                str4 = "网关鉴权";
                str3 = "2";
            }
            jSONObject2.put(Constant.KEY_RESULT_CODE, str);
            jSONObject2.put("authType", str3 + "");
            jSONObject2.put("authTypeDes", str4);
            if ("103000".equals(str)) {
                if (1 == aVar.a(LayaConch5.MARKET_LOGINTYPE, 0)) {
                    jSONObject2.put("openId", aVar.c("openId"));
                }
                str5 = AssistPushConsts.MSG_TYPE_TOKEN;
                str2 = jSONObject.optString(AssistPushConsts.MSG_TYPE_TOKEN);
            } else {
                str5 = "resultDesc";
            }
            jSONObject2.put(str5, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.mobile.auth.k.f.b("AuthnResult", "返回参数:" + jSONObject2.toString());
        return jSONObject2;
    }
}
