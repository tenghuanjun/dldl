package com.cy.yyjia.zhe28.ui.fragment;

import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentDealBinding;
import com.cy.yyjia.zhe28.databinding.ItemDealFunBinding;
import com.cy.yyjia.zhe28.domain.DealIndexBean;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.ui.activity.DealListActivity;
import com.cy.yyjia.zhe28.ui.activity.DealRecordActivity;
import com.cy.yyjia.zhe28.ui.activity.MessageActivity;
import com.cy.yyjia.zhe28.ui.activity.WithdrewActivity;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DealFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0016R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/DealFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentDealBinding;", "Landroid/view/View$OnClickListener;", "()V", "funAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/DealIndexBean$FunBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemDealFunBinding;", "getFunAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "funAdapter$delegate", "Lkotlin/Lazy;", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "init", "", "onClick", "v", "Landroid/view/View;", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DealFragment extends BaseFragment<FragmentDealBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: funAdapter$delegate, reason: from kotlin metadata */
    private final Lazy funAdapter;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    public DealFragment() {
        super(R.layout.fragment_deal);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
            }
        });
        this.funAdapter = LazyKt.lazy(new Function0<BaseAdapter<DealIndexBean.FunBean, ItemDealFunBinding>>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealFragment$funAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<DealIndexBean.FunBean, ItemDealFunBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_deal_fun, null, 2, null);
            }
        });
    }

    public static final /* synthetic */ FragmentDealBinding access$getMBinding(DealFragment dealFragment) {
        return dealFragment.getMBinding();
    }

    private final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    public final BaseAdapter<DealIndexBean.FunBean, ItemDealFunBinding> getFunAdapter() {
        return (BaseAdapter) this.funAdapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getVm().getUser().observe(this, new DealFragment$sam$androidx_lifecycle_Observer$0(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealFragment.init.1
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
                DealFragment.access$getMBinding(DealFragment.this).setData(userBean);
            }
        }));
        getMBinding().setOnClick(this);
        getMBinding().rv.setAdapter(getFunAdapter());
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Repository.INSTANCE.getDealIndex(new Function1<DealIndexBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealFragment.onResume.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DealIndexBean dealIndexBean) {
                invoke2(dealIndexBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DealIndexBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                DealFragment.access$getMBinding(DealFragment.this).tvMoney.setText(it.getUsable_money());
                DealFragment.this.getFunAdapter().setNewInstance(it.getList());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealFragment.onResume.2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.btn_buy /* 2131361953 */:
                startActivity(DealListActivity.class);
                break;
            case R.id.btn_record /* 2131361972 */:
                Util.skipWithLogin(getMContext(), DealRecordActivity.class);
                break;
            case R.id.iv_message /* 2131362184 */:
                startActivity(MessageActivity.class);
                break;
            case R.id.iv_rule /* 2131362190 */:
                Util.openProtocol(getMContext(), "交易说明", "transactionInstructions");
                break;
            case R.id.tv_withdrew /* 2131362808 */:
                Util.skipWithLogin(getMContext(), WithdrewActivity.class);
                break;
        }
    }
}
