package com.mobile.auth.aa;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class d {
    private String a = "";

    private String a(InputStream inputStream) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        byte[] bArr;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    try {
                        bArr = new byte[1024];
                    } catch (Exception e) {
                        e = e;
                        e.printStackTrace();
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception unused) {
                                return null;
                            }
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th3);
                    return null;
                } catch (Throwable th4) {
                    com.mobile.auth.gatewayauth.a.a(th4);
                    return null;
                }
            }
        } catch (Exception e2) {
            e = e2;
            byteArrayOutputStream = null;
        } catch (Throwable th5) {
            byteArrayOutputStream = null;
            th = th5;
        }
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, i);
            th = th2;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Exception unused2) {
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
        String str = new String(byteArrayOutputStream.toByteArray());
        try {
            byteArrayOutputStream.close();
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception unused3) {
        }
        return str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0166 A[Catch: Throwable -> 0x01a6, TRY_ENTER, TRY_LEAVE, TryCatch #5 {Throwable -> 0x01a6, blocks: (B:22:0x007c, B:67:0x0166, B:74:0x0180, B:78:0x018a, B:81:0x0190, B:91:0x01a2), top: B:107:0x0001 }] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(java.lang.String r8, java.util.HashMap<java.lang.String, java.lang.String> r9, android.net.Network r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.aa.d.a(java.lang.String, java.util.HashMap, android.net.Network):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:67:0x015e A[Catch: Throwable -> 0x019e, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Throwable -> 0x019e, blocks: (B:22:0x0074, B:67:0x015e, B:74:0x0178, B:78:0x0182, B:81:0x0188, B:91:0x019a), top: B:104:0x0001 }] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String b(java.lang.String r8, java.util.HashMap<java.lang.String, java.lang.String> r9, android.net.Network r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.aa.d.b(java.lang.String, java.util.HashMap, android.net.Network):java.lang.String");
    }
}
