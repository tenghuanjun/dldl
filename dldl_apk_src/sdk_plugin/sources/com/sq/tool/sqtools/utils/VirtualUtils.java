package com.sq.tool.sqtools.utils;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VirtualUtils {
    private static final String DETECT_APK = ".apk";
    private static final String DETECT_DATA = "/data/";
    private static final String TAG = "VirtualDetector";

    public static String checkByPrivateFilePath(Context context) {
        File filesDir;
        if (context == null || (filesDir = context.getFilesDir()) == null) {
            return null;
        }
        return filesDir.getPath();
    }

    public static String checkByMultiByMaps(Context context) {
        BufferedReader bufferedReader;
        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/self/maps"));
        } catch (Exception unused) {
        }
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    break;
                }
                if (line.contains(DETECT_DATA) && line.contains(packageName) && line.endsWith(DETECT_APK)) {
                    String strSubstring = line.substring(line.indexOf(DETECT_DATA));
                    bufferedReader.close();
                    return strSubstring;
                }
            } finally {
            }
        }
        return null;
    }

    public static int checkByUid(Context context) {
        return Process.myUid();
    }
}
