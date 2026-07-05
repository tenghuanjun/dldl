package com.sy37sdk.account.scanCode;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.account.scanCode.ICameraOperation;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class Camera2 implements ICameraOperation {
    private CameraDevice mCameraDevice;
    private final CameraManager mCameraManager;
    private CameraCaptureSession mCaptureSession;
    private ICameraOperation.OpenFailCallback mFailCallback;
    private ICameraOperation.FrameCallback mFrameCallback;
    private ImageReader mImageReader;
    private CaptureRequest.Builder mPreviewRequestBuilder;
    private Size mPreviewSize;
    private final ImageReader.OnImageAvailableListener imageAvailableListener = new ImageReader.OnImageAvailableListener() { // from class: com.sy37sdk.account.scanCode.-$$Lambda$Camera2$DCnG3kkbfIWHVj9u6oCsidIO3lw
        @Override // android.media.ImageReader.OnImageAvailableListener
        public final void onImageAvailable(ImageReader imageReader) {
            this.f$0.lambda$new$0$Camera2(imageReader);
        }
    };
    private final CameraCaptureSession.CaptureCallback sessionCaptureCallback = new CameraCaptureSession.CaptureCallback() { // from class: com.sy37sdk.account.scanCode.Camera2.2
        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            Camera2.this.mCaptureSession = cameraCaptureSession;
            Integer num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
            Integer num2 = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_STATE);
            if (num == null || num.intValue() == 2 || (num.intValue() == 0 && num2 != null && num2.intValue() == 2)) {
                Camera2.this.capturePicture();
            }
        }
    };
    private final CameraCaptureSession.StateCallback sessionStateCallback = new CameraCaptureSession.StateCallback() { // from class: com.sy37sdk.account.scanCode.Camera2.3
        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            Camera2.this.mCaptureSession = cameraCaptureSession;
            Camera2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 4);
            Camera2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 2);
            try {
                cameraCaptureSession.setRepeatingRequest(Camera2.this.mPreviewRequestBuilder.build(), Camera2.this.sessionCaptureCallback, null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    };

    public Camera2(Context context) {
        this.mCameraManager = (CameraManager) context.getSystemService("camera");
    }

    public /* synthetic */ void lambda$new$0$Camera2(ImageReader imageReader) {
        Image imageAcquireLatestImage = imageReader.acquireLatestImage();
        if (imageAcquireLatestImage == null) {
            return;
        }
        ByteBuffer buffer = imageAcquireLatestImage.getPlanes()[0].getBuffer();
        int width = imageAcquireLatestImage.getWidth();
        int height = imageAcquireLatestImage.getHeight();
        byte[] bArr = new byte[buffer.remaining()];
        buffer.get(bArr);
        imageAcquireLatestImage.close();
        onFrameAvailable(bArr, width, height);
    }

    private void openCamera(final Surface surface) throws CameraAccessException {
        this.mCameraManager.openCamera(getBackCameraId(), new CameraDevice.StateCallback() { // from class: com.sy37sdk.account.scanCode.Camera2.1
            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onOpened(CameraDevice cameraDevice) {
                LogUtil.i(Camera2.this.buildPrefixLog("相机打开"));
                Camera2.this.mCameraDevice = cameraDevice;
                try {
                    Camera2.this.startPreview(surface);
                } catch (CameraAccessException e) {
                    Camera2.this.mFailCallback.onFail("相机预览失败");
                    LogUtil.e(Camera2.this.buildPrefixLog("打开相机报错  " + e.getMessage()));
                    e.printStackTrace();
                }
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onDisconnected(CameraDevice cameraDevice) {
                LogUtil.e(Camera2.this.buildPrefixLog("相机断开链接"));
                cameraDevice.close();
                Camera2.this.mCameraDevice = null;
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onError(CameraDevice cameraDevice, int i) {
                LogUtil.e(Camera2.this.buildPrefixLog("相机链接报错 errCode：" + i));
            }
        }, (Handler) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void setUpCameraOutputs(android.view.TextureView r9, int r10, int r11) throws android.hardware.camera2.CameraAccessException {
        /*
            r8 = this;
            android.hardware.camera2.CameraManager r0 = r8.mCameraManager
            java.lang.String r1 = r8.getBackCameraId()
            android.hardware.camera2.CameraCharacteristics r0 = r0.getCameraCharacteristics(r1)
            android.hardware.camera2.CameraCharacteristics$Key r1 = android.hardware.camera2.CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP
            java.lang.Object r1 = r0.get(r1)
            android.hardware.camera2.params.StreamConfigurationMap r1 = (android.hardware.camera2.params.StreamConfigurationMap) r1
            java.lang.Class<android.graphics.SurfaceTexture> r2 = android.graphics.SurfaceTexture.class
            android.util.Size[] r1 = r1.getOutputSizes(r2)
            android.content.Context r9 = r9.getContext()
            android.app.Activity r9 = (android.app.Activity) r9
            android.view.WindowManager r9 = r9.getWindowManager()
            android.view.Display r9 = r9.getDefaultDisplay()
            int r9 = r9.getRotation()
            android.hardware.camera2.CameraCharacteristics$Key r2 = android.hardware.camera2.CameraCharacteristics.SENSOR_ORIENTATION
            java.lang.Object r0 = r0.get(r2)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r2 = 2
            r3 = 0
            r4 = 1
            if (r9 == 0) goto L4a
            if (r9 == r4) goto L43
            if (r9 == r2) goto L4a
            r5 = 3
            if (r9 == r5) goto L43
            goto L53
        L43:
            if (r0 == 0) goto L54
            r9 = 180(0xb4, float:2.52E-43)
            if (r0 != r9) goto L53
            goto L54
        L4a:
            r9 = 90
            if (r0 == r9) goto L54
            r9 = 270(0x10e, float:3.78E-43)
            if (r0 != r9) goto L53
            goto L54
        L53:
            r4 = 0
        L54:
            if (r4 == 0) goto L59
            r7 = r11
            r11 = r10
            r10 = r7
        L59:
            android.util.Size r9 = new android.util.Size
            r9.<init>(r10, r11)
            r8.mPreviewSize = r9
            float r9 = (float) r10
            float r0 = (float) r11
            float r9 = r9 / r0
            int r0 = r1.length
        L64:
            if (r3 >= r0) goto L89
            r4 = r1[r3]
            int r5 = r4.getWidth()
            float r5 = (float) r5
            int r6 = r4.getHeight()
            float r6 = (float) r6
            float r5 = r5 / r6
            int r5 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r5 != 0) goto L86
            int r5 = r4.getWidth()
            if (r5 > r10) goto L86
            int r5 = r4.getHeight()
            if (r5 > r11) goto L86
            r8.mPreviewSize = r4
            goto L89
        L86:
            int r3 = r3 + 1
            goto L64
        L89:
            android.util.Size r9 = r8.mPreviewSize
            int r9 = r9.getWidth()
            android.util.Size r10 = r8.mPreviewSize
            int r10 = r10.getHeight()
            r11 = 35
            android.media.ImageReader r9 = android.media.ImageReader.newInstance(r9, r10, r11, r2)
            r8.mImageReader = r9
            android.media.ImageReader$OnImageAvailableListener r10 = r8.imageAvailableListener
            r11 = 0
            r9.setOnImageAvailableListener(r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy37sdk.account.scanCode.Camera2.setUpCameraOutputs(android.view.TextureView, int, int):void");
    }

    private void onFrameAvailable(byte[] bArr, int i, int i2) {
        ICameraOperation.FrameCallback frameCallback = this.mFrameCallback;
        if (frameCallback != null ? frameCallback.onFrameAvailable(bArr, i, i2, this.mPreviewSize) : true) {
            try {
                this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.sessionCaptureCallback, null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void capturePicture() {
        if (this.mCaptureSession == null) {
            return;
        }
        if (this.mImageReader == null) {
            LogUtil.e(buildPrefixLog("capturePicture ImageReader为空，return"));
            return;
        }
        try {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
            this.mPreviewRequestBuilder.addTarget(this.mImageReader.getSurface());
            this.mCaptureSession.stopRepeating();
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), new CameraCaptureSession.CaptureCallback() { // from class: com.sy37sdk.account.scanCode.Camera2.4
                @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
                public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
                    Camera2.this.unlockFocus();
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unlockFocus() {
        if (this.mCaptureSession == null) {
            return;
        }
        if (this.mImageReader == null) {
            LogUtil.e(buildPrefixLog("capturePicture ImageReader为空，return"));
        } else {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
            this.mPreviewRequestBuilder.removeTarget(this.mImageReader.getSurface());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPreview(Surface surface) throws CameraAccessException {
        CaptureRequest.Builder builderCreateCaptureRequest = this.mCameraDevice.createCaptureRequest(1);
        this.mPreviewRequestBuilder = builderCreateCaptureRequest;
        builderCreateCaptureRequest.addTarget(surface);
        this.mCameraDevice.createCaptureSession(Arrays.asList(surface, this.mImageReader.getSurface()), this.sessionStateCallback, null);
    }

    private String getBackCameraId() throws CameraAccessException {
        for (String str : this.mCameraManager.getCameraIdList()) {
            Integer num = (Integer) this.mCameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.LENS_FACING);
            if (num != null && num.intValue() == 1) {
                return str;
            }
        }
        return "";
    }

    @Override // com.sy37sdk.account.scanCode.ICameraOperation
    public void openCamera(TextureView textureView, ICameraOperation.FrameCallback frameCallback, ICameraOperation.OpenFailCallback openFailCallback) {
        this.mFrameCallback = frameCallback;
        this.mFailCallback = openFailCallback;
        try {
            SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
            if (surfaceTexture == null) {
                openFailCallback.onFail("相机打开失败");
                LogUtil.e(buildPrefixLog("相机打开失败，getSurfaceTexture为空"));
            } else {
                Surface surface = new Surface(surfaceTexture);
                setUpCameraOutputs(textureView, textureView.getWidth(), textureView.getHeight());
                surfaceTexture.setDefaultBufferSize(this.mPreviewSize.getWidth(), this.mPreviewSize.getHeight());
                openCamera(surface);
            }
        } catch (CameraAccessException e) {
            openFailCallback.onFail("相机打开失败");
            LogUtil.e(buildPrefixLog("相机打开失败" + e.getMessage()));
            e.printStackTrace();
        }
    }

    @Override // com.sy37sdk.account.scanCode.ICameraOperation
    public void closeCamera() {
        LogUtil.d(buildPrefixLog("关闭相机"));
        CameraDevice cameraDevice = this.mCameraDevice;
        if (cameraDevice != null) {
            cameraDevice.close();
            this.mCameraDevice = null;
        }
        ImageReader imageReader = this.mImageReader;
        if (imageReader != null) {
            imageReader.close();
            this.mImageReader = null;
        }
        CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            this.mCaptureSession = null;
        }
        this.mFailCallback = null;
        this.mFrameCallback = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String buildPrefixLog(String str) {
        return "【Camera】" + str;
    }
}
