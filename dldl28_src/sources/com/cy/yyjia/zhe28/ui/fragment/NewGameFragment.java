package com.cy.yyjia.zhe28.ui.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentHomeScheduleBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.ui.adapter.HomeGameAdapter;
import com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment;
import com.cy.yyjia.zhe28.util.Repository;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NewGameFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0011\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/NewGameFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentHomeScheduleBinding;", "()V", "listAdapter", "Lcom/cy/yyjia/zhe28/ui/adapter/HomeGameAdapter;", "getListAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/HomeGameAdapter;", "listAdapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "type", "getType", "type$delegate", "getData", "", "init", "Companion", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class NewGameFragment extends BaseFragment<FragmentHomeScheduleBinding> {

    /* JADX INFO: renamed from: listAdapter$delegate, reason: from kotlin metadata */
    private final Lazy listAdapter;
    private int page;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public static final /* synthetic */ FragmentHomeScheduleBinding access$getMBinding(NewGameFragment newGameFragment) {
        return newGameFragment.getMBinding();
    }

    public NewGameFragment() {
        super(R.layout.fragment_home_schedule);
        this.listAdapter = LazyKt.lazy(new Function0<HomeGameAdapter>() { // from class: com.cy.yyjia.zhe28.ui.fragment.NewGameFragment$listAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final HomeGameAdapter invoke() {
                return new HomeGameAdapter();
            }
        });
        this.page = 1;
        this.type = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.fragment.NewGameFragment$type$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                Bundle arguments = this.this$0.getArguments();
                return Integer.valueOf(arguments != null ? arguments.getInt("type", 0) : 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeGameAdapter getListAdapter() {
        return (HomeGameAdapter) this.listAdapter.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final int getType() {
        return ((Number) this.type.getValue()).intValue();
    }

    /* JADX INFO: compiled from: NewGameFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/NewGameFragment$Companion;", "", "()V", "newInstance", "Lcom/cy/yyjia/zhe28/ui/fragment/HomeNewFragment$ChildFragment;", "type", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HomeNewFragment.ChildFragment newInstance(int type) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", type);
            HomeNewFragment.ChildFragment childFragment = new HomeNewFragment.ChildFragment();
            childFragment.setArguments(bundle);
            return childFragment;
        }
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getListAdapter().setType(getType());
        getMBinding().rv.setAdapter(getListAdapter());
        getListAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.NewGameFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                NewGameFragment.init$lambda$0(this.f$0);
            }
        });
        getMBinding().rv.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.NewGameFragment.init.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrolled(recyclerView, dx, dy);
                if (NewGameFragment.this.getListAdapter().getData().size() == 0) {
                    return;
                }
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) NewGameFragment.access$getMBinding(NewGameFragment.this).rv.getLayoutManager();
                Intrinsics.checkNotNull(linearLayoutManager);
                int iFindFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (NewGameFragment.this.getListAdapter().getItem(iFindFirstVisibleItemPosition).getId() != -1) {
                    NewGameFragment.access$getMBinding(NewGameFragment.this).setTime(NewGameFragment.this.getListAdapter().getItem(iFindFirstVisibleItemPosition).getHeadStr());
                }
            }
        });
        getListAdapter().addChildClickViewIds(R.id.btn);
        getListAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.NewGameFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                NewGameFragment.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getListAdapter().setEmptyView(R.layout.layout_empty);
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(NewGameFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(final NewGameFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.getListAdapter().getItem(i).getIsOrder()) {
            return;
        }
        Repository.INSTANCE.orderGame(this$0.getListAdapter().getItem(i).getId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.NewGameFragment$init$3$1
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
                this.this$0.getListAdapter().getItem(i).setOrder(true);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.NewGameFragment$init$3$2
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
                this.this$0.netFail(it);
            }
        });
    }

    private final void getData() {
        Repository.getNewGame$default(Repository.INSTANCE, getType(), this.page, new Function1<PageBean<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.NewGameFragment.getData.1
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
                List<GameBean> data = NewGameFragment.this.getListAdapter().formatData(it.getList());
                if (NewGameFragment.this.getPage() == 1) {
                    NewGameFragment.this.getListAdapter().setNewInstance(data);
                } else {
                    NewGameFragment.this.getListAdapter().addData((Collection) data);
                }
                NewGameFragment newGameFragment = NewGameFragment.this;
                newGameFragment.setPage(newGameFragment.getPage() + 1);
                newGameFragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(NewGameFragment.this.getListAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    NewGameFragment.this.getListAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.NewGameFragment.getData.2
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
                NewGameFragment.this.netFail(it);
                NewGameFragment.this.getListAdapter().getLoadMoreModule().loadMoreFail();
            }
        }, null, 16, null);
    }
}
