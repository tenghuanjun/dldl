package com.mobile.auth.p;

import android.content.Context;
import com.mobile.auth.gatewayauth.manager.VendorSdkInfoManager;
import com.mobile.auth.gatewayauth.model.psc_sdk_config.ConfigRB;
import com.mobile.auth.gatewayauth.network.TopRequestUtils;
import com.mobile.auth.gatewayauth.utils.EncryptUtils;
import com.nirvana.tools.requestqueue.TimeoutCallable;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class c implements TimeoutCallable<com.mobile.auth.u.c> {
    private Context a;
    private com.mobile.auth.o.a b;
    private VendorSdkInfoManager c;

    public c(Context context, VendorSdkInfoManager vendorSdkInfoManager, com.mobile.auth.o.a aVar) {
        this.a = context;
        this.b = aVar;
        this.c = vendorSdkInfoManager;
    }

    public com.mobile.auth.u.c a() {
        try {
            return new com.mobile.auth.u.c(true);
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

    public com.mobile.auth.u.c b() throws Exception {
        try {
            try {
                String sDKConfig = TopRequestUtils.getSDKConfig(EncryptUtils.noEncryptTinfo(this.a, this.c.c(), com.mobile.auth.gatewayauth.utils.c.b(this.a)));
                this.b.a(new String[]{"getSdkConfig Ret:", sDKConfig});
                ConfigRB configRBFromJson = ConfigRB.fromJson(sDKConfig);
                if (configRBFromJson != null) {
                    if (configRBFromJson.getResponse() == null || configRBFromJson.getResponse().getResult() == null) {
                        if (configRBFromJson.getErrorResponse() != null) {
                            if (configRBFromJson.getErrorResponse().getCode() == 22) {
                                com.mobile.auth.gatewayauth.network.a.a(this.a, true);
                            } else if (configRBFromJson.getErrorResponse().getCode() == 7) {
                                com.mobile.auth.gatewayauth.network.a.a(this.a, com.mobile.auth.gatewayauth.utils.a.a());
                            }
                        }
                    } else if (configRBFromJson.getResponse().getResult().getModel() != null && "OK".equals(configRBFromJson.getResponse().getResult().getCode())) {
                        return new com.mobile.auth.u.c(false, configRBFromJson.getResponse().getResult().getModel());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new com.mobile.auth.u.c(false);
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
    public /* synthetic */ com.mobile.auth.u.c onTimeout() {
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
