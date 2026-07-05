package com.sqwan.common.dev;

import android.content.Context;
import android.text.TextUtils;
import com.sqwan.common.cache.DeviceCacheHelper;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.TelephonyInfoUtils;
import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ImeiLogic extends AbstractDeviceInfoLogic {
    private static final String IMEI_KEY = "dev_imei_2";
    private static final String IMEI_PREFIX = "999";
    private static final int IMEI_RANDOM_LENGTH = 14;
    private static ImeiLogic instance;

    private ImeiLogic(Context context) {
        this.mContext = context;
    }

    public static ImeiLogic getInstance(Context context) {
        if (instance == null) {
            synchronized (ImeiLogic.class) {
                if (instance == null) {
                    instance = new ImeiLogic(context);
                }
            }
        }
        return instance;
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public void saveToCache(String str) throws Throwable {
        DeviceCacheHelper.save(this.mContext, IMEI_KEY, str);
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public DeviceInfoBean getFromCache() {
        String str = DeviceCacheHelper.get(this.mContext, IMEI_KEY);
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
        LogUtil.i("imei, get from sys api");
        String deviceId = TelephonyInfoUtils.getDeviceId(this.mContext);
        if (TextUtils.isEmpty(deviceId)) {
            return null;
        }
        saveToCache(deviceId);
        return new DeviceInfoBean(2, deviceId);
    }

    @Override // com.sqwan.common.dev.AbstractDeviceInfoLogic
    public DeviceInfoBean random() throws Throwable {
        LogUtil.i("imei, get from random");
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 14; i++) {
            sb.append(random.nextInt(9));
        }
        String str = IMEI_PREFIX + sb.toString();
        if (isAuthCheck()) {
            saveToCache(str);
        }
        return new DeviceInfoBean(3, str);
    }
}
