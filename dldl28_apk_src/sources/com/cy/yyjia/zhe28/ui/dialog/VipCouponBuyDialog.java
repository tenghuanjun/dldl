package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.DialogVipCouponBuyBinding;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VipCouponBuyDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B0\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0007¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/VipCouponBuyDialog;", "Lcom/cy/yyjia/zhe28/base/QuickDialog;", "", "Lcom/cy/yyjia/zhe28/databinding/DialogVipCouponBuyBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "onSelect", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "num", "", "(Landroidx/fragment/app/FragmentActivity;Lkotlin/jvm/functions/Function1;)V", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VipCouponBuyDialog extends QuickDialog<String, DialogVipCouponBuyBinding> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VipCouponBuyDialog(FragmentActivity activity, final Function1<? super String, Unit> onSelect) {
        super(activity, R.layout.dialog_vip_coupon_buy);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(onSelect, "onSelect");
        getMBinding().setText("1");
        getMBinding().ivJia.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.VipCouponBuyDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VipCouponBuyDialog._init_$lambda$0(this.f$0, view);
            }
        });
        getMBinding().ivJian.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.VipCouponBuyDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VipCouponBuyDialog._init_$lambda$1(this.f$0, view);
            }
        });
        getMBinding().btn.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.VipCouponBuyDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VipCouponBuyDialog._init_$lambda$2(onSelect, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(VipCouponBuyDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String text = this$0.getMBinding().getText();
        Intrinsics.checkNotNull(text);
        int i = Integer.parseInt(text);
        if (i < this$0.getMBinding().getMax()) {
            this$0.getMBinding().setText(String.valueOf(i + 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(VipCouponBuyDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String text = this$0.getMBinding().getText();
        Intrinsics.checkNotNull(text);
        int i = Integer.parseInt(text);
        if (i > 1) {
            this$0.getMBinding().setText(String.valueOf(i - 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(Function1 onSelect, VipCouponBuyDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(onSelect, "$onSelect");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String text = this$0.getMBinding().getText();
        Intrinsics.checkNotNull(text);
        onSelect.invoke(text);
        this$0.dismiss();
    }
}
