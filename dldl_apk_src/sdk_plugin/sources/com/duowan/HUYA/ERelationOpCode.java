package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ERelationOpCode {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _ERO_BEGINLIVE_PUSH = 16;
    public static final int _ERO_BEGINLIVE_UNPUSH = 32;
    public static final int _ERO_CANCEL_BLACK = 8;
    public static final int _ERO_SET_BLACK = 4;
    public static final int _ERO_SUBSCRIBE = 1;
    public static final int _ERO_UNSUBSCRIBE = 2;
    private String __T;
    private int __value;
    private static ERelationOpCode[] __values = new ERelationOpCode[6];
    public static final ERelationOpCode ERO_SUBSCRIBE = new ERelationOpCode(0, 1, "ERO_SUBSCRIBE");
    public static final ERelationOpCode ERO_UNSUBSCRIBE = new ERelationOpCode(1, 2, "ERO_UNSUBSCRIBE");
    public static final ERelationOpCode ERO_SET_BLACK = new ERelationOpCode(2, 4, "ERO_SET_BLACK");
    public static final ERelationOpCode ERO_CANCEL_BLACK = new ERelationOpCode(3, 8, "ERO_CANCEL_BLACK");
    public static final ERelationOpCode ERO_BEGINLIVE_PUSH = new ERelationOpCode(4, 16, "ERO_BEGINLIVE_PUSH");
    public static final ERelationOpCode ERO_BEGINLIVE_UNPUSH = new ERelationOpCode(5, 32, "ERO_BEGINLIVE_UNPUSH");

    public static ERelationOpCode convert(int i) {
        int i2 = 0;
        while (true) {
            ERelationOpCode[] eRelationOpCodeArr = __values;
            if (i2 >= eRelationOpCodeArr.length) {
                return null;
            }
            if (eRelationOpCodeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static ERelationOpCode convert(String str) {
        int i = 0;
        while (true) {
            ERelationOpCode[] eRelationOpCodeArr = __values;
            if (i >= eRelationOpCodeArr.length) {
                return null;
            }
            if (eRelationOpCodeArr[i].toString().equals(str)) {
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

    private ERelationOpCode(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
