package com.cy.yyjia.zhe28.ui.fragment;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.autofill.HintConstants;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
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
import com.cy.yyjia.zhe28.base.FastDialog;
import com.cy.yyjia.zhe28.databinding.FragmentDealIndexBinding;
import com.cy.yyjia.zhe28.databinding.FragmentRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemDealDickerBinding;
import com.cy.yyjia.zhe28.databinding.ItemHomeTabBinding;
import com.cy.yyjia.zhe28.databinding.LayoutDealAllBinding;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.domain.DealParamBean;
import com.cy.yyjia.zhe28.domain.DickerMessageBean;
import com.cy.yyjia.zhe28.domain.FilterBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.TabBean;
import com.cy.yyjia.zhe28.domain.TradeRuleBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.ui.activity.ItemTradeFragment;
import com.cy.yyjia.zhe28.ui.activity.ItemTradeRecordActivity;
import com.cy.yyjia.zhe28.ui.activity.LoginActivity;
import com.cy.yyjia.zhe28.ui.adapter.DealAdapter;
import com.cy.yyjia.zhe28.ui.adapter.PicAdapter;
import com.cy.yyjia.zhe28.ui.dialog.ConfirmDialog;
import com.cy.yyjia.zhe28.ui.dialog.DealFilterDialog;
import com.cy.yyjia.zhe28.ui.dialog.DickerDialog;
import com.cy.yyjia.zhe28.ui.dialog.RuleDialog;
import com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.google.android.material.appbar.AppBarLayout;
import com.lzy.okgo.cache.CacheEntity;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
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
import kotlin.coroutines.jvm.internal.Boxing;
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

/* JADX INFO: compiled from: DealIndexFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002 !B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018¨\u0006\""}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/DealIndexFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentDealIndexBinding;", "Landroid/view/View$OnClickListener;", "()V", "sortTypes", "Ljava/util/ArrayList;", "Lcom/cy/yyjia/zhe28/domain/FilterBean;", "Lkotlin/collections/ArrayList;", "getSortTypes", "()Ljava/util/ArrayList;", "tabAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/TabBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemHomeTabBinding;", "getTabAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "title", "", "getTitle", "()Ljava/util/List;", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "Lkotlin/Lazy;", "init", "", "onClick", "v", "Landroid/view/View;", "ChildFragment1", "ChildFragment2", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DealIndexFragment extends BaseFragment<FragmentDealIndexBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private final ArrayList<FilterBean> sortTypes;
    private final BaseAdapter<TabBean, ItemHomeTabBinding> tabAdapter;
    private final List<TabBean> title;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public DealIndexFragment() {
        super(R.layout.fragment_deal_index);
        this.sortTypes = CollectionsKt.arrayListOf(new FilterBean("最新发布", "dateline", SocialConstants.PARAM_APP_DESC, true), new FilterBean("官方斗罗账号", "dateline", SocialConstants.PARAM_APP_DESC), new FilterBean("回归号", "dateline", SocialConstants.PARAM_APP_DESC), new FilterBean("开局号", "dateline", SocialConstants.PARAM_APP_DESC), new FilterBean("价格最低", "sellMoney", "asc"), new FilterBean("价格最高", "sellMoney", SocialConstants.PARAM_APP_DESC));
        List<TabBean> listMutableListOf = CollectionsKt.mutableListOf(new TabBean("购买", true), new TabBean("出售"), new TabBean("斗罗材料"), new TabBean("议价"), new TabBean("记录"), new TabBean("官方福利"));
        this.title = listMutableListOf;
        this.tabAdapter = new BaseAdapter<>(R.layout.item_home_tab, listMutableListOf);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$vm$2
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

    public static final /* synthetic */ FragmentDealIndexBinding access$getMBinding(DealIndexFragment dealIndexFragment) {
        return dealIndexFragment.getMBinding();
    }

    public final ArrayList<FilterBean> getSortTypes() {
        return this.sortTypes;
    }

    public final List<TabBean> getTitle() {
        return this.title;
    }

    public final BaseAdapter<TabBean, ItemHomeTabBinding> getTabAdapter() {
        return this.tabAdapter;
    }

    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getVm().getDealParam().setValue(new DealParamBean(null, null, null, null, null, null, null, 0, 0, 0, 0, 0, 4095, null));
        DealIndexFragment dealIndexFragment = this;
        getVm().getDealParam().observe(dealIndexFragment, new DealIndexFragment$sam$androidx_lifecycle_Observer$0(new Function1<DealParamBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment.init.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DealParamBean dealParamBean) {
                invoke2(dealParamBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DealParamBean dealParamBean) {
                if (dealParamBean.getSiteaccount() == 1) {
                    for (FilterBean filterBean : DealIndexFragment.this.getSortTypes()) {
                        filterBean.setSelected(Intrinsics.areEqual(filterBean.getName(), "官方捡漏"));
                    }
                }
                FragmentDealIndexBinding fragmentDealIndexBindingAccess$getMBinding = DealIndexFragment.access$getMBinding(DealIndexFragment.this);
                DealParamBean value = DealIndexFragment.this.getVm().getDealParam().getValue();
                Intrinsics.checkNotNull(value);
                fragmentDealIndexBindingAccess$getMBinding.setKeyword(value.getKeyword());
            }
        }));
        getVm().getShowKeyboard().observe(dealIndexFragment, new DealIndexFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment.init.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$init$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: DealIndexFragment.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
            @DebugMetadata(c = "com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$init$2$1", f = "DealIndexFragment.kt", i = {}, l = {89}, m = "invokeSuspend", n = {}, s = {})
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;
                final /* synthetic */ DealIndexFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(DealIndexFragment dealIndexFragment, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = dealIndexFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, continuation);
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
                        if (DelayKt.delay(100L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    DealIndexFragment.access$getMBinding(this.this$0).et.requestFocus();
                    Object systemService = this.this$0.getMContext().getSystemService("input_method");
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                    ((InputMethodManager) systemService).showSoftInput(DealIndexFragment.access$getMBinding(this.this$0).et, 1);
                    this.this$0.getVm().getShowKeyboard().postValue(Boxing.boxBoolean(false));
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
                Intrinsics.checkNotNull(bool);
                if (bool.booleanValue()) {
                    BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(DealIndexFragment.this), null, null, new AnonymousClass1(DealIndexFragment.this, null), 3, null);
                }
            }
        }));
        getMBinding().setOnClick(this);
        getMBinding().vp2.setAdapter(new FragmentStateAdapter(this) { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment.init.3
            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return 6;
            }

            {
                super(this);
            }

            @Override // androidx.viewpager2.adapter.FragmentStateAdapter
            public Fragment createFragment(int position) {
                if (position == 0) {
                    return new ChildFragment1();
                }
                if (position == 1) {
                    return new DealSellFragment();
                }
                if (position == 2) {
                    return new ItemTradeFragment();
                }
                if (position == 3) {
                    return new ChildFragment2();
                }
                if (position == 4) {
                    return new DealRecordFragment();
                }
                return new DealFragment();
            }
        });
        getMBinding().vp2.setUserInputEnabled(false);
        getMBinding().rv.setAdapter(this.tabAdapter);
        this.tabAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DealIndexFragment.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().et.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$$ExternalSyntheticLambda1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return DealIndexFragment.init$lambda$1(this.f$0, textView, i, keyEvent);
            }
        });
        getMBinding().abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$$ExternalSyntheticLambda2
            @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
            public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                DealIndexFragment.init$lambda$2(this.f$0, appBarLayout, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(DealIndexFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (!Constant.INSTANCE.getLogged() && (i == 1 || i == 2 || i == 3)) {
            this$0.startActivity(LoginActivity.class);
            return;
        }
        this$0.getMBinding().setKeyword("");
        Iterator<TabBean> it = this$0.tabAdapter.getData().iterator();
        while (it.hasNext()) {
            it.next().setSelected(false);
        }
        this$0.tabAdapter.getItem(i).setSelected(true);
        this$0.getMBinding().vp2.setCurrentItem(i, false);
        this$0.getMBinding().setPosition(i);
        DealParamBean value = this$0.getVm().getDealParam().getValue();
        Intrinsics.checkNotNull(value);
        value.setPosition(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean init$lambda$1(DealIndexFragment this$0, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i != 3) {
            return false;
        }
        this$0.hideSoftKeyboard();
        DealParamBean value = this$0.getVm().getDealParam().getValue();
        Intrinsics.checkNotNull(value);
        String keyword = this$0.getMBinding().getKeyword();
        Intrinsics.checkNotNull(keyword);
        value.setKeyword(keyword);
        MutableLiveData<DealParamBean> dealParam = this$0.getVm().getDealParam();
        DealParamBean value2 = this$0.getVm().getDealParam().getValue();
        Intrinsics.checkNotNull(value2);
        dealParam.setValue(value2);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(DealIndexFragment this$0, AppBarLayout appBarLayout, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMBinding().setFolder(Math.abs(i) >= appBarLayout.getTotalScrollRange());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.iv_rule /* 2131362190 */:
                if (getMBinding().vp2.getCurrentItem() == 2) {
                    doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment.onClick.1
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
                            DealIndexFragment.this.startActivity(ItemTradeRecordActivity.class);
                        }
                    });
                } else {
                    Util.openProtocol(getMContext(), "交易说明", "transactionInstructions");
                }
                break;
            case R.id.iv_search /* 2131362191 */:
                getMBinding().abl.setExpanded(true);
                break;
            case R.id.tv_filter /* 2131362700 */:
                DealFilterDialog dealFilterDialog = new DealFilterDialog(getMContext(), new Function2<List<FilterBean>, DealParamBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment.onClick.2
                    {
                        super(2);
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(List<FilterBean> l, DealParamBean d) {
                        Intrinsics.checkNotNullParameter(l, "l");
                        Intrinsics.checkNotNullParameter(d, "d");
                        DealIndexFragment.this.getVm().getDealParam().setValue(d);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(List<FilterBean> list, DealParamBean dealParamBean) {
                        invoke2(list, dealParamBean);
                        return Unit.INSTANCE;
                    }
                });
                ArrayList<FilterBean> arrayList = this.sortTypes;
                DealParamBean value = getVm().getDealParam().getValue();
                Intrinsics.checkNotNull(value);
                dealFilterDialog.setData(arrayList, value).show();
                break;
        }
    }

    /* JADX INFO: compiled from: DealIndexFragment.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010,\u001a\u00020-J\u0006\u0010\u0013\u001a\u00020-J\u0006\u0010.\u001a\u00020-J\b\u0010/\u001a\u00020-H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR#\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u00118FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0013\u0010\u0014R#\u0010\u0016\u001a\n \u0012*\u0004\u0018\u00010\u00170\u00178FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\t\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001b\u0010'\u001a\u00020(8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\t\u001a\u0004\b)\u0010*¨\u00060"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/DealIndexFragment$ChildFragment1;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/ui/adapter/DealAdapter;", "getAdapter", "()Lcom/cy/yyjia/zhe28/ui/adapter/DealAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "all", "", "getAll", "()Z", "setAll", "(Z)V", "empty", "Landroid/view/View;", "kotlin.jvm.PlatformType", "getEmpty", "()Landroid/view/View;", "empty$delegate", CacheEntity.HEAD, "Lcom/cy/yyjia/zhe28/databinding/LayoutDealAllBinding;", "getHead", "()Lcom/cy/yyjia/zhe28/databinding/LayoutDealAllBinding;", "head$delegate", "page", "", "getPage", "()I", "setPage", "(I)V", HintConstants.AUTOFILL_HINT_USERNAME, "", "getUsername", "()Ljava/lang/String;", "setUsername", "(Ljava/lang/String;)V", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "getData", "", "getSearchGame", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ChildFragment1 extends BaseFragment<FragmentRvBinding> {
        public static final int $stable = 8;

        /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
        private final Lazy adapter;
        private boolean all;

        /* JADX INFO: renamed from: empty$delegate, reason: from kotlin metadata */
        private final Lazy empty;

        /* JADX INFO: renamed from: head$delegate, reason: from kotlin metadata */
        private final Lazy head;
        private int page;
        private String username;

        /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
        private final Lazy vm;

        public ChildFragment1() {
            super(R.layout.fragment_rv);
            this.page = 1;
            this.adapter = LazyKt.lazy(new Function0<DealAdapter>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$adapter$2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final DealAdapter invoke() {
                    return new DealAdapter();
                }
            });
            this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$vm$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final MainViewModel invoke() {
                    return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
                }
            });
            this.head = LazyKt.lazy(new Function0<LayoutDealAllBinding>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$head$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final LayoutDealAllBinding invoke() {
                    return (LayoutDealAllBinding) DataBindingUtil.inflate(this.this$0.getLayoutInflater(), R.layout.layout_deal_all, new FrameLayout(this.this$0.getMContext()), false);
                }
            });
            this.empty = LazyKt.lazy(new Function0<View>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$empty$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final View invoke() {
                    return this.this$0.getLayoutInflater().inflate(R.layout.layout_deal_index_empty, new FrameLayout(this.this$0.getMContext()));
                }
            });
            this.username = Constant.INSTANCE.getUsername();
        }

        public final int getPage() {
            return this.page;
        }

        public final void setPage(int i) {
            this.page = i;
        }

        public final DealAdapter getAdapter() {
            return (DealAdapter) this.adapter.getValue();
        }

        public final MainViewModel getVm() {
            return (MainViewModel) this.vm.getValue();
        }

        public final LayoutDealAllBinding getHead() {
            return (LayoutDealAllBinding) this.head.getValue();
        }

        public final View getEmpty() {
            return (View) this.empty.getValue();
        }

        public final boolean getAll() {
            return this.all;
        }

        public final void setAll(boolean z) {
            this.all = z;
        }

        public final String getUsername() {
            return this.username;
        }

        public final void setUsername(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.username = str;
        }

        @Override // com.cy.yyjia.zhe28.base.BaseFragment
        public void init() {
            getMBinding().ll.addView(getHead().getRoot(), 0);
            ChildFragment1 childFragment1 = this;
            getVm().getUser().observe(childFragment1, new DealIndexFragment$sam$androidx_lifecycle_Observer$0(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$init$1
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
                    if (Intrinsics.areEqual(this.this$0.getUsername(), userBean.getUserName())) {
                        return;
                    }
                    this.this$0.setUsername(userBean.getUserName());
                    this.this$0.setPage(1);
                    this.this$0.getAdapter().setNewInstance(null);
                    this.this$0.getData();
                }
            }));
            getHead().btnDl.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DealIndexFragment.ChildFragment1.init$lambda$4(this.f$0, view);
                }
            });
            getHead().btnAll.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DealIndexFragment.ChildFragment1.init$lambda$5(this.f$0, view);
                }
            });
            getMBinding().rv.setAdapter(getAdapter());
            getAdapter().addChildClickViewIds(R.id.iv_switch);
            getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$$ExternalSyntheticLambda6
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    DealIndexFragment.ChildFragment1.init$lambda$6(this.f$0, baseQuickAdapter, view, i);
                }
            });
            getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$$ExternalSyntheticLambda7
                @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
                public final void onLoadMore() {
                    DealIndexFragment.ChildFragment1.init$lambda$7(this.f$0);
                }
            });
            getVm().getDealParam().observe(childFragment1, new DealIndexFragment$sam$androidx_lifecycle_Observer$0(new Function1<DealParamBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$init$6
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DealParamBean dealParamBean) {
                    invoke2(dealParamBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DealParamBean dealParamBean) {
                    if (dealParamBean.getPosition() == 0) {
                        this.this$0.getMBinding().srl.autoRefresh();
                    }
                }
            }));
            getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$$ExternalSyntheticLambda8
                @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
                public final void onRefresh(RefreshLayout refreshLayout) {
                    DealIndexFragment.ChildFragment1.init$lambda$8(this.f$0, refreshLayout);
                }
            });
            m6617getEmpty();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$4(final ChildFragment1 this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            new FastDialog(this$0.getMContext()).setContentView(R.layout.dialog_deal_dolo).setOnClickListener(R.id.iv_close, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$$ExternalSyntheticLambda0
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view2) {
                    baseDialog.dismiss();
                }
            }).setOnClickListener(R.id.tv_rule, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$$ExternalSyntheticLambda1
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view2) {
                    DealIndexFragment.ChildFragment1.init$lambda$4$lambda$1(this.f$0, baseDialog, view2);
                }
            }).setOnClickListener(R.id.btn_recycle, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$$ExternalSyntheticLambda2
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view2) {
                    DealIndexFragment.ChildFragment1.init$lambda$4$lambda$2(this.f$0, baseDialog, view2);
                }
            }).setOnClickListener(R.id.btn_buy, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$$ExternalSyntheticLambda3
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view2) {
                    DealIndexFragment.ChildFragment1.init$lambda$4$lambda$3(this.f$0, baseDialog, view2);
                }
            }).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$4$lambda$1(final ChildFragment1 this$0, BaseDialog baseDialog, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Repository.INSTANCE.getDealRule(new Function1<TradeRuleBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$init$2$2$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TradeRuleBean tradeRuleBean) {
                    invoke2(tradeRuleBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TradeRuleBean it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    new RuleDialog(this.this$0.getMContext()).setText(it.getDolo()).show();
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$init$2$2$2
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

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$4$lambda$2(final ChildFragment1 this$0, final BaseDialog baseDialog, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            new ConfirmDialog(this$0.getMContext()).setTitle("联系客服").setTip("联系客服咨询回收账号").setBtnText("联系客服").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$init$2$3$1
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
                    Util.toService(this.this$0.getMContext());
                    baseDialog.dismiss();
                }
            }).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$4$lambda$3(ChildFragment1 this$0, BaseDialog baseDialog, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.all = false;
            DealParamBean value = this$0.getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value);
            value.setIsopen(0);
            DealParamBean value2 = this$0.getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value2);
            value2.setIsregress(0);
            DealParamBean value3 = this$0.getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value3);
            value3.setSiteaccount(0);
            DealParamBean value4 = this$0.getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value4);
            value4.setIsdolo(1);
            MutableLiveData<DealParamBean> dealParam = this$0.getVm().getDealParam();
            DealParamBean value5 = this$0.getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value5);
            dealParam.setValue(value5);
            baseDialog.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$5(ChildFragment1 this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.all = true;
            DealParamBean value = this$0.getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value);
            value.setIsopen(0);
            DealParamBean value2 = this$0.getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value2);
            value2.setIsregress(0);
            DealParamBean value3 = this$0.getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value3);
            value3.setSiteaccount(0);
            DealParamBean value4 = this$0.getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value4);
            value4.setIsdolo(0);
            MutableLiveData<DealParamBean> dealParam = this$0.getVm().getDealParam();
            DealParamBean value5 = this$0.getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value5);
            dealParam.setValue(value5);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$6(ChildFragment1 this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            this$0.getSearchGame();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$7(ChildFragment1 this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$8(ChildFragment1 this$0, RefreshLayout it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(it, "it");
            this$0.page = 1;
            this$0.getAdapter().setNewInstance(null);
            this$0.getData();
        }

        public final void getData() {
            Repository repository = Repository.INSTANCE;
            int i = this.page;
            DealParamBean value = getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value);
            String order = value.getOrder();
            DealParamBean value2 = getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value2);
            String sort = value2.getSort();
            DealParamBean value3 = getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value3);
            String keyword = value3.getKeyword();
            DealParamBean value4 = getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value4);
            String min = value4.getMin();
            DealParamBean value5 = getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value5);
            String max = value5.getMax();
            DealParamBean value6 = getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value6);
            String server = value6.getServer();
            DealParamBean value7 = getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value7);
            int siteaccount = value7.getSiteaccount();
            DealParamBean value8 = getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value8);
            int isregress = value8.getIsregress();
            DealParamBean value9 = getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value9);
            int isopen = value9.getIsopen();
            Function1<PageBean<DealBean>, Unit> function1 = new Function1<PageBean<DealBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$getData$1
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
                    this.this$0.getMBinding().srl.finishRefresh();
                    if (this.this$0.getPage() == 1) {
                        if (it.getList() != null && it.getList().size() > 0) {
                            it.getList().get(0).setTopButton(true);
                        }
                        this.this$0.getAdapter().setNewInstance(it.getList());
                    } else {
                        this.this$0.getAdapter().addData((Collection) it.getList());
                    }
                    DealIndexFragment.ChildFragment1 childFragment1 = this.this$0;
                    childFragment1.setPage(childFragment1.getPage() + 1);
                    childFragment1.getPage();
                    if (it.getCurrent_page() >= it.getLast_page()) {
                        BaseLoadMoreModule.loadMoreEnd$default(this.this$0.getAdapter().getLoadMoreModule(), false, 1, null);
                    } else {
                        this.this$0.getAdapter().getLoadMoreModule().loadMoreComplete();
                    }
                }
            };
            Function1<Exception, Unit> function12 = new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$getData$2
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
                    DealIndexFragment.ChildFragment1 childFragment1 = this.this$0;
                    String localizedMessage = it.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    childFragment1.log(localizedMessage);
                }
            };
            boolean z = this.all;
            DealParamBean value10 = getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value10);
            repository.getDealList(i, "newlist", order, sort, keyword, min, max, server, siteaccount, isregress, isopen, function1, function12, z, value10.getIsdolo());
        }

        /* JADX INFO: renamed from: getEmpty, reason: collision with other method in class */
        public final void m6617getEmpty() {
            Repository.INSTANCE.getHotDealGame(new DealIndexFragment$ChildFragment1$getEmpty$1(this), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$getEmpty$2
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

        public final void getSearchGame() {
            Repository.INSTANCE.getDealSearchGames(new DealIndexFragment$ChildFragment1$getSearchGame$1(this), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$getSearchGame$2
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
    }

    /* JADX INFO: compiled from: DealIndexFragment.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020\u001eH\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00198FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u000b\u001a\u0004\b\u001a\u0010\u001b¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/DealIndexFragment$ChildFragment2;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentRvBinding;", "()V", "dickerAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/DickerMessageBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemDealDickerBinding;", "getDickerAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "dickerAdapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", HintConstants.AUTOFILL_HINT_USERNAME, "", "getUsername", "()Ljava/lang/String;", "setUsername", "(Ljava/lang/String;)V", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "getDickerData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ChildFragment2 extends BaseFragment<FragmentRvBinding> {
        public static final int $stable = 8;

        /* JADX INFO: renamed from: dickerAdapter$delegate, reason: from kotlin metadata */
        private final Lazy dickerAdapter;
        private int page;
        private String username;

        /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
        private final Lazy vm;

        public ChildFragment2() {
            super(R.layout.fragment_rv);
            this.page = 1;
            this.dickerAdapter = LazyKt.lazy(new Function0<BaseAdapter<DickerMessageBean, ItemDealDickerBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$dickerAdapter$2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final BaseAdapter<DickerMessageBean, ItemDealDickerBinding> invoke() {
                    return new BaseAdapter<>(R.layout.item_deal_dicker, new Function3<BaseDataBindingHolder<ItemDealDickerBinding>, Integer, DickerMessageBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$dickerAdapter$2.1
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
            this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$vm$2
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final MainViewModel invoke() {
                    return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
                }
            });
            this.username = Constant.INSTANCE.getUsername();
        }

        public final int getPage() {
            return this.page;
        }

        public final void setPage(int i) {
            this.page = i;
        }

        public final BaseAdapter<DickerMessageBean, ItemDealDickerBinding> getDickerAdapter() {
            return (BaseAdapter) this.dickerAdapter.getValue();
        }

        public final MainViewModel getVm() {
            return (MainViewModel) this.vm.getValue();
        }

        public final String getUsername() {
            return this.username;
        }

        public final void setUsername(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.username = str;
        }

        @Override // com.cy.yyjia.zhe28.base.BaseFragment
        public void init() {
            ChildFragment2 childFragment2 = this;
            getVm().getUser().observe(childFragment2, new DealIndexFragment$sam$androidx_lifecycle_Observer$0(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$1
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
                    if (!Constant.INSTANCE.getLogged() || Intrinsics.areEqual(this.this$0.getUsername(), userBean.getUserName())) {
                        return;
                    }
                    this.this$0.setUsername(userBean.getUserName());
                    this.this$0.setPage(1);
                    this.this$0.getDickerAdapter().setNewInstance(null);
                    this.this$0.getDickerData();
                }
            }));
            getMBinding().rv.setAdapter(getDickerAdapter());
            getDickerAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
                public final void onLoadMore() {
                    DealIndexFragment.ChildFragment2.init$lambda$0(this.f$0);
                }
            });
            getDickerAdapter().addChildClickViewIds(R.id.tv_dicker_cancel, R.id.tv_dicker_modify, R.id.tv_dicker_refuse, R.id.tv_receive, R.id.tv_dicker_agree);
            BaseAdapter.setMyEmptyView$default(getDickerAdapter(), null, 1, null);
            getDickerAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$$ExternalSyntheticLambda1
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    DealIndexFragment.ChildFragment2.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
                }
            });
            getVm().getDealParam().observe(childFragment2, new DealIndexFragment$sam$androidx_lifecycle_Observer$0(new Function1<DealParamBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$4
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DealParamBean dealParamBean) {
                    invoke2(dealParamBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DealParamBean dealParamBean) {
                    if (dealParamBean.getPosition() == 2) {
                        this.this$0.getMBinding().srl.autoRefresh();
                    }
                }
            }));
            getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$$ExternalSyntheticLambda2
                @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
                public final void onRefresh(RefreshLayout refreshLayout) {
                    DealIndexFragment.ChildFragment2.init$lambda$2(this.f$0, refreshLayout);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$0(ChildFragment2 this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getDickerData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$1(final ChildFragment2 this$0, BaseQuickAdapter baseQuickAdapter, View v, final int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(v, "v");
            int id = v.getId();
            if (id == R.id.tv_dicker_refuse) {
                new ConfirmDialog(this$0.getMContext()).setTip("确定要拒绝本次议价申请吗？").setBtnText("确定").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$1
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
                        final DealIndexFragment.ChildFragment2 childFragment2 = this.this$0;
                        Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$1.1
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
                                childFragment2.toast(it.getMsg());
                                if (it.getCode() == 200) {
                                    childFragment2.getMBinding().srl.autoRefresh();
                                }
                            }
                        };
                        final DealIndexFragment.ChildFragment2 childFragment22 = this.this$0;
                        repository.agreeDicker(id2, 0, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$1.2
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
                                childFragment22.netFail(it);
                            }
                        });
                    }
                }).show();
                return;
            }
            if (id != R.id.tv_receive) {
                switch (id) {
                    case R.id.tv_dicker_agree /* 2131362686 */:
                        new ConfirmDialog(this$0.getMContext()).setTip("确定要同意本次议价申请吗？").setBtnText("确定").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$2
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
                                final DealIndexFragment.ChildFragment2 childFragment2 = this.this$0;
                                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$2.1
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
                                        childFragment2.toast(it.getMsg());
                                        if (it.getCode() == 200) {
                                            childFragment2.getMBinding().srl.autoRefresh();
                                        }
                                    }
                                };
                                final DealIndexFragment.ChildFragment2 childFragment22 = this.this$0;
                                repository.agreeDicker(id2, 1, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$2.2
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
                                        childFragment22.netFail(it);
                                    }
                                });
                            }
                        }).show();
                        break;
                    case R.id.tv_dicker_cancel /* 2131362687 */:
                        new ConfirmDialog(this$0.getMContext()).setTip("确定要取消本次议价申请吗？").setBtnText("确定").setOnConfirm(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$3
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
                                final DealIndexFragment.ChildFragment2 childFragment2 = this.this$0;
                                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$3.1
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
                                        childFragment2.toast(it.getMsg());
                                        if (it.getCode() == 200) {
                                            childFragment2.getMBinding().srl.autoRefresh();
                                        }
                                    }
                                };
                                final DealIndexFragment.ChildFragment2 childFragment22 = this.this$0;
                                repository.cancelDicker(id2, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$3.2
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
                                        childFragment22.netFail(it);
                                    }
                                });
                            }
                        }).show();
                        break;
                    case R.id.tv_dicker_modify /* 2131362688 */:
                        new DickerDialog(this$0.getMContext()).setListener(new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$4
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
                                final DealIndexFragment.ChildFragment2 childFragment2 = this.this$0;
                                Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$4.1
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
                                        childFragment2.toast(it.getMsg());
                                        childFragment2.getMBinding().srl.autoRefresh();
                                    }
                                };
                                final DealIndexFragment.ChildFragment2 childFragment22 = this.this$0;
                                repository.modifyDicker(id2, price, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$4.2
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
                                        childFragment22.netFail(it);
                                    }
                                });
                            }
                        }).show();
                        break;
                }
                return;
            }
            Repository.INSTANCE.confirmDeal(this$0.getDickerAdapter().getItem(i).getAccountTreadId(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$5
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
                        this.this$0.getMBinding().srl.autoRefresh();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$init$3$6
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

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$2(ChildFragment2 this$0, RefreshLayout it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(it, "it");
            this$0.page = 1;
            this$0.getDickerAdapter().setNewInstance(null);
            this$0.getDickerData();
        }

        public final void getDickerData() {
            Repository.INSTANCE.getMessageList3(this.page, new Function1<PageBean<DickerMessageBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$getDickerData$1
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
                    this.this$0.getMBinding().srl.finishRefresh();
                    if (this.this$0.getPage() == 1) {
                        this.this$0.getDickerAdapter().setNewInstance(it.getList());
                    } else {
                        this.this$0.getDickerAdapter().addData(it.getList());
                    }
                    DealIndexFragment.ChildFragment2 childFragment2 = this.this$0;
                    childFragment2.setPage(childFragment2.getPage() + 1);
                    childFragment2.getPage();
                    if (it.getCurrent_page() >= it.getLast_page()) {
                        BaseLoadMoreModule.loadMoreEnd$default(this.this$0.getDickerAdapter().getLoadMoreModule(), false, 1, null);
                    } else {
                        this.this$0.getDickerAdapter().getLoadMoreModule().loadMoreComplete();
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment2$getDickerData$2
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
                    this.this$0.getDickerAdapter().getLoadMoreModule().loadMoreFail();
                    DealIndexFragment.ChildFragment2 childFragment2 = this.this$0;
                    String localizedMessage = it.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    childFragment2.log(localizedMessage);
                }
            });
        }
    }
}
