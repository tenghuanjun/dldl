package com.sqwan.liveshow;

import com.sqwan.liveshow.common.IListenerManager;
import com.youme.voiceengine.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ListenerManager implements IListenerManager {
    @Override // com.sqwan.liveshow.common.IListenerManager
    public void uninit() {
    }

    @Override // com.sqwan.liveshow.common.IListenerManager
    public void setSpeakerMute(boolean z) {
        api.setSpeakerMute(z);
    }

    @Override // com.sqwan.liveshow.common.IListenerManager
    public void setMicrophoneMute(boolean z) {
        api.setMicrophoneMute(z);
    }

    @Override // com.sqwan.liveshow.common.IListenerManager
    public void setVolume(int i) {
        api.setVolume(i);
    }

    @Override // com.sqwan.liveshow.common.IListenerManager
    public void leaveRoom() {
        api.leaveChannelAll();
    }

    @Override // com.sqwan.liveshow.common.IListenerManager
    public void joinRoom(String str, String str2) {
        if (api.isJoined()) {
            return;
        }
        api.joinChannelSingleMode(str, str2, 3);
    }

    @Override // com.sqwan.liveshow.common.IListenerManager
    public void init() {
        setSpeakerMute(false);
        setMicrophoneMute(true);
        setVolume(100);
    }
}
