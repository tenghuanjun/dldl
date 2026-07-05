package com.huya.berry.sdklivelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.duowan.HUYA.CornerMark;
import com.duowan.HUYA.UserRecItem;
import com.huya.android.support.v7.widget.GridLayoutManager;
import com.huya.android.support.v7.widget.RecyclerView;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.widgets.ComnTextView;
import com.huya.live.utils.image.ImageBind;
import com.sqwan.liveshow.huya.SqR;
import java.util.ArrayList;
import java.util.List;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_HEADER = 1;
    private static final int ITEM_TYPE_LOAD = 2;
    private static final String TAG = "LiveListAdapter";
    private Context mContext;
    private HeaderHolder mHeaderHolder;
    private LiveListClickListener mLiveListClickListener;
    private LoadMoreHolder mLoadMoreHolder;
    private List<UserRecItem> mUserRecItemList = new ArrayList();
    private boolean mIsHasHeader = true;
    private boolean mIsHasLoadMore = true;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.huya.berry.sdklivelist.LiveListAdapter.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (LiveListAdapter.this.mLiveListClickListener == null) {
                return;
            }
            if (view.getId() == ResourceUtil.getIdResIDByName(SqR.id.ll_start_live)) {
                LiveListAdapter.this.mLiveListClickListener.onHeaderViewClick();
            } else if (view.getId() == ResourceUtil.getIdResIDByName(SqR.id.fl_anthor_recruit)) {
                LiveListAdapter.this.mLiveListClickListener.onAnthorRecruitClick((String) view.getTag());
            } else if (view.getId() == ResourceUtil.getIdResIDByName(SqR.id.fl_anthor_component)) {
                LiveListAdapter.this.mLiveListClickListener.onComponentClick((String) view.getTag());
            }
        }
    };

    public interface LiveListClickListener {
        void onAnthorRecruitClick(String str);

        void onComponentClick(String str);

        void onHeaderViewClick();

        void onItemClick(UserRecItem userRecItem);
    }

    public LiveListAdapter(Context context) {
        this.mContext = context;
    }

    public void setIsHasHeader(boolean z) {
        this.mIsHasHeader = z;
    }

    public void setIsHasLoadMore(boolean z) {
        this.mIsHasLoadMore = z;
    }

    public void setAnthorRecruitInfo(String str, String str2) {
        HeaderHolder headerHolder;
        if (!this.mIsHasHeader || (headerHolder = this.mHeaderHolder) == null) {
            return;
        }
        headerHolder.setAnthorRecruitInfo(str, str2);
    }

    public void setComponetnInfo(String str, String str2, String str3) {
        HeaderHolder headerHolder;
        if (!this.mIsHasHeader || (headerHolder = this.mHeaderHolder) == null) {
            return;
        }
        headerHolder.setComponentInfo(this.mContext, str, str2, str3);
    }

    public void setOnLiveListClickListener(LiveListClickListener liveListClickListener) {
        this.mLiveListClickListener = liveListClickListener;
    }

    public void setLoadMoreFinish(boolean z) {
        LoadMoreHolder loadMoreHolder = this.mLoadMoreHolder;
        if (loadMoreHolder == null) {
            return;
        }
        if (z) {
            loadMoreHolder.itemView.setVisibility(8);
        } else {
            loadMoreHolder.itemView.setVisibility(0);
        }
    }

    public void setItems(List<UserRecItem> list) {
        setLoadMoreFinish(false);
        int itemCount = getItemCount();
        this.mUserRecItemList.clear();
        if (list == null) {
            return;
        }
        this.mUserRecItemList.addAll(list);
        int size = this.mUserRecItemList.size();
        if (size >= itemCount) {
            notifyItemRangeChanged(0, size);
        } else {
            notifyItemRangeRemoved(size, itemCount - size);
            notifyItemRangeChanged(0, size);
        }
    }

    public void addItems(List<UserRecItem> list) {
        setLoadMoreFinish(false);
        if (list == null || list.size() == 0) {
            return;
        }
        int size = this.mUserRecItemList.size();
        if (this.mIsHasLoadMore) {
            size++;
        }
        this.mUserRecItemList.addAll(list);
        notifyItemRangeInserted(size, list.size());
    }

    @Override // com.huya.android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            View viewInflate = LayoutInflater.from(this.mContext).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_item_header_view), viewGroup, false);
            if (this.mHeaderHolder == null) {
                this.mHeaderHolder = new HeaderHolder(viewInflate);
            }
            return this.mHeaderHolder;
        }
        if (i == 2) {
            View viewInflate2 = LayoutInflater.from(this.mContext).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_item_load_more), viewGroup, false);
            if (this.mLoadMoreHolder == null) {
                this.mLoadMoreHolder = new LoadMoreHolder(viewInflate2);
            }
            return this.mLoadMoreHolder;
        }
        return new ListItemViewHolder(LayoutInflater.from(this.mContext).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_item_live_list), viewGroup, false));
    }

    @Override // com.huya.android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i >= getItemCount()) {
            return;
        }
        if (this.mIsHasHeader && i == 0 && (viewHolder instanceof HeaderHolder)) {
            ((HeaderHolder) viewHolder).setOnClickListener(this.mOnClickListener);
            return;
        }
        if (this.mIsHasHeader) {
            i--;
        }
        if (i >= this.mUserRecItemList.size()) {
            return;
        }
        UserRecItem userRecItem = this.mUserRecItemList.get(i);
        if (viewHolder instanceof ListItemViewHolder) {
            bindData((ListItemViewHolder) viewHolder, userRecItem);
        }
    }

    private void bindData(ListItemViewHolder listItemViewHolder, final UserRecItem userRecItem) {
        listItemViewHolder.tvAnchorName.setVisibility(8);
        listItemViewHolder.ivAudience.setVisibility(8);
        listItemViewHolder.tvAudienceCount.setVisibility(8);
        String strReplace = userRecItem.sCoverUrl;
        if (strReplace.indexOf(IDataSource.SCHEME_HTTPS_TAG) == -1) {
            strReplace = strReplace.replace(IDataSource.SCHEME_HTTP_TAG, IDataSource.SCHEME_HTTPS_TAG);
        }
        ImageBind.displayRound(listItemViewHolder.ivCover, strReplace, ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_default_cover), 4);
        listItemViewHolder.tvLiveDesc.setText(userRecItem.sTitle);
        ArrayList<CornerMark> arrayList = userRecItem.vCornerMarks;
        if (arrayList == null) {
            return;
        }
        for (CornerMark cornerMark : arrayList) {
            int i = cornerMark.iPos;
            if (i == 3) {
                listItemViewHolder.tvAnchorName.setVisibility(0);
                listItemViewHolder.tvAnchorName.setText(cornerMark.sText);
            } else if (i == 4) {
                listItemViewHolder.ivAudience.setVisibility(0);
                listItemViewHolder.tvAudienceCount.setVisibility(0);
                listItemViewHolder.tvAudienceCount.setText(cornerMark.sText);
            }
        }
        listItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdklivelist.LiveListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LiveListAdapter.this.mLiveListClickListener == null) {
                    return;
                }
                LiveListAdapter.this.mLiveListClickListener.onItemClick(userRecItem);
            }
        });
    }

    @Override // com.huya.android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        int size = this.mUserRecItemList.size();
        if (this.mIsHasHeader) {
            size++;
        }
        return this.mIsHasLoadMore ? size + 1 : size;
    }

    @Override // com.huya.android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.mIsHasHeader && i == 0) {
            return 1;
        }
        if (this.mIsHasLoadMore && i == getItemCount() - 1) {
            return 2;
        }
        return super.getItemViewType(i);
    }

    @Override // com.huya.android.support.v7.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.huya.berry.sdklivelist.LiveListAdapter.3
                @Override // com.huya.android.support.v7.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i) {
                    if (LiveListAdapter.this.getItemViewType(i) == 2 || LiveListAdapter.this.getItemViewType(i) == 1) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    public boolean isEmpty() {
        return this.mUserRecItemList.size() == 0;
    }

    private static class ListItemViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAudience;
        ImageView ivCover;
        TextView tvAnchorName;
        TextView tvAudienceCount;
        TextView tvLiveDesc;

        public ListItemViewHolder(View view) {
            super(view);
            this.ivCover = (ImageView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.iv_cover));
            this.tvAnchorName = (TextView) view.findViewById(ResourceUtil.getIdResIDByName("tv_anchor_name"));
            this.ivAudience = (ImageView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.iv_audience));
            this.tvAudienceCount = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_audience_count));
            this.tvLiveDesc = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_live_desc));
        }
    }

    private static class LoadMoreHolder extends RecyclerView.ViewHolder {
        ProgressBar mPbLoadMore;
        TextView mTvLoadMore;

        public LoadMoreHolder(View view) {
            super(view);
            this.mPbLoadMore = (ProgressBar) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.pb_load_more));
            this.mTvLoadMore = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_load_more));
        }

        void setLoadText(CharSequence charSequence) {
            this.mTvLoadMore.setText(charSequence);
        }

        void setLoadPbVisibility(boolean z) {
            this.mPbLoadMore.setVisibility(z ? 0 : 8);
        }
    }

    private static class HeaderHolder extends RecyclerView.ViewHolder {
        FrameLayout mFlAnthorComponent;
        FrameLayout mFlAnthorRecruit;
        LinearLayout mLlStartLive;

        public HeaderHolder(View view) {
            super(view);
            this.mLlStartLive = (LinearLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_start_live));
            this.mFlAnthorRecruit = (FrameLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.fl_anthor_recruit));
            this.mFlAnthorComponent = (FrameLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.fl_anthor_component));
        }

        void setOnClickListener(View.OnClickListener onClickListener) {
            LinearLayout linearLayout = this.mLlStartLive;
            if (linearLayout == null || this.mFlAnthorRecruit == null) {
                return;
            }
            linearLayout.setOnClickListener(onClickListener);
            this.mFlAnthorRecruit.setOnClickListener(onClickListener);
            this.mFlAnthorComponent.setOnClickListener(onClickListener);
        }

        void setAnthorRecruitInfo(String str, String str2) {
            FrameLayout frameLayout = this.mFlAnthorRecruit;
            if (frameLayout == null) {
                return;
            }
            frameLayout.setVisibility(0);
            if (this.mFlAnthorRecruit.getChildAt(2) != null && (this.mFlAnthorRecruit.getChildAt(2) instanceof ComnTextView)) {
                ((ComnTextView) this.mFlAnthorRecruit.getChildAt(2)).setComnText(str);
            }
            this.mFlAnthorRecruit.setTag(str2);
        }

        void setComponentInfo(Context context, String str, String str2, String str3) {
            FrameLayout frameLayout = this.mFlAnthorComponent;
            if (frameLayout == null) {
                return;
            }
            frameLayout.setVisibility(0);
            if (this.mFlAnthorComponent.getChildAt(1) != null && (this.mFlAnthorComponent.getChildAt(1) instanceof ImageView)) {
                ImageBind.displayRound((ImageView) this.mFlAnthorComponent.getChildAt(1), str3, ResourceUtil.getDrawableResIDByName("hyberry_component_horn"), 4);
            }
            if (this.mFlAnthorComponent.getChildAt(2) != null && (this.mFlAnthorComponent.getChildAt(2) instanceof ComnTextView)) {
                ((ComnTextView) this.mFlAnthorComponent.getChildAt(2)).setComnText(str);
            }
            this.mFlAnthorComponent.setTag(str2);
        }
    }
}
