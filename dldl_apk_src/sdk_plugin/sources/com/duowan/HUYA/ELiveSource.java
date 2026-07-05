package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ELiveSource {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _MOBILE_HUYA = 2;
    public static final int _PC_HUYA = 1;
    public static final int _PC_YY = 0;
    public static final int _WEB_HUYA = 3;
    private String __T;
    private int __value;
    private static ELiveSource[] __values = new ELiveSource[4];
    public static final ELiveSource PC_YY = new ELiveSource(0, 0, "PC_YY");
    public static final ELiveSource PC_HUYA = new ELiveSource(1, 1, "PC_HUYA");
    public static final ELiveSource MOBILE_HUYA = new ELiveSource(2, 2, "MOBILE_HUYA");
    public static final ELiveSource WEB_HUYA = new ELiveSource(3, 3, "WEB_HUYA");

    public static ELiveSource convert(int i) {
        int i2 = 0;
        while (true) {
            ELiveSource[] eLiveSourceArr = __values;
            if (i2 >= eLiveSourceArr.length) {
                return null;
            }
            if (eLiveSourceArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static ELiveSource convert(String str) {
        int i = 0;
        while (true) {
            ELiveSource[] eLiveSourceArr = __values;
            if (i >= eLiveSourceArr.length) {
                return null;
            }
            if (eLiveSourceArr[i].toString().equals(str)) {
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

    private ELiveSource(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
