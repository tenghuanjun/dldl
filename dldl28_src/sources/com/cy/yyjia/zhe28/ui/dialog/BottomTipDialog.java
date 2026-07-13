package com.cy.yyjia.zhe28.ui.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.databinding.DialogBottomTip2Binding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BottomTipDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/BottomTipDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDialog$Builder;", "activity", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mBinding", "Lcom/cy/yyjia/zhe28/databinding/DialogBottomTip2Binding;", "setText", "text", "", "setTitle", "title", "setWebData", "webData", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BottomTipDialog extends BaseDialog.Builder<BottomTipDialog> {
    public static final int $stable = 8;
    private DialogBottomTip2Binding mBinding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BottomTipDialog(Context activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_bottom_tip2, new FrameLayout(getContext()), false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        DialogBottomTip2Binding dialogBottomTip2Binding = (DialogBottomTip2Binding) viewDataBindingInflate;
        this.mBinding = dialogBottomTip2Binding;
        setContentView(dialogBottomTip2Binding.getRoot());
        this.mBinding.ivBack.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.BottomTipDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BottomTipDialog._init_$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(BottomTipDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final BottomTipDialog setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.mBinding.title.setText(title);
        return this;
    }

    public final BottomTipDialog setText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.mBinding.f446tv.setText(text);
        this.mBinding.wv.setVisibility(8);
        return this;
    }

    public final BottomTipDialog setWebData(String webData) {
        Intrinsics.checkNotNullParameter(webData, "webData");
        this.mBinding.wv.loadData(webData, "", "");
        this.mBinding.wv.setVisibility(0);
        return this;
    }
}
