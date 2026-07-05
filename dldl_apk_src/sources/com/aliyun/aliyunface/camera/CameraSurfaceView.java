package com.aliyun.aliyunface.camera;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.aliyun.aliyunface.camera.utils.DeviceSettingUtil;
import com.aliyun.aliyunface.camera.utils.DisplayUtil;
import com.aliyun.aliyunface.config.DeviceSetting;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    static ICameraInterface mCameraInterface;
    ICameraCallback mCameraCallback;
    Context mContext;
    private DeviceSetting mDeviceSetting;
    float mPreviewRate;
    SurfaceHolder mSurfaceHolder;

    public static String getCameraName() {
        return "Android";
    }

    public CameraSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mPreviewRate = DisplayUtil.getScreenRate(applicationContext);
        SurfaceHolder holder = getHolder();
        this.mSurfaceHolder = holder;
        holder.setFormat(-2);
        this.mSurfaceHolder.setType(3);
        this.mSurfaceHolder.addCallback(this);
    }

    public void init(Context context, boolean z, boolean z2, DeviceSetting[] deviceSettingArr) {
        this.mDeviceSetting = DeviceSettingUtil.getPropertyDeviceSetting(deviceSettingArr);
        ICameraInterface cameraImpl = getCameraImpl();
        mCameraInterface = cameraImpl;
        if (cameraImpl != null) {
            cameraImpl.initCamera(context, z, z2, this.mDeviceSetting);
        }
    }

    public void enableTakePhotoFlash(boolean z) {
        if (z) {
            mCameraInterface.turnOnTakePhotoFlash();
        } else {
            mCameraInterface.turnOffTakePhotoFlash();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        ICameraInterface iCameraInterface = mCameraInterface;
        if (iCameraInterface != null) {
            iCameraInterface.setCallback(this.mCameraCallback);
        }
        ICameraInterface iCameraInterface2 = mCameraInterface;
        if (iCameraInterface2 != null) {
            iCameraInterface2.startCamera();
        }
        ICameraCallback iCameraCallback = this.mCameraCallback;
        if (iCameraCallback != null) {
            iCameraCallback.onSurfaceCreated();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        ICameraInterface iCameraInterface = mCameraInterface;
        if (iCameraInterface != null) {
            iCameraInterface.startPreview(this.mSurfaceHolder, this.mPreviewRate, i2, i3);
            if (this.mCameraCallback != null) {
                int cameraViewRotation = mCameraInterface.getCameraViewRotation();
                if (cameraViewRotation == 90 || cameraViewRotation == 270) {
                    i2 = mCameraInterface.getPreviewHeight();
                    i3 = mCameraInterface.getPreviewWidth();
                } else if (cameraViewRotation == 0 || cameraViewRotation == 180) {
                    i2 = mCameraInterface.getPreviewWidth();
                    i3 = mCameraInterface.getPreviewHeight();
                }
                this.mCameraCallback.onSurfaceChanged(i2, i3);
            }
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        ICameraInterface iCameraInterface = mCameraInterface;
        if (iCameraInterface != null) {
            iCameraInterface.stopCamera();
            mCameraInterface.setCallback(null);
        }
        ICameraCallback iCameraCallback = this.mCameraCallback;
        if (iCameraCallback != null) {
            iCameraCallback.onSurfaceDestroyed();
        }
    }

    public static synchronized ICameraInterface getCameraImpl() {
        if (mCameraInterface == null) {
            mCameraInterface = AndroidImpl.getInstance();
        }
        return mCameraInterface;
    }

    public SurfaceHolder getSurfaceHolder() {
        return this.mSurfaceHolder;
    }

    public void setCameraCallback(ICameraCallback iCameraCallback) {
        this.mCameraCallback = iCameraCallback;
    }

    public ICameraInterface getCameraInterface() {
        return mCameraInterface;
    }
}
