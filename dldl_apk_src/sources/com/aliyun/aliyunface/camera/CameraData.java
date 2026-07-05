package com.aliyun.aliyunface.camera;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class CameraData {
    public static final int MODE_BGR = 2;
    public static final int MODE_BGRA = 1;
    public static final int MODE_DEPTH_100UM = 51;
    public static final int MODE_DEPTH_1_MM = 50;
    public static final int MODE_GRAY_16 = 6;
    public static final int MODE_GRAY_8 = 5;
    public static final int MODE_IR_GRAY_16 = 6;
    public static final int MODE_IR_GRAY_8 = 5;
    public static final int MODE_NV21 = 0;
    public static final int MODE_RGB = 4;
    public static final int MODE_RGBA = 3;
    ByteBuffer mColorData;
    int mColorFrameMode;
    int mColorHeight;
    int mColorWidth;
    ByteBuffer mDepthData;
    int mDepthFrameMode;
    int mDepthHeight;
    int mDepthWidth;
    ByteBuffer mIRData;
    int mIRFrameMode;
    int mIRHeight;
    int mIRWidth;
    boolean mMirror;
    int mPreviewHeight;
    int mPreviewWidth;

    public CameraData() {
    }

    public CameraData(ByteBuffer byteBuffer, int i, int i2, int i3, ByteBuffer byteBuffer2, int i4, int i5, int i6, int i7) {
        this(byteBuffer, i, i2, i3, byteBuffer2, i4, i5, i6, i7, false);
    }

    public CameraData(ByteBuffer byteBuffer, int i, int i2, int i3, ByteBuffer byteBuffer2, int i4, int i5, int i6, int i7, boolean z) {
        this(byteBuffer, i, i2, i3, byteBuffer2, i4, i5, -1, null, 0, 0, -1, i6, i7, z);
    }

    public CameraData(ByteBuffer byteBuffer, int i, int i2, int i3, ByteBuffer byteBuffer2, int i4, int i5, int i6, ByteBuffer byteBuffer3, int i7, int i8, int i9, int i10, int i11, boolean z) {
        this.mColorData = byteBuffer;
        this.mColorWidth = i;
        this.mColorHeight = i2;
        this.mColorFrameMode = i3;
        this.mDepthData = byteBuffer2;
        this.mDepthWidth = i4;
        this.mDepthHeight = i5;
        this.mDepthFrameMode = i6;
        this.mIRData = byteBuffer3;
        this.mIRWidth = i7;
        this.mIRHeight = i8;
        this.mIRFrameMode = i9;
        this.mPreviewWidth = i10;
        this.mPreviewHeight = i11;
        this.mMirror = z;
    }

    public void setColorData(ByteBuffer byteBuffer) {
        this.mColorData = byteBuffer;
    }

    public ByteBuffer getColorData() {
        return this.mColorData;
    }

    public void setColorWidth(int i) {
        this.mColorWidth = i;
    }

    public int getColorWidth() {
        return this.mColorWidth;
    }

    public void setColorHeight(int i) {
        this.mColorHeight = i;
    }

    public int getColorHeight() {
        return this.mColorHeight;
    }

    public void setColorFrameMode(int i) {
        this.mColorFrameMode = i;
    }

    public int getColorFrameMode() {
        return this.mColorFrameMode;
    }

    public void setDepthData(ByteBuffer byteBuffer) {
        this.mDepthData = byteBuffer;
    }

    public ByteBuffer getDepthData() {
        return this.mDepthData;
    }

    public void setDepthWidth(int i) {
        this.mDepthWidth = i;
    }

    public int getDepthWidth() {
        return this.mDepthWidth;
    }

    public void setDepthHeight(int i) {
        this.mDepthHeight = i;
    }

    public int getDepthHeight() {
        return this.mDepthHeight;
    }

    public void setDepthFrameMode(int i) {
        this.mDepthFrameMode = i;
    }

    public int getDepthFrameMode() {
        return this.mDepthFrameMode;
    }

    public void setIRData(ByteBuffer byteBuffer) {
        this.mIRData = byteBuffer;
    }

    public ByteBuffer getIRData() {
        return this.mIRData;
    }

    public void setIRWidth(int i) {
        this.mIRWidth = i;
    }

    public int getIRWidth() {
        return this.mIRWidth;
    }

    public void setIRHeight(int i) {
        this.mIRHeight = i;
    }

    public int getIRHeight() {
        return this.mIRHeight;
    }

    public void setIRFrameMode(int i) {
        this.mIRFrameMode = i;
    }

    public int getIRFrameMode() {
        return this.mIRFrameMode;
    }

    public void setPreviewWidth(int i) {
        this.mPreviewWidth = i;
    }

    public int getPreviewWidth() {
        return this.mPreviewWidth;
    }

    public void setPreviewHeight(int i) {
        this.mPreviewHeight = i;
    }

    public int getPreviewHeight() {
        return this.mPreviewHeight;
    }

    public void setMirror(boolean z) {
        this.mMirror = z;
    }

    public boolean getMirror() {
        return this.mMirror;
    }

    public CameraData deepClone() {
        CameraData cameraData = new CameraData();
        cameraData.mColorData = ByteBuffer.allocateDirect(this.mColorData.capacity());
        this.mColorData.rewind();
        cameraData.mColorData.put(this.mColorData);
        this.mColorData.rewind();
        cameraData.mColorData.rewind();
        cameraData.mColorWidth = this.mColorWidth;
        cameraData.mColorHeight = this.mColorHeight;
        cameraData.mColorFrameMode = this.mColorFrameMode;
        ByteBuffer byteBuffer = this.mDepthData;
        if (byteBuffer != null) {
            cameraData.mDepthData = ByteBuffer.allocateDirect(byteBuffer.capacity());
            this.mDepthData.rewind();
            cameraData.mDepthData.put(this.mDepthData);
            this.mDepthData.rewind();
            cameraData.mDepthData.rewind();
        } else {
            cameraData.mDepthData = null;
        }
        cameraData.mDepthWidth = this.mDepthWidth;
        cameraData.mDepthHeight = this.mDepthHeight;
        cameraData.mDepthFrameMode = this.mDepthFrameMode;
        ByteBuffer byteBuffer2 = this.mIRData;
        if (byteBuffer2 != null) {
            cameraData.mIRData = ByteBuffer.allocateDirect(byteBuffer2.capacity());
            this.mIRData.rewind();
            cameraData.mIRData.put(this.mIRData);
            this.mIRData.rewind();
            cameraData.mIRData.rewind();
        } else {
            cameraData.mIRData = null;
        }
        cameraData.mIRWidth = this.mIRWidth;
        cameraData.mIRHeight = this.mIRHeight;
        cameraData.mIRFrameMode = this.mIRFrameMode;
        cameraData.mPreviewWidth = this.mPreviewWidth;
        cameraData.mPreviewHeight = this.mPreviewHeight;
        cameraData.mMirror = this.mMirror;
        return cameraData;
    }

    public void recycle() {
        this.mColorData = null;
        this.mDepthData = null;
        this.mIRData = null;
    }

    public String toString() {
        return "CameraData{, mColorWidth=" + this.mColorWidth + ", mColorHeight=" + this.mColorHeight + ", mColorFrameMode=" + this.mColorFrameMode + ", mDepthWidth=" + this.mDepthWidth + ", mDepthHeight=" + this.mDepthHeight + ", mPreviewWidth=" + this.mPreviewWidth + ", mPreviewHeight=" + this.mPreviewHeight + ", mMirror=" + this.mMirror + '}';
    }
}
