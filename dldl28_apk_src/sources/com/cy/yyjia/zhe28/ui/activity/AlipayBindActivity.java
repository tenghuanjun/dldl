package com.cy.yyjia.zhe28.ui.activity;

import android.text.TextUtils;
import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityAlipayBindBinding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlipayBindActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\u0007\u001a\u00020\u0005J\b\u0010\b\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/AlipayBindActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityAlipayBindBinding;", "()V", "bind", "", "getCode", "getData", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AlipayBindActivity extends BaseActivity<ActivityAlipayBindBinding> {
    public static final int $stable = 0;

    public AlipayBindActivity() {
        super(R.layout.activity_alipay_bind, 0, 2, null);
    }

    public static final /* synthetic */ ActivityAlipayBindBinding access$getMBinding(AlipayBindActivity alipayBindActivity) {
        return alipayBindActivity.getMBinding();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(AlipayBindActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.bind();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().tvGo.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.AlipayBindActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlipayBindActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().btnCode.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.AlipayBindActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlipayBindActivity.init$lambda$1(this.f$0, view);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(AlipayBindActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getCode();
    }

    public final void getCode() {
        Repository.INSTANCE.getVerifyCodeAli(new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.AlipayBindActivity.getCode.1
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
                AlipayBindActivity.this.toast(it.getMsg());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.AlipayBindActivity.getCode.2
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
                AlipayBindActivity.access$getMBinding(AlipayBindActivity.this).btnCode.resetState();
                AlipayBindActivity.this.netFail(it);
            }
        });
    }

    public final void bind() {
        if (TextUtils.isEmpty(getMBinding().getCode())) {
            toast("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(getMBinding().getAccount())) {
            toast("请输入支付宝账号");
            return;
        }
        if (TextUtils.isEmpty(getMBinding().getName())) {
            toast("请输入支付宝收款名称");
            return;
        }
        Repository repository = Repository.INSTANCE;
        String code = getMBinding().getCode();
        Intrinsics.checkNotNull(code);
        String account = getMBinding().getAccount();
        Intrinsics.checkNotNull(account);
        String name = getMBinding().getName();
        Intrinsics.checkNotNull(name);
        repository.bindAli(code, account, name, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.AlipayBindActivity.bind.1
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
                AlipayBindActivity.this.toast(it.getMsg());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.AlipayBindActivity.bind.2
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
                AlipayBindActivity.this.netFail(it);
            }
        });
    }

    public final void getData() {
        Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.AlipayBindActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UserBean userBean) {
                invoke2(userBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UserBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                AlipayBindActivity.access$getMBinding(AlipayBindActivity.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.AlipayBindActivity.getData.2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }
        });
    }
}
