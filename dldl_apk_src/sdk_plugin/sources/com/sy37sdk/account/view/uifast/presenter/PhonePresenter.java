package com.sy37sdk.account.view.uifast.presenter;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.AccountLogic;
import com.sy37sdk.account.AutoAccountBean;
import com.sy37sdk.account.LoginTractionManager;
import com.sy37sdk.account.entrance.EntranceManager;
import com.sy37sdk.account.policy.view.PolicyDialog;
import com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter;
import com.sy37sdk.account.uagree.UAgreeManager;
import com.sy37sdk.account.view.uifast.view.IPhoneView;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PhonePresenter extends BaseAccountPagerPresenter<IPhoneView> implements IPhonePresenter {
    public static final int CODE_PHONE_VALID = -77710;
    private static final long TIMER_THRESHOLD = 60000;
    private boolean clauseStatus;
    private VerifyCodeTimer timer;

    public PhonePresenter(Context context, IPhoneView iPhoneView) {
        super(context, iPhoneView);
        this.clauseStatus = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter
    public View getView() {
        return (View) this.mView;
    }

    @Override // com.sqwan.common.mvp.BasePresenter, com.sqwan.common.mvp.IPresenter
    public void initData() {
        super.initData();
        ((IPhoneView) this.mView).regEntrance(EntranceManager.getInstance().isQuickRegEntrance());
        ((IPhoneView) this.mView).accountLoginEntrance(EntranceManager.getInstance().isAccountLoginEntrance());
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IPhonePresenter
    public void obtainVerifyCode() {
        String phone = ((IPhoneView) this.mView).getPhone();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast(this.context, "请输入手机号");
            return;
        }
        if (!AppUtils.isMobileNO(phone)) {
            ToastUtil.showToast(this.context, "请填写正确的手机号");
            return;
        }
        if (!this.clauseStatus) {
            UAgreeManager.getInstance().showLoginPolicyAlert(new PolicyDialog.ConfirmCallback() { // from class: com.sy37sdk.account.view.uifast.presenter.-$$Lambda$PhonePresenter$sQnP6Xzc38aQJVSqGBOY1isxREc
                @Override // com.sy37sdk.account.policy.view.PolicyDialog.ConfirmCallback
                public final void onConfirm() {
                    this.f$0.lambda$obtainVerifyCode$0$PhonePresenter();
                }
            });
            return;
        }
        if (this.mView != 0) {
            ((IPhoneView) this.mView).showLoading();
        }
        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.getPhoneCode, SqTrackBtn.SqTrackBtnExt.getPhoneCode);
        AccountLogic.getInstance(this.context).sendPhoneCode(phone, new AccountLogic.VerifyCodeListener() { // from class: com.sy37sdk.account.view.uifast.presenter.PhonePresenter.1
            @Override // com.sy37sdk.account.AccountLogic.VerifyCodeListener
            public void onSuccess() {
                if (PhonePresenter.this.mView != null) {
                    ((IPhoneView) PhonePresenter.this.mView).hideLoading();
                    ((IPhoneView) PhonePresenter.this.mView).startVerifyCodeView();
                }
                ToastUtil.showToast(PhonePresenter.this.context, "请求已发送，请注意查收短信");
            }

            @Override // com.sy37sdk.account.AccountLogic.VerifyCodeListener
            public void onFailure(int i, String str) {
                ToastUtil.showToast(PhonePresenter.this.context, str);
                if (PhonePresenter.this.mView != null) {
                    ((IPhoneView) PhonePresenter.this.mView).hideLoading();
                }
                if (i == -77710 || PhonePresenter.this.mView == null) {
                    return;
                }
                ((IPhoneView) PhonePresenter.this.mView).startVerifyCodeView();
            }
        });
    }

    public /* synthetic */ void lambda$obtainVerifyCode$0$PhonePresenter() {
        this.clauseStatus = true;
        ((IPhoneView) this.mView).checkedClause();
        obtainVerifyCode();
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IPhonePresenter
    public void quickStart() {
        if (!this.clauseStatus) {
            UAgreeManager.getInstance().showLoginPolicyAlert(new PolicyDialog.ConfirmCallback() { // from class: com.sy37sdk.account.view.uifast.presenter.-$$Lambda$PhonePresenter$R_EIoFBPKCMgI59UQAFozbsCbmY
                @Override // com.sy37sdk.account.policy.view.PolicyDialog.ConfirmCallback
                public final void onConfirm() {
                    this.f$0.lambda$quickStart$1$PhonePresenter();
                }
            });
            return;
        }
        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.quickStart, SqTrackBtn.SqTrackBtnExt.quickStart);
        String autoName = AccountCache.getAutoName(this.context);
        String autoPassword = AccountCache.getAutoPassword(this.context);
        if (AccountCache.getUsername(this.context).equals(autoName) || TextUtils.isEmpty(autoPassword)) {
            authReq();
        } else {
            register(autoName, autoPassword);
        }
    }

    public /* synthetic */ void lambda$quickStart$1$PhonePresenter() {
        ((IPhoneView) this.mView).checkedClause();
        this.clauseStatus = true;
        quickStart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void register(String str, String str2) {
        if ("".equals(str) || "".equals(str2)) {
            ToastUtil.showToast(this.context, SqResUtils.getStringByName(this.context, "sy37_reg_input_empty_tips"));
            return;
        }
        if (!str.equals(AccountCache.getAutoName(this.context)) && str.substring(0, 4).equals("37zd")) {
            ToastUtil.showToast(this.context, SqResUtils.getStringByName(this.context, "sy37_reg_not_auto_account_tips"));
            return;
        }
        if (this.mView != 0) {
            ((IPhoneView) this.mView).showLoading();
        }
        LoginTractionManager.trackInvoke("1", "7");
        AccountLogic.getInstance(this.context).accountRegister(str, str2, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.PhonePresenter.2
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(Map<String, String> map) {
                LoginTractionManager.track("7", map);
                if (PhonePresenter.this.mView != null) {
                    ((IPhoneView) PhonePresenter.this.mView).hideLoading();
                    ((IPhoneView) PhonePresenter.this.mView).accountRegSuccess(map);
                }
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str3) {
                LoginTractionManager.trackFail("1", "7", i + "", str3);
                if (PhonePresenter.this.mView != null) {
                    ((IPhoneView) PhonePresenter.this.mView).hideLoading();
                }
                ToastUtil.showToast(PhonePresenter.this.context, str3);
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IPhonePresenter
    public void clauseClick(boolean z) {
        this.clauseStatus = z;
        if (z) {
            SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.agreement, SqTrackBtn.SqTrackBtnExt.agreement);
        }
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IPhonePresenter
    public void toClausePage() {
        UAgreeManager.getInstance().showUserProtocol(this.context);
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IPhonePresenter
    public void toPolicy() {
        UAgreeManager.getInstance().showPolicy(this.context);
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IPhonePresenter
    public void forgetPassword() {
        super.forgotPassword();
    }

    private void authReq() {
        AccountLogic.getInstance(this.context).autoAccount(new AccountLogic.AutoAccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.PhonePresenter.3
            @Override // com.sy37sdk.account.AccountLogic.AutoAccountListener
            public void onSuccess(AutoAccountBean autoAccountBean) {
                LogUtil.i("msg: " + autoAccountBean.getMsg() + ", ssuccess:" + autoAccountBean.getSsuccess());
                String uname = autoAccountBean.getUname();
                String pwd = autoAccountBean.getPwd();
                AccountCache.setAutoName(PhonePresenter.this.context, uname);
                AccountCache.setAutoPassword(PhonePresenter.this.context, pwd);
                AccountCache.setAutoIssave(PhonePresenter.this.context, autoAccountBean.getIssave());
                AccountCache.setAutoState(PhonePresenter.this.context, autoAccountBean.getAutostate());
                PhonePresenter.this.register(uname, pwd);
            }

            @Override // com.sy37sdk.account.AccountLogic.AutoAccountListener
            public void onFailure(int i, String str) {
                LogUtil.e("请求自动注册账号失败code=" + i + ",meg=" + str);
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IPhonePresenter
    public void loginVerifyCode(String str, String str2) {
        LogUtil.i("调用手机+验证码登录");
        if (this.mView != 0) {
            ((IPhoneView) this.mView).showLoading();
        }
        LoginTractionManager.trackInvoke("2", "2");
        AccountLogic.getInstance(this.context).phoneLoginCheckCode(str, str2, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.PhonePresenter.4
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(Map<String, String> map) {
                LogUtil.i("login success");
                LoginTractionManager.track("2", map);
                if (PhonePresenter.this.mView != null) {
                    ((IPhoneView) PhonePresenter.this.mView).hideLoading();
                    ((IPhoneView) PhonePresenter.this.mView).accountRegSuccess(map);
                }
                AccountCache.setTicketState(false);
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str3) {
                LogUtil.i("login onFailure");
                LoginTractionManager.trackFail("2", "2", i + "", str3);
                if (PhonePresenter.this.mView != null) {
                    ((IPhoneView) PhonePresenter.this.mView).hideLoading();
                    if (AccountCache.getTicketState()) {
                        ((IPhoneView) PhonePresenter.this.mView).startView(2, null);
                        AccountCache.setTicketState(false);
                    }
                }
                ToastUtil.showToast(PhonePresenter.this.context, str3);
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IPhonePresenter
    public void initVerifyCodeTimer() {
        long verifyCodeLastTime = AccountCache.getVerifyCodeLastTime(this.context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - verifyCodeLastTime;
        StringBuilder sb = new StringBuilder();
        sb.append("storeLastRequestTime=");
        sb.append(verifyCodeLastTime);
        sb.append(" currentTime=");
        sb.append(jCurrentTimeMillis);
        sb.append(" spaceTime=");
        sb.append(j);
        sb.append("spaceTime < TIMER_THRESHOLD?");
        sb.append(j < 60000);
        LogUtil.i(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("TIMER_THRESHOLD - spaceTime=");
        long j2 = 60000 - j;
        sb2.append(j2);
        LogUtil.i(sb2.toString());
        VerifyCodeTimer verifyCodeTimer = this.timer;
        if (verifyCodeTimer != null) {
            verifyCodeTimer.cancel();
        }
        VerifyCodeTimer verifyCodeTimer2 = new VerifyCodeTimer(j2, 1000L);
        this.timer = verifyCodeTimer2;
        verifyCodeTimer2.start();
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IPhonePresenter
    public void wechatLogin() {
        if (!this.clauseStatus) {
            UAgreeManager.getInstance().showLoginPolicyAlert(new PolicyDialog.ConfirmCallback() { // from class: com.sy37sdk.account.view.uifast.presenter.-$$Lambda$PhonePresenter$T7zI1xpBfPHJTLNbLgtJYSZP8Ww
                @Override // com.sy37sdk.account.policy.view.PolicyDialog.ConfirmCallback
                public final void onConfirm() {
                    this.f$0.lambda$wechatLogin$2$PhonePresenter();
                }
            });
            return;
        }
        if (this.mView != 0) {
            ((IPhoneView) this.mView).showLoading();
        }
        LoginTractionManager.trackInvoke("3", "8");
        AccountLogic.getInstance(this.context).wechatLogin(new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.PhonePresenter.5
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(Map<String, String> map) {
                LoginTractionManager.track("8", map);
                if (PhonePresenter.this.mView != null) {
                    ((IPhoneView) PhonePresenter.this.mView).hideLoading();
                    ((IPhoneView) PhonePresenter.this.mView).accountRegSuccess(map);
                }
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str) {
                if (PhonePresenter.this.mView != null) {
                    ((IPhoneView) PhonePresenter.this.mView).hideLoading();
                }
                ToastUtil.showToast(PhonePresenter.this.context, "登录失败，请稍后再试");
                LoginTractionManager.trackFail("3", "8", i + "", str);
            }
        });
    }

    public /* synthetic */ void lambda$wechatLogin$2$PhonePresenter() {
        this.clauseStatus = true;
        ((IPhoneView) this.mView).checkedClause();
        wechatLogin();
    }

    class VerifyCodeTimer extends CountDownTimer {
        VerifyCodeTimer(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (PhonePresenter.this.mView != null) {
                ((IPhoneView) PhonePresenter.this.mView).setResendCodeStatus(true);
                ((IPhoneView) PhonePresenter.this.mView).setResendCodeText("重新发送");
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (PhonePresenter.this.mView != null) {
                ((IPhoneView) PhonePresenter.this.mView).setResendCodeStatus(false);
                ((IPhoneView) PhonePresenter.this.mView).setResendCodeText((j / 1000) + "s");
            }
        }
    }
}
