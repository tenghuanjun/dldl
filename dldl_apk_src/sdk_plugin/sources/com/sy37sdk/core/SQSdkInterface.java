package com.sy37sdk.core;

import android.content.Context;
import android.content.res.Configuration;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface SQSdkInterface {
    void changeAccount(Context context, SQResultListener sQResultListener);

    void init(Context context, String str, SQResultListener sQResultListener);

    void login(Context context, SQResultListener sQResultListener);

    void logout(Context context, SQResultListener sQResultListener);

    void onConfigurationChanged(Configuration configuration);

    void onPause();

    void onResume();

    void onStop();

    void pay(Context context, String str, String str2, String str3, float f, String str4, SQResultListener sQResultListener);

    void setAuthResultListener(SQResultListener sQResultListener);

    void setSwitchAccountListener(SQResultListener sQResultListener);

    void showAgeAppropriate(Context context);

    void showUAgreement(Context context);

    void submitRoleInfo(HashMap<String, String> map);
}
