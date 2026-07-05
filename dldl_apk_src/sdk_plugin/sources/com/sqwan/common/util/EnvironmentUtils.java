package com.sqwan.common.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class EnvironmentUtils {
    private static boolean checkedSdCard;
    private static boolean sdCardPermission;

    public static boolean checkSdCardPermission(Context context) {
        boolean z = false;
        if (!SpUtils.get(context).getBoolean("sq_auth_handle")) {
            LogUtil.w("检测频繁获取，未同意，直接返回false");
            return false;
        }
        if (checkedSdCard) {
            return sdCardPermission;
        }
        if (Environment.getExternalStorageState().equals("mounted") && ActivityCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            z = true;
        }
        sdCardPermission = z;
        checkedSdCard = true;
        LogUtil.i("检测频繁获取，检查sd卡权限：" + sdCardPermission);
        return sdCardPermission;
    }

    private static File getExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    public static File getExternalStorageDir() {
        return Environment.getExternalStorageDirectory();
    }

    public static File getCommonDir(Context context) {
        if (checkSdCardPermission(context) && (Build.VERSION.SDK_INT < 29 || context.getApplicationInfo().targetSdkVersion < 29)) {
            return getExternalStorageDir();
        }
        return getExternalCacheDir(context);
    }

    public static String getCommonDirPath(Context context) {
        return getCommonDir(context).getAbsolutePath();
    }

    public static String getCommonDirPathEndWithSprit(Context context) {
        try {
            return getCommonDir(context).getAbsolutePath() + "/";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCommonSubDirPath(Context context, String str) {
        File file = new File(getCommonDir(context), str);
        try {
            if (!file.exists() || file.isDirectory()) {
                file.mkdirs();
            }
            return file.getAbsolutePath() + File.separator;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCommonSubDirPathEndWithSprit(Context context, String str) {
        try {
            return getCommonSubDirPath(context, str) + "/";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isFileExits(Context context, String str) {
        String str2 = getCommonDirPath(context) + File.separator + str;
        LogUtil.i(str2);
        File file = new File(str2);
        if (file.exists() && file.isDirectory()) {
            LogUtil.i(str + " directory is exists");
            return true;
        }
        LogUtil.i(str + " directory is not exists");
        return false;
    }
}
