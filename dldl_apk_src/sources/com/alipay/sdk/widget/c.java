package com.alipay.sdk.widget;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.e == null || !this.a.e.isShowing()) {
            return;
        }
        try {
            this.a.l.removeMessages(1);
            this.a.e.dismiss();
        } catch (Exception e) {
            com.alipay.sdk.util.c.a(e);
        }
    }
}
