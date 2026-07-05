package com.hysdkproxy;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.duowan.live.common.utils.LoginUtils;
import com.huyaudbunify.BuildConfig;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.account.SdkComType;
import com.huyaudbunify.bean.ResBindAuth;
import com.huyaudbunify.bean.ResGetQUrl;
import com.huyaudbunify.bean.ResGetTicket;
import com.huyaudbunify.bean.ResGetUidFromUnionId;
import com.huyaudbunify.bean.ResHuyaLoginDataCookiesInfo;
import com.huyaudbunify.bean.ResHyName;
import com.huyaudbunify.bean.ResLogin;
import com.huyaudbunify.bean.ResShareAppLoginListData;
import com.huyaudbunify.bean.ThirdLoginOpenType;
import com.huyaudbunify.bean.ThirdLoginOption;
import com.huyaudbunify.bean.TrustUserInfo;
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
import com.huyaudbunify.dialog.msg.AuthLoginStrategyBean;
import com.huyaudbunify.handler.HYHandler;
import com.huyaudbunify.handler.ProxyEventHandlerEx;
import com.huyaudbunify.inter.HuyaAuthCallBack;
import com.huyaudbunify.inter.IHuyaAuthLogCallback;
import com.huyaudbunify.inter.IResponseCallBack;
import com.huyaudbunify.inter.ITrustInfoCallBack;
import com.huyaudbunify.msg.response.MsgAuthAccessTokenRes;
import com.huyaudbunify.msg.response.MsgAuthAppInfoRes;
import com.huyaudbunify.msg.response.MsgAuthGetUserInfoRes;
import com.huyaudbunify.msg.response.MsgAuthLoginCodeRes;
import com.huyaudbunify.msg.response.MsgLoginCredRes;
import com.huyaudbunify.msg.response.MsgLoginInfoRes;
import com.huyaudbunify.msg.response.MsgLoginPhoneSmsRes;
import com.huyaudbunify.msg.response.MsgLoginThirdRes;
import com.huyaudbunify.msg.response.MsgSendLoginPhoneSms;
import com.huyaudbunify.util.HuyaAccountSaveUtils;
import com.huyaudbunify.util.HuyaAccountUtils;
import com.huyaudbunify.util.HuyaDeveloperUtils;
import com.huyaudbunify.util.HuyaUrlUtil;
import com.sqwan.msdk.BaseSQwanCore;
import java.net.URLDecoder;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoginProxy {
    private static volatile LoginProxy mInstance;
    private static boolean mIsUserProtosdk;
    private long usernameStart = 0;
    private long passwordStart = 0;
    private long loginStartTime = 0;

    public enum EmailBizType {
        EmailBizType_REGISTER,
        EmailBizType_CHANGE_PASSWORD,
        EmailBizType_FIND_PASSWORD,
        EmailBizType_BIND_LOGIN_MOBILE,
        EmailBizType_UNBIND_LOGIN_MOBILE,
        EmailBizType_CHANGE_BIND_LOGIN_MOBILE
    }

    public static LoginProxy getInstance() {
        if (mInstance == null) {
            synchronized (LoginProxy.class) {
                if (mInstance == null) {
                    mInstance = new LoginProxy();
                }
            }
        }
        return mInstance;
    }

    public int getOpenType() {
        return HuyaAccountSaveUtils.getInstance().getThirdLgnOpenType();
    }

    public String getThirdLgnToken() {
        return HuyaAccountSaveUtils.getInstance().getThirdLgnToken();
    }

    public ThirdLoginOption getThirdLgnOption() {
        return HuyaAccountSaveUtils.getInstance().getThirdLgnOption();
    }

    public boolean isUserProtosdk() {
        return mIsUserProtosdk;
    }

    public void setRegTrustCallBack(ITrustInfoCallBack iTrustInfoCallBack) {
        HuyaAuth.getInstance().setTrustInfoCallBack(iTrustInfoCallBack);
    }

    public boolean isShowHuya() {
        if (HuyaAccountSaveUtils.getInstance().getType().isHY()) {
            return HuyaAccountSaveUtils.getInstance().isShowHuya();
        }
        return false;
    }

    public void setPre(boolean z) {
        HuyaAuth.getInstance().setPre(z);
    }

    public void setForbidLog(boolean z) {
        HuyaDeveloperUtils.getInstance().setForbidLog(z);
    }

    public boolean isSupportYYPay() {
        long uid = HuyaAccountSaveUtils.getInstance().getUid();
        return uid < 1099511627776L || uid > 2199023255551L;
    }

    public void setGuid(String str) {
        HuyaAccountSaveUtils.getInstance().setGuid(str);
    }

    public void setHuyaua(String str) {
        HuyaAccountSaveUtils.getInstance().setHuyaua(str);
    }

    public void setHome(Boolean bool) {
        HuyaAccountSaveUtils.getInstance().setHome(bool);
    }

    void setShowHuya(boolean z) {
        HuyaAccountSaveUtils.getInstance().setShowHuya(z);
    }

    public int getHyUdbByPass() {
        return HuyaAccountSaveUtils.getInstance().getType().getType();
    }

    public void setLogin(boolean z) {
        HuyaAccountSaveUtils.getInstance().setLogin(z);
    }

    public boolean isLogin() {
        return HuyaAccountSaveUtils.getInstance().isLogin();
    }

    public void init(Application application, String str) {
        LoginHYProxy.getInstance().init(application, str);
    }

    public void setDeveloper(boolean z) {
        HuyaAuth.getInstance().setDeveloper(z);
    }

    public void setDeviceId(String str) {
        HuyaAccountSaveUtils.getInstance().setDeviceId(str);
    }

    public void setLcid(String str) {
        HuyaAccountSaveUtils.getInstance().setLcid(str);
    }

    public boolean isDeveloper() {
        return HuyaDeveloperUtils.getInstance().isDeveloper();
    }

    public void setBypass(int i, int i2) {
        HuyaAccountSaveUtils.getInstance().setType(SdkComType.valueOf(i));
        HuyaAccountSaveUtils.getInstance().setDefaultType(SdkComType.valueOf(i2));
        HuyaAuth.getInstance().setByPass(i, i2);
    }

    public void addHandler(HYHandler hYHandler) {
        ProxyEventHandlerEx.getInstance().addHandler(hYHandler);
    }

    public void removeHandler(HYHandler hYHandler) {
        ProxyEventHandlerEx.getInstance().removeHandler(hYHandler);
    }

    public void regTrustInfo() {
        HuyaAuth.getInstance().regTrustInfo(10);
    }

    public TrustUserInfo getTrustInfo() {
        return HuyaAuth.getInstance().getTrustInfo();
    }

    @Deprecated
    public void loginPassport(String str, String str2, boolean z) {
        LoginHYProxy.getInstance().loginPassport(str, str2, z, "");
    }

    @Deprecated
    public void loginPassport(String str, String str2, boolean z, String str3) {
        LoginHYProxy.getInstance().loginPassport(str, str2, z, str3);
    }

    public void loginPassport(String str, String str2, boolean z, String str3, IResponseCallBack<MsgLoginInfoRes> iResponseCallBack) {
        LoginHYProxy.getInstance().loginPassport(str, str2, z, str3, iResponseCallBack);
    }

    public void loginStart(double d, double d2, String str) {
        HuyaAuth.getInstance().loginStart(d, d2, str);
        this.loginStartTime = System.currentTimeMillis();
    }

    public void loginUserAction(String str, int i, int i2) {
        HuyaAuth.getInstance().loginUserAction(str, i, i2, (int) (System.currentTimeMillis() - this.loginStartTime));
    }

    public void EnterLogin() {
        this.usernameStart = 0L;
        this.passwordStart = 0L;
        LoginHYProxy.getInstance().EnterLogin();
    }

    public void pushLoginOperation(String str, int i) {
        LoginHYProxy.getInstance().pushLoginOperation(str, i);
    }

    public void usernameStartTime() {
        this.usernameStart = System.currentTimeMillis() / 1000;
    }

    public void usernameEndTime() {
        pushLoginOperation(BaseSQwanCore.LOGIN_KEY_USERNAME, (int) ((System.currentTimeMillis() / 1000) - this.usernameStart));
    }

    public void passwordStartTime() {
        this.passwordStart = System.currentTimeMillis() / 1000;
    }

    public void passwordEndTime() {
        pushLoginOperation(LoginUtils.PASSWORD, (int) ((System.currentTimeMillis() / 1000) - this.passwordStart));
    }

    public void loginSecondAuth_sendsms(long j, String str) {
        LoginHYProxy.getInstance().loginSecondAuth_sendsms(j, str);
    }

    public void loginSecondAuth_sms(long j, String str) {
        LoginHYProxy.getInstance().loginSecondAuth_sms(j, str);
    }

    public void refreshPic(long j) {
        LoginHYProxy.getInstance().refreshPic(j);
    }

    public void loginSecondAuth_pic(long j, String str) {
        LoginHYProxy.getInstance().loginSecondAuth_pic(j, str);
    }

    public void mobileTokenLogin(long j, String str) {
        LoginHYProxy.getInstance().mobileTokenLogin(j, str);
    }

    public void checkUserRegister(String str) {
        LoginHYProxy.getInstance().checkUserRegister(str, "");
    }

    public void checkUserRegister(String str, String str2) {
        LoginHYProxy.getInstance().checkUserRegister(str, str2);
    }

    public void register_sendsms(String str) {
        LoginHYProxy.getInstance().register_sendsms(str, 0, "");
    }

    public void register_sendsms(String str, int i, String str2) {
        LoginHYProxy.getInstance().register_sendsms(str, i, str2);
    }

    public void register_sms(String str, String str2, String str3) {
        LoginHYProxy.getInstance().register_sms(str, str2, str3, "");
    }

    public void register_sms(String str, String str2, String str3, String str4) {
        LoginHYProxy.getInstance().register_sms(str, str2, str3, str4);
    }

    public void checkPwdCP(String str) {
        LoginHYProxy.getInstance().checkModPwdCP(str);
    }

    public void checkPwdFP(String str) {
        LoginHYProxy.getInstance().checkModPwdFP(str);
    }

    public void modPwd_sendSms(long j) {
        LoginHYProxy.getInstance().modPwd_sendSms(j, 0, "");
    }

    public void modPwd_sendSms(long j, int i, String str) {
        LoginHYProxy.getInstance().modPwd_sendSms(j, i, str);
    }

    public void modPwd_verifySms(long j, String str) {
        LoginHYProxy.getInstance().modPwd_verifySms(j, str, "");
    }

    public void modPwd_verifySms(long j, String str, String str2) {
        LoginHYProxy.getInstance().modPwd_verifySms(j, str, str2);
    }

    public void modPwd(long j, String str) {
        LoginHYProxy.getInstance().modPwd(j, str, "");
    }

    public void modPwd(long j, String str, String str2) {
        LoginHYProxy.getInstance().modPwd(j, str, str2);
    }

    public void findPwd_sendSms(String str) {
        LoginHYProxy.getInstance().findPwd_sendSms(str, 0, "");
    }

    public void findPwd_sendSms(String str, int i, String str2) {
        LoginHYProxy.getInstance().findPwd_sendSms(str, i, str2);
    }

    public void findPwd_verifySms(String str, String str2) {
        LoginHYProxy.getInstance().findPwd_verifySms(str, str2, "");
    }

    public void findPwd_verifySms(String str, String str2, String str3) {
        LoginHYProxy.getInstance().findPwd_verifySms(str, str2, str3);
    }

    public void findPwd(String str, String str2) {
        LoginHYProxy.getInstance().findPwd(str, str2, "");
    }

    public void findPwd(String str, String str2, String str3) {
        LoginHYProxy.getInstance().findPwd(str, str2, str3);
    }

    @Deprecated
    public void loginMobileQuick(int i, String str, boolean z) {
        LoginHYProxy.getInstance().loginMobileQuick(i, str, z, "");
    }

    @Deprecated
    public void loginMobileQuick(int i, String str, boolean z, String str2) {
        LoginHYProxy.getInstance().loginMobileQuick(i, str, z, str2);
    }

    public void loginMobileQuick(int i, String str, boolean z, String str2, IResponseCallBack<MsgLoginInfoRes> iResponseCallBack) {
        LoginHYProxy.getInstance().loginMobileQuick(i, str, z, str2, iResponseCallBack);
    }

    @Deprecated
    public void loginPhoneSms(String str, String str2, boolean z) {
        LoginHYProxy.getInstance().loginPhoneSms(str, str2, z, 0, "");
    }

    @Deprecated
    public void loginPhoneSms(String str, String str2, int i, boolean z, String str3) {
        LoginHYProxy.getInstance().loginPhoneSms(str, str2, z, i, str3);
    }

    public void loginPhoneSms(String str, String str2, int i, boolean z, String str3, IResponseCallBack<MsgLoginPhoneSmsRes> iResponseCallBack) {
        LoginHYProxy.getInstance().loginPhoneSms(str, str2, z, i, str3, iResponseCallBack);
    }

    @Deprecated
    public void sendLoginPhoneSms(String str) {
        LoginHYProxy.getInstance().sendLoginPhoneSms(str, 0, 0, "");
    }

    @Deprecated
    public void sendLoginPhoneSms(String str, int i, int i2, String str2) {
        LoginHYProxy.getInstance().sendLoginPhoneSms(str, i, i2, str2);
    }

    public void sendLoginPhoneSms(String str, int i, int i2, String str2, IResponseCallBack<MsgSendLoginPhoneSms> iResponseCallBack) {
        LoginHYProxy.getInstance().sendLoginPhoneSms(str, i, i2, str2, iResponseCallBack);
    }

    public void register_verifySms(String str, String str2) {
        LoginHYProxy.getInstance().register_verifySms(str, str2, "");
    }

    public void register_verifySms(String str, String str2, String str3) {
        LoginHYProxy.getInstance().register_verifySms(str, str2, str3);
    }

    public void register_token(String str, String str2) {
        LoginHYProxy.getInstance().register_token(str, str2, "");
    }

    public void register_token(String str, String str2, String str3) {
        LoginHYProxy.getInstance().register_token(str, str2, str3);
    }

    public ResGetTicket getToken(long j) {
        return LoginHYProxy.getInstance().getTicket(j, HuyaDeveloperUtils.getInstance().getAppId());
    }

    public ResGetTicket getDefaultToken() {
        return LoginHYProxy.getInstance().getTicket(0L, HuyaDeveloperUtils.getInstance().getAppId());
    }

    public boolean QRCodeConfirmReq(String str, String str2) {
        return HuyaAccountUtils.QRCodeConfirmReq(str, str2);
    }

    public boolean QRCodeCancelReq(String str, String str2) {
        return HuyaAccountUtils.QRCodeCancelReq(str, str2);
    }

    public boolean QRCodeCheckReq(String str, String str2) {
        return HuyaAccountUtils.QRCodeCheckReq(str, str2);
    }

    @Deprecated
    public void credLogin(long j, boolean z) {
        LoginHYProxy.getInstance().credLogin(j, "", "", z, "");
    }

    @Deprecated
    public void credLogin(long j, String str, String str2, boolean z, String str3) {
        LoginHYProxy.getInstance().credLogin(j, str, str2, z, str3);
    }

    public void credLogin(long j, String str, String str2, boolean z, String str3, IResponseCallBack<MsgLoginCredRes> iResponseCallBack) {
        LoginHYProxy.getInstance().credLogin(j, str, str2, z, str3, iResponseCallBack);
    }

    @Deprecated
    public void thirdLogin(int i, String str, ThirdLoginOption thirdLoginOption, String str2, boolean z) {
        saveThirdLoginParam(i, str, thirdLoginOption);
        LoginHYProxy.getInstance().thirdLogin(i, str, thirdLoginOption, str2, z, "");
    }

    @Deprecated
    public void thirdLogin(int i, String str, ThirdLoginOption thirdLoginOption, String str2, boolean z, String str3) {
        LoginHYProxy.getInstance().thirdLogin(i, str, thirdLoginOption, str2, z, str3);
    }

    public void thirdLogin(ThirdLoginOpenType thirdLoginOpenType, String str, ThirdLoginOption thirdLoginOption, String str2, boolean z, String str3, IResponseCallBack<MsgLoginThirdRes> iResponseCallBack) {
        LoginHYProxy.getInstance().thirdLogin(thirdLoginOpenType, str, thirdLoginOption, str2, z, str3, iResponseCallBack);
    }

    @Deprecated
    public void thirdLoginAuth(String str, boolean z) {
        thirdLogin(HuyaAccountSaveUtils.getInstance().getThirdLgnOpenType(), HuyaAccountSaveUtils.getInstance().getThirdLgnToken(), HuyaAccountSaveUtils.getInstance().getThirdLgnOption(), str, z);
    }

    @Deprecated
    void saveThirdLoginParam(int i, String str, ThirdLoginOption thirdLoginOption) {
        HuyaAccountSaveUtils.getInstance().setThirdLgnOpenType(i);
        HuyaAccountSaveUtils.getInstance().setThirdLgnToken(str);
        HuyaAccountSaveUtils.getInstance().setThirdLgnOption(thirdLoginOption);
    }

    public void loginOut() {
        HuyaAuth.getInstance().unRegTrustInfo(HuyaAccountSaveUtils.getInstance().getUid());
        LoginHYProxy.getInstance().loginOut();
    }

    public void loginHyAnonymouse() {
        LoginHYProxy.getInstance().anonymousLogin();
    }

    public String getH5Info() {
        return HuyaAuth.getInstance().getH5Info(HuyaAccountSaveUtils.getInstance().getUid(), "", HuyaAccountSaveUtils.getInstance().getPassport());
    }

    public String getH5InfoEx() {
        return HuyaAuth.getInstance().getH5InfoEx(HuyaAccountSaveUtils.getInstance().getUid());
    }

    public ResGetTicket getHyOtp() {
        return HuyaAuth.getInstance().getHyOtp();
    }

    public ResLogin loginH5Data(String str) {
        if (str.contains("%")) {
            try {
                str = URLDecoder.decode(str, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ResLogin resLoginDecodeH5Info = HuyaAuth.getInstance().decodeH5Info(str);
        if (resLoginDecodeH5Info == null) {
            return null;
        }
        String credit = "";
        if (BuildConfig.IS_HUYA_ACCOUNT.booleanValue()) {
            setBypass(3, HuyaAccountSaveUtils.getInstance().getType().getType());
            if (resLoginDecodeH5Info.getLoginData().getYyloginData() != null && resLoginDecodeH5Info.getLoginData().getYyloginData().getYyuid() != 0) {
                credit = resLoginDecodeH5Info.getLoginData().getYyloginData().getCredit();
            }
            String str2 = credit;
            String cred = resLoginDecodeH5Info.getLoginData().getApploginData() != null ? resLoginDecodeH5Info.getLoginData().getApploginData().getCred() : "";
            long yyuid = resLoginDecodeH5Info.getLoginData().getYyloginData().getYyuid();
            if (yyuid == 0) {
                yyuid = resLoginDecodeH5Info.getLoginData().getApploginData().getUid();
            }
            LoginHYProxy.getInstance().credLogin(yyuid, str2, cred, false, "");
        } else {
            LoginHYProxy.getInstance().credLogin(resLoginDecodeH5Info.getLoginData().getApploginData().getUid(), "", resLoginDecodeH5Info.getLoginData().getApploginData() != null ? resLoginDecodeH5Info.getLoginData().getApploginData().getCred() : "", false, "");
        }
        return resLoginDecodeH5Info;
    }

    public String getLgnJumpUrl(String str, String str2, String str3, String str4) {
        return HuyaUrlUtil.getHyLgnJumpUrl(str, str2, str3, str4);
    }

    public String getBusinessUrl(String str, String str2, String str3, String str4) {
        return HuyaUrlUtil.getHYBusinessUrl(str, str2, str3, str4);
    }

    public String getHYBusinessUrlParam(String str, String str2, String str3) {
        return HuyaUrlUtil.getHYBusinessUrlParam(str, str2, str3);
    }

    private String getHYBusinessUrl(String str, String str2, String str3, String str4) {
        return HuyaUrlUtil.getHYBusinessUrl(str, str2, str3, str4);
    }

    public boolean isBindMobile() {
        String mobileMask = HuyaAccountSaveUtils.getInstance().getMobileMask();
        return mobileMask != null && mobileMask.length() > 5;
    }

    public String getBindMobileUrl() {
        return isBindMobile() ? getHyChangeBindMobileUrl() : getHyBindMobileUrl();
    }

    public boolean isHuyaAccount() {
        return HuyaAccountSaveUtils.getInstance().getType().isHY();
    }

    public String getQUrlData(long j, String str, String str2) {
        ResGetQUrl qUrl = HuyaAuth.getInstance().getQUrl(j, HuyaAuth.getInstance().getOtpEx(j, 12, ""), str, str2);
        return qUrl == null ? "" : qUrl.getCommonInfo();
    }

    public String getOtpEx(long j) {
        return HuyaAuth.getInstance().getOtpEx(j, 12, "");
    }

    public void checkHyName(String str, HuyaAuthCallBack<ResHyName> huyaAuthCallBack) {
        HuyaAuth.getInstance().checkHyName(str, huyaAuthCallBack);
    }

    public void commitHyName(String str, HuyaAuthCallBack<ResHyName> huyaAuthCallBack) {
        HuyaAuth.getInstance().commitHyName(str, huyaAuthCallBack);
    }

    public void loginWithOtpSign(long j, String str, String str2, String str3) {
        loginWithOtpSign(j, str, str2, str3, "");
    }

    public void loginWithOtpSign(long j, String str, String str2, String str3, String str4) {
        LoginHYProxy.getInstance().loginWithOtpSign(j, str, str2, str3, str4);
    }

    public String getMigrateUrl(String str) {
        return !HuyaAccountSaveUtils.getInstance().getType().isHY() ? "" : HuyaUrlUtil.getMigrateUrl(str);
    }

    public String getUrlFromDes(String str) {
        return HuyaUrlUtil.getUrlFromDes(str);
    }

    private String getHyBindMobileUrl() {
        return HuyaUrlUtil.getHyBindMobileUrl();
    }

    private String getHyChangeBindMobileUrl() {
        return HuyaUrlUtil.getHyChangeBindMobileUrl();
    }

    public String getWeiguiUrl() {
        return HuyaUrlUtil.getHyWeiguiUrl();
    }

    public String getH5UrlRegister() {
        return HuyaUrlUtil.getH5UrlRegister();
    }

    public String getH5UrlFindPassword() {
        return HuyaUrlUtil.getH5UrlFindPassword();
    }

    public String getH5UrlModifyPassword() {
        return HuyaUrlUtil.getH5UrlModifyPassword();
    }

    public String getQUrlLink() {
        return HuyaUrlUtil.getQUrlLink();
    }

    public String getUpgradeUrl() {
        return HuyaUrlUtil.getUpgradeUrl(HuyaAccountSaveUtils.getInstance().getUid());
    }

    public String getAuthUrl() {
        return HuyaUrlUtil.getAuthUrl();
    }

    public void setLogCallBack(IHuyaAuthLogCallback iHuyaAuthLogCallback) {
        HuyaAuth.getInstance().setLogCallBack(iHuyaAuthLogCallback);
    }

    public void bindMobile_SendSms(long j, String str, int i) {
        LoginHYProxy.getInstance().bindMobile_SendSms(j, str, i, "");
    }

    public void bindMobile_SendSms(long j, String str, int i, String str2) {
        LoginHYProxy.getInstance().bindMobile_SendSms(j, str, i, str2);
    }

    public void bindMobile_VerifySms(long j, String str, String str2, String str3) {
        LoginHYProxy.getInstance().bindMobile_VerifySms(j, str, str2, str3, "");
    }

    public void bindMobile_VerifySms(long j, String str, String str2, String str3, String str4) {
        LoginHYProxy.getInstance().bindMobile_VerifySms(j, str, str2, str3, str4);
    }

    public void unBind_sendSms(long j, int i) {
        LoginHYProxy.getInstance().unBind_sendSms(j, i, "");
    }

    public void unBind_sendSms(long j, int i, String str) {
        LoginHYProxy.getInstance().unBind_sendSms(j, i, str);
    }

    public void unBind_verifySms(long j, String str) {
        LoginHYProxy.getInstance().unBind_verifySms(j, str, "");
    }

    public void unBind_verifySms(long j, String str, String str2) {
        LoginHYProxy.getInstance().unBind_verifySms(j, str, str2);
    }

    public void bindNew_sendSms(long j, String str, int i) {
        LoginHYProxy.getInstance().bindNew_sendSms(j, str, i, "");
    }

    public void bindNew_sendSms(long j, String str, int i, String str2) {
        LoginHYProxy.getInstance().bindNew_sendSms(j, str, i, str2);
    }

    public void bindNew_verifySms(long j, String str, String str2) {
        LoginHYProxy.getInstance().bindNew_verifySms(j, str, str2, "");
    }

    public void bindNew_verifySms(long j, String str, String str2, String str3) {
        LoginHYProxy.getInstance().bindNew_verifySms(j, str, str2, str3);
    }

    public String getUnionId(long j, String str) {
        return HuyaAuth.getInstance().getUnionId(j, str);
    }

    public ResGetUidFromUnionId getUidFromUnionId(String str) {
        return HuyaAuth.getInstance().getUidFromUnionId(str);
    }

    public void thridBindAuth(int i, String str, String str2, String str3, int i2, HuyaAuthCallBack<ResBindAuth> huyaAuthCallBack) {
        LoginHYProxy.getInstance().bindAuth(i, str, str2, str3, i2, huyaAuthCallBack);
    }

    public void setChannel(String str) {
        HuyaAccountSaveUtils.getInstance().setChannel(str);
    }

    public void setAppGroup(String str) {
        HuyaAccountSaveUtils.getInstance().setAppGroup(str);
    }

    public ResShareAppLoginListData getShareAppLoginList() {
        return HuyaAuth.getInstance().getShareAppLoginList();
    }

    public ResHuyaLoginDataCookiesInfo getLoginDataCookies(long j) {
        return HuyaAuth.getInstance().getLoginDataCookies(j);
    }

    public void reportCarrierInterfaceStatus(int i, JSONObject jSONObject) {
        HuyaAuth.getInstance().reportCarrierInterfaceStatus(i, jSONObject);
    }

    public void openHuyaAuthLoginH5Webview(Activity activity, String str, IResponseCallBack<MsgAuthLoginCodeRes> iResponseCallBack) {
        LoginHYProxy.getInstance().openHuyaAuthLoginH5Webview(activity, str, iResponseCallBack);
    }

    public void openHuyaAppAuthLogin(Activity activity, String str) {
        LoginHYProxy.getInstance().openHuyaAppAuthLogin(activity, str);
    }

    public void getAuthAppInfo(Context context, String str, String str2, String str3, IResponseCallBack<MsgAuthAppInfoRes> iResponseCallBack) {
        LoginHYProxy.getInstance().getAuthAppInfo(context, str, str2, str3, iResponseCallBack);
    }

    public String getAuthAppCommonData(long j) {
        return HuyaAuth.getInstance().getH5InfoEx(j);
    }

    public void getAuthLoginCode(String str, String str2, IResponseCallBack<MsgAuthLoginCodeRes> iResponseCallBack) {
        LoginHYProxy.getInstance().getAuthLoginCode(str, str2, iResponseCallBack);
    }

    public void getAuthAccessToken(String str, String str2, String str3, IResponseCallBack<MsgAuthAccessTokenRes> iResponseCallBack) {
        LoginHYProxy.getInstance().getAuthAccessToken(str, str2, str3, iResponseCallBack);
    }

    public void getAuthUserInfo(String str, String str2, String str3, String str4, IResponseCallBack<MsgAuthGetUserInfoRes> iResponseCallBack) {
        LoginHYProxy.getInstance().getAuthUserInfo(str, str2, str3, str4, iResponseCallBack);
    }

    public void openHuyaUdbLoginStrategyWebview(Activity activity, String str, IResponseCallBack<AuthLoginStrategyBean> iResponseCallBack) {
        LoginHYProxy.getInstance().openHuyaUdbLoginStrategyWebview(activity, str, iResponseCallBack);
    }

    public void checkEmailIsReg(String str, IResponseCallBack<HuyaEmailIsRegRes> iResponseCallBack) {
        LoginHYProxy.getInstance().checkEmailIsReg(str, iResponseCallBack);
    }

    public void sendEmailCode(String str, int i, EmailBizType emailBizType, IResponseCallBack<HuyaEmailSendEmailCodeRsp> iResponseCallBack) {
        LoginHYProxy.getInstance().sendEmailCode(str, i, emailBizType, iResponseCallBack);
    }

    public void sendEmailCodeByUid(long j, int i, EmailBizType emailBizType, IResponseCallBack<HuyaEmailSendEmailCodeRsp> iResponseCallBack) {
        LoginHYProxy.getInstance().sendEmailCodeByUid(j, i, emailBizType, iResponseCallBack);
    }

    public void verifyEmailCode(String str, String str2, IResponseCallBack<HuyaVerifyEmailCodeRsp> iResponseCallBack) {
        LoginHYProxy.getInstance().verifyEmailCode(str, str2, iResponseCallBack);
    }

    public void emailRegister(String str, String str2, String str3, String str4, IResponseCallBack<HuyaEmailRegisterRsp> iResponseCallBack) {
        LoginHYProxy.getInstance().emailRegister(str, str2, str3, str4, iResponseCallBack);
    }

    public void emailUnBindSendCode(long j, IResponseCallBack<HuyaEmailUnBindSendCodeRsp> iResponseCallBack) {
        LoginHYProxy.getInstance().emailUnBindSendCode(j, iResponseCallBack);
    }

    public void emailUnBindVerifyCode(long j, String str, String str2, IResponseCallBack<HuyaEmailUnBindVerifyCodeRsp> iResponseCallBack) {
        LoginHYProxy.getInstance().emailUnBindVerifyCode(j, str, str2, iResponseCallBack);
    }

    public void emailBindNewSendCode(long j, String str, String str2, IResponseCallBack<HuyaEmailBindNewSendCodeRsp> iResponseCallBack) {
        LoginHYProxy.getInstance().emailBindNewSendCode(j, str, str2, iResponseCallBack);
    }

    public void emailBindNew(long j, String str, String str2, String str3, IResponseCallBack<HuyaEmailBindNewRsp> iResponseCallBack) {
        LoginHYProxy.getInstance().emailBindNew(j, str, str2, str3, iResponseCallBack);
    }

    public void findPasswordByEmail(String str, String str2, String str3, String str4, IResponseCallBack<HuyaFindPasswordByEmailRsp> iResponseCallBack) {
        LoginHYProxy.getInstance().findPasswordByEmail(str, str2, str3, str4, iResponseCallBack);
    }

    public void changeEmailPassword(long j, String str, String str2, String str3, IResponseCallBack<HuyaEmailChangePasswordRsp> iResponseCallBack) {
        LoginHYProxy.getInstance().changeEmailPassword(j, str, str2, str3, iResponseCallBack);
    }

    public void sendBindEmailCode(long j, String str, IResponseCallBack<HuyaSendBindEmailCodeRsp> iResponseCallBack) {
        LoginHYProxy.getInstance().sendBindEmailCode(j, str, iResponseCallBack);
    }

    public void bindLoginEmail(long j, String str, String str2, String str3, String str4, IResponseCallBack<HuyaBindLoginEmailRsp> iResponseCallBack) {
        LoginHYProxy.getInstance().bindLoginEmail(j, str, str2, str3, str4, iResponseCallBack);
    }

    public void emailLogin(String str, String str2, IResponseCallBack<MsgLoginInfoRes> iResponseCallBack) {
        LoginHYProxy.getInstance().emailLogin(str, str2, iResponseCallBack);
    }

    public void bindChangeMobile_VerifySms(long j, String str, String str2) {
        LoginHYProxy.getInstance().bindChangeMobile_VerifySms(j, str, str2, "");
    }

    public void bindChangeMobile_VerifySms(long j, String str, String str2, String str3) {
        LoginHYProxy.getInstance().bindChangeMobile_VerifySms(j, str, str2, str3);
    }

    public void setDeviceInfo(String str, String str2) {
        HuyaAuth.getInstance().setDeviceInfo(str, str2);
    }

    public void setServantName(String str) {
        HuyaAccountSaveUtils.getInstance().setServantName(str);
    }

    public String getServantName() {
        return HuyaAccountSaveUtils.getInstance().getServantName();
    }

    public void loginGuest() {
        LoginHYProxy.getInstance().loginGuest("");
    }

    public void loginGuest(String str) {
        LoginHYProxy.getInstance().loginGuest(str);
    }
}
