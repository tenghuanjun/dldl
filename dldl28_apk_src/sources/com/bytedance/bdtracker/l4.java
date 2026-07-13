package com.bytedance.bdtracker;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes2.dex */
public class l4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f289a;
    public static y3<SharedPreferences> b = new a();

    public static class a extends y3<SharedPreferences> {
        @Override // com.bytedance.bdtracker.y3
        public SharedPreferences a(Object[] objArr) {
            return v3.a((Context) objArr[0], "ug_install_settings_pref", 0);
        }
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        if (f289a) {
            return true;
        }
        return b.b(context).getBoolean("_install_started_v2", false);
    }
}
