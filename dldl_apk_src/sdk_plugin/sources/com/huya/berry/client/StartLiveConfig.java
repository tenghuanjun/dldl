package com.huya.berry.client;

import com.huya.berry.gamesdk.utils.CommonUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class StartLiveConfig {
    private boolean isHasLiveList;
    private boolean landscapeMode;

    private StartLiveConfig(boolean z, boolean z2) {
        this.isHasLiveList = z;
        this.landscapeMode = z2;
    }

    public boolean isHasLiveList() {
        return this.isHasLiveList;
    }

    public void setLandscapeMode(boolean z) {
        this.landscapeMode = z;
    }

    public boolean landscapeMode() {
        return this.landscapeMode;
    }

    public static class Builder {
        private boolean isHasLiveList = true;
        private boolean landscapeMode = CommonUtil.isScreenLandScape();

        public Builder smallwindow(boolean z) {
            this.isHasLiveList = !z;
            return this;
        }

        public Builder landscapeMode(boolean z) {
            this.landscapeMode = z;
            return this;
        }

        public StartLiveConfig build() {
            return new StartLiveConfig(this.isHasLiveList, this.landscapeMode);
        }
    }
}
