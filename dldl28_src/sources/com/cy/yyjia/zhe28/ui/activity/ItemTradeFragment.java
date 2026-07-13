package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentItemTradeBinding;
import com.cy.yyjia.zhe28.databinding.ItemItemTradeBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.ItemTradeBean;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Util;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.volcengine.common.contant.CommonConstants;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: ItemTradeFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011J\u0006\u0010\u0014\u001a\u00020\u0011J\b\u0010\u0015\u001a\u00020\u0011H\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/ItemTradeFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentItemTradeBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/ItemTradeBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemItemTradeBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "", "getNewData", "getSearchGame", "getServers", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ItemTradeFragment extends BaseFragment<FragmentItemTradeBinding> {
    public static final int $stable = 8;
    private final BaseAdapter<ItemTradeBean, ItemItemTradeBinding> adapter;
    private int page;

    public ItemTradeFragment() {
        super(R.layout.fragment_item_trade);
        this.page = 1;
        this.adapter = new BaseAdapter<>(R.layout.item_item_trade, null, 2, null);
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final BaseAdapter<ItemTradeBean, ItemItemTradeBinding> getAdapter() {
        return this.adapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(ItemTradeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Util.toService(this$0.getMContext());
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getMBinding().tvService.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ItemTradeFragment.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().llFilter.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ItemTradeFragment.init$lambda$1(this.f$0, view);
            }
        });
        getMBinding().tvServer.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ItemTradeFragment.init$lambda$2(this.f$0, view);
            }
        });
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeFragment$$ExternalSyntheticLambda3
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                ItemTradeFragment.init$lambda$3(this.f$0, refreshLayout);
            }
        });
        getMBinding().rv.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeFragment$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ItemTradeFragment.init$lambda$4(this.f$0, baseQuickAdapter, view, i);
            }
        });
        this.adapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ItemTradeFragment$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                ItemTradeFragment.init$lambda$5(this.f$0);
            }
        });
        getMBinding().srl.autoRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(ItemTradeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getSearchGame();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(ItemTradeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getServers();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(ItemTradeFragment this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.getNewData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4(ItemTradeFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Intent intent = new Intent(this$0.getMContext(), (Class<?>) ItemTradeDetailActivity.class);
        intent.putExtra("id", this$0.adapter.getItem(i).getId());
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$5(ItemTradeFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    public final void getNewData() {
        this.page = 1;
        this.adapter.setNewInstance(null);
        getData();
    }

    public final void getData() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("page", String.valueOf(this.page));
        GameBean game = getMBinding().getGame();
        if (game != null) {
            linkedHashMap.put(CommonConstants.key_gameId, String.valueOf(game.getId()));
        }
        String server = getMBinding().getServer();
        if (server != null) {
            linkedHashMap.put("serviceCode", server);
        }
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new ItemTradeFragment$getData$$inlined$get$1("game/asset/list", linkedHashMap, null, this, this), 3, null);
    }

    public final void getSearchGame() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("isSell", "0");
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new ItemTradeFragment$getSearchGame$$inlined$get$1("game/asset/gameList", linkedHashMap, null, this, this), 3, null);
    }

    public final void getServers() {
        if (getMBinding().getGame() == null) {
            getSearchGame();
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        GameBean game = getMBinding().getGame();
        Intrinsics.checkNotNull(game);
        linkedHashMap.put(CommonConstants.key_gameId, String.valueOf(game.getId()));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new ItemTradeFragment$getServers$$inlined$get$1("game/asset/serviceList", linkedHashMap, null, this, this), 3, null);
    }
}
