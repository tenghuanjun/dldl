package com.aliyun.aliyunface.utils;

import android.os.Build;
import com.aliyun.aliyunface.camera.utils.AndroidCameraUtil;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class EnvCheck {
    private static final int ANDROID_VERSION_4_3 = 18;

    public enum EnvErrorType {
        ENV_SUCCESS,
        ENV_ERROR_LOW_OS,
        ENV_ERROR_UNSUPPORTED_CPU,
        ENV_ERROR_NO_PERMISSION_OF_CAMERA,
        ENV_ERROR_NO_FRONT_CAMERA
    }

    private static boolean isLowOS() {
        return Build.VERSION.SDK != null && Integer.parseInt(Build.VERSION.SDK) < 18;
    }

    public static EnvErrorType check() {
        if (isLowOS()) {
            return EnvErrorType.ENV_ERROR_LOW_OS;
        }
        if (AndroidCameraUtil.findFrontFacingCamera() == -1) {
            return EnvErrorType.ENV_ERROR_NO_FRONT_CAMERA;
        }
        return EnvErrorType.ENV_SUCCESS;
    }
}
