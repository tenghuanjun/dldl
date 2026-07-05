package com.huya.berry.client.customui.model;

import com.huya.berry.gamesdk.base.BaseCallback;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class OptionalResolution extends BaseCallback {
    public String desription;
    public int resolution;

    public OptionalResolution(int i, String str) {
        this.resolution = i;
        this.desription = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(this.resolution);
        stringBuffer.append(",");
        stringBuffer.append(this.desription);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
