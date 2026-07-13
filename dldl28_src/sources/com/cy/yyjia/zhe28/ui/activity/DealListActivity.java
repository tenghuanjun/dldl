package com.cy.yyjia.zhe28.ui.activity;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.Observable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityDealListBinding;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.domain.FilterBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.TradeRuleBean;
import com.cy.yyjia.zhe28.ui.adapter.DealAdapter;
import com.cy.yyjia.zhe28.ui.dialog.RuleDialog;
import com.cy.yyjia.zhe28.util.Repository;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DealListActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001aJ\b\u0010\u001c\u001a\u00020\u001aH\u0016J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R!\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R!\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/DealListActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityDealListBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/ui/adapter/DealAdapter;", "getAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/DealAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "priceTypes", "Ljava/util/ArrayList;", "Lcom/cy/yyjia/zhe28/domain/FilterBean;", "Lkotlin/collections/ArrayList;", "getPriceTypes", "()Ljava/util/ArrayList;", "sortTypes", "getSortTypes", "getData", "", "getNewData", "init", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DealListActivity extends BaseActivity<ActivityDealListBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;
    private final ArrayList<FilterBean> priceTypes;
    private final ArrayList<FilterBean> sortTypes;

    public DealListActivity() {
        super(R.layout.activity_deal_list, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<DealAdapter>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealListActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DealAdapter invoke() {
                return new DealAdapter();
            }
        });
        this.sortTypes = CollectionsKt.arrayListOf(new FilterBean("最新发布", "dateline", SocialConstants.PARAM_APP_DESC, true), new FilterBean("回归号", "dateline", SocialConstants.PARAM_APP_DESC), new FilterBean("开局号", "dateline", SocialConstants.PARAM_APP_DESC), new FilterBean("价格最低", "sellMoney", "asc"), new FilterBean("价格最高", "sellMoney", SocialConstants.PARAM_APP_DESC));
        this.priceTypes = CollectionsKt.arrayListOf(new FilterBean("最新发布", "dateline", SocialConstants.PARAM_APP_DESC, true), new FilterBean("价格最低", "sellMoney", "asc"), new FilterBean("价格最高", "sellMoney", SocialConstants.PARAM_APP_DESC));
        this.page = 1;
    }

    public static final /* synthetic */ ActivityDealListBinding access$getMBinding(DealListActivity dealListActivity) {
        return dealListActivity.getMBinding();
    }

    public final DealAdapter getAdapter() {
        return (DealAdapter) this.adapter.getValue();
    }

    public final ArrayList<FilterBean> getSortTypes() {
        return this.sortTypes;
    }

    public final ArrayList<FilterBean> getPriceTypes() {
        return this.priceTypes;
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        final ActivityDealListBinding mBinding = getMBinding();
        mBinding.setJianlou(0);
        mBinding.setFilter(0);
        mBinding.setKeyword(getIntent().getStringExtra("game"));
        mBinding.setServer("");
        mBinding.setMin("");
        mBinding.setMax("");
        mBinding.setOrder(this.sortTypes.get(getIntent().getIntExtra("order", 0)));
        mBinding.rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealListActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                DealListActivity.init$lambda$5$lambda$0(this.f$0);
            }
        });
        getAdapter().setMyEmptyView("deal");
        mBinding.srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealListActivity$$ExternalSyntheticLambda2
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                DealListActivity.init$lambda$5$lambda$1(this.f$0, refreshLayout);
            }
        });
        mBinding.et.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealListActivity$$ExternalSyntheticLambda3
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return DealListActivity.init$lambda$5$lambda$2(this.f$0, textView, i, keyEvent);
            }
        });
        mBinding.rvFilter.setOnTouchListener(new View.OnTouchListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealListActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return DealListActivity.init$lambda$5$lambda$3(this.f$0, view, motionEvent);
            }
        });
        mBinding.navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealListActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DealListActivity.init$lambda$5$lambda$4(mBinding, this, view);
            }
        });
        mBinding.srl.autoRefresh();
        mBinding.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() { // from class: com.cy.yyjia.zhe28.ui.activity.DealListActivity$init$1$6
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == 69) {
                    FilterBean order = mBinding.getOrder();
                    Intrinsics.checkNotNull(order);
                    String name = order.getName();
                    if (Intrinsics.areEqual(name, "回归号")) {
                        mBinding.navigation.setMoreText("回归号规则");
                    } else if (Intrinsics.areEqual(name, "开局号")) {
                        mBinding.navigation.setMoreText("开局号规则");
                    } else {
                        mBinding.navigation.setMoreText("");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$5$lambda$0(DealListActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$5$lambda$1(DealListActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean init$lambda$5$lambda$2(DealListActivity this$0, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i != 3) {
            return false;
        }
        this$0.hideSoftKeyboard();
        this$0.getNewData();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean init$lambda$5$lambda$3(DealListActivity this$0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (motionEvent.getAction() == 1) {
            this$0.getMBinding().tvOrder.setSelected(false);
            this$0.getMBinding().tvServer.setSelected(false);
            this$0.getMBinding().tvPrice.setSelected(false);
            this$0.getMBinding().rvFilter.setVisibility(8);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$5$lambda$4(final ActivityDealListBinding this_run, final DealListActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Repository.INSTANCE.getDealRule(new Function1<TradeRuleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealListActivity$init$1$5$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TradeRuleBean tradeRuleBean) {
                invoke2(tradeRuleBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TradeRuleBean it) {
                String open;
                Intrinsics.checkNotNullParameter(it, "it");
                FilterBean order = this_run.getOrder();
                Intrinsics.checkNotNull(order);
                String name = order.getName();
                if (Intrinsics.areEqual(name, "回归号")) {
                    open = it.getRegress();
                } else {
                    open = Intrinsics.areEqual(name, "开局号") ? it.getOpen() : "";
                }
                new RuleDialog(this$0).setText(open).show();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealListActivity$init$1$5$2
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
                this.this$0.netFail(it);
            }
        });
    }

    public final void getNewData() {
        getMBinding().setFilter(0);
        getAdapter().setNewInstance(null);
        getMBinding().srl.autoRefresh();
        hideSoftKeyboard();
    }

    public final void getData() {
        FilterBean order = getMBinding().getOrder();
        Intrinsics.checkNotNull(order);
        boolean zAreEqual = Intrinsics.areEqual(order.getName(), "回归号");
        FilterBean order2 = getMBinding().getOrder();
        Intrinsics.checkNotNull(order2);
        boolean zAreEqual2 = Intrinsics.areEqual(order2.getName(), "开局号");
        Repository repository = Repository.INSTANCE;
        int i = this.page;
        FilterBean order3 = getMBinding().getOrder();
        Intrinsics.checkNotNull(order3);
        String data = order3.getData();
        FilterBean order4 = getMBinding().getOrder();
        Intrinsics.checkNotNull(order4);
        String sort = order4.getSort();
        String keyword = getMBinding().getKeyword();
        if (keyword == null) {
            keyword = "";
        }
        String str = keyword;
        String min = getMBinding().getMin();
        Intrinsics.checkNotNull(min);
        String max = getMBinding().getMax();
        Intrinsics.checkNotNull(max);
        String server = getMBinding().getServer();
        Intrinsics.checkNotNull(server);
        repository.getDealList(i, "newlist", data, sort, str, min, max, server, getMBinding().getJianlou(), zAreEqual ? 1 : 0, zAreEqual2 ? 1 : 0, new Function1<PageBean<DealBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealListActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<DealBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<DealBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                DealListActivity.access$getMBinding(DealListActivity.this).srl.finishRefresh();
                if (DealListActivity.this.getPage() == 1) {
                    DealListActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    DealListActivity.this.getAdapter().addData((Collection) it.getList());
                }
                DealListActivity dealListActivity = DealListActivity.this;
                dealListActivity.setPage(dealListActivity.getPage() + 1);
                dealListActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(DealListActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    DealListActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealListActivity.getData.2
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
                DealListActivity.access$getMBinding(DealListActivity.this).srl.finishRefresh(false);
                DealListActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                DealListActivity dealListActivity = DealListActivity.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                dealListActivity.log(localizedMessage);
            }
        }, (24576 & 8192) != 0 ? false : false, (24576 & 16384) != 0 ? 0 : 0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(final View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        hideSoftKeyboard();
        switch (v.getId()) {
            case R.id.cl_jianlou /* 2131362011 */:
                getMBinding().setJianlou(1);
                getNewData();
                break;
            case R.id.fl_filter3 /* 2131362106 */:
                getMBinding().setFilter(0);
                break;
            case R.id.tv_order /* 2131362738 */:
                getMBinding().setFilter(1);
                final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_hall_filter, this.sortTypes);
                getMBinding().rvFilter.setAdapter(baseAdapter);
                getMBinding().rvFilter.setVisibility(0);
                baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealListActivity$$ExternalSyntheticLambda0
                    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        DealListActivity.onClick$lambda$6(baseAdapter, this, v, baseQuickAdapter, view, i);
                    }
                });
                break;
            case R.id.tv_price /* 2131362750 */:
                getMBinding().setFilter(3);
                break;
            case R.id.tv_reset /* 2131362761 */:
                if (getMBinding().getFilter() == 3) {
                    getMBinding().setMin("");
                    getMBinding().setMax("");
                } else {
                    getMBinding().setServer("");
                }
                break;
            case R.id.tv_search /* 2131362768 */:
                getNewData();
                break;
            case R.id.tv_server /* 2131362770 */:
                getMBinding().setFilter(2);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onClick$lambda$6(BaseAdapter popAdapter, DealListActivity this$0, View v, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(popAdapter, "$popAdapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(v, "$v");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Iterator it = popAdapter.getData().iterator();
        while (it.hasNext()) {
            ((FilterBean) it.next()).setSelected(false);
        }
        ((FilterBean) popAdapter.getItem(i)).setSelected(true);
        this$0.getMBinding().setOrder((FilterBean) popAdapter.getItem(i));
        this$0.getMBinding().rvFilter.setVisibility(8);
        v.setSelected(false);
        this$0.getNewData();
    }
}
