package com.huya.berry.client;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.duowan.auk.ArkValue;
import com.duowan.auk.asignal.SignalCenterApiImpl;
import com.duowan.auk.helper.FileStorage;
import com.duowan.auk.http.HttpClient;
import com.duowan.auk.util.Config;
import com.duowan.auk.util.DefaultLogger;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.HuyaStatisAgent;
import com.duowan.live.one.module.report.Report;
import com.duowan.live.one.module.report.ReportApiImpl;
import com.duowan.live.receiver.NetworkStateReceiver;
import com.duowan.monitor.core.UserInfoProvider;
import com.duowan.monitor.jce.UserId;
import com.duowan.networkmars.data.MarsProperties;
import com.duowan.networkmars.hysignal.HySignalHelper;
import com.duowan.networkmars.push.TransmitService;
import com.duowan.networkmars.wup.HaWupFunction;
import com.duowan.networkmars.wup.WupHelper;
import com.huya.berry.client.HuyaBerry;
import com.huya.berry.client.customui.CustomUICallback;
import com.huya.berry.client.customui.model.ErrorInfo;
import com.huya.berry.gamesdk.Params;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.crash.ICrashService;
import com.huya.berry.gamesdk.gameid.GameIdOptions;
import com.huya.berry.gamesdk.module.ICommonService;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.AppUtils;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.PreferenceUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.UIUtil;
import com.huya.berry.module.PresenterConfigHelper;
import com.huya.ciku.apm.MonitorCenter;
import com.huya.component.crash.CrashHandler;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.TokenInfo;
import com.huya.component.user.api.UserApi;
import com.huya.hysignal.listener.HySignalGuidListener;
import com.huya.live.common.api.BaseApi;
import com.huya.live.ns.NSWrapper;
import com.huya.live.ns.impl.INSDebugCrashListener;
import com.huya.live.service.ServiceCenter;
import com.huya.live.service.ServiceHelper;
import com.huya.mtp.hyns.api.NSUserInfoApi;
import com.huya.mtp.hyns.hysignal.HalConfigWrapper;
import com.huyaudbunify.bean.ResGetTicket;
import com.hysdkproxy.LoginProxy;
import com.sqwan.liveshow.huya.SqR;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class HuyaBerryImpl extends HuyaBerry {
    public static final String CLIENT_TYPE = "adr_game_sdk";
    public static final String FORMAT_SHUYAUA = "%s&%s&%s&%s";
    private static final String SDK_NAME = "sysdk";
    private static final String TAG = "HuyaBerryImpl";
    private IHuyaBerryData mHuyaBerryData;
    private ILiveStream mLiveStream;
    private ServerStartManager mServerStartManager;
    private UserId mUserId;
    private boolean mIsInit = false;
    private boolean mIsInitApm = false;
    private NetworkStateReceiver mReceiver = new NetworkStateReceiver();
    private BaseApi.IBaseApiCallback mBaseApiCallback = new BaseApi.IBaseApiCallback() { // from class: com.huya.berry.client.HuyaBerryImpl.7
        @Override // com.huya.live.common.api.BaseApi.IBaseApiCallback
        public com.duowan.HUYA.UserId getUserId() {
            return UserApi.getUserId();
        }
    };

    HuyaBerryImpl() {
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void init(Application application, HuyaBerryConfig huyaBerryConfig) {
        if (this.mIsInit) {
            L.error(TAG, "init fail: mIsInit == true");
            HuyaBerryCallback.getInstance().callbackInit(false, "init fail: mIsInit == true");
            return;
        }
        if (application == null) {
            L.error(TAG, "init fail: application == null");
            HuyaBerryCallback.getInstance().callbackInit(false, "init fail: application == null");
            return;
        }
        if (!CommonUtil.isMainProcess(application)) {
            L.error(TAG, "init fail: not mainProcess");
            HuyaBerryCallback.getInstance().callbackInit(false, "init fail: not mainProcess");
            return;
        }
        if (CommonUtil.isEmulator()) {
            L.error(TAG, "init fail: monitor");
            HuyaBerryCallback.getInstance().callbackInit(false, "init fail: monitor");
            return;
        }
        this.mIsInit = true;
        ArkValue.gContext = application;
        Config.init(application, new Config.IConfig() { // from class: com.huya.berry.client.HuyaBerryImpl.1
            @Override // com.duowan.auk.util.Config.IConfig
            public SharedPreferences getSpImpl(Context context, String str) {
                return context.getSharedPreferences(str, 0);
            }
        });
        ArkValue.init(application);
        ArkValue.setChannelName(AppUtils.getChannel());
        SdkProperties.appId.set(huyaBerryConfig.appId());
        SdkProperties.appKey.set(huyaBerryConfig.appKey());
        SdkProperties.gameId.set(Integer.valueOf(huyaBerryConfig.gameId()));
        SdkProperties.isLandscape.set(Boolean.valueOf(huyaBerryConfig.landscapeMode()));
        SdkProperties.isOneKeyGangUp.set(Boolean.valueOf(huyaBerryConfig.oneKeyGangUp()));
        SdkProperties.isNeedPlay.set(Boolean.valueOf(huyaBerryConfig.isNeedPlay()));
        SdkProperties.hidePauseBtn.set(Boolean.valueOf(huyaBerryConfig.hidePauseBtn()));
        if (huyaBerryConfig.isOpenBugly()) {
            CrashHandler.getInstance().init();
            ICrashService iCrashService = (ICrashService) ServiceCenter.instance().getService(ICrashService.class);
            if (iCrashService != null) {
                iCrashService.init();
            }
        }
        initBaseApi();
        ArkValue.setDebuggable(huyaBerryConfig.debugMode());
        PreferenceUtil.init(application);
        HttpClient.init(application);
        initLog(application);
        initLogin();
        initMTP();
        ServerStartManager serverStartManager = new ServerStartManager();
        this.mServerStartManager = serverStartManager;
        serverStartManager.startModules();
        ServiceHelper.createService(ICrashService.class, CrashService.class);
        ICommonService iCommonService = (ICommonService) ServiceCenter.instance().getService(ICommonService.class);
        if (iCommonService != null) {
            iCommonService.metricReport(new CommonEvent.WupMetricReport(1, 0, 0));
        }
        Report.event(SdkReportConst.PV_INIT);
        HuyaBerryCallback.getInstance().callbackInit(true, "");
        UIUtil.initPoint();
        SdkProperties.sdkMode.set(huyaBerryConfig.cameraMode() ? SdkProperties.SDKMode.CAPTURE_BY_CAMERA : SdkProperties.SDKMode.CAPTURE_BY_SCREEN);
        ArkValue.gContext.registerReceiver(this.mReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        PresenterConfigHelper.requestPresenterConfig();
        this.mHuyaBerryData = new HuyaBerryData();
    }

    public static void initLog(Context context) {
        String str = FileStorage.getInstance().getRootDir(FileStorage.Location.SDCard).getAbsolutePath() + "/berry/logs";
        DefaultLogger defaultLogger = new DefaultLogger();
        L.isStoreExist = FileStorage.isStoreExist(FileStorage.Location.SDCard);
        defaultLogger.init(context, str, ArkValue.debuggable() || ArkValue.gIsSnapshot);
        L.setLogger(defaultLogger);
    }

    private void initMTP() {
        L.info(TAG, "onCreate start -- initMTP");
        WupHelper.setDefineVersion(AppUtils.getVersion());
        NSWrapper.NSHalInit(ArkValue.debuggable(), new HalConfigWrapper.Builder(ArkValue.gContext).setGuidListener(new HySignalGuidListener() { // from class: com.huya.berry.client.HuyaBerryImpl.2
            @Override // com.huya.hysignal.listener.HySignalGuidListener
            public void onGuid(String str) {
                L.info(HuyaBerryImpl.TAG, "onGetGuid: " + str);
                UserApi.setGUID(str);
                LoginProxy.getInstance().setGuid(str);
                Report.reportGuid(str);
            }
        }).setUnableLostMsgUris(HySignalHelper.getCareHistoryMsgUriSet()).setUserInfo(new NSUserInfoApi.NSUserInfo.Builder().setUid(LoginApi.getLastLoginUid()).build()).setUa(getSHuYaUA()).setAppSrc(WupHelper.getNSAppId()).setGuid(UserApi.getGUID()).build(), new INSDebugCrashListener() { // from class: com.huya.berry.client.HuyaBerryImpl.3
            @Override // com.huya.live.ns.impl.INSDebugCrashListener
            public void crashIfDebug(boolean z, Throwable th, String str, Object... objArr) {
                ICrashService iCrashService = (ICrashService) ServiceCenter.instance().getService(ICrashService.class);
                if (iCrashService != null) {
                    iCrashService.postCatchedException(new Throwable(String.format(str, objArr), th));
                }
            }
        });
        if (AppUtils.isMainProcess(ArkValue.gContext)) {
            wupInit();
        }
        TransmitService.getInstance().init(1, 300).start();
    }

    private void wupInit() {
        HaWupFunction.mCb = new HaWupFunction.OnCallback() { // from class: com.huya.berry.client.HuyaBerryImpl.4
            @Override // com.duowan.networkmars.wup.HaWupFunction.OnCallback
            public void onDecodeError(String str, String str2, byte[] bArr) {
                if (MarsProperties.uploadWupDecodePacketError.get().booleanValue() && MarsProperties.uploadWupDecodePacketErrorFunName.get().contains(str2)) {
                    String str3 = String.format(Locale.CHINA, "decodePacket error time:%s ServantName:%s, FuncName:%s, rsp len %d hex:%s end", WupHelper.toDateTimeFormat(System.currentTimeMillis()), str, str2, Integer.valueOf(bArr == null ? 0 : bArr.length), WupHelper.bytesToHexString(bArr));
                    Exception exc = new Exception(str3);
                    ICrashService iCrashService = (ICrashService) ServiceCenter.instance().getService(ICrashService.class);
                    if (iCrashService != null) {
                        iCrashService.postCatchedException(exc);
                    }
                    L.error("HaWupFunction", str3);
                }
            }

            @Override // com.duowan.networkmars.wup.HaWupFunction.OnCallback
            public void onRespError(Throwable th) {
                ICrashService iCrashService = (ICrashService) ServiceCenter.instance().getService(ICrashService.class);
                if (iCrashService != null) {
                    iCrashService.postCatchedException(th);
                }
                L.error("HaWupFunction onRespError", th);
            }
        };
        WupHelper.mCb = new WupHelper.OnCallback() { // from class: com.huya.berry.client.HuyaBerryImpl.5
            @Override // com.duowan.networkmars.wup.WupHelper.OnCallback
            public Exception onParseJceError(Exception exc, String str, byte[] bArr) {
                if (!MarsProperties.uploadWupDecodePacketError.get().booleanValue() || !MarsProperties.uploadWupDecodePacketErrorFunName.get().contains(str)) {
                    return null;
                }
                Exception exc2 = new Exception(String.format(Locale.CHINA, "parseJce error time:%s, data len %d hex:%s end", WupHelper.toDateTimeFormat(System.currentTimeMillis()), Integer.valueOf(bArr == null ? 0 : bArr.length), WupHelper.bytesToHexString(bArr)), exc);
                ICrashService iCrashService = (ICrashService) ServiceCenter.instance().getService(ICrashService.class);
                if (iCrashService != null) {
                    iCrashService.postCatchedException(exc2);
                }
                return exc2;
            }
        };
    }

    private void initBaseApi() {
        String string = ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(ArkValue.gIsSnapshot ? SqR.string.hyberry_appkey_hiido_debug : SqR.string.hyberry_appkey_hiido));
        String str = (ArkValue.gIsSnapshot || ArkValue.debuggable()) ? "huya_sysdk_android_test" : "huya_sysdk_android";
        HuyaStatisAgent.getInstance().getHuyaStatisApi().setGameId(SdkProperties.gameId.get().intValue());
        ReportApiImpl reportApiImpl = new ReportApiImpl();
        reportApiImpl.init(string, str, "official", AppUtils.getVersion());
        BaseApi.init(this.mBaseApiCallback, new BaseApi.OnCrashListener() { // from class: com.huya.berry.client.HuyaBerryImpl.6
            @Override // com.huya.live.common.api.BaseApi.OnCrashListener
            public void onCrashIfDebug(String str2, Throwable th) {
                throw new RuntimeException(th);
            }
        });
        BaseApi.setSignalCenterApi(new SignalCenterApiImpl());
        BaseApi.setReportApi(reportApiImpl);
    }

    private void initLogin() {
        LoginApi.init(new LoginApi.ILoginCallback() { // from class: com.huya.berry.client.HuyaBerryImpl.8
            @Override // com.huya.component.login.api.LoginApi.ILoginCallback
            public boolean isHyUdbLoging() {
                return LoginProxy.getInstance().isLogin();
            }

            @Override // com.huya.component.login.api.LoginApi.ILoginCallback
            public int getHyUdbByPass() {
                if (LoginProxy.getInstance().isLogin()) {
                    return LoginProxy.getInstance().getHyUdbByPass();
                }
                return 0;
            }

            @Override // com.huya.component.login.api.LoginApi.ILoginCallback
            public TokenInfo getTokenInfo() {
                ResGetTicket defaultToken = LoginProxy.getInstance().getDefaultToken();
                if (defaultToken == null) {
                    return null;
                }
                return new TokenInfo(defaultToken.getTokenType(), defaultToken.getUid(), defaultToken.getToken());
            }
        });
        LoginApi.setUdbAppId(Params.UDB_APP_ID);
        LoginApi.setUdbVerifyAppId(Params.UDB_VERIFY_APP_ID);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void setPlayConfig(HuyaBerryPlayConfig huyaBerryPlayConfig) {
        SdkProperties.floatSendDanmu.set(Boolean.valueOf(huyaBerryPlayConfig.floatSendDanmu()));
        SdkProperties.normalSendDanmu.set(Boolean.valueOf(huyaBerryPlayConfig.normalSendDanmu()));
        SdkProperties.showQuality.set(Boolean.valueOf(huyaBerryPlayConfig.showQuality()));
        SdkProperties.showSwitchDanmu.set(Boolean.valueOf(huyaBerryPlayConfig.showSwitchDanmu()));
        SdkProperties.showSwitchVoice.set(Boolean.valueOf(huyaBerryPlayConfig.showSwitchVoice()));
        SdkProperties.showFullScreen.set(Boolean.valueOf(huyaBerryPlayConfig.showFullScreen()));
        SdkProperties.showLiveInfo.set(Boolean.valueOf(huyaBerryPlayConfig.showLiveInfo()));
        SdkProperties.showSubscribe.set(Boolean.valueOf(huyaBerryPlayConfig.showSubscribe()));
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void startLive(Activity activity, StartLiveConfig startLiveConfig) {
        if (!this.mIsInit) {
            L.error(TAG, "no init");
            return;
        }
        if (SdkProperties.isLiving.get().booleanValue()) {
            L.error(TAG, SdkProperties.MarkIsLiving);
            return;
        }
        if (ArkValue.gContext == null) {
            L.error(TAG, "ArkValue.gContext == null");
            Toast.makeText(activity, "初始化失败，请重启游戏", 1).show();
        } else if (initLiveStream(activity, null)) {
            this.mLiveStream.setFromStartlive();
            this.mLiveStream.resetIsCallbackStartUp();
            this.mLiveStream.openLiveList(startLiveConfig);
            L.info(TAG, "getClientType == " + WupHelper.getClientType());
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void startLive(Activity activity, String str, String str2) {
        if (!this.mIsInit) {
            L.error(TAG, "no init");
            return;
        }
        if (SdkProperties.isLiving.get().booleanValue()) {
            L.error(TAG, SdkProperties.MarkIsLiving);
            return;
        }
        if (ArkValue.gContext == null) {
            L.error(TAG, "ArkValue.gContext == null");
            Toast.makeText(activity, "初始化失败，请重启游戏", 1).show();
        } else if (initLiveStream(activity, null)) {
            this.mLiveStream.startLiveDirectly(str, str2);
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void pauseLive(boolean z) {
        if (!this.mIsInit) {
            L.error(TAG, "no init");
            return;
        }
        ILiveStream iLiveStream = this.mLiveStream;
        if (iLiveStream == null) {
            return;
        }
        iLiveStream.pauseLive(z);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void changeLandscapeMode(boolean z) {
        if (!this.mIsInit) {
            L.error(TAG, "no init");
        } else {
            this.mLiveStream.setLandscape(z);
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void changeGame(int i, CustomUICallback customUICallback) {
        boolean z;
        if (!this.mIsInit) {
            L.error(TAG, "no init");
            return;
        }
        if (SdkProperties.isLiving.get().booleanValue()) {
            customUICallback.onResultCallback(1, new ErrorInfo("开播中无法切换品类"));
            L.error(TAG, SdkProperties.MarkIsLiving);
            return;
        }
        String[] strArr = GameIdOptions.getInstance().gameIdArr;
        if (strArr == null || strArr.length == 0) {
            customUICallback.onResultCallback(1, new ErrorInfo("没有该品类的权限"));
            L.error(TAG, "no gameId");
            return;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= strArr.length) {
                z = false;
                break;
            } else {
                if (Integer.parseInt(strArr[i2].split("_")[0]) == i) {
                    z = true;
                    break;
                }
                i2++;
            }
        }
        if (!z) {
            customUICallback.onResultCallback(1, new ErrorInfo("没有该品类的权限"));
            L.error(TAG, "no canChange gameId");
            return;
        }
        SdkProperties.gameId.set(Integer.valueOf(i));
        L.info(TAG, "change gameId ok");
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.changeGameId(i);
        customUICallback.onResultCallback(0, null);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void changeGame(String str, CustomUICallback customUICallback) {
        boolean z;
        if (!this.mIsInit) {
            L.error(TAG, "no init");
            return;
        }
        if (SdkProperties.isLiving.get().booleanValue()) {
            customUICallback.onResultCallback(1, new ErrorInfo("开播中无法切换品类"));
            L.error(TAG, SdkProperties.MarkIsLiving);
            return;
        }
        String[] strArr = GameIdOptions.getInstance().gameIdArr;
        if (strArr == null || strArr.length == 0) {
            customUICallback.onResultCallback(1, new ErrorInfo("没有该品类的权限"));
            L.error(TAG, "no gameId");
            return;
        }
        int i = 2336;
        int i2 = 0;
        while (true) {
            if (i2 >= strArr.length) {
                z = false;
                break;
            } else {
                if (strArr[i2].split("_")[1].equals(str)) {
                    i = Integer.parseInt(strArr[i2].split("_")[0]);
                    z = true;
                    break;
                }
                i2++;
            }
        }
        if (!z) {
            customUICallback.onResultCallback(1, new ErrorInfo("没有该品类的权限"));
            L.error(TAG, "no canChange gameId");
            return;
        }
        SdkProperties.gameId.set(Integer.valueOf(i));
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.changeGameId(i);
        customUICallback.onResultCallback(0, null);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void onActivityResult(int i, int i2, Intent intent) {
        if (!this.mIsInit) {
            L.error(TAG, "no init");
            return;
        }
        ILiveStream iLiveStream = this.mLiveStream;
        if (iLiveStream == null) {
            return;
        }
        iLiveStream.onActivityResult(i, i2, intent);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void uninit() {
        ILiveStream iLiveStream = this.mLiveStream;
        if (iLiveStream != null) {
            iLiveStream.uninit();
            this.mLiveStream = null;
        }
        IHuyaBerryData iHuyaBerryData = this.mHuyaBerryData;
        if (iHuyaBerryData != null) {
            iHuyaBerryData.uninit();
            this.mHuyaBerryData = null;
        }
        this.mIsInitApm = false;
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void setGameAccountID(String str) {
        SdkProperties.gameAccountID.set(str);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void setGangUpTip(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            SdkProperties.notLoginGangUpTip.set(str);
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        SdkProperties.loginGangUpTip.set(str2);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void setBerryEventDelegate(HuyaBerry.BerryEvent berryEvent) {
        HuyaBerryCallback.getInstance().setBerryEventListener(berryEvent);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void sendPlayerData(HuyaBerry.BerryPlayerDataHelper berryPlayerDataHelper) {
        ILiveStream iLiveStream = this.mLiveStream;
        if (iLiveStream == null) {
            return;
        }
        iLiveStream.sendPlayerData(berryPlayerDataHelper);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void sendGameUpData(String str) {
        ILiveStream iLiveStream = this.mLiveStream;
        if (iLiveStream == null) {
            return;
        }
        iLiveStream.sendGameUpData(str);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void customUIStartLive(Activity activity, CustomUICallback customUICallback) {
        Report.event(SdkReportConst.CLICK_INTERFACE_BEGINLIVE);
        if (SdkProperties.isLiving.get().booleanValue()) {
            customUICallback.onResultCallback(1, null);
        } else if (initLiveStream(activity, customUICallback)) {
            this.mLiveStream.onStartLive(customUICallback);
            customUICallback.onResultCallback(0, null);
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void showDanmuView(FrameLayout frameLayout, long j) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.showDanmuView(frameLayout, j);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void hideDanmuView() {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.hideDanmuView();
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void setReceiveDanmuData(boolean z, long j) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.setReceiveDanmuData(z, j);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void sendDanmu(long j, long j2, long j3, Activity activity, String str, CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.sendDanmu(j, j2, j3, activity, str, customUICallback);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void getLiveListData(boolean z, CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.onGetLiveListData(z, customUICallback);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void getTagListData(CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.getTagListData(customUICallback);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void getLiveListDataByTag(String str, boolean z, CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.getLiveListDataByTag(str, z, customUICallback);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void getLiveData(long j, CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.onGetLiveData(j, customUICallback);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void getLiveDataByRoomId(long j, CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.getLiveDataByRoomId(j, customUICallback);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void watchLive(long j, Activity activity, CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.watchLive(j, activity, customUICallback);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void watchLiveByUid(long j, Activity activity, CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.watchLiveByUid(j, activity, customUICallback);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void joinChannel(long j, int i) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.joinChannel(j, i);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void subscribe(long j, CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.subscribe(j, customUICallback);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void unSubscribe(long j, CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.unSubscribe(j, customUICallback);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void querySubscribeStatus(long j, CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.querySubscribeStatus(j, customUICallback);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void fullScreenPlay() {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.fullScreen();
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void fullScreenPlay(long j) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.fullScreen(j);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void smallWindowPlay(Activity activity) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.smallWindowPlay(activity);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void pauseVideoPlay() {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.pauseVideoPlay();
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void startVideoPlay() {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.startVideoPlay();
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void switchDanmu(boolean z) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.switchDanmu(z);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void switchVoice(boolean z) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.switchVoice(z);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void closeFloat() {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.closeFloat();
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void customUIGetAuthorInfo(Activity activity, CustomUICallback customUICallback) {
        Report.event(SdkReportConst.CLICK_INTERFACE_USERINFO);
        if (initLiveStream(activity, customUICallback)) {
            this.mLiveStream.onGetAuthorInfo(customUICallback);
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void customUIModifyNickname(Activity activity, CustomUICallback customUICallback) {
        Report.event(SdkReportConst.CLICK_INTERFACE_NAMEEDIT);
        if (initLiveStream(activity, customUICallback)) {
            this.mLiveStream.onShowModifyNickname(customUICallback);
            customUICallback.onResultCallback(0, null);
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void customUIModifyTitle(Activity activity, CustomUICallback customUICallback, String str) {
        Report.event(SdkReportConst.CLICK_INTERFACE_TITLEEDIT);
        if (TextUtils.isEmpty(str)) {
            customUICallback.onResultCallback(2, null);
        } else if (initLiveStream(activity, customUICallback)) {
            this.mLiveStream.onModifyTitle(customUICallback, str);
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void customUIModifyAnnouncement(Activity activity, CustomUICallback customUICallback, String str) {
        Report.event(SdkReportConst.CLICK_INTERFACE_NOTICEEDIT);
        if (initLiveStream(activity, customUICallback)) {
            this.mLiveStream.onModifyAnnouncement(customUICallback, str);
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void customUILogin(Activity activity, CustomUICallback customUICallback) {
        Report.event(SdkReportConst.CLICK_INTERFACE_LOGIN);
        if (initLiveStream(activity, customUICallback)) {
            if (LoginProperties.loginState.get() == LoginProperties.LoginState.LoggedIn && LoginProperties.uid.get().longValue() != 0) {
                customUICallback.onResultCallback(1, new ErrorInfo("已登录"));
            } else {
                this.mLiveStream.onLogin(customUICallback);
                customUICallback.onResultCallback(0, null);
            }
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void customUILogout(Activity activity, CustomUICallback customUICallback) {
        Report.event(SdkReportConst.CLICK_INTERFACE_LOGOUT);
        if (initLiveStream(activity, customUICallback)) {
            this.mLiveStream.onLogout(customUICallback);
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void customUIGetResolution(Activity activity, CustomUICallback customUICallback) {
        Report.event(SdkReportConst.CLICK_INTERFACE_GETQUALITY);
        if (initLiveStream(activity, customUICallback)) {
            this.mLiveStream.onGetResolution(customUICallback);
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void customUISetResolution(Activity activity, CustomUICallback customUICallback, int i) {
        Report.event(SdkReportConst.CLICK_INTERFACE_SETQUALITY);
        if (initLiveStream(activity, customUICallback)) {
            this.mLiveStream.onSetResolution(customUICallback, i);
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void rtmpPushLive(Activity activity, String str, CustomUICallback customUICallback) {
        if (!this.mIsInit) {
            L.error(TAG, "no init");
        } else if (initLiveStream(activity, customUICallback)) {
            this.mLiveStream.getLiveUrl(str, customUICallback);
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void stopRtmpLive(Activity activity, CustomUICallback customUICallback) {
        if (!this.mIsInit) {
            L.error(TAG, "no init");
        } else if (initLiveStream(activity, customUICallback)) {
            this.mLiveStream.stopRtmpLive(customUICallback);
        }
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void customUIOpenQuality(Activity activity, CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.customUIOpenQuality(activity, customUICallback);
    }

    @Override // com.huya.berry.client.HuyaBerry
    public void customUIOpenSendDanmu(Activity activity, CustomUICallback customUICallback) {
        if (this.mHuyaBerryData == null) {
            this.mHuyaBerryData = new HuyaBerryData();
        }
        this.mHuyaBerryData.customUIOpenSendDanmu(activity, customUICallback);
    }

    private void initApmModule() {
        if (this.mIsInitApm) {
            return;
        }
        this.mIsInitApm = true;
        this.mUserId = null;
        MonitorCenter.getInstance().initMonitorSDK(ArkValue.gContext, new UserInfoProvider() { // from class: com.huya.berry.client.HuyaBerryImpl.9
            @Override // com.duowan.monitor.core.UserInfoProvider
            public UserId getUserId() {
                if (HuyaBerryImpl.this.mUserId == null || HuyaBerryImpl.this.mUserId.lUid == 0 || TextUtils.isEmpty(HuyaBerryImpl.this.mUserId.sGuid)) {
                    com.duowan.HUYA.UserId apmUserId = UserApi.getApmUserId();
                    HuyaBerryImpl.this.mUserId = new UserId(apmUserId.lUid, apmUserId.sGuid, apmUserId.sToken, apmUserId.sHuYaUA);
                }
                return HuyaBerryImpl.this.mUserId;
            }
        });
    }

    private boolean initLiveStream(Activity activity, CustomUICallback customUICallback) {
        if (activity == null) {
            if (customUICallback == null) {
                return false;
            }
            customUICallback.onResultCallback(2, null);
            return false;
        }
        ILiveStream iLiveStream = this.mLiveStream;
        if (iLiveStream == null || iLiveStream.getActivity() == null) {
            this.mLiveStream = new LiveStream(activity);
        }
        if (activity.hashCode() != this.mLiveStream.getActivity().hashCode()) {
            ILiveStream iLiveStream2 = this.mLiveStream;
            if (iLiveStream2 != null) {
                iLiveStream2.uninit();
                this.mLiveStream = null;
            }
            this.mLiveStream = new LiveStream(activity);
        }
        initApmModule();
        return true;
    }

    public static String getSHuYaUA() {
        return String.format("%s&%s&%s&%s", "adr_game_sdk", AppUtils.getVersion(), SdkProperties.appId.get(), SdkProperties.gameId.get());
    }
}
