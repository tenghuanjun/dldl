package com.mobile.auth.v;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.nirvana.tools.requestqueue.Callback;
import com.nirvana.tools.requestqueue.Request;
import com.nirvana.tools.requestqueue.TimeoutCallable;
import com.nirvana.tools.requestqueue.strategy.CallbackStrategy;
import com.nirvana.tools.requestqueue.strategy.ExecuteStrategy;
import com.nirvana.tools.requestqueue.strategy.ThreadStrategy;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class a extends Request<com.mobile.auth.w.c> {
    private static final String a = a.class.getName();

    public a(Callback<com.mobile.auth.w.c> callback, TimeoutCallable<com.mobile.auth.w.c> timeoutCallable, long j, Class<com.mobile.auth.w.c> cls) {
        super(callback, timeoutCallable, ThreadStrategy.THREAD, ExecuteStrategy.USE_PREV, CallbackStrategy.COVER, j, cls);
    }

    @Override // com.nirvana.tools.requestqueue.Request
    public String getKey() {
        try {
            return a;
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
}
