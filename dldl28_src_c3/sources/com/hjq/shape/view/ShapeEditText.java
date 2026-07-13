package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import com.hjq.shape.R;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.builder.TextColorBuilder;
import com.hjq.shape.config.IGetShapeDrawableBuilder;
import com.hjq.shape.config.IGetTextColorBuilder;
import com.hjq.shape.styleable.ShapeEditTextStyleable;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ShapeEditText extends AppCompatEditText implements IGetShapeDrawableBuilder, IGetTextColorBuilder {
    private static final ShapeEditTextStyleable STYLEABLE = new ShapeEditTextStyleable();
    private final ShapeDrawableBuilder mShapeDrawableBuilder;
    private final TextColorBuilder mTextColorBuilder;

    public ShapeEditText(Context context) {
        this(context, null);
    }

    public ShapeEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.editTextStyle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ShapeEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeEditText);
        ShapeEditTextStyleable shapeEditTextStyleable = STYLEABLE;
        ShapeDrawableBuilder shapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArrayObtainStyledAttributes, shapeEditTextStyleable);
        this.mShapeDrawableBuilder = shapeDrawableBuilder;
        TextColorBuilder textColorBuilder = new TextColorBuilder(this, typedArrayObtainStyledAttributes, shapeEditTextStyleable);
        this.mTextColorBuilder = textColorBuilder;
        typedArrayObtainStyledAttributes.recycle();
        shapeDrawableBuilder.intoBackground();
        textColorBuilder.intoTextColor();
    }

    public void setTextColor(int i) {
        super.setTextColor(i);
        TextColorBuilder textColorBuilder = this.mTextColorBuilder;
        if (textColorBuilder == null) {
            return;
        }
        textColorBuilder.setTextColor(i);
    }

    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        TextColorBuilder textColorBuilder = this.mTextColorBuilder;
        if (textColorBuilder != null && textColorBuilder.isTextStrokeColorEnable()) {
            super.setText(this.mTextColorBuilder.buildStrokeFontSpannable(charSequence), TextView.BufferType.SPANNABLE);
        } else {
            super.setText(charSequence, bufferType);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
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
