package com.unionpay;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class p implements ae {
    final /* synthetic */ UPPayWapActivity a;

    p(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.ae
    public final void a(String str, af afVar) {
        try {
            this.a.i = afVar;
            String strOptString = new JSONObject(str).optString("scheme");
            if (TextUtils.isEmpty(strOptString)) {
                if (afVar != null) {
                    afVar.a(UPPayWapActivity.b("1", "Parameter error", (String) null));
                    return;
                }
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(strOptString));
            try {
                this.a.startActivity(intent);
            } catch (Exception unused) {
                if (afVar != null) {
                    afVar.a(UPPayWapActivity.b("2", "Call application error", (String) null));
                }
            }
        } catch (Exception e) {
            if (afVar != null) {
                afVar.a(UPPayWapActivity.b("1", e.getMessage(), (String) null));
            }
        }
    }
}
