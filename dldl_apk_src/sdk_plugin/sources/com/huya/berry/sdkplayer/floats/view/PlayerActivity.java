package com.huya.berry.sdkplayer.floats.view;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.endlive.event.HuyaSdkInterface;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.SystemUI;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.sdkplayer.floats.FloatingVideoMgr;
import com.huya.berry.sdkplayer.line.LineListFragment;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PlayerActivity extends Activity {
    protected static final String TAG = "PlayerActivity";
    protected int layoutId = ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_activity_player);
    private DanmuView mDanmuView;
    protected ImageView mFloatingCloseIV;
    private FrameLayout mFrameLayout;
    private PlayerView mPlayerView;
    private PlayerViewUi mPlayerViewUi;
    private boolean smallWin;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(this.layoutId);
        initView();
        hideBottomUIMenu();
        Report.event(SdkReportConst.LIVE_FULLSCREEN);
    }

    public void initView() {
        this.mFrameLayout = (FrameLayout) findViewById(ResourceUtil.getIdResIDByName(SqR.id.container));
        ImageView imageView = (ImageView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.floating_close));
        this.mFloatingCloseIV = imageView;
        imageView.setVisibility(8);
        DanmuView danmuView = new DanmuView(this.mFrameLayout);
        this.mDanmuView = danmuView;
        danmuView.show(PlayerHelper.presenterUid);
        PlayerView playerView = (PlayerView) findViewById(ResourceUtil.getIdResIDByName(SqR.id.video_playerview));
        this.mPlayerView = playerView;
        playerView.startPlay(PlayerHelper.presenterUid, false);
        PlayerViewUi playerViewUi = new PlayerViewUi(this.mFrameLayout);
        this.mPlayerViewUi = playerViewUi;
        playerViewUi.show();
        this.mPlayerViewUi.setDanmuView(this.mDanmuView);
        this.mPlayerViewUi.setPlayerView(this.mPlayerView);
        this.mPlayerViewUi.setActivity(this);
        this.mPlayerViewUi.setViewVisible(true);
        getWindow().getDecorView().setKeepScreenOn(true);
        startVideo(true);
        ArkUtils.register(this);
    }

    protected void hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            getWindow().getDecorView().setSystemUiVisibility(8);
        } else if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(4102);
        }
    }

    private void back() {
        PlayerView playerView = this.mPlayerView;
        if (playerView != null) {
            playerView.stopPlay();
            this.mPlayerView.onDestroy();
            this.mPlayerView = null;
        }
        finish();
        Report.event(SdkReportConst.LIVE_FULLSCREEN_BACK);
    }

    public void setWaterMark(boolean z) {
        switchBarrage(z);
    }

    public void switchBarrage(boolean z) {
        DanmuView danmuView = this.mDanmuView;
        if (danmuView != null) {
            danmuView.switchBarrage(z);
        }
    }

    public void startVideo(boolean z) {
        L.info(TAG, "enter startVideoisAuto=%b", Boolean.valueOf(z));
        PlayerView playerView = this.mPlayerView;
        if (playerView != null) {
            playerView.startVideo(z);
        }
    }

    public void stopVideo() {
        L.info(TAG, "enter stopVideo");
        switchBarrage(false);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        L.info(TAG, "destroy");
        ArkUtils.unregister(this);
        stopVideo();
        PlayerView playerView = this.mPlayerView;
        if (playerView != null) {
            playerView.stopPlay();
            this.mPlayerView.onDestroy();
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
        if (PlayerHelper.floatPlay && !this.smallWin) {
            PlayerHelper.floatPlay = false;
            FloatingVideoMgr.getInstance().start(PlayerHelper.mScreenType);
        } else {
            ArkUtils.send(new CommonEvent.NormalPlay(true));
        }
        this.smallWin = false;
        super.onDestroy();
    }

    @IASlot(executorID = 1)
    public void onBackFloating(CommonEvent.BackFloating backFloating) {
        back();
    }

    @IASlot(executorID = 1)
    public void openFloatEdit(CommonEvent.OpenFloatEdit openFloatEdit) {
        SystemUI.hideSoftInput(this, openFloatEdit.view);
        FlotingEditFragment.getInstance(getFragmentManager()).show(getFragmentManager(), FlotingEditFragment.TAG);
    }

    @IASlot(executorID = 1)
    public void openLine(CommonEvent.OpenLine openLine) {
        LineListFragment.getInstance(getFragmentManager()).show(getFragmentManager(), LineListFragment.TAG);
        Report.event(SdkReportConst.LIVE_FULLSCREEN_QUALITY);
    }

    @IASlot(executorID = 1)
    public void onSmallWindowPlay(CommonEvent.SmallWindowPlay smallWindowPlay) {
        this.smallWin = true;
        finish();
    }

    @IASlot(executorID = 1)
    public void onHideBottomUIMenu(CommonEvent.HideBottomUIMenu hideBottomUIMenu) {
        hideBottomUIMenu();
    }

    @IASlot(executorID = 1)
    public void onVideoRenderStart(HuyaSdkInterface.VideoRenderStart videoRenderStart) {
        PlayerViewUi playerViewUi = this.mPlayerViewUi;
        if (playerViewUi != null) {
            playerViewUi.setInfo();
        }
    }
}
