package com.huya.berry.sdklive.liveTool;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat1;
import android.text.TextUtils;
import android.view.WindowManager;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.asignal.notify.PropertySet;
import com.duowan.auk.module.ArkProperties;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.NetworkSpeedUtils;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.module.commonevent.HySignalServiceCallback;
import com.huya.berry.module.commonevent.LivePresenterInterface;
import com.huya.berry.sdklive.event.FloatWinInterface;
import com.huya.berry.sdklive.liveTool.LiveToolView;
import com.huya.berry.sdklive.living.ILivingView;
import com.huya.ciku.apm.MonitorCenter;
import com.huya.component.user.UserProperties;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveToolService extends Service implements ILivingView, LiveToolView.LiveToolCallBack {
    public static final String TAG = "LiveToolService";
    private int mKaCount;
    private IToolView mToolView;
    private WindowManager mWindowManager;
    private NetworkSpeedUtils networkSpeedUtils;
    private boolean mIsStarted = false;
    private boolean mIsStopLive = false;
    private int startTime = 0;
    private boolean mIsShow = false;
    private boolean mHalfHide = false;
    private int mSendTimeDelta = 0;
    private Handler mHandler = new Handler() { // from class: com.huya.berry.sdklive.liveTool.LiveToolService.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            L.info(LiveToolService.TAG, "sendTimeDelta:" + LiveToolService.this.mSendTimeDelta + " && " + String.valueOf(message.obj));
            if (!LiveToolService.this.mIsStarted) {
                L.error(LiveToolService.TAG, "initService slow");
                LiveToolService.this.onStartLive(null);
            }
            if (message.what == 100) {
                if (LiveToolService.this.mSendTimeDelta > 1800 || !ArkProperties.networkAvailable.get().booleanValue()) {
                    LiveToolService.this.mToolView.setMsgTip(false, true, "网络出现卡顿，请检查网络连接");
                    if (LiveToolService.this.mSendTimeDelta <= 1800) {
                        LiveToolService.this.mKaCount = 0;
                    } else {
                        MonitorCenter.getInstance().reportLiveInterrupt(MonitorCenter.LIVE_INTERRUPT_REASON_CONGESTION);
                        LiveToolService.access$308(LiveToolService.this);
                        if (LiveToolService.this.mKaCount == 5) {
                            ArkUtils.send(new FloatWinInterface.onKaLongTime());
                        }
                    }
                } else {
                    LiveToolService.this.mToolView.setMsgTip(false, false, String.valueOf(message.obj));
                    LiveToolService.this.mKaCount = 0;
                }
                ArkUtils.send(new FloatWinInterface.GetSendTime());
            }
        }
    };

    @Override // com.huya.berry.sdklive.liveTool.LiveToolView.LiveToolCallBack
    public void requestStartLive() {
    }

    static /* synthetic */ int access$308(LiveToolService liveToolService) {
        int i = liveToolService.mKaCount;
        liveToolService.mKaCount = i + 1;
        return i;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ArkUtils.register(this);
        createFloatViews();
        NetworkSpeedUtils networkSpeedUtils = new NetworkSpeedUtils(ArkValue.gContext, this.mHandler);
        this.networkSpeedUtils = networkSpeedUtils;
        networkSpeedUtils.startNetSpeed();
    }

    private void startForeground() {
        String string = getResources().getString(ResourceUtil.getStringResIDByName("app_name"));
        if (TextUtils.isEmpty(string)) {
            string = getResources().getString(ResourceUtil.getStringResIDByName("hyberry_live_notifination_title"));
        }
        NotificationCompat1.Builder notificationBuilder = NotifyHelper.getNotificationBuilder(getApplicationContext());
        notificationBuilder.setContentTitle(string);
        notificationBuilder.setContentText(getResources().getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_live_notifination_content)));
        notificationBuilder.setSmallIcon(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_start_live_normal));
        L.info(TAG, "startForeground...");
        startForeground(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_start_live_normal) + ((int) (Math.random() * 1000.0d)) + 1, notificationBuilder.build());
    }

    private int parseNetSpeed(Object obj) {
        if (!(obj instanceof String)) {
            return 0;
        }
        try {
            return Integer.valueOf(((String) obj).split(" ")[0]).intValue();
        } catch (Throwable th) {
            L.error(TAG, th);
            return 0;
        }
    }

    private void onStopService() {
        L.info(TAG, "onStopService...");
    }

    private void showTool(boolean z, boolean z2) {
        this.mIsShow = z;
        IToolView iToolView = this.mToolView;
        if (iToolView != null) {
            iToolView.showTool(z, z2);
        }
    }

    private void addSystemNotice() {
        HySignalServiceCallback.ChatText chatText = new HySignalServiceCallback.ChatText();
        chatText.uid = -1L;
        chatText.nickname = ArkValue.gContext.getResources().getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_system_notice));
        chatText.text = ArkValue.gContext.getResources().getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_living_sys_tips));
        ArkUtils.send(new HySignalServiceCallback.PubTextNotice(chatText));
    }

    private void addShareNotice() {
        HySignalServiceCallback.ChatText chatText = new HySignalServiceCallback.ChatText();
        chatText.uid = -2L;
        chatText.text = String.valueOf(UserProperties.roomId.get());
        ArkUtils.send(new HySignalServiceCallback.PubTextNotice(chatText));
    }

    @Override // com.huya.berry.sdklive.liveTool.LiveToolView.LiveToolCallBack
    public void onHalfHide(boolean z) {
        this.mHalfHide = z;
        ArkUtils.send(new LivePresenterInterface.ToolHalfHide(z));
    }

    @Override // com.huya.berry.sdklive.liveTool.LiveToolView.LiveToolCallBack
    public void onExpandMsgContainer() {
        ArkUtils.send(new LivePresenterInterface.ExpandMsgContainer());
    }

    @Override // com.huya.berry.sdklive.liveTool.LiveToolView.LiveToolCallBack
    public void onPauseLive(boolean z) {
        L.info(TAG, "onPauseLive..." + z);
        this.mToolView.setMsgTip(z, false, "0 KB/s");
        if (z) {
            ArkUtils.send(new FloatWinInterface.onPauseLive());
            this.networkSpeedUtils.pauseNetSpeed();
            SdkProperties.openPrivacy.set(true);
        } else {
            ArkUtils.send(new FloatWinInterface.onResumeLive());
            this.networkSpeedUtils.resumeNetSpeed();
            SdkProperties.openPrivacy.set(false);
        }
    }

    @Override // com.huya.berry.sdklive.liveTool.LiveToolView.LiveToolCallBack
    public void onMicBan(boolean z) {
        L.info(TAG, "onMicBan..." + z);
        this.mToolView.setMicban(z);
        if (z) {
            Report.event(SdkReportConst.CLICK_SMALLWINDOW_MUTE);
            ArkUtils.send(new FloatWinInterface.onPauseAudio());
        } else {
            Report.event(SdkReportConst.CLICK_SMALLWINDOW_UNMUTE);
            ArkUtils.send(new FloatWinInterface.onResumeAudio());
        }
    }

    @Override // com.huya.berry.sdklive.liveTool.LiveToolView.LiveToolCallBack
    public void onStopLive() {
        this.mIsStopLive = true;
        ArkUtils.send(new LivePresenterInterface.CloseBtnClicked());
    }

    private void createFloatViews() {
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService("window");
        this.mWindowManager = windowManager;
        if (windowManager == null) {
            L.error(TAG, "mWindowManager is null");
        }
        LiveToolView liveToolView = new LiveToolView(this, this.mWindowManager, this);
        this.mToolView = liveToolView;
        liveToolView.createViews();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        L.info(TAG, "onBind startTime=" + String.valueOf(this.startTime));
        int i = this.startTime;
        if (i > 0) {
            L.error(TAG, "error startTime");
            onStopService();
            return null;
        }
        this.startTime = i + 1;
        L.info(TAG, "startTime:" + this.startTime);
        startForeground();
        requestStartLive();
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        L.info(TAG, "onDestroy...");
        this.networkSpeedUtils.stopNetSpeed();
        this.mToolView.onDestroy();
        stopForeground(true);
        ArkUtils.unregister(this);
        super.onDestroy();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mToolView.onConfigurationChanged(configuration);
    }

    @Override // com.huya.berry.sdklive.living.ILivingView
    public void showProgress(String str, boolean z) {
        this.mToolView.showProgress(str, z);
    }

    @Override // com.huya.berry.sdklive.living.ILivingView
    public void dismissProgress() {
        this.mToolView.dismissProgress();
    }

    @Override // com.huya.berry.sdklive.living.ILivingView
    public void setOnlineUser(int i) {
        this.mToolView.setOnlineUser(i);
    }

    @IASlot(executorID = 1)
    public void onLiveToolState(LivePresenterInterface.LiveToolState liveToolState) {
        boolean z = liveToolState.mIsShow;
        this.mIsShow = z;
        this.mToolView.showTool(z, true);
    }

    @IASlot(executorID = 1)
    public void onLiveToolAction(LivePresenterInterface.LiveToolAction liveToolAction) {
        IToolView iToolView;
        L.info(TAG, "onLiveToolAction %d", Integer.valueOf(liveToolAction.mAction));
        if (liveToolAction.mAction != 9 || (iToolView = this.mToolView) == null) {
            return;
        }
        iToolView.halfHideTool();
    }

    @IASlot(executorID = 1, mark = {SdkProperties.MarkHideMsgNumber})
    public void onPortrait(PropertySet<Integer> propertySet) {
        IToolView iToolView = this.mToolView;
        if (iToolView == null) {
            return;
        }
        iToolView.updateNewsNum(propertySet.newValue.intValue());
    }

    @IASlot(executorID = 1)
    public void onGetLiveUserCount(CommonEvent.OnLiveUserCount onLiveUserCount) {
        if (onLiveUserCount == null) {
            return;
        }
        setOnlineUser(onLiveUserCount.count);
    }

    @Override // com.huya.berry.sdklive.liveTool.LiveToolView.LiveToolCallBack
    public void backToLiveRoom() {
        ArkUtils.send(new CommonEvent.GoUserCenter());
    }

    @IASlot(executorID = 1)
    public void onStartLive(FloatWinInterface.OnStartLive onStartLive) {
        L.info(TAG, "onStartLive");
        if (this.mIsStopLive) {
            return;
        }
        showTool(true, true);
        this.mToolView.resetMsgContainer();
        this.mIsStarted = true;
        this.mToolView.setIsStarted(true);
        this.mToolView.showMsgContainer(true);
        addSystemNotice();
        addShareNotice();
    }

    @IASlot(executorID = 1)
    public void onEndLive(FloatWinInterface.OnEndLive onEndLive) {
        L.info(TAG, "OnEndLive");
        showTool(false, true);
        this.mIsStarted = false;
        this.mIsStopLive = false;
        this.mToolView.setIsStarted(false);
    }

    @IASlot(executorID = 1)
    public void getSendTimeDelta(FloatWinInterface.GetSendTimeCallback getSendTimeCallback) {
        if (getSendTimeCallback == null) {
            return;
        }
        this.mSendTimeDelta = getSendTimeCallback.timeDelta;
    }

    @IASlot(executorID = 1)
    public void OnPauseLive(FloatWinInterface.OnAutoPauseLive onAutoPauseLive) {
        if (this.mIsStopLive || SdkProperties.isStopMidea.get().booleanValue()) {
            return;
        }
        if (!SdkProperties.isClickPauseLive.get().booleanValue()) {
            onPauseLive(onAutoPauseLive.isPause);
        }
        if (onAutoPauseLive.isPause) {
            showTool(false, false);
            Report.event(SdkReportConst.LIVE_BACKGROUND_PAUSE);
        } else {
            showTool(true, false);
        }
    }

    @IASlot(executorID = 1)
    public void onBackPress(CommonEvent.onBackPress onbackpress) {
        L.info(TAG, "onBackPress");
        this.mToolView.showStopLiveAlert();
    }
}
