package com.igexin.sdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.getui.gtc.base.GtcProvider;
import com.igexin.b.a.c.a;
import com.igexin.b.a.c.a.c;
import com.igexin.b.a.c.a.d;
import com.igexin.push.core.p;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PushService extends Service {
    private final String TAG = getClass().getName();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        String type = (intent == null || intent.getType() == null) ? "" : intent.getType();
        if (!type.startsWith("GB-") && !type.startsWith("PB-")) {
            return c.a.equals(type) ? d.a().a.getBinder() : type.startsWith("GTC-") ? p.a.a.a((Service) this, intent) : p.a.a.a((Service) this, intent);
        }
        p.a.a.a(this, intent, 0, 0);
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        try {
            GtcProvider.setContext(this);
        } catch (Exception unused) {
        }
        super.onCreate();
        p unused2 = p.a.a;
        p.b = getApplicationContext();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        p pVar = p.a.a;
        a.a("ServiceManager|onDestroy...", new Object[0]);
        if (pVar.a != null) {
            pVar.a.onServiceDestroy();
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        p unused = p.a.a;
        a.a("ServiceManager|onLowMemory...", new Object[0]);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        p.a.a.a(this, intent, i, i2);
        return 2;
    }
}
