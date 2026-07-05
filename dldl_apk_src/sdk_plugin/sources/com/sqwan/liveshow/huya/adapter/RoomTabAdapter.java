package com.sqwan.liveshow.huya.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.skin.view.SkinLinearLayout;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RoomTabAdapter extends RecyclerView.Adapter<RoomViewHolder> {
    private final Collection<?> data;
    private final int layoutRes;
    private final Context mContext;
    private OnBindViewListener onBindViewListener;
    private final OnItemClickListener onItemClickListener;
    private int currentSelectIndex = 0;
    private int lastSelectIndex = 0;

    public interface OnBindViewListener {
        void onBindView(RoomViewHolder roomViewHolder, int i, boolean z);
    }

    public interface OnItemClickListener {
        void onItemClick(RoomViewHolder roomViewHolder, int i);
    }

    public RoomTabAdapter(Context context, int i, Collection<?> collection, OnBindViewListener onBindViewListener, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.layoutRes = i;
        this.data = collection;
        this.onBindViewListener = onBindViewListener;
        this.onItemClickListener = onItemClickListener;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RoomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewGroup.LayoutParams layoutParams;
        View viewInflate = LayoutInflater.from(this.mContext).inflate(this.layoutRes, viewGroup, false);
        SkinLinearLayout skinLinearLayout = (SkinLinearLayout) viewInflate.findViewById(SqResUtils.getId(this.mContext, SqR.id.ll_sy37_liveshow_bg_left_tab));
        skinLinearLayout.setBackground(SqR.drawable.sy37_liveshow_bg_left_tab_selected, SqR.drawable.sy37_liveshow_bg_left_tab_unselected);
        if (this.mContext.getResources().getConfiguration().orientation == 1 && (layoutParams = skinLinearLayout.getLayoutParams()) != null) {
            layoutParams.width = viewGroup.getWidth() / 2;
        }
        return new RoomViewHolder(viewInflate);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RoomViewHolder roomViewHolder, final int i) {
        roomViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.adapter.RoomTabAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RoomTabAdapter.this.currentSelectIndex = i;
                RoomTabAdapter.this.notifyItemChanged(i);
                RoomTabAdapter roomTabAdapter = RoomTabAdapter.this;
                roomTabAdapter.notifyItemChanged(roomTabAdapter.lastSelectIndex);
            }
        });
        if (this.currentSelectIndex == i) {
            this.onItemClickListener.onItemClick(roomViewHolder, i);
            this.lastSelectIndex = this.currentSelectIndex;
            roomViewHolder.itemView.setSelected(true);
        } else {
            roomViewHolder.itemView.setSelected(false);
        }
        this.onBindViewListener.onBindView(roomViewHolder, i, this.currentSelectIndex == i);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }
}
