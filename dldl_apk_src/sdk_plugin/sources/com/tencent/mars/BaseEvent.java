package com.tencent.mars;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.tencent.mars.xlog.Log2;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class BaseEvent {
    public static native void onCreate();

    public static native void onDestroy();

    public static native void onExceptionCrash();

    public static native void onForeground(boolean z);

    public static native void onNetworkChange();

    public static native void onSingalCrash(int i);

    public static class ConnectionReceiver extends BroadcastReceiver {
        public static String TAG = "mars.ConnectionReceiver";
        public static NetworkInfo lastActiveNetworkInfo = null;
        public static boolean lastConnected = true;
        public static WifiInfo lastWifiInfo;
        private static NetWorkChangeListener netWorkChangeListener;

        public ConnectionReceiver(NetWorkChangeListener netWorkChangeListener2) {
            netWorkChangeListener = netWorkChangeListener2;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (isInitialStickyBroadcast()) {
                Log2.i(TAG, "Sticky broadcast ignore");
            } else {
                if (context == null || intent == null) {
                    return;
                }
                NetworkInfo activeNetworkInfo = null;
                try {
                    activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                } catch (Exception unused) {
                }
                checkConnInfo(context, activeNetworkInfo);
            }
        }

        public void checkConnInfo(Context context, NetworkInfo networkInfo) {
            if (networkInfo == null) {
                lastActiveNetworkInfo = null;
                lastWifiInfo = null;
                onNetworkChangedWithTryCatch();
            } else {
                if (networkInfo.getDetailedState() != NetworkInfo.DetailedState.CONNECTED) {
                    if (lastConnected) {
                        lastActiveNetworkInfo = null;
                        lastWifiInfo = null;
                        onNetworkChangedWithTryCatch();
                    }
                    lastConnected = false;
                    return;
                }
                if (isNetworkChange(context, networkInfo)) {
                    onNetworkChangedWithTryCatch();
                }
                lastConnected = true;
            }
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
                    connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
                } catch (NullPointerException e) {
                    e.printStackTrace();
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

        public static void onNetworkChangedWithTryCatch() {
            try {
                if (netWorkChangeListener != null) {
                    netWorkChangeListener.onNetWorkChange();
                }
                BaseEvent.onNetworkChange();
            } catch (UnsatisfiedLinkError unused) {
                BaseEvent.onNetworkChange();
            }
        }
    }
}
