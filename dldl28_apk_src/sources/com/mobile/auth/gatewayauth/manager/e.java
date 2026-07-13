package com.mobile.auth.gatewayauth.manager;

import android.content.Context;
import android.util.SparseArray;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.model.VendorConfig;
import com.nirvana.tools.requestqueue.Callback;
import com.nirvana.tools.requestqueue.RequestQueue;
import com.nirvana.tools.requestqueue.Response;
import com.nirvana.tools.requestqueue.strategy.ThreadStrategy;

/* JADX INFO: loaded from: classes3.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.mobile.auth.q.a f675a;

    interface a {
        void a(String str, String str2);

        void a(String str, String str2, SparseArray<VendorConfig> sparseArray);
    }

    public e(com.mobile.auth.q.a aVar) {
        this.f675a = aVar;
    }

    public static final e a(Context context, VendorSdkInfoManager vendorSdkInfoManager, b bVar, com.mobile.auth.p.a aVar) {
        try {
            return new e(new com.mobile.auth.q.b(context, vendorSdkInfoManager, bVar, aVar));
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

    public void a(final a aVar) {
        try {
            RequestQueue.getInstance().pushRequest(new com.mobile.auth.u.c(new Callback<com.mobile.auth.v.a>(ThreadStrategy.SAME_WITH_CALLABLE, 2000L) { // from class: com.mobile.auth.gatewayauth.manager.e.1
                public void a(com.mobile.auth.v.a aVar2) {
                    try {
                        if (aVar != null) {
                            if (aVar2.a()) {
                                aVar.a(aVar2.c(), aVar2.b(), aVar2.e());
                            } else {
                                aVar.a(aVar2.c(), aVar2.b());
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

                @Override // com.nirvana.tools.requestqueue.Callback
                public /* synthetic */ void onResult(Response response) {
                    try {
                        a((com.mobile.auth.v.a) response);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }
            }, this.f675a));
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }
}
