package com.bytedance.bdtracker;

import android.content.SharedPreferences;

/* JADX INFO: compiled from: outline */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static StringBuilder a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static void a(SharedPreferences sharedPreferences, String str, String str2) {
        sharedPreferences.edit().putString(str, str2).apply();
    }
}
