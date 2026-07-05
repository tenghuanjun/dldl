package com.huya.live.utils;

import com.sqwan.bugless.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DateUtils {
    private static String pat1 = "yyyy/MM/dd";
    private static SimpleDateFormat sdf1 = new SimpleDateFormat(pat1);
    private static String pat2 = DateUtil.DEFAULT_FORMAT_DATE;
    private static SimpleDateFormat sdf2 = new SimpleDateFormat(pat2);

    public static String getDate(long j) {
        try {
            return sdf1.format(new Date(j));
        } catch (Exception unused) {
            return " ";
        }
    }
}
