package com.mobile.auth.g;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public interface b {

    public static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.g.b$b, reason: collision with other inner class name */
    public static class C0068b {
        private int h;
        private int i;
        private String a = "";
        private String b = "";
        private String c = "";
        private String d = "";
        private boolean e = false;
        private int f = -1;
        private int g = -1;
        private String j = "";
        private String k = "";
        private int l = -1;
        private int m = -1;

        public String a() {
            return this.c;
        }

        public String a(int i) {
            return this.f == i ? this.a : this.g == i ? this.b : "";
        }

        protected void a(String str) {
            if (str != null) {
                this.c = str;
            }
        }

        protected void a(boolean z) {
        }

        public String b() {
            return this.d;
        }

        protected void b(int i) {
            this.l = i;
        }

        protected void b(String str) {
            if (str != null) {
                this.d = str;
            }
        }

        protected void b(boolean z) {
            this.e = z;
        }

        public String c() {
            return this.k;
        }

        protected void c(int i) {
            this.f = i;
        }

        protected void c(String str) {
            if (str != null) {
                this.j = str;
            }
        }

        protected void d(int i) {
            this.g = i;
        }

        protected void d(String str) {
            this.k = str;
        }

        public boolean d() {
            return this.e;
        }

        public int e() {
            return this.l;
        }

        protected void e(int i) {
        }

        public int f() {
            return this.f;
        }

        protected void f(int i) {
        }

        public int g() {
            return this.g;
        }

        public String g(int i) {
            return this.f == i ? this.c : this.g == i ? this.d : "";
        }

        public int h() {
            if (!TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(this.c)) {
                return 2;
            }
            if (TextUtils.isEmpty(this.j) || TextUtils.isEmpty(this.k)) {
                return (TextUtils.isEmpty(this.c) && TextUtils.isEmpty(this.d) && TextUtils.isEmpty(this.j) && TextUtils.isEmpty(this.k)) ? 0 : 1;
            }
            return 2;
        }

        public String h(int i) {
            return this.f == i ? this.j : this.g == i ? this.k : "";
        }
    }

    void a(int i, JSONObject jSONObject);
}
