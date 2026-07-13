package com.shuyu.gsyvideoplayer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Surface;
import com.shuyu.gsyvideoplayer.cache.CacheFactory;
import com.shuyu.gsyvideoplayer.cache.ICacheManager;
import com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener;
import com.shuyu.gsyvideoplayer.model.GSYModel;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.player.BasePlayerManager;
import com.shuyu.gsyvideoplayer.player.IPlayerInitSuccessListener;
import com.shuyu.gsyvideoplayer.player.IPlayerManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: loaded from: classes3.dex */
public abstract class GSYVideoBaseManager implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnSeekCompleteListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnInfoListener, ICacheManager.ICacheAvailableListener, GSYVideoViewBridge {
    protected static final int BUFFER_TIME_OUT_ERROR = -192;
    protected static final int HANDLER_PREPARE = 0;
    protected static final int HANDLER_RELEASE = 2;
    protected static final int HANDLER_RELEASE_SURFACE = 3;
    protected static final int HANDLER_SETDISPLAY = 1;
    public static String TAG = "GSYVideoBaseManager";
    protected int bufferPoint;
    protected ICacheManager cacheManager;
    protected Context context;
    protected WeakReference<GSYMediaPlayerListener> lastListener;
    protected int lastState;
    protected WeakReference<GSYMediaPlayerListener> listener;
    protected MediaHandler mMediaHandler;
    protected IPlayerInitSuccessListener mPlayerInitSuccessListener;
    protected Handler mainThreadHandler;
    protected boolean needTimeOutOther;
    protected List<VideoOptionModel> optionModelList;
    protected IPlayerManager playerManager;
    protected String playTag = "";
    protected int currentVideoWidth = 0;
    protected int currentVideoHeight = 0;
    protected int playPosition = -22;
    protected int timeOut = 8000;
    protected boolean needMute = false;
    private Runnable mTimeOutRunnable = new Runnable() { // from class: com.shuyu.gsyvideoplayer.GSYVideoBaseManager.8
        @Override // java.lang.Runnable
        public void run() {
            if (GSYVideoBaseManager.this.listener != null) {
                Debuger.printfError("time out for error listener");
                GSYVideoBaseManager.this.listener().onError(GSYVideoBaseManager.BUFFER_TIME_OUT_ERROR, GSYVideoBaseManager.BUFFER_TIME_OUT_ERROR);
            }
        }
    };

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public int getRotateInfoFlag() {
        return 10001;
    }

    public void clearAllDefaultCache(Context context) {
        clearDefaultCache(context, null, null);
    }

    public void clearDefaultCache(Context context, File file, String str) {
        ICacheManager iCacheManager = this.cacheManager;
        if (iCacheManager != null) {
            iCacheManager.clearCache(context, file, str);
        } else if (getCacheManager() != null) {
            getCacheManager().clearCache(context, file, str);
        }
    }

    protected void init() {
        this.mMediaHandler = new MediaHandler(Looper.getMainLooper());
        this.mainThreadHandler = new Handler();
    }

    protected IPlayerManager getPlayManager() {
        return PlayerFactory.getPlayManager();
    }

    protected ICacheManager getCacheManager() {
        return CacheFactory.getCacheManager();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public GSYMediaPlayerListener listener() {
        WeakReference<GSYMediaPlayerListener> weakReference = this.listener;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public GSYMediaPlayerListener lastListener() {
        WeakReference<GSYMediaPlayerListener> weakReference = this.lastListener;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void setListener(GSYMediaPlayerListener gSYMediaPlayerListener) {
        if (gSYMediaPlayerListener == null) {
            this.listener = null;
        } else {
            this.listener = new WeakReference<>(gSYMediaPlayerListener);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void setLastListener(GSYMediaPlayerListener gSYMediaPlayerListener) {
        if (gSYMediaPlayerListener == null) {
            this.lastListener = null;
        } else {
            this.lastListener = new WeakReference<>(gSYMediaPlayerListener);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void setSpeed(float f, boolean z) {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            iPlayerManager.setSpeed(f, z);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void prepare(String str, Map<String, String> map, boolean z, float f, boolean z2, File file) {
        prepare(str, map, z, f, z2, file, null);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void prepare(String str, Map<String, String> map, boolean z, float f, boolean z2, File file, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Message message = new Message();
        message.what = 0;
        message.obj = new GSYModel(str, map, z, f, z2, file, str2);
        sendMessage(message);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void releaseMediaPlayer() {
        Message message = new Message();
        message.what = 2;
        sendMessage(message);
        this.playTag = "";
        this.playPosition = -22;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void setDisplay(Surface surface) {
        Message message = new Message();
        message.what = 1;
        message.obj = surface;
        showDisplay(message);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void releaseSurface(Surface surface) {
        Message message = new Message();
        message.what = 3;
        message.obj = surface;
        sendMessage(message);
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.shuyu.gsyvideoplayer.GSYVideoBaseManager.1
            @Override // java.lang.Runnable
            public void run() {
                GSYVideoBaseManager.this.cancelTimeOutBuffer();
                if (GSYVideoBaseManager.this.listener() != null) {
                    GSYVideoBaseManager.this.listener().onPrepared();
                }
            }
        });
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.shuyu.gsyvideoplayer.GSYVideoBaseManager.2
            @Override // java.lang.Runnable
            public void run() {
                GSYVideoBaseManager.this.cancelTimeOutBuffer();
                if (GSYVideoBaseManager.this.listener() != null) {
                    GSYVideoBaseManager.this.listener().onAutoCompletion();
                }
            }
        });
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, final int i) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.shuyu.gsyvideoplayer.GSYVideoBaseManager.3
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoBaseManager.this.listener() != null) {
                    if (i > GSYVideoBaseManager.this.bufferPoint) {
                        GSYVideoBaseManager.this.listener().onBufferingUpdate(i);
                    } else {
                        GSYVideoBaseManager.this.listener().onBufferingUpdate(GSYVideoBaseManager.this.bufferPoint);
                    }
                }
            }
        });
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.shuyu.gsyvideoplayer.GSYVideoBaseManager.4
            @Override // java.lang.Runnable
            public void run() {
                GSYVideoBaseManager.this.cancelTimeOutBuffer();
                if (GSYVideoBaseManager.this.listener() != null) {
                    GSYVideoBaseManager.this.listener().onSeekComplete();
                }
            }
        });
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
    public boolean onError(IMediaPlayer iMediaPlayer, final int i, final int i2) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.shuyu.gsyvideoplayer.GSYVideoBaseManager.5
            @Override // java.lang.Runnable
            public void run() {
                GSYVideoBaseManager.this.cancelTimeOutBuffer();
                if (GSYVideoBaseManager.this.listener() != null) {
                    GSYVideoBaseManager.this.listener().onError(i, i2);
                }
            }
        });
        return true;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
    public boolean onInfo(IMediaPlayer iMediaPlayer, final int i, final int i2) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.shuyu.gsyvideoplayer.GSYVideoBaseManager.6
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoBaseManager.this.needTimeOutOther) {
                    int i3 = i;
                    if (i3 == 701) {
                        GSYVideoBaseManager.this.startTimeOutBuffer();
                    } else if (i3 == 702) {
                        GSYVideoBaseManager.this.cancelTimeOutBuffer();
                    }
                }
                if (GSYVideoBaseManager.this.listener() != null) {
                    GSYVideoBaseManager.this.listener().onInfo(i, i2);
                }
            }
        });
        return false;
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
        this.currentVideoWidth = iMediaPlayer.getVideoWidth();
        this.currentVideoHeight = iMediaPlayer.getVideoHeight();
        this.mainThreadHandler.post(new Runnable() { // from class: com.shuyu.gsyvideoplayer.GSYVideoBaseManager.7
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoBaseManager.this.listener() != null) {
                    GSYVideoBaseManager.this.listener().onVideoSizeChanged();
                }
            }
        });
    }

    @Override // com.shuyu.gsyvideoplayer.cache.ICacheManager.ICacheAvailableListener
    public void onCacheAvailable(File file, String str, int i) {
        this.bufferPoint = i;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public int getLastState() {
        return this.lastState;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void setLastState(int i) {
        this.lastState = i;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public int getCurrentVideoWidth() {
        return this.currentVideoWidth;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public int getCurrentVideoHeight() {
        return this.currentVideoHeight;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void setCurrentVideoHeight(int i) {
        this.currentVideoHeight = i;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void setCurrentVideoWidth(int i) {
        this.currentVideoWidth = i;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public String getPlayTag() {
        return this.playTag;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void setPlayTag(String str) {
        this.playTag = str;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public int getPlayPosition() {
        return this.playPosition;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void setPlayPosition(int i) {
        this.playPosition = i;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public boolean isCacheFile() {
        ICacheManager iCacheManager = this.cacheManager;
        return iCacheManager != null && iCacheManager.hadCached();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public boolean cachePreview(Context context, File file, String str) {
        if (getCacheManager() != null) {
            return getCacheManager().cachePreview(context, file, str);
        }
        return false;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public long getNetSpeed() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            return iPlayerManager.getNetSpeed();
        }
        return 0L;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void clearCache(Context context, File file, String str) {
        clearDefaultCache(context, file, str);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public int getBufferedPercentage() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            return iPlayerManager.getBufferedPercentage();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void setSpeedPlaying(float f, boolean z) {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            iPlayerManager.setSpeedPlaying(f, z);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public IPlayerManager getPlayer() {
        return this.playerManager;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void start() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            iPlayerManager.start();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void stop() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            iPlayerManager.stop();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void pause() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            iPlayerManager.pause();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public int getVideoWidth() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            return iPlayerManager.getVideoWidth();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public int getVideoHeight() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            return iPlayerManager.getVideoHeight();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public boolean isPlaying() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            return iPlayerManager.isPlaying();
        }
        return false;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public void seekTo(long j) {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            iPlayerManager.seekTo(j);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public long getCurrentPosition() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            return iPlayerManager.getCurrentPosition();
        }
        return 0L;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public long getDuration() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            return iPlayerManager.getDuration();
        }
        return 0L;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public int getVideoSarNum() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            return iPlayerManager.getVideoSarNum();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public int getVideoSarDen() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            return iPlayerManager.getVideoSarDen();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge
    public boolean isSurfaceSupportLockCanvas() {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            return iPlayerManager.isSurfaceSupportLockCanvas();
        }
        return false;
    }

    protected void sendMessage(Message message) {
        this.mMediaHandler.sendMessage(message);
    }

    private class MediaHandler extends Handler {
        MediaHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                GSYVideoBaseManager.this.initVideo(message);
                if (GSYVideoBaseManager.this.needTimeOutOther) {
                    GSYVideoBaseManager.this.startTimeOutBuffer();
                    return;
                }
                return;
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                GSYVideoBaseManager.this.releaseSurface(message);
                return;
            }
            if (GSYVideoBaseManager.this.playerManager != null) {
                GSYVideoBaseManager.this.playerManager.release();
            }
            if (GSYVideoBaseManager.this.cacheManager != null) {
                GSYVideoBaseManager.this.cacheManager.release();
            }
            GSYVideoBaseManager.this.bufferPoint = 0;
            GSYVideoBaseManager.this.setNeedMute(false);
            GSYVideoBaseManager.this.cancelTimeOutBuffer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initVideo(Message message) {
        try {
            this.currentVideoWidth = 0;
            this.currentVideoHeight = 0;
            IPlayerManager iPlayerManager = this.playerManager;
            if (iPlayerManager != null) {
                iPlayerManager.release();
            }
            this.playerManager = getPlayManager();
            ICacheManager cacheManager = getCacheManager();
            this.cacheManager = cacheManager;
            if (cacheManager != null) {
                cacheManager.setCacheAvailableListener(this);
            }
            IPlayerManager iPlayerManager2 = this.playerManager;
            if (iPlayerManager2 instanceof BasePlayerManager) {
                ((BasePlayerManager) iPlayerManager2).setPlayerInitSuccessListener(this.mPlayerInitSuccessListener);
            }
            this.playerManager.initVideoPlayer(this.context, message, this.optionModelList, this.cacheManager);
            setNeedMute(this.needMute);
            IMediaPlayer mediaPlayer = this.playerManager.getMediaPlayer();
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setScreenOnWhilePlaying(true);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setOnInfoListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startTimeOutBuffer() {
        Debuger.printfError("startTimeOutBuffer");
        this.mainThreadHandler.postDelayed(this.mTimeOutRunnable, this.timeOut);
    }

    protected void cancelTimeOutBuffer() {
        Debuger.printfError("cancelTimeOutBuffer");
        if (this.needTimeOutOther) {
            this.mainThreadHandler.removeCallbacks(this.mTimeOutRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseSurface(Message message) {
        IPlayerManager iPlayerManager;
        if (message.obj == null || (iPlayerManager = this.playerManager) == null) {
            return;
        }
        iPlayerManager.releaseSurface();
    }

    private void showDisplay(Message message) {
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            iPlayerManager.showDisplay(message);
        }
    }

    public void initContext(Context context) {
        this.context = context.getApplicationContext();
    }

    public void enableRawPlay(Context context) {
        this.context = context.getApplicationContext();
    }

    public List<VideoOptionModel> getOptionModelList() {
        return this.optionModelList;
    }

    public void setOptionModelList(List<VideoOptionModel> list) {
        this.optionModelList = list;
    }

    public boolean isNeedMute() {
        return this.needMute;
    }

    public void setNeedMute(boolean z) {
        this.needMute = z;
        IPlayerManager iPlayerManager = this.playerManager;
        if (iPlayerManager != null) {
            iPlayerManager.setNeedMute(z);
        }
    }

    public int getTimeOut() {
        return this.timeOut;
    }

    public boolean isNeedTimeOutOther() {
        return this.needTimeOutOther;
    }

    public void setTimeOut(int i, boolean z) {
        this.timeOut = i;
        this.needTimeOutOther = z;
    }

    public IPlayerManager getCurPlayerManager() {
        return this.playerManager;
    }

    public ICacheManager getCurCacheManager() {
        return this.cacheManager;
    }

    public IPlayerInitSuccessListener getPlayerPreparedSuccessListener() {
        return this.mPlayerInitSuccessListener;
    }

    public void setPlayerInitSuccessListener(IPlayerInitSuccessListener iPlayerInitSuccessListener) {
        this.mPlayerInitSuccessListener = iPlayerInitSuccessListener;
    }
}
