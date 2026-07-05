package com.huya.component.login.api;

import android.app.Activity;
import android.content.Intent;
import com.huya.component.login.LoginInfo;
import com.huya.component.login.api.LoginInterface;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ILoginModule {
    void checkSmsUp(LoginInterface.CheckSmsUp checkSmsUp);

    void forceLogout();

    void logOut(LoginInterface.LogOut logOut);

    void login(LoginInterface.Login login);

    void onActivityResult(int i, int i2, Intent intent);

    void onLoginMobileQuick(LoginInterface.LoginMobileQuick loginMobileQuick);

    void onLoginPhoneSms(LoginInterface.LoginPhoneSms loginPhoneSms);

    void onRefreshSmsCode(LoginInterface.LoginSmsCode loginSmsCode);

    void onRefreshSmsCode(LoginInterface.RefreshSmsCode refreshSmsCode);

    void onSendLoginPhoneSms(LoginInterface.SendLoginPhoneSms sendLoginPhoneSms);

    void refreshPicCode(LoginInterface.RefreshPicCode refreshPicCode);

    void thirdLogin(Activity activity, LoginInfo.LoginType loginType);

    void thirdLogin(Activity activity, LoginInfo.LoginType loginType, OnThirdAuthCallback onThirdAuthCallback);

    void verifySmsCode(LoginInterface.VerifySmsCode verifySmsCode);
}
