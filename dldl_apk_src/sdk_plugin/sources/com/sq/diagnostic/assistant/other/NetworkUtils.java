package com.sq.diagnostic.assistant.other;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NetworkUtils {
    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isConnected();
        }
        return false;
    }

    public static List<String> getDnsList(Context context) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT < 21) {
            return arrayList;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        for (Network network : connectivityManager.getAllNetworks()) {
            if (connectivityManager.getNetworkInfo(network).getType() == activeNetworkInfo.getType()) {
                Iterator<InetAddress> it = connectivityManager.getLinkProperties(network).getDnsServers().iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().getHostAddress());
                }
            }
        }
        return arrayList;
    }
}
