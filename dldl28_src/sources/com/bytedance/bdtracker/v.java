package com.bytedance.bdtracker;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.applog.log.LoggerImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class v implements Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i0 f329a = new i0(null, "@APPLOG_APP_USE");
    public static boolean b = false;
    public static int c;
    public static t3 d;
    public static t3 e;
    public static long f;
    public static String g;
    public static Object h;
    public static long i;
    public static final Map<Integer, List<t3>> j;
    public static final List<Object> k;
    public static t3 l;
    public static final HashSet<Integer> m;
    public static volatile v n;

    static {
        Arrays.asList("android.arch.lifecycle.ReportFragment", "androidx.lifecycle.ReportFragment");
        Collections.singletonList("com.bumptech.glide.manager.SupportRequestManagerFragment");
        c = 0;
        j = new HashMap();
        k = new ArrayList();
        m = new HashSet<>(8);
        n = null;
    }

    public static t3 a() {
        t3 t3Var = d;
        t3 t3Var2 = e;
        if (t3Var2 != null) {
            return t3Var2;
        }
        if (t3Var != null) {
            return t3Var;
        }
        return null;
    }

    public static t3 a(boolean z, t3 t3Var, long j2) {
        t3 t3Var2 = (t3) t3Var.m6355clone();
        t3Var2.a(j2);
        long j3 = j2 - t3Var.c;
        if (j3 <= 0) {
            j3 = 1000;
        }
        t3Var2.s = j3;
        t3Var2.D = z;
        b.a(t3Var2, new u(t3Var2));
        b.a(new s(t3Var2), new t());
        return t3Var2;
    }

    public static synchronized v a(Application application) {
        if (n == null) {
            n = new v();
            application.registerActivityLifecycleCallbacks(n);
        }
        return n;
    }

    public void a(Activity activity, int i2) {
        t3 t3VarA = a(activity.getClass(), false, activity.getClass().getName(), "", k4.c(activity), k4.b(activity), System.currentTimeMillis(), k4.d(activity));
        d = t3VarA;
        t3VarA.A = !m.remove(Integer.valueOf(i2)) ? 1 : 0;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        m.add(Integer.valueOf(activity.hashCode()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        m.remove(Integer.valueOf(activity.hashCode()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        f329a.a(jCurrentTimeMillis);
        b = false;
        LoggerImpl.global().debug("onActivityPaused:{}", activity != null ? activity.getClass().getName() : "");
        t3 t3Var = e;
        if (t3Var != null) {
            Object obj = h;
            if (t3Var != null) {
                String str = t3Var.u;
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                i = jCurrentTimeMillis2;
                a(true, e, jCurrentTimeMillis2);
                e = null;
                h = null;
            }
            if (obj != null) {
                k.remove(obj);
            }
        }
        t3 t3Var2 = d;
        if (t3Var2 != null) {
            g = t3Var2.u;
            f = jCurrentTimeMillis;
            a(false, t3Var2, jCurrentTimeMillis);
            d = null;
            if (activity != null) {
                activity.isChild();
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        f329a.c(jCurrentTimeMillis);
        b = true;
        String strC = k4.c(activity);
        LoggerImpl.global().debug("onActivityResumed:{} {}", strC, activity.getClass().getName());
        t3 t3VarA = a(activity.getClass(), false, activity.getClass().getName(), "", strC, k4.b(activity), jCurrentTimeMillis, k4.d(activity));
        d = t3VarA;
        t3VarA.A = !m.remove(Integer.valueOf(activity.hashCode())) ? 1 : 0;
        if (activity.isChild()) {
            return;
        }
        activity.getWindow().getDecorView().hashCode();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        c++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (g != null) {
            int i2 = c - 1;
            c = i2;
            if (i2 <= 0) {
                g = null;
                i = 0L;
                f = 0L;
                b.a(new c());
            }
        }
    }

    public static t3 a(Class<?> cls, boolean z, String str, String str2, String str3, String str4, long j2, JSONObject jSONObject) {
        t3 t3Var = new t3();
        t3Var.E = cls;
        if (TextUtils.isEmpty(str2)) {
            t3Var.u = str;
        } else {
            t3Var.u = str + ":" + str2;
        }
        t3Var.a(j2);
        t3Var.z = j2;
        t3Var.s = -1L;
        t3 t3Var2 = l;
        t3Var.t = t3Var2 != null ? t3Var2.u : "";
        if (str3 == null) {
            str3 = "";
        }
        t3Var.v = str3;
        t3 t3Var3 = l;
        t3Var.w = t3Var3 != null ? t3Var3.v : "";
        if (str4 == null) {
            str4 = "";
        }
        t3Var.x = str4;
        t3 t3Var4 = l;
        t3Var.y = t3Var4 != null ? t3Var4.x : "";
        t3Var.o = jSONObject;
        t3Var.D = z;
        b.a(t3Var, new u(t3Var));
        l = t3Var;
        return t3Var;
    }
}
