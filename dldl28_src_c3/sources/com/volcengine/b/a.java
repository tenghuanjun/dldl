package com.volcengine.b;

import android.content.Context;
import com.mobile.auth.gatewayauth.Constant;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class a {
    private final int a;
    private Timer b;
    private long c;
    private volatile boolean d = false;
    private volatile boolean e = false;
    protected final Context f;
    protected final Map<String, String> g;
    protected final Map<String, String> h;
    protected final b i;

    /* JADX INFO: renamed from: com.volcengine.b.a$a, reason: collision with other inner class name */
    class C0200a extends TimerTask {
        final /* synthetic */ Map a;

        C0200a(Map map) {
            this.a = map;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            a.this.e = true;
            this.a.put("diagnosis_interrupt", Constant.API_PARAMS_KEY_TIMEOUT);
            this.a.put("diagnosis_time", Long.toString(System.currentTimeMillis() - a.this.c));
            a aVar = a.this;
            b bVar = aVar.i;
            if (bVar != null) {
                bVar.a(aVar, this.a);
            }
            a.this.d = false;
        }
    }

    public interface b {
        void a(a aVar, Map<String, String> map);
    }

    protected a(Context context, Map<String, String> map, Map<String, String> map2, b bVar, int i) {
        this.f = context;
        this.g = map;
        this.h = map2;
        this.i = bVar;
        this.a = i;
    }

    private void b(Map<String, String> map) {
        if (this.d) {
            return;
        }
        this.d = true;
        Timer timer = new Timer();
        this.b = timer;
        timer.schedule(new C0200a(map), ((long) this.a) * 1000);
    }

    public void a() {
        Timer timer = this.b;
        if (timer != null) {
            timer.cancel();
        }
        this.d = false;
    }

    protected abstract void a(Map<String, String> map);

    protected void b() {
        if (this.e) {
            throw new CancellationException();
        }
    }

    protected abstract String c();

    public void d() {
        b bVar;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        try {
            try {
                this.d = false;
                this.e = false;
                this.c = System.currentTimeMillis();
                b(concurrentHashMap);
                concurrentHashMap.put("diagnosis_type", c());
                a(concurrentHashMap);
            } catch (InterruptedException | CancellationException unused) {
                concurrentHashMap.put("diagnosis_interrupt", Constant.API_PARAMS_KEY_TIMEOUT);
                if (!this.e) {
                    a();
                    concurrentHashMap.put("diagnosis_time", Long.toString(System.currentTimeMillis() - this.c));
                    bVar = this.i;
                    if (bVar != null) {
                    }
                }
            } catch (Exception e) {
                concurrentHashMap.put("diagnosis_interrupt", e.getMessage());
                if (!this.e) {
                    a();
                    concurrentHashMap.put("diagnosis_time", Long.toString(System.currentTimeMillis() - this.c));
                    bVar = this.i;
                    if (bVar != null) {
                    }
                }
            }
            if (!this.e) {
                a();
                concurrentHashMap.put("diagnosis_time", Long.toString(System.currentTimeMillis() - this.c));
                bVar = this.i;
                if (bVar != null) {
                    bVar.a(this, concurrentHashMap);
                }
            }
        } catch (Throwable th) {
            if (!this.e) {
                a();
                concurrentHashMap.put("diagnosis_time", Long.toString(System.currentTimeMillis() - this.c));
                b bVar2 = this.i;
                if (bVar2 != null) {
                    bVar2.a(this, concurrentHashMap);
                }
            }
            throw th;
        }
    }

    public Map<String, String> e() {
        return this.g;
    }
}
