package com.huya.android.support.v7.widget.util;

import com.huya.android.support.v7.util.SortedList;
import com.huya.android.support.v7.widget.RecyclerView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class SortedListAdapterCallback<T2> extends SortedList.Callback<T2> {
    final RecyclerView.Adapter mAdapter;

    public SortedListAdapterCallback(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    @Override // com.huya.android.support.v7.util.SortedList.Callback
    public void onInserted(int i, int i2) {
        this.mAdapter.notifyItemRangeInserted(i, i2);
    }

    @Override // com.huya.android.support.v7.util.SortedList.Callback
    public void onRemoved(int i, int i2) {
        this.mAdapter.notifyItemRangeRemoved(i, i2);
    }

    @Override // com.huya.android.support.v7.util.SortedList.Callback
    public void onMoved(int i, int i2) {
        this.mAdapter.notifyItemMoved(i, i2);
    }

    @Override // com.huya.android.support.v7.util.SortedList.Callback
    public void onChanged(int i, int i2) {
        this.mAdapter.notifyItemRangeChanged(i, i2);
    }
}
