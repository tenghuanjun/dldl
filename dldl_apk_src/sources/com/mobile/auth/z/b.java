package com.mobile.auth.z;

import android.text.TextUtils;
import com.mobile.auth.ab.d;
import com.unionpay.tsmservice.mini.data.Constant;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class b {
    private com.mobile.auth.x.a a = null;

    public void a(com.mobile.auth.x.a aVar) {
        try {
            this.a = aVar;
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public void a(String str) {
        try {
            b(str, "");
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public void a(String str, String str2) {
        try {
            try {
                if (this.a == null) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Constant.KEY_RESULT_CODE, 0);
                jSONObject.put("resultMsg", str);
                if (TextUtils.isEmpty(str2)) {
                    jSONObject.put("resultData", "");
                } else {
                    jSONObject.put("resultData", new JSONObject(str2));
                }
                jSONObject.put("operatorType", "CU");
                this.a.a(jSONObject.toString());
                this.a = null;
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        } catch (Exception e) {
            d.b(e.getMessage());
        }
    }

    public void b(String str, String str2) {
        try {
            try {
                if (this.a == null) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Constant.KEY_RESULT_CODE, 1);
                jSONObject.put("resultMsg", str);
                jSONObject.put("resultData", "");
                jSONObject.put("traceId", str2);
                jSONObject.put("operatorType", "CU");
                this.a.a(jSONObject.toString());
                this.a = null;
                return;
            } catch (Exception e) {
                d.b(e.getMessage());
                return;
            }
        } catch (Throwable th) {
            com.mobile.auth.gatewayauth.a.a(th);
        }
        try {
            com.mobile.auth.gatewayauth.a.a(th);
        } catch (Throwable th2) {
            com.mobile.auth.gatewayauth.a.a(th2);
        }
    }
}
