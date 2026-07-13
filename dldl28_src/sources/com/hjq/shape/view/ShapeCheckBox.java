package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.hjq.shape.R;
import com.hjq.shape.builder.ButtonDrawableBuilder;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.builder.TextColorBuilder;
import com.hjq.shape.config.IGetButtonDrawableBuilder;
import com.hjq.shape.config.IGetShapeDrawableBuilder;
import com.hjq.shape.config.IGetTextColorBuilder;
import com.hjq.shape.styleable.ShapeCheckBoxStyleable;

/* JADX INFO: loaded from: classes3.dex */
public class ShapeCheckBox extends AppCompatCheckBox implements IGetShapeDrawableBuilder, IGetTextColorBuilder, IGetButtonDrawableBuilder {
    private static final ShapeCheckBoxStyleable STYLEABLE = new ShapeCheckBoxStyleable();
    private final ButtonDrawableBuilder mButtonDrawableBuilder;
    private final ShapeDrawableBuilder mShapeDrawableBuilder;
    private final TextColorBuilder mTextColorBuilder;

    public ShapeCheckBox(Context context) {
        this(context, null);
    }

    public ShapeCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkboxStyle);
    }

    public ShapeCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeCheckBox, 0, R.style.ShapeCheckBoxStyle);
        ShapeCheckBoxStyleable shapeCheckBoxStyleable = STYLEABLE;
        ShapeDrawableBuilder shapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArrayObtainStyledAttributes, shapeCheckBoxStyleable);
        this.mShapeDrawableBuilder = shapeDrawableBuilder;
        TextColorBuilder textColorBuilder = new TextColorBuilder(this, typedArrayObtainStyledAttributes, shapeCheckBoxStyleable);
        this.mTextColorBuilder = textColorBuilder;
        ButtonDrawableBuilder buttonDrawableBuilder = new ButtonDrawableBuilder(this, typedArrayObtainStyledAttributes, shapeCheckBoxStyleable);
        this.mButtonDrawableBuilder = buttonDrawableBuilder;
        typedArrayObtainStyledAttributes.recycle();
        shapeDrawableBuilder.intoBackground();
        textColorBuilder.intoTextColor();
        buttonDrawableBuilder.intoButtonDrawable();
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

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        ButtonDrawableBuilder buttonDrawableBuilder = this.mButtonDrawableBuilder;
        if (buttonDrawableBuilder == null) {
            return;
        }
        buttonDrawableBuilder.setButtonDrawable(drawable);
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

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
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

    @Override // com.hjq.shape.config.IGetButtonDrawableBuilder
    public ButtonDrawableBuilder getButtonDrawableBuilder() {
        return this.mButtonDrawableBuilder;
    }
}
