package com.huya.mtp.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StreamUtils {
    static final int BUFFER_SIZE = 4096;

    public static String inputStreamToString(InputStream inputStream) {
        byte[] bArrInputStreamToByteArray = inputStreamToByteArray(inputStream);
        if (bArrInputStreamToByteArray == null) {
            return null;
        }
        return new String(bArrInputStreamToByteArray);
    }

    public static byte[] inputStreamToByteArray(InputStream inputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStream.read(bArr, 0, 4096);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }
}
