package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ELiveAppType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _Live_APP_INVALID = 0;
    public static final int _Live_APP_MOB_LIVE = 1;
    private String __T;
    private int __value;
    private static ELiveAppType[] __values = new ELiveAppType[2];
    public static final ELiveAppType Live_APP_INVALID = new ELiveAppType(0, 0, "Live_APP_INVALID");
    public static final ELiveAppType Live_APP_MOB_LIVE = new ELiveAppType(1, 1, "Live_APP_MOB_LIVE");

    public static ELiveAppType convert(int i) {
        int i2 = 0;
        while (true) {
            ELiveAppType[] eLiveAppTypeArr = __values;
            if (i2 >= eLiveAppTypeArr.length) {
                return null;
            }
            if (eLiveAppTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static ELiveAppType convert(String str) {
        int i = 0;
        while (true) {
            ELiveAppType[] eLiveAppTypeArr = __values;
            if (i >= eLiveAppTypeArr.length) {
                return null;
            }
            if (eLiveAppTypeArr[i].toString().equals(str)) {
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

    private ELiveAppType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
