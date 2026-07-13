package com.bytedance.frameworks.encryptor;

/* JADX INFO: loaded from: classes2.dex */
public class EncryptorUtil {
    private static native byte[] ttEncrypt(byte[] bArr, int i);

    static {
        try {
            System.loadLibrary("Encryptor");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(byte[] bArr, int i) {
        if (bArr != null && i > 0) {
            try {
                if (bArr.length == i) {
                    return ttEncrypt(bArr, i);
                }
            } catch (Throwable unused) {
            }
        }
        return null;
    }
}
