package com.cy.yyjia.zhe28.ui.dialog;

import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogWaitBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WaitDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/WaitDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogWaitBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "hide", "", "setText", "text", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WaitDialog extends BaseDataBindingDialog<DialogWaitBinding, WaitDialog> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WaitDialog(FragmentActivity activity) {
        super(activity, R.layout.dialog_wait);
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public final WaitDialog setText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        ((DialogWaitBinding) this.mBinding).f455tv.setText(text);
        ((DialogWaitBinding) this.mBinding).f455tv.setVisibility(0);
        return this;
    }

    public final void hide() {
        dismiss();
    }
}
