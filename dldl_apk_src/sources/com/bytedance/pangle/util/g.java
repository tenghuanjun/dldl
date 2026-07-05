package com.bytedance.pangle.util;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class g {
    static String a = null;
    static String b = null;
    static int c = 8192;

    public static void a(String str) {
        a(new File(str));
    }

    public static void a(File file) {
        File[] fileArrListFiles;
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                a(file2);
            }
        }
        file.delete();
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException unused) {
            }
        }
    }

    public static String a(Context context) {
        File parentFile;
        if (a == null && (parentFile = context.getCacheDir().getParentFile()) != null) {
            a = parentFile.getAbsolutePath();
        }
        return a;
    }

    public static String b(Context context) {
        File parentFile;
        if (b == null && (parentFile = context.getCacheDir().getParentFile()) != null) {
            try {
                b = parentFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    public static void a(String str, String str2) throws Exception {
        File file = new File(str);
        File file2 = new File(str2);
        if (!file.exists()) {
            throw new Exception("文件夹不存在");
        }
        if (!file.isDirectory()) {
            throw new Exception("源文件夹不是目录");
        }
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (!file2.isDirectory()) {
            throw new Exception("目标文件夹不是目录");
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            return;
        }
        for (File file3 : fileArrListFiles) {
            String str3 = file2 + File.separator + file3.getName();
            if (file3.isDirectory()) {
                a(file3.getAbsolutePath(), str3);
            } else if (!new File(str3).exists()) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file3));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str3));
                byte[] bArr = new byte[c];
                while (true) {
                    int i = bufferedInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    } else {
                        bufferedOutputStream.write(bArr, 0, i);
                    }
                }
                bufferedOutputStream.close();
                bufferedInputStream.close();
            }
        }
    }

    public static void a(RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2, long j, long j2, String str) throws IOException {
        byte[] bArr;
        long j3 = 0;
        if (j < 0 || j2 < 0 || j > j2) {
            throw new IOException("invalid offsets  ".concat(String.valueOf(str)));
        }
        if (j == j2) {
            return;
        }
        randomAccessFile.seek(j);
        long j4 = j2 - j;
        int i = c;
        if (j4 < i) {
            bArr = new byte[(int) j4];
        } else {
            bArr = new byte[i];
        }
        while (true) {
            int i2 = randomAccessFile.read(bArr);
            if (i2 == -1) {
                return;
            }
            randomAccessFile2.write(bArr, 0, i2);
            j3 += (long) i2;
            if (j3 == j4) {
                return;
            }
            if (((long) bArr.length) + j3 > j4) {
                bArr = new byte[(int) (j4 - j3)];
            }
        }
    }

    public static void b(String str, String str2) throws Exception {
        ZipInputStream zipInputStream;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str)));
        } catch (Exception e) {
            e = e;
            zipInputStream = null;
        }
        while (true) {
            try {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    String name = nextEntry.getName();
                    if (!name.contains("..")) {
                        if (nextEntry.isDirectory()) {
                            new File(str2 + File.separator + name).mkdirs();
                        } else {
                            File file = new File(str2 + File.separator + name);
                            if (file.exists()) {
                                file.delete();
                            } else {
                                file.getParentFile().mkdirs();
                            }
                            file.createNewFile();
                            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
                            try {
                                byte[] bArr = new byte[2048];
                                while (true) {
                                    int i = zipInputStream.read(bArr);
                                    if (i == -1) {
                                        break;
                                    } else {
                                        bufferedOutputStream2.write(bArr, 0, i);
                                    }
                                }
                                bufferedOutputStream2.flush();
                                bufferedOutputStream2.close();
                                bufferedOutputStream = bufferedOutputStream2;
                            } catch (Exception e2) {
                                e = e2;
                                bufferedOutputStream = bufferedOutputStream2;
                            }
                        }
                    }
                } else {
                    zipInputStream.close();
                    return;
                }
            } catch (Exception e3) {
                e = e3;
            }
            a(bufferedOutputStream);
            a(zipInputStream);
            throw e;
        }
    }

    public static boolean b(File file) {
        FileInputStream fileInputStream = null;
        if (file == null) {
            a((Closeable) null);
            return false;
        }
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[6];
                if (fileInputStream2.read(bArr) != 6) {
                    a(fileInputStream2);
                    return false;
                }
                byte[] bArr2 = {80, 75, 3, 4};
                for (int i = 0; i < 4; i++) {
                    if (bArr2[i] != bArr[i]) {
                        a(fileInputStream2);
                        return false;
                    }
                }
                if (bArr[4] == 0 && bArr[5] == 0) {
                    a(fileInputStream2);
                    return false;
                }
                if (bArr[4] == 20) {
                    if (bArr[5] == 3) {
                        a(fileInputStream2);
                        return true;
                    }
                }
                a(fileInputStream2);
                return false;
            } catch (Throwable unused) {
                fileInputStream = fileInputStream2;
                a(fileInputStream);
                return false;
            }
        } catch (Throwable unused2) {
        }
    }

    public static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream == null) {
            return;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        try {
            byte[] bArr = new byte[1048576];
            while (true) {
                int i = bufferedInputStream.read(bArr);
                if (i != -1) {
                    bufferedOutputStream.write(bArr, 0, i);
                } else {
                    bufferedOutputStream.flush();
                    return;
                }
            }
        } finally {
            bufferedInputStream.close();
            bufferedOutputStream.close();
        }
    }
}
