package com.shuyu.gsyvideoplayer.video.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import com.shuyu.gsyvideoplayer.R;
import com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.NetInfoModule;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class GSYVideoView extends GSYTextureRenderView implements GSYMediaPlayerListener {
    public static final int CHANGE_DELAY_TIME = 2000;
    public static final int CURRENT_STATE_AUTO_COMPLETE = 6;
    public static final int CURRENT_STATE_ERROR = 7;
    public static final int CURRENT_STATE_NORMAL = 0;
    public static final int CURRENT_STATE_PAUSE = 5;
    public static final int CURRENT_STATE_PLAYING = 2;
    public static final int CURRENT_STATE_PLAYING_BUFFERING_START = 3;
    public static final int CURRENT_STATE_PREPAREING = 1;
    protected AudioManager mAudioManager;
    protected int mBackUpPlayingBufferState;
    protected int mBufferPoint;
    protected boolean mCache;
    protected File mCachePath;
    protected Context mContext;
    protected long mCurrentPosition;
    protected int mCurrentState;
    protected boolean mHadPlay;
    protected boolean mHadPrepared;
    protected boolean mIfCurrentIsFullscreen;
    protected boolean mLooping;
    protected Map<String, String> mMapHeadData;
    protected boolean mNetChanged;
    protected NetInfoModule mNetInfoModule;
    protected String mNetSate;
    protected String mOriginUrl;
    protected String mOverrideExtension;
    protected boolean mPauseBeforePrepared;
    protected int mPlayPosition;
    protected String mPlayTag;
    protected boolean mReleaseWhenLossAudio;
    protected long mSaveChangeViewTIme;
    protected int mScreenHeight;
    protected int mScreenWidth;
    protected long mSeekOnStart;
    protected boolean mShowPauseCover;
    protected boolean mSoundTouch;
    protected float mSpeed;
    protected boolean mStartAfterPrepared;
    protected String mTitle;
    protected String mUrl;
    protected VideoAllCallBack mVideoAllCallBack;
    protected AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;

    protected abstract boolean backFromFull(Context context);

    public abstract GSYVideoViewBridge getGSYVideoManager();

    public abstract int getLayoutId();

    protected void onGankAudio() {
    }

    protected void onLossTransientCanDuck() {
    }

    protected abstract void releaseVideos();

    protected abstract void setStateAndUi(int i);

    public abstract void startPlayLogic();

    public GSYVideoView(Context context) {
        super(context);
        this.mCurrentState = -1;
        this.mPlayPosition = -22;
        this.mBackUpPlayingBufferState = -1;
        this.mSeekOnStart = -1L;
        this.mSaveChangeViewTIme = 0L;
        this.mSpeed = 1.0f;
        this.mCache = false;
        this.mIfCurrentIsFullscreen = false;
        this.mLooping = false;
        this.mHadPlay = false;
        this.mNetChanged = false;
        this.mSoundTouch = false;
        this.mShowPauseCover = false;
        this.mPauseBeforePrepared = false;
        this.mStartAfterPrepared = true;
        this.mHadPrepared = false;
        this.mReleaseWhenLossAudio = true;
        this.mPlayTag = "";
        this.mNetSate = "NORMAL";
        this.mMapHeadData = new HashMap();
        this.onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoView.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                if (i == -3) {
                    GSYVideoView.this.onLossTransientCanDuck();
                    return;
                }
                if (i == -2) {
                    GSYVideoView.this.onLossTransientAudio();
                } else if (i == -1) {
                    GSYVideoView.this.onLossAudio();
                } else {
                    if (i != 1) {
                        return;
                    }
                    GSYVideoView.this.onGankAudio();
                }
            }
        };
        init(context);
    }

    public GSYVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentState = -1;
        this.mPlayPosition = -22;
        this.mBackUpPlayingBufferState = -1;
        this.mSeekOnStart = -1L;
        this.mSaveChangeViewTIme = 0L;
        this.mSpeed = 1.0f;
        this.mCache = false;
        this.mIfCurrentIsFullscreen = false;
        this.mLooping = false;
        this.mHadPlay = false;
        this.mNetChanged = false;
        this.mSoundTouch = false;
        this.mShowPauseCover = false;
        this.mPauseBeforePrepared = false;
        this.mStartAfterPrepared = true;
        this.mHadPrepared = false;
        this.mReleaseWhenLossAudio = true;
        this.mPlayTag = "";
        this.mNetSate = "NORMAL";
        this.mMapHeadData = new HashMap();
        this.onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoView.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                if (i == -3) {
                    GSYVideoView.this.onLossTransientCanDuck();
                    return;
                }
                if (i == -2) {
                    GSYVideoView.this.onLossTransientAudio();
                } else if (i == -1) {
                    GSYVideoView.this.onLossAudio();
                } else {
                    if (i != 1) {
                        return;
                    }
                    GSYVideoView.this.onGankAudio();
                }
            }
        };
        init(context);
    }

    public GSYVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentState = -1;
        this.mPlayPosition = -22;
        this.mBackUpPlayingBufferState = -1;
        this.mSeekOnStart = -1L;
        this.mSaveChangeViewTIme = 0L;
        this.mSpeed = 1.0f;
        this.mCache = false;
        this.mIfCurrentIsFullscreen = false;
        this.mLooping = false;
        this.mHadPlay = false;
        this.mNetChanged = false;
        this.mSoundTouch = false;
        this.mShowPauseCover = false;
        this.mPauseBeforePrepared = false;
        this.mStartAfterPrepared = true;
        this.mHadPrepared = false;
        this.mReleaseWhenLossAudio = true;
        this.mPlayTag = "";
        this.mNetSate = "NORMAL";
        this.mMapHeadData = new HashMap();
        this.onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoView.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i2) {
                if (i2 == -3) {
                    GSYVideoView.this.onLossTransientCanDuck();
                    return;
                }
                if (i2 == -2) {
                    GSYVideoView.this.onLossTransientAudio();
                } else if (i2 == -1) {
                    GSYVideoView.this.onLossAudio();
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    GSYVideoView.this.onGankAudio();
                }
            }
        };
        init(context);
    }

    public GSYVideoView(Context context, Boolean bool) {
        super(context);
        this.mCurrentState = -1;
        this.mPlayPosition = -22;
        this.mBackUpPlayingBufferState = -1;
        this.mSeekOnStart = -1L;
        this.mSaveChangeViewTIme = 0L;
        this.mSpeed = 1.0f;
        this.mCache = false;
        this.mIfCurrentIsFullscreen = false;
        this.mLooping = false;
        this.mHadPlay = false;
        this.mNetChanged = false;
        this.mSoundTouch = false;
        this.mShowPauseCover = false;
        this.mPauseBeforePrepared = false;
        this.mStartAfterPrepared = true;
        this.mHadPrepared = false;
        this.mReleaseWhenLossAudio = true;
        this.mPlayTag = "";
        this.mNetSate = "NORMAL";
        this.mMapHeadData = new HashMap();
        this.onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoView.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i2) {
                if (i2 == -3) {
                    GSYVideoView.this.onLossTransientCanDuck();
                    return;
                }
                if (i2 == -2) {
                    GSYVideoView.this.onLossTransientAudio();
                } else if (i2 == -1) {
                    GSYVideoView.this.onLossAudio();
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    GSYVideoView.this.onGankAudio();
                }
            }
        };
        this.mIfCurrentIsFullscreen = bool.booleanValue();
        init(context);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYTextureRenderView
    protected void showPauseCover() {
        if (this.mCurrentState == 5 && this.mFullPauseBitmap != null && !this.mFullPauseBitmap.isRecycled() && this.mShowPauseCover && this.mSurface != null && this.mSurface.isValid() && getGSYVideoManager().isSurfaceSupportLockCanvas()) {
            try {
                RectF rectF = new RectF(0.0f, 0.0f, this.mTextureView.getWidth(), this.mTextureView.getHeight());
                Canvas canvasLockCanvas = this.mSurface.lockCanvas(new Rect(0, 0, this.mTextureView.getWidth(), this.mTextureView.getHeight()));
                if (canvasLockCanvas != null) {
                    canvasLockCanvas.drawBitmap(this.mFullPauseBitmap, (Rect) null, rectF, (Paint) null);
                    this.mSurface.unlockCanvasAndPost(canvasLockCanvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYTextureRenderView
    protected void releasePauseCover() {
        try {
            if (this.mCurrentState == 5 || this.mFullPauseBitmap == null || this.mFullPauseBitmap.isRecycled() || !this.mShowPauseCover) {
                return;
            }
            this.mFullPauseBitmap.recycle();
            this.mFullPauseBitmap = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.utils.MeasureHelper.MeasureFormVideoParamsListener
    public int getCurrentVideoWidth() {
        if (getGSYVideoManager() != null) {
            return getGSYVideoManager().getVideoWidth();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.utils.MeasureHelper.MeasureFormVideoParamsListener
    public int getCurrentVideoHeight() {
        if (getGSYVideoManager() != null) {
            return getGSYVideoManager().getVideoHeight();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.utils.MeasureHelper.MeasureFormVideoParamsListener
    public int getVideoSarNum() {
        if (getGSYVideoManager() != null) {
            return getGSYVideoManager().getVideoSarNum();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.utils.MeasureHelper.MeasureFormVideoParamsListener
    public int getVideoSarDen() {
        if (getGSYVideoManager() != null) {
            return getGSYVideoManager().getVideoSarDen();
        }
        return 0;
    }

    protected void updatePauseCover() {
        if ((this.mFullPauseBitmap == null || this.mFullPauseBitmap.isRecycled()) && this.mShowPauseCover) {
            try {
                initCover();
            } catch (Exception e) {
                e.printStackTrace();
                this.mFullPauseBitmap = null;
            }
        }
    }

    protected Context getActivityContext() {
        return CommonUtil.getActivityContext(getContext());
    }

    protected void init(Context context) {
        if (getActivityContext() != null) {
            this.mContext = getActivityContext();
        } else {
            this.mContext = context;
        }
        initInflate(this.mContext);
        this.mTextureViewContainer = (ViewGroup) findViewById(R.id.surface_container);
        if (isInEditMode()) {
            return;
        }
        this.mScreenWidth = this.mContext.getResources().getDisplayMetrics().widthPixels;
        this.mScreenHeight = this.mContext.getResources().getDisplayMetrics().heightPixels;
        this.mAudioManager = (AudioManager) this.mContext.getApplicationContext().getSystemService("audio");
    }

    protected void initInflate(Context context) {
        try {
            View.inflate(context, getLayoutId(), this);
        } catch (InflateException e) {
            if (e.toString().contains("GSYImageCover")) {
                Debuger.printfError("********************\n*****   注意   *************************\n*该版本需要清除布局文件中的GSYImageCover\n****  Attention  ***\n*Please remove GSYImageCover from Layout in this Version\n********************\n");
                e.printStackTrace();
                throw new InflateException("该版本需要清除布局文件中的GSYImageCover，please remove GSYImageCover from your layout");
            }
            e.printStackTrace();
        }
    }

    protected void startButtonLogic() {
        int i;
        VideoAllCallBack videoAllCallBack = this.mVideoAllCallBack;
        if (videoAllCallBack != null && ((i = this.mCurrentState) == 0 || i == 6)) {
            Debuger.printfLog("onClickStartIcon");
            this.mVideoAllCallBack.onClickStartIcon(this.mOriginUrl, this.mTitle, this);
        } else if (videoAllCallBack != null) {
            Debuger.printfLog("onClickStartError");
            this.mVideoAllCallBack.onClickStartError(this.mOriginUrl, this.mTitle, this);
        }
        prepareVideo();
    }

    protected void prepareVideo() {
        startPrepare();
    }

    protected void startPrepare() {
        if (getGSYVideoManager().listener() != null) {
            getGSYVideoManager().listener().onCompletion();
        }
        if (this.mVideoAllCallBack != null) {
            Debuger.printfLog("onStartPrepared");
            this.mVideoAllCallBack.onStartPrepared(this.mOriginUrl, this.mTitle, this);
        }
        getGSYVideoManager().setListener(this);
        getGSYVideoManager().setPlayTag(this.mPlayTag);
        getGSYVideoManager().setPlayPosition(this.mPlayPosition);
        this.mAudioManager.requestAudioFocus(this.onAudioFocusChangeListener, 3, 2);
        try {
            Context context = this.mContext;
            if (context instanceof Activity) {
                ((Activity) context).getWindow().addFlags(128);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mBackUpPlayingBufferState = -1;
        GSYVideoViewBridge gSYVideoManager = getGSYVideoManager();
        String str = this.mUrl;
        Map<String, String> map = this.mMapHeadData;
        if (map == null) {
            map = new HashMap<>();
        }
        gSYVideoManager.prepare(str, map, this.mLooping, this.mSpeed, this.mCache, this.mCachePath, this.mOverrideExtension);
        setStateAndUi(1);
    }

    protected void onLossAudio() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoView.2
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoView.this.mReleaseWhenLossAudio) {
                    GSYVideoView.this.releaseVideos();
                } else {
                    GSYVideoView.this.onVideoPause();
                }
            }
        });
    }

    protected void onLossTransientAudio() {
        try {
            onVideoPause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean setUp(String str, boolean z, String str2) {
        return setUp(str, z, null, str2);
    }

    public boolean setUp(String str, boolean z, File file, Map<String, String> map, String str2) {
        if (!setUp(str, z, file, str2)) {
            return false;
        }
        Map<String, String> map2 = this.mMapHeadData;
        if (map2 != null) {
            map2.clear();
        } else {
            this.mMapHeadData = new HashMap();
        }
        if (map == null) {
            return true;
        }
        this.mMapHeadData.putAll(map);
        return true;
    }

    public boolean setUp(String str, boolean z, File file, String str2) {
        return setUp(str, z, file, str2, true);
    }

    protected boolean setUp(String str, boolean z, File file, String str2, boolean z2) {
        this.mCache = z;
        this.mCachePath = file;
        this.mOriginUrl = str;
        if (isCurrentMediaListener() && System.currentTimeMillis() - this.mSaveChangeViewTIme < 2000) {
            return false;
        }
        this.mCurrentState = 0;
        this.mUrl = str;
        this.mTitle = str2;
        if (!z2) {
            return true;
        }
        setStateAndUi(0);
        return true;
    }

    public void onVideoReset() {
        setStateAndUi(0);
    }

    @Override // com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onVideoPause() {
        if (this.mCurrentState == 1) {
            this.mPauseBeforePrepared = true;
        }
        try {
            if (getGSYVideoManager() == null || !getGSYVideoManager().isPlaying()) {
                return;
            }
            setStateAndUi(5);
            this.mCurrentPosition = getGSYVideoManager().getCurrentPosition();
            if (getGSYVideoManager() != null) {
                getGSYVideoManager().pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onVideoResume() {
        onVideoResume(true);
    }

    @Override // com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onVideoResume(boolean z) {
        this.mPauseBeforePrepared = false;
        if (this.mCurrentState == 5) {
            try {
                if (this.mCurrentPosition < 0 || getGSYVideoManager() == null) {
                    return;
                }
                if (z) {
                    getGSYVideoManager().seekTo(this.mCurrentPosition);
                }
                getGSYVideoManager().start();
                setStateAndUi(2);
                AudioManager audioManager = this.mAudioManager;
                if (audioManager != null && !this.mReleaseWhenLossAudio) {
                    audioManager.requestAudioFocus(this.onAudioFocusChangeListener, 3, 2);
                }
                this.mCurrentPosition = 0L;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void netWorkErrorLogic() {
        final long currentPositionWhenPlaying = getCurrentPositionWhenPlaying();
        Debuger.printfError("******* Net State Changed. renew player to connect *******" + currentPositionWhenPlaying);
        getGSYVideoManager().releaseMediaPlayer();
        postDelayed(new Runnable() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoView.3
            @Override // java.lang.Runnable
            public void run() {
                GSYVideoView.this.setSeekOnStart(currentPositionWhenPlaying);
                GSYVideoView.this.startPlayLogic();
            }
        }, 500L);
    }

    protected void deleteCacheFileWhenError() {
        clearCurrentCache();
        Debuger.printfError("Link Or mCache Error, Please Try Again " + this.mOriginUrl);
        if (this.mCache) {
            Debuger.printfError("mCache Link " + this.mUrl);
        }
        this.mUrl = this.mOriginUrl;
    }

    public void onPrepared() {
        if (this.mCurrentState != 1) {
            return;
        }
        this.mHadPrepared = true;
        if (this.mVideoAllCallBack != null && isCurrentMediaListener()) {
            Debuger.printfLog("onPrepared");
            this.mVideoAllCallBack.onPrepared(this.mOriginUrl, this.mTitle, this);
        }
        if (!this.mStartAfterPrepared) {
            setStateAndUi(5);
            onVideoPause();
        } else {
            startAfterPrepared();
        }
    }

    public void onAutoCompletion() {
        setStateAndUi(6);
        this.mSaveChangeViewTIme = 0L;
        this.mCurrentPosition = 0L;
        if (this.mTextureViewContainer.getChildCount() > 0) {
            this.mTextureViewContainer.removeAllViews();
        }
        if (!this.mIfCurrentIsFullscreen) {
            getGSYVideoManager().setLastListener(null);
        }
        this.mAudioManager.abandonAudioFocus(this.onAudioFocusChangeListener);
        Context context = this.mContext;
        if (context instanceof Activity) {
            try {
                ((Activity) context).getWindow().clearFlags(128);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        releaseNetWorkState();
        if (this.mVideoAllCallBack != null && isCurrentMediaListener()) {
            Debuger.printfLog("onAutoComplete");
            this.mVideoAllCallBack.onAutoComplete(this.mOriginUrl, this.mTitle, this);
        }
        this.mHadPlay = false;
    }

    public void onCompletion() {
        setStateAndUi(0);
        this.mSaveChangeViewTIme = 0L;
        this.mCurrentPosition = 0L;
        if (this.mTextureViewContainer.getChildCount() > 0) {
            this.mTextureViewContainer.removeAllViews();
        }
        if (!this.mIfCurrentIsFullscreen) {
            getGSYVideoManager().setListener(null);
            getGSYVideoManager().setLastListener(null);
        }
        getGSYVideoManager().setCurrentVideoHeight(0);
        getGSYVideoManager().setCurrentVideoWidth(0);
        this.mAudioManager.abandonAudioFocus(this.onAudioFocusChangeListener);
        Context context = this.mContext;
        if (context instanceof Activity) {
            try {
                ((Activity) context).getWindow().clearFlags(128);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        releaseNetWorkState();
        if (this.mVideoAllCallBack != null) {
            Debuger.printfLog("onComplete");
            this.mVideoAllCallBack.onComplete(this.mOriginUrl, this.mTitle, this);
        }
        this.mHadPlay = false;
    }

    @Override // com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onSeekComplete() {
        Debuger.printfLog("onSeekComplete");
    }

    public void onError(int i, int i2) {
        if (this.mNetChanged) {
            this.mNetChanged = false;
            netWorkErrorLogic();
            VideoAllCallBack videoAllCallBack = this.mVideoAllCallBack;
            if (videoAllCallBack != null) {
                videoAllCallBack.onPlayError(this.mOriginUrl, this.mTitle, this);
                return;
            }
            return;
        }
        if (i == 38 || i == -38) {
            return;
        }
        setStateAndUi(7);
        deleteCacheFileWhenError();
        VideoAllCallBack videoAllCallBack2 = this.mVideoAllCallBack;
        if (videoAllCallBack2 != null) {
            videoAllCallBack2.onPlayError(this.mOriginUrl, this.mTitle, this);
        }
    }

    public void onInfo(int i, int i2) {
        int i3;
        if (i == 701) {
            int i4 = this.mCurrentState;
            this.mBackUpPlayingBufferState = i4;
            if (!this.mHadPlay || i4 == 1 || i4 <= 0) {
                return;
            }
            setStateAndUi(3);
            return;
        }
        if (i == 702) {
            int i5 = this.mBackUpPlayingBufferState;
            if (i5 != -1) {
                if (i5 == 3) {
                    this.mBackUpPlayingBufferState = 2;
                }
                if (this.mHadPlay && (i3 = this.mCurrentState) != 1 && i3 > 0) {
                    setStateAndUi(this.mBackUpPlayingBufferState);
                }
                this.mBackUpPlayingBufferState = -1;
                return;
            }
            return;
        }
        if (i == getGSYVideoManager().getRotateInfoFlag()) {
            this.mRotate = i2;
            Debuger.printfLog("Video Rotate Info " + i2);
            if (this.mTextureView != null) {
                this.mTextureView.setRotation(this.mRotate);
            }
        }
    }

    @Override // com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onVideoSizeChanged() {
        int currentVideoWidth = getGSYVideoManager().getCurrentVideoWidth();
        int currentVideoHeight = getGSYVideoManager().getCurrentVideoHeight();
        if (currentVideoWidth == 0 || currentVideoHeight == 0 || this.mTextureView == null) {
            return;
        }
        this.mTextureView.requestLayout();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYTextureRenderView
    protected void setDisplay(Surface surface) {
        getGSYVideoManager().setDisplay(surface);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYTextureRenderView
    protected void releaseSurface(Surface surface) {
        getGSYVideoManager().releaseSurface(surface);
    }

    public void clearCurrentCache() {
        if (getGSYVideoManager().isCacheFile() && this.mCache) {
            Debuger.printfError("Play Error " + this.mUrl);
            this.mUrl = this.mOriginUrl;
            getGSYVideoManager().clearCache(this.mContext, this.mCachePath, this.mOriginUrl);
            return;
        }
        String str = this.mUrl;
        if (str == null || !str.contains("127.0.0.1")) {
            return;
        }
        getGSYVideoManager().clearCache(getContext(), this.mCachePath, this.mOriginUrl);
    }

    public long getCurrentPositionWhenPlaying() {
        long currentPosition;
        int i = this.mCurrentState;
        if (i == 2 || i == 5) {
            try {
                currentPosition = getGSYVideoManager().getCurrentPosition();
            } catch (Exception e) {
                e.printStackTrace();
                return 0L;
            }
        } else {
            currentPosition = 0;
        }
        if (currentPosition == 0) {
            long j = this.mCurrentPosition;
            if (j > 0) {
                return j;
            }
        }
        return currentPosition;
    }

    public long getDuration() {
        try {
            return getGSYVideoManager().getDuration();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public void release() {
        this.mSaveChangeViewTIme = 0L;
        if (!isCurrentMediaListener() || System.currentTimeMillis() - this.mSaveChangeViewTIme <= 2000) {
            return;
        }
        releaseVideos();
    }

    public void startAfterPrepared() {
        if (!this.mHadPrepared) {
            prepareVideo();
        }
        try {
            if (getGSYVideoManager() != null) {
                getGSYVideoManager().start();
            }
            setStateAndUi(2);
            if (getGSYVideoManager() != null && this.mSeekOnStart > 0) {
                getGSYVideoManager().seekTo(this.mSeekOnStart);
                this.mSeekOnStart = 0L;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addTextureView();
        createNetWorkState();
        listenerNetWorkState();
        this.mHadPlay = true;
        if (this.mTextureView != null) {
            this.mTextureView.onResume();
        }
        if (this.mPauseBeforePrepared) {
            onVideoPause();
            this.mPauseBeforePrepared = false;
        }
    }

    protected boolean isCurrentMediaListener() {
        return getGSYVideoManager().listener() != null && getGSYVideoManager().listener() == this;
    }

    protected void createNetWorkState() {
        if (this.mNetInfoModule == null) {
            NetInfoModule netInfoModule = new NetInfoModule(this.mContext.getApplicationContext(), new NetInfoModule.NetChangeListener() { // from class: com.shuyu.gsyvideoplayer.video.base.GSYVideoView.4
                @Override // com.shuyu.gsyvideoplayer.utils.NetInfoModule.NetChangeListener
                public void changed(String str) {
                    if (!GSYVideoView.this.mNetSate.equals(str)) {
                        Debuger.printfError("******* change network state ******* " + str);
                        GSYVideoView.this.mNetChanged = true;
                    }
                    GSYVideoView.this.mNetSate = str;
                }
            });
            this.mNetInfoModule = netInfoModule;
            this.mNetSate = netInfoModule.getCurrentConnectionType();
        }
    }

    protected void listenerNetWorkState() {
        NetInfoModule netInfoModule = this.mNetInfoModule;
        if (netInfoModule != null) {
            netInfoModule.onHostResume();
        }
    }

    protected void unListenerNetWorkState() {
        NetInfoModule netInfoModule = this.mNetInfoModule;
        if (netInfoModule != null) {
            netInfoModule.onHostPause();
        }
    }

    protected void releaseNetWorkState() {
        NetInfoModule netInfoModule = this.mNetInfoModule;
        if (netInfoModule != null) {
            netInfoModule.onHostPause();
            this.mNetInfoModule = null;
        }
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public boolean isInPlayingState() {
        int i = this.mCurrentState;
        return (i < 0 || i == 0 || i == 6 || i == 7) ? false : true;
    }

    public String getPlayTag() {
        return this.mPlayTag;
    }

    public void setPlayTag(String str) {
        this.mPlayTag = str;
    }

    public int getPlayPosition() {
        return this.mPlayPosition;
    }

    public void setPlayPosition(int i) {
        this.mPlayPosition = i;
    }

    public long getNetSpeed() {
        return getGSYVideoManager().getNetSpeed();
    }

    public String getNetSpeedText() {
        return CommonUtil.getTextSpeed(getNetSpeed());
    }

    public long getSeekOnStart() {
        return this.mSeekOnStart;
    }

    public void setSeekOnStart(long j) {
        this.mSeekOnStart = j;
    }

    public int getBuffterPoint() {
        return this.mBufferPoint;
    }

    public boolean isIfCurrentIsFullscreen() {
        return this.mIfCurrentIsFullscreen;
    }

    public void setIfCurrentIsFullscreen(boolean z) {
        this.mIfCurrentIsFullscreen = z;
    }

    public boolean isLooping() {
        return this.mLooping;
    }

    public void setLooping(boolean z) {
        this.mLooping = z;
    }

    public void setVideoAllCallBack(VideoAllCallBack videoAllCallBack) {
        this.mVideoAllCallBack = videoAllCallBack;
    }

    public float getSpeed() {
        return this.mSpeed;
    }

    public void setSpeed(float f) {
        setSpeed(f, false);
    }

    public void setSpeed(float f, boolean z) {
        this.mSpeed = f;
        this.mSoundTouch = z;
        if (getGSYVideoManager() != null) {
            getGSYVideoManager().setSpeed(f, z);
        }
    }

    public void setSpeedPlaying(float f, boolean z) {
        setSpeed(f, z);
        getGSYVideoManager().setSpeedPlaying(f, z);
    }

    public boolean isShowPauseCover() {
        return this.mShowPauseCover;
    }

    public void setShowPauseCover(boolean z) {
        this.mShowPauseCover = z;
    }

    public void seekTo(long j) {
        try {
            if (getGSYVideoManager() == null || j <= 0) {
                return;
            }
            getGSYVideoManager().seekTo(j);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isStartAfterPrepared() {
        return this.mStartAfterPrepared;
    }

    public void setStartAfterPrepared(boolean z) {
        this.mStartAfterPrepared = z;
    }

    public boolean isReleaseWhenLossAudio() {
        return this.mReleaseWhenLossAudio;
    }

    public void setReleaseWhenLossAudio(boolean z) {
        this.mReleaseWhenLossAudio = z;
    }

    public Map<String, String> getMapHeadData() {
        return this.mMapHeadData;
    }

    public void setMapHeadData(Map<String, String> map) {
        if (map != null) {
            this.mMapHeadData = map;
        }
    }

    public String getOverrideExtension() {
        return this.mOverrideExtension;
    }

    public void setOverrideExtension(String str) {
        this.mOverrideExtension = str;
    }
}
