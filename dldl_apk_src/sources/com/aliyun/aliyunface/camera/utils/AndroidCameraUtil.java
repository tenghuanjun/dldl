package com.aliyun.aliyunface.camera.utils;

import android.hardware.Camera;
import android.os.Build;
import com.alipay.zoloz.toyger.ToygerLog;
import com.alipay.zoloz.toyger.blob.BlobManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class AndroidCameraUtil {
    private static AndroidCameraUtil myCamPara;
    private CameraSizeComparator sizeComparator = new CameraSizeComparator();

    private AndroidCameraUtil() {
    }

    public static synchronized AndroidCameraUtil getInstance() {
        if (myCamPara == null) {
            AndroidCameraUtil androidCameraUtil = new AndroidCameraUtil();
            myCamPara = androidCameraUtil;
            return androidCameraUtil;
        }
        return myCamPara;
    }

    public Camera.Size getPropPreviewSize(List<Camera.Size> list, final float f, int i) {
        if (list == null) {
            return null;
        }
        Collections.sort(list, new Comparator<Camera.Size>() { // from class: com.aliyun.aliyunface.camera.utils.AndroidCameraUtil.1
            @Override // java.util.Comparator
            public int compare(Camera.Size size, Camera.Size size2) {
                float fAbs = Math.abs((size.width / size.height) - f) - Math.abs((size2.width / size2.height) - f);
                if (Math.abs(fAbs) < 0.01d) {
                    return 0;
                }
                if (fAbs < 0.0f) {
                    return -1;
                }
                return fAbs > 0.0f ? 1 : 0;
            }
        });
        int size = 0;
        Iterator<Camera.Size> it = list.iterator();
        while (it.hasNext() && it.next().width < i) {
            size++;
        }
        if (size == list.size()) {
            size = list.size() - 1;
        }
        return list.get(size);
    }

    public Camera.Size getPropPreviewSize(List<Camera.Size> list, int i, int i2) {
        if (list == null) {
            return null;
        }
        Collections.sort(list, this.sizeComparator);
        for (Camera.Size size : list) {
            if (size.width >= i && size.height >= i2) {
                ToygerLog.i("PreviewSize:w = " + size.width + "h = " + size.height);
                return size;
            }
        }
        return null;
    }

    public Camera.Size getPropPictureSize(List<Camera.Size> list, float f, int i) {
        if (list == null) {
            return null;
        }
        Collections.sort(list, this.sizeComparator);
        float equalRate = 0.0f;
        int i2 = 0;
        int i3 = 0;
        for (Camera.Size size : list) {
            if (size.width >= i) {
                if (i2 == 0) {
                    equalRate = getEqualRate(size, f);
                    i2 = i3;
                }
                if (equalRate > getEqualRate(size, f)) {
                    equalRate = getEqualRate(size, f);
                    i2 = i3;
                }
            }
            i3++;
        }
        return list.get(i2);
    }

    private float getEqualRate(Camera.Size size, float f) {
        return Math.abs((size.width / size.height) - f);
    }

    public boolean equalRate(Camera.Size size, float f) {
        return ((double) Math.abs((((float) size.width) / ((float) size.height)) - f)) <= 0.03d;
    }

    public class CameraSizeComparator implements Comparator<Camera.Size> {
        public CameraSizeComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Camera.Size size, Camera.Size size2) {
            if (size.width == size2.width) {
                return size.height - size2.height;
            }
            return size.width > size2.width ? 1 : -1;
        }
    }

    public void printSupportPreviewSize(Camera.Parameters parameters) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null) {
            return;
        }
        for (int i = 0; i < supportedPreviewSizes.size(); i++) {
            Camera.Size size = supportedPreviewSizes.get(i);
            ToygerLog.i("previewSizes:width = " + size.width + " height = " + size.height);
        }
    }

    public void printSupportPictureSize(Camera.Parameters parameters) {
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        if (supportedPictureSizes == null) {
            return;
        }
        for (int i = 0; i < supportedPictureSizes.size(); i++) {
            Camera.Size size = supportedPictureSizes.get(i);
            ToygerLog.i("pictureSizes:width = " + size.width + " height = " + size.height);
        }
    }

    public void printSupportFocusMode(Camera.Parameters parameters) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes == null) {
            return;
        }
        Iterator<String> it = supportedFocusModes.iterator();
        while (it.hasNext()) {
            ToygerLog.i("focusModes--" + it.next());
        }
    }

    public static Map<String, String> getCameraResolution() {
        HashMap map = new HashMap();
        new ArrayList();
        try {
            int numberOfCameras = Camera.getNumberOfCameras();
            for (int i = 0; i < numberOfCameras; i++) {
                Camera cameraOpen = Camera.open(i);
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
                if (cameraInfo.facing == 1) {
                    Camera.Size size = cameraOpen.getParameters().getSupportedPictureSizes().get(0);
                    map.put("frontCamera", size.width + "*" + size.height);
                } else if (cameraInfo.facing == 0) {
                    Camera.Size size2 = cameraOpen.getParameters().getSupportedPictureSizes().get(0);
                    map.put("backCamera", size2.width + "*" + size2.height);
                }
                if (cameraOpen != null) {
                    cameraOpen.release();
                }
            }
        } catch (Exception unused) {
        }
        return map;
    }

    public static int findFrontFacingCamera() {
        Camera.CameraInfo cameraInfo;
        try {
        } catch (Throwable th) {
            ToygerLog.e(BlobManager.BLOB_ELEM_TYPE_FACE, th.toString());
        }
        int numberOfCameras = Integer.parseInt(Build.VERSION.SDK) > 8 ? Camera.getNumberOfCameras() : 0;
        for (int i = 0; i < numberOfCameras; i++) {
            try {
                cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
            } catch (Throwable unused) {
            }
            if (cameraInfo.facing == 1) {
                return i;
            }
        }
        return -1;
    }

    public static int findBackFacingCamera() {
        Camera.CameraInfo cameraInfo;
        try {
        } catch (Throwable th) {
            ToygerLog.e(BlobManager.BLOB_ELEM_TYPE_FACE, th.toString());
        }
        int numberOfCameras = Integer.parseInt(Build.VERSION.SDK) > 8 ? Camera.getNumberOfCameras() : 0;
        for (int i = 0; i < numberOfCameras; i++) {
            try {
                cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
            } catch (Throwable unused) {
            }
            if (cameraInfo.facing == 0) {
                return i;
            }
        }
        return -1;
    }
}
