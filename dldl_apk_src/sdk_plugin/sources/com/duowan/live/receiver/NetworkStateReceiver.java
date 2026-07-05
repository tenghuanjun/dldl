package com.duowan.live.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.duowan.auk.module.ArkProperties;
import com.duowan.live.receiver.event.NetworkStateEvent;
import com.huya.live.common.api.BaseApi;
import com.huya.live.common.api.signal.SignalCenterApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NetworkStateReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        SignalCenterApi signalCenterApi = BaseApi.getSignalCenterApi();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager == null) {
                    return;
                }
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                boolean z = activeNetworkInfo != null && activeNetworkInfo.isAvailable();
                if (signalCenterApi != null) {
                    signalCenterApi.send(new NetworkStateEvent(z));
                }
                ArkProperties.networkAvailable.set(Boolean.valueOf(z));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
