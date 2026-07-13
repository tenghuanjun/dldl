package com.bytedance.dr;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.bdtracker.o4;
import com.bytedance.dr.impl.a;
import com.bytedance.dr.impl.c;
import com.bytedance.dr.impl.d;
import com.bytedance.dr.impl.e;
import com.bytedance.dr.impl.g;
import com.bytedance.dr.impl.h;
import com.bytedance.dr.impl.i;
import com.bytedance.dr.impl.j;
import com.bytedance.dr.impl.k;
import com.bytedance.dr.impl.l;
import com.bytedance.dr.impl.m;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class OaidFactory {
    public static OaidApi createOaidImpl(Context context) {
        if (o4.b()) {
            return new k(new m());
        }
        if ((Build.MANUFACTURER.equalsIgnoreCase("XIAOMI") || Build.BRAND.equalsIgnoreCase("XIAOMI") || Build.BRAND.equalsIgnoreCase("REDMI")) && m.b != null && m.f381a != null && m.c != null) {
            return new m();
        }
        boolean zContains = false;
        if (j.f378a.b(new Object[0]).booleanValue()) {
            return new j();
        }
        if (o4.a().toUpperCase().contains("HUAWEI") || o4.d()) {
            return new e();
        }
        if ("OnePlus".equalsIgnoreCase(Build.MANUFACTURER)) {
            return new k(null);
        }
        String str = Build.BRAND;
        if (str == null ? false : str.toLowerCase(Locale.ENGLISH).contains("meizu")) {
            return new h();
        }
        if (Build.VERSION.SDK_INT <= 28) {
            if (o4.e() || !e.a(context)) {
                return null;
            }
            return new e();
        }
        if ("samsung".equalsIgnoreCase(Build.BRAND) || "samsung".equalsIgnoreCase(Build.MANUFACTURER)) {
            return new l();
        }
        if (o4.a().toUpperCase().contains("NUBIA")) {
            return new i();
        }
        String str2 = Build.FINGERPRINT;
        if (TextUtils.isEmpty(str2)) {
            String strA = o4.a("ro.build.version.incremental");
            if (!TextUtils.isEmpty(strA) && strA.contains("VIBEUI_V2")) {
                zContains = true;
            }
        } else {
            zContains = str2.contains("VIBEUI_V2");
        }
        if (zContains) {
            return new g();
        }
        if (o4.a().toUpperCase().contains("ASUS")) {
            return new a();
        }
        d dVar = new d(context);
        return dVar.support(context) ? dVar : new c();
    }
}
