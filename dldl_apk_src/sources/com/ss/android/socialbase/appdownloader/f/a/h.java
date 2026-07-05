package com.ss.android.socialbase.appdownloader.f.a;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class h extends Exception {
    protected Throwable a;
    protected int b;
    protected int c;

    public h(String str, g gVar, Throwable th) {
        String str2;
        String str3;
        StringBuilder sb = new StringBuilder();
        String str4 = "";
        if (str == null) {
            str2 = "";
        } else {
            str2 = str + " ";
        }
        sb.append(str2);
        if (gVar == null) {
            str3 = "";
        } else {
            str3 = "(position:" + gVar.d() + ") ";
        }
        sb.append(str3);
        if (th != null) {
            str4 = "caused by: " + th;
        }
        sb.append(str4);
        super(sb.toString());
        this.b = -1;
        this.c = -1;
        if (gVar != null) {
            this.b = gVar.c();
            this.c = gVar.f();
        }
        this.a = th;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        if (this.a == null) {
            super.printStackTrace();
            return;
        }
        synchronized (System.err) {
            System.err.println(super.getMessage() + "; nested exception is:");
            this.a.printStackTrace();
        }
    }
}
