package com.huya.mtp.hyns.miniprogram.jce;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class EWsProxyPacketType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _EWSPROXYPACKETTYPE_HEARTBEAT = 1;
    public static final int _EWSPROXYPACKETTYPE_PROXY_BIN = 3;
    public static final int _EWSPROXYPACKETTYPE_PROXY_TEXT = 2;
    private String __T;
    private int __value;
    private static EWsProxyPacketType[] __values = new EWsProxyPacketType[3];
    public static final EWsProxyPacketType EWSPROXYPACKETTYPE_HEARTBEAT = new EWsProxyPacketType(0, 1, "EWSPROXYPACKETTYPE_HEARTBEAT");
    public static final EWsProxyPacketType EWSPROXYPACKETTYPE_PROXY_TEXT = new EWsProxyPacketType(1, 2, "EWSPROXYPACKETTYPE_PROXY_TEXT");
    public static final EWsProxyPacketType EWSPROXYPACKETTYPE_PROXY_BIN = new EWsProxyPacketType(2, 3, "EWSPROXYPACKETTYPE_PROXY_BIN");

    public static EWsProxyPacketType convert(int i) {
        int i2 = 0;
        while (true) {
            EWsProxyPacketType[] eWsProxyPacketTypeArr = __values;
            if (i2 >= eWsProxyPacketTypeArr.length) {
                return null;
            }
            if (eWsProxyPacketTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static EWsProxyPacketType convert(String str) {
        int i = 0;
        while (true) {
            EWsProxyPacketType[] eWsProxyPacketTypeArr = __values;
            if (i >= eWsProxyPacketTypeArr.length) {
                return null;
            }
            if (eWsProxyPacketTypeArr[i].toString().equals(str)) {
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

    private EWsProxyPacketType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
