package com.cy.yyjia.zhe28.util;

import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Utf8;

/* JADX INFO: loaded from: classes3.dex */
public class Base64 {
    private static final int BASELENGTH = 128;
    private static final int EIGHTBIT = 8;
    private static final int FOURBYTE = 4;
    private static final int LOOKUPLENGTH = 64;
    private static final char PAD = '=';
    private static final int SIGN = -128;
    private static final int SIXTEENBIT = 16;
    private static final int TWENTYFOURBITGROUP = 24;
    private static final boolean fDebug = false;
    private static final byte[] base64Alphabet = new byte[128];
    private static final char[] lookUpBase64Alphabet = new char[64];

    private static boolean isPad(char octect) {
        return octect == '=';
    }

    private static boolean isWhiteSpace(char octect) {
        return octect == ' ' || octect == '\r' || octect == '\n' || octect == '\t';
    }

    static {
        int i = 0;
        for (int i2 = 0; i2 < 128; i2++) {
            base64Alphabet[i2] = -1;
        }
        for (int i3 = 90; i3 >= 65; i3--) {
            base64Alphabet[i3] = (byte) (i3 - 65);
        }
        for (int i4 = 122; i4 >= 97; i4--) {
            base64Alphabet[i4] = (byte) (i4 - 71);
        }
        for (int i5 = 57; i5 >= 48; i5--) {
            base64Alphabet[i5] = (byte) (i5 + 4);
        }
        byte[] bArr = base64Alphabet;
        bArr[43] = 62;
        bArr[47] = Utf8.REPLACEMENT_BYTE;
        for (int i6 = 0; i6 <= 25; i6++) {
            lookUpBase64Alphabet[i6] = (char) (i6 + 65);
        }
        int i7 = 26;
        int i8 = 0;
        while (i7 <= 51) {
            lookUpBase64Alphabet[i7] = (char) (i8 + 97);
            i7++;
            i8++;
        }
        int i9 = 52;
        while (i9 <= 61) {
            lookUpBase64Alphabet[i9] = (char) (i + 48);
            i9++;
            i++;
        }
        char[] cArr = lookUpBase64Alphabet;
        cArr[62] = '+';
        cArr[63] = '/';
    }

    private static boolean isData(char octect) {
        return octect < 128 && base64Alphabet[octect] != -1;
    }

    public static String encode(byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        int length = binaryData.length * 8;
        if (length == 0) {
            return "";
        }
        int i = length % 24;
        int i2 = length / 24;
        char[] cArr = new char[(i != 0 ? i2 + 1 : i2) * 4];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            byte b = binaryData[i3];
            int i6 = i3 + 2;
            byte b2 = binaryData[i3 + 1];
            i3 += 3;
            byte b3 = binaryData[i6];
            byte b4 = (byte) (b2 & 15);
            byte b5 = (byte) (b & 3);
            byte b6 = (byte) ((b & ByteCompanionObject.MIN_VALUE) == 0 ? b >> 2 : (b >> 2) ^ KeyBoardKey.KeyboardKeyOem3);
            byte b7 = (byte) ((b2 & ByteCompanionObject.MIN_VALUE) == 0 ? b2 >> 4 : (b2 >> 4) ^ KeyBoardKey.KeyboardKeyOemAttn);
            int i7 = (b3 & ByteCompanionObject.MIN_VALUE) == 0 ? b3 >> 6 : (b3 >> 6) ^ KeyBoardKey.KeyboardKeyNoName;
            char[] cArr2 = lookUpBase64Alphabet;
            cArr[i4] = cArr2[b6];
            cArr[i4 + 1] = cArr2[b7 | (b5 << 4)];
            int i8 = i4 + 3;
            cArr[i4 + 2] = cArr2[(b4 << 2) | ((byte) i7)];
            i4 += 4;
            cArr[i8] = cArr2[b3 & Utf8.REPLACEMENT_BYTE];
        }
        if (i == 8) {
            byte b8 = binaryData[i3];
            byte b9 = (byte) (b8 & 3);
            int i9 = (b8 & ByteCompanionObject.MIN_VALUE) == 0 ? b8 >> 2 : (b8 >> 2) ^ KeyBoardKey.KeyboardKeyOem3;
            char[] cArr3 = lookUpBase64Alphabet;
            cArr[i4] = cArr3[(byte) i9];
            cArr[i4 + 1] = cArr3[b9 << 4];
            cArr[i4 + 2] = PAD;
            cArr[i4 + 3] = PAD;
        } else if (i == 16) {
            byte b10 = binaryData[i3];
            byte b11 = binaryData[i3 + 1];
            byte b12 = (byte) (b11 & 15);
            byte b13 = (byte) (b10 & 3);
            byte b14 = (byte) ((b10 & ByteCompanionObject.MIN_VALUE) == 0 ? b10 >> 2 : (b10 >> 2) ^ KeyBoardKey.KeyboardKeyOem3);
            int i10 = (b11 & ByteCompanionObject.MIN_VALUE) == 0 ? b11 >> 4 : (b11 >> 4) ^ KeyBoardKey.KeyboardKeyOemAttn;
            char[] cArr4 = lookUpBase64Alphabet;
            cArr[i4] = cArr4[b14];
            cArr[i4 + 1] = cArr4[((byte) i10) | (b13 << 4)];
            cArr[i4 + 2] = cArr4[b12 << 2];
            cArr[i4 + 3] = PAD;
        }
        return new String(cArr);
    }

    public static byte[] decode(String encoded) {
        if (encoded == null) {
            return null;
        }
        char[] charArray = encoded.toCharArray();
        int iRemoveWhiteSpace = removeWhiteSpace(charArray);
        if (iRemoveWhiteSpace % 4 != 0) {
            return null;
        }
        int i = iRemoveWhiteSpace / 4;
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i * 3];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < i - 1) {
            int i5 = i3 + 1;
            char c = charArray[i3];
            if (isData(c)) {
                int i6 = i3 + 2;
                char c2 = charArray[i5];
                if (isData(c2)) {
                    int i7 = i3 + 3;
                    char c3 = charArray[i6];
                    if (isData(c3)) {
                        i3 += 4;
                        char c4 = charArray[i7];
                        if (isData(c4)) {
                            byte[] bArr2 = base64Alphabet;
                            byte b = bArr2[c];
                            byte b2 = bArr2[c2];
                            byte b3 = bArr2[c3];
                            byte b4 = bArr2[c4];
                            bArr[i4] = (byte) ((b << 2) | (b2 >> 4));
                            int i8 = i4 + 2;
                            bArr[i4 + 1] = (byte) (((b2 & 15) << 4) | ((b3 >> 2) & 15));
                            i4 += 3;
                            bArr[i8] = (byte) ((b3 << 6) | b4);
                            i2++;
                        }
                    }
                }
            }
            return null;
        }
        int i9 = i3 + 1;
        char c5 = charArray[i3];
        if (!isData(c5)) {
            return null;
        }
        int i10 = i3 + 2;
        char c6 = charArray[i9];
        if (!isData(c6)) {
            return null;
        }
        byte[] bArr3 = base64Alphabet;
        byte b5 = bArr3[c5];
        byte b6 = bArr3[c6];
        char c7 = charArray[i10];
        char c8 = charArray[i3 + 3];
        if (!isData(c7) || !isData(c8)) {
            if (isPad(c7) && isPad(c8)) {
                if ((b6 & 15) != 0) {
                    return null;
                }
                int i11 = i2 * 3;
                byte[] bArr4 = new byte[i11 + 1];
                System.arraycopy(bArr, 0, bArr4, 0, i11);
                bArr4[i4] = (byte) ((b5 << 2) | (b6 >> 4));
                return bArr4;
            }
            if (isPad(c7) || !isPad(c8)) {
                return null;
            }
            byte b7 = bArr3[c7];
            if ((b7 & 3) != 0) {
                return null;
            }
            int i12 = i2 * 3;
            byte[] bArr5 = new byte[i12 + 2];
            System.arraycopy(bArr, 0, bArr5, 0, i12);
            bArr5[i4] = (byte) ((b5 << 2) | (b6 >> 4));
            bArr5[i4 + 1] = (byte) (((b7 >> 2) & 15) | ((b6 & 15) << 4));
            return bArr5;
        }
        byte b8 = bArr3[c7];
        byte b9 = bArr3[c8];
        bArr[i4] = (byte) ((b5 << 2) | (b6 >> 4));
        bArr[i4 + 1] = (byte) (((b6 & 15) << 4) | ((b8 >> 2) & 15));
        bArr[i4 + 2] = (byte) (b9 | (b8 << 6));
        return bArr;
    }

    private static int removeWhiteSpace(char[] data) {
        if (data == null) {
            return 0;
        }
        int length = data.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (!isWhiteSpace(data[i2])) {
                data[i] = data[i2];
                i++;
            }
        }
        return i;
    }
}
