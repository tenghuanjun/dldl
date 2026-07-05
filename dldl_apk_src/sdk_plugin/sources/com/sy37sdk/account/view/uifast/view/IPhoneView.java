package com.sy37sdk.account.view.uifast.view;

import android.os.Bundle;
import com.sqwan.common.mvp.ILoadView;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IPhoneView extends ILoadView {
    void accountLoginEntrance(boolean z);

    void accountRegSuccess(Map<String, String> map);

    void checkedClause();

    String getPhone();

    void regEntrance(boolean z);

    void setResendCodeStatus(boolean z);

    void setResendCodeText(String str);

    void startVerifyCodeView();

    void startView(int i, Bundle bundle);
}
