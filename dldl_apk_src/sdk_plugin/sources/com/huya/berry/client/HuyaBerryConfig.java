package com.huya.berry.client;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HuyaBerryConfig {
    private String appId;
    private String appKey;
    private boolean cameraMode;
    private boolean debugMode;
    private int gameId;
    private boolean hidePauseBtn;
    private boolean isNeedPlay;
    private boolean isOpenBugly;
    private boolean landscapeMode;
    private boolean oneKeyGangUp;

    private HuyaBerryConfig(String str, String str2, boolean z, boolean z2, boolean z3, int i, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.appId = str;
        this.appKey = str2;
        this.debugMode = z;
        this.landscapeMode = z2;
        this.isOpenBugly = z3;
        this.gameId = i;
        this.cameraMode = z4;
        this.oneKeyGangUp = z5;
        this.isNeedPlay = z6;
        this.hidePauseBtn = z7;
    }

    public String appId() {
        return this.appId;
    }

    public String appKey() {
        return this.appKey;
    }

    public boolean debugMode() {
        return this.debugMode;
    }

    public boolean landscapeMode() {
        return this.landscapeMode;
    }

    public boolean isNeedPlay() {
        return this.isNeedPlay;
    }

    public boolean isOpenBugly() {
        return this.isOpenBugly;
    }

    public int gameId() {
        return this.gameId;
    }

    public boolean cameraMode() {
        return this.cameraMode;
    }

    public boolean oneKeyGangUp() {
        return this.oneKeyGangUp;
    }

    public boolean hidePauseBtn() {
        return this.hidePauseBtn;
    }

    public static class Builder {
        private String appId;
        private String appKey;
        private boolean debugMode;
        private int gameId;
        private boolean hidePauseBtn;
        private boolean landscapeMode = true;
        private boolean isOpenBugly = true;
        private boolean cameraMode = false;
        private boolean oneKeyGangUp = false;
        private boolean isNeedPlay = true;

        public Builder appId(String str) {
            this.appId = str;
            return this;
        }

        public Builder appKey(String str) {
            this.appKey = str;
            return this;
        }

        public Builder debugMode(boolean z) {
            this.debugMode = z;
            return this;
        }

        public Builder landscapeMode(boolean z) {
            this.landscapeMode = z;
            return this;
        }

        public Builder isNeedPlay(boolean z) {
            this.isNeedPlay = z;
            return this;
        }

        public Builder isOpenBugly(boolean z) {
            this.isOpenBugly = z;
            return this;
        }

        public Builder gameId(int i) {
            this.gameId = i;
            return this;
        }

        public Builder cameraMode(boolean z) {
            this.cameraMode = z;
            return this;
        }

        public Builder oneKeyGangUp(boolean z) {
            this.oneKeyGangUp = z;
            return this;
        }

        public Builder hidePauseBtn(boolean z) {
            this.hidePauseBtn = z;
            return this;
        }

        public HuyaBerryConfig build() {
            return new HuyaBerryConfig(this.appId, this.appKey, this.debugMode, this.landscapeMode, this.isOpenBugly, this.gameId, this.cameraMode, this.oneKeyGangUp, this.isNeedPlay, this.hidePauseBtn);
        }
    }
}
