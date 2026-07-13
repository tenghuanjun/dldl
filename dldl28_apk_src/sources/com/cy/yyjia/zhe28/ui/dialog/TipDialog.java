package com.cy.yyjia.zhe28.ui.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.databinding.DialogTipBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TipDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/TipDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDialog$Builder;", "activity", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mBinding", "Lcom/cy/yyjia/zhe28/databinding/DialogTipBinding;", "setBtnText", "s", "", "setTip", "setTitle", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class TipDialog extends BaseDialog.Builder<TipDialog> {
    public static final int $stable = 8;
    private DialogTipBinding mBinding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TipDialog(Context activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_tip, new FrameLayout(getContext()), false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        DialogTipBinding dialogTipBinding = (DialogTipBinding) viewDataBindingInflate;
        this.mBinding = dialogTipBinding;
        setContentView(dialogTipBinding.getRoot());
        this.mBinding.setBtn("确定");
        this.mBinding.tvGo.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.TipDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TipDialog._init_$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(TipDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final TipDialog setTitle(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        this.mBinding.setTitle(s);
        return this;
    }

    public final TipDialog setTip(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        this.mBinding.setTip(s);
        return this;
    }

    public final TipDialog setBtnText(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        this.mBinding.setBtn(s);
        return this;
    }
}
