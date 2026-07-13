package com.hjq.shape.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.hjq.shape.R;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.config.IGetShapeDrawableBuilder;
import com.hjq.shape.styleable.ShapeRelativeLayoutStyleable;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ShapeRelativeLayout extends RelativeLayout implements IGetShapeDrawableBuilder {
    private static final ShapeRelativeLayoutStyleable STYLEABLE = new ShapeRelativeLayoutStyleable();
    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeRelativeLayout(Context context) {
        this(context, null);
    }

    public ShapeRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShapeRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeRelativeLayout);
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
