package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class EStreamLineType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _STREAM_LINE_CC = 3;
    public static final int _STREAM_LINE_NEW_YY = 2;
    public static final int _STREAM_LINE_OLD_YY = 0;
    public static final int _STREAM_LINE_WS = 1;
    private String __T;
    private int __value;
    private static EStreamLineType[] __values = new EStreamLineType[4];
    public static final EStreamLineType STREAM_LINE_OLD_YY = new EStreamLineType(0, 0, "STREAM_LINE_OLD_YY");
    public static final EStreamLineType STREAM_LINE_WS = new EStreamLineType(1, 1, "STREAM_LINE_WS");
    public static final EStreamLineType STREAM_LINE_NEW_YY = new EStreamLineType(2, 2, "STREAM_LINE_NEW_YY");
    public static final EStreamLineType STREAM_LINE_CC = new EStreamLineType(3, 3, "STREAM_LINE_CC");

    public static EStreamLineType convert(int i) {
        int i2 = 0;
        while (true) {
            EStreamLineType[] eStreamLineTypeArr = __values;
            if (i2 >= eStreamLineTypeArr.length) {
                return null;
            }
            if (eStreamLineTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static EStreamLineType convert(String str) {
        int i = 0;
        while (true) {
            EStreamLineType[] eStreamLineTypeArr = __values;
            if (i >= eStreamLineTypeArr.length) {
                return null;
            }
            if (eStreamLineTypeArr[i].toString().equals(str)) {
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

    private EStreamLineType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
