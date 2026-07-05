package com.igexin.push.extension.mod;

import com.igexin.a.c;
import com.igexin.b.a.c.a;
import com.igexin.b.a.c.a.d;
import com.igexin.push.core.p;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class SecurityUtils {
    private static final String SO_NAME = "getuiext3";
    public static final String TAG = "com.igexin.push.extension.mod.SecurityUtils";
    public static String errorMsg = "";
    public static boolean loadSuccess;

    static {
        try {
            try {
                a.a(TAG + "|load so by system start #######", new Object[0]);
                System.loadLibrary(SO_NAME);
                loadSuccess = true;
                d.a().a("load so = getuiext3 by system success");
                a.a(TAG + "|load so by system success ^_^", new Object[0]);
            } catch (UnsatisfiedLinkError e) {
                a.a(TAG + "|load so by system error = " + e.toString(), new Object[0]);
                StringBuilder sb = new StringBuilder();
                sb.append(e.getMessage());
                sb.append(" + ");
                errorMsg = sb.toString();
                a.a(TAG + "|load so by new start !!", new Object[0]);
                if (p.b != null) {
                    c.a().b().c().a(p.b, SO_NAME, null, new c.InterfaceC0054c() { // from class: com.igexin.push.extension.mod.SecurityUtils.1
                        @Override // com.igexin.a.c.InterfaceC0054c
                        public final void a() {
                            String str = SecurityUtils.TAG;
                            a.a(SecurityUtils.TAG + "|load so by new success ^_^", new Object[0]);
                            SecurityUtils.loadSuccess = true;
                            SecurityUtils.errorMsg = "";
                        }

                        @Override // com.igexin.a.c.InterfaceC0054c
                        public final void a(Throwable th) {
                            String str = SecurityUtils.TAG;
                            th.getMessage();
                            a.a(SecurityUtils.TAG + "|load so by new error = " + th.getMessage(), new Object[0]);
                            SecurityUtils.loadSuccess = false;
                            SecurityUtils.errorMsg += th.toString() + " + " + th.getMessage();
                        }
                    });
                    return;
                }
                a.a(TAG + "|load so by new context = null ~~~~", new Object[0]);
                loadSuccess = false;
                errorMsg = e.getMessage();
            }
        } catch (Throwable th) {
            a.a(TAG + "|load so error not UnsatisfiedLinkError", new Object[0]);
            a.a(TAG + "|load so error e = " + th.toString(), new Object[0]);
            loadSuccess = false;
            errorMsg += th.toString() + " + " + th.getMessage();
        }
    }

    public static native byte[] a();

    public static native byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native byte[] c(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native byte[] d(byte[] bArr);

    public static native byte[] e();

    public static native byte[] f(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native byte[] g(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native byte[] h(byte[] bArr);

    public static native byte[] i(byte[] bArr);

    public static native byte[] j();

    public static native byte[] k();

    public static native byte[] l(byte[] bArr, byte[] bArr2);

    public static native byte[] m(byte[] bArr, byte[] bArr2);
}
