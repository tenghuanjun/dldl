package com.aliyun.aliyunface.camera.utils;

import android.os.Build;
import com.aliyun.aliyunface.config.DeviceSetting;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class DeviceSettingUtil {
    public static DeviceSetting getPropertyDeviceSetting(DeviceSetting[] deviceSettingArr) {
        DeviceSetting deviceSetting;
        if (deviceSettingArr != null) {
            int i = Integer.parseInt(Build.VERSION.SDK);
            int length = deviceSettingArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                deviceSetting = deviceSettingArr[i2];
                if (i >= deviceSetting.getMinApiLevel() && i <= deviceSetting.getMaxApiLevel()) {
                    break;
                }
            }
            deviceSetting = null;
        } else {
            deviceSetting = null;
        }
        return deviceSetting == null ? new DeviceSetting() : deviceSetting;
    }
}
