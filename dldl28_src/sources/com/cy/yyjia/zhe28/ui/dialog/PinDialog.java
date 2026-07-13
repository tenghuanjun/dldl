package com.cy.yyjia.zhe28.ui.dialog;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.databinding.DialogPinBinding;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PinDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J)\u0010\u0007\u001a\u00020\u00002!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/PinDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDialog$Builder;", "activity", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mBinding", "Lcom/cy/yyjia/zhe28/databinding/DialogPinBinding;", "onSubmit", "submit", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "data", "", "setTip", "tip", "setTitle", "title", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PinDialog extends BaseDialog.Builder<PinDialog> {
    public static final int $stable = 8;
    private DialogPinBinding mBinding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PinDialog(Context activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_pin, new FrameLayout(getContext()), false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        DialogPinBinding dialogPinBinding = (DialogPinBinding) viewDataBindingInflate;
        this.mBinding = dialogPinBinding;
        setContentView(dialogPinBinding.getRoot());
        this.mBinding.setData("");
    }

    public final PinDialog setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.mBinding.setTitle(title);
        return this;
    }

    public final PinDialog setTip(String tip) {
        Intrinsics.checkNotNullParameter(tip, "tip");
        this.mBinding.setTip(tip);
        this.mBinding.tvTag1.setMovementMethod(ScrollingMovementMethod.getInstance());
        return this;
    }

    public final PinDialog onSubmit(final Function1<? super String, Unit> submit) {
        Intrinsics.checkNotNullParameter(submit, "submit");
        this.mBinding.tvGo.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.PinDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PinDialog.onSubmit$lambda$0(this.f$0, submit, view);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSubmit$lambda$0(PinDialog this$0, Function1 submit, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(submit, "$submit");
        String data = this$0.mBinding.getData();
        Intrinsics.checkNotNull(data);
        if (data.length() > 0) {
            String data2 = this$0.mBinding.getData();
            Intrinsics.checkNotNull(data2);
            submit.invoke(data2);
            this$0.dismiss();
            return;
        }
        Toast.makeText(this$0.getContext(), "请输入二级密码", 0).show();
    }
}
