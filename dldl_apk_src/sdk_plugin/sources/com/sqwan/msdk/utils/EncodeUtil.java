package com.sqwan.msdk.utils;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class EncodeUtil {
    private static final String encoding = "utf-8";
    private static final String iv = "01234567";

    public static String encode(String str, String str2) throws Exception {
        return encodeCore(Md5(str).substring(0, 24), str2);
    }

    public static String encodeCore(String str, String str2) throws Exception {
        SecretKey secretKeyGenerateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(str.getBytes()));
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        cipher.init(1, secretKeyGenerateSecret, new IvParameterSpec(iv.getBytes()));
        return Base64.encode(cipher.doFinal(str2.getBytes(encoding)));
    }

    public static String decode(String str, String str2) throws Exception {
        return decodeCore(Md5(str).substring(0, 24), str2);
    }

    public static String decodeCore(String str, String str2) throws Exception {
        SecretKey secretKeyGenerateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(str.getBytes()));
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        cipher.init(2, secretKeyGenerateSecret, new IvParameterSpec(iv.getBytes()));
        return new String(cipher.doFinal(Base64.decode(str2)), encoding);
    }

    public static String padding(String str) {
        try {
            byte[] bytes = str.getBytes("UTF8");
            int length = bytes.length + (8 - (bytes.length % 8));
            byte[] bArr = new byte[length];
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            for (int length2 = bytes.length; length2 < length; length2++) {
                bArr[length2] = 0;
            }
            return new String(bArr, "UTF8");
        } catch (UnsupportedEncodingException unused) {
            System.out.println("Crypter.padding UnsupportedEncodingException");
            return null;
        }
    }

    public static class Base64 {
        private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

        public static String encode(byte[] bArr) {
            int length = bArr.length;
            StringBuffer stringBuffer = new StringBuffer((bArr.length * 3) / 2);
            int i = length - 3;
            int i2 = 0;
            loop0: while (true) {
                int i3 = 0;
                while (i2 <= i) {
                    int i4 = ((bArr[i2] & 255) << 16) | ((bArr[i2 + 1] & 255) << 8) | (bArr[i2 + 2] & 255);
                    stringBuffer.append(legalChars[(i4 >> 18) & 63]);
                    stringBuffer.append(legalChars[(i4 >> 12) & 63]);
                    stringBuffer.append(legalChars[(i4 >> 6) & 63]);
                    stringBuffer.append(legalChars[i4 & 63]);
                    i2 += 3;
                    int i5 = i3 + 1;
                    if (i3 >= 14) {
                        break;
                    }
                    i3 = i5;
                }
                stringBuffer.append(" ");
            }
            int i6 = 0 + length;
            if (i2 == i6 - 2) {
                int i7 = ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2] & 255) << 16);
                stringBuffer.append(legalChars[(i7 >> 18) & 63]);
                stringBuffer.append(legalChars[(i7 >> 12) & 63]);
                stringBuffer.append(legalChars[(i7 >> 6) & 63]);
                stringBuffer.append(SimpleComparison.EQUAL_TO_OPERATION);
            } else if (i2 == i6 - 1) {
                int i8 = (bArr[i2] & 255) << 16;
                stringBuffer.append(legalChars[(i8 >> 18) & 63]);
                stringBuffer.append(legalChars[(i8 >> 12) & 63]);
                stringBuffer.append("==");
            }
            return stringBuffer.toString();
        }

        private static int decode(char c) {
            int i;
            if (c >= 'A' && c <= 'Z') {
                return c - 'A';
            }
            if (c >= 'a' && c <= 'z') {
                i = c - 'a';
            } else {
                if (c < '0' || c > '9') {
                    if (c == '+') {
                        return 62;
                    }
                    if (c == '/') {
                        return 63;
                    }
                    if (c == '=') {
                        return 0;
                    }
                    throw new RuntimeException("unexpected code: " + c);
                }
                i = (c - '0') + 26;
            }
            return i + 26;
        }

        public static byte[] decode(String str) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                decode(str, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    System.err.println("Error while decoding BASE64: " + e.toString());
                }
                return byteArray;
            } catch (IOException unused) {
                throw new RuntimeException();
            }
        }

        private static void decode(String str, OutputStream outputStream) throws IOException {
            int length = str.length();
            int i = 0;
            while (true) {
                if (i < length && str.charAt(i) <= ' ') {
                    i++;
                } else {
                    if (i == length) {
                        return;
                    }
                    int i2 = i + 2;
                    int i3 = i + 3;
                    int iDecode = (decode(str.charAt(i)) << 18) + (decode(str.charAt(i + 1)) << 12) + (decode(str.charAt(i2)) << 6) + decode(str.charAt(i3));
                    outputStream.write((iDecode >> 16) & 255);
                    if (str.charAt(i2) == '=') {
                        return;
                    }
                    outputStream.write((iDecode >> 8) & 255);
                    if (str.charAt(i3) == '=') {
                        return;
                    }
                    outputStream.write(iDecode & 255);
                    i += 4;
                }
            }
        }
    }

    public static String Md5(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b : bArrDigest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Huh, MD5 should be supported?", e2);
        }
    }
}
