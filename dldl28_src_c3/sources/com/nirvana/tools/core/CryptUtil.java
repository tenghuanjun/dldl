package com.nirvana.tools.core;

import com.tencent.connect.common.Constants;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class CryptUtil {
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final byte[] defaultIV = {48, 48, 48, 48, 48, 48, 48, 48};
    private static final String desAlgorithm = "DESede/CBC/NoPadding";
    private static final String desKeyAlgorithm = "DESede";

    public static class Base64 {
        private static char[] base64EncodeChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        private static byte[] base64DecodeChars = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

        private Base64() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:37:0x0076, code lost:
        
            if (r2 == (-1)) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0078, code lost:
        
            r1.write(r2 | ((r5 & 3) << 6));
            r2 = r4;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static byte[] decode(java.lang.String r8) {
            /*
                byte[] r8 = r8.getBytes()
                int r0 = r8.length
                java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
                r1.<init>(r0)
                r2 = 0
            Lb:
                if (r2 >= r0) goto L82
            Ld:
                byte[] r3 = com.nirvana.tools.core.CryptUtil.Base64.base64DecodeChars
                int r4 = r2 + 1
                r2 = r8[r2]
                r2 = r3[r2]
                r3 = -1
                if (r4 >= r0) goto L1d
                if (r2 == r3) goto L1b
                goto L1d
            L1b:
                r2 = r4
                goto Ld
            L1d:
                if (r2 == r3) goto L82
            L1f:
                byte[] r5 = com.nirvana.tools.core.CryptUtil.Base64.base64DecodeChars
                int r6 = r4 + 1
                r4 = r8[r4]
                r4 = r5[r4]
                if (r6 >= r0) goto L2e
                if (r4 == r3) goto L2c
                goto L2e
            L2c:
                r4 = r6
                goto L1f
            L2e:
                if (r4 == r3) goto L82
                int r2 = r2 << 2
                r5 = r4 & 48
                int r5 = r5 >>> 4
                r2 = r2 | r5
                r1.write(r2)
            L3a:
                int r2 = r6 + 1
                r5 = r8[r6]
                r6 = 61
                if (r5 != r6) goto L47
                byte[] r8 = r1.toByteArray()
                return r8
            L47:
                byte[] r7 = com.nirvana.tools.core.CryptUtil.Base64.base64DecodeChars
                r5 = r7[r5]
                if (r2 >= r0) goto L52
                if (r5 == r3) goto L50
                goto L52
            L50:
                r6 = r2
                goto L3a
            L52:
                if (r5 == r3) goto L82
                r4 = r4 & 15
                int r4 = r4 << 4
                r7 = r5 & 60
                int r7 = r7 >>> 2
                r4 = r4 | r7
                r1.write(r4)
            L60:
                int r4 = r2 + 1
                r2 = r8[r2]
                if (r2 != r6) goto L6b
                byte[] r8 = r1.toByteArray()
                return r8
            L6b:
                byte[] r7 = com.nirvana.tools.core.CryptUtil.Base64.base64DecodeChars
                r2 = r7[r2]
                if (r4 >= r0) goto L76
                if (r2 == r3) goto L74
                goto L76
            L74:
                r2 = r4
                goto L60
            L76:
                if (r2 == r3) goto L82
                r3 = r5 & 3
                int r3 = r3 << 6
                r2 = r2 | r3
                r1.write(r2)
                r2 = r4
                goto Lb
            L82:
                byte[] r8 = r1.toByteArray()
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nirvana.tools.core.CryptUtil.Base64.decode(java.lang.String):byte[]");
        }

        public static String encode(byte[] bArr) {
            String str;
            StringBuffer stringBuffer = new StringBuffer();
            int length = bArr.length;
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                byte b = bArr[i];
                int i3 = b & UByte.MAX_VALUE;
                if (i2 == length) {
                    stringBuffer.append(base64EncodeChars[i3 >>> 2]);
                    stringBuffer.append(base64EncodeChars[(b & 3) << 4]);
                    str = "==";
                } else {
                    int i4 = i + 2;
                    byte b2 = bArr[i2];
                    if (i4 == length) {
                        stringBuffer.append(base64EncodeChars[i3 >>> 2]);
                        stringBuffer.append(base64EncodeChars[((b & 3) << 4) | ((b2 & 240) >>> 4)]);
                        stringBuffer.append(base64EncodeChars[(b2 & 15) << 2]);
                        str = "=";
                    } else {
                        i += 3;
                        byte b3 = bArr[i4];
                        stringBuffer.append(base64EncodeChars[i3 >>> 2]);
                        stringBuffer.append(base64EncodeChars[((b & 3) << 4) | ((b2 & 240) >>> 4)]);
                        stringBuffer.append(base64EncodeChars[((b2 & 15) << 2) | ((b3 & 192) >>> 6)]);
                        stringBuffer.append(base64EncodeChars[b3 & 63]);
                    }
                }
                stringBuffer.append(str);
                break;
            }
            return stringBuffer.toString();
        }
    }

    private static IvParameterSpec IvGenerator(byte[] bArr) {
        return new IvParameterSpec(bArr);
    }

    private static SecretKey KeyGenerator(String str) throws Exception {
        return new SecretKeySpec(md5Hex(str).substring(0, 24).getBytes(Constants.ENC_UTF_8), desKeyAlgorithm);
    }

    public static byte[] cryptBy3Des(String str, int i, byte[] bArr, byte[] bArr2) throws Exception {
        SecretKey secretKeyKeyGenerator = KeyGenerator(str);
        IvParameterSpec ivParameterSpecIvGenerator = bArr == null ? IvGenerator(defaultIV) : IvGenerator(bArr);
        Cipher cipher = Cipher.getInstance(desAlgorithm);
        cipher.init(i, secretKeyKeyGenerator, ivParameterSpecIvGenerator);
        return cipher.doFinal(bArr2);
    }

    public static byte[] decryptBy3Des(byte[] bArr, String str) throws Exception {
        return cryptBy3Des(str, 2, null, bArr);
    }

    public static String decryptBy3DesAndBase64(String str, String str2) throws Exception {
        return decryptBy3DesAndBase64(str, str2, Constants.ENC_UTF_8);
    }

    public static String decryptBy3DesAndBase64(String str, String str2, String str3) throws Exception {
        return new String(decryptBy3Des(Base64.decode(str), str2), str3).trim();
    }

    public static char[] encodeHex(byte[] bArr) {
        char[] cArr = new char[bArr.length << 1];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = DIGITS;
            cArr[i] = cArr2[(b & 240) >>> 4];
            i += 2;
            cArr[i2] = cArr2[b & 15];
        }
        return cArr;
    }

    public static byte[] encryptBy3Des(byte[] bArr, String str) throws Exception {
        return cryptBy3Des(str, 1, null, bArr);
    }

    public static String encryptBy3DesAndBase64(String str, String str2) throws Exception {
        return encryptBy3DesAndBase64(str, str2, Constants.ENC_UTF_8);
    }

    public static String encryptBy3DesAndBase64(String str, String str2, String str3) throws Exception {
        int length = str.getBytes(str3).length % 8;
        if (length != 0) {
            int i = 8 - length;
            StringBuffer stringBuffer = new StringBuffer(str);
            for (int i2 = 0; i2 < i; i2++) {
                stringBuffer.append(' ');
            }
            str = new String(stringBuffer);
        }
        return Base64.encode(encryptBy3Des(str.getBytes(str3), str2)).replaceAll("[\\n\\r]", "");
    }

    public static String md5Hex(String str) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(str.getBytes(Constants.ENC_UTF_8));
        return new String(encodeHex(messageDigest.digest()));
    }
}
