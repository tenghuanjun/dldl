package com.alipay.sdk.app.statistic;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.packet.impl.d;
import com.alipay.sdk.util.j;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    b(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        d dVar = new d();
        try {
            String strB = j.b(this.a, a.a, null);
            if (!TextUtils.isEmpty(strB) && dVar.a(this.a, strB) != null) {
                j.b(this.a, a.a);
            }
        } catch (Throwable unused) {
        }
        try {
            if (TextUtils.isEmpty(this.b)) {
                return;
            }
            dVar.a(this.a, this.b);
        } catch (IOException unused2) {
            j.a(this.a, a.a, this.b);
        } catch (Throwable unused3) {
        }
    }
}
