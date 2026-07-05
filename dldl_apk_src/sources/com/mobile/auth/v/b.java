package com.mobile.auth.v;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.sdktools.upload.pns.model.UploadLogDTO;
import com.mobile.auth.gatewayauth.sdktools.upload.pns.model.UploadMonitorDTO;
import com.nirvana.tools.jsoner.JsonType;
import com.nirvana.tools.requestqueue.Callback;
import com.nirvana.tools.requestqueue.Request;
import com.nirvana.tools.requestqueue.TimeoutCallable;
import com.nirvana.tools.requestqueue.strategy.CallbackStrategy;
import com.nirvana.tools.requestqueue.strategy.ExecuteStrategy;
import com.nirvana.tools.requestqueue.strategy.ThreadStrategy;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class b extends Request<com.mobile.auth.w.d> {
    private static final String a = b.class.getName();

    /* JADX INFO: renamed from: com.mobile.auth.v.b$1, reason: invalid class name */
    class AnonymousClass1 extends JsonType<UploadLogDTO> {
        AnonymousClass1() {
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.v.b$2, reason: invalid class name */
    class AnonymousClass2 extends JsonType<UploadMonitorDTO> {
        AnonymousClass2() {
        }
    }

    public b(Callback<com.mobile.auth.w.d> callback, TimeoutCallable<com.mobile.auth.w.d> timeoutCallable) {
        super(callback, timeoutCallable, ThreadStrategy.THREAD, ExecuteStrategy.USE_PREV, CallbackStrategy.LIST, 500L, com.mobile.auth.w.d.class);
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
