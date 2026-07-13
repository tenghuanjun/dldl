package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityMyWelfareBinding;
import com.cy.yyjia.zhe28.databinding.ItemMyCouponBinding;
import com.cy.yyjia.zhe28.databinding.ItemMyGiftBinding;
import com.cy.yyjia.zhe28.domain.CouponBean;
import com.cy.yyjia.zhe28.domain.GiftBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
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

/* JADX INFO: compiled from: MyWelfareActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0013J\u0006\u0010#\u001a\u00020!J\b\u0010$\u001a\u00020!H\u0016J\u0006\u0010%\u001a\u00020!J\u0010\u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020(H\u0016R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR'\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0010\u0010\nR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001d\u001a\u00020\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\f\u001a\u0004\b\u001e\u0010\u0015¨\u0006)"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/MyWelfareActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityMyWelfareBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/CouponBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemMyCouponBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "giftAdapter", "Lcom/cy/yyjia/zhe28/domain/GiftBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemMyGiftBinding;", "getGiftAdapter", "giftAdapter$delegate", "page", "", "getPage", "()I", "setPage", "(I)V", "title", "", "", "getTitle", "()Ljava/util/List;", "type", "getType", "type$delegate", "changeCoupon", "", ImageSelector.POSITION, "getData", "init", "initTab", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MyWelfareActivity extends BaseActivity<ActivityMyWelfareBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: giftAdapter$delegate, reason: from kotlin metadata */
    private final Lazy giftAdapter;
    private int page;
    private final List<String> title;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;

    public MyWelfareActivity() {
        super(R.layout.activity_my_welfare, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<CouponBean, ItemMyCouponBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity$adapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<CouponBean, ItemMyCouponBinding> invoke() {
                final MyWelfareActivity myWelfareActivity = this.this$0;
                return new BaseAdapter<>(R.layout.item_my_coupon, new Function3<BaseDataBindingHolder<ItemMyCouponBinding>, Integer, CouponBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity$adapter$2.1
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
                        itemMyCouponBinding.setType(MyWelfareActivity.access$getMBinding(myWelfareActivity).getPosition());
                    }
                });
            }
        });
        this.giftAdapter = LazyKt.lazy(new Function0<BaseAdapter<GiftBean, ItemMyGiftBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity$giftAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GiftBean, ItemMyGiftBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_my_gift, null, 2, null);
            }
        });
        this.type = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity$type$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra(ImageSelector.POSITION, 0));
            }
        });
        this.title = CollectionsKt.listOf((Object[]) new String[]{"我的优惠券", "我的礼包"});
        this.page = 1;
    }

    public static final /* synthetic */ ActivityMyWelfareBinding access$getMBinding(MyWelfareActivity myWelfareActivity) {
        return myWelfareActivity.getMBinding();
    }

    public final BaseAdapter<CouponBean, ItemMyCouponBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final BaseAdapter<GiftBean, ItemMyGiftBinding> getGiftAdapter() {
        return (BaseAdapter) this.giftAdapter.getValue();
    }

    public final int getType() {
        return ((Number) this.type.getValue()).intValue();
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

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                MyWelfareActivity.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                MyWelfareActivity.init$lambda$1(this.f$0);
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MyWelfareActivity.init$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().addChildClickViewIds(R.id.iv_more);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MyWelfareActivity.init$lambda$4(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getGiftAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                MyWelfareActivity.init$lambda$5(this.f$0);
            }
        });
        getGiftAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MyWelfareActivity.init$lambda$6(this.f$0, baseQuickAdapter, view, i);
            }
        });
        initTab();
        getMBinding().srl.autoRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(MyWelfareActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        if (this$0.getMBinding().tab.getSelectedTabPosition() == 0) {
            this$0.getMBinding().rv.setAdapter(this$0.getAdapter());
            this$0.getAdapter().setNewInstance(null);
        } else {
            this$0.getMBinding().rv.setAdapter(this$0.getGiftAdapter());
            this$0.getGiftAdapter().setNewInstance(null);
        }
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(MyWelfareActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(MyWelfareActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Integer gameId;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.getMBinding().tab.getSelectedTabPosition() != 0 || (gameId = this$0.getAdapter().getItem(i).getGameId()) == null) {
            return;
        }
        Util.gotoGame(this$0, gameId.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4(MyWelfareActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.getAdapter().getItem(i).setSelected(!this$0.getAdapter().getItem(i).getSelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$5(MyWelfareActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$6(MyWelfareActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Util.copy(this$0, this$0.getGiftAdapter().getItem(i).getGift_code());
    }

    public final void initTab() {
        TabLayout tab = getMBinding().tab;
        Intrinsics.checkNotNullExpressionValue(tab, "tab");
        initTab(tab, this.title, 16.0f, 16.0f, true);
        getMBinding().tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity.initTab.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab2) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab2) {
                Intrinsics.checkNotNullParameter(tab2, "tab");
                MyWelfareActivity.access$getMBinding(MyWelfareActivity.this).llCoupon.setVisibility(MyWelfareActivity.access$getMBinding(MyWelfareActivity.this).tab.getSelectedTabPosition() == 0 ? 0 : 8);
                MyWelfareActivity.access$getMBinding(MyWelfareActivity.this).srl.autoRefresh();
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab2) {
                MyWelfareActivity.access$getMBinding(MyWelfareActivity.this).srl.autoRefresh();
            }
        });
    }

    public final void getData() {
        if (getMBinding().tab.getSelectedTabPosition() == 0) {
            Repository.getMyCoupon$default(Repository.INSTANCE, this.page, getMBinding().getPosition(), new Function1<PageBean<CouponBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity.getData.1
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
                    MyWelfareActivity.access$getMBinding(MyWelfareActivity.this).srl.finishRefresh();
                    if (MyWelfareActivity.this.getPage() == 1) {
                        MyWelfareActivity.this.getAdapter().setNewInstance(it.getList());
                    } else {
                        MyWelfareActivity.this.getAdapter().addData(it.getList());
                    }
                    MyWelfareActivity myWelfareActivity = MyWelfareActivity.this;
                    myWelfareActivity.setPage(myWelfareActivity.getPage() + 1);
                    myWelfareActivity.getPage();
                    if (it.getCurrent_page() >= it.getLast_page()) {
                        BaseLoadMoreModule.loadMoreEnd$default(MyWelfareActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                    } else {
                        MyWelfareActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity.getData.2
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
                    MyWelfareActivity.access$getMBinding(MyWelfareActivity.this).srl.finishRefresh(false);
                    MyWelfareActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                    MyWelfareActivity.this.netFail(it);
                }
            }, null, 16, null);
        } else {
            Repository.getMyGift$default(Repository.INSTANCE, this.page, new Function1<PageBean<GiftBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity.getData.3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PageBean<GiftBean> pageBean) {
                    invoke2(pageBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PageBean<GiftBean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    MyWelfareActivity.access$getMBinding(MyWelfareActivity.this).srl.finishRefresh();
                    if (MyWelfareActivity.this.getPage() == 1) {
                        MyWelfareActivity.this.getGiftAdapter().setNewInstance(it.getList());
                    } else {
                        MyWelfareActivity.this.getGiftAdapter().addData(it.getList());
                    }
                    MyWelfareActivity myWelfareActivity = MyWelfareActivity.this;
                    myWelfareActivity.setPage(myWelfareActivity.getPage() + 1);
                    myWelfareActivity.getPage();
                    if (it.getCurrent_page() >= it.getLast_page()) {
                        BaseLoadMoreModule.loadMoreEnd$default(MyWelfareActivity.this.getGiftAdapter().getLoadMoreModule(), false, 1, null);
                    } else {
                        MyWelfareActivity.this.getGiftAdapter().getLoadMoreModule().loadMoreComplete();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyWelfareActivity.getData.4
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
                    MyWelfareActivity.access$getMBinding(MyWelfareActivity.this).srl.finishRefresh(false);
                    MyWelfareActivity.this.getGiftAdapter().getLoadMoreModule().loadMoreFail();
                    MyWelfareActivity.this.netFail(it);
                }
            }, null, 8, null);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.tv1 /* 2131362645 */:
                changeCoupon(0);
                break;
            case R.id.tv2 /* 2131362646 */:
                changeCoupon(1);
                break;
            case R.id.tv3 /* 2131362647 */:
                changeCoupon(2);
                break;
        }
    }

    public final void changeCoupon(int position) {
        if (getMBinding().srl.isRefreshing()) {
            return;
        }
        getMBinding().setPosition(position);
        getMBinding().srl.autoRefresh();
    }
}
