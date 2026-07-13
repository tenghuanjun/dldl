package com.cy.yyjia.zhe28.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.donkingliang.imageselector.utils.ImageSelector;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoHeightViewPager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bH\u0014J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/view/AutoHeightViewPager;", "Landroidx/viewpager/widget/ViewPager;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "currentHeight", "", "isScrolling", "", "lastPosition", "lastWidthMeasureSpec", "getViewHeight", ImageSelector.POSITION, "onMeasure", "", "widthMeasureSpec", "heightMeasureSpec", "setAdapter", "adapter", "Landroidx/viewpager/widget/PagerAdapter;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AutoHeightViewPager extends ViewPager {
    public static final int $stable = 8;
    private int currentHeight;
    private boolean isScrolling;
    private int lastPosition;
    private int lastWidthMeasureSpec;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AutoHeightViewPager(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ AutoHeightViewPager(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoHeightViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.cy.yyjia.zhe28.view.AutoHeightViewPager.1
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0.0f) {
                    AutoHeightViewPager.this.isScrolling = false;
                    AutoHeightViewPager.this.requestLayout();
                    return;
                }
                int i = position >= AutoHeightViewPager.this.lastPosition ? position : position + 1;
                int i2 = position >= AutoHeightViewPager.this.lastPosition ? position + 1 : position;
                int viewHeight = AutoHeightViewPager.this.getViewHeight(i);
                int viewHeight2 = AutoHeightViewPager.this.getViewHeight(i2);
                AutoHeightViewPager autoHeightViewPager = AutoHeightViewPager.this;
                float f = viewHeight;
                float f2 = viewHeight2 - viewHeight;
                if (position < autoHeightViewPager.lastPosition) {
                    positionOffset = 1 - positionOffset;
                }
                autoHeightViewPager.currentHeight = (int) (f + (f2 * positionOffset));
                AutoHeightViewPager.this.isScrolling = true;
                AutoHeightViewPager.this.requestLayout();
            }

            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    AutoHeightViewPager autoHeightViewPager = AutoHeightViewPager.this;
                    autoHeightViewPager.lastPosition = autoHeightViewPager.getCurrentItem();
                }
            }
        });
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setAdapter(PagerAdapter adapter) {
        if (adapter != null && !(adapter instanceof AutoHeightPager)) {
            throw new IllegalArgumentException("PagerAdapter must implement AutoHeightPager.".toString());
        }
        super.setAdapter(adapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getViewHeight(int position) {
        View view;
        Object adapter = getAdapter();
        AutoHeightPager autoHeightPager = adapter instanceof AutoHeightPager ? (AutoHeightPager) adapter : null;
        if (autoHeightPager == null || (view = autoHeightPager.getView(position)) == null) {
            return 0;
        }
        view.measure(this.lastWidthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 0));
        return view.getMeasuredHeight();
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.lastWidthMeasureSpec = widthMeasureSpec;
        if (this.isScrolling) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.currentHeight, 1073741824);
        } else {
            Integer numValueOf = Integer.valueOf(getViewHeight(getCurrentItem()));
            if (numValueOf.intValue() <= 0) {
                numValueOf = null;
            }
            if (numValueOf != null) {
                heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(numValueOf.intValue(), 1073741824);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
