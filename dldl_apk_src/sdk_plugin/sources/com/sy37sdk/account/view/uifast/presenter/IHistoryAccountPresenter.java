package com.sy37sdk.account.view.uifast.presenter;

import com.sqwan.common.mvp.IPresenter;
import com.sy37sdk.account.UserInfo;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IHistoryAccountPresenter extends IPresenter {
    void clauseClick(boolean z);

    void deleteUser(UserInfo userInfo);

    void forgetPassword();

    void login(UserInfo userInfo);

    void toClausePage();

    void toPolicy();
}
