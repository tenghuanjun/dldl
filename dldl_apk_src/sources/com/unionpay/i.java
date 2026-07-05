package com.unionpay;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class i implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ Context c;
    final /* synthetic */ String d;
    final /* synthetic */ IUnionCallback e;
    final /* synthetic */ g f;

    i(g gVar, String str, String str2, Context context, String str3, IUnionCallback iUnionCallback) {
        this.f = gVar;
        this.a = str;
        this.b = str2;
        this.c = context;
        this.d = str3;
        this.e = iUnionCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f.a.sendEmptyMessageDelayed(1006, 5000L);
            String str = String.format(this.a, com.unionpay.utils.b.b(this.b));
            com.unionpay.utils.j.a("uppay", "merUrl: " + str);
            com.unionpay.a.d dVar = new com.unionpay.a.d(str);
            String strA = com.unionpay.utils.b.a(this.c);
            com.unionpay.a.c cVar = new com.unionpay.a.c(dVar, strA);
            int iA = TextUtils.isEmpty(this.b) ? 22 : cVar.a();
            if (iA == 0) {
                ArrayList<String> arrayListA = this.f.a(this.c, cVar.b(), this.d);
                if (this.f.a != null) {
                    this.f.a.removeMessages(1006);
                    if (this.f.b || this.f.c == null) {
                        return;
                    }
                    Message messageObtainMessage = this.f.a.obtainMessage();
                    messageObtainMessage.what = 1007;
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("directApps", arrayListA);
                    messageObtainMessage.setData(bundle);
                    this.f.a.sendMessage(messageObtainMessage);
                    return;
                }
                return;
            }
            if (iA != 22) {
                if (this.f.a != null) {
                    this.f.a.removeMessages(1006);
                }
                if (this.f.b || this.f.c == null) {
                    return;
                }
                this.e.onError(UPPayAssistEx.SDK_TYPE, "network error");
                g.c(this.f);
                return;
            }
            String str2 = String.format(this.a, "conf_mode_02");
            dVar.b(str2);
            com.unionpay.utils.j.a("uppay", "baseUrl: " + str2);
            com.unionpay.a.c cVar2 = new com.unionpay.a.c(dVar, strA);
            int iA2 = cVar2.a();
            if (this.f.a != null) {
                this.f.a.removeMessages(1006);
            }
            if (iA2 != 0) {
                if (this.f.b || this.f.c == null) {
                    return;
                }
                this.e.onError(UPPayAssistEx.SDK_TYPE, "network error");
                g.c(this.f);
                return;
            }
            ArrayList<String> arrayListA2 = this.f.a(this.c, cVar2.b(), this.d);
            if (this.f.b || this.f.c == null || this.f.a == null) {
                return;
            }
            Message messageObtainMessage2 = this.f.a.obtainMessage();
            messageObtainMessage2.what = 1007;
            Bundle bundle2 = new Bundle();
            bundle2.putStringArrayList("directApps", arrayListA2);
            messageObtainMessage2.setData(bundle2);
            this.f.a.sendMessage(messageObtainMessage2);
        } catch (Exception unused) {
            if (this.f.a != null) {
                this.f.a.removeMessages(1006);
            }
            if (this.f.b || this.f.c == null) {
                return;
            }
            this.e.onError("03", "unknown error");
            g.c(this.f);
        }
    }
}
