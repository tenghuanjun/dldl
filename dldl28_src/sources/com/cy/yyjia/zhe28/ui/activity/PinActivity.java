package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityPinBinding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PinActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u001bH\u0016J\b\u0010\u001f\u001a\u00020\u001bH\u0016J\u0006\u0010 \u001a\u00020\u001bJ\u0006\u0010!\u001a\u00020\u001bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u0004\u0018\u00010\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000b\u0010\u0007R\u001a\u0010\u000e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0007\"\u0004\b\u0010\u0010\tR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0017\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\r\u001a\u0004\b\u0018\u0010\u0014¨\u0006\""}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/PinActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityPinBinding;", "()V", "firstPin", "", "getFirstPin", "()Ljava/lang/String;", "setFirstPin", "(Ljava/lang/String;)V", "hash", "getHash", "hash$delegate", "Lkotlin/Lazy;", "secondPin", "getSecondPin", "setSecondPin", "step", "", "getStep", "()I", "setStep", "(I)V", "type", "getType", "type$delegate", "go", "", "v", "Landroid/view/View;", "init", "onBackPressed", "setPin", "setView", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PinActivity extends BaseActivity<ActivityPinBinding> {
    public static final int $stable = 8;
    private String firstPin;

    /* JADX INFO: renamed from: hash$delegate, reason: from kotlin metadata */
    private final Lazy hash;
    private String secondPin;
    private int step;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;

    public PinActivity() {
        super(R.layout.activity_pin, 0, 2, null);
        this.type = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.PinActivity$type$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("type", 2));
            }
        });
        this.hash = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.activity.PinActivity$hash$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getIntent().getStringExtra("hash");
            }
        });
        this.step = 1;
        this.firstPin = "";
        this.secondPin = "";
    }

    public final int getType() {
        return ((Number) this.type.getValue()).intValue();
    }

    public final String getHash() {
        return (String) this.hash.getValue();
    }

    public final int getStep() {
        return this.step;
    }

    public final void setStep(int i) {
        this.step = i;
    }

    public final String getFirstPin() {
        return this.firstPin;
    }

    public final void setFirstPin(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.firstPin = str;
    }

    public final String getSecondPin() {
        return this.secondPin;
    }

    public final void setSecondPin(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.secondPin = str;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        if (getHash() == null) {
            toast("验证码已过期");
            finish();
        }
        getMBinding().navigation.setBackClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.PinActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PinActivity.init$lambda$0(this.f$0, view);
            }
        });
        setView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(PinActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public final void setView() {
        String str;
        ActivityPinBinding mBinding = getMBinding();
        mBinding.setPassword("");
        if (this.step == 1) {
            if (getType() == 2) {
                mBinding.setTip("设置二级密码");
                mBinding.setBtn("设置密码");
                mBinding.setHint("交易删除等操作需要二级密码验证");
                return;
            } else {
                mBinding.setTip("修改二级密码");
                mBinding.setBtn("修改密码");
                mBinding.setHint("请输入新的二级密码");
                return;
            }
        }
        mBinding.setHint("交易删除等操作需要二级密码验证");
        mBinding.setBtn("确认密码");
        if (getType() == 2) {
            str = "确认设置二级密码";
        } else {
            str = "确认修改二级密码";
        }
        mBinding.setTip(str);
    }

    public final void go(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        ActivityPinBinding mBinding = getMBinding();
        String password = mBinding.getPassword();
        Intrinsics.checkNotNull(password);
        if (password.length() != 4) {
            toast("请输入二级密码");
            return;
        }
        if (this.step == 1) {
            String password2 = mBinding.getPassword();
            Intrinsics.checkNotNull(password2);
            this.firstPin = password2;
            this.step++;
            setView();
            return;
        }
        String password3 = mBinding.getPassword();
        Intrinsics.checkNotNull(password3);
        this.secondPin = password3;
        setPin();
    }

    public final void setPin() {
        if (!Intrinsics.areEqual(this.firstPin, this.secondPin)) {
            toast("前后两个二级密码不一致");
            return;
        }
        Repository repository = Repository.INSTANCE;
        String hash = getHash();
        Intrinsics.checkNotNull(hash);
        repository.setPin(hash, this.secondPin, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PinActivity.setPin.1
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
                PinActivity.this.toast(it.getMsg());
                if (it.getCode() == 200) {
                    Constant.INSTANCE.setNoPin(false);
                    PinActivity.this.setStep(3);
                    PinActivity.this.finish();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.PinActivity.setPin.2
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
                PinActivity.this.netFail(it);
                PinActivity.this.setStep(r2.getStep() - 1);
                PinActivity.this.setView();
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        int i = this.step;
        if (i == 1 || i == 3) {
            super.onBackPressed();
        } else {
            this.step = i - 1;
            setView();
        }
    }
}
