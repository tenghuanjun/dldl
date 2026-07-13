package com.shuyu.gsyvideoplayer.video.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.transition.TransitionManager;
import com.shuyu.gsyvideoplayer.R;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.OrientationOption;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.view.SmallVideoTouch;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class GSYBaseVideoPlayer extends GSYVideoControlView {
    protected boolean isNeedAutoAdaptation;
    protected boolean mActionBar;
    protected boolean mAutoFullWithSize;
    protected View.OnClickListener mBackFromFullScreenListener;
    protected Runnable mCheckoutTask;
    protected boolean mFullAnimEnd;
    protected Handler mInnerHandler;
    private boolean mIsOnlyRotateLand;
    protected int[] mListItemRect;
    protected int[] mListItemSize;
    protected boolean mLockLand;
    protected boolean mNeedOrientationUtils;
    protected OrientationUtils mOrientationUtils;
    protected boolean mRotateViewAuto;
    protected boolean mRotateWithSystem;
    protected boolean mShowFullAnimation;
    protected View mSmallClose;
    protected boolean mStatusBar;
    protected int mSystemUiVisibility;

    protected abstract int getFullId();

    public OrientationOption getOrientationOption() {
        return null;
    }

    protected abstract int getSmallId();

    public GSYBaseVideoPlayer(Context context, Boolean bool) {
        super(context, bool);
        this.mActionBar = false;
        this.mStatusBar = false;
        this.mShowFullAnimation = true;
        this.mRotateViewAuto = true;
        this.mRotateWithSystem = true;
        this.mLockLand = false;
        this.mAutoFullWithSize = false;
        this.mNeedOrientationUtils = true;
        this.isNeedAutoAdaptation = false;
        this.mFullAnimEnd = true;
        this.mIsOnlyRotateLand = false;
        this.mInnerHandler = new Handler();
        this.mCheckoutTask = new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer.5
            @Override // java.lang.Runnable
            public void run() {
                GSYVideoPlayer fullWindowPlayer = GSYBaseVideoPlayer.this.getFullWindowPlayer();
                if (fullWindowPlayer == null || fullWindowPlayer.mCurrentState == GSYBaseVideoPlayer.this.mCurrentState || fullWindowPlayer.mCurrentState != 3 || GSYBaseVideoPlayer.this.mCurrentState == 1) {
                    return;
                }
                fullWindowPlayer.setStateAndUi(GSYBaseVideoPlayer.this.mCurrentState);
            }
        };
    }

    public GSYBaseVideoPlayer(Context context) {
        super(context);
        this.mActionBar = false;
        this.mStatusBar = false;
        this.mShowFullAnimation = true;
        this.mRotateViewAuto = true;
        this.mRotateWithSystem = true;
        this.mLockLand = false;
        this.mAutoFullWithSize = false;
        this.mNeedOrientationUtils = true;
        this.isNeedAutoAdaptation = false;
        this.mFullAnimEnd = true;
        this.mIsOnlyRotateLand = false;
        this.mInnerHandler = new Handler();
        this.mCheckoutTask = new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer.5
            @Override // java.lang.Runnable
            public void run() {
                GSYVideoPlayer fullWindowPlayer = GSYBaseVideoPlayer.this.getFullWindowPlayer();
                if (fullWindowPlayer == null || fullWindowPlayer.mCurrentState == GSYBaseVideoPlayer.this.mCurrentState || fullWindowPlayer.mCurrentState != 3 || GSYBaseVideoPlayer.this.mCurrentState == 1) {
                    return;
                }
                fullWindowPlayer.setStateAndUi(GSYBaseVideoPlayer.this.mCurrentState);
            }
        };
    }

    public GSYBaseVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mActionBar = false;
        this.mStatusBar = false;
        this.mShowFullAnimation = true;
        this.mRotateViewAuto = true;
        this.mRotateWithSystem = true;
        this.mLockLand = false;
        this.mAutoFullWithSize = false;
        this.mNeedOrientationUtils = true;
        this.isNeedAutoAdaptation = false;
        this.mFullAnimEnd = true;
        this.mIsOnlyRotateLand = false;
        this.mInnerHandler = new Handler();
        this.mCheckoutTask = new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer.5
            @Override // java.lang.Runnable
            public void run() {
                GSYVideoPlayer fullWindowPlayer = GSYBaseVideoPlayer.this.getFullWindowPlayer();
                if (fullWindowPlayer == null || fullWindowPlayer.mCurrentState == GSYBaseVideoPlayer.this.mCurrentState || fullWindowPlayer.mCurrentState != 3 || GSYBaseVideoPlayer.this.mCurrentState == 1) {
                    return;
                }
                fullWindowPlayer.setStateAndUi(GSYBaseVideoPlayer.this.mCurrentState);
            }
        };
    }

    public GSYBaseVideoPlayer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mActionBar = false;
        this.mStatusBar = false;
        this.mShowFullAnimation = true;
        this.mRotateViewAuto = true;
        this.mRotateWithSystem = true;
        this.mLockLand = false;
        this.mAutoFullWithSize = false;
        this.mNeedOrientationUtils = true;
        this.isNeedAutoAdaptation = false;
        this.mFullAnimEnd = true;
        this.mIsOnlyRotateLand = false;
        this.mInnerHandler = new Handler();
        this.mCheckoutTask = new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer.5
            @Override // java.lang.Runnable
            public void run() {
                GSYVideoPlayer fullWindowPlayer = GSYBaseVideoPlayer.this.getFullWindowPlayer();
                if (fullWindowPlayer == null || fullWindowPlayer.mCurrentState == GSYBaseVideoPlayer.this.mCurrentState || fullWindowPlayer.mCurrentState != 3 || GSYBaseVideoPlayer.this.mCurrentState == 1) {
                    return;
                }
                fullWindowPlayer.setStateAndUi(GSYBaseVideoPlayer.this.mCurrentState);
            }
        };
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected void init(Context context) {
        super.init(context);
        this.mSmallClose = findViewById(R.id.small_close);
    }

    @Override // com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onBackFullscreen() {
        clearFullscreenLayout();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYTextureRenderView
    protected void setSmallVideoTextureView() {
        if (this.mProgressBar != null) {
            this.mProgressBar.setOnTouchListener(null);
            this.mProgressBar.setVisibility(4);
        }
        if (this.mFullscreenButton != null) {
            this.mFullscreenButton.setOnTouchListener(null);
            this.mFullscreenButton.setVisibility(4);
        }
        if (this.mCurrentTimeTextView != null) {
            this.mCurrentTimeTextView.setVisibility(4);
        }
        if (this.mTextureViewContainer != null) {
            this.mTextureViewContainer.setOnClickListener(null);
        }
        View view = this.mSmallClose;
        if (view != null) {
            view.setVisibility(0);
            this.mSmallClose.setOnClickListener(new View.OnClickListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    GSYBaseVideoPlayer.this.hideSmallVideo();
                    GSYBaseVideoPlayer.this.releaseVideos();
                }
            });
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void lockTouchLogic() {
        super.lockTouchLogic();
        if (!this.mLockCurScreen) {
            OrientationUtils orientationUtils = this.mOrientationUtils;
            if (orientationUtils != null) {
                orientationUtils.setEnable(isRotateViewAuto());
                return;
            }
            return;
        }
        OrientationUtils orientationUtils2 = this.mOrientationUtils;
        if (orientationUtils2 != null) {
            orientationUtils2.setEnable(false);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, com.shuyu.gsyvideoplayer.video.base.GSYVideoView, com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onPrepared() {
        super.onPrepared();
        checkAutoFullSizeWhenFull();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView, com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onInfo(int i, int i2) {
        super.onInfo(i, i2);
        if (i == getGSYVideoManager().getRotateInfoFlag()) {
            checkAutoFullSizeWhenFull();
        }
    }

    private ViewGroup getViewGroup() {
        return (ViewGroup) CommonUtil.scanForActivity(getContext()).findViewById(android.R.id.content);
    }

    private void removeVideo(ViewGroup viewGroup, int i) {
        View viewFindViewById = viewGroup.findViewById(i);
        if (viewFindViewById == null || viewFindViewById.getParent() == null) {
            return;
        }
        viewGroup.removeView((ViewGroup) viewFindViewById.getParent());
    }

    private void saveLocationStatus(Context context, boolean z, boolean z2) {
        getLocationOnScreen(this.mListItemRect);
        if (context instanceof Activity) {
            int statusBarHeight = CommonUtil.getStatusBarHeight(context);
            Activity activity = (Activity) context;
            int actionBarHeight = CommonUtil.getActionBarHeight(activity);
            boolean z3 = (activity.getWindow().getAttributes().flags & 67108864) == 67108864;
            Debuger.printfLog("*************isTranslucent*************** " + z3);
            if (z && !z3) {
                int[] iArr = this.mListItemRect;
                iArr[1] = iArr[1] - statusBarHeight;
            }
            if (z2) {
                int[] iArr2 = this.mListItemRect;
                iArr2[1] = iArr2[1] - actionBarHeight;
            }
        }
        this.mListItemSize[0] = getWidth();
        this.mListItemSize[1] = getHeight();
    }

    protected void cloneParams(GSYBaseVideoPlayer gSYBaseVideoPlayer, GSYBaseVideoPlayer gSYBaseVideoPlayer2) {
        gSYBaseVideoPlayer2.mHadPlay = gSYBaseVideoPlayer.mHadPlay;
        gSYBaseVideoPlayer2.mPlayTag = gSYBaseVideoPlayer.mPlayTag;
        gSYBaseVideoPlayer2.mPlayPosition = gSYBaseVideoPlayer.mPlayPosition;
        gSYBaseVideoPlayer2.mEffectFilter = gSYBaseVideoPlayer.mEffectFilter;
        gSYBaseVideoPlayer2.mFullPauseBitmap = gSYBaseVideoPlayer.mFullPauseBitmap;
        gSYBaseVideoPlayer2.mNeedShowWifiTip = gSYBaseVideoPlayer.mNeedShowWifiTip;
        gSYBaseVideoPlayer2.mShrinkImageRes = gSYBaseVideoPlayer.mShrinkImageRes;
        gSYBaseVideoPlayer2.mEnlargeImageRes = gSYBaseVideoPlayer.mEnlargeImageRes;
        gSYBaseVideoPlayer2.mRotate = gSYBaseVideoPlayer.mRotate;
        gSYBaseVideoPlayer2.mShowPauseCover = gSYBaseVideoPlayer.mShowPauseCover;
        gSYBaseVideoPlayer2.mDismissControlTime = gSYBaseVideoPlayer.mDismissControlTime;
        gSYBaseVideoPlayer2.mSeekRatio = gSYBaseVideoPlayer.mSeekRatio;
        gSYBaseVideoPlayer2.mNetChanged = gSYBaseVideoPlayer.mNetChanged;
        gSYBaseVideoPlayer2.mNetSate = gSYBaseVideoPlayer.mNetSate;
        gSYBaseVideoPlayer2.mRotateViewAuto = gSYBaseVideoPlayer.mRotateViewAuto;
        gSYBaseVideoPlayer2.mRotateWithSystem = gSYBaseVideoPlayer.mRotateWithSystem;
        gSYBaseVideoPlayer2.mBackUpPlayingBufferState = gSYBaseVideoPlayer.mBackUpPlayingBufferState;
        gSYBaseVideoPlayer2.mRenderer = gSYBaseVideoPlayer.mRenderer;
        gSYBaseVideoPlayer2.mMode = gSYBaseVideoPlayer.mMode;
        gSYBaseVideoPlayer2.mBackFromFullScreenListener = gSYBaseVideoPlayer.mBackFromFullScreenListener;
        gSYBaseVideoPlayer2.mGSYVideoProgressListener = gSYBaseVideoPlayer.mGSYVideoProgressListener;
        gSYBaseVideoPlayer2.mHadPrepared = gSYBaseVideoPlayer.mHadPrepared;
        gSYBaseVideoPlayer2.mSurfaceErrorPlay = gSYBaseVideoPlayer.mSurfaceErrorPlay;
        gSYBaseVideoPlayer2.mStartAfterPrepared = gSYBaseVideoPlayer.mStartAfterPrepared;
        gSYBaseVideoPlayer2.mPauseBeforePrepared = gSYBaseVideoPlayer.mPauseBeforePrepared;
        gSYBaseVideoPlayer2.mReleaseWhenLossAudio = gSYBaseVideoPlayer.mReleaseWhenLossAudio;
        gSYBaseVideoPlayer2.mVideoAllCallBack = gSYBaseVideoPlayer.mVideoAllCallBack;
        gSYBaseVideoPlayer2.mRotateViewAuto = gSYBaseVideoPlayer.mRotateViewAuto;
        gSYBaseVideoPlayer2.mActionBar = gSYBaseVideoPlayer.mActionBar;
        gSYBaseVideoPlayer2.mStatusBar = gSYBaseVideoPlayer.mStatusBar;
        gSYBaseVideoPlayer2.mAutoFullWithSize = gSYBaseVideoPlayer.mAutoFullWithSize;
        gSYBaseVideoPlayer2.mOverrideExtension = gSYBaseVideoPlayer.mOverrideExtension;
        gSYBaseVideoPlayer2.mNeedOrientationUtils = gSYBaseVideoPlayer.mNeedOrientationUtils;
        if (gSYBaseVideoPlayer.mSetUpLazy) {
            gSYBaseVideoPlayer2.setUpLazy(gSYBaseVideoPlayer.mOriginUrl, gSYBaseVideoPlayer.mCache, gSYBaseVideoPlayer.mCachePath, gSYBaseVideoPlayer.mMapHeadData, gSYBaseVideoPlayer.mTitle);
            gSYBaseVideoPlayer2.mUrl = gSYBaseVideoPlayer.mUrl;
        } else {
            gSYBaseVideoPlayer2.setUp(gSYBaseVideoPlayer.mOriginUrl, gSYBaseVideoPlayer.mCache, gSYBaseVideoPlayer.mCachePath, gSYBaseVideoPlayer.mMapHeadData, gSYBaseVideoPlayer.mTitle);
        }
        gSYBaseVideoPlayer2.setLooping(gSYBaseVideoPlayer.isLooping());
        gSYBaseVideoPlayer2.setIsTouchWigetFull(gSYBaseVideoPlayer.mIsTouchWigetFull);
        gSYBaseVideoPlayer2.setSpeed(gSYBaseVideoPlayer.getSpeed(), gSYBaseVideoPlayer.mSoundTouch);
        gSYBaseVideoPlayer2.setStateAndUi(gSYBaseVideoPlayer.mCurrentState);
    }

    private void pauseFullCoverLogic() {
        if (this.mCurrentState != 5 || this.mTextureView == null) {
            return;
        }
        if ((this.mFullPauseBitmap == null || this.mFullPauseBitmap.isRecycled()) && this.mShowPauseCover) {
            try {
                initCover();
            } catch (Exception e) {
                e.printStackTrace();
                this.mFullPauseBitmap = null;
            }
        }
    }

    private void pauseFullBackCoverLogic(GSYBaseVideoPlayer gSYBaseVideoPlayer) {
        if (gSYBaseVideoPlayer.mCurrentState == 5 && gSYBaseVideoPlayer.mTextureView != null && this.mShowPauseCover) {
            if (gSYBaseVideoPlayer.mFullPauseBitmap != null && !gSYBaseVideoPlayer.mFullPauseBitmap.isRecycled() && this.mShowPauseCover) {
                this.mFullPauseBitmap = gSYBaseVideoPlayer.mFullPauseBitmap;
                return;
            }
            if (this.mShowPauseCover) {
                try {
                    initCover();
                } catch (Exception e) {
                    e.printStackTrace();
                    this.mFullPauseBitmap = null;
                }
            }
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    protected void resolveFullVideoShow(Context context, final GSYBaseVideoPlayer gSYBaseVideoPlayer, final FrameLayout frameLayout) {
        OrientationUtils orientationUtils;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) gSYBaseVideoPlayer.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
        layoutParams.height = -1;
        layoutParams.width = -1;
        layoutParams.gravity = 17;
        gSYBaseVideoPlayer.setLayoutParams(layoutParams);
        gSYBaseVideoPlayer.setIfCurrentIsFullscreen(true);
        if (this.mNeedOrientationUtils) {
            OrientationUtils orientationUtils2 = new OrientationUtils((Activity) context, gSYBaseVideoPlayer, getOrientationOption());
            this.mOrientationUtils = orientationUtils2;
            orientationUtils2.setEnable(isRotateViewAuto());
            this.mOrientationUtils.setRotateWithSystem(this.mRotateWithSystem);
            this.mOrientationUtils.setOnlyRotateLand(this.mIsOnlyRotateLand);
            gSYBaseVideoPlayer.mOrientationUtils = this.mOrientationUtils;
        }
        final boolean zIsVerticalFullByVideoSize = isVerticalFullByVideoSize();
        final boolean zIsLockLandByAutoFullSize = isLockLandByAutoFullSize();
        if (isShowFullAnimation()) {
            this.mInnerHandler.postDelayed(new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer.2
                @Override // java.lang.Runnable
                public void run() {
                    if (!zIsVerticalFullByVideoSize && zIsLockLandByAutoFullSize && GSYBaseVideoPlayer.this.mOrientationUtils != null && GSYBaseVideoPlayer.this.mOrientationUtils.getIsLand() != 1) {
                        GSYBaseVideoPlayer.this.mOrientationUtils.resolveByClick();
                    }
                    gSYBaseVideoPlayer.setVisibility(0);
                    frameLayout.setVisibility(0);
                }
            }, 300L);
        } else {
            if (!zIsVerticalFullByVideoSize && zIsLockLandByAutoFullSize && (orientationUtils = this.mOrientationUtils) != null) {
                orientationUtils.resolveByClick();
            }
            gSYBaseVideoPlayer.setVisibility(0);
            frameLayout.setVisibility(0);
        }
        if (this.mVideoAllCallBack != null) {
            Debuger.printfError("onEnterFullscreen");
            this.mVideoAllCallBack.onEnterFullscreen(this.mOriginUrl, this.mTitle, gSYBaseVideoPlayer);
        }
        this.mIfCurrentIsFullscreen = true;
        checkoutState();
        checkAutoFullWithSizeAndAdaptation(gSYBaseVideoPlayer);
    }

    protected void resolveNormalVideoShow(View view, ViewGroup viewGroup, GSYVideoPlayer gSYVideoPlayer) {
        if (view != null && view.getParent() != null) {
            viewGroup.removeView((ViewGroup) view.getParent());
        }
        this.mCurrentState = getGSYVideoManager().getLastState();
        if (gSYVideoPlayer != null) {
            cloneParams(gSYVideoPlayer, this);
        }
        if (this.mCurrentState != 0 || this.mCurrentState != 6) {
            createNetWorkState();
        }
        getGSYVideoManager().setListener(getGSYVideoManager().lastListener());
        getGSYVideoManager().setLastListener(null);
        setStateAndUi(this.mCurrentState);
        addTextureView();
        this.mSaveChangeViewTIme = System.currentTimeMillis();
        if (this.mVideoAllCallBack != null) {
            Debuger.printfError("onQuitFullscreen");
            this.mVideoAllCallBack.onQuitFullscreen(this.mOriginUrl, this.mTitle, this);
        }
        this.mIfCurrentIsFullscreen = false;
        if (this.mHideKey) {
            CommonUtil.showNavKey(this.mContext, this.mSystemUiVisibility);
        }
        CommonUtil.showSupportActionBar(this.mContext, this.mActionBar, this.mStatusBar);
        if (getFullscreenButton() != null) {
            getFullscreenButton().setImageResource(getEnlargeImageRes());
        }
    }

    protected void clearFullscreenLayout() {
        int iBackToProtVideo;
        if (this.mFullAnimEnd) {
            this.mIfCurrentIsFullscreen = false;
            OrientationUtils orientationUtils = this.mOrientationUtils;
            if (orientationUtils != null) {
                iBackToProtVideo = orientationUtils.backToProtVideo();
                this.mOrientationUtils.setEnable(false);
                OrientationUtils orientationUtils2 = this.mOrientationUtils;
                if (orientationUtils2 != null) {
                    orientationUtils2.releaseListener();
                    this.mOrientationUtils = null;
                }
            } else {
                iBackToProtVideo = 0;
            }
            if (!this.mShowFullAnimation) {
                iBackToProtVideo = 0;
            }
            View viewFindViewById = getViewGroup().findViewById(getFullId());
            if (viewFindViewById != null) {
                ((GSYVideoPlayer) viewFindViewById).mIfCurrentIsFullscreen = false;
            }
            this.mInnerHandler.postDelayed(new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer.3
                @Override // java.lang.Runnable
                public void run() {
                    GSYBaseVideoPlayer.this.backToNormal();
                }
            }, iBackToProtVideo);
        }
    }

    protected void backToNormal() {
        final ViewGroup viewGroup = getViewGroup();
        final View viewFindViewById = viewGroup.findViewById(getFullId());
        if (viewFindViewById != null) {
            final GSYVideoPlayer gSYVideoPlayer = (GSYVideoPlayer) viewFindViewById;
            pauseFullBackCoverLogic(gSYVideoPlayer);
            if (this.mShowFullAnimation) {
                TransitionManager.beginDelayedTransition(viewGroup);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) gSYVideoPlayer.getLayoutParams();
                int[] iArr = this.mListItemRect;
                layoutParams.setMargins(iArr[0], iArr[1], 0, 0);
                layoutParams.width = this.mListItemSize[0];
                layoutParams.height = this.mListItemSize[1];
                layoutParams.gravity = 0;
                gSYVideoPlayer.setLayoutParams(layoutParams);
                this.mInnerHandler.postDelayed(new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer.4
                    @Override // java.lang.Runnable
                    public void run() {
                        GSYBaseVideoPlayer.this.resolveNormalVideoShow(viewFindViewById, viewGroup, gSYVideoPlayer);
                    }
                }, 400L);
                return;
            }
            resolveNormalVideoShow(viewFindViewById, viewGroup, gSYVideoPlayer);
            return;
        }
        resolveNormalVideoShow(null, viewGroup, null);
    }

    protected void checkoutState() {
        removeCallbacks(this.mCheckoutTask);
        this.mInnerHandler.postDelayed(this.mCheckoutTask, 500L);
    }

    protected boolean isVerticalVideo() {
        int currentVideoHeight = getCurrentVideoHeight();
        int currentVideoWidth = getCurrentVideoWidth();
        Debuger.printfLog("GSYVideoBase isVerticalVideo  videoHeight " + currentVideoHeight + " videoWidth " + currentVideoWidth);
        StringBuilder sb = new StringBuilder("GSYVideoBase isVerticalVideo  mRotate ");
        sb.append(this.mRotate);
        Debuger.printfLog(sb.toString());
        if (currentVideoHeight <= 0 || currentVideoWidth <= 0) {
            return false;
        }
        if (this.mRotate == 90 || this.mRotate == 270) {
            if (currentVideoWidth <= currentVideoHeight) {
                return false;
            }
        } else if (currentVideoHeight <= currentVideoWidth) {
            return false;
        }
        return true;
    }

    protected boolean isLockLandByAutoFullSize() {
        return isAutoFullWithSize() ? isVerticalVideo() : this.mLockLand;
    }

    protected void checkAutoFullSizeWhenFull() {
        OrientationUtils orientationUtils;
        if (this.mIfCurrentIsFullscreen) {
            boolean zIsVerticalFullByVideoSize = isVerticalFullByVideoSize();
            Debuger.printfLog("GSYVideoBase onPrepared isVerticalFullByVideoSize " + zIsVerticalFullByVideoSize);
            if (!zIsVerticalFullByVideoSize || (orientationUtils = this.mOrientationUtils) == null) {
                return;
            }
            orientationUtils.backToProtVideo();
            checkAutoFullWithSizeAndAdaptation(this);
        }
    }

    public boolean isVerticalFullByVideoSize() {
        return isVerticalVideo() && isAutoFullWithSize();
    }

    public void onConfigurationChanged(Activity activity, Configuration configuration, OrientationUtils orientationUtils) {
        onConfigurationChanged(activity, configuration, orientationUtils, true, true);
    }

    public void onConfigurationChanged(Activity activity, Configuration configuration, OrientationUtils orientationUtils, boolean z, boolean z2) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            if (isIfCurrentIsFullscreen()) {
                return;
            }
            startWindowFullscreen(activity, z, z2);
        } else {
            if (isIfCurrentIsFullscreen() && !isVerticalFullByVideoSize()) {
                backFromFull(activity);
            }
            if (orientationUtils != null) {
                orientationUtils.setEnable(isRotateWithSystem());
            }
        }
    }

    public GSYBaseVideoPlayer startWindowFullscreen(final Context context, boolean z, boolean z2) {
        GSYBaseVideoPlayer gSYBaseVideoPlayer;
        this.mSystemUiVisibility = ((Activity) context).getWindow().getDecorView().getSystemUiVisibility();
        CommonUtil.hideSupportActionBar(context, z, z2);
        if (this.mHideKey) {
            CommonUtil.hideNavKey(context);
        }
        this.mActionBar = z;
        this.mStatusBar = z2;
        this.mListItemRect = new int[2];
        this.mListItemSize = new int[2];
        final ViewGroup viewGroup = getViewGroup();
        removeVideo(viewGroup, getFullId());
        pauseFullCoverLogic();
        if (this.mTextureViewContainer.getChildCount() > 0) {
            this.mTextureViewContainer.removeAllViews();
        }
        saveLocationStatus(context, z2, z);
        cancelProgressTimer();
        try {
            try {
                getClass().getConstructor(Context.class, Boolean.class);
                gSYBaseVideoPlayer = (GSYBaseVideoPlayer) getClass().getConstructor(Context.class, Boolean.class).newInstance(this.mContext, true);
            } catch (Exception unused) {
                gSYBaseVideoPlayer = (GSYBaseVideoPlayer) getClass().getConstructor(Context.class).newInstance(this.mContext);
            }
            gSYBaseVideoPlayer.setId(getFullId());
            gSYBaseVideoPlayer.setIfCurrentIsFullscreen(true);
            gSYBaseVideoPlayer.setVideoAllCallBack(this.mVideoAllCallBack);
            cloneParams(this, gSYBaseVideoPlayer);
            if (gSYBaseVideoPlayer.getFullscreenButton() != null) {
                gSYBaseVideoPlayer.getFullscreenButton().setImageResource(getShrinkImageRes());
                gSYBaseVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (GSYBaseVideoPlayer.this.mBackFromFullScreenListener == null) {
                            GSYBaseVideoPlayer.this.clearFullscreenLayout();
                        } else {
                            GSYBaseVideoPlayer.this.mBackFromFullScreenListener.onClick(view);
                        }
                    }
                });
            }
            if (gSYBaseVideoPlayer.getBackButton() != null) {
                gSYBaseVideoPlayer.getBackButton().setVisibility(0);
                gSYBaseVideoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (GSYBaseVideoPlayer.this.mBackFromFullScreenListener == null) {
                            GSYBaseVideoPlayer.this.clearFullscreenLayout();
                        } else {
                            GSYBaseVideoPlayer.this.mBackFromFullScreenListener.onClick(view);
                        }
                    }
                });
            }
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            final FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setBackgroundColor(-16777216);
            if (this.mShowFullAnimation) {
                this.mFullAnimEnd = false;
                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(getWidth(), getHeight());
                int[] iArr = this.mListItemRect;
                layoutParams2.setMargins(iArr[0], iArr[1], 0, 0);
                frameLayout.addView(gSYBaseVideoPlayer, layoutParams2);
                viewGroup.addView(frameLayout, layoutParams);
                final GSYBaseVideoPlayer gSYBaseVideoPlayer2 = gSYBaseVideoPlayer;
                this.mInnerHandler.postDelayed(new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer.8
                    @Override // java.lang.Runnable
                    public void run() {
                        TransitionManager.beginDelayedTransition(viewGroup);
                        GSYBaseVideoPlayer.this.resolveFullVideoShow(context, gSYBaseVideoPlayer2, frameLayout);
                        GSYBaseVideoPlayer.this.mFullAnimEnd = true;
                    }
                }, 300L);
            } else {
                frameLayout.addView(gSYBaseVideoPlayer, new FrameLayout.LayoutParams(getWidth(), getHeight()));
                viewGroup.addView(frameLayout, layoutParams);
                gSYBaseVideoPlayer.setVisibility(4);
                frameLayout.setVisibility(4);
                resolveFullVideoShow(context, gSYBaseVideoPlayer, frameLayout);
            }
            gSYBaseVideoPlayer.addTextureView();
            gSYBaseVideoPlayer.startProgressTimer();
            getGSYVideoManager().setLastListener(this);
            getGSYVideoManager().setListener(gSYBaseVideoPlayer);
            checkoutState();
            return gSYBaseVideoPlayer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public GSYBaseVideoPlayer showSmallVideo(Point point, boolean z, boolean z2) {
        ViewGroup viewGroup = getViewGroup();
        removeVideo(viewGroup, getSmallId());
        if (this.mTextureViewContainer.getChildCount() > 0) {
            this.mTextureViewContainer.removeAllViews();
        }
        try {
            GSYBaseVideoPlayer gSYBaseVideoPlayer = (GSYBaseVideoPlayer) getClass().getConstructor(Context.class).newInstance(getActivityContext());
            gSYBaseVideoPlayer.setId(getSmallId());
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            FrameLayout frameLayout = new FrameLayout(this.mContext);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(point.x, point.y);
            int screenWidth = CommonUtil.getScreenWidth(this.mContext) - point.x;
            int screenHeight = CommonUtil.getScreenHeight(this.mContext) - point.y;
            if (z) {
                screenHeight -= CommonUtil.getActionBarHeight((Activity) this.mContext);
            }
            if (z2) {
                screenHeight -= CommonUtil.getStatusBarHeight(this.mContext);
            }
            layoutParams2.setMargins(screenWidth, screenHeight, 0, 0);
            frameLayout.addView(gSYBaseVideoPlayer, layoutParams2);
            viewGroup.addView(frameLayout, layoutParams);
            cloneParams(this, gSYBaseVideoPlayer);
            gSYBaseVideoPlayer.setIsTouchWiget(false);
            gSYBaseVideoPlayer.addTextureView();
            gSYBaseVideoPlayer.onClickUiToggle(null);
            gSYBaseVideoPlayer.setVideoAllCallBack(this.mVideoAllCallBack);
            gSYBaseVideoPlayer.setSmallVideoTextureView(new SmallVideoTouch(gSYBaseVideoPlayer, screenWidth, screenHeight));
            getGSYVideoManager().setLastListener(this);
            getGSYVideoManager().setListener(gSYBaseVideoPlayer);
            if (this.mVideoAllCallBack != null) {
                Debuger.printfError("onEnterSmallWidget");
                this.mVideoAllCallBack.onEnterSmallWidget(this.mOriginUrl, this.mTitle, gSYBaseVideoPlayer);
            }
            return gSYBaseVideoPlayer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void hideSmallVideo() {
        ViewGroup viewGroup = getViewGroup();
        GSYVideoPlayer gSYVideoPlayer = (GSYVideoPlayer) viewGroup.findViewById(getSmallId());
        removeVideo(viewGroup, getSmallId());
        this.mCurrentState = getGSYVideoManager().getLastState();
        if (gSYVideoPlayer != null) {
            cloneParams(gSYVideoPlayer, this);
        }
        getGSYVideoManager().setListener(getGSYVideoManager().lastListener());
        getGSYVideoManager().setLastListener(null);
        setStateAndUi(this.mCurrentState);
        addTextureView();
        this.mSaveChangeViewTIme = System.currentTimeMillis();
        if (this.mVideoAllCallBack != null) {
            Debuger.printfLog("onQuitSmallWidget");
            this.mVideoAllCallBack.onQuitSmallWidget(this.mOriginUrl, this.mTitle, this);
        }
    }

    public boolean isShowFullAnimation() {
        return this.mShowFullAnimation;
    }

    public void setShowFullAnimation(boolean z) {
        this.mShowFullAnimation = z;
    }

    public boolean isRotateViewAuto() {
        if (this.mAutoFullWithSize) {
            return false;
        }
        return this.mRotateViewAuto;
    }

    public void setRotateViewAuto(boolean z) {
        this.mRotateViewAuto = z;
        OrientationUtils orientationUtils = this.mOrientationUtils;
        if (orientationUtils != null) {
            orientationUtils.setEnable(z);
        }
    }

    public boolean isLockLand() {
        return this.mLockLand;
    }

    public void setLockLand(boolean z) {
        this.mLockLand = z;
    }

    public boolean isRotateWithSystem() {
        return this.mRotateWithSystem;
    }

    public void setRotateWithSystem(boolean z) {
        this.mRotateWithSystem = z;
        OrientationUtils orientationUtils = this.mOrientationUtils;
        if (orientationUtils != null) {
            orientationUtils.setRotateWithSystem(z);
        }
    }

    public GSYVideoPlayer getFullWindowPlayer() {
        View viewFindViewById;
        Activity activityScanForActivity = CommonUtil.scanForActivity(getContext());
        if (activityScanForActivity == null || (viewFindViewById = ((ViewGroup) activityScanForActivity.findViewById(android.R.id.content)).findViewById(getFullId())) == null) {
            return null;
        }
        return (GSYVideoPlayer) viewFindViewById;
    }

    public GSYVideoPlayer getSmallWindowPlayer() {
        View viewFindViewById = ((ViewGroup) CommonUtil.scanForActivity(getContext()).findViewById(android.R.id.content)).findViewById(getSmallId());
        if (viewFindViewById != null) {
            return (GSYVideoPlayer) viewFindViewById;
        }
        return null;
    }

    public GSYBaseVideoPlayer getCurrentPlayer() {
        if (getFullWindowPlayer() != null) {
            return getFullWindowPlayer();
        }
        return getSmallWindowPlayer() != null ? getSmallWindowPlayer() : this;
    }

    public void setBackFromFullScreenListener(View.OnClickListener onClickListener) {
        this.mBackFromFullScreenListener = onClickListener;
    }

    public void setFullHideActionBar(boolean z) {
        this.mActionBar = z;
    }

    public void setFullHideStatusBar(boolean z) {
        this.mStatusBar = z;
    }

    public boolean isFullHideActionBar() {
        return this.mActionBar;
    }

    public boolean isFullHideStatusBar() {
        return this.mStatusBar;
    }

    public int getSaveBeforeFullSystemUiVisibility() {
        return this.mSystemUiVisibility;
    }

    public void setSaveBeforeFullSystemUiVisibility(int i) {
        this.mSystemUiVisibility = i;
    }

    public boolean isAutoFullWithSize() {
        return this.mAutoFullWithSize;
    }

    public void setAutoFullWithSize(boolean z) {
        this.mAutoFullWithSize = z;
    }

    public boolean isNeedAutoAdaptation() {
        return this.isNeedAutoAdaptation;
    }

    public void setNeedOrientationUtils(boolean z) {
        this.mNeedOrientationUtils = z;
    }

    public boolean isNeedOrientationUtils() {
        return this.mNeedOrientationUtils;
    }

    public void setNeedAutoAdaptation(boolean z) {
        this.isNeedAutoAdaptation = z;
    }

    public boolean isOnlyRotateLand() {
        return this.mIsOnlyRotateLand;
    }

    public void setOnlyRotateLand(boolean z) {
        this.mIsOnlyRotateLand = z;
        OrientationUtils orientationUtils = this.mOrientationUtils;
        if (orientationUtils != null) {
            orientationUtils.setOnlyRotateLand(z);
        }
    }

    protected void checkAutoFullWithSizeAndAdaptation(final GSYBaseVideoPlayer gSYBaseVideoPlayer) {
        if (gSYBaseVideoPlayer != null && this.isNeedAutoAdaptation && isAutoFullWithSize() && isVerticalVideo() && isFullHideStatusBar()) {
            this.mInnerHandler.postDelayed(new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer.9
                @Override // java.lang.Runnable
                public void run() {
                    gSYBaseVideoPlayer.getCurrentPlayer().autoAdaptation();
                }
            }, 100L);
        }
    }

    protected void autoAdaptation() {
        Context context = getContext();
        if (isVerticalVideo()) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            if (iArr[1] == 0) {
                setPadding(0, CommonUtil.getStatusBarHeight(context), 0, 0);
                Debuger.printfLog("竖屏，系统未将布局下移");
            } else {
                Debuger.printfLog("竖屏，系统将布局下移；y:" + iArr[1]);
            }
        }
    }
}
