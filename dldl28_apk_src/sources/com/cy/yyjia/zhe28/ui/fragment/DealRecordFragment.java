package com.cy.yyjia.zhe28.ui.fragment;

import android.content.Intent;
import android.view.View;
import androidx.autofill.HintConstants;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.FragmentDealRecordBinding;
import com.cy.yyjia.zhe28.databinding.ItemDealDickerBinding;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.domain.DickerMessageBean;
import com.cy.yyjia.zhe28.domain.FilterBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity;
import com.cy.yyjia.zhe28.ui.adapter.DealAdapter;
import com.cy.yyjia.zhe28.ui.adapter.PicAdapter;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.cy.yyjia.zhe28.ui.dialog.DickerDialog;
import com.cy.yyjia.zhe28.ui.dialog.WaitDialog;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: DealRecordFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020)J\u0006\u0010+\u001a\u00020)J\u0006\u0010,\u001a\u00020)J\b\u0010-\u001a\u00020)H\u0016J\u0006\u0010.\u001a\u00020)J\u0006\u0010/\u001a\u00020)R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R'\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\t\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\t\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001b\u0010#\u001a\u00020$8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\t\u001a\u0004\b%\u0010&¨\u00060"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/DealRecordFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentDealRecordBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/ui/adapter/DealAdapter;", "getAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/DealAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "dickerAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/DickerMessageBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemDealDickerBinding;", "getDickerAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "dickerAdapter$delegate", "page", "", "getPage", "()I", "setPage", "(I)V", "status", "", "Lcom/cy/yyjia/zhe28/domain/FilterBean;", "getStatus", "()Ljava/util/List;", "status$delegate", HintConstants.AUTOFILL_HINT_USERNAME, "", "getUsername", "()Ljava/lang/String;", "setUsername", "(Ljava/lang/String;)V", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "delayRefresh", "", "getData", "getDickerData", "getNewData", "init", "initRv", "showType", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DealRecordFragment extends BaseFragment<FragmentDealRecordBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: dickerAdapter$delegate, reason: from kotlin metadata */
    private final Lazy dickerAdapter;
    private int page;

    /* JADX INFO: renamed from: status$delegate, reason: from kotlin metadata */
    private final Lazy status;
    private String username;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public DealRecordFragment() {
        super(R.layout.fragment_deal_record);
        this.adapter = LazyKt.lazy(new Function0<DealAdapter>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DealAdapter invoke() {
                return new DealAdapter();
            }
        });
        this.dickerAdapter = LazyKt.lazy(new Function0<BaseAdapter<DickerMessageBean, ItemDealDickerBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$dickerAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<DickerMessageBean, ItemDealDickerBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_deal_dicker, new Function3<BaseDataBindingHolder<ItemDealDickerBinding>, Integer, DickerMessageBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$dickerAdapter$2.1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemDealDickerBinding> baseDataBindingHolder, Integer num, DickerMessageBean dickerMessageBean) {
                        invoke(baseDataBindingHolder, num.intValue(), dickerMessageBean);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemDealDickerBinding> h, int i, DickerMessageBean dickerMessageBean) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemDealDickerBinding itemDealDickerBinding = (ItemDealDickerBinding) h.getDataBinding();
                        if (itemDealDickerBinding != null) {
                            itemDealDickerBinding.rv.setLayoutManager(new GridLayoutManager(itemDealDickerBinding.rv.getContext(), 3));
                            itemDealDickerBinding.rv.setAdapter(new PicAdapter());
                        }
                    }
                });
            }
        });
        this.status = LazyKt.lazy(new Function0<List<FilterBean>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$status$2
            @Override // kotlin.jvm.functions.Function0
            public final List<FilterBean> invoke() {
                return CollectionsKt.mutableListOf(new FilterBean("全部", ""), new FilterBean("待审核", "pending"), new FilterBean("出售中", "sell"), new FilterBean("已出售", "selled"), new FilterBean("待确认", "confirm"), new FilterBean("被驳回", "beback"), new FilterBean("已购买", "got"), new FilterBean("已下架", "outline"), new FilterBean("收藏", "collection"), new FilterBean("议价中", ""));
            }
        });
        this.page = 1;
        this.username = Constant.INSTANCE.getUsername();
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$vm$2
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

    public static final /* synthetic */ FragmentDealRecordBinding access$getMBinding(DealRecordFragment dealRecordFragment) {
        return dealRecordFragment.getMBinding();
    }

    public final DealAdapter getAdapter() {
        return (DealAdapter) this.adapter.getValue();
    }

    public final BaseAdapter<DickerMessageBean, ItemDealDickerBinding> getDickerAdapter() {
        return (BaseAdapter) this.dickerAdapter.getValue();
    }

    public final List<FilterBean> getStatus() {
        return (List) this.status.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final String getUsername() {
        return this.username;
    }

    public final void setUsername(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.username = str;
    }

    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getVm().getUser().observe(this, new DealRecordFragment$sam$androidx_lifecycle_Observer$0(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment.init.1
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
                if (!Constant.INSTANCE.getLogged() || Intrinsics.areEqual(DealRecordFragment.this.getUsername(), userBean.getUserName())) {
                    return;
                }
                DealRecordFragment.this.setUsername(userBean.getUserName());
                DealRecordFragment.this.setPage(1);
                DealRecordFragment.this.getAdapter().setNewInstance(null);
                DealRecordFragment.this.getData();
            }
        }));
        Iterator<FilterBean> it = getStatus().iterator();
        while (it.hasNext()) {
            getMBinding().tab.addTab(getMBinding().tab.newTab().setText(it.next().getName()));
        }
        TabLayout tab = getMBinding().tab;
        Intrinsics.checkNotNullExpressionValue(tab, "tab");
        initTab(tab, 14.0f, 16.0f, true);
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                DealRecordFragment.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getMBinding().tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment.init.3
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab2) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab2) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab2) {
                Intrinsics.checkNotNullParameter(tab2, "tab");
                DealRecordFragment.this.getNewData();
                DealRecordFragment.access$getMBinding(DealRecordFragment.this).btn.setText(tab2.getText());
            }
        });
        initRv();
        getMBinding().btn.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DealRecordFragment.init$lambda$1(this.f$0, view);
            }
        });
        getNewData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(DealRecordFragment this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.getNewData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(DealRecordFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showType();
    }

    public final void initRv() {
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                DealRecordFragment.initRv$lambda$2(this.f$0);
            }
        });
        getAdapter().addChildClickViewIds(R.id.tv_offset, R.id.tv_modify, R.id.tv_receive);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DealRecordFragment.initRv$lambda$4(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().setMyEmptyView("deal");
        getDickerAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                DealRecordFragment.initRv$lambda$5(this.f$0);
            }
        });
        getDickerAdapter().addChildClickViewIds(R.id.tv_dicker_cancel, R.id.tv_dicker_modify, R.id.tv_dicker_refuse, R.id.tv_receive, R.id.tv_dicker_agree);
        getDickerAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$$ExternalSyntheticLambda7
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DealRecordFragment.initRv$lambda$6(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRv$lambda$2(DealRecordFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRv$lambda$4(final DealRecordFragment this$0, BaseQuickAdapter baseQuickAdapter, View v, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.tv_offset) {
            new QuickDialog(this$0.getMContext(), R.layout.dialog_deal_offset).setOnClickListener(R.id.tv_go, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$$ExternalSyntheticLambda3
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    DealRecordFragment.initRv$lambda$4$lambda$3(this.f$0, i, baseDialog, view);
                }
            }).show();
            return;
        }
        if (v.getId() == R.id.tv_modify) {
            Intent intent = new Intent(this$0.getMContext(), (Class<?>) DealSellInfoActivity.class);
            intent.putExtra("id", this$0.getAdapter().getItem(i).getId());
            this$0.getMContext().startActivity(intent);
        } else if (v.getId() == R.id.tv_receive) {
            this$0.getAdapter().confirm(i, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$2$2
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
                    this.this$0.getNewData();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRv$lambda$4$lambda$3(final DealRecordFragment this$0, int i, final BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getAdapter().offset(i, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$2$1$1
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
                baseDialog.dismiss();
                this$0.getNewData();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRv$lambda$5(DealRecordFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getDickerData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initRv$lambda$6(final DealRecordFragment this$0, BaseQuickAdapter baseQuickAdapter, View v, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.tv_dicker_refuse) {
            new ConfirmDialog(this$0.getMContext()).setTip("确定要拒绝本次议价申请吗？").setBtnText("确定").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$1
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
                    int id2 = this.this$0.getDickerAdapter().getItem(i).getId();
                    final DealRecordFragment dealRecordFragment = this.this$0;
                    Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$1.1
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
                            dealRecordFragment.toast(it.getMsg());
                            if (it.getCode() == 200) {
                                dealRecordFragment.delayRefresh();
                            }
                        }
                    };
                    final DealRecordFragment dealRecordFragment2 = this.this$0;
                    repository.agreeDicker(id2, 0, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$1.2
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
                            dealRecordFragment2.netFail(it);
                        }
                    });
                }
            }).show();
            return;
        }
        if (id != R.id.tv_receive) {
            switch (id) {
                case R.id.tv_dicker_agree /* 2131362686 */:
                    new ConfirmDialog(this$0.getMContext()).setTip("确定要同意本次议价申请吗？").setBtnText("确定").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$2
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
                            int id2 = this.this$0.getDickerAdapter().getItem(i).getId();
                            final DealRecordFragment dealRecordFragment = this.this$0;
                            Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$2.1
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
                                    dealRecordFragment.toast(it.getMsg());
                                    if (it.getCode() == 200) {
                                        dealRecordFragment.delayRefresh();
                                    }
                                }
                            };
                            final DealRecordFragment dealRecordFragment2 = this.this$0;
                            repository.agreeDicker(id2, 1, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$2.2
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
                                    dealRecordFragment2.netFail(it);
                                }
                            });
                        }
                    }).show();
                    break;
                case R.id.tv_dicker_cancel /* 2131362687 */:
                    new ConfirmDialog(this$0.getMContext()).setTip("确定要取消本次议价申请吗？").setBtnText("确定").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$3
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
                            int id2 = this.this$0.getDickerAdapter().getItem(i).getId();
                            final DealRecordFragment dealRecordFragment = this.this$0;
                            Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$3.1
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
                                    dealRecordFragment.toast(it.getMsg());
                                    if (it.getCode() == 200) {
                                        dealRecordFragment.delayRefresh();
                                    }
                                }
                            };
                            final DealRecordFragment dealRecordFragment2 = this.this$0;
                            repository.cancelDicker(id2, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$3.2
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
                                    dealRecordFragment2.netFail(it);
                                }
                            });
                        }
                    }).show();
                    break;
                case R.id.tv_dicker_modify /* 2131362688 */:
                    new DickerDialog(this$0.getMContext()).setListener(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(String str) {
                            invoke2(str);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String price) {
                            Intrinsics.checkNotNullParameter(price, "price");
                            Repository repository = Repository.INSTANCE;
                            int id2 = this.this$0.getDickerAdapter().getItem(i).getId();
                            final DealRecordFragment dealRecordFragment = this.this$0;
                            Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$4.1
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
                                    dealRecordFragment.toast(it.getMsg());
                                    dealRecordFragment.delayRefresh();
                                }
                            };
                            final DealRecordFragment dealRecordFragment2 = this.this$0;
                            repository.modifyDicker(id2, price, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$4.2
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
                                    dealRecordFragment2.netFail(it);
                                }
                            });
                        }
                    }).show();
                    break;
            }
            return;
        }
        Repository.INSTANCE.confirmDeal(this$0.getDickerAdapter().getItem(i).getAccountTreadId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$5
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
                if (it.getCode() == 200) {
                    this.this$0.delayRefresh();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$initRv$4$6
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

    public final void getNewData() {
        this.page = 1;
        getAdapter().setNewInstance(null);
        if (Intrinsics.areEqual(getStatus().get(getMBinding().tab.getSelectedTabPosition()).getName(), "议价中")) {
            getMBinding().rv.setAdapter(getDickerAdapter());
            getDickerData();
        } else {
            getMBinding().rv.setAdapter(getAdapter());
            getData();
        }
    }

    public final void getData() {
        Repository.INSTANCE.getDealRecord(this.page, getStatus().get(getMBinding().tab.getSelectedTabPosition()).getData(), new Function1<PageBean<DealBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment.getData.1
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
                DealRecordFragment.access$getMBinding(DealRecordFragment.this).srl.finishRefresh();
                if (DealRecordFragment.this.getPage() == 1) {
                    DealRecordFragment.this.getAdapter().setNewInstance(it.getList());
                } else {
                    DealRecordFragment.this.getAdapter().addData((Collection) it.getList());
                }
                DealRecordFragment dealRecordFragment = DealRecordFragment.this;
                dealRecordFragment.setPage(dealRecordFragment.getPage() + 1);
                dealRecordFragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(DealRecordFragment.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    DealRecordFragment.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment.getData.2
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
                DealRecordFragment.access$getMBinding(DealRecordFragment.this).srl.finishRefresh(false);
                DealRecordFragment.this.getAdapter().getLoadMoreModule().loadMoreFail();
                DealRecordFragment dealRecordFragment = DealRecordFragment.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                dealRecordFragment.log(localizedMessage);
            }
        });
    }

    public final void getDickerData() {
        Repository.INSTANCE.getMessageList3(this.page, new Function1<PageBean<DickerMessageBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment.getDickerData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<DickerMessageBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<DickerMessageBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                DealRecordFragment.access$getMBinding(DealRecordFragment.this).srl.finishRefresh();
                if (DealRecordFragment.this.getPage() == 1) {
                    DealRecordFragment.this.getDickerAdapter().setNewInstance(it.getList());
                } else {
                    DealRecordFragment.this.getDickerAdapter().addData(it.getList());
                }
                DealRecordFragment dealRecordFragment = DealRecordFragment.this;
                dealRecordFragment.setPage(dealRecordFragment.getPage() + 1);
                dealRecordFragment.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(DealRecordFragment.this.getDickerAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    DealRecordFragment.this.getDickerAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment.getDickerData.2
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
                DealRecordFragment.access$getMBinding(DealRecordFragment.this).srl.finishRefresh(false);
                DealRecordFragment.this.getDickerAdapter().getLoadMoreModule().loadMoreFail();
                DealRecordFragment dealRecordFragment = DealRecordFragment.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                dealRecordFragment.log(localizedMessage);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void delayRefresh() {
        WaitDialog waitDialog = (WaitDialog) new WaitDialog(getMContext()).setCancelable(false);
        waitDialog.show();
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(waitDialog, this, null), 3, null);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$delayRefresh$1, reason: invalid class name */
    /* JADX INFO: compiled from: DealRecordFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$delayRefresh$1", f = "DealRecordFragment.kt", i = {}, l = {264}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ WaitDialog $dialog;
        int label;
        final /* synthetic */ DealRecordFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(WaitDialog waitDialog, DealRecordFragment dealRecordFragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$dialog = waitDialog;
            this.this$0 = dealRecordFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$dialog, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(300L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.$dialog.hide();
            this.this$0.getNewData();
            return Unit.INSTANCE;
        }
    }

    public final void showType() {
        getStatus().get(getMBinding().tab.getSelectedTabPosition()).setSelected(true);
        BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_deal_record_type, getStatus());
        final BaseDialog baseDialogShow = new QuickDialog(getMContext(), R.layout.dialog_deal_record_type).setAdapter(R.id.rv, baseAdapter).show();
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealRecordFragment$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DealRecordFragment.showType$lambda$7(baseDialogShow, this, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showType$lambda$7(BaseDialog baseDialog, DealRecordFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "view");
        baseDialog.dismiss();
        TabLayout.Tab tabAt = this$0.getMBinding().tab.getTabAt(i);
        Intrinsics.checkNotNull(tabAt);
        tabAt.select();
        TabLayout.Tab tabAt2 = this$0.getMBinding().tab.getTabAt(0);
        Intrinsics.checkNotNull(tabAt2);
        this$0.getMBinding().tab.smoothScrollTo(tabAt2.view.getWidth() * i, 0);
    }
}
