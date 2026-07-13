package com.volcengine.cloudphone.apiservice.outinterface;

import com.volcengine.cloudcore.common.mode.LocalVideoStreamError;
import com.volcengine.cloudcore.common.mode.LocalVideoStreamState;

/* JADX INFO: loaded from: classes3.dex */
public interface CameraManagerListener {
    void onFirstCapture();

    void onLocalVideoStateChanged(LocalVideoStreamState localVideoStreamState, LocalVideoStreamError localVideoStreamError);
}
