package com.ss.android.socialbase.appdownloader.f.a;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
class a implements i {
    private d b;
    private f d;
    private int[] e;
    private boolean g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int[] l;
    private int m;
    private int n;
    private int o;
    private boolean c = false;
    private C0104a f = new C0104a();

    @Override // com.ss.android.socialbase.appdownloader.f.a.g
    public int f() {
        return -1;
    }

    public a() {
        g();
    }

    public void a(InputStream inputStream) {
        a();
        if (inputStream != null) {
            this.b = new d(inputStream, false);
        }
    }

    public void a() {
        if (this.c) {
            this.c = false;
            this.b.a();
            this.b = null;
            this.d = null;
            this.e = null;
            this.f.a();
            g();
        }
    }

    public int b() throws IOException, h {
        if (this.b == null) {
            throw new h("Parser is not opened.", this, (Throwable) null);
        }
        try {
            h();
            return this.h;
        } catch (IOException e) {
            a();
            throw e;
        }
    }

    @Override // com.ss.android.socialbase.appdownloader.f.a.g
    public int c() {
        return this.i;
    }

    @Override // com.ss.android.socialbase.appdownloader.f.a.g
    public String d() {
        return "XML line #" + c();
    }

    public int e() {
        if (this.h != 2) {
            return -1;
        }
        return this.l.length / 5;
    }

    public String a(int i) {
        int i2 = this.l[e(i) + 1];
        return i2 == -1 ? "" : this.d.a(i2);
    }

    public int b(int i) {
        return this.l[e(i) + 3];
    }

    public int c(int i) {
        return this.l[e(i) + 4];
    }

    public String d(int i) {
        int iE = e(i);
        int[] iArr = this.l;
        if (iArr[iE + 3] == 3) {
            return this.d.a(iArr[iE + 2]);
        }
        int i2 = iArr[iE + 4];
        return "";
    }

    private final int e(int i) {
        if (this.h != 2) {
            throw new IndexOutOfBoundsException("Current event is not START_TAG.");
        }
        int i2 = i * 5;
        if (i2 < this.l.length) {
            return i2;
        }
        throw new IndexOutOfBoundsException("Invalid attribute index (" + i + ").");
    }

    private final void g() {
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.k = -1;
        this.l = null;
        this.m = -1;
        this.n = -1;
        this.o = -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0095, code lost:
    
        throw new java.io.IOException("Invalid resource ids size (" + r2 + ").");
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x018e, code lost:
    
        throw new java.io.IOException("Invalid chunk type (" + r5 + ").");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void h() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.f.a.a.h():void");
    }

    /* JADX INFO: renamed from: com.ss.android.socialbase.appdownloader.f.a.a$a, reason: collision with other inner class name */
    private static final class C0104a {
        private int[] a = new int[32];
        private int b;
        private int c;

        public final void a() {
            this.b = 0;
            this.c = 0;
        }

        public final int b() {
            int i = this.b;
            if (i == 0) {
                return 0;
            }
            return this.a[i - 1];
        }

        public final void a(int i, int i2) {
            if (this.c == 0) {
                e();
            }
            a(2);
            int i3 = this.b;
            int i4 = i3 - 1;
            int[] iArr = this.a;
            int i5 = iArr[i4];
            int i6 = (i4 - 1) - (i5 * 2);
            int i7 = i5 + 1;
            iArr[i6] = i7;
            iArr[i4] = i;
            iArr[i4 + 1] = i2;
            iArr[i4 + 2] = i7;
            this.b = i3 + 2;
        }

        public final boolean c() {
            int i;
            int[] iArr;
            int i2;
            int i3 = this.b;
            if (i3 == 0 || (i2 = (iArr = this.a)[i3 - 1]) == 0) {
                return false;
            }
            int i4 = i2 - 1;
            int i5 = i - 2;
            iArr[i5] = i4;
            iArr[i5 - ((i4 * 2) + 1)] = i4;
            this.b = i3 - 2;
            return true;
        }

        public final int d() {
            return this.c;
        }

        public final void e() {
            a(2);
            int i = this.b;
            int[] iArr = this.a;
            iArr[i] = 0;
            iArr[i + 1] = 0;
            this.b = i + 2;
            this.c++;
        }

        public final void f() {
            int i = this.b;
            if (i != 0) {
                int i2 = i - 1;
                int i3 = this.a[i2] * 2;
                if ((i2 - 1) - i3 != 0) {
                    this.b = i - (i3 + 2);
                    this.c--;
                }
            }
        }

        private void a(int i) {
            int[] iArr = this.a;
            int length = iArr.length;
            int i2 = this.b;
            int i3 = length - i2;
            if (i3 <= i) {
                int[] iArr2 = new int[(iArr.length + i3) * 2];
                System.arraycopy(iArr, 0, iArr2, 0, i2);
                this.a = iArr2;
            }
        }
    }
}
