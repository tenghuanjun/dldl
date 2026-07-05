package com.aliyun.aliyunface.ui.widget;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class RoundProgressBar extends View {
    public static final int FILL = 1;
    public static final int STROKE = 0;
    private float backColorWidth;
    private int backgroundColor;
    private Context ctx;
    private int endAngle;
    private boolean isPausing;
    public BitmapShader mBitmapShader;
    private Handler mMainHandle;
    private Matrix mMatrix;
    int mProgressAngle;
    private SweepGradient mRoundShader;
    private int mWidth;
    private int max;
    private int maxMiliSeconds;
    protected Paint paint;
    private Runnable processRunnable;
    private int progress;
    private int radius;
    protected int roundColor;
    private RoundProgressCallback roundProgressCallback;
    protected int roundProgressColor;
    private boolean roundShader;
    private int roundShaderEndColor;
    private int roundShaderStartColor;
    private float roundWidth;
    private int secondProgressColor;
    protected int startAngle;
    private int style;
    private int textColor;
    private boolean textIsDisplayable;
    private float textSize;

    public int getRadius() {
        return this.radius;
    }

    public RoundProgressBar(Context context) {
        this(context, (AttributeSet) null);
        this.ctx = context;
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.ctx = context;
    }

    public void startProcess(int i, RoundProgressCallback roundProgressCallback) {
        this.roundProgressCallback = roundProgressCallback;
        setProgress(0);
        this.maxMiliSeconds = i;
        this.mMainHandle.post(this.processRunnable);
    }

    public void setGradientColor(int i) {
        this.roundShaderEndColor = i;
    }

    public void pauseProcess(boolean z) {
        this.isPausing = z;
    }

    public void stopProcess() {
        this.mMainHandle.removeCallbacks(this.processRunnable);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isPausing = false;
        this.style = 0;
        this.radius = 0;
        this.maxMiliSeconds = -1;
        this.processRunnable = new Runnable() { // from class: com.aliyun.aliyunface.ui.widget.RoundProgressBar.1
            @Override // java.lang.Runnable
            public void run() {
                if (RoundProgressBar.this.isPausing) {
                    RoundProgressBar.this.mMainHandle.postDelayed(this, RoundProgressBar.this.maxMiliSeconds / RoundProgressBar.this.getMax());
                    return;
                }
                int progress = RoundProgressBar.this.getProgress() + 1;
                if (RoundProgressBar.this.roundProgressCallback != null) {
                    RoundProgressBar.this.roundProgressCallback.onProgress(RoundProgressBar.this.progress);
                }
                if (progress >= RoundProgressBar.this.getMax()) {
                    progress = RoundProgressBar.this.getMax();
                }
                RoundProgressBar.this.setProgress(progress);
                if (progress >= RoundProgressBar.this.getMax()) {
                    if (RoundProgressBar.this.roundProgressCallback != null) {
                        RoundProgressBar.this.roundProgressCallback.onFinish();
                        RoundProgressBar.this.stopProcess();
                        return;
                    }
                    return;
                }
                RoundProgressBar.this.mMainHandle.postDelayed(this, RoundProgressBar.this.maxMiliSeconds / RoundProgressBar.this.getMax());
            }
        };
        this.mProgressAngle = 0;
        this.paint = new Paint();
        this.mMainHandle = new Handler(Looper.getMainLooper());
        initAttr(context, attributeSet);
    }

    private void initAttr(Context context, AttributeSet attributeSet) {
        this.roundColor = context.getResources().getColor(context.getResources().getIdentifier("toyger_circle_progress_background", "color", context.getPackageName()));
        this.roundProgressColor = context.getResources().getColor(context.getResources().getIdentifier("toyger_circle_progress_foreground", "color", context.getPackageName()));
        this.secondProgressColor = context.getResources().getColor(context.getResources().getIdentifier("toyger_circle_progress_foreground", "color", context.getPackageName()));
        this.textColor = -16777216;
        this.textSize = 15.0f;
        this.roundWidth = 5.0f;
        this.max = 100;
        this.textIsDisplayable = false;
        this.style = 0;
        this.roundShader = true;
        this.roundShaderStartColor = context.getResources().getColor(context.getResources().getIdentifier("toyger_circle_progress_background", "color", context.getPackageName()));
        this.roundShaderEndColor = context.getResources().getColor(context.getResources().getIdentifier("toyger_circle_progress_foreground", "color", context.getPackageName()));
        this.startAngle = -240;
        this.endAngle = 60;
        this.backColorWidth = 0.0f;
        this.backgroundColor = -1;
        if (0.0f <= 0.0f || !this.roundShader) {
            return;
        }
        this.mMatrix = new Matrix();
        this.mBitmapShader = new BitmapShader(BitmapFactory.decodeResource(getResources(), context.getResources().getIdentifier("zface_circle_bg", "mipmap", context.getPackageName())), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.mWidth = (int) this.backColorWidth;
        float fMin = (this.mWidth * 1.0f) / Math.min(r6.getWidth(), r6.getHeight());
        this.mMatrix.setScale(fMin, fMin);
        this.mBitmapShader.setLocalMatrix(this.mMatrix);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth() / 2;
        this.radius = (int) (width - (this.roundWidth / 2.0f));
        this.paint.setColor(this.roundColor);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(this.roundWidth);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setColor(this.backgroundColor);
        this.paint.setStrokeWidth(0.0f);
        this.paint.setColor(this.textColor);
        this.paint.setTextSize(this.textSize);
        this.paint.setTypeface(Typeface.DEFAULT_BOLD);
        int i = (int) ((this.progress / this.max) * 100.0f);
        float fMeasureText = this.paint.measureText(i + "%");
        this.paint.setShader((Shader) null);
        if (this.textIsDisplayable && i != 0 && this.style == 0) {
            canvas.drawText(i + "%", width - (fMeasureText / 2.0f), width + (this.textSize / 2.0f), this.paint);
        }
        this.paint.setStrokeWidth(this.roundWidth);
        int i2 = this.radius;
        RectF rectF = new RectF(r0 - i2, r0 - i2, r0 + i2, r0 + i2);
        this.paint.setColor(this.roundColor);
        int i3 = this.style;
        if (i3 == 0) {
            paintStroke(canvas, rectF);
            return;
        }
        if (i3 != 1) {
            return;
        }
        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        if (this.progress != 0) {
            int i4 = this.startAngle;
            canvas.drawArc(rectF, i4 + 90, ((this.endAngle - i4) * r0) / this.max, true, this.paint);
        }
    }

    private void paintStroke(Canvas canvas, RectF rectF) {
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(this.roundColor);
        canvas.drawArc(rectF, this.startAngle, this.endAngle - r0, false, this.paint);
        BitmapShader bitmapShader = this.mBitmapShader;
        if (bitmapShader != null) {
            this.paint.setShader(bitmapShader);
        }
        if (this.roundShader && this.roundShaderStartColor != 0 && this.roundShaderEndColor != 0 && this.mRoundShader == null) {
            float fCenterX = rectF.centerX();
            float fCenterY = rectF.centerY();
            this.mRoundShader = new SweepGradient(fCenterX, fCenterY, new int[]{this.roundShaderStartColor, this.roundShaderEndColor}, (float[]) null);
            Matrix matrix = new Matrix();
            matrix.setRotate(90.0f, fCenterX, fCenterY);
            this.mRoundShader.setLocalMatrix(matrix);
        }
        SweepGradient sweepGradient = this.mRoundShader;
        if (sweepGradient != null) {
            this.paint.setShader(sweepGradient);
        }
        this.paint.setColor(this.roundProgressColor);
        canvas.drawArc(rectF, this.startAngle, (this.progress * (this.endAngle - this.startAngle)) / getMax(), false, this.paint);
        this.paint.setShader((Shader) null);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
        postInvalidate();
    }

    public void setRoundColor(int i) {
        this.roundColor = i;
        postInvalidate();
    }

    public synchronized int getMax() {
        return this.max;
    }

    public synchronized void setMax(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = i;
    }

    public synchronized int getProgress() {
        return this.progress;
    }

    public synchronized void setProgress(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (i > this.max) {
            i = this.max;
        }
        if (i <= this.max) {
            this.progress = i;
            postInvalidate();
        }
    }

    public synchronized void setProgressAngle(int i) {
        this.mProgressAngle = i;
        postInvalidate();
    }

    public int getCricleColor() {
        return this.roundColor;
    }

    public void setCricleColor(int i) {
        this.roundColor = i;
    }

    public int getCricleProgressColor() {
        return this.roundProgressColor;
    }

    public void setCricleProgressColor(int i) {
        this.roundProgressColor = i;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void setTextSize(float f) {
        this.textSize = f;
    }

    public float getRoundWidth() {
        return this.roundWidth;
    }

    public void setRoundWidth(float f) {
        this.roundWidth = f;
    }

    public void setRoundProgressColor(int i) {
        this.roundProgressColor = i;
    }
}
