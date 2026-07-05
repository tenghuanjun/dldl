package com.huya.mtp.encrypt;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class EncryptJni {
    public static native void jniDecryptFile(byte[] bArr, String str, String str2);

    public static native void jniEncryptFile(byte[] bArr, String str, String str2);

    public static native byte[] keyDecrypt2(Context context, byte[] bArr, byte[] bArr2);

    public static native byte[] keyEncrypt2(Context context, byte[] bArr, byte[] bArr2);

    EncryptJni() {
    }
}
