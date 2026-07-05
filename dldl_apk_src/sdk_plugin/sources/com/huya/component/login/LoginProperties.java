package com.huya.component.login;

import com.duowan.auk.asignal.Property;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoginProperties {
    public static Property<Account> account = new Property<>(new Account());
    public static Property<Long> loginUid = new Property<>(0L);
    public static final String MarkLoginInfo = "yyLoginInfo";
    public static final Property<LoginInfo> loginInfo = new Property<>(null, MarkLoginInfo);
    public static final String MarkLoginState = "yyLoginState";
    public static final Property<LoginState> loginState = new Property<>(LoginState.NoLogin, MarkLoginState);
    public static final String MarkUid = "yyUid";
    public static final Property<Long> uid = new Property<>(0L, MarkUid);
    public static final String MarkLastLoginUid = "LastLoginUid";
    public static final Property<Long> lastLoginUid = new Property<>(0L, MarkLastLoginUid);
    public static final String MarkPassport = "yyPassport";
    public static final Property<String> passport = new Property<>("", MarkPassport);
    public static final String MarkLoginInfoOffNetwork = "yyLastLoginOffNetwork";
    public static final Property<LoginInfo> lastLoginOffNetwork = new Property<LoginInfo>(null, MarkLoginInfoOffNetwork) { // from class: com.huya.component.login.LoginProperties.1
    };
    public static final String MarkUidPhoneBind = "UidPhoneBind";
    public static final Property<Boolean> uidPhoneBind = new Property<>(false, MarkUidPhoneBind);
    public static final String MarkLoginType = "loginType";
    public static final Property<Integer> loginType = new Property<>(-1, MarkLoginType);
    public static final String MarkYY = "yyYY";
    public static final Property<Long> yy = new Property<>(0L, MarkYY);
    public static Property<String> huyaAccountUpgradeTips = new Property<>(null);
    public static Property<Boolean> cancelParams = new Property<>(false);
    public static final Property<Boolean> udbsdkLogEnabled = new Property<>(true);

    public enum LoginState {
        NoLogin,
        Logining,
        LoggedIn
    }
}
