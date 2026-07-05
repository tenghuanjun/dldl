package com.huya.mtp.utils;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SystemUtils {
    public static boolean IsSystemSDKLessthanKit() {
        return Build.VERSION.SDK_INT < 19;
    }

    public static long getSDFreeSize() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static long getSDAllSize() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }
}
