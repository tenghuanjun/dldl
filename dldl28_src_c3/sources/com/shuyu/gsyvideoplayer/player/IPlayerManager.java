package com.shuyu.gsyvideoplayer.player;

import android.content.Context;
import android.os.Message;
import com.shuyu.gsyvideoplayer.cache.ICacheManager;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import java.util.List;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface IPlayerManager {
    int getBufferedPercentage();

    long getCurrentPosition();

    long getDuration();

    IMediaPlayer getMediaPlayer();

    long getNetSpeed();

    int getVideoHeight();

    int getVideoSarDen();

    int getVideoSarNum();

    int getVideoWidth();

    void initVideoPlayer(Context context, Message message, List<VideoOptionModel> list, ICacheManager iCacheManager);

    boolean isPlaying();

    boolean isSurfaceSupportLockCanvas();

    void pause();

    void release();

    void releaseSurface();

    void seekTo(long j);

    void setNeedMute(boolean z);

    void setSpeed(float f, boolean z);

    void setSpeedPlaying(float f, boolean z);

    void setVolume(float f, float f2);

    void showDisplay(Message message);

    void start();

    void stop();
}
