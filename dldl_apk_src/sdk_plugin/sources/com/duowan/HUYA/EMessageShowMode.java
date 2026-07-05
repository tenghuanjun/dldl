package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class EMessageShowMode {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _kMessageShowModeBulletScreenOnly = 2;
    public static final int _kMessageShowModeDefault = 0;
    public static final int _kMessageShowModePubScreenOnly = 1;
    private String __T;
    private int __value;
    private static EMessageShowMode[] __values = new EMessageShowMode[3];
    public static final EMessageShowMode kMessageShowModeDefault = new EMessageShowMode(0, 0, "kMessageShowModeDefault");
    public static final EMessageShowMode kMessageShowModePubScreenOnly = new EMessageShowMode(1, 1, "kMessageShowModePubScreenOnly");
    public static final EMessageShowMode kMessageShowModeBulletScreenOnly = new EMessageShowMode(2, 2, "kMessageShowModeBulletScreenOnly");

    public static EMessageShowMode convert(int i) {
        int i2 = 0;
        while (true) {
            EMessageShowMode[] eMessageShowModeArr = __values;
            if (i2 >= eMessageShowModeArr.length) {
                return null;
            }
            if (eMessageShowModeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static EMessageShowMode convert(String str) {
        int i = 0;
        while (true) {
            EMessageShowMode[] eMessageShowModeArr = __values;
            if (i >= eMessageShowModeArr.length) {
                return null;
            }
            if (eMessageShowModeArr[i].toString().equals(str)) {
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

    private EMessageShowMode(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
