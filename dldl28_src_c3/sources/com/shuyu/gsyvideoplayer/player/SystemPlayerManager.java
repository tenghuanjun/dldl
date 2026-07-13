package com.shuyu.gsyvideoplayer.player;

import android.content.Context;
import android.media.PlaybackParams;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.view.Surface;
import com.bun.miitmdid.x$;
import com.shuyu.gsyvideoplayer.cache.ICacheManager;
import com.shuyu.gsyvideoplayer.model.GSYModel;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import java.util.List;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class SystemPlayerManager extends BasePlayerManager {
    private Context context;
    private AndroidMediaPlayer mediaPlayer;
    private boolean release;
    private Surface surface;
    private long lastTotalRxBytes = 0;
    private long lastTimeStamp = 0;
    private boolean isPlaying = false;

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public int getBufferedPercentage() {
        return -1;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public boolean isSurfaceSupportLockCanvas() {
        return false;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void setSpeedPlaying(float f, boolean z) {
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public IMediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void initVideoPlayer(Context context, Message message, List<VideoOptionModel> list, ICacheManager iCacheManager) {
        this.context = context.getApplicationContext();
        AndroidMediaPlayer androidMediaPlayer = new AndroidMediaPlayer();
        this.mediaPlayer = androidMediaPlayer;
        androidMediaPlayer.setAudioStreamType(3);
        this.release = false;
        GSYModel gSYModel = (GSYModel) message.obj;
        try {
            if (gSYModel.isCache() && iCacheManager != null) {
                iCacheManager.doCacheLogic(context, this.mediaPlayer, gSYModel.getUrl(), gSYModel.getMapHeadData(), gSYModel.getCachePath());
            } else {
                this.mediaPlayer.setDataSource(context, Uri.parse(gSYModel.getUrl()), gSYModel.getMapHeadData());
            }
            this.mediaPlayer.setLooping(gSYModel.isLooping());
            if (gSYModel.getSpeed() != 1.0f && gSYModel.getSpeed() > 0.0f) {
                setSpeed(gSYModel.getSpeed());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        initSuccess(gSYModel);
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void showDisplay(Message message) {
        AndroidMediaPlayer androidMediaPlayer;
        if (message.obj == null && (androidMediaPlayer = this.mediaPlayer) != null && !this.release) {
            androidMediaPlayer.setSurface((Surface) null);
            return;
        }
        if (message.obj != null) {
            Surface surface = (Surface) message.obj;
            this.surface = surface;
            if (this.mediaPlayer != null && surface.isValid() && !this.release) {
                this.mediaPlayer.setSurface(surface);
            }
            if (this.isPlaying) {
                return;
            }
            pause();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void setSpeed(float f, boolean z) {
        setSpeed(f);
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void setNeedMute(boolean z) {
        try {
            AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
            if (androidMediaPlayer != null && !this.release) {
                if (z) {
                    androidMediaPlayer.setVolume(0.0f, 0.0f);
                } else {
                    androidMediaPlayer.setVolume(1.0f, 1.0f);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void setVolume(float f, float f2) {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            androidMediaPlayer.setVolume(f, f2);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void releaseSurface() {
        if (this.surface != null) {
            this.surface = null;
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void release() {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            this.release = true;
            androidMediaPlayer.release();
            this.mediaPlayer = null;
        }
        this.lastTotalRxBytes = 0L;
        this.lastTimeStamp = 0L;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public long getNetSpeed() {
        if (this.mediaPlayer != null) {
            return getNetSpeed(this.context);
        }
        return 0L;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void start() {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            androidMediaPlayer.start();
            this.isPlaying = true;
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void stop() {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            androidMediaPlayer.stop();
            this.isPlaying = false;
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void pause() {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            androidMediaPlayer.pause();
            this.isPlaying = false;
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public int getVideoWidth() {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            return androidMediaPlayer.getVideoWidth();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public int getVideoHeight() {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            return androidMediaPlayer.getVideoHeight();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public boolean isPlaying() {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            return androidMediaPlayer.isPlaying();
        }
        return false;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void seekTo(long j) {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            androidMediaPlayer.seekTo(j);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public long getCurrentPosition() {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            return androidMediaPlayer.getCurrentPosition();
        }
        return 0L;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public long getDuration() {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            return androidMediaPlayer.getDuration();
        }
        return 0L;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public int getVideoSarNum() {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            return androidMediaPlayer.getVideoSarNum();
        }
        return 1;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public int getVideoSarDen() {
        AndroidMediaPlayer androidMediaPlayer = this.mediaPlayer;
        if (androidMediaPlayer != null) {
            return androidMediaPlayer.getVideoSarDen();
        }
        return 1;
    }

    private void setSpeed(float f) {
        AndroidMediaPlayer androidMediaPlayer;
        if (this.release || (androidMediaPlayer = this.mediaPlayer) == null || androidMediaPlayer.getInternalMediaPlayer() == null || !this.mediaPlayer.isPlayable()) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                x$.ExternalSyntheticApiModelOutline0.m$1();
                PlaybackParams playbackParamsM = x$.ExternalSyntheticApiModelOutline0.m();
                x$.ExternalSyntheticApiModelOutline0.m(playbackParamsM, f);
                x$.ExternalSyntheticApiModelOutline0.m(this.mediaPlayer.getInternalMediaPlayer(), playbackParamsM);
            } else {
                Debuger.printfError(" not support setSpeed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long getNetSpeed(Context context) {
        if (context == null) {
            return 0L;
        }
        long totalRxBytes = TrafficStats.getUidRxBytes(context.getApplicationInfo().uid) == -1 ? 0L : TrafficStats.getTotalRxBytes() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS;
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - this.lastTimeStamp;
        if (j == 0) {
            return j;
        }
        long j2 = ((totalRxBytes - this.lastTotalRxBytes) * 1000) / j;
        this.lastTimeStamp = jCurrentTimeMillis;
        this.lastTotalRxBytes = totalRxBytes;
        return j2;
    }
}
