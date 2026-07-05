package com.aliyun.aliyunface.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import com.aliyun.aliyunface.config.DeviceSetting;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface ICameraInterface {
    public static final String KEY_FACE_REGION = "faceRegion";

    boolean beautifyAvatar(Bitmap bitmap);

    void closeCamera();

    PointF colorToDepth(PointF pointF);

    PointF depthToColor(PointF pointF);

    Camera getCamera();

    CameraParams getCameraParams();

    int getCameraPictureAngle();

    int getCameraRotation();

    String getCameraSN();

    int getCameraViewRotation();

    int getColorHeight();

    int getColorMode();

    int getColorWidth();

    int getDepthHeight();

    int getDepthWidth();

    String getFirmwareVersion();

    int getPreviewHeight();

    int getPreviewWidth();

    Rect getROI();

    Object getUVCCamera();

    void initCamera(Context context, boolean z, boolean z2, DeviceSetting deviceSetting);

    boolean isMirror();

    void lockCameraWhiteBalanceAndExposure();

    void openCamera(DeviceSetting deviceSetting);

    void releaseCamera();

    void setCallback(ICameraCallback iCameraCallback);

    boolean setDrawCapturing(boolean z);

    void setFrameAvailableListener(SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener);

    void setGLSurfaceViewListener(IGLSurfaceViewListener iGLSurfaceViewListener);

    void setRenderLayers(Map<String, Object> map);

    void startCamera();

    void startFpsCheck();

    void startPreview(SurfaceHolder surfaceHolder, float f, int i, int i2);

    void stopCamera();

    void stopFpsCheck();

    void stopPreview();

    void takePhoto(ICameraTakePicture iCameraTakePicture);

    void turnOffTakePhotoFlash();

    void turnOnTakePhotoFlash();

    void unlockCameraWhiteBalanceAndExposure();
}
