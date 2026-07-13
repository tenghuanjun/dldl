package com.hjq.shape.span;

import android.text.Layout;
import android.text.style.AlignmentSpan;
import android.text.style.ReplacementSpan;
import androidx.core.view.GravityCompat;
import com.hjq.shape.config.ITextViewAttribute;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AlignmentReplacementSpan extends ReplacementSpan implements AlignmentSpan {
    private final ITextViewAttribute mTextAttribute;

    protected boolean hasFlag(int i, int i2) {
        return (i & i2) == i2;
    }

    public AlignmentReplacementSpan(ITextViewAttribute iTextViewAttribute) {
        this.mTextAttribute = iTextViewAttribute;
    }

    public ITextViewAttribute getTextAttribute() {
        return this.mTextAttribute;
    }

    @Override // android.text.style.AlignmentSpan
    public Layout.Alignment getAlignment() {
        int textGravity = this.mTextAttribute.getTextGravity();
        boolean z = this.mTextAttribute.getLayoutDirection() == 1;
        if (hasFlag(textGravity, 3)) {
            return z ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL;
        }
        if (hasFlag(textGravity, 5)) {
            return z ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_OPPOSITE;
        }
        if (hasFlag(textGravity, GravityCompat.START)) {
            return Layout.Alignment.ALIGN_NORMAL;
        }
        if (hasFlag(textGravity, GravityCompat.END)) {
            return Layout.Alignment.ALIGN_OPPOSITE;
        }
        if (hasFlag(textGravity, 17) || hasFlag(textGravity, 1)) {
            return Layout.Alignment.ALIGN_CENTER;
        }
        return Layout.Alignment.ALIGN_NORMAL;
    }
}
