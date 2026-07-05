package com.huya.berry.sdklive.living.messageboard.helper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import com.huya.berry.gamesdk.utils.UIUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class RoundBackgroundColorSpan extends ReplacementSpan {
    private int mBgColor;
    private int mSize;
    private String mText;
    private int mTextColor;

    public RoundBackgroundColorSpan(int i, int i2, String str) {
        this.mBgColor = i;
        this.mTextColor = i2;
        this.mText = str;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        int iMeasureText = (int) (paint.measureText(charSequence, i, i2) + UIUtil.getDp(20.0f));
        this.mSize = iMeasureText;
        return iMeasureText;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        paint.setColor(this.mBgColor);
        float f2 = i5;
        canvas.drawRoundRect(new RectF(f, f2 - UIUtil.getDp(14.0f), this.mSize + f, f2), UIUtil.getDp(8.0f), UIUtil.getDp(8.0f), paint);
        paint.setColor(this.mTextColor);
        paint.setTextSize(UIUtil.sp2px(11.0f));
        paint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        canvas.drawText(this.mText, f + UIUtil.getDp(10.0f), i4, paint);
    }
}
