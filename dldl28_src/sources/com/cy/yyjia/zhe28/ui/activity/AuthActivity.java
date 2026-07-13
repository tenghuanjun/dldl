package com.cy.yyjia.zhe28.ui.activity;

import android.text.TextUtils;
import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityAuthBinding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/AuthActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityAuthBinding;", "Landroid/view/View$OnClickListener;", "()V", "init", "", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AuthActivity extends BaseActivity<ActivityAuthBinding> implements View.OnClickListener {
    public static final int $stable = 0;

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
    }

    public AuthActivity() {
        super(R.layout.activity_auth, 2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (TextUtils.isEmpty(getMBinding().getName())) {
            toast("请输入真实姓名");
            return;
        }
        if (TextUtils.isEmpty(getMBinding().getCode())) {
            toast("请输入身份证号");
            return;
        }
        Repository repository = Repository.INSTANCE;
        String name = getMBinding().getName();
        Intrinsics.checkNotNull(name);
        String code = getMBinding().getCode();
        Intrinsics.checkNotNull(code);
        repository.auth(name, code, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.AuthActivity.onClick.1
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
                AuthActivity.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    AuthActivity.this.finish();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.AuthActivity.onClick.2
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
                AuthActivity authActivity = AuthActivity.this;
                String localizedMessage = it.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                authActivity.toast(localizedMessage);
            }
        });
    }
}
