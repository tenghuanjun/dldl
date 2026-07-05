package com.hysdkproxy;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.util.Base64;
import com.huyaudb.webview.HuyaUdbLoginWebviewDialog;
import com.huyaudb.webview.HuyaWebviewDialog;
import com.huyaudb.webview.inter.IWebViewCallback;
import com.huyaudbunify.BuildConfig;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.account.SdkComType;
import com.huyaudbunify.bean.AppLoginData;
import com.huyaudbunify.bean.LoginData;
import com.huyaudbunify.bean.ResBindAuth;
import com.huyaudbunify.bean.ResBindNewSendSms;
import com.huyaudbunify.bean.ResBindNewVerifySms;
import com.huyaudbunify.bean.ResBindSendSms;
import com.huyaudbunify.bean.ResBindVerifySms;
import com.huyaudbunify.bean.ResCPSendSms;
import com.huyaudbunify.bean.ResCPToken;
import com.huyaudbunify.bean.ResCPVerifySms;
import com.huyaudbunify.bean.ResCheckRegMobile;
import com.huyaudbunify.bean.ResCheckUser;
import com.huyaudbunify.bean.ResFPSendSms;
import com.huyaudbunify.bean.ResFPToken;
import com.huyaudbunify.bean.ResFPVerifySms;
import com.huyaudbunify.bean.ResGetTicket;
import com.huyaudbunify.bean.ResInit;
import com.huyaudbunify.bean.ResKickOff;
import com.huyaudbunify.bean.ResLogin;
import com.huyaudbunify.bean.ResLoginAnonymous;
import com.huyaudbunify.bean.ResLoginAntiViolent;
import com.huyaudbunify.bean.ResLoginCred;
import com.huyaudbunify.bean.ResLoginGuest;
import com.huyaudbunify.bean.ResLoginMobileSendSms;
import com.huyaudbunify.bean.ResLoginPhoneSms;
import com.huyaudbunify.bean.ResLoginRefreshPic;
import com.huyaudbunify.bean.ResLoginSecondAuth;
import com.huyaudbunify.bean.ResLoginSessionSendSms;
import com.huyaudbunify.bean.ResLoginThird;
import com.huyaudbunify.bean.ResNewLogin;
import com.huyaudbunify.bean.ResRegisterPhoneCode;
import com.huyaudbunify.bean.ResRegisterPhoneToken;
import com.huyaudbunify.bean.ResRegisterSendSms;
import com.huyaudbunify.bean.ResRegisterVerifySms;
import com.huyaudbunify.bean.ResUnBindSendSms;
import com.huyaudbunify.bean.ResUnBindVerifySms;
import com.huyaudbunify.bean.StrategyDetail;
import com.huyaudbunify.bean.ThirdLoginOpenType;
import com.huyaudbunify.bean.ThirdLoginOption;
import com.huyaudbunify.bean.email.HuyaBindLoginEmailRsp;
import com.huyaudbunify.bean.email.HuyaEmailBindNewRsp;
import com.huyaudbunify.bean.email.HuyaEmailBindNewSendCodeRsp;
import com.huyaudbunify.bean.email.HuyaEmailChangePasswordRsp;
import com.huyaudbunify.bean.email.HuyaEmailIsRegRes;
import com.huyaudbunify.bean.email.HuyaEmailRegisterRsp;
import com.huyaudbunify.bean.email.HuyaEmailSendEmailCodeRsp;
import com.huyaudbunify.bean.email.HuyaEmailUnBindSendCodeRsp;
import com.huyaudbunify.bean.email.HuyaEmailUnBindVerifyCodeRsp;
import com.huyaudbunify.bean.email.HuyaFindPasswordByEmailRsp;
import com.huyaudbunify.bean.email.HuyaSendBindEmailCodeRsp;
import com.huyaudbunify.bean.email.HuyaVerifyEmailCodeRsp;
import com.huyaudbunify.core.AuthEvent;
import com.huyaudbunify.core.LoginEvent;
import com.huyaudbunify.dialog.msg.AuthLoginStrategyBean;
import com.huyaudbunify.dialog.msg.AuthLoginWebviewBean;
import com.huyaudbunify.handler.ProxyEventHandlerEx;
import com.huyaudbunify.inter.HuyaAuthCallBack;
import com.huyaudbunify.inter.IIRegTrustInfoCallBack;
import com.huyaudbunify.inter.IResponseCallBack;
import com.huyaudbunify.msg.response.MsgAuthAccessTokenRes;
import com.huyaudbunify.msg.response.MsgAuthAppInfoRes;
import com.huyaudbunify.msg.response.MsgAuthGetUserInfoRes;
import com.huyaudbunify.msg.response.MsgAuthLoginCodeRes;
import com.huyaudbunify.msg.response.MsgLoginCredRes;
import com.huyaudbunify.msg.response.MsgLoginInfoRes;
import com.huyaudbunify.msg.response.MsgLoginPhoneSmsRes;
import com.huyaudbunify.msg.response.MsgLoginThirdRes;
import com.huyaudbunify.msg.response.MsgSendLoginPhoneSms;
import com.huyaudbunify.request.HuyaWupUrlRequestUtil;
import com.huyaudbunify.util.HuyaAccountSaveUtils;
import com.huyaudbunify.util.HuyaAppUtils;
import com.huyaudbunify.util.HuyaDeveloperUtils;
import com.huyaudbunify.util.HuyaEventUtils;
import com.huyaudbunify.util.HuyaUdbLogUtils;
import com.hysdkproxy.LoginProxy;
import com.sqwan.common.constants.SqConstants;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoginHYProxy {
    private static volatile LoginHYProxy mInstance;
    private Handler mHandler = new Handler();

    /* JADX INFO: Access modifiers changed from: private */
    public int toStrategy(long j) {
        int i = (int) j;
        if (i == 2) {
            return 16;
        }
        if (i == 4) {
            return 1;
        }
        if (i == 8) {
            return 8;
        }
        if (i != 16) {
            return i != 64 ? 0 : 64;
        }
        return 2;
    }

    public static LoginHYProxy getInstance() {
        if (mInstance == null) {
            synchronized (LoginHYProxy.class) {
                if (mInstance == null) {
                    mInstance = new LoginHYProxy();
                }
            }
        }
        return mInstance;
    }

    private void copyLoginEvent(AuthEvent.LoginEvent loginEvent, AppLoginData appLoginData) {
        loginEvent.credit = appLoginData.getCred();
        loginEvent.emailMask = appLoginData.getEmailMask();
        loginEvent.uid = String.valueOf(appLoginData.getUid());
        loginEvent.yyid = String.valueOf(appLoginData.getHyid());
        loginEvent.mobileMask = appLoginData.getMobileMask();
        loginEvent.passport = appLoginData.getPassport();
        loginEvent.regOrigin = appLoginData.getRegOrigin();
        loginEvent.userId = appLoginData.getUserId();
        loginEvent.userIdState = appLoginData.getUserIdState();
        loginEvent.thirdParams = appLoginData.thirdParams;
        if (appLoginData.getIsHuya() == 1) {
            HuyaAccountSaveUtils.getInstance().setShowHuya(true);
        } else {
            HuyaAccountSaveUtils.getInstance().setShowHuya(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copyNextVerifies(AuthEvent.LoginEvent loginEvent, List<StrategyDetail> list) {
        loginEvent.nextVerifies = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            StrategyDetail strategyDetail = list.get(i);
            AuthEvent.NextVerify nextVerify = new AuthEvent.NextVerify();
            nextVerify.dataType = (int) strategyDetail.getDataType();
            nextVerify.data = strategyDetail.getData();
            nextVerify.promptContent = strategyDetail.getPromptContent();
            nextVerify.promptTitle = strategyDetail.getPromptTitle();
            nextVerify.strategy = toStrategy(strategyDetail.getStrategy());
            loginEvent.uid = String.valueOf(strategyDetail.getUid());
            loginEvent.nextVerifies.add(nextVerify);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<AuthEvent.NextVerify> copyNextVerifies(List<StrategyDetail> list) {
        ArrayList<AuthEvent.NextVerify> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            StrategyDetail strategyDetail = list.get(i);
            AuthEvent.NextVerify nextVerify = new AuthEvent.NextVerify();
            nextVerify.dataType = (int) strategyDetail.getDataType();
            nextVerify.data = strategyDetail.getData();
            nextVerify.promptContent = strategyDetail.getPromptContent();
            nextVerify.promptTitle = strategyDetail.getPromptTitle();
            nextVerify.strategy = toStrategy(strategyDetail.getStrategy());
            arrayList.add(nextVerify);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copyLogin(AuthEvent.LoginEvent loginEvent, LoginData loginData, List<StrategyDetail> list) {
        if (loginData != null) {
            HuyaAccountSaveUtils.getInstance().setLoginData(loginData);
            copyLoginEvent(loginEvent, loginData.getApploginData());
            loginEvent.appCommonData = loginData.getAppCommonData();
        }
        if (list != null) {
            copyNextVerifies(loginEvent, list);
        }
    }

    public void init(Application application, String str) {
        HuyaAuth.getInstance().init(application, str, new HuyaAuthCallBack<ResInit>(ResInit.class) { // from class: com.hysdkproxy.LoginHYProxy.1
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResInit resInit) {
                HuyaUdbLogUtils.e("resp-init", resInit);
                if (resInit == null || resInit.getLoginData() == null || resInit.getLoginData().getApploginData() == null || resInit.getLoginData().getApploginData().getUid() == 0) {
                    LoginHYProxy.this.anonymousLogin();
                }
            }
        });
        if (BuildConfig.IS_HUYA_ACCOUNT.booleanValue()) {
            HuyaAuth.getInstance().setUpdateCredWatcher(new HuyaAuthCallBack<ResLoginCred>(ResLoginCred.class) { // from class: com.hysdkproxy.LoginHYProxy.2
                @Override // com.huyaudbunify.inter.HuyaAuthCallBack
                public void hyCallBack(ResLoginCred resLoginCred) {
                    HuyaUdbLogUtils.e("resp-setUpdateCredWatcher", resLoginCred);
                    if (resLoginCred == null || !HuyaEventUtils.checkTimeOut(resLoginCred.getHeader().getRet(), "")) {
                        AuthEvent.CreditRenewEvent creditRenewEvent = new AuthEvent.CreditRenewEvent();
                        if (resLoginCred.getHeader().getRet() != 0) {
                            return;
                        }
                        HuyaAccountSaveUtils.getInstance().setLoginData(resLoginCred.getLoginData());
                        if (HuyaAccountSaveUtils.getInstance().getType() == SdkComType.HYTPYE_MAJOR_HY) {
                            creditRenewEvent.uid = String.valueOf(resLoginCred.getLoginData().getYyloginData().getYyuid());
                            creditRenewEvent.yyid = String.valueOf(resLoginCred.getLoginData().getYyloginData().getYyid());
                            creditRenewEvent.passport = resLoginCred.getLoginData().getYyloginData().getPassport();
                            creditRenewEvent.credit = resLoginCred.getLoginData().getYyloginData().getCreditOri();
                        } else {
                            creditRenewEvent.uid = String.valueOf(resLoginCred.getLoginData().getApploginData().getUid());
                            creditRenewEvent.yyid = String.valueOf(resLoginCred.getLoginData().getApploginData().getHyid());
                            creditRenewEvent.passport = resLoginCred.getLoginData().getApploginData().getPassport();
                            creditRenewEvent.credit = resLoginCred.getLoginData().getApploginData().getCred();
                            creditRenewEvent.userId = resLoginCred.getLoginData().getApploginData().getUserId();
                            creditRenewEvent.userIdState = resLoginCred.getLoginData().getApploginData().getUserIdState();
                        }
                        HuyaEventUtils.dispatchAuthEvent(creditRenewEvent);
                    }
                }
            });
        } else {
            HuyaAuth.getInstance().setNewLoginWatcher(new HuyaAuthCallBack<ResNewLogin>(ResNewLogin.class) { // from class: com.hysdkproxy.LoginHYProxy.3
                @Override // com.huyaudbunify.inter.HuyaAuthCallBack
                public void hyCallBack(ResNewLogin resNewLogin) {
                    HuyaUdbLogUtils.e("resp-setNewLoginWatcher", resNewLogin);
                    if (resNewLogin == null || HuyaAccountSaveUtils.getInstance().getDeviceId().compareTo(resNewLogin.getDeviceid()) == 0) {
                        return;
                    }
                    LoginEvent.ETNewLogin eTNewLogin = new LoginEvent.ETNewLogin();
                    eTNewLogin.strReason = resNewLogin.getContext().getBytes();
                    eTNewLogin.uReason = resNewLogin.getType();
                    eTNewLogin.uid = resNewLogin.getUid();
                    ProxyEventHandlerEx.getInstance().dspatchNewLogin(eTNewLogin);
                }
            });
        }
        HuyaAuth.getInstance().setRegTrustInfoWatch(new IIRegTrustInfoCallBack() { // from class: com.hysdkproxy.LoginHYProxy.4
            @Override // com.huyaudbunify.inter.IIRegTrustInfoCallBack
            public void regTrustInfo() {
                HuyaAuth.getInstance().regTrustInfo(10);
            }
        });
        HuyaAuth.getInstance().setKickOffWatcher(new HuyaAuthCallBack<ResKickOff>(ResKickOff.class) { // from class: com.hysdkproxy.LoginHYProxy.5
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResKickOff resKickOff) {
                HuyaUdbLogUtils.e("resp-setKickOffWatcher", resKickOff);
                if (resKickOff != null) {
                    LoginEvent.ETLoginKickoff eTLoginKickoff = new LoginEvent.ETLoginKickoff();
                    eTLoginKickoff.strReason = resKickOff.getContext().getBytes();
                    eTLoginKickoff.uReason = resKickOff.getType();
                    ProxyEventHandlerEx.getInstance().dispatchKickOff(eTLoginKickoff);
                }
            }
        });
    }

    public void loginOut() {
        HuyaAccountSaveUtils.getInstance().setLogin(false);
        HuyaAccountSaveUtils.getInstance().setUid(0L);
        HuyaAccountSaveUtils.getInstance().setPassport("");
        HuyaAccountSaveUtils.getInstance().setMobileMask("");
        HuyaAuth.getInstance().loginOut();
        anonymousLogin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void anonymousSelfLogin() {
        HuyaAuth.getInstance().loginAnonymous(new HuyaAuthCallBack<ResLoginAnonymous>(ResLoginAnonymous.class) { // from class: com.hysdkproxy.LoginHYProxy.6
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginAnonymous resLoginAnonymous) {
                HuyaUdbLogUtils.e("resp-loginAnonymous", resLoginAnonymous);
                if (resLoginAnonymous == null || resLoginAnonymous.getUid() == 0) {
                    return;
                }
                AuthEvent.AnonymousEvent anonymousEvent = new AuthEvent.AnonymousEvent();
                anonymousEvent.uid = String.valueOf(resLoginAnonymous.getUid());
                HuyaEventUtils.dispatchAuthEvent(anonymousEvent);
                ProxyEventHandlerEx.getInstance().dispatchAnonymInfo(new LoginEvent.ETMyInfoAnonym());
            }
        });
    }

    public void anonymousLogin() {
        AppLoginData anonyLoginCred = HuyaAuth.getInstance().getAnonyLoginCred();
        if (anonyLoginCred == null || anonyLoginCred.getCred().isEmpty()) {
            anonymousSelfLogin();
        } else {
            HuyaAuth.getInstance().loginAnonyCred(anonyLoginCred.getUid(), anonyLoginCred.getCred(), new HuyaAuthCallBack<ResLoginCred>(ResLoginCred.class) { // from class: com.hysdkproxy.LoginHYProxy.7
                @Override // com.huyaudbunify.inter.HuyaAuthCallBack
                public void hyCallBack(ResLoginCred resLoginCred) {
                    HuyaUdbLogUtils.e("resp-loginAnonyCred", resLoginCred);
                    if (resLoginCred != null && HuyaEventUtils.checkTimeOut(resLoginCred.getHeader().getRet(), "", false)) {
                        try {
                            Thread.sleep(1000L);
                            LoginHYProxy.this.anonymousSelfLogin();
                            return;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    if (resLoginCred != null && resLoginCred.getLoginData() != null && resLoginCred.getLoginData().getApploginData() != null && resLoginCred.getLoginData().getApploginData().getUid() != 0) {
                        AuthEvent.AnonymousEvent anonymousEvent = new AuthEvent.AnonymousEvent();
                        anonymousEvent.uid = String.valueOf(resLoginCred.getLoginData().getApploginData().getUid());
                        HuyaEventUtils.dispatchAuthEvent(anonymousEvent);
                    } else {
                        try {
                            Thread.sleep(1000L);
                            LoginHYProxy.this.anonymousSelfLogin();
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    @Deprecated
    public void sendLoginPhoneSms(String str, int i, int i2, final String str2) {
        HuyaAuth.getInstance().sendLoginPhoneSms(str, i, i2, new HuyaAuthCallBack<ResLoginMobileSendSms>(ResLoginMobileSendSms.class) { // from class: com.hysdkproxy.LoginHYProxy.8
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginMobileSendSms resLoginMobileSendSms) {
                HuyaUdbLogUtils.e("resp-sendLoginPhoneSms", resLoginMobileSendSms);
                if (resLoginMobileSendSms == null || !HuyaEventUtils.checkTimeOut(resLoginMobileSendSms.getHeader().getRet(), str2)) {
                    AuthEvent.SendSmsEvent sendSmsEvent = new AuthEvent.SendSmsEvent();
                    if (resLoginMobileSendSms == null) {
                        sendSmsEvent.errCode = -1;
                        sendSmsEvent.uiAction = 1;
                    } else {
                        sendSmsEvent.errCode = resLoginMobileSendSms.getHeader().getRet();
                        sendSmsEvent.extParam = resLoginMobileSendSms.getHeader().getExtParam();
                        sendSmsEvent.description = resLoginMobileSendSms.getHeader().getDescription();
                        if (resLoginMobileSendSms.getHeader().getRet() == 0) {
                            sendSmsEvent.uiAction = 0;
                        } else if (resLoginMobileSendSms.getHeader().getRet() == 10030) {
                            sendSmsEvent.uiAction = 2;
                            sendSmsEvent.nextVerifies = new ArrayList<>();
                            for (int i3 = 0; i3 < resLoginMobileSendSms.getLoginStrategy().size(); i3++) {
                                StrategyDetail strategyDetail = resLoginMobileSendSms.getLoginStrategy().get(i3);
                                AuthEvent.NextVerify nextVerify = new AuthEvent.NextVerify();
                                nextVerify.dataType = (int) strategyDetail.getDataType();
                                nextVerify.data = strategyDetail.getData();
                                nextVerify.promptContent = strategyDetail.getPromptContent();
                                nextVerify.promptTitle = strategyDetail.getPromptTitle();
                                nextVerify.strategy = LoginHYProxy.this.toStrategy(strategyDetail.getStrategy());
                                sendSmsEvent.nextVerifies.add(nextVerify);
                            }
                        } else {
                            sendSmsEvent.uiAction = 1;
                        }
                    }
                    sendSmsEvent.context = str2;
                    HuyaEventUtils.dispatchAuthEvent(sendSmsEvent);
                }
            }
        });
    }

    public void sendLoginPhoneSms(String str, int i, int i2, final String str2, final IResponseCallBack<MsgSendLoginPhoneSms> iResponseCallBack) {
        HuyaAuth.getInstance().sendLoginPhoneSms(str, i, i2, new HuyaAuthCallBack<ResLoginMobileSendSms>(ResLoginMobileSendSms.class) { // from class: com.hysdkproxy.LoginHYProxy.9
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginMobileSendSms resLoginMobileSendSms) {
                HuyaUdbLogUtils.e("resp-sendLoginPhoneSms", resLoginMobileSendSms);
                AuthEvent.SendSmsEvent sendSmsEvent = new AuthEvent.SendSmsEvent();
                if (resLoginMobileSendSms == null) {
                    sendSmsEvent.errCode = -1;
                    sendSmsEvent.uiAction = 1;
                } else {
                    sendSmsEvent.errCode = resLoginMobileSendSms.getHeader().getRet();
                    sendSmsEvent.extParam = resLoginMobileSendSms.getHeader().getExtParam();
                    sendSmsEvent.description = resLoginMobileSendSms.getHeader().getDescription();
                    if (resLoginMobileSendSms.getHeader().getRet() == 0) {
                        sendSmsEvent.uiAction = 0;
                    } else if (resLoginMobileSendSms.getHeader().getRet() == 10030) {
                        sendSmsEvent.uiAction = 2;
                        sendSmsEvent.nextVerifies = new ArrayList<>();
                        for (int i3 = 0; i3 < resLoginMobileSendSms.getLoginStrategy().size(); i3++) {
                            StrategyDetail strategyDetail = resLoginMobileSendSms.getLoginStrategy().get(i3);
                            AuthEvent.NextVerify nextVerify = new AuthEvent.NextVerify();
                            nextVerify.dataType = (int) strategyDetail.getDataType();
                            nextVerify.data = strategyDetail.getData();
                            nextVerify.promptContent = strategyDetail.getPromptContent();
                            nextVerify.promptTitle = strategyDetail.getPromptTitle();
                            nextVerify.strategy = LoginHYProxy.this.toStrategy(strategyDetail.getStrategy());
                            sendSmsEvent.nextVerifies.add(nextVerify);
                        }
                    } else {
                        sendSmsEvent.uiAction = 1;
                    }
                }
                sendSmsEvent.context = str2;
                iResponseCallBack.onResponse(new MsgSendLoginPhoneSms(resLoginMobileSendSms, sendSmsEvent));
            }
        });
    }

    @Deprecated
    public void loginPhoneSms(String str, String str2, boolean z, int i, final String str3) {
        HuyaAuth.getInstance().loginPhoneSms(str, str2, z, i, new HuyaAuthCallBack<ResLoginPhoneSms>(ResLoginPhoneSms.class) { // from class: com.hysdkproxy.LoginHYProxy.10
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginPhoneSms resLoginPhoneSms) {
                HuyaUdbLogUtils.e("resp-loginPhoneSms", resLoginPhoneSms);
                if (resLoginPhoneSms == null || !HuyaEventUtils.checkTimeOut(resLoginPhoneSms.getHeader().getRet(), str3)) {
                    AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                    if (resLoginPhoneSms == null) {
                        loginEvent.errCode = -1;
                        loginEvent.uiAction = 1;
                    } else {
                        loginEvent.errCode = resLoginPhoneSms.getHeader().getRet();
                        loginEvent.extParam = resLoginPhoneSms.getHeader().getExtParam();
                        loginEvent.description = resLoginPhoneSms.getHeader().getDescription();
                        if (resLoginPhoneSms.getHeader().getRet() == 0) {
                            loginEvent.uiAction = 0;
                            LoginHYProxy.this.copyLogin(loginEvent, resLoginPhoneSms.getLoginData(), null);
                        } else if (resLoginPhoneSms.getHeader().getRet() == 10030) {
                            loginEvent.uiAction = 2;
                            LoginHYProxy.this.copyLogin(loginEvent, resLoginPhoneSms.getLoginData(), resLoginPhoneSms.getLoginStrategy());
                        } else {
                            loginEvent.uiAction = 1;
                        }
                    }
                    loginEvent.context = str3;
                    LoginHYProxy.this.loginDispatchAuthEvent(loginEvent);
                }
            }
        });
    }

    public void loginPhoneSms(String str, String str2, boolean z, int i, final String str3, final IResponseCallBack<MsgLoginPhoneSmsRes> iResponseCallBack) {
        HuyaAuth.getInstance().loginPhoneSms(str, str2, z, i, new HuyaAuthCallBack<ResLoginPhoneSms>(ResLoginPhoneSms.class) { // from class: com.hysdkproxy.LoginHYProxy.11
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginPhoneSms resLoginPhoneSms) {
                HuyaUdbLogUtils.e("resp-loginPhoneSms", resLoginPhoneSms);
                AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                if (resLoginPhoneSms == null) {
                    loginEvent.errCode = -1;
                    loginEvent.uiAction = 1;
                } else {
                    loginEvent.errCode = resLoginPhoneSms.getHeader().getRet();
                    loginEvent.extParam = resLoginPhoneSms.getHeader().getExtParam();
                    loginEvent.description = resLoginPhoneSms.getHeader().getDescription();
                    if (resLoginPhoneSms.getHeader().getRet() == 0) {
                        loginEvent.uiAction = 0;
                        LoginHYProxy.this.copyLogin(loginEvent, resLoginPhoneSms.getLoginData(), null);
                    } else if (resLoginPhoneSms.getHeader().getRet() == 10030) {
                        loginEvent.uiAction = 2;
                        LoginHYProxy.this.copyLogin(loginEvent, resLoginPhoneSms.getLoginData(), resLoginPhoneSms.getLoginStrategy());
                    } else {
                        loginEvent.uiAction = 1;
                    }
                }
                loginEvent.context = str3;
                ProxyEventHandlerEx.getInstance().handleLoginRegTrustInfo(loginEvent);
                if (loginEvent.uiAction == 1) {
                    LoginHYProxy.this.anonymousLogin();
                }
                iResponseCallBack.onResponse(new MsgLoginPhoneSmsRes(loginEvent, resLoginPhoneSms));
            }
        });
    }

    @Deprecated
    public void loginMobileQuick(int i, String str, boolean z, final String str2) {
        HuyaAuth.getInstance().loginMobileQuick(i, str, z, null, new HuyaAuthCallBack<ResLogin>(ResLogin.class) { // from class: com.hysdkproxy.LoginHYProxy.12
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLogin resLogin) {
                HuyaUdbLogUtils.e("resp-loginMobileQuick", resLogin);
                if (resLogin == null || !HuyaEventUtils.checkTimeOut(resLogin.getHeader().getRet(), str2)) {
                    AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                    if (resLogin == null) {
                        loginEvent.errCode = -1;
                        loginEvent.uiAction = 1;
                    } else {
                        loginEvent.errCode = resLogin.getHeader().getRet();
                        loginEvent.extParam = resLogin.getHeader().getExtParam();
                        loginEvent.description = resLogin.getHeader().getDescription();
                        if (resLogin.getHeader().getRet() == 0) {
                            loginEvent.uiAction = 0;
                            LoginHYProxy.this.copyLogin(loginEvent, resLogin.getLoginData(), null);
                        } else {
                            loginEvent.uiAction = 1;
                        }
                    }
                    loginEvent.context = str2;
                    LoginHYProxy.this.loginDispatchAuthEvent(loginEvent);
                }
            }
        });
    }

    public void loginMobileQuick(int i, String str, boolean z, final String str2, final IResponseCallBack<MsgLoginInfoRes> iResponseCallBack) {
        HuyaAuth.getInstance().loginMobileQuick(i, str, z, null, new HuyaAuthCallBack<ResLogin>(ResLogin.class) { // from class: com.hysdkproxy.LoginHYProxy.13
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLogin resLogin) {
                HuyaUdbLogUtils.e("resp-loginMobileQuick", resLogin);
                AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                if (resLogin == null) {
                    loginEvent.errCode = -1;
                    loginEvent.uiAction = 1;
                } else {
                    loginEvent.errCode = resLogin.getHeader().getRet();
                    loginEvent.extParam = resLogin.getHeader().getExtParam();
                    loginEvent.description = resLogin.getHeader().getDescription();
                    if (resLogin.getHeader().getRet() == 0) {
                        loginEvent.uiAction = 0;
                        LoginHYProxy.this.copyLogin(loginEvent, resLogin.getLoginData(), null);
                    } else {
                        loginEvent.uiAction = 1;
                    }
                }
                loginEvent.context = str2;
                ProxyEventHandlerEx.getInstance().handleLoginRegTrustInfo(loginEvent);
                if (loginEvent.uiAction == 1) {
                    LoginHYProxy.this.anonymousLogin();
                }
                iResponseCallBack.onResponse(new MsgLoginInfoRes(loginEvent, resLogin));
            }
        });
    }

    @Deprecated
    public void thirdLogin(int i, String str, ThirdLoginOption thirdLoginOption, String str2, boolean z, final String str3) {
        HuyaAuth.getInstance().loginThird(thirdLoginOption != null ? thirdLoginOption.getPartnerUid() : "", i, str, thirdLoginOption, str2, z, null, new HuyaAuthCallBack<ResLoginThird>(ResLoginThird.class) { // from class: com.hysdkproxy.LoginHYProxy.14
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginThird resLoginThird) {
                HuyaUdbLogUtils.e("resp-thirdLogin", resLoginThird);
                if (resLoginThird == null || !HuyaEventUtils.checkTimeOut(resLoginThird.getHeader().getRet(), str3)) {
                    AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                    if (resLoginThird == null) {
                        loginEvent.errCode = -1;
                        loginEvent.uiAction = 1;
                    } else {
                        loginEvent.errCode = resLoginThird.getHeader().getRet();
                        loginEvent.description = resLoginThird.getHeader().getDescription();
                        if (resLoginThird.getHeader().getRet() == 10030 || resLoginThird.getHeader().getRet() == 0) {
                            if (resLoginThird.getHeader().getRet() == 10030) {
                                loginEvent.errCode = 0;
                                loginEvent.uiAction = 2;
                            }
                            LoginHYProxy.this.copyLogin(loginEvent, resLoginThird.getLoginData(), resLoginThird.getLoginStrategy());
                            loginEvent.thirdPartyInfo = new AuthEvent.ThirdPartyInfo();
                            loginEvent.thirdPartyInfo.gender = resLoginThird.getGender();
                            loginEvent.thirdPartyInfo.imageUrl = resLoginThird.getImageUrl();
                            loginEvent.thirdPartyInfo.uid = resLoginThird.getOpenId();
                            loginEvent.thirdPartyInfo.nickname = resLoginThird.getNickName();
                        } else {
                            loginEvent.uiAction = 1;
                        }
                    }
                    loginEvent.context = str3;
                    LoginHYProxy.this.loginDispatchAuthEvent(loginEvent);
                }
            }
        });
    }

    public void bindAuth(int i, String str, String str2, String str3, int i2, HuyaAuthCallBack<ResBindAuth> huyaAuthCallBack) {
        HuyaAuth.getInstance().bindAuth(i, str, str2, str3, i2, huyaAuthCallBack);
    }

    public void thirdLogin(ThirdLoginOpenType thirdLoginOpenType, String str, ThirdLoginOption thirdLoginOption, String str2, boolean z, final String str3, final IResponseCallBack<MsgLoginThirdRes> iResponseCallBack) {
        HuyaAuth.getInstance().loginThird(thirdLoginOption != null ? thirdLoginOption.getPartnerUid() : "", thirdLoginOpenType.getOpenType(), str, thirdLoginOption, str2, z, null, new HuyaAuthCallBack<ResLoginThird>(ResLoginThird.class) { // from class: com.hysdkproxy.LoginHYProxy.15
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginThird resLoginThird) {
                HuyaUdbLogUtils.e("resp-thirdLogin", resLoginThird);
                AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                if (resLoginThird == null) {
                    loginEvent.errCode = -1;
                    loginEvent.uiAction = 1;
                } else {
                    loginEvent.errCode = resLoginThird.getHeader().getRet();
                    loginEvent.description = resLoginThird.getHeader().getDescription();
                    if (resLoginThird.getHeader().getRet() == 10030 || resLoginThird.getHeader().getRet() == 0) {
                        if (resLoginThird.getHeader().getRet() == 10030) {
                            loginEvent.errCode = 0;
                            loginEvent.uiAction = 2;
                        }
                        LoginHYProxy.this.copyLogin(loginEvent, resLoginThird.getLoginData(), resLoginThird.getLoginStrategy());
                        loginEvent.thirdPartyInfo = new AuthEvent.ThirdPartyInfo();
                        loginEvent.thirdPartyInfo.gender = resLoginThird.getGender();
                        loginEvent.thirdPartyInfo.imageUrl = resLoginThird.getImageUrl();
                        loginEvent.thirdPartyInfo.uid = resLoginThird.getOpenId();
                        loginEvent.thirdPartyInfo.nickname = resLoginThird.getNickName();
                    } else {
                        loginEvent.uiAction = 1;
                    }
                }
                loginEvent.context = str3;
                ProxyEventHandlerEx.getInstance().handleLoginRegTrustInfo(loginEvent);
                if (loginEvent.uiAction == 1) {
                    LoginHYProxy.this.anonymousLogin();
                }
                iResponseCallBack.onResponse(new MsgLoginThirdRes(loginEvent, resLoginThird));
            }
        });
    }

    public void EnterLogin() {
        HuyaAuth.getInstance().EnterLogin();
    }

    public void pushLoginOperation(String str, int i) {
        HuyaAuth.getInstance().pushLoginOperation(str, i);
    }

    public void loginStart(double d, double d2, String str) {
        HuyaAuth.getInstance().loginStart(d, d2, str);
    }

    public void loginUserAction(String str, int i, int i2, int i3) {
        HuyaAuth.getInstance().loginUserAction(str, i, i2, i3);
    }

    @Deprecated
    public void loginPassport(String str, String str2, boolean z, final String str3) {
        HuyaAuth.getInstance().login(str, str2, z, new HuyaAuthCallBack<ResLogin>(ResLogin.class) { // from class: com.hysdkproxy.LoginHYProxy.16
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLogin resLogin) {
                HuyaUdbLogUtils.e("resp-loginPassport", resLogin);
                if (resLogin == null || !HuyaEventUtils.checkTimeOut(resLogin.getHeader().getRet(), str3)) {
                    AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                    if (resLogin == null) {
                        loginEvent.errCode = -1;
                        loginEvent.uiAction = 1;
                    } else {
                        loginEvent.description = resLogin.getHeader().getDescription();
                        if (resLogin.getHeader().getRet() == 10030 || resLogin.getHeader().getRet() == 0) {
                            if (resLogin.getHeader().getRet() == 10030) {
                                loginEvent.errCode = 0;
                                loginEvent.uiAction = 2;
                            }
                            LoginHYProxy.this.copyLogin(loginEvent, resLogin.getLoginData(), resLogin.getLoginStrategy());
                        } else {
                            loginEvent.errCode = resLogin.getHeader().getRet();
                            loginEvent.uiAction = 1;
                        }
                    }
                    loginEvent.context = str3;
                    LoginHYProxy.this.loginDispatchAuthEvent(loginEvent);
                }
            }
        });
    }

    public void loginPassport(String str, String str2, boolean z, final String str3, final IResponseCallBack<MsgLoginInfoRes> iResponseCallBack) {
        HuyaAuth.getInstance().login(str, str2, z, new HuyaAuthCallBack<ResLogin>(ResLogin.class) { // from class: com.hysdkproxy.LoginHYProxy.17
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLogin resLogin) {
                HuyaUdbLogUtils.e("resp-loginPassport", resLogin);
                AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                if (resLogin == null) {
                    loginEvent.errCode = -1;
                    loginEvent.uiAction = 1;
                } else {
                    loginEvent.description = resLogin.getHeader().getDescription();
                    if (resLogin.getHeader().getRet() == 10030 || resLogin.getHeader().getRet() == 0) {
                        if (resLogin.getHeader().getRet() == 10030) {
                            loginEvent.errCode = 0;
                            loginEvent.uiAction = 2;
                        }
                        LoginHYProxy.this.copyLogin(loginEvent, resLogin.getLoginData(), resLogin.getLoginStrategy());
                    } else {
                        loginEvent.errCode = resLogin.getHeader().getRet();
                        loginEvent.uiAction = 1;
                    }
                }
                loginEvent.context = str3;
                ProxyEventHandlerEx.getInstance().handleLoginRegTrustInfo(loginEvent);
                if (loginEvent.uiAction == 1) {
                    LoginHYProxy.this.anonymousLogin();
                }
                iResponseCallBack.onResponse(new MsgLoginInfoRes(loginEvent, resLogin));
            }
        });
    }

    public void loginSecondAuth_sendsms(long j, final String str) {
        HuyaAuth.getInstance().sendLoginSessionSms(j, new HuyaAuthCallBack<ResLoginSessionSendSms>(ResLoginSessionSendSms.class) { // from class: com.hysdkproxy.LoginHYProxy.18
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginSessionSendSms resLoginSessionSendSms) {
                HuyaUdbLogUtils.e("resp-loginSecondAuth_sendsms", resLoginSessionSendSms);
                if (resLoginSessionSendSms == null || !HuyaEventUtils.checkTimeOut(resLoginSessionSendSms.getHeader().getRet(), str)) {
                    AuthEvent.SendSmsEvent sendSmsEvent = new AuthEvent.SendSmsEvent();
                    if (resLoginSessionSendSms == null) {
                        sendSmsEvent.errCode = -1;
                        sendSmsEvent.uiAction = 1;
                    } else {
                        sendSmsEvent.errCode = resLoginSessionSendSms.getHeader().getRet();
                        sendSmsEvent.description = resLoginSessionSendSms.getHeader().getDescription();
                        if (resLoginSessionSendSms.getHeader().getRet() == 0) {
                            sendSmsEvent.uiAction = 0;
                        } else {
                            sendSmsEvent.uiAction = 1;
                        }
                    }
                    sendSmsEvent.context = str;
                    HuyaEventUtils.dispatchAuthEvent(sendSmsEvent);
                }
            }
        });
    }

    public void loginSecondAuth_sms(long j, String str) {
        HuyaAuth.getInstance().loginSecondAuth(j, 8, str, new HuyaAuthCallBack<ResLoginSecondAuth>(ResLoginSecondAuth.class) { // from class: com.hysdkproxy.LoginHYProxy.19
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginSecondAuth resLoginSecondAuth) {
                HuyaUdbLogUtils.e("resp-loginSecondAuth_sms", resLoginSecondAuth);
                if (resLoginSecondAuth == null || !HuyaEventUtils.checkTimeOut(resLoginSecondAuth.getHeader().getRet(), "")) {
                    AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                    if (resLoginSecondAuth == null) {
                        loginEvent.errCode = -1;
                        loginEvent.uiAction = 4;
                    } else {
                        loginEvent.errCode = resLoginSecondAuth.getHeader().getRet();
                        loginEvent.description = resLoginSecondAuth.getHeader().getDescription();
                        if (resLoginSecondAuth.getHeader().getRet() == 0 || resLoginSecondAuth.getHeader().getRet() == 10030) {
                            if (resLoginSecondAuth.getHeader().getRet() == 10030) {
                                loginEvent.errCode = 0;
                                loginEvent.uiAction = 2;
                            }
                            LoginHYProxy.this.copyLogin(loginEvent, resLoginSecondAuth.getLoginData(), resLoginSecondAuth.getLoginStrategy());
                        } else {
                            loginEvent.uiAction = 4;
                        }
                    }
                    LoginHYProxy.this.loginDispatchAuthEvent(loginEvent);
                }
            }
        });
    }

    public void refreshPic(long j) {
        HuyaAuth.getInstance().refreshLoginPic(j, new HuyaAuthCallBack<ResLoginRefreshPic>(ResLoginRefreshPic.class) { // from class: com.hysdkproxy.LoginHYProxy.20
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginRefreshPic resLoginRefreshPic) {
                HuyaUdbLogUtils.e("resp-refreshPic", resLoginRefreshPic);
                if (resLoginRefreshPic == null || !HuyaEventUtils.checkTimeOut(resLoginRefreshPic.getHeader().getRet(), "")) {
                    AuthEvent.RefreshPicEvent refreshPicEvent = new AuthEvent.RefreshPicEvent();
                    if (resLoginRefreshPic == null) {
                        refreshPicEvent.errCode = -1;
                        refreshPicEvent.uiAction = 4;
                    } else {
                        refreshPicEvent.errCode = resLoginRefreshPic.getHeader().getRet();
                        refreshPicEvent.description = resLoginRefreshPic.getHeader().getDescription();
                        if (resLoginRefreshPic.getHeader().getRet() == 0) {
                            refreshPicEvent.uiAction = 0;
                            refreshPicEvent.pic = resLoginRefreshPic.getPic();
                        } else {
                            refreshPicEvent.uiAction = 4;
                        }
                    }
                    HuyaEventUtils.dispatchAuthEvent(refreshPicEvent);
                }
            }
        });
    }

    public void mobileTokenLogin(long j, String str) {
        HuyaAuth.getInstance().loginSecondAuth(j, 16, str, new HuyaAuthCallBack<ResLoginSecondAuth>(ResLoginSecondAuth.class) { // from class: com.hysdkproxy.LoginHYProxy.21
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginSecondAuth resLoginSecondAuth) {
                HuyaUdbLogUtils.e("resp-mobileTokenLogin", resLoginSecondAuth);
                if (resLoginSecondAuth == null || !HuyaEventUtils.checkTimeOut(resLoginSecondAuth.getHeader().getRet(), "")) {
                    AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                    if (resLoginSecondAuth == null) {
                        loginEvent.errCode = -1;
                        loginEvent.uiAction = 4;
                    } else {
                        loginEvent.description = resLoginSecondAuth.getHeader().getDescription();
                        if (resLoginSecondAuth.getHeader().getRet() == 10030 || resLoginSecondAuth.getHeader().getRet() == 0) {
                            if (resLoginSecondAuth.getHeader().getRet() == 10030) {
                                loginEvent.errCode = 0;
                                loginEvent.uiAction = 2;
                            }
                            LoginHYProxy.this.copyLogin(loginEvent, resLoginSecondAuth.getLoginData(), resLoginSecondAuth.getLoginStrategy());
                        } else {
                            loginEvent.errCode = resLoginSecondAuth.getHeader().getRet();
                            loginEvent.uiAction = 4;
                        }
                    }
                    LoginHYProxy.this.loginDispatchAuthEvent(loginEvent);
                }
            }
        });
    }

    public void loginSecondAuth_pic(long j, String str) {
        HuyaAuth.getInstance().loginAntiViolent(j, str, null, new HuyaAuthCallBack<ResLoginAntiViolent>(ResLoginAntiViolent.class) { // from class: com.hysdkproxy.LoginHYProxy.22
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginAntiViolent resLoginAntiViolent) {
                HuyaUdbLogUtils.e("resp-loginSecondAuth_pic", resLoginAntiViolent);
                if (resLoginAntiViolent == null || !HuyaEventUtils.checkTimeOut(resLoginAntiViolent.getHeader().getRet(), "")) {
                    AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                    if (resLoginAntiViolent == null) {
                        loginEvent.errCode = -1;
                        loginEvent.uiAction = 1;
                    } else {
                        loginEvent.errCode = resLoginAntiViolent.getHeader().getRet();
                        loginEvent.description = resLoginAntiViolent.getHeader().getDescription();
                        if (resLoginAntiViolent.getHeader().getRet() == 0 || resLoginAntiViolent.getHeader().getRet() == 10030) {
                            if (resLoginAntiViolent.getHeader().getRet() == 10030) {
                                loginEvent.errCode = 0;
                                loginEvent.uiAction = 2;
                            }
                            loginEvent.uiAction = 0;
                            LoginHYProxy.this.copyLogin(loginEvent, resLoginAntiViolent.getLoginData(), resLoginAntiViolent.getLoginStrategy());
                        } else if (resLoginAntiViolent.getHeader().getRet() == 70001) {
                            loginEvent.uiAction = 4;
                            LoginHYProxy.this.copyNextVerifies(loginEvent, resLoginAntiViolent.getLoginStrategy());
                        } else {
                            loginEvent.uiAction = 1;
                        }
                    }
                    LoginHYProxy.this.loginDispatchAuthEvent(loginEvent);
                }
            }
        });
    }

    public void register_sendsms(String str, int i, final String str2) {
        HuyaAuth.getInstance().sendRegSmsCode(str, i, new HuyaAuthCallBack<ResRegisterSendSms>(ResRegisterSendSms.class) { // from class: com.hysdkproxy.LoginHYProxy.23
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResRegisterSendSms resRegisterSendSms) {
                HuyaUdbLogUtils.e("resp-register_sendsms", resRegisterSendSms);
                if (resRegisterSendSms == null || !HuyaEventUtils.checkTimeOut(resRegisterSendSms.getHeader().getRet(), str2)) {
                    AuthEvent.SendSmsEvent sendSmsEvent = new AuthEvent.SendSmsEvent();
                    if (resRegisterSendSms == null) {
                        sendSmsEvent.errCode = -1;
                        sendSmsEvent.uiAction = 1;
                    } else {
                        sendSmsEvent.errCode = resRegisterSendSms.getHeader().getRet();
                        sendSmsEvent.description = resRegisterSendSms.getHeader().getDescription();
                        if (resRegisterSendSms.getHeader().getRet() == 0) {
                            sendSmsEvent.uiAction = 0;
                        } else if (resRegisterSendSms.getHeader().getRet() == 10030) {
                            sendSmsEvent.uiAction = 2;
                            sendSmsEvent.nextVerifies = LoginHYProxy.this.copyNextVerifies(resRegisterSendSms.getLoginStrategy());
                        } else {
                            sendSmsEvent.uiAction = 1;
                        }
                    }
                    sendSmsEvent.context = str2;
                    HuyaEventUtils.dispatchAuthEvent(sendSmsEvent);
                }
            }
        });
    }

    public void register_sms(String str, String str2, String str3, final String str4) {
        HuyaAuth.getInstance().registerPhoneWithCode(str, str2, str3, new HuyaAuthCallBack<ResRegisterPhoneCode>(ResRegisterPhoneCode.class) { // from class: com.hysdkproxy.LoginHYProxy.24
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResRegisterPhoneCode resRegisterPhoneCode) {
                HuyaUdbLogUtils.e("resp-register_sms", resRegisterPhoneCode);
                if (resRegisterPhoneCode == null || !HuyaEventUtils.checkTimeOut(resRegisterPhoneCode.getHeader().getRet(), str4)) {
                    AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                    if (resRegisterPhoneCode == null) {
                        loginEvent.errCode = -1;
                        loginEvent.uiAction = 1;
                    } else {
                        loginEvent.errCode = resRegisterPhoneCode.getHeader().getRet();
                        loginEvent.extParam = resRegisterPhoneCode.getHeader().getExtParam();
                        loginEvent.description = resRegisterPhoneCode.getHeader().getDescription();
                        if (resRegisterPhoneCode.getHeader().getRet() == 0) {
                            loginEvent.isNewUser = true;
                            LoginHYProxy.this.copyLogin(loginEvent, resRegisterPhoneCode.getLoginData(), null);
                        } else {
                            loginEvent.uiAction = 1;
                        }
                    }
                    loginEvent.context = str4;
                    LoginHYProxy.this.loginDispatchAuthEvent(loginEvent);
                }
            }
        });
    }

    public void checkModPwdFP(String str) {
        HuyaAuth.getInstance().checkUserFP(str, new HuyaAuthCallBack<ResCheckUser>(ResCheckUser.class) { // from class: com.hysdkproxy.LoginHYProxy.25
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResCheckUser resCheckUser) {
                HuyaUdbLogUtils.e("resp-checkModPwdFP", resCheckUser);
                if (resCheckUser == null || !HuyaEventUtils.checkTimeOut(resCheckUser.getHeader().getRet(), "")) {
                    AuthEvent.CheckModPwdEvent checkModPwdEvent = new AuthEvent.CheckModPwdEvent();
                    if (resCheckUser == null) {
                        checkModPwdEvent.errCode = -1;
                        checkModPwdEvent.uiAction = 1;
                    } else {
                        checkModPwdEvent.errCode = resCheckUser.getHeader().getRet();
                        checkModPwdEvent.description = resCheckUser.getHeader().getDescription();
                        checkModPwdEvent.mobileMask = resCheckUser.getMobileMask();
                        checkModPwdEvent.emailMask = resCheckUser.getEmailMask();
                        checkModPwdEvent.url = resCheckUser.getUrl();
                        if (resCheckUser.getHeader().getRet() == 0) {
                            checkModPwdEvent.uiAction = 0;
                        } else if (resCheckUser.getHeader().getRet() == 20014) {
                            checkModPwdEvent.uiAction = 6;
                        } else {
                            checkModPwdEvent.uiAction = 1;
                        }
                        checkModPwdEvent.isLoginMobile = resCheckUser.getIsLoginMobile() == 1;
                    }
                    HuyaEventUtils.dispatchAuthEvent(checkModPwdEvent);
                }
            }
        });
    }

    public void checkModPwdCP(String str) {
        HuyaAuth.getInstance().checkUserCP(str, new HuyaAuthCallBack<ResCheckUser>(ResCheckUser.class) { // from class: com.hysdkproxy.LoginHYProxy.26
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResCheckUser resCheckUser) {
                HuyaUdbLogUtils.e("resp-checkModPwdCP", resCheckUser);
                if (resCheckUser == null || !HuyaEventUtils.checkTimeOut(resCheckUser.getHeader().getRet(), "")) {
                    AuthEvent.CheckModPwdEvent checkModPwdEvent = new AuthEvent.CheckModPwdEvent();
                    if (resCheckUser == null) {
                        checkModPwdEvent.errCode = -1;
                        checkModPwdEvent.uiAction = 1;
                    } else {
                        checkModPwdEvent.errCode = resCheckUser.getHeader().getRet();
                        checkModPwdEvent.description = resCheckUser.getHeader().getDescription();
                        checkModPwdEvent.emailMask = resCheckUser.getEmailMask();
                        if (resCheckUser.getHeader().getRet() == 0) {
                            checkModPwdEvent.uiAction = 0;
                        } else if (resCheckUser.getHeader().getRet() == 20014) {
                            checkModPwdEvent.uiAction = 6;
                        } else {
                            checkModPwdEvent.uiAction = 1;
                        }
                        checkModPwdEvent.isLoginMobile = resCheckUser.getIsLoginMobile() == 1;
                    }
                    HuyaEventUtils.dispatchAuthEvent(checkModPwdEvent);
                }
            }
        });
    }

    public void modPwd_sendSms(long j, int i, final String str) {
        String str2;
        String credit;
        if (HuyaAccountSaveUtils.getInstance().getLoginData() != null) {
            String cred = HuyaAccountSaveUtils.getInstance().getLoginData().getApploginData().getCred();
            credit = HuyaAccountSaveUtils.getInstance().getLoginData().getYyloginData().getCredit();
            str2 = cred;
        } else {
            str2 = "";
            credit = str2;
        }
        HuyaAuth.getInstance().sendChangePasswordSmsCode(j, str2, credit, i, new HuyaAuthCallBack<ResCPSendSms>(ResCPSendSms.class) { // from class: com.hysdkproxy.LoginHYProxy.27
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResCPSendSms resCPSendSms) {
                HuyaUdbLogUtils.e("resp-modPwd_sendSms", resCPSendSms);
                if (resCPSendSms == null || !HuyaEventUtils.checkTimeOut(resCPSendSms.getHeader().getRet(), str)) {
                    AuthEvent.SendSmsEvent sendSmsEvent = new AuthEvent.SendSmsEvent();
                    if (resCPSendSms == null) {
                        sendSmsEvent.errCode = -1;
                        sendSmsEvent.uiAction = 1;
                    } else {
                        sendSmsEvent.errCode = resCPSendSms.getHeader().getRet();
                        sendSmsEvent.extParam = resCPSendSms.getHeader().getExtParam();
                        sendSmsEvent.description = resCPSendSms.getHeader().getDescription();
                        if (resCPSendSms.getHeader().getRet() == 0) {
                            sendSmsEvent.uiAction = 0;
                        } else if (resCPSendSms.getHeader().getRet() == 10030) {
                            sendSmsEvent.uiAction = 2;
                            sendSmsEvent.nextVerifies = LoginHYProxy.this.copyNextVerifies(resCPSendSms.getLoginStrategy());
                        } else {
                            sendSmsEvent.uiAction = 1;
                        }
                    }
                    sendSmsEvent.context = str;
                    HuyaEventUtils.dispatchAuthEvent(sendSmsEvent);
                }
            }
        });
    }

    public void modPwd_verifySms(long j, String str, final String str2) {
        String str3;
        String credit;
        if (HuyaAccountSaveUtils.getInstance().getLoginData() != null) {
            String cred = HuyaAccountSaveUtils.getInstance().getLoginData().getApploginData().getCred();
            credit = HuyaAccountSaveUtils.getInstance().getLoginData().getYyloginData().getCredit();
            str3 = cred;
        } else {
            str3 = "";
            credit = str3;
        }
        HuyaAuth.getInstance().verifyChangePasswordSmsCode(j, str3, credit, str, new HuyaAuthCallBack<ResCPVerifySms>(ResCPVerifySms.class) { // from class: com.hysdkproxy.LoginHYProxy.28
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResCPVerifySms resCPVerifySms) {
                HuyaUdbLogUtils.e("resp-modPwd_verifySms", resCPVerifySms);
                if (resCPVerifySms == null || !HuyaEventUtils.checkTimeOut(resCPVerifySms.getHeader().getRet(), str2)) {
                    AuthEvent.VerifySmsCodeEvent verifySmsCodeEvent = new AuthEvent.VerifySmsCodeEvent();
                    if (resCPVerifySms == null) {
                        verifySmsCodeEvent.errCode = -1;
                        verifySmsCodeEvent.uiAction = 1;
                    } else {
                        verifySmsCodeEvent.errCode = resCPVerifySms.getHeader().getRet();
                        verifySmsCodeEvent.extParam = resCPVerifySms.getHeader().getExtParam();
                        verifySmsCodeEvent.description = resCPVerifySms.getHeader().getDescription();
                        if (resCPVerifySms.getHeader().getRet() == 0) {
                            verifySmsCodeEvent.uiAction = 0;
                        } else {
                            verifySmsCodeEvent.uiAction = 1;
                        }
                    }
                    verifySmsCodeEvent.context = str2;
                    HuyaEventUtils.dispatchAuthEvent(verifySmsCodeEvent);
                }
            }
        });
    }

    public void modPwd(long j, String str, final String str2) {
        String str3;
        String credit;
        if (HuyaAccountSaveUtils.getInstance().getLoginData() != null) {
            String cred = HuyaAccountSaveUtils.getInstance().getLoginData().getApploginData().getCred();
            credit = HuyaAccountSaveUtils.getInstance().getLoginData().getYyloginData().getCredit();
            str3 = cred;
        } else {
            str3 = "";
            credit = str3;
        }
        HuyaAuth.getInstance().changePasswordWithToken(j, str3, credit, str, new HuyaAuthCallBack<ResCPToken>(ResCPToken.class) { // from class: com.hysdkproxy.LoginHYProxy.29
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResCPToken resCPToken) {
                HuyaUdbLogUtils.e("resp-modPwd", resCPToken);
                if (resCPToken == null || !HuyaEventUtils.checkTimeOut(resCPToken.getHeader().getRet(), str2)) {
                    AuthEvent.SmsModPwdEvent smsModPwdEvent = new AuthEvent.SmsModPwdEvent();
                    if (resCPToken == null) {
                        smsModPwdEvent.errCode = -1;
                        smsModPwdEvent.uiAction = 1;
                    } else {
                        smsModPwdEvent.errCode = resCPToken.getHeader().getRet();
                        smsModPwdEvent.extParam = resCPToken.getHeader().getExtParam();
                        smsModPwdEvent.description = resCPToken.getHeader().getDescription();
                        if (resCPToken.getHeader().getRet() == 0) {
                            smsModPwdEvent.uiAction = 0;
                        } else if (resCPToken.getHeader().getRet() == 10030) {
                            smsModPwdEvent.uiAction = 2;
                            smsModPwdEvent.nextVerifies = LoginHYProxy.this.copyNextVerifies(resCPToken.getLoginStrategy());
                        } else {
                            smsModPwdEvent.uiAction = 1;
                        }
                    }
                    smsModPwdEvent.context = str2;
                    HuyaEventUtils.dispatchAuthEvent(smsModPwdEvent);
                }
            }
        });
    }

    public void findPwd_sendSms(String str, int i, final String str2) {
        HuyaAuth.getInstance().sendFindPasswordSmsCode(str, i, new HuyaAuthCallBack<ResFPSendSms>(ResFPSendSms.class) { // from class: com.hysdkproxy.LoginHYProxy.30
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResFPSendSms resFPSendSms) {
                HuyaUdbLogUtils.e("resp-findPwd_sendSms", resFPSendSms);
                if (resFPSendSms == null || !HuyaEventUtils.checkTimeOut(resFPSendSms.getHeader().getRet(), str2)) {
                    AuthEvent.SendSmsEvent sendSmsEvent = new AuthEvent.SendSmsEvent();
                    if (resFPSendSms == null) {
                        sendSmsEvent.errCode = -1;
                        sendSmsEvent.uiAction = 1;
                    } else {
                        sendSmsEvent.errCode = resFPSendSms.getHeader().getRet();
                        sendSmsEvent.extParam = resFPSendSms.getHeader().getExtParam();
                        sendSmsEvent.description = resFPSendSms.getHeader().getDescription();
                        if (resFPSendSms.getHeader().getRet() == 0) {
                            sendSmsEvent.uiAction = 0;
                        } else if (resFPSendSms.getHeader().getRet() == 10030) {
                            sendSmsEvent.uiAction = 2;
                            sendSmsEvent.nextVerifies = LoginHYProxy.this.copyNextVerifies(resFPSendSms.getLoginStrategy());
                        } else {
                            sendSmsEvent.uiAction = 1;
                        }
                    }
                    sendSmsEvent.context = str2;
                    HuyaEventUtils.dispatchAuthEvent(sendSmsEvent);
                }
            }
        });
    }

    public void findPwd_verifySms(String str, String str2, final String str3) {
        HuyaAuth.getInstance().verifyFindPasswordSmsCode(str, str2, new HuyaAuthCallBack<ResFPVerifySms>(ResFPVerifySms.class) { // from class: com.hysdkproxy.LoginHYProxy.31
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResFPVerifySms resFPVerifySms) {
                HuyaUdbLogUtils.e("resp-findPwd_verifySms", resFPVerifySms);
                if (resFPVerifySms == null || !HuyaEventUtils.checkTimeOut(resFPVerifySms.getHeader().getRet(), str3)) {
                    AuthEvent.VerifySmsCodeEvent verifySmsCodeEvent = new AuthEvent.VerifySmsCodeEvent();
                    if (resFPVerifySms == null) {
                        verifySmsCodeEvent.errCode = -1;
                        verifySmsCodeEvent.uiAction = 1;
                    } else {
                        verifySmsCodeEvent.errCode = resFPVerifySms.getHeader().getRet();
                        verifySmsCodeEvent.extParam = resFPVerifySms.getHeader().getExtParam();
                        verifySmsCodeEvent.description = resFPVerifySms.getHeader().getDescription();
                        if (resFPVerifySms.getHeader().getRet() == 0) {
                            verifySmsCodeEvent.uiAction = 0;
                        } else {
                            verifySmsCodeEvent.uiAction = 1;
                        }
                    }
                    verifySmsCodeEvent.context = str3;
                    HuyaEventUtils.dispatchAuthEvent(verifySmsCodeEvent);
                }
            }
        });
    }

    public void findPwd(String str, String str2, final String str3) {
        HuyaAuth.getInstance().findPasswordWithToken(str, str2, new HuyaAuthCallBack<ResFPToken>(ResFPToken.class) { // from class: com.hysdkproxy.LoginHYProxy.32
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResFPToken resFPToken) {
                HuyaUdbLogUtils.e("resp-findPwd", resFPToken);
                if (resFPToken == null || !HuyaEventUtils.checkTimeOut(resFPToken.getHeader().getRet(), str3)) {
                    AuthEvent.SmsModPwdEvent smsModPwdEvent = new AuthEvent.SmsModPwdEvent();
                    if (resFPToken == null) {
                        smsModPwdEvent.errCode = -1;
                        smsModPwdEvent.uiAction = 1;
                    } else {
                        smsModPwdEvent.errCode = resFPToken.getHeader().getRet();
                        smsModPwdEvent.extParam = resFPToken.getHeader().getExtParam();
                        smsModPwdEvent.description = resFPToken.getHeader().getDescription();
                        if (resFPToken.getHeader().getRet() == 0) {
                            smsModPwdEvent.uiAction = 0;
                        } else if (resFPToken.getHeader().getRet() == 10030) {
                            smsModPwdEvent.uiAction = 2;
                            smsModPwdEvent.nextVerifies = LoginHYProxy.this.copyNextVerifies(resFPToken.getLoginStrategy());
                        } else {
                            smsModPwdEvent.uiAction = 1;
                        }
                    }
                    smsModPwdEvent.context = str3;
                    HuyaEventUtils.dispatchAuthEvent(smsModPwdEvent);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerCredLoginRes(long j, String str, String str2, boolean z, boolean z2, String str3, ResLoginCred resLoginCred, IResponseCallBack<MsgLoginCredRes> iResponseCallBack) {
        HuyaUdbLogUtils.e("resp-credLogin", resLoginCred);
        if (resLoginCred == null || !HuyaEventUtils.checkTimeOut(resLoginCred.getHeader().getRet(), str3)) {
            AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
            if (resLoginCred == null) {
                loginEvent.errCode = -1;
                loginEvent.uiAction = 1;
            } else {
                loginEvent.errCode = resLoginCred.getHeader().getRet();
                loginEvent.extParam = resLoginCred.getHeader().getExtParam();
                loginEvent.description = resLoginCred.getHeader().getDescription();
                if (resLoginCred.getHeader().getRet() == 0 || resLoginCred.getHeader().getRet() == 10030) {
                    if (resLoginCred.getHeader().getRet() == 10030) {
                        loginEvent.errCode = 0;
                        loginEvent.uiAction = 2;
                    }
                    copyLogin(loginEvent, resLoginCred.getLoginData(), null);
                } else {
                    loginEvent.uiAction = 1;
                }
            }
            loginEvent.context = str3;
            if (iResponseCallBack == null) {
                loginDispatchAuthEvent(loginEvent);
                return;
            }
            ProxyEventHandlerEx.getInstance().handleLoginRegTrustInfo(loginEvent);
            if (loginEvent.uiAction == 1) {
                anonymousLogin();
            }
            iResponseCallBack.onResponse(new MsgLoginCredRes(loginEvent, resLoginCred));
        }
    }

    private void selfCredLogin(final long j, final String str, final String str2, final boolean z, final boolean z2, final String str3, final IResponseCallBack<MsgLoginCredRes> iResponseCallBack) {
        HuyaAuth.getInstance().loginCred(j, str2, str, z, z2, new HuyaAuthCallBack<ResLoginCred>(ResLoginCred.class) { // from class: com.hysdkproxy.LoginHYProxy.33
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginCred resLoginCred) {
                LoginHYProxy.this.handlerCredLoginRes(j, str, str2, z, z2, str3, resLoginCred, iResponseCallBack);
            }
        });
    }

    @Deprecated
    public void credLogin(long j, String str, String str2, boolean z, String str3) {
        selfCredLogin(j, str, str2, z, false, str3, null);
    }

    public void credLogin(long j, String str, String str2, boolean z, String str3, IResponseCallBack<MsgLoginCredRes> iResponseCallBack) {
        selfCredLogin(j, str, str, z, false, str3, iResponseCallBack);
    }

    public void bindMobile_SendSms(long j, String str, int i, final String str2) {
        HuyaAuth.getInstance().bindSendSms(j, str, i, new HuyaAuthCallBack<ResBindSendSms>(ResBindSendSms.class) { // from class: com.hysdkproxy.LoginHYProxy.34
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResBindSendSms resBindSendSms) {
                HuyaUdbLogUtils.e("resp-bindMobile_SendSms", resBindSendSms);
                if (resBindSendSms == null || !HuyaEventUtils.checkTimeOut(resBindSendSms.getHeader().getRet(), str2)) {
                    AuthEvent.SendSmsEvent sendSmsEvent = new AuthEvent.SendSmsEvent();
                    if (resBindSendSms == null) {
                        sendSmsEvent.errCode = -1;
                        sendSmsEvent.uiAction = 1;
                    } else {
                        sendSmsEvent.errCode = resBindSendSms.getHeader().getRet();
                        sendSmsEvent.extParam = resBindSendSms.getHeader().getExtParam();
                        sendSmsEvent.description = resBindSendSms.getHeader().getDescription();
                        if (resBindSendSms.getHeader().getRet() == 0) {
                            sendSmsEvent.uiAction = 0;
                        } else {
                            sendSmsEvent.uiAction = 1;
                        }
                    }
                    sendSmsEvent.context = str2;
                    HuyaEventUtils.dispatchAuthEvent(sendSmsEvent);
                }
            }
        });
    }

    public void bindMobile_VerifySms(long j, String str, String str2, String str3, final String str4) {
        HuyaAuth.getInstance().bindVerifySms(j, str, str2, str3, new HuyaAuthCallBack<ResBindVerifySms>(ResBindVerifySms.class) { // from class: com.hysdkproxy.LoginHYProxy.35
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResBindVerifySms resBindVerifySms) {
                HuyaUdbLogUtils.e("resp-bindMobile_VerifySms", resBindVerifySms);
                if (resBindVerifySms == null || !HuyaEventUtils.checkTimeOut(resBindVerifySms.getHeader().getRet(), str4)) {
                    AuthEvent.VerifySmsCodeEvent verifySmsCodeEvent = new AuthEvent.VerifySmsCodeEvent();
                    if (resBindVerifySms == null) {
                        verifySmsCodeEvent.errCode = -1;
                        verifySmsCodeEvent.uiAction = 1;
                    } else {
                        verifySmsCodeEvent.errCode = resBindVerifySms.getHeader().getRet();
                        verifySmsCodeEvent.extParam = resBindVerifySms.getHeader().getExtParam();
                        verifySmsCodeEvent.description = resBindVerifySms.getHeader().getDescription();
                        if (resBindVerifySms.getHeader().getRet() == 0) {
                            verifySmsCodeEvent.uiAction = 0;
                        } else {
                            verifySmsCodeEvent.uiAction = 1;
                        }
                    }
                    verifySmsCodeEvent.context = str4;
                    HuyaEventUtils.dispatchAuthEvent(verifySmsCodeEvent);
                }
            }
        });
    }

    public void unBind_sendSms(long j, int i, final String str) {
        HuyaAuth.getInstance().unBindSendSms(j, i, new HuyaAuthCallBack<ResUnBindSendSms>(ResUnBindSendSms.class) { // from class: com.hysdkproxy.LoginHYProxy.36
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResUnBindSendSms resUnBindSendSms) {
                HuyaUdbLogUtils.e("resp-unBind_sendSms", resUnBindSendSms);
                if (resUnBindSendSms == null || !HuyaEventUtils.checkTimeOut(resUnBindSendSms.getHeader().getRet(), str)) {
                    AuthEvent.SendSmsEvent sendSmsEvent = new AuthEvent.SendSmsEvent();
                    if (resUnBindSendSms == null) {
                        sendSmsEvent.errCode = -1;
                        sendSmsEvent.uiAction = 1;
                    } else {
                        sendSmsEvent.errCode = resUnBindSendSms.getHeader().getRet();
                        sendSmsEvent.extParam = resUnBindSendSms.getHeader().getExtParam();
                        sendSmsEvent.description = resUnBindSendSms.getHeader().getDescription();
                        if (resUnBindSendSms.getHeader().getRet() == 0) {
                            sendSmsEvent.uiAction = 0;
                        } else {
                            sendSmsEvent.uiAction = 1;
                        }
                    }
                    sendSmsEvent.context = str;
                    HuyaEventUtils.dispatchAuthEvent(sendSmsEvent);
                }
            }
        });
    }

    public void unBind_verifySms(long j, String str, final String str2) {
        HuyaAuth.getInstance().unBindVerifySms(j, str, new HuyaAuthCallBack<ResUnBindVerifySms>(ResUnBindVerifySms.class) { // from class: com.hysdkproxy.LoginHYProxy.37
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResUnBindVerifySms resUnBindVerifySms) {
                HuyaUdbLogUtils.e("resp-unBind_verifySms", resUnBindVerifySms);
                if (resUnBindVerifySms == null || !HuyaEventUtils.checkTimeOut(resUnBindVerifySms.getHeader().getRet(), str2)) {
                    AuthEvent.VerifySmsCodeEvent verifySmsCodeEvent = new AuthEvent.VerifySmsCodeEvent();
                    if (resUnBindVerifySms == null) {
                        verifySmsCodeEvent.errCode = -1;
                        verifySmsCodeEvent.uiAction = 1;
                    } else {
                        verifySmsCodeEvent.errCode = resUnBindVerifySms.getHeader().getRet();
                        verifySmsCodeEvent.extParam = resUnBindVerifySms.getHeader().getExtParam();
                        verifySmsCodeEvent.description = resUnBindVerifySms.getHeader().getDescription();
                        if (resUnBindVerifySms.getHeader().getRet() == 0) {
                            verifySmsCodeEvent.uiAction = 0;
                        } else {
                            verifySmsCodeEvent.uiAction = 1;
                        }
                    }
                    verifySmsCodeEvent.context = str2;
                    HuyaEventUtils.dispatchAuthEvent(verifySmsCodeEvent);
                }
            }
        });
    }

    public void bindNew_sendSms(long j, String str, int i, final String str2) {
        HuyaAuth.getInstance().bindNewSendSms(j, str, i, new HuyaAuthCallBack<ResBindNewSendSms>(ResBindNewSendSms.class) { // from class: com.hysdkproxy.LoginHYProxy.38
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResBindNewSendSms resBindNewSendSms) {
                HuyaUdbLogUtils.e("resp-bindNew_sendSms", resBindNewSendSms);
                if (resBindNewSendSms == null || !HuyaEventUtils.checkTimeOut(resBindNewSendSms.getHeader().getRet(), str2)) {
                    AuthEvent.SendSmsEvent sendSmsEvent = new AuthEvent.SendSmsEvent();
                    if (resBindNewSendSms == null) {
                        sendSmsEvent.errCode = -1;
                        sendSmsEvent.uiAction = 1;
                    } else {
                        sendSmsEvent.errCode = resBindNewSendSms.getHeader().getRet();
                        sendSmsEvent.extParam = resBindNewSendSms.getHeader().getExtParam();
                        sendSmsEvent.description = resBindNewSendSms.getHeader().getDescription();
                        if (resBindNewSendSms.getHeader().getRet() == 0) {
                            sendSmsEvent.uiAction = 0;
                        } else {
                            sendSmsEvent.uiAction = 1;
                        }
                    }
                    sendSmsEvent.context = str2;
                    HuyaEventUtils.dispatchAuthEvent(sendSmsEvent);
                }
            }
        });
    }

    public void bindNew_verifySms(long j, String str, String str2, final String str3) {
        HuyaAuth.getInstance().bindNewVerifySms(j, str, str2, new HuyaAuthCallBack<ResBindNewVerifySms>(ResBindNewVerifySms.class) { // from class: com.hysdkproxy.LoginHYProxy.39
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResBindNewVerifySms resBindNewVerifySms) {
                HuyaUdbLogUtils.e("resp-bindNew_verifySms", resBindNewVerifySms);
                if (resBindNewVerifySms == null || !HuyaEventUtils.checkTimeOut(resBindNewVerifySms.getHeader().getRet(), str3)) {
                    AuthEvent.VerifySmsCodeEvent verifySmsCodeEvent = new AuthEvent.VerifySmsCodeEvent();
                    if (resBindNewVerifySms == null) {
                        verifySmsCodeEvent.errCode = -1;
                        verifySmsCodeEvent.uiAction = 1;
                    } else {
                        verifySmsCodeEvent.errCode = resBindNewVerifySms.getHeader().getRet();
                        verifySmsCodeEvent.extParam = resBindNewVerifySms.getHeader().getExtParam();
                        verifySmsCodeEvent.description = resBindNewVerifySms.getHeader().getDescription();
                        if (resBindNewVerifySms.getHeader().getRet() == 0) {
                            verifySmsCodeEvent.uiAction = 0;
                        } else {
                            verifySmsCodeEvent.uiAction = 1;
                        }
                    }
                    verifySmsCodeEvent.context = str3;
                    HuyaEventUtils.dispatchAuthEvent(verifySmsCodeEvent);
                }
            }
        });
    }

    public void register_verifySms(String str, String str2, final String str3) {
        HuyaAuth.getInstance().verifyRegSmsCode(str, str2, new HuyaAuthCallBack<ResRegisterVerifySms>(ResRegisterVerifySms.class) { // from class: com.hysdkproxy.LoginHYProxy.40
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResRegisterVerifySms resRegisterVerifySms) {
                HuyaUdbLogUtils.e("resp-register_verifySms", resRegisterVerifySms);
                if (resRegisterVerifySms == null || !HuyaEventUtils.checkTimeOut(resRegisterVerifySms.getHeader().getRet(), str3)) {
                    AuthEvent.VerifySmsCodeEvent verifySmsCodeEvent = new AuthEvent.VerifySmsCodeEvent();
                    if (resRegisterVerifySms == null) {
                        verifySmsCodeEvent.errCode = -1;
                        verifySmsCodeEvent.uiAction = 1;
                    } else {
                        verifySmsCodeEvent.errCode = resRegisterVerifySms.getHeader().getRet();
                        verifySmsCodeEvent.extParam = resRegisterVerifySms.getHeader().getExtParam();
                        verifySmsCodeEvent.description = resRegisterVerifySms.getHeader().getDescription();
                        if (resRegisterVerifySms.getHeader().getRet() == 0) {
                            verifySmsCodeEvent.uiAction = 0;
                        } else {
                            verifySmsCodeEvent.uiAction = 1;
                        }
                    }
                    verifySmsCodeEvent.context = str3;
                    HuyaEventUtils.dispatchAuthEvent(verifySmsCodeEvent);
                }
            }
        });
    }

    public void register_token(String str, String str2, final String str3) {
        HuyaAuth.getInstance().registerPhoneWithToken(str, str2, new HuyaAuthCallBack<ResRegisterPhoneToken>(ResRegisterPhoneToken.class) { // from class: com.hysdkproxy.LoginHYProxy.41
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResRegisterPhoneToken resRegisterPhoneToken) {
                HuyaUdbLogUtils.e("resp-register_token", resRegisterPhoneToken);
                if (resRegisterPhoneToken == null || !HuyaEventUtils.checkTimeOut(resRegisterPhoneToken.getHeader().getRet(), str3)) {
                    AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                    if (resRegisterPhoneToken == null) {
                        loginEvent.errCode = -1;
                        loginEvent.uiAction = 1;
                    } else {
                        loginEvent.errCode = resRegisterPhoneToken.getHeader().getRet();
                        loginEvent.extParam = resRegisterPhoneToken.getHeader().getExtParam();
                        loginEvent.description = resRegisterPhoneToken.getHeader().getDescription();
                        if (resRegisterPhoneToken.getHeader().getRet() == 0) {
                            loginEvent.isNewUser = true;
                            loginEvent.uiAction = 0;
                            LoginHYProxy.this.copyLogin(loginEvent, resRegisterPhoneToken.getLoginData(), null);
                        } else if (resRegisterPhoneToken.getHeader().getRet() == 10030) {
                            loginEvent.uiAction = 2;
                            LoginHYProxy.this.copyLogin(loginEvent, resRegisterPhoneToken.getLoginData(), resRegisterPhoneToken.getLoginStrategy());
                        } else {
                            loginEvent.uiAction = 1;
                        }
                    }
                    loginEvent.context = str3;
                    LoginHYProxy.this.loginDispatchAuthEvent(loginEvent);
                }
            }
        });
    }

    public void checkUserRegister(String str, final String str2) {
        HuyaAuth.getInstance().checkMobileIsReg(str, new HuyaAuthCallBack<ResCheckRegMobile>(ResCheckRegMobile.class) { // from class: com.hysdkproxy.LoginHYProxy.42
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResCheckRegMobile resCheckRegMobile) {
                HuyaUdbLogUtils.e("resp-checkUserRegister", resCheckRegMobile);
                if (resCheckRegMobile == null || !HuyaEventUtils.checkTimeOut(resCheckRegMobile.getHeader().getRet(), str2)) {
                    AuthEvent.CheckRegisterEvent checkRegisterEvent = new AuthEvent.CheckRegisterEvent();
                    if (resCheckRegMobile == null) {
                        checkRegisterEvent.errCode = -1;
                        checkRegisterEvent.uiAction = 1;
                    } else {
                        checkRegisterEvent.errCode = resCheckRegMobile.getHeader().getRet();
                        checkRegisterEvent.extParam = resCheckRegMobile.getHeader().getExtParam();
                        checkRegisterEvent.description = resCheckRegMobile.getHeader().getDescription();
                        if (resCheckRegMobile.getHeader().getRet() == 0 && resCheckRegMobile.getStatus() == 0) {
                            checkRegisterEvent.uiAction = 0;
                        } else {
                            checkRegisterEvent.uiAction = 1;
                        }
                    }
                    checkRegisterEvent.context = str2;
                    HuyaEventUtils.dispatchAuthEvent(checkRegisterEvent);
                }
            }
        });
    }

    public void bindChangeMobile_VerifySms(long j, String str, String str2, final String str3) {
        HuyaAuth.getInstance().bindChangePhone(j, str, str2, new HuyaAuthCallBack<ResBindVerifySms>(ResBindVerifySms.class) { // from class: com.hysdkproxy.LoginHYProxy.43
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResBindVerifySms resBindVerifySms) {
                HuyaUdbLogUtils.e("resp-bindChangeMobile_VerifySms", resBindVerifySms);
                if (resBindVerifySms == null || !HuyaEventUtils.checkTimeOut(resBindVerifySms.getHeader().getRet(), str3)) {
                    AuthEvent.VerifySmsCodeEvent verifySmsCodeEvent = new AuthEvent.VerifySmsCodeEvent();
                    if (resBindVerifySms == null) {
                        verifySmsCodeEvent.errCode = -1;
                        verifySmsCodeEvent.uiAction = 1;
                    } else {
                        verifySmsCodeEvent.errCode = resBindVerifySms.getHeader().getRet();
                        verifySmsCodeEvent.extParam = resBindVerifySms.getHeader().getExtParam();
                        verifySmsCodeEvent.description = resBindVerifySms.getHeader().getDescription();
                        if (resBindVerifySms.getHeader().getRet() == 0) {
                            verifySmsCodeEvent.uiAction = 0;
                        } else {
                            verifySmsCodeEvent.uiAction = 1;
                        }
                    }
                    verifySmsCodeEvent.context = str3;
                    HuyaEventUtils.dispatchAuthEvent(verifySmsCodeEvent);
                }
            }
        });
    }

    public void loginGuest(final String str) {
        HuyaAuth.getInstance().loginGuest(null, new HuyaAuthCallBack<ResLoginGuest>(ResLoginGuest.class) { // from class: com.hysdkproxy.LoginHYProxy.44
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginGuest resLoginGuest) {
                HuyaUdbLogUtils.e("resp-loginGuest", resLoginGuest);
                if (resLoginGuest == null || !HuyaEventUtils.checkTimeOut(resLoginGuest.getHeader().getRet(), str)) {
                    AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                    if (resLoginGuest == null) {
                        loginEvent.errCode = -1;
                        loginEvent.uiAction = 1;
                    } else {
                        loginEvent.errCode = resLoginGuest.getHeader().getRet();
                        loginEvent.extParam = resLoginGuest.getHeader().getExtParam();
                        loginEvent.description = resLoginGuest.getHeader().getDescription();
                        if (resLoginGuest.getHeader().getRet() == 0) {
                            LoginHYProxy.this.copyLogin(loginEvent, resLoginGuest.getLoginData(), null);
                        } else {
                            loginEvent.uiAction = 1;
                        }
                    }
                    loginEvent.context = str;
                    LoginHYProxy.this.loginDispatchAuthEvent(loginEvent);
                }
            }
        });
    }

    public void loginDispatchAuthEvent(AuthEvent.LoginEvent loginEvent) {
        HuyaEventUtils.dispatchAuthEvent(loginEvent);
        if (loginEvent.uiAction == 1) {
            anonymousLogin();
        }
    }

    public void loginWithOtpSign(long j, String str, String str2, String str3, final String str4) {
        HuyaAuth.getInstance().loginWithOtpSign(j, str, str2, str3, new HuyaAuthCallBack<ResLoginCred>(ResLoginCred.class) { // from class: com.hysdkproxy.LoginHYProxy.45
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLoginCred resLoginCred) {
                HuyaUdbLogUtils.e("resp-loginWithOtpSign", resLoginCred);
                if (resLoginCred == null || !HuyaEventUtils.checkTimeOut(resLoginCred.getHeader().getRet(), str4)) {
                    AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                    if (resLoginCred == null) {
                        loginEvent.errCode = -1;
                        loginEvent.uiAction = 1;
                    } else {
                        loginEvent.errCode = resLoginCred.getHeader().getRet();
                        loginEvent.extParam = resLoginCred.getHeader().getExtParam();
                        loginEvent.description = resLoginCred.getHeader().getDescription();
                        if (resLoginCred.getHeader().getRet() == 0) {
                            LoginHYProxy.this.copyLogin(loginEvent, resLoginCred.getLoginData(), null);
                        } else {
                            loginEvent.uiAction = 1;
                        }
                    }
                    loginEvent.context = str4;
                    LoginHYProxy.this.loginDispatchAuthEvent(loginEvent);
                }
            }
        });
    }

    public void getAuthAccessToken(final String str, final String str2, final String str3, final IResponseCallBack<MsgAuthAccessTokenRes> iResponseCallBack) {
        final String str4 = HuyaDeveloperUtils.getInstance().isDeveloper() ? "https://udbapi-test.huya.com/open/oauth/accessToken" : "https://udbapi.huya.com/open/oauth/accessToken";
        new Thread(new Runnable() { // from class: com.hysdkproxy.LoginHYProxy.46
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                HashMap map = new HashMap();
                map.put("client_id", str);
                map.put("client_secret", str2);
                map.put("code", str3);
                String strPost = HuyaWupUrlRequestUtil.post(str4, map);
                if (strPost.isEmpty()) {
                    iResponseCallBack.onResponse(new MsgAuthAccessTokenRes(-1, "null response", null));
                } else {
                    IResponseCallBack iResponseCallBack2 = iResponseCallBack;
                    iResponseCallBack2.onResponse(iResponseCallBack2.getFromDataString(strPost));
                }
            }
        }).start();
    }

    public void getAuthUserInfo(final String str, final String str2, final String str3, final String str4, final IResponseCallBack<MsgAuthGetUserInfoRes> iResponseCallBack) {
        final String str5 = HuyaDeveloperUtils.getInstance().isDeveloper() ? "https://udbapi-test.huya.com/open/oauth/userInfo" : "https://udbapi.huya.com/open/oauth/userInfo";
        new Thread(new Runnable() { // from class: com.hysdkproxy.LoginHYProxy.47
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                HashMap map = new HashMap();
                map.put("client_id", str);
                map.put("client_secret", str2);
                map.put(SqConstants.ACCESS_TOKEN, str3);
                map.put("open_id", str4);
                String strPost = HuyaWupUrlRequestUtil.post(str5, map);
                if (strPost.isEmpty()) {
                    iResponseCallBack.onResponse(new MsgAuthGetUserInfoRes(-1, "null response", null));
                } else {
                    IResponseCallBack iResponseCallBack2 = iResponseCallBack;
                    iResponseCallBack2.onResponse(iResponseCallBack2.getFromDataString(strPost));
                }
            }
        }).start();
    }

    public void getAuthAppInfo(Context context, final String str, final String str2, final String str3, final IResponseCallBack<MsgAuthAppInfoRes> iResponseCallBack) {
        final String str4 = HuyaDeveloperUtils.getInstance().isDeveloper() ? "https://udbapi-test.huya.com/open/oauth/appInfo" : "https://udbapi.huya.com/open/oauth/appInfo";
        new Thread(new Runnable() { // from class: com.hysdkproxy.LoginHYProxy.48
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                HashMap map = new HashMap();
                map.put("clientId", str);
                map.put(BillingClientConstants.PACKAGE_NAME, str2);
                map.put("signature", str3);
                map.put("encryptedData", HuyaAppUtils.getAuthEncryptedData(str, str2, str3));
                String strPost = HuyaWupUrlRequestUtil.post(str4, map);
                if (strPost.isEmpty()) {
                    iResponseCallBack.onResponse(new MsgAuthAppInfoRes(-1, "null response", null));
                } else {
                    IResponseCallBack iResponseCallBack2 = iResponseCallBack;
                    iResponseCallBack2.onResponse(iResponseCallBack2.getFromDataString(strPost));
                }
            }
        }).start();
    }

    public void getAuthLoginCode(final String str, final String str2, final IResponseCallBack<MsgAuthLoginCodeRes> iResponseCallBack) {
        final String str3 = HuyaDeveloperUtils.getInstance().isDeveloper() ? "https://udbapi-test.huya.com/open/oauth/inner/authorize" : "https://udbapi.huya.com/open/oauth/inner/authorize";
        new Thread(new Runnable() { // from class: com.hysdkproxy.LoginHYProxy.49
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                HashMap map = new HashMap();
                map.put("wupData", str);
                map.put("clientId", str2);
                String strPost = HuyaWupUrlRequestUtil.post(str3, map);
                if (strPost.isEmpty()) {
                    iResponseCallBack.onResponse(new MsgAuthLoginCodeRes(-1, "null response", null));
                } else {
                    IResponseCallBack iResponseCallBack2 = iResponseCallBack;
                    iResponseCallBack2.onResponse(iResponseCallBack2.getFromDataString(strPost));
                }
            }
        }).start();
    }

    public void openHuyaAuthLoginH5Webview(Activity activity, String str, final IResponseCallBack<MsgAuthLoginCodeRes> iResponseCallBack) {
        new HuyaWebviewDialog(activity, HuyaDeveloperUtils.getInstance().isDeveloper() ? "http://aq-test.huya.com/m/third_auth/third_auth.html" : "https://aq.huya.com/m/third_auth/third_auth.html", new AuthLoginWebviewBean(str, HuyaAppUtils.getPackageName(activity), HuyaAppUtils.getSignatureString(activity), HuyaAppUtils.getAuthEncryptedData(str, HuyaAppUtils.getPackageName(activity), HuyaAppUtils.getSignatureString(activity))), new IWebViewCallback() { // from class: com.hysdkproxy.LoginHYProxy.50
            @Override // com.huyaudb.webview.inter.IWebViewCallback
            public void resposneCallback(int i, String str2) {
                if (str2 != null && !str2.isEmpty()) {
                    IResponseCallBack iResponseCallBack2 = iResponseCallBack;
                    iResponseCallBack2.onResponse(iResponseCallBack2.getFromDataString(str2));
                } else {
                    iResponseCallBack.onResponse(new MsgAuthLoginCodeRes(i, "", new MsgAuthLoginCodeRes.DataBean()));
                }
            }
        }).show();
    }

    public void openHuyaAppAuthLogin(Activity activity, String str) {
        String str2 = "kiwi://auth?client_id=" + str + "&package_name=" + HuyaAppUtils.getPackageName(activity);
        List<ResolveInfo> listQueryIntentActivities = activity.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(str2)), 65536);
        if (listQueryIntentActivities.size() > 0) {
            if (listQueryIntentActivities.get(0) == null) {
                HuyaUdbLogUtils.e("resp-openHuyaAppAuthLogin", "kiwi-app cannot find!(-1)");
                return;
            }
            try {
                activity.startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(str2)), 100);
                return;
            } catch (Exception unused) {
                HuyaUdbLogUtils.e("resp-openHuyaAppAuthLogin", "jump kiwi-app error!(-3)");
                return;
            }
        }
        HuyaUdbLogUtils.e("resp-openHuyaAppAuthLogin", "kiwi-app cannot find!(-2)");
    }

    public void openHuyaUdbLoginStrategyWebview(Activity activity, String str, final IResponseCallBack<AuthLoginStrategyBean> iResponseCallBack) {
        if (str == null || str.length() == 0) {
            return;
        }
        new HuyaUdbLoginWebviewDialog(activity, new String(Base64.decode(str, 0)) + "&rq=1", new IWebViewCallback() { // from class: com.hysdkproxy.LoginHYProxy.51
            @Override // com.huyaudb.webview.inter.IWebViewCallback
            public void resposneCallback(int i, String str2) {
                if (str2 == null || str2.isEmpty()) {
                    return;
                }
                IResponseCallBack iResponseCallBack2 = iResponseCallBack;
                iResponseCallBack2.onResponse(iResponseCallBack2.getFromDataString(str2));
            }
        }).show();
    }

    public void sendBindEmailCode(long j, String str, final IResponseCallBack<HuyaSendBindEmailCodeRsp> iResponseCallBack) {
        HuyaAuth.getInstance().sendBindEmailCode(j, str, new HuyaAuthCallBack<HuyaSendBindEmailCodeRsp>(HuyaSendBindEmailCodeRsp.class) { // from class: com.hysdkproxy.LoginHYProxy.52
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaSendBindEmailCodeRsp huyaSendBindEmailCodeRsp) {
                HuyaUdbLogUtils.e("resp-sendBindEmailCode", huyaSendBindEmailCodeRsp);
                iResponseCallBack.onResponse(huyaSendBindEmailCodeRsp);
            }
        });
    }

    public void bindLoginEmail(long j, String str, String str2, String str3, String str4, final IResponseCallBack<HuyaBindLoginEmailRsp> iResponseCallBack) {
        HuyaAuth.getInstance().bindLoginEmail(j, str, str2, str3, str4, new HuyaAuthCallBack<HuyaBindLoginEmailRsp>(HuyaBindLoginEmailRsp.class) { // from class: com.hysdkproxy.LoginHYProxy.53
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaBindLoginEmailRsp huyaBindLoginEmailRsp) {
                HuyaUdbLogUtils.e("resp-bindLoginEmail", huyaBindLoginEmailRsp);
                iResponseCallBack.onResponse(huyaBindLoginEmailRsp);
            }
        });
    }

    public void emailLogin(String str, String str2, final IResponseCallBack<MsgLoginInfoRes> iResponseCallBack) {
        HuyaAuth.getInstance().emailLogin(str, str2, new HuyaAuthCallBack<ResLogin>(ResLogin.class) { // from class: com.hysdkproxy.LoginHYProxy.54
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResLogin resLogin) {
                HuyaUdbLogUtils.e("resp-emailLogin", resLogin);
                AuthEvent.LoginEvent loginEvent = new AuthEvent.LoginEvent();
                if (resLogin == null) {
                    loginEvent.errCode = -1;
                    loginEvent.uiAction = 1;
                } else {
                    loginEvent.description = resLogin.getHeader().getDescription();
                    if (resLogin.getHeader().getRet() == 10030 || resLogin.getHeader().getRet() == 0) {
                        if (resLogin.getHeader().getRet() == 10030) {
                            loginEvent.errCode = 0;
                            loginEvent.uiAction = 2;
                        }
                        LoginHYProxy.this.copyLogin(loginEvent, resLogin.getLoginData(), resLogin.getLoginStrategy());
                    } else {
                        loginEvent.errCode = resLogin.getHeader().getRet();
                        loginEvent.uiAction = 1;
                    }
                }
                ProxyEventHandlerEx.getInstance().handleLoginRegTrustInfo(loginEvent);
                if (loginEvent.uiAction == 1) {
                    LoginHYProxy.this.anonymousLogin();
                }
                iResponseCallBack.onResponse(new MsgLoginInfoRes(loginEvent, resLogin));
            }
        });
    }

    public ResGetTicket getTicket(long j, String str) {
        return HuyaAuth.getInstance().getTicket(j, str);
    }

    public void checkEmailIsReg(String str, final IResponseCallBack<HuyaEmailIsRegRes> iResponseCallBack) {
        HuyaAuth.getInstance().checkEmailIsReg(str, new HuyaAuthCallBack<HuyaEmailIsRegRes>(HuyaEmailIsRegRes.class) { // from class: com.hysdkproxy.LoginHYProxy.55
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaEmailIsRegRes huyaEmailIsRegRes) {
                HuyaUdbLogUtils.e("resp-sendBindEmailCode", huyaEmailIsRegRes);
                iResponseCallBack.onResponse(huyaEmailIsRegRes);
            }
        });
    }

    public void sendEmailCode(String str, int i, LoginProxy.EmailBizType emailBizType, final IResponseCallBack<HuyaEmailSendEmailCodeRsp> iResponseCallBack) {
        HuyaAuth.getInstance().sendEmailCode(str, i, emailBizType, new HuyaAuthCallBack<HuyaEmailSendEmailCodeRsp>(HuyaEmailSendEmailCodeRsp.class) { // from class: com.hysdkproxy.LoginHYProxy.56
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaEmailSendEmailCodeRsp huyaEmailSendEmailCodeRsp) {
                HuyaUdbLogUtils.e("resp-sendBindEmailCode", huyaEmailSendEmailCodeRsp);
                iResponseCallBack.onResponse(huyaEmailSendEmailCodeRsp);
            }
        });
    }

    public void sendEmailCodeByUid(long j, int i, LoginProxy.EmailBizType emailBizType, final IResponseCallBack<HuyaEmailSendEmailCodeRsp> iResponseCallBack) {
        HuyaAuth.getInstance().sendEmailCodeByUid(j, i, emailBizType, new HuyaAuthCallBack<HuyaEmailSendEmailCodeRsp>(HuyaEmailSendEmailCodeRsp.class) { // from class: com.hysdkproxy.LoginHYProxy.57
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaEmailSendEmailCodeRsp huyaEmailSendEmailCodeRsp) {
                HuyaUdbLogUtils.e("resp-sendEmailCodeByUid", huyaEmailSendEmailCodeRsp);
                iResponseCallBack.onResponse(huyaEmailSendEmailCodeRsp);
            }
        });
    }

    public void verifyEmailCode(String str, String str2, final IResponseCallBack<HuyaVerifyEmailCodeRsp> iResponseCallBack) {
        HuyaAuth.getInstance().verifyEmailCode(str, str2, new HuyaAuthCallBack<HuyaVerifyEmailCodeRsp>(HuyaVerifyEmailCodeRsp.class) { // from class: com.hysdkproxy.LoginHYProxy.58
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaVerifyEmailCodeRsp huyaVerifyEmailCodeRsp) {
                HuyaUdbLogUtils.e("resp-verifyEmailCode", huyaVerifyEmailCodeRsp);
                iResponseCallBack.onResponse(huyaVerifyEmailCodeRsp);
            }
        });
    }

    public void emailRegister(String str, String str2, String str3, String str4, final IResponseCallBack<HuyaEmailRegisterRsp> iResponseCallBack) {
        HuyaAuth.getInstance().emailRegister(str, str2, str3, str4, new HuyaAuthCallBack<HuyaEmailRegisterRsp>(HuyaEmailRegisterRsp.class) { // from class: com.hysdkproxy.LoginHYProxy.59
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaEmailRegisterRsp huyaEmailRegisterRsp) {
                HuyaUdbLogUtils.e("resp-emailRegister", huyaEmailRegisterRsp);
                iResponseCallBack.onResponse(huyaEmailRegisterRsp);
            }
        });
    }

    public void emailUnBindSendCode(long j, final IResponseCallBack<HuyaEmailUnBindSendCodeRsp> iResponseCallBack) {
        HuyaAuth.getInstance().emailUnBindSendCode(j, new HuyaAuthCallBack<HuyaEmailUnBindSendCodeRsp>(HuyaEmailUnBindSendCodeRsp.class) { // from class: com.hysdkproxy.LoginHYProxy.60
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaEmailUnBindSendCodeRsp huyaEmailUnBindSendCodeRsp) {
                HuyaUdbLogUtils.e("resp-emailUnBindSendCode", huyaEmailUnBindSendCodeRsp);
                iResponseCallBack.onResponse(huyaEmailUnBindSendCodeRsp);
            }
        });
    }

    public void emailUnBindVerifyCode(long j, String str, String str2, final IResponseCallBack<HuyaEmailUnBindVerifyCodeRsp> iResponseCallBack) {
        HuyaAuth.getInstance().emailUnBindVerifyCode(j, str, str2, new HuyaAuthCallBack<HuyaEmailUnBindVerifyCodeRsp>(HuyaEmailUnBindVerifyCodeRsp.class) { // from class: com.hysdkproxy.LoginHYProxy.61
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaEmailUnBindVerifyCodeRsp huyaEmailUnBindVerifyCodeRsp) {
                HuyaUdbLogUtils.e("resp-emailUnBindVerifyCode", huyaEmailUnBindVerifyCodeRsp);
                iResponseCallBack.onResponse(huyaEmailUnBindVerifyCodeRsp);
            }
        });
    }

    public void emailBindNewSendCode(long j, String str, String str2, final IResponseCallBack<HuyaEmailBindNewSendCodeRsp> iResponseCallBack) {
        HuyaAuth.getInstance().emailBindNewSendCode(j, str, str2, new HuyaAuthCallBack<HuyaEmailBindNewSendCodeRsp>(HuyaEmailBindNewSendCodeRsp.class) { // from class: com.hysdkproxy.LoginHYProxy.62
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaEmailBindNewSendCodeRsp huyaEmailBindNewSendCodeRsp) {
                HuyaUdbLogUtils.e("resp-emailBindNewSendCode", huyaEmailBindNewSendCodeRsp);
                iResponseCallBack.onResponse(huyaEmailBindNewSendCodeRsp);
            }
        });
    }

    public void emailBindNew(long j, String str, String str2, String str3, final IResponseCallBack<HuyaEmailBindNewRsp> iResponseCallBack) {
        HuyaAuth.getInstance().emailBindNew(j, str, str2, str3, new HuyaAuthCallBack<HuyaEmailBindNewRsp>(HuyaEmailBindNewRsp.class) { // from class: com.hysdkproxy.LoginHYProxy.63
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaEmailBindNewRsp huyaEmailBindNewRsp) {
                HuyaUdbLogUtils.e("resp-emailBindNew", huyaEmailBindNewRsp);
                iResponseCallBack.onResponse(huyaEmailBindNewRsp);
            }
        });
    }

    public void findPasswordByEmail(String str, String str2, String str3, String str4, final IResponseCallBack<HuyaFindPasswordByEmailRsp> iResponseCallBack) {
        HuyaAuth.getInstance().findPasswordByEmail(str, str2, str3, str4, new HuyaAuthCallBack<HuyaFindPasswordByEmailRsp>(HuyaFindPasswordByEmailRsp.class) { // from class: com.hysdkproxy.LoginHYProxy.64
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaFindPasswordByEmailRsp huyaFindPasswordByEmailRsp) {
                HuyaUdbLogUtils.e("resp-findPasswordByEmail", huyaFindPasswordByEmailRsp);
                iResponseCallBack.onResponse(huyaFindPasswordByEmailRsp);
            }
        });
    }

    public void changeEmailPassword(long j, String str, String str2, String str3, final IResponseCallBack<HuyaEmailChangePasswordRsp> iResponseCallBack) {
        HuyaAuth.getInstance().changeEmailPassword(j, str, str2, str3, new HuyaAuthCallBack<HuyaEmailChangePasswordRsp>(HuyaEmailChangePasswordRsp.class) { // from class: com.hysdkproxy.LoginHYProxy.65
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(HuyaEmailChangePasswordRsp huyaEmailChangePasswordRsp) {
                HuyaUdbLogUtils.e("resp-changeEmailPassword", huyaEmailChangePasswordRsp);
                iResponseCallBack.onResponse(huyaEmailChangePasswordRsp);
            }
        });
    }
}
