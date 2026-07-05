package com.igexin.push.core;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.HandlerThread;
import android.os.Message;
import android.os.PowerManager;
import com.igexin.push.core.d;
import com.igexin.push.core.d.AnonymousClass1;
import com.igexin.sdk.PushConsts;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class f extends HandlerThread {
    public f() {
        super("CoreThread");
    }

    @Override // android.os.HandlerThread
    protected final void onLooperPrepared() {
        d dVar = d.a.a;
        try {
            e.a(dVar.a);
            com.igexin.push.config.b.a();
            com.igexin.push.config.b.b();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE);
            intentFilter.addAction(b.H);
            intentFilter.addAction(b.J);
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            dVar.a.registerReceiver(j.a(), intentFilter);
            com.igexin.push.a.a aVar = new com.igexin.push.a.a();
            aVar.a((com.igexin.push.core.e.a) com.igexin.push.core.e.e.a());
            aVar.a((com.igexin.push.core.e.a) com.igexin.push.config.a.a());
            aVar.a((com.igexin.push.core.e.a) com.igexin.push.core.e.d.a());
            dVar.g.a((com.igexin.b.a.d.f) aVar, true, false);
            com.igexin.b.a.b.e eVar = dVar.g;
            Context context = dVar.a;
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
            com.igexin.b.a.b.e eVar2 = dVar.g;
            byte[] bArrA = com.igexin.b.b.a.a(e.I.getBytes());
            eVar2.e = bArrA;
            eVar2.f = com.igexin.b.b.a.a(bArrA);
            if (eVar2.f != null) {
                new String(eVar2.f);
            }
            e.ag = dVar.g.a((com.igexin.b.a.d.f) com.igexin.push.e.b.b.g(), false, true);
            e.ah = dVar.g.a((com.igexin.b.a.d.f) com.igexin.push.e.b.e.g(), true, true);
            com.igexin.push.b.c.a();
            com.igexin.push.b.c.b();
            dVar.c();
            dVar.e = com.igexin.push.core.a.b.d();
            dVar.i.a();
            com.igexin.push.e.e.c().d();
            e.j.set(true);
            while (!dVar.d.isEmpty()) {
                Message messagePoll = dVar.d.poll();
                if (messagePoll != null && dVar.c != null) {
                    dVar.c.sendMessage(messagePoll);
                }
            }
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) dVar.new AnonymousClass1(), true);
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
}
