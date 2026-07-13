package com.mobile.auth.m;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static r f755a;
    private ConnectivityManager b;
    private Network c;
    private ConnectivityManager.NetworkCallback d;
    private boolean e;

    public interface a {
        void a(Network network);
    }

    private r(Context context) {
        try {
            this.b = (ConnectivityManager) context.getSystemService("connectivity");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static r a(Context context) {
        if (f755a == null) {
            synchronized (r.class) {
                if (f755a == null) {
                    f755a = new r(context);
                }
            }
        }
        return f755a;
    }

    public synchronized void a(final a aVar) {
        NetworkInfo networkInfo;
        ConnectivityManager connectivityManager = this.b;
        if (connectivityManager == null) {
            c.a("WifiNetworkUtils", "mConnectivityManager 为空");
            aVar.a(null);
            return;
        }
        Network network = this.c;
        if (network != null && !this.e && (networkInfo = connectivityManager.getNetworkInfo(network)) != null && networkInfo.isAvailable()) {
            Log.e("HttpUtils", "reuse network: ");
            aVar.a(this.c);
            return;
        }
        ConnectivityManager.NetworkCallback networkCallback = this.d;
        if (networkCallback == null) {
            NetworkRequest networkRequestBuild = new NetworkRequest.Builder().addCapability(12).addTransportType(0).build();
            ConnectivityManager.NetworkCallback networkCallback2 = new ConnectivityManager.NetworkCallback() { // from class: com.mobile.auth.m.r.1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network2) {
                    try {
                        if (r.this.b.getNetworkCapabilities(network2).hasTransport(0)) {
                            r.this.c = network2;
                            aVar.a(network2);
                            r.this.e = false;
                        } else {
                            c.a("WifiNetworkUtils", "切换失败，未开启数据网络");
                            r.this.c = null;
                            aVar.a(null);
                            r.this.b.unregisterNetworkCallback(r.this.d);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        r.this.c = null;
                        aVar.a(null);
                    }
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network2) {
                    r.this.e = true;
                }
            };
            this.d = networkCallback2;
            this.b.requestNetwork(networkRequestBuild, networkCallback2);
            return;
        }
        try {
            this.b.unregisterNetworkCallback(networkCallback);
        } catch (Exception e) {
            e.printStackTrace();
            this.d = null;
        }
        Log.e("HttpUtils", "clear: ");
        NetworkRequest networkRequestBuild2 = new NetworkRequest.Builder().addCapability(12).addTransportType(0).build();
        ConnectivityManager.NetworkCallback networkCallback22 = new ConnectivityManager.NetworkCallback() { // from class: com.mobile.auth.m.r.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network2) {
                try {
                    if (r.this.b.getNetworkCapabilities(network2).hasTransport(0)) {
                        r.this.c = network2;
                        aVar.a(network2);
                        r.this.e = false;
                    } else {
                        c.a("WifiNetworkUtils", "切换失败，未开启数据网络");
                        r.this.c = null;
                        aVar.a(null);
                        r.this.b.unregisterNetworkCallback(r.this.d);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    r.this.c = null;
                    aVar.a(null);
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network2) {
                r.this.e = true;
            }
        };
        this.d = networkCallback22;
        try {
            this.b.requestNetwork(networkRequestBuild2, networkCallback22);
        } catch (Exception e2) {
            e2.printStackTrace();
            aVar.a(null);
        }
        return;
    }

    public boolean a() {
        return this.c != null;
    }

    public void b() {
        if (this.b == null) {
            return;
        }
        try {
            ConnectivityManager.NetworkCallback networkCallback = this.d;
            if (networkCallback == null) {
                return;
            }
            this.b.unregisterNetworkCallback(networkCallback);
            this.d = null;
            this.c = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
