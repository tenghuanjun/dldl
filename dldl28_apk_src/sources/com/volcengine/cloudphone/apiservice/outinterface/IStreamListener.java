package com.volcengine.cloudphone.apiservice.outinterface;

import com.volcengine.androidcloud.common.model.StreamStats;
import com.volcengine.cloudcore.common.mode.LocalStreamStats;

/* JADX INFO: loaded from: classes3.dex */
public interface IStreamListener {
    void onDetectDelay(long j);

    void onFirstAudioFrame(String str);

    void onFirstRemoteVideoFrame(String str);

    void onLocalStreamStats(LocalStreamStats localStreamStats);

    void onNetworkQuality(int i);

    void onPodExit(int i, String str);

    void onRotation(int i);

    void onStreamConnectionStateChanged(int i);

    void onStreamPaused();

    void onStreamResumed();

    void onStreamStarted();

    void onStreamStats(StreamStats streamStats);
}
