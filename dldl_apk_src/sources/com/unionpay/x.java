package com.unionpay;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class x implements ae {
    final /* synthetic */ UPPayWapActivity a;

    x(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.ae
    public final void a(String str, af afVar) {
        UPPayWapActivity.a(this.a, Boolean.parseBoolean(str));
        if (afVar != null) {
            afVar.a(UPPayWapActivity.b("0", "success", (String) null));
        }
    }
}
