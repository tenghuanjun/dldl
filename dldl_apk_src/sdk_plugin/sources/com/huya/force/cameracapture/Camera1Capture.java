package com.huya.force.cameracapture;

import android.graphics.SurfaceTexture;
import com.huya.force.cameracapture.impl.Camera1;
import com.huya.force.cameracapture.impl.CameraConfig;
import com.huya.force.common.VideoFrameData;
import com.huya.force.export.cameracapture.BaseCameraCapture;
import com.huya.force.export.surface.ISurface;
import com.huya.force.export.surface.SurfaceConfig;
import com.huya.force.export.videocapture.BaseVideoCapture;
import com.huya.force.export.videocapture.VideoCaptureInput;
import com.huya.force.log.ForceLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Camera1Capture extends BaseCameraCapture implements ISurface.Listener {
    private static final String TAG = "Camera1Capture";
    private Camera1 mCamera;
    private Camera1CaptureInput mCamera1CaptureInput;
    private ISurface mSurface;

    @Override // com.huya.force.export.surface.ISurface.Listener
    public void makePreviewCurrent() {
    }

    public Camera1Capture(VideoCaptureInput videoCaptureInput) {
        super(videoCaptureInput);
        this.mCamera1CaptureInput = (Camera1CaptureInput) videoCaptureInput;
    }

    @Override // com.huya.force.export.videocapture.BaseVideoCapture
    public void start() {
        super.start();
        ForceLog.info(TAG, "start");
        if (this.mCamera1CaptureInput.getEglHandler() != null) {
            this.mCamera1CaptureInput.getEglHandler().post(new Runnable() { // from class: com.huya.force.cameracapture.Camera1Capture.1
                @Override // java.lang.Runnable
                public void run() {
                    Camera1Capture.this.startImpl();
                }
            });
        } else {
            startImpl();
        }
    }

    @Override // com.huya.force.export.videocapture.BaseVideoCapture
    public SurfaceTexture getPreviewSurfaceTexture() {
        return (SurfaceTexture) this.mSurface.getSurfaceObject();
    }

    @Override // com.huya.force.export.videocapture.BaseVideoCapture
    public void stop() {
        ForceLog.info(TAG, "stop");
        if (this.mCamera1CaptureInput.getEglHandler() != null) {
            this.mCamera1CaptureInput.getEglHandler().post(new Runnable() { // from class: com.huya.force.cameracapture.Camera1Capture.2
                @Override // java.lang.Runnable
                public void run() {
                    Camera1Capture.this.stopImpl();
                }
            });
        } else {
            stopImpl();
        }
    }

    @Override // com.huya.force.export.cameracapture.BaseCameraCapture
    public void switchCamera() {
        BaseCameraCapture.CameraPosition cameraPosition;
        if (this.mCamera != null) {
            if (this.mCamera1CaptureInput.getCameraPosition() == BaseCameraCapture.CameraPosition.kFront) {
                cameraPosition = BaseCameraCapture.CameraPosition.kBack;
            } else {
                cameraPosition = BaseCameraCapture.CameraPosition.kFront;
            }
            this.mCamera1CaptureInput.setCameraPosition(cameraPosition);
            this.mCamera.switchCamera();
        }
    }

    @Override // com.huya.force.export.surface.ISurface.Listener
    public void onFrameAvailable(VideoFrameData videoFrameData) {
        if (this.mListener == null || !this.mStarted) {
            return;
        }
        this.mListener.onFrameAvailable(videoFrameData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startImpl() {
        ISurface iSurfaceCreateSurface = SurfaceFactory.createSurface(this.mCamera1CaptureInput.getSurfaceType());
        this.mSurface = iSurfaceCreateSurface;
        iSurfaceCreateSurface.setListener(this);
        this.mSurface.start(new SurfaceConfig(this.mCamera1CaptureInput.getWidth(), this.mCamera1CaptureInput.getHeight()));
        this.mCamera = new Camera1();
        if (!this.mCamera.start(new CameraConfig(this.mCamera1CaptureInput.getContext(), this.mCamera1CaptureInput.getCameraPosition().ordinal(), this.mCamera1CaptureInput.getWidth(), this.mCamera1CaptureInput.getHeight(), this.mCamera1CaptureInput.getFps(), this.mSurface.getSurfaceObject()))) {
            ForceLog.error(TAG, "camera start failed.");
            return;
        }
        this.mCamera.startPreview();
        this.mStarted = true;
        if (this.mListener != null) {
            this.mListener.onStartResult(BaseVideoCapture.VideoCaptureStartResult.kStartSuccess);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopImpl() {
        this.mStarted = false;
        ISurface iSurface = this.mSurface;
        if (iSurface != null) {
            iSurface.setListener(null);
            this.mSurface.stop();
            this.mSurface = null;
        }
        Camera1 camera1 = this.mCamera;
        if (camera1 != null) {
            camera1.stop();
            this.mCamera = null;
        }
        super.stop();
    }
}
