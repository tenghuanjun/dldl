package com.sy37sdk.account.view.uifast.view;

import android.os.Bundle;
import com.sqwan.common.mvp.ILoadView;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IVerifyCodeView extends ILoadView {
    void loginSuccess(Map<String, String> map);

    void startView(int i, Bundle bundle);

    void verifyCodeStatus(boolean z);

    void verifyCodeText(String str);
}
