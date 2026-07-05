package com.sqwan.msdk.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.TextView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CustomTextView extends TextView {
    public static final String TAG = CustomTextView.class.getSimpleName();
    private Paint paint;
    private float positionText1;
    private float positionText2;
    private float speed;
    private String text;
    private float textLength;
    private float textSpace;
    private float viewWidth;
    private float y;

    public CustomTextView(Context context) {
        super(context);
        this.textLength = 0.0f;
        this.viewWidth = 0.0f;
        this.y = 0.0f;
        this.paint = null;
        this.text = "";
        this.textSpace = 1200.0f;
        this.positionText1 = 0.0f;
        this.positionText2 = 0.0f;
        this.speed = 3.0f;
    }

    public CustomTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textLength = 0.0f;
        this.viewWidth = 0.0f;
        this.y = 0.0f;
        this.paint = null;
        this.text = "";
        this.textSpace = 1200.0f;
        this.positionText1 = 0.0f;
        this.positionText2 = 0.0f;
        this.speed = 3.0f;
    }

    public CustomTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.textLength = 0.0f;
        this.viewWidth = 0.0f;
        this.y = 0.0f;
        this.paint = null;
        this.text = "";
        this.textSpace = 1200.0f;
        this.positionText1 = 0.0f;
        this.positionText2 = 0.0f;
        this.speed = 3.0f;
    }

    public void setText(String str) {
        super.setText((CharSequence) str);
        this.text = str;
        init();
    }

    public void init() {
        TextPaint paint = getPaint();
        this.paint = paint;
        this.textLength = paint.measureText(this.text);
        int width = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getWidth();
        if (getContext().getResources().getConfiguration().orientation == 2) {
            this.textSpace = (r0.getDefaultDisplay().getHeight() - this.textLength) - 240.0f;
        } else {
            this.textSpace = (width - this.textLength) - 200.0f;
        }
        if (this.textSpace < 0.0f) {
            this.textSpace = 200.0f;
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        float width = getWidth();
        this.viewWidth = width;
        if (width == 0.0f) {
            this.viewWidth = this.textLength / 2.0f;
        }
        float f = this.viewWidth;
        this.positionText1 = f;
        this.positionText2 = f + this.textLength + this.textSpace;
        this.y = getBaseline(this.paint);
    }

    private static float getBaseline(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return ((fontMetrics.descent - fontMetrics.ascent) / 2.0f) - fontMetrics.descent;
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        canvas.drawText(this.text, this.positionText1, getHeight() - this.y, this.paint);
        canvas.drawText(this.text, this.positionText2, getHeight() - this.y, this.paint);
        float f = this.positionText1;
        float f2 = this.speed;
        float f3 = f - f2;
        this.positionText1 = f3;
        float f4 = this.positionText2 - f2;
        this.positionText2 = f4;
        float f5 = this.textLength;
        if (f3 < (-f5)) {
            this.positionText1 = f4 + this.textSpace + f5;
        }
        float f6 = this.positionText2;
        float f7 = this.textLength;
        if (f6 < (-f7)) {
            this.positionText2 = this.positionText1 + this.textSpace + f7;
        }
        invalidate();
    }
}
