package com.aliyun.aliyunface.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.alibaba.fastjson.asm.Opcodes;
import com.alipay.zoloz.toyger.ToygerLog;
import com.aliyun.aliyunface.camera.utils.AndroidCameraUtil;
import com.aliyun.aliyunface.camera.utils.DisplayUtil;
import com.aliyun.aliyunface.config.DeviceSetting;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class AndroidImpl implements ICameraInterface {
    private static AndroidImpl mCameraInterface;
    private boolean isCameraOpen;
    private boolean isCameraStart;
    private boolean isPreviewing;
    private Camera mCamera;
    private ICameraCallback mCameraCallback;
    private int mCameraID;
    private int mCameraNumber;
    private Context mContext;
    private Camera.Parameters mParams;
    private int mCameraViewRotationAngle = 90;
    private boolean mFront = true;
    private DeviceSetting mDeviceSetting = new DeviceSetting();
    private final Object mLock = new Object();
    private int mWidth = 0;
    private int mHeight = 0;
    private int mPreviewWidth = 0;
    private int mPreviewHeight = 0;
    private boolean isFaceCamera = false;

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public boolean beautifyAvatar(Bitmap bitmap) {
        return true;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public PointF colorToDepth(PointF pointF) {
        return null;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public PointF depthToColor(PointF pointF) {
        return null;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public CameraParams getCameraParams() {
        return null;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public String getCameraSN() {
        return null;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public int getColorMode() {
        return 0;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public int getDepthHeight() {
        return 0;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public int getDepthWidth() {
        return 0;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public String getFirmwareVersion() {
        return null;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public Rect getROI() {
        return null;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public Object getUVCCamera() {
        return null;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public boolean isMirror() {
        return false;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public boolean setDrawCapturing(boolean z) {
        return false;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void setFrameAvailableListener(SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener) {
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void setGLSurfaceViewListener(IGLSurfaceViewListener iGLSurfaceViewListener) {
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void setRenderLayers(Map<String, Object> map) {
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void startFpsCheck() {
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void stopFpsCheck() {
    }

    private AndroidImpl() {
        this.isCameraOpen = false;
        this.isCameraStart = false;
        this.isPreviewing = false;
        this.isCameraOpen = false;
        this.isCameraStart = false;
        this.isPreviewing = false;
    }

    public static synchronized AndroidImpl getInstance() {
        if (mCameraInterface == null) {
            mCameraInterface = new AndroidImpl();
        }
        return mCameraInterface;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void initCamera(Context context, boolean z, boolean z2, DeviceSetting deviceSetting) {
        this.mFront = z;
        this.isFaceCamera = z2;
        if (deviceSetting != null) {
            this.mDeviceSetting = deviceSetting;
        }
        if (!z) {
            this.mCameraViewRotationAngle = 270;
        }
        initContext(context);
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void releaseCamera() {
        this.mContext = null;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void openCamera(DeviceSetting deviceSetting) {
        if (this.isCameraOpen) {
            return;
        }
        if (deviceSetting != null) {
            this.mDeviceSetting = deviceSetting;
        }
        this.isCameraOpen = true;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void closeCamera() {
        if (this.isCameraOpen) {
            this.isCameraOpen = false;
        }
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void startCamera() {
        synchronized (this.mLock) {
            if (this.isCameraStart) {
                return;
            }
            int iFindBackFacingCamera = AndroidCameraUtil.findBackFacingCamera();
            if (this.mFront) {
                iFindBackFacingCamera = AndroidCameraUtil.findFrontFacingCamera();
            }
            if (realStartCamera(iFindBackFacingCamera)) {
                this.isCameraStart = true;
            }
        }
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void stopCamera() {
        stopPreview();
        synchronized (this.mLock) {
            if (this.isCameraStart) {
                this.mCameraCallback = null;
                if (this.mCamera != null) {
                    try {
                        this.mCamera.release();
                        this.mCamera = null;
                        this.isCameraStart = false;
                        ToygerLog.e("关闭摄像头....");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void startPreview(SurfaceHolder surfaceHolder, float f, int i, int i2) {
        synchronized (this.mLock) {
            if (this.isPreviewing) {
                return;
            }
            if (this.mCamera != null) {
                if (surfaceHolder != null) {
                    try {
                        this.mCamera.setPreviewDisplay(surfaceHolder);
                    } catch (Exception unused) {
                        if (this.mCameraCallback != null) {
                            this.mCameraCallback.onError(101);
                        }
                        return;
                    }
                }
                this.mCamera.setPreviewCallback(new Camera.PreviewCallback() { // from class: com.aliyun.aliyunface.camera.AndroidImpl.1
                    @Override // android.hardware.Camera.PreviewCallback
                    public void onPreviewFrame(byte[] bArr, Camera camera) {
                        if (bArr == null || AndroidImpl.this.mCameraCallback == null) {
                            return;
                        }
                        AndroidImpl.this.mCameraCallback.onPreviewFrame(new CameraData(ByteBuffer.wrap(bArr), AndroidImpl.this.mWidth, AndroidImpl.this.mHeight, 0, null, 0, 0, AndroidImpl.this.mPreviewWidth, AndroidImpl.this.mPreviewHeight));
                    }
                });
                this.mCamera.startPreview();
                ToygerLog.e("开始预览....");
                this.isPreviewing = true;
            }
        }
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void stopPreview() {
        synchronized (this.mLock) {
            ToygerLog.e("关闭预览....");
            if (this.isPreviewing) {
                if (this.mCamera != null) {
                    synchronized (this.mLock) {
                        try {
                            this.mCamera.setOneShotPreviewCallback(null);
                            this.mCamera.setPreviewCallback(null);
                            this.mCamera.stopPreview();
                        } catch (Exception unused) {
                        }
                    }
                    this.isPreviewing = false;
                }
            }
        }
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void setCallback(ICameraCallback iCameraCallback) {
        this.mCameraCallback = iCameraCallback;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public int getCameraViewRotation() {
        return this.mCameraViewRotationAngle;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public int getColorWidth() {
        return this.mWidth;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public int getColorHeight() {
        return this.mHeight;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public int getPreviewWidth() {
        return this.mPreviewWidth;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public int getPreviewHeight() {
        return this.mPreviewHeight;
    }

    private void initContext(Context context) {
        this.mContext = context;
    }

    private boolean realStartCamera(int i) {
        try {
            Camera cameraOpen = Camera.open(i);
            this.mCamera = cameraOpen;
            if (cameraOpen == null) {
                if (this.mCameraCallback != null) {
                    this.mCameraCallback.onError(101);
                }
                return false;
            }
            this.mCameraID = i;
            this.mParams = cameraOpen.getParameters();
            adjustCameraParams();
            this.mCamera.setParameters(this.mParams);
            ToygerLog.e("打开摄像头....");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ICameraCallback iCameraCallback = this.mCameraCallback;
            if (iCameraCallback != null) {
                iCameraCallback.onError(101);
            }
            return false;
        } catch (Throwable unused) {
            ICameraCallback iCameraCallback2 = this.mCameraCallback;
            if (iCameraCallback2 != null) {
                iCameraCallback2.onError(101);
            }
            return false;
        }
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public int getCameraRotation() {
        return getCameraAutoAngle(this.mDeviceSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCameraAutoAngle(DeviceSetting deviceSetting) {
        if (deviceSetting == null) {
            throw new IllegalArgumentException("deviceSetting can't be null");
        }
        if (deviceSetting.isDisplayAuto()) {
            return getCameraAutoAngle(this.mCameraID);
        }
        return deviceSetting.getDisplayAngle();
    }

    private void adjustCameraParams() {
        Camera.Size propPreviewSize;
        int iMin;
        Camera.Size propPreviewSize2;
        if (this.mParams != null) {
            DeviceSetting deviceSetting = this.mDeviceSetting;
            if (deviceSetting != null && !deviceSetting.isWidthAuto()) {
                propPreviewSize = AndroidCameraUtil.getInstance().getPropPreviewSize(this.mParams.getSupportedPreviewSizes(), this.mDeviceSetting.getWidth(), 0);
            } else if (this.isFaceCamera) {
                propPreviewSize = AndroidCameraUtil.getInstance().getPropPreviewSize(this.mParams.getSupportedPreviewSizes(), CameraConstants.CAMERA_MAX_WIDTH, 0);
            } else {
                propPreviewSize = AndroidCameraUtil.getInstance().getPropPreviewSize(this.mParams.getSupportedPreviewSizes(), DisplayUtil.getScreenRate(this.mContext), CameraConstants.CAMERA_MAX_WIDTH);
            }
            if (propPreviewSize != null) {
                this.mPreviewWidth = propPreviewSize.width;
                int i = propPreviewSize.height;
                this.mPreviewHeight = i;
                int i2 = this.mPreviewWidth;
                this.mWidth = i2;
                this.mHeight = i;
                this.mParams.setPreviewSize(i2, i);
                if (!this.isFaceCamera && (propPreviewSize2 = AndroidCameraUtil.getInstance().getPropPreviewSize(this.mParams.getSupportedPictureSizes(), DisplayUtil.getScreenRate(this.mContext), CameraConstants.CAMERA_MAX_WIDTH)) != null) {
                    this.mParams.setPictureSize(propPreviewSize2.width, propPreviewSize2.height);
                }
            }
            DeviceSetting deviceSetting2 = this.mDeviceSetting;
            if (deviceSetting2 != null) {
                int cameraAutoAngle = getCameraAutoAngle(deviceSetting2);
                this.mCameraViewRotationAngle = cameraAutoAngle;
                this.mCamera.setDisplayOrientation(cameraAutoAngle);
            }
            if (this.mDeviceSetting != null && this.mParams.isZoomSupported() && (iMin = Math.min(Math.max(this.mDeviceSetting.getZoom(), 0), this.mParams.getMaxZoom())) != this.mParams.getZoom()) {
                this.mParams.setZoom(iMin);
            }
            List<String> supportedFocusModes = this.mParams.getSupportedFocusModes();
            if (supportedFocusModes != null) {
                if (supportedFocusModes.contains("continuous-video")) {
                    this.mParams.setFocusMode("continuous-video");
                } else if (supportedFocusModes.contains("auto")) {
                    this.mParams.setFocusMode("auto");
                }
            }
        }
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public int getCameraPictureAngle() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(this.mCameraID, cameraInfo);
        return cameraInfo.orientation;
    }

    private int getCameraAutoAngle(int i) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        int rotation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        int i2 = 0;
        if (rotation != 0) {
            if (rotation == 1) {
                i2 = 90;
            } else if (rotation == 2) {
                i2 = Opcodes.GETFIELD;
            } else if (rotation == 3) {
                i2 = 270;
            }
        }
        if (cameraInfo.facing == 1) {
            return (360 - ((cameraInfo.orientation + i2) % 360)) % 360;
        }
        return ((cameraInfo.orientation - i2) + 360) % 360;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void lockCameraWhiteBalanceAndExposure() {
        synchronized (this.mLock) {
            if (this.mCamera != null) {
                try {
                    Camera.Parameters parameters = this.mCamera.getParameters();
                    parameters.setAutoExposureLock(true);
                    parameters.setAutoWhiteBalanceLock(true);
                    ToygerLog.e("锁定白平衡...");
                    this.mCamera.setParameters(parameters);
                } catch (Throwable unused) {
                }
            }
        }
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void unlockCameraWhiteBalanceAndExposure() {
        synchronized (this.mLock) {
            if (this.mCamera != null) {
                try {
                    Camera.Parameters parameters = this.mCamera.getParameters();
                    parameters.setAutoExposureLock(false);
                    parameters.setAutoWhiteBalanceLock(false);
                    ToygerLog.e("解锁白平衡...");
                    this.mCamera.setParameters(parameters);
                } catch (Throwable unused) {
                }
            }
        }
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public Camera getCamera() {
        return this.mCamera;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void takePhoto(final ICameraTakePicture iCameraTakePicture) {
        this.mCamera.takePicture(null, null, new Camera.PictureCallback() { // from class: com.aliyun.aliyunface.camera.AndroidImpl.2
            @Override // android.hardware.Camera.PictureCallback
            public void onPictureTaken(byte[] bArr, Camera camera) {
                try {
                    if (bArr == null) {
                        throw new Exception("taken photo exception, image data null");
                    }
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                    options.inSampleSize = AndroidImpl.calculateInSampleSize(options, options.outWidth / 2, options.outHeight / 2);
                    options.inJustDecodeBounds = false;
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    options.inPurgeable = true;
                    options.inInputShareable = true;
                    int i = AndroidImpl.this.mCameraViewRotationAngle = AndroidImpl.this.getCameraAutoAngle(AndroidImpl.this.mDeviceSetting);
                    Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                    if (bitmapDecodeByteArray != null) {
                        Matrix matrix = new Matrix();
                        matrix.setRotate(i);
                        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeByteArray, 0, 0, bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight(), matrix, false);
                        if (!bitmapDecodeByteArray.isRecycled()) {
                            bitmapDecodeByteArray.recycle();
                        }
                        if (iCameraTakePicture != null) {
                            iCameraTakePicture.onTakenPicture(bitmapCreateBitmap);
                        }
                    }
                    AndroidImpl.this.mCamera.startPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                    ICameraTakePicture iCameraTakePicture2 = iCameraTakePicture;
                    if (iCameraTakePicture2 != null) {
                        iCameraTakePicture2.onTakenPicture(null);
                    }
                }
            }
        });
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void turnOnTakePhotoFlash() {
        Camera.Parameters parameters = this.mCamera.getParameters();
        if (parameters == null) {
            return;
        }
        parameters.setFlashMode("torch");
        this.mCamera.setParameters(parameters);
    }

    @Override // com.aliyun.aliyunface.camera.ICameraInterface
    public void turnOffTakePhotoFlash() {
        Camera.Parameters parameters = this.mCamera.getParameters();
        if (parameters == null) {
            return;
        }
        parameters.setFlashMode("off");
        this.mCamera.setParameters(parameters);
    }
}
