package com.huya.mtp.utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EncryptCipher {
    private RC4 mDecrypt;
    private RC4 mEncrypt;

    public EncryptCipher(byte[] bArr) {
        this.mEncrypt = new RC4(bArr);
        this.mDecrypt = new RC4(bArr);
    }

    public byte[] encrypt(byte[] bArr) {
        this.mEncrypt.doFinal(bArr);
        return bArr;
    }

    public byte[] decrypt(byte[] bArr) {
        this.mDecrypt.doFinal(bArr);
        return bArr;
    }

    public void encrypt(byte[] bArr, int i, int i2) {
        this.mEncrypt.doFinal(bArr, i, i2);
    }

    public void decrypt(byte[] bArr, int i, int i2) {
        this.mDecrypt.doFinal(bArr, i, i2);
    }
}
