package com.igexin.push.c;

import android.content.Intent;
import android.os.Bundle;
import com.igexin.push.e.b.d;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c {
    public static final String a = "ConnectModelCoordinator";
    private static final long i = 20000;
    private static final long j = 200000;
    public boolean b;
    public long c;
    public int d;
    public com.igexin.push.c.b e;
    private int f;
    private int g;
    private int h;
    private long k;
    private a l;

    enum a {
        WIFI,
        MOBILE
    }

    public static class b {
        private static final c a = new c(0);

        private b() {
        }
    }

    private c() {
        this.f = com.igexin.push.config.d.B;
        this.g = com.igexin.push.config.d.D;
        this.e = new d();
        this.l = com.igexin.push.f.c.c() ? a.WIFI : a.MOBILE;
    }

    /* synthetic */ c(byte b2) {
        this();
    }

    private static void a(int i2) {
        if (com.igexin.push.core.e.i == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("com.igexin.sdk.action.polling");
            Bundle bundle = new Bundle();
            bundle.putInt("code", i2);
            intent.putExtras(bundle);
            intent.setPackage(com.igexin.push.core.e.i.getPackageName());
            com.igexin.push.core.e.i.sendBroadcast(intent);
        } catch (Throwable unused) {
        }
    }

    private void a(boolean z) {
        this.b = z;
        com.igexin.b.a.c.a.a("ConnectModelCoordinator|init, current is polling model = ".concat(String.valueOf(z)), new Object[0]);
        if (z) {
            d.a.a.g();
        }
    }

    private static c d() {
        return b.a;
    }

    private void e() {
        com.igexin.b.a.c.a.a("ConnectModelCoordinator|reset current model = normal", new Object[0]);
        com.igexin.push.c.b bVar = this.e;
        if (bVar != null && !(bVar instanceof d)) {
            this.e = new d();
        }
        d.a.a.h();
        this.d = 0;
        this.h = 0;
        this.b = false;
        com.igexin.push.core.e.e.a().b(this.b);
    }

    private com.igexin.push.c.b f() {
        return this.e;
    }

    private void g() {
        this.c = System.currentTimeMillis();
        if (this.b) {
            this.e = new e();
            d.a.a.g();
            this.d = 0;
        }
    }

    private void h() {
        com.igexin.push.c.b bVar;
        if (!this.b || (bVar = this.e) == null || (bVar instanceof d)) {
            return;
        }
        this.e = new d();
    }

    private static void i() {
        a(0);
    }

    private static void j() {
        a(1);
    }

    public final synchronized void a() {
        a aVar = com.igexin.push.f.c.c() ? a.WIFI : a.MOBILE;
        if (aVar != this.l) {
            com.igexin.b.a.c.a.a("ConnectModelCoordinator|net type changed " + this.l + "->" + aVar, new Object[0]);
            e();
            this.l = aVar;
        }
    }

    public final synchronized void b() {
        if (this.b) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.c;
        if (jCurrentTimeMillis > i && jCurrentTimeMillis < j) {
            this.h++;
            com.igexin.b.a.c.a.a("ConnectModelCoordinator|read len = -1, interval = " + jCurrentTimeMillis + ", tcpDisconnectSuccess =" + this.h, new Object[0]);
            if (this.h >= this.f) {
                com.igexin.b.a.c.a.a("ConnectModelCoordinator|enter polling mode ####", new Object[0]);
                a(0);
                this.b = true;
                this.e = new e();
                d.a.a.g();
                com.igexin.push.core.e.e.a().b(this.b);
            }
        }
    }

    public final synchronized void c() {
        if (this.b) {
            if (System.currentTimeMillis() - this.k >= com.igexin.push.config.c.l) {
                this.d++;
                com.igexin.b.a.c.a.a("ConnectModelCoordinator|polling mode, cur hearbeat =" + this.d, new Object[0]);
                if (this.d >= this.g) {
                    com.igexin.b.a.c.a.a("ConnectModelCoordinator|enter normal mode ####", new Object[0]);
                    a(1);
                    com.igexin.push.core.e.L = 0L;
                    e();
                }
            }
            this.k = System.currentTimeMillis();
        }
    }
}
