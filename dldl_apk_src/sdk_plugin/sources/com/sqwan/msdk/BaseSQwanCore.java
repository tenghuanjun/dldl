package com.sqwan.msdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.m37.dtszj.sy37.wxapi.WXEntryActivity;
import com.parameters.performfeatureconfig.PerformFeature;
import com.parameters.share.DefaultShareInfo;
import com.parameters.share.ShareImageInfo;
import com.parameters.share.ShareMessage;
import com.parameters.utils.ClassCheckUtils;
import com.plugin.sdk.BasePluginInterface;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sq.diagnostic.assistant.DiagnosticAssistant;
import com.sq.diagnostic.assistant.other.LogConfig;
import com.sq.eventbus.core.EventBus;
import com.sq.oaid.sq_oaid.IdsBean;
import com.sq.oaid.sq_oaid.SqOAIDHelper;
import com.sq.tool.logger.SQLog;
import com.sq.tool.network.EventReporter;
import com.sq.tool.network.ExceptionReporter;
import com.sq.tool.network.GateWayManager;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqHttpClient;
import com.sq.tools.Logger;
import com.sq.tools.manager.SensitiveInfoManager;
import com.sq.tools.network.httpdns.SqHttpDns;
import com.sq.tools.network.httpdns.SqHttpDnsConfig;
import com.sq.tools.network.httpdns.constant.HttpDnsRequestConstant;
import com.sq.tools.network.httpdns.util.VolleyRequest;
import com.sqnetwork.voly.IpController;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.afinal.download.SQDownloadManager;
import com.sqwan.base.L;
import com.sqwan.bugless.core.Bugless;
import com.sqwan.bugless.model.UserInfo;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.annotation.ProtectUrlManager;
import com.sqwan.common.annotation.UrlUpdateUtils;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.dev.ImeiLogic;
import com.sqwan.common.dev.MacLogic;
import com.sqwan.common.dev.RootLogic;
import com.sqwan.common.dev.SimulatorLogic;
import com.sqwan.common.dialog.CommonAlertDialog;
import com.sqwan.common.dialog.LoadingExDialog;
import com.sqwan.common.dialog.PlatformAnnouncementActivity;
import com.sqwan.common.dialog.pop.OnDialogFinishListener;
import com.sqwan.common.eventbus.PreInitEvent;
import com.sqwan.common.mod.CommonConfigs;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mod.advertise.IAdvertiseMod;
import com.sqwan.common.mod.liveshow.BaseBean;
import com.sqwan.common.mod.liveshow.ILiveshowManager;
import com.sqwan.common.mod.liveshow.LiveRadioEngine;
import com.sqwan.common.mod.liveshow.LiveshowEngine;
import com.sqwan.common.mod.push.IPushMod;
import com.sqwan.common.mod.share.IShareMod;
import com.sqwan.common.mod.share.IShareResultListener;
import com.sqwan.common.mod.track.TrackModManager;
import com.sqwan.common.mod.track.TrackModManager2;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.ActivityLifeCycleUtils;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.EnvironmentUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.PermissionHelper;
import com.sqwan.common.util.PermissionSimpleHelper;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.SdkVersionUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.common.webview.SQWeb;
import com.sqwan.common.webview.WebRequestProxy;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.msdk.api.BusinessParameters;
import com.sqwan.msdk.api.DiagnosticSensorManager;
import com.sqwan.msdk.api.InitBean;
import com.sqwan.msdk.api.InitBeanUtil;
import com.sqwan.msdk.api.MRequestCallBack;
import com.sqwan.msdk.api.MRequestManager;
import com.sqwan.msdk.api.MultiSDKUtils;
import com.sqwan.msdk.api.PluginContext;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.api.SQAppConfigImpl;
import com.sqwan.msdk.api.SQPushTransmitMessageListener;
import com.sqwan.msdk.api.SQResultListener;
import com.sqwan.msdk.api.SQSdkApi;
import com.sqwan.msdk.api.SQSdkInterface;
import com.sqwan.msdk.api.SQUpdateManager;
import com.sqwan.msdk.api.popup.ActivePopupDialogManager;
import com.sqwan.msdk.api.popup.SubmitRolePopupDialogManager;
import com.sqwan.msdk.api.sdk.Platform;
import com.sqwan.msdk.api.tool.IScreenshotListener;
import com.sqwan.msdk.config.ConfigManager;
import com.sqwan.msdk.config.MultiConfigManager;
import com.sqwan.msdk.config.MultiSdkManager;
import com.sqwan.msdk.utils.AlarmUtil;
import com.sqwan.msdk.utils.AppSigning;
import com.sqwan.msdk.utils.MUtil;
import com.sqwan.msdk.utils.TimeUtils;
import com.sqwan.msdk.utils.ZipString;
import com.sqwan.msdk.views.SQSplashDialog;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.PlatformAnnouncement.PlatformAnnouncementManager;
import com.sy37sdk.account.UrlConstant;
import com.sy37sdk.account.activebefore.ActiveBeforeManager;
import com.sy37sdk.account.auth.floatview.AuthCountDownManager;
import com.sy37sdk.account.binding.GameBindingManager;
import com.sy37sdk.account.device.DevicesInfo;
import com.sy37sdk.account.policy.AuthHandler;
import com.sy37sdk.account.update.UpdateUrlManager;
import com.sy37sdk.utils.SettingHelper;
import com.sy37sdk.utils.Util;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class BaseSQwanCore extends BasePluginInterface implements SQSdkApi {
    private static final String IMEI = "imei";
    public static final String INFO_BALANCE = "balance";
    public static final String INFO_PARTYNAME = "partyName";
    public static final String INFO_ROLEID = "roleId";
    public static final String INFO_ROLELEVEL = "roleLevel";
    public static final String INFO_ROLENAME = "roleName";
    public static final String INFO_ROLE_TIME_CREATE = "roleCTime";
    public static final String INFO_ROLE_TIME_LEVEL = "roleLevelMTime";
    public static final String INFO_SERVERID = "serverId";
    public static final String INFO_SERVERNAME = "serverName";
    public static final String INFO_SERVERTIME = "serverTime";
    public static final String INFO_VIPLEVEL = "vipLevel";
    private static final String IS_UPDATE = "is_update";
    public static final String LOGIN_KEY_BETA = "beta";
    public static final String LOGIN_KEY_GID = "gid";
    public static final String LOGIN_KEY_NURL = "nurl";
    public static final String LOGIN_KEY_PID = "pid";
    public static final String LOGIN_KEY_TOKEN = "token";
    public static final String LOGIN_KEY_USERID = "userid";
    public static final String LOGIN_KEY_USERNAME = "username";
    public static final int LOG_LEVEL_DEBUG = 0;
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_WARN = 2;
    public static final int Platform_SQwan = 1;
    public static final int SQ_REQUEST_PERMISSION_CODE = 1110;
    protected static final String TAG = "【SQCore】";
    public static final String TOKEN = "token";
    private static final String UPDATE_TYPE = "update_type";
    public static String debug4cp;
    public static InitBean initBean;
    public static SQwanCoreImpl instance;
    public static boolean isInitSqwan;
    private static boolean isInitSuccess;
    public static boolean isPlatformInitRunning;
    public static byte[] lock = new byte[0];
    public static WeakReference<Activity> sContextReference;
    public static Platform sdkapi;
    private String appKey;
    public SQResultListener back2GameListener;
    public SQResultListener baseInitListener;
    public Context context;
    private Handler mHandler;
    private SQResultListener mInitListener;
    private IScreenshotListener mScreenshotListener;
    public SQResultListener switchAccountListener;
    private boolean isCheckOn = false;
    public boolean isLogin = false;
    public boolean isSubmit = false;
    protected MRequestManager requestManager = null;
    protected LoadingExDialog initLoading = null;
    public boolean isAuthCheck = false;

    @Override // com.sqwan.msdk.api.SQSdkApi
    public abstract SQSdkInterface getPlatform(Context context, InitBean initBean2, SQResultListener sQResultListener);

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void init(final Context context, String str, SQResultListener sQResultListener) throws Throwable {
        sendLog("调用了 BaseSQwanCore.init");
        isInitSuccess = false;
        Activity activity = (Activity) context;
        sContextReference = new WeakReference<>(activity);
        SQContextWrapper.init(activity);
        L.init(context);
        this.mInitListener = sQResultListener;
        this.appKey = str;
        this.context = context;
        this.mHandler = new Handler(Looper.getMainLooper());
        setAuthCheck(false);
        SQEngineHandler.getInstance().init(this.context);
        initConfig(str);
        EventBus.getDefault().post(new PreInitEvent(""));
        initHttpDns();
        initDiagnosticAssistant();
        initLocalH5();
        SqHttpClient.getInstance().setIpController(new IpController() { // from class: com.sqwan.msdk.-$$Lambda$BaseSQwanCore$TI2lqewo1Bn8D1IJzoznrH1Or0A
            @Override // com.sqnetwork.voly.IpController
            public final boolean isAllowIpObtain() {
                return ActivityLifeCycleUtils.getInstance().isForeground();
            }
        });
        final UpdateUrlManager updateUrlManager = new UpdateUrlManager();
        updateUrlManager.reqUrlUpdateManager(new SqHttpCallback<JSONObject>() { // from class: com.sqwan.msdk.BaseSQwanCore.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                BaseSQwanCore.sendLog("接口动态下发请求成功！");
                HashMap<String, String> map = updateUrlManager.parse(jSONObject);
                if (GateWayManager.getWhiteList().contains(UrlConstant.UPDATE_URL)) {
                    GateWayManager.updateWhiteList(map);
                    GateWayManager.updateKeySet(jSONObject);
                }
                UrlUpdateUtils.setUrlData(map, BaseSQwanCore.this.context);
                ProtectUrlManager.getInstance().setProtectUrls(updateUrlManager.getApiUrls());
                BaseSQwanCore.this.afterApiUpdate(context);
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                onFailure(str2);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                onFailure(str2);
            }

            private void onFailure(String str2) {
                BaseSQwanCore.sendLog("接口动态下发请求失败，继续后续操作, " + str2);
                ProtectUrlManager.getInstance().setProtectUrls(UrlUpdateUtils.getUrls());
                BaseSQwanCore.this.afterApiUpdate(context);
            }
        });
        initToken();
        resetRoleInfo();
    }

    private void initAds() {
        IAdvertiseMod iAdvertiseMod = (IAdvertiseMod) ModHelper.get(IAdvertiseMod.class);
        if (iAdvertiseMod != null) {
            try {
                iAdvertiseMod.init(this.context);
            } catch (Error e) {
                LogUtil.e("广告模块初始化异常 " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void checkPackageParams() {
        String md5 = AppSigning.getMD5(this.context);
        String sha256 = AppSigning.getSHA256(this.context);
        String sha1 = AppSigning.getSha1(this.context);
        HashMap map = new HashMap();
        map.put(FeedBackConstants.KEY_LOG_MD5, md5);
        map.put("sha256", sha256);
        map.put("sha1", sha1);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.PACKAGE_PARAMS, map);
    }

    private void initHttpDns() {
        SqHttpDns.getInstance().setGlobalEnable(!EnvironmentUtils.isFileExits(this.context, "dns-close"));
        HashMap map = new HashMap();
        SQAppConfig sQAppConfig = ConfigManager.getInstance(this.context).getSQAppConfig();
        map.put("pid", sQAppConfig.getPartner());
        map.put("gid", sQAppConfig.getGameid());
        map.put("refer", sQAppConfig.getRefer());
        map.put("dev", DevLogic.getInstance(this.context).getValue());
        map.put("sversion", VersionUtil.sdkVersion);
        map.put("gwversion", "4.6.7");
        SqHttpDns.getInstance().init(this.context, new SqHttpDnsConfig.Builder().setSecretInfo(HttpDnsRequestConstant.APP_ID, HttpDnsRequestConstant.APP_KEY).setTimeOut(5000).setDnsServerIps(HttpDnsRequestConstant.REQUEST_IPS).setDnsServerHost(HttpDnsRequestConstant.DNS_HOST).enable(false).build(), map, new VolleyRequest(SqHttpClient.getInstance()));
        SqHttpDns.getInstance().setExceptionReporter(new ExceptionReporter());
        ProtectUrlManager.getInstance().addProtectMainHost(MultiSdkManager.APP_HOST);
        ProtectUrlManager.getInstance().addProtectHosts(ProtectUrlManager.defaultProtectHosts);
        if (SdkVersionUtil.isNewVersion(this.context)) {
            SdkVersionUtil.updateVersion(this.context);
            SqHttpDns.getInstance().clearDnsServer();
        }
    }

    private void initLocalH5() {
        try {
            SQAppConfig sQAppConfig = ConfigManager.getInstance(this.context).getSQAppConfig();
            SQWeb.getInstance().localH5Builder().setGid(sQAppConfig.getGameid()).setPid(sQAppConfig.getPartner()).setSversion(VersionUtil.sdkVersion).setRequestProxy(new WebRequestProxy()).build(SQContextWrapper.getActivity().getApplication());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDiagnosticAssistant() {
        Context context = this.context;
        if (context == null || initBean == null) {
            return;
        }
        LogConfig logConfig = new LogConfig(context);
        logConfig.logPrintEnable = initBean.getDebug() == 1;
        DiagnosticAssistant.getInstance().setLogConfig(logConfig).setServerHost(DiagnosticAssistant.SERVER_HOST_CHINA).addDefaultTcpingConfig("37.com.cn", 80).addDefaultTcpingConfig("37.com", 80).addDefaultTcpingConfig("bugless.shan-yu-tech.com", 80).addDefaultTcpingConfig("baidu.com", 80).addDefaultMtrConfig("37.com.cn", 31).addDefaultMtrConfig("37.com", 31).setBusinessParameters(new BusinessParameters(this.context)).setCallBack(new DiagnosticAssistant.CallBack() { // from class: com.sqwan.msdk.BaseSQwanCore.2
            @Override // com.sq.diagnostic.assistant.DiagnosticAssistant.CallBack
            public void onHomePageShow() {
                LogUtil.i("诊断助手显示了");
                DiagnosticSensorManager.getInstance().onPause();
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DIAGNOSTIC_ASSISTANT_HOME_SHOW);
            }

            @Override // com.sq.diagnostic.assistant.DiagnosticAssistant.CallBack
            public void onHomePageClose() {
                LogUtil.i("诊断助手关闭了");
                DiagnosticSensorManager.getInstance().onResume();
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DIAGNOSTIC_ASSISTANT_HOME_CLOSE);
            }

            @Override // com.sq.diagnostic.assistant.DiagnosticAssistant.CallBack
            public void onUpdateLogSuccess() {
                LogUtil.i("日志上传成功");
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DIAGNOSTIC_ASSISTANT_UPDATE_LOG_SUCCESS);
            }

            @Override // com.sq.diagnostic.assistant.DiagnosticAssistant.CallBack
            public void onUpdateLogFail(int i, String str) {
                LogUtil.i("日志上传失败");
                HashMap map = new HashMap();
                map.put(SqTrackKey.fail_code, String.valueOf(i));
                map.put(SqTrackKey.reason_fail, str);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DIAGNOSTIC_ASSISTANT_UPDATE_LOG_FAIL, map);
            }

            @Override // com.sq.diagnostic.assistant.DiagnosticAssistant.CallBack
            public void onLogRetrievalSuccess() {
                LogUtil.i("日志回捞成功");
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DIAGNOSTIC_ASSISTANT_LOG_RETRIEVAL_SUCCESS);
            }

            @Override // com.sq.diagnostic.assistant.DiagnosticAssistant.CallBack
            public void onLogRetrievalFail(int i, String str) {
                LogUtil.i("日志回捞失败");
                HashMap map = new HashMap();
                map.put(SqTrackKey.fail_code, String.valueOf(i));
                map.put(SqTrackKey.reason_fail, str);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DIAGNOSTIC_ASSISTANT_LOG_RETRIEVAL_FAIL, map);
            }
        }).init(this.context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPushService() {
        IPushMod iPushMod = (IPushMod) ModHelper.get(IPushMod.class);
        if (iPushMod != null) {
            iPushMod.init(this.context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void afterApiUpdate(final Context context) {
        SqHttpDns.getInstance().setPreResolveHosts(ProtectUrlManager.getInstance().getProtectHosts());
        SqHttpDns.getInstance().pullDnsConfig();
        UrlUpdateUtils.urlUpdate(context);
        PlatformAnnouncementManager.getInstance().getPlatformAnnouncementRequest(new SQResultListener() { // from class: com.sqwan.msdk.BaseSQwanCore.3
            public void onSuccess(Bundle bundle) {
                String string = bundle.getString("content");
                String string2 = bundle.getString("title");
                if (bundle.getBoolean("is_show")) {
                    BaseSQwanCore.this.showDialog((Activity) context, string2, string);
                }
            }

            public void onFailture(int i, String str) {
                LogUtil.i(i + "_" + str);
            }
        });
        ActiveBeforeManager.getInstance().updateData(new ActiveBeforeManager.UpdateDataCallback() { // from class: com.sqwan.msdk.BaseSQwanCore.4

            /* JADX INFO: renamed from: com.sqwan.msdk.BaseSQwanCore$4$1, reason: invalid class name */
            class AnonymousClass1 implements AuthHandler.PermissionCallback {
                AnonymousClass1() {
                }

                @Override // com.sy37sdk.account.policy.AuthHandler.PermissionCallback
                public void invoke() throws Throwable {
                    HashMap map = new HashMap();
                    map.put(SqConstants.PURCHASE_DETAIL_ALL, true);
                    SqHttpClient.getInstance().setUserConsent(map);
                    SqHttpDns.getInstance().setUserConsent(map);
                    BaseSQwanCore.this.setAuthCheck(true);
                    AuthHandler.getInstance().setAuthHandle();
                    BaseSQwanCore.this.initDevConfig();
                    TrackModManager.init(BaseSQwanCore.this.context);
                    TrackModManager2.init(BaseSQwanCore.this.context);
                    BaseSQwanCore.this.trackPlugin();
                    BaseSQwanCore.this.registerActivityLife();
                    BaseSQwanCore.this.initSelf();
                    LogConfig logConfig = DiagnosticAssistant.getInstance().getLogConfig();
                    if (BaseSQwanCore.initBean != null && logConfig != null) {
                        logConfig.logPrintEnable = BaseSQwanCore.initBean.getDebug() == 1;
                    }
                    ActiveBeforeManager.getInstance().requestGameUrlList();
                    DiagnosticAssistant.logRetrieval();
                    if (!DeviceUtils.isSimulator(BaseSQwanCore.this.context)) {
                        DiagnosticSensorManager.getInstance().init(context);
                        DiagnosticSensorManager.getInstance().registerSensorChangedCallback(new DiagnosticSensorManager.SensorChangedCallback() { // from class: com.sqwan.msdk.-$$Lambda$BaseSQwanCore$4$1$Yu8YFwi1ChoIlNdC6cM50tZc9rA
                            @Override // com.sqwan.msdk.api.DiagnosticSensorManager.SensorChangedCallback
                            public final void onSensorChanged(SensorEvent sensorEvent) {
                                this.f$0.lambda$invoke$0$BaseSQwanCore$4$1(sensorEvent);
                            }
                        });
                    } else {
                        Logger.info("不初始化诊断助手", new Object[0]);
                    }
                }

                public /* synthetic */ void lambda$invoke$0$BaseSQwanCore$4$1(SensorEvent sensorEvent) {
                    Logger.info("通过摇一摇开启了诊断助手", new Object[0]);
                    DiagnosticSensorManager.getInstance().unregisterSensorChangedCallback();
                    DiagnosticAssistant.show(BaseSQwanCore.this.context);
                }
            }

            @Override // com.sy37sdk.account.activebefore.ActiveBeforeManager.UpdateDataCallback
            public void invoke() {
                AuthHandler.getInstance().checkPermission(new AnonymousClass1());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportOaid() {
        SqOAIDHelper sqOAIDHelper = new SqOAIDHelper(new SqOAIDHelper.Callback() { // from class: com.sqwan.msdk.BaseSQwanCore.5
            @Override // com.sq.oaid.sq_oaid.SqOAIDHelper.Callback
            public void onIdsValid(IdsBean idsBean) {
                BaseSQwanCore.this.reportMDev(idsBean.toString());
            }
        }, new EventReporter(), new ExceptionReporter());
        Context context = this.context;
        sqOAIDHelper.getDeviceIds(new PluginContext(context, context.getResources()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackPlugin() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("sq_plugin_config", 0);
        String string = sharedPreferences.getString("hotter_hotter_effect", "");
        Logger.info("读取到hotterEffectBeforeVersion " + string, new Object[0]);
        if (!TextUtils.isEmpty(string)) {
            HashMap map = new HashMap();
            map.put(SqTrackKey.plug_latest_version, string);
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.hotter_update_effect, map);
            sharedPreferences.edit().putString("hotter_hotter_effect", "").apply();
            Logger.info("热更加载生效上报成功，置空 ", new Object[0]);
        }
        String string2 = sharedPreferences.getString("hotter_rollback_effect", "");
        Logger.info("hotterEffectRollbackBeforeVersion " + string2, new Object[0]);
        if (!TextUtils.isEmpty(string2)) {
            HashMap map2 = new HashMap();
            map2.put(SqTrackKey.plug_latest_version, string);
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.hotter_update_rollback_effect, map2);
            sharedPreferences.edit().putString("hotter_rollback_effect", "").apply();
            Logger.info("回滚加载生效上报成功，置空 ", new Object[0]);
        }
        String string3 = sharedPreferences.getString("hotter_consuming", "");
        Logger.info("pluginConsumingTime " + string2, new Object[0]);
        if (TextUtils.isEmpty(string3)) {
            return;
        }
        HashMap map3 = new HashMap();
        map3.put(SqTrackKey.PLUG_CONSUMING_TIME, String.valueOf(string3));
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.HOTTER_UPDATE_CONSUMING, map3);
        sharedPreferences.edit().putString("hotter_consuming", "").apply();
        Logger.info("插件 apk 加载耗时上报成功，置空", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerActivityLife() {
        ActivityLifeCycleUtils.getInstance().registerActivityListener(new ActivityLifeCycleUtils.AppVisibilityCallback() { // from class: com.sqwan.msdk.BaseSQwanCore.6
            @Override // com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallback
            public void onBackground() {
                LogUtil.i("切换到了后台");
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.backstage_succ);
            }

            @Override // com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallback
            public void onForeground() {
                LogUtil.i("切换到了前台");
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.resume_succ);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(final Activity activity, final String str, final String str2) {
        activity.runOnUiThread(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.7
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent(activity, (Class<?>) PlatformAnnouncementActivity.class);
                intent.putExtra("title", str);
                intent.putExtra("content", str2);
                activity.startActivity(intent);
                activity.overridePendingTransition(0, 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSelf() throws Throwable {
        if (!isPlatformInitRunning) {
            isPlatformInitRunning = true;
            initCore(this.context, this.appKey, new SQResultListener() { // from class: com.sqwan.msdk.BaseSQwanCore.8
                public void onSuccess(final Bundle bundle) {
                    boolean z = BaseSQwanCore.initBean != null && BaseSQwanCore.initBean.getDebug() == 1;
                    BaseSQwanCore.this.showTestToast("init 接口调用 初始化成功 debug模式为" + z);
                    BaseSQwanCore.isPlatformInitRunning = false;
                    boolean unused = BaseSQwanCore.isInitSuccess = true;
                    BaseSQwanCore.this.initPushService();
                    BaseSQwanCore.this.post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BaseSQwanCore.this.mInitListener.onSuccess(BaseSQwanCore.this.fillInitData(bundle));
                            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.sdk_init_succ);
                            SqTrackActionManager2.getInstance().flush();
                            ActivePopupDialogManager.getInstance().handlePopup(BaseSQwanCore.this.context);
                        }
                    });
                    SQDownloadManager.getInstance().init(BaseSQwanCore.this.context).getTempsData();
                }

                public void onFailture(final int i, final String str) {
                    BaseSQwanCore.isPlatformInitRunning = false;
                    BaseSQwanCore.this.post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.8.2
                        @Override // java.lang.Runnable
                        public void run() {
                            BaseSQwanCore.this.mInitListener.onFailture(i, str);
                        }
                    });
                }
            });
        } else {
            sendLog("初始化进行中，多次调用");
            ViewUtils.showToast(this.context, "初始化进行中，请勿多次调用..");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle fillInitData(Bundle bundle) {
        bundle.putString("imei", ImeiLogic.getInstance(this.context).getValue());
        String str = SQUpdateManager.updateType;
        bundle.putBoolean(IS_UPDATE, str.equals("2") || str.equals("3"));
        bundle.putString(UPDATE_TYPE, str);
        LogUtil.i("初始化回调数据: " + bundle.toString());
        return bundle;
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void initCore(Context context, String str, SQResultListener sQResultListener) throws Throwable {
        this.context = context;
        sendLogNoDebug("SDK开始初始化");
        initConfig(str);
        BuglessAction.setAppExtension(ConfigManager.getInstance(this.context).getSQAppConfig());
        initSQPlatformRequest(sQResultListener);
        this.baseInitListener = sQResultListener;
        initEngine(context);
        initAds();
        checkPackageParams();
    }

    private void initEngine(Context context) {
        AuthCountDownManager.getInstance().init(context);
    }

    private void initConfig(String str) throws Throwable {
        sendLogNoDebug("获取游戏参数");
        String devMac = MultiSDKUtils.getDevMac(this.context);
        String devImei = MultiSDKUtils.getDevImei(this.context);
        sendLogNoDebug("缓存前 mac=" + devMac + "|imei=" + devImei);
        if (TextUtils.isEmpty(devMac)) {
            Context context = this.context;
            MultiSDKUtils.setDevMac(context, MultiSDKUtils.getMac(context, this.isAuthCheck));
        }
        if (TextUtils.isEmpty(devImei)) {
            Context context2 = this.context;
            MultiSDKUtils.setDevImei(context2, MultiSDKUtils.getIMEI(context2, this.isAuthCheck));
        }
        ConfigManager.getInstance(this.context).initConfig();
        ConfigManager.getInstance(this.context).setAppKey(str);
        MultiSDKUtils.setKey(this.context, ZipString.json2ZipString(str));
        MultiConfigManager.getInstance().initMultiConfig(this.context);
        InitBean initBeanInflactBean = InitBeanUtil.inflactBean(this.context, MultiSDKUtils.readPropertites(this.context, "multiconfig"));
        initBean = initBeanInflactBean;
        if (initBeanInflactBean == null) {
            InitBean initBean2 = new InitBean();
            initBean = initBean2;
            initBean2.setUsesdk(1);
        }
        if (initBean.getUsesdk() == 1) {
            initBean.setAppkey(str);
            initBean.setAppid(MultiSDKUtils.getGID(this.context));
            initBean.setIsSplashShow(0);
            initBean.setUsePlatformExit(1);
        }
        MultiSDKUtils.setPushIsDelay(this.context, initBean.getIsPushDelay() == 1);
        Util.setLogPrintEnable(initBean.getDebug() == 1);
        Logger.setTag("sqsdk");
        if (initBean.isLogDetect == 1) {
            sendLog("开启动态日志检测");
        }
        sendLog("37wan_config:" + getAppConfig().toString() + " | key:" + ZipString.zipString2Json(MultiSDKUtils.getKey(this.context)) + "|");
        StringBuilder sb = new StringBuilder();
        sb.append("multiconfig:");
        sb.append(initBean.toString());
        sendLog(sb.toString());
        String info = MultiSdkManager.getInstance().getInfo();
        Properties propertites = MultiSDKUtils.readPropertites(this.context, MultiSdkManager.getInstance().getInfo());
        if (propertites != null) {
            debug4cp = propertites.getProperty("debug") == null ? "0" : propertites.getProperty("debug");
            VersionUtil.setOriginalVersion(propertites.getProperty("sdkverion") == null ? "" : propertites.getProperty("sdkverion"));
            return;
        }
        ViewUtils.showToast(this.context, "assets缺少" + info + "文件");
        sendLogBase4CP("assets缺少" + info + "文件");
    }

    private void showInitLoading() {
        if (this.initLoading != null || this.context == null) {
            return;
        }
        sendLog("showInitLoading");
        LoadingExDialog loadingExDialog = new LoadingExDialog(this.context);
        this.initLoading = loadingExDialog;
        loadingExDialog.show();
        this.initLoading.setMessage("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideInitLoading() {
        LoadingExDialog loadingExDialog = this.initLoading;
        if (loadingExDialog == null || !loadingExDialog.isShowing() || this.context == null) {
            return;
        }
        this.initLoading.dismiss();
    }

    private void onActiveResSData(JSONObject jSONObject) throws Exception {
        String string = jSONObject.getString("dev");
        MultiSDKUtils.setDevID(this.context, string);
        DevLogic.getInstance(this.context).saveToCache(string);
        if (jSONObject.has("sdata")) {
            String strSqUnZip = MUtil.sqUnZip(this.context, jSONObject.getString("sdata"));
            sendLog("解密内容=" + strSqUnZip);
            int i = new JSONObject(strSqUnZip).getInt("code");
            sendLog("code of login" + i);
            MultiSDKUtils.setCodeOfLogin(this.context, "" + i);
            ConfigManager.getInstance(this.context).setLoginCode(i);
            return;
        }
        ConfigManager.getInstance(this.context).setLoginCode(0);
    }

    private void onActiveResUpdate(JSONObject jSONObject) throws Exception {
        SQUpdateManager.checkUpdateConfig(this.context, jSONObject);
    }

    private void onActivityResLibs(JSONObject jSONObject) throws Exception {
        if (!jSONObject.isNull("libs")) {
            String string = jSONObject.getString("libs");
            JSONArray jSONArray = new JSONArray(string);
            if (string == null || "".equals(string) || jSONArray.length() == 0) {
                sendLog("onActivityResLibs : libs empty");
                return;
            }
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                String str = (String) jSONArray.get(i);
                try {
                    if (Class.forName(str) != null) {
                        sendLog("onActivityResLibs : exit  : " + str);
                        System.exit(0);
                    }
                } catch (Throwable th) {
                    sendLog("onActivityResLibs : not exit  : " + th.toString());
                }
            }
            return;
        }
        sendLog("onActivityResLibs : service not libs");
    }

    private void onActiveResOther(JSONObject jSONObject) throws Exception {
        if (jSONObject.isNull("isTouch")) {
            return;
        }
        AccountCache.setTouch(this.context, jSONObject.getString("isTouch").equals("1"));
    }

    private void onActiveAntiAddiction(JSONObject jSONObject) {
        if (jSONObject.has("anti_addiction")) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("anti_addiction");
                if ((jSONObject2.has("state") ? jSONObject2.getInt("state") : 0) != 0) {
                    sendLog("防沉迷提示状态为开");
                    String string = jSONObject2.has("ejection_time") ? jSONObject2.getString("ejection_time") : "";
                    String string2 = jSONObject2.has("exit_time") ? jSONObject2.getString("exit_time") : "";
                    String string3 = jSONObject2.has("message") ? jSONObject2.getString("message") : "";
                    sendLog(string + "小时之后显示");
                    sendLog("显示" + string2 + "分钟");
                    StringBuilder sb = new StringBuilder();
                    sb.append("显示信息：");
                    sb.append(string3);
                    sendLog(sb.toString());
                    if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                        return;
                    }
                    int iIntValue = Integer.valueOf(string).intValue();
                    int iIntValue2 = Integer.valueOf(string2).intValue();
                    Intent intent = new Intent();
                    intent.setAction("com.game.tip.alarm");
                    intent.putExtra(SqR.string.tips, string3);
                    intent.putExtra("showTime", iIntValue2 * TimeUtils.MINUTE);
                    sendLog("启动闹钟");
                    AlarmUtil.setAlarm(this.context, iIntValue * TimeUtils.HOUR, 1001, intent);
                    return;
                }
                sendLog("防沉迷开关为关");
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        sendLog("防沉迷开关为关");
    }

    private void onActiveNotice(JSONObject jSONObject) throws Exception {
        if (jSONObject.isNull(LOGIN_KEY_NURL)) {
            return;
        }
        LogUtil.i("onActiveNotice show notice dialog, " + jSONObject.getString(LOGIN_KEY_NURL));
    }

    private void onActivePInfo(JSONObject jSONObject) {
        if (jSONObject.has("pInfo")) {
            try {
                String string = jSONObject.getString("pInfo");
                sendLog("激活返回数据中读取到pInfo信息-->" + string);
                String strLoadInfo = DeviceUtils.loadInfo(this.context);
                DevicesInfo.setDeviceInfoFromJson(strLoadInfo);
                this.requestManager.uploadDeviceInfo(strLoadInfo, string);
                return;
            } catch (JSONException e) {
                sendLog("激活返回数据中解析异常");
                e.printStackTrace();
                return;
            }
        }
        sendLog("激活返回数据中没有读取到pInfo信息");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onActiveRequestSuccess(JSONObject jSONObject, SQResultListener sQResultListener) {
        LogUtil.d("active Response: " + jSONObject);
        try {
            onActiveResSData(jSONObject);
            onActiveResUpdate(jSONObject);
            if (!jSONObject.isNull("ph")) {
                MultiSDKUtils.setPushDelay(this.context, jSONObject.getInt("ph") * 60 * 60 * 1000);
            }
            onActivityResLibs(jSONObject);
            onActiveResOther(jSONObject);
            onActiveAntiAddiction(jSONObject);
            onActiveNotice(jSONObject);
            onActivePInfo(jSONObject);
            hideInitLoading();
            showSplashAndInitPlatform();
            onActiveLiveshowInfo(jSONObject);
            DevicesInfo.initAndDoDeviceCollect(this.context);
            com.sy37sdk.account.config.ConfigManager.getInstance().initConfigInfo(this.context);
        } catch (Exception e) {
            e.printStackTrace();
            ViewUtils.showToast(this.context, "初始化解析过程－异常");
            sendLog("初始化解析过程－异常 ： " + e.toString(), 1);
            BuglessAction.reportCatchException(e, jSONObject.toString(), 2);
            sQResultListener.onFailture(204, "init sdk parse data fail.");
            hideInitLoading();
            HashMap map = new HashMap();
            map.put(SqTrackKey.reason_fail, "m层解析错误 " + e.getMessage());
            map.put(SqTrackKey.fail_code, "204");
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.sdk_init_fail, map);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onActiveResponseStateError(int i, String str, SQResultListener sQResultListener) {
        sendLog("初始化请求失败：" + str, 1);
        sQResultListener.onFailture(204, "init sdk request fail, state is not 1");
        HashMap map = new HashMap();
        map.put(SqTrackKey.reason_fail, "m层初始化返回错误：" + str);
        map.put(SqTrackKey.fail_code, String.valueOf(i));
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.sdk_init_fail, map);
        MultiSDKUtils.showTips(this.context, "初始化请求失败：" + str);
        hideInitLoading();
    }

    private void onActiveLiveshowInfo(JSONObject jSONObject) {
        ILiveshowManager liveshowManager = LiveshowEngine.getInstance().getLiveshowManager();
        ILiveshowManager liveshowManager2 = LiveRadioEngine.getInstance().getLiveshowManager();
        if (liveshowManager != null) {
            liveshowManager.onActive(jSONObject);
        }
        if (liveshowManager2 != null) {
            liveshowManager2.onActive(jSONObject);
        }
    }

    private void initSQPlatformRequest(final SQResultListener sQResultListener) {
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.sdk_init);
        sendLog("平台初始化请求");
        showInitLoading();
        MRequestManager mRequestManager = new MRequestManager(this.context);
        this.requestManager = mRequestManager;
        mRequestManager.active(new SqHttpCallback<JSONObject>() { // from class: com.sqwan.msdk.BaseSQwanCore.10
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                BaseSQwanCore.this.onActiveRequestSuccess(jSONObject, sQResultListener);
                SQReportCore.getInstance().afterPermission(BaseSQwanCore.this.context);
                BaseSQwanCore.this.reportOaid();
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                BaseSQwanCore.this.onActiveResponseStateError(i2, str, sQResultListener);
                SQReportCore.getInstance().afterPermission(BaseSQwanCore.this.context);
                BaseSQwanCore.this.reportOaid();
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                ViewUtils.showToast(BaseSQwanCore.this.context, "网络请求失败，请重试");
                BaseSQwanCore.sendLog("初始化请求异常", 1);
                sQResultListener.onFailture(204, "网络请求失败，请重试");
                BaseSQwanCore.this.hideInitLoading();
            }
        });
    }

    private void showSplashAndInitPlatform() {
        String codeOfLogin = MultiSDKUtils.getCodeOfLogin(this.context);
        sendLog("showSplashAndInitPlatform code is " + codeOfLogin);
        if (!TextUtils.isEmpty(codeOfLogin) && "1".equals(codeOfLogin)) {
            initPaltform(this.baseInitListener);
            return;
        }
        InitBean initBean2 = initBean;
        if (initBean2 != null && initBean2.getIsSplashShow() == 1) {
            SQSplashDialog sQSplashDialog = new SQSplashDialog(this.context, 3);
            sQSplashDialog.setSplashListener(new SQSplashDialog.SplashListener() { // from class: com.sqwan.msdk.BaseSQwanCore.11
                @Override // com.sqwan.msdk.views.SQSplashDialog.SplashListener
                public void afterSplash() {
                    BaseSQwanCore.sendLog("splash end ...");
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.open_img);
                    BaseSQwanCore baseSQwanCore = BaseSQwanCore.this;
                    baseSQwanCore.initPaltform(baseSQwanCore.baseInitListener);
                }
            });
            sQSplashDialog.show();
            return;
        }
        initPaltform(this.baseInitListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void initPaltform(SQResultListener sQResultListener) {
        sendLog("初始化平台代码");
        Platform platform = (Platform) getPlatform(this.context, initBean, sQResultListener);
        sdkapi = platform;
        platform.init(this.context);
        LiveshowEngine.getInstance().setHasInited(true);
        LiveRadioEngine.getInstance().setHasInited(true);
        if (this.switchAccountListener != null) {
            sdkapi.setSwitchAccountListener(this.switchAccountListener);
        }
        if (this.back2GameListener != null) {
            sdkapi.setBackToGameLoginListener(this.back2GameListener);
        }
        if (this.mScreenshotListener != null) {
            sdkapi.setScreenshotListener(this.mScreenshotListener);
        }
        handleInitPerformFeatureListeners();
        handleCallback();
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public SQAppConfig getAppConfig() {
        return new SQAppConfigImpl(ConfigManager.getInstance(this.context).getSQAppConfig());
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void track(String str, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "CP未命名自定义事件";
        }
        SqTrackAction2.sdk_expand.construct(str, str2);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.sdk_expand, map);
        LogUtil.i("调用上报事件: " + str);
    }

    public void login(final Context context, final SQResultListener sQResultListener) {
        SQLog.d("【SQCore】调用login");
        post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.12
            @Override // java.lang.Runnable
            public void run() {
                final SQResultListener bindingLoginResultListener = GameBindingManager.getInstance().getBindingLoginResultListener(GameBindingManager.GameBindingLoginType.TYPE_LOGIN, sQResultListener);
                if (!BaseSQwanCore.isInitSuccess) {
                    SQLog.e("【SQCore】初始化成功之后，才能调用登录");
                    bindingLoginResultListener.onFailture(204, "初始化成功之后，才能调用登录");
                    return;
                }
                SqTrackActionManager2.getInstance().trackActionCPTest(SqTrackAction2.DO_LOGIN);
                if (!ActivePopupDialogManager.getInstance().needShowPopup()) {
                    BaseSQwanCore.this.doLogin(context, bindingLoginResultListener);
                } else {
                    SQLog.i("【SQCore】需要显示登录弹窗");
                    ActivePopupDialogManager.getInstance().showPopupDialog(new OnDialogFinishListener() { // from class: com.sqwan.msdk.BaseSQwanCore.12.1
                        @Override // com.sqwan.common.dialog.pop.OnDialogFinishListener
                        public void onFinishPopup() {
                            BaseSQwanCore.this.doLogin(context, bindingLoginResultListener);
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doLogin(final Context context, final SQResultListener sQResultListener) {
        SQLog.d("【SQCore】调用" + sdkapi.getClass().getSimpleName() + "#login");
        sdkapi.login(context, new SQResultListener() { // from class: com.sqwan.msdk.BaseSQwanCore.13
            public void onSuccess(Bundle bundle) {
                sQResultListener.onSuccess(bundle);
                MultiSDKUtils.setLogined(context, true);
                BaseSQwanCore.this.initBuglessUser();
                BaseSQwanCore.this.isLogin = true;
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.cp_login_succ);
                BaseSQwanCore.this.resetRoleInfo();
            }

            public void onFailture(int i, String str) {
                sQResultListener.onFailture(i, str);
                HashMap map = new HashMap();
                map.put("code", i + "");
                map.put("msg", str);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.cp_login_fail, map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initBuglessUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(MultiSDKUtils.getUsername(this.context));
        userInfo.setId(MultiSDKUtils.getUserid(this.context));
        Bugless.getInstance().setUserInfo(userInfo);
    }

    public void pay(final Context context, final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8, final int i, final float f, final int i2, final SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.pay");
        post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.14
            /* JADX WARN: Code restructure failed: missing block: B:32:0x0214, code lost:
            
                if (android.text.TextUtils.isEmpty("" + r12) != false) goto L36;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 588
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sqwan.msdk.BaseSQwanCore.AnonymousClass14.run():void");
            }
        });
    }

    public void setSwitchAccountListener(SQResultListener sQResultListener) {
        sendLogBase4CP("设置切换账号监听");
        sendLog("调用了 BaseSQwanCore.setSwitchAccountListener");
        showTestToast("悬浮窗-切换账号 \n setSwitchAccountListener 调用成功");
        SQResultListener bindingLoginResultListener = GameBindingManager.getInstance().getBindingLoginResultListener(GameBindingManager.GameBindingLoginType.TYPE_SWITCH, sQResultListener);
        this.switchAccountListener = bindingLoginResultListener;
        Platform platform = sdkapi;
        if (platform != null) {
            platform.setSwitchAccountListener(bindingLoginResultListener);
        }
    }

    public void setBackToGameLoginListener(SQResultListener sQResultListener) {
        sendLogBase4CP("设置回到游戏登录监听");
        sendLog("调用了 BaseSQwanCore.setBackToGameLoginListener");
        showTestToast("悬浮窗-账户-注销登录 \n setBackToGameLoginListener 调用成功");
        this.back2GameListener = sQResultListener;
        Platform platform = sdkapi;
        if (platform != null) {
            platform.setBackToGameLoginListener(sQResultListener);
        }
    }

    public void showSQWebDialog(final String str) {
        sendLogBase4CP("打开37Web容器，url --> " + str);
        if (sdkapi != null) {
            post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.15
                @Override // java.lang.Runnable
                public void run() {
                    BaseSQwanCore.sdkapi.showSQWebDialog(str);
                }
            });
        }
    }

    public void showSQPersonalDialog(final Context context) {
        sendLogBase4CP("打开37实名制窗口");
        if (sdkapi != null) {
            post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.16
                @Override // java.lang.Runnable
                public void run() {
                    BaseSQwanCore.sdkapi.showSQPersonalDialog(context);
                }
            });
        }
    }

    public void changeAccount(final Context context, final SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.changeAccount");
        post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.17
            @Override // java.lang.Runnable
            public void run() {
                final SQResultListener bindingLoginResultListener = GameBindingManager.getInstance().getBindingLoginResultListener(GameBindingManager.GameBindingLoginType.TYPE_CHANGE_ACCOUNT, sQResultListener);
                BaseSQwanCore.sendLogBase4CP("主动调用切换账号");
                BaseSQwanCore.this.showTestToast("游戏内切换账号：changeAccount接口调用成功");
                SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.changeAccount, SqTrackBtn.SqTrackBtnExt.CHANGE_ACCOUNT_CP);
                HashMap map = new HashMap();
                map.put(SqTrackKey.logout_type, SqTrackBtn.SqTrackBtnExt.CHANGE_ACCOUNT_CP);
                map.put("login_type", AccountCache.getLoginType(context));
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.LOGOUT_SUCC, map);
                if (BaseSQwanCore.sdkapi != null) {
                    if (Util.isSkipSQChangeAccountLogin(context)) {
                        bindingLoginResultListener.onSuccess(new Bundle());
                        return;
                    } else {
                        BaseSQwanCore.sdkapi.changeAccount(context, new SQResultListener() { // from class: com.sqwan.msdk.BaseSQwanCore.17.1
                            public void onSuccess(Bundle bundle) {
                                bindingLoginResultListener.onSuccess(bundle);
                                BaseSQwanCore.this.initBuglessUser();
                                BaseSQwanCore.this.resetRoleInfo();
                            }

                            public void onFailture(int i, String str) {
                                bindingLoginResultListener.onFailture(i, str);
                            }
                        });
                        return;
                    }
                }
                bindingLoginResultListener.onFailture(203, "初始化成功之后，才能调用登录");
            }
        });
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void shareToWX(Context context, String str, String str2, String str3, String str4, int i, SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.shareToWX");
        Platform.shareListener = sQResultListener;
        Bundle bundle = new Bundle();
        Intent intent = new Intent(context, (Class<?>) WXEntryActivity.class);
        bundle.putString("title", str);
        bundle.putString("description", str2);
        bundle.putString("shareUrl", str3);
        bundle.putString("thumbUrl", str4);
        bundle.putInt("resourceType", i);
        sendLog("分享参数为：" + bundle);
        intent.putExtras(bundle);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void share(String str, SQResultListener sQResultListener) {
        share(str, "", sQResultListener);
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void share(String str, String str2, SQResultListener sQResultListener) {
        DefaultShareInfo defaultShareInfo = new DefaultShareInfo();
        defaultShareInfo.setImgId(str2);
        defaultShareInfo.setInviteCode(str);
        ShareMessage shareMessage = new ShareMessage();
        shareMessage.setShareMessage(defaultShareInfo);
        share(shareMessage, sQResultListener);
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void share(final ShareMessage shareMessage, final SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.share");
        post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.18
            @Override // java.lang.Runnable
            public void run() {
                final IShareResultListener iShareResultListener = new IShareResultListener() { // from class: com.sqwan.msdk.BaseSQwanCore.18.1
                    @Override // com.sqwan.common.mod.share.IShareResultListener
                    public void onSuccess(Bundle bundle) {
                        if (sQResultListener == null) {
                            return;
                        }
                        sQResultListener.onSuccess(bundle);
                    }

                    @Override // com.sqwan.common.mod.share.IShareResultListener
                    public void onFailture(int i, String str) {
                        if (sQResultListener == null) {
                            return;
                        }
                        sQResultListener.onFailture(i, str);
                    }
                };
                if (!(shareMessage.getShareMessage() instanceof ShareImageInfo)) {
                    ((IShareMod) ModHelper.get(IShareMod.class)).share(shareMessage, iShareResultListener);
                } else {
                    PermissionSimpleHelper.requestPermission(PermissionSimpleHelper.STORAGE_PERMISSION, PermissionSimpleHelper.STORAGE_PERMISSION_NAME, PermissionSimpleHelper.STORAGE_PERMISSION_BY_SHARE, new PermissionSimpleHelper.OnPermissionCallback() { // from class: com.sqwan.msdk.BaseSQwanCore.18.2
                        @Override // com.sqwan.common.util.PermissionSimpleHelper.OnPermissionCallback
                        public void onGranted() {
                            ((IShareMod) ModHelper.get(IShareMod.class)).share(shareMessage, iShareResultListener);
                        }

                        @Override // com.sqwan.common.util.PermissionSimpleHelper.OnPermissionCallback
                        public void onDenied() {
                            iShareResultListener.onFailture(1002, "分享图片失败，没有存储权限");
                        }
                    });
                }
            }
        });
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void log(int i, String str) {
        SQLog.log(i, "sqsdk", "[LOG]" + str, null);
    }

    public void showExitDailog(final Context context, final SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.showExitDailog");
        post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.19
            @Override // java.lang.Runnable
            public void run() {
                BaseSQwanCore.this.showTestToast("退出框接口showExitDialog调用成功");
                BaseSQwanCore.sendLogBase4CP("调用退出弹窗");
                BaseSQwanCore.this.logout(context, sQResultListener);
                SqTrackActionManager2.getInstance().trackActionCPTest(SqTrackAction2.SHOW_EXIT_DIALOG);
            }
        });
    }

    public void logout(final Context context, final SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.logout");
        post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.20
            @Override // java.lang.Runnable
            public void run() {
                BaseSQwanCore.sendLog("退出框逻辑实现..logout");
                if (BaseSQwanCore.sdkapi == null) {
                    BaseSQwanCore.this.showAndoridExit(context, sQResultListener);
                    return;
                }
                String codeOfLogin = MultiSDKUtils.getCodeOfLogin(context);
                if (!TextUtils.isEmpty(codeOfLogin) && "1".equals(codeOfLogin)) {
                    BaseSQwanCore.this.showAndoridExit(context, sQResultListener);
                } else if (BaseSQwanCore.initBean == null || BaseSQwanCore.initBean.getUsePlatformExit() != 1) {
                    BaseSQwanCore.this.logoutPlatformWithAndroidExit(context, sQResultListener);
                } else {
                    BaseSQwanCore.this.logoutPlatform(context, sQResultListener);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAndoridExit(final Context context, final SQResultListener sQResultListener) {
        post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.21
            @Override // java.lang.Runnable
            public void run() {
                new CommonAlertDialog.Builder(context).setTitle("您确定退出游戏吗？").setPositiveButton("确定", new View.OnClickListener() { // from class: com.sqwan.msdk.BaseSQwanCore.21.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        HashMap map = new HashMap();
                        map.put(SqTrackKey.logout_type, "退出游戏");
                        map.put("login_type", AccountCache.getLoginType(context));
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.LOGOUT_SUCC, map);
                        sQResultListener.onSuccess(new Bundle());
                    }
                }).setNegativeButton("取消", null).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logoutPlatformWithAndroidExit(final Context context, final SQResultListener sQResultListener) {
        post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.22
            @Override // java.lang.Runnable
            public void run() {
                new CommonAlertDialog.Builder(context).setTitle("您确定退出游戏吗？").setPositiveButton("确定", new View.OnClickListener() { // from class: com.sqwan.msdk.BaseSQwanCore.22.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        HashMap map = new HashMap();
                        map.put(SqTrackKey.logout_type, "退出游戏");
                        map.put("login_type", AccountCache.getLoginType(context));
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.LOGOUT_SUCC, map);
                        BaseSQwanCore.this.logoutPlatform(context, sQResultListener);
                    }
                }).setNegativeButton("取消", null).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logoutPlatform(final Context context, final SQResultListener sQResultListener) {
        post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.23
            @Override // java.lang.Runnable
            public void run() {
                BaseSQwanCore.sdkapi.logout(context, new SQResultListener() { // from class: com.sqwan.msdk.BaseSQwanCore.23.1
                    public void onSuccess(Bundle bundle) {
                        BaseSQwanCore.sendLogBase4CP("调用退出游戏接口,退出成功..");
                        HashMap map = new HashMap();
                        map.put(SqTrackKey.logout_type, "退出游戏");
                        map.put("login_type", AccountCache.getLoginType(context));
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.LOGOUT_SUCC, map);
                        sQResultListener.onSuccess(bundle);
                    }

                    public void onFailture(int i, String str) {
                        BaseSQwanCore.sendLogBase4CP("调用退出游戏接口,退出失败..");
                        sQResultListener.onFailture(i, str);
                    }
                });
            }
        });
    }

    private void checkUrlNeedUpdate(String str, String str2) {
        TextUtils.isEmpty(str);
    }

    public void creatRoleInfo(HashMap<String, String> map) {
        sendLog("调用了 BaseSQwanCore.creatRoleInfo");
        sendLog("角色信息：" + map);
        showRoleInfo(map, 1);
        Platform platform = sdkapi;
        if (platform != null) {
            platform.creatRoleInfo(map);
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.role_add);
            SqTrackActionManager2.getInstance().trackActionCPTest(SqTrackAction2.CREATE_ROLE_INFO, map);
        }
    }

    public void upgradeRoleInfo(HashMap<String, String> map) {
        sendLog("调用了 BaseSQwanCore.upgradeRoleInfo");
        sendLog("角色信息：" + map);
        showRoleInfo(map, 3);
        if (sdkapi != null) {
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.role_level);
            SqTrackActionManager2.getInstance().trackActionCPTest(SqTrackAction2.UPGRADE_ROLE_LEVEL, map);
            sdkapi.upgradeRoleInfo(map);
        }
    }

    private HashMap<String, String> roleTrackParam(HashMap<String, String> map) {
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("role_id", map.get(INFO_ROLEID));
        map2.put("role_name", map.get(INFO_ROLENAME));
        map2.put("role_level", map.get(INFO_ROLELEVEL));
        map2.put("vip_level", map.get(INFO_VIPLEVEL));
        map2.put("server_id", map.get(INFO_SERVERID));
        map2.put("server_name", map.get(INFO_SERVERNAME));
        return map2;
    }

    private void setRoleInfos(HashMap<String, String> map) {
        BaseBean baseBean = new BaseBean();
        baseBean.roleId = map.get(INFO_ROLEID);
        baseBean.roleLevel = map.get(INFO_ROLELEVEL);
        baseBean.roleName = map.get(INFO_ROLENAME);
        baseBean.serverId = map.get(INFO_SERVERID);
        baseBean.serverName = map.get(INFO_SERVERNAME);
        baseBean.vipLevel = map.get(INFO_VIPLEVEL);
        CommonConfigs.getInstance().setBaseUserInfo(baseBean);
        ILiveshowManager liveshowManager = LiveshowEngine.getInstance().getLiveshowManager();
        ILiveshowManager liveshowManager2 = LiveRadioEngine.getInstance().getLiveshowManager();
        if (liveshowManager != null) {
            liveshowManager.onSubmitRole();
        }
        if (liveshowManager2 != null) {
            liveshowManager2.onSubmitRole();
        }
    }

    public void submitRoleInfo(final HashMap<String, String> map) {
        sendLog("调用了 BaseSQwanCore.submitRoleInfo");
        sendLog("角色信息：" + map);
        showRoleInfo(map, 2);
        Util.setRoleInfos(map);
        ConfigManager.getInstance(this.context).setRoleInfo(map);
        try {
            setRoleInfos(map);
        } catch (Throwable th) {
            th.printStackTrace();
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            String string = stringWriter.toString();
            HashMap map2 = new HashMap();
            map2.put(SqTrackKey.reason_fail, string);
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.role_login_throwable, map2);
        }
        Platform platform = sdkapi;
        if (platform != null) {
            platform.submitRoleInfo(map);
            MultiSDKUtils.setServerid(this.context, map.get(INFO_SERVERID));
            MultiSDKUtils.setRoleid(this.context, map.get(INFO_ROLEID));
            MultiSDKUtils.setRolename(this.context, map.get(INFO_ROLENAME));
            MultiSDKUtils.setRolelevel(this.context, map.get(INFO_ROLELEVEL));
            MultiSDKUtils.setVipLevel(this.context, map.get(INFO_VIPLEVEL));
            MultiSDKUtils.setServerName(this.context, map.get(INFO_SERVERNAME));
            this.isSubmit = true;
            SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.role_login);
            SqTrackActionManager2.getInstance().flush();
        }
        SqTrackActionManager2.getInstance().trackActionCPTest(SqTrackAction2.SUBMIT_ROLE_INFO, map);
        setUserInfoToPushService(map.get(INFO_ROLEID));
        sendLogBase4CP("调用提交角色信息接口");
        this.requestManager.submitRoleInfoRequst(map, new MRequestCallBack() { // from class: com.sqwan.msdk.BaseSQwanCore.24
            @Override // com.sqwan.msdk.api.MRequestCallBack
            public void onRequestSuccess(String str) {
                BaseSQwanCore.sendLog("37提交用户角色信息成功:" + str);
                SubmitRolePopupDialogManager.getInstance().handlePopup(BaseSQwanCore.this.context, map);
            }

            @Override // com.sqwan.msdk.api.MRequestCallBack
            public void onRequestError(String str) {
                BaseSQwanCore.sendLog("37提交用户角色信息失败:" + str);
            }
        });
    }

    private void showRoleInfo(HashMap<String, String> map, int i) {
        InitBean initBean2 = initBean;
        if (initBean2 == null || initBean2.getTestTag() != 1) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("serverId = " + map.get(INFO_SERVERID) + ShellAdbUtils.COMMAND_LINE_END);
        sb.append("serverName = " + map.get(INFO_SERVERNAME) + ShellAdbUtils.COMMAND_LINE_END);
        sb.append("roleId = " + map.get(INFO_ROLEID) + ShellAdbUtils.COMMAND_LINE_END);
        sb.append("roleName = " + map.get(INFO_ROLENAME) + ShellAdbUtils.COMMAND_LINE_END);
        sb.append("roleLevel = " + map.get(INFO_ROLELEVEL) + ShellAdbUtils.COMMAND_LINE_END);
        sb.append("balance = " + map.get(INFO_BALANCE) + ShellAdbUtils.COMMAND_LINE_END);
        sb.append("partyName = " + map.get(INFO_PARTYNAME) + ShellAdbUtils.COMMAND_LINE_END);
        sb.append("vipLevel = " + map.get(INFO_VIPLEVEL) + ShellAdbUtils.COMMAND_LINE_END);
        sb.append("roleCTime = " + map.get(INFO_ROLE_TIME_CREATE) + ShellAdbUtils.COMMAND_LINE_END);
        sb.append("roleLevelMTime = " + map.get(INFO_ROLE_TIME_LEVEL) + ShellAdbUtils.COMMAND_LINE_END);
        if (i == 1) {
            showTestToast("创建角色 \n请检查参数正确性：\n" + sb.toString());
            return;
        }
        if (i == 2) {
            showTestToast("进入服务器 \n请检查参数正确性：\n" + sb.toString());
            return;
        }
        if (i != 3) {
            return;
        }
        showTestToast("角色升级 \n请检查参数正确性：\n" + sb.toString());
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void submitStatisticsInfo(String str, String str2) {
        sendLogBase4CP("调用提交统计信息接口");
        sendLog("调用了 BaseSQwanCore.submitStatisticsInfo");
        sendLog("key = " + str + ", values = " + str2);
        this.requestManager.statisticsRequst(str, str2, new MRequestCallBack() { // from class: com.sqwan.msdk.BaseSQwanCore.25
            @Override // com.sqwan.msdk.api.MRequestCallBack
            public void onRequestError(String str3) {
            }

            @Override // com.sqwan.msdk.api.MRequestCallBack
            public void onRequestSuccess(String str3) {
            }
        });
    }

    public void creatRole(Context context, String str) {
        Platform platform = sdkapi;
        if (platform != null) {
            platform.creatRole(context, str);
        }
    }

    public void onStart() {
        LogUtil.v("调用了 BaseSQwanCore.onStart");
        showTestToast("生命周期检查：\nonStart 调用成功");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.onStart();
        }
    }

    public void onRestart() {
        LogUtil.v("调用了 BaseSQwanCore.onRestart");
        showTestToast("生命周期检查：\nonRestart 调用成功");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.onRestart();
        }
    }

    public void onResume() {
        LogUtil.v("调用了 BaseSQwanCore.onResume");
        showTestToast("生命周期检查：\nonResume 调用成功");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.onResume();
        }
        DiagnosticSensorManager.getInstance().onResume();
        SQDownloadManager.getInstance().resume();
    }

    public void onPause() {
        LogUtil.v("调用了 BaseonPause");
        showTestToast("生命周期检查：\nonPause 调用成功");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.onPause();
        }
        DiagnosticSensorManager.getInstance().onPause();
    }

    public void onStop() {
        LogUtil.v("调用了 BaseSQwanCore.onStop");
        showTestToast("生命周期检查：\nonStop 调用成功");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.onStop();
        }
    }

    public void onDestroy() {
        LogUtil.w("调用了 BaseSQwanCore.onDestroy");
        showTestToast("生命周期检查：\nonDestroy 调用成功");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.onDestroy();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.d("调用了 BaseSQwanCore.onActivityResult");
        LogUtil.d("requestCode = " + i + "，resultCode = " + i2 + "，data" + intent);
        showTestToast("生命周期检查：\nonActivityResult 调用成功");
        if (i == 2121) {
            SQwanCore.sendLogNoDebug("设置页回调");
            AuthHandler.getInstance().checkPermission(false, true);
        }
        if (i == 2313) {
            SettingHelper.getInstance((Activity) this.context).onActivityResult(i, i2, intent);
        }
        Platform platform = sdkapi;
        if (platform != null) {
            platform.onActivityResult(i, i2, intent);
        }
    }

    public void onNewIntent(Intent intent) {
        sendLog("调用了 BaseonNewIntent");
        showTestToast("生命周期检查：\nonNewIntent 调用成功");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.onNewIntent(intent);
        }
        IPushMod iPushMod = (IPushMod) ModHelper.get(IPushMod.class);
        Context context = this.context;
        if (context != null && iPushMod != null) {
            iPushMod.onNewIntent((Activity) context, intent);
        }
        if (this.context != null) {
            GameBindingManager.getInstance().handlerNewIntentEvent((Activity) this.context, intent);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        sendLog("调用了 BaseSQwanCore.onRequestPermissionsResult");
        sendLog("调用了 requestCode = " + i + ", permissions = " + Arrays.toString(strArr) + "，grantResults = " + Arrays.toString(iArr));
        StringBuilder sb = new StringBuilder();
        sb.append("权限申请回调：");
        sb.append(i);
        sendLogNoDebug(sb.toString());
        showTestToast("权限申请回调接口接入");
        HashMap map = new HashMap();
        map.put("requestCode", i + "");
        map.put("permissions", Arrays.toString(strArr));
        map.put("grantResults", Arrays.toString(iArr));
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.request_permission_result, map);
        if (i != 1110) {
            return;
        }
        PermissionHelper.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onConfigurationChanged(Configuration configuration) {
        LogUtil.v("调用了 BaseSQwanCore.onConfigurationChanged");
    }

    public void onWindowFocusChanged(boolean z) {
        LogUtil.v("调用了 BaseSQwanCore.onWindowFocusChanged，hasFocus = " + z);
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void reportMDev(String str) {
        HashMap map = new HashMap();
        map.put("oaid", str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.report_oaid, map);
        try {
            if (TextUtils.isEmpty(new JSONObject(str).optString("oaid"))) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.info("获取oaid出错", new Object[0]);
        }
        if (str.equals(MultiSDKUtils.getMDevIds(this.context))) {
            return;
        }
        if (this.requestManager == null) {
            this.requestManager = new MRequestManager(this.context);
        }
        this.requestManager.reportDev(this.context, str);
    }

    public void setContext(Context context) {
        this.context = context;
        Platform platform = sdkapi;
        if (platform != null) {
            platform.setContext(context);
        }
        MRequestManager mRequestManager = this.requestManager;
        if (mRequestManager != null) {
            mRequestManager.setContext(context);
        }
    }

    public void setDebug(Boolean bool) {
        this.isCheckOn = bool.booleanValue();
    }

    public void showTestToast(final String str) {
        String str2 = "母包检查：\n" + str;
        InitBean initBean2 = initBean;
        if (initBean2 == null || initBean2.getTestTag() != 1) {
            return;
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ToastUtil.showToast(this.context, str2);
        } else {
            ((Activity) this.context).runOnUiThread(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.26
                @Override // java.lang.Runnable
                public void run() {
                    ToastUtil.showToast(BaseSQwanCore.this.context, str + " --> 非主线程调用");
                }
            });
        }
    }

    public static void sendLog(String str) {
        LogUtil.w(str);
    }

    public static void sendLogNoDebug(String str) {
        LogUtil.d(str);
    }

    public static void sendLog(String str, int i) {
        LogUtil.e("CODE:" + i + " " + str);
    }

    public static void sendLogBase4CP(String str) {
        if (!TextUtils.isEmpty(debug4cp) && "1".equals(debug4cp)) {
            System.out.println("-->" + str);
        }
        sendLog("BaseSQwanCore-->" + str);
    }

    @Deprecated
    public static void sendLogPlat4CP(String str) {
        if (!TextUtils.isEmpty(debug4cp) && "1".equals(debug4cp)) {
            System.out.println("-->" + str);
        }
        sendLog("Platform-->" + str);
    }

    public void speechInit(Context context, final SQResultListener sQResultListener) {
        sendLogBase4CP("初始化语音接口");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.speechInit(context, new SQResultListener() { // from class: com.sqwan.msdk.BaseSQwanCore.27
                public void onSuccess(Bundle bundle) {
                    BaseSQwanCore.sendLogBase4CP("初始化语音接口成功");
                    sQResultListener.onSuccess(bundle);
                }

                public void onFailture(int i, String str) {
                    BaseSQwanCore.sendLogBase4CP("初始化语音接口失败");
                    sQResultListener.onFailture(i, str);
                }
            });
        }
    }

    public void setServerInfo(String str) {
        sendLogBase4CP("设置海外服务器配置");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.setServerInfo(str);
        }
    }

    public void poll() {
        sendLogBase4CP("回调函数驱动－主循环");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.poll();
        }
    }

    public void joinNationalRoom(String str, int i, int i2) {
        sendLogBase4CP("加入国战房间");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.joinNationalRoom(str, i, i2);
        }
    }

    public void joinTeamRoom(String str, int i) {
        sendLogBase4CP("加入小组房间");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.joinTeamRoom(str, i);
        }
    }

    public void quitRoom(String str, int i) {
        sendLogBase4CP("退出房间");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.quitRoom(str, i);
        }
    }

    public void openMic() {
        sendLogBase4CP("打开麦克风");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.openMic();
        }
    }

    public void closeMic() {
        sendLogBase4CP("关闭麦克风");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.closeMic();
        }
    }

    public void openSpeaker() {
        sendLogBase4CP("打开扬声器");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.openSpeaker();
        }
    }

    public void closeSpeaker() {
        sendLogBase4CP("关闭扬声器");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.closeSpeaker();
        }
    }

    public void setMicLevel(int i) {
        sendLogBase4CP("设置麦克风音量");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.setMicLevel(i);
        }
    }

    public int getMicLevel() {
        sendLogBase4CP("获取麦克风音量");
        Platform platform = sdkapi;
        if (platform != null) {
            return platform.getMicLevel();
        }
        return 0;
    }

    public void setSpeakerVolume(int i) {
        sendLogBase4CP("设置扬声器音量");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.setSpeakerVolume(i);
        }
    }

    public int getSpeakerVolume() {
        sendLogBase4CP("获取扬声器音量");
        Platform platform = sdkapi;
        if (platform != null) {
            return platform.getSpeakerVolume();
        }
        return 0;
    }

    public boolean testMic() {
        sendLogBase4CP("测试麦克风是否可用");
        Platform platform = sdkapi;
        if (platform != null) {
            return platform.testMic();
        }
        return false;
    }

    public void enableSpeakerOn(boolean z) {
        sendLogBase4CP("是否打开扬声器");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.enableSpeakerOn(z);
        }
    }

    public void forbidMemberVoice(int i, boolean z) {
        sendLogBase4CP("禁止某成员语音");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.forbidMemberVoice(i, z);
        }
    }

    public void onJoinRoomListener(Context context, SQResultListener sQResultListener) {
        sendLogBase4CP("进入房间回调接口");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.onJoinRoomListener(context, sQResultListener);
        }
    }

    public void onQuitRoomListener(Context context, SQResultListener sQResultListener) {
        sendLogBase4CP("退出房间回调接口");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.onQuitRoomListener(context, sQResultListener);
        }
    }

    public void onMemberVoiceListener(Context context, SQResultListener sQResultListener) {
        sendLogBase4CP("成员状态回调接口");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.onMemberVoiceListener(context, sQResultListener);
        }
    }

    public void onStatusUpdateListener(Context context, SQResultListener sQResultListener) {
        sendLogBase4CP("掉线回调接口");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.onStatusUpdateListener(context, sQResultListener);
        }
    }

    public void performFeatureBBS() {
        sendLog("调用了 BaseSQwanCore.performFeatureBBS");
        sendLogBase4CP("应用宝BBS论坛");
        if (sdkapi != null) {
            post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.28
                @Override // java.lang.Runnable
                public void run() {
                    BaseSQwanCore.sdkapi.performFeatureBBS();
                }
            });
        }
    }

    public void performFeatureVPlayer() {
        sendLog("调用了 BaseSQwanCore.performFeatureVPlayer");
        sendLogBase4CP("应用宝V+特权");
        Platform platform = sdkapi;
        if (platform != null) {
            platform.performFeatureVPlayer();
        }
    }

    public void performFeature(final Context context, final String str, final Object obj, final SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.performFeature");
        sendLog("type = " + str + "，data = " + obj);
        sendLogBase4CP("调用扩展接口");
        handleUninitPlatformPerformFeatureListeners(str, obj, sQResultListener);
        if (sdkapi != null) {
            try {
                post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.29
                    @Override // java.lang.Runnable
                    public void run() {
                        BaseSQwanCore.sdkapi.performFeature(context, str, obj, sQResultListener);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public boolean isSupportPlugin() {
        sendLog("调用了 BaseSQwanCore.isSupportPlugin");
        return false;
    }

    public void setScreenshotListener(IScreenshotListener iScreenshotListener) {
        sendLog("调用了 BaseSQwanCore.setScreenshotListener");
        sendLogBase4CP("设置游戏中截图回调");
        this.mScreenshotListener = iScreenshotListener;
    }

    public void showUAgreement(final Context context) {
        sendLog("调用了 BaseSQwanCore.showUAgreement");
        if (sdkapi != null) {
            post(new Runnable() { // from class: com.sqwan.msdk.BaseSQwanCore.30
                @Override // java.lang.Runnable
                public void run() {
                    BaseSQwanCore.sdkapi.showUAgreement(context);
                }
            });
        }
        SqTrackActionManager2.getInstance().trackActionCPTest(SqTrackAction2.SHOW_USER_AGREEMENT);
    }

    public void printLog(int i, String str, String str2) {
        String str3 = "[cp] " + str;
        if (i == 0) {
            SQLog.dt(str3, str2);
            return;
        }
        if (i == 1) {
            SQLog.it(str3, str2);
        } else if (i == 2) {
            SQLog.wt(str3, str2);
        } else if (i == 3) {
            SQLog.et(str3, str2);
        }
    }

    private void handleCallback() {
        LiveshowEngine.getInstance().handleCallback();
        LiveRadioEngine.getInstance().handleCallback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void post(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.mHandler.post(runnable);
        }
    }

    private void handleUninitPlatformPerformFeatureListeners(String str, Object obj, SQResultListener sQResultListener) {
        if (ClassCheckUtils.isExistPerformFeatureConfig() && sdkapi == null && sQResultListener != null) {
            PerformFeature.map.put(str, new PerformFeature(str, obj, sQResultListener));
        }
    }

    private void handleInitPerformFeatureListeners() {
        Platform platform;
        if (!ClassCheckUtils.isExistPerformFeatureConfig()) {
            sendLog("handleInitPerformFeatureListeners return");
            return;
        }
        Iterator it = PerformFeature.map.keySet().iterator();
        while (it.hasNext()) {
            PerformFeature performFeature = (PerformFeature) PerformFeature.map.get((String) it.next());
            if (performFeature.sqResultListener != null && (platform = sdkapi) != null) {
                platform.performFeature(this.context, performFeature.type, performFeature.data, performFeature.sqResultListener);
            }
        }
        PerformFeature.map.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAuthCheck(boolean z) {
        this.isAuthCheck = z;
        SensitiveInfoManager.getInstance().setAuthCheck(z);
        MacLogic.getInstance(this.context).setAuthCheck(z);
        DevLogic.getInstance(this.context).setAuthCheck(z);
        ImeiLogic.getInstance(this.context).setAuthCheck(z);
        RootLogic.getInstance(this.context).setAuthCheck(z);
        SimulatorLogic.getInstance(this.context).setAuthCheck(z);
        SqTrackActionManager2.getInstance().setAuthCheck(z);
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public boolean isSupportLiveVideo() {
        sendLog("调用了 BaseSQwanCore.isSupportLiveVideo");
        return LiveshowEngine.getInstance().isSupportLiveVideo();
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void joinLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.joinLiveshowRoom");
        LiveshowEngine.getInstance().joinLiveshowRoom(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void leaveLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.leaveLiveshowRoom");
        LiveshowEngine.getInstance().leaveLiveshowRoom(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void setLiveshowDestroyCallback(SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.setLiveshowDestroyCallback");
        LiveshowEngine.getInstance().setLiveshowDestroyCallback(sQResultListener);
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void setLiveshowVoiceChangeCallback(SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.setLiveshowVoiceChangeCallback");
        LiveshowEngine.getInstance().setLiveshowVoiceChangeCallback(sQResultListener);
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void performLiveshowFeature(Map<String, String> map, SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.performLiveshowFeature");
        LiveshowEngine.getInstance().performLiveshowFeature(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.api.tool.ILiveRadio
    public boolean isSupportLiveRadio() {
        sendLog("调用了 BaseSQwanCore.isSupportLiveRadio");
        return LiveRadioEngine.getInstance().isSupportLiveRadio();
    }

    @Override // com.sqwan.msdk.api.tool.ILiveRadio
    public void joinLiveRadioRoom(Map<String, String> map, SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.joinLiveRadioRoom");
        LiveRadioEngine.getInstance().joinLiveRadioRoom(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.api.tool.ILiveRadio
    public void leaveLiveRadioRoom(Map<String, String> map, SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.leaveLiveRadioRoom");
        LiveRadioEngine.getInstance().leaveLiveRadioRoom(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.api.tool.ILiveRadio
    public void setLiveRadioDestroyCallback(SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.setLiveRadioDestroyCallback");
        LiveRadioEngine.getInstance().setLiveRadioDestroyCallback(sQResultListener);
    }

    @Override // com.sqwan.msdk.api.tool.ILiveRadio
    public void setLiveRadioVoiceChangeCallback(SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.setLiveRadioVoiceChangeCallback");
        LiveRadioEngine.getInstance().setLiveRadioVoiceChangeCallback(sQResultListener);
    }

    @Override // com.sqwan.msdk.api.tool.ILiveRadio
    public void performLiveRadioFeature(Map<String, String> map, SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.performLiveRadioFeature");
        LiveRadioEngine.getInstance().performLiveRadioFeature(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void showAdReward(Context context, String str, SQResultListener sQResultListener) {
        sendLog("调用了 BaseSQwanCore.showAdReward");
        IAdvertiseMod iAdvertiseMod = (IAdvertiseMod) ModHelper.get(IAdvertiseMod.class);
        if (iAdvertiseMod != null) {
            try {
                iAdvertiseMod.showAdvertiseReward(context, str, sQResultListener);
            } catch (Error e) {
                LogUtil.e("广告模块showAdReward异常 " + e.getMessage());
            }
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void setSQPushTransmitMessageListener(final SQPushTransmitMessageListener sQPushTransmitMessageListener) {
        IPushMod iPushMod = (IPushMod) ModHelper.get(IPushMod.class);
        if (iPushMod != null) {
            iPushMod.setTransmitMessageListener(new IPushMod.TransmitMessageListenerInternal() { // from class: com.sqwan.msdk.-$$Lambda$BaseSQwanCore$X4Vymdy9rlhjoLfICLc5nRaEtsU
                @Override // com.sqwan.common.mod.push.IPushMod.TransmitMessageListenerInternal
                public final void onReceiveTransmitMessage(String str) {
                    BaseSQwanCore.lambda$setSQPushTransmitMessageListener$1(sQPushTransmitMessageListener, str);
                }
            });
        }
    }

    static /* synthetic */ void lambda$setSQPushTransmitMessageListener$1(SQPushTransmitMessageListener sQPushTransmitMessageListener, String str) {
        if (sQPushTransmitMessageListener != null) {
            sQPushTransmitMessageListener.onReceiveTransmitMessage(str);
        }
    }

    public void sendFeedbackSQPushMessage(Context context, Map<String, String> map) {
        IPushMod iPushMod = (IPushMod) ModHelper.get(IPushMod.class);
        if (iPushMod != null) {
            iPushMod.sendFeedback(context, map);
        }
    }

    private void setUserInfoToPushService(String str) {
        String uid = ((IAccountMod) ModHelper.get(IAccountMod.class)).getUid();
        IPushMod iPushMod = (IPushMod) ModHelper.get(IPushMod.class);
        if (iPushMod != null) {
            iPushMod.setUserInfo(uid, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetRoleInfo() {
        MultiSDKUtils.setServerid(this.context, "");
        MultiSDKUtils.setRoleid(this.context, "");
        MultiSDKUtils.setRolename(this.context, "");
        MultiSDKUtils.setRolelevel(this.context, "");
        MultiSDKUtils.setVipLevel(this.context, "");
        MultiSDKUtils.setServerName(this.context, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDevConfig() {
        DeviceUtils.getAndroidId(this.context);
    }

    private void initToken() {
        AccountCache.setToken(this.context, "");
    }
}
