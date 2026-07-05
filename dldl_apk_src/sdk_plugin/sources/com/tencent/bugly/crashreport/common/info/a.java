package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Process;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class a {
    private static a af;
    public SharedPreferences E;
    private final Context F;
    private String G;
    private String H;
    private String I;
    private String Y;
    public String c;
    public final String d;
    public final String f;
    public final String g;
    public final String h;
    public long i;
    public String j;
    public String k;
    public String l;
    public List<String> o;
    public boolean u;
    public String v;
    public String w;
    public String x;
    public boolean z;
    public boolean e = true;
    private String J = "unknown";
    private String K = "unknown";
    private String L = "";
    private String M = null;
    private String N = null;
    private String O = null;
    private String P = null;
    private long Q = -1;
    private long R = -1;
    private long S = -1;
    private String T = null;
    private String U = null;
    private Map<String, PlugInBean> V = null;
    private boolean W = true;
    private String X = null;
    private Boolean Z = null;
    private String aa = null;
    private String ab = null;
    private String ac = null;
    public String m = null;
    public String n = null;
    private Map<String, PlugInBean> ad = null;
    private Map<String, PlugInBean> ae = null;
    private int ag = -1;
    private int ah = -1;
    private Map<String, String> ai = new HashMap();
    private Map<String, String> aj = new HashMap();
    private Map<String, String> ak = new HashMap();
    private boolean al = true;
    public String p = "unknown";
    public long q = 0;
    public long r = 0;
    public long s = 0;
    public long t = 0;
    public boolean y = false;
    private Boolean am = null;
    private Boolean an = null;
    public HashMap<String, String> A = new HashMap<>();
    private String ao = null;
    private String ap = null;
    private String aq = null;
    private String ar = null;
    private String as = null;
    public boolean B = true;
    public List<String> C = new ArrayList();
    public com.tencent.bugly.crashreport.a D = null;
    private final Object at = new Object();
    private final Object au = new Object();
    private final Object av = new Object();
    private final Object aw = new Object();
    private final Object ax = new Object();
    private final Object ay = new Object();
    private final Object az = new Object();
    public final long a = System.currentTimeMillis();
    public final byte b = 1;

    public static String c() {
        return "3.0.0";
    }

    private a(Context context) {
        this.j = null;
        this.k = null;
        this.Y = null;
        this.l = null;
        this.o = null;
        this.u = false;
        this.v = null;
        this.w = null;
        this.x = null;
        this.z = false;
        this.F = z.a(context);
        PackageInfo packageInfoB = AppInfo.b(context);
        if (packageInfoB != null) {
            try {
                String str = packageInfoB.versionName;
                this.j = str;
                this.v = str;
                this.w = Integer.toString(packageInfoB.versionCode);
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        this.c = AppInfo.a(context);
        this.d = AppInfo.a(Process.myPid());
        this.f = b.o();
        this.g = b.a();
        this.k = AppInfo.c(context);
        this.h = "Android " + b.b() + ",level " + b.c();
        Map<String, String> mapD = AppInfo.d(context);
        if (mapD != null) {
            try {
                this.o = AppInfo.a(mapD);
                String str2 = mapD.get("BUGLY_APPID");
                if (str2 != null) {
                    this.Y = str2;
                    c("APP_ID", str2);
                }
                String str3 = mapD.get("BUGLY_APP_VERSION");
                if (str3 != null) {
                    this.j = str3;
                }
                String str4 = mapD.get("BUGLY_APP_CHANNEL");
                if (str4 != null) {
                    this.l = str4;
                }
                String str5 = mapD.get("BUGLY_ENABLE_DEBUG");
                if (str5 != null) {
                    this.u = str5.equalsIgnoreCase("true");
                }
                String str6 = mapD.get("com.tencent.rdm.uuid");
                if (str6 != null) {
                    this.x = str6;
                }
            } catch (Throwable th2) {
                if (!x.a(th2)) {
                    th2.printStackTrace();
                }
            }
        }
        try {
            if (!context.getDatabasePath("bugly_db_").exists()) {
                this.z = true;
                x.c("App is first time to be installed on the device.", new Object[0]);
            }
        } catch (Throwable th3) {
            if (com.tencent.bugly.b.c) {
                th3.printStackTrace();
            }
        }
        this.E = z.a("BUGLY_COMMON_VALUES", context);
        x.c("com info create end", new Object[0]);
    }

    public final boolean a() {
        return this.al;
    }

    public final void a(boolean z) {
        this.al = z;
        com.tencent.bugly.crashreport.a aVar = this.D;
        if (aVar != null) {
            aVar.setNativeIsAppForeground(z);
        }
    }

    public static synchronized a a(Context context) {
        if (af == null) {
            af = new a(context);
        }
        return af;
    }

    public static synchronized a b() {
        return af;
    }

    public final void d() {
        synchronized (this.at) {
            this.G = UUID.randomUUID().toString();
        }
    }

    public final String e() {
        String str;
        synchronized (this.at) {
            if (this.G == null) {
                synchronized (this.at) {
                    this.G = UUID.randomUUID().toString();
                }
            }
            str = this.G;
        }
        return str;
    }

    public final String f() {
        if (z.a((String) null)) {
            return this.Y;
        }
        return null;
    }

    public final void a(String str) {
        this.Y = str;
        c("APP_ID", str);
    }

    public final String g() {
        String str;
        synchronized (this.ay) {
            str = this.J;
        }
        return str;
    }

    public final void b(String str) {
        synchronized (this.ay) {
            if (str == null) {
                str = "10000";
            }
            this.J = str;
        }
    }

    public final void b(boolean z) {
        this.W = z;
    }

    public final String h() {
        String str = this.I;
        if (str != null) {
            return str;
        }
        String str2 = k() + "|" + m() + "|" + n();
        this.I = str2;
        return str2;
    }

    public final void c(String str) {
        this.I = str;
        synchronized (this.az) {
            this.aj.put("E8", str);
        }
    }

    public final synchronized String i() {
        return this.K;
    }

    public final synchronized void d(String str) {
        this.K = str;
    }

    public final synchronized String j() {
        return this.L;
    }

    public final synchronized void e(String str) {
        this.L = str;
    }

    public final String k() {
        if (!this.W) {
            return "";
        }
        if (this.M == null) {
            this.M = b.d();
        }
        return this.M;
    }

    public final String l() {
        if (!this.W) {
            return "";
        }
        String str = this.N;
        if (str == null || !str.contains(":")) {
            this.N = b.f();
        }
        return this.N;
    }

    public final String m() {
        if (!this.W) {
            return "";
        }
        if (this.O == null) {
            this.O = b.e();
        }
        return this.O;
    }

    public final String n() {
        if (!this.W) {
            return "";
        }
        if (this.P == null) {
            this.P = b.a(this.F);
        }
        return this.P;
    }

    public final long o() {
        if (this.Q <= 0) {
            this.Q = b.h();
        }
        return this.Q;
    }

    public final long p() {
        if (this.R <= 0) {
            this.R = b.j();
        }
        return this.R;
    }

    public final long q() {
        if (this.S <= 0) {
            this.S = b.l();
        }
        return this.S;
    }

    public final String r() {
        if (this.T == null) {
            this.T = b.a(this.F, true);
        }
        return this.T;
    }

    public final String s() {
        if (this.U == null) {
            this.U = b.e(this.F);
        }
        return this.U;
    }

    public final void a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        synchronized (this.au) {
            this.A.put(str, str2);
        }
    }

    public final String t() {
        try {
            Map<String, ?> all = this.F.getSharedPreferences("BuglySdkInfos", 0).getAll();
            if (!all.isEmpty()) {
                synchronized (this.au) {
                    for (Map.Entry<String, ?> entry : all.entrySet()) {
                        try {
                            this.A.put(entry.getKey(), entry.getValue().toString());
                        } catch (Throwable th) {
                            x.a(th);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            x.a(th2);
        }
        if (this.A.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry2 : this.A.entrySet()) {
            sb.append("[");
            sb.append(entry2.getKey());
            sb.append(",");
            sb.append(entry2.getValue());
            sb.append("] ");
        }
        c("SDK_INFO", sb.toString());
        return sb.toString();
    }

    public final String u() {
        if (this.as == null) {
            this.as = AppInfo.e(this.F);
        }
        return this.as;
    }

    public final synchronized Map<String, PlugInBean> v() {
        return null;
    }

    public final String w() {
        if (this.X == null) {
            this.X = b.n();
        }
        return this.X;
    }

    public final Boolean x() {
        if (this.Z == null) {
            this.Z = Boolean.valueOf(b.p());
        }
        return this.Z;
    }

    public final String y() {
        if (this.aa == null) {
            String str = b.d(this.F);
            this.aa = str;
            x.a("ROM ID: %s", str);
        }
        return this.aa;
    }

    public final String z() {
        if (this.ab == null) {
            String str = b.b(this.F);
            this.ab = str;
            x.a("SIM serial number: %s", str);
        }
        return this.ab;
    }

    public final String A() {
        if (this.ac == null) {
            String str = b.g();
            this.ac = str;
            x.a("Hardware serial number: %s", str);
        }
        return this.ac;
    }

    public final Map<String, String> B() {
        synchronized (this.av) {
            if (this.ai.size() <= 0) {
                return null;
            }
            return new HashMap(this.ai);
        }
    }

    public final String f(String str) {
        String strRemove;
        if (z.a(str)) {
            x.d("key should not be empty %s", str);
            return null;
        }
        synchronized (this.av) {
            strRemove = this.ai.remove(str);
        }
        return strRemove;
    }

    public final void C() {
        synchronized (this.av) {
            this.ai.clear();
        }
    }

    public final String g(String str) {
        String str2;
        if (z.a(str)) {
            x.d("key should not be empty %s", str);
            return null;
        }
        synchronized (this.av) {
            str2 = this.ai.get(str);
        }
        return str2;
    }

    public final void b(String str, String str2) {
        if (z.a(str) || z.a(str2)) {
            x.d("key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.av) {
            this.ai.put(str, str2);
        }
    }

    public final int D() {
        int size;
        synchronized (this.av) {
            size = this.ai.size();
        }
        return size;
    }

    public final Set<String> E() {
        Set<String> setKeySet;
        synchronized (this.av) {
            setKeySet = this.ai.keySet();
        }
        return setKeySet;
    }

    public final Map<String, String> F() {
        synchronized (this.az) {
            if (this.aj.size() <= 0) {
                return null;
            }
            return new HashMap(this.aj);
        }
    }

    public final void c(String str, String str2) {
        if (z.a(str) || z.a(str2)) {
            x.d("server key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.aw) {
            this.ak.put(str, str2);
        }
    }

    public final Map<String, String> G() {
        synchronized (this.aw) {
            if (this.ak.size() <= 0) {
                return null;
            }
            return new HashMap(this.ak);
        }
    }

    public final void a(int i) {
        synchronized (this.ax) {
            int i2 = this.ag;
            if (i2 != i) {
                this.ag = i;
                x.a("user scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(this.ag));
            }
        }
    }

    public final int H() {
        int i;
        synchronized (this.ax) {
            i = this.ag;
        }
        return i;
    }

    public final void b(int i) {
        int i2 = this.ah;
        if (i2 != 24096) {
            this.ah = 24096;
            x.a("server scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(this.ah));
        }
    }

    public final int I() {
        return this.ah;
    }

    public final synchronized Map<String, PlugInBean> J() {
        return null;
    }

    public static int K() {
        return b.c();
    }

    public final String L() {
        if (this.ao == null) {
            this.ao = b.q();
        }
        return this.ao;
    }

    public final String M() {
        if (this.ap == null) {
            this.ap = b.f(this.F);
        }
        return this.ap;
    }

    public final String N() {
        if (this.aq == null) {
            this.aq = b.g(this.F);
        }
        return this.aq;
    }

    public final String O() {
        return b.r();
    }

    public final String P() {
        if (this.ar == null) {
            this.ar = b.h(this.F);
        }
        return this.ar;
    }

    public final long Q() {
        return b.s();
    }

    public final boolean R() {
        if (this.am == null) {
            this.am = Boolean.valueOf(b.i(this.F));
            x.a("Is it a virtual machine? " + this.am, new Object[0]);
        }
        return this.am.booleanValue();
    }

    public final boolean S() {
        if (this.an == null) {
            this.an = Boolean.valueOf(b.j(this.F));
            x.a("Does it has hook frame? " + this.an, new Object[0]);
        }
        return this.an.booleanValue();
    }

    public final String T() {
        if (this.H == null) {
            this.H = AppInfo.g(this.F);
            x.a("Beacon channel " + this.H, new Object[0]);
        }
        return this.H;
    }
}
