package com.unionpay;

import com.unionpay.tsmservice.mini.data.Constant;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class k implements ae {
    final /* synthetic */ UPPayWapActivity a;

    k(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.ae
    public final void a(String str, af afVar) {
        String str2;
        String str3 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            try {
                str2 = (String) jSONObject.get(Constant.KEY_RESULT_CODE);
            } catch (Exception e) {
                e = e;
                str2 = "";
            }
            try {
                str3 = (String) jSONObject.get("resultData");
            } catch (Exception e2) {
                e = e2;
                if (afVar != null) {
                    afVar.a(UPPayWapActivity.b("1", e.getMessage(), (String) null));
                }
            }
            this.a.a(str2, str3);
            if (afVar != null) {
                afVar.a(UPPayWapActivity.b("0", "success", (String) null));
            }
        } catch (Exception e3) {
            if (afVar != null) {
                afVar.a(UPPayWapActivity.b("1", e3.getMessage(), (String) null));
            }
        }
    }
}
