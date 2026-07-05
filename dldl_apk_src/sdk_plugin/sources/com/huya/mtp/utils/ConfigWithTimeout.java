package com.huya.mtp.utils;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ConfigWithTimeout {
    private static final String CONFIG_TIMEOUT_PREFIX = "config_timeout_";
    private static final String EXPIRED_TIME_PREFIX = "expired_time_";
    private static ConfigWithTimeout mInstance;
    private Context mContext;

    public static synchronized ConfigWithTimeout instance(Context context) {
        if (mInstance == null) {
            mInstance = new ConfigWithTimeout(context);
        }
        return mInstance;
    }

    private ConfigWithTimeout(Context context) {
        this.mContext = context;
    }

    private String getExpiredTimeKey(String str) {
        return EXPIRED_TIME_PREFIX + str;
    }

    private String getTimeoutKey(String str) {
        return CONFIG_TIMEOUT_PREFIX + str;
    }

    private boolean setExpiredTime(String str, long j) {
        boolean z = Config.getInstance(this.mContext).setLong(getExpiredTimeKey(str), System.currentTimeMillis() + j);
        Utils.dwAssert(z);
        return z;
    }

    public synchronized boolean setString(String str, String str2, long j) {
        boolean string;
        string = Config.getInstance(this.mContext).setString(getTimeoutKey(str), str2);
        Utils.dwAssert(string);
        return setExpiredTime(str, j) | string;
    }

    public synchronized boolean setFloat(String str, float f, long j) {
        boolean z;
        z = Config.getInstance(this.mContext).setFloat(getTimeoutKey(str), f);
        Utils.dwAssert(z);
        return setExpiredTime(str, j) | z;
    }

    public synchronized boolean setInteger(String str, int i, long j) {
        boolean z;
        z = Config.getInstance(this.mContext).setInt(getTimeoutKey(str), i);
        Utils.dwAssert(z);
        return setExpiredTime(str, j) | z;
    }

    private boolean isExpired(String str) {
        return Config.getInstance(this.mContext).getLong(getExpiredTimeKey(str), 0L) < System.currentTimeMillis();
    }

    public synchronized String getString(String str, String str2) {
        if (isExpired(str)) {
            return str2;
        }
        return Config.getInstance(this.mContext).getString(getTimeoutKey(str), str2);
    }

    public synchronized float getFloat(String str, float f) {
        if (isExpired(str)) {
            return f;
        }
        return Config.getInstance(this.mContext).getFloat(getTimeoutKey(str), f);
    }

    public synchronized int getInteger(String str, int i) {
        if (isExpired(str)) {
            return i;
        }
        return Config.getInstance(this.mContext).getInt(getTimeoutKey(str), i);
    }
}
