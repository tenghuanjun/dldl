package com.igexin.push.e.a;

import android.os.Process;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.igexin.b.a.b.g;
import com.igexin.b.a.d.f;
import com.igexin.push.f.h;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c extends f {
    public static final int a = -2147483638;
    private static final String c = "HttpTask";
    private static final int d = 20000;
    private static final int e = 3;
    public b b;
    private HttpURLConnection f;

    class a {
        boolean a;
        byte[] b;

        public a(boolean z, byte[] bArr) {
            this.a = z;
            this.b = bArr;
        }
    }

    public c(b bVar) {
        super(0);
        this.b = bVar;
    }

    private a a(String str) {
        try {
            this.f = (HttpURLConnection) new URL(str).openConnection();
            this.f.setConnectTimeout(20000);
            this.f.setReadTimeout(20000);
            this.f.setRequestMethod("GET");
            this.f.setDoInput(true);
            a(this.f, (byte[]) null);
            this.f = this.f;
            byte[] bArrA = a(this.f);
            if (bArrA != null) {
                a aVarB = b(this.f, bArrA);
                g();
                return aVarB;
            }
        } catch (Throwable unused) {
        }
        g();
        return new a(false, null);
    }

    private a a(String str, byte[] bArr) throws Throwable {
        DataOutputStream dataOutputStream;
        byte[] bArrA;
        DataOutputStream dataOutputStream2 = null;
        try {
            this.f = (HttpURLConnection) new URL(str).openConnection();
            this.f.setDoInput(true);
            this.f.setDoOutput(true);
            this.f.setRequestMethod("POST");
            this.f.setUseCaches(false);
            this.f.setInstanceFollowRedirects(true);
            this.f.setRequestProperty("Content-Type", OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE);
            this.f.setConnectTimeout(20000);
            this.f.setReadTimeout(20000);
            a(this.f, bArr);
            this.f = this.f;
            bArrA = a(bArr, this.f);
        } catch (Throwable th) {
            th = th;
        }
        if (bArrA == null) {
            a aVar = new a(true, null);
            g.a((Closeable) null);
            g();
            return aVar;
        }
        this.f.connect();
        dataOutputStream = new DataOutputStream(this.f.getOutputStream());
        try {
            dataOutputStream.write(bArrA, 0, bArrA.length);
            dataOutputStream.flush();
            byte[] bArrA2 = a(this.f);
            if (bArrA2 != null) {
                a aVarB = b(this.f, bArrA2);
                g.a(dataOutputStream);
                g();
                return aVarB;
            }
        } catch (Throwable unused) {
        }
        g.a(dataOutputStream);
        g();
        return new a(false, null);
    }

    private static void a(HttpURLConnection httpURLConnection, byte[] bArr) throws Exception {
        if (httpURLConnection == null) {
            return;
        }
        byte[] bArr2 = new byte[0];
        if (bArr == null) {
            bArr = bArr2;
        }
        httpURLConnection.addRequestProperty("GT_C_T", "1");
        httpURLConnection.addRequestProperty("GT_C_K", new String(h.c()));
        httpURLConnection.addRequestProperty("GT_C_V", h.f());
        String strValueOf = String.valueOf(System.currentTimeMillis());
        String strA = h.a(strValueOf, bArr);
        httpURLConnection.addRequestProperty("GT_T", strValueOf);
        httpURLConnection.addRequestProperty("GT_C_S", strA);
    }

    private void a(byte[] bArr) {
        try {
            this.b.a(bArr);
        } catch (Exception unused) {
        }
    }

    private static byte[] a(HttpURLConnection httpURLConnection) throws Exception {
        InputStream inputStream;
        try {
            inputStream = httpURLConnection.getInputStream();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (httpURLConnection.getResponseCode() == 200) {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i == -1) {
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            g.a(inputStream);
                            return byteArray;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                th = th;
                g.a(inputStream);
                throw th;
            }
        } catch (Exception unused2) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
        g.a(inputStream);
        return null;
    }

    private static byte[] a(byte[] bArr, HttpURLConnection httpURLConnection) {
        String requestProperty;
        try {
            if (!httpURLConnection.getRequestProperties().containsKey("GT_C_S") || (requestProperty = httpURLConnection.getRequestProperty("GT_C_S")) == null) {
                return null;
            }
            return h.a(bArr, h.c(requestProperty.getBytes()));
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("HttpTask|" + th.toString(), new Object[0]);
            return null;
        }
    }

    private a b(HttpURLConnection httpURLConnection, byte[] bArr) {
        try {
            if (!(this.b.k && com.igexin.push.f.a.a())) {
                return new a(false, bArr);
            }
            String headerField = httpURLConnection.getHeaderField("GT_ERR");
            if (headerField != null && headerField.equals("0")) {
                String headerField2 = httpURLConnection.getHeaderField("GT_T");
                if (headerField2 == null) {
                    com.igexin.b.a.c.a.a("HttpTask|GT_T = null", new Object[0]);
                    return new a(true, null);
                }
                String headerField3 = httpURLConnection.getHeaderField("GT_C_S");
                if (headerField3 == null) {
                    com.igexin.b.a.c.a.a("HttpTask|GT_C_S = null", new Object[0]);
                    return new a(true, null);
                }
                byte[] bArrB = h.b(bArr, h.c(headerField2.getBytes()));
                String strA = h.a(headerField2, bArrB);
                if (strA != null && strA.equals(headerField3)) {
                    return new a(false, bArrB);
                }
                com.igexin.b.a.c.a.a("HttpTask|signature = null or error", new Object[0]);
                return new a(true, null);
            }
            com.igexin.b.a.c.a.a("HttpTask|GT_ERR = ".concat(String.valueOf(headerField)), new Object[0]);
            return new a(true, null);
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("HttpTask|" + th.toString(), new Object[0]);
            return new a(true, null);
        }
    }

    private HttpURLConnection b(String str) throws Exception {
        this.f = (HttpURLConnection) new URL(str).openConnection();
        this.f.setConnectTimeout(20000);
        this.f.setReadTimeout(20000);
        this.f.setRequestMethod("GET");
        this.f.setDoInput(true);
        a(this.f, (byte[]) null);
        return this.f;
    }

    private HttpURLConnection b(String str, byte[] bArr) throws Exception {
        this.f = (HttpURLConnection) new URL(str).openConnection();
        this.f.setDoInput(true);
        this.f.setDoOutput(true);
        this.f.setRequestMethod("POST");
        this.f.setUseCaches(false);
        this.f.setInstanceFollowRedirects(true);
        this.f.setRequestProperty("Content-Type", OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE);
        this.f.setConnectTimeout(20000);
        this.f.setReadTimeout(20000);
        a(this.f, bArr);
        return this.f;
    }

    private void g() {
        HttpURLConnection httpURLConnection = this.f;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
                this.f = null;
            } catch (Exception unused) {
            }
        }
    }

    private boolean h() {
        return this.b.k && com.igexin.push.f.a.a();
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void b_() throws Exception {
        super.b_();
        Process.setThreadPriority(10);
        b bVar = this.b;
        if (bVar == null || bVar.e == null || (this.b.f != null && this.b.f.length > com.igexin.push.config.d.E * 1024)) {
            g();
            com.igexin.b.a.c.a.a("HttpTask|run return ###", new Object[0]);
            return;
        }
        if (this.b.f != null && this.b.f.length > 0) {
            b bVar2 = this.b;
            bVar2.f = g.a(bVar2.f);
        }
        for (int i = 0; i < 3; i++) {
            a aVarA = this.b.f == null ? a(this.b.e) : a(this.b.e, this.b.f);
            if (!aVarA.a && aVarA.b != null) {
                try {
                    this.b.a(aVarA.b);
                    return;
                } catch (Exception unused) {
                    return;
                }
            } else {
                if (i == 2) {
                    this.b.a(new Exception("try up to limit"));
                }
            }
        }
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return -2147483638;
    }

    @Override // com.igexin.b.a.d.f
    public final void e() {
        g();
    }

    @Override // com.igexin.b.a.d.f
    public final void f() {
    }
}
