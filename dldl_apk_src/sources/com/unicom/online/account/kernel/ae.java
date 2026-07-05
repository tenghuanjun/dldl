package com.unicom.online.account.kernel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class ae {
    private static ae f;
    private Network a = null;
    private ConnectivityManager.NetworkCallback b = null;
    private ConnectivityManager c = null;
    private List<a> d = new ArrayList();
    private Timer e = null;

    public interface a {
        void a(boolean z, Object obj);
    }

    private ae() {
    }

    public static ae a() {
        if (f == null) {
            synchronized (ae.class) {
                if (f == null) {
                    f = new ae();
                }
            }
        }
        return f;
    }

    private synchronized void a(a aVar) {
        try {
            this.d.add(aVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(boolean z, Network network) {
        try {
            if (this.e != null) {
                this.e.cancel();
                this.e = null;
            }
            Iterator<a> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().a(z, network);
            }
            this.d.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final synchronized void a(Context context, a aVar) {
        if (this.a != null) {
            aVar.a(true, this.a);
            return;
        }
        a(aVar);
        if (this.b == null || this.d.size() < 2) {
            try {
                this.c = (ConnectivityManager) context.getSystemService("connectivity");
                NetworkRequest.Builder builder = new NetworkRequest.Builder();
                builder.addTransportType(0);
                builder.addCapability(12);
                NetworkRequest networkRequestBuild = builder.build();
                this.b = new ConnectivityManager.NetworkCallback() { // from class: com.unicom.online.account.kernel.ae.1
                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public final void onAvailable(Network network) {
                        super.onAvailable(network);
                        c.b("Network onAvailable");
                        ae.this.a = network;
                        ae.this.a(true, network);
                        try {
                            String extraInfo = ae.this.c.getNetworkInfo(ae.this.a).getExtraInfo();
                            if (TextUtils.isEmpty(extraInfo)) {
                                return;
                            }
                            d.d(extraInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public final void onLost(Network network) {
                        super.onLost(network);
                        c.b("Network onLost");
                        ae.this.b();
                    }

                    @Override // android.net.ConnectivityManager.NetworkCallback
                    public final void onUnavailable() {
                        super.onUnavailable();
                        c.b("Network onUnavailable");
                        ae.this.a(false, (Network) null);
                        ae.this.b();
                    }
                };
                int i = 3000;
                if (d.g() < 3000) {
                    i = 2000;
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    this.c.requestNetwork(networkRequestBuild, this.b, i);
                    return;
                }
                Timer timer = new Timer();
                this.e = timer;
                timer.schedule(new TimerTask() { // from class: com.unicom.online.account.kernel.ae.2
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public final void run() {
                        ae.this.a(false, (Network) null);
                    }
                }, i);
                this.c.requestNetwork(networkRequestBuild, this.b);
            } catch (Exception e) {
                e.printStackTrace();
                a(false, (Network) null);
            }
        }
    }

    public final synchronized void b() {
        try {
            if (this.e != null) {
                this.e.cancel();
                this.e = null;
            }
            if (Build.VERSION.SDK_INT >= 21 && this.c != null && this.b != null) {
                this.c.unregisterNetworkCallback(this.b);
            }
            this.c = null;
            this.b = null;
            this.a = null;
            this.d.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
