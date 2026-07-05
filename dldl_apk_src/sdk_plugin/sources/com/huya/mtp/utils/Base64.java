package com.huya.mtp.utils;

import com.huya.mtp.api.MTPApi;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Base64 {
    private static final Base64 base64 = new Base64();
    protected final byte[] encodingTable = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    protected byte padding = 61;
    protected final byte[] decodingTable = new byte[128];

    private boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    public static String encodeToString(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            base64.encode(bArr, byteArrayOutputStream);
        } catch (IOException e) {
            MTPApi.LOGGER.error(Base64.class, e);
        }
        try {
            return new String(byteArrayOutputStream.toByteArray(), "US-ASCII");
        } catch (UnsupportedEncodingException e2) {
            throw new AssertionError(e2);
        }
    }

    public static byte[] decodeString(String str) {
        return decode(str.getBytes());
    }

    public static byte[] decode(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((bArr.length / 4) * 3);
        try {
            base64.decode(bArr, 0, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("exception decoding base64 string: " + e);
        }
    }

    public static int decode(byte[] bArr, OutputStream outputStream) {
        try {
            return base64.decode(bArr, 0, bArr.length, outputStream);
        } catch (IOException e) {
            throw new RuntimeException("exception decoding base64 string: " + e);
        }
    }

    protected void initialiseDecodingTable() {
        int i = 0;
        while (true) {
            byte[] bArr = this.encodingTable;
            if (i >= bArr.length) {
                return;
            }
            this.decodingTable[bArr[i]] = (byte) i;
            i++;
        }
    }

    public Base64() {
        initialiseDecodingTable();
    }

    public int encode(byte[] bArr, OutputStream outputStream) throws IOException {
        int length = bArr.length;
        int i = length % 3;
        int i2 = length - i;
        for (int i3 = 0; i3 < i2; i3 += 3) {
            int i4 = bArr[i3] & 255;
            int i5 = bArr[i3 + 1] & 255;
            int i6 = bArr[i3 + 2] & 255;
            outputStream.write(this.encodingTable[(i4 >>> 2) & 63]);
            outputStream.write(this.encodingTable[((i4 << 4) | (i5 >>> 4)) & 63]);
            outputStream.write(this.encodingTable[((i5 << 2) | (i6 >>> 6)) & 63]);
            outputStream.write(this.encodingTable[i6 & 63]);
        }
        if (i == 1) {
            int i7 = bArr[i2] & 255;
            outputStream.write(this.encodingTable[(i7 >>> 2) & 63]);
            outputStream.write(this.encodingTable[(i7 << 4) & 63]);
            outputStream.write(this.padding);
            outputStream.write(this.padding);
        } else if (i == 2) {
            int i8 = bArr[i2] & 255;
            int i9 = bArr[i2 + 1] & 255;
            outputStream.write(this.encodingTable[(i8 >>> 2) & 63]);
            outputStream.write(this.encodingTable[((i8 << 4) | (i9 >>> 4)) & 63]);
            outputStream.write(this.encodingTable[(i9 << 2) & 63]);
            outputStream.write(this.padding);
        }
        return ((i2 / 3) * 4) + (i != 0 ? 4 : 0);
    }

    public int decode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        int i3 = i + i2;
        while (i3 > i && ignore((char) bArr[i3 - 1])) {
            i3--;
        }
        int i4 = i3 - 4;
        int iNextI = nextI(bArr, i, i4);
        int i5 = 0;
        while (iNextI < i4) {
            int i6 = iNextI + 1;
            byte b = this.decodingTable[bArr[iNextI]];
            int iNextI2 = nextI(bArr, i6, i4);
            int i7 = iNextI2 + 1;
            byte b2 = this.decodingTable[bArr[iNextI2]];
            int iNextI3 = nextI(bArr, i7, i4);
            int i8 = iNextI3 + 1;
            byte b3 = this.decodingTable[bArr[iNextI3]];
            int iNextI4 = nextI(bArr, i8, i4);
            int i9 = iNextI4 + 1;
            byte b4 = this.decodingTable[bArr[iNextI4]];
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            outputStream.write((b3 << 6) | b4);
            i5 += 3;
            iNextI = nextI(bArr, i9, i4);
        }
        return i2 >= 4 ? i5 + decodeLastBlock(outputStream, (char) bArr[i4], (char) bArr[i3 - 3], (char) bArr[i3 - 2], (char) bArr[i3 - 1]) : i5;
    }

    private int nextI(byte[] bArr, int i, int i2) {
        while (i < i2 && ignore((char) bArr[i])) {
            i++;
        }
        return i;
    }

    private int decodeLastBlock(OutputStream outputStream, char c, char c2, char c3, char c4) throws IOException {
        char c5 = this.padding;
        if (c3 == c5) {
            byte[] bArr = this.decodingTable;
            outputStream.write((bArr[c] << 2) | (bArr[c2] >> 4));
            return 1;
        }
        if (c4 == c5) {
            byte[] bArr2 = this.decodingTable;
            byte b = bArr2[c];
            byte b2 = bArr2[c2];
            byte b3 = bArr2[c3];
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            return 2;
        }
        byte[] bArr3 = this.decodingTable;
        byte b4 = bArr3[c];
        byte b5 = bArr3[c2];
        byte b6 = bArr3[c3];
        byte b7 = bArr3[c4];
        outputStream.write((b4 << 2) | (b5 >> 4));
        outputStream.write((b5 << 4) | (b6 >> 2));
        outputStream.write((b6 << 6) | b7);
        return 3;
    }
}
