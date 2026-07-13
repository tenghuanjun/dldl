package com.donkingliang.consecutivescroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ConsecutiveViewPager extends ViewPager implements IConsecutiveScroller {
    private int mAdjustHeight;

    public ConsecutiveViewPager(Context context) {
        super(context);
    }

    public ConsecutiveViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isConsecutiveParentAndBottom() && this.mAdjustHeight > 0) {
            super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(getDefaultSize(0, heightMeasureSpec) - this.mAdjustHeight, View.MeasureSpec.getMode(heightMeasureSpec)));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (ScrollUtils.isConsecutiveScrollerChild(this)) {
            disableChildScroll(child);
            if (child instanceof ViewGroup) {
                ((ViewGroup) child).setClipToPadding(false);
            }
        }
    }

    private void disableChildScroll(View child) {
        child.setVerticalScrollBarEnabled(false);
        child.setHorizontalScrollBarEnabled(false);
        child.setOverScrollMode(2);
        ViewCompat.setNestedScrollingEnabled(child, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isConsecutiveParentAndBottom() {
        ViewParent parent = getParent();
        if (!(parent instanceof ConsecutiveScrollerLayout)) {
            return false;
        }
        ConsecutiveScrollerLayout consecutiveScrollerLayout = (ConsecutiveScrollerLayout) parent;
        return consecutiveScrollerLayout.indexOfChild(this) == consecutiveScrollerLayout.getChildCount() - 1;
    }

    public int getAdjustHeight() {
        return this.mAdjustHeight;
    }

    public void setAdjustHeight(int adjustHeight) {
        if (this.mAdjustHeight != adjustHeight) {
            this.mAdjustHeight = adjustHeight;
            requestLayout();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.donkingliang.consecutivescroller.IConsecutiveScroller
    public View getCurrentScrollerView() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getX() == getScrollX() + getPaddingLeft()) {
                return childAt;
            }
        }
        return this;
    }

    @Override // com.donkingliang.consecutivescroller.IConsecutiveScroller
    public List<View> getScrolledViews() {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                arrayList.add(getChildAt(i));
            }
        }
        return arrayList;
    }
}
