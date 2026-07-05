package com.huya.ciku.apm.model;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReclaimData {
    public static final String METRIC_NAME_RECLAIM = "reclaim";
    public static final String METRIC_NAME_START_LIVE_TOTAL_COUNT = "totallivecount";
    public String anchoruid;
    public LiveType liveType = LiveType.NORMAL;
    public ReclaimType reclaimType;
    public int voice;

    public ReclaimData liveType(LiveType liveType) {
        this.liveType = liveType;
        return this;
    }

    public ReclaimData voice(int i) {
        this.voice = i;
        return this;
    }

    public ReclaimData reclaimType(ReclaimType reclaimType) {
        this.reclaimType = reclaimType;
        return this;
    }

    public ReclaimData anchoruid(String str) {
        this.anchoruid = str;
        return this;
    }

    public enum LiveType {
        NORMAL(0, "普通开播"),
        RTMP(1, "RTMP投屏"),
        RTSP(2, "RTSP投屏"),
        NONE(3, "未开播");

        public int type;
        public String typeStr;

        LiveType(int i, String str) {
            this.type = i;
            this.typeStr = str;
        }
    }

    public enum ReclaimType {
        NONE(0, "关闭"),
        CRASH(1, "闪退"),
        RECLAIM(2, "回收"),
        KILL(3, "杀进程");

        public int type;
        public String typeStr;

        ReclaimType(int i, String str) {
            this.type = i;
            this.typeStr = str;
        }
    }

    public enum TotalLiveType {
        NORMAL(0, "普通开播"),
        SCREEN(1, "投屏开播");

        public int type;
        public String typeStr;

        TotalLiveType(int i, String str) {
            this.type = i;
            this.typeStr = str;
        }
    }
}
