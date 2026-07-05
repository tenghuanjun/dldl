package com.huya.mtp.utils.gl.utils;

import android.opengl.GLES20;
import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class KGLGetError {
    private static final String TAG = "KGLGetError";

    public static void debug(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            Log.d(TAG, str + " " + Integer.toHexString(iGlGetError));
        }
    }

    public static void info(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            Log.d(TAG, str + " " + Integer.toHexString(iGlGetError));
        }
    }

    public static void error(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            Log.e(TAG, str + " " + Integer.toHexString(iGlGetError));
        }
    }
}
