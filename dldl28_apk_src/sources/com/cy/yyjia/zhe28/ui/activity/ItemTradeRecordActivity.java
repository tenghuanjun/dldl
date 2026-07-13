package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvTabBinding;
import com.cy.yyjia.zhe28.databinding.ItemItemTradeRecordBinding;
import com.cy.yyjia.zhe28.domain.ItemTradeRecordBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: ItemTradeRecordActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bJ\u0006\u0010\u0018\u001a\u00020\u0016J\u0006\u0010\u0019\u001a\u00020\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0016J\u0006\u0010\u001b\u001a\u00020\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/ItemTradeRecordActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvTabBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/ItemTradeRecordBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemItemTradeRecordBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "page", "", "getPage", "()I", "setPage", "(I)V", "title", "", "", "getTitle", "()Ljava/util/List;", "confirm", "", ImageSelector.POSITION, "getData", "getNewData", "init", "initTab", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ItemTradeRecordActivity extends BaseActivity<ActivityRvTabBinding> {
    public static final int $stable = 8;
    private final BaseAdapter<ItemTradeRecordBean, ItemItemTradeRecordBinding> adapter;
    private int page;
    private final List<String> title;

    public ItemTradeRecordActivity() {
        super(R.layout.activity_rv_tab, 0, 2, null);
        this.title = CollectionsKt.listOf((Object[]) new String[]{"全部", "待发货", "已发货"});
        this.page = 1;
        this.adapter = new BaseAdapter<>(R.layout.item_item_trade_record, null, 2, null);
    }

    public static final /* synthetic */ ActivityRvTabBinding access$getMBinding(ItemTradeRecordActivity itemTradeRecordActivity) {
        return itemTradeRecordActivity.getMBinding();
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

    public final BaseAdapter<ItemTradeRecordBean, ItemItemTradeRecordBinding> getAdapter() {
        return this.adapter;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setTitle("购买材料记录");
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeRecordActivity$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                ItemTradeRecordActivity.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getMBinding().rv.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeRecordActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ItemTradeRecordActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        this.adapter.addChildClickViewIds(R.id.tv_confirm);
        this.adapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeRecordActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ItemTradeRecordActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        BaseAdapter.setMyEmptyView$default(this.adapter, null, 1, null);
        this.adapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeRecordActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                ItemTradeRecordActivity.init$lambda$3(this.f$0);
            }
        });
        initTab();
        getMBinding().srl.autoRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(ItemTradeRecordActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.getNewData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(ItemTradeRecordActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Intent intent = new Intent(this$0, (Class<?>) ItemTradeDetailActivity.class);
        intent.putExtra("id", this$0.adapter.getItem(i).getAssetId());
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(ItemTradeRecordActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.confirm(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(ItemTradeRecordActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    public final void getNewData() {
        this.page = 1;
        this.adapter.setNewInstance(null);
        getData();
    }

    public final void initTab() {
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        TabLayout tab = getMBinding().tab;
        Intrinsics.checkNotNullExpressionValue(tab, "tab");
        initTab(tab, this.title, 14.0f, 14.0f, true);
        getMBinding().tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeRecordActivity.initTab.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab2) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab2) {
                Intrinsics.checkNotNullParameter(tab2, "tab");
                ItemTradeRecordActivity.access$getMBinding(ItemTradeRecordActivity.this).srl.autoRefresh();
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab2) {
                ItemTradeRecordActivity.access$getMBinding(ItemTradeRecordActivity.this).srl.autoRefresh();
            }
        });
    }

    public final void getData() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("page", String.valueOf(this.page));
        if (getMBinding().tab.getSelectedTabPosition() > 0) {
            linkedHashMap.put("status", String.valueOf(getMBinding().tab.getSelectedTabPosition()));
        }
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new ItemTradeRecordActivity$getData$$inlined$get$1("game/asset/getAssetOrders", linkedHashMap, null, this, this), 3, null);
    }

    public final void confirm(final int position) {
        new ConfirmDialog(this).setTitle("确认收货").setTip("确认收货后金额将自动发放给卖家！").setBtnText("确认").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeRecordActivity.confirm.1
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
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put("id", String.valueOf(ItemTradeRecordActivity.this.getAdapter().getItem(position).getId()));
                NetUtil netUtil = NetUtil.INSTANCE;
                final ItemTradeRecordActivity itemTradeRecordActivity = ItemTradeRecordActivity.this;
                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeRecordActivity.confirm.1.1
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
                        itemTradeRecordActivity.toast(it.getMsg());
                        itemTradeRecordActivity.getNewData();
                    }
                };
                final ItemTradeRecordActivity itemTradeRecordActivity2 = ItemTradeRecordActivity.this;
                NetUtil.post2$default(netUtil, "game/asset/confirmAssetOrderDeliver", linkedHashMap, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeRecordActivity.confirm.1.2
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
                        ItemTradeRecordActivity.access$getMBinding(itemTradeRecordActivity2).srl.finishRefresh(false);
                        itemTradeRecordActivity2.netFail(it);
                    }
                }, null, 16, null);
            }
        }).show();
    }
}
