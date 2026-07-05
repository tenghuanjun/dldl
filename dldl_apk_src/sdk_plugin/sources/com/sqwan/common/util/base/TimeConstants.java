package com.sqwan.common.util.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public final class TimeConstants {
    public static final int DAY = 86400000;
    public static final int HOUR = 3600000;
    public static final int MIN = 60000;
    public static final int MSEC = 1;
    public static final int SEC = 1000;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Unit {
    }
}
