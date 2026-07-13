package com.cy.yyjia.zhe28.view;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public class SideAlignSnapHelper extends LinearSnapHelper {
    private OrientationHelper helper;
    private RecyclerView mRecyclerView;
    private boolean mReverse = false;

    @Override // androidx.recyclerview.widget.SnapHelper
    public void attachToRecyclerView(RecyclerView recyclerView) throws IllegalStateException {
        super.attachToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
        RecyclerView.LayoutManager layoutManager = recyclerView != null ? recyclerView.getLayoutManager() : null;
        if (layoutManager instanceof LinearLayoutManager) {
            this.mReverse = ((LinearLayoutManager) layoutManager).getReverseLayout();
        }
    }

    private OrientationHelper getHelper(RecyclerView.LayoutManager layoutManager) {
        if (this.helper == null) {
            this.helper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.helper;
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView) {
        int[] iArr = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = distanceToStart(targetView, getHelper(layoutManager));
        } else {
            iArr[0] = 0;
        }
        return iArr;
    }

    private int distanceToStart(View targetView, OrientationHelper helper) {
        int decoratedStart;
        int startAfterPadding;
        if (this.mReverse) {
            decoratedStart = helper.getDecoratedEnd(targetView);
            startAfterPadding = helper.getEndAfterPadding();
        } else {
            decoratedStart = helper.getDecoratedStart(targetView);
            startAfterPadding = helper.getStartAfterPadding();
        }
        return decoratedStart - startAfterPadding;
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return findStartView(layoutManager, getHelper(layoutManager));
    }

    private View findStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        int decoratedEnd;
        int iAbs;
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        int iFindFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int iFindLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (iFindFirstVisibleItemPosition == -1) {
            return null;
        }
        View viewFindViewByPosition = layoutManager.findViewByPosition(iFindFirstVisibleItemPosition);
        View viewFindViewByPosition2 = layoutManager.findViewByPosition(iFindLastVisibleItemPosition);
        if (this.mReverse) {
            decoratedEnd = Math.abs(helper.getTotalSpace() - helper.getDecoratedStart(viewFindViewByPosition));
            iAbs = helper.getDecoratedEnd(viewFindViewByPosition2);
        } else {
            decoratedEnd = helper.getDecoratedEnd(viewFindViewByPosition);
            iAbs = Math.abs(helper.getDecoratedStart(viewFindViewByPosition2) - helper.getTotalSpace());
        }
        return decoratedEnd >= iAbs ? viewFindViewByPosition : layoutManager.findViewByPosition(iFindFirstVisibleItemPosition + 1);
    }
}
