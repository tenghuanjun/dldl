package com.huya.berry.sdklivelist;

import android.view.View;
import com.huya.android.support.v7.widget.LinearLayoutManager;
import com.huya.android.support.v7.widget.RecyclerView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SwipeToLoadHelper extends RecyclerView.OnScrollListener {
    private RecyclerView.LayoutManager mLayoutManager;
    private LoadMoreListener mListener;
    private boolean mLoading = false;
    private boolean mIsSwipeToLoadEnabled = true;

    public interface LoadMoreListener {
        void onLoad();
    }

    public SwipeToLoadHelper(RecyclerView recyclerView) {
        this.mLayoutManager = recyclerView.getLayoutManager();
        recyclerView.setOnScrollListener(this);
    }

    @Override // com.huya.android.support.v7.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        int bottom;
        if (this.mIsSwipeToLoadEnabled && i == 0 && !this.mLoading) {
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                int iFindLastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (iFindLastCompletelyVisibleItemPosition == this.mLayoutManager.getItemCount() - 2) {
                    int iFindFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    View viewFindViewByPosition = linearLayoutManager.findViewByPosition(iFindLastCompletelyVisibleItemPosition);
                    if (viewFindViewByPosition == null || (bottom = (recyclerView.getBottom() - recyclerView.getPaddingBottom()) - viewFindViewByPosition.getBottom()) <= 0 || iFindFirstCompletelyVisibleItemPosition == 0) {
                        return;
                    }
                    recyclerView.smoothScrollBy(0, -bottom);
                    return;
                }
                if (iFindLastCompletelyVisibleItemPosition == this.mLayoutManager.getItemCount() - 1) {
                    this.mLoading = true;
                    LoadMoreListener loadMoreListener = this.mListener;
                    if (loadMoreListener != null) {
                        loadMoreListener.onLoad();
                    }
                }
            }
        }
    }

    @Override // com.huya.android.support.v7.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
    }

    public void setSwipeToLoadEnabled(boolean z) {
        if (this.mIsSwipeToLoadEnabled != z) {
            this.mIsSwipeToLoadEnabled = z;
        }
    }

    public void setLoadMoreFinish() {
        this.mLoading = false;
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.mListener = loadMoreListener;
    }
}
