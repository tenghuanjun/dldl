package com.sqwan.common.dev;

import android.content.Context;
import android.text.TextUtils;
import com.sq.tools.manager.SensitiveInfoManager;
import com.sqwan.common.cache.DeviceCacheHelper;
import com.sqwan.common.util.LogUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MacLogic extends AbstractDeviceInfoLogic {
    private static final String MAC_DEFAULT = "020000000000";
    private static final String MAC_KEY = "dev_mac_2";
    private static MacLogic instance;

    private MacLogic(Context context) {
        this.mContext = context;
    }

    public static MacLogic getInstance(Context context) {
        if (instance == null) {
            synchronized (MacLogic.class) {
                if (instance == null) {
                    instance = new MacLogic(context);
                }
            }
        }
        return instance;
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public void saveToCache(String str) throws Throwable {
        DeviceCacheHelper.save(this.mContext, MAC_KEY, str);
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public DeviceInfoBean getFromCache() {
        String str = DeviceCacheHelper.get(this.mContext, MAC_KEY);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new DeviceInfoBean(1, str);
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public DeviceInfoBean getFromSystemApi() throws Throwable {
        if (!isAuthCheck()) {
            return null;
        }
        LogUtil.i("mac, get from sys api");
        String macAddress = SensitiveInfoManager.getInstance().getMacAddress(this.mContext);
        if (TextUtils.isEmpty(macAddress)) {
            return null;
        }
        saveToCache(macAddress);
        return new DeviceInfoBean(2, macAddress);
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public DeviceInfoBean random() throws Throwable {
        LogUtil.i("mac, get from default value");
        if (isAuthCheck()) {
            saveToCache(MAC_DEFAULT);
        }
        return new DeviceInfoBean(3, MAC_DEFAULT);
    }
}
