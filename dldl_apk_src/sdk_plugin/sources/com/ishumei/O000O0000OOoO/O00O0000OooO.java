package com.ishumei.O000O0000OOoO;

import java.io.ByteArrayOutputStream;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O00O0000OooO {
    public static byte[] O0000O000000oO(byte[] bArr) {
        int iInflate;
        byte[] bArr2 = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        Inflater inflater = new Inflater();
        inflater.setInput(bArr, 0, bArr.length);
        while (!inflater.finished() && (iInflate = inflater.inflate(bArr2)) > 0) {
            byteArrayOutputStream.write(bArr2, 0, iInflate);
        }
        inflater.end();
        return byteArrayOutputStream.toByteArray();
    }
}
