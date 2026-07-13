package com.cy.yyjia.zhe28.ui.fragment;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.base.BasePopupWindow;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.FragmentHomeSchedule2Binding;
import com.cy.yyjia.zhe28.databinding.ItemHallGameTypeBinding;
import com.cy.yyjia.zhe28.databinding.ItemHomeSchedule2Binding;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.NewGameBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: HomeScheduleFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010+\u001a\u00020,H\u0002J\u000e\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u000eJ\b\u0010/\u001a\u00020,H\u0002J\b\u00100\u001a\u00020,H\u0016J\u0010\u00101\u001a\u00020,2\u0006\u00102\u001a\u000203H\u0016R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001d\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\nR\u001a\u0010#\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0010\"\u0004\b%\u0010\u0012R\u001b\u0010&\u001a\u00020'8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\f\u001a\u0004\b(\u0010)¨\u00064"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/HomeScheduleFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentHomeSchedule2Binding;", "Landroid/view/View$OnClickListener;", "()V", "listAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/NewGameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemHomeSchedule2Binding;", "getListAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "listAdapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "screen", "", "getScreen", "()Ljava/lang/String;", "setScreen", "(Ljava/lang/String;)V", "top", "", "getTop", "()Z", "setTop", "(Z)V", "typeAdapter", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemHallGameTypeBinding;", "getTypeAdapter", "typeId", "getTypeId", "setTypeId", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "getData", "", "getNewData", ImageSelector.POSITION, "getType", "init", "onClick", "view", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HomeScheduleFragment extends BaseFragment<FragmentHomeSchedule2Binding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: listAdapter$delegate, reason: from kotlin metadata */
    private final Lazy listAdapter;
    private int page;
    private String screen;
    private boolean top;
    private final BaseAdapter<TypeBean, ItemHallGameTypeBinding> typeAdapter;
    private int typeId;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public static final /* synthetic */ FragmentHomeSchedule2Binding access$getMBinding(HomeScheduleFragment homeScheduleFragment) {
        return homeScheduleFragment.getMBinding();
    }

    public HomeScheduleFragment() {
        super(R.layout.fragment_home_schedule2);
        this.page = 1;
        this.screen = "";
        this.listAdapter = LazyKt.lazy(new Function0<BaseAdapter<NewGameBean, ItemHomeSchedule2Binding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment$listAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<NewGameBean, ItemHomeSchedule2Binding> invoke() {
                return new BaseAdapter<>(R.layout.item_home_schedule2, null, 2, null);
            }
        });
        this.typeAdapter = new BaseAdapter<>(R.layout.item_hall_game_type, null, 2, null);
        this.top = true;
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment$vm$2
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

    public final String getScreen() {
        return this.screen;
    }

    public final void setScreen(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.screen = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BaseAdapter<NewGameBean, ItemHomeSchedule2Binding> getListAdapter() {
        return (BaseAdapter) this.listAdapter.getValue();
    }

    public final BaseAdapter<TypeBean, ItemHallGameTypeBinding> getTypeAdapter() {
        return this.typeAdapter;
    }

    public final boolean getTop() {
        return this.top;
    }

    public final void setTop(boolean z) {
        this.top = z;
    }

    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getMBinding().setOnClick(this);
        getMBinding().setPosition(2);
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment$$ExternalSyntheticLambda5
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                HomeScheduleFragment.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getVm().getAction().observe(this, new HomeScheduleFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment.init.2
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
                if (num != null && num.intValue() == 8672) {
                    HomeScheduleFragment.this.getVm().getAction().setValue(0);
                    RecyclerView.LayoutManager layoutManager = HomeScheduleFragment.access$getMBinding(HomeScheduleFragment.this).rv.getLayoutManager();
                    Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                    if (((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0) {
                        HomeScheduleFragment.access$getMBinding(HomeScheduleFragment.this).srl.autoRefresh();
                    } else {
                        HomeScheduleFragment.access$getMBinding(HomeScheduleFragment.this).rv.scrollToPosition(0);
                    }
                }
            }
        }));
        getMBinding().rv.setAdapter(getListAdapter());
        getListAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                HomeScheduleFragment.init$lambda$1(this.f$0);
            }
        });
        getListAdapter().setEmptyView(R.layout.layout_empty);
        getType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(HomeScheduleFragment this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.page = 1;
        this$0.getListAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(HomeScheduleFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(final View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        int id = view.getId();
        if (id == R.id.tv_screen) {
            view.setSelected(true);
            BasePopupWindow.OnClickListener onClickListener = new BasePopupWindow.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment$$ExternalSyntheticLambda2
                @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnClickListener
                public final void onClick(BasePopupWindow basePopupWindow, View view2) {
                    HomeScheduleFragment.onClick$lambda$4(this.f$0, view, basePopupWindow, (TextView) view2);
                }
            };
            new BasePopupWindow.Builder(getMContext()).setContentView(R.layout.pop_schedule_time).setText(R.id.tv1, "全部").setText(R.id.tv2, "横屏").setText(R.id.tv3, "竖屏").setOnClickListener(R.id.tv1, onClickListener).setOnClickListener(R.id.tv2, onClickListener).setOnClickListener(R.id.tv3, onClickListener).addOnDismissListener(new BasePopupWindow.OnDismissListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment$$ExternalSyntheticLambda3
                @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnDismissListener
                public final void onDismiss(BasePopupWindow basePopupWindow) {
                    HomeScheduleFragment.onClick$lambda$5(view, basePopupWindow);
                }
            }).setWidth(view.getWidth()).showAsDropDown(view);
        } else if (id == R.id.tv_time) {
            view.setSelected(true);
            BasePopupWindow.OnClickListener onClickListener2 = new BasePopupWindow.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment$$ExternalSyntheticLambda0
                @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnClickListener
                public final void onClick(BasePopupWindow basePopupWindow, View view2) {
                    HomeScheduleFragment.onClick$lambda$2(this.f$0, view, basePopupWindow, (TextView) view2);
                }
            };
            new BasePopupWindow.Builder(getMContext()).setContentView(R.layout.pop_schedule_time).setOnClickListener(R.id.tv1, onClickListener2).setOnClickListener(R.id.tv2, onClickListener2).setOnClickListener(R.id.tv3, onClickListener2).addOnDismissListener(new BasePopupWindow.OnDismissListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment$$ExternalSyntheticLambda1
                @Override // com.cy.yyjia.zhe28.base.BasePopupWindow.OnDismissListener
                public final void onDismiss(BasePopupWindow basePopupWindow) {
                    HomeScheduleFragment.onClick$lambda$3(view, basePopupWindow);
                }
            }).setWidth(view.getWidth()).showAsDropDown(view);
        } else {
            if (id != R.id.tv_type) {
                return;
            }
            view.setSelected(true);
            QuickDialog quickDialog = new QuickDialog(getMContext(), R.layout.dialog_new_game_type);
            BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_game_type2, this.typeAdapter.getData());
            baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment$$ExternalSyntheticLambda4
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                    HomeScheduleFragment.onClick$lambda$6(this.f$0, view, baseQuickAdapter, view2, i);
                }
            });
            quickDialog.setAdapter(R.id.rv, baseAdapter).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$2(HomeScheduleFragment this$0, View view, BasePopupWindow basePopupWindow, TextView textView) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        int id = textView.getId();
        if (id == R.id.tv1) {
            this$0.getNewData(1);
            this$0.getMBinding().tvTime.setText("昨天");
        } else if (id == R.id.tv3) {
            this$0.getNewData(3);
            this$0.getMBinding().tvTime.setText("明天");
        } else {
            this$0.getNewData(2);
            this$0.getMBinding().tvTime.setText("今天");
        }
        basePopupWindow.dismiss();
        view.setSelected(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$3(View view, BasePopupWindow basePopupWindow) {
        Intrinsics.checkNotNullParameter(view, "$view");
        view.setSelected(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$4(HomeScheduleFragment this$0, View view, BasePopupWindow basePopupWindow, TextView textView) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        int id = textView.getId();
        if (id == R.id.tv1) {
            this$0.getMBinding().tvScreen.setText("横屏/竖屏");
            this$0.screen = "";
        } else if (id == R.id.tv3) {
            this$0.getMBinding().tvScreen.setText("竖屏");
            this$0.screen = "vertical";
        } else {
            this$0.getMBinding().tvScreen.setText("横屏");
            this$0.screen = "horizontal";
        }
        this$0.getNewData(this$0.getMBinding().getPosition());
        basePopupWindow.dismiss();
        view.setSelected(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$5(View view, BasePopupWindow basePopupWindow) {
        Intrinsics.checkNotNullParameter(view, "$view");
        view.setSelected(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$6(HomeScheduleFragment this$0, View view, BaseQuickAdapter baseQuickAdapter, View view2, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view2, "<anonymous parameter 1>");
        this$0.getMBinding().rvType.scrollToPosition(i);
        Iterator<TypeBean> it = this$0.typeAdapter.getData().iterator();
        while (it.hasNext()) {
            it.next().setSelected(false);
        }
        this$0.typeAdapter.getItem(i).setSelected(true);
        this$0.typeId = this$0.typeAdapter.getItem(i).getId();
        view.setSelected(false);
        this$0.getMBinding().srl.autoRefresh();
    }

    public final void getNewData(int position) {
        getMBinding().setPosition(position);
        getMBinding().srl.autoRefresh();
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment$getType$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HomeScheduleFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class C11541 extends Lambda implements Function1<List<TypeBean>, Unit> {
        C11541() {
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
            HomeScheduleFragment.this.setTypeId(it.get(0).getId());
            HomeScheduleFragment.this.getTypeAdapter().setNewInstance(it);
            HomeScheduleFragment.access$getMBinding(HomeScheduleFragment.this).rvType.setAdapter(HomeScheduleFragment.this.getTypeAdapter());
            BaseAdapter<TypeBean, ItemHallGameTypeBinding> typeAdapter = HomeScheduleFragment.this.getTypeAdapter();
            final HomeScheduleFragment homeScheduleFragment = HomeScheduleFragment.this;
            typeAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment$getType$1$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    HomeScheduleFragment.C11541.invoke$lambda$0(homeScheduleFragment, baseQuickAdapter, view, i);
                }
            });
            HomeScheduleFragment.this.getData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(HomeScheduleFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            Iterator<TypeBean> it = this$0.getTypeAdapter().getData().iterator();
            while (it.hasNext()) {
                it.next().setSelected(false);
            }
            this$0.getTypeAdapter().getItem(i).setSelected(true);
            this$0.setTypeId(this$0.getTypeAdapter().getItem(i).getId());
            HomeScheduleFragment.access$getMBinding(this$0).srl.autoRefresh();
        }
    }

    private final void getType() {
        Repository.INSTANCE.getHomeGameType(5, new C11541(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment.getType.2
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
                HomeScheduleFragment.this.netFail(it);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getData() {
        Repository.INSTANCE.getSchedule(this.page, getMBinding().getPosition(), new Function1<PageBean<NewGameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<NewGameBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<NewGameBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                HomeScheduleFragment.access$getMBinding(HomeScheduleFragment.this).srl.finishRefresh();
                if (HomeScheduleFragment.this.getPage() == 1) {
                    HomeScheduleFragment.this.getListAdapter().setNewInstance(it.getList());
                } else {
                    HomeScheduleFragment.this.getListAdapter().addData((Collection) it.getList());
                }
                HomeScheduleFragment homeScheduleFragment = HomeScheduleFragment.this;
                homeScheduleFragment.setPage(homeScheduleFragment.getPage() + 1);
                homeScheduleFragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(HomeScheduleFragment.this.getListAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    HomeScheduleFragment.this.getListAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HomeScheduleFragment.getData.2
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
                HomeScheduleFragment.this.netFail(it);
                HomeScheduleFragment.access$getMBinding(HomeScheduleFragment.this).srl.finishRefresh(false);
                HomeScheduleFragment.this.getListAdapter().getLoadMoreModule().loadMoreFail();
            }
        }, this.typeId, this.screen);
    }
}
