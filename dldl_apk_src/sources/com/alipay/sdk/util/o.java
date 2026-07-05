package com.alipay.sdk.util;

import android.app.Activity;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
final class o implements Runnable {
    final /* synthetic */ Activity a;

    o(Activity activity) {
        this.a = activity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.finish();
    }
}
