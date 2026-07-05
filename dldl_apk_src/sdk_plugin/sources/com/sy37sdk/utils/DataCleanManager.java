package com.sy37sdk.utils;

import android.content.Context;
import com.sqwan.common.util.EnvironmentUtils;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class DataCleanManager {
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File("/data/data/" + context.getPackageName() + "/databases"));
    }

    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/" + context.getPackageName() + "/shared_prefs"));
    }

    public static void cleanDatabaseByName(Context context, String str) {
        context.deleteDatabase(str);
    }

    public static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    public static void cleanExternalCache(Context context) {
        if (EnvironmentUtils.checkSdCardPermission(context)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    public static void cleanCustomCache(String str) {
        deleteFilesByDirectory(new File(str));
    }

    public static void cleanApplicationData(Context context, String... strArr) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        for (String str : strArr) {
            cleanCustomCache(str);
        }
    }

    private static void deleteFilesByDirectory(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                file2.delete();
            }
        }
    }
}
