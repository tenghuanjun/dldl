package com.huya.force.gpuimage.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GLTexture {
    private int mTarget;
    private int mTextureID = -1;
    private int mWidth = 0;
    private int mHeight = 0;
    private int mFormat = 6408;

    public GLTexture(int i) {
        this.mTarget = 3553;
        this.mTarget = i;
    }

    public void create(int i, int i2, int i3) {
        destory();
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i4 = iArr[0];
        this.mTextureID = i4;
        GLES20.glBindTexture(this.mTarget, i4);
        GLES20.glTexParameteri(this.mTarget, 10242, 33071);
        GLES20.glTexParameteri(this.mTarget, 10243, 33071);
        GLES20.glTexParameteri(this.mTarget, 10241, 9729);
        GLES20.glTexParameteri(this.mTarget, 10240, 9729);
        GLES20.glTexImage2D(this.mTarget, 0, i3, i, i2, 0, i3, 5121, null);
        this.mWidth = i;
        this.mHeight = i2;
        this.mFormat = i3;
    }

    public void create(Bitmap bitmap, int i) {
        destory();
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        this.mTextureID = iArr[0];
        GLES20.glActiveTexture(33987);
        GLES20.glBindTexture(this.mTarget, this.mTextureID);
        GLES20.glTexParameteri(this.mTarget, 10242, 33071);
        GLES20.glTexParameteri(this.mTarget, 10243, 33071);
        GLES20.glTexParameteri(this.mTarget, 10241, 9729);
        GLES20.glTexParameteri(this.mTarget, 10240, 9729);
        GLUtils.texImage2D(this.mTarget, 0, bitmap, 0);
        this.mWidth = bitmap.getWidth();
        this.mHeight = bitmap.getHeight();
        this.mFormat = i;
    }

    public void destory() {
        int i = this.mTextureID;
        if (i != -1) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
            this.mTextureID = -1;
            this.mHeight = 0;
            this.mWidth = 0;
        }
    }

    public void loadTextures(Context context, int i) {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        this.mWidth = bitmapDecodeResource.getWidth();
        this.mHeight = bitmapDecodeResource.getHeight();
        GLES20.glBindTexture(3553, this.mTextureID);
        GLUtils.texImage2D(3553, 0, bitmapDecodeResource, 0);
        GLES20.glBindTexture(this.mTarget, this.mTextureID);
        GLES20.glTexParameteri(this.mTarget, 10242, 33071);
        GLES20.glTexParameteri(this.mTarget, 10243, 33071);
        GLES20.glTexParameteri(this.mTarget, 10241, 9729);
        GLES20.glTexParameteri(this.mTarget, 10240, 9729);
    }

    public void bindFBO(int i) {
        GLES20.glBindFramebuffer(36160, i);
        GLES20.glBindTexture(this.mTarget, this.mTextureID);
        GLES20.glFramebufferTexture2D(36160, 36064, this.mTarget, this.mTextureID, 0);
    }

    public int getTarget() {
        return this.mTarget;
    }

    public int getTextureId() {
        return this.mTextureID;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }
}
