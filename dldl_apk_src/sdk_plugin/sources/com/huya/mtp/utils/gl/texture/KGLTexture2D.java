package com.huya.mtp.utils.gl.texture;

import android.opengl.GLES20;
import android.util.Log;
import com.huya.mtp.utils.gl.core.KGLAbsGLObject;
import com.huya.mtp.utils.gl.utils.KGLGetError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class KGLTexture2D extends KGLAbsGLObject {
    private static final String TAG = "KGLTexture2D";
    private int mHeight;
    private int mID;
    private int mWidth;

    public static KGLTexture2D create(KGLDensityBitmap kGLDensityBitmap) {
        KGLTexture2D kGLTexture2D = new KGLTexture2D(kGLDensityBitmap);
        if (kGLTexture2D.isValid()) {
            return kGLTexture2D;
        }
        return null;
    }

    private KGLTexture2D(KGLDensityBitmap kGLDensityBitmap) {
        this.mID = -1;
        this.mWidth = -1;
        this.mHeight = -1;
        int[] iArr = {-1};
        GLES20.glGetIntegerv(3379, iArr, 0);
        if (-1 == iArr[0] || kGLDensityBitmap.getWidth() > iArr[0] || kGLDensityBitmap.getHeight() > iArr[0]) {
            Log.e(TAG, "bitmap is too big maxSize " + iArr[0] + " width " + kGLDensityBitmap.getWidth() + " height " + kGLDensityBitmap.getHeight());
            return;
        }
        int[] iArr2 = {-1};
        GLES20.glGenTextures(1, iArr2, 0);
        if (-1 == iArr2[0]) {
            Log.e(TAG, "invalid texture");
            return;
        }
        this.mID = iArr2[0];
        bind();
        if (!GLES20.glIsTexture(iArr2[0])) {
            unBind();
            Log.e(TAG, "invalid texture");
            this.mID = -1;
            return;
        }
        this.mWidth = kGLDensityBitmap.getWidth();
        this.mHeight = kGLDensityBitmap.getHeight();
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexImage2D(3553, 0, kGLDensityBitmap.getGLInternalFormat(), this.mWidth, this.mHeight, 0, kGLDensityBitmap.getGLFormat(), kGLDensityBitmap.getGLBufferType(), kGLDensityBitmap.getBuffer());
        unBind();
        KGLGetError.info("KGLTexture2Dcreate texture");
    }

    public void bind() {
        GLES20.glBindTexture(3553, this.mID);
    }

    public void unBind() {
        GLES20.glBindTexture(3553, 0);
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsGLObject
    public boolean isValid() {
        return -1 != this.mID;
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsGLObject
    protected void deleteGLObject() {
        int i = this.mID;
        if (-1 != i) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
            KGLGetError.info("KGLTexture2D delete texture " + this.mID);
            this.mHeight = -1;
            this.mWidth = -1;
            this.mID = -1;
        }
    }
}
