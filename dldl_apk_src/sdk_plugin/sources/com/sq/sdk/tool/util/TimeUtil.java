package com.sq.sdk.tool.util;

import com.sqwan.bugless.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TimeUtil {
    public static final long DAY = 86400000;
    public static final long HOUR = 3600000;
    public static final long MINUTE = 60000;
    public static final long SECOND = 1000;

    public static String getCurrentTime() {
        return new SimpleDateFormat(DateUtil.DEFAULT_DATE_TIME_FORMAT).format(new Date());
    }
}
