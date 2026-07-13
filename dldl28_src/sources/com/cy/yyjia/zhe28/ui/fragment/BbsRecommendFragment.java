package com.cy.yyjia.zhe28.ui.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemBbsBinding;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BbsRecommendFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0013H\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/BbsRecommendFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/BbsBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemBbsBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BbsRecommendFragment extends BaseFragment<FragmentRvBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;

    public BbsRecommendFragment() {
        super(R.layout.fragment_rv);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<BbsBean, ItemBbsBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsRecommendFragment$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<BbsBean, ItemBbsBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_bbs, null, 2, null);
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ FragmentRvBinding access$getMBinding(BbsRecommendFragment bbsRecommendFragment) {
        return bbsRecommendFragment.getMBinding();
    }

    public final BaseAdapter<BbsBean, ItemBbsBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        ViewGroup.LayoutParams layoutParams = getMBinding().srl.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        layoutParams2.topMargin = Util.dpToPx(getMContext(), 50.0f);
        getMBinding().srl.setLayoutParams(layoutParams2);
        SmartRefreshLayout srl = getMBinding().srl;
        Intrinsics.checkNotNullExpressionValue(srl, "srl");
        setViewFitsSystemWindows(srl);
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsRecommendFragment$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                BbsRecommendFragment.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().setMyEmptyView("");
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsRecommendFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BbsRecommendFragment.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsRecommendFragment$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                BbsRecommendFragment.init$lambda$2(this.f$0);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(BbsRecommendFragment this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(BbsRecommendFragment this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        this$0.getAdapter().getItem(i).go(v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(BbsRecommendFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    public final void getData() {
        Repository.INSTANCE.getBbsRecommend(this.page, new Function1<PageBean<BbsBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsRecommendFragment.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<BbsBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<BbsBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                BbsRecommendFragment.access$getMBinding(BbsRecommendFragment.this).srl.finishRefresh();
                if (BbsRecommendFragment.this.getPage() == 1) {
                    BbsRecommendFragment.this.getAdapter().setNewInstance(it.getList());
                } else {
                    BbsRecommendFragment.this.getAdapter().addData(it.getList());
                }
                BbsRecommendFragment bbsRecommendFragment = BbsRecommendFragment.this;
                bbsRecommendFragment.setPage(bbsRecommendFragment.getPage() + 1);
                bbsRecommendFragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(BbsRecommendFragment.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    BbsRecommendFragment.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.BbsRecommendFragment.getData.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                BbsRecommendFragment.access$getMBinding(BbsRecommendFragment.this).srl.finishRefresh(false);
                BbsRecommendFragment.this.getAdapter().getLoadMoreModule().loadMoreFail();
                BbsRecommendFragment bbsRecommendFragment = BbsRecommendFragment.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                bbsRecommendFragment.log(localizedMessage);
            }
        });
    }
}
