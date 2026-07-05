package com.igexin.push.core.a.a;

import android.os.Message;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.igexin.push.c.c;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.d;
import com.igexin.push.core.e.e.AnonymousClass1;
import com.igexin.push.core.e.e.AnonymousClass12;
import com.igexin.push.core.l;
import com.igexin.push.core.m;
import com.igexin.push.e.b.d;
import com.igexin.push.f.k;
import com.igexin.sdk.main.FeedbackImpl;
import com.igexin.sdk.router.GTBoater;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c extends com.igexin.push.core.a.a {
    private static final String b = "LoginResult";

    /* JADX INFO: renamed from: com.igexin.push.core.a.a.c$1, reason: invalid class name */
    final class AnonymousClass1 extends com.igexin.push.e.d {
        AnonymousClass1() {
        }

        @Override // com.igexin.push.e.d
        public final void b() {
            try {
                com.igexin.push.core.e.e eVarA = com.igexin.push.core.e.e.a();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis != com.igexin.push.core.e.O) {
                    com.igexin.push.core.e.O = jCurrentTimeMillis;
                    com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) eVarA.new AnonymousClass12(), false, true);
                }
                if (com.igexin.push.core.c.a.a == null) {
                    com.igexin.push.core.c.a.a = new com.igexin.push.core.c.a();
                }
                com.igexin.push.core.c.a aVar = com.igexin.push.core.c.a.a;
                ArrayList arrayList = new ArrayList();
                aVar.a(arrayList);
                if (arrayList.isEmpty()) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("action", "reportapplist");
                    jSONObject.put("session_last", com.igexin.push.core.e.w);
                    JSONArray jSONArray = new JSONArray();
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("appid", arrayList.get(i).c);
                        jSONObject2.put("name", arrayList.get(i).a);
                        jSONObject2.put("version", arrayList.get(i).b);
                        jSONObject2.put(TTDownloadField.TT_VERSION_NAME, arrayList.get(i).d);
                        jSONArray.put(jSONObject2);
                    }
                    jSONObject.put("applist", jSONArray);
                } catch (Exception unused) {
                }
                com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new com.igexin.push.e.a.c(new com.igexin.push.core.h.a(SDKUrlConfig.getBiUploadServiceUrl(), jSONObject.toString().getBytes())), false, true);
                com.igexin.b.a.c.a.a("reportapplist", new Object[0]);
            } catch (Throwable unused2) {
            }
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.a.a.c$2, reason: invalid class name */
    final class AnonymousClass2 extends com.igexin.push.e.d {
        AnonymousClass2() {
        }

        @Override // com.igexin.push.e.d
        public final void b() {
            try {
                com.igexin.push.core.e.c cVar = new com.igexin.push.core.e.c(com.igexin.push.core.e.i);
                JSONObject jSONObjectA = cVar.a();
                if (jSONObjectA == null) {
                    return;
                }
                Iterator<String> itKeys = jSONObjectA.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    JSONObject jSONObject = jSONObjectA.getJSONObject(next);
                    com.igexin.b.a.c.a.a("LoginResult|send unFeedback taskid = ".concat(String.valueOf(next)), new Object[0]);
                    jSONObject.put("appid", com.igexin.push.core.e.a);
                    FeedbackImpl.getInstance().feedbackMultiBrandMessageAction(jSONObject, jSONObject.getString("multaid"));
                    itKeys.remove();
                }
                cVar.b();
            } catch (Throwable th) {
                com.igexin.b.a.c.a.a("LoginResult|feedbackMultiBrandPushMessage exception :" + th.toString(), new Object[0]);
            }
        }
    }

    private void d() {
        com.igexin.b.a.c.a.d.a().a("[LoginResult] Login successed with cid = " + com.igexin.push.core.e.x);
        com.igexin.push.c.c cVar = c.b.a;
        cVar.c = System.currentTimeMillis();
        if (cVar.b) {
            cVar.e = new com.igexin.push.c.e();
            d.a.a.g();
            cVar.d = 0;
        }
        String str = com.igexin.push.core.e.x;
        com.igexin.b.a.c.a.a("loginRsp|" + com.igexin.push.core.e.x + "|success", new Object[0]);
        StringBuilder sb = new StringBuilder("isCidBroadcasted|");
        sb.append(com.igexin.push.core.e.s);
        com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
        if (!com.igexin.push.core.e.s) {
            m.a().c();
            com.igexin.push.core.e.s = true;
        }
        com.igexin.push.core.e.r = true;
        k.f();
        m.a().b();
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.g();
        if (TextUtils.isEmpty(com.igexin.push.core.e.F)) {
            com.igexin.b.a.c.a.a("LoginResult device id is empty, get device id from server +++++", new Object[0]);
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.h();
        }
        if ((System.currentTimeMillis() - com.igexin.push.core.e.O) - 86400000 >= 0 && com.igexin.push.config.d.h) {
            com.igexin.b.a.c.a.a("LoginResult, over 24h, start upload applist", new Object[0]);
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass1(), false, true);
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - com.igexin.push.core.e.N;
        long j = com.igexin.push.core.e.N;
        com.igexin.b.a.c.a.a("LoginResult|lastAddphoneinfoTime: " + com.igexin.push.core.e.N, new Object[0]);
        boolean z = jCurrentTimeMillis - 86400000 > 0;
        boolean z2 = !com.igexin.b.b.a.a(com.igexin.push.core.e.H, com.igexin.push.core.e.G);
        boolean z3 = !com.igexin.push.core.e.x.equals(com.igexin.push.core.e.y);
        com.igexin.b.a.c.a.a("LoginResult|isOverOneDay = " + z + ", isDeviceTokenDiff = " + z2 + ", isCidDiff = " + z3, new Object[0]);
        if (z || z2 || z3) {
            com.igexin.push.core.a.b.d().i();
        }
        g();
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) com.igexin.push.core.e.e.a().new AnonymousClass1(), false, true);
        if (!com.igexin.push.core.e.x.equals(com.igexin.push.core.e.y)) {
            com.igexin.push.core.e.y = com.igexin.push.core.e.x;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = com.igexin.push.core.b.V;
        messageObtain.obj = new Object();
        d.a.a.a(messageObtain);
        GTBoater.getInstance();
        if (com.igexin.assist.sdk.a.a().b()) {
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass2(), false, true);
        }
    }

    private static void e() {
        long jCurrentTimeMillis = System.currentTimeMillis() - com.igexin.push.core.e.N;
        long j = com.igexin.push.core.e.N;
        com.igexin.b.a.c.a.a("LoginResult|lastAddphoneinfoTime: " + com.igexin.push.core.e.N, new Object[0]);
        boolean z = jCurrentTimeMillis - 86400000 > 0;
        boolean z2 = !com.igexin.b.b.a.a(com.igexin.push.core.e.H, com.igexin.push.core.e.G);
        boolean zEquals = true ^ com.igexin.push.core.e.x.equals(com.igexin.push.core.e.y);
        com.igexin.b.a.c.a.a("LoginResult|isOverOneDay = " + z + ", isDeviceTokenDiff = " + z2 + ", isCidDiff = " + zEquals, new Object[0]);
        if (z || z2 || zEquals) {
            com.igexin.push.core.a.b.d().i();
        }
    }

    private static void f() {
        com.igexin.b.a.c.a.d.a().a("[LoginResult] Login " + com.igexin.push.core.e.x + " failed");
        com.igexin.b.a.c.a.a("LoginResult login failed, clear session or cid", new Object[0]);
        com.igexin.push.core.e.e.a().c();
        l.a();
        l.b();
    }

    private static void g() {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis() - com.igexin.push.core.e.Q;
            long j = com.igexin.push.core.e.Q;
            if (jCurrentTimeMillis - 86400000 > 0) {
                com.igexin.push.core.d.b.c().a();
            }
        } catch (Exception unused) {
        }
    }

    private void h() {
        if ((System.currentTimeMillis() - com.igexin.push.core.e.O) - 86400000 < 0 || !com.igexin.push.config.d.h) {
            return;
        }
        com.igexin.b.a.c.a.a("LoginResult, over 24h, start upload applist", new Object[0]);
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass1(), false, true);
    }

    private static void i() {
        if (com.igexin.push.core.e.x.equals(com.igexin.push.core.e.y)) {
            return;
        }
        com.igexin.push.core.e.y = com.igexin.push.core.e.x;
    }

    private void j() {
        if (com.igexin.assist.sdk.a.a().b()) {
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass2(), false, true);
        }
    }

    @Override // com.igexin.push.core.a.a
    public final void a() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean a(Object obj) {
        if (obj instanceof com.igexin.push.c.c.k) {
            com.igexin.push.core.e.L = 0L;
            if (!com.igexin.push.core.e.r) {
                com.igexin.push.b.c.a().d().e();
                if (((com.igexin.push.c.c.k) obj).b) {
                    com.igexin.b.a.c.a.d.a().a("[LoginResult] Login successed with cid = " + com.igexin.push.core.e.x);
                    com.igexin.push.c.c cVar = c.b.a;
                    cVar.c = System.currentTimeMillis();
                    if (cVar.b) {
                        cVar.e = new com.igexin.push.c.e();
                        d.a.a.g();
                        cVar.d = 0;
                    }
                    String str = com.igexin.push.core.e.x;
                    com.igexin.b.a.c.a.a("loginRsp|" + com.igexin.push.core.e.x + "|success", new Object[0]);
                    StringBuilder sb = new StringBuilder("isCidBroadcasted|");
                    sb.append(com.igexin.push.core.e.s);
                    com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
                    if (!com.igexin.push.core.e.s) {
                        m.a().c();
                        com.igexin.push.core.e.s = true;
                    }
                    com.igexin.push.core.e.r = true;
                    k.f();
                    m.a().b();
                    com.igexin.push.core.a.b.d();
                    com.igexin.push.core.a.b.g();
                    if (TextUtils.isEmpty(com.igexin.push.core.e.F)) {
                        com.igexin.b.a.c.a.a("LoginResult device id is empty, get device id from server +++++", new Object[0]);
                        com.igexin.push.core.a.b.d();
                        com.igexin.push.core.a.b.h();
                    }
                    if ((System.currentTimeMillis() - com.igexin.push.core.e.O) - 86400000 >= 0 && com.igexin.push.config.d.h) {
                        com.igexin.b.a.c.a.a("LoginResult, over 24h, start upload applist", new Object[0]);
                        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass1(), false, true);
                    }
                    long jCurrentTimeMillis = System.currentTimeMillis() - com.igexin.push.core.e.N;
                    long j = com.igexin.push.core.e.N;
                    com.igexin.b.a.c.a.a("LoginResult|lastAddphoneinfoTime: " + com.igexin.push.core.e.N, new Object[0]);
                    boolean z = jCurrentTimeMillis - 86400000 > 0;
                    boolean z2 = !com.igexin.b.b.a.a(com.igexin.push.core.e.H, com.igexin.push.core.e.G);
                    boolean z3 = !com.igexin.push.core.e.x.equals(com.igexin.push.core.e.y);
                    com.igexin.b.a.c.a.a("LoginResult|isOverOneDay = " + z + ", isDeviceTokenDiff = " + z2 + ", isCidDiff = " + z3, new Object[0]);
                    if (z || z2 || z3) {
                        com.igexin.push.core.a.b.d().i();
                    }
                    g();
                    com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) com.igexin.push.core.e.e.a().new AnonymousClass1(), false, true);
                    if (!com.igexin.push.core.e.x.equals(com.igexin.push.core.e.y)) {
                        com.igexin.push.core.e.y = com.igexin.push.core.e.x;
                    }
                    Message messageObtain = Message.obtain();
                    messageObtain.what = com.igexin.push.core.b.V;
                    messageObtain.obj = new Object();
                    d.a.a.a(messageObtain);
                    GTBoater.getInstance();
                    if (com.igexin.assist.sdk.a.a().b()) {
                        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass2(), false, true);
                    }
                } else {
                    com.igexin.b.a.c.a.d.a().a("[LoginResult] Login " + com.igexin.push.core.e.x + " failed");
                    com.igexin.b.a.c.a.a("LoginResult login failed, clear session or cid", new Object[0]);
                    com.igexin.push.core.e.e.a().c();
                    l.a();
                    l.b();
                }
            }
        }
        return true;
    }

    @Override // com.igexin.push.core.a.a
    public final void b() {
    }

    @Override // com.igexin.push.core.a.a
    public final boolean c() {
        return false;
    }
}
