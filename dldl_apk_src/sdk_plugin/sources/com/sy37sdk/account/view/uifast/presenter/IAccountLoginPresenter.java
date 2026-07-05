package com.sy37sdk.account.view.uifast.presenter;

import com.sqwan.common.mvp.IPresenter;
import com.sy37sdk.account.UserInfo;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IAccountLoginPresenter extends IPresenter {
    void accountRegister(String str, String str2);

    void autoAccount();

    void autoRegister(boolean z);

    void clauseClick(boolean z);

    void deleteUser(UserInfo userInfo);

    void forgotPassword();

    void login(String str, String str2);

    void toClausePage();

    void toPolicy();
}
