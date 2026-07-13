package com.mobile.auth.q;

import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.manager.RequestCallback;
import com.mobile.auth.gatewayauth.manager.a;
import com.mobile.auth.gatewayauth.manager.f;
import com.nirvana.tools.core.ExecutorManager;
import com.nirvana.tools.requestqueue.TimeoutCallable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d implements TimeoutCallable<com.mobile.auth.v.d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private f f776a;
    private String b;
    private long c;
    private String d;

    public d(f fVar, String str, long j, String str2) {
        this.f776a = fVar;
        this.b = str;
        this.c = j;
        this.d = str2;
    }

    public com.mobile.auth.v.d a() {
        try {
            com.mobile.auth.v.d dVar = new com.mobile.auth.v.d(true);
            dVar.a(com.mobile.auth.gatewayauth.manager.base.b.a(ResultCode.CODE_ERROR_FUNCTION_TIME_OUT, "请求超时"));
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

    public abstract void a(RequestCallback requestCallback, com.mobile.auth.gatewayauth.manager.a aVar);

    public com.mobile.auth.v.d b() {
        try {
            com.mobile.auth.gatewayauth.utils.e.a().a(this.d, "doRequest", System.currentTimeMillis());
            com.mobile.auth.gatewayauth.manager.a aVarA = this.f776a.a(this.b);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final com.mobile.auth.v.d dVar = new com.mobile.auth.v.d(false);
            a(new RequestCallback<a.C0424a, com.mobile.auth.gatewayauth.manager.base.b>() { // from class: com.mobile.auth.q.d.1
                public void a(a.C0424a c0424a) {
                    try {
                        dVar.a(true);
                        dVar.a(c0424a);
                        dVar.a(com.mobile.auth.gatewayauth.manager.base.b.a().c(c0424a.c()).a(c0424a.d()).a());
                        countDownLatch.countDown();
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
                        dVar.a(bVar);
                        countDownLatch.countDown();
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }

                @Override // com.mobile.auth.gatewayauth.manager.RequestCallback
                public /* synthetic */ void onError(com.mobile.auth.gatewayauth.manager.base.b bVar) {
                    try {
                        a(bVar);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }

                @Override // com.mobile.auth.gatewayauth.manager.RequestCallback
                public /* synthetic */ void onSuccess(a.C0424a c0424a) {
                    try {
                        a(c0424a);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }
            }, aVarA);
            try {
                long j = this.c;
                if (j <= 5000) {
                    j = 5000;
                }
                countDownLatch.await(j, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                dVar.a(com.mobile.auth.gatewayauth.manager.base.b.a(Constant.CODE_ERROR_UNKNOWN_FAIL, ExecutorManager.getErrorInfoFromException(e)));
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
    public /* synthetic */ com.mobile.auth.v.d onTimeout() {
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
