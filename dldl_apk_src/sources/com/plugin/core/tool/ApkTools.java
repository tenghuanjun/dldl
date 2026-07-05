package com.plugin.core.tool;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class ApkTools {
    public static boolean isApkValid(String str) {
        return new File(str).exists() && isValidZipFile(str);
    }

    private static boolean isValidZipFile(String str) {
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(str);
            try {
                zipFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            zipFile = null;
        }
        return zipFile != null;
    }
}
