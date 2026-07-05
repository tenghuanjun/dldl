package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class EUPOrderType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _EUPActIndexRuleOrder = 3;
    public static final int _EUPActRecruitRuleOrder = 4;
    public static final int _EUPLiveToolBottomOrder = 5;
    public static final int _EUPMultiTopOrder = 1;
    public static final int _EUPTimeDescOrder = 2;
    public static final int _EUPWebTopOrder = 0;
    private String __T;
    private int __value;
    private static EUPOrderType[] __values = new EUPOrderType[6];
    public static final EUPOrderType EUPWebTopOrder = new EUPOrderType(0, 0, "EUPWebTopOrder");
    public static final EUPOrderType EUPMultiTopOrder = new EUPOrderType(1, 1, "EUPMultiTopOrder");
    public static final EUPOrderType EUPTimeDescOrder = new EUPOrderType(2, 2, "EUPTimeDescOrder");
    public static final EUPOrderType EUPActIndexRuleOrder = new EUPOrderType(3, 3, "EUPActIndexRuleOrder");
    public static final EUPOrderType EUPActRecruitRuleOrder = new EUPOrderType(4, 4, "EUPActRecruitRuleOrder");
    public static final EUPOrderType EUPLiveToolBottomOrder = new EUPOrderType(5, 5, "EUPLiveToolBottomOrder");

    public static EUPOrderType convert(int i) {
        int i2 = 0;
        while (true) {
            EUPOrderType[] eUPOrderTypeArr = __values;
            if (i2 >= eUPOrderTypeArr.length) {
                return null;
            }
            if (eUPOrderTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static EUPOrderType convert(String str) {
        int i = 0;
        while (true) {
            EUPOrderType[] eUPOrderTypeArr = __values;
            if (i >= eUPOrderTypeArr.length) {
                return null;
            }
            if (eUPOrderTypeArr[i].toString().equals(str)) {
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

    private EUPOrderType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
