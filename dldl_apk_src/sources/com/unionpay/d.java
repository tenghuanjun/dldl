package com.unionpay;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class d implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ Context c;
    final /* synthetic */ String d;

    d(String str, String str2, Context context, String str3) {
        this.a = str;
        this.b = str2;
        this.c = context;
        this.d = str3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            String str = String.format(this.a, com.unionpay.utils.b.b(this.b));
            com.unionpay.utils.j.a("uppay", "merUrl: " + str);
            com.unionpay.a.d dVar = new com.unionpay.a.d(str);
            String strA = com.unionpay.utils.b.a(this.c);
            com.unionpay.a.c cVar = new com.unionpay.a.c(dVar, strA);
            int iA = TextUtils.isEmpty(this.b) ? 22 : cVar.a();
            if (iA == 0) {
                UPPayAssistEx.a(this.c, cVar.b(), this.d);
                return;
            }
            if (iA == 22) {
                String str2 = String.format(this.a, "conf_mode_02");
                com.unionpay.utils.j.a("uppay", "baseUrl: " + str2);
                dVar.b(str2);
                com.unionpay.a.c cVar2 = new com.unionpay.a.c(dVar, strA);
                if (cVar2.a() == 0) {
                    UPPayAssistEx.a(this.c, cVar2.b(), this.d);
                }
            }
        } catch (Exception unused) {
        }
    }
}
