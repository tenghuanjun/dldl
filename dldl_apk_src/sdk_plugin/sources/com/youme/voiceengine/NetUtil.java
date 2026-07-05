package com.youme.voiceengine;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class NetUtil {
    public static final int NETWORK_TYPE_MOBILE = 2;
    public static final int NETWORK_TYPE_NO = -1;
    public static final int NETWORK_TYPE_WIFI = 1;

    public static int getNetworkState(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return 1;
            }
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if (networkInfo2 != null) {
                if (networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                    return 2;
                }
            }
            return -1;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }
}
