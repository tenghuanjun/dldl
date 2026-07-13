package com.cy.yyjia.zhe28.ui.dialog;

import android.text.method.ScrollingMovementMethod;
import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogRuleBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RuleDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\b¨\u0006\n"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/RuleDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogRuleBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "setText", "text", "", "setTextStr", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RuleDialog extends BaseDataBindingDialog<DialogRuleBinding, RuleDialog> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RuleDialog(FragmentActivity activity) {
        super(activity, R.layout.dialog_rule);
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public final RuleDialog setText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        ((DialogRuleBinding) this.mBinding).wv.loadData(text, "", "");
        return this;
    }

    public final RuleDialog setTextStr(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        ((DialogRuleBinding) this.mBinding).wv.setVisibility(8);
        ((DialogRuleBinding) this.mBinding).f453tv.setVisibility(0);
        ((DialogRuleBinding) this.mBinding).f453tv.setText(text);
        ((DialogRuleBinding) this.mBinding).f453tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        return this;
    }
}
