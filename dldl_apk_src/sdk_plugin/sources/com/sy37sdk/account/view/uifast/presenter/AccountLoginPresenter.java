package com.sy37sdk.account.view.uifast.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.AccountLogic;
import com.sy37sdk.account.AccountTools;
import com.sy37sdk.account.AutoAccountBean;
import com.sy37sdk.account.LoginTractionManager;
import com.sy37sdk.account.UserInfo;
import com.sy37sdk.account.entrance.EntranceManager;
import com.sy37sdk.account.policy.view.PolicyDialog;
import com.sy37sdk.account.presenter.fast.BaseAccountPagerPresenter;
import com.sy37sdk.account.uagree.UAgreeManager;
import com.sy37sdk.account.util.AccountUtil;
import com.sy37sdk.account.view.uifast.view.IAccountLoginView;
import java.util.Map;
import org.json.JSONArray;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountLoginPresenter extends BaseAccountPagerPresenter<IAccountLoginView> implements IAccountLoginPresenter {
    private static final int REG_NAME_MAX_LENGTH = 20;
    private static final int REG_NAME_MIN_LENGTH = 4;
    private static final int REG_PWD_MIN_LENGTH = 6;
    public static String autoMsg = "您的账号已注册成功！";
    public static String autoSuccess = "已截图保存至手机相册";
    public static String autoTitle = "注册成功";
    private boolean clauseStatus;

    public AccountLoginPresenter(Context context, IAccountLoginView iAccountLoginView) {
        super(context, iAccountLoginView);
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
        ((IAccountLoginView) this.mView).toggleUI(0);
        ((IAccountLoginView) this.mView).regEntrance(EntranceManager.getInstance().isUNameRegEntrance());
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IAccountLoginPresenter
    public void deleteUser(UserInfo userInfo) throws Throwable {
        AccountTools.delAccountFromFile(this.context, userInfo.getUname());
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IAccountLoginPresenter
    public void login(final String str, final String str2) {
        if (TextUtils.isEmpty(str)) {
            ToastUtil.showToast(this.context, "请输入账号");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            ToastUtil.showToast(this.context, "请输入密码");
            return;
        }
        if (!this.clauseStatus) {
            UAgreeManager.getInstance().showLoginPolicyAlert(new PolicyDialog.ConfirmCallback() { // from class: com.sy37sdk.account.view.uifast.presenter.-$$Lambda$AccountLoginPresenter$_9kMXhrIlYBUihuTgV6P3dXoLBA
                @Override // com.sy37sdk.account.policy.view.PolicyDialog.ConfirmCallback
                public final void onConfirm() {
                    this.f$0.lambda$login$0$AccountLoginPresenter(str, str2);
                }
            });
            return;
        }
        if (this.mView != 0) {
            ((IAccountLoginView) this.mView).enableLoginBtn(false);
            ((IAccountLoginView) this.mView).showLoading();
        }
        SqTrackActionManager2.getInstance().trackBtn("btn05", SqTrackBtn.SqTrackBtnExt.login);
        LoginTractionManager.trackInvoke("1", "1");
        AccountLogic.getInstance(this.context).checkAccountList(str, str2, new AccountLogic.MultiAccountHandler() { // from class: com.sy37sdk.account.view.uifast.presenter.-$$Lambda$AccountLoginPresenter$lcfwGXLd55BL9ihez0kDZ1kRPPA
            @Override // com.sy37sdk.account.AccountLogic.MultiAccountHandler
            public final void onMultiAccount(JSONArray jSONArray, String str3, String str4) {
                this.f$0.lambda$login$1$AccountLoginPresenter(jSONArray, str3, str4);
            }
        }, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.AccountLoginPresenter.1
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(Map<String, String> map) {
                LoginTractionManager.track("1", map);
                LogUtil.i("login success");
                if (AccountLoginPresenter.this.mView != null) {
                    ((IAccountLoginView) AccountLoginPresenter.this.mView).hideLoading();
                    ((IAccountLoginView) AccountLoginPresenter.this.mView).enableLoginBtn(true);
                    ((IAccountLoginView) AccountLoginPresenter.this.mView).loginSuccess(map);
                }
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str3) {
                LoginTractionManager.trackFail("1", "1", i + "", str3);
                if (AccountLoginPresenter.this.mView != null) {
                    ((IAccountLoginView) AccountLoginPresenter.this.mView).hideLoading();
                    ((IAccountLoginView) AccountLoginPresenter.this.mView).enableLoginBtn(true);
                }
                ToastUtil.showToast(AccountLoginPresenter.this.context, str3);
            }
        });
    }

    public /* synthetic */ void lambda$login$0$AccountLoginPresenter(String str, String str2) {
        this.clauseStatus = true;
        ((IAccountLoginView) this.mView).checkedClause();
        login(str, str2);
    }

    public /* synthetic */ void lambda$login$1$AccountLoginPresenter(JSONArray jSONArray, String str, String str2) {
        if (this.mView != 0) {
            ((IAccountLoginView) this.mView).enableLoginBtn(true);
            ((IAccountLoginView) this.mView).hideLoading();
            ((IAccountLoginView) this.mView).selectMultiAccount(jSONArray, str, str2);
        }
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IAccountLoginPresenter
    public void accountRegister(final String str, final String str2) {
        if (TextUtils.isEmpty(str)) {
            ToastUtil.showToast(this.context, "请输入账号");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            ToastUtil.showToast(this.context, "请输入密码");
            return;
        }
        if (str.length() < 4 || str.length() > 20) {
            ToastUtil.showToast(this.context, SqResUtils.getStringByName(this.context, "sy37_reg_account_illegal_tips"));
            return;
        }
        if (str2.length() < 6) {
            ToastUtil.showToast(this.context, SqResUtils.getStringByName(this.context, "sy37_reg_password_illegal_tips"));
            return;
        }
        if (!this.clauseStatus) {
            UAgreeManager.getInstance().showLoginPolicyAlert(new PolicyDialog.ConfirmCallback() { // from class: com.sy37sdk.account.view.uifast.presenter.-$$Lambda$AccountLoginPresenter$h5rGmjRKnAMIrMz8qbuOsZI1oR0
                @Override // com.sy37sdk.account.policy.view.PolicyDialog.ConfirmCallback
                public final void onConfirm() {
                    this.f$0.lambda$accountRegister$2$AccountLoginPresenter(str, str2);
                }
            });
            return;
        }
        if (!str.equals(AccountCache.getAutoName(this.context)) && str.startsWith("37zd")) {
            ToastUtil.showToast(this.context, SqResUtils.getStringByName(this.context, "sy37_reg_not_auto_account_tips"));
            return;
        }
        if (this.mView != 0) {
            ((IAccountLoginView) this.mView).showLoading();
        }
        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.register, SqTrackBtn.SqTrackBtnExt.register);
        LoginTractionManager.trackInvoke("1", "1");
        AccountLogic.getInstance(this.context).accountRegister(str, str2, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.AccountLoginPresenter.2
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(Map<String, String> map) {
                LoginTractionManager.track("1", map);
                if (AccountLoginPresenter.this.mView != null) {
                    ((IAccountLoginView) AccountLoginPresenter.this.mView).hideLoading();
                    ((IAccountLoginView) AccountLoginPresenter.this.mView).accountRegSuccess(map);
                }
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str3) {
                LoginTractionManager.trackFail("1", "1", i + "", str3);
                if (AccountLoginPresenter.this.mView != null) {
                    ((IAccountLoginView) AccountLoginPresenter.this.mView).hideLoading();
                }
                ToastUtil.showToast(AccountLoginPresenter.this.context, str3);
                if (i == -1) {
                    AccountLoginPresenter.this.autoRegister(true);
                }
            }
        });
    }

    public /* synthetic */ void lambda$accountRegister$2$AccountLoginPresenter(String str, String str2) {
        this.clauseStatus = true;
        ((IAccountLoginView) this.mView).checkedClause();
        accountRegister(str, str2);
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IAccountLoginPresenter
    public void autoRegister(boolean z) {
        if (z) {
            authReq();
            return;
        }
        if (AccountCache.getUsername(this.context).equals(AccountCache.getAutoName(this.context)) || "".equals(AccountCache.getAutoName(this.context))) {
            authReq();
        } else if (this.mView != 0) {
            ((IAccountLoginView) this.mView).setAutoAccount(AccountCache.getAutoName(this.context), AccountCache.getAutoPassword(this.context));
        }
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IAccountLoginPresenter
    public void autoAccount() {
        String upwd;
        UserInfo userInfoFindUserByUname;
        UserInfo lastAccountUserInfo = AccountUtil.getLastAccountUserInfo(this.context);
        String alias = "";
        if (lastAccountUserInfo != null) {
            alias = !TextUtils.isEmpty(lastAccountUserInfo.getAlias()) ? lastAccountUserInfo.getAlias() : lastAccountUserInfo.getUname();
            upwd = lastAccountUserInfo.getUpwd();
            if (TextUtils.isEmpty(upwd) && !TextUtils.isEmpty(alias) && (userInfoFindUserByUname = AccountUtil.findUserByUname(this.context, alias)) != null) {
                upwd = userInfoFindUserByUname.getUpwd();
            }
        } else {
            upwd = "";
        }
        if (this.mView != 0) {
            ((IAccountLoginView) this.mView).setAutoAccount(alias, upwd);
        }
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IAccountLoginPresenter
    public void clauseClick(boolean z) {
        this.clauseStatus = z;
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IAccountLoginPresenter
    public void toClausePage() {
        UAgreeManager.getInstance().showUserProtocol(this.context);
    }

    @Override // com.sy37sdk.account.view.uifast.presenter.IAccountLoginPresenter
    public void toPolicy() {
        UAgreeManager.getInstance().showPolicy(this.context);
    }

    private void authReq() {
        AccountLogic.getInstance(this.context).autoAccount(new AccountLogic.AutoAccountListener() { // from class: com.sy37sdk.account.view.uifast.presenter.AccountLoginPresenter.3
            @Override // com.sy37sdk.account.AccountLogic.AutoAccountListener
            public void onSuccess(AutoAccountBean autoAccountBean) {
                LogUtil.i("msg: " + autoAccountBean.getMsg() + ", ssuccess:" + autoAccountBean.getSsuccess());
                String uname = autoAccountBean.getUname();
                String pwd = autoAccountBean.getPwd();
                AccountCache.setAutoName(AccountLoginPresenter.this.context, uname);
                AccountCache.setAutoPassword(AccountLoginPresenter.this.context, pwd);
                AccountCache.setAutoIssave(AccountLoginPresenter.this.context, autoAccountBean.getIssave());
                AccountCache.setAutoState(AccountLoginPresenter.this.context, autoAccountBean.getAutostate());
                AccountLoginPresenter.autoTitle = autoAccountBean.getTitle();
                AccountLoginPresenter.autoMsg = autoAccountBean.getMsg();
                AccountLoginPresenter.autoSuccess = autoAccountBean.getSsuccess();
                if (AccountLoginPresenter.this.mView != null) {
                    ((IAccountLoginView) AccountLoginPresenter.this.mView).setAutoAccount(uname, pwd);
                }
            }

            @Override // com.sy37sdk.account.AccountLogic.AutoAccountListener
            public void onFailure(int i, String str) {
                LogUtil.e("请求自动注册账号失败code=" + i + ",meg=" + str);
            }
        });
    }
}
