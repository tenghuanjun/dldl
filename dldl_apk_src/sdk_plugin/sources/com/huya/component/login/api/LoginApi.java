package com.huya.component.login.api;

import android.app.Activity;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.Config;
import com.duowan.auk.util.L;
import com.huya.component.login.LoginProperties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoginApi {
    private static String KEY_LOGIN_STATUS = "login_status";
    public static final String STATE_TAG = "AppState";
    private static String mH5Info = "";
    private static ILoginCallback mLoginCallback = null;
    private static LoginSuccessCallback mLoginSuccessCallback = null;
    private static String mUDBAppId = "";
    private static String mUDBVerifyAppId = "";

    public interface ILoginCallback {
        int getHyUdbByPass();

        TokenInfo getTokenInfo();

        boolean isHyUdbLoging();
    }

    public static void init(ILoginCallback iLoginCallback) {
        mLoginCallback = iLoginCallback;
    }

    public static void setLoginSuccessCallback(LoginSuccessCallback loginSuccessCallback) {
        mLoginSuccessCallback = loginSuccessCallback;
    }

    public static boolean doLoginSuccessCallback(Activity activity) {
        LoginSuccessCallback loginSuccessCallback = mLoginSuccessCallback;
        if (loginSuccessCallback == null) {
            return false;
        }
        loginSuccessCallback.onLoginSuccess(activity);
        return true;
    }

    public static long getLastLoginUid() {
        return config().getLong(Constant.KEY_LAST_LOGIN_UID, 0L);
    }

    public static void setLastLoginUid(long j) {
        config().setLong(Constant.KEY_LAST_LOGIN_UID, j);
    }

    public static boolean getLoginTestMode() {
        return ArkValue.debuggable();
    }

    public static void setLoginTestMode(boolean z) {
        config().setBoolean(Constant.KEY_LOGIN_TEST_MODE, z);
    }

    public static boolean isLogined() {
        return LoginProperties.loginState.get() == LoginProperties.LoginState.LoggedIn;
    }

    public static boolean is3rdLogined() {
        return isLogined() && LoginProperties.account.get().type == 255;
    }

    public static void setLogined(LoginProperties.LoginState loginState, int i) {
        L.info(STATE_TAG, "登录状态改变：setLogined:%s,key %d", loginState, Integer.valueOf(i));
        LoginProperties.loginState.set(loginState);
    }

    public static boolean isUidEmpty() {
        if (LoginProperties.uid.get().longValue() != 0) {
            return false;
        }
        L.error(STATE_TAG, "uid is empty,isLoginde:%s", Boolean.valueOf(isLogined()));
        return true;
    }

    public static void setUid(long j) {
        L.info(STATE_TAG, "set uid :%d", Long.valueOf(j));
        LoginProperties.uid.set(Long.valueOf(j));
    }

    public static long getUid() {
        return LoginProperties.uid.get().longValue();
    }

    public static TokenInfo getDefaultToken() {
        ILoginCallback iLoginCallback = mLoginCallback;
        TokenInfo tokenInfo = iLoginCallback != null ? iLoginCallback.getTokenInfo() : null;
        return tokenInfo == null ? new TokenInfo() : tokenInfo;
    }

    public static long getYY() {
        return LoginProperties.yy.get().longValue();
    }

    public static String getPassport() {
        return LoginProperties.passport.get();
    }

    public static int getHyUdbByPass() {
        ILoginCallback iLoginCallback = mLoginCallback;
        if (iLoginCallback == null || !iLoginCallback.isHyUdbLoging()) {
            return 0;
        }
        return mLoginCallback.getHyUdbByPass();
    }

    public static Config config() {
        return Config.getInstance(ArkValue.gContext);
    }

    public static void setUdbAppId(String str) {
        mUDBAppId = str;
    }

    public static String getUdbAppId() {
        return mUDBAppId;
    }

    public static void setUdbVerifyAppId(String str) {
        mUDBVerifyAppId = str;
    }

    public static String getUdbVerifyAppId() {
        return mUDBVerifyAppId;
    }

    public static void setH5Info(String str) {
        mH5Info = str;
    }

    public static String getH5Info() {
        return mH5Info;
    }

    public static boolean isPassportLogined() {
        return isLogined() && LoginProperties.account.get().type == 0;
    }

    public static void saveAutoLogin(boolean z) {
        config().setBoolean(KEY_LOGIN_STATUS, z);
    }

    public static boolean getAutoLogin() {
        return config().getBoolean(KEY_LOGIN_STATUS, false);
    }
}
