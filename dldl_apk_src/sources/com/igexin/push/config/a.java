package com.igexin.push.config;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a implements com.igexin.push.core.e.a {
    private static final int A = 26;
    private static final int B = 28;
    private static final int C = 40;
    private static final int D = 41;
    private static final int E = 42;
    private static final int F = 43;
    private static final int G = 45;
    private static final int H = 51;
    private static final int I = 55;
    private static final int J = 56;
    private static final int K = 58;
    private static final int L = 46;
    private static final int M = 47;
    private static final int N = 48;
    private static final int O = 49;
    private static final int P = 52;
    private static final int Q = 53;
    private static final int R = 60;
    private static final int S = 61;
    private static final int T = 62;
    private static final int U = 69;
    private static final int V = 70;
    private static final int W = 71;
    private static final int X = 72;
    private static final int Y = 73;
    private static final int Z = 74;
    public static final String a = "com.igexin.push.config.a";
    private static final int aa = 75;
    private static final int ab = 76;
    private static volatile a ac = null;
    public static final int b = 63;
    public static final int c = 65;
    public static final int d = 67;
    public static final int e = 68;
    private static final int f = 1;
    private static final int g = 2;
    private static final int h = 3;
    private static final int i = 4;
    private static final int j = 5;
    private static final int k = 6;
    private static final int l = 7;
    private static final int m = 8;
    private static final int n = 9;
    private static final int o = 12;
    private static final int p = 13;
    private static final int q = 14;
    private static final int r = 15;
    private static final int s = 16;
    private static final int t = 18;
    private static final int u = 19;
    private static final int v = 21;
    private static final int w = 22;
    private static final int x = 23;
    private static final int y = 24;
    private static final int z = 25;

    /* JADX INFO: renamed from: com.igexin.push.config.a$1, reason: invalid class name */
    public class AnonymousClass1 extends com.igexin.push.a.d {
        final /* synthetic */ String a;

        public AnonymousClass1(String str) {
            this.a = str;
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            a.b(this.d, 63, this.a);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.config.a$3, reason: invalid class name */
    public class AnonymousClass3 extends com.igexin.push.a.d {
        public AnonymousClass3() {
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            a.b(this.d, 15, String.valueOf(d.d));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.config.a$4, reason: invalid class name */
    public class AnonymousClass4 extends com.igexin.push.a.d {
        public AnonymousClass4() {
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            a.b(this.d, 16, String.valueOf(d.e));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.config.a$5, reason: invalid class name */
    public class AnonymousClass5 extends com.igexin.push.a.d {
        public AnonymousClass5() {
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            a.b(this.d, 3, String.valueOf(d.c));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.config.a$7, reason: invalid class name */
    public class AnonymousClass7 extends com.igexin.push.a.d {
        final /* synthetic */ String a;

        public AnonymousClass7(String str) {
            this.a = str;
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            a.a(this.d, 26, com.igexin.b.a.a.a.b(this.a.getBytes(), com.igexin.push.core.e.J));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.config.a$8, reason: invalid class name */
    public class AnonymousClass8 extends com.igexin.push.a.d {
        final /* synthetic */ String a;

        public AnonymousClass8(String str) {
            this.a = str;
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            a.a(this.d, 24, com.igexin.b.a.a.a.b(this.a.getBytes(), com.igexin.push.core.e.J));
        }
    }

    private a() {
    }

    public static a a() {
        if (ac == null) {
            synchronized (a.class) {
                if (ac == null) {
                    ac = new a();
                }
            }
        }
        return ac;
    }

    private static void a(SQLiteDatabase sQLiteDatabase, int i2) {
        sQLiteDatabase.delete(com.igexin.push.core.b.X, "id = ?", new String[]{String.valueOf(i2)});
    }

    static /* synthetic */ void a(SQLiteDatabase sQLiteDatabase, int i2, byte[] bArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(i2));
        contentValues.put("value", bArr);
        sQLiteDatabase.replace(com.igexin.push.core.b.X, null, contentValues);
    }

    private boolean a(String str) {
        return com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass1(str), false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(SQLiteDatabase sQLiteDatabase, int i2, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(i2));
        contentValues.put("value", str);
        sQLiteDatabase.replace(com.igexin.push.core.b.X, null, contentValues);
    }

    private static void b(SQLiteDatabase sQLiteDatabase, int i2, byte[] bArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(i2));
        contentValues.put("value", bArr);
        sQLiteDatabase.replace(com.igexin.push.core.b.X, null, contentValues);
    }

    private void b(String str) {
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass7(str), true, false);
    }

    private void c(String str) {
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass8(str), true, false);
    }

    private void e() {
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass3(), false, true);
    }

    private void f() {
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass4(), false, true);
    }

    private void g() {
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass5(), false, true);
    }

    public final void a(final long j2) {
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new com.igexin.push.a.d() { // from class: com.igexin.push.config.a.9
            @Override // com.igexin.push.a.d
            public final void a_() {
                com.igexin.push.core.e.aE = j2;
                a.b(this.d, 65, String.valueOf(j2));
            }
        }, true, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:175:0x0415 A[PHI: r12
  0x0415: PHI (r12v5 android.database.Cursor) = (r12v3 android.database.Cursor), (r12v13 android.database.Cursor) binds: [B:174:0x0413, B:166:0x0407] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.igexin.push.core.e.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(android.database.sqlite.SQLiteDatabase r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.config.a.a(android.database.sqlite.SQLiteDatabase):void");
    }

    @Override // com.igexin.push.core.e.a
    public final void b() {
    }

    @Override // com.igexin.push.core.e.a
    public final void b(SQLiteDatabase sQLiteDatabase) {
        b(sQLiteDatabase, 1, String.valueOf(d.a));
        b(sQLiteDatabase, 2, String.valueOf(d.b));
        b(sQLiteDatabase, 3, String.valueOf(d.c));
        b(sQLiteDatabase, 4, String.valueOf(d.f));
        b(sQLiteDatabase, 5, String.valueOf(d.g));
        b(sQLiteDatabase, 6, String.valueOf(d.h));
        b(sQLiteDatabase, 7, String.valueOf(d.j));
        b(sQLiteDatabase, 8, String.valueOf(d.k));
        b(sQLiteDatabase, 9, String.valueOf(d.l));
        b(sQLiteDatabase, 13, String.valueOf(d.m));
        b(sQLiteDatabase, 14, String.valueOf(d.n));
        b(sQLiteDatabase, 15, String.valueOf(d.d));
        b(sQLiteDatabase, 3, String.valueOf(d.c));
        b(sQLiteDatabase, 18, String.valueOf(d.o));
        b(sQLiteDatabase, 19, String.valueOf(d.p));
        b(sQLiteDatabase, 25, String.valueOf(d.u));
        b(sQLiteDatabase, 76, String.valueOf(d.R));
    }

    public final void c() {
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new com.igexin.push.a.d() { // from class: com.igexin.push.config.a.2
            @Override // com.igexin.push.a.d
            public final void a_() {
                a.b(this.d, 1, String.valueOf(d.a));
                a.b(this.d, 2, String.valueOf(d.b));
            }
        }, false, true);
    }

    public final void d() {
        com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new com.igexin.push.a.d() { // from class: com.igexin.push.config.a.6
            @Override // com.igexin.push.a.d
            public final void a_() {
                a.b(this.d, 4, String.valueOf(d.f));
                a.b(this.d, 5, String.valueOf(d.g));
                a.b(this.d, 6, String.valueOf(d.h));
                a.b(this.d, 7, String.valueOf(d.j));
                a.b(this.d, 8, String.valueOf(d.k));
                a.b(this.d, 9, String.valueOf(d.l));
                a.b(this.d, 13, String.valueOf(d.m));
                a.b(this.d, 14, String.valueOf(d.n));
                a.b(this.d, 15, String.valueOf(d.d));
                a.b(this.d, 3, String.valueOf(d.c));
                a.b(this.d, 18, String.valueOf(d.o));
                a.b(this.d, 19, String.valueOf(d.p));
                a.a(this.d, 58, com.igexin.b.a.a.a.b(d.K.getBytes(), com.igexin.push.core.e.J));
                a.a(this.d, 21, com.igexin.b.a.a.a.b(d.q.getBytes(), com.igexin.push.core.e.J));
                a.a(this.d, 22, com.igexin.b.a.a.a.b(d.r.getBytes(), com.igexin.push.core.e.J));
                a.b(this.d, 25, String.valueOf(d.u));
                a.b(this.d, 23, String.valueOf(d.s));
                a.b(this.d, 28, d.A);
                a.b(this.d, 40, String.valueOf(d.B));
                a.b(this.d, 41, String.valueOf(d.C));
                a.b(this.d, 42, String.valueOf(d.D));
                a.b(this.d, 43, String.valueOf(d.E));
                a.a(this.d, 45, com.igexin.b.a.a.a.b(d.F.getBytes(), com.igexin.push.core.e.J));
                a.a(this.d, 51, com.igexin.b.a.a.a.b(d.G.getBytes(), com.igexin.push.core.e.J));
                a.b(this.d, 55, String.valueOf(d.I));
                a.a(this.d, 56, com.igexin.b.a.a.a.b(d.J.getBytes(), com.igexin.push.core.e.J));
                a.b(this.d, 47, String.valueOf(d.M));
                a.b(this.d, 46, String.valueOf(d.L));
                a.b(this.d, 48, String.valueOf(d.N));
                a.b(this.d, 49, String.valueOf(d.O));
                a.b(this.d, 52, String.valueOf(d.P));
                a.b(this.d, 53, d.Q);
                a.b(this.d, 73, String.valueOf(d.S));
                a.b(this.d, 74, String.valueOf(d.H));
                a.a(this.d, 75, com.igexin.b.a.a.a.b(d.U.getBytes(), com.igexin.push.core.e.J));
                a.b(this.d, 61, String.valueOf(com.igexin.push.core.e.aA));
                a.b(this.d, 67, String.valueOf(com.igexin.push.core.e.aC));
                a.b(this.d, 68, String.valueOf(com.igexin.push.core.e.aD));
                a.b(this.d, 69, com.igexin.push.core.e.aF);
                a.b(this.d, 70, String.valueOf(com.igexin.push.core.e.aG));
                a.a(this.d, 71, com.igexin.b.b.a.b(com.igexin.push.core.e.aH.getBytes()));
                a.a(this.d, 72, com.igexin.b.b.a.b(com.igexin.push.core.e.aI.getBytes()));
                a.b(this.d, 76, String.valueOf(d.R));
            }
        }, false, true);
    }
}
