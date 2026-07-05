package com.huya.berry.sdkplayer.player;

import android.text.TextUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.sdkplayer.floats.view.HyberryVideoView;
import com.youme.imsdk.YIMConstInfo;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HYMediaSoftDecodePlayer {
    private static final String TAG = "HYMediaSoftDecodePlayer";
    private StreamInfoHelper mStreamInfoHelper;
    private LivePlayer player;

    public void createPlayer(HyberryVideoView hyberryVideoView) {
        destroy();
        if (this.mStreamInfoHelper == null) {
            this.mStreamInfoHelper = StreamInfoHelper.getInterfaceById(0);
        }
        String str = PlayerHelper.line + "";
        String str2 = PlayerHelper.bitRate + "";
        String str3 = PlayerHelper.streamNames.get(Integer.valueOf(PlayerHelper.line));
        if (TextUtils.isEmpty(str3)) {
            str3 = PlayerHelper.streamName;
        }
        this.mStreamInfoHelper.setPlayConfig(PlayerHelper.roomId + "", str3, "flv", "h264", TextUtils.isEmpty(str) ? 3 : Integer.valueOf(str).intValue(), (TextUtils.isEmpty(str2) || "-1".equals(str2)) ? 0 : Integer.valueOf(str2).intValue(), 1, 1, YIMConstInfo.Errorcode.IsWaitingSend, YIMConstInfo.Errorcode.IsWaitingSend);
        this.mStreamInfoHelper.getPlayConfig().useHttps = false;
        this.player = LivePlayer.create(hyberryVideoView, ArkValue.gContext, 0);
        L.info(TAG, "createPlayer ");
    }

    public void setMuteAudio(boolean z) {
        LivePlayer livePlayer = this.player;
        if (livePlayer != null) {
            livePlayer.setMuteAudio(!z);
        }
    }

    public void startPlay() {
        LivePlayer livePlayer = this.player;
        if (livePlayer != null) {
            livePlayer.startPlay();
        }
    }

    public void pausePlay() {
        LivePlayer livePlayer = this.player;
        if (livePlayer != null) {
            livePlayer.pausePlay();
        }
    }

    public void destroy() {
        LivePlayer livePlayer = this.player;
        if (livePlayer != null) {
            livePlayer.destroy();
        }
        this.player = null;
    }
}
