package com.igexin.push.f;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.igexin.push.core.p;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class o {
    public static final String a = "us";
    public static final String b = "uis";
    public static final String c = "ua";
    public static final String d = "sc";
    public static final String e = "logkey2";
    public static final String f = "hwBadgeNum";
    private static final String g = "SpUtils";
    private static final String h = "getui_sp";

    private static void a(Context context, Intent intent) {
        try {
            if (intent.hasExtra(a)) {
                String strB = com.igexin.b.b.a.b(intent.getStringExtra(a), "");
                String str = (String) b(context, a, "");
                if (!str.equals(strB)) {
                    if (!com.igexin.push.core.b.ak.equals(strB)) {
                        a(context, a, strB);
                    } else if (!TextUtils.isEmpty(str)) {
                        a(context, a, "");
                    }
                }
            }
            if (intent.hasExtra(b)) {
                String str2 = (String) b(context, b, "");
                String strB2 = com.igexin.b.b.a.b(intent.getStringExtra(b), "");
                if (!str2.equals(strB2)) {
                    a(context, b, strB2);
                }
            }
            if (intent.hasExtra(c)) {
                com.igexin.push.core.p unused = p.a.a;
                String str3 = (String) b(context, c, "");
                String strB3 = com.igexin.b.b.a.b(intent.getStringExtra(c), "");
                if (str3.equals(strB3)) {
                    return;
                }
                a(context, c, strB3);
            }
        } catch (Throwable unused2) {
        }
    }

    public static void a(Context context, String str, Object obj) {
        SharedPreferences.Editor editorEdit = context.getApplicationContext().getSharedPreferences(h, 0).edit();
        if (obj instanceof String) {
            editorEdit.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            editorEdit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            editorEdit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            editorEdit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            editorEdit.putLong(str, ((Long) obj).longValue());
        }
        editorEdit.apply();
    }

    public static boolean a(Context context) {
        try {
            String str = (String) b(context, a, "");
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            Class.forName(str);
            return true;
        } catch (Exception e2) {
            com.igexin.b.a.c.a.a("SpUtils|" + e2.toString(), new Object[0]);
            return false;
        }
    }

    public static Object b(Context context, String str, Object obj) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(h, 0);
        return obj instanceof String ? sharedPreferences.getString(str, (String) obj) : obj instanceof Integer ? Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue())) : obj instanceof Boolean ? Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue())) : obj instanceof Float ? Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue())) : obj instanceof Long ? Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue())) : obj;
    }
}
