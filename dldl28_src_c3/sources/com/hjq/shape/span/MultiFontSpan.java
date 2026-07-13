package com.hjq.shape.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import com.hjq.shape.config.ITextViewAttribute;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class MultiFontSpan extends AlignmentReplacementSpan {
    private float mMeasureTextWidth;
    private final List<ReplacementSpan> mReplacementSpans;

    public MultiFontSpan(ITextViewAttribute iTextViewAttribute, ReplacementSpan... replacementSpanArr) {
        super(iTextViewAttribute);
        this.mReplacementSpans = Arrays.asList(replacementSpanArr);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        Iterator<ReplacementSpan> it = this.mReplacementSpans.iterator();
        while (it.hasNext()) {
            this.mMeasureTextWidth = Math.max(this.mMeasureTextWidth, it.next().getSize(paint, charSequence, i, i2, fontMetricsInt));
        }
        return (int) this.mMeasureTextWidth;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Iterator<ReplacementSpan> it = this.mReplacementSpans.iterator();
        while (it.hasNext()) {
            it.next().draw(canvas, charSequence, i, i2, f, i3, i4, i5, paint);
        }
    }

    @Override // android.text.style.ReplacementSpan, android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        super.updateMeasureState(textPaint);
        Iterator<ReplacementSpan> it = this.mReplacementSpans.iterator();
        while (it.hasNext()) {
            it.next().updateMeasureState(textPaint);
        }
    }

    @Override // android.text.style.ReplacementSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        Iterator<ReplacementSpan> it = this.mReplacementSpans.iterator();
        while (it.hasNext()) {
            it.next().updateDrawState(textPaint);
        }
    }
}
