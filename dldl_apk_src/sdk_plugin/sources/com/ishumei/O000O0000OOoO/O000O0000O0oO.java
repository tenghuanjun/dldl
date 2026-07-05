package com.ishumei.O000O0000OOoO;

import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O0000O0oO {
    public static PublicKey O0000O000000oO(String str) throws IllegalAccessException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalAccessException("empty publicKeyStr");
        }
        return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decode(str, 0)))).getPublicKey();
    }

    public static byte[] O0000O000000oO(String str, byte[] bArr) {
        return O0000O000000oO(O0000O000000oO(str), bArr);
    }

    public static byte[] O0000O000000oO(PublicKey publicKey, byte[] bArr) throws Exception {
        if (publicKey == null) {
            throw new Exception("public key can not be null!");
        }
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("empty data");
        }
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, publicKey);
        return cipher.doFinal(bArr);
    }
}
