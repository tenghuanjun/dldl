package com.sqwan.liveshow.huya.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RoomViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> viewCache;

    public RoomViewHolder(View view) {
        super(view);
        this.viewCache = new SparseArray<>();
    }

    public <T extends View> T findViewById(int i) {
        T t = (T) this.viewCache.get(i);
        if (t != null) {
            return t;
        }
        T t2 = (T) this.itemView.findViewById(i);
        this.viewCache.put(i, t2);
        return t2;
    }
}
