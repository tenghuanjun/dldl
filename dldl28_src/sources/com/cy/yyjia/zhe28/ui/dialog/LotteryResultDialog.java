package com.cy.yyjia.zhe28.ui.dialog;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogLotteryResultBinding;
import com.cy.yyjia.zhe28.domain.LotteryGiftBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LotteryResultDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¨\u0006\n"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/LotteryResultDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogLotteryResultBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "setData", "data", "", "Lcom/cy/yyjia/zhe28/domain/LotteryGiftBean;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class LotteryResultDialog extends BaseDataBindingDialog<DialogLotteryResultBinding, LotteryResultDialog> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LotteryResultDialog(FragmentActivity activity) {
        super(activity, R.layout.dialog_lottery_result);
        Intrinsics.checkNotNullParameter(activity, "activity");
        ((DialogLotteryResultBinding) this.mBinding).btn.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.LotteryResultDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LotteryResultDialog._init_$lambda$0(this.f$0, view);
            }
        });
        ((DialogLotteryResultBinding) this.mBinding).tvSkip.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.LotteryResultDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LotteryResultDialog._init_$lambda$1(this.f$0, view);
            }
        });
        Drawable background = ((DialogLotteryResultBinding) this.mBinding).anim.getBackground();
        Intrinsics.checkNotNull(background, "null cannot be cast to non-null type android.graphics.drawable.AnimationDrawable");
        ((AnimationDrawable) background).start();
        postDelayed(new Runnable() { // from class: com.cy.yyjia.zhe28.ui.dialog.LotteryResultDialog$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                LotteryResultDialog._init_$lambda$2(this.f$0);
            }
        }, 2700L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(LotteryResultDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(LotteryResultDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((DialogLotteryResultBinding) this$0.mBinding).ll.setVisibility(0);
        ((DialogLotteryResultBinding) this$0.mBinding).tvSkip.setVisibility(8);
        ((DialogLotteryResultBinding) this$0.mBinding).anim.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(LotteryResultDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((DialogLotteryResultBinding) this$0.mBinding).ll.setVisibility(0);
        ((DialogLotteryResultBinding) this$0.mBinding).tvSkip.setVisibility(8);
        ((DialogLotteryResultBinding) this$0.mBinding).anim.setVisibility(8);
    }

    public final LotteryResultDialog setData(List<LotteryGiftBean> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data.size() == 1) {
            ((DialogLotteryResultBinding) this.mBinding).setData(data.get(0));
        } else {
            ((DialogLotteryResultBinding) this.mBinding).rv.setAdapter(new BaseAdapter(R.layout.item_lottery_gift_result, data));
        }
        return this;
    }
}
