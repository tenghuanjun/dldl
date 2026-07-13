package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemGameUpdate2Binding;
import com.cy.yyjia.zhe28.domain.GameHistoryBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.util.Repository;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameUpdateHistoryActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/GameUpdateHistoryActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameHistoryBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGameUpdate2Binding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "id", "", "getId", "()I", "id$delegate", "page", "getPage", "setPage", "(I)V", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameUpdateHistoryActivity extends BaseActivity<ActivityRvBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final Lazy id;
    private int page;

    public GameUpdateHistoryActivity() {
        super(R.layout.activity_rv, 0, 2, null);
        this.id = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameUpdateHistoryActivity$id$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("gid", 0));
            }
        });
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<GameHistoryBean, ItemGameUpdate2Binding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameUpdateHistoryActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameHistoryBean, ItemGameUpdate2Binding> invoke() {
                return new BaseAdapter<>(R.layout.item_game_update2, new Function3<BaseDataBindingHolder<ItemGameUpdate2Binding>, Integer, GameHistoryBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameUpdateHistoryActivity$adapter$2.1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemGameUpdate2Binding> baseDataBindingHolder, Integer num, GameHistoryBean gameHistoryBean) {
                        invoke(baseDataBindingHolder, num.intValue(), gameHistoryBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemGameUpdate2Binding> h, int i, GameHistoryBean gameHistoryBean) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ViewDataBinding dataBinding = h.getDataBinding();
                        Intrinsics.checkNotNull(dataBinding);
                        ((ItemGameUpdate2Binding) dataBinding).setTop(i == 0);
                    }
                });
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ ActivityRvBinding access$getMBinding(GameUpdateHistoryActivity gameUpdateHistoryActivity) {
        return gameUpdateHistoryActivity.getMBinding();
    }

    public final int getId() {
        return ((Number) this.id.getValue()).intValue();
    }

    public final BaseAdapter<GameHistoryBean, ItemGameUpdate2Binding> getAdapter() {
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
        getMBinding().navigation.setTitle("更新日志");
        getMBinding().rv.setAdapter(getAdapter());
        getMBinding().srl.setBackgroundColor(getResources().getColor(R.color.color_white));
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameUpdateHistoryActivity$$ExternalSyntheticLambda1
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                GameUpdateHistoryActivity.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameUpdateHistoryActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                GameUpdateHistoryActivity.init$lambda$1(this.f$0);
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameUpdateHistoryActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameUpdateHistoryActivity.init$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        BaseAdapter.setMyEmptyView$default(getAdapter(), null, 1, null);
        getMBinding().srl.autoRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(GameUpdateHistoryActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(GameUpdateHistoryActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(GameUpdateHistoryActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        new QuickDialog(this$0.getMContext(), R.layout.dialog_game_update_detail).setData(this$0.getAdapter().getItem(i)).setOnClickListener(R.id.tv_close, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameUpdateHistoryActivity$$ExternalSyntheticLambda0
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view2) {
                baseDialog.dismiss();
            }
        }).show();
    }

    public final void getData() {
        Repository.INSTANCE.getGameUpdateHistory(this.page, getId(), new Function1<PageBean<GameHistoryBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameUpdateHistoryActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<GameHistoryBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<GameHistoryBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                GameUpdateHistoryActivity.access$getMBinding(GameUpdateHistoryActivity.this).srl.finishRefresh();
                if (GameUpdateHistoryActivity.this.getPage() == 1) {
                    GameUpdateHistoryActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    GameUpdateHistoryActivity.this.getAdapter().addData(it.getList());
                }
                GameUpdateHistoryActivity gameUpdateHistoryActivity = GameUpdateHistoryActivity.this;
                gameUpdateHistoryActivity.setPage(gameUpdateHistoryActivity.getPage() + 1);
                gameUpdateHistoryActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(GameUpdateHistoryActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    GameUpdateHistoryActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameUpdateHistoryActivity.getData.2
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
                GameUpdateHistoryActivity.access$getMBinding(GameUpdateHistoryActivity.this).srl.finishRefresh(false);
                GameUpdateHistoryActivity.this.netFail(it);
                GameUpdateHistoryActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
            }
        });
    }
}
