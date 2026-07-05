package com.sq.webview.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ZipUtil {
    public static void unzip(String zipFile, String descDir, String replaceTarget) throws Exception {
        String parent;
        ZipFile zipFile2 = new ZipFile(new File(zipFile));
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile2.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            if (zipEntryNextElement.isDirectory()) {
                new File(descDir + File.separator + zipEntryNextElement.getName()).mkdirs();
            } else {
                File file = new File(descDir + File.separator + zipEntryNextElement.getName().replace(replaceTarget, ""));
                if (!file.exists() && (parent = file.getParent()) != null) {
                    new File(parent).mkdirs();
                }
                file.createNewFile();
                InputStream inputStream = zipFile2.getInputStream(zipEntryNextElement);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i == -1) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, i);
                    }
                }
                inputStream.close();
                fileOutputStream.close();
            }
        }
    }
}
