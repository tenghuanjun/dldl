package com.bytedance.bdtracker;

import android.os.Handler;
import android.text.TextUtils;
import com.bytedance.applog.util.SensitiveUtils;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;

/* JADX INFO: loaded from: classes2.dex */
public abstract class k3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k3 f277a;
    public Handler b;

    public class a implements h<String> {
        public a() {
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String a() {
            return k3.this.b("openudid");
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean b(String str) {
            return n0.e(str);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean a(String str, String str2) {
            return n0.a(str, str2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String a(String str, String str2, k3 k3Var) {
            String str3 = str;
            return k3Var == null ? str3 : k3Var.d(str3, str2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public void a(String str) {
            k3.this.a("openudid", str);
        }
    }

    public class b implements h<String> {
        public b() {
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String a() {
            return k3.this.b("clientudid");
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean b(String str) {
            return n0.e(str);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean a(String str, String str2) {
            return n0.a(str, str2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String a(String str, String str2, k3 k3Var) {
            String str3 = str;
            return k3Var == null ? str3 : k3Var.b(str3, str2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public void a(String str) {
            k3.this.a("clientudid", str);
        }
    }

    public class c implements h<String> {
        public c() {
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String a() {
            return k3.this.b("serial_number");
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean b(String str) {
            String str2 = str;
            return (TextUtils.isEmpty(str2) || TextUtils.equals(str2, "unknown")) ? false : true;
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean a(String str, String str2) {
            return n0.a(str, str2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String a(String str, String str2, k3 k3Var) {
            String str3 = str;
            return k3Var == null ? str3 : k3Var.e(str3, str2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public void a(String str) {
            k3.this.a("serial_number", str);
        }
    }

    public class d implements h<String[]> {
        public d() {
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String[] a() {
            return k3.this.c("sim_serial_number");
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean b(String[] strArr) {
            String[] strArr2 = strArr;
            return strArr2 != null && strArr2.length > 0;
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean a(String[] strArr, String[] strArr2) {
            String[] strArr3 = strArr;
            String[] strArr4 = strArr2;
            if (strArr3 == strArr4) {
                return true;
            }
            if (strArr3 != null && strArr4 != null && strArr3.length == strArr4.length) {
                for (String str : strArr3) {
                    boolean z = false;
                    for (String str2 : strArr4) {
                        z = n0.a(str2, str) || z;
                    }
                    if (z) {
                    }
                }
                return true;
            }
            return false;
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String[] a(String[] strArr, String[] strArr2, k3 k3Var) {
            String[] strArr3 = strArr;
            return k3Var == null ? strArr3 : k3Var.a(strArr3, strArr2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public void a(String[] strArr) {
            k3.this.a("sim_serial_number", strArr);
        }
    }

    public class e implements h<String> {
        public e() {
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String a() {
            return k3.this.b("udid");
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean b(String str) {
            return n0.e(str);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean a(String str, String str2) {
            return n0.a(str, str2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String a(String str, String str2, k3 k3Var) {
            String str3 = str;
            return k3Var == null ? str3 : k3Var.f(str3, str2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public void a(String str) {
            k3.this.a("udid", str);
        }
    }

    public class f implements h<String> {
        public f() {
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String a() {
            return k3.this.b("udid_list");
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean b(String str) {
            return SensitiveUtils.validMultiImei(str);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean a(String str, String str2) {
            return n0.a(str, str2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String a(String str, String str2, k3 k3Var) {
            String str3 = str;
            return k3Var == null ? str3 : k3Var.g(str3, str2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public void a(String str) {
            k3.this.a("udid_list", str);
        }
    }

    public class g implements h<String> {
        public g() {
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String a() {
            return k3.this.b(MonitorConstants.KEY_DEVICE_ID);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean b(String str) {
            return !TextUtils.isEmpty(str);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public boolean a(String str, String str2) {
            return n0.a(str, str2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public String a(String str, String str2, k3 k3Var) {
            String str3 = str;
            return k3Var == null ? str3 : k3Var.c(str3, str2);
        }

        @Override // com.bytedance.bdtracker.k3.h
        public void a(String str) {
            k3.this.a(MonitorConstants.KEY_DEVICE_ID, str);
        }
    }

    public interface h<L> {
        L a();

        L a(L l, L l2, k3 k3Var);

        void a(L l);

        boolean a(L l, L l2);

        boolean b(L l);
    }

    public void a(String str) {
        k3 k3Var = this.f277a;
        if (k3Var != null) {
            k3Var.a(str);
        }
    }

    public abstract void a(String str, String str2);

    public abstract void a(String str, String[] strArr);

    public String[] a(String[] strArr, String[] strArr2) {
        return (String[]) a(strArr, strArr2, new d());
    }

    public abstract String b(String str);

    public String b(String str, String str2) {
        return (String) a(str, str2, new b());
    }

    public String c(String str, String str2) {
        return (String) a(str, str2, new g());
    }

    public abstract String[] c(String str);

    public String d(String str, String str2) {
        return (String) a(str, str2, new a());
    }

    public String e(String str, String str2) {
        return (String) a(str, str2, new c());
    }

    public String f(String str, String str2) {
        return (String) a(str, str2, new e());
    }

    public String g(String str, String str2) {
        return (String) a(str, str2, new f());
    }

    public final <T> T a(T t, T t2, h<T> hVar) {
        boolean z;
        if (hVar == null) {
            throw new IllegalArgumentException("agent == null");
        }
        k3 k3Var = this.f277a;
        T tA = hVar.a();
        boolean zB = hVar.b(t);
        boolean zB2 = hVar.b(tA);
        if (!zB && zB2) {
            t = tA;
        }
        if (k3Var != null) {
            T tA2 = hVar.a(t, t2, k3Var);
            if (!hVar.a(tA2, tA)) {
                hVar.a(tA2);
            }
            return tA2;
        }
        if (zB || zB2) {
            t2 = t;
            z = false;
        } else {
            z = true;
        }
        if ((z && hVar.b(t2)) || (zB && !hVar.a(t2, tA))) {
            hVar.a(t2);
        }
        return t2;
    }

    public void a(Handler handler) {
        k3 k3Var = this.f277a;
        if (k3Var != null) {
            k3Var.a(handler);
        }
        this.b = handler;
    }
}
