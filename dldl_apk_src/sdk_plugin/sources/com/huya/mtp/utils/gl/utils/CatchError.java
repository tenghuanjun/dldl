package com.huya.mtp.utils.gl.utils;

import android.opengl.GLES20;
import com.huya.mtp.api.MTPApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CatchError {
    public static void catchError(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            MTPApi.LOGGER.error("CatchError", str + "  " + iGlGetError);
        }
    }
}
