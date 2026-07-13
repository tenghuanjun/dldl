package com.cy.yyjia.zhe28.ui.activity;

import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityInviteWithdrewBinding;
import com.cy.yyjia.zhe28.domain.InviteInfoBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InviteWithdrewActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0014¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/InviteWithdrewActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityInviteWithdrewBinding;", "Landroid/view/View$OnClickListener;", "()V", "getData", "", "init", "onClick", "v", "Landroid/view/View;", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class InviteWithdrewActivity extends BaseActivity<ActivityInviteWithdrewBinding> implements View.OnClickListener {
    public static final int $stable = 0;

    public static final /* synthetic */ ActivityInviteWithdrewBinding access$getMBinding(InviteWithdrewActivity inviteWithdrewActivity) {
        return inviteWithdrewActivity.getMBinding();
    }

    public InviteWithdrewActivity() {
        super(R.layout.activity_invite_withdrew, 2);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setNumber("");
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteWithdrewActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InviteWithdrewActivity.init$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(InviteWithdrewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(BillActivity.class);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getData();
        getMBinding().tvRule.setMovementMethod(ScrollingMovementMethod.getInstance());
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteWithdrewActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InviteWithdrewActivity.onResume$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResume$lambda$1(InviteWithdrewActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(InviteBillActivity.class);
    }

    public final void getData() {
        Repository.INSTANCE.getInviteInfo(new Function1<InviteInfoBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteWithdrewActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InviteInfoBean inviteInfoBean) {
                invoke2(inviteInfoBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InviteInfoBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                InviteWithdrewActivity.access$getMBinding(InviteWithdrewActivity.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteWithdrewActivity.getData.2
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
                InviteWithdrewActivity.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (v.getId() == R.id.btn) {
            if (TextUtils.isEmpty(getMBinding().getNumber())) {
                toast("请输入提现金额");
                return;
            }
            Repository repository = Repository.INSTANCE;
            String number = getMBinding().getNumber();
            Intrinsics.checkNotNull(number);
            Repository.inviteWithdrew$default(repository, number, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteWithdrewActivity.onClick.1
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
                    InviteWithdrewActivity.this.toast(it.getMsg());
                    InviteWithdrewActivity.this.getData();
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InviteWithdrewActivity.onClick.2
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
                    InviteWithdrewActivity.this.netFail(it);
                }
            }, 0, 8, null);
        }
    }
}
