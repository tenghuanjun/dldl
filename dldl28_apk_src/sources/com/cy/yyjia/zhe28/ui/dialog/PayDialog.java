package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogPayBinding;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PayDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J)\u0010\u0006\u001a\u00020\u00002!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\bJ)\u0010\u000e\u001a\u00020\u00002!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\r0\bJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013¨\u0006\u0014"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/PayDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogPayBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "setPayListener", "listener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "alipay", "", "setPayWithPtbListener", "", "type", "setPrice", "price", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PayDialog extends BaseDataBindingDialog<DialogPayBinding, PayDialog> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PayDialog(FragmentActivity activity) {
        super(activity, R.layout.dialog_pay);
        Intrinsics.checkNotNullParameter(activity, "activity");
        ((DialogPayBinding) this.mBinding).setAlipay(2);
        ((DialogPayBinding) this.mBinding).tvZfb.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.PayDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PayDialog._init_$lambda$0(this.f$0, view);
            }
        });
        ((DialogPayBinding) this.mBinding).tvWx.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.PayDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PayDialog._init_$lambda$1(this.f$0, view);
            }
        });
        ((DialogPayBinding) this.mBinding).tvPtb.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.PayDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PayDialog._init_$lambda$2(this.f$0, view);
            }
        });
        ((DialogPayBinding) this.mBinding).ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.PayDialog$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PayDialog._init_$lambda$3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(PayDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((DialogPayBinding) this$0.mBinding).setAlipay(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(PayDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((DialogPayBinding) this$0.mBinding).setAlipay(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(PayDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((DialogPayBinding) this$0.mBinding).setAlipay(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$3(PayDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final PayDialog setPrice(String price) {
        Intrinsics.checkNotNullParameter(price, "price");
        ((DialogPayBinding) this.mBinding).setData(price);
        return this;
    }

    public final PayDialog setPayListener(final Function1<? super Boolean, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        ((DialogPayBinding) this.mBinding).tvPay.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.PayDialog$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PayDialog.setPayListener$lambda$4(listener, this, view);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setPayListener$lambda$4(Function1 listener, PayDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        listener.invoke(Boolean.valueOf(((DialogPayBinding) this$0.mBinding).getAlipay() == 2));
        this$0.dismiss();
    }

    public final PayDialog setPayWithPtbListener(final Function1<? super Integer, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        ((DialogPayBinding) this.mBinding).tvPtb.setVisibility(0);
        ((DialogPayBinding) this.mBinding).tvPay.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.PayDialog$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PayDialog.setPayWithPtbListener$lambda$5(listener, this, view);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setPayWithPtbListener$lambda$5(Function1 listener, PayDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        listener.invoke(Integer.valueOf(((DialogPayBinding) this$0.mBinding).getAlipay()));
        this$0.dismiss();
    }
}
