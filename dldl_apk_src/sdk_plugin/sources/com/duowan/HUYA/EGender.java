package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class EGender {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _FEMALE = 1;
    public static final int _MALE = 0;
    private String __T;
    private int __value;
    private static EGender[] __values = new EGender[2];
    public static final EGender MALE = new EGender(0, 0, "MALE");
    public static final EGender FEMALE = new EGender(1, 1, "FEMALE");

    public static EGender convert(int i) {
        int i2 = 0;
        while (true) {
            EGender[] eGenderArr = __values;
            if (i2 >= eGenderArr.length) {
                return null;
            }
            if (eGenderArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static EGender convert(String str) {
        int i = 0;
        while (true) {
            EGender[] eGenderArr = __values;
            if (i >= eGenderArr.length) {
                return null;
            }
            if (eGenderArr[i].toString().equals(str)) {
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

    private EGender(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
