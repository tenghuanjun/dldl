package com.igexin.push.f;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class f {
    private static final String a = "DBUtil";

    /* JADX WARN: Removed duplicated region for block: B:15:0x0028 A[PHI: r1
  0x0028: PHI (r1v3 android.database.Cursor) = (r1v2 android.database.Cursor), (r1v4 android.database.Cursor) binds: [B:14:0x0026, B:9:0x001a] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a() {
        /*
            r0 = 0
            r1 = 0
            com.igexin.push.core.d r2 = com.igexin.push.core.d.a.a()     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L1f
            com.igexin.push.a.b r2 = r2.j     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L1f
            java.lang.String r3 = "message"
            android.database.Cursor r1 = r2.a(r3, r1, r1, r1)     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L1f
            if (r1 == 0) goto L1a
            int r0 = r1.getCount()     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L1f
            if (r1 == 0) goto L19
            r1.close()
        L19:
            return r0
        L1a:
            if (r1 == 0) goto L2b
            goto L28
        L1d:
            r0 = move-exception
            goto L2c
        L1f:
            java.lang.String r2 = "DBUtilgetMessageTableNum error"
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L1d
            com.igexin.b.a.c.a.a(r2, r3)     // Catch: java.lang.Throwable -> L1d
            if (r1 == 0) goto L2b
        L28:
            r1.close()
        L2b:
            return r0
        L2c:
            if (r1 == 0) goto L31
            r1.close()
        L31:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.f.f.a():int");
    }
}
