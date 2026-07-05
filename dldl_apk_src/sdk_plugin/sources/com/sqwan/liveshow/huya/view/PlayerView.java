package com.sqwan.liveshow.huya.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.nbvideo.NBVideo;
import com.nbvideo.VideoCallback;
import com.nbvideo.VideoInfo;
import com.nbvideo.VideoManager;
import com.sqwan.common.util.ActivityLifeCycleUtils;
import com.sqwan.common.util.CheckNetwork;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.ViewUtils;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.engine.LiveInfoEx;
import com.sqwan.liveshow.huya.engine.LiveshowManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PlayerView extends FrameLayout implements VideoCallback {
    private static final int LIVE_LOADING_ANIMATION_DURATION = 1000;
    private static final String TAG = "PlayerView";
    private ActivityLifeCycleUtils.AppVisibilityCallback appVisibilityCallback;
    private NBVideo heartVideo;
    private LiveInfoEx liveInfoEx;
    private final NBVideo.LoadingResultListener loadingResultListener;
    private Context mContext;
    private ImageView mIvLiveLoading;
    private ObjectAnimator mLiveLoadingAnimator;
    private LinearLayout mLlLiveNetworkError;
    private Map<Long, ArrayList<String>> mapUrl;

    public PlayerView(Context context) {
        this(context, null);
    }

    public PlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.appVisibilityCallback = new ActivityLifeCycleUtils.AppVisibilityCallbackAdapter() { // from class: com.sqwan.liveshow.huya.view.PlayerView.1
            @Override // com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallbackAdapter, com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallback
            public void onBackground() {
                if (PlayerView.this.heartVideo != null) {
                    LogUtil.d(PlayerView.TAG, "onBackground: pause");
                    PlayerView.this.heartVideo.pause();
                }
            }

            @Override // com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallbackAdapter, com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallback
            public void onForeground() {
                if (PlayerView.this.heartVideo != null) {
                    LogUtil.d(PlayerView.TAG, "onForeground: restart");
                    PlayerView.this.heartVideo.restart();
                }
            }

            @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                if (PlayerView.this.mContext == activity) {
                    onForeground();
                }
            }

            @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                if (PlayerView.this.mContext == activity) {
                    onBackground();
                }
            }
        };
        this.mapUrl = new HashMap();
        this.loadingResultListener = new NBVideo.LoadingResultListener() { // from class: com.sqwan.liveshow.huya.view.PlayerView.3
            @Override // com.nbvideo.NBVideo.LoadingResultListener
            public void loadFail() {
                LogUtil.i(PlayerView.TAG, "视频无法播放，回调重新加载");
                VideoInfo videoInfoMatchLine = PlayerUtils.matchLine(PlayerView.this.liveInfoEx.liveInfo, PlayerView.this.liveInfoEx.videoInfo.disPlayName, (ArrayList) PlayerView.this.mapUrl.get(Long.valueOf(PlayerView.this.liveInfoEx.liveInfo.uid)));
                PlayerView.this.liveInfoEx.videoInfo = videoInfoMatchLine;
                if (videoInfoMatchLine != null) {
                    if (PlayerView.this.mapUrl.get(Long.valueOf(PlayerView.this.liveInfoEx.liveInfo.uid)) == null) {
                        PlayerView.this.mapUrl.put(Long.valueOf(PlayerView.this.liveInfoEx.liveInfo.uid), new ArrayList());
                    }
                    ((ArrayList) PlayerView.this.mapUrl.get(Long.valueOf(PlayerView.this.liveInfoEx.liveInfo.uid))).add(videoInfoMatchLine.getUrl());
                    PlayerView.this.heartVideo.setVideoInfo(videoInfoMatchLine);
                    PlayerView.this.heartVideo.changeUrlMediaAndReset(PlayerView.this.loadingResultListener);
                }
            }
        };
        this.mContext = context;
        initView();
        initClick();
        LiveshowManager.getInstance().setVisibilityCallback(this.appVisibilityCallback);
    }

    private void initClick() {
        LinearLayout linearLayout = this.mLlLiveNetworkError;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.view.PlayerView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (CheckNetwork.getInstance().hasNetwork()) {
                        LogUtil.i(PlayerView.TAG, "hasNetwork true");
                        PlayerView.this.hideErrorView();
                        PlayerView.this.liveLoading();
                        PlayerView.this.heartVideo.restart();
                        return;
                    }
                    ToastUtil.showToast("暂无网络");
                    LogUtil.i(PlayerView.TAG, "hasNetwork false");
                }
            });
        }
    }

    private void initView() {
        Context context = this.mContext;
        inflate(context, SqResUtils.getLayoutId(context, SqR.layout.sy37_layout_playerview), this);
        NBVideo nBVideo = (NBVideo) findViewById(SqResUtils.getId(this.mContext, SqR.id.playerview));
        this.heartVideo = nBVideo;
        nBVideo.setVideoCallback(this);
        this.mLlLiveNetworkError = (LinearLayout) findViewById(SqResUtils.getId(getContext(), SqR.id.ll_live_network_error));
        this.mIvLiveLoading = (ImageView) findViewById(SqResUtils.getId(getContext(), SqR.id.iv_sy37_liveshow_loading));
    }

    public void getLiveShowRoomData() {
        VideoInfo videoInfo;
        liveLoading();
        LiveInfoEx liveInfoEx = this.liveInfoEx;
        if (liveInfoEx == null || (videoInfo = liveInfoEx.videoInfo) == null) {
            return;
        }
        this.heartVideo.setVideoInfo(videoInfo);
        this.heartVideo.start(this.loadingResultListener);
    }

    public void restart(String str) {
        if (this.liveInfoEx != null) {
            VideoManager.getInstance().release();
            this.liveInfoEx.videoInfo = PlayerUtils.matchLine(this.liveInfoEx.liveInfo, str);
            start(this.liveInfoEx);
        }
    }

    public void start(LiveInfoEx liveInfoEx) {
        this.liveInfoEx = liveInfoEx;
        if (liveInfoEx.videoInfo != null) {
            if (this.mapUrl.get(Long.valueOf(liveInfoEx.liveInfo.uid)) != null) {
                this.mapUrl.get(Long.valueOf(liveInfoEx.liveInfo.uid)).add(this.liveInfoEx.videoInfo.getUrl());
            } else {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(this.liveInfoEx.videoInfo.getUrl());
                this.mapUrl.put(Long.valueOf(liveInfoEx.liveInfo.uid), arrayList);
            }
        }
        getLiveShowRoomData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void liveLoading() {
        this.mIvLiveLoading.setVisibility(0);
        if (this.mLiveLoadingAnimator == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mIvLiveLoading, "rotation", 0.0f, 360.0f);
            this.mLiveLoadingAnimator = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(1000L);
            this.mLiveLoadingAnimator.setRepeatCount(-1);
            this.mLiveLoadingAnimator.setRepeatMode(1);
        }
        if (this.mLiveLoadingAnimator.isRunning()) {
            return;
        }
        this.mLiveLoadingAnimator.start();
    }

    private void showErrorView() {
        ViewUtils.show(this.mLlLiveNetworkError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideErrorView() {
        ViewUtils.gone(this.mLlLiveNetworkError);
    }

    public void liveLoaded() {
        this.mIvLiveLoading.setVisibility(8);
        ObjectAnimator objectAnimator = this.mLiveLoadingAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        this.mIvLiveLoading.setAnimation(null);
    }

    public void release() {
        VideoManager.getInstance().release();
    }

    @Override // com.nbvideo.VideoCallback
    public void onError() {
        liveLoaded();
        showErrorView();
    }

    @Override // com.nbvideo.VideoCallback
    public void onSuccess() {
        liveLoaded();
        hideErrorView();
    }

    @Override // com.nbvideo.VideoCallback
    public void onLoading() {
        liveLoading();
    }

    @Override // com.nbvideo.VideoCallback
    public void onPlaying() {
        liveLoaded();
        hideErrorView();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LiveshowManager.getInstance().setVisibilityCallback(null);
    }
}
