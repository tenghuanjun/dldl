package com.duowan.live.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.duowan.auk.util.L;
import com.duowan.live.receiver.event.OnHeadsetPlugEvent;
import com.huya.live.common.api.BaseApi;
import com.huya.live.common.api.signal.SignalCenterApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HeadsetPlugReceiver extends BroadcastReceiver {
    private static final String TAG = "HeadsetPlugReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (isInitialStickyBroadcast()) {
            return;
        }
        SignalCenterApi signalCenterApi = BaseApi.getSignalCenterApi();
        if ("android.intent.action.HEADSET_PLUG".equals(action)) {
            if (intent.hasExtra("state")) {
                if (intent.getIntExtra("state", 0) == 0) {
                    if (signalCenterApi != null) {
                        signalCenterApi.send(new OnHeadsetPlugEvent(false));
                    }
                    L.info(TAG, "OnHeadsetPlug false");
                    return;
                } else {
                    if (intent.getIntExtra("state", 0) == 1) {
                        if (signalCenterApi != null) {
                            signalCenterApi.send(new OnHeadsetPlugEvent(true));
                        }
                        L.info(TAG, "OnHeadsetPlug true");
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(action) && intent.hasExtra("android.bluetooth.profile.extra.STATE")) {
            int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
            if (intExtra == 2) {
                if (signalCenterApi != null) {
                    signalCenterApi.send(new OnHeadsetPlugEvent(true));
                }
                L.info(TAG, "OnWirelessHeadsetPlug true");
            } else if (intExtra == 0) {
                if (signalCenterApi != null) {
                    signalCenterApi.send(new OnHeadsetPlugEvent(false));
                }
                L.info(TAG, "OnWirelessHeadsetPlug false");
            }
        }
    }
}
