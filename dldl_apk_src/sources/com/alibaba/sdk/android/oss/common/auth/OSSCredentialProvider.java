package com.alibaba.sdk.android.oss.common.auth;

import com.alibaba.sdk.android.oss.ClientException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface OSSCredentialProvider {
    OSSFederationToken getFederationToken() throws ClientException;
}
