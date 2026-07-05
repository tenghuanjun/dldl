package com.youme.voiceengine;

import com.sqwan.msdk.BaseSQwanCore;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class IYouMeEventCallback {
    public static YouMeCallBackInterface callBack;
    public static YouMeCallBackInterfacePcm mCallbackPcm;
    public static YouMeCallBackInterfacePcmForUnity mCallbackPcmForUntiy;

    public static void onEvent(int i, int i2, String str, String str2) {
        YouMeCallBackInterface youMeCallBackInterface = callBack;
        if (youMeCallBackInterface != null) {
            youMeCallBackInterface.onEvent(i, i2, str, str2);
        }
    }

    public static void onEventByte(int i, int i2, String str, byte[] bArr) {
        if (callBack != null) {
            callBack.onEvent(i, i2, str, new String(bArr));
        }
    }

    public static void onPcmDataRemote(int i, int i2, int i3, byte[] bArr) {
        YouMeCallBackInterfacePcm youMeCallBackInterfacePcm = mCallbackPcm;
        if (youMeCallBackInterfacePcm != null) {
            youMeCallBackInterfacePcm.onPcmDataRemote(i, i2, i3, bArr);
            return;
        }
        YouMeCallBackInterfacePcmForUnity youMeCallBackInterfacePcmForUnity = mCallbackPcmForUntiy;
        if (youMeCallBackInterfacePcmForUnity != null) {
            youMeCallBackInterfacePcmForUnity.onPcmDataRemote(i, i2, i3, new YouMePcmDataForUnity(bArr));
        }
    }

    public static void onPcmDataRecord(int i, int i2, int i3, byte[] bArr) {
        YouMeCallBackInterfacePcm youMeCallBackInterfacePcm = mCallbackPcm;
        if (youMeCallBackInterfacePcm != null) {
            youMeCallBackInterfacePcm.onPcmDataRecord(i, i2, i3, bArr);
            return;
        }
        YouMeCallBackInterfacePcmForUnity youMeCallBackInterfacePcmForUnity = mCallbackPcmForUntiy;
        if (youMeCallBackInterfacePcmForUnity != null) {
            youMeCallBackInterfacePcmForUnity.onPcmDataRecord(i, i2, i3, new YouMePcmDataForUnity(bArr));
        }
    }

    public static void onPcmDataMix(int i, int i2, int i3, byte[] bArr) {
        YouMeCallBackInterfacePcm youMeCallBackInterfacePcm = mCallbackPcm;
        if (youMeCallBackInterfacePcm != null) {
            youMeCallBackInterfacePcm.onPcmDataMix(i, i2, i3, bArr);
            return;
        }
        YouMeCallBackInterfacePcmForUnity youMeCallBackInterfacePcmForUnity = mCallbackPcmForUntiy;
        if (youMeCallBackInterfacePcmForUnity != null) {
            youMeCallBackInterfacePcmForUnity.onPcmDataMix(i, i2, i3, new YouMePcmDataForUnity(bArr));
        }
    }

    public static void onRequestRestAPI(int i, int i2, String str, String str2) {
        YouMeCallBackInterface youMeCallBackInterface = callBack;
        if (youMeCallBackInterface != null) {
            youMeCallBackInterface.onRequestRestAPI(i, i2, str, str2);
        }
    }

    public static void onMemberChange(String str, String str2, boolean z) {
        if (callBack != null) {
            try {
                JSONArray jSONArrayOptJSONArray = new JSONObject(str2).optJSONArray("memchange");
                if (jSONArrayOptJSONArray != null) {
                    int length = jSONArrayOptJSONArray.length();
                    MemberChange[] memberChangeArr = new MemberChange[length];
                    for (int i = 0; i < length; i++) {
                        memberChangeArr[i] = new MemberChange();
                        memberChangeArr[i].userID = jSONArrayOptJSONArray.optJSONObject(i).optString(BaseSQwanCore.LOGIN_KEY_USERID);
                        memberChangeArr[i].isJoin = jSONArrayOptJSONArray.optJSONObject(i).optBoolean("isJoin");
                    }
                    callBack.onMemberChange(str, memberChangeArr, z);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void onBroadcast(int i, String str, String str2, String str3, String str4) {
        YouMeCallBackInterface youMeCallBackInterface = callBack;
        if (youMeCallBackInterface != null) {
            youMeCallBackInterface.onBroadcast(i, str, str2, str3, str4);
        }
    }
}
