package com.sy37sdk.account.view.uifast.presenter;

import com.sqwan.common.mvp.IPresenter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IPhonePresenter extends IPresenter {
    void clauseClick(boolean z);

    void forgetPassword();

    void initVerifyCodeTimer();

    void loginVerifyCode(String str, String str2);

    void obtainVerifyCode();

    void quickStart();

    void toClausePage();

    void toPolicy();

    void wechatLogin();
}
