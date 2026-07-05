package com.ss.android.downloadlib.a.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Pair;
import com.ss.android.downloadlib.a.a.c;
import com.ss.android.downloadlib.addownload.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a {
    private static String d = "";
    private static String e = "";
    private static String f = "";
    private static volatile a g;
    public c a;
    private Context k;
    private boolean h = true;
    private boolean i = false;
    private volatile boolean j = false;
    private final List<Pair<b, d>> l = new ArrayList();
    public final List<InterfaceC0089a> b = new ArrayList();
    private final ServiceConnection m = new ServiceConnection() { // from class: com.ss.android.downloadlib.a.a.a.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (a.this.c) {
                a.this.a(false);
                a.this.a = c.a.a(iBinder);
                a.this.c();
                Iterator<InterfaceC0089a> it = a.this.b.iterator();
                while (it.hasNext()) {
                    it.next().a();
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (a.this.c) {
                a.this.a(false);
                a.this.a = null;
                Iterator<InterfaceC0089a> it = a.this.b.iterator();
                while (it.hasNext()) {
                    it.next().b();
                }
            }
        }
    };
    private String n = "";
    public final Object c = new Object();

    /* JADX INFO: renamed from: com.ss.android.downloadlib.a.a.a$a, reason: collision with other inner class name */
    public interface InterfaceC0089a {
        void a();

        void b();
    }

    private a() {
    }

    public static a a() {
        if (g == null) {
            synchronized (a.class) {
                if (g == null) {
                    g = new a();
                }
            }
        }
        return g;
    }

    public boolean a(Context context, boolean z) {
        if (TextUtils.isEmpty(d)) {
            JSONObject jSONObjectJ = k.j();
            String strOptString = jSONObjectJ.optString(com.igexin.push.core.d.c.d);
            d = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("q"), strOptString);
            e = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("u"), strOptString);
            f = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("w"), strOptString);
        }
        this.i = z;
        if (context == null) {
            return true;
        }
        this.k = context.getApplicationContext();
        if (TextUtils.isEmpty(f)) {
            f = this.k.getPackageName();
        }
        if (this.a != null || d()) {
            return true;
        }
        return this.k.bindService(a(context), this.m, 33);
    }

    public void b() {
        if (this.a != null) {
            this.k.unbindService(this.m);
            this.a = null;
        }
        this.b.clear();
        this.l.clear();
    }

    public Intent a(Context context) {
        Intent intent = new Intent();
        intent.setAction(d);
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.size() != 1) {
            return null;
        }
        for (ResolveInfo resolveInfo : listQueryIntentServices) {
            String str = resolveInfo.serviceInfo.packageName;
            String str2 = resolveInfo.serviceInfo.name;
            if (e.equals(str)) {
                ComponentName componentName = new ComponentName(str, str2);
                Intent intent2 = new Intent(intent);
                intent2.setComponent(componentName);
                return intent2;
            }
        }
        return null;
    }

    public void a(b bVar, d dVar) {
        synchronized (this.c) {
            bVar.e = f;
            if (TextUtils.isEmpty(bVar.f)) {
                bVar.f = this.n;
            }
            if (this.a != null) {
                try {
                    this.a.a(bVar, dVar);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            } else if (d() || a(this.k, this.i)) {
                this.l.add(Pair.create(bVar, dVar));
            }
        }
    }

    public void c() {
        for (Pair<b, d> pair : this.l) {
            try {
                this.a.a((b) pair.first, (d) pair.second);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        this.l.clear();
    }

    public boolean d() {
        return this.j;
    }

    public void a(boolean z) {
        this.j = z;
    }
}
