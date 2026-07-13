package com.mobile.auth.v;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.manager.a;
import com.nirvana.tools.requestqueue.TimeoutResponse;

/* JADX INFO: loaded from: classes3.dex */
public class d extends TimeoutResponse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a.C0424a f802a;
    private boolean b;
    private com.mobile.auth.gatewayauth.manager.base.b c;

    public d(boolean z) {
        super(z);
    }

    public void a(a.C0424a c0424a) {
        try {
            this.f802a = c0424a;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void a(com.mobile.auth.gatewayauth.manager.base.b bVar) {
        try {
            this.c = bVar;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void a(boolean z) {
        try {
            this.b = z;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public boolean a() {
        try {
            return this.b;
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

    public com.mobile.auth.gatewayauth.manager.base.b b() {
        try {
            return this.c;
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

    public a.C0424a c() {
        try {
            return this.f802a;
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

    @Override // com.nirvana.tools.requestqueue.TimeoutResponse
    public boolean isResultTimeout() {
        return false;
    }
}
