package com.nirvana.tools.logger.env;

import com.nirvana.tools.logger.model.ACMLimitConfig;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface ACMComponent {
    void clearLimitConfig();

    void setLimitConfig(ACMLimitConfig aCMLimitConfig);

    void setUploadEnabled(boolean z);

    void uploadFailed();
}
