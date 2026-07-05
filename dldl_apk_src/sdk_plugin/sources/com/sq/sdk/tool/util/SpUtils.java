package com.sq.sdk.tool.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SpUtils {
    private static SpUtils sInstance;
    private Context mContext;

    public static SpUtils getInstance() {
        if (sInstance == null) {
            sInstance = new SpUtils(SQContextWrapper.getApplicationContext());
        }
        return sInstance;
    }

    private SpUtils(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public boolean getBoolean(String str, String str2) {
        return this.mContext.getSharedPreferences(str, 0).getBoolean(str2, false);
    }

    public String getString(String str, String str2) {
        return this.mContext.getSharedPreferences(str, 0).getString(str2, null);
    }

    public Integer getInt(String str, String str2) {
        return Integer.valueOf(this.mContext.getSharedPreferences(str, 0).getInt(str2, -1));
    }

    public boolean getBoolean(String str, String str2, boolean z) {
        return this.mContext.getSharedPreferences(str, 0).getBoolean(str2, z);
    }

    public String getString(String str, String str2, String str3) {
        return this.mContext.getSharedPreferences(str, 0).getString(str2, str3);
    }

    public Integer getInt(String str, String str2, int i) {
        return Integer.valueOf(this.mContext.getSharedPreferences(str, 0).getInt(str2, i));
    }

    public Long getLong(String str, String str2, long j) {
        return Long.valueOf(this.mContext.getSharedPreferences(str, 0).getLong(str2, j));
    }

    public Float getFloat(String str, String str2, float f) {
        return Float.valueOf(this.mContext.getSharedPreferences(str, 0).getFloat(str2, f));
    }

    public void putBoolean(String str, String str2, boolean z) {
        this.mContext.getSharedPreferences(str, 0).edit().putBoolean(str2, z).apply();
    }

    public void putString(String str, String str2, String str3) {
        this.mContext.getSharedPreferences(str, 0).edit().putString(str2, str3).apply();
    }

    public void putInt(String str, String str2, int i) {
        this.mContext.getSharedPreferences(str, 0).edit().putInt(str2, i).apply();
    }

    public void putLong(String str, String str2, long j) {
        this.mContext.getSharedPreferences(str, 0).edit().putLong(str2, j).apply();
    }

    public void putFloat(String str, String str2, float f) {
        this.mContext.getSharedPreferences(str, 0).edit().putFloat(str2, f).apply();
    }

    public void putStrings(String str, HashMap<String, String> map) {
        SharedPreferences.Editor editorEdit = this.mContext.getSharedPreferences(str, 0).edit();
        for (String str2 : map.keySet()) {
            editorEdit.putString(str2, map.get(str2));
        }
        editorEdit.apply();
    }

    public void putIntegers(String str, HashMap<String, Integer> map) {
        SharedPreferences.Editor editorEdit = this.mContext.getSharedPreferences(str, 0).edit();
        for (String str2 : map.keySet()) {
            editorEdit.putInt(str2, map.get(str2).intValue());
        }
        editorEdit.apply();
    }
}
