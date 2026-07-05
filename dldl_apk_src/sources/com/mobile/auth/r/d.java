package com.mobile.auth.r;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.nirvana.tools.requestqueue.TimeoutCallable;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class d implements TimeoutCallable<com.mobile.auth.w.d> {
    private Context a;
    private com.mobile.auth.q.a b;

    public d(Context context, com.mobile.auth.q.a aVar) {
        this.a = context;
        this.b = aVar;
    }

    private void a(Context context, com.mobile.auth.w.d dVar) {
        try {
            String strB = com.mobile.auth.gatewayauth.utils.c.b();
            if (TextUtils.isEmpty(strB)) {
                dVar.a(false);
            } else {
                dVar.a(true);
                dVar.a(strB);
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public com.mobile.auth.w.d a() {
        try {
            return new com.mobile.auth.w.d(true, false);
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

    public com.mobile.auth.w.d b() throws Exception {
        try {
            com.mobile.auth.w.d dVar = new com.mobile.auth.w.d(false, false);
            if (Build.VERSION.SDK_INT >= 21) {
                a(this.a, dVar);
            } else {
                dVar.a(false);
            }
            return dVar;
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

    @Override // java.util.concurrent.Callable
    public /* synthetic */ Object call() throws Exception {
        try {
            return b();
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

    @Override // com.nirvana.tools.requestqueue.TimeoutCallable
    public /* synthetic */ com.mobile.auth.w.d onTimeout() {
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
