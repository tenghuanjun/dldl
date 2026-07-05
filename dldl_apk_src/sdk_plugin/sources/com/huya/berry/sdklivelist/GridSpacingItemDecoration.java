package com.huya.berry.sdklivelist;

import android.graphics.Rect;
import android.view.View;
import com.huya.android.support.v7.widget.RecyclerView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int mHorSpacing;
    private boolean mIncludeEdge;
    private int mSpanCount;
    private int mVerSpacing;

    public GridSpacingItemDecoration(int i, int i2, int i3, boolean z) {
        this.mSpanCount = i;
        this.mHorSpacing = i2;
        this.mVerSpacing = i3;
        this.mIncludeEdge = z;
    }

    @Override // com.huya.android.support.v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childPosition = recyclerView.getChildPosition(view);
        int i = this.mSpanCount;
        int i2 = childPosition % i;
        if (this.mIncludeEdge) {
            int i3 = this.mHorSpacing;
            rect.left = i3 - ((i2 * i3) / i);
            rect.right = ((i2 + 1) * this.mHorSpacing) / this.mSpanCount;
            if (childPosition < this.mSpanCount) {
                rect.top = this.mVerSpacing;
            }
            rect.bottom = this.mVerSpacing;
            return;
        }
        rect.left = (this.mHorSpacing * i2) / i;
        int i4 = this.mHorSpacing;
        rect.right = i4 - (((i2 + 1) * i4) / this.mSpanCount);
        if (childPosition >= this.mSpanCount) {
            rect.top = this.mVerSpacing;
        }
    }
}
