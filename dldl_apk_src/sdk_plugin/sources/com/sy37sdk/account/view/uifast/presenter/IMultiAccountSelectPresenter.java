package com.sy37sdk.account.view.uifast.presenter;

import com.sqwan.common.mvp.IPresenter;
import com.sy37sdk.account.AccountLogic;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IMultiAccountSelectPresenter extends IPresenter {
    void login(String str, String str2, String str3, AccountLogic.AccountListener accountListener);
}
