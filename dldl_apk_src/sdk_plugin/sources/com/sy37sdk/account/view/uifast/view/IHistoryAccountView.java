package com.sy37sdk.account.view.uifast.view;

import android.os.Bundle;
import com.sqwan.common.mvp.ILoadView;
import com.sy37sdk.account.UserInfo;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IHistoryAccountView extends ILoadView {
    void checkedClause();

    void enableLoginBtn(boolean z);

    String getPhone();

    void loginSuccess(Map<String, String> map);

    void onSwitch(int i);

    void setAccount(UserInfo userInfo);

    void startView(int i, Bundle bundle);
}
