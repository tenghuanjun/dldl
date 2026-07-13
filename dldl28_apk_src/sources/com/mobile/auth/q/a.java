package com.mobile.auth.q;

import android.content.Context;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.utils.EncryptUtils;
import com.mobile.auth.v.a;
import com.nirvana.tools.requestqueue.TimeoutCallable;

/* JADX INFO: loaded from: classes3.dex */
public abstract class a<T extends com.mobile.auth.v.a> implements TimeoutCallable<com.mobile.auth.v.a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f773a;
    private com.mobile.auth.gatewayauth.manager.b b;

    public a(Context context, com.mobile.auth.gatewayauth.manager.b bVar) {
        this.f773a = context.getApplicationContext();
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
            return this.f773a;
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
