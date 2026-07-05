package com.huya.force.gpuimage.util;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GLShaderProgram {
    static final String Tag = "GLShaderProgram";
    private String fragmentSource;
    private String vertexSource;
    private int mProgram = -1;
    private int mShaderVertex = -1;
    private int mShaderFragment = -1;
    private final HashMap<String, Integer> mShaderHandleMap = new HashMap<>();

    public void setProgram(int i, int i2, Context context) {
        this.vertexSource = loadRawString(i, context);
        String strLoadRawString = loadRawString(i2, context);
        this.fragmentSource = strLoadRawString;
        setProgram(this.vertexSource, strLoadRawString);
    }

    public void setProgram(String str, String str2) {
        this.mShaderVertex = loadShader(35633, str);
        this.mShaderFragment = loadShader(35632, str2);
        int iGlCreateProgram = GLES20.glCreateProgram();
        if (iGlCreateProgram != 0) {
            GLES20.glAttachShader(iGlCreateProgram, this.mShaderVertex);
            GLES20.glAttachShader(iGlCreateProgram, this.mShaderFragment);
            GLES20.glLinkProgram(iGlCreateProgram);
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
            if (iArr[0] != 1) {
                String strGlGetProgramInfoLog = GLES20.glGetProgramInfoLog(iGlCreateProgram);
                destory();
                Log.e(Tag, "Link shader error: " + strGlGetProgramInfoLog);
                return;
            }
        }
        this.mProgram = iGlCreateProgram;
        this.mShaderHandleMap.clear();
    }

    public void useProgram() {
        GLES20.glUseProgram(this.mProgram);
    }

    public void destory() {
        if (this.mProgram != -1) {
            GLES20.glDeleteShader(this.mShaderVertex);
            GLES20.glDeleteShader(this.mShaderFragment);
            GLES20.glDeleteProgram(this.mProgram);
            this.mShaderFragment = -1;
            this.mShaderVertex = -1;
            this.mProgram = -1;
        }
    }

    public int programHandle() {
        return this.mProgram;
    }

    public int getHandle(String str) {
        if (this.mShaderHandleMap.containsKey(str)) {
            return this.mShaderHandleMap.get(str).intValue();
        }
        int iGlGetAttribLocation = GLES20.glGetAttribLocation(this.mProgram, str);
        if (iGlGetAttribLocation == -1) {
            iGlGetAttribLocation = GLES20.glGetUniformLocation(this.mProgram, str);
        }
        if (iGlGetAttribLocation == -1) {
            Log.d("GLSL shader", "Could not get attrib location for " + str);
        } else {
            this.mShaderHandleMap.put(str, Integer.valueOf(iGlGetAttribLocation));
        }
        return iGlGetAttribLocation;
    }

    public int[] getHandles(String... strArr) {
        int[] iArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            iArr[i] = getHandle(strArr[i]);
        }
        return iArr;
    }

    private int loadShader(int i, String str) {
        int iGlCreateShader = GLES20.glCreateShader(i);
        if (iGlCreateShader == 0) {
            return iGlCreateShader;
        }
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return iGlCreateShader;
        }
        String strGlGetShaderInfoLog = GLES20.glGetShaderInfoLog(iGlCreateShader);
        GLES20.glDeleteShader(iGlCreateShader);
        Log.e(Tag, "Compile shader error: " + strGlGetShaderInfoLog);
        return -1;
    }

    private String loadRawString(int i, Context context) {
        InputStream inputStreamOpenRawResource = context.getResources().openRawResource(i);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i2 = inputStreamOpenRawResource.read(bArr);
                if (i2 == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }

    public void setUniform1i(String str, int i) {
        GLES20.glUniform1i(getHandle(str), i);
    }

    public void setUniform2i(String str, int i, int i2) {
        GLES20.glUniform2i(getHandle(str), i, i2);
    }

    public void setUniform1f(String str, float f) {
        GLES20.glUniform1f(getHandle(str), f);
    }

    public void setUniform2f(String str, float f, float f2) {
        GLES20.glUniform2f(getHandle(str), f, f2);
    }

    public void setUniformMatrix4fv(String str, int i, boolean z, float[] fArr, int i2) {
        GLES20.glUniformMatrix4fv(getHandle(str), i, z, fArr, i2);
    }

    public void setVertexAttribPointer(String str, int i, int i2, boolean z, int i3, Buffer buffer) {
        int handle = getHandle(str);
        GLES20.glEnableVertexAttribArray(handle);
        GLES20.glVertexAttribPointer(handle, i, i2, z, i3, buffer);
    }

    public void setUniformTexture(String str, int i, int i2, int i3) {
        GLES20.glUniform1i(getHandle(str), i);
        GLES20.glActiveTexture(i + 33984);
        GLES20.glBindTexture(i3, i2);
    }
}
