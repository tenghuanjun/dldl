package com.duowan.live.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.duowan.auk.util.L;
import com.duowan.live.receiver.event.CloseAnchorLinkAndStopLive;
import com.huya.live.common.api.BaseApi;
import com.huya.live.common.api.signal.SignalCenterApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PhoneReceiver extends BroadcastReceiver {
    private static String TAG = PhoneReceiver.class.getSimpleName();
    public static boolean sFlag = false;
    public static Listener sListener;
    private PhoneStateListener mPhoneStateListener = null;

    public interface Listener {
        void onCallStateChanged();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL") || this.mPhoneStateListener != null) {
            return;
        }
        this.mPhoneStateListener = new PhoneStateListener() { // from class: com.duowan.live.receiver.PhoneReceiver.1
            @Override // android.telephony.PhoneStateListener
            public void onCallStateChanged(int i, String str) {
                super.onCallStateChanged(i, str);
                if (i == 2 && PhoneReceiver.sFlag) {
                    PhoneReceiver.sFlag = false;
                    L.info(PhoneReceiver.TAG, "stopLive");
                    SignalCenterApi signalCenterApi = BaseApi.getSignalCenterApi();
                    if (signalCenterApi != null) {
                        signalCenterApi.send(new CloseAnchorLinkAndStopLive());
                    }
                    if (PhoneReceiver.sListener != null) {
                        PhoneReceiver.sListener.onCallStateChanged();
                    }
                }
            }
        };
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            telephonyManager.listen(this.mPhoneStateListener, 32);
        }
    }
}
