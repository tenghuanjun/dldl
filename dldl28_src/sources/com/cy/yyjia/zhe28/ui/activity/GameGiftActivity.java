package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
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
import com.cy.yyjia.zhe28.databinding.ActivityRvTabBinding;
import com.cy.yyjia.zhe28.databinding.ItemGiftBinding;
import com.cy.yyjia.zhe28.domain.GiftBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameGiftActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u001dH\u0016J\u0006\u0010\u001f\u001a\u00020\u001dR'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0014R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/GameGiftActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvTabBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GiftBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGiftBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "gid", "", "getGid", "()I", "gid$delegate", "page", "getPage", "setPage", "(I)V", "types", "", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "getTypes", "()Ljava/util/List;", "setTypes", "(Ljava/util/List;)V", "getData", "", "init", "initTab", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameGiftActivity extends BaseActivity<ActivityRvTabBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: gid$delegate, reason: from kotlin metadata */
    private final Lazy gid;
    private int page;
    public List<TypeBean> types;

    public GameGiftActivity() {
        super(R.layout.activity_rv_tab, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<GiftBean, ItemGiftBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity$adapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GiftBean, ItemGiftBinding> invoke() {
                final GameGiftActivity gameGiftActivity = this.this$0;
                return new BaseAdapter<>(R.layout.item_gift, new Function3<BaseDataBindingHolder<ItemGiftBinding>, Integer, GiftBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity$adapter$2.1
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemGiftBinding> baseDataBindingHolder, Integer num, GiftBean giftBean) {
                        invoke(baseDataBindingHolder, num.intValue(), giftBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemGiftBinding> h, int i, GiftBean giftBean) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemGiftBinding itemGiftBinding = (ItemGiftBinding) h.getDataBinding();
                        if (itemGiftBinding == null) {
                            return;
                        }
                        itemGiftBinding.setVip(gameGiftActivity.getTypes().get(GameGiftActivity.access$getMBinding(gameGiftActivity).tab.getSelectedTabPosition()).getId() == 5);
                    }
                });
            }
        });
        this.gid = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity$gid$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("gid", 0));
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ ActivityRvTabBinding access$getMBinding(GameGiftActivity gameGiftActivity) {
        return gameGiftActivity.getMBinding();
    }

    public final BaseAdapter<GiftBean, ItemGiftBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getGid() {
        return ((Number) this.gid.getValue()).intValue();
    }

    public final List<TypeBean> getTypes() {
        List<TypeBean> list = this.types;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("types");
        return null;
    }

    public final void setTypes(List<TypeBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.types = list;
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setTitle("礼包");
        getMBinding().navigation.setMoreText("我的礼包");
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameGiftActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity$$ExternalSyntheticLambda1
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                GameGiftActivity.init$lambda$1(this.f$0, refreshLayout);
            }
        });
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                GameGiftActivity.init$lambda$2(this.f$0);
            }
        });
        getAdapter().addChildClickViewIds(R.id.btn);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameGiftActivity.init$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameGiftActivity.init$lambda$4(this.f$0, baseQuickAdapter, view, i);
            }
        });
        initTab();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(GameGiftActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Util.skipWithLogin(this$0, MyGiftActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(GameGiftActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(GameGiftActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(final GameGiftActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity$init$4$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                new ReceiveStatusDialog(this.this$0).set(1, this.this$0.getAdapter().getItem(i).getGift_id(), this.this$0.getGid()).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4(GameGiftActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Intent intent = new Intent(this$0, (Class<?>) GiftDetailActivity.class);
        intent.putExtra("id", this$0.getAdapter().getItem(i).getGift_id());
        this$0.startActivity(intent);
    }

    public final void initTab() {
        Repository.INSTANCE.getGameGiftType(new Function1<List<TypeBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity.initTab.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<TypeBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<TypeBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                GameGiftActivity.this.setTypes(it);
                Iterator<TypeBean> it2 = it.iterator();
                while (it2.hasNext()) {
                    GameGiftActivity.access$getMBinding(GameGiftActivity.this).tab.addTab(GameGiftActivity.access$getMBinding(GameGiftActivity.this).tab.newTab().setText(it2.next().getName()));
                }
                if (GameGiftActivity.access$getMBinding(GameGiftActivity.this).tab.getTabCount() > 3) {
                    GameGiftActivity.access$getMBinding(GameGiftActivity.this).tab.setTabMode(0);
                }
                GameGiftActivity gameGiftActivity = GameGiftActivity.this;
                TabLayout tab = GameGiftActivity.access$getMBinding(gameGiftActivity).tab;
                Intrinsics.checkNotNullExpressionValue(tab, "tab");
                gameGiftActivity.initTab(tab, 14.0f, 16.0f, true);
                TabLayout tabLayout = GameGiftActivity.access$getMBinding(GameGiftActivity.this).tab;
                final GameGiftActivity gameGiftActivity2 = GameGiftActivity.this;
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity.initTab.1.1
                    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                    public void onTabUnselected(TabLayout.Tab tab2) {
                    }

                    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                    public void onTabSelected(TabLayout.Tab tab2) {
                        GameGiftActivity.access$getMBinding(gameGiftActivity2).srl.autoRefresh();
                    }

                    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                    public void onTabReselected(TabLayout.Tab tab2) {
                        GameGiftActivity.access$getMBinding(gameGiftActivity2).srl.autoRefresh();
                    }
                });
                GameGiftActivity.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity.initTab.2
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
                GameGiftActivity.this.netFail(it);
            }
        });
    }

    public final void getData() {
        Repository.INSTANCE.getGameGiftList(this.page, getGid(), getTypes().get(getMBinding().tab.getSelectedTabPosition()).getId(), new Function1<PageBean<GiftBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity.getData.1
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
                GameGiftActivity.access$getMBinding(GameGiftActivity.this).srl.finishRefresh();
                if (GameGiftActivity.this.getPage() == 1) {
                    GameGiftActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    GameGiftActivity.this.getAdapter().addData(it.getList());
                }
                GameGiftActivity gameGiftActivity = GameGiftActivity.this;
                gameGiftActivity.setPage(gameGiftActivity.getPage() + 1);
                gameGiftActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(GameGiftActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    GameGiftActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameGiftActivity.getData.2
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
                GameGiftActivity.access$getMBinding(GameGiftActivity.this).srl.finishRefresh(false);
                GameGiftActivity.this.netFail(it);
            }
        });
    }
}
