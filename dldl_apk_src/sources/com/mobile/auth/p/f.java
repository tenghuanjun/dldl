package com.mobile.auth.p;

import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.manager.RequestCallback;
import com.mobile.auth.gatewayauth.manager.a;
import com.nirvana.tools.requestqueue.TimeoutCallable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class f implements TimeoutCallable<com.mobile.auth.u.f> {
    private com.mobile.auth.gatewayauth.manager.f a;
    private a.b b;
    private String c;

    public f(com.mobile.auth.gatewayauth.manager.f fVar, String str, a.b bVar) {
        this.a = fVar;
        this.b = bVar;
        this.c = str;
    }

    public com.mobile.auth.u.f a() {
        try {
            com.mobile.auth.u.f fVar = new com.mobile.auth.u.f(true);
            fVar.a(com.mobile.auth.gatewayauth.manager.base.c.a(ResultCode.CODE_ERROR_FUNCTION_TIME_OUT, "请求超时"));
            return fVar;
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

    public com.mobile.auth.u.f b() {
        try {
            com.mobile.auth.gatewayauth.manager.a aVarA = this.a.a(this.c);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final com.mobile.auth.u.f fVar = new com.mobile.auth.u.f(false);
            aVarA.c(new RequestCallback<a.c, com.mobile.auth.gatewayauth.manager.base.c>() { // from class: com.mobile.auth.p.f.1
                public void a(a.c cVar) {
                    try {
                        fVar.a(true);
                        fVar.a(com.mobile.auth.gatewayauth.manager.base.c.a().c(cVar.b()).a(cVar.c()).a());
                        countDownLatch.countDown();
                    } catch (Throwable th) {
                        try {
                            com.mobile.auth.gatewayauth.a.a(th);
                        } catch (Throwable th2) {
                            com.mobile.auth.gatewayauth.a.a(th2);
                        }
                    }
                }

                public void a(com.mobile.auth.gatewayauth.manager.base.c cVar) {
                    try {
                        fVar.a(cVar);
                        countDownLatch.countDown();
                    } catch (Throwable th) {
                        try {
                            com.mobile.auth.gatewayauth.a.a(th);
                        } catch (Throwable th2) {
                            com.mobile.auth.gatewayauth.a.a(th2);
                        }
                    }
                }

                @Override // com.mobile.auth.gatewayauth.manager.RequestCallback
                public /* synthetic */ void onError(com.mobile.auth.gatewayauth.manager.base.c cVar) {
                    try {
                        a(cVar);
                    } catch (Throwable th) {
                        try {
                            com.mobile.auth.gatewayauth.a.a(th);
                        } catch (Throwable th2) {
                            com.mobile.auth.gatewayauth.a.a(th2);
                        }
                    }
                }

                @Override // com.mobile.auth.gatewayauth.manager.RequestCallback
                public /* synthetic */ void onSuccess(a.c cVar) {
                    try {
                        a(cVar);
                    } catch (Throwable th) {
                        try {
                            com.mobile.auth.gatewayauth.a.a(th);
                        } catch (Throwable th2) {
                            com.mobile.auth.gatewayauth.a.a(th2);
                        }
                    }
                }
            }, this.b);
            try {
                countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                fVar.a(com.mobile.auth.gatewayauth.manager.base.c.a(Constant.CODE_ERROR_UNKNOWN_FAIL, com.mobile.auth.gatewayauth.utils.b.b(e)));
            }
            return fVar;
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

    @Override // java.util.concurrent.Callable
    public /* synthetic */ Object call() throws Exception {
        try {
            return b();
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

    @Override // com.nirvana.tools.requestqueue.TimeoutCallable
    public /* synthetic */ com.mobile.auth.u.f onTimeout() {
        try {
            return a();
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
}
