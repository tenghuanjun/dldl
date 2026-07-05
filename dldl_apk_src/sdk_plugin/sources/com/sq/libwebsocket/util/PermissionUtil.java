package com.sq.libwebsocket.util;

import android.content.Context;
import android.os.Build;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PermissionUtil {
    public static boolean checkPermission(Context context, String str) {
        return Build.VERSION.SDK_INT < 23 || context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }
}
