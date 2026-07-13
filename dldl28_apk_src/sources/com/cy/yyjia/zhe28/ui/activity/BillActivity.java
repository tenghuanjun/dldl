package com.cy.yyjia.zhe28.ui.activity;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvTabBinding;
import com.cy.yyjia.zhe28.databinding.ItemDealMoneyRecordBinding;
import com.cy.yyjia.zhe28.databinding.ItemWithdrewRecordBinding;
import com.cy.yyjia.zhe28.domain.DealMoneyRecordBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.WithdrewBillBean;
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
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BillActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u001dH\u0016J\u0006\u0010\u001f\u001a\u00020\u001dR'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R'\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000b\u001a\u0004\b\u001a\u0010\t¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/BillActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvTabBinding;", "()V", "moneyAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/DealMoneyRecordBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemDealMoneyRecordBinding;", "getMoneyAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "moneyAdapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "title", "", "", "getTitle", "()Ljava/util/List;", "withdrewAdapter", "Lcom/cy/yyjia/zhe28/domain/WithdrewBillBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemWithdrewRecordBinding;", "getWithdrewAdapter", "withdrewAdapter$delegate", "getData", "", "init", "initTab", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BillActivity extends BaseActivity<ActivityRvTabBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: moneyAdapter$delegate, reason: from kotlin metadata */
    private final Lazy moneyAdapter;
    private int page;
    private final List<String> title;

    /* JADX INFO: renamed from: withdrewAdapter$delegate, reason: from kotlin metadata */
    private final Lazy withdrewAdapter;

    public BillActivity() {
        super(R.layout.activity_rv_tab, 0, 2, null);
        this.title = CollectionsKt.listOf((Object[]) new String[]{"提现记录", "交易记录"});
        this.page = 1;
        this.withdrewAdapter = LazyKt.lazy(new Function0<BaseAdapter<WithdrewBillBean, ItemWithdrewRecordBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.BillActivity$withdrewAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<WithdrewBillBean, ItemWithdrewRecordBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_withdrew_record, null, 2, null);
            }
        });
        this.moneyAdapter = LazyKt.lazy(new Function0<BaseAdapter<DealMoneyRecordBean, ItemDealMoneyRecordBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.BillActivity$moneyAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<DealMoneyRecordBean, ItemDealMoneyRecordBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_deal_money_record, null, 2, null);
            }
        });
    }

    public static final /* synthetic */ ActivityRvTabBinding access$getMBinding(BillActivity billActivity) {
        return billActivity.getMBinding();
    }

    @Override // android.app.Activity
    public final List<String> getTitle() {
        return this.title;
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final BaseAdapter<WithdrewBillBean, ItemWithdrewRecordBinding> getWithdrewAdapter() {
        return (BaseAdapter) this.withdrewAdapter.getValue();
    }

    public final BaseAdapter<DealMoneyRecordBean, ItemDealMoneyRecordBinding> getMoneyAdapter() {
        return (BaseAdapter) this.moneyAdapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setTitle("账单");
        getMBinding().rv.setAdapter(getWithdrewAdapter());
        getWithdrewAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BillActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                BillActivity.init$lambda$0(this.f$0);
            }
        });
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BillActivity$$ExternalSyntheticLambda1
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                BillActivity.init$lambda$1(this.f$0, refreshLayout);
            }
        });
        initTab();
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(BillActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(BillActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getData();
    }

    public final void initTab() {
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        TabLayout tab = getMBinding().tab;
        Intrinsics.checkNotNullExpressionValue(tab, "tab");
        initTab(tab, this.title, 14.0f, 14.0f, true);
        getMBinding().tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BillActivity.initTab.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab2) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab2) {
                Intrinsics.checkNotNullParameter(tab2, "tab");
                BillActivity.access$getMBinding(BillActivity.this).srl.autoRefresh();
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab2) {
                BillActivity.access$getMBinding(BillActivity.this).srl.autoRefresh();
            }
        });
    }

    public final void getData() {
        if (getMBinding().tab.getSelectedTabPosition() == 0) {
            getMBinding().rv.setAdapter(getWithdrewAdapter());
            if (this.page == 1) {
                getWithdrewAdapter().setNewInstance(null);
            }
            Repository.INSTANCE.getWithdrewRecord(this.page, new Function1<PageBean<WithdrewBillBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BillActivity.getData.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PageBean<WithdrewBillBean> pageBean) {
                    invoke2(pageBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PageBean<WithdrewBillBean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    BillActivity.access$getMBinding(BillActivity.this).srl.finishRefresh();
                    if (BillActivity.this.getPage() == 1) {
                        BillActivity.this.getWithdrewAdapter().setNewInstance(it.getList());
                    } else {
                        BillActivity.this.getWithdrewAdapter().addData(it.getList());
                    }
                    BillActivity billActivity = BillActivity.this;
                    billActivity.setPage(billActivity.getPage() + 1);
                    billActivity.getPage();
                    if (it.getCurrent_page() >= it.getLast_page()) {
                        BaseLoadMoreModule.loadMoreEnd$default(BillActivity.this.getWithdrewAdapter().getLoadMoreModule(), false, 1, null);
                    } else {
                        BillActivity.this.getWithdrewAdapter().getLoadMoreModule().loadMoreComplete();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BillActivity.getData.2
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
                    BillActivity.this.netFail(it);
                    BillActivity.access$getMBinding(BillActivity.this).srl.finishRefresh(false);
                    BillActivity.this.getWithdrewAdapter().getLoadMoreModule().loadMoreFail();
                }
            });
            return;
        }
        getMBinding().rv.setAdapter(getMoneyAdapter());
        if (this.page == 1) {
            getMoneyAdapter().setNewInstance(null);
        }
        Repository.INSTANCE.getDealMoneyRecord(this.page, new Function1<PageBean<DealMoneyRecordBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BillActivity.getData.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<DealMoneyRecordBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<DealMoneyRecordBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                BillActivity.access$getMBinding(BillActivity.this).srl.finishRefresh();
                if (BillActivity.this.getPage() == 1) {
                    BillActivity.this.getMoneyAdapter().setNewInstance(it.getList());
                } else {
                    BillActivity.this.getMoneyAdapter().addData(it.getList());
                }
                BillActivity billActivity = BillActivity.this;
                billActivity.setPage(billActivity.getPage() + 1);
                billActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(BillActivity.this.getMoneyAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    BillActivity.this.getMoneyAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BillActivity.getData.4
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
                BillActivity.this.netFail(it);
                BillActivity.access$getMBinding(BillActivity.this).srl.finishRefresh(false);
                BillActivity.this.getMoneyAdapter().getLoadMoreModule().loadMoreFail();
            }
        });
    }
}
