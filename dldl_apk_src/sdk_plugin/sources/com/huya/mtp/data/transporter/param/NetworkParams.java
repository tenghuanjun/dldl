package com.huya.mtp.data.transporter.param;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface NetworkParams<Rsp> extends CacheParams, HttpParams {
    Class<? extends Rsp> getResponseType();

    boolean shouldUseCustomCache();

    boolean testDataEnabled();

    String testDataFileName();

    String testDataFolderPath();
}
