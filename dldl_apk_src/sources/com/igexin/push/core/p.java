package com.igexin.push.core;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Pair;
import com.igexin.push.f.i;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.IPushCore;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushService;
import com.igexin.sdk.main.PushCoreLoader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class p {
    public static Context b = null;
    private static final String e = "ServiceManager";
    public IPushCore a;
    public Pair<Integer, String> c;
    final ServiceConnection d;
    private final AtomicBoolean f;
    private ExecutorService g;
    private Class h;
    private Class i;

    public static class a {
        private static final p a = new p(0);

        private a() {
        }
    }

    private p() {
        this.f = new AtomicBoolean(false);
        this.d = new ServiceConnection() { // from class: com.igexin.push.core.p.3
            @Override // android.content.ServiceConnection
            public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            }

            @Override // android.content.ServiceConnection
            public final void onServiceDisconnected(ComponentName componentName) {
            }
        };
        this.g = new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    /* synthetic */ p(byte b2) {
        this();
    }

    private int a(Intent intent, int i, int i2) {
        if (this.a == null) {
            return 2;
        }
        com.igexin.b.a.c.a.a("ServiceManager|inInit = true, call onServiceStartCommand...", new Object[0]);
        return this.a.onServiceStartCommand(intent, i, i2);
    }

    private static p a() {
        return a.a;
    }

    private void a(Activity activity) {
        try {
            Intent intent = activity.getIntent();
            com.igexin.push.core.a.b.d();
            Intent intent2 = new Intent(activity, (Class<?>) com.igexin.push.core.a.b.a((Context) activity));
            if (intent != null) {
                try {
                    if (intent.hasExtra("action") && intent.hasExtra("isSlave")) {
                        intent2.putExtra("action", intent.getStringExtra("action"));
                        intent2.putExtra("isSlave", intent.getBooleanExtra("isSlave", false));
                        if (intent.hasExtra("op_app")) {
                            intent2.putExtra("op_app", intent.getStringExtra("op_app"));
                        }
                    }
                } catch (Exception unused) {
                }
            }
            a(activity, intent2);
            com.igexin.b.a.c.a.a("ServiceManager|start PushService from da", new Object[0]);
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(e + th.toString(), new Object[0]);
        } finally {
            activity.finish();
        }
    }

    private void a(final Service service) {
        com.igexin.b.a.c.a.a("ServiceManager|startGTCore ++++", new Object[0]);
        if (!com.igexin.push.f.h.a()) {
            com.igexin.push.f.i.a(new i.a() { // from class: com.igexin.push.core.p.1
                @Override // com.igexin.push.f.i.a
                public final void a(boolean z) {
                    com.igexin.b.a.c.a.a("ServiceManager|load so error, report bi result = " + z + " ###########", new Object[0]);
                    service.stopSelf();
                }
            }, service);
            return;
        }
        PushCoreLoader.getInstance().init(service);
        this.a = PushCoreLoader.getInstance().getPushCore();
        if (PushCoreLoader.getInstance().getGtcCore() != null) {
            PushCoreLoader.getInstance().getGtcCore().start(service);
        }
        IPushCore iPushCore = this.a;
        if (iPushCore != null) {
            iPushCore.start(service);
        }
    }

    private static boolean a(Context context, boolean z) {
        if (com.igexin.push.f.k.a(context)) {
            return false;
        }
        if (!z) {
            return true;
        }
        com.igexin.push.config.e.a();
        return com.igexin.push.config.d.t;
    }

    private int b(Service service) {
        com.igexin.b.a.c.a.a("ServiceManager|start by system ####", new Object[0]);
        if (!a((Context) service, false)) {
            service.stopSelf();
            return 2;
        }
        com.igexin.b.a.c.a.a("ServiceManager|intent = null", new Object[0]);
        if (!this.f.getAndSet(true)) {
            a(service);
        }
        return 2;
    }

    private int b(Service service, Intent intent, int i, int i2) {
        com.igexin.b.a.c.a.a("ServiceManager|start from initialize...", new Object[0]);
        com.igexin.b.a.c.a.d.a().a("[ServiceManager] ServiceManager start from initialize...");
        a(service);
        IPushCore iPushCore = this.a;
        if (iPushCore != null) {
            return iPushCore.onServiceStartCommand(intent, i, i2);
        }
        return 2;
    }

    private static void b() {
        com.igexin.b.a.c.a.a("ServiceManager|onLowMemory...", new Object[0]);
    }

    private boolean b(Context context, Intent intent) {
        return a(context, intent);
    }

    private int c(Service service, Intent intent, int i, int i2) {
        if (!a((Context) service, true)) {
            this.f.set(false);
            service.stopSelf();
            return 2;
        }
        a(service);
        IPushCore iPushCore = this.a;
        if (iPushCore != null) {
            return iPushCore.onServiceStartCommand(intent, i, i2);
        }
        return 2;
    }

    public static String c(Context context) {
        return (String) com.igexin.push.f.o.b(context, com.igexin.push.f.o.c, "");
    }

    private void c() {
        com.igexin.b.a.c.a.a("ServiceManager|onDestroy...", new Object[0]);
        IPushCore iPushCore = this.a;
        if (iPushCore != null) {
            iPushCore.onServiceDestroy();
        }
    }

    private static void d(Context context) {
        b = context.getApplicationContext();
    }

    private static String e(Context context) {
        return (String) com.igexin.push.f.o.b(context, com.igexin.push.f.o.c, "");
    }

    public final int a(Service service, Intent intent, int i, int i2) {
        try {
            if (intent == null) {
                this.c = Pair.create(1, null);
                com.igexin.b.a.c.a.a("ServiceManager|start by system ####", new Object[0]);
                if (!a((Context) service, false)) {
                    service.stopSelf();
                    return 2;
                }
                com.igexin.b.a.c.a.a("ServiceManager|intent = null", new Object[0]);
                if (!this.f.getAndSet(true)) {
                    a(service);
                }
                return 2;
            }
            try {
                if (intent.hasExtra(com.igexin.push.f.o.a)) {
                    String strB = com.igexin.b.b.a.b(intent.getStringExtra(com.igexin.push.f.o.a), "");
                    String str = (String) com.igexin.push.f.o.b(service, com.igexin.push.f.o.a, "");
                    if (!str.equals(strB)) {
                        if (!b.ak.equals(strB)) {
                            com.igexin.push.f.o.a(service, com.igexin.push.f.o.a, strB);
                        } else if (!TextUtils.isEmpty(str)) {
                            com.igexin.push.f.o.a(service, com.igexin.push.f.o.a, "");
                        }
                    }
                }
                if (intent.hasExtra(com.igexin.push.f.o.b)) {
                    String str2 = (String) com.igexin.push.f.o.b(service, com.igexin.push.f.o.b, "");
                    String strB2 = com.igexin.b.b.a.b(intent.getStringExtra(com.igexin.push.f.o.b), "");
                    if (!str2.equals(strB2)) {
                        com.igexin.push.f.o.a(service, com.igexin.push.f.o.b, strB2);
                    }
                }
                if (intent.hasExtra(com.igexin.push.f.o.c)) {
                    p unused = a.a;
                    String str3 = (String) com.igexin.push.f.o.b(service, com.igexin.push.f.o.c, "");
                    String strB3 = com.igexin.b.b.a.b(intent.getStringExtra(com.igexin.push.f.o.c), "");
                    if (!str3.equals(strB3)) {
                        com.igexin.push.f.o.a(service, com.igexin.push.f.o.c, strB3);
                    }
                }
            } catch (Throwable unused2) {
            }
            Bundle extras = intent.getExtras();
            if (extras != null) {
                for (String str4 : extras.keySet()) {
                    extras.get(str4);
                    com.igexin.b.a.c.a.a("ServiceManager|key [" + str4 + "]: " + extras.get(str4), new Object[0]);
                }
            } else {
                com.igexin.b.a.c.a.a("ServiceManager|no extras", new Object[0]);
            }
            String stringExtra = intent.getStringExtra("action");
            if (PushConsts.ACTION_SERVICE_INITIALIZE.equals(stringExtra)) {
                com.igexin.push.f.k.b(service);
            }
            if (this.f.getAndSet(true)) {
                if (this.a == null) {
                    return 2;
                }
                com.igexin.b.a.c.a.a("ServiceManager|inInit = true, call onServiceStartCommand...", new Object[0]);
                return this.a.onServiceStartCommand(intent, i, i2);
            }
            if (PushConsts.ACTION_SERVICE_INITIALIZE.equals(stringExtra)) {
                this.c = Pair.create(0, null);
                com.igexin.b.a.c.a.a("ServiceManager|start from initialize...", new Object[0]);
                com.igexin.b.a.c.a.d.a().a("[ServiceManager] ServiceManager start from initialize...");
                a(service);
                if (this.a != null) {
                    return this.a.onServiceStartCommand(intent, i, i2);
                }
                return 2;
            }
            this.c = Pair.create(1, intent.getStringExtra("pkg"));
            if (a((Context) service, true)) {
                a(service);
                if (this.a != null) {
                    return this.a.onServiceStartCommand(intent, i, i2);
                }
            } else {
                this.f.set(false);
                service.stopSelf();
            }
            return 2;
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("ServiceManager|" + th.toString(), new Object[0]);
            return 2;
        }
    }

    public final IBinder a(Service service, Intent intent) {
        com.igexin.b.a.c.a.a("ServiceManager|onBind...", new Object[0]);
        a(service, intent, 0, 0);
        IPushCore iPushCore = this.a;
        if (iPushCore != null) {
            return iPushCore.onServiceBind(intent);
        }
        return null;
    }

    public final Class a(Context context) {
        Class cls = this.i;
        if (cls != null) {
            return cls;
        }
        this.i = (Class) com.igexin.push.f.d.a(context, PushService.class).second;
        Class cls2 = this.i;
        if (cls2 != null) {
            return cls2;
        }
        try {
            String str = (String) com.igexin.push.f.o.b(context, com.igexin.push.f.o.a, "");
            if (TextUtils.isEmpty(str)) {
                return PushService.class;
            }
            this.i = Class.forName(str);
            return this.i;
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("ServiceManager|" + th.toString(), new Object[0]);
            return PushService.class;
        }
    }

    public final boolean a(final Context context, final Intent intent) {
        this.g.execute(new Runnable() { // from class: com.igexin.push.core.p.2
            private void a() {
                com.igexin.b.a.c.a.a("ServiceManager|startPService by bind", new Object[0]);
                intent.setType("PB-" + System.nanoTime());
                Intent intent2 = intent;
                Context context2 = context;
                intent2.setClass(context2, p.this.a(context2));
                context.getApplicationContext().bindService(intent, p.this.d, 1);
            }

            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (Build.VERSION.SDK_INT < 26 || !com.igexin.push.f.c.h()) {
                        context.getApplicationContext().startService(intent);
                    } else {
                        a();
                    }
                } catch (Throwable th) {
                    com.igexin.b.a.c.a.a("ServiceManager|startPService err：" + th.toString(), new Object[0]);
                    if (th instanceof IllegalStateException) {
                        a();
                    }
                }
            }
        });
        return true;
    }

    public final Class b(Context context) {
        Class cls = this.h;
        if (cls != null) {
            return cls;
        }
        this.h = (Class) com.igexin.push.f.d.a(context, GTIntentService.class).second;
        Class cls2 = this.h;
        if (cls2 != null) {
            return cls2;
        }
        try {
            String str = (String) com.igexin.push.f.o.b(context, com.igexin.push.f.o.b, "");
            if (!TextUtils.isEmpty(str)) {
                this.h = Class.forName(str);
                return this.h;
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("ServiceManager|" + th.toString(), new Object[0]);
        }
        return this.h;
    }
}
