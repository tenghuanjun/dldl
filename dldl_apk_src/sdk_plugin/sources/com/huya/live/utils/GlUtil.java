package com.huya.live.utils;

import android.opengl.GLES20;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GlUtil {
    private static final String TAG = "GlUtil";

    public static void checkGlError(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            L.error(TAG, (Throwable) new RuntimeException(str + ": glError 0x" + Integer.toHexString(iGlGetError)));
        }
    }
}
