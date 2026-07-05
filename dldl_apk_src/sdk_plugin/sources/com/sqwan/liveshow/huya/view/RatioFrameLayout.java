package com.sqwan.liveshow.huya.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.sqwan.liveshow.huya.R;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public final class RatioFrameLayout extends FrameLayout {
    private float mHeightRatio;
    private float mWidthRatio;

    public RatioFrameLayout(Context context) {
        this(context, null);
    }

    public RatioFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RatioFrameLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public RatioFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatioFrameLayout);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.RatioFrameLayout_sizeRatio);
        if (!TextUtils.isEmpty(string)) {
            String[] strArrSplit = string.split(":");
            int length = strArrSplit.length;
            if (length == 1) {
                this.mWidthRatio = Float.parseFloat(strArrSplit[0]);
                this.mHeightRatio = 1.0f;
            } else if (length == 2) {
                this.mWidthRatio = Float.parseFloat(strArrSplit[0]);
                this.mHeightRatio = Float.parseFloat(strArrSplit[1]);
            } else {
                throw new IllegalArgumentException("are you ok?");
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mWidthRatio != 0.0f && this.mHeightRatio != 0.0f) {
            float sizeRatio = getSizeRatio();
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            if (layoutParams.width != -2 && layoutParams.height != -2 && mode == 1073741824 && mode2 == 1073741824) {
                float f = size;
                float f2 = f / sizeRatio;
                float f3 = size2;
                if (f2 <= f3) {
                    i2 = View.MeasureSpec.makeMeasureSpec((int) f2, 1073741824);
                } else {
                    float f4 = f3 * sizeRatio;
                    if (f4 <= f) {
                        i = View.MeasureSpec.makeMeasureSpec((int) f4, 1073741824);
                    }
                }
            } else if (layoutParams.width != -2 && mode == 1073741824 && mode2 != 1073741824) {
                i2 = View.MeasureSpec.makeMeasureSpec((int) (size / sizeRatio), 1073741824);
            } else if (layoutParams.height != -2 && mode2 == 1073741824 && mode != 1073741824) {
                i = View.MeasureSpec.makeMeasureSpec((int) (size2 * sizeRatio), 1073741824);
            }
        }
        super.onMeasure(i, i2);
    }

    public float getWidthRatio() {
        return this.mWidthRatio;
    }

    public float getHeightRatio() {
        return this.mHeightRatio;
    }

    public float getSizeRatio() {
        return this.mWidthRatio / this.mHeightRatio;
    }

    public void setSizeRatio(float f, float f2) {
        this.mWidthRatio = f;
        this.mHeightRatio = f2;
        requestLayout();
        invalidate();
    }
}
