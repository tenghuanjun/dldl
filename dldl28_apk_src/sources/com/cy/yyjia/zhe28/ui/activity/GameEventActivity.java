package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemGameEventBinding;
import com.cy.yyjia.zhe28.domain.EventBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameEventActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u001bH\u0016J\u000e\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0006R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001d\u0010\f\u001a\u0004\u0018\u00010\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0011\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0016\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0019¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/GameEventActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/EventBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGameEventBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "gamename", "", "getGamename", "()Ljava/lang/String;", "gamename$delegate", "gid", "", "getGid", "()I", "gid$delegate", "page", "getPage", "setPage", "(I)V", "getData", "", "init", "toApply", NotificationCompat.CATEGORY_EVENT, "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameEventActivity extends BaseActivity<ActivityRvBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: gamename$delegate, reason: from kotlin metadata */
    private final Lazy gamename;

    /* JADX INFO: renamed from: gid$delegate, reason: from kotlin metadata */
    private final Lazy gid;
    private int page;

    public GameEventActivity() {
        super(R.layout.activity_rv, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<EventBean, ItemGameEventBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameEventActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<EventBean, ItemGameEventBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_game_event, null, 2, null);
            }
        });
        this.gid = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameEventActivity$gid$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("gid", 0));
            }
        });
        this.gamename = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameEventActivity$gamename$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getIntent().getStringExtra("gamename");
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ ActivityRvBinding access$getMBinding(GameEventActivity gameEventActivity) {
        return gameEventActivity.getMBinding();
    }

    public final BaseAdapter<EventBean, ItemGameEventBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getGid() {
        return ((Number) this.gid.getValue()).intValue();
    }

    public final String getGamename() {
        return (String) this.gamename.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameEventActivity$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                GameEventActivity.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getMBinding().navigation.setTitle("活动公告");
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameEventActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameEventActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().addChildClickViewIds(R.id.btn);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameEventActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameEventActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameEventActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                GameEventActivity.init$lambda$3(this.f$0);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(GameEventActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(GameEventActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Intent intent = new Intent(this$0, (Class<?>) EventDetailActivity.class);
        intent.putExtra("newsId", this$0.getAdapter().getItem(i).getId());
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(GameEventActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.toApply(this$0.getAdapter().getItem(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(GameEventActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    public final void getData() {
        Repository.INSTANCE.getGameEventList(this.page, getGid(), new Function1<PageBean<EventBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameEventActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<EventBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<EventBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                GameEventActivity.access$getMBinding(GameEventActivity.this).srl.finishRefresh();
                if (GameEventActivity.this.getPage() == 1) {
                    GameEventActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    GameEventActivity.this.getAdapter().addData(it.getList());
                }
                GameEventActivity gameEventActivity = GameEventActivity.this;
                gameEventActivity.setPage(gameEventActivity.getPage() + 1);
                gameEventActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(GameEventActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    GameEventActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameEventActivity.getData.2
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
                GameEventActivity.access$getMBinding(GameEventActivity.this).srl.finishRefresh(false);
                GameEventActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                GameEventActivity.this.netFail(it);
            }
        });
    }

    public final void toApply(EventBean event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getSapplyType(), "2")) {
            Util.openWeb(getMContext(), event.getTitle(), NetUtil.BASE_URL3 + "dist/apply-for-rewards?gid=" + getGid() + "&gamename=" + getGamename() + "&id=" + event.getId() + "&title=" + event.getTitle(), false);
        }
    }
}
