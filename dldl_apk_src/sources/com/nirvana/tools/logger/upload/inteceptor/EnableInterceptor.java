package com.nirvana.tools.logger.upload.inteceptor;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class EnableInterceptor implements BaseInterceptor {
    private boolean mEnabled;

    @Override // com.nirvana.tools.logger.upload.inteceptor.BaseInterceptor
    public boolean isAllowUploading() {
        return this.mEnabled;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }
}
