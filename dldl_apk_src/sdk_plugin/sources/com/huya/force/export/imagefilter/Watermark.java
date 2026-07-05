package com.huya.force.export.imagefilter;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Watermark {
    private Bitmap mBitmap;
    private int mHeight;
    private int mWidth;
    private int mX;
    private int mY;

    public Watermark(Bitmap bitmap, int i, int i2, int i3, int i4) {
        this.mBitmap = bitmap;
        this.mX = i;
        this.mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }
}
