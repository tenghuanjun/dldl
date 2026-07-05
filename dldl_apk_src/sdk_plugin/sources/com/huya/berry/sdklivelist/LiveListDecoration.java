package com.huya.berry.sdklivelist;

import android.graphics.Rect;
import android.view.View;
import com.huya.android.support.v7.widget.RecyclerView;
import com.huya.berry.gamesdk.utils.UIUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveListDecoration extends RecyclerView.ItemDecoration {
    private boolean mIsHasHeader;
    private int mSpaceBottom;
    private int mSpaceInside;
    private int mSpaceOutside;
    private int mSpaceTop;
    private int mSpanCount;

    public LiveListDecoration(int i, int i2, int i3, int i4, int i5, boolean z) {
        this.mSpaceInside = i;
        this.mSpaceOutside = i2;
        this.mSpaceTop = i3;
        this.mSpaceBottom = i4;
        this.mSpanCount = i5;
        this.mIsHasHeader = z;
    }

    @Override // com.huya.android.support.v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i;
        int childPosition = recyclerView.getChildPosition(view);
        if (this.mIsHasHeader) {
            if (childPosition == 0 || childPosition == recyclerView.getAdapter().getItemCount() - 1) {
                return;
            } else {
                i = (childPosition - 1) % this.mSpanCount;
            }
        } else if (childPosition == recyclerView.getAdapter().getItemCount() - 1) {
            return;
        } else {
            i = childPosition % this.mSpanCount;
        }
        if (i == 0) {
            rect.left = (int) UIUtil.getDp(this.mSpaceOutside);
            rect.right = ((int) UIUtil.getDp(this.mSpaceInside)) / 2;
        } else if (i == this.mSpanCount - 1) {
            rect.left = ((int) UIUtil.getDp(this.mSpaceInside)) / 2;
            rect.right = (int) UIUtil.getDp(this.mSpaceOutside);
        } else {
            rect.left = ((int) UIUtil.getDp(this.mSpaceInside)) / 2;
            rect.right = ((int) UIUtil.getDp(this.mSpaceInside)) / 2;
        }
        rect.top = (int) UIUtil.getDp(this.mSpaceTop);
        rect.bottom = (int) UIUtil.getDp(this.mSpaceBottom);
    }
}
