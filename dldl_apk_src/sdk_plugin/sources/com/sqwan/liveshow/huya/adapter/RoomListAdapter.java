package com.sqwan.liveshow.huya.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.huya.berry.GranularRoundedCorners;
import com.huya.berry.client.customui.model.LiveListInfo;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.skin.SkinHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RoomListAdapter extends RecyclerView.Adapter<RoomViewHolder> {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private final Context context;
    private List<LiveListInfo> data;
    private final OnRoomClickListener mRoomClickListener;
    private final Runnable mRunnable = new Runnable() { // from class: com.sqwan.liveshow.huya.adapter.-$$Lambda$RoomListAdapter$CalRylN4XDp4BcmHDezjoHQYQ54
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$1$RoomListAdapter();
        }
    };
    private RequestOptions requestOptions;

    public interface OnRoomClickListener {
        void onRoomClick(LiveListInfo liveListInfo);
    }

    public RoomListAdapter(Context context, OnRoomClickListener onRoomClickListener) {
        this.context = context;
        this.mRoomClickListener = onRoomClickListener;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RoomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RoomViewHolder(LayoutInflater.from(this.context).inflate(SqResUtils.getLayoutId(this.context, SqR.layout.sy37_item_liveshow_room), viewGroup, false));
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$RoomListAdapter(RoomViewHolder roomViewHolder, View view) {
        this.mRoomClickListener.onRoomClick(this.data.get(roomViewHolder.getAdapterPosition()));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RoomViewHolder roomViewHolder, int i) {
        roomViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.adapter.-$$Lambda$RoomListAdapter$V0LU473c7y1wdVKhzmRWAQ3ZaLM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$0$RoomListAdapter(roomViewHolder, view);
            }
        });
        ImageView imageView = (ImageView) roomViewHolder.findViewById(SqResUtils.getId(this.context, SqR.id.iv_sy37_liveshow_icon_default_cover));
        TextView textView = (TextView) roomViewHolder.findViewById(SqResUtils.getId(this.context, "tv_title"));
        TextView textView2 = (TextView) roomViewHolder.findViewById(SqResUtils.getId(this.context, SqR.id.tv_nickname));
        TextView textView3 = (TextView) roomViewHolder.findViewById(SqResUtils.getId(this.context, SqR.id.tv_audience_count));
        if (this.requestOptions == null) {
            this.requestOptions = RequestOptions.placeholderOf(SkinHelper.getDrawable(this.context, SqR.drawable.sy37_liveshow_icon_default_cover)).error(SkinHelper.getDrawable(this.context, SqR.drawable.sy37_liveshow_icon_default_cover));
            if (this.context.getResources().getConfiguration().orientation == 1) {
                this.requestOptions.transform(new GranularRoundedCorners(TypedValue.applyDimension(1, 4.0f, this.context.getResources().getDisplayMetrics()), TypedValue.applyDimension(1, 4.0f, this.context.getResources().getDisplayMetrics()), 0.0f, 0.0f));
            }
        }
        Glide.with(this.context).load(this.data.get(i).coverUrl).apply(this.requestOptions).into(imageView);
        textView.setText(this.data.get(i).title);
        textView2.setText(this.data.get(i).nickName);
        textView3.setText(this.data.get(i).audienceCount);
        ViewGroup.LayoutParams layoutParams = roomViewHolder.itemView.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            if (this.context.getResources().getConfiguration().orientation == 1 && i >= 2) {
                ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = (int) TypedValue.applyDimension(1, 9.5f, this.context.getResources().getDisplayMetrics());
            } else {
                ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = 0;
            }
            roomViewHolder.itemView.setLayoutParams(layoutParams);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<LiveListInfo> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setLiveListInfoData(List<LiveListInfo> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.data = list;
        HANDLER.removeCallbacks(this.mRunnable);
        HANDLER.post(this.mRunnable);
    }

    public /* synthetic */ void lambda$new$1$RoomListAdapter() {
        notifyDataSetChanged();
    }
}
