package com.ss.android.download.api.model;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class d {
    private String a;
    private String b;

    public d(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
    }

    public String a() {
        return this.a;
    }

    public static class a {
        private String a;
        private String b;

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public d a() {
            return new d(this);
        }
    }
}
