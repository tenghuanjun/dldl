package com.huya.mtp.utils;

import android.text.TextUtils;
import com.huya.mtp.api.MTPApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DecimalUtils {
    public static final String TAG = "DecimalUtils";

    public static long safelyParseLong(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            return Long.valueOf(str).longValue();
        } catch (Exception e) {
            MTPApi.LOGGER.debug(TAG, e);
            return i;
        }
    }

    public static int safelyParseInt(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception e) {
            MTPApi.LOGGER.debug(TAG, e);
            return i;
        }
    }

    public static double safelyParseDouble(String str, double d) {
        if (TextUtils.isEmpty(str)) {
            return d;
        }
        try {
            return Double.valueOf(str).doubleValue();
        } catch (Exception e) {
            MTPApi.LOGGER.debug(TAG, e);
            return d;
        }
    }
}
