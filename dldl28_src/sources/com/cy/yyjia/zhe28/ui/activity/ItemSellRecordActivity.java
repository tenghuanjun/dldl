package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityItemSellRecordBinding;
import com.cy.yyjia.zhe28.databinding.ItemItemSellOrderBinding;
import com.cy.yyjia.zhe28.databinding.ItemItemSellingBinding;
import com.cy.yyjia.zhe28.domain.ItemSellRecordBean;
import com.cy.yyjia.zhe28.domain.ItemTradeRecordBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.google.android.material.tabs.TabLayout;
import com.mobile.auth.gatewayauth.Constant;
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
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: ItemSellRecordActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000fJ\u000e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000fJ\u0006\u0010\u001d\u001a\u00020\u001aJ\u0006\u0010\u001e\u001a\u00020\u001aJ\u0006\u0010\u001f\u001a\u00020\u001aJ\u000e\u0010 \u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000fJ\b\u0010!\u001a\u00020\u001aH\u0016J\u0006\u0010\"\u001a\u00020\u001aJ\u000e\u0010#\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000fJ\"\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u000f2\b\u0010'\u001a\u0004\u0018\u00010(H\u0014R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006)"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/ItemSellRecordActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityItemSellRecordBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/ItemSellRecordBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemItemSellingBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "orderAdapter", "Lcom/cy/yyjia/zhe28/domain/ItemTradeRecordBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemItemSellOrderBinding;", "getOrderAdapter", "page", "", "getPage", "()I", "setPage", "(I)V", "title", "", "", "getTitle", "()Ljava/util/List;", "adjust", "", ImageSelector.POSITION, "cancel", "getData", "getNewData", "getOrder", "go", "init", "initTab", IjkMediaPlayer.OnNativeInvokeListener.ARG_OFFSET, "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "resultCode", "data", "Landroid/content/Intent;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ItemSellRecordActivity extends BaseActivity<ActivityItemSellRecordBinding> {
    public static final int $stable = 8;
    private final BaseAdapter<ItemSellRecordBean, ItemItemSellingBinding> adapter;
    private final BaseAdapter<ItemTradeRecordBean, ItemItemSellOrderBinding> orderAdapter;
    private int page;
    private final List<String> title;

    public static final /* synthetic */ ActivityItemSellRecordBinding access$getMBinding(ItemSellRecordActivity itemSellRecordActivity) {
        return itemSellRecordActivity.getMBinding();
    }

    public ItemSellRecordActivity() {
        super(R.layout.activity_item_sell_record, 0, 2, null);
        this.title = CollectionsKt.listOf((Object[]) new String[]{"正在出售", "待发货", "已完成"});
        this.page = 1;
        this.adapter = new BaseAdapter<>(R.layout.item_item_selling, null, 2, null);
        this.orderAdapter = new BaseAdapter<>(R.layout.item_item_sell_order, null, 2, null);
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

    public final BaseAdapter<ItemSellRecordBean, ItemItemSellingBinding> getAdapter() {
        return this.adapter;
    }

    public final BaseAdapter<ItemTradeRecordBean, ItemItemSellOrderBinding> getOrderAdapter() {
        return this.orderAdapter;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setTitle("出售材料记录");
        getMBinding().btn.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ItemSellRecordActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity$$ExternalSyntheticLambda1
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                ItemSellRecordActivity.init$lambda$1(this.f$0, refreshLayout);
            }
        });
        getMBinding().rv.setAdapter(this.adapter);
        this.adapter.addChildClickViewIds(R.id.tv_offset, R.id.tv_adjust);
        this.adapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ItemSellRecordActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        this.adapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                ItemSellRecordActivity.init$lambda$3(this.f$0);
            }
        });
        this.orderAdapter.addChildClickViewIds(R.id.tv_cancel, R.id.tv_go, R.id.ll_role, R.id.ll_role_id);
        this.orderAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ItemSellRecordActivity.init$lambda$4(this.f$0, baseQuickAdapter, view, i);
            }
        });
        this.orderAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                ItemSellRecordActivity.init$lambda$5(this.f$0);
            }
        });
        initTab();
        getMBinding().srl.autoRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(ItemSellRecordActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivityForResult(new Intent(this$0, (Class<?>) ItemSellActivity.class), 777);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(ItemSellRecordActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.getNewData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(ItemSellRecordActivity this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.tv_adjust) {
            this$0.adjust(i);
        } else {
            if (id != R.id.tv_offset) {
                return;
            }
            this$0.offset(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(ItemSellRecordActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4(ItemSellRecordActivity this$0, BaseQuickAdapter baseQuickAdapter, View v, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.ll_role /* 2131362275 */:
                Util.copy(this$0, this$0.orderAdapter.getItem(i).getRoleName());
                break;
            case R.id.ll_role_id /* 2131362276 */:
                Util.copy(this$0, this$0.orderAdapter.getItem(i).getRoleId());
                break;
            case R.id.tv_cancel /* 2131362667 */:
                this$0.cancel(i);
                break;
            case R.id.tv_go /* 2131362715 */:
                this$0.go(i);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$5(ItemSellRecordActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getOrder();
    }

    public final void getNewData() {
        this.page = 1;
        if (getMBinding().tab.getSelectedTabPosition() == 0) {
            this.adapter.setNewInstance(null);
            getMBinding().rv.setAdapter(this.adapter);
            BaseAdapter.setMyEmptyView$default(this.adapter, null, 1, null);
            getData();
            return;
        }
        this.orderAdapter.setNewInstance(null);
        getMBinding().rv.setAdapter(this.orderAdapter);
        BaseAdapter.setMyEmptyView$default(this.orderAdapter, null, 1, null);
        getOrder();
    }

    public final void initTab() {
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        getMBinding().tab.addTab(getMBinding().tab.newTab());
        TabLayout tab = getMBinding().tab;
        Intrinsics.checkNotNullExpressionValue(tab, "tab");
        initTab(tab, this.title, 14.0f, 14.0f, true);
        getMBinding().tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity.initTab.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab2) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab2) {
                Intrinsics.checkNotNullParameter(tab2, "tab");
                ItemSellRecordActivity.access$getMBinding(ItemSellRecordActivity.this).srl.autoRefresh();
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab2) {
                ItemSellRecordActivity.access$getMBinding(ItemSellRecordActivity.this).srl.autoRefresh();
            }
        });
    }

    public final void getData() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("page", String.valueOf(this.page));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new ItemSellRecordActivity$getData$$inlined$get$1("game/asset/sellAssetList", linkedHashMap, null, this, this), 3, null);
    }

    public final void getOrder() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("page", String.valueOf(this.page));
        linkedHashMap.put("status", String.valueOf(getMBinding().tab.getSelectedTabPosition()));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new ItemSellRecordActivity$getOrder$$inlined$get$1("game/asset/sellAssetOrder", linkedHashMap, null, this, this), 3, null);
    }

    public final void offset(final int position) {
        new ConfirmDialog(this).setTitle("下架商品").setTip("确定要下架该商品吗？").setBtnText("确认").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity.offset.1
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
                linkedHashMap.put("id", String.valueOf(ItemSellRecordActivity.this.getAdapter().getItem(position).getId()));
                NetUtil netUtil = NetUtil.INSTANCE;
                final ItemSellRecordActivity itemSellRecordActivity = ItemSellRecordActivity.this;
                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity.offset.1.1
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
                        itemSellRecordActivity.toast(it.getMsg());
                        itemSellRecordActivity.getNewData();
                    }
                };
                final ItemSellRecordActivity itemSellRecordActivity2 = ItemSellRecordActivity.this;
                NetUtil.post2$default(netUtil, "game/asset/deleteAsset", linkedHashMap, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity.offset.1.2
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
                        ItemSellRecordActivity.access$getMBinding(itemSellRecordActivity2).srl.finishRefresh(false);
                        itemSellRecordActivity2.netFail(it);
                    }
                }, null, 16, null);
            }
        }).show();
    }

    public final void adjust(final int position) {
        new QuickDialog(this, R.layout.dialog_item_adjust).setData(this.adapter.getItem(position)).setOnClickListener(R.id.tv_adjust, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity$$ExternalSyntheticLambda6
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                ItemSellRecordActivity.adjust$lambda$10(this.f$0, position, baseDialog, view);
            }
        }).setOnClickListener(R.id.tv_cancel, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity$$ExternalSyntheticLambda7
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                baseDialog.dismiss();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void adjust$lambda$10(final ItemSellRecordActivity this$0, int i, final BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditText editText = (EditText) baseDialog.findViewById(R.id.et);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("id", String.valueOf(this$0.adapter.getItem(i).getId()));
        Intrinsics.checkNotNull(editText);
        linkedHashMap.put("num", editText.getText().toString());
        NetUtil.post2$default(NetUtil.INSTANCE, "game/asset/updateAssetNum", linkedHashMap, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity$adjust$1$1
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
                this.this$0.toast(it.getMsg());
                this.this$0.getNewData();
                baseDialog.dismiss();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity$adjust$1$2
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
                ItemSellRecordActivity.access$getMBinding(this.this$0).srl.finishRefresh(false);
                this.this$0.netFail(it);
            }
        }, null, 16, null);
    }

    public final void go(final int position) {
        new ConfirmDialog(this).setTitle("确认发货").setTip("请确认该商品已经发货！").setBtnText("确认").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity.go.1
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
                linkedHashMap.put("id", String.valueOf(ItemSellRecordActivity.this.getOrderAdapter().getItem(position).getId()));
                NetUtil netUtil = NetUtil.INSTANCE;
                final ItemSellRecordActivity itemSellRecordActivity = ItemSellRecordActivity.this;
                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity.go.1.1
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
                        itemSellRecordActivity.toast(it.getMsg());
                        itemSellRecordActivity.getNewData();
                    }
                };
                final ItemSellRecordActivity itemSellRecordActivity2 = ItemSellRecordActivity.this;
                NetUtil.post2$default(netUtil, "game/asset/sellAssetDeliver", linkedHashMap, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity.go.1.2
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
                        ItemSellRecordActivity.access$getMBinding(itemSellRecordActivity2).srl.finishRefresh(false);
                        itemSellRecordActivity2.netFail(it);
                    }
                }, null, 16, null);
            }
        }).show();
    }

    public final void cancel(final int position) {
        new ConfirmDialog(this).setTitle("取消发货").setTip("取消发货后金额将自动返回给用户！").setBtnText("确认").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity.cancel.1
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
                linkedHashMap.put("id", String.valueOf(ItemSellRecordActivity.this.getOrderAdapter().getItem(position).getId()));
                NetUtil netUtil = NetUtil.INSTANCE;
                final ItemSellRecordActivity itemSellRecordActivity = ItemSellRecordActivity.this;
                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity.cancel.1.1
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
                        itemSellRecordActivity.toast(it.getMsg());
                        itemSellRecordActivity.getNewData();
                    }
                };
                final ItemSellRecordActivity itemSellRecordActivity2 = ItemSellRecordActivity.this;
                NetUtil.post2$default(netUtil, "game/asset/cancelAssetOrderDeliver", linkedHashMap, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemSellRecordActivity.cancel.1.2
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
                        ItemSellRecordActivity.access$getMBinding(itemSellRecordActivity2).srl.finishRefresh(false);
                        itemSellRecordActivity2.netFail(it);
                    }
                }, null, 16, null);
            }
        }).show();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 777) {
            getNewData();
        }
    }
}
