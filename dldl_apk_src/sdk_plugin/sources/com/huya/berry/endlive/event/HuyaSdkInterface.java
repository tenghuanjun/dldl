package com.huya.berry.endlive.event;

import com.duowan.auk.NoProguard;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HuyaSdkInterface implements NoProguard {

    public static class GetLiveInfo implements NoProguard {
    }

    public static class SwitchLive implements NoProguard {
    }

    public static class SwitchRate implements NoProguard {
    }

    public static class VideoRenderStart implements NoProguard {
    }

    public static class VideoRenderStop implements NoProguard {
    }

    public static class GetLiveInfoByGame implements NoProguard {
        public long channelId;
        public long roomId;
        public long subId;
        public long uid;

        public GetLiveInfoByGame(long j, long j2, long j3, long j4) {
            this.channelId = j;
            this.subId = j2;
            this.uid = j3;
            this.roomId = j4;
        }
    }
}
