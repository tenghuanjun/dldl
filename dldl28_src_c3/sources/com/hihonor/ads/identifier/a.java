package com.hihonor.ads.identifier;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.bun.miitmdid.q0;
import com.hihonor.ads.identifier.AdvertisingIdClient;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a implements ServiceConnection {
    public AdvertisingIdClient.Info a;
    public Context b;
    public C0177a c = new C0177a();
    public b d = new b();
    public CountDownLatch e = new CountDownLatch(2);

    /* JADX INFO: renamed from: com.hihonor.ads.identifier.a$a, reason: collision with other inner class name */
    public class C0177a extends q0.a {
        public C0177a() {
        }

        public native void a(int i, long j, boolean z, float f, double d, String str);

        public native void a(int i, Bundle bundle);
    }

    public class b extends q0.a {
        public b() {
        }

        public native void a(int i, long j, boolean z, float f, double d, String str);

        public native void a(int i, Bundle bundle);
    }

    public final native void a();

    public native boolean a(Context context);

    @Override // android.content.ServiceConnection
    public native void onServiceConnected(ComponentName componentName, IBinder iBinder);

    @Override // android.content.ServiceConnection
    public native void onServiceDisconnected(ComponentName componentName);
}
