package com.bytedance.pangle.util;

import android.content.SharedPreferences;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class l {
    private static volatile l b;
    public SharedPreferences a = Zeus.getAppApplication().getSharedPreferences("pangle_meta_data_sp", 0);

    public static l a() {
        if (b == null) {
            synchronized (l.class) {
                if (b == null) {
                    b = new l();
                }
            }
        }
        return b;
    }

    private l() {
    }

    public final int a(String str) {
        int i = this.a.getInt("PLUGIN_API_VERSION_".concat(String.valueOf(str)), 0);
        ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusSpUtils getPluginApiVersion pluginPKg = " + str + ", pluginApiVersion = " + i);
        return i;
    }

    public final String b(String str) {
        String string = this.a.getString("HOST_IDENTITY_".concat(String.valueOf(str)), "");
        ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusSpUtils getHostIdentity pluginPKg = " + str + ", hostIdentity = " + string);
        return string;
    }

    public final void a(String str, int i, boolean z) {
        SharedPreferences.Editor editorEdit = this.a.edit();
        String str2 = "INSTALLED_" + str + "-" + i;
        if (z) {
            editorEdit.putBoolean(str2, true);
        } else {
            editorEdit.remove(str2);
        }
        editorEdit.apply();
    }

    public final boolean a(String str, int i) {
        return this.a.getBoolean(String.format(Locale.getDefault(), "INSTALLED_%s-%d", str, Integer.valueOf(i)), false);
    }

    public final void b(String str, int i, boolean z) {
        SharedPreferences.Editor editorEdit = this.a.edit();
        editorEdit.putBoolean("dex_opt_state_" + str + "_" + i, z);
        editorEdit.apply();
    }

    public final void c(String str, int i, boolean z) {
        SharedPreferences.Editor editorEdit = this.a.edit();
        editorEdit.putBoolean("dex_remove_state_" + str + "_" + i, z);
        editorEdit.apply();
    }

    public final int b(String str, int i) {
        return this.a.getInt("remove_entry_flag_" + str + "_" + i, 0);
    }

    public final void a(String str, int i, String str2) {
        int iB = b(str, i, str2);
        SharedPreferences.Editor editorEdit = this.a.edit();
        editorEdit.putInt(str2 + "_failed_count_when_rm_entry_" + str + "_" + i, iB + 1);
        editorEdit.apply();
    }

    public final int b(String str, int i, String str2) {
        return this.a.getInt(str2 + "_failed_count_when_rm_entry_" + str + "_" + i, 0);
    }
}
