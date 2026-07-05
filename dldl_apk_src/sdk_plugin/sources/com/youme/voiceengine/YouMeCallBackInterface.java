package com.youme.voiceengine;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface YouMeCallBackInterface {
    void onBroadcast(int i, String str, String str2, String str3, String str4);

    void onEvent(int i, int i2, String str, Object obj);

    void onMemberChange(String str, MemberChange[] memberChangeArr, boolean z);

    void onRequestRestAPI(int i, int i2, String str, String str2);
}
