package com.sy37sdk.account.view;

import com.sqwan.common.mvp.IView;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IAutoLoginDialog extends IView {

    public interface IChangeAccountListener {
        void changeAccount();
    }

    void autoLoginFail(int i, String str);

    void autoLoginSuccess(Map<String, String> map);

    void closeDialog();

    void setChangeAccountListener(IChangeAccountListener iChangeAccountListener);
}
