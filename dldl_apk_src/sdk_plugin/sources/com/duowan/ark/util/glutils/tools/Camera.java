package com.duowan.ark.util.glutils.tools;

import android.opengl.GLES20;
import android.opengl.Matrix;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Camera {
    private float mBottom;
    private float[] mCurrentMatrix;
    private float mEyeZ;
    private float mFar;
    private float[] mFrustumMatrix;
    private float[] mLookAtMatrix;
    private float mNear;
    private float mTop;

    public Camera(float f, float f2, float f3, float f4, float f5) {
        this.mFrustumMatrix = null;
        this.mLookAtMatrix = null;
        this.mCurrentMatrix = null;
        float[] fArr = new float[16];
        this.mFrustumMatrix = fArr;
        this.mLookAtMatrix = new float[16];
        this.mCurrentMatrix = new float[16];
        Matrix.setIdentityM(fArr, 0);
        Matrix.setIdentityM(this.mLookAtMatrix, 0);
        Matrix.setIdentityM(this.mCurrentMatrix, 0);
        this.mTop = f;
        this.mBottom = f2;
        this.mNear = f3;
        this.mFar = f4;
        this.mEyeZ = f5;
    }

    public void sharpFocusing(float f, float f2) {
        Matrix.frustumM(this.mFrustumMatrix, 0, f, f2, this.mBottom, this.mTop, this.mNear, this.mFar);
        Matrix.multiplyMM(this.mCurrentMatrix, 0, this.mFrustumMatrix, 0, this.mLookAtMatrix, 0);
    }

    public void setUp() {
        Matrix.setLookAtM(this.mLookAtMatrix, 0, 0.0f, 0.0f, this.mEyeZ, 0.0f, 0.0f, 0.0f, 0.0f, -0.1f, 0.0f);
        Matrix.multiplyMM(this.mCurrentMatrix, 0, this.mFrustumMatrix, 0, this.mLookAtMatrix, 0);
    }

    public void pressShutter(int i, int i2, float[] fArr) {
        GLES20.glUniformMatrix4fv(i, 1, false, this.mCurrentMatrix, 0);
        GLES20.glUniformMatrix4fv(i2, 1, false, fArr, 0);
    }
}
