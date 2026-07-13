package com.cy.yyjia.zhe28.ui.dialog;

import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogPrivacyBinding;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PrivacyDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/PrivacyDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogPrivacyBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "cs", "Landroid/text/style/ClickableSpan;", ImageSelector.POSITION, "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PrivacyDialog extends BaseDataBindingDialog<DialogPrivacyBinding, PrivacyDialog> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrivacyDialog(FragmentActivity activity) {
        super(activity, R.layout.dialog_privacy);
        Intrinsics.checkNotNullParameter(activity, "activity");
        ((DialogPrivacyBinding) this.mBinding).tvClose.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.PrivacyDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyDialog._init_$lambda$0(view);
            }
        });
        ((DialogPrivacyBinding) this.mBinding).tvGo.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.PrivacyDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyDialog._init_$lambda$1(this.f$0, view);
            }
        });
        String string = ((DialogPrivacyBinding) this.mBinding).f451tv.getText().toString();
        String string2 = getString(R.string.app_name);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        SpannableString spannableString = new SpannableString(StringsKt.replace$default(string, "28手游", string2, false, 4, (Object) null));
        spannableString.setSpan(cs(0), 31, 41, 33);
        spannableString.setSpan(new ForegroundColorSpan(activity.getResources().getColor(R.color.colorPrimary)), 31, 41, 33);
        spannableString.setSpan(cs(1), 42, 48, 33);
        spannableString.setSpan(new ForegroundColorSpan(activity.getResources().getColor(R.color.colorPrimary)), 42, 48, 33);
        spannableString.setSpan(cs(2), 49, 60, 33);
        spannableString.setSpan(new ForegroundColorSpan(activity.getResources().getColor(R.color.colorPrimary)), 49, 60, 33);
        ((DialogPrivacyBinding) this.mBinding).f451tv.setText(spannableString);
        ((DialogPrivacyBinding) this.mBinding).f451tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(View view) {
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(PrivacyDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final ClickableSpan cs(final int position) {
        final List listListOf = CollectionsKt.listOf((Object[]) new String[]{getString(R.string.app_name) + "用户协议", getString(R.string.app_name) + "隐私政策", "第三方服务共享清单", "应用权限使用规则"});
        final List listListOf2 = CollectionsKt.listOf((Object[]) new String[]{"https://www.28zhe.com/help/agreement.htm", "https://www.28zhe.com/help/privacyPolicy.htm", "https://28zhe.com/help/thirdinfo.htm", "https://www.28zhe.com/help/appPermissions.htm"});
        return new ClickableSpan() { // from class: com.cy.yyjia.zhe28.ui.dialog.PrivacyDialog.cs.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View widget) {
                Intrinsics.checkNotNullParameter(widget, "widget");
                Util.openWeb(PrivacyDialog.this.getContext(), listListOf.get(position), listListOf2.get(position), false);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                ds.setUnderlineText(false);
            }
        };
    }
}
