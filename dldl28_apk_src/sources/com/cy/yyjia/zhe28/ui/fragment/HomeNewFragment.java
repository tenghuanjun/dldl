package com.cy.yyjia.zhe28.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentHomeList2Binding;
import com.cy.yyjia.zhe28.databinding.FragmentHomeNewBinding;
import com.cy.yyjia.zhe28.databinding.FragmentHomeScheduleBinding;
import com.cy.yyjia.zhe28.databinding.ItemGameTypeBinding;
import com.cy.yyjia.zhe28.databinding.ItemHomeRankBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.MainTabBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.ui.activity.MessageActivity;
import com.cy.yyjia.zhe28.ui.activity.QiandaoActivity;
import com.cy.yyjia.zhe28.ui.activity.SearchActivity;
import com.cy.yyjia.zhe28.ui.adapter.HotSearchAdapter;
import com.cy.yyjia.zhe28.ui.adapter.NewGameAdapter;
import com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment;
import com.cy.yyjia.zhe28.util.CalendarUtil;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.google.android.material.appbar.AppBarLayout;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeNewFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 #2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0003\"#$B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0006\u0010\u001c\u001a\u00020\u001bJ\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u001bH\u0016J\u000e\u0010!\u001a\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\fR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0012\u0010\u000eR\u001b\u0010\u0015\u001a\u00020\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/HomeNewFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentHomeNewBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemGameTypeBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "height", "", "getHeight", "()I", "setHeight", "(I)V", ImageSelector.POSITION, "getPosition", "position$delegate", "Lkotlin/Lazy;", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "init", "", "initType", "onClick", "v", "Landroid/view/View;", "onResume", "select", "ChildFragment", "Companion", "RankFragment", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HomeNewFragment extends BaseFragment<FragmentHomeNewBinding> implements View.OnClickListener {
    private final BaseAdapter<TypeBean, ItemGameTypeBinding> adapter;
    private int height;

    /* JADX INFO: renamed from: position$delegate, reason: from kotlin metadata */
    private final Lazy position;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public HomeNewFragment() {
        super(R.layout.fragment_home_new);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
            }
        });
        this.position = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$position$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.requireArguments().getInt(ImageSelector.POSITION, 0));
            }
        });
        this.adapter = new BaseAdapter<>(R.layout.item_game_type, null, 2, null);
    }

    public static final /* synthetic */ FragmentHomeNewBinding access$getMBinding(HomeNewFragment homeNewFragment) {
        return homeNewFragment.getMBinding();
    }

    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    public final int getPosition() {
        return ((Number) this.position.getValue()).intValue();
    }

    public final int getHeight() {
        return this.height;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    public final BaseAdapter<TypeBean, ItemGameTypeBinding> getAdapter() {
        return this.adapter;
    }

    /* JADX INFO: compiled from: HomeNewFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/HomeNewFragment$Companion;", "", "()V", "newInstance", "Lcom/cy/yyjia/zhe28/ui/fragment/HomeNewFragment;", ImageSelector.POSITION, "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HomeNewFragment newInstance(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt(ImageSelector.POSITION, position);
            HomeNewFragment homeNewFragment = new HomeNewFragment();
            homeNewFragment.setArguments(bundle);
            return homeNewFragment;
        }
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getMBinding().setPosition(1);
        getMBinding().setOnClick(this);
        HomeNewFragment homeNewFragment = this;
        getVm().getHomeNav().observe(homeNewFragment, new HomeNewFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<MainTabBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment.init.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<MainTabBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<MainTabBean> list) {
                HomeNewFragment.this.initType();
            }
        }));
        getMBinding().tvQiandao.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        getMBinding().tvSearch.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onClick(view);
            }
        });
        getMBinding().abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$$ExternalSyntheticLambda2
            @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
            public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                HomeNewFragment.init$lambda$0(this.f$0, appBarLayout, i);
            }
        });
        getVm().getAction().observe(homeNewFragment, new HomeNewFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment.init.5
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
                if (num != null && num.intValue() == 2) {
                    HomeNewFragment.this.getVm().getAction().setValue(Integer.valueOf(HomeNewFragment.this.getAdapter().getData().get(HomeNewFragment.access$getMBinding(HomeNewFragment.this).vp.getCurrentItem()).getId()));
                }
            }
        }));
        getMBinding().vf.setAdapter(new HotSearchAdapter(getMContext()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(HomeNewFragment this$0, AppBarLayout appBarLayout, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            this$0.height = Util.dpToPx(this$0.getMContext(), 50.0f);
            this$0.getVm().getHomeHeight().setValue(Integer.valueOf(this$0.height));
        } else {
            this$0.height = 0;
            this$0.getVm().getHomeHeight().setValue(Integer.valueOf(this$0.height));
        }
    }

    public final void initType() {
        log("来了来了");
        List<MainTabBean> value = getVm().getHomeNav().getValue();
        Intrinsics.checkNotNull(value);
        final List<TypeBean> cates = value.get(getPosition()).getCates();
        cates.add(0, new TypeBean(8671, "首发", "", "", "", null, 32, null));
        cates.add(new TypeBean(8673, "预约", "", "", "", null, 32, null));
        cates.add(new TypeBean(8672, "分类", "", "", "", null, 32, null));
        int size = cates.size();
        int i = 0;
        while (i < size) {
            cates.get(i).setSelected(i == 0);
            i++;
        }
        getMBinding().f460tv.setText(cates.get(0).getTips());
        getMBinding().rvType.setAdapter(this.adapter);
        this.adapter.setNewInstance(cates);
        this.adapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                HomeNewFragment.initType$lambda$1(this.f$0, baseQuickAdapter, view, i2);
            }
        });
        getMBinding().vp.setAdapter(new FragmentStateAdapter(this) { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment.initType.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return cates.size();
            }

            @Override // androidx.viewpager2.adapter.FragmentStateAdapter
            public Fragment createFragment(int position) {
                switch (cates.get(position).getId()) {
                    case 8671:
                        return ChildFragment.INSTANCE.newInstance(0);
                    case 8672:
                        return new HomeScheduleFragment();
                    case 8673:
                        return ChildFragment.INSTANCE.newInstance(1);
                    case 8674:
                        return new HallGameFragment();
                    default:
                        return RankFragment.INSTANCE.newInstance(cates.get(position).getId());
                }
            }
        });
        getMBinding().vp.setUserInputEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initType$lambda$1(HomeNewFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.checkClick(1)) {
            return;
        }
        Iterator<TypeBean> it = this$0.adapter.getData().iterator();
        while (true) {
            i2 = 0;
            if (!it.hasNext()) {
                break;
            } else {
                it.next().setSelected(false);
            }
        }
        this$0.adapter.getItem(i).setSelected(true);
        this$0.getMBinding().f460tv.setText(this$0.adapter.getItem(i).getTips());
        LinearLayout linearLayout = this$0.getMBinding().ll;
        switch (this$0.adapter.getItem(i).getId()) {
            case 8671:
            case 8672:
            case 8673:
            case 8674:
                i2 = 8;
                break;
        }
        linearLayout.setVisibility(i2);
        this$0.select(i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.tv1 /* 2131362645 */:
                select(1);
                break;
            case R.id.tv2 /* 2131362646 */:
                select(2);
                break;
            case R.id.tv3 /* 2131362647 */:
                select(3);
                break;
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

    public final void select(int position) {
        getMBinding().setPosition(position);
        getMBinding().vp.setCurrentItem(position, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Integer value = getVm().getMainTabPosition().getValue();
        if (value != null && value.intValue() == 0) {
            getVm().getHomeHeight().setValue(Integer.valueOf(this.height));
        }
    }

    /* JADX INFO: compiled from: HomeNewFragment.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \"2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0006\u0010 \u001a\u00020\u001fJ\b\u0010!\u001a\u00020\u001fH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0016\u001a\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0017\u0010\rR\u001b\u0010\u0019\u001a\u00020\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\t\u001a\u0004\b\u001b\u0010\u001c¨\u0006#"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/HomeNewFragment$ChildFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentHomeScheduleBinding;", "()V", "listAdapter", "Lcom/cy/yyjia/zhe28/ui/adapter/NewGameAdapter;", "getListAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/NewGameAdapter;", "listAdapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "time", "", "getTime", "()Ljava/lang/String;", "setTime", "(Ljava/lang/String;)V", "type", "getType", "type$delegate", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "getData", "", "getNewGame", "init", "Companion", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ChildFragment extends BaseFragment<FragmentHomeScheduleBinding> {

        /* JADX INFO: renamed from: listAdapter$delegate, reason: from kotlin metadata */
        private final Lazy listAdapter;
        private int page;
        private String time;

        /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
        private final Lazy type;

        /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
        private final Lazy vm;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final int $stable = 8;

        public ChildFragment() {
            super(R.layout.fragment_home_schedule);
            this.type = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$type$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    Bundle arguments = this.this$0.getArguments();
                    Intrinsics.checkNotNull(arguments);
                    return Integer.valueOf(arguments.getInt("type", 0));
                }
            });
            this.listAdapter = LazyKt.lazy(new Function0<NewGameAdapter>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$listAdapter$2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final NewGameAdapter invoke() {
                    return new NewGameAdapter();
                }
            });
            this.page = 1;
            this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$vm$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final MainViewModel invoke() {
                    return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
                }
            });
            this.time = "";
        }

        public final int getType() {
            return ((Number) this.type.getValue()).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final NewGameAdapter getListAdapter() {
            return (NewGameAdapter) this.listAdapter.getValue();
        }

        public final int getPage() {
            return this.page;
        }

        public final void setPage(int i) {
            this.page = i;
        }

        /* JADX INFO: compiled from: HomeNewFragment.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/HomeNewFragment$ChildFragment$Companion;", "", "()V", "newInstance", "Lcom/cy/yyjia/zhe28/ui/fragment/HomeNewFragment$ChildFragment;", "type", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ChildFragment newInstance(int type) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", type);
                ChildFragment childFragment = new ChildFragment();
                childFragment.setArguments(bundle);
                return childFragment;
            }
        }

        public final MainViewModel getVm() {
            return (MainViewModel) this.vm.getValue();
        }

        public final String getTime() {
            return this.time;
        }

        public final void setTime(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.time = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$0(ChildFragment this$0, RefreshLayout it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(it, "it");
            this$0.getNewGame();
        }

        @Override // com.cy.yyjia.zhe28.base.BaseFragment
        public void init() {
            getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$$ExternalSyntheticLambda0
                @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
                public final void onRefresh(RefreshLayout refreshLayout) {
                    HomeNewFragment.ChildFragment.init$lambda$0(this.f$0, refreshLayout);
                }
            });
            getListAdapter().setType(getType());
            getMBinding().ll.setVisibility(getType() == 0 ? 8 : 0);
            getMBinding().f461tv.setText(getType() == 0 ? "展示30天内上线的新游" : "展示即将上线的游戏");
            getMBinding().rv.setAdapter(getListAdapter());
            getListAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$$ExternalSyntheticLambda1
                @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
                public final void onLoadMore() {
                    HomeNewFragment.ChildFragment.init$lambda$1(this.f$0);
                }
            });
            getListAdapter().addChildClickViewIds(R.id.btn);
            getListAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$$ExternalSyntheticLambda2
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    HomeNewFragment.ChildFragment.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
                }
            });
            Repository.INSTANCE.getNewGameTime(getType(), new HomeNewFragment$ChildFragment$init$4(this), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$init$5
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
            getVm().getAction().observe(this, new HomeNewFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$init$6
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
                    if (num != null && num.intValue() == 8671) {
                        this.this$0.getVm().getAction().setValue(0);
                        RecyclerView.LayoutManager layoutManager = this.this$0.getMBinding().rv.getLayoutManager();
                        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                        if (((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0) {
                            this.this$0.getMBinding().srl.autoRefresh();
                        } else {
                            this.this$0.getMBinding().rv.scrollToPosition(0);
                        }
                    }
                }
            }));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$1(ChildFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$2(final ChildFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            if (this$0.getListAdapter().getItem(i).getIsOrder()) {
                return;
            }
            CalendarUtil.INSTANCE.checkCalendarPermission(this$0.getMContext(), new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$init$3$1
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
                    Repository repository = Repository.INSTANCE;
                    int id = this.this$0.getListAdapter().getItem(i).getId();
                    final HomeNewFragment.ChildFragment childFragment = this.this$0;
                    final int i2 = i;
                    Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$init$3$1.1
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
                            childFragment.toast(it.getMsg());
                            childFragment.getListAdapter().getItem(i2).setOrder(true);
                            boolean zAddCalendarEvent = CalendarUtil.INSTANCE.addCalendarEvent(childFragment.getMContext(), childFragment.getListAdapter().getItem(i2).getStartTime(), childFragment.getListAdapter().getItem(i2).getName() + "即将上线", "您预约的游戏 " + childFragment.getListAdapter().getItem(i2).getName() + " 十分钟后上线");
                            childFragment.log("写入日历" + zAddCalendarEvent);
                        }
                    };
                    final HomeNewFragment.ChildFragment childFragment2 = this.this$0;
                    repository.orderGame(id, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$init$3$1.2
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
                            childFragment2.netFail(it);
                        }
                    });
                }
            }, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$init$3$2
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
                    this.this$0.tip("预约需要授予日历权限");
                }
            });
        }

        public final void getNewGame() {
            this.page = 1;
            getListAdapter().setNewInstance(null);
            getData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void getData() {
            Repository.INSTANCE.getNewGame(getType(), this.page, new Function1<PageBean<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$getData$1
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
                    this.this$0.getMBinding().srl.finishRefresh();
                    List<GameBean> list = it.getList();
                    if (this.this$0.getPage() == 1) {
                        this.this$0.getListAdapter().setNewInstance(list);
                    } else {
                        this.this$0.getListAdapter().addData((Collection) list);
                    }
                    HomeNewFragment.ChildFragment childFragment = this.this$0;
                    childFragment.setPage(childFragment.getPage() + 1);
                    childFragment.getPage();
                    if (it.getCurrent_page() >= it.getLast_page()) {
                        BaseLoadMoreModule.loadMoreEnd$default(this.this$0.getListAdapter().getLoadMoreModule(), false, 1, null);
                    } else {
                        this.this$0.getListAdapter().getLoadMoreModule().loadMoreComplete();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$ChildFragment$getData$2
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
                    this.this$0.getMBinding().srl.finishRefresh(false);
                    this.this$0.netFail(it);
                    this.this$0.getListAdapter().getLoadMoreModule().loadMoreFail();
                }
            }, this.time);
        }
    }

    /* JADX INFO: compiled from: HomeNewFragment.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0013\u0010\u000fR\u001b\u0010\u0015\u001a\u00020\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u000b\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001e"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/HomeNewFragment$RankFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentHomeList2Binding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemHomeRankBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "type", "getType", "type$delegate", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "getData", "", "init", "Companion", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class RankFragment extends BaseFragment<FragmentHomeList2Binding> {

        /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
        private final Lazy adapter;
        private int page;

        /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
        private final Lazy type;

        /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
        private final Lazy vm;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final int $stable = 8;

        public RankFragment() {
            super(R.layout.fragment_home_list2);
            this.type = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$RankFragment$type$2
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
            this.page = 1;
            this.adapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemHomeRankBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$RankFragment$adapter$2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final BaseAdapter<GameBean, ItemHomeRankBinding> invoke() {
                    return new BaseAdapter<>(R.layout.item_home_rank, new Function3<BaseDataBindingHolder<ItemHomeRankBinding>, Integer, GameBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$RankFragment$adapter$2.1
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
            this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$RankFragment$vm$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final MainViewModel invoke() {
                    return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
                }
            });
        }

        public final int getType() {
            return ((Number) this.type.getValue()).intValue();
        }

        public final int getPage() {
            return this.page;
        }

        public final void setPage(int i) {
            this.page = i;
        }

        public final BaseAdapter<GameBean, ItemHomeRankBinding> getAdapter() {
            return (BaseAdapter) this.adapter.getValue();
        }

        /* JADX INFO: compiled from: HomeNewFragment.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/HomeNewFragment$RankFragment$Companion;", "", "()V", "newInstance", "Lcom/cy/yyjia/zhe28/ui/fragment/HomeNewFragment$RankFragment;", "type", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final RankFragment newInstance(int type) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", type);
                RankFragment rankFragment = new RankFragment();
                rankFragment.setArguments(bundle);
                return rankFragment;
            }
        }

        public final MainViewModel getVm() {
            return (MainViewModel) this.vm.getValue();
        }

        @Override // com.cy.yyjia.zhe28.base.BaseFragment
        public void init() {
            getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$RankFragment$$ExternalSyntheticLambda0
                @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
                public final void onRefresh(RefreshLayout refreshLayout) {
                    HomeNewFragment.RankFragment.init$lambda$0(this.f$0, refreshLayout);
                }
            });
            getVm().getAction().observe(this, new HomeNewFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$RankFragment$init$2
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
                    int type = this.this$0.getType();
                    if (num != null && num.intValue() == type) {
                        this.this$0.getVm().getAction().setValue(0);
                        RecyclerView.LayoutManager layoutManager = this.this$0.getMBinding().rv.getLayoutManager();
                        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                        if (((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0) {
                            this.this$0.getMBinding().srl.autoRefresh();
                        } else {
                            this.this$0.getMBinding().rv.scrollToPosition(0);
                        }
                    }
                }
            }));
            getMBinding().ll.setVisibility(8);
            getMBinding().rv.setAdapter(getAdapter());
            getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$RankFragment$$ExternalSyntheticLambda1
                @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
                public final void onLoadMore() {
                    HomeNewFragment.RankFragment.init$lambda$1(this.f$0);
                }
            });
            getData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$0(RankFragment this$0, RefreshLayout it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(it, "it");
            this$0.page = 1;
            this$0.getAdapter().setNewInstance(null);
            this$0.getData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$1(RankFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getData();
        }

        private final void getData() {
            Repository.INSTANCE.getHomeGame(this.page, getType(), new Function1<PageBean<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$RankFragment$getData$1
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
                    this.this$0.getMBinding().srl.finishRefresh();
                    if (this.this$0.getPage() == 1) {
                        this.this$0.getAdapter().setNewInstance(it.getList());
                    } else {
                        this.this$0.getAdapter().addData(it.getList());
                    }
                    HomeNewFragment.RankFragment rankFragment = this.this$0;
                    rankFragment.setPage(rankFragment.getPage() + 1);
                    rankFragment.getPage();
                    if (it.getCurrent_page() >= it.getLast_page()) {
                        BaseLoadMoreModule.loadMoreEnd$default(this.this$0.getAdapter().getLoadMoreModule(), false, 1, null);
                    } else {
                        this.this$0.getAdapter().getLoadMoreModule().loadMoreComplete();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeNewFragment$RankFragment$getData$2
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
                    this.this$0.getMBinding().srl.finishRefresh(false);
                    this.this$0.getAdapter().getLoadMoreModule().loadMoreFail();
                    this.this$0.netFail(it);
                }
            });
        }
    }
}
