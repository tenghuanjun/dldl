package com.sy37sdk.account.view.uifast.presenter;

import com.sqwan.common.mvp.IPresenter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IWechatV2Presenter extends IPresenter {
    void clauseClick(boolean z);

    void obtainVerifyCode();

    void toClausePage();

    void toPolicy();

    void wechatLogin();

    void wechatRegister();
}
