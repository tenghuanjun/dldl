package a.a.a.c;

import a.a.a.c.m.a;
import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: compiled from: AliDetectImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends l {
    public static AtomicBoolean b = new AtomicBoolean(false);

    public final void a(a.a.a.c.m.a aVar) {
        List<a.C0002a> list;
        if (TextUtils.isEmpty(aVar.h) || (list = aVar.e) == null || list.isEmpty()) {
            return;
        }
        String str = aVar.h;
        for (a.C0002a c0002a : aVar.e) {
            if (str.compareTo(c0002a.f92a) >= 0 && str.compareTo(c0002a.b) <= 0) {
                aVar.i = c0002a.c;
                aVar.j = c0002a.d;
                aVar.g = c0002a.f;
                aVar.f = c0002a.e;
            }
        }
    }

    public static /* synthetic */ void a(c cVar, a.a.a.c.m.a aVar) {
        cVar.getClass();
        if (b.get()) {
            return;
        }
        try {
            cVar.a(aVar);
            if (TextUtils.isEmpty(aVar.i)) {
                d.d("AL", "set holder name failed");
                return;
            }
            Object objA = a.a.a.g.a.a(aVar.i, aVar.j);
            Class<?> cls = Class.forName(aVar.c);
            if (objA == null) {
                d.d("AL", "get lg field in aa null");
            }
            a.a.a.g.a.a(Class.forName(aVar.i), aVar.j, Proxy.newProxyInstance(aVar.getClass().getClassLoader(), new Class[]{cls}, new b(cVar, aVar, objA)));
            b.set(true);
        } catch (Exception e) {
            d.a("AL", Log.getStackTraceString(e));
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static /* synthetic */ String a(a.a.a.c.m.a aVar, Activity activity) {
        Class<?> cls;
        Constructor<?> constructor;
        Object objNewInstance = null;
        try {
            try {
                cls = Class.forName(aVar.f91a);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                cls = null;
            }
            if (cls == null) {
                return "";
            }
            try {
                constructor = cls.getConstructor(Activity.class);
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
                constructor = null;
            }
            if (constructor == null) {
                return "";
            }
            try {
                objNewInstance = constructor.newInstance(activity);
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            } catch (InstantiationException e4) {
                e4.printStackTrace();
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
            }
            return (String) a.a.a.g.a.a(objNewInstance, aVar.b, new Object[0]);
        } catch (Throwable th) {
            d.d("AL", Log.getStackTraceString(th));
            return "";
        }
    }

    public static /* synthetic */ void a(c cVar, a.a.a.c.m.a aVar, String str) {
        byte b2;
        cVar.getClass();
        d.a("AL", "handleOnResCallback:\n" + str);
        d.a("AL", "config.successStr1:\n" + aVar.l);
        d.a("AL", "config.element:\n" + aVar.k);
        try {
            int i = -1;
            if (str.contains(aVar.l)) {
                b2 = 9000;
            } else {
                b2 = str.contains(aVar.m) ? (byte) 4000 : (byte) -1;
            }
            if (b2 != -1 && str.contains(aVar.n)) {
                String strReplace = str.substring(str.indexOf(aVar.n), str.length() - 1).replace(aVar.n, "");
                try {
                    double d = Double.parseDouble(strReplace.substring(0, strReplace.indexOf("\"")));
                    d.b("AL", "amount:" + d);
                    i = ((int) (d * 100.0d)) ^ aVar.k;
                } catch (Throwable th) {
                    d.b("AL", Log.getStackTraceString(th));
                }
            }
            if (h.a("reportEnd")) {
                return;
            }
            if (b2 != 9000 && b2 != 4000) {
                cVar.f90a.a(2);
                return;
            }
            cVar.f90a.a(2, i);
        } catch (Throwable th2) {
            d.d("AL", Log.getStackTraceString(th2));
        }
    }

    @Override // a.a.a.c.l
    public void a(Application application, a.a.a.c.m.b bVar, a.a.a.a aVar) {
        this.f90a = aVar;
        if (h.a("gatherAL") || application == null) {
            return;
        }
        String strB = a.a.a.b.d.b(bVar.f, bVar.d);
        a.a.a.c.m.a aVar2 = null;
        if (TextUtils.isEmpty(strB)) {
            d.d("Utils", "config str is empty");
        } else {
            try {
                JSONObject jSONObject = new JSONObject(strB);
                a.a.a.c.m.a aVar3 = new a.a.a.c.m.a();
                aVar3.a(aVar3, jSONObject);
                aVar2 = aVar3;
            } catch (Exception e) {
                d.d("Utils", Log.getStackTraceString(e));
            }
        }
        if (aVar2 == null) {
            return;
        }
        d.a("AL", "AAConfig:" + aVar2);
        if (!a(aVar2.f91a)) {
            d.a("AL", "not has AL");
        } else {
            application.registerActivityLifecycleCallbacks(new a(this, aVar2));
        }
    }
}
