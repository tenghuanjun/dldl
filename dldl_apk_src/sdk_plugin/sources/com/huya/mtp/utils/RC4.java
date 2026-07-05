package com.huya.mtp.utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RC4 {
    private static final int STATE_LENGTH = 256;
    private byte[] engineState = null;
    private int x = 0;
    private int y = 0;
    private byte[] workingKey = null;

    public RC4(byte[] bArr) {
        setKey(bArr);
    }

    public void setKey(byte[] bArr) {
        this.workingKey = bArr;
        this.x = 0;
        this.y = 0;
        if (this.engineState == null) {
            this.engineState = new byte[256];
        }
        for (int i = 0; i < 256; i++) {
            this.engineState[i] = (byte) i;
        }
        int length = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            int i4 = bArr[length] & 255;
            byte[] bArr2 = this.engineState;
            i2 = (i4 + bArr2[i3] + i2) & 255;
            byte b = bArr2[i3];
            bArr2[i3] = bArr2[i2];
            bArr2[i2] = b;
            length = (length + 1) % bArr.length;
        }
    }

    public void doFinal(byte[] bArr) {
        doFinal(bArr, 0, bArr.length);
    }

    public void doFinal(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            int i4 = (this.x + 1) & 255;
            this.x = i4;
            byte[] bArr2 = this.engineState;
            int i5 = (bArr2[i4] + this.y) & 255;
            this.y = i5;
            byte b = bArr2[i4];
            bArr2[i4] = bArr2[i5];
            bArr2[i5] = b;
            bArr[i3] = (byte) (bArr2[(bArr2[i4] + bArr2[i5]) & 255] ^ bArr[i3]);
        }
    }

    public void reset() {
        setKey(this.workingKey);
    }
}
