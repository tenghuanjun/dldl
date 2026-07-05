package com.huya.berry.sdkplayer.floats.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.duowan.HUYA.GetLivingInfoRsp;
import com.duowan.HUYA.ScreenType;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.huya.berry.endlive.event.HuyaSdkInterface;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.utils.NetworkUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.TaskExecutor;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.module.live.ISdkLiveService;
import com.huya.berry.sdkplayer.player.HYMediaSoftDecodePlayer;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.live.rxutils.SchedulerUtils;
import com.huya.live.service.ServiceCenter;
import com.sqwan.liveshow.huya.SqR;
import io.reactivex.android.schedulers.AndroidSchedulers;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PlayerView extends RelativeLayout {
    private static final String TAG = "PlayerView";
    private boolean isCanPlay;
    private DanmuView mDanmuView;
    Runnable mDelayTask;
    private ImageView mFloatingBg;
    protected HYMediaSoftDecodePlayer mLinkPlayer;
    private HyberryVideoView mLivePlayerView;
    private ProgressBar mLoading;
    private boolean mNeedShowDanmu;
    private boolean mNeedShowFlaot;
    private boolean mNeedShowUi;
    private RelativeLayout mPlayViewContainer;
    private PlayerViewUi mPlayerViewUi;
    private TextView mPrompt;
    private int mRoomId;
    public long mUid;
    private FrameLayout parentLayout;
    private boolean smallWinPlay;

    public PlayerView(Context context) {
        super(context);
        this.isCanPlay = false;
        this.smallWinPlay = false;
        this.mNeedShowDanmu = false;
        this.mNeedShowUi = false;
        this.mNeedShowFlaot = true;
        this.mDelayTask = new Runnable() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerView.2
            @Override // java.lang.Runnable
            public void run() {
                TaskExecutor.uiHandler().removeCallbacks(PlayerView.this.mDelayTask);
                PlayerView.this.stopPlay();
                PlayerView.this.showRefresh();
            }
        };
        configView(context);
    }

    public PlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isCanPlay = false;
        this.smallWinPlay = false;
        this.mNeedShowDanmu = false;
        this.mNeedShowUi = false;
        this.mNeedShowFlaot = true;
        this.mDelayTask = new Runnable() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerView.2
            @Override // java.lang.Runnable
            public void run() {
                TaskExecutor.uiHandler().removeCallbacks(PlayerView.this.mDelayTask);
                PlayerView.this.stopPlay();
                PlayerView.this.showRefresh();
            }
        };
        configView(context);
    }

    public PlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isCanPlay = false;
        this.smallWinPlay = false;
        this.mNeedShowDanmu = false;
        this.mNeedShowUi = false;
        this.mNeedShowFlaot = true;
        this.mDelayTask = new Runnable() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerView.2
            @Override // java.lang.Runnable
            public void run() {
                TaskExecutor.uiHandler().removeCallbacks(PlayerView.this.mDelayTask);
                PlayerView.this.stopPlay();
                PlayerView.this.showRefresh();
            }
        };
        configView(context);
    }

    protected void configView(Context context) {
        if (context == null) {
            return;
        }
        LayoutInflater.from(context).inflate(ResourceUtil.getLayoutResIDByName(PlayerHelper.mScreenType == ScreenType.ST_Horizonal ? SqR.layout.hyberry_view_player : SqR.layout.hyberry_view_player_portrait), (ViewGroup) this, true);
        this.mPlayViewContainer = (RelativeLayout) findViewById(ResourceUtil.getIdResIDByName(SqR.id.playview_container));
        this.parentLayout = (FrameLayout) findViewById(ResourceUtil.getIdResIDByName(SqR.id.danmu_container));
        this.mLoading = (ProgressBar) findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_loading));
        this.mFloatingBg = (ImageView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_video_bg));
        if (PlayerHelper.mScreenType == ScreenType.ST_Horizonal) {
            this.mFloatingBg.setImageResource(ResourceUtil.getIdResIDByName(SqR.drawable.hyberry_floating_bg_game));
        } else {
            this.mFloatingBg.setImageResource(ResourceUtil.getIdResIDByName(SqR.drawable.hyberry_floating_bg_moble));
        }
        this.mPrompt = (TextView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_prompt));
        this.mLivePlayerView = (HyberryVideoView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_video_playerView));
        this.mLoading.setVisibility(8);
        this.mPrompt.setEnabled(true);
        this.mPrompt.setClickable(true);
        this.mPrompt.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlayerView.this.replay();
            }
        });
        addEvents();
    }

    public void createPlayer() {
        L.info(TAG, "createPlayer");
        if (NetworkUtil.isNetworkAvailable(ArkValue.gContext)) {
            if (this.mLinkPlayer == null) {
                this.mLinkPlayer = new HYMediaSoftDecodePlayer();
            }
            this.mLinkPlayer.createPlayer(this.mLivePlayerView);
            loading();
            L.info(TAG, "createPlayer end");
        }
    }

    private void startLoading() {
        L.info(TAG, "enter loading");
        this.mLoading.setVisibility(0);
        this.mPrompt.setVisibility(8);
    }

    private void connect(boolean z) {
        L.info(TAG, "connect");
        if (!NetworkUtil.isNetworkAvailable(ArkValue.gContext)) {
            L.info(TAG, "network unavailable");
        } else {
            this.mFloatingBg.setVisibility(0);
        }
    }

    private void destroyPlayer() {
        L.info(TAG, "destroyPlayer mRoomId " + this.mRoomId);
        this.mRoomId = 0;
        HYMediaSoftDecodePlayer hYMediaSoftDecodePlayer = this.mLinkPlayer;
        if (hYMediaSoftDecodePlayer != null) {
            hYMediaSoftDecodePlayer.destroy();
            this.mLinkPlayer = null;
        }
    }

    public void startVideo(boolean z) {
        this.mFloatingBg.setVisibility(0);
        if (PlayerHelper.mScreenType == ScreenType.ST_Horizonal) {
            this.mFloatingBg.setImageResource(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_floating_bg_game));
        } else {
            this.mFloatingBg.setImageResource(ResourceUtil.getDrawableResIDByName(SqR.drawable.hyberry_floating_bg_moble));
        }
        connect(z);
    }

    public void loading() {
        TaskExecutor.uiHandler().removeCallbacks(this.mDelayTask);
        this.mFloatingBg.setVisibility(0);
        startLoading();
        TaskExecutor.uiHandler().postDelayed(this.mDelayTask, 8000L);
    }

    public void setMuteAudio(boolean z) {
        HYMediaSoftDecodePlayer hYMediaSoftDecodePlayer = this.mLinkPlayer;
        if (hYMediaSoftDecodePlayer != null) {
            hYMediaSoftDecodePlayer.setMuteAudio(z);
        }
    }

    public void onResume() {
        L.info(TAG, "onResume");
    }

    public void onPause() {
        L.info(TAG, "onPause");
        destroyPlayer();
    }

    protected void addEvents() {
        ArkUtils.register(this);
    }

    protected void removeEvents() {
        ArkUtils.unregister(this);
        TaskExecutor.uiHandler().removeCallbacks(this.mDelayTask);
    }

    public void startPlay(long j, boolean z, boolean z2, boolean z3) {
        if (this.isCanPlay) {
            return;
        }
        this.mNeedShowFlaot = z3;
        this.mNeedShowDanmu = z;
        this.mNeedShowUi = z2;
        startPlay(j, z, z2);
        PlayerViewUi playerViewUi = this.mPlayerViewUi;
        if (playerViewUi != null) {
            playerViewUi.setViewVisible(!z3);
        }
    }

    public void startPlay(long j, boolean z, boolean z2) {
        if (this.isCanPlay) {
            return;
        }
        this.mNeedShowDanmu = z;
        this.mNeedShowUi = z2;
        startPlay(j, z);
        if (z2 && this.mPlayerViewUi == null) {
            PlayerViewUi playerViewUi = new PlayerViewUi(this.mPlayViewContainer);
            this.mPlayerViewUi = playerViewUi;
            playerViewUi.show();
            this.mPlayerViewUi.setDanmuView(this.mDanmuView);
            this.mPlayerViewUi.setPlayerView(this);
            this.mPlayerViewUi.setViewVisible(false);
        }
    }

    public void startPlay(long j, boolean z) {
        if (this.isCanPlay) {
            return;
        }
        this.mNeedShowDanmu = z;
        startPlay(j);
        if (z && this.mDanmuView == null) {
            DanmuView danmuView = new DanmuView(this.parentLayout);
            this.mDanmuView = danmuView;
            danmuView.show(j);
            this.mDanmuView.switchBarrage(PlayerHelper.isOpenDanmu);
        }
    }

    public void startPlay(long j) {
        if (this.isCanPlay) {
            return;
        }
        this.mUid = j;
        this.isCanPlay = true;
        this.mLoading.setVisibility(0);
        getLiveInfoByGame(0L, 0L, j, 0L);
        L.info(TAG, "startPlay isCanPlay " + this.isCanPlay + "  startPlay mView " + getParent());
    }

    private void getLiveInfoByGame(long j, long j2, long j3, long j4) {
        L.info(TAG, "getLiveInfoByGame uid " + j3 + "  roomId " + j4);
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.getLivingInfo(j, j2, j3, j4).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<GetLivingInfoRsp>() { // from class: com.huya.berry.sdkplayer.floats.view.PlayerView.3
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(GetLivingInfoRsp getLivingInfoRsp) {
                    PlayerView.this.mRoomId = getLivingInfoRsp.tNotice.iRoomId;
                    L.info(PlayerView.TAG, "onGetLiveInfo mRoomId" + PlayerView.this.mRoomId);
                    if (PlayerView.this.mRoomId > 0) {
                        if (PlayerView.this.isCanPlay) {
                            PlayerView.this.createPlayer();
                        }
                    } else {
                        PlayerView.this.hideAll();
                        PlayerView.this.stopPlay();
                        ArkToast.show("主播未开播");
                    }
                }
            });
        }
    }

    public void stopPlay() {
        this.isCanPlay = false;
        this.mLoading.setVisibility(8);
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
        destroyPlayer();
        L.info(TAG, "stopPlay isCanPlay " + this.isCanPlay + "  stopPlay mView " + getParent());
    }

    public void replay() {
        this.mPrompt.setVisibility(8);
        stopPlay();
        startPlay(this.mUid, this.mNeedShowDanmu, this.mNeedShowUi, this.mNeedShowFlaot);
    }

    public void showRefresh() {
        this.mFloatingBg.setVisibility(0);
        this.mLoading.setVisibility(8);
        this.mPrompt.setVisibility(0);
        ArkUtils.send(new CommonEvent.ShowFloatingRefresh());
    }

    public void hide() {
        L.info(TAG, "DanmuView hide");
        removeEvents();
        clear();
    }

    private void clear() {
        RelativeLayout relativeLayout = this.mPlayViewContainer;
        if (relativeLayout != null) {
            relativeLayout.removeAllViews();
        }
        destroyPlayer();
    }

    public void hideAll() {
        L.info(TAG, "enter hideAll");
        this.mLoading.setVisibility(8);
        this.mFloatingBg.setVisibility(8);
        this.mPrompt.setVisibility(8);
    }

    public void onDestroy() {
        hide();
        this.parentLayout = null;
    }

    @IASlot(executorID = 1)
    public void onSwitchLive(HuyaSdkInterface.SwitchLive switchLive) {
        L.info(TAG, "onSwitchLive mView");
        if (!this.isCanPlay || this.mUid <= 0) {
            return;
        }
        destroyPlayer();
        getLiveInfoByGame(0L, 0L, this.mUid, 0L);
        L.info(TAG, "onSwitchLive replay");
    }

    @IASlot(executorID = 1)
    public void onSwitchRate(HuyaSdkInterface.SwitchRate switchRate) {
        if (this.isCanPlay && this.mUid > 0) {
            destroyPlayer();
            getLiveInfoByGame(0L, 0L, this.mUid, 0L);
            L.info(TAG, "onSwitchRate replay");
        }
        L.info(TAG, "onSwitchRate");
    }

    @IASlot(executorID = 1)
    public void onVideoRenderStart(HuyaSdkInterface.VideoRenderStart videoRenderStart) {
        HYMediaSoftDecodePlayer hYMediaSoftDecodePlayer = this.mLinkPlayer;
        if (hYMediaSoftDecodePlayer != null) {
            hYMediaSoftDecodePlayer.setMuteAudio(PlayerHelper.isOpenMute);
        }
        TaskExecutor.uiHandler().removeCallbacks(this.mDelayTask);
        hideAll();
        L.info(TAG, "onVideoRenderStart");
    }

    @IASlot(executorID = 1)
    public void onVideoRenderStop(HuyaSdkInterface.VideoRenderStop videoRenderStop) {
        if (this.mUid > 0) {
            destroyPlayer();
            getLiveInfoByGame(0L, 0L, this.mUid, 0L);
            L.info(TAG, "onVideoRenderStop replay");
        }
        L.info(TAG, "onVideoRenderStop");
    }

    @IASlot(executorID = 1)
    public void onFullScreenCloseFloating(CommonEvent.FullScreenCloseFloating fullScreenCloseFloating) {
        stopPlay();
    }

    @IASlot(executorID = 1)
    public void onSmallWindowPlay(CommonEvent.SmallWindowPlay smallWindowPlay) {
        this.smallWinPlay = true;
        stopPlay();
    }

    @IASlot(executorID = 1)
    public void onNormalPlay(CommonEvent.NormalPlay normalPlay) {
        long j = this.mUid;
        if (j > 0) {
            this.smallWinPlay = false;
            startPlay(j, this.mNeedShowDanmu, this.mNeedShowUi, this.mNeedShowFlaot);
        }
    }

    @IASlot(executorID = 1)
    public void onPauseOrPlay(CommonEvent.PauseOrPlay pauseOrPlay) {
        if (this.mLinkPlayer == null) {
            return;
        }
        if (pauseOrPlay.hasPause) {
            this.mLinkPlayer.startPlay();
        } else {
            this.mLinkPlayer.pausePlay();
        }
    }

    @IASlot(executorID = 1)
    public void onSwitchVoice(CommonEvent.SwitchVoice switchVoice) {
        PlayerHelper.isOpenMute = switchVoice.open;
        setMuteAudio(PlayerHelper.isOpenMute);
        PlayerViewUi playerViewUi = this.mPlayerViewUi;
        if (playerViewUi != null) {
            playerViewUi.setVoiceSelected();
        }
    }

    @IASlot(executorID = 1)
    public void onSwitchDanmu(CommonEvent.SwitchDanmu switchDanmu) {
        PlayerHelper.isOpenDanmu = switchDanmu.open;
        PlayerViewUi playerViewUi = this.mPlayerViewUi;
        if (playerViewUi != null) {
            playerViewUi.setDanmuSelected();
        }
    }
}
