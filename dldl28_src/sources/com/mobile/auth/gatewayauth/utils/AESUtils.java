package com.mobile.auth.gatewayauth.utils;

import com.ali.security.MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes3.dex */
public class AESUtils {
    private static final String CipherMode = "AES/CBC/PKCS7Padding";

    static {
        MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7.SLoad("pns-2.13.4-LogOnlineStandardCuumRelease_alijtca_plus");
    }

    private static native IvParameterSpec createIV(String str);

    private static native SecretKeySpec createKey(String str);

    public static native String decrypt(String str, String str2);

    public static native byte[] decryptBase642Byte(String str, String str2, String str3);

    public static native String decryptBase642String(String str, String str2, String str3);

    public static native byte[] decryptByte2Byte(byte[] bArr, String str, String str2);

    public static native String decryptByte2String(byte[] bArr, String str, String str2);

    public static native byte[] decryptString2Byte(String str, String str2, String str3);

    public static native String encrypt(String str, String str2);

    public static native String encryptByte2Base64(byte[] bArr, String str, String str2);

    public static native byte[] encryptByte2Byte(byte[] bArr, String str, String str2);

    public static native String encryptByte2String(byte[] bArr, String str, String str2);

    public static native String encryptString2Base64(String str, String str2, String str3);

    public static native byte[] encryptString2Byte(String str, String str2, String str3);

    public static native String encryptString2String(String str, String str2, String str3);
}
