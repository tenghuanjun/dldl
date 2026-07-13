package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvTabBinding;
import com.cy.yyjia.zhe28.databinding.ItemMyGameBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.cy.yyjia.zhe28.util.Repository;
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
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MyGameActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rJ\u0006\u0010\u001d\u001a\u00020\u001bJ\b\u0010\u001e\u001a\u00020\u001bH\u0016J\u0006\u0010\u001f\u001a\u00020\u001bR'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0017\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u000b\u001a\u0004\b\u0018\u0010\u000f¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/MyGameActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvTabBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemMyGameBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "title", "", "", "getTitle", "()Ljava/util/List;", "type", "getType", "type$delegate", "delete", "", ImageSelector.POSITION, "getData", "init", "initTab", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MyGameActivity extends BaseActivity<ActivityRvTabBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;
    private final List<String> title;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;

    public MyGameActivity() {
        super(R.layout.activity_rv_tab, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemMyGameBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyGameActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ItemMyGameBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_my_game, null, 2, null);
            }
        });
        this.type = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyGameActivity$type$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra(ImageSelector.POSITION, 0));
            }
        });
        this.title = CollectionsKt.listOf((Object[]) new String[]{"我预约的", "我收藏的", "我玩过的"});
        this.page = 1;
    }

    public static final /* synthetic */ ActivityRvTabBinding access$getMBinding(MyGameActivity myGameActivity) {
        return myGameActivity.getMBinding();
    }

    public final BaseAdapter<GameBean, ItemMyGameBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
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
        getMBinding().navigation.setTitle(this.title.get(getType()));
        getMBinding().rv.setAdapter(getAdapter());
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyGameActivity$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                MyGameActivity.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyGameActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                MyGameActivity.init$lambda$1(this.f$0);
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyGameActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MyGameActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().addChildClickViewIds(R.id.iv_delete);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyGameActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MyGameActivity.init$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        initTab();
        TabLayout.Tab tabAt = getMBinding().tab.getTabAt(getType());
        if (tabAt != null) {
            tabAt.select();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(MyGameActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(MyGameActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(MyGameActivity this$0, BaseQuickAdapter a2, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(v, "v");
        this$0.getAdapter().getItem(i).gotoGame(v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(final MyGameActivity this$0, BaseQuickAdapter baseQuickAdapter, View v, final int i) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.iv_delete) {
            int selectedTabPosition = this$0.getMBinding().tab.getSelectedTabPosition();
            if (selectedTabPosition == 0) {
                str = "取消预约";
            } else if (selectedTabPosition == 1) {
                str = "取消收藏";
            } else {
                str = "删除历史";
            }
            new ConfirmDialog(this$0).setTip("您确定要" + str + "吗").setBtnText(str).setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyGameActivity$init$4$1
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
                    this.this$0.delete(i);
                }
            }).show();
        }
    }

    public final void initTab() {
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        TabLayout tab = getMBinding().tab;
        Intrinsics.checkNotNullExpressionValue(tab, "tab");
        initTab(tab, this.title, 16.0f, 16.0f, true);
        getMBinding().tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.activity.MyGameActivity.initTab.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab2) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab2) {
                Intrinsics.checkNotNullParameter(tab2, "tab");
                MyGameActivity.access$getMBinding(MyGameActivity.this).srl.autoRefresh();
                MyGameActivity.access$getMBinding(MyGameActivity.this).navigation.setTitle(MyGameActivity.this.getTitle().get(tab2.getPosition()));
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab2) {
                MyGameActivity.access$getMBinding(MyGameActivity.this).srl.autoRefresh();
            }
        });
    }

    public final void getData() {
        Repository.INSTANCE.getMyGame(this.page, getMBinding().tab.getSelectedTabPosition(), new Function1<PageBean<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyGameActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<GameBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<GameBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                MyGameActivity.access$getMBinding(MyGameActivity.this).srl.finishRefresh();
                if (MyGameActivity.this.getPage() == 1) {
                    MyGameActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    MyGameActivity.this.getAdapter().addData(it.getList());
                }
                MyGameActivity myGameActivity = MyGameActivity.this;
                myGameActivity.setPage(myGameActivity.getPage() + 1);
                myGameActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(MyGameActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    MyGameActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyGameActivity.getData.2
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
                MyGameActivity.access$getMBinding(MyGameActivity.this).srl.finishRefresh(false);
                MyGameActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                MyGameActivity.this.netFail(it);
            }
        });
    }

    public final void delete(final int position) {
        Repository.INSTANCE.deleteMyGame(getMBinding().tab.getSelectedTabPosition(), getAdapter().getItem(position).getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyGameActivity.delete.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                invoke2(result);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Result it) {
                Intrinsics.checkNotNullParameter(it, "it");
                MyGameActivity.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    MyGameActivity.this.getAdapter().removeAt(position);
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.MyGameActivity.delete.2
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
                MyGameActivity.this.netFail(it);
            }
        });
    }
}
