package com.igexin.push.core;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Pair;
import com.igexin.push.core.d;
import com.igexin.push.core.p;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class l {
    public static final int a = -1;
    public static final int b = 0;
    public static final int c = 1;
    private static final String d = "LoginInteractor";
    private static l e;

    public static l a() {
        if (e == null) {
            e = new l();
        }
        return e;
    }

    private static void a(List<com.igexin.push.c.c.j> list) {
        if (p.a.a.c == null) {
            return;
        }
        int iIntValue = ((Integer) p.a.a.c.first).intValue();
        String strValueOf = String.valueOf(iIntValue);
        if (iIntValue == 1) {
            strValueOf = strValueOf + "#" + ((String) p.a.a.c.second);
        }
        com.igexin.push.c.c.j jVar = new com.igexin.push.c.c.j();
        jVar.a = (byte) 5;
        jVar.b = strValueOf;
        list.add(jVar);
    }

    public static void b() {
        com.igexin.b.a.c.a.d.a().a("[LoginInteractor] Start login appid = " + e.a);
        if (e.q) {
            e.q = false;
            e.R = System.currentTimeMillis() + (((long) Math.abs(new Random().nextInt() % 24)) * 3600000);
        }
        com.igexin.push.b.c.a().d().d();
        if (e.w == 0) {
            com.igexin.b.a.c.a.a("registerReq #####", new Object[0]);
            com.igexin.push.c.c.d dVar = new com.igexin.push.c.c.d(e.A, e.B, e.I, e.a);
            String str = e.A;
            String str2 = e.B;
            String str3 = e.I;
            com.igexin.push.d.a aVar = d.a.a.i;
            StringBuilder sb = new StringBuilder("R-");
            sb.append(e.I);
            com.igexin.b.a.c.a.a("registerReq|" + (aVar.a(sb.toString(), dVar, true) >= 0) + "|" + e.I, new Object[0]);
            return;
        }
        long j = e.w;
        com.igexin.push.c.c.i iVarC = c();
        com.igexin.b.a.c.a.a("loginReqBefore|" + iVarC.b, new Object[0]);
        com.igexin.push.d.a aVar2 = d.a.a.i;
        StringBuilder sb2 = new StringBuilder("S-");
        sb2.append(e.w);
        if (aVar2.a(sb2.toString(), iVarC, true) >= 0) {
            String str4 = e.x;
            com.igexin.b.a.c.a.a("LoginInteractor|loginReq|" + e.x, new Object[0]);
        }
    }

    public static com.igexin.push.c.c.i c() {
        Pair<String, String> pairB;
        NetworkInfo activeNetworkInfo;
        com.igexin.push.c.c.i iVar = new com.igexin.push.c.c.i();
        iVar.b = e.w;
        iVar.c = (byte) 0;
        iVar.d = 65280;
        iVar.e = e.a;
        try {
            boolean zA = com.igexin.push.f.c.a();
            com.igexin.b.a.c.a.a(d, "getLoginCommand checkSafeStatus = ".concat(String.valueOf(zA)));
            int type = -1;
            if (zA) {
                ArrayList arrayList = new ArrayList();
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager) e.i.getSystemService("connectivity");
                    if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
                        type = activeNetworkInfo.getType();
                        com.igexin.push.c.c.j jVar = new com.igexin.push.c.c.j();
                        jVar.a = (byte) 2;
                        jVar.b = String.valueOf(type);
                        arrayList.add(jVar);
                    }
                } catch (Throwable unused) {
                }
                if (type == 1 && (pairB = com.igexin.push.f.n.b()) != null) {
                    String str = (String) pairB.first;
                    String str2 = (String) pairB.second;
                    if (str != null) {
                        com.igexin.push.c.c.j jVar2 = new com.igexin.push.c.c.j();
                        jVar2.a = (byte) 1;
                        jVar2.b = str;
                        arrayList.add(jVar2);
                    }
                    if (str2 != null) {
                        com.igexin.push.c.c.j jVar3 = new com.igexin.push.c.c.j();
                        jVar3.a = (byte) 4;
                        jVar3.b = str2;
                        arrayList.add(jVar3);
                    }
                }
                if (p.a.a.c != null) {
                    int iIntValue = ((Integer) p.a.a.c.first).intValue();
                    String strValueOf = String.valueOf(iIntValue);
                    if (iIntValue == 1) {
                        strValueOf = strValueOf + "#" + ((String) p.a.a.c.second);
                    }
                    com.igexin.push.c.c.j jVar4 = new com.igexin.push.c.c.j();
                    jVar4.a = (byte) 5;
                    jVar4.b = strValueOf;
                    arrayList.add(jVar4);
                }
                if (!arrayList.isEmpty()) {
                    iVar.f = arrayList;
                }
            }
        } catch (Throwable unused2) {
        }
        return iVar;
    }

    private static int d() {
        if (!e.p || com.igexin.push.f.c.a(System.currentTimeMillis()) || !com.igexin.push.f.c.b()) {
            com.igexin.b.a.c.a.a("LoginInteractor|keyNegotiate stop ++++++++++", new Object[0]);
            return -1;
        }
        com.igexin.push.c.c.g gVar = new com.igexin.push.c.c.g();
        gVar.b = e.a;
        int iA = d.a.a.i.a("K-", gVar, true);
        com.igexin.b.a.c.a.a("LoginInteractor|keyNegotiate result=".concat(String.valueOf(iA)), new Object[0]);
        return iA < 0 ? 0 : 1;
    }
}
