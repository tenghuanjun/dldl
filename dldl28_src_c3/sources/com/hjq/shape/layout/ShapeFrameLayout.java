package com.hjq.shape.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.hjq.shape.R;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.config.IGetShapeDrawableBuilder;
import com.hjq.shape.styleable.ShapeFrameLayoutStyleable;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ShapeFrameLayout extends FrameLayout implements IGetShapeDrawableBuilder {
    private static final ShapeFrameLayoutStyleable STYLEABLE = new ShapeFrameLayoutStyleable();
    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeFrameLayout(Context context) {
        this(context, null);
    }

    public ShapeFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShapeFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeFrameLayout);
        ShapeDrawableBuilder shapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArrayObtainStyledAttributes, STYLEABLE);
        this.mShapeDrawableBuilder = shapeDrawableBuilder;
        typedArrayObtainStyledAttributes.recycle();
        shapeDrawableBuilder.intoBackground();
    }

    @Override // com.hjq.shape.config.IGetShapeDrawableBuilder
    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return this.mShapeDrawableBuilder;
    }
}
