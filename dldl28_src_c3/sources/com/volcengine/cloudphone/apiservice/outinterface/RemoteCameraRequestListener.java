package com.volcengine.cloudphone.apiservice.outinterface;

import com.volcengine.cloudcore.common.mode.CameraId;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface RemoteCameraRequestListener {
    void onVideoStreamStartRequested(CameraId cameraId);

    void onVideoStreamStopRequested();
}
