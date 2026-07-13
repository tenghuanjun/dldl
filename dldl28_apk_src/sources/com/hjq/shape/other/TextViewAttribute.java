package com.hjq.shape.other;

import android.widget.TextView;
import com.hjq.shape.config.ITextViewAttribute;

/* JADX INFO: loaded from: classes3.dex */
public class TextViewAttribute implements ITextViewAttribute {
    private final TextView mTextView;

    public TextViewAttribute(TextView textView) {
        this.mTextView = textView;
    }

    @Override // com.hjq.shape.config.ITextViewAttribute
    public int getLayoutDirection() {
        return this.mTextView.getLayoutDirection();
    }

    @Override // com.hjq.shape.config.ITextViewAttribute
    public int getTextGravity() {
        return this.mTextView.getGravity();
    }

    @Override // com.hjq.shape.config.ITextViewAttribute
    public int getPaddingLeft() {
        return this.mTextView.getPaddingLeft();
    }

    @Override // com.hjq.shape.config.ITextViewAttribute
    public int getPaddingRight() {
        return this.mTextView.getPaddingRight();
    }
}
