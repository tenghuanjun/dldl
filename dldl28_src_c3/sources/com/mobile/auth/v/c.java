package com.mobile.auth.v;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.model.ConfigRule;
import com.nirvana.tools.requestqueue.TimeoutResponse;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class c extends TimeoutResponse {
    private ConfigRule a;

    public c(boolean z) {
        super(z);
    }

    public c(boolean z, ConfigRule configRule) {
        super(z);
        this.a = configRule;
    }

    public ConfigRule a() {
        try {
            return this.a;
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

    @Override // com.nirvana.tools.requestqueue.TimeoutResponse
    public boolean isResultTimeout() {
        return false;
    }
}
