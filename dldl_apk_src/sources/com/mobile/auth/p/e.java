package com.mobile.auth.p;

import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.manager.RequestCallback;
import com.mobile.auth.gatewayauth.manager.a;
import com.nirvana.tools.requestqueue.TimeoutCallable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public abstract class e implements TimeoutCallable<com.mobile.auth.u.e> {
    private com.mobile.auth.gatewayauth.manager.f a;
    private String b;

    public e(com.mobile.auth.gatewayauth.manager.f fVar, String str) {
        this.a = fVar;
        this.b = str;
    }

    public com.mobile.auth.u.e a() {
        try {
            com.mobile.auth.u.e eVar = new com.mobile.auth.u.e(true);
            eVar.a(com.mobile.auth.gatewayauth.manager.base.c.a(ResultCode.CODE_ERROR_FUNCTION_TIME_OUT, "请求超时"));
            return eVar;
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

    public abstract void a(RequestCallback requestCallback, com.mobile.auth.gatewayauth.manager.a aVar);

    public com.mobile.auth.u.e b() {
        try {
            com.mobile.auth.gatewayauth.manager.a aVarA = this.a.a(this.b);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final com.mobile.auth.u.e eVar = new com.mobile.auth.u.e(false);
            a(new RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.c>() { // from class: com.mobile.auth.p.e.1
                public void a(a.C0070a c0070a) {
                    try {
                        eVar.a(true);
                        eVar.a(c0070a);
                        eVar.a(com.mobile.auth.gatewayauth.manager.base.c.a().c(c0070a.c()).a(c0070a.d()).a());
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
                        eVar.a(cVar);
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
                public /* synthetic */ void onSuccess(a.C0070a c0070a) {
                    try {
                        a(c0070a);
                    } catch (Throwable th) {
                        try {
                            com.mobile.auth.gatewayauth.a.a(th);
                        } catch (Throwable th2) {
                            com.mobile.auth.gatewayauth.a.a(th2);
                        }
                    }
                }
            }, aVarA);
            try {
                countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                eVar.a(com.mobile.auth.gatewayauth.manager.base.c.a(Constant.CODE_ERROR_UNKNOWN_FAIL, com.mobile.auth.gatewayauth.utils.b.b(e)));
            }
            return eVar;
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
    public /* synthetic */ com.mobile.auth.u.e onTimeout() {
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
