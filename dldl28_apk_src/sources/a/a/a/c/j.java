package a.a.a.c;

import android.util.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: compiled from: WwDetectImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements InvocationHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a.a.a.c.m.c f88a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ k c;

    /* JADX INFO: compiled from: WwDetectImpl.java */
    public class a implements InvocationHandler {
        public a() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            try {
                if (method.getName().equals(j.this.f88a.i)) {
                    int iIntValue = ((Integer) a.a.a.g.a.a(objArr[0], j.this.f88a.j)).intValue();
                    d.d("WW", "onRp: in detect code:" + iIntValue);
                    k.b.set(false);
                    k kVar = j.this.c;
                    int i = j.this.f88a.n;
                    if (kVar == null) {
                        throw null;
                    }
                    if (!h.a("reportEnd")) {
                        if (iIntValue == 0) {
                            kVar.f90a.a(1, i);
                        } else {
                            kVar.f90a.a(1);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
            return null;
        }
    }

    public j(k kVar, a.a.a.c.m.c cVar, Object obj) {
        this.c = kVar;
        this.f88a = cVar;
        this.b = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            if (objArr[1].toString().contains(this.f88a.r.f)) {
                if (!k.b.get()) {
                    k.b.set(true);
                    a.a.a.g.a.a(a.a.a.g.a.a(this.f88a.f, this.f88a.g, k.c.get(), "", false), this.f88a.e, k.c.get().getIntent(), Proxy.newProxyInstance(this.f88a.getClass().getClassLoader(), new Class[]{Class.forName(this.f88a.h)}, new a()));
                }
            } else if (objArr[1].toString().contains(this.f88a.r.g)) {
                k kVar = this.c;
                if (kVar == null) {
                    throw null;
                }
                if (!h.a("reportStart")) {
                    kVar.f90a.b(1);
                }
            }
        } catch (Throwable th) {
            d.d("WW", Log.getStackTraceString(th));
        }
        Object obj2 = this.b;
        if (obj2 == null) {
            return null;
        }
        try {
            return method.invoke(obj2, objArr);
        } catch (Throwable unused) {
            return null;
        }
    }
}
