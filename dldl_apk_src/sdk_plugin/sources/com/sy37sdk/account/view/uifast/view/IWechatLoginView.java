package com.sy37sdk.account.view.uifast.view;

import com.sqwan.common.mvp.ILoadView;
import com.sy37sdk.account.view.ui.WechatRegSuccessDialog;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IWechatLoginView extends ILoadView {
    void checkedClause();

    void enableLoginBtn(boolean z);

    void loginSuccess(Map<String, String> map);

    void showRegDialog(Map<String, String> map, WechatRegSuccessDialog.IWechatRegListener iWechatRegListener);
}
