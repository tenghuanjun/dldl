package com.sy37sdk.account.controller;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.sq.tool.logger.Printer;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.mod.account.ILoginListener;
import com.sqwan.common.track.SqTrackAction;
import com.sqwan.common.track.SqTrackActionManager;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.QrCodeInfo;
import com.sy37sdk.account.UserInfo;
import com.sy37sdk.account.binding.GameBindingManager;
import com.sy37sdk.account.presenter.AutoLoginPresenter;
import com.sy37sdk.account.view.IAutoLoginDialog;
import com.sy37sdk.account.view.IRegSuccessDialog;
import com.sy37sdk.account.view.ui.AutoLoginDialog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class AbstractLoginController {
    protected static final Printer LOG = SQLog.m("【Login Ctrl】");
    protected Context mContext;

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public abstract void showLoginDialog(ILoginListener iLoginListener);

    public abstract void showRegSuccessDialog(String str, String str2, QrCodeInfo qrCodeInfo, IRegSuccessDialog.EnterGameListener enterGameListener);

    public AbstractLoginController(Context context) {
        this.mContext = context;
    }

    public void login(ILoginListener iLoginListener) {
        UserInfo userInfo = AccountCache.getUserInfo(this.mContext);
        LOG.i("缓存的user: " + userInfo);
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getUname())) {
            LOG.d("有效的缓存账号, 触发自动登录");
            String alias = userInfo.getAlias();
            String uname = userInfo.getUname();
            String mobile = userInfo.getMobile();
            if (TextUtils.isEmpty(alias)) {
                alias = uname;
            }
            if (TextUtils.isEmpty(mobile)) {
                mobile = alias;
            }
            showAutoLoginDialog(mobile, iLoginListener);
            return;
        }
        showLoginDialog(iLoginListener);
    }

    protected void showAutoLoginDialog(String str, final ILoginListener iLoginListener) {
        String string;
        LOG.d("展示自动登录弹窗: " + str);
        SqTrackActionManager.getInstance().trackAction(SqTrackAction.SHOW_AUTO_LOGIN_VIEW);
        AutoLoginDialog autoLoginDialog = new AutoLoginDialog(this.mContext, str, iLoginListener);
        AutoLoginPresenter autoLoginPresenter = new AutoLoginPresenter(this.mContext, autoLoginDialog);
        String accountAlias = AccountCache.getAccountAlias(this.mContext);
        String username = AccountCache.getUsername(this.mContext);
        String str2 = TextUtils.isEmpty(accountAlias) ? username : accountAlias;
        String password = AccountCache.getPassword(this.mContext);
        UserInfo userInfo = AccountCache.getUserInfo(this.mContext);
        if (accountAlias != null && !accountAlias.equals(username)) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
            if (userInfo != null) {
                string = userInfo.toString();
            } else {
                string = "alias=" + accountAlias + ", uname=" + username;
            }
            BuglessAction.reportCatchException(illegalArgumentException, "alias和uname不一致", string, 999);
        }
        boolean z = userInfo != null && userInfo.isPhoneLoginType();
        String mobile = (userInfo == null || !userInfo.isPhoneLoginType()) ? "" : userInfo.getMobile();
        if (z) {
            str2 = mobile;
        }
        autoLoginPresenter.setLoginInfo(str2, password, z);
        autoLoginDialog.setPresenter(autoLoginPresenter);
        autoLoginDialog.setChangeAccountListener(new IAutoLoginDialog.IChangeAccountListener() { // from class: com.sy37sdk.account.controller.AbstractLoginController.1
            @Override // com.sy37sdk.account.view.IAutoLoginDialog.IChangeAccountListener
            public void changeAccount() {
                GameBindingManager.getInstance().setIsLogin(false);
                AbstractLoginController.LOG.d("点击自动登录中的切换账号");
                AbstractLoginController.this.showLoginDialog(iLoginListener);
            }
        });
        autoLoginDialog.show();
    }
}
