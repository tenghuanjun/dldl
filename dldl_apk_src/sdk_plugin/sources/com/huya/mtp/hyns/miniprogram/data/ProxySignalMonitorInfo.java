package com.huya.mtp.hyns.miniprogram.data;

import com.duowan.taf.jce.JceStruct;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ProxySignalMonitorInfo {
    private int mCommand;
    private long mHeartBeatInterval;
    private JceStruct mLoginReq;

    public ProxySignalMonitorInfo(JceStruct jceStruct, long j, int i) {
        this.mLoginReq = jceStruct;
        this.mHeartBeatInterval = j;
        this.mCommand = i;
    }

    public JceStruct getLoginReq() {
        return this.mLoginReq;
    }

    public long getHeartBeatInterval() {
        return this.mHeartBeatInterval;
    }

    public int getCommand() {
        return this.mCommand;
    }
}
