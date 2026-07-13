package com.mobile.auth.q;

import android.content.Context;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.utils.EncryptUtils;
import com.mobile.auth.v.a;
import com.nirvana.tools.requestqueue.TimeoutCallable;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class a<T extends com.mobile.auth.v.a> implements TimeoutCallable<com.mobile.auth.v.a> {
    private Context a;
    private com.mobile.auth.gatewayauth.manager.b b;

    public a(Context context, com.mobile.auth.gatewayauth.manager.b bVar) {
        this.a = context.getApplicationContext();
        this.b = bVar;
    }

    public abstract T a();

    public abstract T a(String str);

    public com.mobile.auth.v.a b() {
        try {
            if (this.b.j()) {
                return a();
            }
            this.b.k();
            return a(EncryptUtils.generateAesKey());
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

    public Context c() {
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
}
