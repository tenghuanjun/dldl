package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class EMsgTermType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _MSGTERM_MOB_ADR = 8;
    public static final int _MSGTERM_MOB_ADRPAD = 9;
    public static final int _MSGTERM_MOB_ADRTV = 10;
    public static final int _MSGTERM_MOB_IPAD = 7;
    public static final int _MSGTERM_MOB_IPHONE = 6;
    public static final int _MSGTERM_PC_UWP = 2;
    public static final int _MSGTERM_PC_YY = 1;
    public static final int _MSGTERM_UNKNOW = 0;
    public static final int _MSGTERM_WEB_EXTERNAL = 4;
    public static final int _MSGTERM_WEB_FLASH = 3;
    public static final int _MSGTERM_WEB_H5 = 5;
    private String __T;
    private int __value;
    private static EMsgTermType[] __values = new EMsgTermType[11];
    public static final EMsgTermType MSGTERM_UNKNOW = new EMsgTermType(0, 0, "MSGTERM_UNKNOW");
    public static final EMsgTermType MSGTERM_PC_YY = new EMsgTermType(1, 1, "MSGTERM_PC_YY");
    public static final EMsgTermType MSGTERM_PC_UWP = new EMsgTermType(2, 2, "MSGTERM_PC_UWP");
    public static final EMsgTermType MSGTERM_WEB_FLASH = new EMsgTermType(3, 3, "MSGTERM_WEB_FLASH");
    public static final EMsgTermType MSGTERM_WEB_EXTERNAL = new EMsgTermType(4, 4, "MSGTERM_WEB_EXTERNAL");
    public static final EMsgTermType MSGTERM_WEB_H5 = new EMsgTermType(5, 5, "MSGTERM_WEB_H5");
    public static final EMsgTermType MSGTERM_MOB_IPHONE = new EMsgTermType(6, 6, "MSGTERM_MOB_IPHONE");
    public static final EMsgTermType MSGTERM_MOB_IPAD = new EMsgTermType(7, 7, "MSGTERM_MOB_IPAD");
    public static final EMsgTermType MSGTERM_MOB_ADR = new EMsgTermType(8, 8, "MSGTERM_MOB_ADR");
    public static final EMsgTermType MSGTERM_MOB_ADRPAD = new EMsgTermType(9, 9, "MSGTERM_MOB_ADRPAD");
    public static final EMsgTermType MSGTERM_MOB_ADRTV = new EMsgTermType(10, 10, "MSGTERM_MOB_ADRTV");

    public static EMsgTermType convert(int i) {
        int i2 = 0;
        while (true) {
            EMsgTermType[] eMsgTermTypeArr = __values;
            if (i2 >= eMsgTermTypeArr.length) {
                return null;
            }
            if (eMsgTermTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static EMsgTermType convert(String str) {
        int i = 0;
        while (true) {
            EMsgTermType[] eMsgTermTypeArr = __values;
            if (i >= eMsgTermTypeArr.length) {
                return null;
            }
            if (eMsgTermTypeArr[i].toString().equals(str)) {
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

    private EMsgTermType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
