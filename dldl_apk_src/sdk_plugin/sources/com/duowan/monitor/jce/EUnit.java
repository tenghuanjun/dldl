package com.duowan.monitor.jce;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class EUnit {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _EUnit_Bits = 9;
    public static final int _EUnit_BitsPerSecond = 21;
    public static final int _EUnit_Bytes = 4;
    public static final int _EUnit_BytesPerSecond = 16;
    public static final int _EUnit_Count = 15;
    public static final int _EUnit_CountPerSecond = 26;
    public static final int _EUnit_Gigabits = 12;
    public static final int _EUnit_GigabitsPerSecond = 24;
    public static final int _EUnit_Gigabytes = 7;
    public static final int _EUnit_GigabytesPerSecond = 19;
    public static final int _EUnit_Kilobits = 10;
    public static final int _EUnit_KilobitsPerSecond = 22;
    public static final int _EUnit_Kilobytes = 5;
    public static final int _EUnit_KilobytesPerSecond = 17;
    public static final int _EUnit_Megabits = 11;
    public static final int _EUnit_MegabitsPerSecond = 23;
    public static final int _EUnit_Megabytes = 6;
    public static final int _EUnit_MegabytesPerSecond = 18;
    public static final int _EUnit_Microseconds = 2;
    public static final int _EUnit_Milliseconds = 3;
    public static final int _EUnit_None = 0;
    public static final int _EUnit_Percent = 14;
    public static final int _EUnit_Seconds = 1;
    public static final int _EUnit_Terabits = 13;
    public static final int _EUnit_TerabitsPerSecond = 25;
    public static final int _EUnit_Terabytes = 8;
    public static final int _EUnit_TerabytesPerSecond = 20;
    private String __T;
    private int __value;
    private static EUnit[] __values = new EUnit[27];
    public static final EUnit EUnit_None = new EUnit(0, 0, "EUnit_None");
    public static final EUnit EUnit_Seconds = new EUnit(1, 1, "EUnit_Seconds");
    public static final EUnit EUnit_Microseconds = new EUnit(2, 2, "EUnit_Microseconds");
    public static final EUnit EUnit_Milliseconds = new EUnit(3, 3, "EUnit_Milliseconds");
    public static final EUnit EUnit_Bytes = new EUnit(4, 4, "EUnit_Bytes");
    public static final EUnit EUnit_Kilobytes = new EUnit(5, 5, "EUnit_Kilobytes");
    public static final EUnit EUnit_Megabytes = new EUnit(6, 6, "EUnit_Megabytes");
    public static final EUnit EUnit_Gigabytes = new EUnit(7, 7, "EUnit_Gigabytes");
    public static final EUnit EUnit_Terabytes = new EUnit(8, 8, "EUnit_Terabytes");
    public static final EUnit EUnit_Bits = new EUnit(9, 9, "EUnit_Bits");
    public static final EUnit EUnit_Kilobits = new EUnit(10, 10, "EUnit_Kilobits");
    public static final EUnit EUnit_Megabits = new EUnit(11, 11, "EUnit_Megabits");
    public static final EUnit EUnit_Gigabits = new EUnit(12, 12, "EUnit_Gigabits");
    public static final EUnit EUnit_Terabits = new EUnit(13, 13, "EUnit_Terabits");
    public static final EUnit EUnit_Percent = new EUnit(14, 14, "EUnit_Percent");
    public static final EUnit EUnit_Count = new EUnit(15, 15, "EUnit_Count");
    public static final EUnit EUnit_BytesPerSecond = new EUnit(16, 16, "EUnit_BytesPerSecond");
    public static final EUnit EUnit_KilobytesPerSecond = new EUnit(17, 17, "EUnit_KilobytesPerSecond");
    public static final EUnit EUnit_MegabytesPerSecond = new EUnit(18, 18, "EUnit_MegabytesPerSecond");
    public static final EUnit EUnit_GigabytesPerSecond = new EUnit(19, 19, "EUnit_GigabytesPerSecond");
    public static final EUnit EUnit_TerabytesPerSecond = new EUnit(20, 20, "EUnit_TerabytesPerSecond");
    public static final EUnit EUnit_BitsPerSecond = new EUnit(21, 21, "EUnit_BitsPerSecond");
    public static final EUnit EUnit_KilobitsPerSecond = new EUnit(22, 22, "EUnit_KilobitsPerSecond");
    public static final EUnit EUnit_MegabitsPerSecond = new EUnit(23, 23, "EUnit_MegabitsPerSecond");
    public static final EUnit EUnit_GigabitsPerSecond = new EUnit(24, 24, "EUnit_GigabitsPerSecond");
    public static final EUnit EUnit_TerabitsPerSecond = new EUnit(25, 25, "EUnit_TerabitsPerSecond");
    public static final EUnit EUnit_CountPerSecond = new EUnit(26, 26, "EUnit_CountPerSecond");

    public static EUnit convert(int i) {
        int i2 = 0;
        while (true) {
            EUnit[] eUnitArr = __values;
            if (i2 >= eUnitArr.length) {
                return null;
            }
            if (eUnitArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static EUnit convert(String str) {
        int i = 0;
        while (true) {
            EUnit[] eUnitArr = __values;
            if (i >= eUnitArr.length) {
                return null;
            }
            if (eUnitArr[i].toString().equals(str)) {
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

    private EUnit(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
