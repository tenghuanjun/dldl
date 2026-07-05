package com.igexin.push.core.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import com.igexin.push.c.c.h;
import com.igexin.push.c.c.q;
import com.igexin.push.core.a.a.c;
import com.igexin.push.core.a.a.d;
import com.igexin.push.core.a.a.e;
import com.igexin.push.core.a.a.f;
import com.igexin.push.core.b.i;
import com.igexin.push.core.d;
import com.igexin.push.core.e.d.AnonymousClass3;
import com.igexin.push.core.k;
import com.igexin.push.core.m;
import com.igexin.push.core.p;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.push.f.k;
import com.igexin.push.f.n;
import com.igexin.push.f.o;
import com.igexin.sdk.PushConsts;
import com.parameters.performfeatureconfig.PerformFeatureKey;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b extends a implements com.igexin.push.d.b {
    public static SparseArray<a> b = null;
    private static final String c = "CoreAction";
    private static volatile b d;

    private b() {
        SparseArray<a> sparseArray = new SparseArray<>();
        b = sparseArray;
        sparseArray.put(0, new com.igexin.push.core.a.a.a());
        b.put(5, new c());
        b.put(37, new d());
        b.put(9, new f());
        b.put(20, new e());
        b.put(26, new com.igexin.push.core.a.b.d());
        b.put(97, new com.igexin.push.core.a.a.b());
    }

    public static Class a(Context context) {
        return p.a.a.a(context);
    }

    public static String a(String str, String str2) {
        return str + ":" + str2;
    }

    public static void a(Intent intent) {
        com.igexin.b.a.c.a.a("CoreAction|onServiceInitialize ##", new Object[0]);
        if (intent == null) {
            return;
        }
        com.igexin.push.core.d unused = d.a.a;
        com.igexin.push.core.d.a(false);
        com.igexin.push.core.e.K = intent.hasExtra("op_app") ? intent.getStringExtra("op_app") : "";
        com.igexin.push.core.e.s = false;
        if (com.igexin.push.core.e.r) {
            m.a().c();
            com.igexin.push.core.e.s = true;
        }
        if (!o.a(com.igexin.push.core.e.i) || com.igexin.push.core.e.ad == null) {
            return;
        }
        String name = p.a.a.a(com.igexin.push.core.e.i).getName();
        if (!com.igexin.push.core.b.ak.equals(name)) {
            byte[] bArrB = com.igexin.b.b.a.b(name.getBytes());
            if (bArrB != null) {
                k.a(bArrB, com.igexin.push.core.e.ad, false);
                return;
            }
            return;
        }
        if (new File(com.igexin.push.core.e.ad).delete()) {
            String str = com.igexin.push.core.e.ad;
            com.igexin.b.a.c.a.a("del " + com.igexin.push.core.e.ad + " success ~~~", new Object[0]);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.os.Bundle r15) {
        /*
            Method dump skipped, instruction units count: 1560
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.b.a(android.os.Bundle):void");
    }

    public static void a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", com.igexin.push.core.b.B);
            jSONObject.put("id", str);
        } catch (JSONException unused) {
        }
        String string = jSONObject.toString();
        com.igexin.push.c.c.o oVar = new com.igexin.push.c.c.o();
        oVar.c = 128;
        ((com.igexin.push.c.c.b) oVar).b = (int) System.currentTimeMillis();
        oVar.e = com.igexin.push.core.b.K;
        oVar.f = string;
        oVar.h = com.igexin.push.core.e.x;
        d.a.a.i.a("C-" + com.igexin.push.core.e.x, oVar, false);
    }

    @TargetApi(12)
    private static void a(String str, String str2, String str3, String str4) {
        m.a().a(str, str2, str3, str4);
    }

    public static boolean a(String str, String str2, String str3) {
        return com.igexin.push.core.o.a().a(str, str2, str3);
    }

    private static boolean a(JSONObject jSONObject, PushTaskBean pushTaskBean) {
        return com.igexin.push.core.o.a().a(jSONObject, pushTaskBean);
    }

    public static void b(Intent intent) {
        if (intent == null || !intent.hasExtra("isSlave")) {
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("isSlave", false);
        com.igexin.b.a.c.a.a("CoreAction|onServiceInitializeForSlave isSlave =".concat(String.valueOf(booleanExtra)), new Object[0]);
        if (booleanExtra) {
            com.igexin.push.core.d unused = d.a.a;
            com.igexin.push.core.d.a(true);
            com.igexin.push.core.e.K = intent.hasExtra("op_app") ? intent.getStringExtra("op_app") : "";
            if (com.igexin.push.core.e.r) {
                m.a().c();
            }
        }
    }

    public static void b(String str) {
        com.igexin.b.a.c.a.a("CoreAction|resetDelayTime from = ".concat(String.valueOf(str)), new Object[0]);
        if (com.igexin.push.core.e.L <= com.igexin.push.config.c.i && com.igexin.push.core.e.L != 0) {
            long j = com.igexin.push.core.e.L;
            com.igexin.b.a.c.a.a("CoreAction|resetDelayTime ignore, delay = " + com.igexin.push.core.e.L, new Object[0]);
            return;
        }
        int iRandom = (int) ((Math.random() * 100.0d) + 1000.0d);
        long j2 = com.igexin.push.core.e.L;
        com.igexin.b.a.c.a.a("CoreAction|reConnectDelayTime = " + com.igexin.push.core.e.L + ", reset = " + iRandom, new Object[0]);
        com.igexin.push.e.b.e.g().a((long) iRandom);
    }

    private static void c(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        try {
            String action = intent.getAction();
            if (PushConsts.ACTION_BROADCAST_NETWORK_CHANGE.equals(action)) {
                e();
                return;
            }
            if (com.igexin.push.core.b.H.equals(action)) {
                com.igexin.push.core.o.a().a(intent);
                return;
            }
            if (com.igexin.push.core.b.J.equals(action)) {
                if (com.igexin.push.config.d.b != 0) {
                    com.igexin.push.e.e.c().d();
                }
            } else {
                if (!"android.intent.action.SCREEN_ON".equals(action)) {
                    if ("android.intent.action.SCREEN_OFF".equals(action)) {
                        com.igexin.push.core.e.v = 0;
                        return;
                    }
                    return;
                }
                com.igexin.push.core.e.v = 1;
                com.igexin.push.core.o.a();
                if (com.igexin.push.core.o.b()) {
                    com.igexin.push.core.o.a().e();
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    b("screen on");
                }
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(c + th.toString(), new Object[0]);
        }
    }

    public static b d() {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b();
                }
            }
        }
        return d;
    }

    public static void e() {
        com.igexin.push.core.d unused = d.a.a;
        com.igexin.push.d.a.e();
        com.igexin.push.core.o.a();
        if (com.igexin.push.core.o.b()) {
            com.igexin.b.a.c.a.a("CoreAction|network changed check condition status", new Object[0]);
            com.igexin.push.core.o.a().e();
        }
    }

    public static int f() {
        com.igexin.b.a.c.a.a("CoreAction|send heart beat data ........", new Object[0]);
        return d.a.a.i.a("H-" + com.igexin.push.core.e.x, new com.igexin.push.c.c.f(), true);
    }

    public static void g() {
        try {
            for (i iVar : com.igexin.push.core.e.d.a().a) {
                if (iVar.e >= com.igexin.push.config.d.R - 1) {
                    com.igexin.b.a.c.a.a("CoreAction|data.getSendTimes=" + iVar.e + " id=" + iVar.a, new Object[0]);
                } else if (iVar.d + 20000 <= System.currentTimeMillis()) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    JSONObject jSONObject = new JSONObject(iVar.b);
                    com.igexin.push.c.c.b bVar = new com.igexin.push.c.c.b();
                    bVar.c = 128;
                    bVar.b = (int) jCurrentTimeMillis;
                    bVar.e = com.igexin.push.core.b.K;
                    if (jSONObject.has("extraData")) {
                        bVar.g = Base64.decode(jSONObject.optString("extraData").getBytes(), 0);
                        jSONObject.remove("extraData");
                    }
                    bVar.f = iVar.b;
                    bVar.h = com.igexin.push.core.e.x;
                    com.igexin.b.a.c.a.a("freshral|" + iVar.b, new Object[0]);
                    com.igexin.push.core.e.d dVarA = com.igexin.push.core.e.d.a();
                    long j = iVar.a;
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    i iVarA = dVarA.a(j);
                    if (iVarA != null) {
                        iVarA.d = jCurrentTimeMillis2;
                        iVarA.e++;
                        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) dVarA.new AnonymousClass3(com.igexin.push.core.e.d.a(iVarA), j), true, true);
                    }
                    d.a.a.i.a("C-" + com.igexin.push.core.e.x, bVar, false);
                    return;
                }
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("CoreActionfreshRAL error :" + th.toString(), new Object[0]);
        }
    }

    public static void h() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", "request_deviceid");
            jSONObject.put("id", String.valueOf(jCurrentTimeMillis));
        } catch (JSONException unused) {
        }
        String string = jSONObject.toString();
        com.igexin.push.c.c.b bVar = new com.igexin.push.c.c.b();
        bVar.c = 128;
        bVar.b = (int) jCurrentTimeMillis;
        bVar.e = com.igexin.push.core.b.K;
        bVar.f = string;
        bVar.h = com.igexin.push.core.e.x;
        d.a.a.i.a("C-" + com.igexin.push.core.e.x, bVar, false);
        com.igexin.b.a.c.a.a("CoreAction|deviceidReq", new Object[0]);
    }

    public static void j() {
        String[] list;
        File file = new File("/sdcard/libs/");
        if (file.exists() && (list = file.list()) != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String str = simpleDateFormat.format(new Date());
            String str2 = com.igexin.push.core.e.d;
            if (str2 == null) {
                str2 = "unknowPacageName";
            }
            int length = list.length;
            for (int i = 0; i < length; i++) {
                int length2 = list[i].length();
                if (list[i].startsWith(str2) && list[i].endsWith(".log") && length2 > str2.length() + 14 && str2.equals(list[i].substring(0, length2 - 15))) {
                    try {
                        if (Math.abs((simpleDateFormat.parse(str).getTime() - simpleDateFormat.parse(list[i].substring(str2.length() + 1, length2 - 4)).getTime()) / 86400000) > 6) {
                            File file2 = new File("/sdcard/libs/" + list[i]);
                            if (file2.exists()) {
                                file2.delete();
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    public static void k() {
        com.igexin.push.f.c.e();
    }

    public static void l() {
        if (!com.igexin.push.core.e.U || com.igexin.push.core.e.V >= System.currentTimeMillis()) {
            return;
        }
        com.igexin.push.core.e.e.a().a(false);
    }

    public static void m() {
        if (!com.igexin.push.core.e.ag) {
            com.igexin.push.core.e.ag = com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) com.igexin.push.e.b.b.g(), false, true);
        }
        if (!com.igexin.push.core.e.ah) {
            com.igexin.push.core.e.ah = com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) com.igexin.push.e.b.e.g(), true, true);
        }
        if (com.igexin.push.core.e.ai) {
            return;
        }
        d.a.a.c();
    }

    private static boolean n() {
        return false;
    }

    @Override // com.igexin.push.core.a.a
    public final void a() {
    }

    @Override // com.igexin.push.d.b
    public final boolean a(com.igexin.push.c.c.c cVar) {
        if (cVar == null) {
            return false;
        }
        a aVar = b.get(cVar.m);
        if ((cVar instanceof h) || (cVar instanceof com.igexin.push.c.c.k) || (cVar instanceof com.igexin.push.c.c.m) || (cVar instanceof com.igexin.push.c.c.p) || (cVar instanceof com.igexin.push.c.c.f) || (cVar instanceof q)) {
            cVar.getClass().getName();
            com.igexin.b.a.c.a.a("CoreAction|receive : " + cVar.getClass().getName() + " resp ~~~~", new Object[0]);
            com.igexin.b.a.b.a.a.d.a().a(cVar.getClass().getName());
        }
        if ((cVar instanceof com.igexin.push.c.c.k) || (cVar instanceof com.igexin.push.c.c.m) || (cVar instanceof com.igexin.push.c.c.p)) {
            com.igexin.push.core.e.L = 0L;
            com.igexin.push.b.c.a().d().b();
        }
        if (aVar != null) {
            aVar.a(cVar);
        }
        com.igexin.push.e.b.b.g().h();
        return true;
    }

    @Override // com.igexin.push.core.a.a
    public final boolean a(Object obj) {
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        String str2;
        com.igexin.push.d.a aVar = d.a.a.i;
        if ((obj instanceof com.igexin.push.c.c.c) && aVar != null) {
            com.igexin.push.d.a.a((com.igexin.push.c.c.c) obj);
        } else if (obj instanceof com.igexin.push.c.b.b) {
            com.igexin.b.a.c.a.a("CoreAction|TcpExceptionNotify###", new Object[0]);
            com.igexin.push.b.c.a().d().c();
            com.igexin.push.b.a aVarD = com.igexin.push.b.c.a().d();
            com.igexin.push.core.k.a().a(k.a.c);
            aVarD.f();
            if (com.igexin.push.d.a.d()) {
                sb2 = new StringBuilder();
                sb2.append(com.igexin.push.d.a.a);
                str2 = "|sdkOn = false or pushOn = false, disconect|user";
            } else {
                sb2 = new StringBuilder();
                sb2.append(com.igexin.push.d.a.a);
                str2 = "|disconnect by network";
            }
            sb2.append(str2);
            com.igexin.b.a.c.a.a(sb2.toString(), new Object[0]);
            com.igexin.b.a.d.e<com.igexin.b.a.d.f> eVar = com.igexin.b.a.b.e.a().s;
            if (eVar != null) {
                eVar.a(com.igexin.b.a.b.a.a.f.class);
            }
            com.igexin.push.d.a.a(false);
        } else if (obj instanceof com.igexin.push.c.b.a) {
            com.igexin.b.a.c.a.a("CoreAction|TcpDisconnectSuccessNotify ###", new Object[0]);
            if (com.igexin.push.core.e.r) {
                com.igexin.push.core.e.r = false;
                com.igexin.b.a.c.a.a("CoreAction|broadcast online state = offline", new Object[0]);
                m.a().b();
            }
            com.igexin.push.c.a.c.b = -1;
            if (com.igexin.push.core.e.n) {
                com.igexin.b.a.c.a.a(com.igexin.push.d.a.a + "|isAppidWrong = true", new Object[0]);
                com.igexin.b.a.c.a.d.a().a("isAppidWrong = true");
            } else {
                if (!com.igexin.push.f.h.a()) {
                    sb = new StringBuilder();
                    sb.append(com.igexin.push.d.a.a);
                    str = "|so error ++++++++";
                } else if (com.igexin.push.core.e.aw) {
                    com.igexin.push.d.a.c();
                } else {
                    sb = new StringBuilder();
                    sb.append(com.igexin.push.d.a.a);
                    str = "|initSuccess = false";
                }
                sb.append(str);
                com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
            }
        }
        return false;
    }

    @Override // com.igexin.push.core.a.a
    public final void b() {
    }

    public final void i() {
        try {
            if ((System.currentTimeMillis() - com.igexin.push.core.e.N) - 86400000 > 0) {
                com.igexin.push.core.e.e.a().b(0);
                com.igexin.push.core.e.e.a().d(System.currentTimeMillis());
            }
            if (com.igexin.push.core.e.ax <= 5) {
                com.igexin.push.core.e.e.a().b(com.igexin.push.core.e.ax + 1);
                com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new com.igexin.push.e.d() { // from class: com.igexin.push.core.a.b.1
                    private boolean c = true;

                    @Override // com.igexin.push.e.d
                    public final void b() {
                        n.m();
                        try {
                            com.igexin.push.core.b.a aVar = new com.igexin.push.core.b.a();
                            long j = aVar.n;
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("model", aVar.a == null ? "" : aVar.a);
                            jSONObject.put("sim", aVar.b == null ? "" : aVar.b);
                            jSONObject.put("imei", aVar.c == null ? "" : aVar.c);
                            jSONObject.put("mac", aVar.d == null ? "" : aVar.d);
                            jSONObject.put("version", aVar.e == null ? "" : aVar.e);
                            jSONObject.put("channelid", aVar.f == null ? "" : aVar.f);
                            jSONObject.put("type", "ANDROID");
                            jSONObject.put(Constants.JumpUrlConstants.SRC_TYPE_APP, aVar.k == null ? "" : aVar.k);
                            StringBuilder sb = new StringBuilder("ANDROID-");
                            sb.append(aVar.g == null ? "" : aVar.g);
                            jSONObject.put("deviceid", sb.toString());
                            jSONObject.put("device_token", aVar.l == null ? "" : aVar.l);
                            jSONObject.put("brand", aVar.m == null ? "" : aVar.m);
                            jSONObject.put("system_version", aVar.j == null ? "" : aVar.j);
                            jSONObject.put("cell", aVar.i == null ? "" : aVar.i);
                            jSONObject.put("aid", n.g());
                            jSONObject.put("adid", n.h());
                            jSONObject.put("gtcid", TextUtils.isEmpty(aVar.o) ? "" : aVar.o);
                            jSONObject.put(PerformFeatureKey.KEY_OAID, com.igexin.push.core.e.e == null ? "" : com.igexin.push.core.e.e);
                            String name = p.a.a.a(com.igexin.push.core.e.i).getName();
                            if (!com.igexin.push.core.b.ak.equals(name)) {
                                jSONObject.put(o.a, name);
                            }
                            p unused = p.a.a;
                            jSONObject.put(o.c, p.c(com.igexin.push.core.e.i));
                            jSONObject.put("notification_enabled", com.igexin.push.f.c.b(com.igexin.push.core.e.i) ? 1 : 0);
                            jSONObject.put("installChannel", com.igexin.b.b.a.b(com.igexin.push.core.e.b, "").replaceAll("\\|", ""));
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("action", "addphoneinfo");
                            jSONObject2.put("id", String.valueOf(aVar.n));
                            jSONObject2.put(DBDefinition.SEGMENT_INFO, jSONObject);
                            String string = jSONObject2.toString();
                            com.igexin.b.a.c.a.a("addphoneinfo |  ".concat(String.valueOf(string)), new Object[0]);
                            com.igexin.push.core.e.d dVarA = com.igexin.push.core.e.d.a();
                            if (dVarA != null) {
                                dVarA.b(new i(j, string, (byte) 5, j));
                            }
                            com.igexin.push.c.c.b bVar = new com.igexin.push.c.c.b();
                            bVar.c = 128;
                            bVar.b = (int) j;
                            bVar.e = com.igexin.push.core.b.K;
                            bVar.f = string;
                            bVar.h = com.igexin.push.core.e.x;
                            d.a.a.i.a("C-" + com.igexin.push.core.e.x, bVar, false);
                            if (com.igexin.b.b.a.a(com.igexin.push.core.e.H, com.igexin.push.core.e.G)) {
                                return;
                            }
                            com.igexin.push.core.e.e.a().c(com.igexin.push.core.e.G);
                        } catch (Throwable unused2) {
                        }
                    }
                }, false, true);
            }
        } catch (Throwable unused) {
        }
    }
}
