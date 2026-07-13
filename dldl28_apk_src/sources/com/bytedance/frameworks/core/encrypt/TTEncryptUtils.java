package com.bytedance.frameworks.core.encrypt;

import com.bytedance.frameworks.encryptor.EncryptorUtil;

/* JADX INFO: loaded from: classes2.dex */
public class TTEncryptUtils {
    public static volatile boolean sUseNewEncrypt = true;

    public static byte[] encrypt(byte[] bArr, int i) {
        return EncryptorUtil.encrypt(bArr, i);
    }
}
