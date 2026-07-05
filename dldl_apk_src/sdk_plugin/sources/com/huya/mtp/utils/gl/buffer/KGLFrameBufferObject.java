package com.huya.mtp.utils.gl.buffer;

import android.opengl.GLES20;
import android.util.Log;
import com.huya.mtp.utils.gl.utils.KGLGetError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class KGLFrameBufferObject extends KGLAbsBufferObject {
    private static final String TAG = "KGLFrameBufferObject";
    private int mTextureID = -1;
    private int mRenderBufferID = -1;
    private int mFrameBufferID = -1;

    public static KGLFrameBufferObject create(int i, int i2) {
        return create(i, i2, false);
    }

    public static KGLFrameBufferObject create(int i, int i2, boolean z) {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(34024, iArr, 0);
        if (iArr[0] <= i || iArr[0] <= i2) {
            Log.e(TAG, "frame buffer max size not big enough max " + iArr[0] + " width " + i + " height " + i2);
            return null;
        }
        GLES20.glGetIntegerv(3379, iArr, 0);
        if (iArr[0] <= i || iArr[0] <= i2) {
            Log.e(TAG, "texture max size not big enough max " + iArr[0] + " width " + i + " height " + i2);
            return null;
        }
        KGLFrameBufferObject kGLFrameBufferObject = new KGLFrameBufferObject(i, i2, z);
        if (kGLFrameBufferObject.isValid()) {
            return kGLFrameBufferObject;
        }
        kGLFrameBufferObject.deleteGLObject();
        return null;
    }

    public void bindTexture() {
        GLES20.glBindTexture(3553, this.mTextureID);
    }

    public void unBindTexture() {
        GLES20.glBindTexture(3553, 0);
    }

    @Override // com.huya.mtp.utils.gl.buffer.KGLAbsBufferObject
    public void bind() {
        GLES20.glBindFramebuffer(36160, this.mFrameBufferID);
    }

    @Override // com.huya.mtp.utils.gl.buffer.KGLAbsBufferObject
    public void unBind() {
        GLES20.glBindFramebuffer(36160, 0);
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsGLObject
    public boolean isValid() {
        if (-1 == this.mTextureID) {
            return false;
        }
        bindTexture();
        if (!GLES20.glIsTexture(this.mTextureID)) {
            unBindTexture();
            Log.e(TAG, "not texture " + this.mTextureID);
            return false;
        }
        unBindTexture();
        int i = this.mRenderBufferID;
        if (-1 != i && !GLES20.glIsRenderbuffer(i)) {
            Log.e(TAG, "not render buffer " + this.mRenderBufferID);
            return false;
        }
        if (!GLES20.glIsFramebuffer(this.mFrameBufferID)) {
            Log.e(TAG, "not frame buffer " + this.mFrameBufferID);
            return false;
        }
        bind();
        int iGlCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(36160);
        unBind();
        if (36053 == iGlCheckFramebufferStatus) {
            return true;
        }
        Log.e(TAG, "frame buffer statue " + Integer.toHexString(iGlCheckFramebufferStatus));
        return false;
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsGLObject
    protected void deleteGLObject() {
        int i = this.mTextureID;
        if (-1 != i) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
            KGLGetError.info(TAG);
            this.mTextureID = -1;
        }
        int i2 = this.mRenderBufferID;
        if (-1 != i2) {
            GLES20.glDeleteRenderbuffers(1, new int[]{i2}, 0);
            KGLGetError.info(TAG);
            this.mRenderBufferID = -1;
        }
        int i3 = this.mFrameBufferID;
        if (-1 != i3) {
            GLES20.glDeleteFramebuffers(1, new int[]{i3}, 0);
            KGLGetError.info(TAG);
            this.mFrameBufferID = -1;
        }
    }

    private KGLFrameBufferObject(int i, int i2, boolean z) {
        createFrameTexture(i, i2);
        if (z) {
            createRenderBuffer(i, i2);
        }
        createFrameBuffer();
    }

    private void createFrameTexture(int i, int i2) {
        int[] iArr = {-1};
        GLES20.glGenTextures(1, iArr, 0);
        int i3 = iArr[0];
        this.mTextureID = i3;
        GLES20.glBindTexture(3553, i3);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexImage2D(3553, 0, 6407, i, i2, 0, 6407, 5121, null);
        GLES20.glBindTexture(3553, 0);
        KGLGetError.info("KGLFrameBufferObjectcreateFrameTexture");
    }

    private void createRenderBuffer(int i, int i2) {
        int[] iArr = {-1};
        GLES20.glGenRenderbuffers(1, iArr, 0);
        int i3 = iArr[0];
        this.mRenderBufferID = i3;
        GLES20.glBindRenderbuffer(36161, i3);
        GLES20.glRenderbufferStorage(36161, 33189, i, i2);
        GLES20.glBindRenderbuffer(36161, 0);
        KGLGetError.info("KGLFrameBufferObjectcreateRenderBuffer");
    }

    private void createFrameBuffer() {
        int[] iArr = {-1};
        GLES20.glGenFramebuffers(1, iArr, 0);
        int i = iArr[0];
        this.mFrameBufferID = i;
        GLES20.glBindFramebuffer(36160, i);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.mTextureID, 0);
        int i2 = this.mRenderBufferID;
        if (-1 != i2) {
            GLES20.glFramebufferRenderbuffer(36160, 36096, 36161, i2);
        }
        GLES20.glBindFramebuffer(36160, 0);
        KGLGetError.info("KGLFrameBufferObjectcreateFrameBuffer");
    }
}
