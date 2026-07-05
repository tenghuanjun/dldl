package com.huya.mtp.utils.gl.core;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class KGLCoordinate {
    private float mEyeZ;
    private float mNearZ;
    private float mTop = 1.0f;
    private float mBottom = -1.0f;
    private float mNear = 1.0f;
    private float mFar = 2.0f * 1.0f;
    private float mLeft = -1.0f;
    private float mRight = -(-1.0f);
    private float mGLUnit = 1.0f;
    private float mWorldUnit = 1.0f;
    private float mWorldWidth = 1.0f;
    private float mWorldHeight = 1.0f;

    public static KGLCoordinate create(float... fArr) {
        KGLCoordinate kGLCoordinate = new KGLCoordinate();
        kGLCoordinate.mTop = fArr[0];
        kGLCoordinate.mBottom = fArr[1];
        kGLCoordinate.mNear = fArr[2];
        kGLCoordinate.mFar = fArr[3];
        kGLCoordinate.mEyeZ = fArr[4];
        kGLCoordinate.mNearZ = fArr[5];
        kGLCoordinate.mLeft = fArr[6];
        kGLCoordinate.mRight = fArr[7];
        kGLCoordinate.mGLUnit = fArr[8];
        kGLCoordinate.mWorldUnit = fArr[9];
        kGLCoordinate.mWorldWidth = fArr[10];
        kGLCoordinate.mWorldHeight = fArr[11];
        return kGLCoordinate;
    }

    private KGLCoordinate() {
        float f = -1.0f;
        this.mEyeZ = f;
        this.mNearZ = f + 1.0f;
    }

    public float toGLSize(float f) {
        return (f * this.mGLUnit) / this.mWorldUnit;
    }

    public float toWorldSize(float f) {
        return (f * this.mWorldUnit) / this.mGLUnit;
    }

    public float toGLPositionX(float f) {
        return (f * Math.abs(this.mLeft - this.mRight)) / this.mWorldWidth;
    }

    public float toGLPositionY(float f) {
        return (f * Math.abs(this.mTop - this.mBottom)) / this.mWorldHeight;
    }

    public float toWorldPositionX(float f) {
        return (f * this.mWorldWidth) / Math.abs(this.mLeft - this.mRight);
    }

    public float toWorldPositionY(float f) {
        return (f * this.mWorldHeight) / Math.abs(this.mTop - this.mBottom);
    }

    public float glAbsoluteX(float f) {
        return f * Math.abs(this.mLeft - this.mRight);
    }

    public float glAbsoluteY(float f) {
        return f * Math.abs(this.mTop - this.mBottom);
    }

    public void setGLUnit(float f) {
        this.mGLUnit = f;
    }

    public void setWorldUnit(float f) {
        this.mWorldUnit = f;
    }

    public void setWorldSize(float f, float f2) {
        this.mWorldWidth = f;
        this.mWorldHeight = f2;
    }

    public void setLeft(float f) {
        this.mLeft = f;
    }

    public void setRight(float f) {
        this.mRight = f;
    }

    public float getTop() {
        return this.mTop;
    }

    public float getBottom() {
        return this.mBottom;
    }

    public float getNear() {
        return this.mNear;
    }

    public float getNearZ() {
        return this.mNearZ;
    }

    public float getFar() {
        return this.mFar;
    }

    public float getEyeZ() {
        return this.mEyeZ;
    }

    public float getLeft() {
        return this.mLeft;
    }

    public float getRight() {
        return this.mRight;
    }

    public float getWorldUnit() {
        return this.mWorldUnit;
    }

    public float getGLUnit() {
        return this.mGLUnit;
    }

    public float getWorldWidth() {
        return this.mWorldWidth;
    }

    public float getWorldHeight() {
        return this.mWorldHeight;
    }
}
