package com.sy37sdk.account;

import com.sqwan.common.constants.SqConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountConfig {
    public static String doubtGuideContent = "";
    public static String doubtGuideCopyContent = "";
    public static String downinfo_url = "";
    public static String exitDpgn = "";
    public static String exitImgPath = "";
    public static String exitUrl = "";
    public static String gameName = "";
    public static String notice_url = "";
    public static String packageName = "com.sy37.gamebox";
    public static String tips_downinfo = "";
    public static String tips_notice = "欢迎来到37手游，祝您游戏愉快!";

    public static void praseConfig(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("c")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("c");
                if (jSONObject2.has("content")) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("content");
                    doubtGuideContent = jSONObject3.optString("totastKf", "");
                    doubtGuideCopyContent = jSONObject3.optString("totastGzh", "");
                }
                if (jSONObject2.has("gg")) {
                    JSONObject jSONObject4 = jSONObject2.getJSONObject("gg");
                    tips_notice = jSONObject4.optString("n", "");
                    notice_url = jSONObject4.optString("u", "");
                }
                if (jSONObject2.has("dl")) {
                    JSONObject jSONObject5 = jSONObject2.getJSONObject("dl");
                    tips_downinfo = jSONObject5.optString("n", "");
                    downinfo_url = jSONObject5.optString("u", "");
                    packageName = jSONObject5.optString(SqConstants.DPGN, "");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
