package com.huya.berry.sdkplayer.floats.view;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.duowan.HUYA.ScreenType;
import com.duowan.HUYA.UserEventRsp;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.NetworkUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.module.live.ISdkLiveService;
import com.huya.berry.sdkplayer.floats.view.BaseFloatingLayout;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.live.rxutils.SchedulerUtils;
import com.huya.live.service.ServiceCenter;
import com.huya.statistics.LiveCommonFieldProvider;
import com.huya.statistics.LiveStaticsicsSdk;
import com.sqwan.bugless.core.ParamsManager;
import com.sqwan.liveshow.huya.SqR;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FloatingLayout extends BaseFloatingLayout {
    private static final String TAG = "FloatingLayout";
    private DanmuView mDanmuView;
    private ImageView mFloatingCloseIV;
    private FrameLayout mFrameLayout;
    private boolean mIsFullScreen;
    private PlayerView mPlayerView;
    private PlayerViewUi mPlayerViewUi;
    private TextView mPrompt;
    private ImageView mToucheArea;

    public FloatingLayout(Context context, WindowManager.LayoutParams layoutParams, ScreenType screenType) {
        super(context, layoutParams);
        initView(context);
    }

    @Override // com.huya.berry.sdkplayer.floats.view.BaseFloatingLayout
    public void initView(Context context) {
        SdkProperties.isVideoLandScape.set(true);
        Report.event(SdkReportConst.LIVE_SMALLWINDOW);
        this.mWindowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
        this.mlayout = (ViewGroup) LayoutInflater.from(context).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_activity_player), this);
        this.mFrameLayout = (FrameLayout) this.mlayout.findViewById(ResourceUtil.getIdResIDByName(SqR.id.container));
        ImageView imageView = (ImageView) this.mlayout.findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_close));
        this.mFloatingCloseIV = imageView;
        imageView.setVisibility(0);
        this.mFloatingCloseIV.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.FloatingLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlayerHelper.floatPlay = false;
                FloatingLayout.this.closeFloating(false);
                Report.event(SdkReportConst.LIVE_SMALLWINDOW_CLOSE);
            }
        });
        TextView textView = (TextView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_prompt1));
        this.mPrompt = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.FloatingLayout.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatingLayout.this.mPrompt.setVisibility(8);
                FloatingLayout.this.createPlayer();
            }
        });
        DanmuView danmuView = new DanmuView(this.mFrameLayout);
        this.mDanmuView = danmuView;
        danmuView.show(PlayerHelper.presenterUid);
        PlayerView playerView = (PlayerView) this.mlayout.findViewById(ResourceUtil.getIdResIDByName(SqR.id.video_playerview));
        this.mPlayerView = playerView;
        playerView.startPlay(PlayerHelper.presenterUid, false);
        this.mToucheArea = new ImageView(context);
        this.mToucheArea.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.mToucheArea.setLongClickable(true);
        this.mFrameLayout.addView(this.mToucheArea);
        this.mToucheArea.setOnTouchListener(new BaseFloatingLayout.FloatingListener(new GestureDetector(ArkValue.gContext, new MyOnGestureListener())));
        PlayerViewUi playerViewUi = new PlayerViewUi(this.mFrameLayout);
        this.mPlayerViewUi = playerViewUi;
        playerViewUi.show();
        this.mPlayerViewUi.setDanmuView(this.mDanmuView);
        this.mPlayerViewUi.setPlayerView(this.mPlayerView);
        setKeepScreenOn(true);
        this.mPlayerViewUi.setViewVisible(false);
        ArkUtils.register(this);
        SdkProperties.isPLayerFloating.set(true);
        joinChannel(PlayerHelper.presenterUid, 1);
        PlayerHelper.floatPlay = true;
    }

    private void joinChannel(final long j, int i) {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.userEvent(j, i).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<UserEventRsp>() { // from class: com.huya.berry.sdkplayer.floats.view.FloatingLayout.3
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(UserEventRsp userEventRsp) {
                }
            });
            if (i == 1) {
                LiveStaticsicsSdk.setLiveCommonFieldProvider(new LiveCommonFieldProvider() { // from class: com.huya.berry.sdkplayer.floats.view.FloatingLayout.4
                    @Override // com.huya.statistics.LiveCommonFieldProvider
                    public Map<String, String> getLiveCommonField() {
                        HashMap map = new HashMap();
                        map.put("ayyuid", j + "");
                        map.put(ParamsManager.CONFIG_NODE_GAMEID, SdkProperties.gameId.get() + "");
                        return map;
                    }
                });
                LiveStaticsicsSdk.chnStartUp();
            } else {
                LiveStaticsicsSdk.chnEndUp();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeFloating(boolean z) {
        L.info(TAG, "enter onClick, quit");
        this.mIsFullScreen = z;
        PlayerView playerView = this.mPlayerView;
        if (playerView != null) {
            playerView.stopPlay();
            this.mPlayerView.mUid = 0L;
        }
        if (!this.mIsFullScreen) {
            SdkProperties.isPLayerFloating.set(false);
            SdkProperties.needPlayerFloat.set(false);
            PlayerHelper.lastInputText = "";
            ArkUtils.send(new CommonEvent.CloseFloating());
            ArkUtils.send(new CommonEvent.NormalPlay(false));
        }
        closeFloatingWindow();
    }

    @Override // com.huya.berry.sdkplayer.floats.view.BaseFloatingLayout
    public void createPlayer() {
        PlayerView playerView = this.mPlayerView;
        if (playerView != null) {
            playerView.replay();
        }
    }

    @Override // com.huya.berry.sdkplayer.floats.view.BaseFloatingLayout
    public void startVideo(ScreenType screenType, boolean z, boolean z2) {
        L.info(TAG, "enter startVideo, liveRoomType=%s, isShowAndPlay=%b, isAuto=%b", screenType, Boolean.valueOf(z), Boolean.valueOf(z2));
        this.mAlertHelperId = System.currentTimeMillis();
        startVideo(z2);
        super.startVideo(screenType, z, z2);
    }

    private void startVideo(boolean z) {
        PlayerView playerView = this.mPlayerView;
        if (playerView != null) {
            playerView.startVideo(z);
        }
    }

    @Override // com.huya.berry.sdkplayer.floats.view.BaseFloatingLayout
    public void stopVideo() {
        L.info(TAG, "enter stopVideo");
        this.mDanmuView.switchBarrage(false);
    }

    @Override // com.huya.berry.sdkplayer.floats.view.BaseFloatingLayout
    public void setWaterMark(boolean z) {
        switchBarrage(z);
    }

    @Override // com.huya.berry.sdkplayer.floats.view.BaseFloatingLayout
    public void switchBarrage(boolean z) {
        DanmuView danmuView = this.mDanmuView;
        if (danmuView != null) {
            danmuView.switchBarrage(z);
        }
    }

    public void report() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("Gameid", SdkProperties.gameId.get());
            jSONObject.put("Network", NetworkUtil.isWifiActive(ArkValue.gContext) ? "wifi" : "4G");
            jSONObject.put("Roomid", PlayerHelper.roomId);
            jSONObject.put("Ifapi", false);
            Report.event(SdkReportConst.LIVE_STATUS_WATCHLIVE, SdkReportConst.LIVE_STATUS_WATCHLIVE_DES, "", jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.huya.berry.sdkplayer.floats.view.BaseFloatingLayout
    public void destroy() {
        L.info(TAG, "destroy");
        ArkUtils.unregister(this);
        stopVideo();
        joinChannel(PlayerHelper.presenterUid, 2);
        PlayerView playerView = this.mPlayerView;
        if (playerView != null) {
            playerView.onDestroy();
            this.mPlayerView = null;
        }
        DanmuView danmuView = this.mDanmuView;
        if (danmuView != null) {
            danmuView.onDestroy();
            this.mDanmuView = null;
        }
        PlayerViewUi playerViewUi = this.mPlayerViewUi;
        if (playerViewUi != null) {
            playerViewUi.onDestroy();
            this.mPlayerViewUi = null;
        }
        super.destroy();
    }

    private class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        private MyOnGestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (FloatingLayout.this.mPlayerViewUi != null && FloatingLayout.this.mPlayerViewUi.getLlLeft() != null) {
                if (FloatingLayout.this.mPlayerViewUi.getLlLeft().getVisibility() == 0) {
                    if (FloatingLayout.this.mPlayerViewUi.getPlayerPresenterImpl() != null) {
                        FloatingLayout.this.mPlayerViewUi.getPlayerPresenterImpl().hideView();
                    }
                    return super.onSingleTapConfirmed(motionEvent);
                }
                if (FloatingLayout.this.mPlayerViewUi.getPlayerPresenterImpl() != null) {
                    FloatingLayout.this.mPlayerViewUi.getPlayerPresenterImpl().showView();
                }
            }
            return super.onSingleTapConfirmed(motionEvent);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (!FloatingLayout.this.isMove()) {
                FloatingWindowMgr.scaleFloatingWindow();
            }
            return super.onDoubleTap(motionEvent);
        }
    }

    @IASlot(executorID = 1)
    public void onFullScreenCloseFloating(CommonEvent.FullScreenCloseFloating fullScreenCloseFloating) {
        closeFloating(fullScreenCloseFloating.fullScreen);
    }

    @IASlot(executorID = 1)
    public void onShowFloatingRefresh(CommonEvent.ShowFloatingRefresh showFloatingRefresh) {
        this.mPrompt.setVisibility(0);
    }
}
