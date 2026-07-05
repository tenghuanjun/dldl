package com.huya.hyhttpdns.dns;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class ConnectionReceiver extends BroadcastReceiver {
    private static final String TAG = "hyhttpdns.CR";
    public static NetworkInfo lastActiveNetworkInfo = null;
    public static boolean lastConnected = true;
    public static WifiInfo lastWifiInfo;
    public NetworkListener mNetworkListener;

    public interface NetworkListener {
        void onNetworkChange();
    }

    public ConnectionReceiver(NetworkListener networkListener) {
        this.mNetworkListener = networkListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            try {
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (Exception e) {
                Log.d(TAG, "getActiveNetworkInfo error: " + e.getMessage());
            }
        }
        checkConnInfo(context, activeNetworkInfo);
    }

    public void checkConnInfo(Context context, NetworkInfo networkInfo) {
        NetworkListener networkListener;
        if (networkInfo == null) {
            lastActiveNetworkInfo = null;
            lastWifiInfo = null;
            NetworkListener networkListener2 = this.mNetworkListener;
            if (networkListener2 != null) {
                networkListener2.onNetworkChange();
                return;
            }
            return;
        }
        if (networkInfo.getDetailedState() != NetworkInfo.DetailedState.CONNECTED) {
            if (lastConnected) {
                lastActiveNetworkInfo = null;
                lastWifiInfo = null;
                NetworkListener networkListener3 = this.mNetworkListener;
                if (networkListener3 != null) {
                    networkListener3.onNetworkChange();
                }
            }
            lastConnected = false;
            return;
        }
        if (isNetworkChange(context, networkInfo) && (networkListener = this.mNetworkListener) != null) {
            networkListener.onNetworkChange();
        }
        lastConnected = true;
    }

    private boolean isEqual(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }

    public boolean isNetworkChange(Context context, NetworkInfo networkInfo) {
        WifiInfo wifiInfo;
        if (networkInfo.getType() == 1) {
            WifiInfo connectionInfo = null;
            try {
                connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            } catch (Exception e) {
                Log.d(TAG, "isNetworkChange exception: " + e.getMessage());
            }
            if (connectionInfo == null && lastWifiInfo == null) {
                return false;
            }
            if (connectionInfo != null && (wifiInfo = lastWifiInfo) != null && isEqual(wifiInfo.getBSSID(), connectionInfo.getBSSID()) && isEqual(lastWifiInfo.getSSID(), connectionInfo.getSSID()) && lastWifiInfo.getNetworkId() == connectionInfo.getNetworkId()) {
                return false;
            }
            lastWifiInfo = connectionInfo;
        } else {
            if (isEqual(lastActiveNetworkInfo, networkInfo)) {
                return false;
            }
            NetworkInfo networkInfo2 = lastActiveNetworkInfo;
            if (networkInfo2 != null && networkInfo2.getExtraInfo() != null && networkInfo.getExtraInfo() != null && isEqual(lastActiveNetworkInfo.getExtraInfo(), networkInfo.getExtraInfo()) && lastActiveNetworkInfo.getSubtype() == networkInfo.getSubtype() && lastActiveNetworkInfo.getType() == networkInfo.getType()) {
                return false;
            }
            NetworkInfo networkInfo3 = lastActiveNetworkInfo;
            if (networkInfo3 != null && networkInfo3.getExtraInfo() == null && networkInfo.getExtraInfo() == null && lastActiveNetworkInfo.getSubtype() == networkInfo.getSubtype() && lastActiveNetworkInfo.getType() == networkInfo.getType()) {
                return false;
            }
        }
        lastActiveNetworkInfo = networkInfo;
        return true;
    }
}
