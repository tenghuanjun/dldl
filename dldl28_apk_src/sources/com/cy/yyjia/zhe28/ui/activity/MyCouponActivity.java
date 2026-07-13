package com.cy.yyjia.zhe28.ui.activity;

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
import com.cy.yyjia.zhe28.databinding.ItemMyCouponBinding;
import com.cy.yyjia.zhe28.domain.CouponBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MyCouponActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u0006\u0010\u0015\u001a\u00020\u0013R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/MyCouponActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvTabBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/CouponBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemMyCouponBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "", "init", "initTab", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MyCouponActivity extends BaseActivity<ActivityRvTabBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;

    public MyCouponActivity() {
        super(R.layout.activity_rv_tab, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<CouponBean, ItemMyCouponBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyCouponActivity$adapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<CouponBean, ItemMyCouponBinding> invoke() {
                final MyCouponActivity myCouponActivity = this.this$0;
                return new BaseAdapter<>(R.layout.item_my_coupon, new Function3<BaseDataBindingHolder<ItemMyCouponBinding>, Integer, CouponBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyCouponActivity$adapter$2.1
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemMyCouponBinding> baseDataBindingHolder, Integer num, CouponBean couponBean) {
                        invoke(baseDataBindingHolder, num.intValue(), couponBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemMyCouponBinding> h, int i, CouponBean couponBean) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemMyCouponBinding itemMyCouponBinding = (ItemMyCouponBinding) h.getDataBinding();
                        if (itemMyCouponBinding == null) {
                            return;
                        }
                        itemMyCouponBinding.setType(MyCouponActivity.access$getMBinding(myCouponActivity).tab.getSelectedTabPosition());
                    }
                });
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ ActivityRvTabBinding access$getMBinding(MyCouponActivity myCouponActivity) {
        return myCouponActivity.getMBinding();
    }

    public final BaseAdapter<CouponBean, ItemMyCouponBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setTitle("我的优惠券");
        getMBinding().rv.setAdapter(getAdapter());
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyCouponActivity$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                MyCouponActivity.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyCouponActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                MyCouponActivity.init$lambda$1(this.f$0);
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyCouponActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MyCouponActivity.init$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        initTab();
        getMBinding().srl.autoRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(MyCouponActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(MyCouponActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(MyCouponActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Integer gameId;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.getMBinding().tab.getSelectedTabPosition() != 0 || (gameId = this$0.getAdapter().getItem(i).getGameId()) == null) {
            return;
        }
        Util.gotoGame(this$0, gameId.intValue());
    }

    public final void initTab() {
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        TabLayout tab = getMBinding().tab;
        Intrinsics.checkNotNullExpressionValue(tab, "tab");
        initTab(tab, CollectionsKt.listOf((Object[]) new String[]{"未使用", "已使用", "已过期"}), 16.0f, 16.0f, true);
        getMBinding().tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyCouponActivity.initTab.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab2) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab2) {
                MyCouponActivity.access$getMBinding(MyCouponActivity.this).srl.autoRefresh();
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab2) {
                MyCouponActivity.access$getMBinding(MyCouponActivity.this).srl.autoRefresh();
            }
        });
    }

    public final void getData() {
        Repository.getMyCoupon$default(Repository.INSTANCE, this.page, getMBinding().tab.getSelectedTabPosition(), new Function1<PageBean<CouponBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyCouponActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<CouponBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<CouponBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                MyCouponActivity.access$getMBinding(MyCouponActivity.this).srl.finishRefresh();
                if (MyCouponActivity.this.getPage() == 1) {
                    MyCouponActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    MyCouponActivity.this.getAdapter().addData(it.getList());
                }
                MyCouponActivity myCouponActivity = MyCouponActivity.this;
                myCouponActivity.setPage(myCouponActivity.getPage() + 1);
                myCouponActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(MyCouponActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    MyCouponActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyCouponActivity.getData.2
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
                MyCouponActivity.access$getMBinding(MyCouponActivity.this).srl.finishRefresh(false);
                MyCouponActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                MyCouponActivity.this.netFail(it);
            }
        }, null, 16, null);
    }
}
