package com.sqwan.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SpUtils {
    private static final String SQ_PREFS = "sq_prefs";
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;

    public SpUtils(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        this.sp = sharedPreferences;
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        this.editor = editorEdit;
        editorEdit.apply();
    }

    public void put(String str, String str2) {
        this.editor.putString(str, str2).apply();
    }

    public String getString(String str) {
        return getString(str, null);
    }

    public String getString(String str, String str2) {
        return this.sp.getString(str, str2);
    }

    public void put(String str, int i) {
        this.editor.putInt(str, i).apply();
    }

    public int getInt(String str) {
        return getInt(str, -1);
    }

    public int getInt(String str, int i) {
        return this.sp.getInt(str, i);
    }

    public void put(String str, long j) {
        this.editor.putLong(str, j).apply();
    }

    public long getLong(String str) {
        return getLong(str, -1L);
    }

    public long getLong(String str, long j) {
        return this.sp.getLong(str, j);
    }

    public void put(String str, float f) {
        this.editor.putFloat(str, f).apply();
    }

    public float getFloat(String str) {
        return getFloat(str, -1.0f);
    }

    public float getFloat(String str, float f) {
        return this.sp.getFloat(str, f);
    }

    public void put(String str, boolean z) {
        this.editor.putBoolean(str, z).apply();
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.sp.getBoolean(str, z);
    }

    public void put(String str, Set<String> set) {
        this.editor.putStringSet(str, set).apply();
    }

    public Set<String> getStringSet(String str) {
        return getStringSet(str, null);
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return this.sp.getStringSet(str, set);
    }

    public Map<String, ?> getAll() {
        return this.sp.getAll();
    }

    public void remove(String str) {
        this.editor.remove(str).apply();
    }

    public boolean contains(String str) {
        return this.sp.contains(str);
    }

    public void clear() {
        this.editor.clear().apply();
    }

    public static SpUtils get(Context context, String str) {
        return new SpUtils(context, str);
    }

    public static SpUtils get(Context context) {
        return get(context, SQ_PREFS);
    }
}
