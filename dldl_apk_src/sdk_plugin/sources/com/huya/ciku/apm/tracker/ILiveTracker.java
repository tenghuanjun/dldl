package com.huya.ciku.apm.tracker;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ILiveTracker {
    void beginLiveFail(ErrorCode errorCode);

    void beginLiveSuccess();

    void onAudioCaptureError();

    void onAudioCaptureResult();

    void onAudioEncodeResult();

    void onAudioMediaCodecError();

    void onAudioPublishReady(int i);

    void onCreateAudioMediaCodecError();

    void onCreateVideoCodecError();

    void onHuyaConnect(boolean z);

    void onHuyaPush(boolean z);

    void onRtmpConnect(boolean z);

    void onRtmpPush(boolean z);

    void onRtmpPushResult(int i);

    void onStartVideoCaptureError(boolean z);

    void onSwitchPush();

    void onVideoCaptureError(int i);

    void onVideoCaptureResult();

    void onVideoEncodeResult();

    void onVideoPublishReady(int i);

    void onVideoSendEnd(boolean z);

    void onVideoSendStart();

    void onVpConnect(boolean z);

    void onVpResult(int i);

    void start();

    void startConnectVp();

    void startHuyaPush();

    void startLive();

    void startRtmpPush();
}
