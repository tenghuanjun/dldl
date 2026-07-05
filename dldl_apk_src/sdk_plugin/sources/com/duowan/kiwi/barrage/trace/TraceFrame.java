package com.duowan.kiwi.barrage.trace;

import com.duowan.kiwi.barrage.utils.GLCoordinate;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TraceFrame {
    public static final int ALPHA = 2;
    public static final int SCALE_X = 3;
    public static final int SCALE_Y = 4;
    public static final int X = 0;
    public static final int Y = 1;
    public float mX = GLCoordinate.outSideWorldX();
    public float mY = GLCoordinate.outSideWorldY();
    public float mAlpha = 1.0f;
    public float mScaleX = 1.0f;
    public float mScaleY = 1.0f;

    public float x() {
        return this.mX;
    }

    public float y() {
        return this.mY;
    }

    public float alpha() {
        return this.mAlpha;
    }

    public float scaleX() {
        return this.mScaleX;
    }

    public float scaleY() {
        return this.mScaleY;
    }
}
