package com.huyaudbunify.util;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaFileUtils {
    public static boolean existSDCard() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Exception unused) {
            return false;
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static byte[] readAllFromFile(String str) {
        return readAllFromFile(new File(str));
    }

    public static byte[] readAllFromFile(File file) throws Throwable {
        FileInputStream fileInputStream;
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FileInputStream fileInputStream2 = null;
        byteArray = null;
        byte[] byteArray = null;
        try {
            bArr = new byte[256];
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException | Exception unused) {
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        while (true) {
            try {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            } catch (FileNotFoundException | Exception unused2) {
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                close(fileInputStream2);
                throw th;
            }
            close(fileInputStream);
            return byteArray;
        }
        byteArrayOutputStream.flush();
        byteArray = byteArrayOutputStream.toByteArray();
        close(fileInputStream);
        return byteArray;
    }

    public static void mkdir(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return;
            }
            file.mkdirs();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeAllToFile(File file, byte[] bArr) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                fileOutputStream2.write(bArr);
                fileOutputStream2.flush();
                close(fileOutputStream2);
            } catch (FileNotFoundException | Exception unused) {
                fileOutputStream = fileOutputStream2;
                close(fileOutputStream);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                close(fileOutputStream);
                throw th;
            }
        } catch (FileNotFoundException | Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static File getSDCardRootDir() {
        if (isExist(Environment.getExternalStorageDirectory())) {
            return Environment.getExternalStorageDirectory();
        }
        return new File("/sdcard/");
    }

    private static String getSDCardRootPath(Context context, boolean z) {
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            Method method2 = cls.getMethod("getPath", new Class[0]);
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Object objInvoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(objInvoke);
            for (int i = 0; i < length; i++) {
                Object obj = Array.get(objInvoke, i);
                String str = (String) method2.invoke(obj, new Object[0]);
                if (z == ((Boolean) method3.invoke(obj, new Object[0])).booleanValue()) {
                    return str;
                }
            }
            return "/sdcard/";
        } catch (Exception unused) {
            return "/sdcard/";
        }
    }

    public static boolean isExist(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }

    public static boolean isExist(File file) {
        return file.exists();
    }

    public static String getFileFromDir(String str, String str2, String str3) {
        File[] fileArrListFiles = new File(str).listFiles();
        String str4 = "";
        if (fileArrListFiles == null) {
            return "";
        }
        for (File file : fileArrListFiles) {
            String name = file.getName();
            if (file.isFile() && name.endsWith(str2) && !name.equals(str3)) {
                str4 = name;
            }
        }
        return str4;
    }
}
