package com.plugin.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class FileUtil {
    private static final int MAX_BUFFER_SIZE = 10240;

    public static boolean copytoFile(InputStream inputStream, File file) throws Throwable {
        FileOutputStream fileOutputStream;
        boolean z = false;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[MAX_BUFFER_SIZE];
            while (true) {
                int i = inputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, i);
                fileOutputStream.flush();
            }
            z = true;
            fileOutputStream.close();
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                    throw th;
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
        return z;
    }
}
