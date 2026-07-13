package com.hjq.shape.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.widget.TextView;
import com.hjq.shape.config.ITextViewAttribute;
import com.hjq.shape.other.TextViewAttribute;

/* JADX INFO: loaded from: classes3.dex */
public class StrokeFontSpan extends CommonFontSpan {
    private final Paint mStrokePaint;
    private int mTextSolidColor;
    private int mTextStrokeColor;
    private int mTextStrokeSize;

    public static SpannableStringBuilder buildStrokeFontSpannable(TextView textView, CharSequence charSequence, int i, int i2) {
        return buildStrokeFontSpannable(new TextViewAttribute(textView), charSequence, i, i2);
    }

    public static SpannableStringBuilder buildStrokeFontSpannable(ITextViewAttribute iTextViewAttribute, CharSequence charSequence, int i, int i2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        spannableStringBuilder.setSpan(new StrokeFontSpan(iTextViewAttribute).setTextStrokeColor(i).setTextStrokeSize(i2), 0, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    public StrokeFontSpan(ITextViewAttribute iTextViewAttribute) {
        super(iTextViewAttribute);
        this.mStrokePaint = new Paint();
    }

    @Override // com.hjq.shape.span.CommonFontSpan
    public void onDraw(Canvas canvas, Paint paint, CharSequence charSequence, float f, int i, int i2, float f2, int i3, int i4, int i5) {
        this.mStrokePaint.set(paint);
        this.mStrokePaint.setShader(null);
        this.mStrokePaint.setAntiAlias(true);
        this.mStrokePaint.setDither(true);
        this.mStrokePaint.setTextSize(paint.getTextSize());
        this.mStrokePaint.setStrokeWidth(this.mTextStrokeSize);
        this.mStrokePaint.setStyle(Paint.Style.STROKE);
        this.mStrokePaint.setColor(this.mTextStrokeColor);
        float f3 = i4;
        canvas.drawText(charSequence, i, i2, f2, f3, this.mStrokePaint);
        int i6 = this.mTextSolidColor;
        if (i6 != 0) {
            paint.setColor(i6);
            canvas.drawText(charSequence, i, i2, f2, f3, paint);
        }
    }

    public StrokeFontSpan setTextSolidColor(int i) {
        this.mTextSolidColor = i;
        return this;
    }

    public StrokeFontSpan setTextStrokeColor(int i) {
        this.mTextStrokeColor = i;
        return this;
    }

    public StrokeFontSpan setTextStrokeSize(int i) {
        this.mTextStrokeSize = i;
        return this;
    }
}
