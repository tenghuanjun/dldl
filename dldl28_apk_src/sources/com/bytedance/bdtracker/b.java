package com.bytedance.bdtracker;

import android.text.TextUtils;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.IAppLogInstance;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static d f216a = new a();
    public static d b = new C0149b();

    public static class a implements d {
        @Override // com.bytedance.bdtracker.b.d
        public boolean a(com.bytedance.bdtracker.d dVar) {
            return dVar.isH5CollectEnable();
        }
    }

    /* JADX INFO: renamed from: com.bytedance.bdtracker.b$b, reason: collision with other inner class name */
    public static class C0149b implements d {
        @Override // com.bytedance.bdtracker.b.d
        public boolean a(com.bytedance.bdtracker.d dVar) {
            return dVar.getInitConfig() != null && dVar.getInitConfig().isHandleLifeCycle();
        }
    }

    public interface c {
        void a(com.bytedance.bdtracker.d dVar);
    }

    public interface d {
        boolean a(com.bytedance.bdtracker.d dVar);
    }

    public interface e {
        j3 a();
    }

    public static com.bytedance.bdtracker.d a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (com.bytedance.bdtracker.d dVar : com.bytedance.bdtracker.d.J) {
            if (str.equals(dVar.m)) {
                return dVar;
            }
        }
        return null;
    }

    public static boolean b(String str) {
        if (!TextUtils.isEmpty(str)) {
            Iterator<com.bytedance.bdtracker.d> it = com.bytedance.bdtracker.d.J.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().m)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String a(IAppLogInstance iAppLogInstance, String str) {
        if (AppLog.getInstance() == iAppLogInstance) {
            return str;
        }
        return str + "_" + iAppLogInstance.getAppId();
    }

    public static void a(c cVar) {
        Iterator<com.bytedance.bdtracker.d> it = com.bytedance.bdtracker.d.J.iterator();
        while (it.hasNext()) {
            cVar.a(it.next());
        }
    }

    public static boolean a(d dVar) {
        Iterator<com.bytedance.bdtracker.d> it = com.bytedance.bdtracker.d.J.iterator();
        while (it.hasNext()) {
            if (dVar.a(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static void a(String[] strArr) {
        Iterator<com.bytedance.bdtracker.d> it = com.bytedance.bdtracker.d.J.iterator();
        while (it.hasNext()) {
            it.next().receive((String[]) strArr.clone());
        }
    }

    public static void a(e eVar, d dVar) {
        j3 j3VarA = null;
        for (com.bytedance.bdtracker.d dVar2 : com.bytedance.bdtracker.d.J) {
            if (dVar.a(dVar2)) {
                if (j3VarA == null) {
                    j3VarA = eVar.a();
                }
                dVar2.receive(j3VarA.m6355clone());
            }
        }
    }

    public static void a(j3 j3Var, d dVar) {
        for (com.bytedance.bdtracker.d dVar2 : com.bytedance.bdtracker.d.J) {
            if (dVar.a(dVar2)) {
                dVar2.receive(j3Var.m6355clone());
            }
        }
    }
}
