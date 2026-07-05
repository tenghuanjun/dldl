package com.sy37sdk.account.alifast;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.AuthUIControlClickListener;
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper;
import com.mobile.auth.gatewayauth.TokenResultListener;
import com.mobile.auth.gatewayauth.model.TokenRet;
import com.sq.tool.logger.SQLog;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.dialog.LoadingDialog;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.AESUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.msdk.api.SQResultListener;
import com.sqwan.msdk.config.ConfigManager;
import com.sy37sdk.account.AccountLogic;
import com.sy37sdk.account.LoginTractionManager;
import com.sy37sdk.account.alifast.FastLoginConstants;
import com.sy37sdk.account.alifast.config.BaseUIConfig;
import com.sy37sdk.account.uagree.UAgreeManager;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FastLoginManager {
    private static final String TAG = "【Login Fast】";
    private static FastLoginManager instance;
    private String accessKey = "";
    private Activity activity;
    private FastLoginListener fastLoginListener;
    private LoadingDialog loadingDialog;
    private Context mContext;
    private PhoneNumberAuthHelper mPhoneNumberAuthHelper;
    private TokenResultListener mTokenResultListener;
    private BaseUIConfig mUIConfig;
    private boolean requestedAccessKey;
    private WeChatLoginListener weChatLoginListener;

    public interface FastLoginListener {
        void onFastLoginFail(Bundle bundle);

        void onFastLoginSuccess(Map<String, String> map);

        void onFastRelease();

        void onVerifyAccount(boolean z);
    }

    public interface VerifyDialogListener {
        void onClose(boolean z);

        void onShow();
    }

    public interface WeChatLoginListener {
        void onWeChatLoginFail(int i, String str);

        void onWeChatLoginSuccess(Map<String, String> map);
    }

    private FastLoginManager(Context context) {
        this.mContext = context;
    }

    public static FastLoginManager getInstance(Context context) {
        if (instance == null) {
            synchronized (FastLoginManager.class) {
                if (instance == null) {
                    instance = new FastLoginManager(context);
                }
            }
        }
        return instance;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void doFastVerifyLogin(FastLoginListener fastLoginListener, WeChatLoginListener weChatLoginListener) {
        this.fastLoginListener = fastLoginListener;
        this.weChatLoginListener = weChatLoginListener;
        showLoading();
        if (requestedAccessKey()) {
            oneKeyLogin();
        } else {
            SQLog.d("【Login Fast】请求闪验配置");
            FastLoginHttpUtil.requestFastConfig(new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.alifast.FastLoginManager.1
                @Override // com.sdk.sq.net.SqRequestCallback
                public void onResponseStateError(int i, int i2, String str, String str2) {
                    SQLog.w("【Login Fast】请求闪验配置失败, " + i2 + ", " + str);
                    FastLoginManager.this.disableFastLogin(FastLoginConstants.Code.FAILURE_REQUEST_CONFIG, FastLoginConstants.MESSAGE.FAILURE_REQUEST_CONFIG);
                }

                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(JSONObject jSONObject) {
                    SQLog.i("【Login Fast】请求闪验配置成功");
                    try {
                        FastLoginManager.this.accessKey = FastLoginManager.this.decodeAccessKey(jSONObject.optString("access_key"));
                        if (!TextUtils.isEmpty(FastLoginManager.this.accessKey)) {
                            FastLoginManager.this.requestedAccessKey = true;
                            FastLoginManager.this.oneKeyLogin();
                        } else {
                            SQLog.w("【Login Fast】获取闪验key失败, " + jSONObject);
                            FastLoginManager.this.disableFastLogin("2", FastLoginConstants.MESSAGE.FAILURE_NOT_CONFIG);
                        }
                    } catch (Exception unused) {
                        SQLog.e("【Login Fast】解析闪验配置出错, " + jSONObject);
                        FastLoginManager.this.disableFastLogin("1", FastLoginConstants.MESSAGE.FAILURE_PARSE_CONFIG);
                    }
                }

                @Override // com.sq.tool.network.SqHttpCallback
                public void onFailure(int i, String str, VolleyError volleyError) {
                    SQLog.e("【Login Fast】请求闪验配置异常, " + i + ", " + str);
                    FastLoginManager.this.disableFastLogin("3", FastLoginConstants.MESSAGE.FAILURE_CONFIG_ERROR);
                }
            });
        }
    }

    public void initFastAccessKey(final SQResultListener sQResultListener) {
        showLoading();
        if (requestedAccessKey()) {
            hideLoading();
            sQResultListener.onSuccess((Bundle) null);
        } else {
            SQLog.d("【Login Fast】请求闪验配置");
            FastLoginHttpUtil.requestFastConfig(new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.alifast.FastLoginManager.2
                @Override // com.sdk.sq.net.SqRequestCallback
                public void onResponseStateError(int i, int i2, String str, String str2) {
                    SQLog.w("【Login Fast】请求闪验配置失败, " + i2 + ", " + str);
                    sQResultListener.onFailture(i2, "请求闪验配置失败, " + i2 + ", " + str);
                    FastLoginManager.this.hideLoading();
                }

                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(JSONObject jSONObject) {
                    SQLog.i("【Login Fast】请求闪验配置成功");
                    try {
                        FastLoginManager.this.accessKey = FastLoginManager.this.decodeAccessKey(jSONObject.optString("access_key"));
                        if (!TextUtils.isEmpty(FastLoginManager.this.accessKey)) {
                            FastLoginManager.this.requestedAccessKey = true;
                            sQResultListener.onSuccess((Bundle) null);
                        } else {
                            SQLog.w("【Login Fast】获取闪验key失败, " + jSONObject);
                            sQResultListener.onFailture(0, "获取闪验key失败, " + jSONObject);
                        }
                    } catch (Exception unused) {
                        SQLog.e("【Login Fast】解析闪验配置出错, " + jSONObject);
                        sQResultListener.onFailture(0, "解析闪验配置出错, " + jSONObject);
                        FastLoginManager.this.hideLoading();
                    }
                    FastLoginManager.this.hideLoading();
                }

                @Override // com.sq.tool.network.SqHttpCallback
                public void onFailure(int i, String str, VolleyError volleyError) {
                    SQLog.e("【Login Fast】请求闪验配置异常, " + i + ", " + str);
                    sQResultListener.onFailture(i, "请求闪验配置异常, " + i + ", " + str);
                    FastLoginManager.this.hideLoading();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disableFastLogin(String str, String str2) {
        SQLog.w("【Login Fast】禁用闪验登录, code=" + str + ", msg=" + str2);
        Bundle bundle = new Bundle();
        bundle.putString("code", str);
        bundle.putString("msg", str2);
        disableFastLogin(bundle);
    }

    private void disableFastLogin(Bundle bundle) {
        if (this.fastLoginListener != null) {
            SQLog.w("【Login Fast】回调闪验失败");
            this.fastLoginListener.onFastLoginFail(bundle);
        }
        hideLoading();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String decodeAccessKey(String str) {
        String strSubstring;
        String appKey = ConfigManager.getInstance(this.mContext).getAppKey();
        if (TextUtils.isEmpty(appKey)) {
            strSubstring = "";
        } else {
            int length = appKey.length();
            if (length < 16) {
                StringBuilder sb = new StringBuilder(appKey);
                for (int i = 0; i < 16 - length; i++) {
                    sb.append("0");
                }
                strSubstring = sb.toString();
            } else {
                strSubstring = appKey.substring(0, 16);
            }
        }
        return AESUtil.decryptString(str, strSubstring);
    }

    public void oneKeyLogin() {
        try {
            fastInit();
            fastLogin();
        } catch (Exception e) {
            e.printStackTrace();
            disableFastLogin(FastLoginConstants.Code.FAILURE_NOT_SUPPORT, FastLoginConstants.MESSAGE.FAILURE_NOT_SUPPORT);
        }
    }

    public boolean requestedAccessKey() {
        return this.requestedAccessKey;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fastInit() {
        SQLog.d("【Login Fast】初始化闪验");
        TokenResultListener tokenResultListener = new TokenResultListener() { // from class: com.sy37sdk.account.alifast.FastLoginManager.3
            public void onTokenSuccess(String str) {
                FastLoginManager.this.hideLoading();
                try {
                    TokenRet tokenRetFromJson = TokenRet.fromJson(str);
                    if ("600001".equals(tokenRetFromJson.getCode())) {
                        SQLog.i("【Login Fast】唤起授权页成功: " + str);
                    } else if ("600000".equals(tokenRetFromJson.getCode())) {
                        SQLog.i("【Login Fast】获取token成功: " + str);
                        FastLoginManager.this.mPhoneNumberAuthHelper.hideLoginLoading();
                        FastLoginManager.this.verifyFastToken(tokenRetFromJson.getToken());
                    }
                } catch (Exception e) {
                    SQLog.e("【Login Fast】解析token异常", e);
                    FastLoginManager.this.disableFastLogin("4", FastLoginConstants.MESSAGE.FAILURE_PARSE_ALI);
                }
            }

            public void onTokenFailed(String str) {
                SQLog.w("【Login Fast】获取token失败: " + str);
                FastLoginManager.this.mPhoneNumberAuthHelper.hideLoginLoading();
                FastLoginManager.this.hideLoading();
                try {
                    TokenRet tokenRetFromJson = TokenRet.fromJson(str);
                    if ("700000".equals(tokenRetFromJson.getCode())) {
                        FastLoginManager.this.disableFastLogin("5", FastLoginConstants.MESSAGE.CANCEL);
                        if (FastLoginManager.this.fastLoginListener != null) {
                            FastLoginManager.this.fastLoginListener.onFastRelease();
                        }
                        FastLoginManager.this.quitLoginPage();
                        return;
                    }
                    if ("600023".equals(tokenRetFromJson.getCode())) {
                        FastLoginManager.this.disableFastLogin(tokenRetFromJson.getCode(), tokenRetFromJson.getMsg());
                        if (FastLoginManager.this.fastLoginListener != null) {
                            FastLoginManager.this.fastLoginListener.onFastRelease();
                        }
                        FastLoginManager.this.quitLoginPage();
                        return;
                    }
                    FastLoginManager.this.disableFastLogin(tokenRetFromJson.getCode(), tokenRetFromJson.getMsg());
                } catch (Exception e) {
                    SQLog.e("【Login Fast】解析token异常", e);
                    FastLoginManager.this.disableFastLogin("6", FastLoginConstants.MESSAGE.FAILURE_PARSE_ALI_FAILURE);
                }
            }
        };
        this.mTokenResultListener = tokenResultListener;
        this.mPhoneNumberAuthHelper = PhoneNumberAuthHelper.getInstance(this.mContext, tokenResultListener);
        Context applicationContext = SQContextWrapper.getApplicationContext();
        if (applicationContext != null) {
            this.mPhoneNumberAuthHelper.getReporter().setLoggerEnable((applicationContext.getApplicationInfo().flags & 2) != 0);
        }
        this.mPhoneNumberAuthHelper.setAuthSDKInfo(this.accessKey);
        this.mPhoneNumberAuthHelper.setUIClickListener(new AuthUIControlClickListener() { // from class: com.sy37sdk.account.alifast.FastLoginManager.4
            public void onClick(String str, Context context, String str2) {
                SQLog.d("【Login Fast】授权页点击 code=" + str + ", data=" + str2);
                FastLoginManager.this.handleClickBtn(str, context, str2);
            }
        });
        this.mUIConfig = BaseUIConfig.init(7, (Activity) this.mContext, this.mPhoneNumberAuthHelper);
        this.mPhoneNumberAuthHelper = PhoneNumberAuthHelper.getInstance(this.mContext, this.mTokenResultListener);
        this.mUIConfig.configAuthPage();
        this.mUIConfig.setOnClickFastLoginListener(new BaseUIConfig.OnClickFastLoginListener() { // from class: com.sy37sdk.account.alifast.FastLoginManager.5
            @Override // com.sy37sdk.account.alifast.config.BaseUIConfig.OnClickFastLoginListener
            public void clickOtherLogin() {
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.ali_login_phone_login, SqTrackBtn.SqTrackBtnExt.ali_login_phone_login);
                SQLog.d("【Login Fast】闪验点击其他方式登录");
                FastLoginManager.this.disableFastLogin(FastLoginConstants.Code.FAILURE_CLICK_OTHER_WAY, FastLoginConstants.MESSAGE.FAILURE_CLICK_OTHER_WAY);
            }

            @Override // com.sy37sdk.account.alifast.config.BaseUIConfig.OnClickFastLoginListener
            public void clickClose() {
                SQLog.d("【Login Fast】闪验点击关闭");
                FastLoginManager.this.disableFastLogin("5", FastLoginConstants.MESSAGE.CANCEL);
                if (FastLoginManager.this.fastLoginListener != null) {
                    FastLoginManager.this.fastLoginListener.onFastRelease();
                }
                FastLoginManager.this.quitLoginPage();
            }

            @Override // com.sy37sdk.account.alifast.config.BaseUIConfig.OnClickFastLoginListener
            public void clickBack() {
                SQLog.d("【Login Fast】闪验点击返回");
                FastLoginManager.this.disableFastLogin(FastLoginConstants.Code.FAILURE_CLICK_BACK, FastLoginConstants.MESSAGE.FAILURE_CLICK_BACK);
            }

            @Override // com.sy37sdk.account.alifast.config.BaseUIConfig.OnClickFastLoginListener
            public void clickWechatLogin() {
                SQLog.d("【Login Fast】点击微信登录");
                FastLoginManager.this.hideLoading();
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.ali_login_wechat_login, SqTrackBtn.SqTrackBtnExt.ali_login_wechat_login);
                if (FastLoginManager.this.mPhoneNumberAuthHelper.queryCheckBoxIsChecked()) {
                    FastLoginManager.this.showLoading();
                    LoginTractionManager.trackInvoke("3", "8");
                    AccountLogic.getInstance(FastLoginManager.this.mContext).wechatLogin(new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.alifast.FastLoginManager.5.1
                        @Override // com.sy37sdk.account.AccountLogic.AccountListener
                        public void onSuccess(Map<String, String> map) {
                            SQLog.d("【Login Fast】微信登录成功");
                            FastLoginManager.this.hideLoading();
                            if (FastLoginManager.this.weChatLoginListener != null) {
                                FastLoginManager.this.weChatLoginListener.onWeChatLoginSuccess(map);
                            }
                            FastLoginManager.this.quitLoginPage();
                        }

                        @Override // com.sy37sdk.account.AccountLogic.AccountListener
                        public void onFailure(int i, String str) {
                            SQLog.e("【Login Fast】微信登录失败: " + i + ", " + str);
                            FastLoginManager.this.hideLoading();
                            if (FastLoginManager.this.weChatLoginListener != null) {
                                FastLoginManager.this.weChatLoginListener.onWeChatLoginFail(i, str);
                            }
                        }
                    });
                    return;
                }
                UAgreeManager.getInstance().showUAgreeToast();
            }

            @Override // com.sy37sdk.account.alifast.config.BaseUIConfig.OnClickFastLoginListener
            public void clickAccountLogin() {
                SQLog.d("【Login Fast】闪验点击账号密码登录");
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.ali_login_account_login, SqTrackBtn.SqTrackBtnExt.ali_login_account_login);
                FastLoginManager.this.disableFastLogin(FastLoginConstants.Code.FAILURE_CLICK_ACCOUNT_LOGIN, FastLoginConstants.MESSAGE.FAILURE_CLICK_ACCOUNT_LOGIN);
            }
        });
    }

    private void fastLogin() {
        SQLog.d("【Login Fast】拉起闪验授权页");
        this.mPhoneNumberAuthHelper.getLoginToken(this.mContext, 5000);
    }

    public void getFastEnv(final FastBooleanResultListener fastBooleanResultListener) {
        SQLog.i("【Login Fast】检查闪验环境");
        initFastAccessKey(new SQResultListener() { // from class: com.sy37sdk.account.alifast.FastLoginManager.6
            public void onSuccess(Bundle bundle) {
                if (FastLoginManager.this.mPhoneNumberAuthHelper == null) {
                    SQLog.e("【Login Fast】mPhoneNumberAuthHelper为空，初始化闪验");
                    FastLoginManager.this.fastInit();
                }
                boolean zCheckEnvAvailable = FastLoginManager.this.mPhoneNumberAuthHelper.checkEnvAvailable();
                SQLog.i("【Login Fast】闪验环境检测结果：" + zCheckEnvAvailable);
                fastBooleanResultListener.callback(zCheckEnvAvailable);
            }

            public void onFailture(int i, String str) {
                SQLog.e("【Login Fast】闪验配置获取失败，闪验环境返回false");
                fastBooleanResultListener.callback(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleClickBtn(java.lang.String r2, android.content.Context r3, java.lang.String r4) {
        /*
            r1 = this;
            int r3 = r2.hashCode()
            r0 = 1
            switch(r3) {
                case 1620409947: goto L13;
                case 1620409948: goto L9;
                default: goto L8;
            }
        L8:
            goto L1d
        L9:
            java.lang.String r3 = "700003"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L1d
            r2 = 1
            goto L1e
        L13:
            java.lang.String r3 = "700002"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L1d
            r2 = 0
            goto L1e
        L1d:
            r2 = -1
        L1e:
            java.lang.String r3 = "isChecked"
            if (r2 == 0) goto L41
            if (r2 == r0) goto L25
            goto L64
        L25:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Exception -> L3c
            r2.<init>(r4)     // Catch: java.lang.Exception -> L3c
            boolean r2 = r2.optBoolean(r3)     // Catch: java.lang.Exception -> L3c
            if (r2 == 0) goto L64
            com.sqwan.common.track.SqTrackActionManager2 r2 = com.sqwan.common.track.SqTrackActionManager2.getInstance()     // Catch: java.lang.Exception -> L3c
            java.lang.String r3 = "btn07"
            java.lang.String r4 = "同意用户协议"
            r2.trackBtn(r3, r4)     // Catch: java.lang.Exception -> L3c
            goto L64
        L3c:
            r2 = move-exception
            r2.printStackTrace()
            goto L64
        L41:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Exception -> L60
            r2.<init>(r4)     // Catch: java.lang.Exception -> L60
            boolean r2 = r2.optBoolean(r3)     // Catch: java.lang.Exception -> L60
            if (r2 == 0) goto L58
            com.sqwan.common.track.SqTrackActionManager2 r2 = com.sqwan.common.track.SqTrackActionManager2.getInstance()     // Catch: java.lang.Exception -> L60
            java.lang.String r3 = "btn05"
            java.lang.String r4 = "点击一键进入"
            r2.trackBtn(r3, r4)     // Catch: java.lang.Exception -> L60
            goto L64
        L58:
            com.sy37sdk.account.uagree.UAgreeManager r2 = com.sy37sdk.account.uagree.UAgreeManager.getInstance()     // Catch: java.lang.Exception -> L60
            r2.showUAgreeToast()     // Catch: java.lang.Exception -> L60
            goto L64
        L60:
            r2 = move-exception
            r2.printStackTrace()
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy37sdk.account.alifast.FastLoginManager.handleClickBtn(java.lang.String, android.content.Context, java.lang.String):void");
    }

    public void verifyFastToken(String str) {
        showLoading();
        AccountLogic.getInstance(this.mContext).fastVerifyLogin(str, new AccountLogic.AccountListener() { // from class: com.sy37sdk.account.alifast.FastLoginManager.7
            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onSuccess(Map<String, String> map) {
                FastLoginManager.this.hideLoading();
                LoginTractionManager.track("3", map);
                if (FastLoginManager.this.fastLoginListener != null) {
                    FastLoginManager.this.fastLoginListener.onFastLoginSuccess(map);
                }
                if (FastLoginManager.this.fastLoginListener != null) {
                    FastLoginManager.this.fastLoginListener.onFastRelease();
                }
                FastLoginManager.this.quitLoginPage();
            }

            @Override // com.sy37sdk.account.AccountLogic.AccountListener
            public void onFailure(int i, String str2) {
                FastLoginManager.this.hideLoading();
                LogUtil.i("校验闪验token失败 ");
                ToastUtil.showToast(FastLoginManager.this.mContext, str2);
                FastLoginManager.this.disableFastLogin(FastLoginConstants.Code.FAILURE_VERIFY_FAIL, FastLoginConstants.MESSAGE.FAILURE_VERIFY_FAIL);
            }
        }, new VerifyDialogListener() { // from class: com.sy37sdk.account.alifast.FastLoginManager.8
            @Override // com.sy37sdk.account.alifast.FastLoginManager.VerifyDialogListener
            public void onShow() {
                FastLoginManager.this.quitLoginPage();
            }

            @Override // com.sy37sdk.account.alifast.FastLoginManager.VerifyDialogListener
            public void onClose(boolean z) {
                if (FastLoginManager.this.fastLoginListener != null) {
                    FastLoginManager.this.fastLoginListener.onVerifyAccount(z);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoading() {
        if (this.loadingDialog == null) {
            LoadingDialog loadingDialog = new LoadingDialog(this.mContext);
            this.loadingDialog = loadingDialog;
            loadingDialog.setCancelable(false);
        }
        LoadingDialog loadingDialog2 = this.loadingDialog;
        if (loadingDialog2 == null || loadingDialog2.isShowing()) {
            return;
        }
        this.loadingDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideLoading() {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return;
        }
        this.loadingDialog.dismiss();
    }

    public void quitLoginPage() {
        SQLog.w("【Login Fast】退出登录页");
        PhoneNumberAuthHelper phoneNumberAuthHelper = this.mPhoneNumberAuthHelper;
        if (phoneNumberAuthHelper != null) {
            phoneNumberAuthHelper.setAuthListener((TokenResultListener) null);
            FastLoginListener fastLoginListener = this.fastLoginListener;
            if (fastLoginListener != null) {
                fastLoginListener.onFastRelease();
            }
            this.mPhoneNumberAuthHelper.quitLoginPage();
        }
    }
}
