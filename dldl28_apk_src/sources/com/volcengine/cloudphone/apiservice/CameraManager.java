package com.volcengine.cloudphone.apiservice;

import android.view.SurfaceView;
import com.volcengine.cloudcore.common.mode.CameraId;
import com.volcengine.cloudcore.common.mode.LocalVideoStreamDescription;
import com.volcengine.cloudcore.common.mode.MirrorMode;
import com.volcengine.cloudcore.common.mode.RenderMode;
import com.volcengine.cloudphone.apiservice.outinterface.CameraManagerListener;
import com.volcengine.cloudphone.apiservice.outinterface.RemoteCameraRequestListener;
import com.volcengine.cloudphone.base.VeVideoFrame;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface CameraManager {
    int publishLocalVideo();

    int pushExternalVideoFrame(int i, VeVideoFrame veVideoFrame);

    void setCameraManagerListener(CameraManagerListener cameraManagerListener);

    void setLocalVideoCanvas(SurfaceView surfaceView);

    void setLocalVideoCanvas(SurfaceView surfaceView, RenderMode renderMode);

    void setLocalVideoMirrorMode(MirrorMode mirrorMode);

    void setRemoteRequestListener(RemoteCameraRequestListener remoteCameraRequestListener);

    void setVideoEncoderConfig(List<LocalVideoStreamDescription> list);

    int setVideoSourceType(int i, int i2);

    int startVideoStream(CameraId cameraId);

    int stopVideoStream();

    int switchCamera(CameraId cameraId);

    int unpublishLocalVideo();
}
