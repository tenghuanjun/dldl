package com.cy.yyjia.zhe28.ui.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentHallGameBinding;
import com.cy.yyjia.zhe28.databinding.ItemHallGameBinding;
import com.cy.yyjia.zhe28.databinding.ItemHallGameTypeBinding;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.ui.fragment.HallGameFragment;
import com.cy.yyjia.zhe28.util.Repository;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: HallGameFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u00104\u001a\u000205H\u0002J\b\u0010\"\u001a\u000205H\u0002J\b\u00106\u001a\u000205H\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R\u001a\u0010!\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001dR'\u0010$\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&0\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\u000b\u001a\u0004\b'\u0010\tR\u001a\u0010)\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001b\"\u0004\b+\u0010\u001dR\u001a\u0010,\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001b\"\u0004\b.\u0010\u001dR\u001b\u0010/\u001a\u0002008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b3\u0010\u000b\u001a\u0004\b1\u00102¨\u00067"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/HallGameFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentHallGameBinding;", "()V", "gameAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemHallGameBinding;", "getGameAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "gameAdapter$delegate", "Lkotlin/Lazy;", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "order", "", "getOrder", "()Ljava/lang/String;", "setOrder", "(Ljava/lang/String;)V", "page", "", "getPage", "()I", "setPage", "(I)V", "size", "getSize", "setSize", "type", "getType", "setType", "typeAdapter", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemHallGameTypeBinding;", "getTypeAdapter", "typeAdapter$delegate", "typeId", "getTypeId", "setTypeId", "typePosition", "getTypePosition", "setTypePosition", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HallGameFragment extends BaseFragment<FragmentHallGameBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: gameAdapter$delegate, reason: from kotlin metadata */
    private final Lazy gameAdapter;
    public Job job;
    private String order;
    private int page;
    private String size;
    private int type;

    /* JADX INFO: renamed from: typeAdapter$delegate, reason: from kotlin metadata */
    private final Lazy typeAdapter;
    private int typeId;
    private int typePosition;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public HallGameFragment() {
        super(R.layout.fragment_hall_game);
        this.page = 1;
        this.size = MonitorCommonConstants.DEFAULT_AID;
        this.order = MonitorCommonConstants.DEFAULT_AID;
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
            }
        });
        this.gameAdapter = LazyKt.lazy(new Function0<BaseAdapter<GameBean, ItemHallGameBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment$gameAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<GameBean, ItemHallGameBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_hall_game, new Function3<BaseDataBindingHolder<ItemHallGameBinding>, Integer, GameBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment$gameAdapter$2.1
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
                        itemHallGameBinding.setPosition(h.getLayoutPosition());
                    }
                });
            }
        });
        this.typeAdapter = LazyKt.lazy(new Function0<BaseAdapter<TypeBean, ItemHallGameTypeBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment$typeAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<TypeBean, ItemHallGameTypeBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_hall_game_type, null, 2, null);
            }
        });
    }

    public static final /* synthetic */ FragmentHallGameBinding access$getMBinding(HallGameFragment hallGameFragment) {
        return hallGameFragment.getMBinding();
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final int getTypePosition() {
        return this.typePosition;
    }

    public final void setTypePosition(int i) {
        this.typePosition = i;
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

    public final String getSize() {
        return this.size;
    }

    public final void setSize(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.size = str;
    }

    public final String getOrder() {
        return this.order;
    }

    public final void setOrder(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.order = str;
    }

    public final Job getJob() {
        Job job = this.job;
        if (job != null) {
            return job;
        }
        Intrinsics.throwUninitializedPropertyAccessException("job");
        return null;
    }

    public final void setJob(Job job) {
        Intrinsics.checkNotNullParameter(job, "<set-?>");
        this.job = job;
    }

    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BaseAdapter<GameBean, ItemHallGameBinding> getGameAdapter() {
        return (BaseAdapter) this.gameAdapter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BaseAdapter<TypeBean, ItemHallGameTypeBinding> getTypeAdapter() {
        return (BaseAdapter) this.typeAdapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        Bundle arguments = getArguments();
        this.type = arguments != null ? arguments.getInt("type", 0) : 0;
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                HallGameFragment.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getMBinding().rvGame.setAdapter(getGameAdapter());
        getGameAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                HallGameFragment.init$lambda$1(this.f$0);
            }
        });
        getMBinding().rvGame.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment.init.3
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrolled(recyclerView, dx, dy);
                GSYVideoManager.releaseAllVideos();
            }
        });
        HallGameFragment hallGameFragment = this;
        getVm().getCategory().observe(hallGameFragment, new HallGameFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment.init.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke2(num);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Integer num) {
                HallGameFragment.this.setPage(1);
                HallGameFragment.this.getGameAdapter().setNewInstance(null);
                if (HallGameFragment.this.getTypeId() == 0) {
                    HallGameFragment hallGameFragment2 = HallGameFragment.this;
                    Intrinsics.checkNotNull(num);
                    hallGameFragment2.setTypeId(num.intValue());
                    return;
                }
                HallGameFragment hallGameFragment3 = HallGameFragment.this;
                Intrinsics.checkNotNull(num);
                hallGameFragment3.setTypeId(num.intValue());
                int size = HallGameFragment.this.getTypeAdapter().getData().size();
                for (int i = 0; i < size; i++) {
                    ((TypeBean) HallGameFragment.this.getTypeAdapter().getItem(i)).setSelected(((TypeBean) HallGameFragment.this.getTypeAdapter().getItem(i)).getId() == HallGameFragment.this.getTypeId());
                    if (((TypeBean) HallGameFragment.this.getTypeAdapter().getItem(i)).getSelected()) {
                        HallGameFragment.this.setTypePosition(i);
                    }
                }
                HallGameFragment.access$getMBinding(HallGameFragment.this).srl.finishRefresh();
                HallGameFragment.access$getMBinding(HallGameFragment.this).srl.autoRefresh();
            }
        }));
        getVm().getAction().observe(hallGameFragment, new HallGameFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment.init.5
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
                if (num != null && num.intValue() == 8674) {
                    HallGameFragment.this.getVm().getAction().setValue(0);
                    RecyclerView.LayoutManager layoutManager = HallGameFragment.access$getMBinding(HallGameFragment.this).rvGame.getLayoutManager();
                    Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                    if (((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0) {
                        HallGameFragment.access$getMBinding(HallGameFragment.this).srl.autoRefresh();
                    } else {
                        HallGameFragment.access$getMBinding(HallGameFragment.this).rvGame.scrollToPosition(0);
                    }
                }
            }
        }));
        getType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(HallGameFragment this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Job.DefaultImpls.cancel$default(this$0.getJob(), (CancellationException) null, 1, (Object) null);
        this$0.page = 1;
        this$0.getGameAdapter().setNewInstance(null);
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(HallGameFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment$getType$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HallGameFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class C11481 extends Lambda implements Function1<List<TypeBean>, Unit> {
        C11481() {
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
            if (HallGameFragment.this.getTypeId() == 0) {
                it.get(0).setSelected(true);
                HallGameFragment.this.setTypeId(it.get(0).getId());
            } else {
                Iterator<TypeBean> it2 = it.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    TypeBean next = it2.next();
                    if (next.getId() == HallGameFragment.this.getTypeId()) {
                        next.setSelected(true);
                        break;
                    }
                }
            }
            HallGameFragment.this.getTypeAdapter().setNewInstance(it);
            HallGameFragment.access$getMBinding(HallGameFragment.this).rvType.setAdapter(HallGameFragment.this.getTypeAdapter());
            BaseAdapter typeAdapter = HallGameFragment.this.getTypeAdapter();
            final HallGameFragment hallGameFragment = HallGameFragment.this;
            typeAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment$getType$1$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    HallGameFragment.C11481.invoke$lambda$0(hallGameFragment, baseQuickAdapter, view, i);
                }
            });
            HallGameFragment.this.getData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void invoke$lambda$0(HallGameFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            if (this$0.checkClick()) {
                return;
            }
            Iterator it = this$0.getTypeAdapter().getData().iterator();
            while (it.hasNext()) {
                ((TypeBean) it.next()).setSelected(false);
            }
            this$0.setTypePosition(i);
            ((TypeBean) this$0.getTypeAdapter().getItem(i)).setSelected(true);
            this$0.setTypeId(((TypeBean) this$0.getTypeAdapter().getItem(i)).getId());
            HallGameFragment.access$getMBinding(this$0).srl.finishRefresh();
            HallGameFragment.access$getMBinding(this$0).srl.autoRefresh();
        }
    }

    private final void getType() {
        Repository.INSTANCE.getHomeGameType(this.type, new C11481(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment.getType.2
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
                HallGameFragment.this.netFail(it);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getData() {
        setJob(Repository.INSTANCE.getHallGame(this.page, this.typeId, this.order, this.size, new Function1<PageBean<GameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment.getData.1
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
                HallGameFragment.access$getMBinding(HallGameFragment.this).srl.finishRefresh();
                if (HallGameFragment.this.getPage() == 1) {
                    HallGameFragment.this.getGameAdapter().setNewInstance(it.getList());
                } else {
                    HallGameFragment.this.getGameAdapter().addData((Collection) it.getList());
                }
                HallGameFragment hallGameFragment = HallGameFragment.this;
                hallGameFragment.setPage(hallGameFragment.getPage() + 1);
                hallGameFragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(HallGameFragment.this.getGameAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    HallGameFragment.this.getGameAdapter().getLoadMoreModule().loadMoreComplete();
                }
                RecyclerView.LayoutManager layoutManager = HallGameFragment.access$getMBinding(HallGameFragment.this).rvType.getLayoutManager();
                Intrinsics.checkNotNull(layoutManager);
                layoutManager.scrollToPosition(HallGameFragment.this.getTypePosition());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.HallGameFragment.getData.2
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
                HallGameFragment.access$getMBinding(HallGameFragment.this).srl.finishRefresh(false);
                HallGameFragment.this.getGameAdapter().getLoadMoreModule().loadMoreFail();
                HallGameFragment.this.netFail(it);
            }
        }));
    }
}
