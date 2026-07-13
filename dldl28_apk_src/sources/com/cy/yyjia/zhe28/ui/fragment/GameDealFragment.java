package com.cy.yyjia.zhe28.ui.fragment;

import androidx.lifecycle.ViewModelProvider;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemGameDealBinding;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.domain.GameDetailBean;
import com.cy.yyjia.zhe28.domain.GameViewModel;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameDealFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0018H\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/GameDealFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/DealBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGameDealBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "vm", "Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/GameViewModel;", "vm$delegate", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameDealFragment extends BaseFragment<FragmentRvBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public GameDealFragment() {
        super(R.layout.fragment_rv);
        this.vm = LazyKt.lazy(new Function0<GameViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameDealFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final GameViewModel invoke() {
                return (GameViewModel) new ViewModelProvider(this.this$0.getMContext()).get(GameViewModel.class);
            }
        });
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<DealBean, ItemGameDealBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameDealFragment$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<DealBean, ItemGameDealBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_game_deal, null, 2, null);
            }
        });
        this.page = 1;
    }

    public final GameViewModel getVm() {
        return (GameViewModel) this.vm.getValue();
    }

    public final BaseAdapter<DealBean, ItemGameDealBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getVm().getData().observe(this, new GameDealFragment$sam$androidx_lifecycle_Observer$0(new Function1<GameDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameDealFragment.init.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GameDetailBean gameDetailBean) {
                invoke2(gameDetailBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GameDetailBean gameDetailBean) {
                GameDealFragment.this.getData();
            }
        }));
        getMBinding().srl.setEnabled(false);
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().setMyEmptyView("deal");
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameDealFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                GameDealFragment.init$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(GameDealFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    public final void getData() {
        GameDetailBean value = getVm().getData().getValue();
        Intrinsics.checkNotNull(value);
        Repository.INSTANCE.getGameDealList(this.page, value.getId(), new Function1<PageBean<DealBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameDealFragment$getData$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<DealBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<DealBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                for (DealBean dealBean : it.getList()) {
                    GameDetailBean value2 = this.this$0.getVm().getData().getValue();
                    Intrinsics.checkNotNull(value2);
                    dealBean.setGame(value2);
                }
                if (this.this$0.getPage() == 1) {
                    this.this$0.getAdapter().setNewInstance(it.getList());
                } else {
                    this.this$0.getAdapter().addData(it.getList());
                }
                GameDealFragment gameDealFragment = this.this$0;
                gameDealFragment.setPage(gameDealFragment.getPage() + 1);
                gameDealFragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(this.this$0.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    this.this$0.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameDealFragment$getData$1$2
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
                this.this$0.getAdapter().getLoadMoreModule().loadMoreFail();
                GameDealFragment gameDealFragment = this.this$0;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                gameDealFragment.log(localizedMessage);
            }
        });
    }
}
