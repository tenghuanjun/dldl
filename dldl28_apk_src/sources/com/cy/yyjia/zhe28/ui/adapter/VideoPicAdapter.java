package com.cy.yyjia.zhe28.ui.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseProvider;
import com.cy.yyjia.zhe28.domain.GameBannerBean;
import com.cy.yyjia.zhe28.ui.activity.ImageActivity;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPicAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/adapter/VideoPicAdapter;", "Lcom/chad/library/adapter/base/BaseProviderMultiAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBannerBean;", "()V", "getItemType", "", "data", "", ImageSelector.POSITION, "onAttachedToRecyclerView", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VideoPicAdapter extends BaseProviderMultiAdapter<GameBannerBean> {
    public static final int $stable = 0;

    public VideoPicAdapter() {
        super(null, 1, null);
        addItemProvider(new BaseProvider(1, R.layout.item_game_banner_video));
        addItemProvider(new BaseProvider(0, R.layout.item_game_detail_banner));
        setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.VideoPicAdapter$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VideoPicAdapter._init_$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(VideoPicAdapter this$0, BaseQuickAdapter a2, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(v, "v");
        ArrayList<String> arrayList = new ArrayList<>();
        boolean z = false;
        for (GameBannerBean gameBannerBean : this$0.getData()) {
            if (TextUtils.isEmpty(gameBannerBean.getVideo())) {
                arrayList.add(gameBannerBean.getPic());
            } else {
                z = true;
            }
        }
        Intent intent = new Intent(this$0.getContext(), (Class<?>) ImageActivity.class);
        intent.putStringArrayListExtra("images", arrayList);
        if (z) {
            i--;
        }
        intent.putExtra(ImageSelector.POSITION, i);
        this$0.getContext().startActivity(intent);
    }

    @Override // com.chad.library.adapter.base.BaseProviderMultiAdapter
    protected int getItemType(List<? extends GameBannerBean> data, int position) {
        Intrinsics.checkNotNullParameter(data, "data");
        return !TextUtils.isEmpty(data.get(position).getVideo()) ? 1 : 0;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.cy.yyjia.zhe28.ui.adapter.VideoPicAdapter.onAttachedToRecyclerView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrollStateChanged(recyclerView2, newState);
                GSYVideoManager.releaseAllVideos();
            }
        });
    }
}
