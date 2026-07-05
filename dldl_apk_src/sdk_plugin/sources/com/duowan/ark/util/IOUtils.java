package com.duowan.ark.util;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class IOUtils {
    private static final String TAG = "IOUtils";

    private IOUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static File getDir(File file, String... strArr) {
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            File file2 = new File(file, strArr[i]);
            if (!file2.exists() || !file2.isDirectory()) {
                return null;
            }
            i++;
            file = file2;
        }
        return file;
    }

    public static File getFile(File file, String str, String... strArr) {
        File dir = getDir(file, strArr);
        if (dir == null) {
            return null;
        }
        File file2 = new File(dir, str);
        if (!file2.exists() || file2.isDirectory()) {
            return null;
        }
        return file2;
    }

    public static File createDirIfNoExist(File file, String... strArr) {
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            File file2 = new File(file, strArr[i]);
            if (file2.exists() && !file2.isDirectory()) {
                return null;
            }
            if (!file2.exists() && !file2.mkdir()) {
                return null;
            }
            i++;
            file = file2;
        }
        return file;
    }

    public static File createFileIfNoExist(File file, String str, String... strArr) {
        File fileCreateDirIfNoExist = createDirIfNoExist(file, strArr);
        if (fileCreateDirIfNoExist == null) {
            return null;
        }
        File file2 = new File(fileCreateDirIfNoExist, str);
        if (file2.isDirectory()) {
            return null;
        }
        try {
            if (file2.createNewFile()) {
                return file2;
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static long getFileSize(File file) {
        long fileSize = 0;
        if (file == null) {
            return 0L;
        }
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                return 0L;
            }
            for (File file2 : fileArrListFiles) {
                fileSize += getFileSize(file2);
            }
            return fileSize;
        }
        return file.length();
    }

    public static boolean removeFile(File file, boolean z) {
        boolean zRemoveFile;
        File[] fileArrListFiles;
        if (file == null) {
            return false;
        }
        if (!file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
            zRemoveFile = true;
        } else {
            zRemoveFile = true;
            for (File file2 : fileArrListFiles) {
                zRemoveFile &= removeFile(file2, true);
            }
        }
        if (zRemoveFile) {
            return !z || file.delete();
        }
        return false;
    }

    public static byte[] readBytes(File file, String str, String... strArr) {
        return readBytes(getFile(file, str, strArr));
    }

    public static Bitmap readBitmap(File file) {
        return BitmapUtils.bytesToBitmap(readBytes(file));
    }

    public static String readString(File file) {
        return new String(readBytes(file));
    }

    public static byte[] readBytes(File file) {
        if (file == null) {
            return new byte[0];
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    fileInputStream.close();
                    return byteArray;
                }
            }
        } catch (IOException unused) {
            return new byte[0];
        }
    }

    public static boolean writeBytes(File file, String str, byte[] bArr, String... strArr) {
        return writeBytes(createFileIfNoExist(file, str, strArr), bArr);
    }

    public static boolean writeBytes(File file, byte[] bArr) {
        if (file == null) {
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static void close(Closeable... closeableArr) {
        if (closeableArr == null) {
            return;
        }
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    KLog.error(TAG, "Exception while close IO ,Caused by" + e.getMessage());
                }
            }
        }
    }

    public static void closeSilent(Closeable... closeableArr) {
        if (closeableArr == null) {
            return;
        }
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException unused) {
                }
            }
        }
    }
}
