package com.sy37sdk.utils;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class FileUtils {
    public static void write(Context context, String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput(str, 0);
            fileOutputStreamOpenFileOutput.write(str2.getBytes());
            fileOutputStreamOpenFileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String read(Context context, String str) {
        try {
            return readInStream(context.openFileInput(str));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String readInStream(FileInputStream fileInputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[512];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    byteArrayOutputStream.close();
                    fileInputStream.close();
                    return byteArrayOutputStream.toString();
                }
            }
        } catch (IOException e) {
            Log.i("FileTest", e.getMessage());
            return null;
        }
    }

    public static File createFile(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(str, str2 + str2);
    }

    public static long getFileSize(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.length();
        }
        return 0L;
    }

    public static String getFileSize(long j) {
        if (j <= 0) {
            return "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        float f = j / 1024.0f;
        if (f >= 1024.0f) {
            return decimalFormat.format(f / 1024.0f) + "M";
        }
        return decimalFormat.format(f) + "K";
    }

    public static String formatFileSize(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        if (j < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return decimalFormat.format(j) + "B";
        }
        if (j < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            return decimalFormat.format(j / 1024.0d) + "KB";
        }
        if (j < 1073741824) {
            return decimalFormat.format(j / 1048576.0d) + "MB";
        }
        return decimalFormat.format(j / 1.073741824E9d) + "G";
    }

    public static long getDirSize(File file) {
        long dirSize;
        long length = 0;
        if (file == null || !file.isDirectory()) {
            return 0L;
        }
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                dirSize = file2.length();
            } else if (file2.isDirectory()) {
                length += file2.length();
                dirSize = getDirSize(file2);
            }
            length += dirSize;
        }
        return length;
    }

    public long getFileList(File file) {
        File[] fileArrListFiles = file.listFiles();
        long length = fileArrListFiles.length;
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                length = (length + getFileList(file2)) - 1;
            }
        }
        return length;
    }

    public static byte[] toBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i = inputStream.read();
            if (i != -1) {
                byteArrayOutputStream.write(i);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static String moveFile(String str, String str2) {
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return "";
        }
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File file3 = new File(str2 + File.separator + file.getName());
        file.renameTo(file3);
        return file3.getAbsolutePath();
    }
}
