package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class EAuditorUserType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _EAR_BLACK_USER = 5;
    public static final int _EAR_MUTED_USER = 3;
    public static final int _EAR_NORMAL_MANAGER = 2;
    public static final int _EAR_NORMAL_USER = 4;
    public static final int _EAR_OWVP_USER = 6;
    public static final int _EAR_SUPER_MANAGER = 1;
    private String __T;
    private int __value;
    private static EAuditorUserType[] __values = new EAuditorUserType[6];
    public static final EAuditorUserType EAR_SUPER_MANAGER = new EAuditorUserType(0, 1, "EAR_SUPER_MANAGER");
    public static final EAuditorUserType EAR_NORMAL_MANAGER = new EAuditorUserType(1, 2, "EAR_NORMAL_MANAGER");
    public static final EAuditorUserType EAR_MUTED_USER = new EAuditorUserType(2, 3, "EAR_MUTED_USER");
    public static final EAuditorUserType EAR_NORMAL_USER = new EAuditorUserType(3, 4, "EAR_NORMAL_USER");
    public static final EAuditorUserType EAR_BLACK_USER = new EAuditorUserType(4, 5, "EAR_BLACK_USER");
    public static final EAuditorUserType EAR_OWVP_USER = new EAuditorUserType(5, 6, "EAR_OWVP_USER");

    public static EAuditorUserType convert(int i) {
        int i2 = 0;
        while (true) {
            EAuditorUserType[] eAuditorUserTypeArr = __values;
            if (i2 >= eAuditorUserTypeArr.length) {
                return null;
            }
            if (eAuditorUserTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static EAuditorUserType convert(String str) {
        int i = 0;
        while (true) {
            EAuditorUserType[] eAuditorUserTypeArr = __values;
            if (i >= eAuditorUserTypeArr.length) {
                return null;
            }
            if (eAuditorUserTypeArr[i].toString().equals(str)) {
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

    private EAuditorUserType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
