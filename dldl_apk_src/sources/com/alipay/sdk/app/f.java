package com.alipay.sdk.app;

import android.app.Activity;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
final class f implements Runnable {
    final /* synthetic */ Activity a;

    f(Activity activity) {
        this.a = activity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.finish();
    }
}
