package cn.thinkingdata.android.r;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class d {
    private e a;
    private String b;
    private boolean c;

    public d() {
    }

    public d(e eVar, String str, String str2, boolean z) {
        this.a = eVar;
        this.b = str2;
        this.c = z;
    }

    public static d a(e eVar, String str, String str2, boolean z) {
        return new d(eVar, str, str2, z);
    }

    public String a() {
        return this.b;
    }

    public void a(e eVar) {
        this.a = eVar;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public e b() {
        return this.a;
    }

    public boolean c() {
        return this.c;
    }
}
