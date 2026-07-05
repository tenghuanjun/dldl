package com.huya.berry.sdkplayer.player;

import android.text.TextUtils;
import com.duowan.auk.util.L;
import com.huya.berry.module.Player.PlayerHelper;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StreamInfoHelper {
    private static final String TAG = "StreamInfoHelper";
    private static Map<Integer, StreamInfoHelper> mStreamInfoHelperMap = new HashMap();
    private playConfig mPlayConfig = new playConfig();

    public class playConfig {
        public int audioMinBuffer;
        public int bitRate;
        public String codecType;
        public int hardDecode;
        public int lindId;
        public String roomId;
        public String streamName;
        public String streamType;
        public int videoMinBuffer;
        public int loginModel = 1;
        public boolean useHttps = true;

        public playConfig() {
        }

        public String getPlaybackUrl() {
            if (this.streamName == null) {
                return null;
            }
            String str = PlayerHelper.HlsUrl.get(Integer.valueOf(this.lindId));
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            String str2 = str + "&ratio=" + this.bitRate;
            if (TextUtils.isEmpty(str2)) {
                return null;
            }
            return str2;
        }
    }

    public static synchronized StreamInfoHelper getInterfaceById(int i) {
        if (!mStreamInfoHelperMap.containsKey(Integer.valueOf(i))) {
            mStreamInfoHelperMap.put(Integer.valueOf(i), new StreamInfoHelper());
        }
        return mStreamInfoHelperMap.get(Integer.valueOf(i));
    }

    private StreamInfoHelper() {
    }

    public void setPlayConfig(String str, String str2, String str3, String str4, int i, int i2, int i3, int i4, int i5, int i6) {
        this.mPlayConfig.roomId = str;
        this.mPlayConfig.streamName = str2;
        this.mPlayConfig.streamType = str3;
        this.mPlayConfig.codecType = str4;
        this.mPlayConfig.lindId = i;
        this.mPlayConfig.bitRate = i2;
        this.mPlayConfig.hardDecode = i3;
        this.mPlayConfig.loginModel = i4;
        this.mPlayConfig.audioMinBuffer = i5;
        this.mPlayConfig.videoMinBuffer = i6;
        L.info(TAG, "setPlayConfig streamName = " + str2 + " roomId " + str + " lindId " + i + " bitRate " + i2);
    }

    public playConfig getPlayConfig() {
        return this.mPlayConfig;
    }
}
