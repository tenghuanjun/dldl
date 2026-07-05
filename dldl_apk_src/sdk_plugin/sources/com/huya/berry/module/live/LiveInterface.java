package com.huya.berry.module.live;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveInterface {

    public static class UpdateResolutionList {
    }

    public static class StartLive {
        public int bitrateKbps;
        public int frameRate;
        public int gameId;
        public int height;
        public int width;

        public StartLive(int i, int i2, int i3, int i4, int i5) {
            this.width = i;
            this.height = i2;
            this.bitrateKbps = i3;
            this.frameRate = i4;
            this.gameId = i5;
        }
    }

    public static class TransMsgToViewer {
        public String buffer;
        public int type;

        public TransMsgToViewer(String str, int i) {
            this.buffer = str;
            this.type = i;
        }

        public TransMsgToViewer(int i) {
            this(null, i);
        }
    }
}
