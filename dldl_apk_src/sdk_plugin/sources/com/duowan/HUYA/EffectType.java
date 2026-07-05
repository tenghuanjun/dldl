package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class EffectType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _EFFECTTYPE_MAIN_BIGGIFT = 7;
    public static final int _EFFECTTYPE_MAIN_LOW = 4;
    public static final int _EFFECTTYPE_MAIN_MID = 5;
    public static final int _EFFECTTYPE_MAIN_TOP = 6;
    public static final int _EFFECTTYPE_NULL = 0;
    public static final int _EFFECTTYPE_QTYH = 2;
    public static final int _EFFECTTYPE_XXXY = 3;
    public static final int _EFFECTTYPE_YJZQ = 1;
    private String __T;
    private int __value;
    private static EffectType[] __values = new EffectType[8];
    public static final EffectType EFFECTTYPE_NULL = new EffectType(0, 0, "EFFECTTYPE_NULL");
    public static final EffectType EFFECTTYPE_YJZQ = new EffectType(1, 1, "EFFECTTYPE_YJZQ");
    public static final EffectType EFFECTTYPE_QTYH = new EffectType(2, 2, "EFFECTTYPE_QTYH");
    public static final EffectType EFFECTTYPE_XXXY = new EffectType(3, 3, "EFFECTTYPE_XXXY");
    public static final EffectType EFFECTTYPE_MAIN_LOW = new EffectType(4, 4, "EFFECTTYPE_MAIN_LOW");
    public static final EffectType EFFECTTYPE_MAIN_MID = new EffectType(5, 5, "EFFECTTYPE_MAIN_MID");
    public static final EffectType EFFECTTYPE_MAIN_TOP = new EffectType(6, 6, "EFFECTTYPE_MAIN_TOP");
    public static final EffectType EFFECTTYPE_MAIN_BIGGIFT = new EffectType(7, 7, "EFFECTTYPE_MAIN_BIGGIFT");

    public static EffectType convert(int i) {
        int i2 = 0;
        while (true) {
            EffectType[] effectTypeArr = __values;
            if (i2 >= effectTypeArr.length) {
                return null;
            }
            if (effectTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static EffectType convert(String str) {
        int i = 0;
        while (true) {
            EffectType[] effectTypeArr = __values;
            if (i >= effectTypeArr.length) {
                return null;
            }
            if (effectTypeArr[i].toString().equals(str)) {
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

    private EffectType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
