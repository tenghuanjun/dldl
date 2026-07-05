package com.igexin.push.core.d;

import android.content.Context;
import android.text.TextUtils;
import com.getui.gtc.api.GtcManager;
import com.getui.gtc.api.SdkInfo;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.d.e;
import com.igexin.push.core.n;
import com.igexin.push.core.o;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a implements d {
    private static final String a = "DycSdkConfig";
    private Map<String, String> b = new HashMap();

    private int a(String str, int i) {
        if (!b(str)) {
            return i;
        }
        try {
            return Integer.valueOf(a(str)).intValue();
        } catch (Exception unused) {
            return i;
        }
    }

    private Boolean a(String str, Boolean bool) {
        if (!b(str)) {
            return bool;
        }
        try {
            return Boolean.valueOf(a(str));
        } catch (Exception unused) {
            return bool;
        }
    }

    private Long a(String str, Long l) {
        if (!b(str)) {
            return l;
        }
        try {
            return Long.valueOf(a(str));
        } catch (Exception unused) {
            return l;
        }
    }

    private String a(String str) {
        return this.b.get(str);
    }

    private String a(String str, String str2) {
        if (!b(str)) {
            return str2;
        }
        try {
            return a(str);
        } catch (Exception unused) {
            return str2;
        }
    }

    private static Map<String, String> a(Context context, e eVar) {
        return com.getui.gtc.dyc.b.a.a(context, eVar.a);
    }

    private boolean b(String str) {
        Map<String, String> map = this.b;
        if (map == null) {
            return false;
        }
        return map.containsKey(str);
    }

    private static void c() {
        String str = com.igexin.push.core.e.a;
        String str2 = com.igexin.push.core.e.x;
        GtcManager.getInstance().loadSdk(new SdkInfo.Builder().appid(com.igexin.push.core.e.a).cid(com.igexin.push.core.e.x).moduleName(com.igexin.push.core.b.h).version("3.2.4.0").build());
    }

    private static /* synthetic */ void d() {
        String str = com.igexin.push.core.e.a;
        String str2 = com.igexin.push.core.e.x;
        GtcManager.getInstance().loadSdk(new SdkInfo.Builder().appid(com.igexin.push.core.e.a).cid(com.igexin.push.core.e.x).moduleName(com.igexin.push.core.b.h).version("3.2.4.0").build());
    }

    @Override // com.igexin.push.core.d.d
    public final Map<String, String> a() {
        SDKUrlConfig.getConfigServiceUrl();
        String str = com.igexin.push.core.e.a;
        e.a aVar = new e.a();
        aVar.a.b(com.igexin.push.core.b.h);
        aVar.a.a(SDKUrlConfig.getConfigServiceUrl());
        aVar.a.f("3.2.4.0");
        aVar.a.c(com.igexin.push.core.e.a);
        aVar.a.d(com.igexin.push.core.e.x);
        aVar.a.e(com.getui.gtc.dyc.b.b.a);
        aVar.a.g(86400000L);
        aVar.a.h(new f() { // from class: com.igexin.push.core.d.a.1
            @Override // com.igexin.push.core.d.f
            public final void a(String str2) {
                com.igexin.b.a.c.a.a("DycSdkConfig| get gtc config error ,message is : ".concat(String.valueOf(str2)), new Object[0]);
            }

            @Override // com.igexin.push.core.d.f
            public final void a(Map<String, String> map) {
                a.this.a(map);
                String str2 = com.igexin.push.core.e.a;
                String str3 = com.igexin.push.core.e.x;
                GtcManager.getInstance().loadSdk(new SdkInfo.Builder().appid(com.igexin.push.core.e.a).cid(com.igexin.push.core.e.x).moduleName(com.igexin.push.core.b.h).version("3.2.4.0").build());
            }
        }.b);
        Map<String, String> mapA = com.getui.gtc.dyc.b.a.a(com.igexin.push.core.e.i, new e(aVar, (byte) 0).a);
        a(mapA);
        return mapA;
    }

    @Override // com.igexin.push.core.d.d
    public final boolean a(Map<String, String> map) {
        if (map != null) {
            try {
                if (map.size() != 0) {
                    this.b = map;
                    com.igexin.b.a.c.a.a("DycSdkConfig|parse sdk config from server resp = " + this.b.toString(), new Object[0]);
                    com.igexin.push.core.e.e.a().c(System.currentTimeMillis());
                    com.igexin.push.config.d.h = a("sdk.uploadapplist.enable", Boolean.valueOf(com.igexin.push.config.d.h)).booleanValue();
                    com.igexin.push.config.d.j = a("sdk.feature.sendmessage.enable", Boolean.valueOf(com.igexin.push.config.d.j)).booleanValue();
                    com.igexin.push.config.d.g = a("sdk.readlocalcell.enable", Boolean.valueOf(com.igexin.push.config.d.g)).booleanValue();
                    com.igexin.push.config.d.f = a("sdk.domainbackup.enable", Boolean.valueOf(com.igexin.push.config.d.f)).booleanValue();
                    boolean zBooleanValue = a("sdk.feature.setsilenttime.enable", Boolean.valueOf(com.igexin.push.config.d.l)).booleanValue();
                    com.igexin.push.config.d.l = zBooleanValue;
                    if (!zBooleanValue && com.igexin.push.config.d.b != 0) {
                        n.a();
                        n.a(12, 0);
                    }
                    com.igexin.push.config.d.k = a("sdk.feature.settag.enable", Boolean.valueOf(com.igexin.push.config.d.k)).booleanValue();
                    com.igexin.push.config.d.m = a("sdk.feature.setheartbeatinterval.enable", Boolean.valueOf(com.igexin.push.config.d.m)).booleanValue();
                    com.igexin.push.config.d.n = a("sdk.feature.setsockettimeout.enable", Boolean.valueOf(com.igexin.push.config.d.n)).booleanValue();
                    boolean zBooleanValue2 = a("sdk.needlook.enable", Boolean.valueOf(com.igexin.push.config.d.t)).booleanValue();
                    if (zBooleanValue2 != com.igexin.push.config.d.t) {
                        com.igexin.push.config.d.t = zBooleanValue2;
                        c.a().a(c.e, Boolean.valueOf(com.igexin.push.config.d.t));
                    }
                    com.igexin.push.config.d.u = a("sdk.report.initialize.enable", Boolean.valueOf(com.igexin.push.config.d.u)).booleanValue();
                    com.igexin.push.config.d.o = a("sdk.wakeupsdk.enable", Boolean.valueOf(com.igexin.push.config.d.o)).booleanValue();
                    com.igexin.push.config.d.p = a("sdk.feature.feedback.enable", Boolean.valueOf(com.igexin.push.config.d.p)).booleanValue();
                    com.igexin.push.config.d.q = a("sdk.watchout.app", com.igexin.push.config.d.q);
                    com.igexin.push.config.d.r = a("sdk.watchout.service", com.igexin.push.config.d.r);
                    com.igexin.push.config.d.s = a("sdk.daemon.enable", Boolean.valueOf(com.igexin.push.config.d.s)).booleanValue();
                    com.igexin.push.config.d.B = a("sdk.polling.dis.cnt", com.igexin.push.config.d.B);
                    com.igexin.push.config.d.C = a("sdk.polling.login.interval", Long.valueOf(com.igexin.push.config.d.C)).longValue();
                    com.igexin.push.config.d.D = a("sdk.polling.exit.heartbeat.cnt", com.igexin.push.config.d.D);
                    com.igexin.push.config.d.R = a("sdk.ral.send.maxcnt", com.igexin.push.config.d.R);
                    com.igexin.push.config.d.E = a("sdk.httpdata.maxsize", com.igexin.push.config.d.E);
                    com.igexin.push.config.d.F = a("sdk.hide.righticon.blacklist", com.igexin.push.config.d.F);
                    String strA = a("sdk.taskid.blacklist", com.igexin.push.config.d.G);
                    com.igexin.push.config.d.G = strA;
                    if (TextUtils.isEmpty(strA)) {
                        com.igexin.push.config.d.G = com.igexin.push.a.i;
                    } else {
                        o.a();
                        o.d();
                    }
                    com.igexin.push.config.d.I = a("sdk.applink.feedback.enable", Boolean.valueOf(com.igexin.push.config.d.I)).booleanValue();
                    com.igexin.push.config.d.J = a("sdk.applink.domains", com.igexin.push.config.d.J);
                    if (TextUtils.isEmpty(com.igexin.push.config.d.K)) {
                        com.igexin.push.config.d.J = com.igexin.push.a.i;
                    }
                    String strA2 = a("sdk.del.alarm.brand", com.igexin.push.config.d.K);
                    com.igexin.push.config.d.K = strA2;
                    if (TextUtils.isEmpty(strA2)) {
                        com.igexin.push.config.d.K = com.igexin.push.a.i;
                    }
                    com.igexin.push.config.d.P = a("sdk.vivopush.enable", Boolean.valueOf(com.igexin.push.config.d.P)).booleanValue();
                    com.igexin.push.config.d.S = a("sdk.upload.gzip.limit", Long.valueOf(com.igexin.push.config.d.S)).longValue();
                    com.igexin.push.config.d.Q = a("sdk.multiPuh.stoplist", com.igexin.push.config.d.Q);
                    com.igexin.push.config.d.T = a("sdk.startservice.limit", com.igexin.push.config.d.T);
                    com.igexin.push.config.d.H = a("sdk.miui.wakeup.enable", Boolean.valueOf(com.igexin.push.config.d.H)).booleanValue();
                    com.igexin.push.config.d.U = a("sdk.guard.brandsdkrombl", com.igexin.push.config.d.U);
                    com.igexin.push.config.d.V = a("sdk.query.applist", com.igexin.push.config.d.V);
                    b();
                    return true;
                }
            } catch (Throwable th) {
                com.igexin.b.a.c.a.a("DycSdkConfig|" + th.toString(), new Object[0]);
                return true;
            }
        }
        return false;
    }

    @Override // com.igexin.push.core.d.d
    public final boolean b() {
        com.igexin.b.a.c.a.a("DycSdkConfig| parse config success", new Object[0]);
        com.igexin.push.config.a.a().d();
        return true;
    }
}
