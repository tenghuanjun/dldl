package com.hjq.shape.span;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.SpannableStringBuilder;
import android.widget.TextView;
import com.hjq.shape.config.ITextViewAttribute;
import com.hjq.shape.other.TextViewAttribute;

/* JADX INFO: loaded from: classes3.dex */
public class LinearGradientFontSpan extends CommonFontSpan {
    public static final int GRADIENT_ORIENTATION_HORIZONTAL = 0;
    public static final int GRADIENT_ORIENTATION_VERTICAL = 1;
    private int[] mTextGradientColor;
    private int mTextGradientOrientation;
    private float[] mTextGradientPositions;

    public static SpannableStringBuilder buildLinearGradientFontSpannable(TextView textView, CharSequence charSequence, int[] iArr, float[] fArr, int i) {
        return buildLinearGradientFontSpannable(new TextViewAttribute(textView), charSequence, iArr, fArr, i);
    }

    public static SpannableStringBuilder buildLinearGradientFontSpannable(ITextViewAttribute iTextViewAttribute, CharSequence charSequence, int[] iArr, float[] fArr, int i) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        spannableStringBuilder.setSpan(new LinearGradientFontSpan(iTextViewAttribute).setTextGradientColor(iArr).setTextGradientOrientation(i).setTextGradientPositions(fArr), 0, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    public LinearGradientFontSpan(ITextViewAttribute iTextViewAttribute) {
        super(iTextViewAttribute);
    }

    @Override // com.hjq.shape.span.CommonFontSpan
    public void onDraw(Canvas canvas, Paint paint, CharSequence charSequence, float f, int i, int i2, float f2, int i3, int i4, int i5) {
        LinearGradient linearGradient;
        if (this.mTextGradientOrientation == 1) {
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, fontMetricsInt.bottom - fontMetricsInt.top, this.mTextGradientColor, this.mTextGradientPositions, Shader.TileMode.MIRROR);
        } else {
            linearGradient = new LinearGradient(f2, 0.0f, f2 + f, 0.0f, this.mTextGradientColor, this.mTextGradientPositions, Shader.TileMode.REPEAT);
        }
        paint.setShader(linearGradient);
        canvas.drawText(charSequence, i, i2, f2, i4, paint);
    }

    public LinearGradientFontSpan setTextGradientOrientation(int i) {
        this.mTextGradientOrientation = i;
        return this;
    }

    public LinearGradientFontSpan setTextGradientColor(int[] iArr) {
        this.mTextGradientColor = iArr;
        return this;
    }

    public LinearGradientFontSpan setTextGradientPositions(float[] fArr) {
        this.mTextGradientPositions = fArr;
        return this;
    }
}
