package com.nirvana.tools.logger.env;

import com.nirvana.tools.logger.model.ACMLimitConfig;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public interface ACMComponent {
    void clearLimitConfig();

    void setLimitConfig(ACMLimitConfig aCMLimitConfig);

    void setUploadEnabled(boolean z);

    void uploadFailed();
}
