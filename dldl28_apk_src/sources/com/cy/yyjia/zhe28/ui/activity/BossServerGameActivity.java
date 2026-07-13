package com.cy.yyjia.zhe28.ui.activity;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemHomeGame2Binding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: BossServerGameActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0013H\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/BossServerGameActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemHomeGame2Binding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BossServerGameActivity extends BaseActivity<ActivityRvBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;

    public BossServerGameActivity() {
        super(R.layout.activity_rv, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemHomeGame2Binding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.BossServerGameActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ItemHomeGame2Binding> invoke() {
                return new BaseAdapter<>(R.layout.item_home_game2, null, 2, null);
            }
        });
        this.page = 1;
    }

    public final BaseAdapter<GameBean, ItemHomeGame2Binding> getAdapter() {
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
        getMBinding().navigation.setTitle("老板服");
        getMBinding().rv.setAdapter(getAdapter());
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BossServerGameActivity$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                BossServerGameActivity.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BossServerGameActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                BossServerGameActivity.init$lambda$1(this.f$0);
            }
        });
        getAdapter().setMyEmptyView("");
        getMBinding().srl.autoRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(BossServerGameActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(BossServerGameActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    public final void getData() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("page", String.valueOf(this.page));
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new BossServerGameActivity$getData$$inlined$get$1("topic/bossTopicGameList", linkedHashMap, null, this, this), 3, null);
    }
}
