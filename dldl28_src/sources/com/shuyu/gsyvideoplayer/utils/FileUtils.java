package com.shuyu.gsyvideoplayer.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: classes3.dex */
public class FileUtils {
    public static final String NAME = "GSYVideo";
    public static final String NAME_TEST = "GSYVideoTest";
    private static final String SD_PATH = Environment.getExternalStorageDirectory().getPath();

    public static String getAppPath(String str) {
        return SD_PATH + File.separator + str + File.separator;
    }

    public static String getPath() {
        String appPath = getAppPath(NAME);
        File file = new File(appPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return appPath;
    }

    public static String getTestPath() {
        String appPath = getAppPath(NAME_TEST);
        File file = new File(appPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return appPath;
    }

    public static void deleteFiles(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (!file2.isDirectory() && file2.exists()) {
                    try {
                        file2.delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void saveBitmap(Bitmap bitmap, File file) {
        if (bitmap != null) {
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
                bitmap.recycle();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
