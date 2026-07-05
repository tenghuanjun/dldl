package com.igexin.push.core.e;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.igexin.b.a.d.f;
import com.igexin.push.f.h;
import com.igexin.push.f.k;
import com.igexin.push.f.n;
import java.util.Random;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class e implements a {
    private static final int A = 50;
    private static final int B = 30;
    private static final int C = 31;
    private static final int D = 22;
    private static final int E = 23;
    private static final int F = 51;
    private static final int G = 52;
    private static volatile e H = null;
    public static final String a = "com.igexin.push.core.e.e";
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private static final int g = 6;
    private static final int h = 8;
    private static final int i = 11;
    private static final int j = 12;
    private static final int k = 13;
    private static final int l = 14;
    private static final int m = 15;
    private static final int n = 16;
    private static final int o = 17;
    private static final int p = 18;
    private static final int q = 19;
    private static final int r = 20;
    private static final int s = 21;
    private static final int t = 25;
    private static final int u = 32;
    private static final int v = 40;
    private static final int w = 46;
    private static final int x = 47;
    private static final int y = 48;
    private static final int z = 49;
    public boolean b;

    /* JADX INFO: renamed from: com.igexin.push.core.e.e$1, reason: invalid class name */
    public class AnonymousClass1 extends com.igexin.push.a.d {
        public AnonymousClass1() {
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            e.this.b(this.d);
            k.a();
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.e$12, reason: invalid class name */
    public class AnonymousClass12 extends com.igexin.push.a.d {
        public AnonymousClass12() {
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            e.a();
            e.b(this.d, 8, String.valueOf(com.igexin.push.core.e.O));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.e$16, reason: invalid class name */
    public class AnonymousClass16 extends com.igexin.push.a.d {
        final /* synthetic */ String a;

        public AnonymousClass16(String str) {
            this.a = str;
        }

        @Override // com.igexin.push.a.d
        public final void a_() throws Exception {
            e.a();
            e.b(this.d, 3, this.a);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.e$21, reason: invalid class name */
    public class AnonymousClass21 extends com.igexin.push.a.d {
        final /* synthetic */ boolean a;
        final /* synthetic */ String b;

        public AnonymousClass21(boolean z, String str) {
            this.a = z;
            this.b = str;
        }

        @Override // com.igexin.push.a.d
        public final void a_() throws Exception {
            e.a();
            e.a(this.d, this.a ? 50 : 49, e.e(this.b));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.e$22, reason: invalid class name */
    public class AnonymousClass22 extends com.igexin.push.a.d {
        final /* synthetic */ String a;

        public AnonymousClass22(String str) {
            this.a = str;
        }

        @Override // com.igexin.push.a.d
        public final void a_() throws Exception {
            e.a();
            e.b(this.d, 52, this.a);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.e$24, reason: invalid class name */
    public class AnonymousClass24 extends com.igexin.push.a.d {
        public AnonymousClass24() {
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            e.a();
            e.a(this.d, 19, h.a(com.igexin.push.core.e.E.getBytes()));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.e$27, reason: invalid class name */
    public class AnonymousClass27 extends com.igexin.push.a.d {
        public AnonymousClass27() {
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            e.a();
            e.b(this.d, 51, com.igexin.push.core.e.z);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.e$3, reason: invalid class name */
    public class AnonymousClass3 extends com.igexin.push.a.d {
        public AnonymousClass3() {
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            e.a();
            e.b(this.d, 12, String.valueOf(com.igexin.push.core.e.R));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.e$6, reason: invalid class name */
    public class AnonymousClass6 extends com.igexin.push.a.d {
        public AnonymousClass6() {
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            e.a();
            e.b(this.d, 13, com.igexin.push.core.e.T);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.e$8, reason: invalid class name */
    public class AnonymousClass8 extends com.igexin.push.a.d {
        public AnonymousClass8() {
        }

        @Override // com.igexin.push.a.d
        public final void a_() {
            e.a();
            e.b(this.d, 16, String.valueOf(com.igexin.push.core.e.V));
        }
    }

    private e() {
    }

    public static e a() {
        if (H == null) {
            synchronized (e.class) {
                if (H == null) {
                    H = new e();
                }
            }
        }
        return H;
    }

    public static void a(SQLiteDatabase sQLiteDatabase, int i2, byte[] bArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(i2));
        contentValues.put("value", bArr);
        sQLiteDatabase.replace(com.igexin.push.core.b.Y, null, contentValues);
    }

    private boolean a(String str, String str2, long j2) {
        com.igexin.push.core.e.w = j2;
        if (TextUtils.isEmpty(com.igexin.push.core.e.F)) {
            com.igexin.push.core.e.F = str2;
        }
        com.igexin.push.core.e.x = str;
        return d();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004a A[PHI: r9
  0x004a: PHI (r9v3 android.database.Cursor) = (r9v2 android.database.Cursor), (r9v4 android.database.Cursor) binds: [B:20:0x0048, B:13:0x003d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(android.database.sqlite.SQLiteDatabase r9, int r10) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.String r2 = "runtime"
            java.lang.String r1 = "value"
            java.lang.String[] r3 = new java.lang.String[]{r1}     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L47
            java.lang.String r1 = "id="
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L47
            java.lang.String r4 = r1.concat(r10)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L47
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r9
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L47
            if (r9 == 0) goto L3d
            boolean r10 = r9.moveToFirst()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L48
            if (r10 == 0) goto L3d
            java.lang.String r10 = "value"
            int r10 = r9.getColumnIndex(r10)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L48
            byte[] r10 = r9.getBlob(r10)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L48
            java.lang.String r1 = com.igexin.push.core.e.J     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L48
            byte[] r10 = com.igexin.b.a.a.a.a(r10, r1)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L48
            if (r9 == 0) goto L39
            r9.close()
        L39:
            return r10
        L3a:
            r10 = move-exception
            r0 = r9
            goto L41
        L3d:
            if (r9 == 0) goto L4d
            goto L4a
        L40:
            r10 = move-exception
        L41:
            if (r0 == 0) goto L46
            r0.close()
        L46:
            throw r10
        L47:
            r9 = r0
        L48:
            if (r9 == 0) goto L4d
        L4a:
            r9.close()
        L4d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.e.e.a(android.database.sqlite.SQLiteDatabase, int):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0044 A[PHI: r9
  0x0044: PHI (r9v3 android.database.Cursor) = (r9v2 android.database.Cursor), (r9v4 android.database.Cursor) binds: [B:20:0x0042, B:13:0x0037] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b(android.database.sqlite.SQLiteDatabase r9, int r10) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.String r2 = "runtime"
            java.lang.String r1 = "value"
            java.lang.String[] r3 = new java.lang.String[]{r1}     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L41
            java.lang.String r1 = "id="
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L41
            java.lang.String r4 = r1.concat(r10)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L41
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r9
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L41
            if (r9 == 0) goto L37
            boolean r10 = r9.moveToFirst()     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L42
            if (r10 == 0) goto L37
            java.lang.String r10 = "value"
            int r10 = r9.getColumnIndex(r10)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L42
            java.lang.String r10 = r9.getString(r10)     // Catch: java.lang.Throwable -> L34 java.lang.Exception -> L42
            if (r9 == 0) goto L33
            r9.close()
        L33:
            return r10
        L34:
            r10 = move-exception
            r0 = r9
            goto L3b
        L37:
            if (r9 == 0) goto L47
            goto L44
        L3a:
            r10 = move-exception
        L3b:
            if (r0 == 0) goto L40
            r0.close()
        L40:
            throw r10
        L41:
            r9 = r0
        L42:
            if (r9 == 0) goto L47
        L44:
            r9.close()
        L47:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.e.e.b(android.database.sqlite.SQLiteDatabase, int):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(SQLiteDatabase sQLiteDatabase, int i2, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(i2));
        contentValues.put("value", str);
        sQLiteDatabase.replace(com.igexin.push.core.b.Y, null, contentValues);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004c A[PHI: r0
  0x004c: PHI (r0v3 android.database.Cursor) = (r0v2 android.database.Cursor), (r0v9 android.database.Cursor) binds: [B:14:0x004a, B:8:0x0040] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(android.database.sqlite.SQLiteDatabase r9) {
        /*
            r0 = 0
            java.lang.String r2 = "runtime"
            java.lang.String r1 = "value"
            java.lang.String[] r3 = new java.lang.String[]{r1}     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
            java.lang.String r4 = "id=?"
            java.lang.String r1 = "25"
            java.lang.String[] r5 = new java.lang.String[]{r1}     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r9
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
            if (r0 == 0) goto L40
            boolean r9 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
            if (r9 == 0) goto L40
            java.lang.String r9 = new java.lang.String     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
            java.lang.String r1 = "value"
            int r1 = r0.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
            byte[] r1 = r0.getBlob(r1)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
            android.content.Context r2 = com.igexin.push.core.p.b     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
            java.lang.String r2 = r2.getPackageName()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
            java.lang.String r2 = com.igexin.b.b.a.a(r2)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
            byte[] r1 = com.igexin.b.a.a.a.a(r1, r2)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
            r9.<init>(r1)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
            com.igexin.push.core.e.J = r9     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4a
        L40:
            if (r0 == 0) goto L4f
            goto L4c
        L43:
            r9 = move-exception
            if (r0 == 0) goto L49
            r0.close()
        L49:
            throw r9
        L4a:
            if (r0 == 0) goto L4f
        L4c:
            r0.close()
        L4f:
            java.lang.String r9 = com.igexin.push.core.e.J
            if (r9 != 0) goto L62
            java.lang.String r9 = com.igexin.push.core.e.A
            if (r9 != 0) goto L5a
            java.lang.String r9 = "cantgetimei"
            goto L5c
        L5a:
            java.lang.String r9 = com.igexin.push.core.e.A
        L5c:
            java.lang.String r9 = com.igexin.b.b.a.a(r9)
            com.igexin.push.core.e.J = r9
        L62:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = com.igexin.push.core.e.e.a
            r9.append(r0)
            java.lang.String r0 = "|storageKey = "
            r9.append(r0)
            java.lang.String r0 = com.igexin.push.core.e.J
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            r0 = 0
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.igexin.b.a.c.a.a(r9, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.e.e.c(android.database.sqlite.SQLiteDatabase):void");
    }

    private boolean c(String str, boolean z2) {
        if (str == null) {
            return false;
        }
        String str2 = str.equals("null") ? null : str;
        if (z2 && !TextUtils.equals(com.igexin.push.core.e.au, str)) {
            com.igexin.push.core.e.au = str2;
        } else {
            if (z2 || TextUtils.equals(com.igexin.push.core.e.at, str)) {
                return false;
            }
            com.igexin.push.core.e.at = str2;
        }
        com.igexin.b.a.c.a.a(a + "|saveLastRedirectCmList isMobile = " + z2 + ", lastRedirectCmList = " + str, new Object[0]);
        return com.igexin.b.a.b.e.a().a((f) new AnonymousClass21(z2, str), false, true);
    }

    private void d(SQLiteDatabase sQLiteDatabase) throws Throwable {
        this.b = true;
        c(sQLiteDatabase);
        byte[] bArrA = a(sQLiteDatabase, 1);
        if (bArrA != null) {
            try {
                String str = new String(bArrA);
                com.igexin.push.core.e.w = str.equals("null") ? 0L : Long.parseLong(str);
            } catch (Exception unused) {
            }
            com.igexin.b.a.c.a.a(a + "|db version changed, save session = " + com.igexin.push.core.e.w, new Object[0]);
        }
        byte[] bArrA2 = a(sQLiteDatabase, 20);
        if (bArrA2 != null) {
            String str2 = new String(bArrA2);
            if (str2.equals("null")) {
                str2 = null;
            }
            com.igexin.push.core.e.y = str2;
            com.igexin.push.core.e.x = str2;
            com.igexin.b.a.c.a.a(a + "|db version changed, save cid = " + str2, new Object[0]);
        }
        String strB = b(sQLiteDatabase, 3);
        if (!TextUtils.isEmpty(strB)) {
            if (strB.equals("null")) {
                strB = null;
            }
            com.igexin.push.core.e.I = strB;
        }
        String str3 = com.igexin.push.core.e.I;
        String strB2 = b(sQLiteDatabase, 2);
        if (!TextUtils.isEmpty(strB2)) {
            if (strB2.equals("null")) {
                strB2 = null;
            }
            com.igexin.push.core.e.F = strB2;
        }
        String strB3 = b(sQLiteDatabase, 46);
        if (!TextUtils.isEmpty(strB3)) {
            if (strB3.equals("null")) {
                strB3 = null;
            }
            com.igexin.push.core.e.G = strB3;
        }
        String strB4 = b(sQLiteDatabase, 48);
        if (!TextUtils.isEmpty(strB4)) {
            if (strB4.equals("null")) {
                strB4 = null;
            }
            com.igexin.push.core.e.H = strB4;
        }
        String strB5 = b(sQLiteDatabase, 51);
        if (TextUtils.isEmpty(strB5)) {
            return;
        }
        if (strB5.equals("null")) {
            strB5 = null;
        }
        com.igexin.push.core.e.z = strB5;
    }

    public static void e() {
        StringBuilder sb;
        String strI;
        String string = com.igexin.push.core.e.A;
        if (TextUtils.isEmpty(string) || string.length() <= 8) {
            if (Build.VERSION.SDK_INT < 26) {
                sb = new StringBuilder("V");
                strI = n.g();
            } else {
                try {
                    StringBuilder sb2 = new StringBuilder("V");
                    sb2.append(com.igexin.b.b.a.a(i() + com.igexin.push.core.e.d + UUID.randomUUID()));
                    string = sb2.toString();
                } catch (Throwable th) {
                    com.igexin.b.a.c.a.a(a + "|" + th.toString(), new Object[0]);
                    sb = new StringBuilder("V");
                    strI = i();
                    sb.append(strI);
                    string = sb.toString();
                }
            }
            sb.append(strI);
            string = sb.toString();
        }
        String str = "A-" + string + "-" + System.currentTimeMillis();
        com.igexin.push.core.e.I = str;
        if (str.length() >= 64) {
            try {
                com.igexin.push.core.e.I = com.igexin.push.core.e.I.substring(0, 62);
            } catch (Throwable th2) {
                com.igexin.b.a.c.a.a(a + "|" + th2.toString(), new Object[0]);
            }
        }
    }

    private static void e(SQLiteDatabase sQLiteDatabase) throws Throwable {
        String strB = b(sQLiteDatabase, 2);
        if (TextUtils.isEmpty(strB)) {
            return;
        }
        if (strB.equals("null")) {
            strB = null;
        }
        com.igexin.push.core.e.F = strB;
    }

    private boolean e(long j2) {
        if (j2 == com.igexin.push.core.e.O) {
            return false;
        }
        com.igexin.push.core.e.O = j2;
        return com.igexin.b.a.b.e.a().a((f) new AnonymousClass12(), false, true);
    }

    static /* synthetic */ byte[] e(String str) {
        return h.a(str.getBytes());
    }

    static /* synthetic */ void f() {
        k.a();
        String strC = k.c();
        if (strC == null || strC.length() <= 5) {
            k.e();
        }
    }

    private static void f(SQLiteDatabase sQLiteDatabase) throws Throwable {
        String strB = b(sQLiteDatabase, 51);
        if (TextUtils.isEmpty(strB)) {
            return;
        }
        if (strB.equals("null")) {
            strB = null;
        }
        com.igexin.push.core.e.z = strB;
    }

    private boolean f(long j2) {
        if (com.igexin.push.core.e.R == j2) {
            return false;
        }
        com.igexin.push.core.e.R = j2;
        com.igexin.b.a.b.e.a().a((f) new AnonymousClass3(), false, true);
        return true;
    }

    private boolean f(String str) {
        com.igexin.push.core.e.e = str;
        return com.igexin.b.a.b.e.a().a((f) new AnonymousClass22(str), false, true);
    }

    private void g() {
        com.igexin.b.a.b.e.a().a((f) new AnonymousClass1(), false, true);
    }

    private static void g(SQLiteDatabase sQLiteDatabase) throws Throwable {
        String strB = b(sQLiteDatabase, 46);
        if (TextUtils.isEmpty(strB)) {
            return;
        }
        if (strB.equals("null")) {
            strB = null;
        }
        com.igexin.push.core.e.G = strB;
    }

    private boolean g(long j2) {
        if (com.igexin.push.core.e.V == j2) {
            return false;
        }
        com.igexin.push.core.e.V = j2;
        return com.igexin.b.a.b.e.a().a((f) new AnonymousClass8(), false, true);
    }

    private static byte[] g(String str) {
        return h.a(str.getBytes());
    }

    private static void h() {
        k.a();
        String strC = k.c();
        if (strC == null || strC.length() <= 5) {
            k.e();
        }
    }

    private static void h(SQLiteDatabase sQLiteDatabase) throws Throwable {
        String strB = b(sQLiteDatabase, 48);
        if (TextUtils.isEmpty(strB)) {
            return;
        }
        if (strB.equals("null")) {
            strB = null;
        }
        com.igexin.push.core.e.H = strB;
    }

    private boolean h(long j2) {
        if (com.igexin.push.core.e.S == j2) {
            return false;
        }
        com.igexin.push.core.e.S = j2;
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.17
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 32, String.valueOf(com.igexin.push.core.e.S));
            }
        }, false, true);
    }

    private boolean h(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        com.igexin.push.core.e.E = str;
        return com.igexin.b.a.b.e.a().a((f) new AnonymousClass24(), false, true);
    }

    private static String i() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random(Math.abs(new Random().nextLong()));
        for (int i2 = 0; i2 < 15; i2++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static void i(SQLiteDatabase sQLiteDatabase) throws Throwable {
        String strB = b(sQLiteDatabase, 3);
        if (!TextUtils.isEmpty(strB)) {
            if (strB.equals("null")) {
                strB = null;
            }
            com.igexin.push.core.e.I = strB;
        }
        String str = com.igexin.push.core.e.I;
    }

    private boolean i(String str) {
        com.igexin.push.core.e.z = str;
        return com.igexin.b.a.b.e.a().a((f) new AnonymousClass27(), false, true);
    }

    private static void j() {
        String str = com.igexin.push.core.e.x;
        com.igexin.b.a.c.a.a(a + "| found a duplicate cid " + com.igexin.push.core.e.x, new Object[0]);
        com.igexin.push.core.e.I = null;
        e();
        com.igexin.b.a.b.e.a().a((f) a().new AnonymousClass16(com.igexin.push.core.e.I), false, true);
        a().c();
        com.igexin.push.core.e.o = 0;
        com.igexin.push.e.b.e.g().a = SystemClock.elapsedRealtime();
    }

    private static void j(SQLiteDatabase sQLiteDatabase) throws Throwable {
        byte[] bArrA = a(sQLiteDatabase, 1);
        if (bArrA != null) {
            try {
                String str = new String(bArrA);
                com.igexin.push.core.e.w = str.equals("null") ? 0L : Long.parseLong(str);
            } catch (Exception unused) {
            }
            com.igexin.b.a.c.a.a(a + "|db version changed, save session = " + com.igexin.push.core.e.w, new Object[0]);
        }
    }

    private boolean j(String str) {
        if (str.equals(com.igexin.push.core.e.T)) {
            return false;
        }
        com.igexin.push.core.e.T = str;
        com.igexin.b.a.b.e.a().a((f) new AnonymousClass6(), false, true);
        return true;
    }

    private static void k(SQLiteDatabase sQLiteDatabase) throws Throwable {
        byte[] bArrA = a(sQLiteDatabase, 20);
        if (bArrA != null) {
            String str = new String(bArrA);
            if (str.equals("null")) {
                str = null;
            }
            com.igexin.push.core.e.y = str;
            com.igexin.push.core.e.x = str;
            com.igexin.b.a.c.a.a(a + "|db version changed, save cid = " + str, new Object[0]);
        }
    }

    private boolean k(String str) {
        return com.igexin.b.a.b.e.a().a((f) new AnonymousClass16(str), false, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:200:0x037f A[PHI: r9
  0x037f: PHI (r9v5 android.database.Cursor) = (r9v4 android.database.Cursor), (r9v7 android.database.Cursor) binds: [B:199:0x037d, B:191:0x0371] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.igexin.push.core.e.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(android.database.sqlite.SQLiteDatabase r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.e.e.a(android.database.sqlite.SQLiteDatabase):void");
    }

    public final void a(boolean z2) {
        com.igexin.push.core.e.U = z2;
        com.igexin.b.a.c.a.a(z2);
        com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.7
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 15, String.valueOf(com.igexin.push.core.e.U));
            }
        }, false, true);
    }

    public final boolean a(int i2) {
        com.igexin.push.core.e.Z = i2;
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.10
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 18, String.valueOf(com.igexin.push.core.e.Z));
            }
        }, false, true);
    }

    public final boolean a(long j2) {
        com.igexin.push.core.e.a(j2);
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.25
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.a(this.d, 1, h.a(String.valueOf(com.igexin.push.core.e.w).getBytes()));
                e.a();
                e.a(this.d, 20, e.e(com.igexin.push.core.e.x));
                k.a();
            }
        }, false, true);
    }

    public final boolean a(String str) {
        com.igexin.push.core.e.F = str;
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.26
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 2, com.igexin.push.core.e.F);
                String strC = k.c();
                if (strC == null || strC.length() <= 5) {
                    k.e();
                }
            }
        }, false, true);
    }

    public final boolean a(final String str, boolean z2) {
        com.igexin.b.a.b.e eVarA;
        com.igexin.push.a.d dVar;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (z2) {
            if (!str.equals(com.igexin.push.core.e.ar)) {
                com.igexin.push.core.e.ar = str.equals("null") ? null : str;
                eVarA = com.igexin.b.a.b.e.a();
                dVar = new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.11
                    @Override // com.igexin.push.a.d
                    public final void a_() throws Exception {
                        e.a();
                        e.a(this.d, 31, e.e(str));
                    }
                };
                return eVarA.a((f) dVar, false, true);
            }
            return false;
        }
        if (!str.equals(com.igexin.push.core.e.as)) {
            com.igexin.push.core.e.as = str.equals("null") ? null : str;
            eVarA = com.igexin.b.a.b.e.a();
            dVar = new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.13
                @Override // com.igexin.push.a.d
                public final void a_() throws Exception {
                    e.a();
                    e.a(this.d, 30, e.e(str));
                }
            };
            return eVarA.a((f) dVar, false, true);
        }
        return false;
    }

    @Override // com.igexin.push.core.e.a
    public final void b() {
    }

    @Override // com.igexin.push.core.e.a
    public final void b(SQLiteDatabase sQLiteDatabase) {
        byte[] bArrB = com.igexin.b.a.a.a.b(String.valueOf(com.igexin.push.core.e.w).getBytes(), com.igexin.push.core.e.J);
        long j2 = com.igexin.push.core.e.w;
        a(sQLiteDatabase, 1, bArrB);
        b(sQLiteDatabase, 4, String.valueOf(com.igexin.push.core.e.q));
        b(sQLiteDatabase, 8, String.valueOf(com.igexin.push.core.e.O));
        b(sQLiteDatabase, 32, String.valueOf(com.igexin.push.core.e.S));
        b(sQLiteDatabase, 3, com.igexin.push.core.e.I);
        b(sQLiteDatabase, 11, String.valueOf(com.igexin.push.core.e.Q));
        b(sQLiteDatabase, 12, String.valueOf(com.igexin.push.core.e.R));
        a(sQLiteDatabase, 20, com.igexin.b.a.a.a.b(com.igexin.push.core.e.x.getBytes(), com.igexin.push.core.e.J));
        b(sQLiteDatabase, 2, com.igexin.push.core.e.F);
        a(sQLiteDatabase, 25, com.igexin.b.a.a.a.b(com.igexin.push.core.e.J.getBytes(), com.igexin.b.b.a.a(com.igexin.push.core.e.i.getPackageName())));
    }

    public final boolean b(int i2) {
        if (com.igexin.push.core.e.ax == i2) {
            return false;
        }
        com.igexin.push.core.e.ax = i2;
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.19
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 47, String.valueOf(com.igexin.push.core.e.ax));
            }
        }, false, true);
    }

    public final boolean b(final long j2) {
        com.igexin.push.core.e.ao = j2;
        com.igexin.b.a.c.a.a(a + "|save idc config failed time : " + j2, new Object[0]);
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.4
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 21, String.valueOf(j2));
            }
        }, false, true);
    }

    public final boolean b(String str) {
        com.igexin.push.core.e.G = str;
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.28
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 46, com.igexin.push.core.e.G);
            }
        }, false, true);
    }

    public final boolean b(final String str, boolean z2) {
        com.igexin.b.a.b.e eVarA;
        com.igexin.push.a.d dVar;
        if (str == null) {
            return false;
        }
        if (z2) {
            if (!str.equals(com.igexin.push.core.e.ap)) {
                com.igexin.push.core.e.ap = str.equals("null") ? null : str;
                eVarA = com.igexin.b.a.b.e.a();
                dVar = new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.14
                    @Override // com.igexin.push.a.d
                    public final void a_() throws Exception {
                        e.a();
                        e.a(this.d, 23, e.e(str));
                    }
                };
                return eVarA.a((f) dVar, false, true);
            }
            return false;
        }
        if (!str.equals(com.igexin.push.core.e.aq)) {
            com.igexin.push.core.e.aq = str.equals("null") ? null : str;
            eVarA = com.igexin.b.a.b.e.a();
            dVar = new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.15
                @Override // com.igexin.push.a.d
                public final void a_() throws Exception {
                    e.a();
                    e.a(this.d, 22, e.e(str));
                }
            };
            return eVarA.a((f) dVar, false, true);
        }
        return false;
    }

    public final boolean b(final boolean z2) {
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.20
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 40, String.valueOf(z2));
            }
        }, false, true);
    }

    public final boolean c() {
        com.igexin.push.core.e.w = 0L;
        com.igexin.push.core.e.x = "null";
        return d();
    }

    public final boolean c(long j2) {
        if (com.igexin.push.core.e.Q == j2) {
            return false;
        }
        com.igexin.push.core.e.Q = j2;
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.5
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 11, String.valueOf(com.igexin.push.core.e.Q));
            }
        }, false, true);
    }

    public final boolean c(String str) {
        com.igexin.push.core.e.H = str;
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.2
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 48, com.igexin.push.core.e.H);
            }
        }, false, true);
    }

    public final boolean d() {
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.23
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 2, com.igexin.push.core.e.F);
                e.a(this.d, 1, e.e(String.valueOf(com.igexin.push.core.e.w)));
                e.a(this.d, 20, e.e(com.igexin.push.core.e.x));
                e.f();
            }
        }, false, true);
    }

    public final boolean d(long j2) {
        if (com.igexin.push.core.e.N == j2) {
            return false;
        }
        com.igexin.push.core.e.N = j2;
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.18
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 6, String.valueOf(com.igexin.push.core.e.N));
            }
        }, false, true);
    }

    public final boolean d(String str) {
        if (str.equals(com.igexin.push.core.e.X)) {
            return false;
        }
        com.igexin.push.core.e.X = str;
        return com.igexin.b.a.b.e.a().a((f) new com.igexin.push.a.d() { // from class: com.igexin.push.core.e.e.9
            @Override // com.igexin.push.a.d
            public final void a_() {
                e.a();
                e.b(this.d, 17, String.valueOf(com.igexin.push.core.e.X));
            }
        }, false, true);
    }
}
