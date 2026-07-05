package com.aliyun.aliyunface.api;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface AppDataProvider {
    String getApdidToken(Context context);

    String getAppName(Context context);

    String getAppVersion(Context context);

    String getDeviceModel();

    String getDeviceType();

    String getOsVersion();
}
