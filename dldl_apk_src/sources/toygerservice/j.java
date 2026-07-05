package toygerservice;

import java.security.interfaces.RSAPublicKey;
import java2jni_do_not_delete_this.java2jni_do_not_delete_this_library_zkfv_1ts_1tj;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public class j {
    public byte[] a;
    public byte[] b;
    public RSAPublicKey c;
    public boolean d;

    static {
        java2jni_do_not_delete_this_library_zkfv_1ts_1tj.loadLibrary();
    }

    public j(String str, boolean z) {
        this.d = true;
        try {
            this.c = n.a(str);
            byte[] bArrA = a(16);
            this.a = bArrA;
            this.b = n.a(this.c, bArrA);
            this.d = z;
        } catch (Exception unused) {
            throw new IllegalArgumentException("fail to init crypto manager");
        }
    }

    public final native byte[] a(int i);

    public native byte[] a(byte[] bArr);
}
