package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ELiveProxyType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _Live_APP_INVALID_PROXY = 0;
    public static final int _Live_APP_ONLINE_WUP_PROXY = 3;
    public static final int _Live_APP_WUP_DIRECT = 4;
    public static final int _Live_APP_WUP_LVS = 9;
    public static final int _Live_APP_WUP_PROXY = 1;
    public static final int _Live_APP_WUP_STAT = 8;
    public static final int _Live_APP_YY_PROXY = 2;
    public static final int _Live_WebSocket_Activity = 6;
    public static final int _Live_WebSocket_Others = 7;
    public static final int _Live_WebSocket_Proxy = 5;
    private String __T;
    private int __value;
    private static ELiveProxyType[] __values = new ELiveProxyType[10];
    public static final ELiveProxyType Live_APP_INVALID_PROXY = new ELiveProxyType(0, 0, "Live_APP_INVALID_PROXY");
    public static final ELiveProxyType Live_APP_WUP_PROXY = new ELiveProxyType(1, 1, "Live_APP_WUP_PROXY");
    public static final ELiveProxyType Live_APP_YY_PROXY = new ELiveProxyType(2, 2, "Live_APP_YY_PROXY");
    public static final ELiveProxyType Live_APP_ONLINE_WUP_PROXY = new ELiveProxyType(3, 3, "Live_APP_ONLINE_WUP_PROXY");
    public static final ELiveProxyType Live_APP_WUP_DIRECT = new ELiveProxyType(4, 4, "Live_APP_WUP_DIRECT");
    public static final ELiveProxyType Live_WebSocket_Proxy = new ELiveProxyType(5, 5, "Live_WebSocket_Proxy");
    public static final ELiveProxyType Live_WebSocket_Activity = new ELiveProxyType(6, 6, "Live_WebSocket_Activity");
    public static final ELiveProxyType Live_WebSocket_Others = new ELiveProxyType(7, 7, "Live_WebSocket_Others");
    public static final ELiveProxyType Live_APP_WUP_STAT = new ELiveProxyType(8, 8, "Live_APP_WUP_STAT");
    public static final ELiveProxyType Live_APP_WUP_LVS = new ELiveProxyType(9, 9, "Live_APP_WUP_LVS");

    public static ELiveProxyType convert(int i) {
        int i2 = 0;
        while (true) {
            ELiveProxyType[] eLiveProxyTypeArr = __values;
            if (i2 >= eLiveProxyTypeArr.length) {
                return null;
            }
            if (eLiveProxyTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static ELiveProxyType convert(String str) {
        int i = 0;
        while (true) {
            ELiveProxyType[] eLiveProxyTypeArr = __values;
            if (i >= eLiveProxyTypeArr.length) {
                return null;
            }
            if (eLiveProxyTypeArr[i].toString().equals(str)) {
                return __values[i];
            }
            i++;
        }
    }

    public int value() {
        return this.__value;
    }

    public String toString() {
        return this.__T;
    }

    private ELiveProxyType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
