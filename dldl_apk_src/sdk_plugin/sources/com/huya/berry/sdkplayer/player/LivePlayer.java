package com.huya.berry.sdkplayer.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.asignal.SignalCenter;
import com.duowan.auk.asignal.notify.PropertySet;
import com.duowan.auk.module.ArkProperties;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.util.L;
import com.huya.berry.endlive.event.HuyaSdkInterface;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.wup.WupHelper;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.module.help.LiveHelper;
import com.huya.berry.sdkplayer.floats.FloatingVideoMgr;
import com.huya.berry.sdkplayer.floats.view.HyberryVideoView;
import java.util.HashMap;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LivePlayer {
    private static final String TAG = "LivePlayer";
    private HyberryVideoView mLivePlayerView;
    private MediaPlayer mMediaPlayer;
    private StreamInfoHelper mStreamInfoHelper;
    private String mUrlStr;
    private Runnable replay = new Runnable() { // from class: com.huya.berry.sdkplayer.player.LivePlayer.4
        @Override // java.lang.Runnable
        public void run() {
            L.info(LivePlayer.TAG, "MediaPlayer replay ");
            if (TextUtils.isEmpty(LivePlayer.this.mUrlStr) || LivePlayer.this.mLivePlayerView == null) {
                return;
            }
            if (LivePlayer.this.mLivePlayerView.isPlaying()) {
                LivePlayer.this.mLivePlayerView.postDelayed(LivePlayer.this.replay, 120000L);
                return;
            }
            L.info(LivePlayer.TAG, "MediaPlayer real replay ");
            LivePlayer.this.mLivePlayerView.stopPlayback();
            LivePlayer.this.mLivePlayerView.setVideoURI(Uri.parse(LivePlayer.this.mUrlStr));
        }
    };

    public static LivePlayer create(HyberryVideoView hyberryVideoView, Context context, int i) {
        return new LivePlayer(hyberryVideoView, context, i);
    }

    private LivePlayer(HyberryVideoView hyberryVideoView, Context context, int i) {
        this.mLivePlayerView = null;
        this.mLivePlayerView = hyberryVideoView;
        this.mStreamInfoHelper = StreamInfoHelper.getInterfaceById(i);
        initPlayer();
        SignalCenter.register(this);
    }

    public void setMuteAudio(boolean z) {
        try {
            if (z) {
                if (this.mMediaPlayer != null) {
                    this.mMediaPlayer.setVolume(0.0f, 0.0f);
                }
            } else if (this.mMediaPlayer != null) {
                this.mMediaPlayer.setVolume(1.0f, 1.0f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPlayer() {
        String playbackUrl = this.mStreamInfoHelper.getPlayConfig().getPlaybackUrl();
        if (playbackUrl != null) {
            if (playbackUrl.indexOf(IDataSource.SCHEME_HTTPS_TAG) == -1) {
                playbackUrl = playbackUrl.replace(IDataSource.SCHEME_HTTP_TAG, IDataSource.SCHEME_HTTPS_TAG);
            }
            this.mUrlStr = playbackUrl;
            L.info(TAG, "playUrl is " + playbackUrl);
            HashMap map = new HashMap();
            map.put("User-Agent", WupHelper.getSHuYaUA());
            this.mLivePlayerView.setVideoURI(Uri.parse(this.mUrlStr), map);
            this.mLivePlayerView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.huya.berry.sdkplayer.player.LivePlayer.1
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer) {
                    if (LivePlayer.this.mLivePlayerView == null) {
                        return;
                    }
                    LivePlayer.this.mMediaPlayer = mediaPlayer;
                    LivePlayer.this.mLivePlayerView.start();
                    ArkUtils.send(new HuyaSdkInterface.VideoRenderStart());
                    LivePlayer.this.mLivePlayerView.removeCallbacks(LivePlayer.this.replay);
                    LivePlayer.this.mLivePlayerView.postDelayed(LivePlayer.this.replay, 120000L);
                }
            });
            this.mLivePlayerView.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.huya.berry.sdkplayer.player.LivePlayer.2
                @Override // android.media.MediaPlayer.OnErrorListener
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    if (LivePlayer.this.mLivePlayerView != null) {
                        LivePlayer.this.mLivePlayerView.stopPlayback();
                    }
                    ArkUtils.send(new HuyaSdkInterface.VideoRenderStop());
                    return true;
                }
            });
            this.mLivePlayerView.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: com.huya.berry.sdkplayer.player.LivePlayer.3
                @Override // android.media.MediaPlayer.OnInfoListener
                public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                    L.info(LivePlayer.TAG, "MediaPlayer change " + i);
                    switch (i) {
                        case 700:
                        case 702:
                            ArkUtils.send(new HuyaSdkInterface.VideoRenderStart());
                            break;
                        case 701:
                            if (LivePlayer.this.mLivePlayerView != null) {
                                LivePlayer.this.mLivePlayerView.postDelayed(LivePlayer.this.replay, 1000L);
                            }
                            break;
                    }
                    return false;
                }
            });
            return;
        }
        L.error(TAG, "playUrl is null");
        if (PlayerHelper.mActivity == null || PlayerHelper.mUserRecItem == null) {
            return;
        }
        LiveHelper.startApp(PlayerHelper.mActivity, PlayerHelper.mUserRecItem, WupHelper.getSHuYaUA(), SdkProperties.gameId.get().intValue());
        FloatingVideoMgr.getInstance().destroy();
    }

    @IASlot(mark = {ArkProperties.MarkNetworkAvailable})
    public void onNetworkChange(PropertySet<Boolean> propertySet) {
        if (this.mLivePlayerView == null || propertySet.oldValue.booleanValue() || !propertySet.newValue.booleanValue()) {
            return;
        }
        L.info(TAG, "net change ");
        ArkUtils.send(new HuyaSdkInterface.VideoRenderStop());
    }

    public void startPlay() {
        HyberryVideoView hyberryVideoView = this.mLivePlayerView;
        if (hyberryVideoView != null) {
            hyberryVideoView.start();
            this.mLivePlayerView.postDelayed(this.replay, 120000L);
        }
    }

    public void pausePlay() {
        HyberryVideoView hyberryVideoView = this.mLivePlayerView;
        if (hyberryVideoView == null || !hyberryVideoView.isPlaying()) {
            return;
        }
        this.mLivePlayerView.pause();
        this.mLivePlayerView.removeCallbacks(this.replay);
    }

    public void destroy() {
        SignalCenter.unregister(this);
        HyberryVideoView hyberryVideoView = this.mLivePlayerView;
        if (hyberryVideoView != null) {
            hyberryVideoView.stopPlayback();
            this.mLivePlayerView.setOnInfoListener(null);
            this.mLivePlayerView.setOnPreparedListener(null);
            this.mLivePlayerView.removeCallbacks(this.replay);
            this.mMediaPlayer = null;
            this.mLivePlayerView = null;
        }
    }
}
