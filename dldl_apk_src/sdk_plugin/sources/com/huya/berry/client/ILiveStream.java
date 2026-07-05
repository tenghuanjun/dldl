package com.huya.berry.client;

import android.app.Activity;
import android.content.Intent;
import com.huya.berry.client.HuyaBerry;
import com.huya.berry.client.customui.ICustomUI;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ILiveStream extends ICustomUI {
    Activity getActivity();

    void onActivityResult(int i, int i2, Intent intent);

    void openLiveList(StartLiveConfig startLiveConfig);

    void pauseLive(boolean z);

    void resetIsCallbackStartUp();

    void sendGameUpData(String str);

    void sendPlayerData(HuyaBerry.BerryPlayerDataHelper berryPlayerDataHelper);

    void setLandscape(boolean z);

    void startLiveDirectly(String str, String str2);

    void uninit();
}
