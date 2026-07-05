package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ScreenType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _ST_Horizonal = 1;
    public static final int _ST_Vertical = 0;
    private String __T;
    private int __value;
    private static ScreenType[] __values = new ScreenType[2];
    public static final ScreenType ST_Vertical = new ScreenType(0, 0, "ST_Vertical");
    public static final ScreenType ST_Horizonal = new ScreenType(1, 1, "ST_Horizonal");

    public static ScreenType convert(int i) {
        int i2 = 0;
        while (true) {
            ScreenType[] screenTypeArr = __values;
            if (i2 >= screenTypeArr.length) {
                return null;
            }
            if (screenTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static ScreenType convert(String str) {
        int i = 0;
        while (true) {
            ScreenType[] screenTypeArr = __values;
            if (i >= screenTypeArr.length) {
                return null;
            }
            if (screenTypeArr[i].toString().equals(str)) {
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

    private ScreenType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
