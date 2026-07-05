package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ERelationType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _ERT_BEGINLIVE_PUSH = 16;
    public static final int _ERT_BEGINLIVE_PUSHED = 32;
    public static final int _ERT_BLACK = 4;
    public static final int _ERT_BLACKED = 8;
    public static final int _ERT_SUBSCRIBE_FROM = 2;
    public static final int _ERT_SUBSCRIBE_TO = 1;
    private String __T;
    private int __value;
    private static ERelationType[] __values = new ERelationType[6];
    public static final ERelationType ERT_SUBSCRIBE_TO = new ERelationType(0, 1, "ERT_SUBSCRIBE_TO");
    public static final ERelationType ERT_SUBSCRIBE_FROM = new ERelationType(1, 2, "ERT_SUBSCRIBE_FROM");
    public static final ERelationType ERT_BLACK = new ERelationType(2, 4, "ERT_BLACK");
    public static final ERelationType ERT_BLACKED = new ERelationType(3, 8, "ERT_BLACKED");
    public static final ERelationType ERT_BEGINLIVE_PUSH = new ERelationType(4, 16, "ERT_BEGINLIVE_PUSH");
    public static final ERelationType ERT_BEGINLIVE_PUSHED = new ERelationType(5, 32, "ERT_BEGINLIVE_PUSHED");

    public static ERelationType convert(int i) {
        int i2 = 0;
        while (true) {
            ERelationType[] eRelationTypeArr = __values;
            if (i2 >= eRelationTypeArr.length) {
                return null;
            }
            if (eRelationTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static ERelationType convert(String str) {
        int i = 0;
        while (true) {
            ERelationType[] eRelationTypeArr = __values;
            if (i >= eRelationTypeArr.length) {
                return null;
            }
            if (eRelationTypeArr[i].toString().equals(str)) {
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

    private ERelationType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
