package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemVipGiftBinding;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.VipGiftBean;
import com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog;
import com.cy.yyjia.zhe28.util.Repository;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VipGiftListActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0018H\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/VipGiftListActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/VipGiftBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemVipGiftBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "newGame", "", "getNewGame", "()Z", "newGame$delegate", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VipGiftListActivity extends BaseActivity<ActivityRvBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: newGame$delegate, reason: from kotlin metadata */
    private final Lazy newGame;
    private int page;

    public VipGiftListActivity() {
        super(R.layout.activity_rv, 0, 2, null);
        this.newGame = LazyKt.lazy(new Function0<Boolean>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipGiftListActivity$newGame$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(this.this$0.getIntent().getBooleanExtra("newGame", false));
            }
        });
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<VipGiftBean, ItemVipGiftBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipGiftListActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<VipGiftBean, ItemVipGiftBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_vip_gift, null, 2, null);
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ ActivityRvBinding access$getMBinding(VipGiftListActivity vipGiftListActivity) {
        return vipGiftListActivity.getMBinding();
    }

    public final boolean getNewGame() {
        return ((Boolean) this.newGame.getValue()).booleanValue();
    }

    public final BaseAdapter<VipGiftBean, ItemVipGiftBinding> getAdapter() {
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
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.VipGiftListActivity$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                VipGiftListActivity.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getMBinding().navigation.setTitle("游戏礼包");
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.VipGiftListActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                VipGiftListActivity.init$lambda$1(this.f$0);
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.VipGiftListActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VipGiftListActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(VipGiftListActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(VipGiftListActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(VipGiftListActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        new ReceiveStatusDialog(this$0).set(3, this$0.getAdapter().getItem(i).getId(), this$0.getAdapter().getItem(i).getGame().getId()).show();
    }

    public final void getData() {
        Repository.INSTANCE.getVipGiftList(this.page, new Function1<PageBean<VipGiftBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipGiftListActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<VipGiftBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<VipGiftBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VipGiftListActivity.access$getMBinding(VipGiftListActivity.this).srl.finishRefresh();
                if (VipGiftListActivity.this.getPage() == 1) {
                    VipGiftListActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    VipGiftListActivity.this.getAdapter().addData(it.getList());
                }
                VipGiftListActivity vipGiftListActivity = VipGiftListActivity.this;
                vipGiftListActivity.setPage(vipGiftListActivity.getPage() + 1);
                vipGiftListActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(VipGiftListActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    VipGiftListActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipGiftListActivity.getData.2
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
                VipGiftListActivity.access$getMBinding(VipGiftListActivity.this).srl.finishRefresh(false);
                VipGiftListActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                VipGiftListActivity.this.netFail(it);
            }
        });
    }
}
