package cn.thinkingdata.android.encrypt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class d implements a {
    byte[] a;
    String b;

    @Override // cn.thinkingdata.android.encrypt.a
    public String a() {
        return "AES";
    }

    @Override // cn.thinkingdata.android.encrypt.a
    public String a(String str) {
        return c.a(this.a, str);
    }

    @Override // cn.thinkingdata.android.encrypt.a
    public String b() {
        return "RSA";
    }

    @Override // cn.thinkingdata.android.encrypt.a
    public String b(String str) {
        try {
            byte[] bArrA = c.a();
            this.a = bArrA;
            String strA = c.a(str, bArrA);
            this.b = strA;
            return strA;
        } catch (Exception unused) {
            return null;
        }
    }
}
