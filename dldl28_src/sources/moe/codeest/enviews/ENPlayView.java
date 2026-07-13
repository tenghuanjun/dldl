package moe.codeest.enviews;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import com.shuyu.gsyvideoplayer.R;

/* JADX INFO: loaded from: classes4.dex */
public class ENPlayView extends View {
    public static int DEFAULT_BG_LINE_COLOR = -328966;
    public static int DEFAULT_BG_LINE_WIDTH = 4;
    public static int DEFAULT_DURATION = 1200;
    public static int DEFAULT_LINE_COLOR = -1;
    public static int DEFAULT_LINE_WIDTH = 4;
    public static int STATE_PAUSE = 1;
    public static int STATE_PLAY;
    private Paint mBgPaint;
    private RectF mBgRectF;
    private int mCenterX;
    private int mCenterY;
    private int mCircleRadius;
    private int mCurrentState;
    private Path mDstPath;
    private int mDuration;
    private float mFraction;
    private int mHeight;
    private Paint mPaint;
    private Path mPath;
    private float mPathLength;
    private PathMeasure mPathMeasure;
    private RectF mRectF;
    private int mWidth;

    public ENPlayView(Context context) {
        super(context);
        this.mCurrentState = STATE_PAUSE;
        this.mFraction = 1.0f;
    }

    public ENPlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentState = STATE_PAUSE;
        this.mFraction = 1.0f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.play);
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.play_play_line_color, DEFAULT_LINE_COLOR);
        int color2 = typedArrayObtainStyledAttributes.getColor(R.styleable.play_play_bg_line_color, DEFAULT_BG_LINE_COLOR);
        int integer = typedArrayObtainStyledAttributes.getInteger(R.styleable.play_play_line_width, dp2px(DEFAULT_LINE_WIDTH));
        int integer2 = typedArrayObtainStyledAttributes.getInteger(R.styleable.play_play_bg_line_width, dp2px(DEFAULT_BG_LINE_WIDTH));
        typedArrayObtainStyledAttributes.recycle();
        setLayerType(1, null);
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setColor(color);
        this.mPaint.setStrokeWidth(integer);
        this.mPaint.setPathEffect(new CornerPathEffect(1.0f));
        Paint paint2 = new Paint(1);
        this.mBgPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.mBgPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mBgPaint.setColor(color2);
        this.mBgPaint.setStrokeWidth(integer2);
        this.mPath = new Path();
        this.mDstPath = new Path();
        this.mPathMeasure = new PathMeasure();
        this.mDuration = DEFAULT_DURATION;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int i5 = (i * 9) / 10;
        this.mWidth = i5;
        this.mHeight = (i2 * 9) / 10;
        this.mCircleRadius = i5 / dp2px(4);
        this.mCenterX = i / 2;
        this.mCenterY = i2 / 2;
        int i6 = this.mCenterX;
        int i7 = this.mCircleRadius;
        int i8 = this.mCenterY;
        this.mRectF = new RectF(i6 - i7, i8 + (i7 * 0.6f), i6 + i7, i8 + (i7 * 2.6f));
        int i9 = this.mCenterX;
        int i10 = this.mWidth;
        int i11 = this.mCenterY;
        int i12 = this.mHeight;
        this.mBgRectF = new RectF(i9 - (i10 / 2), i11 - (i12 / 2), i9 + (i10 / 2), i11 + (i12 / 2));
        Path path = this.mPath;
        int i13 = this.mCenterX;
        path.moveTo(i13 - r7, this.mCenterY + (this.mCircleRadius * 1.8f));
        Path path2 = this.mPath;
        int i14 = this.mCenterX;
        path2.lineTo(i14 - r7, this.mCenterY - (this.mCircleRadius * 1.8f));
        this.mPath.lineTo(this.mCenterX + this.mCircleRadius, this.mCenterY);
        this.mPath.close();
        this.mPathMeasure.setPath(this.mPath, false);
        this.mPathLength = this.mPathMeasure.getLength();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(this.mCenterX, this.mCenterY, this.mWidth / 2, this.mBgPaint);
        float f = this.mFraction;
        if (f < 0.0f) {
            int i = this.mCenterX;
            int i2 = this.mCircleRadius;
            int i3 = this.mCenterY;
            canvas.drawLine(i + i2, (i3 - (i2 * 1.6f)) + (i2 * 10 * f), i + i2, i3 + (i2 * 1.6f) + (i2 * 10 * f), this.mPaint);
            int i4 = this.mCenterX;
            int i5 = this.mCircleRadius;
            int i6 = this.mCenterY;
            canvas.drawLine(i4 - i5, i6 - (i5 * 1.6f), i4 - i5, i6 + (i5 * 1.6f), this.mPaint);
            canvas.drawArc(this.mBgRectF, -105.0f, 360.0f, false, this.mPaint);
            return;
        }
        if (f <= 0.3d) {
            int i7 = this.mCenterX;
            int i8 = this.mCircleRadius;
            int i9 = this.mCenterY;
            canvas.drawLine(i7 + i8, (i9 - (i8 * 1.6f)) + (((i8 * 3.2f) / 0.3f) * f), i7 + i8, i9 + (i8 * 1.6f), this.mPaint);
            int i10 = this.mCenterX;
            int i11 = this.mCircleRadius;
            int i12 = this.mCenterY;
            canvas.drawLine(i10 - i11, i12 - (i11 * 1.6f), i10 - i11, i12 + (i11 * 1.6f), this.mPaint);
            float f2 = this.mFraction;
            if (f2 != 0.0f) {
                canvas.drawArc(this.mRectF, 0.0f, f2 * 600.0f, false, this.mPaint);
            }
            canvas.drawArc(this.mBgRectF, (r1 * 360.0f) - 105.0f, (1.0f - this.mFraction) * 360.0f, false, this.mPaint);
            return;
        }
        if (f <= 0.6d) {
            canvas.drawArc(this.mRectF, (f - 0.3f) * 600.0f, 180.0f - ((f - 0.3f) * 600.0f), false, this.mPaint);
            this.mDstPath.reset();
            PathMeasure pathMeasure = this.mPathMeasure;
            float f3 = this.mPathLength;
            pathMeasure.getSegment(0.02f * f3, (0.38f * f3) + (((f3 * 0.42f) / 0.3f) * (this.mFraction - 0.3f)), this.mDstPath, true);
            canvas.drawPath(this.mDstPath, this.mPaint);
            canvas.drawArc(this.mBgRectF, (r1 * 360.0f) - 105.0f, (1.0f - this.mFraction) * 360.0f, false, this.mPaint);
            return;
        }
        if (f <= 0.8d) {
            this.mDstPath.reset();
            PathMeasure pathMeasure2 = this.mPathMeasure;
            float f4 = this.mPathLength;
            float f5 = this.mFraction;
            pathMeasure2.getSegment((0.02f * f4) + (((f4 * 0.2f) / 0.2f) * (f5 - 0.6f)), (0.8f * f4) + (((f4 * 0.2f) / 0.2f) * (f5 - 0.6f)), this.mDstPath, true);
            canvas.drawPath(this.mDstPath, this.mPaint);
            canvas.drawArc(this.mBgRectF, (r1 * 360.0f) - 105.0f, (1.0f - this.mFraction) * 360.0f, false, this.mPaint);
            return;
        }
        this.mDstPath.reset();
        this.mPathMeasure.getSegment(this.mCircleRadius * 10 * (this.mFraction - 1.0f), this.mPathLength, this.mDstPath, true);
        canvas.drawPath(this.mDstPath, this.mPaint);
    }

    public void play() {
        int i = this.mCurrentState;
        int i2 = STATE_PLAY;
        if (i == i2) {
            return;
        }
        this.mCurrentState = i2;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 100.0f);
        valueAnimatorOfFloat.setDuration(this.mDuration);
        valueAnimatorOfFloat.setInterpolator(new AnticipateInterpolator());
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: moe.codeest.enviews.ENPlayView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ENPlayView.this.mFraction = 1.0f - valueAnimator.getAnimatedFraction();
                ENPlayView.this.invalidate();
            }
        });
        if (valueAnimatorOfFloat.isRunning()) {
            return;
        }
        valueAnimatorOfFloat.start();
    }

    public void pause() {
        int i = this.mCurrentState;
        int i2 = STATE_PAUSE;
        if (i == i2) {
            return;
        }
        this.mCurrentState = i2;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 100.0f);
        valueAnimatorOfFloat.setDuration(this.mDuration);
        valueAnimatorOfFloat.setInterpolator(new AnticipateInterpolator());
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: moe.codeest.enviews.ENPlayView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ENPlayView.this.mFraction = valueAnimator.getAnimatedFraction();
                ENPlayView.this.invalidate();
            }
        });
        if (valueAnimatorOfFloat.isRunning()) {
            return;
        }
        valueAnimatorOfFloat.start();
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    private int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, i, getContext().getResources().getDisplayMetrics());
    }
}
