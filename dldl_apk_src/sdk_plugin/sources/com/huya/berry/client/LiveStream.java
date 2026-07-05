package com.huya.berry.client;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.duowan.HUYA.BeginLiveRsp;
import com.duowan.HUYA.CreateAddrReq;
import com.duowan.HUYA.CreateAddrRsp;
import com.duowan.HUYA.GetLiveSummaryRsp;
import com.duowan.HUYA.GetLivingInfoRsp;
import com.duowan.HUYA.GetPresenterVeriInfoRsp;
import com.duowan.HUYA.LiveAnnouncementFetchRsp;
import com.duowan.HUYA.OpenRtmpEndLiveReq;
import com.duowan.HUYA.PopupButtonInfo;
import com.duowan.HUYA.PresenterPopData;
import com.duowan.HUYA.TransMsgToViewerRsp;
import com.duowan.HUYA.ZhixuPopupNotify;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.asignal.SignalCenter;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.live.common.webview.jssdk.EventManager;
import com.duowan.live.login.LoginReportConstants;
import com.duowan.live.login.api.ILoginService;
import com.duowan.live.one.module.report.Report;
import com.duowan.live.one.module.uploadLog.FeedBackInterface;
import com.duowan.monitor.jce.Dimension;
import com.duowan.monitor.jce.MetricDetail;
import com.huya.berry.client.HuyaBerry;
import com.huya.berry.client.customui.CustomUICallback;
import com.huya.berry.client.customui.CustomUIPresenterImpl;
import com.huya.berry.client.customui.ICustomUIPresenter;
import com.huya.berry.client.customui.model.AuthorInfo;
import com.huya.berry.client.customui.model.RtmpPushInfo;
import com.huya.berry.client.tasks.LiveListTask;
import com.huya.berry.endlive.api.ISdkPlayerService;
import com.huya.berry.endlive.data.EndLiveData;
import com.huya.berry.forcelive.api.IForceLiveService;
import com.huya.berry.forcelive.api.IForceLiveStream;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.certicate.CerticateHelper;
import com.huya.berry.gamesdk.certicate.CertificateCallback;
import com.huya.berry.gamesdk.module.ICommonService;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.report.monitor.MonitorReport;
import com.huya.berry.gamesdk.report.monitor.MonitorReportConst;
import com.huya.berry.gamesdk.report.monitor.MonitorReportInterface;
import com.huya.berry.gamesdk.resolutions.LivingParams;
import com.huya.berry.gamesdk.resolutions.ResolutionOptions;
import com.huya.berry.gamesdk.utils.AppStatusReportUtil;
import com.huya.berry.gamesdk.utils.AppUtils;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.DigitUtil;
import com.huya.berry.gamesdk.utils.NetworkUtil;
import com.huya.berry.gamesdk.utils.PermissionTool;
import com.huya.berry.gamesdk.utils.PreferenceUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.TaskExecutor;
import com.huya.berry.gamesdk.utils.UIUtil;
import com.huya.berry.gamesdk.widgets.HtmlAlertDialog;
import com.huya.berry.gamesdk.widgets.LiveAlert;
import com.huya.berry.gamesdk.wup.WupHelper;
import com.huya.berry.modifytitle.InputTitleFragment;
import com.huya.berry.modifytitle.event.InputTitleCallback;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.module.PresenterConfigHelper;
import com.huya.berry.module.commonevent.LivePresenterInterface;
import com.huya.berry.module.help.LiveHelper;
import com.huya.berry.module.live.ISdkLiveService;
import com.huya.berry.module.live.LiveCallback;
import com.huya.berry.module.live.LiveInterface;
import com.huya.berry.module.live.PressionSettingUtil;
import com.huya.berry.module.living.heartbeat.BaseHeartBeat;
import com.huya.berry.module.living.heartbeat.GangUpHeartBeat;
import com.huya.berry.sdkcamera.event.CameraCallback;
import com.huya.berry.sdkcamera.event.CameraListener;
import com.huya.berry.sdklive.api.ILiveService;
import com.huya.berry.sdklive.event.FloatWinInterface;
import com.huya.berry.sdklivelist.api.ISdkLiveListService;
import com.huya.berry.sdklivelist.api.LiveListListener;
import com.huya.berry.webview.JssdkConst;
import com.huya.berry.webview.WebviewApi;
import com.huya.ciku.apm.MonitorCenter;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.LoginCallback;
import com.huya.component.login.api.LoginInterface;
import com.huya.component.user.UserProperties;
import com.huya.component.user.api.IUserService;
import com.huya.live.channelinfo.impl.wup.IChannelInfoWup;
import com.huya.live.common.api.BaseApi;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.live.rxutils.SchedulerUtils;
import com.huya.live.service.ServiceCenter;
import com.huya.mtp.hyns.NS;
import com.huya.mtp.utils.NetworkUtils;
import com.huya.mtp.utils.StringUtils;
import com.huya.statistics.core.StatisticsContent;
import com.sqwan.bugless.core.ParamsManager;
import com.sqwan.msdk.BaseSQwanCore;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveStream implements ILiveStream, LiveListListener {
    private static final int CODE_ALERT_RESULT = 1002;
    private static final int GAME_SEND_DATA_TYPE = 1000120;
    private static final int HUWAI_GAME_ID = 2165;
    private static final String HUYA_MAIN = "https://www.huya.com/";
    private static final int RECORD_AUDIO = 1003;
    private static final String TAG = "LiveStream";
    private WeakReference<Activity> mActivity;
    private ICustomUIPresenter mCustomUIPresenter;
    private IForceLiveStream mForceLiveStream;
    private GangUpHeartBeat mGangUpHeartBeat;
    private CustomUICallback mGetAuthorInfoCallBack;
    private CustomUICallback mGetRtmpUrlCallBack;
    private boolean mIsBeginLive;
    private boolean mIsCallbackEndLive;
    private boolean mIsCallbackStartUp;
    private boolean mIsDirect;
    private boolean mIsVerify;
    private LiveListTask mLiveListTask;
    private long mLiveTime;
    private String mLiveTitle;
    private boolean mReadyToStartLive;
    private volatile int mSendDataCount;
    private StartLiveConfig mStartLiveConfig;
    private CustomUICallback mStopRtmpLiveCallBack;
    private boolean mIsStartUp = false;
    private boolean mAutoStartLive = false;
    private Runnable mSendDataCountResetTask = new Runnable() { // from class: com.huya.berry.client.LiveStream.1
        @Override // java.lang.Runnable
        public void run() {
            LiveStream.this.mSendDataCount = 0;
        }
    };
    private Runnable mQueryUserInfoTask = new Runnable() { // from class: com.huya.berry.client.LiveStream.6
        @Override // java.lang.Runnable
        public void run() {
            if (UserProperties.roomId.get().intValue() <= 0) {
                TaskExecutor.uiHandler().removeCallbacks(LiveStream.this.mQueryUserInfoTask);
                LiveStream.this.queryUserInfo();
            } else {
                LiveStream.this.getAuthorInfoCallBack();
            }
        }
    };
    private CameraListener mPreviewListener = new CameraListener() { // from class: com.huya.berry.client.LiveStream.15
        @Override // com.huya.berry.sdkcamera.event.CameraListener
        public void onSurfaceDestroy() {
            if (!SdkProperties.isLiving.get().booleanValue()) {
            }
        }
    };

    public LiveStream(Activity activity) {
        WeakReference<Activity> weakReference = new WeakReference<>(activity);
        this.mActivity = weakReference;
        this.mLiveListTask = new LiveListTask(weakReference.get());
        CustomUIPresenterImpl customUIPresenterImpl = new CustomUIPresenterImpl(this);
        this.mCustomUIPresenter = customUIPresenterImpl;
        customUIPresenterImpl.onCreate();
        IForceLiveService iForceLiveService = (IForceLiveService) ServiceCenter.instance().getService(IForceLiveService.class);
        if (iForceLiveService != null) {
            this.mForceLiveStream = iForceLiveService.getForceLiveStream(activity);
        }
        this.mLiveListTask.setListener(this);
        SignalCenter.register(this);
        initWebview();
    }

    @Override // com.huya.berry.client.ILiveStream
    public void openLiveList(StartLiveConfig startLiveConfig) {
        IForceLiveStream iForceLiveStream = this.mForceLiveStream;
        if ((iForceLiveStream != null && iForceLiveStream.getMediaClient() != null) || startLiveConfig == null) {
            L.error(TAG, "duplicate start live.");
            this.mIsCallbackStartUp = true;
            return;
        }
        L.info(TAG, "openLiveList,landscapeMode:" + startLiveConfig.landscapeMode() + ",isHasLiveList:" + startLiveConfig.isHasLiveList());
        setIsDirect(false);
        this.mStartLiveConfig = startLiveConfig;
        SdkProperties.isLandscape.set(Boolean.valueOf(startLiveConfig.landscapeMode()));
        if (!this.mIsCallbackStartUp) {
            ILoginService iLoginService = (ILoginService) ServiceCenter.instance().getService(ILoginService.class);
            if (iLoginService == null) {
                return;
            }
            if (LoginProperties.uid.get().longValue() > 0 || !iLoginService.needAutoLogin()) {
                HuyaBerryCallback.getInstance().callbackStartUp();
                this.mIsCallbackStartUp = true;
            }
        }
        if (!PreferenceUtil.getEndLiveCallback() && PreferenceUtil.getLiveId() > 0) {
            getLiveSummary(PreferenceUtil.getLiveId(), true);
        }
        this.mLiveListTask.start(startLiveConfig.isHasLiveList());
        reportStartUp();
    }

    @Override // com.huya.berry.client.ILiveStream
    public void onActivityResult(int i, int i2, Intent intent) {
        IForceLiveStream iForceLiveStream;
        L.info(TAG, "onActivityResult requestCode:" + i + ",resultCode:" + i2 + ",data:" + intent);
        if (i == 1002) {
            if (PermissionTool.checkDrawOverlays(this.mActivity.get())) {
                if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(ArkValue.gContext, "android.permission.RECORD_AUDIO") != 0) {
                    this.mActivity.get().requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 1003);
                    return;
                } else if (!SdkProperties.needPlayerFloat.get().booleanValue()) {
                    startLiveImpl();
                    return;
                } else {
                    openRealFloat();
                    return;
                }
            }
            return;
        }
        if (i == 1003) {
            return;
        }
        if (i == 10001) {
            if (i2 == -1 && intent != null) {
                ArkToast.show("直播已开启");
            } else {
                ArkToast.show("未获得录屏权限");
                stopLive();
            }
        }
        if (intent == null || (iForceLiveStream = this.mForceLiveStream) == null || iForceLiveStream.getMediaClient() == null) {
            return;
        }
        L.info(TAG, "mMediaClient onActivityResult");
        this.mForceLiveStream.getMediaClient().onActivityResult(i, i2, intent);
    }

    @Override // com.huya.berry.client.ILiveStream
    public void uninit() {
        reportEndUp();
        stopLive();
        IForceLiveStream iForceLiveStream = this.mForceLiveStream;
        if (iForceLiveStream != null) {
            iForceLiveStream.uninit();
        }
        this.mCustomUIPresenter.onDestroy();
        stopGameUpHeartBeat();
        SignalCenter.unregister(this);
        TaskExecutor.proxyHandler().removeCallbacks(this.mQueryUserInfoTask);
    }

    @Override // com.huya.berry.client.ILiveStream
    public Activity getActivity() {
        WeakReference<Activity> weakReference = this.mActivity;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return this.mActivity.get();
    }

    @Override // com.huya.berry.client.ILiveStream
    public void pauseLive(boolean z) {
        ArkUtils.send(new FloatWinInterface.OnAutoPauseLive(z));
    }

    @Override // com.huya.berry.client.ILiveStream
    public void setLandscape(boolean z) {
        if (this.mStartLiveConfig.landscapeMode() == z) {
            return;
        }
        this.mStartLiveConfig.setLandscapeMode(z);
        SdkProperties.isLandscape.set(Boolean.valueOf(z));
    }

    @Override // com.huya.berry.client.ILiveStream
    public void sendPlayerData(HuyaBerry.BerryPlayerDataHelper berryPlayerDataHelper) {
        if (berryPlayerDataHelper == null || berryPlayerDataHelper.getSize() > SdkProperties.sendDataSizeLimit.get().intValue() || this.mSendDataCount > SdkProperties.sendDataCountLimit.get().intValue() || !SdkProperties.isLiving.get().booleanValue()) {
            return;
        }
        if (this.mSendDataCount == 0) {
            TaskExecutor.proxyHandler().postDelayed(this.mSendDataCountResetTask, 60000L);
        }
        this.mSendDataCount++;
        transMsgToViewer(getSendDataJSON(berryPlayerDataHelper), GAME_SEND_DATA_TYPE);
    }

    @Override // com.huya.berry.client.ILiveStream
    public void sendGameUpData(String str) {
        SdkProperties.gangUpData.set(str);
        startGameUpHeartBeat();
    }

    @Override // com.huya.berry.client.ILiveStream
    public void startLiveDirectly(String str, String str2) {
        IForceLiveStream iForceLiveStream = this.mForceLiveStream;
        if (iForceLiveStream != null && iForceLiveStream.getMediaClient() != null) {
            L.error(TAG, "duplicate start live.");
            this.mIsCallbackStartUp = true;
        } else {
            setIsDirect(true);
            SdkProperties.liveTitle.set(PreferenceUtil.getLiveTitle());
            SdkProperties.openId.set(str);
            SdkProperties.tencentGameName.set(str2);
        }
    }

    @Override // com.huya.berry.client.ILiveStream
    public void resetIsCallbackStartUp() {
        this.mIsCallbackStartUp = false;
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void setFromStartlive() {
        this.mCustomUIPresenter.setFromStartlive();
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onStartLive(CustomUICallback customUICallback) {
        this.mCustomUIPresenter.onStartLive(customUICallback);
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onGetAuthorInfo(CustomUICallback customUICallback) {
        if (networkAvailable()) {
            if (isNotLogin()) {
                customUICallback.onResultCallback(1, new AuthorInfo("", "", "", 0, 0, 0L, false));
                return;
            }
            if (UserProperties.roomId.get().intValue() <= 0) {
                this.mGetAuthorInfoCallBack = customUICallback;
                queryUserInfo();
            } else {
                customUICallback.onResultCallback(0, new AuthorInfo("https://www.huya.com/" + UserProperties.roomId.get(), UserProperties.avatarUrl.get(), UserProperties.nickName.get(), UserProperties.roomId.get().intValue(), UserProperties.subscribesCount.get().intValue(), LoginProperties.uid.get().longValue(), true));
            }
        }
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onShowModifyNickname(CustomUICallback customUICallback) {
        this.mCustomUIPresenter.onShowModifyNickname(customUICallback);
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onModifyTitle(CustomUICallback customUICallback, String str) {
        this.mCustomUIPresenter.onModifyTitle(customUICallback, str);
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onModifyAnnouncement(CustomUICallback customUICallback, String str) {
        this.mCustomUIPresenter.onModifyAnnouncement(customUICallback, str);
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onLogin(CustomUICallback customUICallback) {
        if (networkAvailable()) {
            if (isOutSideCapture()) {
                ILoginService iLoginService = (ILoginService) ServiceCenter.instance().getService(ILoginService.class);
                if (iLoginService != null) {
                    this.mGetAuthorInfoCallBack = null;
                    this.mGetRtmpUrlCallBack = null;
                    this.mStopRtmpLiveCallBack = null;
                    this.mAutoStartLive = false;
                    iLoginService.login(getActivity());
                }
                metricReport(1, 0);
                return;
            }
            this.mCustomUIPresenter.onLogin(customUICallback);
        }
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onLogout(CustomUICallback customUICallback) {
        if (networkAvailable()) {
            this.mCustomUIPresenter.onLogout(customUICallback);
        }
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onGetResolution(CustomUICallback customUICallback) {
        this.mCustomUIPresenter.onGetResolution(customUICallback);
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onSetResolution(CustomUICallback customUICallback, int i) {
        this.mCustomUIPresenter.onSetResolution(customUICallback, i);
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void getLiveUrl(String str, CustomUICallback customUICallback) {
        ILoginService iLoginService;
        if (networkAvailable() && (iLoginService = (ILoginService) ServiceCenter.instance().getService(ILoginService.class)) != null) {
            this.mLiveTitle = str;
            this.mGetRtmpUrlCallBack = customUICallback;
            if (LoginProperties.uid.get().longValue() == 0 && iLoginService.needAutoLogin()) {
                iLoginService.autoLogin();
                this.mAutoStartLive = true;
            } else if (isNotLogin()) {
                iLoginService.login(getActivity());
                this.mAutoStartLive = true;
            } else {
                this.mAutoStartLive = true;
                getPresenterVeriInfo();
            }
        }
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void stopRtmpLive(CustomUICallback customUICallback) {
        if (networkAvailable()) {
            this.mStopRtmpLiveCallBack = customUICallback;
            onRtmpClose();
        }
    }

    @Override // com.huya.berry.sdklivelist.api.LiveListListener
    public void onLogin() {
        if (this.mActivity.get() == null) {
            return;
        }
        ILoginService iLoginService = (ILoginService) ServiceCenter.instance().getService(ILoginService.class);
        if (iLoginService != null) {
            iLoginService.login(getActivity());
        }
        ArkUtils.send(new CommonEvent.WupMetricReport(0, 1, 0));
    }

    @Override // com.huya.berry.sdklivelist.api.LiveListListener
    public void onStartLive() {
        if (LoginProperties.uid.get().longValue() == 0) {
            this.mReadyToStartLive = true;
            onLogin();
        } else if (TextUtils.isEmpty(SdkProperties.liveTitle.get())) {
            onInputLiveTitle();
        } else {
            startLive();
        }
    }

    private String getSendDataJSON(HuyaBerry.BerryPlayerDataHelper berryPlayerDataHelper) {
        if (berryPlayerDataHelper == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(BaseSQwanCore.INFO_ROLENAME, (Object) berryPlayerDataHelper.roleName);
        jSONObject.put("serverID", (Object) berryPlayerDataHelper.serverID);
        jSONObject.put("playerLevel", (Object) berryPlayerDataHelper.playerLevel);
        jSONObject.put("customJson", (Object) berryPlayerDataHelper.customJson);
        jSONObject.put("appid", (Object) SdkProperties.appId.get());
        jSONObject.put(StatisticsContent.APPKEY, (Object) SdkProperties.appKey.get());
        jSONObject.put(ParamsManager.CONFIG_NODE_GAMEID, (Object) SdkProperties.gameId.get());
        jSONObject.put("uid", (Object) LoginProperties.uid.get());
        jSONObject.put("nickname", (Object) UserProperties.nickName.get());
        IForceLiveStream iForceLiveStream = this.mForceLiveStream;
        if (iForceLiveStream != null && iForceLiveStream.getMediaClient() != null) {
            jSONObject.put("videoTimeStamp", (Object) Long.valueOf(System.currentTimeMillis()));
        }
        return jSONObject.toJSONString();
    }

    public void setIsDirect(boolean z) {
        this.mIsDirect = z;
    }

    public void setReadyToStartLive(boolean z) {
        this.mReadyToStartLive = z;
    }

    public boolean getReadyToStartLive() {
        return this.mReadyToStartLive;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onInputLiveTitle() {
        LiveListTask liveListTask = this.mLiveListTask;
        if (liveListTask == null) {
            L.error(TAG, "mLiveListTask =" + this.mLiveListTask);
            return;
        }
        liveListTask.closeLoading();
        this.mLiveListTask.showInputLiveTitleDialog();
    }

    public void onShowModifyNickname() {
        if (this.mLiveListTask == null) {
            L.error(TAG, "mLiveListTask =" + this.mLiveListTask);
            return;
        }
        if (isNotLogin()) {
            onLogin();
        } else {
            this.mLiveListTask.showModifyNicknameFragment();
        }
    }

    public boolean isOutSideCapture() {
        return SdkProperties.sdkMode.get() == SdkProperties.SDKMode.CAPTURE_BY_OUTSIDE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCameraCapture() {
        return SdkProperties.sdkMode.get() == SdkProperties.SDKMode.CAPTURE_BY_CAMERA;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLive() {
        if (getActivity() == null) {
            return;
        }
        if (isOutSideCapture() && !this.mIsVerify) {
            showVerifyAlert();
            return;
        }
        this.mReadyToStartLive = false;
        if (isOutSideCapture()) {
            startLiveImpl();
        } else if (this.mIsBeginLive) {
            L.info(TAG, "mIsBeginLive....");
        } else {
            checkAlertWindowPermission();
        }
    }

    private void checkAlertWindowPermission() {
        L.info(TAG, "checkAlertWindowPermission");
        if (this.mActivity.get() == null) {
            onClose();
            return;
        }
        if (this.mForceLiveStream == null) {
            return;
        }
        if (isCameraCapture() && !PermissionTool.checkCameraPermission(1280, 720, this.mForceLiveStream.getIsFrontCamera(), SdkProperties.isLandscape.get().booleanValue())) {
            ArkToast.show("无摄像头权限，请在系统权限设置中开启游戏的相机权限");
            return;
        }
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(ArkValue.gContext, "android.permission.RECORD_AUDIO") != 0) {
            this.mActivity.get().requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 1003);
            return;
        }
        if (!PermissionTool.checkAVPermission()) {
            ArkToast.show("无法录取直播声音，请在系统权限设置中开启游戏的录音权限");
            LiveListTask liveListTask = this.mLiveListTask;
            if (liveListTask != null) {
                liveListTask.closeLoading();
                return;
            }
            return;
        }
        if (!PermissionTool.isIgnoreDrawOverlays() && !PermissionTool.checkDrawOverlays(this.mActivity.get())) {
            showAlertWindowPermissionFragment();
        } else {
            startLiveImpl();
        }
    }

    private void showAlertWindowPermissionFragment() {
        if (this.mActivity.get() == null || this.mActivity.get().isFinishing()) {
            return;
        }
        ISdkLiveListService iSdkLiveListService = (ISdkLiveListService) ServiceCenter.instance().getService(ISdkLiveListService.class);
        if (iSdkLiveListService == null) {
            L.error(TAG, "showAlertWindowPermissionFragment =" + iSdkLiveListService);
            return;
        }
        iSdkLiveListService.showAlertWindowDialogFragment(getActivity());
    }

    private void startLiveImpl() {
        if (isOutSideCapture()) {
            onGetRtmpAddr(this.mLiveTitle);
        } else {
            L.info(TAG, "startLiveImpl" + this.mIsBeginLive);
            if (this.mIsBeginLive) {
                return;
            }
            if (SdkProperties.gameId.get().intValue() < 1) {
                ArkToast.show("gameId有误");
                return;
            }
            if (this.mLiveListTask != null) {
                if (isCameraCapture()) {
                    this.mLiveListTask.showCameraLive(this.mPreviewListener);
                }
                this.mLiveListTask.startLoading();
            }
            LivingParams curLivingParams = ResolutionOptions.getInstance().getCurLivingParams();
            PreferenceUtil.setResolution(LoginProperties.uid.get().longValue(), curLivingParams.getResolution());
            beginLive(new LiveInterface.StartLive(curLivingParams.getVideoWidth(), curLivingParams.getVideoHeight(), curLivingParams.getVideoBitrate(), curLivingParams.getVideoFrameRate(), SdkProperties.gameId.get().intValue()));
            this.mIsBeginLive = true;
        }
        metricReport(0, 1);
    }

    @Override // com.huya.berry.sdklivelist.api.LiveListListener
    public void onClose() {
        LiveListTask liveListTask = this.mLiveListTask;
        if (liveListTask == null) {
            L.error(TAG, "mLiveListTask == null");
        } else {
            liveListTask.hideLiveListFragment();
            HuyaBerryCallback.getInstance().callbackCloseLiveList(true);
        }
    }

    @Override // com.huya.berry.sdklivelist.api.LiveListListener
    public void onActivityError() {
        this.mActivity = new WeakReference<>(null);
    }

    private void showVerifyAlert() {
        new LiveAlert.Builder(getActivity()).title("提示").message("需要完成实名认证才可以使用远程推流功能").positive("马上认证").negative("我知道了").cancelable(true).onClickListener(new DialogInterface.OnClickListener() { // from class: com.huya.berry.client.LiveStream.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    WebviewApi.openWebview(LiveStream.this.getActivity(), "主播认证", CerticateHelper.getCreditUrl(), false);
                    Report.event(SdkReportConst.PV_CERTIFICATE);
                }
            }
        }).show();
    }

    private void reportStartUp() {
        if (this.mIsStartUp) {
            return;
        }
        Report.event("startup");
        this.mIsStartUp = true;
    }

    private void reportEndUp() {
        if (this.mIsStartUp) {
            Report.event(SdkReportConst.HuyaEndUp);
            this.mIsStartUp = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportMonitor(String str, String str2, boolean z) {
        HashMap map = new HashMap();
        map.put(MonitorReportConst.METRIC_PARAM_VERSION2, MonitorReport.formatVersion(WupHelper.getVersion()));
        map.put(MonitorReportConst.METRIC_PARAM_AUID, String.valueOf(LoginProperties.uid.get()));
        map.put(MonitorReportConst.METRIC_PARAM_INNER_ERR, str);
        map.put(MonitorReportConst.METRIC_PARAM_SVR_RETCODE, str2);
        map.put("success", z ? "0" : "1");
        MonitorReport.event(MonitorReportConst.METRIC_NAME_ANCHOR_QUALITY_BEGINLIVE, map);
    }

    private void showToastOrHtmlDialog(Activity activity, String str) {
        if (HtmlAlertDialog.isHtmlAlertDialog(str)) {
            try {
                new HtmlAlertDialog(activity).setContent(str).show();
            } catch (Exception unused) {
            }
        } else {
            ArkToast.show(str);
        }
    }

    public boolean isNotLogin() {
        return LoginProperties.loginState.get() != LoginProperties.LoginState.LoggedIn || LoginProperties.uid.get().longValue() == 0;
    }

    private boolean networkAvailable() {
        if (NetworkUtils.isNetworkAvailable()) {
            return true;
        }
        ArkToast.show("您的网络有点问题，请检查网络连接");
        return false;
    }

    private void onGetRtmpAddr(String str) {
        if (TextUtils.isEmpty(str)) {
            str = InputTitleFragment.DEFAULT_TITLE;
        }
        this.mLiveTime = System.currentTimeMillis();
        ((IChannelInfoWup) NS.get(IChannelInfoWup.class)).createAddr(new CreateAddrReq(str, HUWAI_GAME_ID, BaseApi.getUserId(), 0L, null)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<CreateAddrRsp>() { // from class: com.huya.berry.client.LiveStream.3
            @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
            public void onNext(CreateAddrRsp createAddrRsp) {
                if (!TextUtils.isEmpty(createAddrRsp.sErrorMsg)) {
                    ArkToast.show(createAddrRsp.sErrorMsg);
                    LiveStream.this.getRtmpUrlFail(createAddrRsp.sErrorMsg);
                    return;
                }
                if (createAddrRsp.getSRtmpAddr() == null || createAddrRsp.getSRtmpKey() == null) {
                    LiveStream.this.getRtmpUrlFail("开播失败，请重试");
                    return;
                }
                if (LiveStream.this.mGetRtmpUrlCallBack != null) {
                    LiveStream.this.mGetRtmpUrlCallBack.onResultCallback(0, new RtmpPushInfo(createAddrRsp.getSRtmpAddr() + "/" + createAddrRsp.getSRtmpKey(), "开播成功", true));
                    Report.event(SdkReportConst.STATUS_STARTRTMP_SUCCESS, SdkReportConst.STATUS_STARTRTMP_SUCCESS_DES);
                }
            }

            @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
            public void onError(Throwable th) {
                LiveStream.this.getRtmpUrlFail("开播失败，请重试");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRtmpUrlFail(String str) {
        CustomUICallback customUICallback = this.mGetRtmpUrlCallBack;
        if (customUICallback != null) {
            customUICallback.onResultCallback(1, new RtmpPushInfo("", str, false));
            Report.event(SdkReportConst.STATUS_STARTRTMP_FAIL, SdkReportConst.STATUS_STARTRTMP_FAIL_DES);
        }
    }

    private void onRtmpClose() {
        new LiveAlert.Builder(getActivity()).message("确定要关闭直播吗？").positive("确定").negative("取消").onClickListener(new DialogInterface.OnClickListener() { // from class: com.huya.berry.client.LiveStream.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    LiveStream.this.sendFeedback();
                    LiveStream.this.rtmpclose(true);
                }
                dialogInterface.dismiss();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rtmpclose(final boolean z) {
        ((IChannelInfoWup) NS.get(IChannelInfoWup.class)).openRtmpEndLive(new OpenRtmpEndLiveReq(BaseApi.getUserId())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<Object>() { // from class: com.huya.berry.client.LiveStream.5
            @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
            public void onNext(Object obj) {
                if (LiveStream.this.mStopRtmpLiveCallBack == null || !z) {
                    return;
                }
                LiveStream.this.mStopRtmpLiveCallBack.onResultCallback(0, null);
                Report.event(SdkReportConst.STATUS_STOPRTMP_SUCCESS, SdkReportConst.STATUS_STOPRTMP_SUCCESS_DES);
            }

            @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
            public void onError(Throwable th) {
                L.info(LiveStream.TAG, "OpenRtmpEndLive[onError]->");
                if (LiveStream.this.mStopRtmpLiveCallBack == null || !z) {
                    return;
                }
                LiveStream.this.mStopRtmpLiveCallBack.onResultCallback(1, null);
                Report.event(SdkReportConst.STATUS_STOPRTMP_FAIL, SdkReportConst.STATUS_STOPRTMP_FAIL_DES);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFeedback() {
        this.mLiveTime = System.currentTimeMillis() - this.mLiveTime;
        ArkUtils.send(new FeedBackInterface.AddFeedBack("结束开播自动反馈", String.format(Locale.CHINA, "%s[sid:%d|subid:%d|resolution:%d|land:%b|net:%s|time:%d|v:%s]", UserProperties.nickName.get(), LoginProperties.uid.get(), LoginProperties.uid.get(), SdkProperties.resolution.get(), Boolean.valueOf(CommonUtil.isScreenLandScape()), AppStatusReportUtil.getNetworkConnectionName(), Long.valueOf(this.mLiveTime / 1000), WupHelper.getVersion())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryUserInfo() {
        if (UserProperties.roomId.get().intValue() <= 0) {
            IUserService iUserService = (IUserService) ServiceCenter.instance().getService(IUserService.class);
            if (iUserService != null) {
                iUserService.getUserProfile(LoginApi.getUid()).compose(SchedulerUtils.ioio()).subscribe(new WupObserver());
            }
            if (this.mIsVerify) {
                TaskExecutor.uiHandler().postDelayed(this.mQueryUserInfoTask, 500L);
            } else {
                getAuthorInfoCallBack();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAuthorInfoCallBack() {
        CustomUICallback customUICallback = this.mGetAuthorInfoCallBack;
        if (customUICallback != null) {
            customUICallback.onResultCallback(0, new AuthorInfo("https://www.huya.com/" + UserProperties.roomId.get(), UserProperties.avatarUrl.get(), UserProperties.nickName.get(), UserProperties.roomId.get().intValue(), UserProperties.subscribesCount.get().intValue(), LoginProperties.uid.get().longValue(), true));
        }
    }

    private void getPresenterVeriInfo() {
        ICommonService iCommonService = (ICommonService) ServiceCenter.instance().getService(ICommonService.class);
        if (iCommonService != null) {
            iCommonService.getPresenterVeriInfo().compose(SchedulerUtils.net()).subscribe(new WupObserver<GetPresenterVeriInfoRsp>() { // from class: com.huya.berry.client.LiveStream.7
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(GetPresenterVeriInfoRsp getPresenterVeriInfoRsp) {
                    if (getPresenterVeriInfoRsp.getIVerified() != 1 || StringUtils.isNullOrEmpty(getPresenterVeriInfoRsp.getSVerifiedAvatar())) {
                        LiveStream.this.mIsVerify = false;
                    } else {
                        LiveStream.this.mIsVerify = true;
                    }
                    if (LiveStream.this.mAutoStartLive) {
                        LiveStream.this.startLive();
                        LiveStream.this.mAutoStartLive = false;
                    }
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    if (LiveStream.this.mAutoStartLive) {
                        LiveStream.this.startLive();
                        LiveStream.this.mAutoStartLive = false;
                    }
                }
            });
        }
    }

    private void startGameUpHeartBeat() {
        if (TextUtils.isEmpty(SdkProperties.gangUpData.get()) || isNotLogin()) {
            return;
        }
        GangUpHeartBeat gangUpHeartBeat = this.mGangUpHeartBeat;
        if (gangUpHeartBeat == null) {
            GangUpHeartBeat gangUpHeartBeat2 = new GangUpHeartBeat(10000L, new BaseHeartBeat.HeartBeatListener() { // from class: com.huya.berry.client.LiveStream.8
                @Override // com.huya.berry.module.living.heartbeat.BaseHeartBeat.HeartBeatListener
                public void afterHeartBeat() {
                }

                @Override // com.huya.berry.module.living.heartbeat.BaseHeartBeat.HeartBeatListener
                public void onHeartBeatError(VolleyError volleyError) {
                    L.error(LiveStream.TAG, "GangUpHeartBeat:%d", volleyError);
                }
            });
            this.mGangUpHeartBeat = gangUpHeartBeat2;
            gangUpHeartBeat2.startHeartBeat();
            return;
        }
        gangUpHeartBeat.sendHeartBeat();
    }

    private void stopGameUpHeartBeat() {
        GangUpHeartBeat gangUpHeartBeat = this.mGangUpHeartBeat;
        if (gangUpHeartBeat != null) {
            gangUpHeartBeat.sendHeartBeat();
            this.mGangUpHeartBeat.stopHeartBeat();
            this.mGangUpHeartBeat = null;
        }
    }

    private void metricReport(int i, int i2) {
        ICommonService iCommonService = (ICommonService) ServiceCenter.instance().getService(ICommonService.class);
        if (iCommonService != null) {
            iCommonService.metricReport(new CommonEvent.WupMetricReport(0, i, i2));
        }
    }

    private void transMsgToViewer(String str, int i) {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.transMsgToViewer(str, i).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<TransMsgToViewerRsp>() { // from class: com.huya.berry.client.LiveStream.9
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(TransMsgToViewerRsp transMsgToViewerRsp) {
                    if (transMsgToViewerRsp == null) {
                        HuyaBerryCallback.getInstance().callbackSendPlayerData(true, "");
                    } else {
                        HuyaBerryCallback.getInstance().callbackSendPlayerData(true, transMsgToViewerRsp.sMessage);
                    }
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    HuyaBerryCallback.getInstance().callbackSendPlayerData(false, "");
                }
            });
        }
    }

    private void getLiveSummary(long j, final boolean z) {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.getLiveSummary(j, z).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<GetLiveSummaryRsp>() { // from class: com.huya.berry.client.LiveStream.10
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(GetLiveSummaryRsp getLiveSummaryRsp) {
                    if (LiveStream.this.mIsCallbackEndLive) {
                        L.error(LiveStream.TAG, "onGetLiveSummary error " + LiveStream.this.mIsCallbackEndLive);
                        if (z) {
                            return;
                        }
                        LiveStream.this.mLiveListTask.setEndLiveData(null);
                        return;
                    }
                    if (getLiveSummaryRsp == null) {
                        return;
                    }
                    String dataFormat = DigitUtil.toDataFormat(((long) getLiveSummaryRsp.iDuration) * 1000);
                    HuyaBerryCallback.getInstance().callbackEndLive(z, getLiveSummaryRsp.iPeakViewer, dataFormat);
                    LiveStream.this.mIsCallbackEndLive = true;
                    PreferenceUtil.setEndLiveCallback(true);
                    if (z) {
                        return;
                    }
                    LiveStream.this.mLiveListTask.setEndLiveData(new EndLiveData(dataFormat, DigitUtil.intFormat(getLiveSummaryRsp.iPeakViewer), DigitUtil.intFormat(getLiveSummaryRsp.iGiftCount), DigitUtil.intFormat(getLiveSummaryRsp.iNewFans), DigitUtil.intFormat(getLiveSummaryRsp.iLiveShare)));
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    L.error(LiveStream.TAG, "onGetLiveSummary error " + LiveStream.this.mIsCallbackEndLive);
                    if (z) {
                        return;
                    }
                    LiveStream.this.mLiveListTask.setEndLiveData(null);
                }
            });
        }
    }

    private void openRealFloat() {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.getLivingInfo(PlayerHelper.sid, PlayerHelper.subSid, PlayerHelper.presenterUid, 0L).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<GetLivingInfoRsp>() { // from class: com.huya.berry.client.LiveStream.11
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(GetLivingInfoRsp getLivingInfoRsp) {
                    ISdkPlayerService iSdkPlayerService = (ISdkPlayerService) ServiceCenter.instance().getService(ISdkPlayerService.class);
                    if (iSdkPlayerService != null) {
                        iSdkPlayerService.openRealFloat();
                    }
                    L.info(LiveStream.TAG, "OpenRealFloat mView");
                }
            });
        }
    }

    private void onLoginSuccess() {
        queryUserInfo();
        startGameUpHeartBeat();
        if (TextUtils.isEmpty(SdkProperties.liveTitle.get())) {
            onInputLiveTitle();
        } else {
            startLive();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRtmpPush() {
        if (this.mActivity.get() == null) {
            L.error(TAG, "mActivity.get() == null");
            onClose();
            return;
        }
        IForceLiveStream iForceLiveStream = this.mForceLiveStream;
        if (iForceLiveStream == null) {
            L.error(TAG, "mForceLiveStream == null");
            onClose();
        } else if (iForceLiveStream.startPush()) {
            Report.startLive(SdkProperties.gameId.get().intValue(), SdkProperties.liveId.get().longValue(), LoginProperties.uid.get().longValue(), AppUtils.getVersion());
            ILiveService iLiveService = (ILiveService) ServiceCenter.instance().getService(ILiveService.class);
            if (iLiveService != null) {
                iLiveService.onStartLive();
            }
            HuyaBerryCallback.getInstance().callbackStartLive();
            PreferenceUtil.setEndLiveCallback(false);
            this.mIsCallbackEndLive = false;
            reportMonitor("0", "0", true);
        }
    }

    private void stopLive() {
        L.info(TAG, "stopLive");
        getLiveSummary(SdkProperties.liveId.get().longValue(), false);
        SdkProperties.isPushStreamOk.set(false);
        SdkProperties.isClickPauseLive.set(false);
        TaskExecutor.proxyHandler().removeCallbacks(this.mSendDataCountResetTask);
        endLive();
        IForceLiveStream iForceLiveStream = this.mForceLiveStream;
        if (iForceLiveStream != null) {
            iForceLiveStream.stopLive();
        }
        MonitorCenter.getInstance().stopReport();
        ILiveService iLiveService = (ILiveService) ServiceCenter.instance().getService(ILiveService.class);
        if (iLiveService != null) {
            iLiveService.onEndLive();
            iLiveService.stopLiveToolService();
        }
        setFromStartlive();
        Report.stopLive();
    }

    private void beginLive(LiveInterface.StartLive startLive) {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.beginLive(startLive).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<BeginLiveRsp>() { // from class: com.huya.berry.client.LiveStream.12
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(BeginLiveRsp beginLiveRsp) {
                    LiveStream.this.mIsBeginLive = false;
                    if (LiveStream.this.mLiveListTask != null) {
                        LiveStream.this.mLiveListTask.closeLoading();
                    }
                    if (!(beginLiveRsp != null)) {
                        ArkToast.show("开播失败");
                        if (LiveStream.this.isCameraCapture()) {
                            ArkUtils.send(new CameraCallback.onFinishPreview());
                        }
                        LiveStream.this.endLive();
                        return;
                    }
                    if (beginLiveRsp.getIRespCode() != 0) {
                        L.info(LiveStream.TAG, beginLiveRsp.sTitle + " " + StringUtils.equal(beginLiveRsp.sTitle, "虎牙认证"));
                        LiveStream.this.endLive();
                        if (StringUtils.equal(beginLiveRsp.sTitle, "虎牙认证")) {
                            if (!PermissionTool.checkCameraPermission(1280, 720, !SdkProperties.isLandscape.get().booleanValue(), SdkProperties.isLandscape.get().booleanValue())) {
                                ArkToast.show("无摄像头权限，请在系统权限设置中开启游戏的相机权限");
                                return;
                            } else {
                                SdkProperties.isCertificate.set(true);
                                WebviewApi.openWebview(LiveStream.this.getActivity(), "主播认证", CerticateHelper.getCreditUrl(), false);
                            }
                        } else if (!TextUtils.isEmpty(beginLiveRsp.sMesssage) && beginLiveRsp.sMesssage.contains("标题")) {
                            LiveStream.this.onInputLiveTitle();
                            ArkToast.show(beginLiveRsp.getSMesssage());
                        } else {
                            ArkToast.show(beginLiveRsp.getSMesssage());
                        }
                        LiveStream.this.reportMonitor("7", String.valueOf(beginLiveRsp.getIRespCode()), false);
                        if (LiveStream.this.isCameraCapture()) {
                            ArkUtils.send(new CameraCallback.onFinishPreview());
                            return;
                        }
                        return;
                    }
                    if (SdkProperties.isLiving.get().booleanValue() && LiveStream.this.mForceLiveStream != null && LiveStream.this.mForceLiveStream.getMediaClient() != null) {
                        L.error(LiveStream.TAG, "have started live");
                        LiveStream.this.mForceLiveStream.stopMediaClient();
                    }
                    ILiveService iLiveService = (ILiveService) ServiceCenter.instance().getService(ILiveService.class);
                    if (iLiveService != null) {
                        iLiveService.startLiveToolService();
                    }
                    L.info(LiveStream.TAG, "startLive videoBitrate" + ResolutionOptions.getInstance().getCurLivingParams().getVideoBitrate());
                    SdkProperties.isLiving.set(true);
                    LiveStream.this.onClose();
                    if (LiveStream.this.mForceLiveStream != null) {
                        LiveStream.this.mForceLiveStream.setRtmpUrl(beginLiveRsp.getSUpStreamAddress());
                        if (LiveStream.this.mForceLiveStream.getMediaClient() == null) {
                            LiveStream.this.startRtmpPush();
                        }
                    }
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("Gameid", (Object) SdkProperties.gameId.get());
                        jSONObject.put("Network", (Object) NetworkUtil.getNetWorkType(LiveStream.this.getActivity()));
                        jSONObject.put("Quality", (Object) Integer.valueOf(ResolutionOptions.getInstance().getCurLivingParams().getVideoBitrate()));
                        jSONObject.put("Rollid", (Object) SdkProperties.gameAccountID.get());
                        Report.event(SdkReportConst.LIVE_STATUS_LIVESTART, SdkReportConst.LIVE_STATUS_LIVESTART_DES, "", jSONObject.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    LiveStream.this.reportMonitor("6", "beginlive超时", false);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endLive() {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.endLive();
        }
    }

    private void onAlertWindowPermission(boolean z) {
        L.info(TAG, "onAlertWindowPermission " + z);
        if (this.mActivity.get() == null || this.mActivity.get().isFinishing()) {
            return;
        }
        if (z) {
            if (Build.VERSION.SDK_INT >= 23 && !Build.MANUFACTURER.equals("Meizu")) {
                try {
                    Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
                    intent.setData(Uri.parse("package:" + ArkValue.gContext.getPackageName()));
                    this.mActivity.get().startActivityForResult(intent, 1002);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PressionSettingUtil.showPressionSetting(this.mActivity.get());
                } catch (PressionSettingUtil.OpenFalseException e2) {
                    L.error(TAG, e2.toString());
                    PermissionTool.setIgnoreDrawOverlays(true);
                }
            }
            LiveListTask liveListTask = this.mLiveListTask;
            if (liveListTask != null) {
                liveListTask.closeLoading();
                return;
            }
            return;
        }
        startLiveImpl();
    }

    private void showPopup(PresenterPopData presenterPopData) {
        if (this.mActivity.get() == null || presenterPopData.vButtonInfo == null || presenterPopData.vButtonInfo.size() == 0) {
            return;
        }
        final Activity activity = this.mActivity.get();
        final ArrayList<PopupButtonInfo> arrayList = presenterPopData.vButtonInfo;
        final String str = presenterPopData.sTitle;
        new LiveAlert.Builder(activity).title(str).message(presenterPopData.sContent).negative(!TextUtils.isEmpty(arrayList.get(0).getSTitle()) ? arrayList.get(0).getSTitle() : ArkValue.gContext.getResources().getString(ResourceUtil.getStringResIDByName("hyberry_have_known"))).positive(arrayList.size() > 1 ? !TextUtils.isEmpty(arrayList.get(1).getSTitle()) ? arrayList.get(1).getSTitle() : ArkValue.gContext.getResources().getString(ResourceUtil.getStringResIDByName("hyberry_look_for_details")) : null).cancelable(true).onClickListener(new DialogInterface.OnClickListener() { // from class: com.huya.berry.client.LiveStream.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -2) {
                    if (activity == null || TextUtils.isEmpty(((PopupButtonInfo) arrayList.get(0)).getSActionUrl())) {
                        return;
                    }
                    WebviewApi.openWebview(activity, str, ((PopupButtonInfo) arrayList.get(0)).getSActionUrl(), ((PopupButtonInfo) arrayList.get(0)).getILoginStatus() == 1);
                    return;
                }
                if (i != -1 || activity == null || TextUtils.isEmpty(((PopupButtonInfo) arrayList.get(1)).getSActionUrl())) {
                    return;
                }
                WebviewApi.openWebview(activity, str, ((PopupButtonInfo) arrayList.get(1)).getSActionUrl(), ((PopupButtonInfo) arrayList.get(1)).getILoginStatus() == 1);
            }
        }).show();
    }

    @IASlot(executorID = 1)
    public void onCloseBtnClicked(LivePresenterInterface.CloseBtnClicked closeBtnClicked) {
        if (this.mActivity.get() == null) {
            return;
        }
        this.mLiveListTask.showEndLiveFragment();
        this.mLiveListTask.hideLiveListFragment();
        stopLive();
    }

    @IASlot(executorID = 1)
    public void onPopupNotice(LiveCallback.ZhixuPopupNotice zhixuPopupNotice) {
        if (zhixuPopupNotice == null || zhixuPopupNotice.notify == null) {
            return;
        }
        ZhixuPopupNotify zhixuPopupNotify = zhixuPopupNotice.notify;
        if (zhixuPopupNotify.getVData() == null) {
            return;
        }
        Iterator<PresenterPopData> it = zhixuPopupNotify.getVData().iterator();
        while (it.hasNext()) {
            showPopup(it.next());
        }
    }

    @IASlot(executorID = 1)
    public void onSendDataCallback(LiveCallback.TransMsgToViewer transMsgToViewer) {
        if (transMsgToViewer == null) {
            return;
        }
        if (transMsgToViewer.status == 0) {
            if (transMsgToViewer.resp == null) {
                HuyaBerryCallback.getInstance().callbackSendPlayerData(true, "");
                return;
            } else {
                HuyaBerryCallback.getInstance().callbackSendPlayerData(true, transMsgToViewer.resp.sMessage);
                return;
            }
        }
        if (transMsgToViewer.resp == null) {
            HuyaBerryCallback.getInstance().callbackSendPlayerData(false, "");
        } else {
            HuyaBerryCallback.getInstance().callbackSendPlayerData(false, transMsgToViewer.resp.sMessage);
        }
    }

    @IASlot(executorID = 1)
    public void onLoginFinished(LoginCallback.LoginFinished loginFinished) {
        if (loginFinished.success) {
            queryUserInfo();
            getPresenterVeriInfo();
            SdkProperties.liveTitle.set(PreferenceUtil.getLiveTitle());
            Report.event(LoginReportConstants.STATUS_LOGIN_LOGINSUCCEED);
            ICommonService iCommonService = (ICommonService) ServiceCenter.instance().getService(ICommonService.class);
            if (iCommonService != null) {
                iCommonService.getMyLiveAnnouncement(LiveHelper.getUserId()).compose(SchedulerUtils.ioio()).subscribe(new WupObserver<LiveAnnouncementFetchRsp>() { // from class: com.huya.berry.client.LiveStream.14
                    @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                    public void onError(Throwable th) {
                    }

                    @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                    public void onNext(LiveAnnouncementFetchRsp liveAnnouncementFetchRsp) {
                        SdkProperties.liveAnnouncement.set(liveAnnouncementFetchRsp.sLiveAnnouncement);
                    }
                });
            }
            if (!this.mIsCallbackStartUp) {
                HuyaBerryCallback.getInstance().callbackStartUp();
                this.mIsCallbackStartUp = true;
            }
            PresenterConfigHelper.requestPresenterConfig();
            startGameUpHeartBeat();
            return;
        }
        if (!NetworkUtils.isNetworkAvailable()) {
            loginFinished.desc = "您的网络有点问题，请检查网络连接";
        }
        if (!TextUtils.isEmpty(loginFinished.desc)) {
            showToastOrHtmlDialog(getActivity(), loginFinished.desc);
        }
        stopGameUpHeartBeat();
        Report.event(LoginReportConstants.STATUS_LOGIN_LOGINFAIL);
    }

    @IASlot(executorID = 1)
    public void onLogout(LoginInterface.LogOut logOut) {
        stopGameUpHeartBeat();
    }

    @IASlot(executorID = 1)
    public void onCertificateFinish(CertificateCallback.CertificateFinish certificateFinish) {
        if (certificateFinish == null) {
            return;
        }
        if (certificateFinish.resultCode == 0) {
            queryUserInfo();
            startLive();
            Report.event(SdkReportConst.PV_CERTIFICATE_SUCCESS);
        } else {
            ArkToast.show(certificateFinish.message);
            Report.event(SdkReportConst.PV_CERTIFICATE_FAIL);
        }
    }

    @IASlot(executorID = 1)
    public void onGetCertificateParams(CertificateCallback.GetCertificateParams getCertificateParams) {
        if (getCertificateParams == null) {
            return;
        }
        queryUserInfo();
    }

    @IASlot(executorID = 1)
    public void onGetLiveTitle(InputTitleCallback.GetLiveTitle getLiveTitle) {
        LiveListTask liveListTask;
        if (getLiveTitle == null || TextUtils.isEmpty(getLiveTitle.liveTitle) || (liveListTask = this.mLiveListTask) == null) {
            return;
        }
        liveListTask.hideInputLiveTitleDialog();
        startLive();
    }

    @IASlot(executorID = 1)
    public void onCloseFloating(CommonEvent.CloseFloating closeFloating) {
        StartLiveConfig startLiveConfig;
        LiveListTask liveListTask = this.mLiveListTask;
        if (liveListTask == null || (startLiveConfig = this.mStartLiveConfig) == null) {
            return;
        }
        liveListTask.start(startLiveConfig.isHasLiveList());
    }

    @IASlot(executorID = 1)
    public void onEndLiveNotice(LiveCallback.EndLiveNotice endLiveNotice) {
        if (endLiveNotice == null || TextUtils.isEmpty(endLiveNotice.msg)) {
            return;
        }
        ArkToast.show(endLiveNotice.msg);
        this.mLiveListTask.showEndLiveFragment();
        stopLive();
    }

    @IASlot(executorID = 1)
    public void onServerEndLive(LiveCallback.HeartBeatError heartBeatError) {
        if (heartBeatError == null) {
            return;
        }
        if (SdkProperties.isLiving.get().booleanValue()) {
            ArkToast.show("直播中断");
        }
        this.mLiveListTask.showEndLiveFragment();
        stopLive();
    }

    @IASlot(executorID = 1)
    public void onAlertWindowPermission(LiveCallback.AlertWindowPermissionRequest alertWindowPermissionRequest) {
        if (this.mActivity.get() == null || this.mActivity.get().isFinishing()) {
            return;
        }
        onAlertWindowPermission(true);
    }

    @IASlot(executorID = 1)
    public void onAlertWindowPermission(LiveCallback.AlertWindowPermissionCancel alertWindowPermissionCancel) {
        if (this.mActivity.get() == null || this.mActivity.get().isFinishing() || Build.VERSION.SDK_INT >= 23) {
            return;
        }
        onAlertWindowPermission(true);
    }

    @IASlot(executorID = 1)
    public void onGoUserCenter(CommonEvent.GoUserCenter goUserCenter) {
        float dp;
        float dp2;
        L.info(TAG, "onBackPress");
        String str = ArkValue.debuggable() ? "http://test.hd.huya.com/h5/gamesdkcenter/index.html" : "https://hd.huya.com/h5/gamesdkcenter/index.html";
        String version = WupHelper.getVersion();
        if (version.contains("SNAPSHOT")) {
            version = version.substring(0, version.indexOf("SNAPSHOT") - 1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("?isPortrait=");
        sb.append(CommonUtil.isScreenLandScape() ? "0" : "1");
        sb.append("&version=");
        sb.append(version);
        String string = sb.toString();
        if (CommonUtil.isScreenLandScape()) {
            dp = UIUtil.getDp(470.0f);
            dp2 = UIUtil.getDp(290.0f);
        } else {
            dp = UIUtil.getDp(310.0f);
            dp2 = UIUtil.getDp(406.0f);
        }
        WebviewApi.openWebview(getActivity(), "资料", string, true, true, dp, dp2);
    }

    @IASlot(executorID = 1)
    public void onRePushLive(CommonEvent.RePushLive rePushLive) {
        HuyaBerryCallback.getInstance().callbackReStartLive();
    }

    @IASlot
    public void onReportEvent(MonitorReportInterface.ReportEvent reportEvent) {
        ArrayList<Dimension> arrayList = new ArrayList<>();
        if (!reportEvent.metricParams.isEmpty()) {
            for (String str : reportEvent.metricParams.keySet()) {
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(new Dimension(str, reportEvent.metricParams.get(str)));
                }
            }
        }
        MetricDetail metricDetail = new MetricDetail();
        metricDetail.sMetricName = reportEvent.metricName;
        metricDetail.vDimension = arrayList;
        MonitorCenter.getInstance().request(metricDetail);
    }

    private void initWebview() {
        EventManager.instance().initCallMap(JssdkConst.callList);
        WebviewApi.init(new WebviewApi.Callback() { // from class: com.huya.berry.client.LiveStream.16
            @Override // com.huya.berry.webview.WebviewApi.Callback
            public void bindFinish(String str) {
            }

            @Override // com.huya.berry.webview.WebviewApi.Callback
            public void logout() {
                ((ILoginService) ServiceCenter.instance().getService(ILoginService.class)).logout();
            }

            @Override // com.huya.berry.webview.WebviewApi.Callback
            public void showModifyNickname() {
                ModifyNicknameFragment.getIntance(LiveStream.this.getActivity().getFragmentManager()).show(LiveStream.this.getActivity().getFragmentManager());
            }

            @Override // com.huya.berry.webview.WebviewApi.Callback
            public void showLogin() {
                ((ILoginService) ServiceCenter.instance().getService(ILoginService.class)).login(LiveStream.this.getActivity());
            }

            @Override // com.huya.berry.webview.WebviewApi.Callback
            public void closeLoading() {
                if (LiveStream.this.mLiveListTask != null) {
                    LiveStream.this.mLiveListTask.closeLoading();
                }
            }
        });
    }
}
