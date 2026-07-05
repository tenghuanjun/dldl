package com.huya.mtp.hyns.api;

import com.huya.mtp.hyns.NSException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface OnConnectParamCallBack {
    void onRequestError(NSException nSException);

    void onRequestSuccess(CloudGameHostData cloudGameHostData);

    public static class CloudGameHostData {
        public String sMessage = "";
        public String sRoomId = "";
        public String sServerIP = "";
        public int iPort = 0;
        public int iHeartBeatInterval = 0;
        public String sExtraData = "";

        public String getsMessage() {
            return this.sMessage;
        }

        public CloudGameHostData setsMessage(String str) {
            this.sMessage = str;
            return this;
        }

        public String getsRoomId() {
            return this.sRoomId;
        }

        public CloudGameHostData setsRoomId(String str) {
            this.sRoomId = str;
            return this;
        }

        public String getsServerIP() {
            return this.sServerIP;
        }

        public CloudGameHostData setsServerIP(String str) {
            this.sServerIP = str;
            return this;
        }

        public int getiPort() {
            return this.iPort;
        }

        public CloudGameHostData setiPort(int i) {
            this.iPort = i;
            return this;
        }

        public int getiHeartBeatInterval() {
            return this.iHeartBeatInterval;
        }

        public CloudGameHostData setiHeartBeatInterval(int i) {
            this.iHeartBeatInterval = i;
            return this;
        }

        public String getsExtraData() {
            return this.sExtraData;
        }

        public CloudGameHostData setsExtraData(String str) {
            this.sExtraData = str;
            return this;
        }
    }
}
