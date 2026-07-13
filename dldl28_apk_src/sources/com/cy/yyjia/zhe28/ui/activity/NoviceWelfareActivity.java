package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityNoviceWelfareBinding;
import com.cy.yyjia.zhe28.domain.NoviceDataBean;
import com.cy.yyjia.zhe28.domain.UnableGameBean;
import com.cy.yyjia.zhe28.ui.dialog.RuleDialog;
import com.cy.yyjia.zhe28.ui.dialog.UnableGameDialog;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoviceWelfareActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\b\u0010\b\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/NoviceWelfareActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityNoviceWelfareBinding;", "Landroid/view/View$OnClickListener;", "()V", "getAllData", "", "getCoupon", "init", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class NoviceWelfareActivity extends BaseActivity<ActivityNoviceWelfareBinding> implements View.OnClickListener {
    public static final int $stable = 0;

    public static final /* synthetic */ ActivityNoviceWelfareBinding access$getMBinding(NoviceWelfareActivity noviceWelfareActivity) {
        return noviceWelfareActivity.getMBinding();
    }

    public NoviceWelfareActivity() {
        super(R.layout.activity_novice_welfare, 1);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        setLoginSuccessListener(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceWelfareActivity.init.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                NoviceWelfareActivity.this.getAllData();
            }
        });
        getAllData();
    }

    public final void getAllData() {
        getCoupon();
    }

    public final void getCoupon() {
        Repository.INSTANCE.getNoviceCouponData(new Function1<NoviceDataBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceWelfareActivity.getCoupon.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NoviceDataBean noviceDataBean) {
                invoke2(noviceDataBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(NoviceDataBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                NoviceWelfareActivity.access$getMBinding(NoviceWelfareActivity.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceWelfareActivity.getCoupon.2
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
                NoviceWelfareActivity.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.iv_invite /* 2131362180 */:
                startActivity(InviteActivity.class);
                break;
            case R.id.iv_service /* 2131362193 */:
                Util.toService(getMContext());
                break;
            case R.id.tv_game /* 2131362708 */:
                Repository.INSTANCE.getUnableGames2(new Function1<List<UnableGameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceWelfareActivity.onClick.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(List<UnableGameBean> list) {
                        invoke2(list);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(List<UnableGameBean> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        new UnableGameDialog(NoviceWelfareActivity.this).setData(it).show();
                    }
                }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.NoviceWelfareActivity.onClick.2
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
                        NoviceWelfareActivity.this.netFail(it);
                    }
                });
                break;
            case R.id.tv_more /* 2131362730 */:
                Util.skip(getMContext(), DailyTaskActivity.class);
                break;
            case R.id.tv_qiandao /* 2131362755 */:
                startActivity(QiandaoActivity.class);
                break;
            case R.id.tv_record /* 2131362757 */:
                Util.openWebWithLogin(this, "申请记录", NetUtil.BASE_URL3 + "dist/apply-for-rewards-record?module=1");
                break;
            case R.id.tv_rule /* 2131362764 */:
                if (getMBinding().getData() != null) {
                    RuleDialog ruleDialog = new RuleDialog(this);
                    NoviceDataBean data = getMBinding().getData();
                    Intrinsics.checkNotNull(data);
                    ruleDialog.setTextStr(data.getRule()).show();
                }
                break;
            case R.id.tv_show /* 2131362773 */:
                Util.openWeb(this, "发放公示", NetUtil.BASE_URL3 + "dist/coupon-new-user-notice", false);
                break;
        }
    }
}
