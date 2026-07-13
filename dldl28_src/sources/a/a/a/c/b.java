package a.a.a.c;

import android.util.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: AliDetectImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements InvocationHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a.a.a.c.m.a f81a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ c c;

    /* JADX INFO: compiled from: AliDetectImpl.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f82a;

        public a(String str) {
            this.f82a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                c.a(b.this.c, b.this.f81a, this.f82a);
            } catch (Throwable th) {
                d.b("AL", Log.getStackTraceString(th));
            }
        }
    }

    public b(c cVar, a.a.a.c.m.a aVar, Object obj) {
        this.c = cVar;
        this.f81a = aVar;
        this.b = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            if (method.getName().contains(this.f81a.d)) {
                String str = (String) objArr[0];
                if (str.contains(this.f81a.f)) {
                    a.a.a.c.n.a.a(new a(str));
                } else if (str.contains(this.f81a.g)) {
                    c cVar = this.c;
                    if (cVar == null) {
                        throw null;
                    }
                    if (!h.a("reportStart")) {
                        cVar.f90a.b(2);
                    }
                }
            }
        } catch (Throwable th) {
            d.b("AL", Log.getStackTraceString(th));
        }
        Object obj2 = this.b;
        if (obj2 != null) {
            try {
                return method.invoke(obj2, objArr);
            } catch (Throwable th2) {
                d.d("AL", Log.getStackTraceString(th2));
            }
        }
        return null;
    }
}
