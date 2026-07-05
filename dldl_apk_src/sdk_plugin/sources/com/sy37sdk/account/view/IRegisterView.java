package com.sy37sdk.account.view;

import com.sqwan.common.mvp.ILoadView;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IRegisterView extends ILoadView {
    void accountRegSuccess(Map<String, String> map);

    void changeUAgreeCbStatus(boolean z);

    int currentRegType();

    void phoneRegSuccess(Map<String, String> map);

    void setAutoAccount(String str, String str2);

    void showAccountRegister();

    void showPhoneRegister();

    void verifyCodeBtnStatus(boolean z);

    void verifyCodeBtnText(String str);
}
