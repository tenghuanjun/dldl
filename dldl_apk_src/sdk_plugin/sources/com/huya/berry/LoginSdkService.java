package com.huya.berry;

import android.app.Activity;
import android.content.Intent;
import com.duowan.auk.ArkUtils;
import com.duowan.live.login.api.ILoginService;
import com.huya.component.login.Account;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.LoginInterface;
import com.huya.live.service.AbsService;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LoginSdkService extends AbsService implements ILoginService {
    @Override // com.duowan.live.login.api.ILoginService
    public boolean autoLogin(List<Account> list, Account account) {
        return false;
    }

    @Override // com.duowan.live.login.api.ILoginService
    public Account getAccountByUid(List<Account> list, long j) {
        return null;
    }

    @Override // com.duowan.live.login.api.ILoginService
    public Account getLastLoginAccount(Account account, List<Account> list) {
        return null;
    }

    @Override // com.duowan.live.login.api.ILoginService
    public void login(Activity activity, boolean z, int i) {
    }

    @Override // com.duowan.live.login.api.ILoginService
    public void login(String str, String str2) {
    }

    @Override // com.duowan.live.login.api.ILoginService
    public boolean onActivityResult(int i, int i2, Intent intent) {
        return false;
    }

    @Override // com.duowan.live.login.api.ILoginService
    public void onChangeNeedAutoLogin() {
    }

    @Override // com.duowan.live.login.api.ILoginService
    public void onLoginFail(int i, int i2, String str) {
    }

    @Override // com.duowan.live.login.api.ILoginService
    public boolean onLoginSuccess(Activity activity) {
        return false;
    }

    @Override // com.duowan.live.login.api.ILoginService
    public void setShowPrivacyPolicy(boolean z) {
    }

    @Override // com.duowan.live.login.api.ILoginService
    public void login(Activity activity) {
        if (LoginProperties.loginState.get() == LoginProperties.LoginState.NoLogin) {
            LoginFragment.getInstance(activity.getFragmentManager()).show(activity.getFragmentManager());
        }
    }

    @Override // com.duowan.live.login.api.ILoginService
    public void autoLogin() {
        long lastLoginUid = LoginApi.getLastLoginUid();
        if (lastLoginUid == 0) {
            return;
        }
        ArkUtils.send(new LoginInterface.Login(lastLoginUid));
    }

    @Override // com.duowan.live.login.api.ILoginService
    public void logout() {
        ArkUtils.send(new LoginInterface.LogOut());
        LoginApi.setLastLoginUid(0L);
    }

    @Override // com.duowan.live.login.api.ILoginService
    public boolean needAutoLogin() {
        return LoginApi.getLastLoginUid() != 0;
    }
}
