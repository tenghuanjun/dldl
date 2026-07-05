package cn.thinkingdata.android.utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class a {
    private static final char[] a = new char[64];
    private static final byte[] b;

    static {
        char c = 'A';
        int i = 0;
        while (c <= 'Z') {
            a[i] = c;
            c = (char) (c + 1);
            i++;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            a[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = '0';
        while (c3 <= '9') {
            a[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        char[] cArr = a;
        cArr[i] = '+';
        cArr[i + 1] = '/';
        b = new byte[128];
        int i2 = 0;
        while (true) {
            byte[] bArr = b;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        for (int i3 = 0; i3 < 64; i3++) {
            b[a[i3]] = (byte) i3;
        }
    }

    public static char[] a(byte[] bArr) {
        return a(bArr, bArr.length);
    }

    public static char[] a(byte[] bArr, int i) {
        int i2;
        int i3;
        int i4 = ((i * 4) + 2) / 3;
        char[] cArr = new char[((i + 2) / 3) * 4];
        int i5 = 0;
        int i6 = 0;
        while (i5 < i) {
            int i7 = i5 + 1;
            int i8 = bArr[i5] & 255;
            if (i7 < i) {
                i2 = bArr[i7] & 255;
                i7++;
            } else {
                i2 = 0;
            }
            if (i7 < i) {
                i3 = bArr[i7] & 255;
                i7++;
            } else {
                i3 = 0;
            }
            int i9 = i8 >>> 2;
            int i10 = ((i8 & 3) << 4) | (i2 >>> 4);
            int i11 = ((i2 & 15) << 2) | (i3 >>> 6);
            int i12 = i6 + 1;
            char[] cArr2 = a;
            cArr[i6] = cArr2[i9];
            int i13 = i12 + 1;
            cArr[i12] = cArr2[i10];
            char c = '=';
            cArr[i13] = i13 < i4 ? cArr2[i11] : '=';
            int i14 = i13 + 1;
            int i15 = i3 & 63;
            if (i14 < i4) {
                c = a[i15];
            }
            cArr[i14] = c;
            i6 = i14 + 1;
            i5 = i7;
        }
        return cArr;
    }
}
