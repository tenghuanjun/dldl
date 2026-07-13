package com.bytedance.bdtracker;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.applog.IOaidObserver;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.bdtracker.c0;
import com.bytedance.dr.OaidApi;
import com.bytedance.dr.OaidFactory;
import com.bytedance.dr.impl.e;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class z4 {
    public static final String h = "z4#";
    public static final String i = "z4#";
    public static final List<IOaidObserver> j = new ArrayList();
    public static g k;
    public static String l;
    public static Map<String, String> m;
    public final OaidApi b;
    public final boolean c;
    public final b5 d;
    public final Context e;
    public Long g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ReentrantLock f347a = new ReentrantLock();
    public final AtomicBoolean f = new AtomicBoolean(false);

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            z4.this.b();
        }
    }

    public z4(Context context) {
        this.e = context.getApplicationContext();
        OaidApi oaidApiCreateOaidImpl = OaidFactory.createOaidImpl(context);
        this.b = oaidApiCreateOaidImpl;
        if (oaidApiCreateOaidImpl != null) {
            this.c = oaidApiCreateOaidImpl.support(context);
        } else {
            this.c = false;
        }
        this.d = new b5(context);
    }

    public static void a(IOaidObserver.Oaid oaid, Object[] objArr) {
        if (oaid == null || objArr == null) {
            return;
        }
        for (Object obj : objArr) {
            ((IOaidObserver) obj).onOaidLoaded(oaid);
        }
    }

    public static void a(IOaidObserver iOaidObserver) {
        g gVar;
        List<IOaidObserver> list = j;
        synchronized (list) {
            list.add(iOaidObserver);
        }
        String str = l;
        if (str != null) {
            a(new IOaidObserver.Oaid(str), new Object[]{iOaidObserver});
        }
        Map<String, String> map = m;
        if (map == null || (gVar = k) == null) {
            return;
        }
        ((c0.b) gVar).a(map);
    }

    public static <K, V> void a(Map<K, V> map, K k2, V v) {
        if (k2 == null || v == null) {
            return;
        }
        map.put(k2, v);
    }

    public static void a(JSONObject jSONObject, String str, Object obj) {
        if (TextUtils.isEmpty(str) || obj == null) {
            return;
        }
        try {
            jSONObject.put(str, obj);
        } catch (Throwable th) {
            LoggerImpl.global().error(1, "JSON put failed", th, new Object[0]);
        }
    }

    public static void b(IOaidObserver iOaidObserver) {
        List<IOaidObserver> list = j;
        synchronized (list) {
            list.remove(iOaidObserver);
        }
    }

    public static Object[] c() {
        Object[] array;
        List<IOaidObserver> list = j;
        synchronized (list) {
            array = list.size() > 0 ? list.toArray() : null;
        }
        return array;
    }

    public void a() {
        if (this.f.compareAndSet(false, true)) {
            a aVar = new a();
            String str = i + "-query";
            if (TextUtils.isEmpty(str)) {
                str = "TrackerDr";
            }
            new Thread(new y4(aVar, str), str).start();
        }
    }

    public final void b() {
        String str;
        Boolean boolValueOf;
        int iIntValue;
        OaidApi.a oaid;
        LoggerImpl.global().debug(1, "Oaid#initOaid", new Object[0]);
        try {
            this.f347a.lock();
            LoggerImpl.global().debug(1, "Oaid#initOaid exec", new Object[0]);
            a5 a5VarA = this.d.a();
            LoggerImpl.global().debug(1, "Oaid#initOaid fetch={}", a5VarA);
            if (a5VarA != null) {
                l = a5VarA.f215a;
                m = a5VarA.a();
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            Context context = this.e;
            OaidApi oaidApi = this.b;
            a5 a5Var = null;
            String string = null;
            if (oaidApi == null || (oaid = oaidApi.getOaid(context)) == null) {
                str = null;
                boolValueOf = null;
            } else {
                str = oaid.f366a;
                boolValueOf = Boolean.valueOf(oaid.b);
                if (oaid instanceof e.b) {
                    this.g = Long.valueOf(((e.b) oaid).c);
                }
            }
            Pair pair = new Pair(str, boolValueOf);
            long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
            if (pair.first != null) {
                if (a5VarA != null) {
                    string = a5VarA.b;
                    iIntValue = a5VarA.f.intValue() + 1;
                } else {
                    iIntValue = -1;
                }
                if (TextUtils.isEmpty(string)) {
                    string = UUID.randomUUID().toString();
                }
                if (iIntValue <= 0) {
                    iIntValue = 1;
                }
                a5 a5Var2 = new a5((String) pair.first, string, (Boolean) pair.second, Long.valueOf(jElapsedRealtime2), Long.valueOf(System.currentTimeMillis()), Integer.valueOf(iIntValue), this.g);
                this.d.a(a5Var2);
                a5Var = a5Var2;
            }
            if (a5Var != null) {
                l = a5Var.f215a;
                m = a5Var.a();
            }
            LoggerImpl.global().debug(1, "Oaid#initOaid oaidModel={}", a5Var);
        } finally {
            this.f347a.unlock();
            a(new IOaidObserver.Oaid(l), c());
            g gVar = k;
            if (gVar != null) {
                ((c0.b) gVar).a(m);
            }
        }
    }
}
