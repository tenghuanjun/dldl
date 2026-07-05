package com.ss.android.download.api.model;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;

    public a(C0086a c0086a) {
        this.b = "";
        this.a = c0086a.a;
        this.b = c0086a.b;
        this.c = c0086a.c;
        this.d = c0086a.d;
        this.e = c0086a.e;
    }

    /* JADX INFO: renamed from: com.ss.android.download.api.model.a$a, reason: collision with other inner class name */
    public static class C0086a {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;

        public C0086a a(String str) {
            this.a = str;
            return this;
        }

        public C0086a b(String str) {
            this.b = str;
            return this;
        }

        public C0086a c(String str) {
            this.d = str;
            return this;
        }

        public C0086a d(String str) {
            this.e = str;
            return this;
        }

        public a a() {
            return new a(this);
        }
    }
}
