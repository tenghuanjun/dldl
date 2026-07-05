package com.mobile.auth.gatewayauth.utils;

import com.alicom.tools.networking.RSA;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class h {
    public static String a(String str, String str2) throws Exception {
        try {
            PublicKey publicKeyA = a(str2);
            Cipher cipher = Cipher.getInstance(RSA.RSA_ALGORITHM);
            cipher.init(1, publicKeyA);
            return com.mobile.auth.gatewayauth.utils.security.a.a(cipher.doFinal(str.getBytes()));
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static PublicKey a(String str) throws Exception {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(com.mobile.auth.gatewayauth.utils.security.a.a(str)));
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }
}
