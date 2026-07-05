package com.huya.mtp.utils;

import com.huya.mtp.api.MTPApi;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ZipUtils {
    private static final int BUFFER = 1024;

    private ZipUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static File zipFiles(Collection<File> collection, String str) throws Throwable {
        FileInputStream fileInputStream;
        if (collection != null && collection.size() > 0 && str != null) {
            byte[] bArr = new byte[1024];
            try {
                MTPApi.LOGGER.verbose("feedback", "zipPath = " + str);
                File file = new File(str);
                if (file.exists() && !file.delete()) {
                    MTPApi.LOGGER.error("can not delete a exists file : " + str);
                    return null;
                }
                if (!file.createNewFile()) {
                    MTPApi.LOGGER.error("can not create a new zip file : " + str);
                    return null;
                }
                ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str));
                for (File file2 : collection) {
                    if (file2 != null && file2.exists()) {
                        zipOutputStream.putNextEntry(new ZipEntry(file2.getName()));
                        try {
                            fileInputStream = new FileInputStream(file2);
                            while (true) {
                                try {
                                    try {
                                        int i = fileInputStream.read(bArr);
                                        if (i <= 0) {
                                            break;
                                        }
                                        zipOutputStream.write(bArr, 0, i);
                                    } catch (Throwable th) {
                                        th = th;
                                        IOUtils.close(fileInputStream);
                                        throw th;
                                    }
                                } catch (FileNotFoundException unused) {
                                    MTPApi.LOGGER.error("feedback", "compress logs file not found");
                                    IOUtils.close(fileInputStream);
                                }
                            }
                            IOUtils.close(fileInputStream);
                        } catch (FileNotFoundException unused2) {
                            fileInputStream = null;
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream = null;
                        }
                    }
                }
                zipOutputStream.closeEntry();
                zipOutputStream.close();
                return file;
            } catch (Exception e) {
                MTPApi.LOGGER.error("feedback", "compress logs file error = " + e.getMessage());
            }
        }
        return null;
    }

    public static List<File> unzipFile(File file, File file2) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream = null;
        if (file == null || file2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            File file3 = new File(file2 + File.separator + zipEntryNextElement.getName());
            arrayList.add(file3);
            if (zipEntryNextElement.isDirectory()) {
                if (!IOUtils.createOrExistsDir(file3)) {
                    return null;
                }
            } else {
                if (!IOUtils.createOrExistsFile(file3)) {
                    return null;
                }
                try {
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(zipFile.getInputStream(zipEntryNextElement));
                    try {
                        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file3));
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int i = bufferedInputStream2.read(bArr);
                                if (i == -1) {
                                    break;
                                }
                                bufferedOutputStream.write(bArr, 0, i);
                            }
                            IOUtils.closeSilent(bufferedInputStream2);
                            IOUtils.closeSilent(bufferedOutputStream);
                        } catch (Throwable th) {
                            th = th;
                            bufferedInputStream = bufferedInputStream2;
                            IOUtils.closeSilent(bufferedInputStream);
                            IOUtils.closeSilent(bufferedOutputStream);
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
        return arrayList;
    }

    public static byte[] ungzip(byte[] bArr) throws IOException {
        GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        byte[] bArr2 = new byte[1024];
        while (true) {
            int i = gZIPInputStream.read(bArr2, 0, 1024);
            if (i != -1) {
                byteArrayOutputStream.write(bArr2, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static byte[] gzip(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        gzip(byteArrayInputStream, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        byteArrayInputStream.close();
        return byteArray;
    }

    public static void gzip(InputStream inputStream, OutputStream outputStream) throws IOException {
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr, 0, 1024);
            if (i != -1) {
                gZIPOutputStream.write(bArr, 0, i);
            } else {
                gZIPOutputStream.finish();
                gZIPOutputStream.flush();
                gZIPOutputStream.close();
                return;
            }
        }
    }
}
