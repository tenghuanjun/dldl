package com.huya.berry.sdkplayer.line;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.huya.android.support.v7.widget.RecyclerView;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.sqwan.liveshow.huya.SqR;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VideoLineAdapter extends RecyclerView.Adapter<ViewHolder> {
    private WeakReference<Context> mContext;
    private LayoutInflater mInflater;
    private List<VideoInfo> mItemList = new ArrayList();
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(VideoInfo videoInfo);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public VideoLineAdapter(Context context) {
        this.mContext = null;
        WeakReference<Context> weakReference = new WeakReference<>(context);
        this.mContext = weakReference;
        this.mInflater = LayoutInflater.from(weakReference.get());
    }

    @Override // com.huya.android.support.v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_video_line_item), viewGroup, false));
    }

    @Override // com.huya.android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final VideoInfo videoInfo = this.mItemList.get(i);
        viewHolder.mTvTag.setSelected(videoInfo.selected);
        if (videoInfo.selected) {
            viewHolder.mTvTag.setTextColor(ContextCompat.getColor(this.mContext.get(), ResourceUtil.getColorResIDByName(SqR.color.hyberry_color_ffa200)));
        } else {
            viewHolder.mTvTag.setTextColor(ContextCompat.getColor(this.mContext.get(), ResourceUtil.getColorResIDByName(SqR.color.hyberry_white)));
        }
        viewHolder.mTvTag.setText(videoInfo.name);
        viewHolder.mTvTag.setSingleLine(true);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.line.VideoLineAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (VideoLineAdapter.this.mOnItemClickListener == null) {
                    return;
                }
                VideoLineAdapter.this.mOnItemClickListener.onItemClick(videoInfo);
            }
        });
    }

    public String getItemViewTag(int i) {
        VideoInfo videoInfo = this.mItemList.get(i);
        return videoInfo != null ? videoInfo.name : "";
    }

    @Override // com.huya.android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItemList.size();
    }

    public void setData(List<VideoInfo> list) {
        this.mItemList = list;
        notifyDataSetChanged();
    }

    public void addData(int i, VideoInfo videoInfo) {
        this.mItemList.add(i, videoInfo);
        notifyItemInserted(i);
        notifyItemRangeChanged(i, this.mItemList.size() - i);
    }

    public void removeData(int i) {
        this.mItemList.remove(i);
        notifyItemRemoved(i);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvTag;

        public ViewHolder(View view) {
            super(view);
            this.mTvTag = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_tag));
        }
    }

    public void onDestroy() {
        this.mInflater = null;
        this.mItemList.clear();
    }
}
