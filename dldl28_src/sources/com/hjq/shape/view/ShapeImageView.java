package com.hjq.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.hjq.shape.R;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.config.IGetShapeDrawableBuilder;
import com.hjq.shape.styleable.ShapeImageViewStyleable;

/* JADX INFO: loaded from: classes3.dex */
public class ShapeImageView extends AppCompatImageView implements IGetShapeDrawableBuilder {
    private static final ShapeImageViewStyleable STYLEABLE = new ShapeImageViewStyleable();
    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeImageView(Context context) {
        this(context, null);
    }

    public ShapeImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShapeImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeImageView);
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
