package com.meizu.flyme.openidsdk;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class OpenId {
    public int code;
    public long expiredTime;
    public String type;
    public String value;

    public OpenId(String str) {
        this.type = str;
    }

    public native boolean isValid();

    public native void setDataExpired();

    public native void updateCode(int i);

    public native void updateExpiredTime(long j);

    public native void updateValue(String str);
}
