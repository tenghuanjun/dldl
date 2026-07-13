package com.cy.yyjia.zhe28.ui.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentHomeGameBinding;
import com.cy.yyjia.zhe28.databinding.ItemHallGameBinding;
import com.cy.yyjia.zhe28.databinding.ItemHomeRankBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.MainTabBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.ui.activity.MessageActivity;
import com.cy.yyjia.zhe28.ui.activity.QiandaoActivity;
import com.cy.yyjia.zhe28.ui.activity.SearchActivity;
import com.cy.yyjia.zhe28.ui.adapter.HotSearchAdapter;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.google.android.material.appbar.AppBarLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeGameFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 02\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00010B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010(\u001a\u00020)J\b\u0010*\u001a\u00020)H\u0016J\u0006\u0010+\u001a\u00020)J\u000e\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020.J\b\u0010/\u001a\u00020)H\u0016R)\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\b\u0001\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR'\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\r0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u000e\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u001b\u0010\u0019\u001a\u00020\u00118FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000b\u001a\u0004\b\u001a\u0010\u0013R'\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001d0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u000b\u001a\u0004\b\u001e\u0010\tR\u001a\u0010 \u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0013\"\u0004\b\"\u0010\u0015R\u001b\u0010#\u001a\u00020$8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u000b\u001a\u0004\b%\u0010&¨\u00061"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/HomeGameFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentHomeGameBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Landroidx/databinding/ViewDataBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "gameAdapter", "Lcom/cy/yyjia/zhe28/databinding/ItemHallGameBinding;", "getGameAdapter", "gameAdapter$delegate", "height", "", "getHeight", "()I", "setHeight", "(I)V", "page", "getPage", "setPage", ImageSelector.POSITION, "getPosition", "position$delegate", "rankAdapter", "Lcom/cy/yyjia/zhe28/databinding/ItemHomeRankBinding;", "getRankAdapter", "rankAdapter$delegate", "typeId", "getTypeId", "setTypeId", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "getData", "", "init", "initType", "onClick", "v", "Landroid/view/View;", "onResume", "Companion", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HomeGameFragment extends BaseFragment<FragmentHomeGameBinding> {

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: gameAdapter$delegate, reason: from kotlin metadata */
    private final Lazy gameAdapter;
    private int height;
    private int page;

    /* JADX INFO: renamed from: position$delegate, reason: from kotlin metadata */
    private final Lazy position;

    /* JADX INFO: renamed from: rankAdapter$delegate, reason: from kotlin metadata */
    private final Lazy rankAdapter;
    private int typeId;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public HomeGameFragment() {
        super(R.layout.fragment_home_game);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
            }
        });
        this.position = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$position$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.requireArguments().getInt(ImageSelector.POSITION, 0));
            }
        });
        this.gameAdapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemHallGameBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$gameAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ItemHallGameBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_hall_game, new Function3<BaseDataBindingHolder<ItemHallGameBinding>, Integer, GameBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$gameAdapter$2.1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemHallGameBinding> baseDataBindingHolder, Integer num, GameBean gameBean) {
                        invoke(baseDataBindingHolder, num.intValue(), gameBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemHallGameBinding> h, int i, GameBean gameBean) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemHallGameBinding itemHallGameBinding = (ItemHallGameBinding) h.getDataBinding();
                        if (itemHallGameBinding == null) {
                            return;
                        }
                        itemHallGameBinding.setPosition(4);
                    }
                });
            }
        });
        this.rankAdapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemHomeRankBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$rankAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ItemHomeRankBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_home_rank, new Function3<BaseDataBindingHolder<ItemHomeRankBinding>, Integer, GameBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$rankAdapter$2.1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemHomeRankBinding> baseDataBindingHolder, Integer num, GameBean gameBean) {
                        invoke(baseDataBindingHolder, num.intValue(), gameBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemHomeRankBinding> h, int i, GameBean gameBean) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemHomeRankBinding itemHomeRankBinding = (ItemHomeRankBinding) h.getDataBinding();
                        if (itemHomeRankBinding == null) {
                            return;
                        }
                        itemHomeRankBinding.setPosition(h.getLayoutPosition());
                    }
                });
            }
        });
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ? extends ViewDataBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$adapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ? extends ViewDataBinding> invoke() {
                List<MainTabBean> value = this.this$0.getVm().getHomeNav().getValue();
                Intrinsics.checkNotNull(value);
                return value.get(this.this$0.getPosition()).getNav_template() == 3 ? this.this$0.getGameAdapter() : this.this$0.getRankAdapter();
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ FragmentHomeGameBinding access$getMBinding(HomeGameFragment homeGameFragment) {
        return homeGameFragment.getMBinding();
    }

    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    public final int getPosition() {
        return ((Number) this.position.getValue()).intValue();
    }

    public final BaseAdapter<GameBean, ItemHallGameBinding> getGameAdapter() {
        return (BaseAdapter) this.gameAdapter.getValue();
    }

    public final BaseAdapter<GameBean, ItemHomeRankBinding> getRankAdapter() {
        return (BaseAdapter) this.rankAdapter.getValue();
    }

    public final BaseAdapter<GameBean, ? extends ViewDataBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getHeight() {
        return this.height;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    public final int getTypeId() {
        return this.typeId;
    }

    public final void setTypeId(int i) {
        this.typeId = i;
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    /* JADX INFO: compiled from: HomeGameFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/HomeGameFragment$Companion;", "", "()V", "newInstance", "Lcom/cy/yyjia/zhe28/ui/fragment/HomeGameFragment;", ImageSelector.POSITION, "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HomeGameFragment newInstance(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt(ImageSelector.POSITION, position);
            HomeGameFragment homeGameFragment = new HomeGameFragment();
            homeGameFragment.setArguments(bundle);
            return homeGameFragment;
        }
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        initType();
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$$ExternalSyntheticLambda1
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                HomeGameFragment.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getMBinding().rvGame.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                HomeGameFragment.init$lambda$1(this.f$0);
            }
        });
        getMBinding().srl.autoRefresh();
        getVm().getAction().observe(this, new HomeGameFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment.init.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke2(num);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Integer num) {
                if (num != null && num.intValue() == 4) {
                    HomeGameFragment.this.getVm().getAction().setValue(0);
                    RecyclerView.LayoutManager layoutManager = HomeGameFragment.access$getMBinding(HomeGameFragment.this).rvGame.getLayoutManager();
                    Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                    if (((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0) {
                        HomeGameFragment.access$getMBinding(HomeGameFragment.this).srl.autoRefresh();
                    } else {
                        HomeGameFragment.access$getMBinding(HomeGameFragment.this).rvGame.scrollToPosition(0);
                    }
                }
            }
        }));
        getMBinding().abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$$ExternalSyntheticLambda3
            @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
            public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                HomeGameFragment.init$lambda$2(this.f$0, appBarLayout, i);
            }
        });
        getMBinding().tvThunt.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        getMBinding().tvQiandao.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        getMBinding().tvSearch.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        getMBinding().vf.setAdapter(new HotSearchAdapter(getMContext()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(HomeGameFragment this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(HomeGameFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(HomeGameFragment this$0, AppBarLayout appBarLayout, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            this$0.height = Util.dpToPx(this$0.getMContext(), 50.0f);
            this$0.getVm().getHomeHeight().setValue(Integer.valueOf(this$0.height));
        } else {
            this$0.height = 0;
            this$0.getVm().getHomeHeight().setValue(Integer.valueOf(this$0.height));
        }
    }

    public final void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.tv_message /* 2131362725 */:
                Util.skipWithLogin(getMContext(), MessageActivity.class);
                break;
            case R.id.tv_qiandao /* 2131362755 */:
                Util.skipWithLogin(getMContext(), QiandaoActivity.class);
                break;
            case R.id.tv_search /* 2131362768 */:
                Util.skip(getMContext(), SearchActivity.class);
                break;
            case R.id.tv_thunt /* 2131362789 */:
                Util.openWebWithLogin(getMContext(), "夺宝", NetUtil.BASE_URL1 + "thunt/index");
                break;
        }
    }

    public final void initType() {
        List<MainTabBean> value = getVm().getHomeNav().getValue();
        Intrinsics.checkNotNull(value);
        List<TypeBean> cates = value.get(getPosition()).getCates();
        this.typeId = cates.get(0).getId();
        int size = cates.size();
        int i = 0;
        while (i < size) {
            cates.get(i).setSelected(i == 0);
            i++;
        }
        getMBinding().f458tv.setText(cates.get(0).getTips());
        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_game_type, cates);
        getMBinding().rvType.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                HomeGameFragment.initType$lambda$3(this.f$0, baseAdapter, baseQuickAdapter, view, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void initType$lambda$3(HomeGameFragment this$0, BaseAdapter adapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "$adapter");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.checkClick(1)) {
            return;
        }
        Iterator it = adapter.getData().iterator();
        while (it.hasNext()) {
            ((TypeBean) it.next()).setSelected(false);
        }
        ((TypeBean) adapter.getItem(i)).setSelected(true);
        this$0.typeId = ((TypeBean) adapter.getItem(i)).getId();
        this$0.getMBinding().f458tv.setText(((TypeBean) adapter.getItem(i)).getTips());
        this$0.getMBinding().srl.autoRefresh();
    }

    public final void getData() {
        Repository.INSTANCE.getHomeGame(this.page, this.typeId, new Function1<PageBean<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment.getData.1
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
                HomeGameFragment.access$getMBinding(HomeGameFragment.this).srl.finishRefresh();
                if (HomeGameFragment.this.getPage() == 1) {
                    HomeGameFragment.this.getAdapter().setNewInstance(it.getList());
                } else {
                    HomeGameFragment.this.getAdapter().addData(it.getList());
                }
                HomeGameFragment homeGameFragment = HomeGameFragment.this;
                homeGameFragment.setPage(homeGameFragment.getPage() + 1);
                homeGameFragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(HomeGameFragment.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    HomeGameFragment.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeGameFragment.getData.2
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
                HomeGameFragment.this.getAdapter().getLoadMoreModule().loadMoreFail();
                HomeGameFragment.this.netFail(it);
                HomeGameFragment.access$getMBinding(HomeGameFragment.this).srl.finishRefresh(false);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Integer value = getVm().getMainTabPosition().getValue();
        if (value != null && value.intValue() == 0) {
            getVm().getHomeHeight().setValue(Integer.valueOf(this.height));
        }
    }
}
