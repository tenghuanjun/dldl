package com.sqwan.common.dev;

import android.content.Context;
import android.text.TextUtils;
import com.sqwan.common.cache.DeviceCacheHelper;
import com.sqwan.common.util.MD5Util;
import com.sqwan.common.util.TelephonyInfoUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DevLogic extends AbstractDeviceInfoLogic {
    private static final String DEV_KEY = "dev2";
    private static DevLogic instance;

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public DeviceInfoBean getFromSystemApi() {
        return null;
    }

    private DevLogic(Context context) {
        this.mContext = context;
    }

    public static DevLogic getInstance(Context context) {
        if (instance == null) {
            synchronized (DevLogic.class) {
                if (instance == null) {
                    instance = new DevLogic(context);
                }
            }
        }
        return instance;
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public void saveToCache(String str) throws Throwable {
        DeviceCacheHelper.save(this.mContext, DEV_KEY, str, false);
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public DeviceInfoBean getFromCache() {
        String str = DeviceCacheHelper.get(this.mContext, DEV_KEY, false);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new DeviceInfoBean(1, str);
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public DeviceInfoBean random() throws Throwable {
        String strMd5 = MD5Util.Md5(ImeiLogic.getInstance(this.mContext).getValue() + MacLogic.getInstance(this.mContext).getValue() + TelephonyInfoUtils.getSettingAndroidId(this.mContext, isAuthCheck()) + (System.currentTimeMillis() + ""));
        if (isAuthCheck()) {
            saveToCache(strMd5);
        }
        return new DeviceInfoBean(3, strMd5);
    }
}
