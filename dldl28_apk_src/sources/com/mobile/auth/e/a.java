package com.mobile.auth.e;

/* JADX INFO: loaded from: classes3.dex */
public class a implements Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f525a;
    private String b;
    private String c;
    private String d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private int k;
    private int l;

    /* JADX INFO: renamed from: com.mobile.auth.e.a$a, reason: collision with other inner class name */
    public static class C0421a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final a f526a = new a();

        C0421a a(int i) {
            this.f526a.k = i;
            return this;
        }

        C0421a a(String str) {
            this.f526a.f525a = str;
            return this;
        }

        C0421a a(boolean z) {
            this.f526a.e = z;
            return this;
        }

        public a a() {
            return this.f526a;
        }

        C0421a b(int i) {
            this.f526a.l = i;
            return this;
        }

        C0421a b(String str) {
            this.f526a.b = str;
            return this;
        }

        C0421a b(boolean z) {
            this.f526a.f = z;
            return this;
        }

        C0421a c(String str) {
            this.f526a.c = str;
            return this;
        }

        C0421a c(boolean z) {
            this.f526a.g = z;
            return this;
        }

        C0421a d(String str) {
            this.f526a.d = str;
            return this;
        }

        C0421a d(boolean z) {
            this.f526a.h = z;
            return this;
        }

        C0421a e(boolean z) {
            this.f526a.i = z;
            return this;
        }

        C0421a f(boolean z) {
            this.f526a.j = z;
            return this;
        }
    }

    private a() {
        this.f525a = "rcs.cmpassport.com";
        this.b = "rcs.cmpassport.com";
        this.c = "config2.cmpassport.com";
        this.d = "log2.cmpassport.com:9443";
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = 3;
        this.l = 1;
    }

    public String a() {
        return this.f525a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public boolean f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public boolean i() {
        return this.i;
    }

    public boolean j() {
        return this.j;
    }

    public int k() {
        return this.k;
    }

    public int l() {
        return this.l;
    }

    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public a clone() throws CloneNotSupportedException {
        return (a) super.clone();
    }
}
