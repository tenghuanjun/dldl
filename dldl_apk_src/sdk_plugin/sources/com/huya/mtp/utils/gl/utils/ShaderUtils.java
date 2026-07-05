package com.huya.mtp.utils.gl.utils;

import android.opengl.GLES20;
import com.huya.mtp.api.MTPApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ShaderUtils {
    public static int compileShader(int i, String str) {
        CatchError.catchError("compileShader0");
        int iGlCreateShader = GLES20.glCreateShader(i);
        CatchError.catchError("compileShader1");
        GLES20.glShaderSource(iGlCreateShader, str);
        CatchError.catchError("compileShader2");
        GLES20.glCompileShader(iGlCreateShader);
        CatchError.catchError("compileShader3");
        int iCheckShaderStatus = checkShaderStatus(iGlCreateShader, 35713);
        CatchError.catchError("compileShader4");
        if (iCheckShaderStatus != 0) {
            return iGlCreateShader;
        }
        GLES20.glDeleteShader(iGlCreateShader);
        CatchError.catchError("compileShader5");
        return 0;
    }

    public static int linkShader(int... iArr) {
        int iGlCreateProgram = GLES20.glCreateProgram();
        for (int i : iArr) {
            GLES20.glAttachShader(iGlCreateProgram, i);
            CatchError.catchError("linkShader 0 shader " + i);
        }
        GLES20.glLinkProgram(iGlCreateProgram);
        if (checkProgramStatus(iGlCreateProgram, 35714) != 0) {
            return iGlCreateProgram;
        }
        deleteShaderProgram(iGlCreateProgram, iArr);
        return 0;
    }

    public static int validateShaderProgram(int i) {
        GLES20.glValidateProgram(i);
        return checkProgramStatus(i, 35715);
    }

    public static int deleteShaderProgram(int i, int... iArr) {
        for (int i2 : iArr) {
            if (GLES20.glIsShader(i2)) {
                GLES20.glDetachShader(i, i2);
                GLES20.glDeleteShader(i2);
                CatchError.catchError("deleteShaderProgram program " + i + " shader " + i2);
                if (checkShaderStatus(i2, 35712) == 0) {
                    CatchError.catchError("deleteShaderProgram check status " + i + " shader " + i2);
                }
            }
        }
        if (!GLES20.glIsProgram(i)) {
            return 1;
        }
        GLES20.glDeleteProgram(i);
        return checkProgramStatus(i, 35712) == 0 ? 0 : 1;
    }

    private static int checkProgramStatus(int i, int i2) {
        int[] iArr = {1};
        GLES20.glGetProgramiv(i, i2, iArr, 0);
        CatchError.catchError("checkProgramStatus");
        if (1 == iArr[0]) {
            return 1;
        }
        MTPApi.LOGGER.error("checkProgramStatus", GLES20.glGetProgramInfoLog(i) + " " + iArr[0]);
        CatchError.catchError("checkProgramStatus1");
        return 0;
    }

    private static int checkShaderStatus(int i, int i2) {
        int[] iArr = {1};
        GLES20.glGetShaderiv(i, i2, iArr, 0);
        CatchError.catchError("checkShaderStatus");
        if (1 == iArr[0]) {
            return 1;
        }
        MTPApi.LOGGER.error("checkShaderStatus", GLES20.glGetShaderInfoLog(i) + " " + iArr[0]);
        CatchError.catchError("checkShaderStatus1");
        return 0;
    }
}
