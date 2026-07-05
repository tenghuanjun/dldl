package com.sy37sdk.account.view.uifast;

import android.os.Bundle;
import com.sqwan.common.mvp.ILoadView;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface ILoginDialog extends ILoadView {
    void accountRegSuccess(Map<String, String> map);

    boolean canGoBack();

    void closeAccountDialog();

    void dismissAccountDialog();

    void goBack();

    boolean isFromAliFastLogin();

    void loginSuccess(Map<String, String> map);

    void onSwitch(int i, Bundle bundle);

    void printStackViews();
}
