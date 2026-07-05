package com.huya.force.cameracapture.impl;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import com.huya.force.log.ForceLog;
import java.io.IOException;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Camera1 {
    static final String TAG = "Camera1";
    private Camera mCamera;
    private CameraConfig mConfig;

    public boolean start(CameraConfig cameraConfig) {
        this.mConfig = cameraConfig;
        return open(cameraConfig);
    }

    private boolean open(CameraConfig cameraConfig) {
        try {
            openCamera(cameraConfig);
            Camera camera = this.mCamera;
            if (camera == null) {
                ForceLog.error(TAG, "camera open failed.");
                return false;
            }
            Camera.Parameters parameters = camera.getParameters();
            Camera.Size sizeChoosePreviewSize = Camera1Util.choosePreviewSize(parameters, Math.max(cameraConfig.width, cameraConfig.height), Math.min(cameraConfig.width, cameraConfig.height));
            if (sizeChoosePreviewSize != null) {
                ForceLog.info(TAG, String.format("choosePreviewSize Camera result size, width:%d height:%d", Integer.valueOf(sizeChoosePreviewSize.width), Integer.valueOf(sizeChoosePreviewSize.height)));
                parameters.setPreviewSize(sizeChoosePreviewSize.width, sizeChoosePreviewSize.height);
            } else {
                ForceLog.error(TAG, "result is null");
            }
            Camera1Util.chooseFixedPreviewFps(parameters, cameraConfig.fps * 1000);
            parameters.setWhiteBalance(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
            parameters.setRecordingHint(true);
            parameters.setPreviewFormat(17);
            if (parameters.getSupportedFocusModes().contains("continuous-video")) {
                parameters.setFocusMode("continuous-video");
            }
            if (Build.VERSION.SDK_INT >= 15 && parameters.isVideoStabilizationSupported()) {
                parameters.setVideoStabilization(true);
            }
            this.mCamera.setParameters(parameters);
            Camera1Util.setCameraDisplayOrientation(cameraConfig.weakContext.get(), this.mCamera, CameraType.toCamera1(cameraConfig.facing));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void openCamera(CameraConfig cameraConfig) {
        int camera1 = CameraType.toCamera1(cameraConfig.facing);
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        int i = 0;
        while (true) {
            if (i >= numberOfCameras) {
                break;
            }
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == camera1) {
                this.mCamera = Camera.open(i);
                break;
            }
            i++;
        }
        if (this.mCamera == null) {
            Log.d(TAG, "No front-facing camera found; opening default");
            this.mCamera = Camera.open();
        }
        if (this.mCamera == null) {
            throw new RuntimeException("Unable to open camera");
        }
    }

    public void stop() {
        Log.d(TAG, "releasing camera");
        stopPreview();
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.release();
            this.mCamera = null;
        }
    }

    public void startPreview() {
        if (this.mCamera == null) {
            ForceLog.error(TAG, "camera open failed.");
            return;
        }
        CameraConfig cameraConfig = this.mConfig;
        if (cameraConfig == null) {
            ForceLog.error(TAG, "mConfig == null.");
        } else if (cameraConfig.surfaceObject instanceof SurfaceTexture) {
            try {
                this.mCamera.setPreviewTexture((SurfaceTexture) this.mConfig.surfaceObject);
            } catch (IOException unused) {
                ForceLog.error(TAG, "");
            }
            this.mCamera.startPreview();
        }
    }

    public void stopPreview() {
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.stopPreview();
        }
    }

    public void switchCamera() {
        if (this.mConfig == null) {
            Log.e(TAG, "switchCamera, mConfig == null");
            return;
        }
        stop();
        CameraConfig cameraConfig = this.mConfig;
        if (cameraConfig != null) {
            cameraConfig.facing = CameraType.switchType(cameraConfig.facing);
        }
        if (!open(this.mConfig)) {
            ForceLog.error(TAG, "switchCamera open failed.");
        } else {
            startPreview();
        }
    }

    public void setZoom(boolean z) {
        Camera camera = this.mCamera;
        if (camera == null) {
            ForceLog.error(TAG, "setZoomIn, mCamera == null");
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        if (!parameters.isZoomSupported()) {
            ForceLog.error(TAG, "setZoomIn, mCamera not support zoom");
            return;
        }
        int maxZoom = parameters.getMaxZoom();
        int zoom = parameters.getZoom();
        if (z && zoom < maxZoom) {
            zoom++;
        } else if (!z && zoom > 0) {
            zoom--;
        }
        parameters.setZoom(zoom);
        this.mCamera.setParameters(parameters);
    }

    public void setFlash(boolean z) {
        Camera camera = this.mCamera;
        if (camera == null) {
            ForceLog.error(TAG, "setFlash, mCamera == null");
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(z ? "torch" : "off");
        this.mCamera.setParameters(parameters);
    }
}
