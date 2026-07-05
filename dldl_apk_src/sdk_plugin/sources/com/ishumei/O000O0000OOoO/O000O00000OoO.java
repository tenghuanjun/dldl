package com.ishumei.O000O0000OOoO;

import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000OoO {
    public static String O0000O000000oO(String str, byte[] bArr) throws IOException {
        try {
            return new String(O000O00000OoO(str, bArr), "utf-8");
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public static String O0000O000000oO(String str, byte[] bArr, int i) throws IOException {
        try {
            return new String(O000O00000OoO(str, bArr, i), "utf-8");
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public static byte[] O000O00000OoO(String str, byte[] bArr) throws IOException {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(2, new SecretKeySpec(str.getBytes("utf-8"), "DES"));
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public static byte[] O000O00000OoO(String str, byte[] bArr, int i) throws IOException {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(2, new SecretKeySpec(str.getBytes("utf-8"), "DES"));
            byte[] bArrDoFinal = cipher.doFinal(bArr);
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArrDoFinal, 0, bArr2, 0, i);
            return bArr2;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
