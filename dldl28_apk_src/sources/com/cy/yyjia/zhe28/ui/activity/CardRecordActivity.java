package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvTabBinding;
import com.cy.yyjia.zhe28.databinding.ItemCardRecordBinding;
import com.cy.yyjia.zhe28.databinding.ItemCardRewardRecordBinding;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.RecordBean;
import com.cy.yyjia.zhe28.util.Repository;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CardRecordActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020!H\u0016J\u0006\u0010#\u001a\u00020!R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR'\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u000e\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0016\u001a\u00020\u00178FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006$"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/CardRecordActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvTabBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/RecordBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemCardRecordBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "adapter2", "Lcom/cy/yyjia/zhe28/databinding/ItemCardRewardRecordBinding;", "getAdapter2", "adapter2$delegate", "page", "", "getPage", "()I", "setPage", "(I)V", "saving", "", "getSaving", "()Z", "saving$delegate", "title", "", "", "getTitle", "()Ljava/util/List;", "getData", "", "init", "initTab", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CardRecordActivity extends BaseActivity<ActivityRvTabBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: adapter2$delegate, reason: from kotlin metadata */
    private final Lazy adapter2;
    private int page;

    /* JADX INFO: renamed from: saving$delegate, reason: from kotlin metadata */
    private final Lazy saving;
    private final List<String> title;

    public CardRecordActivity() {
        super(R.layout.activity_rv_tab, 0, 2, null);
        this.saving = LazyKt.lazy(new Function0<Boolean>() { // from class: com.cy.yyjia.zhe28.ui.activity.CardRecordActivity$saving$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(this.this$0.getIntent().getBooleanExtra("saving", false));
            }
        });
        this.title = CollectionsKt.listOf((Object[]) new String[]{"购买记录", "领取记录"});
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<RecordBean, ItemCardRecordBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.CardRecordActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<RecordBean, ItemCardRecordBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_card_record, null, 2, null);
            }
        });
        this.adapter2 = LazyKt.lazy(new Function0<BaseAdapter<RecordBean, ItemCardRewardRecordBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.CardRecordActivity$adapter2$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<RecordBean, ItemCardRewardRecordBinding> invoke() {
                final CardRecordActivity cardRecordActivity = this.this$0;
                return new BaseAdapter<>(R.layout.item_card_reward_record, new Function3<BaseDataBindingHolder<ItemCardRewardRecordBinding>, Integer, RecordBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CardRecordActivity$adapter2$2.1
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemCardRewardRecordBinding> baseDataBindingHolder, Integer num, RecordBean recordBean) {
                        invoke(baseDataBindingHolder, num.intValue(), recordBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemCardRewardRecordBinding> holder, int i, RecordBean recordBean) {
                        Intrinsics.checkNotNullParameter(holder, "holder");
                        ItemCardRewardRecordBinding itemCardRewardRecordBinding = (ItemCardRewardRecordBinding) holder.getDataBinding();
                        if (itemCardRewardRecordBinding == null) {
                            return;
                        }
                        itemCardRewardRecordBinding.setCard(!cardRecordActivity.getSaving());
                    }
                });
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ ActivityRvTabBinding access$getMBinding(CardRecordActivity cardRecordActivity) {
        return cardRecordActivity.getMBinding();
    }

    public final boolean getSaving() {
        return ((Boolean) this.saving.getValue()).booleanValue();
    }

    @Override // android.app.Activity
    public final List<String> getTitle() {
        return this.title;
    }

    public final BaseAdapter<RecordBean, ItemCardRecordBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final BaseAdapter<RecordBean, ItemCardRewardRecordBinding> getAdapter2() {
        return (BaseAdapter) this.adapter2.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CardRecordActivity$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                CardRecordActivity.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getMBinding().navigation.setTitle("记录");
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CardRecordActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                CardRecordActivity.init$lambda$1(this.f$0);
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CardRecordActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CardRecordActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        initTab();
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(CardRecordActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(CardRecordActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(CardRecordActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Intent intent = new Intent(this$0, (Class<?>) RecordDetailActivity.class);
        intent.putExtra("id", this$0.getAdapter().getItem(i).getId());
        intent.putExtra("type", this$0.getSaving() ? 2 : 1);
        this$0.startActivity(intent);
    }

    public final void initTab() {
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        TabLayout tab = getMBinding().tab;
        Intrinsics.checkNotNullExpressionValue(tab, "tab");
        initTab(tab, this.title, 16.0f, 16.0f, true);
        getMBinding().tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CardRecordActivity.initTab.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab2) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab2) {
                Intrinsics.checkNotNullParameter(tab2, "tab");
                CardRecordActivity.access$getMBinding(CardRecordActivity.this).srl.autoRefresh();
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab2) {
                CardRecordActivity.access$getMBinding(CardRecordActivity.this).srl.autoRefresh();
            }
        });
    }

    public final void getData() {
        if (getMBinding().tab.getSelectedTabPosition() == 0) {
            Repository.INSTANCE.getCardRecord(getSaving(), this.page, new Function1<PageBean<RecordBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CardRecordActivity.getData.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PageBean<RecordBean> pageBean) {
                    invoke2(pageBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PageBean<RecordBean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    CardRecordActivity.access$getMBinding(CardRecordActivity.this).srl.finishRefresh();
                    if (CardRecordActivity.this.getPage() == 1) {
                        CardRecordActivity.access$getMBinding(CardRecordActivity.this).rv.setAdapter(CardRecordActivity.this.getAdapter());
                        CardRecordActivity.this.getAdapter().setNewInstance(it.getList());
                    } else {
                        CardRecordActivity.this.getAdapter().addData(it.getList());
                    }
                    CardRecordActivity cardRecordActivity = CardRecordActivity.this;
                    cardRecordActivity.setPage(cardRecordActivity.getPage() + 1);
                    cardRecordActivity.getPage();
                    if (it.getCurrent_page() >= it.getLast_page()) {
                        BaseLoadMoreModule.loadMoreEnd$default(CardRecordActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                    } else {
                        CardRecordActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CardRecordActivity.getData.2
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
                    CardRecordActivity.access$getMBinding(CardRecordActivity.this).srl.finishRefresh(false);
                    CardRecordActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                    CardRecordActivity.this.netFail(it);
                }
            });
        } else {
            Repository.INSTANCE.getCardRecordReward(getSaving(), this.page, new Function1<PageBean<RecordBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CardRecordActivity.getData.3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PageBean<RecordBean> pageBean) {
                    invoke2(pageBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PageBean<RecordBean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    CardRecordActivity.access$getMBinding(CardRecordActivity.this).srl.finishRefresh();
                    if (CardRecordActivity.this.getPage() == 1) {
                        CardRecordActivity.access$getMBinding(CardRecordActivity.this).rv.setAdapter(CardRecordActivity.this.getAdapter2());
                        CardRecordActivity.this.getAdapter2().setNewInstance(it.getList());
                    } else {
                        CardRecordActivity.this.getAdapter2().addData(it.getList());
                    }
                    CardRecordActivity cardRecordActivity = CardRecordActivity.this;
                    cardRecordActivity.setPage(cardRecordActivity.getPage() + 1);
                    cardRecordActivity.getPage();
                    if (it.getCurrent_page() >= it.getLast_page()) {
                        BaseLoadMoreModule.loadMoreEnd$default(CardRecordActivity.this.getAdapter2().getLoadMoreModule(), false, 1, null);
                    } else {
                        CardRecordActivity.this.getAdapter2().getLoadMoreModule().loadMoreComplete();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CardRecordActivity.getData.4
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
                    CardRecordActivity.access$getMBinding(CardRecordActivity.this).srl.finishRefresh(false);
                    CardRecordActivity.this.getAdapter2().getLoadMoreModule().loadMoreFail();
                    CardRecordActivity.this.netFail(it);
                }
            });
        }
    }
}
