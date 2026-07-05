package com.sq.webview.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FileUtils {
    public static final int FLAG_EXISTS = 2;
    public static final int FLAG_FAILED = 3;
    public static final int FLAG_SUCCESS = 1;

    public static int createFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return 2;
        }
        if (filePath.endsWith(File.separator)) {
            return 3;
        }
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            return 3;
        }
        try {
            return file.createNewFile() ? 1 : 3;
        } catch (IOException e) {
            e.printStackTrace();
            return 3;
        }
    }

    public static String read(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileName), "utf-8");
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                int i = inputStreamReader.read();
                if (i != -1) {
                    stringBuffer.append((char) i);
                } else {
                    fileReader.close();
                    inputStreamReader.close();
                    return stringBuffer.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean deleteFile(File dirFile) {
        if (!dirFile.exists()) {
            return false;
        }
        if (dirFile.isFile()) {
            return dirFile.delete();
        }
        File[] fileArrListFiles = dirFile.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        for (File file : fileArrListFiles) {
            deleteFile(file);
        }
        return dirFile.delete();
    }
}
