package com.nbvideo;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.task.Task;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NBVideo extends FrameLayout implements TextureView.SurfaceTextureListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {
    private static final String TAG = "NBVideo";
    private Context context;
    private int currStatus;
    private boolean isLoaded;
    private Task loadTask;
    private AudioManager mAudioManager;
    private FrameLayout mContainer;
    private MediaPlayer mMediaPlayer;
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    private NBTextureView mTextureView;
    private FrameLayout textureViewLayout;
    private VideoCallback videoCallback;
    private VideoInfo videoInfo;

    public interface LoadingResultListener {
        void loadFail();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public VideoInfo getVideoInfo() {
        return this.videoInfo;
    }

    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    public void setVideoCallback(VideoCallback videoCallback) {
        this.videoCallback = videoCallback;
    }

    public NBVideo(Context context) {
        this(context, null, 0);
    }

    public NBVideo(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NBVideo(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.currStatus = 0;
        this.isLoaded = false;
        this.loadTask = Task.create();
        this.context = context;
        initVideo();
    }

    private void initVideo() {
        Context context = this.context;
        if (context instanceof Activity) {
            ((Activity) context).getWindow().setFlags(16777216, 16777216);
        }
        FrameLayout frameLayout = new FrameLayout(this.context);
        this.mContainer = frameLayout;
        frameLayout.setBackgroundColor(-16777216);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        addView(this.mContainer, layoutParams);
        layoutParams.gravity = 17;
        FrameLayout frameLayout2 = new FrameLayout(this.context);
        this.textureViewLayout = frameLayout2;
        frameLayout2.setBackgroundColor(-16777216);
        this.mContainer.addView(this.textureViewLayout, layoutParams);
    }

    public void start(final LoadingResultListener loadingResultListener) {
        this.isLoaded = false;
        if (this.currStatus == 0) {
            if (VideoManager.getInstance().getCurrPlayVideo() != null) {
                VideoManager.getInstance().getCurrPlayVideo().pause();
                VideoManager.getInstance().getCurrPlayVideo().releasePlayer();
            }
            VideoManager.getInstance().setCurrPlayVideo(this);
            initAudioManager();
            initMediaPlayer();
            initTextureView();
            addTextureView();
        }
        int i = this.currStatus;
        if (i == 0 || i == 1) {
            this.loadTask.oneShot(6000L, new Task.TaskFunc() { // from class: com.nbvideo.NBVideo.1
                @Override // com.sqwan.common.util.task.Task.TaskFunc
                public Task.Result exec() {
                    if (NBVideo.this.isLoaded) {
                        return null;
                    }
                    LogUtil.d(NBVideo.TAG, "加载视频流失败，回调重新加载新 url");
                    LoadingResultListener loadingResultListener2 = loadingResultListener;
                    if (loadingResultListener2 == null) {
                        return null;
                    }
                    loadingResultListener2.loadFail();
                    return null;
                }
            });
        }
    }

    public void changeUrlMediaAndReset(final LoadingResultListener loadingResultListener) {
        this.isLoaded = false;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        openMediaPlayer(this.mSurfaceTexture);
        this.loadTask.oneShot(6000L, new Task.TaskFunc() { // from class: com.nbvideo.NBVideo.2
            @Override // com.sqwan.common.util.task.Task.TaskFunc
            public Task.Result exec() {
                if (NBVideo.this.isLoaded) {
                    return null;
                }
                LogUtil.d(NBVideo.TAG, "加载视频流失败，回调重新加载新 url");
                LoadingResultListener loadingResultListener2 = loadingResultListener;
                if (loadingResultListener2 == null) {
                    return null;
                }
                loadingResultListener2.loadFail();
                return null;
            }
        });
    }

    public void restart() {
        LogUtil.d(TAG, "restart: " + this.currStatus);
        int i = this.currStatus;
        if (i == 4) {
            this.mMediaPlayer.start();
            this.currStatus = 3;
            return;
        }
        if (i == 6) {
            this.mMediaPlayer.start();
            this.currStatus = 5;
            return;
        }
        if (i == 7) {
            this.mMediaPlayer.reset();
            openMediaPlayer(this.mSurfaceTexture);
            this.currStatus = 10;
        } else if (i == -1) {
            this.mMediaPlayer.reset();
            openMediaPlayer(this.mSurfaceTexture);
            this.currStatus = 10;
        } else {
            this.mMediaPlayer.reset();
            openMediaPlayer(this.mSurfaceTexture);
            this.currStatus = 11;
        }
    }

    public void pause() {
        int i = this.currStatus;
        if (i == 3 || i == 2 || i == 7) {
            if (this.mMediaPlayer.isPlaying()) {
                LogUtil.i(TAG, "pause: ");
                this.mMediaPlayer.pause();
            }
            this.currStatus = 4;
        }
        if (this.currStatus == 5) {
            if (this.mMediaPlayer.isPlaying()) {
                LogUtil.i(TAG, "pause: ");
                this.mMediaPlayer.pause();
            }
            this.currStatus = 6;
        }
    }

    public void releasePlayer() {
        LogUtil.i(TAG, "releasePlayer: ");
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            audioManager.abandonAudioFocus(null);
            this.mAudioManager = null;
        }
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mMediaPlayer = null;
        }
        this.textureViewLayout.removeView(this.mTextureView);
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
            this.mSurface = null;
        }
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.mSurfaceTexture = null;
        }
        this.currStatus = 0;
    }

    private void initTextureView() {
        if (this.mTextureView == null) {
            NBTextureView nBTextureView = new NBTextureView(this.context);
            this.mTextureView = nBTextureView;
            nBTextureView.setSurfaceTextureListener(this);
        }
    }

    private void addTextureView() {
        this.textureViewLayout.removeView(this.mTextureView);
        this.textureViewLayout.addView(this.mTextureView, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    private void initMediaPlayer() {
        if (this.mMediaPlayer == null) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mMediaPlayer = mediaPlayer;
            mediaPlayer.setAudioStreamType(3);
            this.mMediaPlayer.setScreenOnWhilePlaying(true);
            this.mMediaPlayer.setOnPreparedListener(this);
            this.mMediaPlayer.setOnVideoSizeChangedListener(this);
            this.mMediaPlayer.setOnCompletionListener(this);
            this.mMediaPlayer.setOnErrorListener(this);
            this.mMediaPlayer.setOnInfoListener(this);
            this.mMediaPlayer.setOnBufferingUpdateListener(this);
        }
    }

    private void initAudioManager() {
        if (this.mAudioManager == null) {
            AudioManager audioManager = (AudioManager) getContext().getSystemService("audio");
            this.mAudioManager = audioManager;
            audioManager.requestAudioFocus(null, 3, 1);
        }
    }

    private void openMediaPlayer(SurfaceTexture surfaceTexture) {
        try {
            if (this.videoInfo != null) {
                this.mMediaPlayer.setDataSource(this.context, Uri.parse(this.videoInfo.getUrl()), this.videoInfo.getHeaders());
            }
            Surface surface = new Surface(surfaceTexture);
            this.mSurface = surface;
            this.mMediaPlayer.setSurface(surface);
            this.mMediaPlayer.prepareAsync();
            this.currStatus = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCurrStatus() {
        return this.currStatus;
    }

    public int getMaxVolume() {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            return audioManager.getStreamMaxVolume(3);
        }
        return 0;
    }

    public void setVolume(int i) {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            audioManager.setStreamVolume(3, i, 0);
        }
    }

    public int getVolume() {
        AudioManager audioManager = this.mAudioManager;
        if (audioManager != null) {
            return audioManager.getStreamVolume(3);
        }
        return 0;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        LogUtil.i(TAG, "onBufferingUpdate: ");
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.currStatus = 7;
        this.mContainer.setKeepScreenOn(false);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        LogUtil.w(TAG, "MediaPlayer onError: " + String.format("what %d extra %d", Integer.valueOf(i), Integer.valueOf(i2)));
        if (this.currStatus != 11) {
            this.currStatus = -1;
        }
        handleErrorCallback();
        return true;
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 3) {
            this.currStatus = 3;
            LogUtil.d(TAG, "onInfo ——> MEDIA_INFO_VIDEO_RENDERING_START：STATE_PLAYING");
            handleSuccessCallback();
            return true;
        }
        if (i == 701) {
            int i3 = this.currStatus;
            if (i3 == 4 || i3 == 6) {
                this.currStatus = 6;
                LogUtil.d(TAG, "onInfo ——> MEDIA_INFO_BUFFERING_START：STATE_BUFFERING_PAUSED");
                return true;
            }
            this.currStatus = 5;
            LogUtil.d(TAG, "onInfo ——> MEDIA_INFO_BUFFERING_START：STATE_BUFFERING_PLAYING");
            handleErrorCallback();
            return true;
        }
        if (i == 702) {
            if (this.currStatus == 5) {
                this.currStatus = 3;
                LogUtil.d(TAG, "onInfo ——> MEDIA_INFO_BUFFERING_END： STATE_PLAYING");
                handlePlayingCallback();
            }
            if (this.currStatus != 6) {
                return true;
            }
            this.currStatus = 4;
            LogUtil.d(TAG, "onInfo ——> MEDIA_INFO_BUFFERING_END： STATE_PAUSED");
            return true;
        }
        if (i == 801) {
            LogUtil.d(TAG, "视频不能seekTo，为直播视频");
            return true;
        }
        if (i == 804 || i == 805) {
            LogUtil.w(TAG, "NOT_PLAYING:" + i);
            handleErrorCallback();
            return true;
        }
        LogUtil.d(TAG, "onInfo ——> what：" + i);
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        LogUtil.d(TAG, "onPrepared: ");
        if (mediaPlayer != null) {
            this.currStatus = 2;
            mediaPlayer.start();
            this.isLoaded = true;
        }
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        LogUtil.i(TAG, "onVideoSizeChanged: ");
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        LogUtil.i(TAG, "onSurfaceTextureAvailable: ");
        SurfaceTexture surfaceTexture2 = this.mSurfaceTexture;
        if (surfaceTexture2 == null) {
            this.mSurfaceTexture = surfaceTexture;
            openMediaPlayer(surfaceTexture);
        } else {
            this.mTextureView.setSurfaceTexture(surfaceTexture2);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        LogUtil.i(TAG, "onSurfaceTextureSizeChanged: ");
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        LogUtil.i(TAG, "onSurfaceTextureDestroyed: ");
        return false;
    }

    private void handleSuccessCallback() {
        LogUtil.i(TAG, "handleSuccessCallback: ");
        if (this.videoCallback != null) {
            Task.post(new Runnable() { // from class: com.nbvideo.NBVideo.3
                @Override // java.lang.Runnable
                public void run() {
                    NBVideo.this.videoCallback.onSuccess();
                }
            });
        }
    }

    private void handleErrorCallback() {
        LogUtil.e(TAG, "handleErrorCallback: ");
        if (this.videoCallback != null) {
            Task.post(new Runnable() { // from class: com.nbvideo.NBVideo.4
                @Override // java.lang.Runnable
                public void run() {
                    NBVideo.this.videoCallback.onError();
                }
            });
        }
    }

    private void handlePlayingCallback() {
        LogUtil.i(TAG, "handlePlayingCallback: ");
        if (this.videoCallback != null) {
            Task.post(new Runnable() { // from class: com.nbvideo.NBVideo.5
                @Override // java.lang.Runnable
                public void run() {
                    NBVideo.this.videoCallback.onPlaying();
                }
            });
        }
    }

    private void handleLoadingCallback() {
        LogUtil.e(TAG, "handleLoadingCallback: ");
        if (this.videoCallback != null) {
            Task.post(new Runnable() { // from class: com.nbvideo.NBVideo.6
                @Override // java.lang.Runnable
                public void run() {
                    NBVideo.this.videoCallback.onLoading();
                }
            });
        }
    }

    public void setOnlySoundEnable(boolean z) {
        if (z) {
            this.textureViewLayout.removeView(this.mTextureView);
        } else {
            this.textureViewLayout.addView(this.mTextureView);
        }
    }

    public boolean isOnlySoundEnable() {
        return this.textureViewLayout.indexOfChild(this.mTextureView) == -1;
    }
}
