package com.duowan.auk.module;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.duowan.auk.util.L;
import com.huya.live.common.api.BaseApi;
import com.huya.live.common.api.signal.SignalCenterApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NetworkReceiver extends BroadcastReceiver {
    private static final String TAG = "NetworkReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        Boolean boolValueOf = Boolean.valueOf(NetworkModule.isNetworkAvailable());
        L.debug(TAG, "received broadcast: %s, networkAvailable: %s", intent.getAction(), boolValueOf);
        ArkProperties.networkAvailable.setAndNotify(boolValueOf);
        SignalCenterApi signalCenterApi = BaseApi.getSignalCenterApi();
        if (signalCenterApi != null) {
            signalCenterApi.send(new NetworkChangeEvent(boolValueOf.booleanValue()));
        }
    }
}
