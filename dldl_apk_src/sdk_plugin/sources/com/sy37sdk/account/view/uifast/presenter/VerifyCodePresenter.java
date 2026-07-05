package com.sy37sdk.account.view.uifast.presenter;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.AccountLogic;
import com.sy37sdk.account.LoginTractionManager;
import com.sy37sdk.account.UrlConstant;
import com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter;
import com.sy37sdk.account.view.uifast.view.IVerifyCodeView;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class VerifyCodePresenter extends BaseAccountPagerPresenter<IVerifyCodeView> implements IVerifyCodePresenter {
    private static final long TIMER_THRESHOLD = 60000;
    private VerifyCodeTimer timer;

    public VerifyCodePresenter(Context context, IVerifyCodeView iVerifyCodeView) {
        super(context, iVerifyCodeView);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter
    public View getView() {
        return (View) this.mView;
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IVerifyCodePresenter
    public void sendCode(String str) {
        ((IVerifyCodeView) this.mView).showLoading();
        AccountLogic.getInstance(this.context).sendPhoneCode(str, new AccountLogic.VerifyCodeListener() { // from class: com.sy37sdk.account.view.uifast.presenter.VerifyCodePresenter.1
            @Override // com.sy37sdk.account.AccountLogic.VerifyCodeListener
            public void onSuccess() {
                if (VerifyCodePresenter.this.mView != null) {
                    ((IVerifyCodeView) VerifyCodePresenter.this.mView).hideLoading();
                }
                ToastUtil.showToast(VerifyCodePresenter.this.context, "请求已发送，请注意查收短信");
                VerifyCodePresenter.this.initTimer();
            }

            @Override // com.sy37sdk.account.AccountLogic.VerifyCodeListener
            public void onFailure(int i, String str2) {
                ToastUtil.showToast(VerifyCodePresenter.this.context, str2);
                if (VerifyCodePresenter.this.mView != null) {
                    ((IVerifyCodeView) VerifyCodePresenter.this.mView).hideLoading();
                }
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IVerifyCodePresenter
    public void loginPwd(String str, String str2) {
        LogUtil.i("调用手机+密码登录");
        if (TextUtils.isEmpty(str2)) {
            ToastUtil.showToast(this.context, "请输入密码");
            return;
        }
        ((IVerifyCodeView) this.mView).showLoading();
        LoginTractionManager.trackInvoke("2", "5");
        AccountLogic.getInstance(this.context).phoneLoginPwd(str, str2, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.VerifyCodePresenter.2
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(Map<String, String> map) {
                LogUtil.i("login success");
                VerifyCodePresenter.this.trackLoginSuccess(map, false);
                if (VerifyCodePresenter.this.mView != null) {
                    ((IVerifyCodeView) VerifyCodePresenter.this.mView).hideLoading();
                    ((IVerifyCodeView) VerifyCodePresenter.this.mView).loginSuccess(map);
                }
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str3) {
                LogUtil.i("login onFailure");
                LoginTractionManager.trackFail("2", "5", i + "", str3);
                if (VerifyCodePresenter.this.mView != null) {
                    ((IVerifyCodeView) VerifyCodePresenter.this.mView).hideLoading();
                }
                ToastUtil.showToast(VerifyCodePresenter.this.context, str3);
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IVerifyCodePresenter
    public void loginVerifyCode(String str, String str2) {
        LogUtil.i("调用手机+验证码登录");
        ((IVerifyCodeView) this.mView).showLoading();
        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.verifyPhoneCode, SqTrackBtn.SqTrackBtnExt.verifyPhoneCode);
        LoginTractionManager.trackInvoke("2", "2");
        AccountLogic.getInstance(this.context).phoneLoginCheckCode(str, str2, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.VerifyCodePresenter.3
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(Map<String, String> map) {
                LogUtil.i("login success");
                VerifyCodePresenter.this.trackLoginSuccess(map, true);
                if (VerifyCodePresenter.this.mView != null) {
                    ((IVerifyCodeView) VerifyCodePresenter.this.mView).hideLoading();
                    ((IVerifyCodeView) VerifyCodePresenter.this.mView).loginSuccess(map);
                }
                AccountCache.setTicketState(false);
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str3) {
                LogUtil.i("login onFailure");
                LoginTractionManager.trackFail("2", "2", i + "", str3);
                if (VerifyCodePresenter.this.mView != null) {
                    ((IVerifyCodeView) VerifyCodePresenter.this.mView).hideLoading();
                    if (AccountCache.getTicketState()) {
                        ((IVerifyCodeView) VerifyCodePresenter.this.mView).startView(2, null);
                        AccountCache.setTicketState(false);
                    }
                }
                ToastUtil.showToast(VerifyCodePresenter.this.context, str3);
            }
        });
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IVerifyCodePresenter
    public void initTimer() {
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

    @Override // com.sy37sdk.account.view.uifast.presenter.IVerifyCodePresenter
    public void toAppeal() {
        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.loginHelp, SqTrackBtn.SqTrackBtnExt.loginHelp);
        AppUtils.toSQWebUrl(this.context, UrlConstant.APPEAL_PAGE, "账号申诉");
    }

    class VerifyCodeTimer extends CountDownTimer {
        VerifyCodeTimer(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (VerifyCodePresenter.this.mView != null) {
                ((IVerifyCodeView) VerifyCodePresenter.this.mView).verifyCodeStatus(true);
                ((IVerifyCodeView) VerifyCodePresenter.this.mView).verifyCodeText("重新发送");
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (VerifyCodePresenter.this.mView != null) {
                ((IVerifyCodeView) VerifyCodePresenter.this.mView).verifyCodeStatus(false);
                ((IVerifyCodeView) VerifyCodePresenter.this.mView).verifyCodeText((j / 1000) + "");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackLoginSuccess(Map<String, String> map, boolean z) {
        LoginTractionManager.track(z ? "2" : "5", map);
    }
}
