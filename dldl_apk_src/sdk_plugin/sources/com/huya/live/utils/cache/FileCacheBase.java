package com.huya.live.utils.cache;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import com.duowan.auk.ArkValue;
import com.jakewharton.disklrucache.DiskLruCache;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FileCacheBase {
    public static File getDiskCacheDir(Context context, String str) {
        String path;
        String str2;
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            path = context.getExternalCacheDir().getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        if (ArkValue.debuggable()) {
            str2 = "testEnv" + File.separator;
        } else {
            str2 = "";
        }
        return new File(path + File.separator + str2 + str);
    }

    public static int getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static DiskLruCache openDiskLruCache(Context context, String str, int i, long j) {
        if (context == null) {
            return null;
        }
        try {
            File diskCacheDir = getDiskCacheDir(context, str);
            if (!diskCacheDir.exists()) {
                diskCacheDir.mkdirs();
            }
            return DiskLruCache.open(diskCacheDir, getAppVersion(context), i, j);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DiskLruCache openDiskLruCache(Context context, String str, int i, int i2, long j) {
        if (context == null) {
            return null;
        }
        try {
            File diskCacheDir = getDiskCacheDir(context, str);
            if (!diskCacheDir.exists()) {
                diskCacheDir.mkdirs();
            }
            return DiskLruCache.open(diskCacheDir, i, i2, j);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
