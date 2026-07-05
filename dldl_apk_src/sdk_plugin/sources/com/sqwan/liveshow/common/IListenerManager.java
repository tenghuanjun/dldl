package com.sqwan.liveshow.common;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IListenerManager {
    void init();

    void joinRoom(String str, String str2);

    void leaveRoom();

    void setMicrophoneMute(boolean z);

    void setSpeakerMute(boolean z);

    void setVolume(int i);

    void uninit();
}
