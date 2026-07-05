package com.duowan.live.login.api;

import android.app.Activity;
import android.content.Intent;
import com.huya.component.login.Account;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ILoginService {
    void autoLogin();

    boolean autoLogin(List<Account> list, Account account);

    Account getAccountByUid(List<Account> list, long j);

    Account getLastLoginAccount(Account account, List<Account> list);

    void login(Activity activity);

    void login(Activity activity, boolean z, int i);

    void login(String str, String str2);

    void logout();

    boolean needAutoLogin();

    boolean onActivityResult(int i, int i2, Intent intent);

    void onChangeNeedAutoLogin();

    void onLoginFail(int i, int i2, String str);

    boolean onLoginSuccess(Activity activity);

    void setShowPrivacyPolicy(boolean z);
}
