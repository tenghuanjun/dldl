package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ETraceSource {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _E_TRACE_SOURCE_NEARBY = 1;
    public static final int _E_TRACE_SOURCE_NULL = 0;
    public static final int _E_TRACE_SOURCE_SHARE = 2;
    private String __T;
    private int __value;
    private static ETraceSource[] __values = new ETraceSource[3];
    public static final ETraceSource E_TRACE_SOURCE_NULL = new ETraceSource(0, 0, "E_TRACE_SOURCE_NULL");
    public static final ETraceSource E_TRACE_SOURCE_NEARBY = new ETraceSource(1, 1, "E_TRACE_SOURCE_NEARBY");
    public static final ETraceSource E_TRACE_SOURCE_SHARE = new ETraceSource(2, 2, "E_TRACE_SOURCE_SHARE");

    public static ETraceSource convert(int i) {
        int i2 = 0;
        while (true) {
            ETraceSource[] eTraceSourceArr = __values;
            if (i2 >= eTraceSourceArr.length) {
                return null;
            }
            if (eTraceSourceArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static ETraceSource convert(String str) {
        int i = 0;
        while (true) {
            ETraceSource[] eTraceSourceArr = __values;
            if (i >= eTraceSourceArr.length) {
                return null;
            }
            if (eTraceSourceArr[i].toString().equals(str)) {
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

    private ETraceSource(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
