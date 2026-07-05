package com.sqwan.common.dev;

import android.content.Context;
import com.plugin.standard.IDevValue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class AbstractDeviceInfoLogic implements IDevValue {
    private boolean isAuthCheck = false;
    protected Context mContext;

    public abstract DeviceInfoBean getFromCache();

    public abstract DeviceInfoBean getFromSystemApi();

    public abstract DeviceInfoBean random();

    public abstract void saveToCache(String str);

    public DeviceInfoBean getDeviceInfo() {
        DeviceInfoBean fromCache = getFromCache();
        if (fromCache == null) {
            fromCache = getFromSystemApi();
        }
        return fromCache == null ? random() : fromCache;
    }

    public String getValue() {
        return getDeviceInfo().getValue();
    }

    public boolean isAuthCheck() {
        return this.isAuthCheck;
    }

    public void setAuthCheck(boolean z) {
        this.isAuthCheck = z;
    }
}
