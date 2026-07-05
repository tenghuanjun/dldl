package com.huya.mtp.utils.gl.utils;

import android.opengl.GLES20;
import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class KGLShaderUtils {

    public enum ShaderType {
        Vertex(35633),
        Fragment(35632);

        private int mType;

        ShaderType(int i) {
            this.mType = i;
        }
    }

    public static int compileShader(ShaderType shaderType, String str) {
        int iGlCreateShader = GLES20.glCreateShader(shaderType.mType);
        KGLGetError.info("compile shader step 1");
        GLES20.glShaderSource(iGlCreateShader, str);
        KGLGetError.info("compile shader step 2");
        GLES20.glCompileShader(iGlCreateShader);
        KGLGetError.info("compile shader step 3");
        int iCheckShaderStatus = checkShaderStatus(iGlCreateShader, 35713);
        KGLGetError.info("compile shader step 4");
        if (iCheckShaderStatus != 0) {
            return iGlCreateShader;
        }
        GLES20.glDeleteShader(iGlCreateShader);
        KGLGetError.info("compile shader step 5");
        return 0;
    }

    public static int linkShader(int... iArr) {
        int iGlCreateProgram = GLES20.glCreateProgram();
        for (int i : iArr) {
            GLES20.glAttachShader(iGlCreateProgram, i);
            KGLGetError.error("link shader error " + i);
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
                KGLGetError.error("delete shader error " + i + " shader " + i2);
                if (checkShaderStatus(i2, 35712) == 0) {
                    KGLGetError.error("delete shader chech status error " + i + " shader " + i2);
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
        if (1 == iArr[0]) {
            return 1;
        }
        Log.e("checkProgramStatus", GLES20.glGetProgramInfoLog(i) + " " + iArr[0]);
        return 0;
    }

    private static int checkShaderStatus(int i, int i2) {
        int[] iArr = {1};
        GLES20.glGetShaderiv(i, i2, iArr, 0);
        if (1 == iArr[0]) {
            return 1;
        }
        Log.e("checkShaderStatus", GLES20.glGetShaderInfoLog(i) + " " + iArr[0]);
        return 0;
    }
}
