package com.mobile.auth.gatewayauth.sdktools.upload.pns.model;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.nirvana.tools.jsoner.JsonerTag;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class PnsUploadResponse {

    @JsonerTag(keyName = "alibaba_aliqin_pns_info_upload_response")
    private PnsResponse response;

    public PnsResponse getResponse() {
        try {
            return this.response;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public void setResponse(PnsResponse pnsResponse) {
        try {
            this.response = pnsResponse;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }
}
