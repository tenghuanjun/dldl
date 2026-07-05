package com.huya.force.gpuimage;

import android.opengl.GLES20;
import com.huya.force.gles.GlUtil;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GlHelper {
    public static final FloatBuffer FULL_RECTANGLE_BUF;
    private static final float[] FULL_RECTANGLE_COORDS;
    private static final float[] FULL_RECTANGLE_TEX_COORDS = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
    public static final FloatBuffer FULL_RECTANGLE_TEX_BUF = GlUtil.createFloatBuffer(FULL_RECTANGLE_TEX_COORDS);

    static {
        float[] fArr = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
        FULL_RECTANGLE_COORDS = fArr;
        FULL_RECTANGLE_BUF = GlUtil.createFloatBuffer(fArr);
    }

    public static int createTexture(int i, int i2, int i3) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(i, iArr[0]);
        GLES20.glTexParameterf(i, 10241, 9729.0f);
        GLES20.glTexParameterf(i, 10240, 9729.0f);
        GLES20.glTexParameteri(i, 10242, 33071);
        GLES20.glTexParameteri(i, 10243, 33071);
        GLES20.glTexImage2D(i, 0, 6408, i2, i3, 0, 6408, 5121, null);
        return iArr[0];
    }

    public static int createFrameBuffer() {
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        int i = iArr[0];
        GlUtil.checkGlError("init fbo");
        return i;
    }

    public static void bindFrameBufferWidthTexture(int i, int i2, int i3, int i4) {
        GLES20.glBindFramebuffer(i, i2);
        GLES20.glFramebufferTexture2D(i, 36064, i3, i4, 0);
        GLES20.glBindFramebuffer(i, 0);
        GlUtil.checkGlError("bind fbo and texture");
    }

    public static int deleteTexture(int i) {
        if (i != -1) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
        }
        return -1;
    }

    public static int deleteFrameBuffer(int i) {
        if (i != -1) {
            GLES20.glDeleteFramebuffers(1, new int[]{i}, 0);
        }
        return -1;
    }

    public static float[] newIdentityTransform() {
        int length = GlUtil.IDENTITY_MATRIX.length;
        float[] fArr = new float[length];
        System.arraycopy(GlUtil.IDENTITY_MATRIX, 0, fArr, 0, length);
        return fArr;
    }
}
