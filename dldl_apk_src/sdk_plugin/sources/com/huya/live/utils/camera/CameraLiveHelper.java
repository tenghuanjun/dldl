package com.huya.live.utils.camera;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CameraLiveHelper {
    private static final String TAG = "CameraLiveHelper";
    private static CameraLiveHelper instance = new CameraLiveHelper();
    public Camera mCamera;
    public boolean mIsLandscape;
    private boolean mIsPreviewing = false;
    public SurfaceTexture mSurface;

    private CameraLiveHelper() {
    }

    public static CameraLiveHelper getInstance() {
        return instance;
    }

    public boolean openCamera(boolean z, boolean z2) {
        return openCamera(720, 408, z, 20, z2);
    }

    public boolean openCamera(int i, int i2, boolean z, int i3, boolean z2) {
        if (i < i2) {
            i2 = i;
            i = i2;
        }
        try {
            CameraUtil.openCamera(i, i2, z, i3, z2);
            this.mIsLandscape = CameraUtil.mIsLandscape;
            this.mCamera = CameraUtil.mCamera;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setPreview(SurfaceTexture surfaceTexture) {
        setPreview(surfaceTexture, 0, 0, 20);
    }

    public void setPreview(SurfaceTexture surfaceTexture, int i, int i2, int i3) {
        if (i < i2) {
            i2 = i;
            i = i2;
        }
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                if (this.mIsPreviewing) {
                    camera.stopPreview();
                    this.mIsPreviewing = false;
                }
                this.mCamera.setPreviewTexture(surfaceTexture);
                this.mIsPreviewing = true;
                this.mSurface = surfaceTexture;
                Camera.Parameters parameters = this.mCamera.getParameters();
                if (parameters != null) {
                    initCamera(parameters, i, i2, i3);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setCameraOrientation(boolean z) {
        CameraUtil.setCameraOrientation(z);
        this.mIsLandscape = z;
    }

    public void stopPreview() {
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.stopPreview();
            this.mIsPreviewing = false;
        }
    }

    public void doStopCamera() {
        this.mIsPreviewing = false;
        if (this.mCamera != null) {
            this.mCamera = null;
            CameraUtil.releaseCamera();
        }
        if (this.mSurface != null) {
            this.mSurface = null;
        }
    }

    public void initCamera(Camera.Parameters parameters, int i, int i2, int i3) {
        if (this.mCamera == null) {
            return;
        }
        if (i != 0) {
            CameraUtil.choosePreviewSize(parameters, i, i2, i3);
            this.mCamera.setParameters(parameters);
        }
        try {
            this.mCamera.startPreview();
            this.mIsPreviewing = true;
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
