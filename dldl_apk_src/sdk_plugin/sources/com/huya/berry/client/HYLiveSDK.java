package com.huya.berry.client;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import com.duowan.auk.util.L;
import com.huya.berry.client.HuyaBerryConfig;
import com.huya.berry.client.StartLiveConfig;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HYLiveSDK {
    public static final String TAG = "HYLiveSDK";

    public static void initHYLiveSDK(Application application, boolean z, String str, String str2, int i) {
        HuyaBerry.instance().init(application, new HuyaBerryConfig.Builder().appId(str).appKey(str2).gameId(i).debugMode(z).build());
        L.info(TAG, "initHYLiveSDK,debugMode:" + z + ",appId:" + str + ",appKey:" + str2 + ",gameId:" + i);
    }

    public static void openHYLiveSDK(Activity activity, boolean z, boolean z2) {
        L.info(TAG, "openHYLiveSDK,landscapeMode:" + z + ",smallwindow:" + z2);
        HuyaBerry.instance().startLive(activity, new StartLiveConfig.Builder().landscapeMode(z).smallwindow(z2).build());
    }

    public static void changeLandscapeHYLiveSDK(boolean z) {
        L.info(TAG, "changeLandscapeHYLiveSDK,landscapeMode:" + z);
        HuyaBerry.instance().changeLandscapeMode(z);
    }

    public static void exitHYLiveSDK() {
        L.info(TAG, "exitHYLiveSDK");
        HuyaBerry.instance().uninit();
    }

    public static void onActivityResult(int i, int i2, Intent intent) {
        L.info(TAG, "onActivityResult,requestCode:" + i + ",resultCode:" + i2 + ",data:" + intent);
        HuyaBerry.instance().onActivityResult(i, i2, intent);
    }
}
