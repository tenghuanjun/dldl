package com.alipay.sdk.widget;

import com.alipay.sdk.widget.a.AlertDialogC0010a;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.e == null) {
            a aVar = this.a;
            a aVar2 = this.a;
            aVar.e = aVar2.new AlertDialogC0010a(aVar2.f);
            this.a.e.setCancelable(this.a.k);
        }
        try {
            if (this.a.e.isShowing()) {
                return;
            }
            this.a.e.show();
            this.a.l.sendEmptyMessageDelayed(1, 15000L);
        } catch (Exception e) {
            com.alipay.sdk.util.c.a(e);
        }
    }
}
