package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import com.hjq.shape.R;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.builder.TextColorBuilder;
import com.hjq.shape.config.IGetShapeDrawableBuilder;
import com.hjq.shape.config.IGetTextColorBuilder;
import com.hjq.shape.styleable.ShapeButtonStyleable;

/* JADX INFO: loaded from: classes3.dex */
public class ShapeButton extends AppCompatButton implements IGetShapeDrawableBuilder, IGetTextColorBuilder {
    private static final ShapeButtonStyleable STYLEABLE = new ShapeButtonStyleable();
    private final ShapeDrawableBuilder mShapeDrawableBuilder;
    private final TextColorBuilder mTextColorBuilder;

    public ShapeButton(Context context) {
        this(context, null);
    }

    public ShapeButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShapeButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeButton);
        ShapeButtonStyleable shapeButtonStyleable = STYLEABLE;
        ShapeDrawableBuilder shapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArrayObtainStyledAttributes, shapeButtonStyleable);
        this.mShapeDrawableBuilder = shapeDrawableBuilder;
        TextColorBuilder textColorBuilder = new TextColorBuilder(this, typedArrayObtainStyledAttributes, shapeButtonStyleable);
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
