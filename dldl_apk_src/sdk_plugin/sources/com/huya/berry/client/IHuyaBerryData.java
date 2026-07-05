package com.huya.berry.client;

import android.app.Activity;
import android.widget.FrameLayout;
import com.huya.berry.client.customui.CustomUICallback;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface IHuyaBerryData {
    void changeGameId(int i);

    void closeFloat();

    void customUIOpenQuality(Activity activity, CustomUICallback customUICallback);

    void customUIOpenSendDanmu(Activity activity, CustomUICallback customUICallback);

    void fullScreen();

    void fullScreen(long j);

    void getLiveDataByRoomId(long j, CustomUICallback customUICallback);

    void getLiveListDataByTag(String str, boolean z, CustomUICallback customUICallback);

    void getTagListData(CustomUICallback customUICallback);

    void hideDanmuView();

    void joinChannel(long j, int i);

    void onGetLiveData(long j, CustomUICallback customUICallback);

    void onGetLiveListData(boolean z, CustomUICallback customUICallback);

    void pauseVideoPlay();

    void querySubscribeStatus(long j, CustomUICallback customUICallback);

    void sendDanmu(long j, long j2, long j3, Activity activity, String str, CustomUICallback customUICallback);

    void setReceiveDanmuData(boolean z, long j);

    void showDanmuView(FrameLayout frameLayout, long j);

    void smallWindowPlay(Activity activity);

    void startVideoPlay();

    void subscribe(long j, CustomUICallback customUICallback);

    void switchDanmu(boolean z);

    void switchVoice(boolean z);

    void unSubscribe(long j, CustomUICallback customUICallback);

    void uninit();

    void watchLive(long j, Activity activity, CustomUICallback customUICallback);

    void watchLiveByUid(long j, Activity activity, CustomUICallback customUICallback);
}
