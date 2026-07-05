package com.unionpay;

import com.unionpay.utils.UPUtils;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class v implements ae {
    final /* synthetic */ UPPayWapActivity a;

    v(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.ae
    public final void a(String str, af afVar) {
        String strA = UPUtils.a(this.a, str);
        if (afVar != null) {
            afVar.a(UPPayWapActivity.b("0", "success", strA));
        }
    }
}
