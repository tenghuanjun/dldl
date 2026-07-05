package com.alipay.sdk.app;

import com.alipay.sdk.util.e;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
class h implements e.a {
    final /* synthetic */ PayTask a;

    @Override // com.alipay.sdk.util.e.a
    public void a() {
    }

    h(PayTask payTask) {
        this.a = payTask;
    }

    @Override // com.alipay.sdk.util.e.a
    public void b() {
        this.a.dismissLoading();
    }
}
