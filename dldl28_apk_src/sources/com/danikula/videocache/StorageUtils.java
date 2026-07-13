package com.danikula.videocache;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class StorageUtils {
    private static final String INDIVIDUAL_DIR_NAME = "video-cache";

    public static File getIndividualCacheDirectory(Context context) {
        return new File(getCacheDirectory(context, true), INDIVIDUAL_DIR_NAME);
    }

    private static File getCacheDirectory(Context context, boolean z) {
        String externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException unused) {
            externalStorageState = "";
        }
        File externalCacheDir = (z && "mounted".equals(externalStorageState) && Build.VERSION.SDK_INT <= 28) ? getExternalCacheDir(context) : null;
        if (externalCacheDir == null) {
            externalCacheDir = context.getCacheDir();
        }
        if (externalCacheDir != null) {
            return externalCacheDir;
        }
        String str = "/data/data/" + context.getPackageName() + "/cache/";
        HttpProxyCacheDebuger.printfWarning("Can't define system cache directory! '" + str + "%s' will be used.");
        return new File(str);
    }

    private static File getExternalCacheDir(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        HttpProxyCacheDebuger.printfWarning("Unable to create external cache directory");
        return null;
    }
}
