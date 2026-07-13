package com.volcengine.cloudphone.apiservice.outinterface;

import com.volcengine.cloudcore.common.mode.LocalVideoStreamError;
import com.volcengine.cloudcore.common.mode.LocalVideoStreamState;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface CameraManagerListener {
    void onFirstCapture();

    void onLocalVideoStateChanged(LocalVideoStreamState localVideoStreamState, LocalVideoStreamError localVideoStreamError);
}
