package com.cy.yyjia.zhe28.ui.dialog;

import com.cy.yyjia.zhe28.domain.SlideBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HomeDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.cy.yyjia.zhe28.ui.dialog.HomeDialog$job$1", f = "HomeDialog.kt", i = {0}, l = {74}, m = "invokeSuspend", n = {"i"}, s = {"I$0"})
final class HomeDialog$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<SlideBean> $data;
    int I$0;
    int label;
    final /* synthetic */ HomeDialog this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeDialog$job$1(List<SlideBean> list, HomeDialog homeDialog, Continuation<? super HomeDialog$job$1> continuation) {
        super(2, continuation);
        this.$data = list;
        this.this$0 = homeDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HomeDialog$job$1(this.$data, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HomeDialog$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0074  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0067 -> B:14:0x006a). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 1
            if (r1 == 0) goto L19
            if (r1 != r2) goto L11
            int r1 = r5.I$0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L6a
        L11:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L19:
            kotlin.ResultKt.throwOnFailure(r6)
            java.util.List<com.cy.yyjia.zhe28.domain.SlideBean> r6 = r5.$data
            r1 = 0
            java.lang.Object r6 = r6.get(r1)
            com.cy.yyjia.zhe28.domain.SlideBean r6 = (com.cy.yyjia.zhe28.domain.SlideBean) r6
            int r6 = r6.getCountdown()
            r1 = r6
        L2a:
            r6 = -1
            if (r6 >= r1) goto L74
            com.cy.yyjia.zhe28.ui.dialog.HomeDialog r6 = r5.this$0
            androidx.databinding.ViewDataBinding r6 = com.cy.yyjia.zhe28.ui.dialog.HomeDialog.m6590access$getMBinding$p$s1844947161(r6)
            com.cy.yyjia.zhe28.databinding.DialogHomeBinding r6 = (com.cy.yyjia.zhe28.databinding.DialogHomeBinding) r6
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "倒计时"
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r4 = "秒关闭"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r6.setText(r3)
            com.cy.yyjia.zhe28.ui.dialog.HomeDialog r6 = r5.this$0
            androidx.databinding.ViewDataBinding r3 = com.cy.yyjia.zhe28.ui.dialog.HomeDialog.m6590access$getMBinding$p$s1844947161(r6)
            com.cy.yyjia.zhe28.databinding.DialogHomeBinding r3 = (com.cy.yyjia.zhe28.databinding.DialogHomeBinding) r3
            java.lang.String r3 = r3.getText()
            r6.log(r3)
            r6 = r5
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r5.I$0 = r1
            r5.label = r2
            r3 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r6 = kotlinx.coroutines.DelayKt.delay(r3, r6)
            if (r6 != r0) goto L6a
            return r0
        L6a:
            if (r1 != 0) goto L71
            com.cy.yyjia.zhe28.ui.dialog.HomeDialog r6 = r5.this$0
            com.cy.yyjia.zhe28.ui.dialog.HomeDialog.access$dismiss(r6)
        L71:
            int r1 = r1 + (-1)
            goto L2a
        L74:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.ui.dialog.HomeDialog$job$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
