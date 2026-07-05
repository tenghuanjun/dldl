package com.alipay.sdk.util;

import com.alipay.sdk.app.AlipayResultActivity;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
class g implements AlipayResultActivity.a {
    final /* synthetic */ CountDownLatch a;
    final /* synthetic */ e b;

    g(e eVar, CountDownLatch countDownLatch) {
        this.b = eVar;
        this.a = countDownLatch;
    }

    @Override // com.alipay.sdk.app.AlipayResultActivity.a
    public void a(int i, String str, String str2) {
        this.b.i = com.alipay.sdk.app.j.a(i, str, str2);
        this.a.countDown();
    }
}
