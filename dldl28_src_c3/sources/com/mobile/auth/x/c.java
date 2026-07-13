package com.mobile.auth.x;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.nirvana.tools.logger.model.ACMMonitorRecord;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class c extends a<ACMMonitorRecord> {
    @Override // com.mobile.auth.x.a
    protected boolean a(String str) {
        try {
            if (this.a != null) {
                return this.a.a(str);
            }
            return false;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
    }
}
