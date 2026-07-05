package com.huya.mtp.utils.gl.camera;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.huya.mtp.utils.gl.core.KGLCoordinate;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class KGLAbsCamera {
    private float[] mLookAtMatrix = new float[16];
    private float[] mProjectionMatrix = new float[16];
    private float[] mCurrentMatrix = new float[16];

    protected abstract void sharp(float[] fArr, float f, float f2, float f3, float f4, float f5, float f6);

    protected KGLAbsCamera() {
    }

    public void setViewPort(int i, int i2, int i3, int i4) {
        GLES20.glViewport(i, i2, i3, i4);
    }

    public void lookAt(KGLCoordinate kGLCoordinate) {
        Matrix.setLookAtM(this.mLookAtMatrix, 0, kGLCoordinate.getRight(), kGLCoordinate.getTop(), kGLCoordinate.getEyeZ(), kGLCoordinate.getRight(), kGLCoordinate.getTop(), 0.0f, 0.0f, -0.1f, 0.0f);
        Matrix.multiplyMM(this.mCurrentMatrix, 0, this.mProjectionMatrix, 0, this.mLookAtMatrix, 0);
    }

    public void sharp(KGLCoordinate kGLCoordinate) {
        sharp(this.mProjectionMatrix, kGLCoordinate.getLeft(), kGLCoordinate.getRight(), kGLCoordinate.getBottom(), kGLCoordinate.getTop(), kGLCoordinate.getNear(), kGLCoordinate.getFar());
        Matrix.multiplyMM(this.mCurrentMatrix, 0, this.mProjectionMatrix, 0, this.mLookAtMatrix, 0);
    }

    public float[] getViewMatrix() {
        return this.mLookAtMatrix;
    }

    public float[] getProjectionMatrix() {
        return this.mProjectionMatrix;
    }

    public float[] getCurrentMatrix() {
        return this.mCurrentMatrix;
    }
}
