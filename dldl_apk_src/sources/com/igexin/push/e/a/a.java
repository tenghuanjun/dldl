package com.igexin.push.e.a;

import android.os.Process;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.igexin.b.a.d.f;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a extends f {
    public static final String a = "com.igexin.push.e.a.a";
    public static final int b = -2147483639;
    private static final int d = 20000;
    public b c;
    private HttpURLConnection e;

    public a(b bVar) {
        super(0);
        this.c = bVar;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:0|2|(4:66|3|64|4)|(3:68|5|(8:7|(2:8|(1:10)(1:70))|11|(2:60|13)|58|14|15|16)(1:(2:52|18)))|62|42|43|44|(1:(0))) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private byte[] a(java.lang.String r7) throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            r1.<init>(r7)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            java.net.URLConnection r7 = r1.openConnection()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            r6.e = r7     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            java.net.HttpURLConnection r7 = r6.e     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            r1 = 20000(0x4e20, float:2.8026E-41)
            r7.setConnectTimeout(r1)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            java.net.HttpURLConnection r7 = r6.e     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            r7.setReadTimeout(r1)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            java.net.HttpURLConnection r7 = r6.e     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            java.lang.String r1 = "GET"
            r7.setRequestMethod(r1)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            java.net.HttpURLConnection r7 = r6.e     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            r1 = 1
            r7.setDoInput(r1)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            java.net.HttpURLConnection r7 = r6.e     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            java.io.InputStream r7 = r7.getInputStream()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L83
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6f
            r1.<init>()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6f
            java.net.HttpURLConnection r2 = r6.e     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L67
            int r2 = r2.getResponseCode()     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L67
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 != r3) goto L5c
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L67
        L40:
            int r3 = r7.read(r2)     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L67
            r4 = -1
            if (r3 == r4) goto L4c
            r4 = 0
            r1.write(r2, r4, r3)     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L67
            goto L40
        L4c:
            byte[] r0 = r1.toByteArray()     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L67
            if (r7 == 0) goto L55
            r7.close()     // Catch: java.lang.Exception -> L55
        L55:
            r1.close()     // Catch: java.lang.Exception -> L58
        L58:
            r6.g()
            return r0
        L5c:
            if (r7 == 0) goto L8e
            r7.close()     // Catch: java.lang.Exception -> L8e
            goto L8e
        L62:
            r0 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
            goto L73
        L67:
            goto L85
        L69:
            r1 = move-exception
            r5 = r0
            r0 = r7
            r7 = r1
            r1 = r5
            goto L73
        L6f:
            r1 = r0
            goto L85
        L71:
            r7 = move-exception
            r1 = r0
        L73:
            if (r0 == 0) goto L7a
            r0.close()     // Catch: java.lang.Exception -> L79
            goto L7a
        L79:
        L7a:
            if (r1 == 0) goto L7f
            r1.close()     // Catch: java.lang.Exception -> L7f
        L7f:
            r6.g()
            throw r7
        L83:
            r7 = r0
            r1 = r7
        L85:
            if (r7 == 0) goto L8c
            r7.close()     // Catch: java.lang.Exception -> L8b
            goto L8c
        L8b:
        L8c:
            if (r1 == 0) goto L91
        L8e:
            r1.close()     // Catch: java.lang.Exception -> L91
        L91:
            r6.g()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.e.a.a.a(java.lang.String):byte[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.ByteArrayOutputStream] */
    private byte[] a(String str, byte[] bArr) throws Throwable {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        DataOutputStream dataOutputStream;
        ?? r1;
        DataOutputStream dataOutputStream2 = null;
        try {
            try {
                this.e = (HttpURLConnection) new URL(str).openConnection();
                this.e.setDoInput(true);
                this.e.setDoOutput(true);
                this.e.setRequestMethod("POST");
                this.e.setUseCaches(false);
                this.e.setInstanceFollowRedirects(true);
                this.e.setRequestProperty("Content-Type", OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE);
                this.e.setConnectTimeout(20000);
                this.e.setReadTimeout(20000);
                this.e.connect();
                dataOutputStream = new DataOutputStream(this.e.getOutputStream());
                try {
                    dataOutputStream.write(bArr, 0, bArr.length);
                    dataOutputStream.flush();
                } catch (Exception unused) {
                    inputStream = null;
                    r1 = inputStream;
                } catch (Throwable th) {
                    byteArrayOutputStream = null;
                    dataOutputStream2 = dataOutputStream;
                    th = th;
                    inputStream = null;
                }
            } catch (Exception unused2) {
            }
        } catch (Exception unused3) {
            dataOutputStream = null;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            byteArrayOutputStream = null;
        }
        if (this.e.getResponseCode() != 200) {
            dataOutputStream.close();
            g();
            return null;
        }
        inputStream = this.e.getInputStream();
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                byte[] bArr2 = new byte[1024];
                while (true) {
                    int i = inputStream.read(bArr2);
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream2.write(bArr2, 0, i);
                }
                byte[] byteArray = byteArrayOutputStream2.toByteArray();
                try {
                    dataOutputStream.close();
                } catch (Exception unused4) {
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception unused5) {
                    }
                }
                try {
                    byteArrayOutputStream2.close();
                } catch (Exception unused6) {
                }
                g();
                return byteArray;
            } catch (Exception unused7) {
                r1 = byteArrayOutputStream2;
            } catch (Throwable th3) {
                dataOutputStream2 = dataOutputStream;
                th = th3;
                byteArrayOutputStream = byteArrayOutputStream2;
                if (dataOutputStream2 != null) {
                    try {
                        dataOutputStream2.close();
                    } catch (Exception unused8) {
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception unused9) {
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception unused10) {
                    }
                }
                g();
                throw th;
            }
        } catch (Exception unused11) {
            r1 = 0;
        } catch (Throwable th4) {
            dataOutputStream2 = dataOutputStream;
            th = th4;
            byteArrayOutputStream = null;
        }
        if (dataOutputStream != null) {
            try {
                dataOutputStream.close();
            } catch (Exception unused12) {
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused13) {
            }
        }
        if (r1 != 0) {
            r1.close();
        }
        g();
        return null;
    }

    private void g() {
        HttpURLConnection httpURLConnection = this.e;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
                this.e = null;
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.a
    public final void a() {
        super.a();
        g();
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void b_() throws Exception {
        super.b_();
        Process.setThreadPriority(10);
        b bVar = this.c;
        if (bVar == null || bVar.e == null || (this.c.f != null && this.c.f.length > com.igexin.push.config.d.E * 1024)) {
            k();
            com.igexin.b.a.c.a.a(a + "|run return ###", new Object[0]);
            return;
        }
        try {
            byte[] bArrA = this.c.f == null ? a(this.c.e) : a(this.c.e, this.c.f);
            if (bArrA == null) {
                Exception exc = new Exception("Http response ＝＝ null");
                this.c.a(exc);
                throw exc;
            }
            try {
                this.c.a(bArrA);
                com.igexin.b.a.b.e.a().a(this.c);
                com.igexin.b.a.b.e.a().b();
            } catch (Exception e) {
                this.c.a(e);
                throw e;
            }
        } catch (Exception e2) {
            this.c.a(e2);
            throw e2;
        }
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return -2147483639;
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void d() {
        this.o = true;
    }

    @Override // com.igexin.b.a.d.f
    public final void e() {
        g();
    }

    @Override // com.igexin.b.a.d.f
    public final void f() {
    }
}
