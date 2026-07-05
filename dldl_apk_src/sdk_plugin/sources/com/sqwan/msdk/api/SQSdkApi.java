package com.sqwan.msdk.api;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.parameters.share.ShareMessage;
import com.sqwan.msdk.api.tool.ILiveRadio;
import com.sqwan.msdk.api.tool.ILiveshow;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface SQSdkApi extends ILiveshow, ILiveRadio, SQSdkInterface {
    SQAppConfig getAppConfig();

    AssetManager getAssets(AssetManager assetManager);

    ClassLoader getClassLoader(ClassLoader classLoader);

    SQSdkInterface getPlatform(Context context, InitBean initBean, SQResultListener sQResultListener);

    Resources getResources(Resources resources);

    void init(Context context, String str, SQResultListener sQResultListener);

    void initCore(Context context, String str, SQResultListener sQResultListener);

    boolean isSupportPlugin();

    void log(int i, String str);

    void reportMDev(String str);

    void setSQPushTransmitMessageListener(SQPushTransmitMessageListener sQPushTransmitMessageListener);

    void share(ShareMessage shareMessage, SQResultListener sQResultListener);

    void share(String str, SQResultListener sQResultListener);

    void share(String str, String str2, SQResultListener sQResultListener);

    void shareToWX(Context context, String str, String str2, String str3, String str4, int i, SQResultListener sQResultListener);

    void showAdReward(Context context, String str, SQResultListener sQResultListener);

    void startActivity(Intent intent);

    void startActivityForResult(Intent intent, int i);

    void submitStatisticsInfo(String str, String str2);

    void track(String str, String str2, HashMap<String, String> map);
}
