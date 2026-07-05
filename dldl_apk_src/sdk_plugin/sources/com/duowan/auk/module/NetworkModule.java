package com.duowan.auk.module;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.live.common.api.BaseApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NetworkModule {
    private static final String TAG = "NetworkModule";

    private static final class NetworkModuleHolder {
        static NetworkModule sInstance = new NetworkModule();

        private NetworkModuleHolder() {
        }
    }

    public static NetworkModule getInstance() {
        return NetworkModuleHolder.sInstance;
    }

    public void init() {
        ConnectivityManager connectivityManager;
        L.info(TAG, "NetworkModule start");
        ArkProperties.networkAvailable.set(Boolean.valueOf(isNetworkAvailable()));
        if (Build.VERSION.SDK_INT < 24 || (connectivityManager = (ConnectivityManager) ArkValue.gContext.getSystemService("connectivity")) == null) {
            return;
        }
        connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(), new ConnectivityManager.NetworkCallback() { // from class: com.duowan.auk.module.NetworkModule.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                super.onAvailable(network);
                L.info(NetworkModule.TAG, "networkAvailable onAvailable");
                NetworkModule.this.notifyNetworkChanged();
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                super.onLost(network);
                L.info(NetworkModule.TAG, "networkAvailable onLost");
                NetworkModule.this.notifyNetworkChanged();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyNetworkChanged() {
        boolean zIsNetworkAvailable = isNetworkAvailable();
        if (ArkProperties.networkAvailable.get().booleanValue() != zIsNetworkAvailable) {
            ArkProperties.networkAvailable.setAndNotify(Boolean.valueOf(zIsNetworkAvailable));
            BaseApi.getSignalCenterApi().send(new NetworkChangeEvent(zIsNetworkAvailable));
        }
    }

    static boolean isNetworkAvailable() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) ArkValue.gContext.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            if (!activeNetworkInfo.isConnected()) {
                if (!activeNetworkInfo.isAvailable()) {
                    return false;
                }
                if (!activeNetworkInfo.isConnectedOrConnecting()) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
