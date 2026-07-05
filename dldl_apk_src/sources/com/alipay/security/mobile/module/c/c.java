package com.alipay.security.mobile.module.c;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
final class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.a.b();
        } catch (Exception e) {
            d.a(e);
        }
    }
}
