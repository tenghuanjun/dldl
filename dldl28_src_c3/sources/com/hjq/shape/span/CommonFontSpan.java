package com.hjq.shape.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.hjq.shape.config.ITextViewAttribute;
import kotlin.KotlinVersion;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class CommonFontSpan extends AlignmentReplacementSpan {
    private float mMeasureTextWidth;

    public abstract void onDraw(Canvas canvas, Paint paint, CharSequence charSequence, float f, int i, int i2, float f2, int i3, int i4, int i5);

    public CommonFontSpan(ITextViewAttribute iTextViewAttribute) {
        super(iTextViewAttribute);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        this.mMeasureTextWidth = onMeasure(paint, fontMetricsInt, charSequence, i, i2);
        Paint.FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
        if (fontMetricsInt != null) {
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return (int) this.mMeasureTextWidth;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        float f2;
        float fMax;
        int alpha = paint.getAlpha();
        if (alpha != 255) {
            paint.setAlpha(KotlinVersion.MAX_COMPONENT_VALUE);
        }
        float fMeasureText = paint.measureText(charSequence, i, i2);
        ITextViewAttribute textAttribute = getTextAttribute();
        float width = (canvas.getWidth() - textAttribute.getPaddingLeft()) - textAttribute.getPaddingRight();
        int textGravity = textAttribute.getTextGravity();
        boolean z = textAttribute.getLayoutDirection() == 1;
        if (hasFlag(textGravity, 3)) {
            f2 = f;
        } else {
            if (hasFlag(textGravity, 5)) {
                fMax = Math.max(width - fMeasureText, 0.0f);
            } else {
                if ((!z || !hasFlag(textGravity, 8388613)) && (z || !hasFlag(textGravity, 8388611))) {
                    if ((z && hasFlag(textGravity, 8388611)) || (!z && hasFlag(textGravity, 8388613))) {
                        fMax = Math.max(width - fMeasureText, 0.0f);
                    } else if (hasFlag(textGravity, 17) || hasFlag(textGravity, 1)) {
                        fMax = Math.max((width - fMeasureText) / 2.0f, 0.0f);
                    }
                }
                f2 = f;
            }
            f2 = fMax;
        }
        onDraw(canvas, paint, charSequence, fMeasureText, i, i2, f2, i3, i4, i5);
        paint.setAlpha(alpha);
    }

    public float onMeasure(Paint paint, Paint.FontMetricsInt fontMetricsInt, CharSequence charSequence, int i, int i2) {
        return paint.measureText(charSequence, i, i2);
    }
}
