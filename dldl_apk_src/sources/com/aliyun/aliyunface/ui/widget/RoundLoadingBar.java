package com.aliyun.aliyunface.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import com.alibaba.fastjson.asm.Opcodes;
import com.aliyun.aliyuncomm.R;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class RoundLoadingBar extends View {
    public static final int FILL = 1;
    public static final int STROKE = 0;
    private float backColorWidth;
    private int backgroundColor;
    private Context ctx;
    private int endAngle;
    public BitmapShader mBitmapShader;
    private Handler mMainHandle;
    private Matrix mMatrix;
    int mProgressAngle;
    private int mWidth;
    private int max;
    protected Paint paint;
    private Runnable processRunnable;
    private int progress;
    private int radius;
    protected int roundColor;
    protected int roundProgressColor;
    private boolean roundShader;
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

    public RoundLoadingBar(Context context) {
        this(context, null);
        this.ctx = context;
    }

    public RoundLoadingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.ctx = context;
    }

    public void startLoading() {
        this.mProgressAngle = 0;
        this.mMainHandle.post(this.processRunnable);
    }

    public void stopLoading() {
        this.mMainHandle.removeCallbacks(this.processRunnable);
    }

    public RoundLoadingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.style = 0;
        this.radius = 0;
        this.processRunnable = new Runnable() { // from class: com.aliyun.aliyunface.ui.widget.RoundLoadingBar.1
            @Override // java.lang.Runnable
            public void run() {
                RoundLoadingBar.this.mProgressAngle += 5;
                RoundLoadingBar.this.mProgressAngle %= 360;
                RoundLoadingBar roundLoadingBar = RoundLoadingBar.this;
                roundLoadingBar.setProgressAngle(roundLoadingBar.mProgressAngle);
                RoundLoadingBar.this.mMainHandle.postDelayed(this, 80L);
            }
        };
        this.mProgressAngle = 0;
        this.paint = new Paint();
        this.mMainHandle = new Handler(Looper.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.zface_round_progressBar);
        this.roundColor = typedArrayObtainStyledAttributes.getColor(R.styleable.zface_round_progressBar_zface_round_color, -65536);
        this.roundProgressColor = typedArrayObtainStyledAttributes.getColor(R.styleable.zface_round_progressBar_zface_round_progress_color, -16711936);
        this.secondProgressColor = typedArrayObtainStyledAttributes.getColor(R.styleable.zface_round_progressBar_zface_round_progress_color, -16711936);
        this.textColor = typedArrayObtainStyledAttributes.getColor(R.styleable.zface_round_progressBar_zface_text_color, -16711936);
        this.textSize = typedArrayObtainStyledAttributes.getDimension(R.styleable.zface_round_progressBar_zface_text_size, 15.0f);
        this.roundWidth = typedArrayObtainStyledAttributes.getDimension(R.styleable.zface_round_progressBar_zface_round_width, 5.0f);
        this.max = typedArrayObtainStyledAttributes.getInteger(R.styleable.zface_round_progressBar_zface_max, 100);
        this.textIsDisplayable = typedArrayObtainStyledAttributes.getBoolean(R.styleable.zface_round_progressBar_zface_text_is_displayable, true);
        this.style = typedArrayObtainStyledAttributes.getInt(R.styleable.zface_round_progressBar_zface_style, 0);
        this.roundShader = typedArrayObtainStyledAttributes.getBoolean(R.styleable.zface_round_progressBar_zface_progress_shader, false);
        this.backColorWidth = typedArrayObtainStyledAttributes.getDimension(R.styleable.zface_round_progressBar_zface_color_bg_width, 0.0f);
        this.startAngle = typedArrayObtainStyledAttributes.getInt(R.styleable.zface_round_progressBar_zface_start_angle, 0);
        this.endAngle = typedArrayObtainStyledAttributes.getInt(R.styleable.zface_round_progressBar_zface_end_angle, 360);
        this.backgroundColor = typedArrayObtainStyledAttributes.getColor(R.styleable.zface_round_progressBar_zface_background_color, -1);
        if (this.backColorWidth > 0.0f && this.roundShader) {
            this.mMatrix = new Matrix();
            this.mBitmapShader = new BitmapShader(BitmapFactory.decodeResource(getResources(), R.mipmap.zface_circle_bg), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.mWidth = (int) this.backColorWidth;
            float fMin = (this.mWidth * 1.0f) / Math.min(r4.getWidth(), r4.getHeight());
            this.mMatrix.setScale(fMin, fMin);
            this.mBitmapShader.setLocalMatrix(this.mMatrix);
        }
        typedArrayObtainStyledAttributes.recycle();
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
        this.paint.setShader(null);
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
        BitmapShader bitmapShader = this.mBitmapShader;
        if (bitmapShader != null) {
            this.paint.setShader(bitmapShader);
        }
        this.paint.setColor(this.roundColor);
        canvas.drawArc(rectF, 0.0f, 360.0f, false, this.paint);
        this.paint.setColor(this.roundProgressColor);
        canvas.drawArc(rectF, this.mProgressAngle, 50.0f, false, this.paint);
        canvas.drawArc(rectF, (this.mProgressAngle + Opcodes.GETFIELD) % 360, 50.0f, false, this.paint);
        this.paint.setShader(null);
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
