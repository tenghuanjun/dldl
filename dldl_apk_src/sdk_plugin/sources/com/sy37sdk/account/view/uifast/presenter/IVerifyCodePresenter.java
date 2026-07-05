package com.sy37sdk.account.view.uifast.presenter;

import com.sqwan.common.mvp.IPresenter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IVerifyCodePresenter extends IPresenter {
    void initTimer();

    void loginPwd(String str, String str2);

    void loginVerifyCode(String str, String str2);

    void sendCode(String str);

    void toAppeal();
}
