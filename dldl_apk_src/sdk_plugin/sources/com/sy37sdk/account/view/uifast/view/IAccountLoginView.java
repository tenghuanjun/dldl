package com.sy37sdk.account.view.uifast.view;

import com.sqwan.common.mvp.ILoadView;
import java.util.Map;
import org.json.JSONArray;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IAccountLoginView extends ILoadView {
    void accountRegSuccess(Map<String, String> map);

    void checkedClause();

    void enableLoginBtn(boolean z);

    void loginSuccess(Map<String, String> map);

    void regEntrance(boolean z);

    void selectMultiAccount(JSONArray jSONArray, String str, String str2);

    void setAutoAccount(String str, String str2);

    void toggleUI(int i);
}
