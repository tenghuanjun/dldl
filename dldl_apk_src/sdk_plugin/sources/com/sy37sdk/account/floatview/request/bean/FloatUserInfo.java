package com.sy37sdk.account.floatview.request.bean;

import android.text.TextUtils;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FloatUserInfo {
    private String avatar;
    private String level;
    private String nickName;
    private String uid;

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String str) {
        this.level = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String toString() {
        return "FloatUserInfo{nickName='" + this.nickName + "', avatar='" + this.avatar + "', level='" + this.level + "', uid='" + this.uid + '\'' + AbstractJsonLexerKt.END_OBJ;
    }

    public static String objectToJson(FloatUserInfo floatUserInfo) {
        if (floatUserInfo == null) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("nickname", floatUserInfo.getNickName());
            jSONObject.putOpt("avatar", floatUserInfo.getAvatar());
            jSONObject.putOpt("level", floatUserInfo.getLevel());
            jSONObject.putOpt("uid", floatUserInfo.getUid());
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static FloatUserInfo jsonToObject(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("nickname");
            String strOptString2 = jSONObject.optString("avatar");
            String strOptString3 = jSONObject.optString("level");
            String strOptString4 = jSONObject.optString("uid");
            FloatUserInfo floatUserInfo = new FloatUserInfo();
            floatUserInfo.setNickName(strOptString);
            floatUserInfo.setAvatar(strOptString2);
            floatUserInfo.setLevel(strOptString3);
            floatUserInfo.setUid(strOptString4);
            return floatUserInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static FloatUserInfo parse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("nickname");
            String strOptString2 = jSONObject.optString("avatar");
            String strOptString3 = jSONObject.optString("level");
            String strOptString4 = jSONObject.optString("uid");
            FloatUserInfo floatUserInfo = new FloatUserInfo();
            floatUserInfo.setNickName(strOptString);
            floatUserInfo.setAvatar(strOptString2);
            floatUserInfo.setLevel(strOptString3);
            floatUserInfo.setUid(strOptString4);
            return floatUserInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
