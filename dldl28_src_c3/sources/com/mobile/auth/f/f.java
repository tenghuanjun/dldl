package com.mobile.auth.f;

import android.text.TextUtils;
import android.util.Log;
import com.tencent.open.SocialConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class f {
    static JSONObject a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", "103000");
            jSONObject.put(SocialConstants.PARAM_APP_DESC, "true");
            jSONObject.put("securityphone", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    static JSONObject a(String str, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
        String[] strArr = {"未知", "移动", "联通", "电信"};
        try {
            String strB = aVar.b("operatortype", "0");
            jSONObject.put("operatorType", ("0".equals(strB) || TextUtils.isEmpty(strB)) ? "103000".equals(str) ? strArr[1] : strArr[0] : strArr[Integer.parseInt(strB)]);
        } catch (Exception e) {
            Log.e("AuthnResult", "JSONException", e);
        }
        return jSONObject;
    }

    public static JSONObject a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", str);
            jSONObject.put(SocialConstants.PARAM_APP_DESC, str2);
        } catch (Exception e) {
            Log.e("AuthnResult", "Exception", e);
        }
        return jSONObject;
    }

    static JSONObject a(String str, String str2, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
        String str3;
        String str4;
        String str5;
        String str6 = "0";
        JSONObject jSONObject2 = new JSONObject();
        try {
            int i = Integer.parseInt(aVar.b("authType", "0"));
            int iC = aVar.c("networktype");
            if (i == 3) {
                if (iC == 3) {
                    str4 = "WIFI下网关鉴权";
                    str5 = "1";
                } else {
                    str4 = "网关鉴权";
                    str5 = "2";
                }
                String str7 = str5;
                str3 = str4;
                str6 = str7;
            } else {
                str3 = "其他";
            }
            jSONObject2.put("resultCode", str);
            jSONObject2.put("authType", str6);
            jSONObject2.put("authTypeDes", str3);
            if ("103000".equals(str)) {
                if (1 == aVar.c("logintype")) {
                    jSONObject2.put("openId", aVar.b("openId"));
                    jSONObject2.put("securityphone", aVar.b("securityphone"));
                }
                jSONObject2.put("token", jSONObject.optString("token"));
                jSONObject2.put("tokenExpiresIn", jSONObject.optString("tokenExpiresIn"));
            } else {
                jSONObject2.put(SocialConstants.PARAM_APP_DESC, str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.mobile.auth.m.c.b("AuthnResult", "返回参数:" + jSONObject2.toString());
        return jSONObject2;
    }

    static JSONObject b(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", str);
            jSONObject.put(SocialConstants.PARAM_APP_DESC, str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
