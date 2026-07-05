package com.huya.berry.client.customui.model;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BitRateInfo {
    public int bitRate;
    public String disPlayName = new String();

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(this.bitRate);
        stringBuffer.append(",");
        stringBuffer.append(this.disPlayName);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
