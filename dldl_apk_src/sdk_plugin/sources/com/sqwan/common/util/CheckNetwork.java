package com.sqwan.common.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.text.TextUtils;
import com.sqwan.base.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CheckNetwork {
    private static final CheckNetwork ourInstance = new CheckNetwork();
    public CheckNetworkListener checkNetworkListener;
    private ConnectionStateMonitor connectivityManagerCallback_CELLULAR;
    private ConnectionStateMonitor connectivityManagerCallback_WIFI;
    private boolean isUseNewCheckNetwork;
    private NetWorkStateReceiver netWorkStateReceiver;
    private final String TAG = getClass().getSimpleName();
    private boolean isWifiAvailable = false;
    private boolean isCellularAvailable = false;

    public interface CheckNetworkListener {
        void onNetworkChange(boolean z);
    }

    public static CheckNetwork getInstance() {
        return ourInstance;
    }

    private CheckNetwork() {
        this.isUseNewCheckNetwork = Build.VERSION.SDK_INT >= 24;
        this.connectivityManagerCallback_WIFI = null;
        this.connectivityManagerCallback_CELLULAR = null;
        this.netWorkStateReceiver = null;
    }

    class ConnectionStateMonitor extends ConnectivityManager.NetworkCallback {
        private boolean isWifi;
        private NetworkRequest networkRequest;

        public ConnectionStateMonitor(boolean z) {
            this.isWifi = z;
            if (z) {
                this.networkRequest = new NetworkRequest.Builder().addTransportType(1).build();
            } else {
                this.networkRequest = new NetworkRequest.Builder().addTransportType(0).build();
            }
        }

        public void register() {
            ((ConnectivityManager) L.getApplicationContext().getSystemService("connectivity")).registerNetworkCallback(this.networkRequest, this);
        }

        public void unregister() {
            ((ConnectivityManager) L.getApplicationContext().getSystemService("connectivity")).unregisterNetworkCallback(this);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            boolean z = CheckNetwork.this.isWifiAvailable || CheckNetwork.this.isCellularAvailable;
            LogUtil.d(CheckNetwork.this.TAG, "onAvailable  isWifi:" + this.isWifi);
            if (this.isWifi) {
                CheckNetwork.this.isWifiAvailable = true;
            } else {
                CheckNetwork.this.isCellularAvailable = true;
            }
            if (z == (CheckNetwork.this.isWifiAvailable || CheckNetwork.this.isCellularAvailable) || CheckNetwork.this.checkNetworkListener == null) {
                return;
            }
            LogUtil.i(CheckNetwork.this.TAG, "isConnected:true");
            CheckNetwork.this.checkNetworkListener.onNetworkChange(true);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onUnavailable() {
            LogUtil.d(CheckNetwork.this.TAG, "onUnavailable isWifi:" + this.isWifi);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            boolean z = true;
            boolean z2 = CheckNetwork.this.isWifiAvailable || CheckNetwork.this.isCellularAvailable;
            LogUtil.d(CheckNetwork.this.TAG, "onLost isWifi:" + this.isWifi);
            if (this.isWifi) {
                CheckNetwork.this.isWifiAvailable = false;
            } else {
                CheckNetwork.this.isCellularAvailable = false;
            }
            if (!CheckNetwork.this.isWifiAvailable && !CheckNetwork.this.isCellularAvailable) {
                z = false;
            }
            if (z2 == z || CheckNetwork.this.checkNetworkListener == null) {
                return;
            }
            LogUtil.i(CheckNetwork.this.TAG, "isConnected:false");
            CheckNetwork.this.checkNetworkListener.onNetworkChange(false);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            if (networkCapabilities.hasCapability(16)) {
                if (networkCapabilities.hasTransport(1)) {
                    LogUtil.d(CheckNetwork.this.TAG, "onCapabilitiesChanged: 网络类型为wifi isWifi:" + this.isWifi);
                    return;
                }
                if (networkCapabilities.hasTransport(0)) {
                    LogUtil.d(CheckNetwork.this.TAG, "onCapabilitiesChanged: 蜂窝网络 isWifi:" + this.isWifi);
                    return;
                }
                LogUtil.d(CheckNetwork.this.TAG, "onCapabilitiesChanged: 其他网络 isWifi:" + this.isWifi);
            }
        }
    }

    public void register() {
        if (this.isUseNewCheckNetwork) {
            if (this.connectivityManagerCallback_WIFI == null) {
                ConnectionStateMonitor connectionStateMonitor = new ConnectionStateMonitor(true);
                this.connectivityManagerCallback_WIFI = connectionStateMonitor;
                connectionStateMonitor.register();
            }
            if (this.connectivityManagerCallback_CELLULAR == null) {
                ConnectionStateMonitor connectionStateMonitor2 = new ConnectionStateMonitor(false);
                this.connectivityManagerCallback_CELLULAR = connectionStateMonitor2;
                connectionStateMonitor2.register();
                return;
            }
            return;
        }
        if (this.netWorkStateReceiver == null) {
            this.netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        L.getApplicationContext().registerReceiver(this.netWorkStateReceiver, intentFilter);
    }

    public void unregister() {
        if (this.isUseNewCheckNetwork) {
            ConnectionStateMonitor connectionStateMonitor = this.connectivityManagerCallback_CELLULAR;
            if (connectionStateMonitor != null) {
                connectionStateMonitor.unregister();
                this.connectivityManagerCallback_CELLULAR = null;
            }
            ConnectionStateMonitor connectionStateMonitor2 = this.connectivityManagerCallback_WIFI;
            if (connectionStateMonitor2 != null) {
                connectionStateMonitor2.unregister();
                this.connectivityManagerCallback_WIFI = null;
                return;
            }
            return;
        }
        if (this.netWorkStateReceiver != null) {
            L.getApplicationContext().unregisterReceiver(this.netWorkStateReceiver);
            this.netWorkStateReceiver = null;
        }
    }

    class NetWorkStateReceiver extends BroadcastReceiver {
        NetWorkStateReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean zHasNetwork = CheckNetwork.this.hasNetwork();
            if (CheckNetwork.this.checkNetworkListener != null) {
                CheckNetwork.this.checkNetworkListener.onNetworkChange(zHasNetwork);
            }
        }
    }

    public boolean hasNetwork() {
        return isWifi() || isMobile();
    }

    public boolean isMobile() {
        ConnectivityManager connectivityManager = (ConnectivityManager) L.getApplicationContext().getSystemService("connectivity");
        if (Build.VERSION.SDK_INT < 21) {
            return connectivityManager.getNetworkInfo(0).isConnected();
        }
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            if (TextUtils.equals("MOBILE", networkInfo.getTypeName()) && networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public boolean isWifi() {
        ConnectivityManager connectivityManager = (ConnectivityManager) L.getApplicationContext().getSystemService("connectivity");
        if (Build.VERSION.SDK_INT < 21) {
            return connectivityManager.getNetworkInfo(1).isConnected();
        }
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            if (networkInfo != null && TextUtils.equals("WIFI", networkInfo.getTypeName()) && networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }
}
