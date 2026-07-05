package com.sqwan.common.constants;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class SqErrorCode {
    public static final int MODULE_INIT = 0;
    public static final int MODULE_LOGIN = 1;
    public static final int MODULE_PAY = 3;
    public static final int MODULE_SHARE = 2;

    public static int code(int i, int i2) {
        return (i * 1000) + 370000 + i2;
    }
}
