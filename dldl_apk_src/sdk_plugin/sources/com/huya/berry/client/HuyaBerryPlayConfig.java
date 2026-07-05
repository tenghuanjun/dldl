package com.huya.berry.client;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HuyaBerryPlayConfig {
    private boolean floatSendDanmu;
    private boolean normalSendDanmu;
    private boolean showFullScreen;
    private boolean showLiveInfo;
    private boolean showQuality;
    private boolean showSubscribe;
    private boolean showSwitchDanmu;
    private boolean showSwitchVoice;

    private HuyaBerryPlayConfig(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        this.floatSendDanmu = z;
        this.normalSendDanmu = z2;
        this.showQuality = z3;
        this.showSwitchDanmu = z4;
        this.showSwitchVoice = z5;
        this.showFullScreen = z6;
        this.showLiveInfo = z7;
        this.showSubscribe = z8;
    }

    public boolean floatSendDanmu() {
        return this.floatSendDanmu;
    }

    public boolean normalSendDanmu() {
        return this.normalSendDanmu;
    }

    public boolean showQuality() {
        return this.showQuality;
    }

    public boolean showSwitchDanmu() {
        return this.showSwitchDanmu;
    }

    public boolean showSwitchVoice() {
        return this.showSwitchVoice;
    }

    public boolean showFullScreen() {
        return this.showFullScreen;
    }

    public boolean showLiveInfo() {
        return this.showLiveInfo;
    }

    public boolean showSubscribe() {
        return this.showSubscribe;
    }

    public static class Builder {
        private boolean showSubscribe;
        private boolean floatSendDanmu = true;
        private boolean normalSendDanmu = true;
        private boolean showQuality = true;
        private boolean showSwitchDanmu = true;
        private boolean showSwitchVoice = true;
        private boolean showFullScreen = true;
        private boolean showLiveInfo = true;

        public Builder floatSendDanmu(boolean z) {
            this.floatSendDanmu = z;
            return this;
        }

        public Builder normalSendDanmu(boolean z) {
            this.normalSendDanmu = z;
            return this;
        }

        public Builder showQuality(boolean z) {
            this.showQuality = z;
            return this;
        }

        public Builder showSwitchDanmu(boolean z) {
            this.showSwitchDanmu = z;
            return this;
        }

        public Builder showSwitchVoice(boolean z) {
            this.showSwitchVoice = z;
            return this;
        }

        public Builder showFullScreen(boolean z) {
            this.showFullScreen = z;
            return this;
        }

        public Builder showLiveInfo(boolean z) {
            this.showLiveInfo = z;
            return this;
        }

        public Builder showSubscribe(boolean z) {
            this.showSubscribe = z;
            return this;
        }

        public HuyaBerryPlayConfig build() {
            return new HuyaBerryPlayConfig(this.floatSendDanmu, this.normalSendDanmu, this.showQuality, this.showSwitchDanmu, this.showSwitchVoice, this.showFullScreen, this.showLiveInfo, this.showSubscribe);
        }
    }
}
