package com.duowan.ark.util.glutils.tools;

import android.opengl.GLES20;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FBO {
    private static final int InValidId = -1;
    public static final int TYPE_RENDER_BUFFER_COLOR = 4;
    public static final int TYPE_RENDER_BUFFER_DEPTH = 2;
    public static final int TYPE_TEXTURE = 1;
    private int mFrameBufferId = -1;
    private int mTextureId = -1;
    private int mColorBufferId = -1;
    private int mDepthBufferId = -1;

    public static FBO createFBO(int i, int i2, int i3) {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(34024, iArr, 0);
        if (iArr[0] <= i || iArr[0] <= i2) {
            return null;
        }
        FBO fbo = new FBO(i, i2, i3);
        if (36053 == GLES20.glCheckFramebufferStatus(36160)) {
            return fbo;
        }
        fbo.delete();
        return null;
    }

    private FBO(int i, int i2, int i3) {
        if (1 == (i3 & 1)) {
            createFrameTexture(i, i2);
        }
        if (4 == (i3 & 4)) {
            createRenderBuffer(i, i2, 4);
        }
        if (2 == (i3 & 2)) {
            createRenderBuffer(i, i2, 2);
        }
        createFrameBuffer();
    }

    public int getFrameTextureId() {
        return this.mTextureId;
    }

    public void bind() {
        GLES20.glBindFramebuffer(36160, this.mFrameBufferId);
    }

    public void unBind() {
        GLES20.glBindFramebuffer(36160, 0);
    }

    public void delete() {
        int i = this.mTextureId;
        if (-1 != i) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
            this.mTextureId = -1;
        }
        int i2 = this.mColorBufferId;
        if (-1 != i2) {
            GLES20.glDeleteRenderbuffers(1, new int[]{i2}, 0);
            this.mColorBufferId = -1;
        }
        int i3 = this.mDepthBufferId;
        if (-1 != i3) {
            GLES20.glDeleteRenderbuffers(1, new int[]{i3}, 0);
            this.mDepthBufferId = -1;
        }
        int i4 = this.mFrameBufferId;
        if (-1 != i4) {
            GLES20.glDeleteFramebuffers(1, new int[]{i4}, 0);
            this.mFrameBufferId = -1;
        }
    }

    private void createFrameBuffer() {
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        int i = iArr[0];
        this.mFrameBufferId = i;
        GLES20.glBindFramebuffer(36160, i);
        int i2 = this.mTextureId;
        if (-1 != i2) {
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, i2, 0);
        }
        int i3 = this.mColorBufferId;
        if (-1 != i3) {
            GLES20.glFramebufferRenderbuffer(36160, 36064, 36161, i3);
        }
        int i4 = this.mDepthBufferId;
        if (-1 != i4) {
            GLES20.glFramebufferRenderbuffer(36160, 36096, 36161, i4);
        }
        GLES20.glBindFramebuffer(36160, 0);
    }

    private void createFrameTexture(int i, int i2) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i3 = iArr[0];
        this.mTextureId = i3;
        GLES20.glBindTexture(3553, i3);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
        GLES20.glBindTexture(3553, 0);
    }

    private void createRenderBuffer(int i, int i2, int i3) {
        int[] iArr = new int[1];
        GLES20.glGenRenderbuffers(1, iArr, 0);
        GLES20.glBindRenderbuffer(36161, iArr[0]);
        if (4 == i3) {
            this.mColorBufferId = iArr[0];
            GLES20.glRenderbufferStorage(36161, 32854, i, i2);
        } else if (2 == i3) {
            this.mDepthBufferId = iArr[0];
            GLES20.glRenderbufferStorage(36161, 33189, i, i2);
        }
        GLES20.glBindRenderbuffer(36161, 0);
    }
}
