package com.huya.force.export.cameracapture;

import android.content.Context;
import android.os.Handler;
import com.huya.force.export.cameracapture.BaseCameraCapture;
import com.huya.force.export.videocapture.VideoCaptureInput;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CameraCaptureInput extends VideoCaptureInput {
    BaseCameraCapture.CameraPosition mCameraPosition;

    public CameraCaptureInput(Context context, VideoCaptureInput.VideoCaptureType videoCaptureType, int i, int i2, int i3, BaseCameraCapture.CameraPosition cameraPosition) {
        super(context, videoCaptureType, i, i2, i3);
        this.mCameraPosition = cameraPosition;
    }

    public CameraCaptureInput(Context context, VideoCaptureInput.VideoCaptureType videoCaptureType, int i, int i2, int i3, BaseCameraCapture.CameraPosition cameraPosition, Handler handler) {
        super(context, videoCaptureType, i, i2, i3, handler);
        this.mCameraPosition = cameraPosition;
    }

    public BaseCameraCapture.CameraPosition getCameraPosition() {
        return this.mCameraPosition;
    }

    public void setCameraPosition(BaseCameraCapture.CameraPosition cameraPosition) {
        this.mCameraPosition = cameraPosition;
    }
}
