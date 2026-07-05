package com.duowan.ark.util.glutils.utils;

import android.opengl.GLES20;
import com.duowan.ark.util.KLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CatchError {
    public static void catchError(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            KLog.error("CatchError", str + "  " + iGlGetError);
        }
    }
}
