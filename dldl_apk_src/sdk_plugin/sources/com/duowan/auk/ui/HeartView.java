package com.duowan.auk.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HeartView {
    private static final int DIR_LEFT = 0;
    private static final int DIR_RIGHT = 1;
    private static final long DURATION = 4000;
    private static final long SCALE_DURATION = 100;
    public static final float TRANSPARENT_POS_Y = 0.8f;
    private Bitmap mBmp;
    private float mCurrentX;
    private float mCurrentY;
    private Interpolator mDecInter;
    private int mDir;
    private int mHeight;
    private int mRightEdge;
    private float mSpeedX;
    private long mTimeStartedForY;
    private int mWidth;
    private static Random sRandom = new Random();
    private static int Y_SEGMENTS = 3;
    private float mSpeedY = 1.0f;
    private int mTurnLevel = 0;
    private float scalePercent = 0.0f;
    private Paint mPaint = new Paint();
    private int mLeftEdge = 0;
    private Interpolator mAccInter = new AccelerateInterpolator((sRandom.nextFloat() * 0.4f) + 0.4f);
    private Matrix mMatrix = new Matrix();

    private void calculateSpeedX() {
    }

    public HeartView(Bitmap bitmap, int i, int i2) {
        this.mDir = -1;
        this.mSpeedX = 0.5f;
        this.mCurrentY = i2 - bitmap.getHeight();
        this.mWidth = i - bitmap.getWidth();
        this.mHeight = i2 - bitmap.getHeight();
        this.mBmp = bitmap;
        this.mRightEdge = this.mWidth + (bitmap.getWidth() / 2);
        this.mCurrentX = this.mWidth / 2;
        this.mDir = sRandom.nextInt(2);
        this.mSpeedX = (sRandom.nextFloat() * 0.8f) + 0.2f;
        setupAnimation();
    }

    public void draw(Canvas canvas, long j) {
        canvas.drawBitmap(this.mBmp, this.mMatrix, this.mPaint);
        calculateNextDraw(j);
    }

    private void calculateAlpha() {
        float f = this.mHeight * 0.8f;
        float f2 = this.mCurrentY;
        this.mPaint.setAlpha((int) ((f2 < f ? f2 / f : 1.0f) * 255.0f));
    }

    private void calculateNextDraw(long j) {
        calculateXY(j);
        long j2 = this.mTimeStartedForY;
        if (j2 == 0) {
            this.mTimeStartedForY = j;
            return;
        }
        long j3 = j - j2;
        calculateAnimation(j3);
        calculateSpeedY(j3);
        calculateSpeedX();
        calculateAlpha();
    }

    private void calculateAnimation(float f) {
        float f2 = f / 100.0f;
        this.scalePercent = f2;
        if (f2 > 1.0f) {
            this.scalePercent = 1.0f;
        }
        setupAnimation();
    }

    private void setupAnimation() {
        Matrix matrix = this.mMatrix;
        float f = this.scalePercent;
        matrix.setScale(f, f, this.mBmp.getWidth() / 2, this.mBmp.getHeight());
        this.mMatrix.postTranslate(this.mCurrentX, this.mCurrentY);
    }

    private void calculateXY(long j) {
        calculateX(j);
        calculateY();
    }

    private void calculateX(long j) {
        int i = this.mHeight;
        float f = i / Y_SEGMENTS;
        int i2 = this.mTurnLevel;
        if (i - this.mCurrentY > f * (i2 + 1)) {
            this.mTurnLevel = i2 + 1;
            this.mDir = sRandom.nextInt(2);
        }
        float f2 = this.mCurrentX;
        if (f2 <= this.mLeftEdge) {
            if (this.mDir == 0) {
                this.mDir = 1;
                this.mTurnLevel++;
            }
        } else if (f2 >= this.mRightEdge && this.mDir == 1) {
            this.mDir = 0;
            this.mTurnLevel++;
        }
        int i3 = this.mDir;
        if (i3 == 0) {
            this.mCurrentX -= this.mSpeedX;
        } else {
            if (i3 != 1) {
                return;
            }
            this.mCurrentX += this.mSpeedX;
        }
    }

    private void calculateY() {
        float f = this.mCurrentY - this.mSpeedY;
        this.mCurrentY = f;
        if (f < 0.0f) {
            this.mCurrentY = 0.0f;
        }
    }

    private void calculateSpeedY(long j) {
        if (j < 4000) {
            this.mSpeedY = this.mCurrentY - (this.mHeight * (1.0f - this.mAccInter.getInterpolation(j / 4000.0f)));
        } else {
            this.mSpeedY = this.mCurrentY;
        }
        if (this.mSpeedY < 0.0f) {
            this.mSpeedY = 0.0f;
        }
    }

    public boolean isExceedBounds() {
        return this.mCurrentY <= 0.0f;
    }
}
