package com.duowan.ark.util.glutils.utils;

import android.app.ActivityManager;
import android.content.Context;
import com.sqwan.bugless.util.FileUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GlUtils {
    public static String getOpenGLVersion(Context context) {
        int i = ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion;
        return String.valueOf(((-65536) & i) >> 16) + FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(i & 65535);
    }
}
