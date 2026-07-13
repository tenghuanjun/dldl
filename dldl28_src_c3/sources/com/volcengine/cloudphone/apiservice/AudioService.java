package com.volcengine.cloudphone.apiservice;

import com.volcengine.cloudcore.common.mode.LocalAudioStreamError;
import com.volcengine.cloudcore.common.mode.LocalAudioStreamState;
import com.volcengine.cloudphone.base.VeAudioFrame;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface AudioService {

    public interface AudioControlListener {
        void onAudioPlaybackDeviceChanged(int i);

        void onLocalAudioStateChanged(LocalAudioStreamState localAudioStreamState, LocalAudioStreamError localAudioStreamError);

        void onRemoteAudioPlaybackVolumeChanged(int i);

        void onRemoteAudioStartRequest();

        void onRemoteAudioStopRequest();
    }

    int getAudioPlaybackDevice();

    int getJitterBufferDelay();

    int getLocalAudioCaptureVolume();

    int getLocalAudioPlaybackVolume();

    int getRemoteAudioPlaybackVolume();

    boolean isEnableSendAudioStream();

    boolean isSendingAudioStream();

    int publishLocalAudio();

    int pushExternalAudioFrame(int i, VeAudioFrame veAudioFrame);

    void setAudioControlListener(AudioControlListener audioControlListener);

    void setAudioPlaybackDevice(int i);

    int setAudioSourceType(int i, int i2);

    void setAvSyncParam(String str, String str2);

    void setEnableSendAudioStream(boolean z);

    void setJitterBufferDelay(int i);

    int setLocalAudioCaptureVolume(int i);

    int setLocalAudioPlaybackVolume(int i);

    void setRemoteAudioPlaybackVolume(int i);

    int startSendAudioStream();

    int stopSendAudioStream();

    int unpublishLocalAudio();
}
