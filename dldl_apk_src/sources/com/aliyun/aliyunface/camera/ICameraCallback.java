package com.aliyun.aliyunface.camera;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface ICameraCallback {
    void onError(int i);

    void onPreviewFrame(CameraData cameraData);

    void onSurfaceChanged(double d, double d2);

    void onSurfaceCreated();

    void onSurfaceDestroyed();
}
