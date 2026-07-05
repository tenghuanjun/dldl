package com.huya.mtp.encrypt;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyEncrypt {
    private static final String TAG = "HyEncrypt";

    public static void init() {
    }

    static {
        try {
            System.loadLibrary("mtpencrypt");
        } catch (Throwable unused) {
            Log.d(TAG, "loadLibrary mtpencrypt error");
        }
    }

    public static byte[] encrypt(Context context, String str, byte[] bArr) {
        return EncryptJni.keyEncrypt2(context, str != null ? str.getBytes() : null, bArr);
    }

    public static byte[] decrypt(Context context, String str, byte[] bArr) {
        return EncryptJni.keyDecrypt2(context, str != null ? str.getBytes() : null, bArr);
    }

    public static void encryptFile(Context context, String str, String str2, String str3) {
        EncryptJni.jniEncryptFile(str == null ? null : str.getBytes(), str2, str3);
    }

    public static void decryptFile(Context context, String str, String str2, String str3) {
        EncryptJni.jniDecryptFile(str == null ? null : str.getBytes(), str2, str3);
    }
}
