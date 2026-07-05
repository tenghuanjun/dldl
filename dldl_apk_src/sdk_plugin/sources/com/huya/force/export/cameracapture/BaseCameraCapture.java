package com.huya.force.export.cameracapture;

import com.huya.force.export.videocapture.BaseVideoCapture;
import com.huya.force.export.videocapture.VideoCaptureInput;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseCameraCapture extends BaseVideoCapture {

    public enum CameraPosition {
        kFront,
        kBack
    }

    public abstract void switchCamera();

    public BaseCameraCapture(VideoCaptureInput videoCaptureInput) {
        super(videoCaptureInput);
    }
}
