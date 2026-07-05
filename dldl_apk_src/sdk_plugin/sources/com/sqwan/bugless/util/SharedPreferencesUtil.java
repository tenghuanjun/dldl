package com.sqwan.bugless.util;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SharedPreferencesUtil {
    private static final String FILE_NAME = "bugless.xml";
    private static SharedPreferencesUtil instance;
    private Context mContext;

    public static SharedPreferencesUtil getInstance() {
        if (instance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (instance == null) {
                    instance = new SharedPreferencesUtil();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <V> void setValue(String key, V value) {
        Context context = this.mContext;
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(FILE_NAME, 0).edit();
        if (value instanceof String) {
            editorEdit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editorEdit.putInt(key, ((Integer) value).intValue());
        } else if (value instanceof Long) {
            editorEdit.putLong(key, ((Long) value).longValue());
        } else if (value instanceof Boolean) {
            editorEdit.putBoolean(key, ((Boolean) value).booleanValue());
        } else if (value instanceof Float) {
            editorEdit.putFloat(key, ((Float) value).floatValue());
        }
        editorEdit.apply();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <V> V getValue(String str, V v) {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(FILE_NAME, 0);
        if (v instanceof String) {
            return (V) sharedPreferences.getString(str, (String) v);
        }
        if (v instanceof Integer) {
            return (V) Integer.valueOf(sharedPreferences.getInt(str, ((Integer) v).intValue()));
        }
        if (v instanceof Long) {
            return (V) Long.valueOf(sharedPreferences.getLong(str, ((Long) v).longValue()));
        }
        if (v instanceof Boolean) {
            return (V) Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) v).booleanValue()));
        }
        return v instanceof Float ? (V) Float.valueOf(sharedPreferences.getFloat(str, ((Float) v).floatValue())) : v;
    }

    public void clearData() {
        SharedPreferences.Editor editorEdit = this.mContext.getSharedPreferences(FILE_NAME, 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }
}
