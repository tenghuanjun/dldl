package com.hihonor.ads.identifier;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class b implements ServiceConnection {
    public static final ThreadPoolExecutor c = new ThreadPoolExecutor(0, 3, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(2048), new ThreadPoolExecutor.DiscardPolicy());
    public boolean a = false;
    public final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(IBinder iBinder) {
        try {
            Log.d("OaidSerivceConnection", "onServiceConnected " + System.currentTimeMillis());
            this.b.offer(iBinder);
        } catch (Throwable th) {
            Log.e("OaidSerivceConnection", "onServiceConnected  " + th.getMessage());
        }
    }

    public IBinder a() {
        if (this.a) {
            throw new IllegalStateException();
        }
        this.a = true;
        return this.b.take();
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        Log.d("OaidSerivceConnection", "onServiceConnected");
        c.execute(new Runnable() { // from class: com.hihonor.ads.identifier.-$$Lambda$b$yaGZKusENYr8H7RLwD4thGPtnDw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(iBinder);
            }
        });
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.d("OaidSerivceConnection", "onServiceDisconnected " + System.currentTimeMillis());
    }
}
