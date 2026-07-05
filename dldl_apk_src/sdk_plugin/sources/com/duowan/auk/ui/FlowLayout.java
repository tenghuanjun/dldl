package com.duowan.auk.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FlowLayout extends ViewGroup {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int PAD_H;
    private int PAD_V;
    private FlowLayoutListener mFlowLayoutListener;
    private List<Integer> mLineColumnCountList;
    private int mSingleLineHeight;

    public interface FlowLayoutListener {
        void onMeasureFinish(int i);
    }

    public FlowLayout(Context context) {
        super(context);
        this.PAD_H = 20;
        this.PAD_V = 20;
        this.mLineColumnCountList = new ArrayList();
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.PAD_H = 20;
        this.PAD_V = 20;
        this.mLineColumnCountList = new ArrayList();
    }

    public void setPaddingVertical(int i) {
        this.PAD_V = i;
    }

    public void setPaddingHorizontal(int i) {
        this.PAD_H = i;
    }

    private static class ViewPos {
        int column;
        int line;
        View view;

        private ViewPos() {
        }
    }

    public int getLineCount() {
        return this.mLineColumnCountList.size();
    }

    public int getLineColumnCount(int i) {
        if (i >= this.mLineColumnCountList.size() || i < 0) {
            return -1;
        }
        return this.mLineColumnCountList.get(i).intValue();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int iMakeMeasureSpec;
        int i3;
        int i4;
        int size = (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int size2 = (View.MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE);
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        this.mSingleLineHeight = 0;
        this.mLineColumnCountList.clear();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), iMakeMeasureSpec);
                int measuredWidth = childAt.getMeasuredWidth();
                this.mSingleLineHeight = Math.max(this.mSingleLineHeight, childAt.getMeasuredHeight() + this.PAD_V);
                if (paddingLeft + measuredWidth > size) {
                    paddingLeft = getPaddingLeft();
                    paddingTop += this.mSingleLineHeight;
                    this.mLineColumnCountList.add(Integer.valueOf(i5));
                    i5 = 1;
                } else {
                    i5++;
                }
                if (i6 == childCount - 1) {
                    this.mLineColumnCountList.add(Integer.valueOf(i5));
                }
                paddingLeft += measuredWidth + this.PAD_H;
            }
        }
        FlowLayoutListener flowLayoutListener = this.mFlowLayoutListener;
        if (flowLayoutListener != null) {
            flowLayoutListener.onMeasureFinish(this.mLineColumnCountList.size());
        }
        if (View.MeasureSpec.getMode(i2) == 0) {
            i3 = paddingTop + this.mSingleLineHeight;
            i4 = this.PAD_V;
        } else {
            if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
                int i7 = this.mSingleLineHeight;
                if (paddingTop + i7 < size2) {
                    i3 = paddingTop + i7;
                    i4 = this.PAD_V;
                }
            }
            setMeasuredDimension(size, size2 + getPaddingBottom());
        }
        size2 = i3 - i4;
        setMeasuredDimension(size, size2 + getPaddingBottom());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i6 = 0; i6 < getChildCount(); i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (paddingLeft + measuredWidth > i5) {
                    paddingLeft = getPaddingLeft();
                    paddingTop += this.mSingleLineHeight;
                }
                childAt.layout(paddingLeft, paddingTop, paddingLeft + measuredWidth, measuredHeight + paddingTop);
                paddingLeft += measuredWidth + this.PAD_H;
            }
        }
    }

    public void setFlowLayoutListener(FlowLayoutListener flowLayoutListener) {
        this.mFlowLayoutListener = flowLayoutListener;
    }
}
