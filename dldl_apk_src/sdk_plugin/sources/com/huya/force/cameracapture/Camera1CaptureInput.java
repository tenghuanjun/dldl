package com.huya.force.cameracapture;

import android.content.Context;
import android.os.Handler;
import com.huya.force.export.cameracapture.BaseCameraCapture;
import com.huya.force.export.cameracapture.CameraCaptureInput;
import com.huya.force.export.surface.ISurface;
import com.huya.force.export.videocapture.VideoCaptureInput;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Camera1CaptureInput extends CameraCaptureInput {
    ISurface.SurfaceType mSurfaceType;

    public Camera1CaptureInput(ISurface.SurfaceType surfaceType, Context context, int i, int i2, int i3, BaseCameraCapture.CameraPosition cameraPosition) {
        this(surfaceType, context, i, i2, i3, cameraPosition, null);
    }

    public Camera1CaptureInput(ISurface.SurfaceType surfaceType, Context context, int i, int i2, int i3, BaseCameraCapture.CameraPosition cameraPosition, Handler handler) {
        super(context, VideoCaptureInput.VideoCaptureType.kCameraCapture, i, i2, i3, cameraPosition, handler);
        this.mSurfaceType = surfaceType;
    }

    public ISurface.SurfaceType getSurfaceType() {
        return this.mSurfaceType;
    }
}
