package com.mobile.auth.k;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import java.net.InetAddress;
import java.net.UnknownHostException;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class z {
    static ConnectivityManager a;
    private static z b;
    private Network c;
    private ConnectivityManager.NetworkCallback d;
    private boolean e;

    class a extends ConnectivityManager.NetworkCallback {
        final /* synthetic */ b a;

        a(b bVar) {
            this.a = bVar;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            z.this.c = network;
            this.a.a(network);
            z.this.e = false;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            z.this.e = true;
        }
    }

    public interface b {
        void a(Network network);
    }

    private z(Context context) {
        a = (ConnectivityManager) context.getSystemService("connectivity");
    }

    static int a(String str) {
        try {
            byte[] address = InetAddress.getByName(str).getAddress();
            return (address[0] & UByte.MAX_VALUE) | ((address[3] & UByte.MAX_VALUE) << 24) | ((address[2] & UByte.MAX_VALUE) << 16) | ((address[1] & UByte.MAX_VALUE) << 8);
        } catch (UnknownHostException unused) {
            return -1;
        }
    }

    public static z a(Context context) {
        if (b == null) {
            synchronized (z.class) {
                if (b == null) {
                    b = new z(context);
                }
            }
        }
        return b;
    }

    static String b(String str) {
        int iIndexOf = str.indexOf("://");
        if (iIndexOf > 0) {
            str = str.substring(iIndexOf + 3);
        }
        int iIndexOf2 = str.indexOf(58);
        if (iIndexOf2 >= 0) {
            str = str.substring(0, iIndexOf2);
        }
        int iIndexOf3 = str.indexOf(47);
        if (iIndexOf3 >= 0) {
            str = str.substring(0, iIndexOf3);
        }
        int iIndexOf4 = str.indexOf(63);
        return iIndexOf4 >= 0 ? str.substring(0, iIndexOf4) : str;
    }

    public void a() {
        try {
            if (Build.VERSION.SDK_INT >= 21 && a != null && this.d != null) {
                a.unregisterNetworkCallback(this.d);
                this.d = null;
                this.c = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void a(b bVar) {
        NetworkInfo networkInfo;
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        Network network = this.c;
        if (network != null && !this.e && (networkInfo = a.getNetworkInfo(network)) != null && networkInfo.isAvailable()) {
            bVar.a(this.c);
            return;
        }
        ConnectivityManager.NetworkCallback networkCallback = this.d;
        if (networkCallback != null) {
            try {
                a.unregisterNetworkCallback(networkCallback);
            } catch (Exception e) {
                e.printStackTrace();
                this.d = null;
            }
            f.a("HttpUtils", "clear: ");
        }
        NetworkRequest networkRequestBuild = new NetworkRequest.Builder().addCapability(12).addTransportType(0).build();
        a aVar = new a(bVar);
        this.d = aVar;
        a.requestNetwork(networkRequestBuild, aVar);
    }
}
