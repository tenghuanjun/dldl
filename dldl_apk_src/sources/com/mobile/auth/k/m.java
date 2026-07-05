package com.mobile.auth.k;

import android.os.Build;
import com.alicom.tools.networking.RSA;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class m {
    private static final String a = "m";
    private static m b;
    private PublicKey c = null;

    private m() {
        try {
            b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static m a() {
        if (b == null) {
            b = new m();
        }
        return b;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.mobile.auth.l.a, com.mobile.auth.l.d] */
    private void b() throws Exception {
        try {
            this.c = (Build.VERSION.SDK_INT >= 28 ? KeyFactory.getInstance("RSA") : KeyFactory.getInstance("RSA", "BC")).generatePublic(new X509EncodedKeySpec(new com.mobile.auth.l.a().a("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/YHP9utFGOhGk7Xf5L7jOgQz5\nv2JKxdrIE3yzYsHoZJwzKC7Ttx380UZmBFzr5I1k6FFMn/YGXd4ts6UHT/nzsCIc\ngZlTTem7Pjdm1V9bJgQ6iQvFHsvT+vNgJ3wAIRd+iCMXm8y96yZhD2+SH5odBYS2\nZzwTYXBQDvB/rTfdjwIDAQAB")));
        } catch (IOException unused) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException unused2) {
            throw new Exception("公钥输入流为空");
        }
    }

    public String a(String str) {
        if (this.c == null) {
            f.a(a, "mServerPublicKey == null");
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance(RSA.RSA_ALGORITHM);
            cipher.init(1, this.c);
            return r.a(cipher.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
