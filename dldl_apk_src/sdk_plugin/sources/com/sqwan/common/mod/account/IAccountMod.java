package com.sqwan.common.mod.account;

import android.content.res.Configuration;
import com.sqwan.common.mod.IModBase;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IAccountMod extends IModBase {
    void backToGameLogin();

    void changeAccount(ILoginListener iLoginListener);

    IAuthResultListener getAuthResultListener();

    String getToken();

    String getUid();

    String getUname();

    boolean hasSubmitRole();

    void login(ILoginListener iLoginListener);

    void logout();

    void modifyPassword();

    void onConfigurationChanged(Configuration configuration);

    void onPause();

    void onResume();

    void redDotCalled(String str);

    void saveAccount(String str, String str2);

    void setAccountChangeListener(IAccountChangeListener iAccountChangeListener);

    void setAuthResultListener(IAuthResultListener iAuthResultListener);

    void setBackToGameLoginListener(IBackToGameLoginListener iBackToGameLoginListener);

    void setScreenshotListener(IScreenshotListener iScreenshotListener);

    void setSubmitRole(boolean z);

    void showAgeAppropriate();

    void showFloatMenu();

    void showLoginView(ILoginListener iLoginListener);

    void showUAgreement();

    void submitRoleInfo(Map<String, String> map);

    void webEnLogin(boolean z);

    void wxBind(IBindWxListener iBindWxListener);
}
