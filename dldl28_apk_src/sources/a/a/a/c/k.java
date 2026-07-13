package a.a.a.c;

import android.app.Activity;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: WwDetectImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends l {
    public static final AtomicBoolean b = new AtomicBoolean(false);
    public static WeakReference<Activity> c;
    public static WeakReference<Activity> d;

    public static int a(Activity activity, a.a.a.c.m.c cVar) {
        try {
            if (activity != null) {
                return ((Integer) a.a.a.g.a.a(a.a.a.g.a.a(cVar.f, cVar.g, activity, "", false), cVar.l, new Object[0])).intValue();
            }
            d.d("WW", "getVersion but activity == null");
            return -1;
        } catch (Exception e) {
            d.d("WW", Log.getStackTraceString(e));
            return -1;
        }
    }

    public static boolean a(a.a.a.c.m.c cVar) {
        try {
            Class.forName(cVar.f94a);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // a.a.a.c.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.app.Application r7, a.a.a.c.m.b r8, a.a.a.a r9) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.a.c.k.a(android.app.Application, a.a.a.c.m.b, a.a.a.a):void");
    }
}
