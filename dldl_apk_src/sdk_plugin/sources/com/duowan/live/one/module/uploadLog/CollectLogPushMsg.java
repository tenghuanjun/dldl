package com.duowan.live.one.module.uploadLog;

import com.duowan.auk.NoProguard;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CollectLogPushMsg implements NoProguard {
    private Detail details;
    private int maxFileSize;
    private String msgType;
    private long serverTime;

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String str) {
        this.msgType = str;
    }

    public long getServerTime() {
        return this.serverTime;
    }

    public void setServerTime(long j) {
        this.serverTime = j;
    }

    public int getMaxFileSize() {
        return this.maxFileSize;
    }

    public void setMaxFileSize(int i) {
        this.maxFileSize = i;
    }

    public Detail getDetails() {
        return this.details;
    }

    public void setDetails(Detail detail) {
        this.details = detail;
    }

    public static class Detail implements NoProguard {
        String appId;
        String appVersion;
        String clientCmd;
        String deviceType;
        String fbId;
        String isRequireSupplementary;
        long logBeginTime;
        long logDeadlineTime;
        long logEndTime;

        public String getClientCmd() {
            return this.clientCmd;
        }

        public void setClientCmd(String str) {
            this.clientCmd = str;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String str) {
            this.appId = str;
        }

        public String getFbId() {
            return this.fbId;
        }

        public void setFbId(String str) {
            this.fbId = str;
        }

        public String getIsRequireSupplementary() {
            return this.isRequireSupplementary;
        }

        public void setIsRequireSupplementary(String str) {
            this.isRequireSupplementary = str;
        }

        public long getLogBeginTime() {
            return this.logBeginTime;
        }

        public void setLogBeginTime(long j) {
            this.logBeginTime = j;
        }

        public long getLogEndTime() {
            return this.logEndTime;
        }

        public void setLogEndTime(long j) {
            this.logEndTime = j;
        }

        public long getLogDeadlineTime() {
            return this.logDeadlineTime;
        }

        public void setLogDeadlineTime(long j) {
            this.logDeadlineTime = j;
        }

        public String getAppVersion() {
            return this.appVersion;
        }

        public void setAppVersion(String str) {
            this.appVersion = str;
        }

        public String getDeviceType() {
            return this.deviceType;
        }

        public void setDeviceType(String str) {
            this.deviceType = str;
        }
    }
}
