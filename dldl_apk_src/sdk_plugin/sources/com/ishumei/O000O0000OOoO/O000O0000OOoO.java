package com.ishumei.O000O0000OOoO;

import android.content.SharedPreferences;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O0000OOoO {
    public static void O0000O000000oO(SharedPreferences sharedPreferences, String str, int i) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
    }

    public static void O0000O000000oO(SharedPreferences sharedPreferences, String str, long j) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putLong(str, j);
        editorEdit.apply();
    }

    public static void O0000O000000oO(SharedPreferences sharedPreferences, String str, Set<String> set) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putStringSet(str, set);
        editorEdit.apply();
    }
}
