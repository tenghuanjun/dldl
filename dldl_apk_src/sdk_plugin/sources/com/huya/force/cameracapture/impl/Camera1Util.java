package com.huya.force.cameracapture.impl;

import android.content.Context;
import android.hardware.Camera;
import android.view.WindowManager;
import com.huya.force.log.ForceLog;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class Camera1Util {
    private static final String TAG = "Camera1";

    private static float ratio(int i, int i2) {
        return (i * 1.0f) / i2;
    }

    static void setOriention() {
    }

    Camera1Util() {
    }

    static void setCameraDisplayOrientation(Context context, Camera camera, int i) {
        int i2;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        int i3 = 0;
        if (rotation != 0) {
            if (rotation == 1) {
                i3 = 90;
            } else if (rotation == 2) {
                i3 = 180;
            } else if (rotation == 3) {
                i3 = 270;
            }
        }
        if (cameraInfo.facing == 1) {
            i2 = (360 - ((cameraInfo.orientation + i3) % 360)) % 360;
        } else {
            i2 = ((cameraInfo.orientation - i3) + 360) % 360;
        }
        camera.setDisplayOrientation(i2);
    }

    static Camera.Size choosePreviewSize(Camera.Parameters parameters, int i, int i2) {
        double dAbs;
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        ForceLog.info(TAG, "choosePreviewSize expect width:" + i + " height:" + i2);
        for (Camera.Size size : supportedPreviewSizes) {
            ForceLog.info(TAG, "choosePreviewSize camera support width:" + size.width + " height:" + size.height);
        }
        Camera.Size size2 = null;
        double d = 10.0d;
        int i3 = -10000;
        double d2 = (((double) i) * 1.0d) / ((double) i2);
        for (Camera.Size size3 : supportedPreviewSizes) {
            double d3 = (((double) size3.width) * 1.0d) / ((double) size3.height);
            int i4 = ((size3.width - i) + size3.height) - i2;
            if (i4 >= 0) {
                i4 = 10000 - i4;
            }
            double d4 = d2 - d3;
            if (Math.abs(d - Math.abs(d4)) < 0.1d) {
                if (i4 > i3) {
                    dAbs = Math.abs(d4);
                    d = dAbs;
                    size2 = size3;
                    i3 = i4;
                }
            } else if (d > Math.abs(d4)) {
                dAbs = Math.abs(d4);
                d = dAbs;
                size2 = size3;
                i3 = i4;
            }
        }
        return size2;
    }

    static int chooseFixedPreviewFps(Camera.Parameters parameters, int i) {
        int i2;
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        for (int[] iArr : supportedPreviewFpsRange) {
            ForceLog.info(TAG, String.format("camera preview fps, minFps=%d, maxFps=%d", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1])));
        }
        for (int[] iArr2 : supportedPreviewFpsRange) {
            if (iArr2[0] == iArr2[1] && iArr2[0] == i) {
                parameters.setPreviewFpsRange(iArr2[0], iArr2[1]);
                ForceLog.info(TAG, String.format("camera preview setFps, minFps=%d, maxFps=%d", Integer.valueOf(iArr2[0]), Integer.valueOf(iArr2[1])));
                return iArr2[0];
            }
        }
        int[] iArr3 = new int[2];
        parameters.getPreviewFpsRange(iArr3);
        if (iArr3[0] == iArr3[1]) {
            i2 = iArr3[0];
        } else {
            i2 = iArr3[1] / 2;
        }
        ForceLog.info(TAG, "Couldn't find match for " + i + ", using " + i2);
        return i2;
    }

    static void chooseFps(Camera.Parameters parameters, int i) {
        int[] iArr = null;
        for (int[] iArr2 : parameters.getSupportedPreviewFpsRange()) {
            if (iArr == null || iArr2[1] > iArr[1] || (iArr2[1] == iArr[1] && iArr2[0] < iArr[0])) {
                iArr = iArr2;
            }
        }
        if (iArr != null) {
            parameters.setPreviewFpsRange(iArr[0], iArr[1]);
        }
    }
}
