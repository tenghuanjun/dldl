package com.tencent.bugly.proguard;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class ak extends k implements Cloneable {
    public String a = "";
    public String b = "";
    public String c = "";
    private String e = "";
    public String d = "";

    @Override // com.tencent.bugly.proguard.k
    public final void a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.k
    public final void a(j jVar) {
        jVar.a(this.a, 0);
        String str = this.b;
        if (str != null) {
            jVar.a(str, 1);
        }
        String str2 = this.c;
        if (str2 != null) {
            jVar.a(str2, 2);
        }
        String str3 = this.e;
        if (str3 != null) {
            jVar.a(str3, 3);
        }
        String str4 = this.d;
        if (str4 != null) {
            jVar.a(str4, 4);
        }
    }

    @Override // com.tencent.bugly.proguard.k
    public final void a(i iVar) {
        this.a = iVar.b(0, true);
        this.b = iVar.b(1, false);
        this.c = iVar.b(2, false);
        this.e = iVar.b(3, false);
        this.d = iVar.b(4, false);
    }
}
