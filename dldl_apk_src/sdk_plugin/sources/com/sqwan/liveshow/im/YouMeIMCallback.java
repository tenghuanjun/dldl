package com.sqwan.liveshow.im;

import com.youme.imsdk.YIMMessage;
import com.youme.imsdk.callback.YIMEventCallback;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class YouMeIMCallback implements YIMEventCallback.ReconnectCallback, YIMEventCallback.MessageEventCallback, YIMEventCallback.KickOffCallback {
    protected String TAG = getClass().getSimpleName();

    @Override // com.youme.imsdk.callback.YIMEventCallback.MessageEventCallback
    public void onGetRecognizeSpeechText(int i, long j, String str) {
    }

    @Override // com.youme.imsdk.callback.YIMEventCallback.KickOffCallback
    public void onKickOff() {
    }

    @Override // com.youme.imsdk.callback.YIMEventCallback.MessageEventCallback
    public void onRecordVolume(float f) {
    }

    @Override // com.youme.imsdk.callback.YIMEventCallback.MessageEventCallback
    public void onRecvMessage(YIMMessage yIMMessage) {
    }

    @Override // com.youme.imsdk.callback.YIMEventCallback.MessageEventCallback
    public void onRecvNewMessage(int i, String str) {
    }

    @Override // com.youme.imsdk.callback.YIMEventCallback.ReconnectCallback
    public void onRecvReconnectResult(int i) {
    }

    @Override // com.youme.imsdk.callback.YIMEventCallback.ReconnectCallback
    public void onStartReconnect() {
    }
}
