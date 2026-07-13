package com.volcengine.j;

import android.text.TextUtils;
import com.volcengine.androidcloud.common.log.AcLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes3.dex */
public final class p {
    public static List<File> a(File file, File file2, String str) throws IOException {
        if (file == null || file2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        try {
            if (TextUtils.isEmpty(str)) {
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    String strReplace = zipEntryNextElement.getName().replace("\\", "/");
                    if (strReplace.contains("../")) {
                        AcLog.e("ZipUtils", "entryName: " + strReplace + " is dangerous!");
                    } else if (!a(file2, arrayList, zipFile, zipEntryNextElement, strReplace)) {
                        return arrayList;
                    }
                }
            } else {
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement2 = enumerationEntries.nextElement();
                    String strReplace2 = zipEntryNextElement2.getName().replace("\\", "/");
                    if (strReplace2.contains("../")) {
                        AcLog.e("ZipUtils", "entryName: " + strReplace2 + " is dangerous!");
                    } else if (strReplace2.contains(str) && !a(file2, arrayList, zipFile, zipEntryNextElement2, strReplace2)) {
                        return arrayList;
                    }
                }
            }
            return arrayList;
        } finally {
            zipFile.close();
        }
    }

    public static List<File> a(String str, String str2, String str3) {
        return a(i.c(str), i.c(str2), str3);
    }

    private static boolean a(File file, List<File> list, ZipFile zipFile, ZipEntry zipEntry, String str) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        File file2 = new File(file, str + "temp");
        File file3 = new File(file, str);
        AcLog.d("ZipUtils", "zip start " + str);
        if (file3.exists()) {
            AcLog.d("ZipUtils", "resultFile is exit");
            file3.delete();
        }
        if (zipEntry.isDirectory()) {
            return i.a(file3);
        }
        if (!i.b(file2)) {
            return false;
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int i = bufferedInputStream2.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, i);
                    }
                    h.a(bufferedInputStream2, bufferedOutputStream);
                    if (file2.renameTo(file3)) {
                        list.add(file3);
                        AcLog.d("ZipUtils", "rename success " + str);
                    } else {
                        AcLog.e("ZipUtils", "rename fail " + str);
                    }
                    return true;
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    h.a(bufferedInputStream, bufferedOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
        }
    }
}
