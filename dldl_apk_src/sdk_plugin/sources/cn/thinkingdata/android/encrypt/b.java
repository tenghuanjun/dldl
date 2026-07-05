package cn.thinkingdata.android.encrypt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class b {
    private static final byte[] a = new byte[256];

    static {
        for (int i = 0; i < 256; i++) {
            a[i] = -1;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            a[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            a[i3] = (byte) ((i3 + 26) - 97);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            a[i4] = (byte) ((i4 + 52) - 48);
        }
        byte[] bArr = a;
        bArr[43] = 62;
        bArr[47] = 63;
    }

    public static byte[] a(String str) {
        return a(str.toCharArray());
    }

    public static byte[] a(char[] cArr) {
        int length = cArr.length;
        for (char c : cArr) {
            if (c > 255 || a[c] < 0) {
                length--;
            }
        }
        int i = (length / 4) * 3;
        int i2 = length % 4;
        if (i2 == 3) {
            i += 2;
        }
        if (i2 == 2) {
            i++;
        }
        byte[] bArr = new byte[i];
        int length2 = cArr.length;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < length2; i6++) {
            char c2 = cArr[i6];
            byte b = c2 > 255 ? (byte) -1 : a[c2];
            if (b >= 0) {
                i5 += 6;
                i4 = (i4 << 6) | b;
                if (i5 >= 8) {
                    i5 -= 8;
                    bArr[i3] = (byte) ((i4 >> i5) & 255);
                    i3++;
                }
            }
        }
        if (i3 == i) {
            return bArr;
        }
        throw new Error("Miscalculated data length (wrote " + i3 + " instead of " + i + ")");
    }
}
