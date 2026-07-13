package com.cy.yyjia.zhe28.ui.activity;

import android.content.SharedPreferences;
import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.domain.CardInfoBean;
import com.cy.yyjia.zhe28.domain.CardModuleBean;
import com.cy.yyjia.zhe28.ui.activity.SubscribeActivity;
import com.cy.yyjia.zhe28.ui.dialog.CardRulerDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: SubscribeActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
final class SubscribeActivity$ChildFragment2$onClick$3 extends Lambda implements Function0<Unit> {
    final /* synthetic */ SubscribeActivity.ChildFragment2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SubscribeActivity$ChildFragment2$onClick$3(SubscribeActivity.ChildFragment2 childFragment2) {
        super(0);
        this.this$0 = childFragment2;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        final SharedPreferences sharedPreferences = this.this$0.getMContext().getSharedPreferences("yktip", 0);
        if (sharedPreferences.getBoolean("show", true)) {
            CardRulerDialog title = new CardRulerDialog(this.this$0.getMContext()).setTitle("月卡规则");
            CardModuleBean data = this.this$0.getMBinding().getData();
            Intrinsics.checkNotNull(data);
            CardInfoBean selectedModule = data.getSelectedModule();
            Intrinsics.checkNotNull(selectedModule);
            CardRulerDialog text = title.setText(selectedModule.getConfig().getDesc());
            final SubscribeActivity.ChildFragment2 childFragment2 = this.this$0;
            CardRulerDialog cardRulerDialog = (CardRulerDialog) text.setOnClickListener(R.id.tv_buy, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$onClick$3$$ExternalSyntheticLambda0
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    SubscribeActivity$ChildFragment2$onClick$3.invoke$lambda$0(childFragment2, baseDialog, view);
                }
            });
            final SubscribeActivity.ChildFragment2 childFragment22 = this.this$0;
            ((CardRulerDialog) cardRulerDialog.setOnClickListener(R.id.tv_tip, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SubscribeActivity$ChildFragment2$onClick$3$$ExternalSyntheticLambda1
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    SubscribeActivity$ChildFragment2$onClick$3.invoke$lambda$1(sharedPreferences, childFragment22, baseDialog, view);
                }
            })).show();
            return;
        }
        this.this$0.buy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(SubscribeActivity.ChildFragment2 this$0, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        baseDialog.dismiss();
        this$0.buy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1(SharedPreferences sharedPreferences, SubscribeActivity.ChildFragment2 this$0, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean("show", false);
        editorEdit.apply();
        baseDialog.dismiss();
        this$0.buy();
    }
}
