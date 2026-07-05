package com.mobile.auth.k;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class p {

    @SuppressLint({"StaticFieldLeak"})
    private static Context a;

    private static class b implements SharedPreferences.Editor {
        private SharedPreferences.Editor a;

        @SuppressLint({"CommitPrefEdits"})
        private b() {
            this.a = p.a.getSharedPreferences("ssoconfigs", 0).edit();
        }

        @SuppressLint({"CommitPrefEdits"})
        private b(String str) {
            this.a = p.a.getSharedPreferences(str, 0).edit();
        }

        private String a(String str) {
            return h.a(str);
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            this.a.apply();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            return this.a.clear();
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            return this.a.commit();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            return this.a.putBoolean(a(str), z);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f) {
            return this.a.putFloat(a(str), f);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i) {
            return this.a.putInt(a(str), i);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j) {
            return this.a.putLong(a(str), j);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            return this.a.putString(a(str), str2);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            return this.a.putStringSet(a(str), set);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            return this.a.remove(a(str));
        }
    }

    public static int a(String str, int i) {
        return a.getSharedPreferences("ssoconfigs", 0).getInt(h.a(str), i);
    }

    public static int a(String str, String str2, int i) {
        return a.getSharedPreferences(str, 0).getInt(h.a(str2), i);
    }

    public static long a(String str, String str2, long j) {
        return a.getSharedPreferences(str, 0).getLong(h.a(str2), j);
    }

    public static SharedPreferences.Editor a() {
        return new b();
    }

    public static String a(String str, String str2, String str3) {
        return a.getSharedPreferences(str, 0).getString(h.a(str2), str3);
    }

    public static void a(Context context) {
        a = context.getApplicationContext();
    }

    static void a(String str) {
        SharedPreferences sharedPreferences = a.getSharedPreferences("ssoconfigs", 0);
        sharedPreferences.edit().remove(h.a(str)).apply();
    }

    public static void a(String str, long j) {
        SharedPreferences sharedPreferences = a.getSharedPreferences("ssoconfigs", 0);
        sharedPreferences.edit().putLong(h.a(str), j).apply();
    }

    public static void a(String str, String str2) {
        SharedPreferences sharedPreferences = a.getSharedPreferences("ssoconfigs", 0);
        sharedPreferences.edit().putString(h.a(str), str2).apply();
    }

    public static void a(String str, boolean z) {
        SharedPreferences sharedPreferences = a.getSharedPreferences("ssoconfigs", 0);
        sharedPreferences.edit().putBoolean(h.a(str), z).apply();
    }

    public static long b(String str, long j) {
        return a.getSharedPreferences("ssoconfigs", 0).getLong(h.a(str), j);
    }

    public static SharedPreferences.Editor b(String str) {
        return new b(str);
    }

    public static String b(String str, String str2) {
        return a.getSharedPreferences("ssoconfigs", 0).getString(h.a(str), str2);
    }
}
