package com.sqwan.msdk.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CircularProgressView extends View {
    private int backgroundColor;
    private Paint backgroundPaint;
    private int maxProgress;
    private int progress;
    private int progressColor;
    private Paint progressPaint;
    private RectF rectF;
    private float strokeWidth;
    private int textColor;
    private Paint textPaint;
    private float textSize;

    public CircularProgressView(Context context) {
        super(context);
        this.progress = 0;
        this.maxProgress = 100;
        this.backgroundColor = -8355712;
        this.progressColor = -1;
        this.textColor = -13421773;
        this.strokeWidth = 8.0f;
        this.textSize = 24.0f;
        init();
    }

    public CircularProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.progress = 0;
        this.maxProgress = 100;
        this.backgroundColor = -8355712;
        this.progressColor = -1;
        this.textColor = -13421773;
        this.strokeWidth = 8.0f;
        this.textSize = 24.0f;
        init();
    }

    public CircularProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.progress = 0;
        this.maxProgress = 100;
        this.backgroundColor = -8355712;
        this.progressColor = -1;
        this.textColor = -13421773;
        this.strokeWidth = 8.0f;
        this.textSize = 24.0f;
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.backgroundPaint = paint;
        paint.setAntiAlias(true);
        this.backgroundPaint.setColor(this.backgroundColor);
        this.backgroundPaint.setStyle(Paint.Style.STROKE);
        this.backgroundPaint.setStrokeWidth(this.strokeWidth);
        this.backgroundPaint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.progressPaint = paint2;
        paint2.setAntiAlias(true);
        this.progressPaint.setColor(this.progressColor);
        this.progressPaint.setStyle(Paint.Style.STROKE);
        this.progressPaint.setStrokeWidth(this.strokeWidth);
        this.progressPaint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint3 = new Paint();
        this.textPaint = paint3;
        paint3.setAntiAlias(true);
        this.textPaint.setColor(this.textColor);
        this.textPaint.setTextSize(this.textSize);
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        this.rectF = new RectF();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int iMin = Math.min(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        setMeasuredDimension(iMin, iMin);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int iMin = Math.min(width, height) / 2;
        float f = this.strokeWidth / 2.0f;
        float f2 = width;
        float f3 = height;
        this.rectF.set(f, f, f2 - f, f3 - f);
        canvas.drawCircle(f2 / 2.0f, f3 / 2.0f, iMin - (this.strokeWidth / 2.0f), this.backgroundPaint);
        int i = this.progress;
        if (i > 0) {
            canvas.drawArc(this.rectF, -90.0f, (i * 360.0f) / this.maxProgress, false, this.progressPaint);
        }
    }

    public void setProgress(int i) {
        this.progress = Math.max(0, Math.min(i, this.maxProgress));
        invalidate();
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgressColor(int i) {
        this.progressColor = i;
        this.progressPaint.setColor(i);
        invalidate();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
        this.backgroundPaint.setColor(i);
        invalidate();
    }

    public void setStrokeWidth(float f) {
        this.strokeWidth = f;
        this.backgroundPaint.setStrokeWidth(f);
        this.progressPaint.setStrokeWidth(f);
        invalidate();
    }

    public void setTextSize(float f) {
        this.textSize = f;
        this.textPaint.setTextSize(f);
        invalidate();
    }
}
