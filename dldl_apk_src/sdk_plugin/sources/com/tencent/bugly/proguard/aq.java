package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class aq extends k {
    private static byte[] i = {0};
    private static Map<String, String> j;
    public byte a = 0;
    public int b = 0;
    public byte[] c = null;
    public String d = "";
    public long e = 0;
    private String h = "";
    public String f = "";
    public Map<String, String> g = null;

    @Override // com.tencent.bugly.proguard.k
    public final void a(j jVar) {
        jVar.a(this.a, 0);
        jVar.a(this.b, 1);
        byte[] bArr = this.c;
        if (bArr != null) {
            jVar.a(bArr, 2);
        }
        String str = this.d;
        if (str != null) {
            jVar.a(str, 3);
        }
        jVar.a(this.e, 4);
        String str2 = this.h;
        if (str2 != null) {
            jVar.a(str2, 5);
        }
        String str3 = this.f;
        if (str3 != null) {
            jVar.a(str3, 6);
        }
        Map<String, String> map = this.g;
        if (map != null) {
            jVar.a((Map) map, 7);
        }
    }

    static {
        HashMap map = new HashMap();
        j = map;
        map.put("", "");
    }

    @Override // com.tencent.bugly.proguard.k
    public final void a(i iVar) {
        this.a = iVar.a(this.a, 0, true);
        this.b = iVar.a(this.b, 1, true);
        this.c = iVar.c(2, false);
        this.d = iVar.b(3, false);
        this.e = iVar.a(this.e, 4, false);
        this.h = iVar.b(5, false);
        this.f = iVar.b(6, false);
        this.g = (Map) iVar.a(j, 7, false);
    }
}
