package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class EClientTemplateType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _TPL_HUYAAPP = 2;
    public static final int _TPL_JIEDAI = 16;
    public static final int _TPL_MATCH = 4;
    public static final int _TPL_MIRROR = 1;
    public static final int _TPL_PC = 64;
    public static final int _TPL_TEXAS = 8;
    public static final int _TPL_WEB = 32;
    private String __T;
    private int __value;
    private static EClientTemplateType[] __values = new EClientTemplateType[7];
    public static final EClientTemplateType TPL_PC = new EClientTemplateType(0, 64, "TPL_PC");
    public static final EClientTemplateType TPL_WEB = new EClientTemplateType(1, 32, "TPL_WEB");
    public static final EClientTemplateType TPL_JIEDAI = new EClientTemplateType(2, 16, "TPL_JIEDAI");
    public static final EClientTemplateType TPL_TEXAS = new EClientTemplateType(3, 8, "TPL_TEXAS");
    public static final EClientTemplateType TPL_MATCH = new EClientTemplateType(4, 4, "TPL_MATCH");
    public static final EClientTemplateType TPL_HUYAAPP = new EClientTemplateType(5, 2, "TPL_HUYAAPP");
    public static final EClientTemplateType TPL_MIRROR = new EClientTemplateType(6, 1, "TPL_MIRROR");

    public static EClientTemplateType convert(int i) {
        int i2 = 0;
        while (true) {
            EClientTemplateType[] eClientTemplateTypeArr = __values;
            if (i2 >= eClientTemplateTypeArr.length) {
                return null;
            }
            if (eClientTemplateTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static EClientTemplateType convert(String str) {
        int i = 0;
        while (true) {
            EClientTemplateType[] eClientTemplateTypeArr = __values;
            if (i >= eClientTemplateTypeArr.length) {
                return null;
            }
            if (eClientTemplateTypeArr[i].toString().equals(str)) {
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

    private EClientTemplateType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
