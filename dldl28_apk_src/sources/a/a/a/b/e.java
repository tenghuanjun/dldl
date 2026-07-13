package a.a.a.b;

import a.a.a.b.a;
import a.a.a.d.c;
import a.a.a.e.a;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.kwai.monitor.log.TurboConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: TurboSDK.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    public static String k = "";
    public static String l = "";
    public static String m;
    public static JSONObject n;
    public static boolean o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile int f71a;
    public final List<a.a.a.d.a> b;
    public Context c;
    public String d;
    public String e;
    public String f;
    public String g;
    public final Handler h;
    public volatile boolean i;
    public volatile boolean j;

    /* JADX INFO: compiled from: TurboSDK.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e eVar = e.this;
            if (eVar.a()) {
                eVar.h.postDelayed(new f(eVar), 5000L);
            } else {
                eVar.d();
            }
        }
    }

    /* JADX INFO: compiled from: TurboSDK.java */
    public enum c {
        INSTANCE;


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final e f74a = new e(null);

        c() {
        }
    }

    public /* synthetic */ e(a aVar) {
        this();
    }

    public static synchronized String h() {
        JSONObject jSONObject = n;
        if (jSONObject == null) {
            a.a.a.g.b.a("TurboSDK", "mJsonObject is null");
            return "";
        }
        try {
            if (!jSONObject.optBoolean("enableGetCallback")) {
                return "";
            }
            String str = m;
            if (str != null) {
                return str;
            }
            String strA = d.a(c.INSTANCE.f74a.c, "ks_callback");
            m = strA;
            if (!TextUtils.isEmpty(strA)) {
                return m;
            }
            try {
                m = a.b.a.b.a(new File(c.INSTANCE.f74a.c.getApplicationInfo().sourceDir));
                d.a(c.INSTANCE.f74a.c, "ks_callback", m);
            } catch (Throwable th) {
                a.a.a.g.b.b("TurboSDK", "get callback info error, error is " + th.getMessage());
            }
            String str2 = TextUtils.isEmpty(m) ? "" : m;
            m = str2;
            return str2;
        } catch (Exception e) {
            a.a.a.g.b.a("TurboSDK", "querySwitchMessage  parse error" + e.getMessage());
            return "";
        }
    }

    public static String i() {
        if (!TextUtils.isEmpty(k)) {
            return k;
        }
        String strA = d.a(c.INSTANCE.f74a.c, "ks_global_id");
        k = strA;
        return a.a.a.g.d.a(strA);
    }

    public static String j() {
        if (!TextUtils.isEmpty(l)) {
            return l;
        }
        l = a.a.a.f.a.a(c.INSTANCE.f74a.c);
        a.a.a.g.b.a("TurboSDK", "oaid:" + l);
        return a.a.a.g.d.a(l);
    }

    public final synchronized boolean b() {
        if (this.i) {
            return true;
        }
        this.i = d.a(this.c, "ks_register_success", false);
        return this.i;
    }

    public final void c() {
        Context context;
        try {
            JSONObject jSONObject = n;
            if (jSONObject == null) {
                return;
            }
            String strOptString = jSONObject.optString("detectConfig");
            if (TextUtils.isEmpty(strOptString) || (context = this.c) == null || !(context instanceof Application)) {
                return;
            }
            a.a.a.c.n.a.a(new a.a.a.c.e(strOptString, (Application) context, new j(this)));
        } catch (Exception e) {
            a.a.a.g.b.a("TurboSDK", "querySwitchMessage  parse error" + e.getMessage());
        }
    }

    public final void d() {
        a.c.INSTANCE.f113a.a("https://api.e.kuaishou.com/rest/config/client/v1/open/globalId", new b());
    }

    public final synchronized void e() {
        if (b()) {
            if (TextUtils.isEmpty(i())) {
                d.a(this.c);
                SharedPreferences sharedPreferences = d.d;
                long j = sharedPreferences != null ? sharedPreferences.getLong("ks_register_get_global_id_time", 0L) : 0L;
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (j == 0) {
                    d.a(this.c, "ks_register_get_global_id_time", jCurrentTimeMillis);
                } else if (jCurrentTimeMillis - j >= 259200000) {
                    d.a(this.c, "ks_register_get_global_id_time", jCurrentTimeMillis);
                    a.c.INSTANCE.f113a.a("https://api.e.kuaishou.com/rest/config/client/v1/open/globalId", new h(this));
                }
            }
            a.b.INSTANCE.f67a.a(this.c);
            c();
        } else {
            this.h.postDelayed(new a(), a() ? 1000L : 0L);
        }
    }

    public final void f() {
        Iterator<a.a.a.d.a> it = this.b.iterator();
        while (it.hasNext()) {
            a.c.INSTANCE.f113a.a("https://ad.partner.gifshow.com/api/v2/sdk/log?token=dee6172daef74f0895c7d185956ac0a7", it.next());
            it.remove();
        }
    }

    public final synchronized void g() {
        int i = this.f71a;
        this.f71a = i + 1;
        if (i < 10) {
            e();
        }
    }

    public e() {
        this.f71a = 0;
        this.b = Collections.synchronizedList(new ArrayList());
        this.h = new Handler(Looper.getMainLooper());
    }

    public synchronized void a(TurboConfig turboConfig) {
        if (this.j) {
            return;
        }
        this.j = true;
        Context applicationContext = turboConfig.mContext.getApplicationContext();
        this.c = applicationContext;
        this.d = turboConfig.mAppId;
        this.e = turboConfig.mAppName;
        this.f = turboConfig.mChannel;
        this.g = applicationContext.getPackageName();
        boolean z = turboConfig.mEnableDebug;
        a.a.a.g.b.f135a = "KS_LOG_1.0.15";
        a.a.a.g.b.b = z;
        a.a.a.g.b.c = false;
        a.a.a.f.a.a(this.c);
        a.a.a.e.a aVar = a.c.INSTANCE.f113a;
        aVar.f104a.execute(new a.a.a.e.b(aVar, "https://api.e.kuaishou.com/rest/config/client/v1/open/sdkGatherConfig", new i(this)));
    }

    public final boolean a() {
        return d.c(j()) && d.c(a.a.a.g.d.a(this.c));
    }

    public void a(a.a.a.d.a aVar) {
        if (!this.j) {
            a.a.a.g.b.d("TurboSDK", "onEvent sdk is not init, please init first eventName:" + aVar.f99a);
        } else {
            if (!o) {
                this.b.add(aVar);
                return;
            }
            if (!b()) {
                a.a.a.g.b.a("TurboSDK", "sdk is not init finish, cache add report later eventName:" + aVar.f99a);
                this.b.add(aVar);
                e();
                return;
            }
            a.c.INSTANCE.f113a.a("https://ad.partner.gifshow.com/api/v2/sdk/log?token=dee6172daef74f0895c7d185956ac0a7", aVar);
        }
    }

    /* JADX INFO: compiled from: TurboSDK.java */
    public class b implements a.a.a.e.e {
        public b() {
        }

        @Override // a.a.a.e.e
        public void a(a.a.a.d.c cVar) {
            c.b bVar;
            if (cVar == null || (bVar = cVar.c) == null || !bVar.b) {
                StringBuilder sb = new StringBuilder("register sdk fail :");
                sb.append(cVar != null ? cVar.b : null);
                a.a.a.g.b.a("TurboSDK", sb.toString());
                e.this.g();
                return;
            }
            a.a.a.g.b.a("TurboSDK", "register sdk success");
            e.this.i = true;
            e eVar = e.this;
            Context context = eVar.c;
            boolean z = eVar.i;
            d.a(context);
            SharedPreferences sharedPreferences = d.d;
            if (sharedPreferences != null) {
                sharedPreferences.edit().putBoolean("ks_register_success", z).apply();
            }
            String str = cVar.c.f103a;
            e.k = str;
            d.a(e.this.c, "ks_global_id", str);
            c.INSTANCE.f74a.a(new a.a.a.d.a("EVENT_CONVERSION"));
            e.this.f();
            a.b.INSTANCE.f67a.a(e.this.c);
            e.this.c();
        }

        @Override // a.a.a.e.e
        public void a(int i, String str) {
            a.a.a.g.b.a("TurboSDK", "register sdk onError errorCode:" + i + "-errorMsg:" + str);
            e.this.g();
        }
    }
}
