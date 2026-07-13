package com.cy.yyjia.zhe28.ui.dialog;

import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogDealDickerBinding;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DickerDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J)\u0010\u0006\u001a\u00020\u00002!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\b¨\u0006\u000e"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/DickerDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogDealDickerBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "setListener", "listener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "price", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DickerDialog extends BaseDataBindingDialog<DialogDealDickerBinding, DickerDialog> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DickerDialog(FragmentActivity activity) {
        super(activity, R.layout.dialog_deal_dicker);
        Intrinsics.checkNotNullParameter(activity, "activity");
        ((DialogDealDickerBinding) this.mBinding).cancel.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.DickerDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DickerDialog._init_$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(DickerDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final DickerDialog setListener(final Function1<? super String, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        ((DialogDealDickerBinding) this.mBinding).sure.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.DickerDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DickerDialog.setListener$lambda$1(this.f$0, listener, view);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setListener$lambda$1(DickerDialog this$0, Function1 listener, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        if (TextUtils.isEmpty(((DialogDealDickerBinding) this$0.mBinding).getPrice())) {
            return;
        }
        String price = ((DialogDealDickerBinding) this$0.mBinding).getPrice();
        Intrinsics.checkNotNull(price);
        listener.invoke(price);
        this$0.dismiss();
    }
}
