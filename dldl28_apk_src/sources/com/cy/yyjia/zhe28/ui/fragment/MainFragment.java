package com.cy.yyjia.zhe28.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentMainBinding;
import com.cy.yyjia.zhe28.databinding.ItemMainTabBinding;
import com.cy.yyjia.zhe28.domain.MainTabBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.TopicBean;
import com.cy.yyjia.zhe28.ui.activity.QiandaoActivity;
import com.cy.yyjia.zhe28.ui.activity.SearchActivity;
import com.cy.yyjia.zhe28.ui.fragment.MainFragment;
import com.cy.yyjia.zhe28.ui.fragment.TopicDetailFragment;
import com.cy.yyjia.zhe28.ui.fragment.WebFragment;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u001dH\u0016J\b\u0010\u001f\u001a\u00020\u001dH\u0016J\u0012\u0010 \u001a\u00020\u001d2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R'\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\t\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0011\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006$"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/MainFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentMainBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/ui/fragment/MainFragment$VpAdapter;", "getAdapter", "()Lcom/cy/yyjia/zhe28/ui/fragment/MainFragment$VpAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "tabAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/MainTabBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemMainTabBinding;", "getTabAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "tabAdapter$delegate", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "white", "", "getWhite", "()Z", "setWhite", "(Z)V", "getTab", "", "init", "onResume", "onViewStateRestored", "savedInstanceState", "Landroid/os/Bundle;", "VpAdapter", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MainFragment extends BaseFragment<FragmentMainBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: tabAdapter$delegate, reason: from kotlin metadata */
    private final Lazy tabAdapter;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;
    private boolean white;

    public MainFragment() {
        super(R.layout.fragment_main);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.MainFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
            }
        });
        this.tabAdapter = LazyKt.lazy(new Function0<BaseAdapter<MainTabBean, ItemMainTabBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.MainFragment$tabAdapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<MainTabBean, ItemMainTabBinding> invoke() {
                final MainFragment mainFragment = this.this$0;
                return new BaseAdapter<>(R.layout.item_main_tab, new Function3<BaseDataBindingHolder<ItemMainTabBinding>, Integer, MainTabBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.MainFragment$tabAdapter$2.1
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemMainTabBinding> baseDataBindingHolder, Integer num, MainTabBean mainTabBean) {
                        invoke(baseDataBindingHolder, num.intValue(), mainTabBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemMainTabBinding> h, int i, MainTabBean mainTabBean) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemMainTabBinding itemMainTabBinding = (ItemMainTabBinding) h.getDataBinding();
                        if (itemMainTabBinding == null) {
                            return;
                        }
                        itemMainTabBinding.setWhite(mainFragment.getWhite());
                    }
                });
            }
        });
        this.adapter = LazyKt.lazy(new Function0<VpAdapter>() { // from class: com.cy.yyjia.zhe28.ui.fragment.MainFragment$adapter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainFragment.VpAdapter invoke() {
                return new MainFragment.VpAdapter(this.this$0);
            }
        });
    }

    public static final /* synthetic */ FragmentMainBinding access$getMBinding(MainFragment mainFragment) {
        return mainFragment.getMBinding();
    }

    public final boolean getWhite() {
        return this.white;
    }

    public final void setWhite(boolean z) {
        this.white = z;
    }

    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    public final BaseAdapter<MainTabBean, ItemMainTabBinding> getTabAdapter() {
        return (BaseAdapter) this.tabAdapter.getValue();
    }

    public final VpAdapter getAdapter() {
        return (VpAdapter) this.adapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        log("开始初始化");
        getMBinding().vp.setAdapter(getAdapter());
        getMBinding().vp.setUserInputEnabled(false);
        getMBinding().ivSearch.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.MainFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainFragment.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().ivQiandao.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.MainFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainFragment.init$lambda$1(this.f$0, view);
            }
        });
        getMBinding().rv.setAdapter(getTabAdapter());
        getTabAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.MainFragment$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MainFragment.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        MainFragment mainFragment = this;
        getVm().getHomeHeight().observe(mainFragment, new MainFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.MainFragment.init.4
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
                ImageView imageView = MainFragment.access$getMBinding(MainFragment.this).ivSearch;
                Intrinsics.checkNotNull(num);
                imageView.setVisibility(num.intValue() > Util.dpToPx(MainFragment.this.getMContext(), 45.0f) ? 0 : 8);
            }
        }));
        getVm().getAction().observe(mainFragment, new MainFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.MainFragment.init.5
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
                if (num != null && num.intValue() == -1) {
                    MainFragment.this.getVm().getAction().setValue(Integer.valueOf(MainFragment.this.getAdapter().getData().get(MainFragment.access$getMBinding(MainFragment.this).vp.getCurrentItem()).getNav_template()));
                }
            }
        }));
        getTab();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(MainFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivityForResult(new Intent(this$0.getMContext(), (Class<?>) SearchActivity.class), 9899);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(MainFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Util.skipWithLogin(this$0.getMContext(), QiandaoActivity.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(MainFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        int color;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        for (MainTabBean mainTabBean : this$0.getTabAdapter().getData()) {
            mainTabBean.setSelected(Intrinsics.areEqual(mainTabBean, this$0.getTabAdapter().getItem(i)));
        }
        this$0.getMBinding().vp.setCurrentItem(i, false);
        this$0.getVm().getMainTabPosition().setValue(Integer.valueOf(i));
        if (i != 0) {
            this$0.getMBinding().ivSearch.setVisibility(0);
        } else {
            ImageView imageView = this$0.getMBinding().ivSearch;
            Integer value = this$0.getVm().getHomeHeight().getValue();
            Intrinsics.checkNotNull(value);
            imageView.setVisibility(value.intValue() > Util.dpToPx(this$0.getMContext(), 45.0f) ? 0 : 8);
        }
        if (this$0.getTabAdapter().getItem(i).getNav_template() == 6) {
            this$0.white = true;
            TopicBean topic = this$0.getTabAdapter().getItem(i).getTopic();
            Intrinsics.checkNotNull(topic);
            color = Color.parseColor(topic.getColor());
        } else {
            this$0.white = false;
            color = -1;
        }
        this$0.getMContext().immersionBar(R.color.transparent, !this$0.white);
        this$0.getMBinding().bg.setBackgroundColor(color);
        this$0.getMBinding().setWhite(this$0.white);
        this$0.getTabAdapter().notifyDataSetChanged();
    }

    public final void getTab() {
        Repository.INSTANCE.getMainTab(new Function1<List<MainTabBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.MainFragment.getTab.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<MainTabBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<MainTabBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                MainFragment.this.log("开始初始化1");
                it.get(0).setSelected(true);
                MainFragment.this.getTabAdapter().setNewInstance(it);
                MainFragment.this.getVm().getHomeNav().setValue(it);
                MainFragment.this.getAdapter().setNewInstance(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.MainFragment.getTab.2
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
                MainFragment.this.netFail(it);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        getMBinding().vp.setCurrentItem(0, false);
        getMBinding().setWhite(false);
        getVm().getMainTabPosition().setValue(0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Constant.INSTANCE.getLogged();
    }

    /* JADX INFO: compiled from: MainFragment.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u0014\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014R+\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/MainFragment$VpAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "fragment", "Landroidx/fragment/app/Fragment;", "(Landroidx/fragment/app/Fragment;)V", "data", "Ljava/util/ArrayList;", "Lcom/cy/yyjia/zhe28/domain/MainTabBean;", "Lkotlin/collections/ArrayList;", "getData", "()Ljava/util/ArrayList;", "data$delegate", "Lkotlin/Lazy;", "createFragment", ImageSelector.POSITION, "", "getItemCount", "setNewInstance", "", "newData", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class VpAdapter extends FragmentStateAdapter {
        public static final int $stable = 8;

        /* JADX INFO: renamed from: data$delegate, reason: from kotlin metadata */
        private final Lazy data;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VpAdapter(Fragment fragment) {
            super(fragment);
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            this.data = LazyKt.lazy(new Function0<ArrayList<MainTabBean>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.MainFragment$VpAdapter$data$2
                @Override // kotlin.jvm.functions.Function0
                public final ArrayList<MainTabBean> invoke() {
                    return CollectionsKt.arrayListOf(new MainTabBean(0, "首页", 1, null, new ArrayList()));
                }
            });
        }

        public final ArrayList<MainTabBean> getData() {
            return (ArrayList) this.data.getValue();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return getData().size();
        }

        @Override // androidx.viewpager2.adapter.FragmentStateAdapter
        public Fragment createFragment(int position) {
            int nav_template = getData().get(position).getNav_template();
            if (nav_template == 1) {
                return new Fragment();
            }
            if (nav_template == 2) {
                return HomeNewFragment.INSTANCE.newInstance(position);
            }
            if (nav_template == 5) {
                TopicDetailFragment.Companion companion = TopicDetailFragment.INSTANCE;
                TopicBean topic = getData().get(position).getTopic();
                Intrinsics.checkNotNull(topic);
                return companion.newInstance(topic.getId());
            }
            if (nav_template == 6) {
                WebFragment.Companion companion2 = WebFragment.INSTANCE;
                TopicBean topic2 = getData().get(position).getTopic();
                Intrinsics.checkNotNull(topic2);
                return companion2.newInstance(topic2.getUrl());
            }
            return HomeGameFragment.INSTANCE.newInstance(position);
        }

        public final void setNewInstance(List<MainTabBean> newData) {
            Intrinsics.checkNotNullParameter(newData, "newData");
            getData().addAll(newData.subList(1, newData.size()));
            notifyItemRangeInserted(1, newData.size() - 1);
        }
    }
}
