package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityPhoneBinding;
import com.cy.yyjia.zhe28.domain.PinCheckResult;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.util.Repository;
import com.volcengine.common.contant.CommonConstants;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PhoneActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012J\b\u0010\u0014\u001a\u00020\u0012H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0012H\u0014J\u0006\u0010\u0019\u001a\u00020\u0012R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/PhoneActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityPhoneBinding;", "Landroid/view/View$OnClickListener;", "()V", CommonConstants.VALUE_LEVEL_INFO, "Lcom/cy/yyjia/zhe28/domain/UserBean;", "getInfo", "()Lcom/cy/yyjia/zhe28/domain/UserBean;", "setInfo", "(Lcom/cy/yyjia/zhe28/domain/UserBean;)V", "type", "", "getType", "()I", "type$delegate", "Lkotlin/Lazy;", "bind", "", "check", "init", "onClick", "v", "Landroid/view/View;", "onResume", "unbind", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PhoneActivity extends BaseActivity<ActivityPhoneBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private UserBean info;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;

    public PhoneActivity() {
        super(R.layout.activity_phone, 0, 2, null);
        this.type = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.PhoneActivity$type$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("type", 0));
            }
        });
    }

    public static final /* synthetic */ ActivityPhoneBinding access$getMBinding(PhoneActivity phoneActivity) {
        return phoneActivity.getMBinding();
    }

    public final int getType() {
        return ((Number) this.type.getValue()).intValue();
    }

    public final UserBean getInfo() {
        return this.info;
    }

    public final void setInfo(UserBean userBean) {
        this.info = userBean;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        ActivityPhoneBinding mBinding = getMBinding();
        int type = getType();
        if (type == 0) {
            mBinding.setTitle("手机绑定");
            mBinding.setTitle2("绑定手机号码");
            mBinding.setBtn("保存");
            return;
        }
        if (type == 1) {
            mBinding.setTitle("解绑手机号");
            mBinding.setTitle2("绑定手机号码");
            mBinding.setBtn("解绑");
        } else if (type == 2) {
            mBinding.setTitle("设置二级密码");
            mBinding.setTitle2("验证手机号码");
            mBinding.setBtn("验证");
        } else {
            if (type != 3) {
                return;
            }
            mBinding.setTitle("修改二级密码");
            mBinding.setTitle2("验证手机号码");
            mBinding.setBtn("验证");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        String str;
        String telphone;
        Intrinsics.checkNotNullParameter(v, "v");
        UserBean userBean = this.info;
        if (userBean != null) {
            int id = v.getId();
            if (id == R.id.btn_code) {
                int type = getType();
                if (type == 0 || type == 1) {
                    str = "code";
                } else {
                    str = "pin";
                }
                if (TextUtils.isEmpty(userBean.getTelphone())) {
                    telphone = getMBinding().getPhone();
                    Intrinsics.checkNotNull(telphone);
                } else {
                    telphone = userBean.getTelphone();
                }
                Intrinsics.checkNotNull(telphone);
                Repository.INSTANCE.getVerifyCode(str, telphone, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PhoneActivity$onClick$1$1
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
                        this.this$0.toast(it.getMsg());
                        if (it.getCode() != 200) {
                            PhoneActivity.access$getMBinding(this.this$0).btnCode.resetState();
                        }
                    }
                }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PhoneActivity$onClick$1$2
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
                        PhoneActivity.access$getMBinding(this.this$0).btnCode.resetState();
                        this.this$0.netFail(it);
                    }
                });
                return;
            }
            if (id != R.id.tv_go) {
                return;
            }
            if (TextUtils.isEmpty(getMBinding().getPhone())) {
                toast("请输入手机号");
                return;
            }
            if (TextUtils.isEmpty(getMBinding().getCode())) {
                toast("请输入验证码");
                return;
            }
            int type2 = getType();
            if (type2 == 0) {
                bind();
            } else if (type2 == 1) {
                unbind();
            } else {
                check();
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PhoneActivity.onResume.1
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
                PhoneActivity.this.setInfo(it);
                if (TextUtils.isEmpty(it.getTelphone())) {
                    return;
                }
                PhoneActivity.access$getMBinding(PhoneActivity.this).setPhone(it.getEncodePhone());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PhoneActivity.onResume.2
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
                PhoneActivity.this.netFail(it);
            }
        });
    }

    public final void bind() {
        Repository repository = Repository.INSTANCE;
        String phone = getMBinding().getPhone();
        Intrinsics.checkNotNull(phone);
        String code = getMBinding().getCode();
        Intrinsics.checkNotNull(code);
        repository.bindPhone(phone, code, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PhoneActivity.bind.1
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
                PhoneActivity.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    PhoneActivity.this.finish();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PhoneActivity.bind.2
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
                PhoneActivity.this.netFail(it);
            }
        });
    }

    public final void unbind() {
        Repository repository = Repository.INSTANCE;
        String code = getMBinding().getCode();
        Intrinsics.checkNotNull(code);
        repository.unbindPhone(code, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PhoneActivity.unbind.1
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
                PhoneActivity.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    PhoneActivity.this.finish();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PhoneActivity.unbind.2
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
                PhoneActivity.this.netFail(it);
            }
        });
    }

    public final void check() {
        Repository repository = Repository.INSTANCE;
        UserBean userBean = this.info;
        Intrinsics.checkNotNull(userBean);
        String telphone = userBean.getTelphone();
        String code = getMBinding().getCode();
        Intrinsics.checkNotNull(code);
        repository.checkPhone(telphone, code, new Function1<PinCheckResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PhoneActivity.check.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PinCheckResult pinCheckResult) {
                invoke2(pinCheckResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PinCheckResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                PhoneActivity.this.startActivity(new Intent(PhoneActivity.this, (Class<?>) PinActivity.class).putExtra("type", PhoneActivity.this.getType()).putExtra("hash", it.getHash()));
                PhoneActivity.this.finish();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PhoneActivity.check.2
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
                PhoneActivity.this.netFail(it);
            }
        });
    }
}
