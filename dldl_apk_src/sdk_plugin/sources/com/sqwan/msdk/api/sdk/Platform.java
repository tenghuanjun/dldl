package com.sqwan.msdk.api.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.parameters.bean.WebDialogBean;
import com.parameters.utils.ClassCheckUtils;
import com.sq.eventbus.core.EventBus;
import com.sq.oaid.sq_oaid.IdsBean;
import com.sq.oaid.sq_oaid.SqOAIDHelper;
import com.sq.tool.logger.SQLog;
import com.sq.tool.network.EventReporter;
import com.sq.tool.network.ExceptionReporter;
import com.sqwan.base.EventDispatcher;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.dialog.CommonAlertDialog;
import com.sqwan.common.eventbus.OnActivityResultEvent;
import com.sqwan.common.net.risk.RiskWebActivity;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.user.RoleInfo;
import com.sqwan.common.user.UserInfoManager;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.BaseSQwanCore;
import com.sqwan.msdk.SQReportCore;
import com.sqwan.msdk.SQwanCore;
import com.sqwan.msdk.api.InitBean;
import com.sqwan.msdk.api.MRequestManager;
import com.sqwan.msdk.api.MultiSDKUtils;
import com.sqwan.msdk.api.PluginContext;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.api.SQResultListener;
import com.sqwan.msdk.api.SQSdkInterface;
import com.sqwan.msdk.api.tool.IScreenshotListener;
import com.sqwan.msdk.config.MultiSdkManager;
import com.sqwan.msdk.utils.ViewUtils;
import com.sqwan.msdk.utils.ZipString;
import com.sqwan.msdk.views.SQActivationCodeDialog;
import com.sqwan.order.base.IPay;
import com.sqwan.order.base.PayContext;
import com.sqwan.order.base.PayInfoModel;
import com.sqwan.order.base.SqPayError;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.age.AppropriateAge;
import com.sy37sdk.account.age.AppropriateAgeCacheHelper;
import com.sy37sdk.account.age.AppropriateAgeManager;
import com.sy37sdk.account.auth.AuthConfigCache;
import com.sy37sdk.account.auth.AuthManager;
import com.sy37sdk.account.config.ConfigManager;
import com.sy37sdk.account.entrance.EntranceManager;
import com.sy37sdk.account.scanCode.ScanCodeCameraActivity;
import com.sy37sdk.core.SQScreenshotListener;
import com.sy37sdk.core.SQwan;
import com.sy37sdk.order.SQPay;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class Platform implements SQSdkInterface {
    protected static final String TAG = "【Platform】";
    protected static SQResultListener back2GameListener;
    protected static Context context;
    protected static InitBean init;
    protected static SQResultListener initListener;
    protected static SQResultListener joinToRoomListener;
    protected static SQResultListener listener;
    protected static SQResultListener memberVoiceListener;
    protected static SQResultListener quitToRoomListener;
    public static SQResultListener shareListener;
    protected static SQResultListener speachInitListener;
    protected static SQwan sq;
    protected static SQResultListener statusUpdateListener;
    protected static SQResultListener switchAccountListener;
    public static boolean upingData25g;
    protected String codeOfLogin;
    private IScreenshotListener mScreenshotListener;
    protected MRequestManager requestManager;
    private static final SQPay sSQPay = new SQPay();
    private static String debug4cp = null;
    protected HashMap<String, String> userMap = new HashMap<>();
    protected boolean isNeedInputMoney = true;
    protected String pdata = "";
    protected HashMap<String, String> loginCallbackExtendParams = null;
    private Handler handler = new Handler(Looper.getMainLooper());

    public void forbidMemberVoice(int i, boolean z) {
    }

    protected IPay getPlatformPay() {
        return null;
    }

    protected abstract void initPlatform();

    protected abstract void loginPlatform(SQResultListener sQResultListener);

    public void logout(Context context2, SQResultListener sQResultListener) {
    }

    protected abstract void payPlatform(Context context2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, float f, int i2, String str9, String str10, SQResultListener sQResultListener);

    public void printLog(int i, String str, String str2) {
    }

    public void showExitDailog(Context context2, SQResultListener sQResultListener) {
    }

    public Platform(Context context2, InitBean initBean, SQResultListener sQResultListener) {
        this.requestManager = null;
        sendLogPlatform("Platform的初始化");
        init = initBean;
        context = context2;
        initListener = sQResultListener;
        this.requestManager = new MRequestManager(context2);
        this.codeOfLogin = MultiSDKUtils.getCodeOfLogin(context);
        sendLogPlatform("调用初始化信息, id:" + init.getAppid() + " key:" + init.getAppkey() + " codeOfLogin:" + this.codeOfLogin);
        EventDispatcher.getInstance().clear();
    }

    public void init(Context context2) {
        if (isCut2SQ()) {
            sendLogPlatform("单平台初始化");
            initSQ(false);
        } else {
            sendLogPlatform("多平台初始化");
            initPlatform();
        }
        sSQPay.init(context2);
        SqPayManager.getInstance().setPlatform(this);
        SqPayManager.getInstance().setSQPay(sSQPay);
        SqPayManager.getInstance().setPlatformPay(getPlatformPay());
    }

    public void login(Context context2, SQResultListener sQResultListener) {
        SQLog.d("【Platform】调用login");
        listener = sQResultListener;
        if (upingData25g) {
            SQLog.w("【Platform】upingData25g, 忽略调用");
            ViewUtils.showToast(context2, "处理中，请稍候.");
            return;
        }
        if (context2 == null) {
            SQLog.e("【Platform】context为空, 忽略调用");
            return;
        }
        upingData25g = true;
        if (isCut2SQ()) {
            SQLog.d("【Platform】login-切37");
            upingData25g = false;
            loginSQ(sQResultListener);
        } else {
            SQLog.d("【Platform】login-Platform: " + getClass().getSimpleName());
            loginPlatform(sQResultListener);
        }
    }

    protected void loginSuccessCallBack(String str, final SQResultListener sQResultListener) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("state");
            JSONObject jSONObject2 = jSONObject.getJSONObject(FunctionRouter.KEY_DATA);
            if (i == 1) {
                String string = jSONObject2.getString("token");
                String string2 = jSONObject2.getString("uid");
                String string3 = jSONObject2.getString("uname");
                String string4 = jSONObject2.getString("puid");
                String string5 = jSONObject2.getString("puname");
                if (jSONObject2.has("pwd")) {
                    sendLogPlatform("sq loginSuccessCallBack,has pwd!");
                    String string6 = jSONObject2.getString("pwd");
                    if (!TextUtils.isEmpty(string3) && !TextUtils.isEmpty(string6)) {
                        MultiSDKUtils.setPassword(context, ZipString.json2ZipString(string6));
                    }
                }
                MultiSDKUtils.setToken(context, string);
                MultiSDKUtils.setUserid(context, string2);
                MultiSDKUtils.setUsername(context, string3);
                MultiSDKUtils.setPlatUserid(context, string4);
                MultiSDKUtils.setPlatUsername(context, string5);
                final Bundle bundle = new Bundle();
                bundle.putString("token", string);
                bundle.putString(BaseSQwanCore.LOGIN_KEY_USERID, string2);
                bundle.putString(BaseSQwanCore.LOGIN_KEY_USERNAME, string3);
                SQAppConfig appConfig = SQwanCore.getInstance().getAppConfig();
                bundle.putString("gid", appConfig.getGameid());
                bundle.putString("pid", appConfig.getPartner());
                if (this.loginCallbackExtendParams != null) {
                    for (Map.Entry<String, String> entry : this.loginCallbackExtendParams.entrySet()) {
                        bundle.putString(entry.getKey(), entry.getValue());
                    }
                }
                sendLogPlatform("调用登录接口:与37交互成功，返回onSuccess信息:" + bundle.toString());
                if (!jSONObject2.isNull(BaseSQwanCore.LOGIN_KEY_BETA)) {
                    String string7 = jSONObject2.getJSONObject(BaseSQwanCore.LOGIN_KEY_BETA).toString();
                    if (!TextUtils.isEmpty(string7)) {
                        MultiSDKUtils.showActivationCodeDialog(context, string7, new SQActivationCodeDialog.CheckActivationCodeCallback() { // from class: com.sqwan.msdk.api.sdk.Platform.1
                            @Override // com.sqwan.msdk.views.SQActivationCodeDialog.CheckActivationCodeCallback
                            public void onActiveSucecess() {
                                sQResultListener.onSuccess(bundle);
                            }
                        });
                    } else {
                        MultiSDKUtils.hideActivationCodeDialog();
                        sQResultListener.onSuccess(bundle);
                    }
                } else {
                    MultiSDKUtils.hideActivationCodeDialog();
                    sQResultListener.onSuccess(bundle);
                }
            } else if (i == 0) {
                String string8 = jSONObject.getString("msg");
                sQResultListener.onFailture(203, string8);
                MultiSDKUtils.showTips(context, string8);
                sendLogPlatform("登录回调监听：37服务器返回错误信息：" + string8);
            }
            if (jSONObject2.isNull(BaseSQwanCore.LOGIN_KEY_NURL)) {
                return;
            }
            jSONObject2.getString(BaseSQwanCore.LOGIN_KEY_NURL);
        } catch (Exception e) {
            e.printStackTrace();
            BuglessAction.reportCatchException(e, str, 3);
            sQResultListener.onFailture(203, "登录失败，请稍后重试");
            sendLogPlatform("登录回调监听：处理37交互得到信息出现异常：" + e.getMessage());
        }
    }

    public void changeAccount(Context context2, SQResultListener sQResultListener) {
        sendLogPlatform("changeAccount");
    }

    public void setSwitchAccountListener(SQResultListener sQResultListener) {
        sendLogPlatform("setSwitchAccountListener");
        switchAccountListener = sQResultListener;
    }

    public void setBackToGameLoginListener(SQResultListener sQResultListener) {
        sendLogPlatform("设置回到游戏登录界面监听");
        back2GameListener = sQResultListener;
    }

    public void pay(Context context2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, float f, int i2, final SQResultListener sQResultListener) {
        PayContext payContext = new PayContext("pay");
        PayInfoModel payInfoModel = new PayInfoModel();
        payInfoModel.setOrderId(str);
        payInfoModel.setProductName(str2);
        payInfoModel.setCurrencyName(str3);
        payInfoModel.setServerId(str4);
        payInfoModel.setServerName(str5);
        payInfoModel.setExtend(str6);
        payInfoModel.setRoleId(str7);
        payInfoModel.setRoleName(str8);
        payInfoModel.setRoleLevel(i);
        payInfoModel.setMoney(f);
        payInfoModel.setRadio(i2);
        SqTrackActionManager2.getInstance().trackActionCPTest(SqTrackAction2.PAY_ORDER, payInfoModel.getDataMap());
        payContext.setPayInfo(payInfoModel);
        Bundle bundle = new Bundle();
        bundle.putString(SqPayManager.EXTRA_PDATA, this.pdata);
        SqPayManager.getInstance().pay((Activity) context2, payContext, payInfoModel, bundle, new IPay.PayCallback() { // from class: com.sqwan.msdk.api.sdk.Platform.2
            @Override // com.sqwan.order.base.IPay.PayCallback
            public void onSuccess(PayContext payContext2) {
                Bundle bundle2 = new Bundle();
                bundle2.putString(SqConstants.MOID, payContext2.getMoid());
                sQResultListener.onSuccess(bundle2);
            }

            @Override // com.sqwan.order.base.IPay.PayCallback
            public void onCancel(PayContext payContext2) {
                sQResultListener.onFailture(205, "取消支付【20005】");
            }

            @Override // com.sqwan.order.base.IPay.PayCallback
            public void onFailed(PayContext payContext2, SqPayError sqPayError) {
                sQResultListener.onFailture(sqPayError.code, sqPayError.msg);
            }
        });
    }

    public void setContext(Context context2) {
        context = context2;
    }

    public void onStart() {
        sendLogPlat4CP("onStart()");
    }

    public void onRestart() {
        sendLogPlat4CP("onRestart()");
    }

    public void onResume() {
        sendLogPlat4CP("onResume()");
    }

    public void onPause() {
        sendLogPlat4CP("onPause()");
    }

    public void onStop() {
        sendLogPlat4CP("onStop()");
    }

    public void onDestroy() {
        sendLogPlat4CP("onDestroy()");
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        sendLogPlat4CP("onActivityResult()");
        OnActivityResultEvent onActivityResultEvent = new OnActivityResultEvent(i, i2, intent);
        EventBus.getDefault().post(onActivityResultEvent);
        EventDispatcher.getInstance().dispatcherActivityResultListener(onActivityResultEvent);
    }

    public void onNewIntent(Intent intent) {
        sendLogPlat4CP("onNewIntent()");
    }

    public void creatRoleInfo(HashMap<String, String> map) {
        sendLogPlat4CP("创建角色信息接口:--->" + map.toString());
        UserInfoManager.getInstance().setCurrentRoleInfo(RoleInfo.fromRoleMap(map));
    }

    public void upgradeRoleInfo(HashMap<String, String> map) {
        sendLogPlat4CP("角色升级信息接口:--->" + map.toString());
        UserInfoManager.getInstance().setCurrentRoleInfo(RoleInfo.fromRoleMap(map));
    }

    public void submitRoleInfo(HashMap<String, String> map) {
        sendLogPlat4CP("提交角色信息接口:--->" + map.toString());
        this.userMap = map;
        UserInfoManager.getInstance().setCurrentRoleInfo(RoleInfo.fromRoleMap(map));
        sq.submitRoleInfo(map);
    }

    public void creatRole(Context context2, String str) {
        sendLogPlat4CP("不再使用！调用创建角色信息接口，serverId：" + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendLogPlatform(String str) {
        LogUtil.w("Platform-->" + str);
    }

    public static String mapToJson(HashMap<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            try {
                jSONObject.put(str, map.get(str));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    public boolean isCut2SQ() {
        String codeOfLogin = MultiSDKUtils.getCodeOfLogin(context);
        this.codeOfLogin = codeOfLogin;
        return "1".equals(codeOfLogin);
    }

    public static synchronized void initSQ(boolean z) {
        sendLogPlatform("37是否初始化？=" + BaseSQwanCore.isInitSqwan);
        if (!BaseSQwanCore.isInitSqwan) {
            sq = SQwan.getInstance();
            SQwan.isLoad = z;
            sq.init(context, ZipString.zipString2Json(MultiSDKUtils.getKey(context)), new com.sy37sdk.core.SQResultListener() { // from class: com.sqwan.msdk.api.sdk.Platform.3
                @Override // com.sy37sdk.core.SQResultListener
                public void onSuccess(Bundle bundle) {
                    if (Platform.initListener != null) {
                        Platform.initListener.onSuccess(bundle);
                        Platform.sendLogPlatform("初始化sq成功");
                        EntranceManager.getInstance().requestEntranceConfig(Platform.context);
                        return;
                    }
                    System.err.println("SQ initListener is NULL!");
                }

                @Override // com.sy37sdk.core.SQResultListener
                public void onFailture(int i, String str) {
                    if (Platform.initListener != null) {
                        Platform.initListener.onFailture(i, str);
                    } else {
                        System.err.println("SQ initListener is NULL!");
                    }
                }
            });
            if (switchAccountListener != null) {
                setSwitchAccountListenerSQ(switchAccountListener);
            }
            BaseSQwanCore.isInitSqwan = true;
        }
    }

    public void loginSQ(final SQResultListener sQResultListener) {
        upingData25g = false;
        if (sq == null) {
            SQLog.e("【Platform】sq未初始化! 初始化之");
            initSQ(false);
        } else {
            SQLog.d("【Platform】37登录: " + sq.getClass().getSimpleName());
        }
        sq.login(context, new com.sy37sdk.core.SQResultListener() { // from class: com.sqwan.msdk.api.sdk.Platform.4
            @Override // com.sy37sdk.core.SQResultListener
            public void onSuccess(Bundle bundle) {
                SQLog.i("【Platform】37登录成功, " + bundle);
                Platform.this.sqLoginSuccess(Platform.context, sQResultListener, bundle);
            }

            @Override // com.sy37sdk.core.SQResultListener
            public void onFailture(int i, String str) {
                SQLog.e("【Platform】37登录失败, code=" + i + ", msg=" + str);
                SQResultListener sQResultListener2 = sQResultListener;
                if (sQResultListener2 != null) {
                    sQResultListener2.onFailture(i, str);
                }
                MultiSDKUtils.showTips(Platform.context, str);
            }
        });
    }

    @Deprecated
    public static void paySQ(Context context2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, float f, int i2, String str9, String str10, final SQResultListener sQResultListener) {
        PayContext payContext = new PayContext("static pay");
        PayInfoModel payInfoModel = new PayInfoModel();
        payInfoModel.setOrderId(str);
        payInfoModel.setProductName(str2);
        payInfoModel.setCurrencyName(str3);
        payInfoModel.setServerId(str4);
        payInfoModel.setServerName(str5);
        payInfoModel.setExtend(str6);
        payInfoModel.setRoleId(str7);
        payInfoModel.setRoleName(str8);
        payInfoModel.setRoleLevel(i);
        payInfoModel.setMoney(f);
        payInfoModel.setRadio(i2);
        payContext.setPayInfo(payInfoModel);
        payContext.setMoid(str9);
        payContext.setRawOrderData(str10);
        sSQPay.pay((Activity) context2, payContext, payInfoModel, null, false, new IPay.PayCallback() { // from class: com.sqwan.msdk.api.sdk.Platform.5
            @Override // com.sqwan.order.base.IPay.PayCallback
            public void onSuccess(PayContext payContext2) {
                Bundle bundle = new Bundle();
                bundle.putString(SqConstants.MOID, payContext2.getMoid());
                sQResultListener.onSuccess(bundle);
            }

            @Override // com.sqwan.order.base.IPay.PayCallback
            public void onCancel(PayContext payContext2) {
                sQResultListener.onFailture(205, "取消支付【20005】");
            }

            @Override // com.sqwan.order.base.IPay.PayCallback
            public void onFailed(PayContext payContext2, SqPayError sqPayError) {
                sQResultListener.onFailture(sqPayError.code, sqPayError.msg);
            }
        });
    }

    public void changeAccountSQ(final Context context2, final SQResultListener sQResultListener) {
        if (sq == null) {
            sendLogPlatform("SQ未初始化!初始化之");
            initSQ(false);
        }
        upingData25g = false;
        sq.changeAccount(context2, new com.sy37sdk.core.SQResultListener() { // from class: com.sqwan.msdk.api.sdk.Platform.6
            @Override // com.sy37sdk.core.SQResultListener
            public void onSuccess(Bundle bundle) {
                Platform.sendLogPlatform("SQ主动切换账号成功");
                Platform.this.sqLoginSuccess(context2, sQResultListener, bundle);
            }

            @Override // com.sy37sdk.core.SQResultListener
            public void onFailture(int i, String str) {
                Platform.sendLogPlatform("SQ主动切换账号失败");
                sQResultListener.onFailture(i, str);
            }
        });
    }

    public static void setSwitchAccountListenerSQ(final SQResultListener sQResultListener) {
        sendLogPlatform("37 setSwitchAccountListenerSQ 设置监听");
        SQwan sQwan = sq;
        if (sQwan != null) {
            if (sQResultListener != null) {
                sQwan.setSwitchAccountListener(new com.sy37sdk.core.SQResultListener() { // from class: com.sqwan.msdk.api.sdk.Platform.7
                    @Override // com.sy37sdk.core.SQResultListener
                    public void onSuccess(Bundle bundle) {
                        Platform.sendLogPlatform("SQ悬浮窗切换账号成功");
                        sQResultListener.onSuccess(bundle);
                    }

                    @Override // com.sy37sdk.core.SQResultListener
                    public void onFailture(int i, String str) {
                        Platform.sendLogPlatform("SQ悬浮窗切换账号失败");
                        sQResultListener.onFailture(i, str);
                    }
                });
            } else {
                sendLogPlatform("37切换账号监听为空");
            }
        }
    }

    public void setBackToGameListenerSQ(final SQResultListener sQResultListener) {
        sendLogPlatform("37 setBackToGameListenerSQ 设置监听");
        SQwan sQwan = sq;
        if (sQwan != null) {
            if (sQResultListener != null) {
                sQwan.setBackToGameLoginListener(new com.sy37sdk.core.SQResultListener() { // from class: com.sqwan.msdk.api.sdk.Platform.8
                    @Override // com.sy37sdk.core.SQResultListener
                    public void onSuccess(Bundle bundle) {
                        Platform.sendLogPlatform("sq回到游戏成功");
                        sQResultListener.onSuccess(bundle);
                    }

                    @Override // com.sy37sdk.core.SQResultListener
                    public void onFailture(int i, String str) {
                        Platform.sendLogPlatform("sq回到游戏失败");
                        sQResultListener.onFailture(i, str);
                    }
                });
            } else {
                sendLogPlatform("37回到游戏监听为空");
            }
        }
    }

    public void setScreenshotListenerSQ(final IScreenshotListener iScreenshotListener) {
        sendLogPlatform("37 setScreenshotListenerSQ 设置监听");
        SQwan sQwan = sq;
        if (sQwan != null) {
            if (iScreenshotListener != null) {
                sQwan.setScreenshotListener(new SQScreenshotListener() { // from class: com.sqwan.msdk.api.sdk.Platform.9
                    @Override // com.sy37sdk.core.SQScreenshotListener
                    public Bitmap createScreenshot() {
                        return iScreenshotListener.createScreenshot();
                    }
                });
            } else {
                sendLogPlatform("37悬浮窗截图监听为空");
            }
        }
    }

    public void showSQWebDialog(String str) {
        MultiSDKUtils.showSQWebDialog(context, transformURLForChannel(str));
    }

    public void showSQPersonalDialog(Context context2) {
        LogUtil.w("Platform-->打开实名制弹窗");
        if (AuthConfigCache.getIsAuth()) {
            LogUtil.w("Platform-->已经实名制了");
            ViewUtils.showToast(context2, "您已经注册了实名制了");
            return;
        }
        String personalDurl = AuthConfigCache.getPersonalDurl();
        LogUtil.w("Platform--->实名制url:" + personalDurl);
        AuthManager.getInstance(context2).showAuthDialog(personalDurl, false, false, null);
    }

    public String transformURLForChannel(String str) {
        return MultiSDKUtils.getPID(context).endsWith("1") ? MultiSDKUtils.constructCommonURL(context, str) : str;
    }

    public void logoutSQ(Context context2, final SQResultListener sQResultListener) {
        SQwan sQwan;
        if (sq == null) {
            sendLogPlatform("SQ未初始化!初始化之");
            initSQ(false);
        }
        InitBean initBean = init;
        if (initBean != null && initBean.getUseSQExit() == 1 && (sQwan = sq) != null) {
            sQwan.logout(context2, new com.sy37sdk.core.SQResultListener() { // from class: com.sqwan.msdk.api.sdk.Platform.10
                @Override // com.sy37sdk.core.SQResultListener
                public void onSuccess(Bundle bundle) {
                    sQResultListener.onSuccess(bundle);
                }

                @Override // com.sy37sdk.core.SQResultListener
                public void onFailture(int i, String str) {
                    sQResultListener.onFailture(i, str);
                }
            });
        } else {
            new CommonAlertDialog.Builder(context2).setTitle("您确定退出游戏吗？").setPositiveButton("确定", new View.OnClickListener() { // from class: com.sqwan.msdk.api.sdk.Platform.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    sQResultListener.onSuccess(new Bundle());
                }
            }).setNegativeButton("取消", null).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sqLoginSuccess(Context context2, SQResultListener sQResultListener, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        String string = bundle.getString(BaseSQwanCore.LOGIN_KEY_USERID);
        String string2 = bundle.getString(BaseSQwanCore.LOGIN_KEY_USERNAME);
        String string3 = bundle.getString("token");
        MultiSDKUtils.setUserid(context2, string);
        MultiSDKUtils.setUsername(context2, string2);
        MultiSDKUtils.setToken(context2, string3);
        SQAppConfig appConfig = SQwanCore.getInstance().getAppConfig();
        bundle2.putString("token", string3);
        bundle2.putString("gid", appConfig.getGameid());
        bundle2.putString("pid", appConfig.getPartner());
        bundle2.putString(BaseSQwanCore.LOGIN_KEY_USERID, string);
        bundle2.putString(BaseSQwanCore.LOGIN_KEY_USERNAME, string2);
        MultiSDKUtils.hideActivationCodeDialog();
        sQResultListener.onSuccess(bundle2);
    }

    public void speechInit(Context context2, SQResultListener sQResultListener) {
        sendLogPlatform("语音接口实例化");
        speachInitListener = sQResultListener;
    }

    public void setServerInfo(String str) {
        sendLogPlatform("服务器配置");
    }

    public void poll() {
        sendLogPlatform("回调函数驱动");
    }

    public void joinNationalRoom(String str, int i, int i2) {
        sendLogPlatform("进入国战语音房间");
    }

    public void joinTeamRoom(String str, int i) {
        sendLogPlatform("进入小组语音房间");
    }

    public void quitRoom(String str, int i) {
        sendLogPlatform("退出语音房间");
    }

    public void openMic() {
        sendLogPlatform("打开麦克风");
    }

    public void closeMic() {
        sendLogPlatform("关闭麦克风");
    }

    public void openSpeaker() {
        sendLogPlatform("打开扬声器");
    }

    public void closeSpeaker() {
        sendLogPlatform("关闭扬声器");
    }

    public void setMicLevel(int i) {
        sendLogPlatform("设置麦克风音量");
    }

    public int getMicLevel() {
        sendLogPlatform("获取麦克风音量");
        return 0;
    }

    public void setSpeakerVolume(int i) {
        sendLogPlatform("设置扬声器音量");
    }

    public int getSpeakerVolume() {
        sendLogPlatform("获取扬声器音量");
        return 0;
    }

    public boolean testMic() {
        sendLogPlatform("设置测试麦克风是否可用");
        return false;
    }

    public void enableSpeakerOn(boolean z) {
        sendLogPlatform("设置是否开启扬声器");
    }

    public void onJoinRoomListener(Context context2, SQResultListener sQResultListener) {
        sendLogPlatform("设置进入语音房间监听");
        joinToRoomListener = sQResultListener;
    }

    public void onQuitRoomListener(Context context2, SQResultListener sQResultListener) {
        sendLogPlatform("设置退出语音房间监听");
        quitToRoomListener = sQResultListener;
    }

    public void onMemberVoiceListener(Context context2, SQResultListener sQResultListener) {
        sendLogPlatform("设置成员状态监听");
        memberVoiceListener = sQResultListener;
    }

    public void onStatusUpdateListener(Context context2, SQResultListener sQResultListener) {
        sendLogPlatform("设置掉线监听");
        statusUpdateListener = sQResultListener;
    }

    public void setScreenshotListener(IScreenshotListener iScreenshotListener) {
        this.mScreenshotListener = iScreenshotListener;
    }

    public void performFeatureBBS() {
        sendLogPlatform("应用宝BBS论坛");
    }

    public void performFeatureVPlayer() {
        sendLogPlatform("应用宝V+特权");
    }

    public void performFeature(Context context2, String str, Object obj, SQResultListener sQResultListener) {
        sendLogPlatform("通用扩展接口  type = " + str + " data = " + obj);
        if (TextUtils.isEmpty(str)) {
            sendLogPlatform("type字段不能传空");
            sQResultListener.onFailture(203, "type不能传空");
        }
        if (!ClassCheckUtils.isExistPerformFeatureConfig()) {
            if ("showTransparentWebDialog".equals(str)) {
                sendLogPlatform("接收到显示透明web页面协议");
                MultiSDKUtils.showSQWebDialog(context2, transformURLForChannel((String) obj));
                return;
            }
            return;
        }
        switch (str) {
            case "showTransparentWebDialog":
                sendLogPlatform("接收到显示透明web页面协议");
                if (obj instanceof String) {
                    MultiSDKUtils.showSQWebDialog(context2, transformURLForChannel((String) obj));
                    break;
                }
                break;
            case "authResultCheck":
                setAuthResultListener(sQResultListener);
                break;
            case "age_appropriate_icon":
                requestAgeAppropriate(sQResultListener);
                break;
            case "showAgeAppropriate":
                AppropriateAgeManager.getInstance().showAppropriateAgeDialog(context2);
                break;
            case "showWebDialog":
                if (obj instanceof String) {
                    WebDialogBean toObject = WebDialogBean.parseToObject((String) obj);
                    toObject.setUrl(transformURLForChannel(toObject.getUrl()));
                    MultiSDKUtils.showSQWebDialog(context2, toObject);
                    break;
                }
                break;
            case "getSupplierId":
                getSupplierId(sQResultListener);
                break;
            case "reportPurchaseData":
                if (obj instanceof String) {
                    reportPurchaseData((String) obj);
                    break;
                }
                break;
            case "scan_login":
                doScanLogin();
                break;
            case "support_scan_login":
                if (sQResultListener != null) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("show_scan_login", !TextUtils.isEmpty(AccountCache.getToken(context2)) && EntranceManager.getInstance().isSupportScanLogin());
                    sQResultListener.onSuccess(bundle);
                    break;
                }
                break;
            case "showGoodReview":
                if (sQResultListener != null) {
                    showGoodReview(context2, sQResultListener);
                    break;
                }
                break;
            case "policy":
                if (sQResultListener != null) {
                    handlePolicy(context2, obj, sQResultListener);
                    break;
                }
                break;
            default:
                sQResultListener.onFailture(203, "不支持的type类型");
                break;
        }
    }

    private void reportPurchaseData(String str) {
        try {
            SQReportCore.getInstance().eventCpPay(str);
        } catch (Exception e) {
            e.printStackTrace();
            listener.onFailture(203, "reportPurchaseData处理错误");
        }
    }

    private void getSupplierId(final SQResultListener sQResultListener) {
        String mDevIds = MultiSDKUtils.getMDevIds(context);
        if (!TextUtils.isEmpty(mDevIds)) {
            Bundle bundle = new Bundle();
            try {
                JSONObject jSONObject = new JSONObject(mDevIds);
                String strOptString = jSONObject.optString("oaid");
                String strOptString2 = jSONObject.optString("aaid");
                String strOptString3 = jSONObject.optString("vaid");
                if (!TextUtils.isEmpty(strOptString)) {
                    bundle.putString("oaid", strOptString);
                    bundle.putString("vaid", strOptString3);
                    bundle.putString("aaid", strOptString2);
                    sQResultListener.onSuccess(bundle);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SqOAIDHelper sqOAIDHelper = new SqOAIDHelper(new SqOAIDHelper.Callback() { // from class: com.sqwan.msdk.api.sdk.Platform.12
            @Override // com.sq.oaid.sq_oaid.SqOAIDHelper.Callback
            public void onIdsValid(IdsBean idsBean) {
                if (sQResultListener == null || idsBean == null) {
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("oaid", idsBean.getOaid());
                bundle2.putString("vaid", idsBean.getVaid());
                bundle2.putString("aaid", idsBean.getAaid());
                sQResultListener.onSuccess(bundle2);
            }
        }, new EventReporter(), new ExceptionReporter());
        Context context2 = context;
        sqOAIDHelper.getDeviceIds(new PluginContext(context2, context2.getResources()));
    }

    private void requestAgeAppropriate(SQResultListener sQResultListener) {
        AppropriateAge appropriateAge = AppropriateAgeCacheHelper.getAppropriateAge(context);
        if (appropriateAge == null) {
            AppropriateAgeManager.getInstance().refreshConfig(sQResultListener);
            return;
        }
        String appropriateIconUrl = (appropriateAge.isStatus() && appropriateAge.getTiming() != null && appropriateAge.getTiming().contains("2")) ? AppropriateAgeManager.getInstance().getAppropriateIconUrl() : "";
        Bundle bundle = new Bundle();
        bundle.putString("age_appropriate_icon", appropriateIconUrl);
        LogUtil.i("通过缓存回调适龄提醒url：" + appropriateIconUrl);
        sQResultListener.onSuccess(bundle);
    }

    private void doScanLogin() {
        if (TextUtils.isEmpty(AccountCache.getToken(context))) {
            MultiSDKUtils.showTips(context, "请先完成登录");
            return;
        }
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setClass(context, ScanCodeCameraActivity.class);
        intent.putExtra(RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION, "portrait");
        context.startActivity(intent);
    }

    public void showGoodReview(Context context2, SQResultListener sQResultListener) {
        LogUtil.i("调用了 Platform.showGoodReview");
        String commentInfo = ConfigManager.getInstance().getCommentInfo(context2);
        ConfigManager.getInstance().setCommentListener(sQResultListener);
        try {
            JSONObject jSONObject = new JSONObject(commentInfo);
            String strOptString = jSONObject.optString("picture");
            String strOptString2 = jSONObject.optString("status");
            String strOptString3 = jSONObject.optString("cp_control");
            ConfigManager.getInstance().setJumpUrl(jSONObject.optString("url"));
            if (!strOptString2.equals("1")) {
                sQResultListener.onFailture(3, "弹窗配置已关");
                return;
            }
            if (strOptString3.equals("1")) {
                ConfigManager.getInstance().jumpComment();
                return;
            }
            if (strOptString3.equals("2")) {
                LogUtil.i("调用了 showGoodReview打开弹窗url：" + strOptString);
                MultiSDKUtils.showSQWebDialog(context2, transformURLForChannel(strOptString));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            sQResultListener.onFailture(3, "打开弹窗异常:" + e.getMessage());
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void handlePolicy(Context context2, Object obj, SQResultListener sQResultListener) {
        String string;
        LogUtil.i("调用了 Platform.handlePolicy");
        if (obj instanceof String) {
            LogUtil.i("handlePolicy传入参数为String类型");
            try {
                string = new JSONObject((String) obj).getString("type");
                LogUtil.i("handlePolicy type为：" + string);
            } catch (JSONException e) {
                e.printStackTrace();
                ConfigManager.getInstance().handlePolicyFailure(101, "传入data参数类型有误，解析String类型data异常", SqTrackAction2.POLICY_ERROR, sQResultListener);
                return;
            }
        } else if (obj instanceof JSONObject) {
            LogUtil.i("handlePolicy传入参数为JSONObject类型");
            try {
                string = ((JSONObject) obj).getString("type");
                LogUtil.i("handlePolicy type为：" + string);
            } catch (JSONException e2) {
                e2.printStackTrace();
                ConfigManager.getInstance().handlePolicyFailure(102, "传入data参数类型有误，解析json类型data异常", SqTrackAction2.POLICY_ERROR, sQResultListener);
                return;
            }
        } else {
            ConfigManager.getInstance().handlePolicyFailure(103, "传入data参数类型有误，要求String或者json类型", SqTrackAction2.POLICY_ERROR, sQResultListener);
            return;
        }
        if (string.isEmpty()) {
            ConfigManager.getInstance().handlePolicyFailure(104, "data获取异常,type为空", SqTrackAction2.POLICY_ERROR, sQResultListener);
            return;
        }
        String policyInfo = ConfigManager.getInstance().getPolicyInfo(context2);
        ConfigManager.getInstance().setPolicyListener(sQResultListener);
        try {
            JSONObject jSONObject = new JSONObject(policyInfo);
            String strOptString = jSONObject.optString("share_url");
            String strOptString2 = jSONObject.optString("personal_url");
            String strOptString3 = jSONObject.optString("record_no");
            String strOptString4 = jSONObject.optString("record_url");
            byte b = -1;
            switch (string.hashCode()) {
                case -1903536183:
                    if (string.equals("show_miit")) {
                        b = 3;
                    }
                    break;
                case -1716037253:
                    if (string.equals("show_personal_list")) {
                        b = 1;
                    }
                    break;
                case -1181814322:
                    if (string.equals("get_record_number")) {
                        b = 2;
                    }
                    break;
                case 636802808:
                    if (string.equals("show_third_list")) {
                        b = 0;
                    }
                    break;
            }
            if (b == 0) {
                try {
                    if (!strOptString.isEmpty()) {
                        showSQWebDialog(strOptString + "?forceOrientation=1");
                        ConfigManager.getInstance().handlePolicySuccess(null, SqTrackAction2.POLICY_SHARE_LIST_SUCCESS, sQResultListener);
                    } else {
                        ConfigManager.getInstance().handlePolicyFailure(106, "个人信息第三方共享清单配置为空，请检查后台配置", SqTrackAction2.POLICY_SHARE_LIST_ERROR, sQResultListener);
                    }
                    return;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    ConfigManager.getInstance().handlePolicyFailure(112, "个人信息第三方共享清单打开异常：" + e3.getMessage(), SqTrackAction2.POLICY_SHARE_LIST_ERROR, sQResultListener);
                    return;
                }
            }
            if (b == 1) {
                try {
                    if (!strOptString2.isEmpty()) {
                        showSQWebDialog(strOptString2 + "?forceOrientation=1");
                        ConfigManager.getInstance().handlePolicySuccess(null, SqTrackAction2.POLICY_PERSONAL_LIST_SUCCESS, sQResultListener);
                    } else {
                        ConfigManager.getInstance().handlePolicyFailure(107, "个人信息收集与使用清单配置为空，请检查后台配置", SqTrackAction2.POLICY_PERSONAL_LIST_ERROR, sQResultListener);
                    }
                    return;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    ConfigManager.getInstance().handlePolicyFailure(113, "个人信息收集与使用清单打开异常：" + e4.getMessage(), SqTrackAction2.POLICY_PERSONAL_LIST_ERROR, sQResultListener);
                    return;
                }
            }
            if (b == 2) {
                if (!strOptString2.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("record_no", strOptString3);
                    ConfigManager.getInstance().handlePolicySuccess(bundle, SqTrackAction2.POLICY_RECORD_NUMBER_SUCCESS, sQResultListener);
                    return;
                }
                ConfigManager.getInstance().handlePolicyFailure(108, "备案号配置为空，请检查后台配置", SqTrackAction2.POLICY_RECORD_NUMBER_ERROR, sQResultListener);
                return;
            }
            if (b == 3) {
                if (!strOptString4.isEmpty()) {
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setFlags(268435456);
                        intent.setData(Uri.parse(strOptString4));
                        context2.startActivity(intent);
                        ConfigManager.getInstance().handlePolicySuccess(null, SqTrackAction2.POLICY_SHOW_MIIT_SUCCESS, sQResultListener);
                        return;
                    } catch (Exception e5) {
                        e5.printStackTrace();
                        ConfigManager.getInstance().handlePolicyFailure(110, "跳转工信部网页异常", SqTrackAction2.POLICY_SHOW_MIIT_ERROR, sQResultListener);
                        return;
                    }
                }
                ConfigManager.getInstance().handlePolicyFailure(109, "工信部网址配置为空，请检查后台配置", SqTrackAction2.POLICY_SHOW_MIIT_ERROR, sQResultListener);
                return;
            }
            ConfigManager.getInstance().handlePolicyFailure(111, "type类型错误，请查看传入参数", SqTrackAction2.POLICY_ERROR, sQResultListener);
        } catch (Exception e6) {
            e6.printStackTrace();
            ConfigManager.getInstance().handlePolicyFailure(105, "获取配置异常:" + e6.getMessage(), SqTrackAction2.POLICY_ERROR, sQResultListener);
        }
    }

    private void setAuthResultListener(SQResultListener sQResultListener) {
        setAuthResultListenerSQ(sQResultListener);
    }

    private void setAuthResultListenerSQ(final SQResultListener sQResultListener) {
        SQwan sQwan = sq;
        if (sQwan != null) {
            sQwan.setAuthResultListener(new com.sy37sdk.core.SQResultListener() { // from class: com.sqwan.msdk.api.sdk.Platform.13
                @Override // com.sy37sdk.core.SQResultListener
                public void onSuccess(Bundle bundle) {
                    LogUtil.i("回调给研发setAuthResultListener onSuccess");
                    SQResultListener sQResultListener2 = sQResultListener;
                    if (sQResultListener2 != null) {
                        sQResultListener2.onSuccess(bundle);
                    }
                }

                @Override // com.sy37sdk.core.SQResultListener
                public void onFailture(int i, String str) {
                    LogUtil.i("回调给研发setAuthResultListener onFailture");
                    SQResultListener sQResultListener2 = sQResultListener;
                    if (sQResultListener2 != null) {
                        sQResultListener2.onFailture(i, str);
                    }
                }
            });
        }
    }

    private static void sendLogPlat4CP(String str) {
        Properties propertites;
        if (debug4cp == null && (propertites = MultiSDKUtils.readPropertites(context, MultiSdkManager.getInstance().getInfo())) != null) {
            debug4cp = propertites.getProperty("debug") == null ? "0" : propertites.getProperty("debug");
        }
        if (!TextUtils.isEmpty(debug4cp) && "1".equals(debug4cp)) {
            System.out.println("-->" + str);
        }
        LogUtil.w("Platform-->" + str);
    }
}
