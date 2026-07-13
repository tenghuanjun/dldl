package com.shuyu.gsyvideoplayer.video.base;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.shuyu.gsyvideoplayer.R;
import com.shuyu.gsyvideoplayer.listener.GSYStateUiListener;
import com.shuyu.gsyvideoplayer.listener.GSYVideoProgressListener;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import java.io.File;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class GSYVideoControlView extends GSYVideoView implements View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {
    Runnable dismissControlTask;
    protected GestureDetector gestureDetector;
    protected boolean isShowDragProgressTextOnSeekBar;
    protected ImageView mBackButton;
    protected ViewGroup mBottomContainer;
    protected ProgressBar mBottomProgressBar;
    protected boolean mBrightness;
    protected float mBrightnessData;
    protected boolean mChangePosition;
    protected boolean mChangeVolume;
    protected TextView mCurrentTimeTextView;
    protected int mDismissControlTime;
    protected long mDownPosition;
    protected float mDownX;
    protected float mDownY;
    protected int mEnlargeImageRes;
    protected boolean mFirstTouch;
    protected ImageView mFullscreenButton;
    protected GSYVideoProgressListener mGSYVideoProgressListener;
    protected int mGestureDownVolume;
    protected GSYStateUiListener mGsyStateUiListener;
    protected boolean mHadSeekTouch;
    protected boolean mHideKey;
    protected boolean mIsTouchWiget;
    protected boolean mIsTouchWigetFull;
    protected View mLoadingProgressBar;
    protected LockClickListener mLockClickListener;
    protected boolean mLockCurScreen;
    protected ImageView mLockScreen;
    protected float mMoveY;
    protected boolean mNeedLockFull;
    protected boolean mNeedShowWifiTip;
    protected boolean mPostDismiss;
    protected boolean mPostProgress;
    protected SeekBar mProgressBar;
    protected int mSeekEndOffset;
    protected float mSeekRatio;
    protected long mSeekTimePosition;
    protected boolean mSetUpLazy;
    protected boolean mShowVKey;
    protected int mShrinkImageRes;
    protected View mStartButton;
    protected boolean mSurfaceErrorPlay;
    protected int mThreshold;
    protected View mThumbImageView;
    protected RelativeLayout mThumbImageViewLayout;
    protected boolean mThumbPlay;
    protected TextView mTitleTextView;
    protected ViewGroup mTopContainer;
    protected TextView mTotalTimeTextView;
    protected boolean mTouchingProgressBar;
    Runnable progressTask;

    protected abstract void changeUiToCompleteShow();

    protected abstract void changeUiToError();

    protected abstract void changeUiToNormal();

    protected abstract void changeUiToPauseShow();

    protected abstract void changeUiToPlayingBufferingShow();

    protected abstract void changeUiToPlayingShow();

    protected abstract void changeUiToPreparingShow();

    protected abstract void dismissBrightnessDialog();

    protected abstract void dismissProgressDialog();

    protected abstract void dismissVolumeDialog();

    protected abstract void hideAllWidget();

    protected abstract void onClickUiToggle(MotionEvent motionEvent);

    protected abstract void showBrightnessDialog(float f);

    protected abstract void showProgressDialog(float f, String str, long j, String str2, long j2);

    protected abstract void showVolumeDialog(float f, int i);

    protected abstract void showWifiDialog();

    protected void touchLongPress(MotionEvent motionEvent) {
    }

    public GSYVideoControlView(Context context) {
        super(context);
        this.mThreshold = 80;
        this.mShrinkImageRes = -1;
        this.mEnlargeImageRes = -1;
        this.mDismissControlTime = 2500;
        this.mBrightnessData = -1.0f;
        this.mSeekRatio = 1.0f;
        this.mTouchingProgressBar = false;
        this.mChangeVolume = false;
        this.mChangePosition = false;
        this.mShowVKey = false;
        this.mBrightness = false;
        this.mFirstTouch = false;
        this.mHideKey = true;
        this.mNeedShowWifiTip = true;
        this.mIsTouchWiget = true;
        this.mIsTouchWigetFull = true;
        this.mSurfaceErrorPlay = true;
        this.mSetUpLazy = false;
        this.mHadSeekTouch = false;
        this.mPostProgress = false;
        this.mPostDismiss = false;
        this.isShowDragProgressTextOnSeekBar = false;
        this.gestureDetector = new GestureDetector(getContext().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                GSYVideoControlView.this.touchDoubleUp(motionEvent);
                return super.onDoubleTap(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (!GSYVideoControlView.this.mChangePosition && !GSYVideoControlView.this.mChangeVolume && !GSYVideoControlView.this.mBrightness) {
                    GSYVideoControlView.this.onClickUiToggle(motionEvent);
                }
                return super.onSingleTapConfirmed(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                super.onLongPress(motionEvent);
                GSYVideoControlView.this.touchLongPress(motionEvent);
            }
        });
        this.progressTask = new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.4
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoControlView.this.mCurrentState == 2 || GSYVideoControlView.this.mCurrentState == 5) {
                    GSYVideoControlView.this.setTextAndProgress(0);
                }
                if (GSYVideoControlView.this.mPostProgress) {
                    GSYVideoControlView.this.postDelayed(this, 1000L);
                }
            }
        };
        this.dismissControlTask = new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.5
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoControlView.this.mCurrentState == 0 || GSYVideoControlView.this.mCurrentState == 7 || GSYVideoControlView.this.mCurrentState == 6) {
                    return;
                }
                if (GSYVideoControlView.this.getActivityContext() != null) {
                    GSYVideoControlView.this.hideAllWidget();
                    GSYVideoControlView gSYVideoControlView = GSYVideoControlView.this;
                    gSYVideoControlView.setViewShowState(gSYVideoControlView.mLockScreen, 8);
                    if (GSYVideoControlView.this.mHideKey && GSYVideoControlView.this.mIfCurrentIsFullscreen && GSYVideoControlView.this.mShowVKey) {
                        CommonUtil.hideNavKey(GSYVideoControlView.this.mContext);
                    }
                }
                if (GSYVideoControlView.this.mPostDismiss) {
                    GSYVideoControlView.this.postDelayed(this, r0.mDismissControlTime);
                }
            }
        };
    }

    public GSYVideoControlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mThreshold = 80;
        this.mShrinkImageRes = -1;
        this.mEnlargeImageRes = -1;
        this.mDismissControlTime = 2500;
        this.mBrightnessData = -1.0f;
        this.mSeekRatio = 1.0f;
        this.mTouchingProgressBar = false;
        this.mChangeVolume = false;
        this.mChangePosition = false;
        this.mShowVKey = false;
        this.mBrightness = false;
        this.mFirstTouch = false;
        this.mHideKey = true;
        this.mNeedShowWifiTip = true;
        this.mIsTouchWiget = true;
        this.mIsTouchWigetFull = true;
        this.mSurfaceErrorPlay = true;
        this.mSetUpLazy = false;
        this.mHadSeekTouch = false;
        this.mPostProgress = false;
        this.mPostDismiss = false;
        this.isShowDragProgressTextOnSeekBar = false;
        this.gestureDetector = new GestureDetector(getContext().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                GSYVideoControlView.this.touchDoubleUp(motionEvent);
                return super.onDoubleTap(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (!GSYVideoControlView.this.mChangePosition && !GSYVideoControlView.this.mChangeVolume && !GSYVideoControlView.this.mBrightness) {
                    GSYVideoControlView.this.onClickUiToggle(motionEvent);
                }
                return super.onSingleTapConfirmed(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                super.onLongPress(motionEvent);
                GSYVideoControlView.this.touchLongPress(motionEvent);
            }
        });
        this.progressTask = new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.4
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoControlView.this.mCurrentState == 2 || GSYVideoControlView.this.mCurrentState == 5) {
                    GSYVideoControlView.this.setTextAndProgress(0);
                }
                if (GSYVideoControlView.this.mPostProgress) {
                    GSYVideoControlView.this.postDelayed(this, 1000L);
                }
            }
        };
        this.dismissControlTask = new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.5
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoControlView.this.mCurrentState == 0 || GSYVideoControlView.this.mCurrentState == 7 || GSYVideoControlView.this.mCurrentState == 6) {
                    return;
                }
                if (GSYVideoControlView.this.getActivityContext() != null) {
                    GSYVideoControlView.this.hideAllWidget();
                    GSYVideoControlView gSYVideoControlView = GSYVideoControlView.this;
                    gSYVideoControlView.setViewShowState(gSYVideoControlView.mLockScreen, 8);
                    if (GSYVideoControlView.this.mHideKey && GSYVideoControlView.this.mIfCurrentIsFullscreen && GSYVideoControlView.this.mShowVKey) {
                        CommonUtil.hideNavKey(GSYVideoControlView.this.mContext);
                    }
                }
                if (GSYVideoControlView.this.mPostDismiss) {
                    GSYVideoControlView.this.postDelayed(this, r0.mDismissControlTime);
                }
            }
        };
    }

    public GSYVideoControlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mThreshold = 80;
        this.mShrinkImageRes = -1;
        this.mEnlargeImageRes = -1;
        this.mDismissControlTime = 2500;
        this.mBrightnessData = -1.0f;
        this.mSeekRatio = 1.0f;
        this.mTouchingProgressBar = false;
        this.mChangeVolume = false;
        this.mChangePosition = false;
        this.mShowVKey = false;
        this.mBrightness = false;
        this.mFirstTouch = false;
        this.mHideKey = true;
        this.mNeedShowWifiTip = true;
        this.mIsTouchWiget = true;
        this.mIsTouchWigetFull = true;
        this.mSurfaceErrorPlay = true;
        this.mSetUpLazy = false;
        this.mHadSeekTouch = false;
        this.mPostProgress = false;
        this.mPostDismiss = false;
        this.isShowDragProgressTextOnSeekBar = false;
        this.gestureDetector = new GestureDetector(getContext().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                GSYVideoControlView.this.touchDoubleUp(motionEvent);
                return super.onDoubleTap(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (!GSYVideoControlView.this.mChangePosition && !GSYVideoControlView.this.mChangeVolume && !GSYVideoControlView.this.mBrightness) {
                    GSYVideoControlView.this.onClickUiToggle(motionEvent);
                }
                return super.onSingleTapConfirmed(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                super.onLongPress(motionEvent);
                GSYVideoControlView.this.touchLongPress(motionEvent);
            }
        });
        this.progressTask = new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.4
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoControlView.this.mCurrentState == 2 || GSYVideoControlView.this.mCurrentState == 5) {
                    GSYVideoControlView.this.setTextAndProgress(0);
                }
                if (GSYVideoControlView.this.mPostProgress) {
                    GSYVideoControlView.this.postDelayed(this, 1000L);
                }
            }
        };
        this.dismissControlTask = new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.5
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoControlView.this.mCurrentState == 0 || GSYVideoControlView.this.mCurrentState == 7 || GSYVideoControlView.this.mCurrentState == 6) {
                    return;
                }
                if (GSYVideoControlView.this.getActivityContext() != null) {
                    GSYVideoControlView.this.hideAllWidget();
                    GSYVideoControlView gSYVideoControlView = GSYVideoControlView.this;
                    gSYVideoControlView.setViewShowState(gSYVideoControlView.mLockScreen, 8);
                    if (GSYVideoControlView.this.mHideKey && GSYVideoControlView.this.mIfCurrentIsFullscreen && GSYVideoControlView.this.mShowVKey) {
                        CommonUtil.hideNavKey(GSYVideoControlView.this.mContext);
                    }
                }
                if (GSYVideoControlView.this.mPostDismiss) {
                    GSYVideoControlView.this.postDelayed(this, r0.mDismissControlTime);
                }
            }
        };
    }

    public GSYVideoControlView(Context context, Boolean bool) {
        super(context, bool);
        this.mThreshold = 80;
        this.mShrinkImageRes = -1;
        this.mEnlargeImageRes = -1;
        this.mDismissControlTime = 2500;
        this.mBrightnessData = -1.0f;
        this.mSeekRatio = 1.0f;
        this.mTouchingProgressBar = false;
        this.mChangeVolume = false;
        this.mChangePosition = false;
        this.mShowVKey = false;
        this.mBrightness = false;
        this.mFirstTouch = false;
        this.mHideKey = true;
        this.mNeedShowWifiTip = true;
        this.mIsTouchWiget = true;
        this.mIsTouchWigetFull = true;
        this.mSurfaceErrorPlay = true;
        this.mSetUpLazy = false;
        this.mHadSeekTouch = false;
        this.mPostProgress = false;
        this.mPostDismiss = false;
        this.isShowDragProgressTextOnSeekBar = false;
        this.gestureDetector = new GestureDetector(getContext().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                GSYVideoControlView.this.touchDoubleUp(motionEvent);
                return super.onDoubleTap(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (!GSYVideoControlView.this.mChangePosition && !GSYVideoControlView.this.mChangeVolume && !GSYVideoControlView.this.mBrightness) {
                    GSYVideoControlView.this.onClickUiToggle(motionEvent);
                }
                return super.onSingleTapConfirmed(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                super.onLongPress(motionEvent);
                GSYVideoControlView.this.touchLongPress(motionEvent);
            }
        });
        this.progressTask = new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.4
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoControlView.this.mCurrentState == 2 || GSYVideoControlView.this.mCurrentState == 5) {
                    GSYVideoControlView.this.setTextAndProgress(0);
                }
                if (GSYVideoControlView.this.mPostProgress) {
                    GSYVideoControlView.this.postDelayed(this, 1000L);
                }
            }
        };
        this.dismissControlTask = new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.5
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoControlView.this.mCurrentState == 0 || GSYVideoControlView.this.mCurrentState == 7 || GSYVideoControlView.this.mCurrentState == 6) {
                    return;
                }
                if (GSYVideoControlView.this.getActivityContext() != null) {
                    GSYVideoControlView.this.hideAllWidget();
                    GSYVideoControlView gSYVideoControlView = GSYVideoControlView.this;
                    gSYVideoControlView.setViewShowState(gSYVideoControlView.mLockScreen, 8);
                    if (GSYVideoControlView.this.mHideKey && GSYVideoControlView.this.mIfCurrentIsFullscreen && GSYVideoControlView.this.mShowVKey) {
                        CommonUtil.hideNavKey(GSYVideoControlView.this.mContext);
                    }
                }
                if (GSYVideoControlView.this.mPostDismiss) {
                    GSYVideoControlView.this.postDelayed(this, r0.mDismissControlTime);
                }
            }
        };
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected void init(Context context) {
        RelativeLayout relativeLayout;
        super.init(context);
        this.mStartButton = findViewById(R.id.start);
        this.mTitleTextView = (TextView) findViewById(R.id.title);
        this.mBackButton = (ImageView) findViewById(R.id.back);
        this.mFullscreenButton = (ImageView) findViewById(R.id.fullscreen);
        this.mProgressBar = (SeekBar) findViewById(R.id.progress);
        this.mCurrentTimeTextView = (TextView) findViewById(R.id.current);
        this.mTotalTimeTextView = (TextView) findViewById(R.id.total);
        this.mBottomContainer = (ViewGroup) findViewById(R.id.layout_bottom);
        this.mTopContainer = (ViewGroup) findViewById(R.id.layout_top);
        this.mBottomProgressBar = (ProgressBar) findViewById(R.id.bottom_progressbar);
        this.mThumbImageViewLayout = (RelativeLayout) findViewById(R.id.thumb);
        this.mLockScreen = (ImageView) findViewById(R.id.lock_screen);
        this.mLoadingProgressBar = findViewById(R.id.loading);
        if (isInEditMode()) {
            return;
        }
        View view = this.mStartButton;
        if (view != null) {
            view.setOnClickListener(this);
        }
        ImageView imageView = this.mFullscreenButton;
        if (imageView != null) {
            imageView.setOnClickListener(this);
            this.mFullscreenButton.setOnTouchListener(this);
        }
        SeekBar seekBar = this.mProgressBar;
        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(this);
        }
        ViewGroup viewGroup = this.mBottomContainer;
        if (viewGroup != null) {
            viewGroup.setOnClickListener(this);
        }
        if (this.mTextureViewContainer != null) {
            this.mTextureViewContainer.setOnClickListener(this);
            this.mTextureViewContainer.setOnTouchListener(this);
        }
        SeekBar seekBar2 = this.mProgressBar;
        if (seekBar2 != null) {
            seekBar2.setOnTouchListener(this);
        }
        RelativeLayout relativeLayout2 = this.mThumbImageViewLayout;
        if (relativeLayout2 != null) {
            relativeLayout2.setVisibility(8);
            this.mThumbImageViewLayout.setOnClickListener(this);
        }
        if (this.mThumbImageView != null && !this.mIfCurrentIsFullscreen && (relativeLayout = this.mThumbImageViewLayout) != null) {
            relativeLayout.removeAllViews();
            resolveThumbImage(this.mThumbImageView);
        }
        ImageView imageView2 = this.mBackButton;
        if (imageView2 != null) {
            imageView2.setOnClickListener(this);
        }
        ImageView imageView3 = this.mLockScreen;
        if (imageView3 != null) {
            imageView3.setVisibility(8);
            this.mLockScreen.setOnClickListener(new View.OnClickListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (GSYVideoControlView.this.mCurrentState == 6 || GSYVideoControlView.this.mCurrentState == 7) {
                        return;
                    }
                    GSYVideoControlView.this.lockTouchLogic();
                    if (GSYVideoControlView.this.mLockClickListener != null) {
                        GSYVideoControlView.this.mLockClickListener.onClick(view2, GSYVideoControlView.this.mLockCurScreen);
                    }
                }
            });
        }
        if (getActivityContext() != null) {
            this.mSeekEndOffset = CommonUtil.dip2px(getActivityContext(), 50.0f);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Debuger.printfLog(hashCode() + "------------------------------ dismiss onDetachedFromWindow");
        cancelProgressTimer();
        cancelDismissControlViewTimer();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView, com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onAutoCompletion() {
        super.onAutoCompletion();
        if (this.mLockCurScreen) {
            lockTouchLogic();
            this.mLockScreen.setVisibility(8);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView, com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onError(int i, int i2) {
        super.onError(i, i2);
        if (this.mLockCurScreen) {
            lockTouchLogic();
            this.mLockScreen.setVisibility(8);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected void setStateAndUi(int i) {
        TextView textView;
        this.mCurrentState = i;
        if ((i == 0 && isCurrentMediaListener()) || i == 6 || i == 7) {
            this.mHadPrepared = false;
        }
        int i2 = this.mCurrentState;
        if (i2 == 0) {
            if (isCurrentMediaListener()) {
                Debuger.printfLog(hashCode() + "------------------------------ dismiss CURRENT_STATE_NORMAL");
                cancelProgressTimer();
                getGSYVideoManager().releaseMediaPlayer();
                releasePauseCover();
                this.mBufferPoint = 0;
                this.mSaveChangeViewTIme = 0L;
                if (this.mAudioManager != null) {
                    this.mAudioManager.abandonAudioFocus(this.onAudioFocusChangeListener);
                }
            }
            releaseNetWorkState();
        } else if (i2 == 1) {
            resetProgressAndTime();
        } else if (i2 != 2) {
            if (i2 == 5) {
                Debuger.printfLog(hashCode() + "------------------------------ CURRENT_STATE_PAUSE");
                startProgressTimer();
            } else if (i2 != 6) {
                if (i2 == 7 && isCurrentMediaListener()) {
                    getGSYVideoManager().releaseMediaPlayer();
                }
            } else {
                Debuger.printfLog(hashCode() + "------------------------------ dismiss CURRENT_STATE_AUTO_COMPLETE");
                cancelProgressTimer();
                SeekBar seekBar = this.mProgressBar;
                if (seekBar != null) {
                    seekBar.setProgress(100);
                }
                TextView textView2 = this.mCurrentTimeTextView;
                if (textView2 != null && (textView = this.mTotalTimeTextView) != null) {
                    textView2.setText(textView.getText());
                }
                ProgressBar progressBar = this.mBottomProgressBar;
                if (progressBar != null) {
                    progressBar.setProgress(100);
                }
            }
        } else if (isCurrentMediaListener()) {
            Debuger.printfLog(hashCode() + "------------------------------ CURRENT_STATE_PLAYING");
            startProgressTimer();
        }
        resolveUIState(i);
        GSYStateUiListener gSYStateUiListener = this.mGsyStateUiListener;
        if (gSYStateUiListener != null) {
            gSYStateUiListener.onStateChanged(i);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYTextureRenderView
    protected void setSmallVideoTextureView(View.OnTouchListener onTouchListener) {
        super.setSmallVideoTextureView(onTouchListener);
        RelativeLayout relativeLayout = this.mThumbImageViewLayout;
        if (relativeLayout != null) {
            relativeLayout.setOnTouchListener(onTouchListener);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (this.mHideKey && this.mIfCurrentIsFullscreen) {
            CommonUtil.hideNavKey(this.mContext);
        }
        if (id == R.id.start) {
            clickStartIcon();
            return;
        }
        if (id == R.id.surface_container && this.mCurrentState == 7) {
            if (!this.mSurfaceErrorPlay) {
                onClickUiToggle(null);
                return;
            }
            if (this.mVideoAllCallBack != null) {
                Debuger.printfLog("onClickStartError");
                this.mVideoAllCallBack.onClickStartError(this.mOriginUrl, this.mTitle, this);
            }
            prepareVideo();
            return;
        }
        if (id == R.id.thumb) {
            if (this.mThumbPlay) {
                if (TextUtils.isEmpty(this.mUrl)) {
                    Debuger.printfError("********" + getResources().getString(R.string.no_url));
                    return;
                } else {
                    if (this.mCurrentState == 0) {
                        if (isShowNetConfirm()) {
                            showWifiDialog();
                            return;
                        } else {
                            startPlayLogic();
                            return;
                        }
                    }
                    if (this.mCurrentState == 6) {
                        onClickUiToggle(null);
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (id == R.id.surface_container) {
            if (this.mVideoAllCallBack != null && isCurrentMediaListener()) {
                if (this.mIfCurrentIsFullscreen) {
                    Debuger.printfLog("onClickBlankFullscreen");
                    this.mVideoAllCallBack.onClickBlankFullscreen(this.mOriginUrl, this.mTitle, this);
                } else {
                    Debuger.printfLog("onClickBlank");
                    this.mVideoAllCallBack.onClickBlank(this.mOriginUrl, this.mTitle, this);
                }
            }
            startDismissControlViewTimer();
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (this.mIfCurrentIsFullscreen && this.mLockCurScreen && this.mNeedLockFull) {
            onClickUiToggle(motionEvent);
            startDismissControlViewTimer();
            return true;
        }
        if (id == R.id.fullscreen) {
            return false;
        }
        if (id == R.id.surface_container) {
            int action = motionEvent.getAction();
            if (action == 0) {
                touchSurfaceDown(x, y);
            } else if (action == 1) {
                startDismissControlViewTimer();
                touchSurfaceUp();
                Debuger.printfLog(hashCode() + "------------------------------ surface_container ACTION_UP");
                startProgressTimer();
                if (this.mHideKey && this.mShowVKey) {
                    return true;
                }
            } else if (action == 2) {
                float f = x - this.mDownX;
                float f2 = y - this.mDownY;
                float fAbs = Math.abs(f);
                float fAbs2 = Math.abs(f2);
                if (((this.mIfCurrentIsFullscreen && this.mIsTouchWigetFull) || (this.mIsTouchWiget && !this.mIfCurrentIsFullscreen)) && !this.mChangePosition && !this.mChangeVolume && !this.mBrightness) {
                    touchSurfaceMoveFullLogic(fAbs, fAbs2);
                }
                touchSurfaceMove(f, f2, y);
            }
            this.gestureDetector.onTouchEvent(motionEvent);
        } else if (id == R.id.progress) {
            int action2 = motionEvent.getAction();
            if (action2 == 0) {
                cancelDismissControlViewTimer();
            } else if (action2 == 1) {
                startDismissControlViewTimer();
                Debuger.printfLog(hashCode() + "------------------------------ progress ACTION_UP");
                startProgressTimer();
                for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
                    parent.requestDisallowInterceptTouchEvent(false);
                }
                this.mBrightnessData = -1.0f;
            } else if (action2 == 2) {
            }
            cancelProgressTimer();
            for (ViewParent parent2 = getParent(); parent2 != null; parent2 = parent2.getParent()) {
                parent2.requestDisallowInterceptTouchEvent(true);
            }
        }
        return false;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    public boolean setUp(String str, boolean z, String str2) {
        return setUp(str, z, null, str2);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    public boolean setUp(String str, boolean z, File file, String str2) {
        TextView textView;
        if (!super.setUp(str, z, file, str2)) {
            return false;
        }
        if (str2 != null && (textView = this.mTitleTextView) != null) {
            textView.setText(str2);
        }
        if (this.mIfCurrentIsFullscreen) {
            ImageView imageView = this.mFullscreenButton;
            if (imageView == null) {
                return true;
            }
            imageView.setImageResource(getShrinkImageRes());
            return true;
        }
        ImageView imageView2 = this.mFullscreenButton;
        if (imageView2 == null) {
            return true;
        }
        imageView2.setImageResource(getEnlargeImageRes());
        return true;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        showDragProgressTextOnSeekBar(z, i);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        this.mHadSeekTouch = true;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (this.mVideoAllCallBack != null && isCurrentMediaListener()) {
            if (isIfCurrentIsFullscreen()) {
                Debuger.printfLog("onClickSeekbarFullscreen");
                this.mVideoAllCallBack.onClickSeekbarFullscreen(this.mOriginUrl, this.mTitle, this);
            } else {
                Debuger.printfLog("onClickSeekbar");
                this.mVideoAllCallBack.onClickSeekbar(this.mOriginUrl, this.mTitle, this);
            }
        }
        if (getGSYVideoManager() != null && this.mHadPlay) {
            try {
                getGSYVideoManager().seekTo((((long) seekBar.getProgress()) * getDuration()) / 100);
            } catch (Exception e) {
                Debuger.printfWarning(e.toString());
            }
        }
        this.mHadSeekTouch = false;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView, com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onPrepared() {
        setTextAndProgress(0, true);
        super.onPrepared();
        if (this.mCurrentState != 1) {
            return;
        }
        startProgressTimer();
        Debuger.printfLog(hashCode() + "------------------------------ surface_container onPrepared");
    }

    @Override // com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onBufferingUpdate(final int i) {
        post(new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView.3
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoControlView.this.mCurrentState == 0 || GSYVideoControlView.this.mCurrentState == 1) {
                    return;
                }
                int i2 = i;
                if (i2 != 0) {
                    GSYVideoControlView.this.setTextAndProgress(i2);
                    GSYVideoControlView.this.mBufferPoint = i;
                    Debuger.printfLog("Net speed: " + GSYVideoControlView.this.getNetSpeedText() + " percent " + i);
                }
                if (GSYVideoControlView.this.mProgressBar != null && GSYVideoControlView.this.mLooping && GSYVideoControlView.this.mHadPlay && i == 0 && GSYVideoControlView.this.mProgressBar.getProgress() >= GSYVideoControlView.this.mProgressBar.getMax() - 1) {
                    GSYVideoControlView.this.loopSetProgressAndTime();
                }
            }
        });
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected void prepareVideo() {
        if (this.mSetUpLazy) {
            super.setUp(this.mOriginUrl, this.mCache, this.mCachePath, this.mMapHeadData, this.mTitle);
        }
        super.prepareVideo();
    }

    protected void touchSurfaceDown(float f, float f2) {
        this.mTouchingProgressBar = true;
        this.mDownX = f;
        this.mDownY = f2;
        this.mMoveY = 0.0f;
        this.mChangeVolume = false;
        this.mChangePosition = false;
        this.mShowVKey = false;
        this.mBrightness = false;
        this.mFirstTouch = true;
    }

    protected void showDragProgressTextOnSeekBar(boolean z, int i) {
        if (z && this.isShowDragProgressTextOnSeekBar) {
            long duration = getDuration();
            TextView textView = this.mCurrentTimeTextView;
            if (textView != null) {
                textView.setText(CommonUtil.stringForTime((((long) i) * duration) / 100));
            }
        }
    }

    protected void touchSurfaceMove(float f, float f2, float f3) {
        int i;
        int i2;
        if (getActivityContext() != null) {
            i = CommonUtil.getCurrentScreenLand((Activity) getActivityContext()) ? this.mScreenHeight : this.mScreenWidth;
            i2 = CommonUtil.getCurrentScreenLand((Activity) getActivityContext()) ? this.mScreenWidth : this.mScreenHeight;
        } else {
            i = 0;
            i2 = 0;
        }
        if (this.mChangePosition) {
            long duration = getDuration();
            long j = (int) (this.mDownPosition + (((duration * f) / i) / this.mSeekRatio));
            this.mSeekTimePosition = j;
            if (j > duration) {
                this.mSeekTimePosition = duration;
            }
            showProgressDialog(f, CommonUtil.stringForTime(this.mSeekTimePosition), this.mSeekTimePosition, CommonUtil.stringForTime(duration), duration);
            return;
        }
        if (this.mChangeVolume) {
            float f4 = -f2;
            float f5 = i2;
            this.mAudioManager.setStreamVolume(3, this.mGestureDownVolume + ((int) (((this.mAudioManager.getStreamMaxVolume(3) * f4) * 3.0f) / f5)), 0);
            showVolumeDialog(-f4, (int) (((this.mGestureDownVolume * 100) / r14) + (((3.0f * f4) * 100.0f) / f5)));
            return;
        }
        if (!this.mBrightness || Math.abs(f2) <= this.mThreshold) {
            return;
        }
        onBrightnessSlide((-f2) / i2);
        this.mDownY = f3;
    }

    protected void touchSurfaceMoveFullLogic(float f, float f2) {
        int i;
        if (getActivityContext() != null) {
            i = CommonUtil.getCurrentScreenLand((Activity) getActivityContext()) ? this.mScreenHeight : this.mScreenWidth;
        } else {
            i = 0;
        }
        int i2 = this.mThreshold;
        if (f > i2 || f2 > i2) {
            cancelProgressTimer();
            if (f >= this.mThreshold) {
                if (Math.abs(CommonUtil.getScreenWidth(getContext()) - this.mDownX) > this.mSeekEndOffset) {
                    this.mChangePosition = true;
                    this.mDownPosition = getCurrentPositionWhenPlaying();
                    return;
                } else {
                    this.mShowVKey = true;
                    return;
                }
            }
            boolean z = Math.abs(((float) CommonUtil.getScreenHeight(getContext())) - this.mDownY) > ((float) this.mSeekEndOffset);
            if (this.mFirstTouch) {
                this.mBrightness = this.mDownX < ((float) i) * 0.5f && z;
                this.mFirstTouch = false;
            }
            if (!this.mBrightness) {
                this.mChangeVolume = z;
                this.mGestureDownVolume = this.mAudioManager.getStreamVolume(3);
            }
            this.mShowVKey = !z;
        }
    }

    protected void touchSurfaceUp() {
        if (this.mChangePosition) {
            long duration = getDuration();
            long j = this.mSeekTimePosition * 100;
            if (duration == 0) {
                duration = 1;
            }
            long j2 = j / duration;
            ProgressBar progressBar = this.mBottomProgressBar;
            if (progressBar != null) {
                progressBar.setProgress((int) j2);
            }
        }
        this.mTouchingProgressBar = false;
        dismissProgressDialog();
        dismissVolumeDialog();
        dismissBrightnessDialog();
        if (this.mChangePosition && getGSYVideoManager() != null && (this.mCurrentState == 2 || this.mCurrentState == 5)) {
            try {
                getGSYVideoManager().seekTo(this.mSeekTimePosition);
            } catch (Exception e) {
                e.printStackTrace();
            }
            long duration2 = getDuration();
            long j3 = (this.mSeekTimePosition * 100) / (duration2 != 0 ? duration2 : 1L);
            SeekBar seekBar = this.mProgressBar;
            if (seekBar != null) {
                seekBar.setProgress((int) j3);
            }
            if (this.mVideoAllCallBack == null || !isCurrentMediaListener()) {
                return;
            }
            Debuger.printfLog("onTouchScreenSeekPosition");
            this.mVideoAllCallBack.onTouchScreenSeekPosition(this.mOriginUrl, this.mTitle, this);
            return;
        }
        if (this.mBrightness) {
            if (this.mVideoAllCallBack == null || !isCurrentMediaListener()) {
                return;
            }
            Debuger.printfLog("onTouchScreenSeekLight");
            this.mVideoAllCallBack.onTouchScreenSeekLight(this.mOriginUrl, this.mTitle, this);
            return;
        }
        if (this.mChangeVolume && this.mVideoAllCallBack != null && isCurrentMediaListener()) {
            Debuger.printfLog("onTouchScreenSeekVolume");
            this.mVideoAllCallBack.onTouchScreenSeekVolume(this.mOriginUrl, this.mTitle, this);
        }
    }

    protected void touchDoubleUp(MotionEvent motionEvent) {
        if (this.mHadPlay) {
            clickStartIcon();
        }
    }

    protected void resolveUIState(int i) {
        if (i == 0) {
            changeUiToNormal();
            cancelDismissControlViewTimer();
            return;
        }
        if (i == 1) {
            changeUiToPreparingShow();
            startDismissControlViewTimer();
            return;
        }
        if (i == 2) {
            changeUiToPlayingShow();
            startDismissControlViewTimer();
            return;
        }
        if (i == 3) {
            changeUiToPlayingBufferingShow();
            return;
        }
        if (i == 5) {
            changeUiToPauseShow();
            cancelDismissControlViewTimer();
        } else if (i == 6) {
            changeUiToCompleteShow();
            cancelDismissControlViewTimer();
        } else {
            if (i != 7) {
                return;
            }
            changeUiToError();
        }
    }

    protected void clickStartIcon() {
        if (TextUtils.isEmpty(this.mUrl)) {
            Debuger.printfError("********" + getResources().getString(R.string.no_url));
            return;
        }
        if (this.mCurrentState == 0 || this.mCurrentState == 7) {
            if (isShowNetConfirm()) {
                showWifiDialog();
                return;
            } else {
                startButtonLogic();
                return;
            }
        }
        if (this.mCurrentState == 2) {
            try {
                onVideoPause();
            } catch (Exception e) {
                e.printStackTrace();
            }
            setStateAndUi(5);
            if (this.mVideoAllCallBack == null || !isCurrentMediaListener()) {
                return;
            }
            if (this.mIfCurrentIsFullscreen) {
                Debuger.printfLog("onClickStopFullscreen");
                this.mVideoAllCallBack.onClickStopFullscreen(this.mOriginUrl, this.mTitle, this);
                return;
            } else {
                Debuger.printfLog("onClickStop");
                this.mVideoAllCallBack.onClickStop(this.mOriginUrl, this.mTitle, this);
                return;
            }
        }
        if (this.mCurrentState == 5) {
            if (this.mVideoAllCallBack != null && isCurrentMediaListener()) {
                if (this.mIfCurrentIsFullscreen) {
                    Debuger.printfLog("onClickResumeFullscreen");
                    this.mVideoAllCallBack.onClickResumeFullscreen(this.mOriginUrl, this.mTitle, this);
                } else {
                    Debuger.printfLog("onClickResume");
                    this.mVideoAllCallBack.onClickResume(this.mOriginUrl, this.mTitle, this);
                }
            }
            if (!this.mHadPlay && !this.mStartAfterPrepared) {
                startAfterPrepared();
            }
            try {
                getGSYVideoManager().start();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            setStateAndUi(2);
            return;
        }
        if (this.mCurrentState == 6) {
            startButtonLogic();
        }
    }

    protected void lockTouchLogic() {
        if (this.mLockCurScreen) {
            this.mLockScreen.setImageResource(R.drawable.unlock);
            this.mLockCurScreen = false;
        } else {
            this.mLockScreen.setImageResource(R.drawable.lock);
            this.mLockCurScreen = true;
            hideAllWidget();
        }
    }

    protected void startProgressTimer() {
        cancelProgressTimer();
        this.mPostProgress = true;
        postDelayed(this.progressTask, 300L);
    }

    protected void cancelProgressTimer() {
        this.mPostProgress = false;
        removeCallbacks(this.progressTask);
    }

    protected void setTextAndProgress(int i) {
        setTextAndProgress(i, false);
    }

    protected void setTextAndProgress(int i, boolean z) {
        long currentPositionWhenPlaying = getCurrentPositionWhenPlaying();
        long duration = getDuration();
        setProgressAndTime((100 * currentPositionWhenPlaying) / (duration == 0 ? 1L : duration), i, currentPositionWhenPlaying, duration, z);
    }

    protected void setProgressAndTime(long j, long j2, long j3, long j4, boolean z) {
        if (this.mGSYVideoProgressListener != null && this.mCurrentState == 2) {
            this.mGSYVideoProgressListener.onProgress(j, j2, j3, j4);
        }
        SeekBar seekBar = this.mProgressBar;
        if (seekBar == null || this.mTotalTimeTextView == null || this.mCurrentTimeTextView == null || this.mHadSeekTouch) {
            return;
        }
        if (!this.mTouchingProgressBar && (j != 0 || z)) {
            seekBar.setProgress((int) j);
        }
        long bufferedPercentage = getGSYVideoManager().getBufferedPercentage() > 0 ? getGSYVideoManager().getBufferedPercentage() : j2;
        if (bufferedPercentage > 94) {
            bufferedPercentage = 100;
        }
        setSecondaryProgress(bufferedPercentage);
        this.mTotalTimeTextView.setText(CommonUtil.stringForTime(j4));
        if (j3 > 0) {
            this.mCurrentTimeTextView.setText(CommonUtil.stringForTime(j3));
        }
        ProgressBar progressBar = this.mBottomProgressBar;
        if (progressBar != null) {
            if (j != 0 || z) {
                progressBar.setProgress((int) j);
            }
            setSecondaryProgress(bufferedPercentage);
        }
    }

    protected void setSecondaryProgress(long j) {
        if (this.mProgressBar != null && j != 0 && !getGSYVideoManager().isCacheFile()) {
            this.mProgressBar.setSecondaryProgress((int) j);
        }
        if (this.mBottomProgressBar == null || j == 0 || getGSYVideoManager().isCacheFile()) {
            return;
        }
        this.mBottomProgressBar.setSecondaryProgress((int) j);
    }

    protected void resetProgressAndTime() {
        SeekBar seekBar = this.mProgressBar;
        if (seekBar == null || this.mTotalTimeTextView == null || this.mCurrentTimeTextView == null) {
            return;
        }
        seekBar.setProgress(0);
        this.mProgressBar.setSecondaryProgress(0);
        this.mCurrentTimeTextView.setText(CommonUtil.stringForTime(0L));
        this.mTotalTimeTextView.setText(CommonUtil.stringForTime(0L));
        ProgressBar progressBar = this.mBottomProgressBar;
        if (progressBar != null) {
            progressBar.setProgress(0);
            this.mBottomProgressBar.setSecondaryProgress(0);
        }
    }

    protected void loopSetProgressAndTime() {
        SeekBar seekBar = this.mProgressBar;
        if (seekBar == null || this.mTotalTimeTextView == null || this.mCurrentTimeTextView == null) {
            return;
        }
        seekBar.setProgress(0);
        this.mProgressBar.setSecondaryProgress(0);
        this.mCurrentTimeTextView.setText(CommonUtil.stringForTime(0L));
        ProgressBar progressBar = this.mBottomProgressBar;
        if (progressBar != null) {
            progressBar.setProgress(0);
        }
    }

    protected void startDismissControlViewTimer() {
        cancelDismissControlViewTimer();
        this.mPostDismiss = true;
        postDelayed(this.dismissControlTask, this.mDismissControlTime);
    }

    protected void cancelDismissControlViewTimer() {
        this.mPostDismiss = false;
        removeCallbacks(this.dismissControlTask);
    }

    protected void resolveThumbImage(View view) {
        RelativeLayout relativeLayout = this.mThumbImageViewLayout;
        if (relativeLayout != null) {
            relativeLayout.removeAllViews();
            this.mThumbImageViewLayout.addView(view);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = -1;
            layoutParams.width = -1;
            view.setLayoutParams(layoutParams);
        }
    }

    protected void setViewShowState(View view, int i) {
        if (view != null) {
            view.setVisibility(i);
        }
    }

    protected void onBrightnessSlide(float f) {
        float f2 = ((Activity) this.mContext).getWindow().getAttributes().screenBrightness;
        this.mBrightnessData = f2;
        if (f2 <= 0.0f) {
            this.mBrightnessData = 0.5f;
        } else if (f2 < 0.01f) {
            this.mBrightnessData = 0.01f;
        }
        WindowManager.LayoutParams attributes = ((Activity) this.mContext).getWindow().getAttributes();
        attributes.screenBrightness = this.mBrightnessData + f;
        if (attributes.screenBrightness > 1.0f) {
            attributes.screenBrightness = 1.0f;
        } else if (attributes.screenBrightness < 0.01f) {
            attributes.screenBrightness = 0.01f;
        }
        showBrightnessDialog(attributes.screenBrightness);
        ((Activity) this.mContext).getWindow().setAttributes(attributes);
    }

    protected boolean isShowNetConfirm() {
        return (this.mOriginUrl.startsWith("file") || this.mOriginUrl.startsWith("android.resource") || CommonUtil.isWifiConnected(getContext()) || !this.mNeedShowWifiTip || getGSYVideoManager().cachePreview(this.mContext.getApplicationContext(), this.mCachePath, this.mOriginUrl)) ? false : true;
    }

    public boolean setUpLazy(String str, boolean z, File file, Map<String, String> map, String str2) {
        this.mOriginUrl = str;
        this.mCache = z;
        this.mCachePath = file;
        this.mSetUpLazy = true;
        this.mTitle = str2;
        this.mMapHeadData = map;
        if (isCurrentMediaListener() && System.currentTimeMillis() - this.mSaveChangeViewTIme < 2000) {
            return false;
        }
        this.mUrl = "waiting";
        this.mCurrentState = 0;
        return true;
    }

    public void initUIState() {
        setStateAndUi(0);
    }

    public RelativeLayout getThumbImageViewLayout() {
        return this.mThumbImageViewLayout;
    }

    public void setThumbImageView(View view) {
        if (this.mThumbImageViewLayout != null) {
            this.mThumbImageView = view;
            resolveThumbImage(view);
        }
    }

    public void clearThumbImageView() {
        RelativeLayout relativeLayout = this.mThumbImageViewLayout;
        if (relativeLayout != null) {
            relativeLayout.removeAllViews();
        }
    }

    public View getThumbImageView() {
        return this.mThumbImageView;
    }

    public TextView getTitleTextView() {
        return this.mTitleTextView;
    }

    public View getStartButton() {
        return this.mStartButton;
    }

    public ImageView getFullscreenButton() {
        return this.mFullscreenButton;
    }

    public ImageView getBackButton() {
        return this.mBackButton;
    }

    public int getEnlargeImageRes() {
        int i = this.mEnlargeImageRes;
        return i == -1 ? R.drawable.video_enlarge : i;
    }

    public void setEnlargeImageRes(int i) {
        this.mEnlargeImageRes = i;
    }

    public int getShrinkImageRes() {
        int i = this.mShrinkImageRes;
        return i == -1 ? R.drawable.video_shrink : i;
    }

    public void setShrinkImageRes(int i) {
        this.mShrinkImageRes = i;
    }

    public void setIsTouchWigetFull(boolean z) {
        this.mIsTouchWigetFull = z;
    }

    public void setThumbPlay(boolean z) {
        this.mThumbPlay = z;
    }

    public boolean isSurfaceErrorPlay() {
        return this.mSurfaceErrorPlay;
    }

    public void setSurfaceErrorPlay(boolean z) {
        this.mSurfaceErrorPlay = z;
    }

    public boolean isHideKey() {
        return this.mHideKey;
    }

    public void setHideKey(boolean z) {
        this.mHideKey = z;
    }

    public boolean isNeedShowWifiTip() {
        return this.mNeedShowWifiTip;
    }

    public boolean isTouchWiget() {
        return this.mIsTouchWiget;
    }

    public void setIsTouchWiget(boolean z) {
        this.mIsTouchWiget = z;
    }

    public boolean isTouchWigetFull() {
        return this.mIsTouchWigetFull;
    }

    public void setNeedShowWifiTip(boolean z) {
        this.mNeedShowWifiTip = z;
    }

    public void setSeekRatio(float f) {
        if (f < 0.0f) {
            return;
        }
        this.mSeekRatio = f;
    }

    public float getSeekRatio() {
        return this.mSeekRatio;
    }

    public boolean isNeedLockFull() {
        return this.mNeedLockFull;
    }

    public void setNeedLockFull(boolean z) {
        this.mNeedLockFull = z;
    }

    public void setLockClickListener(LockClickListener lockClickListener) {
        this.mLockClickListener = lockClickListener;
    }

    public void setDismissControlTime(int i) {
        this.mDismissControlTime = i;
    }

    public int getDismissControlTime() {
        return this.mDismissControlTime;
    }

    public void setGSYVideoProgressListener(GSYVideoProgressListener gSYVideoProgressListener) {
        this.mGSYVideoProgressListener = gSYVideoProgressListener;
    }

    public boolean isShowDragProgressTextOnSeekBar() {
        return this.isShowDragProgressTextOnSeekBar;
    }

    public void setShowDragProgressTextOnSeekBar(boolean z) {
        this.isShowDragProgressTextOnSeekBar = z;
    }

    public GSYStateUiListener getGSYStateUiListener() {
        return this.mGsyStateUiListener;
    }

    public void setGSYStateUiListener(GSYStateUiListener gSYStateUiListener) {
        this.mGsyStateUiListener = gSYStateUiListener;
    }
}
