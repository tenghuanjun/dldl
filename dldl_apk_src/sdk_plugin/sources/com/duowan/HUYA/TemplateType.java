package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class TemplateType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _PRIMARY = 1;
    public static final int _RECEPTION = 2;
    private String __T;
    private int __value;
    private static TemplateType[] __values = new TemplateType[2];
    public static final TemplateType PRIMARY = new TemplateType(0, 1, "PRIMARY");
    public static final TemplateType RECEPTION = new TemplateType(1, 2, "RECEPTION");

    public static TemplateType convert(int i) {
        int i2 = 0;
        while (true) {
            TemplateType[] templateTypeArr = __values;
            if (i2 >= templateTypeArr.length) {
                return null;
            }
            if (templateTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static TemplateType convert(String str) {
        int i = 0;
        while (true) {
            TemplateType[] templateTypeArr = __values;
            if (i >= templateTypeArr.length) {
                return null;
            }
            if (templateTypeArr[i].toString().equals(str)) {
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

    private TemplateType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
