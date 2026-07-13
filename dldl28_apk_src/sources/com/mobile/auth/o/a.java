package com.mobile.auth.o;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.PnsLoggerHandler;
import com.mobile.auth.gatewayauth.PnsReporter;
import com.mobile.auth.gatewayauth.manager.d;
import com.mobile.auth.gatewayauth.manager.f;
import com.mobile.auth.gatewayauth.utils.i;
import com.nirvana.tools.logger.utils.ConsoleLogUtils;

/* JADX INFO: loaded from: classes3.dex */
public class a implements PnsReporter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.mobile.auth.p.a f758a;
    private d b;
    private f c;

    public a(com.mobile.auth.p.a aVar, d dVar) {
        this.f758a = aVar;
        this.b = dVar;
    }

    private static boolean a() {
        try {
            return Class.forName("com.nirvana.tools.logger.utils.ConsoleLogUtils") != null;
        } catch (ClassNotFoundException unused) {
            return false;
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

    public void a(f fVar) {
        try {
            this.c = fVar;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.PnsReporter
    public void setLogExtension(String str) {
        try {
            this.b.c(str);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.PnsReporter
    public void setLoggerEnable(boolean z) {
        try {
            System.currentTimeMillis();
            i.a(z);
            a();
            ConsoleLogUtils.setLoggerEnable(z);
            f fVar = this.c;
            if (fVar != null) {
                for (com.mobile.auth.gatewayauth.manager.a aVar : fVar.a()) {
                    if (aVar != null) {
                        aVar.a(z);
                    }
                }
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.PnsReporter
    public void setLoggerHandler(PnsLoggerHandler pnsLoggerHandler) {
        try {
            this.f758a.a(pnsLoggerHandler);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.PnsReporter
    public void setUploadEnable(boolean z) {
        try {
            System.currentTimeMillis();
            i.b(z);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }
}
