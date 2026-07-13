package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivitySettingBinding;
import com.cy.yyjia.zhe28.domain.LoginChangeBean;
import com.cy.yyjia.zhe28.domain.UpdateBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.ui.dialog.UpdateDialog;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.lzy.okserver.OkDownload;
import com.mobile.auth.gatewayauth.Constant;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.BooleanUtils;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: SettingActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\"\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0014J\u0006\u0010\u0011\u001a\u00020\u0006¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/SettingActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivitySettingBinding;", "Landroid/view/View$OnClickListener;", "()V", "init", "", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onResume", "update", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SettingActivity extends BaseActivity<ActivitySettingBinding> implements View.OnClickListener {
    public static final int $stable = 0;

    public SettingActivity() {
        super(R.layout.activity_setting, 0, 2, null);
    }

    public static final /* synthetic */ ActivitySettingBinding access$getMBinding(SettingActivity settingActivity) {
        return settingActivity.getMBinding();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().tvCache.setText(Util.getTotalCacheSize(this));
        getMBinding().ivSwitchApk.setSelected(com.cy.yyjia.zhe28.util.Constant.INSTANCE.getDeleteApk());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.iv_switch_apk /* 2131362199 */:
                getMBinding().ivSwitchApk.setSelected(true ^ getMBinding().ivSwitchApk.isSelected());
                com.cy.yyjia.zhe28.util.Constant.INSTANCE.setDeleteApk(getMBinding().ivSwitchApk.isSelected());
                SharedPreferences.Editor editorEdit = getSharedPreferences("user", 0).edit();
                editorEdit.putBoolean("deleteApk", com.cy.yyjia.zhe28.util.Constant.INSTANCE.getDeleteApk());
                editorEdit.apply();
                break;
            case R.id.ll_about /* 2131362241 */:
                Util.openProtocol(this, "关于我们", "aboutUs");
                break;
            case R.id.ll_cancellation /* 2131362249 */:
                startActivityForResult(new Intent(this, (Class<?>) CancellationActivity.class), 1);
                break;
            case R.id.ll_parents /* 2131362267 */:
                Util.openProtocol(this, "家长监护", "parentalGuardianship");
                break;
            case R.id.ll_sdk /* 2131362278 */:
                Util.openProtocol(this, "第三方SDK列表", "thirdInfoShareList");
                break;
            case R.id.ll_update /* 2131362283 */:
                update();
                break;
            case R.id.rl_user /* 2131362448 */:
                doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity.onClick.1
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
                        SettingActivity.this.startActivity(InfoActivity.class);
                    }
                });
                break;
            case R.id.tv_cache /* 2131362666 */:
                SettingActivity settingActivity = this;
                Util.clearAllCache(settingActivity);
                OkDownload.getInstance().removeAll(true);
                getMBinding().tvCache.setText(Util.getTotalCacheSize(settingActivity));
                break;
            case R.id.tv_download /* 2131362692 */:
                toast("已清除错误文件，请重新下载");
                OkDownload.getInstance().removeAll(true);
                break;
            case R.id.tv_download_manager /* 2131362693 */:
                startActivity(DownloadManagerActivity.class);
                break;
            case R.id.tv_feedback /* 2131362699 */:
                doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity.onClick.3
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
                        SettingActivity.this.startActivity(FeedbackActivity.class);
                    }
                });
                break;
            case R.id.tv_logout /* 2131362723 */:
                EventBus.getDefault().post(new LoginChangeBean());
                com.cy.yyjia.zhe28.util.Constant.INSTANCE.logout(this);
                finish();
                break;
            case R.id.tv_safe /* 2131362765 */:
                doWithLogin(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity.onClick.2
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
                        SettingActivity.this.startActivity(SafeActivity.class);
                    }
                });
                break;
        }
    }

    public final void update() {
        Repository.INSTANCE.update(new Function1<UpdateBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity.update.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UpdateBean updateBean) {
                invoke2(updateBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UpdateBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (Intrinsics.areEqual(it.getIsUpdate(), BooleanUtils.YES) && it.getVersionCode() > 429) {
                    new UpdateDialog(SettingActivity.this, it).show();
                } else {
                    SettingActivity.this.toast("已经是最新版本，不需要更新");
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity.update.2
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
                SettingActivity.this.netFail(it);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (com.cy.yyjia.zhe28.util.Constant.INSTANCE.getCancellation()) {
            com.cy.yyjia.zhe28.util.Constant.INSTANCE.logout(this);
            finish();
        } else if (com.cy.yyjia.zhe28.util.Constant.INSTANCE.getLogged()) {
            Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity.onResume.1
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
                    SettingActivity.access$getMBinding(SettingActivity.this).setData(it);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity.onResume.2
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
                    SettingActivity.this.netFail(it);
                }
            });
        }
        getMBinding().tvLogout.setVisibility(com.cy.yyjia.zhe28.util.Constant.INSTANCE.getLogged() ? 0 : 8);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            com.cy.yyjia.zhe28.util.Constant.INSTANCE.logout(this);
            finish();
        }
    }
}
