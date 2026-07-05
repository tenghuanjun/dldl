package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class NobleLevelAttrType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _NO_EXTRA_ATTR = 0;
    public static final int _SUPER_GOD = 66;
    private String __T;
    private int __value;
    private static NobleLevelAttrType[] __values = new NobleLevelAttrType[2];
    public static final NobleLevelAttrType NO_EXTRA_ATTR = new NobleLevelAttrType(0, 0, "NO_EXTRA_ATTR");
    public static final NobleLevelAttrType SUPER_GOD = new NobleLevelAttrType(1, 66, "SUPER_GOD");

    public static NobleLevelAttrType convert(int i) {
        int i2 = 0;
        while (true) {
            NobleLevelAttrType[] nobleLevelAttrTypeArr = __values;
            if (i2 >= nobleLevelAttrTypeArr.length) {
                return null;
            }
            if (nobleLevelAttrTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static NobleLevelAttrType convert(String str) {
        int i = 0;
        while (true) {
            NobleLevelAttrType[] nobleLevelAttrTypeArr = __values;
            if (i >= nobleLevelAttrTypeArr.length) {
                return null;
            }
            if (nobleLevelAttrTypeArr[i].toString().equals(str)) {
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

    private NobleLevelAttrType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
