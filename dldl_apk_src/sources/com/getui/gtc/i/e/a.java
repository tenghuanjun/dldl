package com.getui.gtc.i.e;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0022 -> B:50:0x0045). Please report as a decompilation issue!!! */
    public static byte[] a(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream;
        byte[] byteArray = null;
        byteArray = null;
        byteArray = null;
        gZIPOutputStream = null;
        GZIPOutputStream gZIPOutputStream2 = null;
        try {
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (Throwable th) {
                    th = th;
                    gZIPOutputStream2 = gZIPOutputStream;
                }
            } catch (Exception e) {
                e = e;
                byteArrayOutputStream = null;
                gZIPOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
            }
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.finish();
                    byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception e2) {
                        com.getui.gtc.i.c.a.b(e2);
                    }
                    byteArrayOutputStream.close();
                } catch (Exception e3) {
                    e = e3;
                    com.getui.gtc.i.c.a.b(e);
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (Exception e4) {
                            com.getui.gtc.i.c.a.b(e4);
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                gZIPOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                if (gZIPOutputStream2 != null) {
                    try {
                        gZIPOutputStream2.close();
                    } catch (Exception e6) {
                        com.getui.gtc.i.c.a.b(e6);
                    }
                }
                if (byteArrayOutputStream == null) {
                    throw th;
                }
                try {
                    byteArrayOutputStream.close();
                    throw th;
                } catch (Exception e7) {
                    com.getui.gtc.i.c.a.b(e7);
                    throw th;
                }
            }
        } catch (Exception e8) {
            com.getui.gtc.i.c.a.b(e8);
        }
        return byteArray;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:0|2|(3:75|3|(2:89|4))|(2:91|5)|(5:72|6|(1:8)(1:93)|74|49)|9|86|10|70|14|18|74|49|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0023, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0024, code lost:
    
        com.getui.gtc.i.c.a.b(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002b, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002c, code lost:
    
        com.getui.gtc.i.c.a.b(r6);
     */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0034 -> B:74:0x006e). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] b(byte[] r6) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4e
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4e
            java.util.zip.GZIPInputStream r6 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L46
            r6.<init>(r1)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L46
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L40
            r2.<init>()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L40
        L10:
            int r3 = r6.read()     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L6f
            r4 = -1
            if (r3 == r4) goto L1b
            r2.write(r3)     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L6f
            goto L10
        L1b:
            byte[] r0 = r2.toByteArray()     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L6f
            r6.close()     // Catch: java.lang.Exception -> L23
            goto L27
        L23:
            r6 = move-exception
            com.getui.gtc.i.c.a.b(r6)
        L27:
            r2.close()     // Catch: java.lang.Exception -> L2b
            goto L2f
        L2b:
            r6 = move-exception
            com.getui.gtc.i.c.a.b(r6)
        L2f:
            r1.close()     // Catch: java.lang.Exception -> L33
            goto L6e
        L33:
            r6 = move-exception
            com.getui.gtc.i.c.a.b(r6)
            goto L6e
        L38:
            r3 = move-exception
            goto L52
        L3a:
            r2 = move-exception
            r5 = r0
            r0 = r6
            r6 = r2
            r2 = r5
            goto L73
        L40:
            r3 = move-exception
            r2 = r0
            goto L52
        L43:
            r6 = move-exception
            r2 = r0
            goto L73
        L46:
            r3 = move-exception
            r6 = r0
            r2 = r6
            goto L52
        L4a:
            r6 = move-exception
            r1 = r0
            r2 = r1
            goto L73
        L4e:
            r3 = move-exception
            r6 = r0
            r1 = r6
            r2 = r1
        L52:
            com.getui.gtc.i.c.a.b(r3)     // Catch: java.lang.Throwable -> L6f
            if (r6 == 0) goto L5f
            r6.close()     // Catch: java.lang.Exception -> L5b
            goto L5f
        L5b:
            r6 = move-exception
            com.getui.gtc.i.c.a.b(r6)
        L5f:
            if (r2 == 0) goto L69
            r2.close()     // Catch: java.lang.Exception -> L65
            goto L69
        L65:
            r6 = move-exception
            com.getui.gtc.i.c.a.b(r6)
        L69:
            if (r1 == 0) goto L6e
            r1.close()     // Catch: java.lang.Exception -> L33
        L6e:
            return r0
        L6f:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
        L73:
            if (r0 == 0) goto L7d
            r0.close()     // Catch: java.lang.Exception -> L79
            goto L7d
        L79:
            r0 = move-exception
            com.getui.gtc.i.c.a.b(r0)
        L7d:
            if (r2 == 0) goto L87
            r2.close()     // Catch: java.lang.Exception -> L83
            goto L87
        L83:
            r0 = move-exception
            com.getui.gtc.i.c.a.b(r0)
        L87:
            if (r1 == 0) goto L91
            r1.close()     // Catch: java.lang.Exception -> L8d
            goto L91
        L8d:
            r0 = move-exception
            com.getui.gtc.i.c.a.b(r0)
        L91:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.i.e.a.b(byte[]):byte[]");
    }
}
