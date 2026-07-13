package com.cy.yyjia.zhe28.ui.activity;

import android.content.Context;
import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.view.CountdownView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: LoginActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/cy/yyjia/zhe28/view/CountdownView;", "context", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
final class LoginActivity$initView$1$1$1$1$2$3 extends Lambda implements Function1<Context, CountdownView> {
    final /* synthetic */ LoginActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LoginActivity$initView$1$1$1$1$2$3(LoginActivity loginActivity) {
        super(1);
        this.this$0 = loginActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public final CountdownView invoke(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        CountdownView countdownView = new CountdownView(context);
        final LoginActivity loginActivity = this.this$0;
        countdownView.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$2$3$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity$initView$1$1$1$1$2$3.invoke$lambda$0(loginActivity, view);
            }
        });
        countdownView.setBig(true);
        countdownView.setGravity(17);
        countdownView.setTextSize(16.0f);
        countdownView.setTextColor(this.this$0.getResources().getColor(R.color.colorWhite));
        countdownView.setText("获取验证码");
        countdownView.performClick();
        return countdownView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(final LoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Repository.INSTANCE.getVerifyCode("login", this$0.getPhone(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$2$3$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                invoke2(result);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Result it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this$0.toast(it.getMsg());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.LoginActivity$initView$1$1$1$1$2$3$1$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this$0.setCountDown(false);
                this$0.netFail(it);
            }
        });
    }
}
