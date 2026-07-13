package moe.codeest.enviews;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import com.shuyu.gsyvideoplayer.R;

/* JADX INFO: loaded from: classes4.dex */
public class ENDownloadView extends View {
    private static final int DEFAULT_BG_LINE_COLOR = -12959931;
    private static final int DEFAULT_BG_LINE_WIDTH = 9;
    private static final int DEFAULT_DOWNLOAD_TIME = 2000;
    private static final DownloadUnit DEFAULT_DOWNLOAD_UNIT = DownloadUnit.B;
    private static final int DEFAULT_LINE_COLOR = -1;
    private static final int DEFAULT_LINE_WIDTH = 9;
    private static final int DEFAULT_RIPPLE_SPEED = 2;
    private static final int DEFAULT_STATE = 0;
    private static final int DEFAULT_TEXT_COLOR = -1;
    private static final int DEFAULT_TEXT_SIZE = 14;
    public static final int STATE_DOWNLOADING = 1;
    public static final int STATE_END = 2;
    public static final int STATE_PRE = 0;
    public static final int STATE_RESET = 3;
    private float mBaseLength;
    private float mBaseRippleLength;
    private Paint mBgPaint;
    private float mCenterX;
    private float mCenterY;
    private float mCircleRadius;
    private RectF mClipRectF;
    private float mCurrentRippleX;
    private double mCurrentSize;
    private int mCurrentState;
    private int mDownloadTime;
    private float mFraction;
    private float mHeight;
    private Paint mPaint;
    private Path mPath;
    private RectF mRectF;
    private Paint mTextPaint;
    private int mTextSize;
    private double mTotalSize;
    private DownloadUnit mUnit;
    ValueAnimator mValueAnimator;
    private float mWidth;
    private OnDownloadStateListener onDownloadStateListener;

    public enum DownloadUnit {
        GB,
        MB,
        KB,
        B,
        NONE
    }

    interface OnDownloadStateListener {
        void onDownloadFinish();

        void onResetFinish();
    }

    public ENDownloadView(Context context) {
        super(context);
    }

    public ENDownloadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.download);
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.download_download_line_color, -1);
        int color2 = typedArrayObtainStyledAttributes.getColor(R.styleable.download_download_bg_line_color, DEFAULT_BG_LINE_COLOR);
        int color3 = typedArrayObtainStyledAttributes.getColor(R.styleable.download_download_text_color, -1);
        int integer = typedArrayObtainStyledAttributes.getInteger(R.styleable.download_download_line_width, 9);
        int integer2 = typedArrayObtainStyledAttributes.getInteger(R.styleable.download_download_bg_line_width, 9);
        int integer3 = typedArrayObtainStyledAttributes.getInteger(R.styleable.download_download_text_size, 14);
        typedArrayObtainStyledAttributes.recycle();
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setStrokeWidth(integer);
        this.mPaint.setColor(color);
        Paint paint2 = new Paint(1);
        this.mBgPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.mBgPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mBgPaint.setStrokeWidth(integer2);
        this.mBgPaint.setColor(color2);
        Paint paint3 = new Paint(1);
        this.mTextPaint = paint3;
        paint3.setColor(color3);
        this.mTextPaint.setTextSize(integer3);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mPath = new Path();
        this.mTextSize = integer3;
        this.mCurrentState = 0;
        this.mUnit = DEFAULT_DOWNLOAD_UNIT;
        this.mDownloadTime = 2000;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float f = i;
        this.mWidth = f;
        float f2 = i2;
        this.mHeight = f2;
        float f3 = f / 2.0f;
        this.mCenterX = f3;
        this.mCenterY = f2 / 2.0f;
        float f4 = (f * 5.0f) / 12.0f;
        this.mCircleRadius = f4;
        float f5 = f4 / 3.0f;
        this.mBaseLength = f5;
        float f6 = (f5 * 4.4f) / 12.0f;
        this.mBaseRippleLength = f6;
        this.mCurrentRippleX = f3 - (f6 * 10.0f);
        float f7 = this.mCenterX;
        float f8 = this.mCircleRadius;
        float f9 = this.mCenterY;
        this.mRectF = new RectF(f7 - f8, f9 - f8, f7 + f8, f9 + f8);
        float f10 = this.mCenterX;
        float f11 = this.mBaseRippleLength;
        this.mClipRectF = new RectF(f10 - (f11 * 6.0f), 0.0f, f10 + (f11 * 6.0f), this.mHeight);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.mCurrentState;
        if (i == 0) {
            float f = this.mFraction;
            if (f <= 0.4d) {
                canvas.drawCircle(this.mCenterX, this.mCenterY, this.mCircleRadius, this.mBgPaint);
                float f2 = this.mCenterX;
                float f3 = this.mBaseLength;
                float f4 = this.mCenterY;
                canvas.drawLine(f2 - f3, f4, f2, f4 + f3, this.mPaint);
                float f5 = this.mCenterX;
                float f6 = this.mCenterY;
                float f7 = this.mBaseLength;
                canvas.drawLine(f5, f6 + f7, f5 + f7, f6, this.mPaint);
                float f8 = this.mCenterX;
                float f9 = this.mCenterY;
                float f10 = this.mBaseLength;
                float f11 = this.mFraction;
                canvas.drawLine(f8, (f9 + f10) - (((f10 * 1.3f) / 0.4f) * f11), f8, (f9 - (1.6f * f10)) + (((f10 * 1.3f) / 0.4f) * f11), this.mPaint);
                return;
            }
            if (f <= 0.6d) {
                canvas.drawCircle(this.mCenterX, this.mCenterY, this.mCircleRadius, this.mBgPaint);
                canvas.drawCircle(this.mCenterX, this.mCenterY - (this.mBaseLength * 0.3f), 2.0f, this.mPaint);
                float f12 = this.mCenterX;
                float f13 = this.mBaseLength;
                float f14 = this.mFraction;
                float f15 = this.mCenterY;
                canvas.drawLine((f12 - f13) - (((f13 * 1.2f) / 0.2f) * (f14 - 0.4f)), f15, f12, (f15 + f13) - ((f13 / 0.2f) * (f14 - 0.4f)), this.mPaint);
                float f16 = this.mCenterX;
                float f17 = this.mCenterY;
                float f18 = this.mBaseLength;
                float f19 = this.mFraction;
                canvas.drawLine(f16, (f17 + f18) - ((f18 / 0.2f) * (f19 - 0.4f)), f16 + f18 + (((f18 * 1.2f) / 0.2f) * (f19 - 0.4f)), f17, this.mPaint);
                return;
            }
            if (f <= 1.0f) {
                canvas.drawCircle(this.mCenterX, this.mCenterY, this.mCircleRadius, this.mBgPaint);
                float f20 = this.mCenterX;
                float f21 = this.mCenterY;
                float f22 = this.mBaseLength;
                canvas.drawCircle(f20, (f21 - (f22 * 0.3f)) - (((this.mCircleRadius - (f22 * 0.3f)) / 0.4f) * (this.mFraction - 0.6f)), 2.0f, this.mPaint);
                float f23 = this.mCenterX;
                float f24 = this.mBaseLength;
                float f25 = this.mCenterY;
                canvas.drawLine(f23 - (f24 * 2.2f), f25, f23 + (f24 * 2.2f), f25, this.mPaint);
                return;
            }
            canvas.drawCircle(this.mCenterX, this.mCenterY, this.mCircleRadius, this.mBgPaint);
            canvas.drawCircle(this.mCenterX, (this.mCenterY - this.mCircleRadius) - ((this.mBaseLength * 3.0f) * (this.mFraction - 1.0f)), 3.0f, this.mPaint);
            float f26 = this.mCenterX;
            float f27 = this.mBaseLength;
            float f28 = this.mCenterY;
            canvas.drawLine(f26 - (f27 * 2.2f), f28, f26 + (f27 * 2.2f), f28, this.mPaint);
            return;
        }
        if (i == 1) {
            float f29 = this.mFraction;
            if (f29 <= 0.2d) {
                this.mTextPaint.setTextSize((this.mTextSize / 0.2f) * f29);
            }
            canvas.drawCircle(this.mCenterX, this.mCenterY, this.mCircleRadius, this.mBgPaint);
            canvas.drawArc(this.mRectF, -90.0f, this.mFraction * 359.99f, false, this.mPaint);
            this.mPath.reset();
            float f30 = this.mCurrentRippleX + 2.0f;
            this.mCurrentRippleX = f30;
            float f31 = this.mCenterX;
            float f32 = this.mBaseRippleLength;
            if (f30 > f31 - (6.0f * f32)) {
                this.mCurrentRippleX = f31 - (f32 * 10.0f);
            }
            this.mPath.moveTo(this.mCurrentRippleX, this.mCenterY);
            for (int i2 = 0; i2 < 4; i2++) {
                Path path = this.mPath;
                float f33 = this.mBaseRippleLength;
                path.rQuadTo(f33, (-(1.0f - this.mFraction)) * f33, f33 * 2.0f, 0.0f);
                Path path2 = this.mPath;
                float f34 = this.mBaseRippleLength;
                path2.rQuadTo(f34, (1.0f - this.mFraction) * f34, f34 * 2.0f, 0.0f);
            }
            canvas.save();
            canvas.clipRect(this.mClipRectF);
            canvas.drawPath(this.mPath, this.mPaint);
            canvas.restore();
            DownloadUnit downloadUnit = DownloadUnit.NONE;
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            canvas.drawCircle(this.mCenterX, this.mCenterY, this.mCircleRadius, this.mBgPaint);
            float f35 = this.mCenterX;
            float f36 = this.mBaseLength;
            float f37 = this.mCenterY;
            float f38 = this.mFraction;
            canvas.drawLine(f35 - f36, f37, (f36 * 0.5f * f38) + (f35 - (f36 * 0.5f)), (f36 * 0.65f) + f37 + (f36 * 0.35f * f38), this.mPaint);
            float f39 = this.mCenterX;
            float f40 = this.mBaseLength;
            float f41 = this.mFraction;
            float f42 = this.mCenterY;
            canvas.drawLine((f39 - (f40 * 0.5f)) + (f40 * 0.5f * f41), (f40 * 0.65f) + f42 + (f40 * 0.35f * f41), (f39 + (1.2f * f40)) - ((0.2f * f40) * f41), (f42 - (f40 * 1.3f)) + (f40 * 1.3f * f41), this.mPaint);
            float f43 = this.mCenterX;
            float f44 = this.mBaseLength;
            float f45 = this.mFraction;
            float f46 = this.mCenterY;
            canvas.drawLine((f43 - (f44 * 0.5f)) + (f44 * 0.5f * f45), (f44 * 0.65f) + f46 + (0.35f * f44 * f45), (0.5f * f44 * f45) + (f43 - (f44 * 0.5f)), (f46 + (0.65f * f44)) - ((f44 * 2.25f) * f45), this.mPaint);
            return;
        }
        canvas.drawCircle(this.mCenterX, this.mCenterY, this.mCircleRadius, this.mPaint);
        float f47 = this.mFraction;
        if (f47 <= 0.5d) {
            Paint paint = this.mTextPaint;
            int i3 = this.mTextSize;
            paint.setTextSize(i3 - ((i3 / 0.2f) * f47));
        } else {
            this.mTextPaint.setTextSize(0.0f);
        }
        if (this.mUnit != DownloadUnit.NONE && this.mCurrentSize > 0.0d) {
            canvas.drawText(String.format("%.2f", Double.valueOf(this.mCurrentSize)) + getUnitStr(this.mUnit), this.mCenterX, this.mCenterY + (this.mBaseLength * 1.4f), this.mTextPaint);
        }
        float f48 = this.mCenterX;
        float f49 = this.mBaseLength;
        float f50 = this.mFraction;
        float f51 = this.mCenterY;
        canvas.drawLine((f48 - (f49 * 2.2f)) + (1.2f * f49 * f50), f51, f48 - (f49 * 0.5f), f51 + (f49 * 0.5f * f50 * 1.3f), this.mPaint);
        float f52 = this.mCenterX;
        float f53 = this.mBaseLength;
        float f54 = this.mCenterY;
        float f55 = this.mFraction;
        canvas.drawLine(f52 - (f53 * 0.5f), f54 + (0.5f * f53 * f55 * 1.3f), (f52 + (2.2f * f53)) - (f53 * f55), f54 - ((f53 * f55) * 1.3f), this.mPaint);
    }

    public void release() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            this.mValueAnimator.removeAllUpdateListeners();
            if (this.mValueAnimator.isRunning()) {
                this.mValueAnimator.cancel();
            }
            this.mValueAnimator = null;
        }
    }

    public void start() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            this.mValueAnimator.removeAllUpdateListeners();
            if (this.mValueAnimator.isRunning()) {
                this.mValueAnimator.cancel();
            }
            this.mValueAnimator = null;
        }
        this.mCurrentState = 1;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 100.0f);
        this.mValueAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(1500L);
        this.mValueAnimator.setInterpolator(new OvershootInterpolator());
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: moe.codeest.enviews.ENDownloadView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                ENDownloadView.this.mFraction = valueAnimator2.getAnimatedFraction();
                ENDownloadView.this.invalidate();
            }
        });
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: moe.codeest.enviews.ENDownloadView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ENDownloadView.this.mCurrentState = 1;
                ENDownloadView.this.downloadAnim();
            }
        });
        this.mValueAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadAnim() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            this.mValueAnimator.removeAllUpdateListeners();
            if (this.mValueAnimator.isRunning()) {
                this.mValueAnimator.cancel();
            }
            this.mValueAnimator = null;
        }
        if (this.mCurrentState != 1) {
            return;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 100.0f);
        this.mValueAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(this.mDownloadTime);
        this.mValueAnimator.setInterpolator(new LinearInterpolator());
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: moe.codeest.enviews.ENDownloadView.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                ENDownloadView.this.mFraction = valueAnimator2.getAnimatedFraction();
                if (ENDownloadView.this.mUnit != DownloadUnit.NONE && ENDownloadView.this.mTotalSize > 0.0d) {
                    ENDownloadView eNDownloadView = ENDownloadView.this;
                    eNDownloadView.mCurrentSize = ((double) eNDownloadView.mFraction) * ENDownloadView.this.mTotalSize;
                }
                ENDownloadView.this.invalidate();
            }
        });
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: moe.codeest.enviews.ENDownloadView.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ENDownloadView.this.mCurrentState = 1;
                ENDownloadView.this.downloadAnim();
            }
        });
        this.mValueAnimator.start();
    }

    private void endAnim() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            this.mValueAnimator.removeAllUpdateListeners();
            if (this.mValueAnimator.isRunning()) {
                this.mValueAnimator.cancel();
            }
            this.mValueAnimator = null;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 100.0f);
        this.mValueAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(700L);
        this.mValueAnimator.setInterpolator(new OvershootInterpolator());
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: moe.codeest.enviews.ENDownloadView.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                ENDownloadView.this.mFraction = valueAnimator2.getAnimatedFraction();
                ENDownloadView.this.invalidate();
            }
        });
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: moe.codeest.enviews.ENDownloadView.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ENDownloadView.this.mFraction = 0.0f;
                ENDownloadView.this.mCurrentState = 3;
                if (ENDownloadView.this.onDownloadStateListener != null) {
                    ENDownloadView.this.onDownloadStateListener.onDownloadFinish();
                }
            }
        });
        this.mValueAnimator.start();
    }

    public void reset() {
        this.mFraction = 0.0f;
        this.mCurrentState = 0;
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            this.mValueAnimator.removeAllUpdateListeners();
            if (this.mValueAnimator.isRunning()) {
                this.mValueAnimator.cancel();
            }
            this.mValueAnimator = null;
        }
    }

    /* JADX INFO: renamed from: moe.codeest.enviews.ENDownloadView$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$moe$codeest$enviews$ENDownloadView$DownloadUnit;

        static {
            int[] iArr = new int[DownloadUnit.values().length];
            $SwitchMap$moe$codeest$enviews$ENDownloadView$DownloadUnit = iArr;
            try {
                iArr[DownloadUnit.GB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$moe$codeest$enviews$ENDownloadView$DownloadUnit[DownloadUnit.MB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$moe$codeest$enviews$ENDownloadView$DownloadUnit[DownloadUnit.KB.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$moe$codeest$enviews$ENDownloadView$DownloadUnit[DownloadUnit.B.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private String getUnitStr(DownloadUnit downloadUnit) {
        int i = AnonymousClass7.$SwitchMap$moe$codeest$enviews$ENDownloadView$DownloadUnit[downloadUnit.ordinal()];
        if (i == 1) {
            return " gb";
        }
        if (i == 2) {
            return " mb";
        }
        if (i == 3) {
            return " kb";
        }
        return " b";
    }

    public void setDownloadConfig(int i, double d, DownloadUnit downloadUnit) {
        this.mDownloadTime = i;
        this.mTotalSize = d;
        this.mUnit = downloadUnit;
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public void setOnDownloadStateListener(OnDownloadStateListener onDownloadStateListener) {
        this.onDownloadStateListener = onDownloadStateListener;
    }
}
