package com.sy37sdk.account.face.data;

import com.sy37sdk.account.face.ui.FaceVerifyConfirmActivity;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FaceVerifyData {
    private String idCardName;
    private String name;
    private boolean needVerify;
    private String verifyTip;

    public String getIdCardName() {
        return this.idCardName;
    }

    public void setIdCardName(String str) {
        this.idCardName = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isNeedVerify() {
        return this.needVerify;
    }

    public void setNeedVerify(boolean z) {
        this.needVerify = z;
    }

    public String getVerifyTip() {
        return this.verifyTip;
    }

    public void setVerifyTip(String str) {
        this.verifyTip = str;
    }

    public String toString() {
        return "FaceVerifyData{idCardName='" + this.idCardName + "', name='" + this.name + "', needVerify=" + this.needVerify + ", verifyTip='" + this.verifyTip + '\'' + AbstractJsonLexerKt.END_OBJ;
    }

    public static FaceVerifyData jsonToObject(String str) {
        FaceVerifyData faceVerifyData = new FaceVerifyData();
        try {
            JSONObject jSONObject = new JSONObject(str);
            boolean z = true;
            if (jSONObject.optInt("is_need_verify") != 1) {
                z = false;
            }
            faceVerifyData.setNeedVerify(z);
            faceVerifyData.setName(jSONObject.optString("name"));
            faceVerifyData.setIdCardName(jSONObject.optString("id_card_num"));
            faceVerifyData.setVerifyTip(jSONObject.optString(FaceVerifyConfirmActivity.BUNDLE_VERIFY_TIP));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return faceVerifyData;
    }
}
