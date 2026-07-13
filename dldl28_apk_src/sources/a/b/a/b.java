package a.b.a;

import java.util.logging.Logger;

/* JADX INFO: compiled from: CallbackReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f138a = Logger.getLogger("CallbackReader");

    /* JADX WARN: Removed duplicated region for block: B:15:0x004d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.io.File r5) {
        /*
            r0 = 0
            int[] r1 = a.b.a.a.f137a     // Catch: java.lang.Throwable -> L37
            java.lang.String r5 = a.b.a.d.a(r5, r1)     // Catch: java.lang.Throwable -> L37
            if (r5 != 0) goto L13
            java.util.logging.Logger r5 = a.b.a.b.f138a     // Catch: java.lang.Throwable -> L37
            java.util.logging.Level r1 = java.util.logging.Level.WARNING     // Catch: java.lang.Throwable -> L37
            java.lang.String r2 = "callback not found"
            r5.log(r1, r2)     // Catch: java.lang.Throwable -> L37
            goto L4a
        L13:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L37
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L37
            java.util.Iterator r5 = r1.keys()     // Catch: java.lang.Throwable -> L37
            java.util.HashMap r2 = new java.util.HashMap     // Catch: java.lang.Throwable -> L37
            r2.<init>()     // Catch: java.lang.Throwable -> L37
        L21:
            boolean r3 = r5.hasNext()     // Catch: java.lang.Throwable -> L37
            if (r3 == 0) goto L4b
            java.lang.Object r3 = r5.next()     // Catch: java.lang.Throwable -> L37
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L37
            java.lang.String r4 = r1.getString(r3)     // Catch: java.lang.Throwable -> L37
            r2.put(r3, r4)     // Catch: java.lang.Throwable -> L37
            goto L21
        L37:
            r5 = move-exception
            java.util.logging.Logger r1 = a.b.a.b.f138a
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "read callback failed"
            r1.log(r2, r3)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r5 = r5.getMessage()
            r1.log(r2, r5)
        L4a:
            r2 = r0
        L4b:
            if (r2 != 0) goto L4e
            return r0
        L4e:
            java.lang.String r5 = "kwaiCallback"
            java.lang.Object r5 = r2.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.a.b.a(java.io.File):java.lang.String");
    }
}
