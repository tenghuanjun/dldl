package com.mobile.auth.u;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.nirvana.tools.requestqueue.Callback;
import com.nirvana.tools.requestqueue.Request;
import com.nirvana.tools.requestqueue.strategy.CallbackStrategy;
import com.nirvana.tools.requestqueue.strategy.ExecuteStrategy;
import com.nirvana.tools.requestqueue.strategy.ThreadStrategy;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class c extends Request<com.mobile.auth.v.a> {
    public c(Callback<com.mobile.auth.v.a> callback, com.mobile.auth.q.a aVar) {
        super(callback, aVar, ThreadStrategy.THREAD, ExecuteStrategy.USE_PREV, CallbackStrategy.LIST, 5000L, com.mobile.auth.v.a.class);
    }

    public static synchronized String a() {
        try {
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
        return UUID.randomUUID().toString();
    }

    @Override // com.nirvana.tools.requestqueue.Request
    public String getKey() {
        try {
            return a();
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
