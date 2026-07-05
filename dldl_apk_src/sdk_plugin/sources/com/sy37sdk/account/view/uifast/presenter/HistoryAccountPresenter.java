package com.sy37sdk.account.view.uifast.presenter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.AccountLogic;
import com.sy37sdk.account.AccountTools;
import com.sy37sdk.account.LoginTractionManager;
import com.sy37sdk.account.UserInfo;
import com.sy37sdk.account.entrance.EntranceManager;
import com.sy37sdk.account.policy.view.PolicyDialog;
import com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter;
import com.sy37sdk.account.uagree.UAgreeManager;
import com.sy37sdk.account.util.AccountUtil;
import com.sy37sdk.account.view.uifast.view.IHistoryAccountView;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class HistoryAccountPresenter extends BaseAccountPagerPresenter<IHistoryAccountView> implements IHistoryAccountPresenter {
    private boolean clauseStatus;

    public HistoryAccountPresenter(Context context, IHistoryAccountView iHistoryAccountView) {
        super(context, iHistoryAccountView);
        this.clauseStatus = false;
    }

    @Override // com.sqwan.common.mvp.BasePresenter, com.sqwan.common.mvp.IPresenter
    public void initData() {
        super.initData();
        UserInfo lastUserInfo = AccountUtil.getLastUserInfo(this.context);
        if (lastUserInfo != null && !TextUtils.isEmpty(lastUserInfo.getUname())) {
            ((IHistoryAccountView) this.mView).setAccount(lastUserInfo);
        } else {
            ((IHistoryAccountView) this.mView).onSwitch(0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter
    public View getView() {
        return (View) this.mView;
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IHistoryAccountPresenter
    public void deleteUser(UserInfo userInfo) throws Throwable {
        AccountTools.delAccountFromFile(this.context, userInfo.getUname());
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IHistoryAccountPresenter
    public void login(UserInfo userInfo) {
        fastLogin(userInfo);
    }

    private void fastLogin(final UserInfo userInfo) {
        if (!this.clauseStatus) {
            UAgreeManager.getInstance().showLoginPolicyAlert(new PolicyDialog.ConfirmCallback() { // from class: com.sy37sdk.account.view.uifast.presenter.-$$Lambda$HistoryAccountPresenter$nLaJJVRotcxBer-MPlkzwJtXWbQ
                @Override // com.sy37sdk.account.policy.view.PolicyDialog.ConfirmCallback
                public final void onConfirm() {
                    this.f$0.lambda$fastLogin$0$HistoryAccountPresenter(userInfo);
                }
            });
            return;
        }
        if (userInfo != null) {
            if (userInfo.isPhoneLoginType() && !TextUtils.isEmpty(userInfo.getTicket())) {
                AccountLogic.getInstance(this.context).phoneLoginTicket(userInfo.getTicket(), userInfo.getMobile(), new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.HistoryAccountPresenter.1
                    @Override // com.sy37sdk.account.AccountLogic.AccountListener
                    public void onSuccess(Map<String, String> map) {
                        LogUtil.i("历史账号页面快速login success");
                        HistoryAccountPresenter.this.trackLoginSuccess(map);
                        if (HistoryAccountPresenter.this.mView != null) {
                            ((IHistoryAccountView) HistoryAccountPresenter.this.mView).hideLoading();
                            ((IHistoryAccountView) HistoryAccountPresenter.this.mView).enableLoginBtn(true);
                            ((IHistoryAccountView) HistoryAccountPresenter.this.mView).loginSuccess(map);
                        }
                    }

                    @Override // com.sy37sdk.account.AccountLogic.AccountListener
                    public void onFailure(int i, String str) {
                        LogUtil.i("历史账号页面ticket登录失败");
                        if (i == AccountLogic.SERVER_ERROR_CODE_TICKET_TIMEOUT) {
                            HistoryAccountPresenter.this.loginNormal(userInfo);
                        } else {
                            ToastUtil.showToast(HistoryAccountPresenter.this.context, str);
                        }
                    }
                });
                return;
            }
            if (!TextUtils.isEmpty(userInfo.getToken()) && !TextUtils.isEmpty(userInfo.getRefreshToken())) {
                if (this.mView != 0) {
                    ((IHistoryAccountView) this.mView).showLoading();
                }
                final String loginType = userInfo.getLoginType();
                LoginTractionManager.trackInvoke(loginType, "6");
                AccountLogic.getInstance(this.context).fastLogin(userInfo, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.HistoryAccountPresenter.2
                    @Override // com.sy37sdk.account.AccountLogic.AccountListener
                    public void onSuccess(Map<String, String> map) {
                        LogUtil.i("历史账号页面快速login success");
                        HistoryAccountPresenter.this.trackLoginSuccess(map);
                        if (HistoryAccountPresenter.this.mView != null) {
                            ((IHistoryAccountView) HistoryAccountPresenter.this.mView).hideLoading();
                            ((IHistoryAccountView) HistoryAccountPresenter.this.mView).enableLoginBtn(true);
                            ((IHistoryAccountView) HistoryAccountPresenter.this.mView).loginSuccess(map);
                        }
                    }

                    @Override // com.sy37sdk.account.AccountLogic.AccountListener
                    public void onFailure(int i, String str) {
                        LoginTractionManager.trackFail(loginType, "6", i + "", str);
                        if (i == 42000) {
                            LogUtil.i("历史账号页面快速登录失败，但是是风控引起的，所以这里不触发帐密登录");
                            if (HistoryAccountPresenter.this.mView != null) {
                                ((IHistoryAccountView) HistoryAccountPresenter.this.mView).hideLoading();
                                ((IHistoryAccountView) HistoryAccountPresenter.this.mView).enableLoginBtn(true);
                            }
                            ToastUtil.showToast(HistoryAccountPresenter.this.context, str);
                            return;
                        }
                        LogUtil.i("历史账号页面快速登录失败，切换至普通登录");
                        HistoryAccountPresenter.this.loginNormal(userInfo);
                    }
                });
                return;
            }
            loginNormal(userInfo);
        }
    }

    public /* synthetic */ void lambda$fastLogin$0$HistoryAccountPresenter(UserInfo userInfo) {
        ((IHistoryAccountView) this.mView).checkedClause();
        this.clauseStatus = true;
        fastLogin(userInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginNormal(UserInfo userInfo) {
        if (this.mView != 0) {
            ((IHistoryAccountView) this.mView).showLoading();
        }
        String mobile = userInfo.getMobile();
        if (TextUtils.equals(userInfo.getLoginType(), "3") && EntranceManager.getInstance().isWxLoginEntrance()) {
            if (this.mView != 0) {
                ((IHistoryAccountView) this.mView).hideLoading();
                if (EntranceManager.getInstance().getWxLoginUIVersion() == 2) {
                    ((IHistoryAccountView) this.mView).startView(0, null);
                    return;
                } else {
                    ((IHistoryAccountView) this.mView).startView(4, null);
                    return;
                }
            }
            return;
        }
        if (TextUtils.isEmpty(mobile)) {
            if (TextUtils.isEmpty(userInfo.getUname()) || TextUtils.isEmpty(userInfo.getUpwd())) {
                if (this.mView != 0) {
                    ((IHistoryAccountView) this.mView).hideLoading();
                    ((IHistoryAccountView) this.mView).startView(1, null);
                    return;
                }
                return;
            }
            loginByAccount(userInfo.getUname(), userInfo.getUpwd());
            return;
        }
        if (TextUtils.isEmpty(userInfo.getUpwd())) {
            if (this.mView != 0) {
                ((IHistoryAccountView) this.mView).hideLoading();
                Bundle bundle = new Bundle();
                bundle.putString("mobile", mobile);
                ((IHistoryAccountView) this.mView).startView(0, bundle);
                return;
            }
            return;
        }
        loginByMobile(userInfo.getMobile(), userInfo.getUpwd());
    }

    private void loginByAccount(String str, String str2) {
        LoginTractionManager.trackInvoke("1", "6");
        AccountLogic.getInstance(this.context).accountLogin(str, str2, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.HistoryAccountPresenter.3
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(Map<String, String> map) {
                HistoryAccountPresenter.this.trackLoginSuccess(map);
                LogUtil.i("login success");
                if (HistoryAccountPresenter.this.mView != null) {
                    ((IHistoryAccountView) HistoryAccountPresenter.this.mView).hideLoading();
                    ((IHistoryAccountView) HistoryAccountPresenter.this.mView).enableLoginBtn(true);
                    ((IHistoryAccountView) HistoryAccountPresenter.this.mView).loginSuccess(map);
                }
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str3) {
                LoginTractionManager.trackFail("1", "6", i + "", str3);
                if (HistoryAccountPresenter.this.mView != null) {
                    ((IHistoryAccountView) HistoryAccountPresenter.this.mView).hideLoading();
                    ((IHistoryAccountView) HistoryAccountPresenter.this.mView).enableLoginBtn(true);
                }
                ToastUtil.showToast(HistoryAccountPresenter.this.context, str3);
                ((IHistoryAccountView) HistoryAccountPresenter.this.mView).startView(1, null);
            }
        });
    }

    private void loginByMobile(String str, String str2) {
        LogUtil.i("调用手机+密码登录");
        LoginTractionManager.trackInvoke("2", "6");
        AccountLogic.getInstance(this.context).phoneLoginPwd(str, str2, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.HistoryAccountPresenter.4
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(Map<String, String> map) {
                LogUtil.i("login success");
                HistoryAccountPresenter.this.trackLoginSuccess(map);
                if (HistoryAccountPresenter.this.mView != null) {
                    ((IHistoryAccountView) HistoryAccountPresenter.this.mView).hideLoading();
                    ((IHistoryAccountView) HistoryAccountPresenter.this.mView).loginSuccess(map);
                }
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str3) {
                LogUtil.i("login onFailure");
                LoginTractionManager.trackFail("2", "6", i + "", str3);
                if (HistoryAccountPresenter.this.mView != null) {
                    ((IHistoryAccountView) HistoryAccountPresenter.this.mView).hideLoading();
                }
                ToastUtil.showToast(HistoryAccountPresenter.this.context, str3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackLoginSuccess(Map<String, String> map) {
        LoginTractionManager.track("6", map);
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IHistoryAccountPresenter
    public void forgetPassword() {
        super.forgotPassword();
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IHistoryAccountPresenter
    public void clauseClick(boolean z) {
        this.clauseStatus = z;
        if (z) {
            SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.agreement, SqTrackBtn.SqTrackBtnExt.agreement);
        }
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IHistoryAccountPresenter
    public void toClausePage() {
        UAgreeManager.getInstance().showUserProtocol(this.context);
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IHistoryAccountPresenter
    public void toPolicy() {
        UAgreeManager.getInstance().showPolicy(this.context);
    }
}
