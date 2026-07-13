package com.mobile.auth.gatewayauth.manager;

import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.model.VendorConfig;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class f {
    private SystemManager a;
    private a b;
    private a c;
    private a d;
    private d e;

    public f(SystemManager systemManager, d dVar) {
        this.a = systemManager;
        this.e = dVar;
        this.b = new com.mobile.auth.r.d(systemManager.e(), this.e);
        this.c = new com.mobile.auth.t.a(this.a.e(), this.e);
        this.d = new com.mobile.auth.s.a(this.a.e(), this.e);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.mobile.auth.gatewayauth.manager.a a(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            int r1 = r6.hashCode()     // Catch: java.lang.Throwable -> L46
            r2 = -1350608857(0xffffffffaf7f5827, float:-2.3223433E-10)
            r3 = 2
            r4 = 1
            if (r1 == r2) goto L2b
            r2 = 95009260(0x5a9b9ec, float:1.596098E-35)
            if (r1 == r2) goto L21
            r2 = 880617272(0x347d2738, float:2.3576729E-7)
            if (r1 == r2) goto L17
            goto L35
        L17:
            java.lang.String r1 = "cm_zyhl"
            boolean r6 = r6.equals(r1)     // Catch: java.lang.Throwable -> L46
            if (r6 == 0) goto L35
            r6 = 0
            goto L36
        L21:
            java.lang.String r1 = "cu_xw"
            boolean r6 = r6.equals(r1)     // Catch: java.lang.Throwable -> L46
            if (r6 == 0) goto L35
            r6 = 1
            goto L36
        L2b:
            java.lang.String r1 = "ct_sjl"
            boolean r6 = r6.equals(r1)     // Catch: java.lang.Throwable -> L46
            if (r6 == 0) goto L35
            r6 = 2
            goto L36
        L35:
            r6 = -1
        L36:
            if (r6 == 0) goto L43
            if (r6 == r4) goto L40
            if (r6 == r3) goto L3d
            return r0
        L3d:
            com.mobile.auth.gatewayauth.manager.a r6 = r5.d     // Catch: java.lang.Throwable -> L46
            return r6
        L40:
            com.mobile.auth.gatewayauth.manager.a r6 = r5.c     // Catch: java.lang.Throwable -> L46
            return r6
        L43:
            com.mobile.auth.gatewayauth.manager.a r6 = r5.b     // Catch: java.lang.Throwable -> L46
            return r6
        L46:
            r6 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r6)     // Catch: java.lang.Throwable -> L4b
            return r0
        L4b:
            r6 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.gatewayauth.manager.f.a(java.lang.String):com.mobile.auth.gatewayauth.manager.a");
    }

    public List<a> a() {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.b);
            arrayList.add(this.c);
            arrayList.add(this.d);
            return arrayList;
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

    public boolean a(VendorSdkInfoManager vendorSdkInfoManager) {
        int i;
        try {
            VendorConfig vendorConfigA = vendorSdkInfoManager.a(1);
            VendorConfig vendorConfigA2 = vendorSdkInfoManager.a(2);
            VendorConfig vendorConfigA3 = vendorSdkInfoManager.a(3);
            if (vendorConfigA != null) {
                this.b.a(vendorConfigA.getVendorAccessId(), vendorConfigA.getVendorAccessSecret());
                i = 1;
            } else {
                i = 0;
            }
            if (vendorConfigA2 != null) {
                this.c.a(vendorConfigA2.getVendorAccessId(), vendorConfigA2.getVendorAccessSecret());
                i++;
            }
            if (vendorConfigA3 != null) {
                this.d.a(vendorConfigA3.getVendorAccessId(), vendorConfigA3.getVendorAccessSecret());
                i++;
            }
            return i == 3;
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
}
