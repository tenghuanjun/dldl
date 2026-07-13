package com.hjq.shape.view;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.builder.TextColorBuilder;
import com.hjq.shape.config.IGetShapeDrawableBuilder;
import com.hjq.shape.config.IGetTextColorBuilder;
import com.hjq.shape.styleable.ShapeTextViewStyleable;

/* JADX INFO: loaded from: classes3.dex */
public class ShapeTextView extends AppCompatTextView implements IGetShapeDrawableBuilder, IGetTextColorBuilder {
    private static final ShapeTextViewStyleable STYLEABLE = new ShapeTextViewStyleable();
    private final ShapeDrawableBuilder mShapeDrawableBuilder;
    private final TextColorBuilder mTextColorBuilder;

    public ShapeTextView(Context context) {
        this(context, null);
    }

    public ShapeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public ShapeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.hjq.shape.R.styleable.ShapeTextView);
        ShapeTextViewStyleable shapeTextViewStyleable = STYLEABLE;
        ShapeDrawableBuilder shapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArrayObtainStyledAttributes, shapeTextViewStyleable);
        this.mShapeDrawableBuilder = shapeDrawableBuilder;
        TextColorBuilder textColorBuilder = new TextColorBuilder(this, typedArrayObtainStyledAttributes, shapeTextViewStyleable);
        this.mTextColorBuilder = textColorBuilder;
        typedArrayObtainStyledAttributes.recycle();
        shapeDrawableBuilder.intoBackground();
        textColorBuilder.intoTextColor();
    }

    @Override // android.widget.TextView
    public void setTextColor(int i) {
        super.setTextColor(i);
        TextColorBuilder textColorBuilder = this.mTextColorBuilder;
        if (textColorBuilder == null) {
            return;
        }
        textColorBuilder.setTextColor(i);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        TextColorBuilder textColorBuilder = this.mTextColorBuilder;
        if (textColorBuilder != null && textColorBuilder.isTextStrokeColorEnable()) {
            super.setText(this.mTextColorBuilder.buildStrokeFontSpannable(charSequence), TextView.BufferType.SPANNABLE);
        } else {
            super.setText(charSequence, bufferType);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.mTextColorBuilder.onDraw(this, canvas, getPaint());
        super.onDraw(canvas);
    }

    @Override // com.hjq.shape.config.IGetShapeDrawableBuilder
    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return this.mShapeDrawableBuilder;
    }

    @Override // com.hjq.shape.config.IGetTextColorBuilder
    public TextColorBuilder getTextColorBuilder() {
        return this.mTextColorBuilder;
    }
}
