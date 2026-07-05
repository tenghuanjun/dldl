package com.huya.berry.client.customui.model;

import com.huya.berry.gamesdk.base.BaseCallback;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class RtmpPushInfo extends BaseCallback {
    public boolean isSuccess;
    public String liveUrl;
    public String tip;

    public RtmpPushInfo(String str, String str2, boolean z) {
        this.liveUrl = str;
        this.tip = str2;
        this.isSuccess = z;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(this.liveUrl);
        stringBuffer.append(",");
        stringBuffer.append(this.tip);
        stringBuffer.append(",");
        stringBuffer.append(this.isSuccess);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
