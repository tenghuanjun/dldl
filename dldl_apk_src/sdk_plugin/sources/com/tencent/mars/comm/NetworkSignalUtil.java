package com.tencent.mars.comm;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class NetworkSignalUtil {
    public static final String TAG = "MicroMsg.NetworkSignalUtil";
    private static Context context = null;
    private static long strength = 10000;

    public static long getNetworkSignalStrength(boolean z) {
        return 0L;
    }

    public static void InitNetworkSignalUtil(Context context2) {
        context = context2;
        ((TelephonyManager) context2.getSystemService("phone")).listen(new PhoneStateListener() { // from class: com.tencent.mars.comm.NetworkSignalUtil.1
            @Override // android.telephony.PhoneStateListener
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);
                NetworkSignalUtil.calSignalStrength(signalStrength);
            }
        }, 256);
    }

    public static long getGSMSignalStrength() {
        return strength;
    }

    public static long getWifiSignalStrength() {
        WifiInfo connectionInfo;
        try {
            connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        } catch (NullPointerException e) {
            e.printStackTrace();
            connectionInfo = null;
        }
        if (connectionInfo == null || connectionInfo.getBSSID() == null) {
            return 0L;
        }
        int iCalculateSignalLevel = WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 10);
        if (iCalculateSignalLevel > 10) {
            iCalculateSignalLevel = 10;
        }
        if (iCalculateSignalLevel < 0) {
            iCalculateSignalLevel = 0;
        }
        return iCalculateSignalLevel * 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void calSignalStrength(SignalStrength signalStrength) {
        int cdmaDbm;
        if (signalStrength.isGsm()) {
            cdmaDbm = signalStrength.getGsmSignalStrength();
        } else {
            cdmaDbm = (signalStrength.getCdmaDbm() + 113) / 2;
        }
        if (signalStrength.isGsm() && cdmaDbm == 99) {
            strength = 0L;
            return;
        }
        long j = (long) (cdmaDbm * 3.2258065f);
        strength = j;
        if (j > 100) {
            j = 100;
        }
        strength = j;
        strength = j >= 0 ? j : 0L;
    }
}
