package com.sqwan.common.dev;

import android.content.Context;
import android.text.TextUtils;
import com.sqwan.common.cache.DeviceCacheHelper;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.LogUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RootLogic extends AbstractDeviceInfoLogic {
    private static final String ROOT_DEFAULT = "";
    private static final String ROOT_KEY = "dev_root";
    private static RootLogic instance;

    private RootLogic(Context context) {
        this.mContext = context;
    }

    public static RootLogic getInstance(Context context) {
        if (instance == null) {
            synchronized (RootLogic.class) {
                if (instance == null) {
                    instance = new RootLogic(context);
                }
            }
        }
        return instance;
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public void saveToCache(String str) throws Throwable {
        DeviceCacheHelper.save(this.mContext, ROOT_KEY, str);
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public DeviceInfoBean getFromCache() {
        String str = DeviceCacheHelper.get(this.mContext, ROOT_KEY);
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
        LogUtil.i("root, get from sys api");
        String str = DeviceUtils.isRoot() ? "1" : "0";
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        saveToCache(str);
        return new DeviceInfoBean(2, str);
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public DeviceInfoBean random() throws Throwable {
        LogUtil.i("root, get from default value");
        if (isAuthCheck()) {
            saveToCache("");
        }
        return new DeviceInfoBean(3, "");
    }
}
