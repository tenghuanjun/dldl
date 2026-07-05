package com.huya.berry.client.customui.model;

import com.huya.berry.gamesdk.base.BaseCallback;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SubscribeInfo extends BaseCallback {
    public boolean isLogin;
    public boolean isSubscribe;
    public String msg = new String();

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(this.isLogin);
        stringBuffer.append(",");
        stringBuffer.append(this.isSubscribe);
        stringBuffer.append(",");
        stringBuffer.append(this.msg);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
