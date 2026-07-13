package com.bytedance.bdtracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.bytedance.applog.IOaidObserver;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.bdtracker.c0;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class x4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static v4<z4> f339a = new a();

    public static class a extends v4<z4> {
        @Override // com.bytedance.bdtracker.v4
        public z4 a(Object[] objArr) {
            return new z4((Context) objArr[0]);
        }
    }

    public static String a(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optString("id", null);
        }
        return null;
    }

    public static void a(IOaidObserver iOaidObserver) {
        z4.a(iOaidObserver);
    }

    public static void a(g gVar) {
        g gVar2;
        z4.k = gVar;
        Map<String, String> map = z4.m;
        if (map == null || (gVar2 = z4.k) == null) {
            return;
        }
        ((c0.b) gVar2).a(map);
    }

    public static void b(IOaidObserver iOaidObserver) {
        z4.b(iOaidObserver);
    }

    public static String a(SharedPreferences sharedPreferences) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        String strB = w4.f336a.b(sharedPreferences);
        long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
        LoggerImpl.global().debug(1, "getCdid takes " + jElapsedRealtime2 + " ms", new Object[0]);
        return strB;
    }

    public static Map a(Context context) {
        Map<String, String> map;
        boolean zTryLock;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        z4 z4VarB = f339a.b(context);
        if (z4VarB.c) {
            z4VarB.a();
            LoggerImpl.global().debug(1, "Oaid#getOaid timeoutMills=100", new Object[0]);
            if (z4.m == null) {
                long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                try {
                    zTryLock = z4VarB.f347a.tryLock(100L, TimeUnit.MILLISECONDS);
                    try {
                        long jElapsedRealtime3 = SystemClock.elapsedRealtime() - jElapsedRealtime2;
                        LoggerImpl.global().debug(1, "Oaid#getOaid locked=" + zTryLock + ", took " + jElapsedRealtime3 + " ms", new Object[0]);
                    } catch (Throwable th) {
                        th = th;
                        try {
                            LoggerImpl.global().error(1, "Get oaid failed", th, new Object[0]);
                            LoggerImpl.global().debug(1, "Oaid#getOaid return apiMap={}", z4.m);
                            map = z4.m;
                            long jElapsedRealtime4 = SystemClock.elapsedRealtime() - jElapsedRealtime;
                            LoggerImpl.global().debug(1, "getOaid takes " + jElapsedRealtime4 + " ms", new Object[0]);
                            return map;
                        } finally {
                            if (zTryLock) {
                                z4VarB.f347a.unlock();
                            }
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    zTryLock = false;
                }
                if (zTryLock) {
                }
            }
            LoggerImpl.global().debug(1, "Oaid#getOaid return apiMap={}", z4.m);
            map = z4.m;
        } else {
            map = null;
        }
        long jElapsedRealtime42 = SystemClock.elapsedRealtime() - jElapsedRealtime;
        LoggerImpl.global().debug(1, "getOaid takes " + jElapsedRealtime42 + " ms", new Object[0]);
        return map;
    }
}
