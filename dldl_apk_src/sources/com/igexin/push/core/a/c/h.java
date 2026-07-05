package com.igexin.push.core.a.c;

import android.os.Bundle;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class h {
    public static final String a = "ReportCidAction";
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 51688;
    private static final int f = 180000;
    private static h i;
    private Long g;
    private ServerSocket h;

    private h() {
    }

    public static h a() {
        if (i == null) {
            i = new h();
        }
        return i;
    }

    public final void a(boolean z) {
        int i2;
        JSONArray jSONArray;
        if (z) {
            try {
                if (com.igexin.push.core.e.m && com.igexin.push.core.e.p) {
                    try {
                        if (this.h == null) {
                            this.h = new ServerSocket(e);
                        }
                    } catch (Exception unused) {
                        com.igexin.b.a.c.a.a("ReportCidAction|port 51688 has occupy by others", new Object[0]);
                    }
                    if (this.h != null) {
                        if (com.igexin.push.core.e.aD < 180000) {
                            com.igexin.push.core.e.aD = 180000L;
                        }
                        if (com.igexin.push.core.e.aC < 180000) {
                            com.igexin.push.core.e.aC = 180000L;
                        }
                        if (this.g == null) {
                            long jCurrentTimeMillis = System.currentTimeMillis() - com.igexin.push.core.e.aE;
                            if (jCurrentTimeMillis < com.igexin.push.core.e.aD) {
                                com.igexin.b.a.c.a.a("ReportCidAction|lastReportInterval < reportCidRestartThreshold not report", new Object[0]);
                                return;
                            }
                            i2 = jCurrentTimeMillis < com.igexin.push.core.e.aC ? 2 : 0;
                        } else {
                            if (System.currentTimeMillis() - this.g.longValue() < com.igexin.push.core.e.aC) {
                                com.igexin.b.a.c.a.a("ReportCidAction|offline time < reportCidOfflineThreshold not report", new Object[0]);
                                return;
                            }
                            i2 = 1;
                        }
                        List<JSONObject> listB = com.igexin.push.f.k.b(com.igexin.push.core.b.L + "/libs");
                        if (listB == null) {
                            jSONArray = new JSONArray();
                        } else if (listB.size() <= 0) {
                            return;
                        } else {
                            jSONArray = new JSONArray((Collection) listB);
                        }
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("appinfo", jSONArray);
                        jSONObject.put("deviceid", "ANDROID-" + com.igexin.push.core.e.F);
                        jSONObject.put("type", i2);
                        jSONObject.put("pkg", com.igexin.push.core.e.i.getPackageName());
                        Bundle bundle = new Bundle();
                        bundle.putString("action", "sendMessage");
                        StringBuilder sb = new StringBuilder(com.igexin.push.core.b.W);
                        sb.append(com.igexin.b.b.a.a(com.igexin.push.core.e.x + System.currentTimeMillis()));
                        bundle.putString("taskid", sb.toString());
                        bundle.putByteArray("extraData", jSONObject.toString().getBytes());
                        com.igexin.push.core.a.b.d();
                        com.igexin.push.core.a.b.a(bundle);
                        com.igexin.push.config.a.a().a(System.currentTimeMillis());
                    }
                }
            } catch (Throwable th) {
                com.igexin.b.a.c.a.a("ReportCidAction|do report exception:" + th.toString(), new Object[0]);
                return;
            }
        }
        if (z) {
            return;
        }
        this.g = Long.valueOf(System.currentTimeMillis());
    }
}
