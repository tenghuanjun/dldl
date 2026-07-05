package com.huya.berry.client.customui;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ICustomUI {
    void getLiveUrl(String str, CustomUICallback customUICallback);

    void onGetAuthorInfo(CustomUICallback customUICallback);

    void onGetResolution(CustomUICallback customUICallback);

    void onLogin(CustomUICallback customUICallback);

    void onLogout(CustomUICallback customUICallback);

    void onModifyAnnouncement(CustomUICallback customUICallback, String str);

    void onModifyTitle(CustomUICallback customUICallback, String str);

    void onSetResolution(CustomUICallback customUICallback, int i);

    void onShowModifyNickname(CustomUICallback customUICallback);

    void onStartLive(CustomUICallback customUICallback);

    void setFromStartlive();

    void stopRtmpLive(CustomUICallback customUICallback);
}
