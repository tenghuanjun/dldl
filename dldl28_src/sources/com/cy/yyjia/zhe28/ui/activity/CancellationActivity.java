package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import androidx.autofill.HintConstants;
import androidx.fragment.app.Fragment;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.base.FastDialog;
import com.cy.yyjia.zhe28.databinding.ActivityCancellationBinding;
import com.cy.yyjia.zhe28.databinding.FragmentCancellation1Binding;
import com.cy.yyjia.zhe28.databinding.FragmentCancellation2Binding;
import com.cy.yyjia.zhe28.databinding.FragmentCancellation3Binding;
import com.cy.yyjia.zhe28.databinding.FragmentCancellation4Binding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.ui.activity.CancellationActivity;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CancellationActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004\u0011\u0012\u0013\u0014B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u000bH\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/CancellationActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityCancellationBinding;", "()V", "success", "", "getSuccess", "()Z", "setSuccess", "(Z)V", "finish", "", "go", "f", "Landroidx/fragment/app/Fragment;", "init", "onBackPressed", "Fragment1", "Fragment2", "Fragment3", "Fragment4", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CancellationActivity extends BaseActivity<ActivityCancellationBinding> {
    public static final int $stable = 8;
    private boolean success;

    public CancellationActivity() {
        super(R.layout.activity_cancellation, 0, 2, null);
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final void setSuccess(boolean z) {
        this.success = z;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getSupportFragmentManager().beginTransaction().replace(R.id.body, new Fragment1()).commit();
    }

    public final void go(Fragment f) {
        Intrinsics.checkNotNullParameter(f, "f");
        if (f instanceof Fragment1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.body, new Fragment2()).commit();
            return;
        }
        if (f instanceof Fragment2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.body, new Fragment3()).commit();
        } else if (f instanceof Fragment3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.body, new Fragment4()).commit();
            this.success = true;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.success) {
            Constant.INSTANCE.setCancellation(true);
            setResult(1);
        }
        super.finish();
    }

    /* JADX INFO: compiled from: CancellationActivity.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/CancellationActivity$Fragment4;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentCancellation4Binding;", "()V", "init", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Fragment4 extends BaseFragment<FragmentCancellation4Binding> {
        public static final int $stable = 0;

        @Override // com.cy.yyjia.zhe28.base.BaseFragment
        public void init() {
        }

        public Fragment4() {
            super(R.layout.fragment_cancellation4);
        }
    }

    /* JADX INFO: compiled from: CancellationActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/CancellationActivity$Fragment3;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentCancellation3Binding;", "()V", HintConstants.AUTOFILL_HINT_PHONE, "", "getPhone", "()Ljava/lang/String;", "setPhone", "(Ljava/lang/String;)V", "init", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Fragment3 extends BaseFragment<FragmentCancellation3Binding> {
        public static final int $stable = 8;
        private String phone;

        public Fragment3() {
            super(R.layout.fragment_cancellation3);
            this.phone = "";
        }

        public final String getPhone() {
            return this.phone;
        }

        public final void setPhone(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.phone = str;
        }

        @Override // com.cy.yyjia.zhe28.base.BaseFragment
        public void init() {
            Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment3$init$1
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
                    this.this$0.getMBinding().setPhone(it.getEncodePhone());
                    this.this$0.setPhone(it.getTelphone());
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment3$init$2
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
                    this.this$0.netFail(it);
                }
            });
            getMBinding().tvGo.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment3$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CancellationActivity.Fragment3.init$lambda$2(this.f$0, view);
                }
            });
            getMBinding().btnCode.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment3$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CancellationActivity.Fragment3.init$lambda$3(this.f$0, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$2(final Fragment3 this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (view.isSelected()) {
                return;
            }
            new FastDialog(this$0.getMContext()).setContentView(R.layout.dialog_canellation_user_determine).setOnClickListener(R.id.tv_go, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment3$$ExternalSyntheticLambda2
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view2) {
                    CancellationActivity.Fragment3.init$lambda$2$lambda$0(this.f$0, baseDialog, view2);
                }
            }).setOnClickListener(R.id.tv_cancel, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment3$$ExternalSyntheticLambda3
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view2) {
                    baseDialog.dismiss();
                }
            }).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$2$lambda$0(final Fragment3 this$0, final BaseDialog baseDialog, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Repository repository = Repository.INSTANCE;
            String code = this$0.getMBinding().getCode();
            Intrinsics.checkNotNull(code);
            repository.unsetAccount(code, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment3$init$3$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                    invoke2(result);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Result result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    this.this$0.toast(result.getMsg());
                    if (result.getCode() == 200) {
                        baseDialog.dismiss();
                        BaseActivity mContext = this.this$0.getMContext();
                        Intrinsics.checkNotNull(mContext, "null cannot be cast to non-null type com.cy.yyjia.zhe28.ui.activity.CancellationActivity");
                        ((CancellationActivity) mContext).go(this.this$0);
                    }
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment3$init$3$1$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    this.this$0.netFail(e);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$3(final Fragment3 this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Repository.INSTANCE.getVerifyCode("code", this$0.phone, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment3$init$4$1
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
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment3$init$4$2
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
                    this.this$0.getMBinding().btnCode.resetState();
                    this.this$0.netFail(it);
                }
            });
        }
    }

    /* JADX INFO: compiled from: CancellationActivity.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/CancellationActivity$Fragment2;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentCancellation2Binding;", "()V", "init", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Fragment2 extends BaseFragment<FragmentCancellation2Binding> {
        public static final int $stable = 0;

        public Fragment2() {
            super(R.layout.fragment_cancellation2);
        }

        @Override // com.cy.yyjia.zhe28.base.BaseFragment
        public void init() {
            getMBinding().tvGo.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment2$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CancellationActivity.Fragment2.init$lambda$0(this.f$0, view);
                }
            });
            getMBinding().tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment2$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CancellationActivity.Fragment2.init$lambda$1(this.f$0, view);
                }
            });
            getMBinding().ivCheck.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment2$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CancellationActivity.Fragment2.init$lambda$2(this.f$0, view);
                }
            });
            getMBinding().f457tv.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment2$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CancellationActivity.Fragment2.init$lambda$3(this.f$0, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$0(Fragment2 this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (this$0.getMBinding().getCheck()) {
                BaseActivity<?> mContext = this$0.getMContext();
                Intrinsics.checkNotNull(mContext, "null cannot be cast to non-null type com.cy.yyjia.zhe28.ui.activity.CancellationActivity");
                ((CancellationActivity) mContext).go(this$0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$1(Fragment2 this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getMContext().finish();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$2(Fragment2 this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getMBinding().setCheck(!this$0.getMBinding().getCheck());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$3(Fragment2 this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Util.openProtocol(this$0.getMContext(), "账号注销须知", "unset");
        }
    }

    /* JADX INFO: compiled from: CancellationActivity.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/CancellationActivity$Fragment1;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentCancellation1Binding;", "()V", "init", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Fragment1 extends BaseFragment<FragmentCancellation1Binding> {
        public static final int $stable = 0;

        public Fragment1() {
            super(R.layout.fragment_cancellation1);
        }

        @Override // com.cy.yyjia.zhe28.base.BaseFragment
        public void init() {
            getMBinding().f456tv.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.CancellationActivity$Fragment1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CancellationActivity.Fragment1.init$lambda$0(this.f$0, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$0(Fragment1 this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            BaseActivity<?> mContext = this$0.getMContext();
            Intrinsics.checkNotNull(mContext, "null cannot be cast to non-null type com.cy.yyjia.zhe28.ui.activity.CancellationActivity");
            ((CancellationActivity) mContext).go(this$0);
        }
    }
}
