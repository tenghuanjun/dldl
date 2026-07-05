package com.mobile.auth.t;

import com.nirvana.tools.requestqueue.Callback;
import com.nirvana.tools.requestqueue.Request;
import com.nirvana.tools.requestqueue.strategy.CallbackStrategy;
import com.nirvana.tools.requestqueue.strategy.ExecuteStrategy;
import com.nirvana.tools.requestqueue.strategy.ThreadStrategy;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class d extends Request<com.mobile.auth.u.a> {
    /* JADX WARN: Multi-variable type inference failed */
    public d(Callback<com.mobile.auth.u.a> callback, com.mobile.auth.p.a aVar) {
        super(callback, aVar, ThreadStrategy.THREAD, ExecuteStrategy.USE_PREV, CallbackStrategy.LIST, 5000L, com.mobile.auth.u.a.class);
    }

    @Override // com.nirvana.tools.requestqueue.Request
    public String getKey() {
        try {
            return UUID.randomUUID().toString();
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }
}
