package com.igexin.push.e.a;

import android.os.Process;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.igexin.b.a.b.g;
import com.igexin.b.a.d.f;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class e extends f {
    public static final int a = -2147483638;
    private static final String c = "SimpleHttpTask";
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

    private e(b bVar) {
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
            this.f = this.f;
            byte[] bArrA = a(this.f);
            if (bArrA != null) {
                a aVarB = b(bArrA);
                g();
                return aVarB;
            }
        } catch (Throwable th) {
            g();
            throw th;
        }
        g();
        return new a(false, null);
    }

    private a a(String str, byte[] bArr) throws Throwable {
        DataOutputStream dataOutputStream;
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
            this.f = this.f;
        } catch (Throwable th) {
            th = th;
        }
        if (bArr == null) {
            a aVar = new a(true, null);
            g.a((Closeable) null);
            g();
            return aVar;
        }
        byte[] bArrB = com.igexin.b.b.a.b(bArr);
        this.f.connect();
        dataOutputStream = new DataOutputStream(this.f.getOutputStream());
        try {
            dataOutputStream.write(bArrB, 0, bArrB.length);
            dataOutputStream.flush();
            byte[] bArrA = a(this.f);
            if (bArrA != null) {
                a aVarB = b(bArrA);
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

    private void a(byte[] bArr) {
        try {
            this.b.a(bArr);
        } catch (Exception unused) {
        }
    }

    private static byte[] a(HttpURLConnection httpURLConnection) throws Exception {
        InputStream errorStream;
        InputStream inputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (httpURLConnection.getResponseCode() == 200) {
                errorStream = httpURLConnection.getInputStream();
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = errorStream.read(bArr);
                        if (i == -1) {
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            g.a(errorStream);
                            return byteArray;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                } catch (Exception unused) {
                } catch (Throwable th) {
                    inputStream = errorStream;
                    th = th;
                    g.a(inputStream);
                    throw th;
                }
            } else {
                errorStream = httpURLConnection.getErrorStream();
            }
        } catch (Exception unused2) {
            errorStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
        g.a(errorStream);
        return null;
    }

    private a b(byte[] bArr) {
        try {
            return new a(false, bArr);
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("SimpleHttpTask|" + th.toString(), new Object[0]);
            return new a(true, null);
        }
    }

    private HttpURLConnection b(String str) throws Exception {
        this.f = (HttpURLConnection) new URL(str).openConnection();
        this.f.setDoInput(true);
        this.f.setDoOutput(true);
        this.f.setRequestMethod("POST");
        this.f.setUseCaches(false);
        this.f.setInstanceFollowRedirects(true);
        this.f.setRequestProperty("Content-Type", OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE);
        this.f.setConnectTimeout(20000);
        this.f.setReadTimeout(20000);
        return this.f;
    }

    private HttpURLConnection c(String str) throws Exception {
        this.f = (HttpURLConnection) new URL(str).openConnection();
        this.f.setConnectTimeout(20000);
        this.f.setReadTimeout(20000);
        this.f.setRequestMethod("GET");
        this.f.setDoInput(true);
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
        if (bVar == null || bVar.e == null) {
            g();
            com.igexin.b.a.c.a.a("SimpleHttpTask|run return ###", new Object[0]);
            return;
        }
        for (int i = 0; i < 3; i++) {
            a aVarA = this.b.f == null ? a(this.b.e) : a(this.b.e, this.b.f);
            if (aVarA.b != null) {
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
