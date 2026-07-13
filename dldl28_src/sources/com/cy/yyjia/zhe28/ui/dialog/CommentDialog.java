package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogCommentBinding;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommentDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ)\u0010\n\u001a\u00020\u00002!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\fJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\b¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/CommentDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogCommentBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "setBtnText", "str", "", "setHint", "setSubmitListener", "listener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "text", "", "setText", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CommentDialog extends BaseDataBindingDialog<DialogCommentBinding, CommentDialog> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommentDialog(FragmentActivity activity) {
        super(activity, R.layout.dialog_comment);
        Intrinsics.checkNotNullParameter(activity, "activity");
        ((DialogCommentBinding) this.mBinding).setText("");
    }

    public final CommentDialog setSubmitListener(final Function1<? super String, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        ((DialogCommentBinding) this.mBinding).btn.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.CommentDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommentDialog.setSubmitListener$lambda$0(listener, this, view);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setSubmitListener$lambda$0(Function1 listener, CommentDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String text = ((DialogCommentBinding) this$0.mBinding).getText();
        Intrinsics.checkNotNull(text);
        listener.invoke(text);
        this$0.dismiss();
    }

    public final CommentDialog setHint(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        ((DialogCommentBinding) this.mBinding).et.setHint(str);
        return this;
    }

    public final CommentDialog setText(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        ((DialogCommentBinding) this.mBinding).setText(str);
        return this;
    }

    public final CommentDialog setBtnText(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        ((DialogCommentBinding) this.mBinding).btn.setText(str);
        return this;
    }
}
