package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.data.a;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.l;
import com.alipay.sdk.util.n;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class AuthTask {
    static final Object a = com.alipay.sdk.util.e.class;
    private Activity b;
    private com.alipay.sdk.widget.a c;

    public AuthTask(Activity activity) {
        this.b = activity;
        com.alipay.sdk.sys.b.a().a(this.b, com.alipay.sdk.data.c.b());
        com.alipay.sdk.app.statistic.a.a(activity);
        this.c = new com.alipay.sdk.widget.a(activity, com.alipay.sdk.widget.a.c);
    }

    private e.a a() {
        return new a(this);
    }

    private void b() {
        com.alipay.sdk.widget.a aVar = this.c;
        if (aVar != null) {
            aVar.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        com.alipay.sdk.widget.a aVar = this.c;
        if (aVar != null) {
            aVar.c();
        }
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        return l.a(auth(str, z));
    }

    public synchronized String auth(String str, boolean z) {
        String strC;
        Activity activity;
        if (z) {
            b();
            com.alipay.sdk.sys.b.a().a(this.b, com.alipay.sdk.data.c.b());
            strC = j.c();
            i.a("");
            try {
                try {
                    strC = a(this.b, str);
                    com.alipay.sdk.data.a.g().a(this.b);
                    c();
                    activity = this.b;
                } catch (Exception e) {
                    com.alipay.sdk.util.c.a(e);
                    com.alipay.sdk.data.a.g().a(this.b);
                    c();
                    activity = this.b;
                }
                com.alipay.sdk.app.statistic.a.b(activity, str);
            } finally {
            }
        } else {
            com.alipay.sdk.sys.b.a().a(this.b, com.alipay.sdk.data.c.b());
            strC = j.c();
            i.a("");
            strC = a(this.b, str);
            com.alipay.sdk.data.a.g().a(this.b);
            c();
            activity = this.b;
            com.alipay.sdk.app.statistic.a.b(activity, str);
        }
        return strC;
    }

    private String a(Activity activity, String str) {
        String strA = new com.alipay.sdk.sys.a(this.b).a(str);
        List<a.C0008a> listF = com.alipay.sdk.data.a.g().f();
        if (!com.alipay.sdk.data.a.g().q || listF == null) {
            listF = i.a;
        }
        if (n.b(this.b, listF)) {
            String strA2 = new com.alipay.sdk.util.e(activity, a()).a(strA);
            if (!TextUtils.equals(strA2, com.alipay.sdk.util.e.a) && !TextUtils.equals(strA2, com.alipay.sdk.util.e.b)) {
                return TextUtils.isEmpty(strA2) ? j.c() : strA2;
            }
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.O, "");
            return b(activity, strA);
        }
        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.P, "");
        return b(activity, strA);
    }

    private String b(Activity activity, String str) {
        k kVarB;
        b();
        try {
            try {
                try {
                    List<com.alipay.sdk.protocol.b> listA = com.alipay.sdk.protocol.b.a(new com.alipay.sdk.packet.impl.a().a(activity, str).c().optJSONObject(com.alipay.sdk.cons.c.c).optJSONObject(com.alipay.sdk.cons.c.d));
                    c();
                    for (int i = 0; i < listA.size(); i++) {
                        if (listA.get(i).b() == com.alipay.sdk.protocol.a.WapPay) {
                            String strA = a(listA.get(i));
                            c();
                            return strA;
                        }
                    }
                } catch (IOException e) {
                    k kVarB2 = k.b(k.NETWORK_ERROR.a());
                    com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.a, e);
                    c();
                    kVarB = kVarB2;
                }
            } catch (Throwable th) {
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.v, th);
            }
            c();
            kVarB = null;
            if (kVarB == null) {
                kVarB = k.b(k.FAILED.a());
            }
            return j.a(kVarB.a(), kVarB.b(), "");
        } catch (Throwable th2) {
            c();
            throw th2;
        }
    }

    private String a(com.alipay.sdk.protocol.b bVar) {
        String[] strArrC = bVar.c();
        Bundle bundle = new Bundle();
        bundle.putString("url", strArrC[0]);
        Intent intent = new Intent(this.b, (Class<?>) H5AuthActivity.class);
        intent.putExtras(bundle);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException unused) {
                return j.c();
            }
        }
        String strA = j.a();
        return TextUtils.isEmpty(strA) ? j.c() : strA;
    }
}
