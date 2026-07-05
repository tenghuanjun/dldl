package com.sy37sdk.account.presenter;

import android.content.Context;
import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.user.UserInfoManager;
import com.sqwan.common.util.JsonMap;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.AccountLogic;
import com.sy37sdk.account.LoginTractionManager;
import com.sy37sdk.account.UserInfo;
import com.sy37sdk.account.view.IAutoLoginDialog;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AutoLoginPresenter extends BaseAutoLoginPresenter {
    private boolean isMobile;
    private String name;
    private String pwd;

    public AutoLoginPresenter(Context context, IAutoLoginDialog iAutoLoginDialog) {
        super(context, iAutoLoginDialog);
    }

    @Override // com.sy37sdk.account.presenter.BaseAutoLoginPresenter
    public void autoLoginFinish(Map<String, String> map) {
        super.autoLoginFinish(map);
        ((IAutoLoginDialog) this.mView).autoLoginSuccess(map);
    }

    public void setLoginInfo(String str, String str2, boolean z) {
        this.name = str;
        this.pwd = str2;
        this.isMobile = z;
    }

    @Override // com.sy37sdk.account.presenter.IAutoLoginPresenter
    public void autoLogin() {
        if (!TextUtils.isEmpty(this.name)) {
            doLogin();
        } else {
            SQLog.e("【Login AutoP】用户名为空, 无法自动登录");
        }
    }

    private void doLogin() {
        UserInfo userInfo = AccountCache.getUserInfo(this.context);
        if (userInfo != null) {
            checkAndReport(userInfo);
            if (userInfo.isPhoneLoginType() && !TextUtils.isEmpty(userInfo.getTicket())) {
                AccountLogic.getInstance(this.context).phoneLoginTicket(userInfo.getTicket(), userInfo.getMobile(), new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.presenter.AutoLoginPresenter.1
                    @Override // com.sy37sdk.account.AccountLogic.AccountListener
                    public void onSuccess(Map<String, String> map) {
                        AutoLoginPresenter.this.loginSuccess(map);
                    }

                    @Override // com.sy37sdk.account.AccountLogic.AccountListener
                    public void onFailure(int i, String str) {
                        SQLog.w("【Login AutoP】ticket登录失败");
                        ToastUtil.showToast(AutoLoginPresenter.this.context, str);
                        AutoLoginPresenter.this.loginAccount();
                    }
                });
                return;
            }
            if (!TextUtils.isEmpty(userInfo.getToken()) && !TextUtils.isEmpty(userInfo.getRefreshToken())) {
                SQLog.d("【Login AutoP】自动登录");
                AccountLogic.getInstance(this.context).fastLogin(userInfo, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.presenter.AutoLoginPresenter.2
                    @Override // com.sy37sdk.account.AccountLogic.AccountListener
                    public void onSuccess(Map<String, String> map) {
                        AutoLoginPresenter.this.loginSuccess(map);
                    }

                    @Override // com.sy37sdk.account.AccountLogic.AccountListener
                    public void onFailure(int i, String str) {
                        if (i == 42000) {
                            if (AutoLoginPresenter.this.isMobile) {
                                if (TextUtils.isEmpty(AutoLoginPresenter.this.pwd)) {
                                    LoginTractionManager.trackInvoke("2", "4");
                                    LoginTractionManager.trackFail("2", "4", "-1", "手机号自动登录没有密码");
                                    AutoLoginPresenter.this.loginFail(-1, "");
                                    return;
                                } else {
                                    LoginTractionManager.trackInvoke("2", "4");
                                    LoginTractionManager.trackFail("2", "4", i + "", str);
                                    AutoLoginPresenter.this.loginFail(i, str);
                                    return;
                                }
                            }
                            LoginTractionManager.trackInvoke("1", "4");
                            LoginTractionManager.trackFail("1", "4", i + "", str);
                            AutoLoginPresenter.this.loginFail(i, str);
                            return;
                        }
                        AutoLoginPresenter.this.loginAccount();
                    }
                });
                return;
            } else {
                SQLog.w("【Login AutoP】无效的用户信息, 无法自动登录");
                loginAccount();
                return;
            }
        }
        SQLog.w("【Login AutoP】无用户信息, 无法自动登录");
        loginAccount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginAccount() {
        UserInfo userInfo = AccountCache.getUserInfo(this.context);
        if (userInfo != null && TextUtils.equals(userInfo.getLoginType(), "3")) {
            UserInfo.wechatLoginFail = true;
            loginFail(-1, "");
        } else if (!this.isMobile) {
            AccountLogic.getInstance(this.context).accountLogin(this.name, this.pwd, false, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.presenter.AutoLoginPresenter.3
                @Override // com.sy37sdk.account.AccountLogic.AccountListener
                public void onSuccess(Map<String, String> map) {
                    AutoLoginPresenter.this.loginSuccess(map);
                }

                @Override // com.sy37sdk.account.AccountLogic.AccountListener
                public void onFailure(int i, String str) {
                    LoginTractionManager.trackInvoke("1", "4");
                    LoginTractionManager.trackFail("1", "4", i + "", str);
                    AutoLoginPresenter.this.loginFail(i, str);
                }
            });
        } else {
            if (TextUtils.isEmpty(this.pwd)) {
                LoginTractionManager.trackInvoke("2", "4");
                LoginTractionManager.trackFail("2", "4", "-1", "手机号自动登录没有密码");
                loginFail(-1, "");
                return;
            }
            AccountLogic.getInstance(this.context).phoneLoginPwd(this.name, this.pwd, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.presenter.AutoLoginPresenter.4
                @Override // com.sy37sdk.account.AccountLogic.AccountListener
                public void onSuccess(Map<String, String> map) {
                    AutoLoginPresenter.this.loginSuccess(map);
                }

                @Override // com.sy37sdk.account.AccountLogic.AccountListener
                public void onFailure(int i, String str) {
                    LoginTractionManager.trackInvoke("2", "4");
                    LoginTractionManager.trackFail("2", "4", i + "", str);
                    AutoLoginPresenter.this.loginFail(i, str);
                }
            });
        }
    }

    private void checkAndReport(UserInfo userInfo) {
        com.sqwan.common.user.UserInfo lastLoginUser = UserInfoManager.getInstance().getLastLoginUser();
        JsonMap jsonMap = new JsonMap();
        jsonMap.put("desc", "last user");
        jsonMap.put("old", userInfo.toString());
        boolean z = true;
        if (lastLoginUser != null && Objects.equals(userInfo.getToken(), lastLoginUser.getToken()) && Objects.equals(userInfo.getUid(), lastLoginUser.getUid()) && Objects.equals(userInfo.getUname(), lastLoginUser.getUname()) && Objects.equals(userInfo.getLoginType(), String.valueOf(lastLoginUser.type.code))) {
            String pwd = com.sqwan.common.user.UserInfo.getPwd(lastLoginUser);
            String upwd = userInfo.getUpwd();
            if (upwd == null) {
                upwd = "";
            }
            String str = lastLoginUser.refreshToken;
            if (str == null) {
                str = "";
            }
            String refreshToken = userInfo.getRefreshToken();
            String str2 = refreshToken != null ? refreshToken : "";
            if (Objects.equals(pwd, upwd) && Objects.equals(str, str2)) {
                z = false;
            }
        }
        if (z) {
            if (lastLoginUser != null) {
                jsonMap.put("new_user", lastLoginUser.toString());
            }
            BuglessAction.reportCatchException(new IllegalArgumentException(), "上次登录用户参数重构前后不一致", jsonMap.toString(), 999);
        }
    }
}
