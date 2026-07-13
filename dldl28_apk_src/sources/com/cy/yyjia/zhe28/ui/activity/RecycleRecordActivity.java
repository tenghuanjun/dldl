package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvTabBinding;
import com.cy.yyjia.zhe28.databinding.ItemRecycleRecordBinding;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.RecycleRecordBean;
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

/* JADX INFO: compiled from: RecycleRecordActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\u0006\u0010\u001a\u001a\u00020\u0018J\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\rR'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/RecycleRecordActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvTabBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/RecycleRecordBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemRecycleRecordBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "title", "", "", "getTitle", "()Ljava/util/List;", "getData", "", "init", "initTab", "off", ImageSelector.POSITION, "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RecycleRecordActivity extends BaseActivity<ActivityRvTabBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;
    private final List<String> title;

    public RecycleRecordActivity() {
        super(R.layout.activity_rv_tab, 0, 2, null);
        this.title = CollectionsKt.listOf((Object[]) new String[]{"全部", "审核中", "已回收", "被驳回"});
        this.page = 1;
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<RecycleRecordBean, ItemRecycleRecordBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleRecordActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<RecycleRecordBean, ItemRecycleRecordBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_recycle_record, null, 2, null);
            }
        });
    }

    public static final /* synthetic */ ActivityRvTabBinding access$getMBinding(RecycleRecordActivity recycleRecordActivity) {
        return recycleRecordActivity.getMBinding();
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

    public final BaseAdapter<RecycleRecordBean, ItemRecycleRecordBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setTitle("我的回收");
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleRecordActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                RecycleRecordActivity.init$lambda$0(this.f$0);
            }
        });
        getAdapter().addChildClickViewIds(R.id.tv_off);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleRecordActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                RecycleRecordActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        initTab();
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleRecordActivity$$ExternalSyntheticLambda2
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                RecycleRecordActivity.init$lambda$2(this.f$0, refreshLayout);
            }
        });
        getMBinding().srl.autoRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(RecycleRecordActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(final RecycleRecordActivity this$0, BaseQuickAdapter baseQuickAdapter, View v, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.tv_off) {
            new ConfirmDialog(this$0).setTip("您确定要下架游戏小号吗？").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleRecordActivity$init$2$1
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
                    this.this$0.off(i);
                }
            }).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(RecycleRecordActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    public final void initTab() {
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        TabLayout tab = getMBinding().tab;
        Intrinsics.checkNotNullExpressionValue(tab, "tab");
        initTab(tab, this.title, 14.0f, 14.0f, true);
        getMBinding().tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleRecordActivity.initTab.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab2) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab2) {
                Intrinsics.checkNotNullParameter(tab2, "tab");
                RecycleRecordActivity.access$getMBinding(RecycleRecordActivity.this).srl.autoRefresh();
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab2) {
                RecycleRecordActivity.access$getMBinding(RecycleRecordActivity.this).srl.autoRefresh();
            }
        });
    }

    public final void getData() {
        Repository.INSTANCE.getRecycleRecordList(getMBinding().tab.getSelectedTabPosition(), new Function1<PageBean<RecycleRecordBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleRecordActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<RecycleRecordBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<RecycleRecordBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                RecycleRecordActivity.access$getMBinding(RecycleRecordActivity.this).srl.finishRefresh();
                if (RecycleRecordActivity.this.getPage() == 1) {
                    RecycleRecordActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    RecycleRecordActivity.this.getAdapter().addData(it.getList());
                }
                RecycleRecordActivity recycleRecordActivity = RecycleRecordActivity.this;
                recycleRecordActivity.setPage(recycleRecordActivity.getPage() + 1);
                recycleRecordActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(RecycleRecordActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    RecycleRecordActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleRecordActivity.getData.2
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
                RecycleRecordActivity.this.netFail(it);
                RecycleRecordActivity.access$getMBinding(RecycleRecordActivity.this).srl.finishRefresh(false);
                RecycleRecordActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
            }
        });
    }

    public final void off(int position) {
        Repository.INSTANCE.recycleOff(getAdapter().getItem(position).getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleRecordActivity.off.1
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
                RecycleRecordActivity.this.toast(it.getMsg());
                RecycleRecordActivity.this.setPage(1);
                RecycleRecordActivity.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.RecycleRecordActivity.off.2
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
                RecycleRecordActivity.this.netFail(it);
            }
        });
    }
}
