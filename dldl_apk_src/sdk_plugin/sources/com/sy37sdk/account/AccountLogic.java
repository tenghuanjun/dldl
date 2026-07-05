package com.sy37sdk.account;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import com.huya.hyhttpdns.dns.NetworkUtil;
import com.social.sdk.SocialApi;
import com.social.sdk.common.listener.OnAuthListener;
import com.social.sdk.platform.PlatformType;
import com.sq.tool.logger.SQLog;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tools.Logger;
import com.sqnetwork.voly.TimeoutError;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IBindWxListener;
import com.sqwan.common.mod.config.IConfigMod;
import com.sqwan.common.user.UserInfoManager;
import com.sqwan.common.util.AESUtil;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.EnvironmentUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.NetWorkUtils;
import com.sqwan.common.util.SDKError;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.ZipString;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.msdk.BaseSQwanCore;
import com.sqwan.msdk.config.ConfigManager;
import com.sy37sdk.account.alifast.FastLoginHttpUtil;
import com.sy37sdk.account.alifast.FastLoginManager;
import com.sy37sdk.account.captcha.CaptchaDialog;
import com.sy37sdk.account.captcha.VerifyPhoneDialog;
import com.sy37sdk.account.db.LoginTrigger;
import com.sy37sdk.account.db.LoginTriggerDBManager;
import com.sy37sdk.account.net.LoginRequestManager;
import com.sy37sdk.account.trackaction.UserNameEmptyTrackAction;
import com.sy37sdk.account.uagree.UAgreeManager;
import com.sy37sdk.account.util.AccountLoginType;
import com.sy37sdk.account.util.AccountRegType;
import com.sy37sdk.account.util.AccountUtil;
import com.sy37sdk.account.view.uifast.presenter.PhonePresenter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountLogic {
    private static int MAX_TIME_OUT = 2;
    private static int SERVER_ERROR_CODE = 500;
    private static int SERVER_ERROR_CODE_LINK = 302;
    public static int SERVER_ERROR_CODE_TICKET_TIMEOUT = 401;
    private static final String TAG = "【Login Logic】";
    private static volatile AccountLogic instance;
    private Context context;
    private String loginData;
    private AccountListener loginListener;
    private int login_timeout_count = 0;
    private QrCodeInfo qrCodeInfo;
    private AccountRequestManager requestManager;

    public interface AccountListener {
        void onFailure(int i, String str);

        void onSuccess(Map<String, String> map);
    }

    public interface AutoAccountListener {
        void onFailure(int i, String str);

        void onSuccess(AutoAccountBean autoAccountBean);
    }

    public interface MultiAccountHandler {
        void onMultiAccount(JSONArray jSONArray, String str, String str2);
    }

    public interface VerifyCodeListener {
        void onFailure(int i, String str);

        void onSuccess();
    }

    static /* synthetic */ int access$308(AccountLogic accountLogic) {
        int i = accountLogic.login_timeout_count;
        accountLogic.login_timeout_count = i + 1;
        return i;
    }

    private AccountLogic(Context context) {
        this.context = context;
        this.requestManager = new AccountRequestManager(context);
    }

    public static AccountLogic getInstance(Context context) {
        if (instance == null) {
            synchronized (AccountLogic.class) {
                if (instance == null) {
                    instance = new AccountLogic(context);
                }
            }
        }
        return instance;
    }

    public void accountLogin(final String str, final String str2, final boolean z, final AccountListener accountListener) {
        this.loginListener = accountListener;
        this.requestManager.loginRequest(str, encryptPwd(str2), z, new SqHttpCallback<String>() { // from class: com.sy37sdk.account.AccountLogic.1
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, final int i2, String str3, final String str4) {
                if (i2 == AccountLogic.SERVER_ERROR_CODE) {
                    AccountLogic.this.handleTriggerLoginAccount(str);
                } else {
                    AccountLogic.this.handleCaptcha(str4, str3, new CaptchaDialog.VerifyListener() { // from class: com.sy37sdk.account.AccountLogic.1.1
                        @Override // com.sy37sdk.account.captcha.CaptchaDialog.VerifyListener
                        public void result(boolean z2, String str5) {
                            if (z2) {
                                AccountLogic.this.accountLogin(str, str2, z, accountListener);
                            } else {
                                accountListener.onFailure(i2, str5);
                                BuglessAction.reportCatchException(new Exception("登录失败"), str5, str4, 11);
                            }
                        }
                    });
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str3) {
                AccountLogic.this.handleLoginSuccess(str3, str2, "1", false, "");
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str3, VolleyError volleyError) {
                if (volleyError instanceof TimeoutError) {
                    AccountLogic.access$308(AccountLogic.this);
                    if (AccountLogic.this.login_timeout_count >= AccountLogic.MAX_TIME_OUT) {
                        AccountLogic.this.handleTriggerLoginAccount(str);
                        return;
                    } else {
                        accountListener.onFailure(SDKError.NET_TIME_OUT_ERROR.code, SDKError.NET_TIME_OUT_ERROR.message);
                        return;
                    }
                }
                if (500 <= i && i <= 599) {
                    AccountLogic.this.handleTriggerLoginAccount(str);
                } else {
                    accountListener.onFailure(i, str3);
                }
            }
        });
    }

    public void accountLogin(String str, String str2, AccountListener accountListener) {
        accountLogin(str, str2, false, accountListener);
    }

    public void checkAccountList(final String str, final String str2, final MultiAccountHandler multiAccountHandler, final AccountListener accountListener) {
        this.requestManager.checkAccountList(str, encryptPwd(str2), new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.AccountLogic.2
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                try {
                    int iOptInt = jSONObject.optInt("total");
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("account_list");
                    if (jSONArrayOptJSONArray == null || iOptInt <= 0) {
                        accountListener.onFailure(-1, "check_account_list 接口返回错误，没有角色数据");
                        return;
                    }
                    if (iOptInt == 1) {
                        String strOptString = jSONArrayOptJSONArray.optJSONObject(0).optString("login_type");
                        if ("account".equals(strOptString)) {
                            AccountLogic.this.accountLogin(str, str2, accountListener);
                            return;
                        } else if ("phone_pwd".equals(strOptString)) {
                            AccountLogic.this.phoneLoginPwd(str, str2, accountListener);
                            return;
                        } else {
                            accountListener.onFailure(-1, "check_account_list 接口返回错误");
                            return;
                        }
                    }
                    multiAccountHandler.onMultiAccount(jSONArrayOptJSONArray, str, str2);
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(0);
                    JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(1);
                    String str3 = "";
                    String strOptString2 = jSONObjectOptJSONObject.optBoolean(SqR.string.ok) ? jSONObjectOptJSONObject.optString("uid") : "";
                    if (jSONObjectOptJSONObject2.optBoolean(SqR.string.ok)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(strOptString2);
                        if (!TextUtils.isEmpty(strOptString2)) {
                            str3 = ",";
                        }
                        sb.append(str3);
                        sb.append(jSONObjectOptJSONObject2.optString("uid"));
                        strOptString2 = sb.toString();
                    }
                    LoginTractionManager.trackCheckAccountListSucc(new String[]{jSONObjectOptJSONObject.optString("uid"), jSONObjectOptJSONObject2.optString("uid")}, strOptString2);
                } catch (Exception e) {
                    e.printStackTrace();
                    accountListener.onFailure(-1, "check_account_list 接口返回错误，json解析失败");
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str3, VolleyError volleyError) {
                accountListener.onFailure(i, str3);
                LoginTractionManager.trackCheckAccountListFail(str3);
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str3, String str4) {
                accountListener.onFailure(i2, str3);
                LoginTractionManager.trackCheckAccountListFail(str3);
            }
        });
    }

    public void accountRegister(final String str, final String str2, final AccountListener accountListener) {
        this.loginListener = accountListener;
        this.requestManager.registerRequest(str, encryptPwd(str2), new SqHttpCallback<String>() { // from class: com.sy37sdk.account.AccountLogic.3
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, final int i2, final String str3, final String str4) {
                AccountLogic.this.handleCaptcha(str4, str3, new CaptchaDialog.VerifyListener() { // from class: com.sy37sdk.account.AccountLogic.3.1
                    @Override // com.sy37sdk.account.captcha.CaptchaDialog.VerifyListener
                    public void result(boolean z, String str5) {
                        if (z) {
                            AccountLogic.this.accountRegister(str, str2, accountListener);
                        } else {
                            accountListener.onFailure(i2, str5);
                            BuglessAction.reportCatchException(new Exception("登录失败"), str3, str4, 11);
                        }
                    }
                });
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str3) {
                AccountCache.setAccountAlias(AccountLogic.this.context, "");
                if (str.equals(AccountCache.getAutoName(AccountLogic.this.context)) && AccountCache.getAutoState(AccountLogic.this.context)) {
                    AccountLogic.this.handleRegQrCode(str3);
                }
                AccountLogic.this.handleLoginSuccess(str3, str2, "1", true);
                LogUtil.d("账号注册成功");
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str3, VolleyError volleyError) {
                accountListener.onFailure(i, str3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCaptcha(String str, String str2, final CaptchaDialog.VerifyListener verifyListener) {
        try {
            final String strOptString = new JSONObject(str).optString("captcha_url", "");
            LogUtil.i("解析到 captchaUrl: " + strOptString);
            if (!TextUtils.isEmpty(strOptString)) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.sy37sdk.account.AccountLogic.4
                    @Override // java.lang.Runnable
                    public void run() {
                        CaptchaDialog captchaDialog = new CaptchaDialog(AccountLogic.this.context);
                        captchaDialog.setUrl(AppUtils.constructWebUrlParam(AccountLogic.this.context, strOptString));
                        captchaDialog.setVerifyListener(verifyListener);
                        captchaDialog.show();
                    }
                });
            } else if (verifyListener != null) {
                verifyListener.result(false, str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (verifyListener != null) {
                verifyListener.result(false, str2);
            }
        }
    }

    public void handlePhoneVerify(final String str, final VerifyPhoneDialog.VerifyListener verifyListener) {
        LogUtil.i("打开 verifyLink: " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        new Handler().post(new Runnable() { // from class: com.sy37sdk.account.AccountLogic.5
            @Override // java.lang.Runnable
            public void run() {
                VerifyPhoneDialog verifyPhoneDialog = new VerifyPhoneDialog(AccountLogic.this.context);
                verifyPhoneDialog.setUrl(str);
                verifyPhoneDialog.setVerifyListener(verifyListener);
                verifyPhoneDialog.show();
            }
        });
    }

    public void sendPhoneCode(final String str, final VerifyCodeListener verifyCodeListener) {
        LoginRequestManager.sendPhoneCode(str, new SqHttpCallback<Void>() { // from class: com.sy37sdk.account.AccountLogic.6
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, final int i2, String str2, String str3) {
                if (i2 != -77710) {
                    AccountLogic.this.handleCaptcha(str3, str2, new CaptchaDialog.VerifyListener() { // from class: com.sy37sdk.account.AccountLogic.6.1
                        @Override // com.sy37sdk.account.captcha.CaptchaDialog.VerifyListener
                        public void result(boolean z, String str4) {
                            if (z) {
                                AccountLogic.this.sendPhoneCode(str, verifyCodeListener);
                            } else {
                                verifyCodeListener.onFailure(i2, str4);
                            }
                        }
                    });
                } else {
                    verifyCodeListener.onFailure(PhonePresenter.CODE_PHONE_VALID, str2);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(Void r3) {
                AccountCache.setVerifyCodeLastTime(AccountLogic.this.context, System.currentTimeMillis());
                verifyCodeListener.onSuccess();
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                verifyCodeListener.onFailure(i, str2);
            }
        });
    }

    public void autoAccount(final AutoAccountListener autoAccountListener) {
        this.requestManager.autoAccountRequest(new SqHttpCallback<String>() { // from class: com.sy37sdk.account.AccountLogic.7
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                autoAccountListener.onFailure(i2, str);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str) {
                AutoAccountBean autoAccountBeanFromJson;
                try {
                    autoAccountBeanFromJson = AutoAccountBean.fromJson(str);
                    try {
                        autoAccountBeanFromJson.setPwd(AccountLogic.this.decryptPwd(autoAccountBeanFromJson.getPwd()));
                    } catch (JSONException e) {
                        e = e;
                        e.printStackTrace();
                        BuglessAction.reportCatchException(e, str, 10);
                    }
                } catch (JSONException e2) {
                    e = e2;
                    autoAccountBeanFromJson = null;
                }
                if (autoAccountBeanFromJson == null || !autoAccountBeanFromJson.isAutoAccount()) {
                    return;
                }
                autoAccountListener.onSuccess(autoAccountBeanFromJson);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                autoAccountListener.onFailure(i, str);
            }
        });
    }

    public void bindWxOpenId(String str, final IBindWxListener iBindWxListener) {
        this.requestManager.wxAuthRequest(str, new SqHttpCallback<String>() { // from class: com.sy37sdk.account.AccountLogic.8
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                iBindWxListener.onFailure(-1, "绑定微信openId出错");
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str2) {
                iBindWxListener.onSuccess(str2);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                iBindWxListener.onFailure(-1, "绑定微信openId出错" + str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRegQrCode(String str) {
        this.qrCodeInfo = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("qrcode")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("qrcode");
                String string = jSONObject2.getString("img");
                String string2 = jSONObject2.getString("content");
                LogUtil.d("qrCodeImgUrl: " + string + ", qrCodeImgContent: " + string2);
                this.qrCodeInfo = new QrCodeInfo(string, string2);
            }
        } catch (JSONException e) {
            LogUtil.d("获取qrCode异常");
            e.printStackTrace();
        }
    }

    public QrCodeInfo getQrCodeInfo() {
        return this.qrCodeInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLoginSuccess(String str, String str2, String str3, boolean z) {
        handleLoginSuccess(str, str2, str3, z, "");
    }

    public void handleLoginSuccess(String str, String str2, String str3, boolean z, String str4) {
        handleLoginSuccess(str, str2, AccountLoginType.parseLoginType(str3), str3, z, str4);
    }

    private void handleLoginSuccess(String str, String str2, String str3, String str4, boolean z, String str5) {
        this.loginData = str;
        try {
            UserInfo userInfoCreateUserInfo = createUserInfo(str, str2, str3, str4, z, str5);
            IConfigMod config = ModHelper.getConfig();
            if (config != null) {
                config.getCommonConfig().setUserId(userInfoCreateUserInfo.getUid());
            }
            UserNameEmptyTrackAction.report(UserNameEmptyTrackAction.ActionType.login, userInfoCreateUserInfo.getUname(), str);
            SQLog.i("【Login Logic】登录成功用户: " + userInfoCreateUserInfo);
            com.sqwan.common.user.UserInfo userInfoConvert = userInfoCreateUserInfo.convert();
            if (userInfoConvert != null) {
                UserInfoManager.getInstance().setLoginUser(userInfoConvert);
            }
            AccountCache.setUserInfo(this.context, userInfoCreateUserInfo);
            AccountTools.setAccountToFile(this.context, userInfoCreateUserInfo);
            UAgreeManager.getInstance().refreshVersion(userInfoCreateUserInfo.getUname());
            HashMap map = new HashMap();
            map.put(BaseSQwanCore.LOGIN_KEY_USERID, userInfoCreateUserInfo.getUid());
            map.put(BaseSQwanCore.LOGIN_KEY_USERNAME, userInfoCreateUserInfo.getUname());
            map.put("token", userInfoCreateUserInfo.getToken());
            map.put("pwd", userInfoCreateUserInfo.getUpwd());
            map.put("pid", ConfigManager.getInstance(this.context).getSQAppConfig().getPartner());
            map.put("gid", ConfigManager.getInstance(this.context).getSQAppConfig().getGameid());
            String actionType = userInfoCreateUserInfo.getActionType();
            String str6 = LoginTractionManager.TRACK_LOGIN_TYPE_REGISTER;
            boolean zEquals = actionType.equals(LoginTractionManager.TRACK_LOGIN_TYPE_REGISTER);
            String regWay = z ? "4" : AccountRegType.parseRegWay(str4);
            if (zEquals) {
                str3 = AccountRegType.parseRegType(regWay);
            }
            map.put(LoginTractionManager.TRACK_ACCOUNT_TYPE, LoginTractionManager.parseAccountType(str3));
            if (!userInfoCreateUserInfo.getActionType().equals(LoginTractionManager.TRACK_LOGIN_TYPE_REGISTER)) {
                str6 = "login";
            }
            map.put("login_type", str6);
            DeviceUtils.setLoginInvoked(this.context);
            this.loginListener.onSuccess(map);
            AccountCache.setLogined(this.context, true);
        } catch (Exception e) {
            SQLog.e("【Login Logic】处理用户信息异常, 登录失败", e);
            BuglessAction.reportCatchException(e, str, 11);
            this.loginListener.onFailure(SDKError.ACCOUNT_LOGIN_ERROR.code, SDKError.ACCOUNT_LOGIN_ERROR.message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTicket(String str, String str2, String str3, String str4) {
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUid("");
            userInfo.setUpwd("");
            userInfo.setUname(str3);
            userInfo.setToken("");
            userInfo.setRefreshToken("");
            userInfo.setAlias("");
            userInfo.setActionType("");
            userInfo.setLoginType(str);
            userInfo.setLoginWay(str2);
            userInfo.setMobile(str3);
            userInfo.setTicket(str4);
            IConfigMod config = ModHelper.getConfig();
            if (config != null) {
                config.getCommonConfig().setUserId(userInfo.getUid());
            }
            SQLog.i("【Login Logic】handleTicket-->user:" + userInfo);
            SQLog.i("【Login Logic】handleTicket-->Ticket:" + str4);
            com.sqwan.common.user.UserInfo userInfoConvert = userInfo.convert();
            if (userInfoConvert != null) {
                UserInfoManager.getInstance().setLoginUser(userInfoConvert);
            }
            AccountCache.setUserInfo(this.context, userInfo);
            AccountTools.setAccountToFile(this.context, userInfo);
        } catch (Exception e) {
            SQLog.e("【Login Logic】处理用户信息异常, 登录失败", e);
            BuglessAction.reportCatchException(e, "", 11);
            this.loginListener.onFailure(SDKError.ACCOUNT_LOGIN_ERROR.code, SDKError.ACCOUNT_LOGIN_ERROR.message);
        }
    }

    private UserInfo createUserInfo(String str, String str2, String str3, String str4, boolean z, String str5) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        if (z && jSONObject.has(SqConstants.UPWD)) {
            str2 = decryptPwd(jSONObject.optString(SqConstants.UPWD));
        }
        if (jSONObject.has("pwd")) {
            str2 = decryptPwd(jSONObject.optString("pwd"));
        }
        String strOptString = jSONObject.optString("uid");
        String strOptString2 = jSONObject.optString("uname");
        String strOptString3 = jSONObject.optString("token");
        String strOptString4 = jSONObject.optString(SqConstants.REFRESH_TOKEN);
        String strOptString5 = jSONObject.optString("login_account");
        String strOptString6 = jSONObject.optString("action_type");
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(strOptString);
        userInfo.setUpwd(!TextUtils.isEmpty(str2) ? ZipString.json2ZipString(str2) : "");
        userInfo.setUname(strOptString2);
        userInfo.setToken(strOptString3);
        userInfo.setRefreshToken(strOptString4);
        userInfo.setAlias(strOptString5);
        userInfo.setActionType(strOptString6);
        userInfo.setLoginType(str3);
        userInfo.setLoginWay(str4);
        userInfo.setMobile(str5);
        userInfo.setTicket("");
        return userInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTriggerLoginAccount(String str) {
        UserInfo userInfo = AccountCache.getUserInfo(this.context);
        if (userInfo == null || TextUtils.isEmpty(str) || ((TextUtils.isEmpty(userInfo.getUname()) || !str.equals(userInfo.getUname())) && (TextUtils.isEmpty(userInfo.getAlias()) || !str.equals(userInfo.getAlias())))) {
            userInfo = AccountUtil.findUserByUname(this.context, str);
        }
        handleTriggerLoginSuccess(userInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTriggerLoginPhone(String str) {
        UserInfo userInfo = AccountCache.getUserInfo(this.context);
        if (userInfo == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(userInfo.getActionType()) || !userInfo.getActionType().equals("2") || !str.equals(userInfo.getMobile())) {
            userInfo = AccountUtil.findUserByPhone(this.context, str);
        }
        handleTriggerLoginSuccess(userInfo);
    }

    private void handleTriggerLoginSuccess(UserInfo userInfo) {
        if (!EnvironmentUtils.isFileExits(this.context, "sq-trigger") && (!NetWorkUtils.isNetworkAvailable(this.context) || NetWorkUtils.isWifiProxy())) {
            this.loginListener.onFailure(SDKError.ACCOUNT_LOGIN_ERROR.code, SDKError.ACCOUNT_LOGIN_ERROR.message);
            return;
        }
        if (userInfo == null) {
            this.loginListener.onFailure(SDKError.ACCOUNT_LOGIN_ERROR.code, SDKError.ACCOUNT_LOGIN_ERROR.message);
            return;
        }
        this.login_timeout_count = 0;
        handleLoginSuccess(UserInfo.encodeToJson(userInfo).toString(), userInfo.getUpwd(), userInfo.getLoginType(), userInfo.getLoginWay(), false, userInfo.getMobile());
        try {
            LoginTrigger loginTrigger = new LoginTrigger();
            loginTrigger.setLoginType(userInfo.getLoginType().equals("2") ? "phone" : NetworkUtil.NET_TYPE_COMMON);
            loginTrigger.setTriggerTime(System.currentTimeMillis());
            loginTrigger.setUid(userInfo.getUid());
            loginTrigger.setUname(userInfo.getUname());
            loginTrigger.setToken(userInfo.getToken());
            LoginTriggerDBManager.getInstance().insertLoginTrigger(loginTrigger);
            LoginTriggerManager.getInstance().startQueryLoginTrigger();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLoginData() {
        return this.loginData;
    }

    public void phoneLoginCheckCode(final String str, String str2, final AccountListener accountListener) {
        this.loginListener = accountListener;
        LoginRequestManager.phoneLoginCheckCode(str, str2, new SqHttpCallback<String>() { // from class: com.sy37sdk.account.AccountLogic.9
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str3, String str4) {
                AccountLogic.this.loginListener.onFailure(i2, str3);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str3) {
                try {
                    String strOptString = new JSONObject(str3).optString("ticket");
                    AccountCache.setTicketState(true);
                    AccountLogic.this.handleTicket(AccountLoginType.parseLoginType("2"), "2", str, strOptString);
                    AccountLogic.this.phoneLoginTicket(strOptString, str, accountListener);
                } catch (JSONException e) {
                    e.printStackTrace();
                    AccountLogic.this.loginListener.onFailure(SDKError.ACCOUNT_LOGIN_ERROR.code, SDKError.ACCOUNT_LOGIN_ERROR.message);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str3, VolleyError volleyError) {
                if (i == 6003) {
                    AccountLogic.access$308(AccountLogic.this);
                    if (AccountLogic.this.login_timeout_count >= AccountLogic.MAX_TIME_OUT) {
                        AccountLogic.this.handleTriggerLoginPhone(str);
                        return;
                    } else {
                        AccountLogic.this.loginListener.onFailure(SDKError.NET_TIME_OUT_ERROR.code, SDKError.NET_TIME_OUT_ERROR.message);
                        return;
                    }
                }
                if (500 > i || i > 599) {
                    AccountLogic.this.loginListener.onFailure(i, str3);
                } else {
                    AccountLogic.this.handleTriggerLoginPhone(str);
                }
            }
        });
    }

    public void phoneLoginTicket(final String str, final String str2, final AccountListener accountListener) {
        this.loginListener = accountListener;
        LoginRequestManager.phoneLoginTicket(str, new SqHttpCallback<String>() { // from class: com.sy37sdk.account.AccountLogic.10
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, final int i2, final String str3, final String str4) {
                if (i2 == AccountLogic.SERVER_ERROR_CODE) {
                    AccountLogic.this.handleTriggerLoginPhone(str2);
                    return;
                }
                if (i2 == AccountLogic.SERVER_ERROR_CODE_TICKET_TIMEOUT) {
                    AccountLogic.this.loginListener.onFailure(i2, "请重新获取验证码，" + str3);
                    BuglessAction.reportCatchException(new Exception("登录失败"), str3, str4, 11);
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(str4);
                    String strOptString = jSONObject.optString("link");
                    if (!TextUtils.isEmpty(strOptString) && i2 == AccountLogic.SERVER_ERROR_CODE_LINK) {
                        AccountLogic.this.handlePhoneVerify(strOptString, new VerifyPhoneDialog.VerifyListener() { // from class: com.sy37sdk.account.AccountLogic.10.1
                            @Override // com.sy37sdk.account.captcha.VerifyPhoneDialog.VerifyListener
                            public void result(boolean z, String str5) {
                                if (!z) {
                                    AccountLogic.this.loginListener.onFailure(i2, str5);
                                    BuglessAction.reportCatchException(new Exception("登录失败"), str3, str5, 11);
                                } else {
                                    AccountLogic.this.handleLoginSuccess(str5, "", "2", false, str2);
                                }
                            }
                        });
                    } else if (!TextUtils.isEmpty(jSONObject.optString("captcha_url", ""))) {
                        AccountLogic.this.handleCaptcha(str4, str3, new CaptchaDialog.VerifyListener() { // from class: com.sy37sdk.account.AccountLogic.10.2
                            @Override // com.sy37sdk.account.captcha.CaptchaDialog.VerifyListener
                            public void result(boolean z, String str5) {
                                if (!z) {
                                    AccountLogic.this.loginListener.onFailure(i2, str5);
                                    BuglessAction.reportCatchException(new Exception("登录失败"), str3, str4, 11);
                                } else {
                                    AccountLogic.this.phoneLoginTicket(str, str2, accountListener);
                                }
                            }
                        });
                    } else {
                        AccountLogic.this.loginListener.onFailure(i2, str3);
                        BuglessAction.reportCatchException(new Exception("登录失败"), str3, str4, 11);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    AccountLogic.this.loginListener.onFailure(i2, str3);
                    BuglessAction.reportCatchException(new Exception("登录失败"), str3, str4, 11);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str3) {
                AccountLogic.this.handleLoginSuccess(str3, "", "2", false, str2);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str3, VolleyError volleyError) {
                if (i == 6003) {
                    AccountLogic.access$308(AccountLogic.this);
                    if (AccountLogic.this.login_timeout_count >= AccountLogic.MAX_TIME_OUT) {
                        AccountLogic.this.handleTriggerLoginPhone(str2);
                        return;
                    } else {
                        AccountLogic.this.loginListener.onFailure(SDKError.NET_TIME_OUT_ERROR.code, SDKError.NET_TIME_OUT_ERROR.message);
                        return;
                    }
                }
                if (500 > i || i > 599) {
                    AccountLogic.this.loginListener.onFailure(i, str3);
                } else {
                    AccountLogic.this.handleTriggerLoginPhone(str2);
                }
            }
        });
    }

    public void phoneLoginPwd(final String str, final String str2, final AccountListener accountListener) {
        this.loginListener = accountListener;
        LoginRequestManager.phoneLoginPwd(str, encryptPwd(str2), new SqHttpCallback<String>() { // from class: com.sy37sdk.account.AccountLogic.11
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, final int i2, final String str3, final String str4) {
                if (i2 == AccountLogic.SERVER_ERROR_CODE) {
                    AccountLogic.this.handleTriggerLoginPhone(str);
                } else {
                    AccountLogic.this.handleCaptcha(str4, str3, new CaptchaDialog.VerifyListener() { // from class: com.sy37sdk.account.AccountLogic.11.1
                        @Override // com.sy37sdk.account.captcha.CaptchaDialog.VerifyListener
                        public void result(boolean z, String str5) {
                            if (!z) {
                                AccountLogic.this.loginListener.onFailure(i2, str5);
                                BuglessAction.reportCatchException(new Exception("登录失败"), str3, str4, 11);
                            } else {
                                AccountLogic.this.phoneLoginPwd(str, str2, accountListener);
                            }
                        }
                    });
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str3) {
                AccountLogic.this.handleLoginSuccess(str3, str2, "5", false, str);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str3, VolleyError volleyError) {
                if (i == 6003) {
                    AccountLogic.access$308(AccountLogic.this);
                    if (AccountLogic.this.login_timeout_count >= AccountLogic.MAX_TIME_OUT) {
                        AccountLogic.this.handleTriggerLoginPhone(str);
                        return;
                    } else {
                        AccountLogic.this.loginListener.onFailure(SDKError.NET_TIME_OUT_ERROR.code, SDKError.NET_TIME_OUT_ERROR.message);
                        return;
                    }
                }
                if (500 > i || i > 599) {
                    AccountLogic.this.loginListener.onFailure(i, str3);
                } else {
                    AccountLogic.this.handleTriggerLoginPhone(str);
                }
            }
        });
    }

    public void fastVerifyLogin(String str, AccountListener accountListener, FastLoginManager.VerifyDialogListener verifyDialogListener) {
        this.loginListener = accountListener;
        SQLog.d("【Login Logic】校验闪验token");
        FastLoginHttpUtil.verifyFastToken(str, new AnonymousClass12(verifyDialogListener));
    }

    /* JADX INFO: renamed from: com.sy37sdk.account.AccountLogic$12, reason: invalid class name */
    class AnonymousClass12 extends SqHttpCallback<JSONObject> {
        final /* synthetic */ FastLoginManager.VerifyDialogListener val$verifyDialogListener;

        AnonymousClass12(FastLoginManager.VerifyDialogListener verifyDialogListener) {
            this.val$verifyDialogListener = verifyDialogListener;
        }

        @Override // com.sq.tool.network.SqHttpCallback
        public void onSuccess(JSONObject jSONObject) {
            try {
                String strOptString = jSONObject.optString("mobile");
                SQLog.i("【Login Logic】校验闪验token成功: " + strOptString);
                AccountLogic.this.handleLoginSuccess(jSONObject.toString(), "", "3", false, strOptString);
            } catch (Exception e) {
                SQLog.e("【Login Logic】校验闪验token未解析到手机号: " + jSONObject, e);
                AccountLogic.this.loginListener.onFailure(SDKError.NET_DATA_PARSE_ERROR.code, SDKError.NET_DATA_PARSE_ERROR.message);
            }
        }

        @Override // com.sdk.sq.net.SqRequestCallback
        public void onResponseStateError(int i, int i2, String str, String str2) {
            SQLog.e("【Login Logic】校验闪验token失败, code=" + i2 + ", msg=" + str);
            if (str2 == null || str2.isEmpty()) {
                AccountLogic.this.loginListener.onFailure(i2, str);
                BuglessAction.reportCatchException(new Exception("登录失败"), str, str2, 11);
                return;
            }
            try {
                String strOptString = new JSONObject(str2).optString("link");
                if (TextUtils.isEmpty(strOptString)) {
                    AccountLogic.this.loginListener.onFailure(i2, str);
                    BuglessAction.reportCatchException(new Exception("登录失败"), str, str2, 11);
                    return;
                }
                if (this.val$verifyDialogListener != null) {
                    this.val$verifyDialogListener.onShow();
                }
                AccountLogic accountLogic = AccountLogic.this;
                final FastLoginManager.VerifyDialogListener verifyDialogListener = this.val$verifyDialogListener;
                accountLogic.handlePhoneVerify(strOptString, new VerifyPhoneDialog.VerifyListener() { // from class: com.sy37sdk.account.-$$Lambda$AccountLogic$12$uxDTlQZHYqCplUG9J_AL5f9lSwY
                    @Override // com.sy37sdk.account.captcha.VerifyPhoneDialog.VerifyListener
                    public final void result(boolean z, String str3) {
                        this.f$0.lambda$onResponseStateError$0$AccountLogic$12(verifyDialogListener, z, str3);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                AccountLogic.this.loginListener.onFailure(i2, str);
                BuglessAction.reportCatchException(new Exception("登录失败"), str, str2, 11);
            }
        }

        public /* synthetic */ void lambda$onResponseStateError$0$AccountLogic$12(FastLoginManager.VerifyDialogListener verifyDialogListener, boolean z, String str) {
            String strOptString;
            if (z) {
                try {
                    strOptString = new JSONObject(str).optString("mobile");
                } catch (Exception e) {
                    e.printStackTrace();
                    strOptString = "";
                }
                AccountLogic.this.handleLoginSuccess(str, "", "3", false, strOptString);
            }
            if (verifyDialogListener != null) {
                verifyDialogListener.onClose(!z);
            }
        }

        @Override // com.sq.tool.network.SqHttpCallback
        public void onFailure(int i, String str, VolleyError volleyError) {
            SQLog.e("【Login Logic】校验闪验token异常, code=" + i + ", msg=" + str);
            AccountLogic.this.loginListener.onFailure(SDKError.NET_REQUEST_FAIL.code, str);
        }
    }

    public void fastLogin(UserInfo userInfo, AccountListener accountListener) {
        this.loginListener = accountListener;
        SQLog.d("【Login Logic】快速登录");
        FastLoginHttpUtil.fastLogin(userInfo, new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.AccountLogic.13
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                SQLog.e("【Login Logic】快速登录失败 " + str + "(" + i2 + ")");
                AccountLogic.this.loginListener.onFailure(i2, str);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                SQLog.i("【Login Logic】快速登录成功");
                AccountLogic.this.handleFastLoginSuccess(jSONObject);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                SQLog.e("【Login Logic】快速登录异常 " + str + "(" + i + ")");
                AccountLogic.this.loginListener.onFailure(i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleFastLoginSuccess(org.json.JSONObject r10) {
        /*
            r9 = this;
            java.lang.String r0 = ""
            r1 = 0
            java.lang.String r2 = "uname"
            java.lang.String r2 = r10.optString(r2)     // Catch: java.lang.Exception -> L3c
            java.lang.String r3 = "token"
            java.lang.String r3 = r10.optString(r3)     // Catch: java.lang.Exception -> L3c
            java.lang.String r4 = "action_type"
            java.lang.String r0 = r10.optString(r4)     // Catch: java.lang.Exception -> L38
            android.content.Context r10 = r9.context     // Catch: java.lang.Exception -> L38
            java.lang.String r10 = com.sy37sdk.account.AccountCache.getUsername(r10)     // Catch: java.lang.Exception -> L38
            boolean r10 = r2.equals(r10)     // Catch: java.lang.Exception -> L38
            if (r10 == 0) goto L29
            android.content.Context r10 = r9.context     // Catch: java.lang.Exception -> L38
            com.sy37sdk.account.UserInfo r10 = com.sy37sdk.account.AccountCache.getUserInfo(r10)     // Catch: java.lang.Exception -> L38
        L27:
            r1 = r10
            goto L43
        L29:
            android.content.Context r10 = r9.context     // Catch: java.lang.Exception -> L38
            com.sy37sdk.account.UserInfo r10 = com.sy37sdk.account.util.AccountUtil.findUserByUname(r10, r2)     // Catch: java.lang.Exception -> L38
            if (r10 == 0) goto L43
            android.content.Context r10 = r9.context     // Catch: java.lang.Exception -> L38
            com.sy37sdk.account.UserInfo r10 = com.sy37sdk.account.util.AccountUtil.findUserByUname(r10, r2)     // Catch: java.lang.Exception -> L38
            goto L27
        L38:
            r10 = move-exception
            r2 = r0
            r0 = r3
            goto L3e
        L3c:
            r10 = move-exception
            r2 = r0
        L3e:
            r10.printStackTrace()
            r3 = r0
            r0 = r2
        L43:
            if (r1 == 0) goto L67
            r1.setToken(r3)
            r1.setActionType(r0)
            org.json.JSONObject r10 = com.sy37sdk.account.UserInfo.encodeToJson(r1)
            java.lang.String r3 = r10.toString()
            java.lang.String r4 = r1.getUpwd()
            java.lang.String r5 = r1.getLoginType()
            r7 = 0
            java.lang.String r8 = r1.getMobile()
            java.lang.String r6 = "6"
            r2 = r9
            r2.handleLoginSuccess(r3, r4, r5, r6, r7, r8)
            goto L77
        L67:
            java.lang.String r10 = "【Login Logic】用户信息无效, 登录失败"
            com.sq.tool.logger.SQLog.e(r10)
            com.sy37sdk.account.AccountLogic$AccountListener r10 = r9.loginListener
            com.sqwan.common.util.SDKError r0 = com.sqwan.common.util.SDKError.NET_REQUEST_FAIL
            int r0 = r0.code
            java.lang.String r1 = "快速登录失败"
            r10.onFailure(r0, r1)
        L77:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy37sdk.account.AccountLogic.handleFastLoginSuccess(org.json.JSONObject):void");
    }

    public void wechatLogin(AccountListener accountListener) {
        this.loginListener = accountListener;
        socialLoginWechat(new OnAuthListener() { // from class: com.sy37sdk.account.AccountLogic.14
            public void onSuccess(PlatformType platformType, Bundle bundle) {
                Logger.info("onSuccess bundle:", new Object[0]);
                String string = bundle.getString("code");
                LoginTractionManager.trackWechatSuccess(string);
                AccountLogic.this.requestWechatLogin(string);
            }

            public void onCancel(PlatformType platformType) {
                ToastUtil.showToast(AccountLogic.this.context, "取消微信登录");
                Logger.info("wechat login onCancel", new Object[0]);
                if (AccountLogic.this.loginListener != null) {
                    AccountLogic.this.loginListener.onFailure(203, "取消微信登录");
                }
                LoginTractionManager.trackWechatFail("取消微信登录");
            }

            public void onFailure(PlatformType platformType, String str) {
                ToastUtil.showToast(AccountLogic.this.context, str);
                Logger.info("wechat login onFailure,msg:" + str, new Object[0]);
                AccountLogic.this.loginListener.onFailure(203, str);
                LoginTractionManager.trackWechatFail(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestWechatLogin(String str) {
        LoginRequestManager.wechatLogin(str, new SqHttpCallback<String>() { // from class: com.sy37sdk.account.AccountLogic.15
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, final int i2, final String str2, final String str3) {
                AccountLogic.this.handleCaptcha(str3, str2, new CaptchaDialog.VerifyListener() { // from class: com.sy37sdk.account.AccountLogic.15.1
                    @Override // com.sy37sdk.account.captcha.CaptchaDialog.VerifyListener
                    public void result(boolean z, String str4) {
                        if (z) {
                            AccountLogic.this.wechatLogin(AccountLogic.this.loginListener);
                            return;
                        }
                        if (AccountLogic.this.loginListener != null) {
                            AccountLogic.this.loginListener.onFailure(i2, str4);
                        }
                        BuglessAction.reportCatchException(new Exception("登录失败"), str2, str3, 11);
                    }
                });
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str2) {
                AccountLogic.this.handleLoginSuccess(str2, "", "8", false);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                LogUtil.i("快速登录失败 " + volleyError);
                if (AccountLogic.this.loginListener != null) {
                    AccountLogic.this.loginListener.onFailure(i, str2);
                }
            }
        });
    }

    private void socialLoginWechat(OnAuthListener onAuthListener) {
        Context context = this.context;
        if (context instanceof Activity) {
            SocialApi.getInstance().authorize((Activity) this.context, PlatformType.WECHAT, onAuthListener);
            return;
        }
        ToastUtil.showToast(context, "微信登录错误，请联系客服【10001】");
        Logger.info("context is not Activity", new Object[0]);
        LoginTractionManager.trackWechatFail("context异常");
        AccountListener accountListener = this.loginListener;
        if (accountListener != null) {
            accountListener.onFailure(203, "context异常");
        }
    }

    private String encryptPwd(String str) {
        String strEncodeToString = "";
        try {
            strEncodeToString = Base64.encodeToString(AESUtil.encrypt(str, getEncodeKey()), 2);
            LogUtil.i("加密后：" + strEncodeToString);
            return strEncodeToString;
        } catch (Exception e) {
            e.printStackTrace();
            return strEncodeToString;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String decryptPwd(String str) {
        try {
            return AESUtil.decryptString(str, getEncodeKey());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getEncodeKey() {
        String appKey = ConfigManager.getInstance(this.context).getAppKey();
        if (TextUtils.isEmpty(appKey)) {
            return "";
        }
        int length = appKey.length();
        if (length < 16) {
            StringBuilder sb = new StringBuilder(appKey);
            for (int i = 0; i < 16 - length; i++) {
                sb.append("0");
            }
            return sb.toString();
        }
        return appKey.substring(0, 16);
    }
}
