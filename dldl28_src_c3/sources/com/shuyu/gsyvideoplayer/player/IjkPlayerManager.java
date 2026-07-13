package com.shuyu.gsyvideoplayer.player;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.Surface;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.shuyu.gsyvideoplayer.cache.ICacheManager;
import com.shuyu.gsyvideoplayer.model.GSYModel;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.utils.RawDataSourceProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkLibLoader;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class IjkPlayerManager extends BasePlayerManager {
    private static IjkLibLoader ijkLibLoader = null;
    private static int logLevel = 1;
    private IjkMediaPlayer mediaPlayer;
    private List<VideoOptionModel> optionModelList;
    private Surface surface;

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public int getBufferedPercentage() {
        return -1;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public boolean isSurfaceSupportLockCanvas() {
        return true;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public IMediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void initVideoPlayer(Context context, Message message, List<VideoOptionModel> list, ICacheManager iCacheManager) {
        IjkMediaPlayer ijkMediaPlayer = ijkLibLoader == null ? new IjkMediaPlayer() : new IjkMediaPlayer(ijkLibLoader);
        this.mediaPlayer = ijkMediaPlayer;
        ijkMediaPlayer.setAudioStreamType(3);
        this.mediaPlayer.setOnNativeInvokeListener(new IjkMediaPlayer.OnNativeInvokeListener() { // from class: com.shuyu.gsyvideoplayer.player.IjkPlayerManager.1
            public boolean onNativeInvoke(int i, Bundle bundle) {
                return true;
            }
        });
        GSYModel gSYModel = (GSYModel) message.obj;
        String url = gSYModel.getUrl();
        try {
            if (GSYVideoType.isMediaCodec()) {
                Debuger.printfLog("enable mediaCodec");
                this.mediaPlayer.setOption(4, "mediacodec", 1L);
                this.mediaPlayer.setOption(4, "mediacodec-auto-rotate", 1L);
                this.mediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 1L);
            }
            if (gSYModel.isCache() && iCacheManager != null) {
                iCacheManager.doCacheLogic(context, this.mediaPlayer, url, gSYModel.getMapHeadData(), gSYModel.getCachePath());
            } else if (!TextUtils.isEmpty(url)) {
                Uri uri = Uri.parse(url);
                if (uri != null && uri.getScheme() != null && uri.getScheme().equals("android.resource")) {
                    this.mediaPlayer.setDataSource(RawDataSourceProvider.create(context, uri));
                } else if (uri != null && uri.getScheme() != null && uri.getScheme().equals(DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT)) {
                    try {
                        this.mediaPlayer.setDataSource(context.getContentResolver().openFileDescriptor(uri, "r").getFileDescriptor());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    this.mediaPlayer.setDataSource(url, gSYModel.getMapHeadData());
                }
            } else {
                this.mediaPlayer.setDataSource(url, gSYModel.getMapHeadData());
            }
            this.mediaPlayer.setLooping(gSYModel.isLooping());
            if (gSYModel.getSpeed() != 1.0f && gSYModel.getSpeed() > 0.0f) {
                this.mediaPlayer.setSpeed(gSYModel.getSpeed());
            }
            IjkMediaPlayer.native_setLogLevel(logLevel);
            initIJKOption(this.mediaPlayer, list);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        initSuccess(gSYModel);
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void showDisplay(Message message) {
        IjkMediaPlayer ijkMediaPlayer;
        if (message.obj == null && (ijkMediaPlayer = this.mediaPlayer) != null) {
            ijkMediaPlayer.setSurface((Surface) null);
            return;
        }
        Surface surface = (Surface) message.obj;
        this.surface = surface;
        if (this.mediaPlayer == null || !surface.isValid()) {
            return;
        }
        this.mediaPlayer.setSurface(surface);
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void setSpeed(float f, boolean z) {
        if (f > 0.0f) {
            try {
                IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
                if (ijkMediaPlayer != null) {
                    ijkMediaPlayer.setSpeed(f);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (z) {
                VideoOptionModel videoOptionModel = new VideoOptionModel(4, "soundtouch", 1);
                List<VideoOptionModel> optionModelList = getOptionModelList();
                if (optionModelList != null) {
                    optionModelList.add(videoOptionModel);
                } else {
                    optionModelList = new ArrayList<>();
                    optionModelList.add(videoOptionModel);
                }
                setOptionModelList(optionModelList);
            }
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void setNeedMute(boolean z) {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            if (z) {
                ijkMediaPlayer.setVolume(0.0f, 0.0f);
            } else {
                ijkMediaPlayer.setVolume(1.0f, 1.0f);
            }
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void setVolume(float f, float f2) {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.setVolume(f, f2);
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
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.release();
            this.mediaPlayer = null;
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public long getNetSpeed() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getTcpSpeed();
        }
        return 0L;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void setSpeedPlaying(float f, boolean z) {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.setSpeed(f);
            this.mediaPlayer.setOption(4, "soundtouch", z ? 1L : 0L);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void start() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.start();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void stop() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.stop();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void pause() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.pause();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public int getVideoWidth() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getVideoWidth();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public int getVideoHeight() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getVideoHeight();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public boolean isPlaying() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.isPlaying();
        }
        return false;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public void seekTo(long j) {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.seekTo(j);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public long getCurrentPosition() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getCurrentPosition();
        }
        return 0L;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public long getDuration() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getDuration();
        }
        return 0L;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public int getVideoSarNum() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getVideoSarNum();
        }
        return 1;
    }

    @Override // com.shuyu.gsyvideoplayer.player.IPlayerManager
    public int getVideoSarDen() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getVideoSarDen();
        }
        return 1;
    }

    public IjkTrackInfo[] getTrackInfo() {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getTrackInfo();
        }
        return null;
    }

    public int getSelectedTrack(int i) {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getSelectedTrack(i);
        }
        return -1;
    }

    public void selectTrack(int i) {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.selectTrack(i);
        }
    }

    public void deselectTrack(int i) {
        IjkMediaPlayer ijkMediaPlayer = this.mediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.deselectTrack(i);
        }
    }

    private void initIJKOption(IjkMediaPlayer ijkMediaPlayer, List<VideoOptionModel> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (VideoOptionModel videoOptionModel : list) {
            if (videoOptionModel.getValueType() == 0) {
                ijkMediaPlayer.setOption(videoOptionModel.getCategory(), videoOptionModel.getName(), videoOptionModel.getValueInt());
            } else {
                ijkMediaPlayer.setOption(videoOptionModel.getCategory(), videoOptionModel.getName(), videoOptionModel.getValueString());
            }
        }
    }

    public List<VideoOptionModel> getOptionModelList() {
        return this.optionModelList;
    }

    public void setOptionModelList(List<VideoOptionModel> list) {
        this.optionModelList = list;
    }

    public static IjkLibLoader getIjkLibLoader() {
        return ijkLibLoader;
    }

    public static void setIjkLibLoader(IjkLibLoader ijkLibLoader2) {
        ijkLibLoader = ijkLibLoader2;
    }

    public static int getLogLevel() {
        return logLevel;
    }

    public static void setLogLevel(int i) {
        logLevel = i;
    }
}
