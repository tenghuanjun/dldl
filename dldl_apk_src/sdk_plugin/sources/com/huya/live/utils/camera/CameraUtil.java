package com.huya.live.utils.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CameraUtil {
    private static String TAG = "CameraUtil";
    public static boolean isLightCanUsed = true;
    public static Camera mCamera;
    public static boolean mIsLandscape;

    public static void openCamera(Context context, int i, int i2, boolean z, int i3) throws Exception {
        openCamera(i, i2, z, i3, isScreenOriatationLandscape(context));
    }

    public static void openCamera(int i, int i2, boolean z, int i3, boolean z2) throws Exception {
        if (mCamera != null) {
            releaseCamera();
        }
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        int i4 = 0;
        while (true) {
            if (i4 >= numberOfCameras) {
                break;
            }
            Camera.getCameraInfo(i4, cameraInfo);
            if (z) {
                if (cameraInfo.facing == 1) {
                    mCamera = Camera.open(i4);
                    isLightCanUsed = false;
                    break;
                }
                i4++;
            } else {
                if (cameraInfo.facing == 0) {
                    mCamera = Camera.open(i4);
                    isLightCanUsed = true;
                    break;
                }
                i4++;
            }
        }
        if (mCamera == null) {
            mCamera = Camera.open();
            isLightCanUsed = true;
        }
        Camera camera = mCamera;
        if (camera == null) {
            throw new RuntimeException("unable to open camera");
        }
        Camera.Parameters parameters = camera.getParameters();
        if (parameters.getSupportedFocusModes().contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
        choosePreviewSize(parameters, i, i2, i3);
        mCamera.setParameters(parameters);
        mIsLandscape = z2;
        mCamera.setDisplayOrientation(z2 ? 0 : 90);
    }

    public static void setCameraOrientation(boolean z) {
        Camera camera = mCamera;
        if (camera == null) {
            return;
        }
        if (z) {
            camera.setDisplayOrientation(0);
        } else {
            camera.setDisplayOrientation(90);
        }
    }

    public static boolean isScreenOriatationLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static void choosePreviewSize(Camera.Parameters parameters, int i, int i2, int i3) {
        List<Integer> supportedPreviewFrameRates = parameters.getSupportedPreviewFrameRates();
        if (supportedPreviewFrameRates != null) {
            Iterator<Integer> it = supportedPreviewFrameRates.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().intValue() == i3) {
                    parameters.setPreviewFrameRate(i3);
                    break;
                }
            }
        }
        Camera.Size previewSize = parameters.getPreviewSize();
        Camera.Size propPreviewSize = getPropPreviewSize(parameters.getSupportedPreviewSizes(), (i * 1.0f) / i2, i);
        if (propPreviewSize != null) {
            previewSize = propPreviewSize;
        }
        if (previewSize != null) {
            parameters.setPreviewSize(previewSize.width, previewSize.height);
            Log.i(TAG, String.format("camera finish:%d-%d", Integer.valueOf(previewSize.width), Integer.valueOf(previewSize.height)));
        }
    }

    public static Camera.Size getPropPreviewSize(List<Camera.Size> list, float f, int i) {
        Collections.sort(list, new CameraSizeComparator());
        int i2 = 0;
        for (Camera.Size size : list) {
            if (size.width >= i && equalRate(size, f)) {
                break;
            }
            i2++;
        }
        if (i2 == list.size()) {
            return null;
        }
        return list.get(i2);
    }

    public static class CameraSizeComparator implements Comparator<Camera.Size> {
        @Override // java.util.Comparator
        public int compare(Camera.Size size, Camera.Size size2) {
            if (size.width == size2.width) {
                return 0;
            }
            return size.width > size2.width ? 1 : -1;
        }
    }

    public static boolean equalRate(Camera.Size size, float f) {
        return ((double) Math.abs((((float) size.width) / ((float) size.height)) - f)) <= 0.03d;
    }

    public static void releaseCamera() {
        Camera camera = mCamera;
        if (camera != null) {
            try {
                camera.stopPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mCamera.release();
            mCamera = null;
        }
    }

    public static void cameraLightingOn(boolean z) {
        Camera camera = mCamera;
        if (camera == null) {
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        if (z) {
            parameters.setFlashMode("torch");
        } else {
            parameters.setFlashMode("off");
        }
        mCamera.setParameters(parameters);
    }

    public static int cameraNum() {
        return Camera.getNumberOfCameras();
    }

    public static void setZoom(boolean z) {
        Camera.Parameters parameters = mCamera.getParameters();
        if (parameters.isZoomSupported()) {
            int maxZoom = parameters.getMaxZoom();
            int zoom = parameters.getZoom();
            if (z && zoom < maxZoom) {
                zoom++;
            } else if (!z && zoom > 0) {
                zoom--;
            }
            parameters.setZoom(zoom);
            try {
                mCamera.setParameters(parameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
