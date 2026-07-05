package com.mobile.auth.t;

import com.nirvana.tools.requestqueue.Callback;
import com.nirvana.tools.requestqueue.Request;
import com.nirvana.tools.requestqueue.TimeoutCallable;
import com.nirvana.tools.requestqueue.strategy.CallbackStrategy;
import com.nirvana.tools.requestqueue.strategy.ExecuteStrategy;
import com.nirvana.tools.requestqueue.strategy.ThreadStrategy;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class b extends Request<com.mobile.auth.u.d> {
    private static final String a = "com.mobile.auth.t.b";

    public b(Callback<com.mobile.auth.u.d> callback, TimeoutCallable<com.mobile.auth.u.d> timeoutCallable) {
        super(callback, timeoutCallable, ThreadStrategy.THREAD, ExecuteStrategy.USE_PREV, CallbackStrategy.LIST, 500L, com.mobile.auth.u.d.class);
    }

    @Override // com.nirvana.tools.requestqueue.Request
    public String getKey() {
        try {
            return a;
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
