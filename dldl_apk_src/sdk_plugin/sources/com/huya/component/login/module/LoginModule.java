package com.huya.component.login.module;

import android.app.Activity;
import android.content.Intent;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.Report;
import com.duowan.live.sql.SqlHelper;
import com.duowan.networkmars.wup.WupHelper;
import com.huya.component.login.Account;
import com.huya.component.login.LoginAccount;
import com.huya.component.login.LoginInfo;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.ILoginModule;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.LoginCallback;
import com.huya.component.login.api.LoginEvent;
import com.huya.component.login.api.LoginInterface;
import com.huya.component.login.api.OnThirdAuthCallback;
import com.huya.component.login.api.TokenInfo;
import com.huya.component.login.report.LoginReport;
import com.huya.live.service.AbsService;
import com.huya.mtp.utils.FP;
import com.huya.mtp.utils.NetworkUtils;
import com.huyaudbunify.bean.ThirdLoginOption;
import com.huyaudbunify.core.AuthEvent;
import com.huyaudbunify.core.LoginEvent;
import com.huyaudbunify.handler.HYHandler;
import com.huyaudbunify.handler.ProxyEventHandlerEx;
import com.huyaudbunify.inter.IHuyaAuthLogCallback;
import com.huyaudbunify.inter.ITrustInfoCallBack;
import com.hysdkproxy.LoginProxy;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoginModule extends AbsService implements ILoginModule {
    private static final String TAG = "LoginModule";
    private static final HandlerThread mLoginThread;
    private LoginReport mLoginReport;
    private boolean mAuthSuccess = false;
    private boolean mLoginSuccess = false;
    private int mLoginType = 0;
    private volatile ThirdLogin mThirdLogin = null;
    HYHandler myYYHandler = new HYHandler(mLoginThread.getLooper()) { // from class: com.huya.component.login.module.LoginModule.3
        @HYHandler.MessageHandler(message = LoginEvent.LoginMessage.onLoginNGRes)
        public void onAuthRes(LoginEvent.LoginResNGEvent loginResNGEvent) {
            LoginModule.this.onLoginResult(loginResNGEvent);
        }

        @HYHandler.MessageHandler(message = LoginEvent.LoginMessage.onKickoff)
        public void onKickOff(LoginEvent.ETLoginKickoff eTLoginKickoff) {
            LoginModule.this.onETLoginKickOffNtf(eTLoginKickoff);
        }
    };

    static {
        HandlerThread handlerThread = new HandlerThread("mLoginThread");
        mLoginThread = handlerThread;
        handlerThread.start();
    }

    @Override // com.huya.live.service.AbsService
    public void onCreate() {
        ArkUtils.register(this);
        boolean loginTestMode = LoginApi.getLoginTestMode();
        L.info(TAG, "setDeveloper " + loginTestMode);
        LoginProxy.getInstance().setDeveloper(loginTestMode);
        LoginProxy.getInstance().setRegTrustCallBack(new ITrustInfoCallBack() { // from class: com.huya.component.login.module.LoginModule.1
            @Override // com.huyaudbunify.inter.ITrustInfoCallBack
            public void verifyResultCallback(int i, String str) {
            }

            @Override // com.huyaudbunify.inter.ITrustInfoCallBack
            public void infoRet(long j, Boolean bool) {
                L.info(LoginModule.TAG, "login RegTrustCallBack %d, %b", Long.valueOf(j), bool);
            }
        });
        LoginProxy.getInstance().setLogCallBack(new IHuyaAuthLogCallback() { // from class: com.huya.component.login.module.LoginModule.2
            @Override // com.huyaudbunify.inter.IHuyaAuthLogCallback
            public void log(String str) {
                if (str == null || str.equals("msg id is b000003") || str.equals("UdbMsgHandler  receive msg") || str.equals("HandlerGetTicket  receive msg")) {
                    return;
                }
                L.info("udbauth", str);
            }
        });
        LoginProxy.getInstance().setForbidLog(!loginTestMode);
        LoginProxy.getInstance().init(ArkValue.gContext, WupHelper.getClientType());
        LoginProxy.getInstance().addHandler(this.myYYHandler);
        this.mThirdLogin = new ThirdLogin();
        LoginReport loginReport = new LoginReport();
        this.mLoginReport = loginReport;
        loginReport.onstart();
    }

    @Override // com.huya.live.service.AbsService
    public void onStop() {
        ArkUtils.unregister(this);
        this.mThirdLogin = null;
        LoginProxy.getInstance().removeHandler(this.myYYHandler);
        LoginReport loginReport = this.mLoginReport;
        if (loginReport != null) {
            loginReport.onStop();
        }
        super.onStop();
    }

    private long getUid() {
        return LoginProperties.loginUid.get().longValue();
    }

    @Override // com.huya.component.login.api.ILoginModule
    @IASlot
    public void login(LoginInterface.Login login) {
        login(login.loginInfo);
    }

    @Override // com.huya.component.login.api.ILoginModule
    public void forceLogout() {
        L.info(TAG, "forceLogout");
        if (LoginProperties.uid.get().longValue() > 0) {
            LoginProxy.getInstance().loginOut();
            reset();
        }
    }

    /* JADX INFO: renamed from: com.huya.component.login.module.LoginModule$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$huya$component$login$LoginProperties$LoginState;

        static {
            int[] iArr = new int[LoginProperties.LoginState.values().length];
            $SwitchMap$com$huya$component$login$LoginProperties$LoginState = iArr;
            try {
                iArr[LoginProperties.LoginState.NoLogin.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huya$component$login$LoginProperties$LoginState[LoginProperties.LoginState.Logining.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$huya$component$login$LoginProperties$LoginState[LoginProperties.LoginState.LoggedIn.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.huya.component.login.api.ILoginModule
    @IASlot
    public void logOut(LoginInterface.LogOut logOut) {
        LoginProxy.getInstance().loginOut();
        int i = AnonymousClass5.$SwitchMap$com$huya$component$login$LoginProperties$LoginState[LoginProperties.loginState.get().ordinal()];
        if (i == 2) {
            loginFail(LoginEvent.LoginFail.Reason.Cancel, "");
        } else {
            if (i != 3) {
                return;
            }
            logOut(LoginEvent.LogOutFinished.Reason.Normal);
        }
    }

    @Override // com.huya.component.login.api.ILoginModule
    @IASlot
    public void refreshPicCode(LoginInterface.RefreshPicCode refreshPicCode) {
        L.info(TAG, "refreshPicCode...uid %d ,user %s", Long.valueOf(getUid()), refreshPicCode.user);
        LoginProxy.getInstance().refreshPic(getUid());
    }

    @Override // com.huya.component.login.api.ILoginModule
    @IASlot
    public void checkSmsUp(LoginInterface.CheckSmsUp checkSmsUp) {
        L.info(TAG, "checkSmsUp...");
    }

    @IASlot
    public void checkModPwd(LoginInterface.CheckModPwd checkModPwd) {
        L.info(TAG, "checkModPwd...");
        LoginProxy.getInstance().checkPwdFP(checkModPwd.user);
    }

    @Override // com.huya.component.login.api.ILoginModule
    @IASlot
    public void verifySmsCode(LoginInterface.VerifySmsCode verifySmsCode) {
        L.info(TAG, "verifySmsCode...");
        LoginProxy.getInstance().findPwd_verifySms(verifySmsCode.user, verifySmsCode.mStrMobile, verifySmsCode.code);
    }

    @IASlot
    public void QuickModPwd(LoginInterface.QuickModPwd quickModPwd) {
        LoginProxy.getInstance().findPwd(quickModPwd.user, quickModPwd.password);
    }

    @IASlot
    public void CheckUserRegister(LoginInterface.CheckUserRegister checkUserRegister) {
        L.info(TAG, "CheckUserRegister...");
        LoginProxy.getInstance().checkUserRegister(checkUserRegister.mobile);
    }

    @IASlot
    public void Register(LoginInterface.Register register) {
        LoginProxy.getInstance().register_sms(register.mobile, register.smsCode, register.pwd);
    }

    @Override // com.huya.component.login.api.ILoginModule
    @IASlot
    public void onRefreshSmsCode(LoginInterface.RefreshSmsCode refreshSmsCode) {
        if (refreshSmsCode.isRegister) {
            L.info(TAG, "onRefreshSmsCode .isRegister...", refreshSmsCode.mobile);
            LoginProxy.getInstance().register_sendsms(refreshSmsCode.mobile);
        } else {
            L.info(TAG, "refreshPicCode.NOisRegister..user %s , mobile %s", refreshSmsCode.user, refreshSmsCode.mobile);
            LoginProxy.getInstance().findPwd_sendSms(refreshSmsCode.mobile);
        }
    }

    @Override // com.huya.component.login.api.ILoginModule
    @IASlot
    public void onRefreshSmsCode(LoginInterface.LoginSmsCode loginSmsCode) {
        L.info(TAG, "onRefreshSmsCode..user %s , mobile %s", Long.valueOf(getUid()), loginSmsCode.user);
        LoginProxy.getInstance().loginSecondAuth_sendsms(getUid(), "");
    }

    @IASlot(executorID = 1)
    public void thirdPartyLogin(LoginInterface.ThirdPartyLogin thirdPartyLogin) {
        ThirdLoginOption thirdLoginOption = new ThirdLoginOption();
        thirdLoginOption.setPartnerUid(thirdPartyLogin.openId);
        if (!TextUtils.isEmpty(thirdPartyLogin.thirdPartyAppId)) {
            thirdLoginOption.setThirdAppkey(thirdPartyLogin.thirdPartyAppId);
        }
        thirdLoginOption.setOauthType(thirdPartyLogin.oauthType);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.thirdType = thirdPartyLogin.thirdType;
        loginInfo.type = 255;
        LoginProperties.loginInfo.set(loginInfo);
        LoginProperties.loginState.set(LoginProperties.LoginState.Logining);
        LoginProxy.getInstance().thirdLogin(thirdPartyLogin.thirdType, thirdPartyLogin.tokenId, thirdLoginOption, "", false);
    }

    @Override // com.huya.component.login.api.ILoginModule
    @IASlot
    public void onSendLoginPhoneSms(LoginInterface.SendLoginPhoneSms sendLoginPhoneSms) {
        LoginProxy.getInstance().sendLoginPhoneSms(sendLoginPhoneSms.strMobile);
    }

    @Override // com.huya.component.login.api.ILoginModule
    @IASlot
    public void onLoginPhoneSms(LoginInterface.LoginPhoneSms loginPhoneSms) {
        reset();
        this.mLoginType = 3;
        LoginProxy.getInstance().loginPhoneSms(loginPhoneSms.strMobile, loginPhoneSms.strSms, false);
    }

    @Override // com.huya.component.login.api.ILoginModule
    @IASlot
    public void onLoginMobileQuick(LoginInterface.LoginMobileQuick loginMobileQuick) {
        reset();
        this.mLoginType = 4;
        LoginProxy.getInstance().loginMobileQuick(loginMobileQuick.type, loginMobileQuick.token, false);
    }

    private void authLoginSuccess() {
        boolean z;
        L.error(TAG, "authLoginSuccess ->mLoginSuccess %s   mAuthSuccess %s", Boolean.valueOf(this.mLoginSuccess), Boolean.valueOf(this.mAuthSuccess));
        if (this.mAuthSuccess) {
            L.info(TAG, "loginSuccess 已经登录...");
            return;
        }
        this.mAuthSuccess = true;
        if (LoginProperties.loginState.get() == LoginProperties.LoginState.LoggedIn) {
            L.info(TAG, "loginSuccess ->duplicated LoginResEvent!");
        }
        LoginInfo loginInfo = LoginProperties.loginInfo.get();
        if (loginInfo == null) {
            L.error(TAG, "loginSuccess ->no loginInfo == null");
            if (LoginProperties.lastLoginOffNetwork.isDefault()) {
                L.error(TAG, "loginSuccess ->no loginInfo");
                return;
            } else {
                loginInfo = LoginProperties.lastLoginOffNetwork.get();
                LoginProperties.loginInfo.set(loginInfo);
            }
        }
        L.info(TAG, "loginSuccess->==:" + loginInfo);
        LoginProperties.lastLoginOffNetwork.reset();
        Account account = new Account();
        LoginAccount loginAccount = new LoginAccount();
        long jLongValue = LoginProperties.loginUid.get().longValue();
        if (loginInfo.type == 0) {
            loginAccount.account = loginInfo.account;
            loginAccount.password = loginInfo.password;
            loginAccount.token = loginInfo.token;
            LoginApi.saveAutoLogin(true);
        } else if (loginInfo.type == 255) {
            LoginProperties.loginType.set(Integer.valueOf(loginInfo.thirdType));
            loginAccount.account = loginInfo.account;
            loginAccount.password = loginInfo.password;
            loginAccount.token = loginInfo.token;
            LoginApi.saveAutoLogin(true);
        } else if (loginInfo.type == 2) {
            loginAccount.account = loginInfo.account;
            loginAccount.password = loginInfo.password;
            loginAccount.token = "123";
            LoginApi.saveAutoLogin(true);
        } else if (loginInfo.type == 3 || loginInfo.type == 4) {
            loginAccount.account = "";
            loginAccount.password = "";
            loginAccount.token = "";
            LoginApi.saveAutoLogin(true);
        } else {
            loginAccount.account = String.valueOf(jLongValue);
            loginAccount.password = "123456";
            loginAccount.token = "123";
            LoginApi.saveAutoLogin(true);
        }
        loginAccount.uid = jLongValue;
        loginAccount.type = loginInfo.type;
        loginAccount.lastLoginTime = System.currentTimeMillis();
        LoginInfo loginInfo2 = loginInfo;
        account.setAccountInfo(loginAccount.account, loginAccount.password, loginAccount.token, loginAccount.type, loginAccount.lastLoginTime, loginAccount.uid);
        LoginProperties.account.set(account);
        L.info(TAG, "loginSuccess uid...%d", Long.valueOf(jLongValue));
        long jLongValue2 = LoginProperties.uid.get().longValue();
        LoginProperties.uid.set(Long.valueOf(jLongValue));
        LoginProperties.lastLoginUid.set(Long.valueOf(jLongValue));
        Report.loginSuccess(jLongValue);
        LoginApi.setH5Info(LoginProxy.getInstance().getH5Info());
        LoginProperties.loginState.set(LoginProperties.LoginState.LoggedIn);
        ArkUtils.call(new LoginEvent.LoginSuccess(LoginProperties.loginInfo.get(), jLongValue));
        ArkUtils.call(new LoginCallback.UidChanged(jLongValue2, jLongValue));
        HySignalHelper.onLoginSuccess(jLongValue);
        if (loginInfo2.type != 2) {
            z = true;
            if (loginInfo2.type != 1 && loginInfo2.type != 3 && loginInfo2.type != 4) {
                SqlHelper.asyncCreateOrUpdate(ArkValue.gContext, loginAccount);
            }
        } else {
            z = true;
        }
        LoginApi.setLastLoginUid(jLongValue);
        LoginCallback.LoginFinished loginFinished = new LoginCallback.LoginFinished();
        loginFinished.success = z;
        ArkUtils.send(loginFinished);
        if (TextUtils.isEmpty(loginAccount.password)) {
            updateAccountInfo();
        }
    }

    private void loginFail(LoginEvent.LoginFail.Reason reason, String str) {
        loginFail(reason, 0, str);
    }

    private void loginFail(LoginEvent.LoginFail.Reason reason, int i, String str) {
        if (reason != LoginEvent.LoginFail.Reason.NoNetwork && reason != LoginEvent.LoginFail.Reason.TimeOut) {
            LoginProperties.lastLoginOffNetwork.reset();
        }
        ArkUtils.send(new LoginEvent.LoginFail(reason, LoginProperties.loginInfo.get(), i, str));
        LoginCallback.LoginFinished loginFinished = new LoginCallback.LoginFinished();
        loginFinished.success = false;
        loginFinished.desc = str;
        ArkUtils.send(loginFinished);
        LoginProperties.loginInfo.reset();
        LoginProperties.loginState.reset();
    }

    private void loginPassport(LoginInfo loginInfo) {
        if (!NetworkUtils.isNetworkAvailable()) {
            loginFail(LoginEvent.LoginFail.Reason.NoNetwork, "");
            return;
        }
        String str = loginInfo.account;
        if (FP.empty(str)) {
            loginFail(LoginEvent.LoginFail.Reason.NullAccount, "");
            return;
        }
        String str2 = loginInfo.password;
        if (FP.empty(str2)) {
            loginFail(LoginEvent.LoginFail.Reason.NullPassword, "");
            return;
        }
        LoginProperties.loginInfo.set(loginInfo);
        LoginProperties.loginState.set(LoginProperties.LoginState.Logining);
        this.mLoginSuccess = false;
        this.mAuthSuccess = false;
        L.info(TAG, "login %s", str);
        String str3 = loginInfo.token;
        if (TextUtils.isEmpty(str3)) {
            LoginProxy.getInstance().loginPassport(str, str2, false);
            return;
        }
        int i = loginInfo.strategy_type;
        if (i == 1) {
            LoginProxy.getInstance().loginSecondAuth_pic(LoginProperties.loginUid.get().longValue(), str3);
        } else if (i == 2) {
            LoginProxy.getInstance().mobileTokenLogin(LoginProperties.loginUid.get().longValue(), str3);
        } else {
            if (i != 8) {
                return;
            }
            LoginProxy.getInstance().loginSecondAuth_sms(LoginProperties.loginUid.get().longValue(), str3);
        }
    }

    private void loginCredit(LoginInfo loginInfo) {
        if (!NetworkUtils.isNetworkAvailable()) {
            loginFail(LoginEvent.LoginFail.Reason.NoNetwork, "");
            return;
        }
        LoginProperties.loginInfo.set(loginInfo);
        LoginProperties.loginState.set(LoginProperties.LoginState.Logining);
        this.mLoginSuccess = false;
        this.mAuthSuccess = false;
        L.info(TAG, "loginCredit, uid=%d", Long.valueOf(loginInfo.uid));
        LoginProxy.getInstance().credLogin(loginInfo.uid, false);
    }

    private void login(LoginInfo loginInfo) {
        if (loginInfo.type == 1) {
            loginCredit(loginInfo);
        }
        if (loginInfo.type == 2) {
            loginCredit(loginInfo);
        } else {
            loginPassport(loginInfo);
        }
    }

    private void logOut(LoginEvent.LogOutFinished.Reason reason) {
        logOut(reason, null);
    }

    private void logOut(LoginEvent.LogOutFinished.Reason reason, byte[] bArr) {
        L.info(TAG, "logOut: reason = %d, ext = %s", Integer.valueOf(reason.ordinal()), reason.toString());
        if (reason != LoginEvent.LogOutFinished.Reason.NoNetwork && reason != LoginEvent.LogOutFinished.Reason.KickOff) {
            LoginProperties.lastLoginOffNetwork.reset();
        }
        HySignalHelper.onLogout();
        ArkUtils.send(new LoginCallback.EndAnchorLink(LoginProperties.uid.get().longValue()));
        reset();
        ArkUtils.send(new LoginEvent.LogOutFinished(reason, bArr == null ? "" : new String(bArr)));
    }

    private void reset() {
        LoginProperties.loginInfo.reset();
        LoginProperties.loginState.reset();
        long jLongValue = LoginProperties.uid.get().longValue();
        LoginProperties.uid.reset();
        ArkUtils.call(new LoginCallback.UidChanged(jLongValue, LoginProperties.uid.get().longValue()));
        LoginProperties.yy.reset();
        LoginProperties.uidPhoneBind.reset();
        LoginProperties.huyaAccountUpgradeTips.set(null);
        this.mLoginSuccess = false;
        this.mAuthSuccess = false;
        this.mLoginType = 0;
        ArkUtils.send(new LoginEvent.LogoutRest());
        LoginProperties.loginType.set(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onETLoginKickOffNtf(com.huyaudbunify.core.LoginEvent.ETLoginKickoff r4) {
        /*
            r3 = this;
            int r0 = r4.uReason
            if (r0 == 0) goto L15
            switch(r0) {
                case 10220052: goto L15;
                case 10220053: goto Ld;
                default: goto L7;
            }
        L7:
            com.huya.component.login.api.LoginEvent$LogOutFinished$Reason r0 = com.huya.component.login.api.LoginEvent.LogOutFinished.Reason.KickOff
            r3.logOut(r0)
            goto L1c
        Ld:
            com.huya.component.login.api.LoginEvent$LogOutFinished$Reason r0 = com.huya.component.login.api.LoginEvent.LogOutFinished.Reason.PasswdChanged
            byte[] r1 = r4.strReason
            r3.logOut(r0, r1)
            goto L1c
        L15:
            com.huya.component.login.api.LoginEvent$LogOutFinished$Reason r0 = com.huya.component.login.api.LoginEvent.LogOutFinished.Reason.Ban
            byte[] r1 = r4.strReason
            r3.logOut(r0, r1)
        L1c:
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 0
            int r2 = r4.uReason
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0[r1] = r2
            r1 = 1
            byte[] r4 = r4.strReason
            r0[r1] = r4
            java.lang.String r4 = "onETLoginKickOffNtf %d %s"
            com.duowan.auk.util.L.info(r3, r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.component.login.module.LoginModule.onETLoginKickOffNtf(com.huyaudbunify.core.LoginEvent$ETLoginKickoff):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLoginResult(LoginEvent.LoginResNGEvent loginResNGEvent) {
        if (loginResNGEvent.uSrvResCode != 200 && loginResNGEvent.uSrvResCode != 4) {
            L.error(TAG, "AP failed, " + loginResNGEvent.uSrvResCode);
            return;
        }
        if (loginResNGEvent.uSrvResCode == 200) {
            L.info(TAG, "ap loginSuccess.; mLoginSuccess %s, %s", Boolean.valueOf(this.mLoginSuccess), loginResNGEvent.toString());
        } else {
            OnEvent(((ProxyEventHandlerEx.ProxyLoginResNGEvent) loginResNGEvent).event);
        }
    }

    private void OnEvent(AuthEvent.AuthBaseEvent authBaseEvent) {
        if (authBaseEvent.getUid() != null && !(authBaseEvent instanceof AuthEvent.AnonymousEvent)) {
            LoginProperties.loginUid.set(Long.valueOf(authBaseEvent.getUid()));
            L.info(TAG, "loginSuccess,OnEvent: %s, %s ", authBaseEvent.getClass().getName(), authBaseEvent.getUid());
        }
        if (authBaseEvent instanceof AuthEvent.CheckModPwdEvent) {
            AuthEvent.CheckModPwdEvent checkModPwdEvent = (AuthEvent.CheckModPwdEvent) authBaseEvent;
            L.info(TAG, "LoginEvent,  OnEvent CheckModPwdEvent：" + checkModPwdEvent);
            ArkUtils.send(new LoginCallback.CheckModPwdResult(checkModPwdEvent));
            return;
        }
        if (authBaseEvent instanceof AuthEvent.VerifySmsCodeEvent) {
            AuthEvent.VerifySmsCodeEvent verifySmsCodeEvent = (AuthEvent.VerifySmsCodeEvent) authBaseEvent;
            L.info(TAG, "LoginEvent,  OnEvent CheckModPwdResult：" + verifySmsCodeEvent);
            ArkUtils.send(new LoginCallback.VerifySmsCodeResult(verifySmsCodeEvent));
            return;
        }
        if (authBaseEvent instanceof AuthEvent.SmsModPwdEvent) {
            AuthEvent.SmsModPwdEvent smsModPwdEvent = (AuthEvent.SmsModPwdEvent) authBaseEvent;
            L.info(TAG, "LoginEvent,  OnEvent SmsModPwdEvent：" + smsModPwdEvent);
            ArkUtils.send(new LoginCallback.SmsModPwdResult(smsModPwdEvent));
            return;
        }
        if (authBaseEvent instanceof AuthEvent.SendSmsEvent) {
            AuthEvent.SendSmsEvent sendSmsEvent = (AuthEvent.SendSmsEvent) authBaseEvent;
            L.info(TAG, "LoginEvent,  OnEvent SendSmsEvent：" + sendSmsEvent);
            ArkUtils.send(new LoginCallback.RefreshSmsCodeCallBack(sendSmsEvent));
            return;
        }
        if (authBaseEvent instanceof AuthEvent.CheckRegisterEvent) {
            AuthEvent.CheckRegisterEvent checkRegisterEvent = (AuthEvent.CheckRegisterEvent) authBaseEvent;
            L.info(TAG, "LoginEvent,  OnEvent CheckRegisterEvent：" + checkRegisterEvent);
            ArkUtils.send(new LoginCallback.CheckUserRegisterResult(checkRegisterEvent));
            return;
        }
        if (authBaseEvent instanceof AuthEvent.RegisterEvent) {
            AuthEvent.RegisterEvent registerEvent = (AuthEvent.RegisterEvent) authBaseEvent;
            L.info(TAG, "LoginEvent,  OnEvent RegisterEvent 注册结果：" + registerEvent);
            ArkUtils.send(new LoginCallback.RegisterResult(registerEvent));
            return;
        }
        if (authBaseEvent instanceof AuthEvent.RefreshPicEvent) {
            AuthEvent.RefreshPicEvent refreshPicEvent = (AuthEvent.RefreshPicEvent) authBaseEvent;
            L.info(TAG, "LoginEvent,  OnEvent RefreshPicEvent：" + refreshPicEvent);
            ArkUtils.send(new LoginCallback.RefreshPicCodeCallback(refreshPicEvent));
            return;
        }
        if (authBaseEvent instanceof AuthEvent.LoginEvent) {
            AuthEvent.LoginEvent loginEvent = (AuthEvent.LoginEvent) authBaseEvent;
            if (loginEvent.getUid() != null) {
                LoginProperties.loginUid.set(Long.valueOf(Long.valueOf(loginEvent.getUid()).longValue()));
            }
            if (loginEvent.passport != null && loginEvent.passport.length() != 0) {
                LoginProperties.passport.set(loginEvent.passport);
            }
            L.info(TAG, "LoginEvent, uiAction : " + loginEvent.uiAction + ", " + loginEvent.description + ",isNew:" + loginEvent.isNewUser + ", uid = " + loginEvent.getUid());
            if (loginEvent.uiAction == 0) {
                ArkUtils.send(new LoginCallback.LoginReqSuccess(loginEvent.mobileMask));
                L.info(TAG, "loginSuccess.LoginReqSuccess... " + loginEvent.description);
                this.mLoginSuccess = true;
                TokenInfo defaultToken = LoginApi.getDefaultToken();
                L.info(TAG, "ResGetTicket %s %d", defaultToken.getToken(), Integer.valueOf(defaultToken.getTokenType()));
                String migrateUrl = LoginProxy.getInstance().getMigrateUrl(loginEvent.description);
                if (!TextUtils.isEmpty(migrateUrl)) {
                    LoginProperties.huyaAccountUpgradeTips.set(migrateUrl);
                }
                L.info(TAG, "loginSuccess.getMigrateUrl... " + migrateUrl);
                int i = this.mLoginType;
                if (i == 3 || i == 4) {
                    LoginInfo loginInfo = new LoginInfo();
                    loginInfo.type = this.mLoginType;
                    loginInfo.account = loginEvent.getUid();
                    LoginProperties.loginInfo.set(loginInfo);
                }
                authLoginSuccess();
                return;
            }
            if (loginEvent.uiAction == 1) {
                if (LoginProperties.loginState.get() == LoginProperties.LoginState.LoggedIn) {
                    L.info(TAG, "LoginProperties.loginState is LoggedIn return.");
                    return;
                } else {
                    L.error(TAG, "Login Failed. errorCode : %d, description : %s", Integer.valueOf(loginEvent.errCode), loginEvent.description);
                    loginFail(LoginEvent.LoginFail.Reason.Unknown, loginEvent.errCode, loginEvent.description);
                    return;
                }
            }
            if (loginEvent.uiAction == 5) {
                loginFail(LoginEvent.LoginFail.Reason.ServerHasNotReceivedSms, loginEvent.description);
                return;
            } else if (loginEvent.uiAction == 2) {
                ArkUtils.send(new LoginCallback.LoginNextVerify(loginEvent.nextVerifies));
                return;
            } else {
                if (loginEvent.uiAction == 4) {
                    ArkUtils.send(new LoginCallback.LoginNextVerifyFailed(loginEvent.description, loginEvent.nextVerifies));
                    return;
                }
                return;
            }
        }
        if (authBaseEvent instanceof AuthEvent.TimeoutEvent) {
            AuthEvent.TimeoutEvent timeoutEvent = (AuthEvent.TimeoutEvent) authBaseEvent;
            ArkUtils.send(new LoginCallback.LoginTimeout(timeoutEvent.description));
            L.info(TAG, "time out:%s", timeoutEvent);
        } else if (!(authBaseEvent instanceof AuthEvent.CreditRenewEvent) && (authBaseEvent instanceof AuthEvent.AnonymousEvent)) {
            L.info(TAG, "loginSuccess，匿名登录:%s", authBaseEvent.getUid());
        }
    }

    private void updateAccountInfo() {
        SqlHelper.asyncQueryForAll(ArkValue.gContext, LoginAccount.class, new SqlHelper.OnQueryListener<LoginAccount>() { // from class: com.huya.component.login.module.LoginModule.4
            @Override // com.duowan.live.sql.SqlHelper.OnQueryListener
            public void onQueryResult(List<LoginAccount> list) {
                if (list == null || list.isEmpty()) {
                    return;
                }
                Account account = LoginProperties.account.get();
                if (account == null || account.uid == 0) {
                    L.info(LoginModule.TAG, "updateAccountList：no loginIn..");
                    return;
                }
                if (!TextUtils.isEmpty(account.password)) {
                    L.info(LoginModule.TAG, "updateAccountList：login info is not empty..");
                    return;
                }
                long j = account.uid;
                for (LoginAccount loginAccount : list) {
                    if (loginAccount != null && j == loginAccount.uid) {
                        L.error(LoginModule.TAG, "updateAccountList：update password..");
                        account.password = loginAccount.password;
                    }
                }
            }
        });
    }

    @Override // com.huya.component.login.api.ILoginModule
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mThirdLogin != null) {
            this.mThirdLogin.onActivityResult(i, i2, intent);
        }
    }

    @Override // com.huya.component.login.api.ILoginModule
    public void thirdLogin(Activity activity, LoginInfo.LoginType loginType) {
        thirdLogin(activity, loginType, null);
    }

    @Override // com.huya.component.login.api.ILoginModule
    public void thirdLogin(Activity activity, LoginInfo.LoginType loginType, OnThirdAuthCallback onThirdAuthCallback) {
        if (this.mThirdLogin != null) {
            this.mThirdLogin.login(activity, loginType, onThirdAuthCallback);
        }
    }
}
