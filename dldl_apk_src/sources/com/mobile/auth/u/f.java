package com.mobile.auth.u;

import com.nirvana.tools.requestqueue.TimeoutResponse;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class f extends TimeoutResponse {
    private boolean a;
    private com.mobile.auth.gatewayauth.manager.base.c b;

    public f(boolean z) {
        super(z);
    }

    public void a(com.mobile.auth.gatewayauth.manager.base.c cVar) {
        try {
            this.b = cVar;
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public void a(boolean z) {
        try {
            this.a = z;
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public boolean a() {
        try {
            return this.a;
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return false;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return false;
            }
        }
    }

    public com.mobile.auth.gatewayauth.manager.base.c b() {
        try {
            return this.b;
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

    @Override // com.nirvana.tools.requestqueue.TimeoutResponse
    public boolean isResultTimeout() {
        return false;
    }
}
