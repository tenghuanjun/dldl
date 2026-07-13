package com.cy.yyjia.zhe28.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityWithdrewBinding;
import com.cy.yyjia.zhe28.domain.DealIndexBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.domain.WithdrewResult;
import com.cy.yyjia.zhe28.util.Repository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WithdrewActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0014¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/WithdrewActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityWithdrewBinding;", "Landroid/view/View$OnClickListener;", "()V", "getData", "", "init", "onClick", "v", "Landroid/view/View;", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WithdrewActivity extends BaseActivity<ActivityWithdrewBinding> implements View.OnClickListener {
    public static final int $stable = 0;

    public WithdrewActivity() {
        super(R.layout.activity_withdrew, 2);
    }

    public static final /* synthetic */ ActivityWithdrewBinding access$getMBinding(WithdrewActivity withdrewActivity) {
        return withdrewActivity.getMBinding();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setPtb(true);
        getMBinding().setNumber("");
        EditText et = getMBinding().et;
        Intrinsics.checkNotNullExpressionValue(et, "et");
        et.addTextChangedListener(new TextWatcher() { // from class: com.cy.yyjia.zhe28.ui.activity.WithdrewActivity$init$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (s == null || s.length() != 0) {
                    BigDecimal scale = new BigDecimal(String.valueOf(s)).multiply(new BigDecimal(1.03d)).setScale(2, RoundingMode.HALF_UP);
                    WithdrewActivity.access$getMBinding(this.this$0).f444tv.setText("￥" + scale);
                    return;
                }
                WithdrewActivity.access$getMBinding(this.this$0).f444tv.setText("￥0");
            }
        });
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.WithdrewActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WithdrewActivity.init$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(WithdrewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(BillActivity.class);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getData();
    }

    public final void getData() {
        Repository.INSTANCE.getDealIndex(new Function1<DealIndexBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WithdrewActivity.getData.1
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
                WithdrewActivity.access$getMBinding(WithdrewActivity.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WithdrewActivity.getData.2
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
        Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WithdrewActivity.getData.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserBean userBean) {
                invoke2(userBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (TextUtils.isEmpty(it.getAliAccount())) {
                    WithdrewActivity.access$getMBinding(WithdrewActivity.this).tvAlipay.setText("支付宝（未绑定）");
                    return;
                }
                WithdrewActivity.access$getMBinding(WithdrewActivity.this).tvAlipay.setText("支付宝（" + it.getAliAccount() + "）");
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WithdrewActivity.getData.4
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
            case R.id.btn /* 2131361939 */:
                if (TextUtils.isEmpty(getMBinding().getNumber())) {
                    toast("请输入提现金额");
                } else {
                    String str = getMBinding().getPtb() ? "balance" : "bank";
                    Repository repository = Repository.INSTANCE;
                    String number = getMBinding().getNumber();
                    Intrinsics.checkNotNull(number);
                    repository.withdrew(number, str, new Function1<WithdrewResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WithdrewActivity.onClick.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(WithdrewResult withdrewResult) {
                            invoke2(withdrewResult);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(WithdrewResult it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            WithdrewActivity.this.toast(it.getMsg());
                            WithdrewActivity.this.getData();
                        }
                    }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WithdrewActivity.onClick.2
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
                            WithdrewActivity.this.netFail(it);
                        }
                    });
                }
                break;
            case R.id.ll_ptb /* 2131362272 */:
                getMBinding().setPtb(true);
                break;
            case R.id.ll_zfb /* 2131362286 */:
                getMBinding().setPtb(false);
                break;
            case R.id.tv_all /* 2131362657 */:
                ActivityWithdrewBinding mBinding = getMBinding();
                DealIndexBean data = getMBinding().getData();
                Intrinsics.checkNotNull(data);
                mBinding.setNumber(data.getUsable_money());
                break;
        }
    }
}
