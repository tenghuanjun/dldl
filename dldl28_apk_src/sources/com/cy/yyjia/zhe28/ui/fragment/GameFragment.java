package com.cy.yyjia.zhe28.ui.fragment;

import android.os.Bundle;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentGameBinding;
import com.cy.yyjia.zhe28.databinding.ItemHomeLargeBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.ui.fragment.GameFragment;
import com.cy.yyjia.zhe28.util.Repository;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: GameFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u0013\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u0019H\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/GameFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentGameBinding;", "()V", "gameAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemHomeLargeBinding;", "getGameAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "gameAdapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "type", "getType", "setType", "typeId", "getTypeId", "setTypeId", "getBanner", "", "getData", "init", "Companion", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameFragment extends BaseFragment<FragmentGameBinding> {

    /* JADX INFO: renamed from: gameAdapter$delegate, reason: from kotlin metadata */
    private final Lazy gameAdapter;
    private int page;
    private int type;
    private int typeId;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public GameFragment() {
        super(R.layout.fragment_game);
        this.page = 1;
        this.gameAdapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemHomeLargeBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameFragment$gameAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ItemHomeLargeBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_home_large, null, 2, null);
            }
        });
    }

    public static final /* synthetic */ FragmentGameBinding access$getMBinding(GameFragment gameFragment) {
        return gameFragment.getMBinding();
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final int getTypeId() {
        return this.typeId;
    }

    public final void setTypeId(int i) {
        this.typeId = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BaseAdapter<GameBean, ItemHomeLargeBinding> getGameAdapter() {
        return (BaseAdapter) this.gameAdapter.getValue();
    }

    /* JADX INFO: compiled from: GameFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/GameFragment$Companion;", "", "()V", "newInstance", "Lcom/cy/yyjia/zhe28/ui/fragment/GameFragment;", "type", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GameFragment newInstance(int type) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", type);
            GameFragment gameFragment = new GameFragment();
            gameFragment.setArguments(bundle);
            return gameFragment;
        }
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        Bundle arguments = getArguments();
        this.type = arguments != null ? arguments.getInt("type", 0) : 0;
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameFragment$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                GameFragment.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getMBinding().rvGame.setAdapter(getGameAdapter());
        getGameAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                GameFragment.init$lambda$1(this.f$0);
            }
        });
        getType();
        getBanner();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(GameFragment this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getGameAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(GameFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.GameFragment$getType$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: GameFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class C11431 extends Lambda implements Function1<List<TypeBean>, Unit> {
        C11431() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<TypeBean> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(List<TypeBean> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.get(0).setSelected(true);
            GameFragment.this.setTypeId(it.get(0).getId());
            final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_game_type, it);
            GameFragment.access$getMBinding(GameFragment.this).rvType.setAdapter(baseAdapter);
            final GameFragment gameFragment = GameFragment.this;
            baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameFragment$getType$1$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    GameFragment.C11431.invoke$lambda$0(baseAdapter, gameFragment, baseQuickAdapter, view, i);
                }
            });
            GameFragment.this.getData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void invoke$lambda$0(BaseAdapter adapter, GameFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(adapter, "$adapter");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            Iterator it = adapter.getData().iterator();
            while (it.hasNext()) {
                ((TypeBean) it.next()).setSelected(false);
            }
            ((TypeBean) adapter.getItem(i)).setSelected(true);
            this$0.setTypeId(((TypeBean) adapter.getItem(i)).getId());
            GameFragment.access$getMBinding(this$0).srl.autoRefresh();
        }
    }

    private final void getType() {
        Repository.INSTANCE.getHomeGameType(this.type, new C11431(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameFragment.getType.2
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
                GameFragment.this.netFail(it);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getData() {
        Repository.INSTANCE.getHomeGame(this.type, this.page, this.typeId, new Function1<PageBean<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameFragment.getData.1
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
                GameFragment.access$getMBinding(GameFragment.this).srl.finishRefresh();
                if (GameFragment.this.getPage() == 1) {
                    GameFragment.this.getGameAdapter().setNewInstance(it.getList());
                } else {
                    GameFragment.this.getGameAdapter().addData((Collection) it.getList());
                }
                GameFragment gameFragment = GameFragment.this;
                gameFragment.setPage(gameFragment.getPage() + 1);
                gameFragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(GameFragment.this.getGameAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    GameFragment.this.getGameAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameFragment.getData.2
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
                GameFragment.access$getMBinding(GameFragment.this).srl.finishRefresh(false);
                GameFragment.this.getGameAdapter().getLoadMoreModule().loadMoreFail();
                GameFragment.this.netFail(it);
            }
        });
    }

    private final void getBanner() {
        String str;
        int i = this.type;
        if (i == 2) {
            str = "01zhe";
        } else if (i == 3) {
            str = "1zhe";
        } else if (i == 4) {
            str = "2zhe";
        } else {
            str = "hot";
        }
        Repository.INSTANCE.getHomeGameBanner(str, new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameFragment.getBanner.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                invoke2(str2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                GameFragment.access$getMBinding(GameFragment.this).setBanner(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.GameFragment.getBanner.2
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
                GameFragment.this.netFail(it);
            }
        });
    }
}
