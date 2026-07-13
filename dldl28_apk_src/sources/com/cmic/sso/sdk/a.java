package com.cmic.sso.sdk;

import com.mobile.auth.e.a;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<String, Object> f404a;

    public a(int i) {
        this.f404a = new ConcurrentHashMap<>(i);
    }

    public com.cmic.sso.sdk.d.b a() {
        com.cmic.sso.sdk.d.b bVar = (com.cmic.sso.sdk.d.b) this.f404a.get("logBean");
        return bVar != null ? bVar : new com.cmic.sso.sdk.d.b();
    }

    public void a(com.cmic.sso.sdk.d.b bVar) {
        if (bVar != null) {
            this.f404a.put("logBean", bVar);
        }
    }

    public void a(com.mobile.auth.e.a aVar) {
        if (aVar != null) {
            this.f404a.put("current_config", aVar);
        }
    }

    public void a(String str, int i) {
        if (str != null) {
            this.f404a.put(str, Integer.valueOf(i));
        }
    }

    public void a(String str, long j) {
        if (str != null) {
            this.f404a.put(str, Long.valueOf(j));
        }
    }

    public void a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        this.f404a.put(str, str2);
    }

    public void a(String str, boolean z) {
        if (str != null) {
            this.f404a.put(str, Boolean.valueOf(z));
        }
    }

    public void a(String str, byte[] bArr) {
        if (str == null || bArr == null) {
            return;
        }
        this.f404a.put(str, bArr);
    }

    public byte[] a(String str) {
        if (str != null) {
            return (byte[]) this.f404a.get(str);
        }
        return null;
    }

    public int b(String str, int i) {
        return (str == null || !this.f404a.containsKey(str)) ? i : ((Integer) this.f404a.get(str)).intValue();
    }

    public long b(String str, long j) {
        return (str == null || !this.f404a.containsKey(str)) ? j : ((Long) this.f404a.get(str)).longValue();
    }

    public com.mobile.auth.e.a b() {
        com.mobile.auth.e.a aVar = (com.mobile.auth.e.a) this.f404a.get("current_config");
        if (aVar != null) {
            return aVar;
        }
        com.mobile.auth.m.c.a("UmcConfigBean为空", "请核查");
        return new a.C0421a().a();
    }

    public String b(String str) {
        return b(str, "");
    }

    public String b(String str, String str2) {
        return (str == null || !this.f404a.containsKey(str)) ? str2 : (String) this.f404a.get(str);
    }

    public boolean b(String str, boolean z) {
        return (str == null || !this.f404a.containsKey(str)) ? z : ((Boolean) this.f404a.get(str)).booleanValue();
    }

    public int c(String str) {
        return b(str, 0);
    }
}
