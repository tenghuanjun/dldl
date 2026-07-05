package com.youme.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class NetworkStatusReceiver2 extends BroadcastReceiver {
    private static boolean m_isLastConnected = true;
    private static NetworkInfo m_lastActiveNetworkInfo;
    private static WifiInfo m_lastWifiInfo;

    public enum NetworkType {
        NETWORKTYPE_NONETWORK,
        NETWORKTYPE_MOBILE,
        NETWORKTYPE_WIFI,
        NETWORKTYPE_UNINET,
        NETWORKTYPE_UNIWAP,
        NETWORKTYPE_WAP_3G,
        NETWORKTYPE_NET_3G,
        NETWORKTYPE_CMWAP,
        NETWORKTYPE_CMNET,
        NETWORKTYPE_CTWAP,
        NETWORKTYPE_CTNET,
        NETWORKTYPE_LTE
    }

    public static NetworkType GetNetworkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return NetworkType.NETWORKTYPE_NONETWORK;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return NetworkType.NETWORKTYPE_NONETWORK;
        }
        if (activeNetworkInfo.getType() == 1) {
            return NetworkType.NETWORKTYPE_WIFI;
        }
        return NetworkType.NETWORKTYPE_MOBILE;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NetworkInfo activeNetworkInfo;
        NetworkType networkType;
        if (context == null || intent == null) {
            return;
        }
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Throwable th) {
            th.printStackTrace();
            Log.e("NetworkStatusReceiver", "getActiveNetworkInfo failed.");
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo == null) {
            m_lastActiveNetworkInfo = null;
            m_lastWifiInfo = null;
            if (IMEngine.m_init) {
                NativeEngine.onNetWorkChanged(NetworkType.NETWORKTYPE_NONETWORK.ordinal());
                return;
            }
            return;
        }
        if (activeNetworkInfo.getDetailedState() != NetworkInfo.DetailedState.CONNECTED) {
            if (m_isLastConnected) {
                m_lastActiveNetworkInfo = null;
                m_lastWifiInfo = null;
                if (IMEngine.m_init) {
                    NativeEngine.onNetWorkChanged(NetworkType.NETWORKTYPE_NONETWORK.ordinal());
                }
            }
            m_isLastConnected = false;
            return;
        }
        if (IsNetworkChange(context, activeNetworkInfo)) {
            NetworkType networkType2 = NetworkType.NETWORKTYPE_NONETWORK;
            if (activeNetworkInfo.getType() == 1) {
                networkType = NetworkType.NETWORKTYPE_WIFI;
            } else {
                networkType = NetworkType.NETWORKTYPE_MOBILE;
            }
            if (IMEngine.m_init) {
                NativeEngine.onNetWorkChanged(networkType.ordinal());
            }
        }
        m_isLastConnected = true;
    }

    public boolean IsNetworkChange(Context context, NetworkInfo networkInfo) {
        if (networkInfo.getType() == 1) {
            try {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
                if (connectionInfo != null && m_lastWifiInfo != null && m_lastWifiInfo.getBSSID().equals(connectionInfo.getBSSID()) && m_lastWifiInfo.getSSID().equals(connectionInfo.getSSID()) && m_lastWifiInfo.getNetworkId() == connectionInfo.getNetworkId()) {
                    Log.i("NetworkStatusReceiver", "return same Wifi");
                    return false;
                }
                m_lastWifiInfo = connectionInfo;
            } catch (Throwable th) {
                th.printStackTrace();
                Log.e("NetworkStatusReceiver", "getConnectionInfo failed.");
            }
        } else {
            NetworkInfo networkInfo2 = m_lastActiveNetworkInfo;
            if (networkInfo2 != null && networkInfo2.getExtraInfo() != null && networkInfo.getExtraInfo() != null && m_lastActiveNetworkInfo.getExtraInfo().equals(networkInfo.getExtraInfo()) && m_lastActiveNetworkInfo.getSubtype() == networkInfo.getSubtype() && m_lastActiveNetworkInfo.getType() == networkInfo.getType()) {
                Log.i("NetworkStatusReceiver", "return same newtwork");
                return false;
            }
            NetworkInfo networkInfo3 = m_lastActiveNetworkInfo;
            if (networkInfo3 != null && networkInfo3.getExtraInfo() == null && networkInfo.getExtraInfo() == null && m_lastActiveNetworkInfo.getSubtype() == networkInfo.getSubtype() && m_lastActiveNetworkInfo.getType() == networkInfo.getType()) {
                Log.i("NetworkStatusReceiver", "return same newtwork 2");
                return false;
            }
        }
        m_lastActiveNetworkInfo = networkInfo;
        return true;
    }
}
