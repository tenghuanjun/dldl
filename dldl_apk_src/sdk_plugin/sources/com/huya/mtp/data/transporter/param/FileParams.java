package com.huya.mtp.data.transporter.param;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface FileParams extends Params {
    public static final long DEFAULT_CACHE_EXPIRE_TIME = 86400000;
    public static final long DEFAULT_CACHE_REFRESH_TIME = 60000;

    String getCacheDir();

    long getCacheExpireTimeMillis();

    String getCacheKey();

    long getCacheRefreshTimeMillis();
}
