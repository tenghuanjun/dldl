package com.cy.yyjia.zhe28.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentHomeBinding;
import com.cy.yyjia.zhe28.databinding.ItemFunBinding;
import com.cy.yyjia.zhe28.databinding.ItemHomeGame2Binding;
import com.cy.yyjia.zhe28.databinding.ItemHomeTypeBinding;
import com.cy.yyjia.zhe28.domain.BannerBean;
import com.cy.yyjia.zhe28.domain.FunBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.HomeBean;
import com.cy.yyjia.zhe28.domain.LoginChangeBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.ui.activity.MessageActivity;
import com.cy.yyjia.zhe28.ui.activity.MonthCardActivity;
import com.cy.yyjia.zhe28.ui.activity.QiandaoActivity;
import com.cy.yyjia.zhe28.ui.activity.SearchActivity;
import com.cy.yyjia.zhe28.ui.adapter.HomeTypeAdapter;
import com.cy.yyjia.zhe28.ui.adapter.HotSearchAdapter;
import com.cy.yyjia.zhe28.ui.fragment.HomeFragment;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.cy.yyjia.zhe28.view.BannerHolder;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: HomeFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010#\u001a\u00020$H\u0002J\u0006\u0010%\u001a\u00020$J\b\u0010&\u001a\u00020$H\u0016J\b\u0010'\u001a\u00020$H\u0002J\u0010\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020$H\u0016J\u0010\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020.H\u0007J\b\u0010/\u001a\u00020$H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001d\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0010R\u001b\u0010\u001e\u001a\u00020\u001f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\n\u001a\u0004\b \u0010!¨\u00060"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/HomeFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentHomeBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter3", "Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter;", "getAdapter3", "()Lcom/cy/yyjia/zhe28/ui/adapter/HomeTypeAdapter;", "adapter3$delegate", "Lkotlin/Lazy;", "gameAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemHomeGame2Binding;", "getGameAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "height", "", "getHeight", "()I", "setHeight", "(I)V", "page", "getPage", "setPage", "typeAdapter", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemHomeTypeBinding;", "getTypeAdapter", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "getData", "", "getGameList", "init", "initFun", "onClick", "v", "Landroid/view/View;", "onDestroy", "onLogin", "result", "Lcom/cy/yyjia/zhe28/domain/LoginChangeBean;", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HomeFragment extends BaseFragment<FragmentHomeBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter3$delegate, reason: from kotlin metadata */
    private final Lazy adapter3;
    private final BaseAdapter<GameBean, ItemHomeGame2Binding> gameAdapter;
    private int height;
    private int page;
    private final BaseAdapter<TypeBean, ItemHomeTypeBinding> typeAdapter;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public HomeFragment() {
        super(R.layout.fragment_home);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
            }
        });
        this.adapter3 = LazyKt.lazy(new Function0<HomeTypeAdapter>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$adapter3$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final HomeTypeAdapter invoke() {
                return new HomeTypeAdapter();
            }
        });
        this.typeAdapter = new BaseAdapter<>(R.layout.item_home_type, null, 2, null);
        this.gameAdapter = new BaseAdapter<>(R.layout.item_home_game2, null, 2, null);
        this.page = 1;
    }

    public static final /* synthetic */ FragmentHomeBinding access$getMBinding(HomeFragment homeFragment) {
        return homeFragment.getMBinding();
    }

    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    public final HomeTypeAdapter getAdapter3() {
        return (HomeTypeAdapter) this.adapter3.getValue();
    }

    public final BaseAdapter<TypeBean, ItemHomeTypeBinding> getTypeAdapter() {
        return this.typeAdapter;
    }

    public final BaseAdapter<GameBean, ItemHomeGame2Binding> getGameAdapter() {
        return this.gameAdapter;
    }

    public final int getHeight() {
        return this.height;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        log("开始初始化");
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        getMBinding().setIndex(true);
        HomeFragment homeFragment = this;
        getVm().getAction().observe(homeFragment, new HomeFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment.init.1
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
                if (num != null && num.intValue() == 1 && HomeFragment.this.isVisible()) {
                    if (HomeFragment.this.getHeight() == 0) {
                        HomeFragment.access$getMBinding(HomeFragment.this).srl.autoRefresh();
                    } else {
                        HomeFragment.access$getMBinding(HomeFragment.this).nsv.scrollTo(0, 0);
                    }
                    HomeFragment homeFragment2 = HomeFragment.this;
                    homeFragment2.log("看看top：" + HomeFragment.access$getMBinding(homeFragment2).nsv.getChildAt(0).getTop());
                }
            }
        }));
        getVm().getUser().observe(homeFragment, new HomeFragment$sam$androidx_lifecycle_Observer$0(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment.init.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserBean userBean) {
                invoke2(userBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserBean userBean) {
                HomeFragment.access$getMBinding(HomeFragment.this).tvMessage.setSelected(userBean.getUnread_msg());
            }
        }));
        getMBinding().abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$$ExternalSyntheticLambda0
            @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
            public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                HomeFragment.init$lambda$0(this.f$0, appBarLayout, i);
            }
        });
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$$ExternalSyntheticLambda1
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                HomeFragment.init$lambda$1(this.f$0, refreshLayout);
            }
        });
        initFun();
        getMBinding().rv1.setAdapter(new BaseAdapter(R.layout.item_home_large, null, 2, null));
        getMBinding().rv3.setAdapter(getAdapter3());
        getMBinding().rvTry.setAdapter(new BaseAdapter(R.layout.item_home_mini, null, 2, null));
        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_home_cate, null, 2, null);
        getMBinding().rvMore.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                HomeFragment.init$lambda$2(this.f$0, baseAdapter, baseQuickAdapter, view, i);
            }
        });
        HomeFragment homeFragment2 = this;
        getMBinding().tvMessage.setOnClickListener(homeFragment2);
        getMBinding().tvQiandao.setOnClickListener(homeFragment2);
        getMBinding().tvSearch.setOnClickListener(homeFragment2);
        getMBinding().tvMonthCard.setOnClickListener(homeFragment2);
        getMBinding().rv.setAdapter(this.typeAdapter);
        this.typeAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                HomeFragment.init$lambda$3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().srl2.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$$ExternalSyntheticLambda4
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                HomeFragment.init$lambda$4(this.f$0, refreshLayout);
            }
        });
        getMBinding().rvGame.setAdapter(this.gameAdapter);
        this.gameAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                HomeFragment.init$lambda$5(this.f$0);
            }
        });
        getMBinding().vf.setAdapter(new HotSearchAdapter(getMContext()));
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(HomeFragment this$0, AppBarLayout appBarLayout, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            this$0.height = Util.dpToPx(this$0.getMContext(), 50.0f);
            this$0.getVm().getHomeHeight().setValue(Integer.valueOf(this$0.height));
        } else {
            this$0.height = 0;
            this$0.getVm().getHomeHeight().setValue(Integer.valueOf(this$0.height));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(HomeFragment this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void init$lambda$2(HomeFragment this$0, BaseAdapter cateAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cateAdapter, "$cateAdapter");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.getVm().getCategory().postValue(Integer.valueOf(((HomeBean.CateBlock) cateAdapter.getItem(i)).getCateId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$3(HomeFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.getMBinding().setIndex(i == 0);
        for (TypeBean typeBean : this$0.typeAdapter.getData()) {
            typeBean.setSelected(this$0.typeAdapter.getItem(i).getId() == typeBean.getId());
        }
        if (this$0.getMBinding().getIndex()) {
            return;
        }
        this$0.getMBinding().srl2.autoRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4(HomeFragment this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.gameAdapter.setNewInstance(null);
        this$0.getGameList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$5(HomeFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getGameList();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.tv_message /* 2131362725 */:
                Util.skipWithLogin(getMContext(), MessageActivity.class);
                break;
            case R.id.tv_month_card /* 2131362728 */:
                startActivity(MonthCardActivity.class);
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

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$getData$1, reason: invalid class name */
    /* JADX INFO: compiled from: HomeFragment.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/HomeBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function1<HomeBean, Unit> {
        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(HomeBean homeBean) {
            invoke2(homeBean);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final HomeBean it) {
            Intrinsics.checkNotNullParameter(it, "it");
            TypeBean typeBean = new TypeBean(0, "推荐", "", "", "", "");
            typeBean.setSelected(true);
            it.getNav_category().add(0, typeBean);
            it.setHeavy_recommend(null);
            HomeFragment.access$getMBinding(HomeFragment.this).srl.finishRefresh();
            HomeFragment.access$getMBinding(HomeFragment.this).setData(it);
            HomeFragment.this.getVm().getHome().setValue(it);
            ConvenientBanner pages = HomeFragment.access$getMBinding(HomeFragment.this).banner.setPages(new CBViewHolderCreator() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$getData$1$$ExternalSyntheticLambda0
                @Override // com.bigkoo.convenientbanner.holder.CBViewHolderCreator
                public final Object createHolder() {
                    return HomeFragment.AnonymousClass1.invoke$lambda$0();
                }
            }, it.getTop_banner());
            List<BannerBean> top_banner = it.getTop_banner();
            Intrinsics.checkNotNull(top_banner);
            pages.setPointViewVisible(top_banner.size() > 1).setPageIndicator(new int[]{R.mipmap.ic_indicator, R.mipmap.ic_indicator_true}).startTurning(4000L);
            View root = HomeFragment.access$getMBinding(HomeFragment.this).getRoot();
            final HomeFragment homeFragment = HomeFragment.this;
            root.postDelayed(new Runnable() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$getData$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    HomeFragment.AnonymousClass1.invoke$lambda$1(homeFragment, it);
                }
            }, 500L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Object invoke$lambda$0() {
            return new BannerHolder();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1(HomeFragment this$0, HomeBean it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(it, "$it");
            this$0.getAdapter3().setNewInstance(it.getRecommend_list());
        }
    }

    private final void getData() {
        Repository.INSTANCE.getHomeData(new AnonymousClass1(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment.getData.2
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
                HomeFragment.access$getMBinding(HomeFragment.this).srl.finishRefresh(false);
                HomeFragment.this.netFail(it);
            }
        });
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final void getGameList() {
        int id;
        Iterator<TypeBean> it = this.typeAdapter.getData().iterator();
        while (true) {
            if (!it.hasNext()) {
                id = 0;
                break;
            }
            TypeBean next = it.next();
            if (next.getSelected()) {
                id = next.getId();
                break;
            }
        }
        Repository.INSTANCE.getHomeGameList(this.page, id, new Function1<PageBean<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment.getGameList.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<GameBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<GameBean> it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                HomeFragment.access$getMBinding(HomeFragment.this).srl2.finishRefresh();
                if (HomeFragment.this.getPage() == 1) {
                    HomeFragment.this.getGameAdapter().setNewInstance(it2.getList());
                } else {
                    HomeFragment.this.getGameAdapter().addData(it2.getList());
                }
                HomeFragment homeFragment = HomeFragment.this;
                homeFragment.setPage(homeFragment.getPage() + 1);
                homeFragment.getPage();
                if (it2.getCurrent_page() >= it2.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(HomeFragment.this.getGameAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    HomeFragment.this.getGameAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment.getGameList.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                HomeFragment.access$getMBinding(HomeFragment.this).srl2.finishRefresh(false);
                HomeFragment.this.netFail(it2);
            }
        });
    }

    private final void initFun() {
        final int i = 4;
        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_fun, new Function3<BaseDataBindingHolder<ItemFunBinding>, Integer, FunBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$initFun$funAdapter$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemFunBinding> baseDataBindingHolder, Integer num, FunBean funBean) {
                invoke(baseDataBindingHolder, num.intValue(), funBean);
                return Unit.INSTANCE;
            }

            public final void invoke(BaseDataBindingHolder<ItemFunBinding> h, int i2, FunBean funBean) {
                Intrinsics.checkNotNullParameter(h, "h");
                ItemFunBinding itemFunBinding = (ItemFunBinding) h.getDataBinding();
                if (itemFunBinding != null) {
                    HomeFragment homeFragment = this.this$0;
                    int width = (Util.getWidth(homeFragment.getMContext()) - Util.dpToPx(homeFragment.getMContext(), 30.0f)) / i;
                    ViewGroup.LayoutParams layoutParams = itemFunBinding.getRoot().getLayoutParams();
                    Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
                    RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
                    layoutParams2.width = width;
                    itemFunBinding.getRoot().setLayoutParams(layoutParams2);
                }
            }
        });
        getMBinding().rvFun.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeFragment$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                HomeFragment.initFun$lambda$6(baseAdapter, this, baseQuickAdapter, view, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void initFun$lambda$6(BaseAdapter funAdapter, HomeFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(funAdapter, "$funAdapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        String name = ((FunBean) funAdapter.getItem(i)).getName();
        if (Intrinsics.areEqual(name, "新游首发") ? true : Intrinsics.areEqual(name, "福利中心")) {
            this$0.getVm().getHomeFun().postValue(((FunBean) funAdapter.getItem(i)).getName());
            return;
        }
        if (!TextUtils.isEmpty(((FunBean) funAdapter.getItem(i)).getAppUrl())) {
            Util.openWebWithLogin(this$0.getMContext(), ((FunBean) funAdapter.getItem(i)).getName(), NetUtil.BASE_URL3 + "dist/" + ((FunBean) funAdapter.getItem(i)).getAppUrl());
            return;
        }
        String linkUrl = ((FunBean) funAdapter.getItem(i)).getLinkUrl();
        if (TextUtils.isEmpty(linkUrl) || !StringsKt.startsWith$default(linkUrl, "http", false, 2, (Object) null)) {
            return;
        }
        Util.openWebWithLogin(this$0.getMContext(), ((FunBean) funAdapter.getItem(i)).getName(), linkUrl);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public final void onLogin(LoginChangeBean result) {
        Intrinsics.checkNotNullParameter(result, "result");
        getData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Integer value = getVm().getMainTabPosition().getValue();
        if (value != null && value.intValue() == 0) {
            getVm().getHomeHeight().setValue(Integer.valueOf(this.height));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
