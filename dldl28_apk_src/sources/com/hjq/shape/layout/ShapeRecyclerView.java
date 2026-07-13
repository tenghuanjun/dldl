package com.hjq.shape.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import com.hjq.shape.R;
import com.hjq.shape.builder.ShapeDrawableBuilder;
import com.hjq.shape.config.IGetShapeDrawableBuilder;
import com.hjq.shape.styleable.ShapeRecyclerViewStyleable;

/* JADX INFO: loaded from: classes3.dex */
public class ShapeRecyclerView extends RecyclerView implements IGetShapeDrawableBuilder {
    private static final ShapeRecyclerViewStyleable STYLEABLE = new ShapeRecyclerViewStyleable();
    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeRecyclerView(Context context) {
        this(context, null);
    }

    public ShapeRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShapeRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShapeRecyclerView);
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
