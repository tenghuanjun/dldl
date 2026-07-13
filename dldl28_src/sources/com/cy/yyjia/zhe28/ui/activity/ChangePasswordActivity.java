package com.cy.yyjia.zhe28.ui.activity;

import android.text.TextUtils;
import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityChangePasswordBinding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChangePasswordActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/ChangePasswordActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityChangePasswordBinding;", "Landroid/view/View$OnClickListener;", "()V", "init", "", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ChangePasswordActivity extends BaseActivity<ActivityChangePasswordBinding> implements View.OnClickListener {
    public static final int $stable = 0;

    public static final /* synthetic */ ActivityChangePasswordBinding access$getMBinding(ChangePasswordActivity changePasswordActivity) {
        return changePasswordActivity.getMBinding();
    }

    public ChangePasswordActivity() {
        super(R.layout.activity_change_password, 0, 2, null);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        if (getIntent().getBooleanExtra("forget", false)) {
            getMBinding().navigation.setTitle("忘记密码");
            getMBinding().tvTitle.setText("重置密码");
            getMBinding().tvGo.setText("立即重置");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.btn_code) {
            if (TextUtils.isEmpty(getMBinding().getPhone())) {
                toast("请输入手机号");
                getMBinding().btnCode.resetState();
                return;
            } else {
                Repository repository = Repository.INSTANCE;
                String phone = getMBinding().getPhone();
                Intrinsics.checkNotNull(phone);
                repository.getVerifyCode("lostPasswd", phone, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ChangePasswordActivity.onClick.1
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
                        ChangePasswordActivity.this.toast(it.getMsg());
                    }
                }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ChangePasswordActivity.onClick.2
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
                        ChangePasswordActivity.access$getMBinding(ChangePasswordActivity.this).btnCode.resetState();
                        ChangePasswordActivity.this.netFail(it);
                    }
                });
                return;
            }
        }
        if (TextUtils.isEmpty(getMBinding().getPhone())) {
            toast("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(getMBinding().getCode())) {
            toast("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(getMBinding().getPassword())) {
            toast("请输入新密码");
            return;
        }
        if (TextUtils.isEmpty(getMBinding().getPassword2())) {
            toast("请确认新密码");
            return;
        }
        if (!Intrinsics.areEqual(getMBinding().getPassword2(), getMBinding().getPassword())) {
            toast("两次密码不一致");
            return;
        }
        hideSoftKeyboard();
        Repository repository2 = Repository.INSTANCE;
        String phone2 = getMBinding().getPhone();
        Intrinsics.checkNotNull(phone2);
        String code = getMBinding().getCode();
        Intrinsics.checkNotNull(code);
        String password = getMBinding().getPassword();
        Intrinsics.checkNotNull(password);
        repository2.setPassword(phone2, code, password, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ChangePasswordActivity.onClick.3
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
                ChangePasswordActivity.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    Constant.INSTANCE.logout(ChangePasswordActivity.this);
                    Constant.INSTANCE.setCancellation(true);
                    ChangePasswordActivity.this.finish();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ChangePasswordActivity.onClick.4
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
                ChangePasswordActivity.this.netFail(it);
            }
        });
    }
}
