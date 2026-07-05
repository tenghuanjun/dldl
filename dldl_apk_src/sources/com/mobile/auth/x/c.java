package com.mobile.auth.x;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.nirvana.tools.logger.model.ACMLoggerRecord;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class c extends a<ACMLoggerRecord> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mobile.auth.x.a
    public boolean a(String str) {
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
