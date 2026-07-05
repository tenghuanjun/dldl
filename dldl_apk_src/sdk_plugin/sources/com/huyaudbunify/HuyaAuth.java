package com.huyaudbunify;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.huya.berry.client.HuyaBerry;
import com.huya.mtp.hyns.api.NSVerifyApi;
import com.huya.security.DeviceFingerprintSDK;
import com.huya.security.SdidHandler;
import com.huyaudbunify.bean.AppLoginData;
import com.huyaudbunify.bean.ReqCheckHyName;
import com.huyaudbunify.bean.ResBindAuth;
import com.huyaudbunify.bean.ResBindNewSendSms;
import com.huyaudbunify.bean.ResBindNewVerifySms;
import com.huyaudbunify.bean.ResBindScanQr;
import com.huyaudbunify.bean.ResBindSendSms;
import com.huyaudbunify.bean.ResBindVerifySms;
import com.huyaudbunify.bean.ResCPCode;
import com.huyaudbunify.bean.ResCPSendSms;
import com.huyaudbunify.bean.ResCPToken;
import com.huyaudbunify.bean.ResCPVerifySms;
import com.huyaudbunify.bean.ResCancleQrLogin;
import com.huyaudbunify.bean.ResCheckRegMobile;
import com.huyaudbunify.bean.ResCheckUser;
import com.huyaudbunify.bean.ResDecodeVerifyHyTk;
import com.huyaudbunify.bean.ResDynamicCfg;
import com.huyaudbunify.bean.ResFPCode;
import com.huyaudbunify.bean.ResFPSendSms;
import com.huyaudbunify.bean.ResFPToken;
import com.huyaudbunify.bean.ResFPVerifySms;
import com.huyaudbunify.bean.ResGetBindList;
import com.huyaudbunify.bean.ResGetByPass;
import com.huyaudbunify.bean.ResGetByPassFromUid;
import com.huyaudbunify.bean.ResGetCred;
import com.huyaudbunify.bean.ResGetLoginTime;
import com.huyaudbunify.bean.ResGetQUrl;
import com.huyaudbunify.bean.ResGetTicket;
import com.huyaudbunify.bean.ResGetUidFromUnionId;
import com.huyaudbunify.bean.ResGetUnionId;
import com.huyaudbunify.bean.ResGetUserStatus;
import com.huyaudbunify.bean.ResHuyaLoginDataCookiesInfo;
import com.huyaudbunify.bean.ResHyName;
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
import com.huyaudbunify.bean.ResNotifyScanCode;
import com.huyaudbunify.bean.ResRegisterPhoneCode;
import com.huyaudbunify.bean.ResRegisterPhoneToken;
import com.huyaudbunify.bean.ResRegisterSendSms;
import com.huyaudbunify.bean.ResRegisterVerifySms;
import com.huyaudbunify.bean.ResShareAppLoginListData;
import com.huyaudbunify.bean.ResUnBindAuth;
import com.huyaudbunify.bean.ResUnBindSendSms;
import com.huyaudbunify.bean.ResUnBindVerifySms;
import com.huyaudbunify.bean.SdkVersionJsonData;
import com.huyaudbunify.bean.ThirdLoginOption;
import com.huyaudbunify.bean.TrustUserInfo;
import com.huyaudbunify.bean.UserAction;
import com.huyaudbunify.bean.UserOperation;
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
import com.huyaudbunify.inter.HuyaAuthCallBack;
import com.huyaudbunify.inter.IHuyaAuthLogCallback;
import com.huyaudbunify.inter.IIHttpCallBack;
import com.huyaudbunify.inter.IIHuyaAuthMgrCallBack;
import com.huyaudbunify.inter.IIRegTrustInfoCallBack;
import com.huyaudbunify.inter.ITrustInfoCallBack;
import com.huyaudbunify.msg.MsgAnonyLoginCred;
import com.huyaudbunify.msg.MsgAuthScanQr;
import com.huyaudbunify.msg.MsgBindAuth;
import com.huyaudbunify.msg.MsgBindBypassAndUid;
import com.huyaudbunify.msg.MsgBindChangePhone;
import com.huyaudbunify.msg.MsgBindLoginEmail;
import com.huyaudbunify.msg.MsgBindNewSendSms;
import com.huyaudbunify.msg.MsgBindNewVerifySms;
import com.huyaudbunify.msg.MsgBindScanQr;
import com.huyaudbunify.msg.MsgBindSendSms;
import com.huyaudbunify.msg.MsgBindVerifySms;
import com.huyaudbunify.msg.MsgCPCode;
import com.huyaudbunify.msg.MsgCPSendSms;
import com.huyaudbunify.msg.MsgCPToken;
import com.huyaudbunify.msg.MsgCPVerifySms;
import com.huyaudbunify.msg.MsgCancleAuthLogin;
import com.huyaudbunify.msg.MsgCancleQrLogin;
import com.huyaudbunify.msg.MsgCheckRegMobile;
import com.huyaudbunify.msg.MsgCheckUserCP;
import com.huyaudbunify.msg.MsgCheckUserFP;
import com.huyaudbunify.msg.MsgEmailLogin;
import com.huyaudbunify.msg.MsgFPCode;
import com.huyaudbunify.msg.MsgFPSendSms;
import com.huyaudbunify.msg.MsgFPToken;
import com.huyaudbunify.msg.MsgFPVerifySms;
import com.huyaudbunify.msg.MsgGetAppLoginDataCookies;
import com.huyaudbunify.msg.MsgGetBindList;
import com.huyaudbunify.msg.MsgGetByPass;
import com.huyaudbunify.msg.MsgGetBypassFromUid;
import com.huyaudbunify.msg.MsgGetCred;
import com.huyaudbunify.msg.MsgGetDynamicCfg;
import com.huyaudbunify.msg.MsgGetH5Info;
import com.huyaudbunify.msg.MsgGetH5InfoEx;
import com.huyaudbunify.msg.MsgGetLoginTime;
import com.huyaudbunify.msg.MsgGetOtpEx;
import com.huyaudbunify.msg.MsgGetQUrl;
import com.huyaudbunify.msg.MsgGetRegInfo;
import com.huyaudbunify.msg.MsgGetShareAppLogin;
import com.huyaudbunify.msg.MsgGetTicket;
import com.huyaudbunify.msg.MsgGetUidFromUnionId;
import com.huyaudbunify.msg.MsgGetUnVerifyHyTk;
import com.huyaudbunify.msg.MsgGetUnionId;
import com.huyaudbunify.msg.MsgGetUserStatus;
import com.huyaudbunify.msg.MsgGetVerifyHyTk;
import com.huyaudbunify.msg.MsgInit;
import com.huyaudbunify.msg.MsgKickOff;
import com.huyaudbunify.msg.MsgLogin;
import com.huyaudbunify.msg.MsgLoginAnonymous;
import com.huyaudbunify.msg.MsgLoginAntiViolent;
import com.huyaudbunify.msg.MsgLoginCred;
import com.huyaudbunify.msg.MsgLoginGuest;
import com.huyaudbunify.msg.MsgLoginMobileQuick;
import com.huyaudbunify.msg.MsgLoginOut;
import com.huyaudbunify.msg.MsgLoginPhoneSms;
import com.huyaudbunify.msg.MsgLoginRefreshPic;
import com.huyaudbunify.msg.MsgLoginSecondAuth;
import com.huyaudbunify.msg.MsgLoginSendMobileSms;
import com.huyaudbunify.msg.MsgLoginSessionSendSms;
import com.huyaudbunify.msg.MsgLoginThird;
import com.huyaudbunify.msg.MsgNewLogin;
import com.huyaudbunify.msg.MsgNotifyScanCode;
import com.huyaudbunify.msg.MsgOtherAppCredLogin;
import com.huyaudbunify.msg.MsgPushMessage;
import com.huyaudbunify.msg.MsgRegisterPhoneCode;
import com.huyaudbunify.msg.MsgRegisterPhoneToken;
import com.huyaudbunify.msg.MsgRegisterSendSms;
import com.huyaudbunify.msg.MsgRegisterVerifySms;
import com.huyaudbunify.msg.MsgReportLoginStatus;
import com.huyaudbunify.msg.MsgSendBindEmailCode;
import com.huyaudbunify.msg.MsgSetBypass;
import com.huyaudbunify.msg.MsgSetDeviceInfo;
import com.huyaudbunify.msg.MsgSetNetState;
import com.huyaudbunify.msg.MsgUnBindAuth;
import com.huyaudbunify.msg.MsgUnBindSendSms;
import com.huyaudbunify.msg.MsgUnBindVerifySms;
import com.huyaudbunify.msg.MsgUpdateCred;
import com.huyaudbunify.request.HuyaAuthMarsNetRequestUtil;
import com.huyaudbunify.request.HuyaAuthNetRequestUtil;
import com.huyaudbunify.util.HuyaAccountSaveUtils;
import com.huyaudbunify.util.HuyaCfgUtil;
import com.huyaudbunify.util.HuyaCommonUtil;
import com.huyaudbunify.util.HuyaDeveloperUtils;
import com.huyaudbunify.util.HuyaEventUtils;
import com.huyaudbunify.util.HuyaFileUtils;
import com.huyaudbunify.util.HuyaUdbLogUtils;
import com.huyaudbunify.util.HuyaUrlUtil;
import com.hysdkproxy.LoginProxy;
import com.sqwan.msdk.api.IMUrl;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaAuth {
    private static volatile HuyaAuth mInstance;
    public HuyaAuthMgr mHuyaAuthMgr;
    private ITrustInfoCallBack mTrustInfoCallBack;
    Application mApplication = null;
    public Handler mHandler = null;
    IHuyaAuthLogCallback logCallBack = null;
    private UserOperation operationLogin = null;
    private BroadcastReceiver myNetReceiver = new BroadcastReceiver() { // from class: com.huyaudbunify.HuyaAuth.11
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                if (!HuyaDeveloperUtils.getInstance().isForbidLog()) {
                    Log.i("udbauth", "BroadcastReceiver netstate change");
                }
                HuyaAuth.this.getCarrierType(new HuyaCommonUtil.GetCarrierTypeCallback() { // from class: com.huyaudbunify.HuyaAuth.11.1
                    @Override // com.huyaudbunify.util.HuyaCommonUtil.GetCarrierTypeCallback
                    public void callback(int i) {
                        HuyaAuth.this.setCarrierType(i);
                    }
                });
            }
        }
    };
    private Map<Long, HuyaAuthCallBack> mMapCallBack = new HashMap();

    public void changeEmailPassword(long j, String str, String str2, String str3, HuyaAuthCallBack<HuyaEmailChangePasswordRsp> huyaAuthCallBack) {
    }

    public void checkEmailIsReg(String str, HuyaAuthCallBack<HuyaEmailIsRegRes> huyaAuthCallBack) {
    }

    public void emailBindNew(long j, String str, String str2, String str3, HuyaAuthCallBack<HuyaEmailBindNewRsp> huyaAuthCallBack) {
    }

    public void emailBindNewSendCode(long j, String str, String str2, HuyaAuthCallBack<HuyaEmailBindNewSendCodeRsp> huyaAuthCallBack) {
    }

    public void emailRegister(String str, String str2, String str3, String str4, HuyaAuthCallBack<HuyaEmailRegisterRsp> huyaAuthCallBack) {
    }

    public void emailUnBindSendCode(long j, HuyaAuthCallBack<HuyaEmailUnBindSendCodeRsp> huyaAuthCallBack) {
    }

    public void emailUnBindVerifyCode(long j, String str, String str2, HuyaAuthCallBack<HuyaEmailUnBindVerifyCodeRsp> huyaAuthCallBack) {
    }

    public void findPasswordByEmail(String str, String str2, String str3, String str4, HuyaAuthCallBack<HuyaFindPasswordByEmailRsp> huyaAuthCallBack) {
    }

    public void sendEmailCode(String str, int i, LoginProxy.EmailBizType emailBizType, HuyaAuthCallBack<HuyaEmailSendEmailCodeRsp> huyaAuthCallBack) {
    }

    public void sendEmailCodeByUid(long j, int i, LoginProxy.EmailBizType emailBizType, HuyaAuthCallBack<HuyaEmailSendEmailCodeRsp> huyaAuthCallBack) {
    }

    public void verifyEmailCode(String str, String str2, HuyaAuthCallBack<HuyaVerifyEmailCodeRsp> huyaAuthCallBack) {
    }

    public void setLogCallBack(IHuyaAuthLogCallback iHuyaAuthLogCallback) {
        this.logCallBack = iHuyaAuthLogCallback;
    }

    public void setTerminalType(int i) {
        HuyaAccountSaveUtils.getInstance().setTerminalType(i);
    }

    public String getLcid() {
        return HuyaAccountSaveUtils.getInstance().getLcid();
    }

    public void setLcid(String str) {
        HuyaAccountSaveUtils.getInstance().setLcid(str);
    }

    public void setPre(boolean z) {
        HuyaDeveloperUtils.getInstance().setIsPre(z ? 1 : 0);
        if (z) {
            setDeveloper(false);
        }
    }

    public boolean isDeveloper() {
        return HuyaDeveloperUtils.getInstance().isDeveloper();
    }

    public void setDeveloper(boolean z) {
        HuyaDeveloperUtils.getInstance().setDeveloper(z);
        if (z) {
            setPre(false);
        }
    }

    public void setIsTcp(boolean z) {
        HuyaDeveloperUtils.getInstance().setTcp(z);
        if (z) {
            setNetType(1);
        } else {
            setNetType(2);
        }
    }

    public void setUserMars(boolean z) {
        HuyaDeveloperUtils.getInstance().setUseMars(z);
        HuyaDeveloperUtils.getInstance().setNetType(z ? 3 : 2);
    }

    public void setCarrierType(int i) {
        HuyaDeveloperUtils.getInstance().setCarrierType(i);
        setNetState();
    }

    public int getNetType() {
        return HuyaDeveloperUtils.getInstance().getNetType();
    }

    public void setNetType(int i) {
        HuyaDeveloperUtils.getInstance().setNetType(i);
        setNetState();
    }

    public static HuyaAuth getInstance() {
        if (mInstance == null) {
            synchronized (HuyaAuth.class) {
                if (mInstance == null) {
                    mInstance = new HuyaAuth();
                }
            }
        }
        return mInstance;
    }

    public HuyaAuth() {
        this.mHuyaAuthMgr = null;
        this.mHuyaAuthMgr = new HuyaAuthMgr();
        this.mHuyaAuthMgr.setCallback(new IIHuyaAuthMgrCallBack() { // from class: com.huyaudbunify.HuyaAuth.1
            @Override // com.huyaudbunify.inter.IIHuyaAuthMgrCallBack
            public void onReceiveCallBack(long j, final String str) {
                if (HuyaAuth.this.mHandler == null) {
                    return;
                }
                final HuyaAuthCallBack huyaAuthCallBack = (HuyaAuthCallBack) HuyaAuth.this.mMapCallBack.get(Long.valueOf(j));
                HuyaAuth.this.mHandler.post(new Runnable() { // from class: com.huyaudbunify.HuyaAuth.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HuyaAuthCallBack huyaAuthCallBack2 = huyaAuthCallBack;
                        if (huyaAuthCallBack2 != null) {
                            huyaAuthCallBack2.hyCallBackData(str);
                        }
                    }
                });
            }

            @Override // com.huyaudbunify.inter.IIHuyaAuthMgrCallBack
            public void log(final String str) {
                if (HuyaAuth.this.mHandler == null || HuyaAuth.this.logCallBack == null) {
                    return;
                }
                HuyaAuth.this.mHandler.post(new Runnable() { // from class: com.huyaudbunify.HuyaAuth.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (HuyaAuth.this.logCallBack != null) {
                            HuyaAuth.this.logCallBack.log(str);
                        }
                    }
                });
            }
        });
    }

    public void setIsHome(Boolean bool) {
        HuyaAccountSaveUtils.getInstance().setHome(bool);
    }

    public void init(Application application, String str, HuyaAuthCallBack<ResInit> huyaAuthCallBack) {
        int i;
        String str2;
        this.mHuyaAuthMgr.init(application);
        this.mApplication = application;
        this.mHandler = new Handler();
        if (BuildConfig.IS_HUYA_ACCOUNT.booleanValue()) {
            i = 0;
        } else if (HuyaAccountSaveUtils.getInstance().isHome().booleanValue()) {
            HuyaUrlUtil.constUrlLog = "http://udblog.huya.com";
            HuyaUrlUtil.constUrlLogDev = "http://udblog.huya.com";
            HuyaUrlUtil.constServName = "huyaudbdomesticwebui";
            i = 1;
        } else {
            DeviceFingerprintSDK.host = "https://udbdf-v2.nimo.tv";
            i = 0;
        }
        MsgInit msgInit = new MsgInit();
        String channel = HuyaAccountSaveUtils.getInstance().getChannel();
        if (TextUtils.isEmpty(channel)) {
            channel = HuyaCfgUtil.getChannel(application);
        }
        String appId = HuyaCfgUtil.getAppId(application);
        String appKey = HuyaCfgUtil.getAppKey(application);
        String deviceName = HuyaCfgUtil.getDeviceName();
        String versionCode = HuyaCfgUtil.getVersionCode(application);
        String systemVersion = HuyaCfgUtil.getSystemVersion();
        int[] iArrDisplayMetrics = HuyaCfgUtil.DisplayMetrics(application);
        int i2 = iArrDisplayMetrics[0];
        int i3 = iArrDisplayMetrics[1];
        HuyaDeveloperUtils.getInstance().setAppId(appId);
        File file = new File(application.getFilesDir(), "huyaudb");
        file.mkdirs();
        String absolutePath = file.getAbsolutePath();
        if (HuyaAccountSaveUtils.getInstance().getDeviceId().isEmpty()) {
            HuyaAccountSaveUtils.getInstance().setDeviceId(DeviceFingerprintSDK.getInstance().getCDID());
        }
        if (HuyaFileUtils.existSDCard()) {
            str2 = HuyaFileUtils.getSDCardRootDir().getAbsolutePath() + "/Android/data/com.duowan.kiwi/files";
            if (!HuyaFileUtils.isExist(str2)) {
                HuyaFileUtils.mkdir(str2);
            }
        } else {
            str2 = "";
        }
        msgInit.getData().setAppId(appId);
        msgInit.getData().setAppkey(appKey);
        msgInit.getData().setAppVer(versionCode);
        msgInit.getData().setChannel(channel);
        msgInit.getData().setDeviceId(HuyaAccountSaveUtils.getInstance().getDeviceId());
        msgInit.getData().setDeviceName(deviceName);
        msgInit.getData().setHeightPixels(String.valueOf(i3));
        msgInit.getData().setWidthPixels(String.valueOf(i2));
        msgInit.getData().setSavePath(absolutePath);
        msgInit.getData().setAppGroup(HuyaAccountSaveUtils.getInstance().getAppGroup());
        msgInit.getData().setSystemInfo(IMUrl.OS);
        msgInit.getData().setShareAppDataPath(str2);
        msgInit.getData().setSystemVer(systemVersion);
        msgInit.getData().setIsPre(HuyaDeveloperUtils.getInstance().getIsPre());
        msgInit.getData().setOpenAppId(HuyaAccountSaveUtils.getInstance().getHuyaua());
        msgInit.getData().setIsForbidLog(HuyaDeveloperUtils.getInstance().isForbidLog() ? 1 : 0);
        msgInit.getData().setTerminalType(HuyaAccountSaveUtils.getInstance().getTerminalType());
        if (!BuildConfig.IS_HUYA_ACCOUNT.booleanValue()) {
            msgInit.getData().setLcid(HuyaAccountSaveUtils.getInstance().getLcid());
            msgInit.getData().setIsHome(i);
            msgInit.getData().setServantName(HuyaAccountSaveUtils.getInstance().getServantName());
            msgInit.getData().setSdkVer(new Gson().toJson(new SdkVersionJsonData(BuildConfig.SDKVersion, 2)));
        }
        String string = msgInit.toString();
        this.mMapCallBack.put(Long.valueOf(MsgInit.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgInit.mMsgId, string);
        HuyaAuthMarsNetRequestUtil.getInstance().init();
        initBroadcasetReceiver();
        initSDID();
    }

    public void initSDID() {
        String sdid = DeviceFingerprintSDK.getInstance().getSDID();
        if (sdid != null) {
            setSafeDeviceId(sdid);
        }
        DeviceFingerprintSDK.getInstance().addSdidHandler(new SdidHandler() { // from class: com.huyaudbunify.HuyaAuth.2
            @Override // com.huya.security.SdidHandler
            public void onSdid(String str) {
                if (str != null) {
                    HuyaAuth.this.setSafeDeviceId(str);
                }
            }
        });
    }

    public void login(String str, String str2, boolean z, List<String> list, HuyaAuthCallBack<ResLogin> huyaAuthCallBack) {
        MsgLogin msgLogin = new MsgLogin();
        msgLogin.getData().setName(str);
        msgLogin.getData().setPassword(str2);
        msgLogin.getData().setAuthLogin(Boolean.valueOf(z));
        if (BuildConfig.IS_HUYA_ACCOUNT.booleanValue()) {
            msgLogin.getData().setUserAction(getLoginUserAction());
        } else {
            msgLogin.getData().setLgnExtParam(getOperationLogin());
        }
        if (list != null) {
            msgLogin.getData().setBizAppids(list);
        }
        String string = msgLogin.toString();
        this.mMapCallBack.put(Long.valueOf(MsgLogin.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgLogin.mMsgId, string);
    }

    public void EnterLogin() {
        if (this.operationLogin == null) {
            this.operationLogin = new UserOperation();
        }
    }

    public void pushLoginOperation(String str, int i) {
        UserOperation userOperation = this.operationLogin;
        if (userOperation != null) {
            userOperation.getRecord().put(str, Integer.valueOf(i));
        }
    }

    public Map<String, String> getOperationLogin() {
        if (this.operationLogin == null) {
            EnterLogin();
        }
        HashMap map = new HashMap();
        map.put("longitude", this.operationLogin.getLongitude() + "");
        map.put("latitude", this.operationLogin.getLatitude() + "");
        map.put("ssid", this.operationLogin.getSsid());
        map.put("user_event_paths", new Gson().toJson(this.operationLogin.getRecord()));
        return map;
    }

    public void loginStart(double d, double d2, String str) {
        HuyaAccountSaveUtils.getInstance().setUserAction(new UserAction(d + "", d2 + "", str + ""));
    }

    public void loginUserAction(String str, int i, int i2, int i3) {
        if (HuyaAccountSaveUtils.getInstance().getUserAction() != null) {
            if (HuyaAccountSaveUtils.getInstance().getUserAction().getUser_action() == null) {
                HuyaAccountSaveUtils.getInstance().getUserAction().setUser_action(new ArrayList());
            }
            HuyaAccountSaveUtils.getInstance().getUserAction().getUser_action().add(new UserAction.UserActionBean(str, i + "", i2 + "", i3 + ""));
        }
    }

    public String getLoginUserAction() {
        try {
            return HuyaAccountSaveUtils.getInstance().getUserAction() != null ? new Gson().toJson(HuyaAccountSaveUtils.getInstance().getUserAction()) : "";
        } catch (Exception unused) {
            return "";
        }
    }

    public void loginThird(String str, int i, String str2, ThirdLoginOption thirdLoginOption, String str3, boolean z, List<String> list, HuyaAuthCallBack<ResLoginThird> huyaAuthCallBack) {
        MsgLoginThird msgLoginThird = new MsgLoginThird();
        msgLoginThird.getData().setOpenId(str);
        msgLoginThird.getData().setOpenType(i);
        msgLoginThird.getData().setToken(str2);
        msgLoginThird.getData().setUdbcode(str3);
        msgLoginThird.getData().setUserAction(getLoginUserAction());
        msgLoginThird.getData().setAuthLogin(z);
        if (list != null) {
            msgLoginThird.getData().setBizAppids(list);
        }
        if (thirdLoginOption != null) {
            if (!TextUtils.isEmpty(thirdLoginOption.channel)) {
                msgLoginThird.getData().setChannel(thirdLoginOption.channel);
            }
            if (!TextUtils.isEmpty(thirdLoginOption.thirdAppkey)) {
                msgLoginThird.getData().setThirdAppkey(thirdLoginOption.thirdAppkey);
            }
            if (!TextUtils.isEmpty(thirdLoginOption.oauthUrl)) {
                msgLoginThird.getData().setOauthUrl(thirdLoginOption.oauthUrl);
            }
            if (!TextUtils.isEmpty(thirdLoginOption.tokenSecret)) {
                msgLoginThird.getData().setTokenSecret(thirdLoginOption.tokenSecret);
            }
            if (!TextUtils.isEmpty(thirdLoginOption.oauthType)) {
                msgLoginThird.getData().setOauthType(thirdLoginOption.oauthType);
            }
            if (thirdLoginOption.improve == 1) {
                msgLoginThird.getData().setImprove(1);
            } else {
                msgLoginThird.getData().setImprove(0);
            }
            if (!TextUtils.isEmpty(thirdLoginOption.bizData)) {
                msgLoginThird.getData().setBizData(thirdLoginOption.bizData);
            }
            if (!TextUtils.isEmpty(thirdLoginOption.appTerminalType)) {
                msgLoginThird.getData().setAppTerminalType(thirdLoginOption.appTerminalType);
            }
        }
        String string = msgLoginThird.toString();
        this.mMapCallBack.put(Long.valueOf(MsgLoginThird.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgLoginThird.mMsgId, string);
    }

    public void loginAntiViolent(long j, String str, List<String> list, HuyaAuthCallBack<ResLoginAntiViolent> huyaAuthCallBack) {
        MsgLoginAntiViolent msgLoginAntiViolent = new MsgLoginAntiViolent();
        msgLoginAntiViolent.getData().setUid(j);
        msgLoginAntiViolent.getData().setViolentToken(str);
        if (list != null) {
            msgLoginAntiViolent.getData().setBizAppids(list);
        }
        String string = msgLoginAntiViolent.toString();
        this.mMapCallBack.put(Long.valueOf(MsgLoginAntiViolent.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgLoginAntiViolent.mMsgId, string);
    }

    public void loginAntiViolent(long j, String str, HuyaAuthCallBack<ResLoginAntiViolent> huyaAuthCallBack) {
        loginAntiViolent(j, str, null, huyaAuthCallBack);
    }

    public void refreshLoginPic(long j, HuyaAuthCallBack<ResLoginRefreshPic> huyaAuthCallBack) {
        MsgLoginRefreshPic msgLoginRefreshPic = new MsgLoginRefreshPic();
        msgLoginRefreshPic.getData().setUid(j);
        String string = msgLoginRefreshPic.toString();
        this.mMapCallBack.put(Long.valueOf(MsgLoginRefreshPic.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgLoginRefreshPic.mMsgId, string);
    }

    public void sendLoginPhoneSms(String str, int i, int i2, HuyaAuthCallBack<ResLoginMobileSendSms> huyaAuthCallBack) {
        MsgLoginSendMobileSms msgLoginSendMobileSms = new MsgLoginSendMobileSms();
        msgLoginSendMobileSms.getData().setMobile(str);
        if (BuildConfig.IS_HUYA_ACCOUNT.booleanValue()) {
            msgLoginSendMobileSms.getData().setUserAction(getLoginUserAction());
        } else {
            msgLoginSendMobileSms.getData().setCodeDigit(i2);
            msgLoginSendMobileSms.getData().setDeliverType(i);
        }
        String string = msgLoginSendMobileSms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgLoginSendMobileSms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgLoginSendMobileSms.mMsgId, string);
    }

    public void loginPhoneSms(String str, String str2, boolean z, int i, HuyaAuthCallBack<ResLoginPhoneSms> huyaAuthCallBack) {
        MsgLoginPhoneSms msgLoginPhoneSms = new MsgLoginPhoneSms();
        msgLoginPhoneSms.getData().setMobile(str);
        msgLoginPhoneSms.getData().setSmscode(str2);
        msgLoginPhoneSms.getData().setAuthLogin(Boolean.valueOf(z));
        if (BuildConfig.IS_HUYA_ACCOUNT.booleanValue()) {
            msgLoginPhoneSms.getData().setUserAction(getLoginUserAction());
        } else {
            msgLoginPhoneSms.getData().setImprove(i);
        }
        String string = msgLoginPhoneSms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgLoginPhoneSms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgLoginPhoneSms.mMsgId, string);
    }

    public void login(String str, String str2, boolean z, HuyaAuthCallBack<ResLogin> huyaAuthCallBack) {
        login(str, str2, z, null, huyaAuthCallBack);
    }

    public void loginAnonymous(HuyaAuthCallBack<ResLoginAnonymous> huyaAuthCallBack) {
        String string = new MsgLoginAnonymous().toString();
        this.mMapCallBack.put(Long.valueOf(MsgLoginAnonymous.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgLoginAnonymous.mMsgId, string);
    }

    public void loginOut() {
        new MsgLoginOut();
        this.mHuyaAuthMgr.sendMessage(MsgLoginOut.mMsgId, "");
    }

    public void loginGuest(List<String> list, HuyaAuthCallBack<ResLoginGuest> huyaAuthCallBack) {
        MsgLoginGuest msgLoginGuest = new MsgLoginGuest();
        if (list != null) {
            msgLoginGuest.getData().setBizAppids(list);
        }
        String string = msgLoginGuest.toString();
        this.mMapCallBack.put(Long.valueOf(MsgLoginGuest.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgLoginGuest.mMsgId, string);
    }

    public void loginCred(long j, String str, String str2, boolean z, boolean z2, List<String> list, HuyaAuthCallBack<ResLoginCred> huyaAuthCallBack) {
        MsgLoginCred msgLoginCred = new MsgLoginCred();
        msgLoginCred.getData().setUid(j);
        msgLoginCred.getData().setHyCred(str);
        msgLoginCred.getData().setYyCred(str2);
        msgLoginCred.getData().setAuthLogin(z);
        msgLoginCred.getData().setStillRequest(z2);
        if (list != null) {
            msgLoginCred.getData().setBizAppids(list);
        }
        String string = msgLoginCred.toString();
        this.mMapCallBack.put(Long.valueOf(MsgLoginCred.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgLoginCred.mMsgId, string);
    }

    public void loginCred(long j, String str, String str2, boolean z, boolean z2, HuyaAuthCallBack<ResLoginCred> huyaAuthCallBack) {
        loginCred(j, str, str2, z, z2, null, huyaAuthCallBack);
    }

    public void loginAnonyCred(long j, String str, HuyaAuthCallBack<ResLoginCred> huyaAuthCallBack) {
        MsgAnonyLoginCred msgAnonyLoginCred = new MsgAnonyLoginCred();
        msgAnonyLoginCred.getData().setUid(j);
        msgAnonyLoginCred.getData().setHyCred(str);
        String string = msgAnonyLoginCred.toString();
        this.mMapCallBack.put(Long.valueOf(MsgAnonyLoginCred.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgAnonyLoginCred.mMsgId, string);
    }

    public void sendLoginSessionSms(long j, HuyaAuthCallBack<ResLoginSessionSendSms> huyaAuthCallBack) {
        MsgLoginSessionSendSms msgLoginSessionSendSms = new MsgLoginSessionSendSms();
        msgLoginSessionSendSms.getData().setUid(j);
        String string = msgLoginSessionSendSms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgLoginSessionSendSms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgLoginSessionSendSms.mMsgId, string);
    }

    public void loginSecondAuth(long j, int i, String str, List<String> list, HuyaAuthCallBack<ResLoginSecondAuth> huyaAuthCallBack) {
        MsgLoginSecondAuth msgLoginSecondAuth = new MsgLoginSecondAuth();
        msgLoginSecondAuth.getData().setUid(j);
        msgLoginSecondAuth.getData().setSecondToken(str);
        msgLoginSecondAuth.getData().setStrategy(i);
        if (list != null) {
            msgLoginSecondAuth.getData().setBizAppids(list);
        }
        String string = msgLoginSecondAuth.toString();
        this.mMapCallBack.put(Long.valueOf(MsgLoginSecondAuth.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgLoginSecondAuth.mMsgId, string);
    }

    public void loginSecondAuth(long j, int i, String str, HuyaAuthCallBack<ResLoginSecondAuth> huyaAuthCallBack) {
        loginSecondAuth(j, i, str, null, huyaAuthCallBack);
    }

    public void sendRegSmsCode(String str, int i, HuyaAuthCallBack<ResRegisterSendSms> huyaAuthCallBack) {
        MsgRegisterSendSms msgRegisterSendSms = new MsgRegisterSendSms();
        msgRegisterSendSms.getData().setMobile(str);
        msgRegisterSendSms.getData().setDeliverType(i);
        String string = msgRegisterSendSms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgRegisterSendSms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgRegisterSendSms.mMsgId, string);
    }

    public void verifyRegSmsCode(String str, String str2, HuyaAuthCallBack<ResRegisterVerifySms> huyaAuthCallBack) {
        MsgRegisterVerifySms msgRegisterVerifySms = new MsgRegisterVerifySms();
        msgRegisterVerifySms.getData().setMobile(str);
        msgRegisterVerifySms.getData().setSmscode(str2);
        String string = msgRegisterVerifySms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgRegisterVerifySms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgRegisterVerifySms.mMsgId, string);
    }

    public void registerPhoneWithCode(String str, String str2, String str3, List<String> list, HuyaAuthCallBack<ResRegisterPhoneCode> huyaAuthCallBack) {
        MsgRegisterPhoneCode msgRegisterPhoneCode = new MsgRegisterPhoneCode();
        msgRegisterPhoneCode.getData().setMobile(str);
        msgRegisterPhoneCode.getData().setSmscode(str2);
        msgRegisterPhoneCode.getData().setPassword(str3);
        if (list != null) {
            msgRegisterPhoneCode.getData().setBizAppids(list);
        }
        String string = msgRegisterPhoneCode.toString();
        this.mMapCallBack.put(Long.valueOf(MsgRegisterPhoneCode.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgRegisterPhoneCode.mMsgId, string);
    }

    public void registerPhoneWithCode(String str, String str2, String str3, HuyaAuthCallBack<ResRegisterPhoneCode> huyaAuthCallBack) {
        registerPhoneWithCode(str, str2, str3, null, huyaAuthCallBack);
    }

    public void registerPhoneWithToken(String str, String str2, List<String> list, HuyaAuthCallBack<ResRegisterPhoneToken> huyaAuthCallBack) {
        MsgRegisterPhoneToken msgRegisterPhoneToken = new MsgRegisterPhoneToken();
        msgRegisterPhoneToken.getData().setMobile(str);
        msgRegisterPhoneToken.getData().setPassword(str2);
        if (list != null) {
            msgRegisterPhoneToken.getData().setBizAppids(list);
        }
        String string = msgRegisterPhoneToken.toString();
        this.mMapCallBack.put(Long.valueOf(MsgRegisterPhoneToken.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgRegisterPhoneToken.mMsgId, string);
    }

    public void registerPhoneWithToken(String str, String str2, HuyaAuthCallBack<ResRegisterPhoneToken> huyaAuthCallBack) {
        registerPhoneWithToken(str, str2, null, huyaAuthCallBack);
    }

    public void sendFindPasswordSmsCode(String str, int i, HuyaAuthCallBack<ResFPSendSms> huyaAuthCallBack) {
        MsgFPSendSms msgFPSendSms = new MsgFPSendSms();
        msgFPSendSms.getData().setMobile(str);
        msgFPSendSms.getData().setDeliverType(i);
        String string = msgFPSendSms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgFPSendSms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgFPSendSms.mMsgId, string);
    }

    public void verifyFindPasswordSmsCode(String str, String str2, HuyaAuthCallBack<ResFPVerifySms> huyaAuthCallBack) {
        MsgFPVerifySms msgFPVerifySms = new MsgFPVerifySms();
        msgFPVerifySms.getData().setMobile(str);
        msgFPVerifySms.getData().setSmscode(str2);
        String string = msgFPVerifySms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgFPVerifySms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgFPVerifySms.mMsgId, string);
    }

    public void findPasswordWithToken(String str, String str2, HuyaAuthCallBack<ResFPToken> huyaAuthCallBack) {
        MsgFPToken msgFPToken = new MsgFPToken();
        msgFPToken.getData().setUser(str);
        msgFPToken.getData().setNewPassword(str2);
        String string = msgFPToken.toString();
        this.mMapCallBack.put(Long.valueOf(MsgFPToken.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgFPToken.mMsgId, string);
    }

    public void findPasswordWithCode(String str, String str2, String str3, HuyaAuthCallBack<ResFPCode> huyaAuthCallBack) {
        MsgFPCode msgFPCode = new MsgFPCode();
        msgFPCode.getData().setUser(str);
        msgFPCode.getData().setNewPassword(str2);
        msgFPCode.getData().setSmscode(str3);
        String string = msgFPCode.toString();
        this.mMapCallBack.put(Long.valueOf(MsgFPCode.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgFPCode.mMsgId, string);
    }

    public void sendChangePasswordSmsCode(long j, String str, String str2, int i, HuyaAuthCallBack<ResCPSendSms> huyaAuthCallBack) {
        MsgCPSendSms msgCPSendSms = new MsgCPSendSms();
        msgCPSendSms.getData().setUid(j);
        msgCPSendSms.getData().setHyCred(str);
        msgCPSendSms.getData().setYyCred(str2);
        msgCPSendSms.getData().setDeliverType(i);
        String string = msgCPSendSms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgCPSendSms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgCPSendSms.mMsgId, string);
    }

    public void verifyChangePasswordSmsCode(long j, String str, String str2, String str3, HuyaAuthCallBack<ResCPVerifySms> huyaAuthCallBack) {
        MsgCPVerifySms msgCPVerifySms = new MsgCPVerifySms();
        msgCPVerifySms.getData().setUid(j);
        msgCPVerifySms.getData().setHyCred(str);
        msgCPVerifySms.getData().setYyCred(str2);
        msgCPVerifySms.getData().setSmscode(str3);
        String string = msgCPVerifySms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgCPVerifySms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgCPVerifySms.mMsgId, string);
    }

    public void changePasswordWithToken(long j, String str, String str2, String str3, HuyaAuthCallBack<ResCPToken> huyaAuthCallBack) {
        MsgCPToken msgCPToken = new MsgCPToken();
        msgCPToken.getData().setUid(j);
        msgCPToken.getData().setHyCred(str);
        msgCPToken.getData().setYyCred(str2);
        msgCPToken.getData().setNewPassword(str3);
        String string = msgCPToken.toString();
        this.mMapCallBack.put(Long.valueOf(MsgCPToken.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgCPToken.mMsgId, string);
    }

    public void changePasswordWithCode(long j, String str, String str2, String str3, String str4, HuyaAuthCallBack<ResCPCode> huyaAuthCallBack) {
        MsgCPCode msgCPCode = new MsgCPCode();
        msgCPCode.getData().setUid(j);
        msgCPCode.getData().setHyCred(str);
        msgCPCode.getData().setYyCred(str2);
        msgCPCode.getData().setNewPassword(str3);
        msgCPCode.getData().setSmscode(str4);
        String string = msgCPCode.toString();
        this.mMapCallBack.put(Long.valueOf(MsgCPCode.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgCPCode.mMsgId, string);
    }

    public void checkMobileIsReg(String str, HuyaAuthCallBack<ResCheckRegMobile> huyaAuthCallBack) {
        MsgCheckRegMobile msgCheckRegMobile = new MsgCheckRegMobile();
        msgCheckRegMobile.getData().setMobile(str);
        String string = msgCheckRegMobile.toString();
        this.mMapCallBack.put(Long.valueOf(MsgCheckRegMobile.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgCheckRegMobile.mMsgId, string);
    }

    public void checkUserFP(String str, HuyaAuthCallBack<ResCheckUser> huyaAuthCallBack) {
        MsgCheckUserFP msgCheckUserFP = new MsgCheckUserFP();
        msgCheckUserFP.getData().setUser(str);
        String string = msgCheckUserFP.toString();
        this.mMapCallBack.put(Long.valueOf(MsgCheckUserFP.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgCheckUserFP.mMsgId, string);
    }

    public void checkUserCP(String str, HuyaAuthCallBack<ResCheckUser> huyaAuthCallBack) {
        MsgCheckUserCP msgCheckUserCP = new MsgCheckUserCP();
        msgCheckUserCP.getData().setUser(str);
        String string = msgCheckUserCP.toString();
        this.mMapCallBack.put(Long.valueOf(MsgCheckUserCP.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgCheckUserCP.mMsgId, string);
    }

    public void getByPass(long j, String str, String str2, HuyaAuthCallBack<ResGetByPass> huyaAuthCallBack) {
        MsgGetByPass msgGetByPass = new MsgGetByPass();
        msgGetByPass.getData().setUid(j);
        msgGetByPass.getData().setUserName(str);
        msgGetByPass.getData().setThirdLgnType(str2);
        String string = msgGetByPass.toString();
        this.mMapCallBack.put(Long.valueOf(MsgGetByPass.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgGetByPass.mMsgId, string);
    }

    public void setByPass(int i, int i2) {
        MsgSetBypass msgSetBypass = new MsgSetBypass();
        msgSetBypass.getData().setBypass(i);
        msgSetBypass.getData().setDefaultBypass(i2);
        this.mHuyaAuthMgr.sendMessage(MsgSetBypass.mMsgId, msgSetBypass.toString());
    }

    public ResGetTicket getTicket(long j, String str) {
        MsgGetTicket msgGetTicket = new MsgGetTicket();
        msgGetTicket.getData().setUid(j);
        msgGetTicket.getData().setBizAppid(str);
        String strSendMessage = this.mHuyaAuthMgr.sendMessage(MsgGetTicket.mMsgId, msgGetTicket.toString());
        ResGetTicket resGetTicket = (ResGetTicket) HuyaAuthCallBack.parseJson(strSendMessage, ResGetTicket.class);
        if (resGetTicket == null) {
            HuyaUdbLogUtils.eString("udbauth", "getTicket is null, strRet is " + strSendMessage);
        }
        if (resGetTicket != null) {
            try {
                resGetTicket.setToken(URLEncoder.encode(resGetTicket.getToken(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return resGetTicket;
    }

    public TrustUserInfo getTrustInfo(String str, String str2, int i) {
        MsgGetRegInfo msgGetRegInfo = new MsgGetRegInfo();
        msgGetRegInfo.getData().setBizAppid(str);
        msgGetRegInfo.getData().setYytok_str(str2);
        msgGetRegInfo.getData().setYytok_type(i);
        String strSendMessage = this.mHuyaAuthMgr.sendMessage(MsgGetRegInfo.mMsgId, msgGetRegInfo.toString());
        TrustUserInfo trustUserInfo = (TrustUserInfo) HuyaAuthCallBack.parseJson(strSendMessage, TrustUserInfo.class);
        if (trustUserInfo == null) {
            HuyaUdbLogUtils.eString("udbauth", "getTrustInfo is null, strRet is " + strSendMessage);
        }
        return trustUserInfo;
    }

    private void unRegMarsTrusInfo(long j) {
        HuyaAuthMarsNetRequestUtil.getInstance().unRegMarsTrusInfo(j, 3);
    }

    public void setRegTrustInfoWatch(IIRegTrustInfoCallBack iIRegTrustInfoCallBack) {
        HuyaAuthMarsNetRequestUtil.getInstance().setTrustInfoCallBack(iIRegTrustInfoCallBack);
    }

    public long getLoginTime() {
        ResGetLoginTime resGetLoginTime = (ResGetLoginTime) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(MsgGetLoginTime.mMsgId, new MsgGetLoginTime().toString()), ResGetLoginTime.class);
        return resGetLoginTime != null ? resGetLoginTime.getLoginTime() : System.currentTimeMillis();
    }

    public void setNetState() {
        MsgSetNetState msgSetNetState = new MsgSetNetState();
        msgSetNetState.getData().setCarrier_type(HuyaDeveloperUtils.getInstance().getCarrierType());
        msgSetNetState.getData().setNet_type(HuyaDeveloperUtils.getInstance().getNetType());
        msgSetNetState.getData().setServer_host(HuyaDeveloperUtils.getInstance().getHost());
        this.mHuyaAuthMgr.sendMessage(MsgSetNetState.mMsgId, msgSetNetState.toString());
    }

    public ResGetCred getCred(long j) {
        MsgGetCred msgGetCred = new MsgGetCred();
        msgGetCred.getData().setUid(j);
        msgGetCred.getData().setType(0);
        return (ResGetCred) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(MsgGetCred.mMsgId, msgGetCred.toString()), ResGetCred.class);
    }

    public String getOriYYCred(long j) {
        MsgGetCred msgGetCred = new MsgGetCred();
        msgGetCred.getData().setUid(j);
        msgGetCred.getData().setType(1);
        msgGetCred.getData().setYypath(this.mApplication.getDir("auth", 0).getAbsolutePath());
        ResGetCred resGetCred = (ResGetCred) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(MsgGetCred.mMsgId, msgGetCred.toString()), ResGetCred.class);
        return resGetCred != null ? resGetCred.getYyCred() : "";
    }

    public int getByPassFromUid(long j) {
        MsgGetBypassFromUid msgGetBypassFromUid = new MsgGetBypassFromUid();
        msgGetBypassFromUid.getData().setUid(j);
        return ((ResGetByPassFromUid) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(MsgGetBypassFromUid.mMsgId, msgGetBypassFromUid.toString()), ResGetByPassFromUid.class)).getBypass();
    }

    public void bindByPassAndUid(long j, int i) {
        MsgBindBypassAndUid msgBindBypassAndUid = new MsgBindBypassAndUid();
        msgBindBypassAndUid.getData().setUid(j);
        msgBindBypassAndUid.getData().setBypass(i);
        this.mHuyaAuthMgr.sendMessage(MsgBindBypassAndUid.mMsgId, msgBindBypassAndUid.toString());
    }

    public String getH5Info(long j, String str, String str2) {
        MsgGetH5Info msgGetH5Info = new MsgGetH5Info();
        msgGetH5Info.getData().setUid(j);
        msgGetH5Info.getData().setYyCred(str);
        msgGetH5Info.getData().setUser(str2);
        return this.mHuyaAuthMgr.sendMessage(MsgGetH5Info.mMsgId, msgGetH5Info.toString());
    }

    public String getH5InfoEx(long j) {
        MsgGetH5InfoEx msgGetH5InfoEx = new MsgGetH5InfoEx();
        msgGetH5InfoEx.getData().setUid(j);
        return this.mHuyaAuthMgr.sendMessage(MsgGetH5InfoEx.mMsgId, msgGetH5InfoEx.toString());
    }

    public ResLogin decodeH5Info(String str) {
        return (ResLogin) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(184549386L, str), ResLogin.class);
    }

    public ResGetTicket getHyOtp() {
        return (ResGetTicket) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(184549388L, ""), ResGetTicket.class);
    }

    public AppLoginData getAnonyLoginCred() {
        return (AppLoginData) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(184549413L, ""), AppLoginData.class);
    }

    public void setDeviceInfo(String str, String str2) {
        HuyaAccountSaveUtils.getInstance().setLcid(str);
        HuyaAccountSaveUtils.getInstance().setContrycode(str2);
        MsgSetDeviceInfo msgSetDeviceInfo = new MsgSetDeviceInfo();
        msgSetDeviceInfo.getData().setSafedeviceid(HuyaAccountSaveUtils.getInstance().getSdid());
        msgSetDeviceInfo.getData().setCountryCode(HuyaAccountSaveUtils.getInstance().getContrycode());
        msgSetDeviceInfo.getData().setLcid(HuyaAccountSaveUtils.getInstance().getLcid());
        this.mHuyaAuthMgr.sendMessage(MsgSetDeviceInfo.mMsgId, msgSetDeviceInfo.toString());
    }

    public void bindChangePhone(long j, String str, String str2, HuyaAuthCallBack<ResBindVerifySms> huyaAuthCallBack) {
        MsgBindChangePhone msgBindChangePhone = new MsgBindChangePhone();
        msgBindChangePhone.getData().setMobile(str);
        msgBindChangePhone.getData().setSmscode(str2);
        msgBindChangePhone.getData().setUid(j);
        String string = msgBindChangePhone.toString();
        this.mMapCallBack.put(Long.valueOf(MsgBindChangePhone.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgBindChangePhone.mMsgId, string);
    }

    public void bindAuth(int i, String str, String str2, String str3, int i2, HuyaAuthCallBack<ResBindAuth> huyaAuthCallBack) {
        MsgBindAuth msgBindAuth = new MsgBindAuth();
        msgBindAuth.getData().setType(i);
        msgBindAuth.getData().setAuthCode(str);
        msgBindAuth.getData().setAccesstoken(str2);
        msgBindAuth.getData().setOpenid(str3);
        msgBindAuth.getData().setOpentype(i2);
        String string = msgBindAuth.toString();
        this.mMapCallBack.put(Long.valueOf(MsgBindAuth.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgBindAuth.mMsgId, string);
    }

    public void getBindList(HuyaAuthCallBack<ResGetBindList> huyaAuthCallBack) {
        String string = new MsgGetBindList().toString();
        this.mMapCallBack.put(Long.valueOf(MsgGetBindList.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgGetBindList.mMsgId, string);
    }

    public void unBindAuth(String str, String str2, int i, HuyaAuthCallBack<ResUnBindAuth> huyaAuthCallBack) {
        MsgUnBindAuth msgUnBindAuth = new MsgUnBindAuth();
        msgUnBindAuth.getData().setUnionId(str);
        msgUnBindAuth.getData().setOpenid(str2);
        msgUnBindAuth.getData().setOpentype(i);
        String string = msgUnBindAuth.toString();
        this.mMapCallBack.put(Long.valueOf(MsgUnBindAuth.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgUnBindAuth.mMsgId, string);
    }

    public void setUpdateCredWatcher(HuyaAuthCallBack<ResLoginCred> huyaAuthCallBack) {
        new MsgUpdateCred();
        this.mMapCallBack.put(Long.valueOf(MsgUpdateCred.mMsgId), huyaAuthCallBack);
    }

    public void setKickOffWatcher(HuyaAuthCallBack<ResKickOff> huyaAuthCallBack) {
        new MsgKickOff();
        this.mMapCallBack.put(Long.valueOf(MsgKickOff.mMsgId), huyaAuthCallBack);
    }

    public void setNewLoginWatcher(HuyaAuthCallBack<ResNewLogin> huyaAuthCallBack) {
        new MsgNewLogin();
        this.mMapCallBack.put(Long.valueOf(MsgNewLogin.mMsgId), huyaAuthCallBack);
    }

    public String getVerifyHyTk(long j, int i, String str) {
        MsgGetVerifyHyTk msgGetVerifyHyTk = new MsgGetVerifyHyTk();
        msgGetVerifyHyTk.getData().setToken(str);
        msgGetVerifyHyTk.getData().setType(i);
        msgGetVerifyHyTk.getData().setUid(j);
        msgGetVerifyHyTk.getData().setHuyaua(HuyaAccountSaveUtils.getInstance().getHuyaua());
        msgGetVerifyHyTk.getData().setGuid(HuyaAccountSaveUtils.getInstance().getGuid());
        return this.mHuyaAuthMgr.sendMessage(MsgGetVerifyHyTk.mMsgId, msgGetVerifyHyTk.toString());
    }

    public ResDecodeVerifyHyTk decodeVerifyHyTk(String str) {
        return (ResDecodeVerifyHyTk) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(184549397L, str), ResDecodeVerifyHyTk.class);
    }

    public String getUnVerifyHyTk(long j) {
        MsgGetUnVerifyHyTk msgGetUnVerifyHyTk = new MsgGetUnVerifyHyTk();
        msgGetUnVerifyHyTk.getData().setUid(j);
        return this.mHuyaAuthMgr.sendMessage(MsgGetUnVerifyHyTk.mMsgId, msgGetUnVerifyHyTk.toString());
    }

    public void getUserStatus(long j, HuyaAuthCallBack<ResGetUserStatus> huyaAuthCallBack) {
        MsgGetUserStatus msgGetUserStatus = new MsgGetUserStatus();
        msgGetUserStatus.getData().setUid(j);
        String string = msgGetUserStatus.toString();
        this.mMapCallBack.put(Long.valueOf(MsgGetUserStatus.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgGetUserStatus.mMsgId, string);
    }

    public String getOtpEx(long j, int i, String str) {
        MsgGetOtpEx msgGetOtpEx = new MsgGetOtpEx();
        msgGetOtpEx.getData().setUid(j);
        msgGetOtpEx.getData().setType(i);
        msgGetOtpEx.getData().setToken(str);
        return this.mHuyaAuthMgr.sendMessage(MsgGetOtpEx.mMsgId, msgGetOtpEx.toString());
    }

    public ResGetQUrl getQUrl(long j, String str, String str2, String str3) {
        MsgGetQUrl msgGetQUrl = new MsgGetQUrl();
        msgGetQUrl.getData().setUid(j);
        msgGetQUrl.getData().setNickName(str2);
        msgGetQUrl.getData().setOtp(str);
        msgGetQUrl.getData().setPicture(str3);
        return (ResGetQUrl) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(MsgGetQUrl.mMsgId, msgGetQUrl.toString()), ResGetQUrl.class);
    }

    public void QRNotifyScanCode(long j, String str, HuyaAuthCallBack<ResNotifyScanCode> huyaAuthCallBack) {
        MsgNotifyScanCode msgNotifyScanCode = new MsgNotifyScanCode();
        msgNotifyScanCode.getData().setUid(j);
        msgNotifyScanCode.getData().setQrId(str);
        String string = msgNotifyScanCode.toString();
        this.mMapCallBack.put(Long.valueOf(MsgNotifyScanCode.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgNotifyScanCode.mMsgId, string);
    }

    public void QRCodeConfirmReq(long j, String str, HuyaAuthCallBack<ResBindScanQr> huyaAuthCallBack) {
        MsgBindScanQr msgBindScanQr = new MsgBindScanQr();
        msgBindScanQr.getData().setUid(j);
        msgBindScanQr.getData().setQrId(str);
        String string = msgBindScanQr.toString();
        this.mMapCallBack.put(Long.valueOf(MsgBindScanQr.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgBindScanQr.mMsgId, string);
    }

    public ResShareAppLoginListData getShareAppLoginList() {
        return (ResShareAppLoginListData) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(MsgGetShareAppLogin.mMsgId, new MsgGetShareAppLogin().toString()), ResShareAppLoginListData.class);
    }

    public void QRCodeCancelReq(long j, String str, HuyaAuthCallBack<ResCancleQrLogin> huyaAuthCallBack) {
        MsgCancleQrLogin msgCancleQrLogin = new MsgCancleQrLogin();
        msgCancleQrLogin.getData().setUid(j);
        msgCancleQrLogin.getData().setQrId(str);
        String string = msgCancleQrLogin.toString();
        this.mMapCallBack.put(Long.valueOf(MsgCancleQrLogin.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgCancleQrLogin.mMsgId, string);
    }

    public void QRAuthConfirmReq(long j, String str, HuyaAuthCallBack<MsgAuthScanQr> huyaAuthCallBack) {
        MsgAuthScanQr msgAuthScanQr = new MsgAuthScanQr();
        msgAuthScanQr.getData().setUid(j);
        msgAuthScanQr.getData().setQrId(str);
        String string = msgAuthScanQr.toString();
        this.mMapCallBack.put(Long.valueOf(MsgAuthScanQr.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgAuthScanQr.mMsgId, string);
    }

    public void QRAuthCancelReq(long j, String str, HuyaAuthCallBack<ResCancleQrLogin> huyaAuthCallBack) {
        MsgCancleAuthLogin msgCancleAuthLogin = new MsgCancleAuthLogin();
        msgCancleAuthLogin.getData().setUid(j);
        msgCancleAuthLogin.getData().setQrId(str);
        String string = msgCancleAuthLogin.toString();
        this.mMapCallBack.put(Long.valueOf(MsgCancleAuthLogin.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgCancleAuthLogin.mMsgId, string);
    }

    public void pushMessage(long j, String str) {
        MsgPushMessage msgPushMessage = new MsgPushMessage();
        msgPushMessage.getData().setUri(j);
        msgPushMessage.getData().setData(str);
        this.mHuyaAuthMgr.sendMessage(MsgPushMessage.mMsgId, msgPushMessage.toString());
    }

    public String getDynamicConfigData(long j, Map<String, String> map) {
        MsgGetDynamicCfg msgGetDynamicCfg = new MsgGetDynamicCfg();
        msgGetDynamicCfg.getData().setMpParams(map);
        msgGetDynamicCfg.getData().setUid(j);
        return this.mHuyaAuthMgr.sendMessage(MsgGetDynamicCfg.mMsgId, msgGetDynamicCfg.toString());
    }

    public ResDynamicCfg decodeDynamicConfigData(String str) {
        return (ResDynamicCfg) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(184549395L, str), ResDynamicCfg.class);
    }

    public ResGetTicket getDefaultToken() {
        return getInstance().getTicket(0L, HuyaDeveloperUtils.getInstance().getAppId());
    }

    public void setSafeDeviceId(String str) {
        HuyaAccountSaveUtils.getInstance().setSdid(str);
        MsgSetDeviceInfo msgSetDeviceInfo = new MsgSetDeviceInfo();
        msgSetDeviceInfo.getData().setSafedeviceid(str);
        if (!BuildConfig.IS_HUYA_ACCOUNT.booleanValue()) {
            msgSetDeviceInfo.getData().setSafedeviceid(HuyaAccountSaveUtils.getInstance().getSdid());
            msgSetDeviceInfo.getData().setCountryCode(HuyaAccountSaveUtils.getInstance().getContrycode());
            msgSetDeviceInfo.getData().setLcid(HuyaAccountSaveUtils.getInstance().getLcid());
        }
        this.mHuyaAuthMgr.sendMessage(MsgSetDeviceInfo.mMsgId, msgSetDeviceInfo.toString());
    }

    public boolean loginH5Data(String str, HuyaAuthCallBack<ResLoginCred> huyaAuthCallBack) {
        long uid;
        long j;
        if (str.contains("%")) {
            try {
                str = URLDecoder.decode(str, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ResLogin resLoginDecodeH5Info = getInstance().decodeH5Info(str);
        if (resLoginDecodeH5Info == null || resLoginDecodeH5Info.getHeader().getRet() != 0) {
            return false;
        }
        String credit = (!BuildConfig.IS_HUYA_ACCOUNT.booleanValue() || resLoginDecodeH5Info.getLoginData().getYyloginData() == null || resLoginDecodeH5Info.getLoginData().getYyloginData().getYyuid() == 0) ? "" : resLoginDecodeH5Info.getLoginData().getYyloginData().getCredit();
        String cred = resLoginDecodeH5Info.getLoginData().getApploginData() != null ? resLoginDecodeH5Info.getLoginData().getApploginData().getCred() : "";
        if (BuildConfig.IS_HUYA_ACCOUNT.booleanValue()) {
            long yyuid = resLoginDecodeH5Info.getLoginData().getYyloginData().getYyuid();
            if (yyuid == 0) {
                uid = resLoginDecodeH5Info.getLoginData().getApploginData().getUid();
            } else {
                j = yyuid;
                loginCred(j, cred, credit, false, false, huyaAuthCallBack);
                return true;
            }
        } else {
            uid = resLoginDecodeH5Info.getLoginData().getApploginData().getUid();
        }
        j = uid;
        loginCred(j, cred, credit, false, false, huyaAuthCallBack);
        return true;
    }

    public void loginMobileQuick(int i, String str, boolean z, List<String> list, HuyaAuthCallBack<ResLogin> huyaAuthCallBack) {
        MsgLoginMobileQuick msgLoginMobileQuick = new MsgLoginMobileQuick();
        msgLoginMobileQuick.getData().setOperatorType(i);
        msgLoginMobileQuick.getData().setOperatorToken(str);
        msgLoginMobileQuick.getData().setUserAction(getLoginUserAction());
        msgLoginMobileQuick.getData().setAuthLogin(Boolean.valueOf(z));
        if (list != null) {
            msgLoginMobileQuick.getData().setBizAppids(list);
        }
        String string = msgLoginMobileQuick.toString();
        this.mMapCallBack.put(Long.valueOf(MsgLoginMobileQuick.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgLoginMobileQuick.mMsgId, string);
    }

    public void QRCodeCheckReq(long j, String str, final String str2) {
        getInstance().QRNotifyScanCode(j, str, new HuyaAuthCallBack<ResNotifyScanCode>(ResNotifyScanCode.class) { // from class: com.huyaudbunify.HuyaAuth.3
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResNotifyScanCode resNotifyScanCode) {
                if (resNotifyScanCode == null || !HuyaEventUtils.checkTimeOut(resNotifyScanCode.getHeader().getRet(), str2)) {
                    AuthEvent.QRCodeCheckEvent qRCodeCheckEvent = new AuthEvent.QRCodeCheckEvent();
                    if (resNotifyScanCode == null) {
                        qRCodeCheckEvent.errCode = -1;
                        qRCodeCheckEvent.uiAction = 1;
                    } else {
                        qRCodeCheckEvent.errCode = resNotifyScanCode.getHeader().getRet();
                        qRCodeCheckEvent.description = resNotifyScanCode.getHeader().getDescription();
                        if (resNotifyScanCode.getHeader().getRet() == 0) {
                            qRCodeCheckEvent.authAppId = resNotifyScanCode.getLoginAppId();
                            qRCodeCheckEvent.authAppInfo = resNotifyScanCode.getLoginAppName();
                        } else {
                            qRCodeCheckEvent.uiAction = 1;
                        }
                    }
                    qRCodeCheckEvent.context = str2;
                    HuyaEventUtils.dispatchAuthEvent(qRCodeCheckEvent);
                }
            }
        });
    }

    public void QRCodeConfirmReq(long j, String str, final String str2) {
        getInstance().QRCodeConfirmReq(j, str, new HuyaAuthCallBack<ResBindScanQr>(ResBindScanQr.class) { // from class: com.huyaudbunify.HuyaAuth.4
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResBindScanQr resBindScanQr) {
                if (resBindScanQr == null || !HuyaEventUtils.checkTimeOut(resBindScanQr.getHeader().getRet(), str2)) {
                    AuthEvent.QRCodeConfirmEvent qRCodeConfirmEvent = new AuthEvent.QRCodeConfirmEvent();
                    if (resBindScanQr == null) {
                        qRCodeConfirmEvent.errCode = -1;
                        qRCodeConfirmEvent.uiAction = 1;
                    } else {
                        qRCodeConfirmEvent.errCode = resBindScanQr.getHeader().getRet();
                        qRCodeConfirmEvent.description = resBindScanQr.getHeader().getDescription();
                        if (resBindScanQr.getHeader().getRet() != 0) {
                            qRCodeConfirmEvent.uiAction = 1;
                        }
                    }
                    qRCodeConfirmEvent.context = str2;
                    HuyaEventUtils.dispatchAuthEvent(qRCodeConfirmEvent);
                }
            }
        });
    }

    public void QRCodeCancelReq(long j, String str, final String str2) {
        getInstance().QRCodeCancelReq(j, str, new HuyaAuthCallBack<ResCancleQrLogin>(ResCancleQrLogin.class) { // from class: com.huyaudbunify.HuyaAuth.5
            @Override // com.huyaudbunify.inter.HuyaAuthCallBack
            public void hyCallBack(ResCancleQrLogin resCancleQrLogin) {
                if (resCancleQrLogin == null || !HuyaEventUtils.checkTimeOut(resCancleQrLogin.getHeader().getRet(), str2)) {
                    AuthEvent.QRCodeCancelEvent qRCodeCancelEvent = new AuthEvent.QRCodeCancelEvent();
                    if (resCancleQrLogin == null) {
                        qRCodeCancelEvent.errCode = -1;
                        qRCodeCancelEvent.uiAction = 1;
                    } else {
                        qRCodeCancelEvent.errCode = resCancleQrLogin.getHeader().getRet();
                        qRCodeCancelEvent.description = resCancleQrLogin.getHeader().getDescription();
                        if (resCancleQrLogin.getHeader().getRet() != 0) {
                            qRCodeCancelEvent.uiAction = 1;
                        }
                    }
                    qRCodeCancelEvent.context = str2;
                    HuyaEventUtils.dispatchAuthEvent(qRCodeCancelEvent);
                }
            }
        });
    }

    public void checkHyName(String str, final HuyaAuthCallBack<ResHyName> huyaAuthCallBack) {
        ResGetTicket ticket = getTicket(0L, HuyaDeveloperUtils.getInstance().getAppId());
        ReqCheckHyName reqCheckHyName = new ReqCheckHyName();
        reqCheckHyName.setAppId(HuyaDeveloperUtils.getInstance().getAppId());
        reqCheckHyName.getData().setUserId(str);
        if (ticket != null) {
            reqCheckHyName.getData().setUid(ticket.getUid());
            reqCheckHyName.getData().setToken(ticket.getToken());
            reqCheckHyName.getData().setTokenType(ticket.getTokenType());
        }
        String json = new Gson().toJson(reqCheckHyName);
        new HuyaAuthNetRequestUtil().sendRequestPost(isDeveloper() ? "http://udbreg-test.huya.com/check/userId" : "https://udbreg.huya.com/check/userId", json.getBytes(), 0, true, new IIHttpCallBack() { // from class: com.huyaudbunify.HuyaAuth.6
            @Override // com.huyaudbunify.inter.IIHttpCallBack
            public void onResponse(byte[] bArr, int i, int i2, int i3) {
                if (bArr == null || bArr.length == 0) {
                    ResHyName resHyName = new ResHyName();
                    resHyName.setReturnCode(-1);
                    HuyaAuthCallBack huyaAuthCallBack2 = huyaAuthCallBack;
                    if (huyaAuthCallBack2 != null) {
                        huyaAuthCallBack2.hyCallBack(resHyName);
                        return;
                    }
                    return;
                }
                String str2 = new String(bArr);
                HuyaAuthCallBack huyaAuthCallBack3 = huyaAuthCallBack;
                if (huyaAuthCallBack3 != null) {
                    huyaAuthCallBack3.hyCallBackData(str2);
                }
            }
        });
    }

    public void commitHyName(String str, final HuyaAuthCallBack<ResHyName> huyaAuthCallBack) {
        ResGetTicket ticket = getTicket(0L, HuyaDeveloperUtils.getInstance().getAppId());
        ReqCheckHyName reqCheckHyName = new ReqCheckHyName();
        reqCheckHyName.setAppId(HuyaDeveloperUtils.getInstance().getAppId());
        reqCheckHyName.getData().setUserId(str);
        if (ticket != null) {
            reqCheckHyName.getData().setUid(ticket.getUid());
            reqCheckHyName.getData().setToken(ticket.getToken());
            reqCheckHyName.getData().setTokenType(ticket.getTokenType());
        }
        String json = new Gson().toJson(reqCheckHyName);
        new HuyaAuthNetRequestUtil().sendRequestPost(isDeveloper() ? "http://udbreg-test.huya.com/update/userId" : "https://udbreg.huya.com/update/userId", json.getBytes(), 0, true, new IIHttpCallBack() { // from class: com.huyaudbunify.HuyaAuth.7
            @Override // com.huyaudbunify.inter.IIHttpCallBack
            public void onResponse(byte[] bArr, int i, int i2, int i3) {
                if (bArr == null || bArr.length == 0) {
                    ResHyName resHyName = new ResHyName();
                    resHyName.setReturnCode(-1);
                    HuyaAuthCallBack huyaAuthCallBack2 = huyaAuthCallBack;
                    if (huyaAuthCallBack2 != null) {
                        huyaAuthCallBack2.hyCallBack(resHyName);
                        return;
                    }
                    return;
                }
                String str2 = new String(bArr);
                HuyaAuthCallBack huyaAuthCallBack3 = huyaAuthCallBack;
                if (huyaAuthCallBack3 != null) {
                    huyaAuthCallBack3.hyCallBackData(str2);
                }
            }
        });
    }

    public void bindSendSms(long j, String str, int i, HuyaAuthCallBack<ResBindSendSms> huyaAuthCallBack) {
        MsgBindSendSms msgBindSendSms = new MsgBindSendSms();
        msgBindSendSms.getData().setDeliverType(i);
        msgBindSendSms.getData().setMobile(str);
        msgBindSendSms.getData().setUid(j);
        String string = msgBindSendSms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgBindSendSms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgBindSendSms.mMsgId, string);
    }

    public void bindVerifySms(long j, String str, String str2, String str3, HuyaAuthCallBack<ResBindVerifySms> huyaAuthCallBack) {
        MsgBindVerifySms msgBindVerifySms = new MsgBindVerifySms();
        msgBindVerifySms.getData().setMobile(str);
        msgBindVerifySms.getData().setSha1Psw(str3);
        msgBindVerifySms.getData().setSmscode(str2);
        msgBindVerifySms.getData().setUid(j);
        String string = msgBindVerifySms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgBindVerifySms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgBindVerifySms.mMsgId, string);
    }

    public void unBindSendSms(long j, int i, HuyaAuthCallBack<ResUnBindSendSms> huyaAuthCallBack) {
        MsgUnBindSendSms msgUnBindSendSms = new MsgUnBindSendSms();
        msgUnBindSendSms.getData().setUid(j);
        msgUnBindSendSms.getData().setDeliverType(i);
        String string = msgUnBindSendSms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgUnBindSendSms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgUnBindSendSms.mMsgId, string);
    }

    public void unBindVerifySms(long j, String str, HuyaAuthCallBack<ResUnBindVerifySms> huyaAuthCallBack) {
        MsgUnBindVerifySms msgUnBindVerifySms = new MsgUnBindVerifySms();
        msgUnBindVerifySms.getData().setUid(j);
        msgUnBindVerifySms.getData().setSmscode(str);
        String string = msgUnBindVerifySms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgUnBindVerifySms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgUnBindVerifySms.mMsgId, string);
    }

    public void bindNewSendSms(long j, String str, int i, HuyaAuthCallBack<ResBindNewSendSms> huyaAuthCallBack) {
        MsgBindNewSendSms msgBindNewSendSms = new MsgBindNewSendSms();
        msgBindNewSendSms.getData().setUid(j);
        msgBindNewSendSms.getData().setMobile(str);
        msgBindNewSendSms.getData().setDeliverType(i);
        String string = msgBindNewSendSms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgBindNewSendSms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgBindNewSendSms.mMsgId, string);
    }

    public void bindNewVerifySms(long j, String str, String str2, HuyaAuthCallBack<ResBindNewVerifySms> huyaAuthCallBack) {
        MsgBindNewVerifySms msgBindNewVerifySms = new MsgBindNewVerifySms();
        msgBindNewVerifySms.getData().setUid(j);
        msgBindNewVerifySms.getData().setNewmobile(str);
        msgBindNewVerifySms.getData().setSmscode(str2);
        String string = msgBindNewVerifySms.toString();
        this.mMapCallBack.put(Long.valueOf(MsgBindNewVerifySms.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgBindNewVerifySms.mMsgId, string);
    }

    public String getUnionId(long j, String str) {
        MsgGetUnionId msgGetUnionId = new MsgGetUnionId();
        msgGetUnionId.getData().setUid(j);
        msgGetUnionId.getData().setDeveloperId(str);
        ResGetUnionId resGetUnionId = (ResGetUnionId) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(MsgGetUnionId.mMsgId, msgGetUnionId.toString()), ResGetUnionId.class);
        return resGetUnionId == null ? "" : resGetUnionId.getUnionId();
    }

    public ResGetUidFromUnionId getUidFromUnionId(String str) {
        MsgGetUidFromUnionId msgGetUidFromUnionId = new MsgGetUidFromUnionId();
        msgGetUidFromUnionId.getData().setUnionId(str);
        return (ResGetUidFromUnionId) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(MsgGetUidFromUnionId.mMsgId, msgGetUidFromUnionId.toString()), ResGetUidFromUnionId.class);
    }

    public void unRegTrustInfo(long j) {
        if (HuyaDeveloperUtils.getInstance().isUseMars()) {
            unRegMarsTrusInfo(j);
        }
    }

    public void regTrustInfo(final int i) {
        TrustUserInfo trustInfo;
        if (i <= 0 || (trustInfo = getTrustInfo()) == null) {
            return;
        }
        final long uid = trustInfo.getUid();
        if (HuyaAccountSaveUtils.getInstance().isLogin()) {
            HuyaAuthMarsNetRequestUtil.getInstance().regMarsTrustInfo(trustInfo, new NSVerifyApi.VerifyBizListenerV2() { // from class: com.huyaudbunify.HuyaAuth.8
                @Override // com.huya.mtp.hyns.api.NSVerifyApi.VerifyBizListenerV2
                public void onResult(boolean z, int i2, String str) {
                    if (!HuyaDeveloperUtils.getInstance().isForbidLog() && !z) {
                        Log.i("udbauth", "regTrustInfo error msg:" + str);
                    }
                    if (z) {
                        if (HuyaAuth.this.mTrustInfoCallBack != null) {
                            HuyaAuth.this.mTrustInfoCallBack.infoRet(uid, Boolean.valueOf(z));
                        }
                    } else if (i2 == 1 || i2 == -10 || i2 == -11 || i2 == -12) {
                        if (HuyaAuth.this.mTrustInfoCallBack != null) {
                            HuyaAuth.this.mTrustInfoCallBack.verifyResultCallback(i2, str);
                            return;
                        }
                        return;
                    } else {
                        try {
                            Thread.sleep(300L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        HuyaAuth.this.regTrustInfo(i - 1);
                    }
                    if (HuyaAuth.this.logCallBack != null) {
                        HuyaAuth.this.logCallBack.log("udbauth--regTrustInfo-" + z + "，code=" + i2 + ",error=" + str);
                    }
                }
            });
        }
    }

    public TrustUserInfo getTrustInfo() {
        return getInstance().getTrustInfo(HuyaDeveloperUtils.getInstance().getAppId(), "", 0);
    }

    private void initBroadcasetReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.mApplication.registerReceiver(this.myNetReceiver, intentFilter);
        getCarrierType(new HuyaCommonUtil.GetCarrierTypeCallback() { // from class: com.huyaudbunify.HuyaAuth.9
            @Override // com.huyaudbunify.util.HuyaCommonUtil.GetCarrierTypeCallback
            public void callback(int i) {
                HuyaAuth.this.setCarrierType(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getCarrierType(final HuyaCommonUtil.GetCarrierTypeCallback getCarrierTypeCallback) {
        new Thread(new Runnable() { // from class: com.huyaudbunify.HuyaAuth.10
            @Override // java.lang.Runnable
            public void run() {
                getCarrierTypeCallback.callback(HuyaCommonUtil.getCarrierType(HuyaAuth.this.mApplication));
            }
        }).start();
    }

    public void setTrustInfoCallBack(ITrustInfoCallBack iTrustInfoCallBack) {
        this.mTrustInfoCallBack = iTrustInfoCallBack;
    }

    public void loginWithOtpSign(long j, String str, String str2, String str3, HuyaAuthCallBack<ResLoginCred> huyaAuthCallBack) {
        MsgOtherAppCredLogin msgOtherAppCredLogin = new MsgOtherAppCredLogin();
        msgOtherAppCredLogin.getData().setUid(j);
        msgOtherAppCredLogin.getData().setOtp(str);
        msgOtherAppCredLogin.getData().setSrcAppId(str2);
        msgOtherAppCredLogin.getData().setTargetAppId(str3);
        String string = msgOtherAppCredLogin.toString();
        this.mMapCallBack.put(Long.valueOf(MsgOtherAppCredLogin.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgOtherAppCredLogin.mMsgId, string);
    }

    public ResHuyaLoginDataCookiesInfo getLoginDataCookies(long j) {
        MsgGetAppLoginDataCookies msgGetAppLoginDataCookies = new MsgGetAppLoginDataCookies();
        msgGetAppLoginDataCookies.getData().setUid(j);
        return (ResHuyaLoginDataCookiesInfo) HuyaAuthCallBack.parseJson(this.mHuyaAuthMgr.sendMessage(MsgGetAppLoginDataCookies.mMsgId, msgGetAppLoginDataCookies.toString()), ResHuyaLoginDataCookiesInfo.class);
    }

    public void reportCarrierInterfaceStatus(int i, JSONObject jSONObject) {
        try {
            MsgReportLoginStatus msgReportLoginStatus = new MsgReportLoginStatus();
            if (i == 0) {
                msgReportLoginStatus.getData().setUri("hyCarrierPhoneInfo");
            } else {
                msgReportLoginStatus.getData().setUri("hyCarrierAuth");
            }
            if (jSONObject != null) {
                if (jSONObject.has(HuyaBerry.BerryEvent.BERRYEVENT_RESULTCODE)) {
                    msgReportLoginStatus.getData().setRescode(jSONObject.getInt(HuyaBerry.BerryEvent.BERRYEVENT_RESULTCODE) + "");
                }
                if (jSONObject.has("desc")) {
                    msgReportLoginStatus.getData().setResultDesc(jSONObject.getBoolean("desc") + "");
                }
                if (jSONObject.has("resultDesc")) {
                    msgReportLoginStatus.getData().setResultDesc(jSONObject.getString("resultDesc") + "");
                }
                if (jSONObject.has("authType")) {
                    msgReportLoginStatus.getData().setAuthType(jSONObject.getString("authType") + "");
                }
                if (jSONObject.has("authTypeDec")) {
                    msgReportLoginStatus.getData().setAuthTypeDec(jSONObject.getString("authTypeDec") + "");
                }
                if (jSONObject.has("token")) {
                    msgReportLoginStatus.getData().setToken(jSONObject.getString("token") + "");
                }
                if (jSONObject.has("raceID")) {
                    msgReportLoginStatus.getData().setRaceID(jSONObject.getString("raceID") + "");
                }
            }
            this.mHuyaAuthMgr.sendMessage(MsgReportLoginStatus.mMsgId, msgReportLoginStatus.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendBindEmailCode(long j, String str, HuyaAuthCallBack<HuyaSendBindEmailCodeRsp> huyaAuthCallBack) {
        MsgSendBindEmailCode msgSendBindEmailCode = new MsgSendBindEmailCode();
        msgSendBindEmailCode.getData().setUid(j);
        msgSendBindEmailCode.getData().setEmail(str);
        String string = msgSendBindEmailCode.toString();
        this.mMapCallBack.put(Long.valueOf(MsgSendBindEmailCode.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgSendBindEmailCode.mMsgId, string);
    }

    public void bindLoginEmail(long j, String str, String str2, String str3, String str4, HuyaAuthCallBack<HuyaBindLoginEmailRsp> huyaAuthCallBack) {
        MsgBindLoginEmail msgBindLoginEmail = new MsgBindLoginEmail();
        msgBindLoginEmail.getData().setUid(j);
        msgBindLoginEmail.getData().setEmail(str);
        msgBindLoginEmail.getData().setEmailcode(str2);
        msgBindLoginEmail.getData().setSessionData(str3);
        msgBindLoginEmail.getData().setSha1Psw(str4);
        String string = msgBindLoginEmail.toString();
        this.mMapCallBack.put(Long.valueOf(MsgBindLoginEmail.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgBindLoginEmail.mMsgId, string);
    }

    public void emailLogin(String str, String str2, HuyaAuthCallBack<ResLogin> huyaAuthCallBack) {
        MsgEmailLogin msgEmailLogin = new MsgEmailLogin();
        msgEmailLogin.getData().setEmail(str);
        msgEmailLogin.getData().setSha1Psw(str2);
        String string = msgEmailLogin.toString();
        this.mMapCallBack.put(Long.valueOf(MsgEmailLogin.mMsgId), huyaAuthCallBack);
        this.mHuyaAuthMgr.sendMessage(MsgEmailLogin.mMsgId, string);
    }
}
