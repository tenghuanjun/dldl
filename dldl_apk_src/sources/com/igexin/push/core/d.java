package com.igexin.push.core;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.getui.gtc.api.GtcManager;
import com.getui.gtc.api.SdkInfo;
import com.igexin.assist.sdk.AssistPushManager;
import com.igexin.push.config.a.AnonymousClass1;
import com.igexin.push.core.e.e.AnonymousClass24;
import com.igexin.sdk.PushConsts;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d implements com.igexin.b.a.d.a.c {
    private static final String l = "CoreLogic";
    public Context a;
    public final f b;
    public Handler c;
    final ConcurrentLinkedQueue<Message> d;
    com.igexin.push.core.a.b e;
    public Handler f;
    final com.igexin.b.a.b.e g;
    public com.igexin.b.a.b.d h;
    public final com.igexin.push.d.a i;
    public final com.igexin.push.a.b j;
    public final AtomicBoolean k;

    /* JADX INFO: renamed from: com.igexin.push.core.d$1, reason: invalid class name */
    final class AnonymousClass1 extends com.igexin.b.a.b.a.a.a {
        AnonymousClass1() {
            super(com.igexin.b.a.b.c.f, null);
        }

        @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
        public final void b_() throws Exception {
            super.b_();
            if (TextUtils.isEmpty(e.E)) {
                try {
                    if (com.igexin.push.f.c.a()) {
                        String strC = com.igexin.push.f.n.c();
                        com.igexin.push.core.e.e eVarA = com.igexin.push.core.e.e.a();
                        if (!TextUtils.isEmpty(strC)) {
                            e.E = strC;
                            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) eVarA.new AnonymousClass24(), false, true);
                        }
                    }
                } catch (Throwable unused) {
                }
            } else {
                String str = e.E;
            }
            int iMyPid = Process.myPid();
            m mVarA = m.a();
            Bundle bundle = new Bundle();
            bundle.putInt("action", 10008);
            bundle.putInt(PushConsts.KEY_SERVICE_PIT, iMyPid);
            mVarA.a(bundle);
            String str2 = e.a;
            String str3 = e.x;
            GtcManager.getInstance().loadSdk(new SdkInfo.Builder().appid(e.a).cid(e.x).moduleName(b.h).version("3.2.4.0").build());
            o.a().e();
            try {
                AssistPushManager.getInstance().initialize(e.i);
                AssistPushManager.getInstance().register(e.i);
            } catch (Throwable th) {
                th = th;
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = th.getStackTrace();
                while (th.getCause() != null) {
                    th = th.getCause();
                }
                sb.append(th.toString());
                sb.append("\n");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                }
                com.igexin.b.a.c.a.a("CoreLogic|init|failed|".concat(String.valueOf(sb.toString())), new Object[0]);
            }
        }

        @Override // com.igexin.b.a.d.a.e
        public final int c() {
            return 0;
        }

        @Override // com.igexin.b.a.b.a.a.a
        public final void c_() {
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.d$2, reason: invalid class name */
    final class AnonymousClass2 extends com.igexin.push.e.b.f {
        final /* synthetic */ boolean a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(long j, boolean z) {
            super(j, (byte) 0);
            this.a = z;
        }

        @Override // com.igexin.push.e.b.f
        public final void b() {
            com.igexin.push.core.a.c.h.a().a(this.a);
        }

        @Override // com.igexin.b.a.d.a.e
        public final int c() {
            return 0;
        }
    }

    public static class a {
        private static final d a = new d(0);

        private a() {
        }
    }

    private d() {
        this.d = new ConcurrentLinkedQueue<>();
        this.k = new AtomicBoolean(false);
        this.b = new f();
        this.g = com.igexin.b.a.b.e.a();
        this.g.g = new com.igexin.push.c.a(this.a);
        this.g.a((com.igexin.b.a.d.a.c) this);
        this.i = new com.igexin.push.d.a();
        this.j = new com.igexin.push.a.b(p.b);
        this.h = com.igexin.push.c.a.c.a();
    }

    /* synthetic */ d(byte b) {
        this();
    }

    private boolean a(Context context) {
        this.a = context.getApplicationContext();
        f fVar = this.b;
        if (fVar != null && fVar.isAlive()) {
            com.igexin.b.a.c.a.a("CoreLogic|coreThread is alive +++++", new Object[0]);
            return true;
        }
        if (!this.k.getAndSet(true)) {
            com.igexin.b.a.c.a.a("CoreLogic|start coreThread +++++", new Object[0]);
            this.b.start();
            this.c = new c(this.b.getLooper());
            this.f = new com.igexin.b.a.b.a.a.c(this.b.getLooper());
        }
        return true;
    }

    public static boolean a(com.igexin.push.e.b.f fVar) {
        return com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) fVar, false, true);
    }

    public static boolean a(boolean z) {
        com.igexin.b.a.c.a.a("CoreLogic|start sdkSwitch isSlave = ".concat(String.valueOf(z)), new Object[0]);
        if (e.i == null) {
            return false;
        }
        if (!com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.b, new boolean[0])) {
            com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.b, Boolean.TRUE);
            com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.c, Boolean.TRUE);
            e.p = true;
        }
        if (z) {
            com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.c, Boolean.TRUE);
            e.p = true;
        }
        a.a.i.a();
        return true;
    }

    private void b(boolean z) {
        try {
            com.igexin.b.a.c.a.a("CoreLogic|ExtCidReceiver onlineState = ".concat(String.valueOf(z)), new Object[0]);
            d unused = a.a;
            a((com.igexin.push.e.b.f) new AnonymousClass2((new Random().nextInt(5) + 5) * 1000, z));
        } catch (Exception e) {
            com.igexin.b.a.c.a.a("CoreLogic|exception = " + e.toString(), new Object[0]);
        }
    }

    public static String d() {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) e.i.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "wifi";
            }
            if (activeNetworkInfo.getType() == 0) {
                return "mobile";
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static d i() {
        return a.a;
    }

    private Handler j() {
        return this.f;
    }

    private void k() {
        try {
            e.a(this.a);
            com.igexin.push.config.b.a();
            com.igexin.push.config.b.b();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE);
            intentFilter.addAction(b.H);
            intentFilter.addAction(b.J);
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.a.registerReceiver(j.a(), intentFilter);
            com.igexin.push.a.a aVar = new com.igexin.push.a.a();
            aVar.a((com.igexin.push.core.e.a) com.igexin.push.core.e.e.a());
            aVar.a((com.igexin.push.core.e.a) com.igexin.push.config.a.a());
            aVar.a((com.igexin.push.core.e.a) com.igexin.push.core.e.d.a());
            this.g.a((com.igexin.b.a.d.f) aVar, true, false);
            com.igexin.b.a.b.e eVar = this.g;
            Context context = this.a;
            if (!eVar.H) {
                if (!com.igexin.push.f.n.l()) {
                    eVar.u = (PowerManager) context.getSystemService("power");
                    eVar.C = true;
                    eVar.v = (AlarmManager) context.getSystemService("alarm");
                    context.registerReceiver(eVar, new IntentFilter("AlarmTaskSchedule." + context.getPackageName()));
                    context.registerReceiver(eVar, new IntentFilter("AlarmTaskScheduleBak." + context.getPackageName()));
                    context.registerReceiver(eVar, new IntentFilter("android.intent.action.SCREEN_OFF"));
                    context.registerReceiver(eVar, new IntentFilter("android.intent.action.SCREEN_ON"));
                    eVar.A = "AlarmNioTaskSchedule." + context.getPackageName();
                    context.registerReceiver(eVar, new IntentFilter(eVar.A));
                    eVar.w = new Intent("AlarmTaskSchedule." + context.getPackageName());
                    eVar.x = PendingIntent.getBroadcast(context, eVar.hashCode(), eVar.w, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
                    eVar.hashCode();
                    eVar.y = new Intent(eVar.A);
                    eVar.z = PendingIntent.getBroadcast(context, eVar.hashCode() + 2, eVar.y, DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
                    eVar.hashCode();
                }
                eVar.p.start();
                try {
                    Thread.yield();
                } catch (Throwable unused) {
                }
                eVar.H = true;
            }
            com.igexin.b.a.b.e eVar2 = this.g;
            byte[] bArrA = com.igexin.b.b.a.a(e.I.getBytes());
            eVar2.e = bArrA;
            eVar2.f = com.igexin.b.b.a.a(bArrA);
            if (eVar2.f != null) {
                new String(eVar2.f);
            }
            e.ag = this.g.a((com.igexin.b.a.d.f) com.igexin.push.e.b.b.g(), false, true);
            e.ah = this.g.a((com.igexin.b.a.d.f) com.igexin.push.e.b.e.g(), true, true);
            com.igexin.push.b.c.a();
            com.igexin.push.b.c.b();
            c();
            this.e = com.igexin.push.core.a.b.d();
            this.i.a();
            com.igexin.push.e.e.c().d();
            e.j.set(true);
            while (!this.d.isEmpty()) {
                Message messagePoll = this.d.poll();
                if (messagePoll != null && this.c != null) {
                    this.c.sendMessage(messagePoll);
                }
            }
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new AnonymousClass1(), true);
        } catch (Throwable th) {
            th = th;
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = th.getStackTrace();
            while (th.getCause() != null) {
                th = th.getCause();
            }
            sb.append(th.toString());
            sb.append("\n");
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append(stackTraceElement.toString());
                sb.append("\n");
            }
            String string = sb.toString();
            com.igexin.b.a.c.a.a("CoreLogic|init|failed|".concat(String.valueOf(string)), new Object[0]);
            com.igexin.b.a.c.a.d.a().a("[CoreLogic] ------ CoreLogic init failed = " + string + " ------");
        }
    }

    private static void l() {
        if (!TextUtils.isEmpty(e.E)) {
            String str = e.E;
            return;
        }
        try {
            if (com.igexin.push.f.c.a()) {
                String strC = com.igexin.push.f.n.c();
                com.igexin.push.core.e.e eVarA = com.igexin.push.core.e.e.a();
                if (TextUtils.isEmpty(strC)) {
                    return;
                }
                e.E = strC;
                com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) eVarA.new AnonymousClass24(), false, true);
            }
        } catch (Throwable unused) {
        }
    }

    private boolean m() {
        if (e.i == null) {
            return true;
        }
        com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.c, Boolean.FALSE);
        e.p = false;
        e.s = false;
        this.i.b();
        return true;
    }

    private com.igexin.b.a.b.d n() {
        return this.h;
    }

    private com.igexin.push.d.a o() {
        return this.i;
    }

    private com.igexin.push.a.b p() {
        return this.j;
    }

    private void q() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE);
        intentFilter.addAction(b.H);
        intentFilter.addAction(b.J);
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.a.registerReceiver(j.a(), intentFilter);
    }

    private void r() {
        try {
            this.a.unregisterReceiver(j.a());
        } catch (Exception unused) {
        }
    }

    private static void s() {
        String str = e.a;
        String str2 = e.x;
        GtcManager.getInstance().loadSdk(new SdkInfo.Builder().appid(e.a).cid(e.x).moduleName(b.h).version("3.2.4.0").build());
    }

    private static /* synthetic */ void t() {
        String str = e.a;
        String str2 = e.x;
        GtcManager.getInstance().loadSdk(new SdkInfo.Builder().appid(e.a).cid(e.x).moduleName(b.h).version("3.2.4.0").build());
    }

    @Override // com.igexin.b.a.d.a.c
    public final void a(long j) {
    }

    public final boolean a() {
        com.igexin.b.a.c.a.a("CoreLogic|ext init ###", new Object[0]);
        Process.myPid();
        DisplayMetrics displayMetrics = e.i.getResources().getDisplayMetrics();
        e.g = Math.max(displayMetrics.heightPixels, displayMetrics.widthPixels);
        e.h = Math.min(displayMetrics.heightPixels, displayMetrics.widthPixels);
        try {
            if (Build.VERSION.SDK_INT < 30) {
                h hVarA = h.a();
                String strD = hVarA.d();
                String str = e.F;
                com.igexin.b.a.c.a.a(h.a + "|read deviceId.db = " + strD + "; CoreRuntimeInfo.deviceId = " + e.F, new Object[0]);
                if (strD != null) {
                    if (!strD.equals(e.F)) {
                        e.F = strD;
                        h.c(strD);
                    }
                } else if (e.F != null) {
                    hVarA.b(e.F);
                }
            }
        } catch (Throwable unused) {
        }
        if (e.az == null) {
            e.az = com.igexin.b.b.a.a(e.i.getPackageName() + System.currentTimeMillis());
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) com.igexin.push.config.a.a().new AnonymousClass1(e.az), false, true);
            String str2 = e.az;
        }
        boolean z = e.r;
        if (e.r) {
            com.igexin.push.core.a.c.h.a().a(e.r);
        } else {
            boolean z2 = e.r;
            try {
                com.igexin.b.a.c.a.a("CoreLogic|ExtCidReceiver onlineState = ".concat(String.valueOf(z2)), new Object[0]);
                d unused2 = a.a;
                a((com.igexin.push.e.b.f) new AnonymousClass2((new Random().nextInt(5) + 5) * 1000, z2));
            } catch (Exception e) {
                com.igexin.b.a.c.a.a("CoreLogic|exception = " + e.toString(), new Object[0]);
            }
        }
        if (!com.igexin.push.f.k.i()) {
            e.aJ = e.i.getCacheDir() + "/ImgCache/";
        }
        return true;
    }

    public final boolean a(Message message) {
        if (e.j.get()) {
            this.c.sendMessage(message);
            return true;
        }
        this.d.add(message);
        return true;
    }

    @Override // com.igexin.b.a.d.a.c
    public final boolean a(com.igexin.b.a.d.a.e eVar) {
        com.igexin.push.core.a.b bVar = this.e;
        return bVar != null && bVar.a(eVar);
    }

    public final long b() {
        Handler handler = this.c;
        if (handler == null) {
            return -2L;
        }
        return handler.getLooper().getThread().getId();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void c() {
        com.igexin.push.e.b.a aVarG = com.igexin.push.e.b.a.g();
        com.igexin.push.e.c cVar = new com.igexin.push.e.c();
        aVarG.a((com.igexin.push.e.b.c) cVar);
        aVarG.a((com.igexin.push.e.b.c) new com.igexin.push.e.a());
        aVarG.a((com.igexin.push.e.b.c) new com.igexin.push.e.f());
        aVarG.a((com.igexin.push.e.b.c) com.igexin.push.e.e.c());
        try {
            cVar.a();
            cVar.b = System.currentTimeMillis();
        } catch (Throwable unused) {
        }
        e.ai = this.g.a((com.igexin.b.a.d.f) aVarG, false, true);
    }

    @Override // com.igexin.b.a.d.a.c
    public final boolean e() {
        return false;
    }

    @Override // com.igexin.b.a.d.a.c
    public final boolean f() {
        return false;
    }

    @Override // com.igexin.b.a.d.a.c
    public final boolean g() {
        return true;
    }

    @Override // com.igexin.b.a.d.a.c
    public final long h() {
        return 94808L;
    }
}
