package com.jiguang.h5;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class SignalListener {
    public static final int NETWORK_2G = 2;
    public static final int NETWORK_3G = 3;
    public static final int NETWORK_4G = 4;
    public static final int NETWORK_MOBILE = 5;
    public static final int NETWORK_NONE = 0;
    public static final int NETWORK_WIFI = 1;
    public static final int SIGNAL_1 = 1;
    public static final int SIGNAL_2 = 2;
    public static final int SIGNAL_3 = 3;
    public static final int SIGNAL_4 = 4;
    public static final int SIGNAL_NONE = 0;
    private static int gsmSignal;
    private static Context mContext;

    public SignalListener(Context context) {
        mContext = context;
        ((TelephonyManager) context.getSystemService("phone")).listen(new PhoneStateListener() { // from class: com.jiguang.h5.SignalListener.1
            @Override // android.telephony.PhoneStateListener
            public void onSignalStrengthsChanged(SignalStrength signalStrength) throws IllegalAccessException {
                int gsmSignalStrength = signalStrength.getGsmSignalStrength();
                if (gsmSignalStrength == 99) {
                    try {
                        Field declaredField = signalStrength.getClass().getDeclaredField("mLteSignalStrength");
                        if (declaredField != null) {
                            declaredField.setAccessible(true);
                            gsmSignalStrength = declaredField.getInt(signalStrength);
                        }
                    } catch (Exception unused) {
                    }
                }
                if (gsmSignalStrength <= 2 || gsmSignalStrength == 99) {
                    int unused2 = SignalListener.gsmSignal = 0;
                } else if (gsmSignalStrength >= 12) {
                    int unused3 = SignalListener.gsmSignal = 4;
                } else if (gsmSignalStrength >= 8) {
                    int unused4 = SignalListener.gsmSignal = 3;
                } else if (gsmSignalStrength >= 5) {
                    int unused5 = SignalListener.gsmSignal = 2;
                } else {
                    int unused6 = SignalListener.gsmSignal = 1;
                }
                SdkMgr.Logd("信号强度变化 signal:" + SignalListener.gsmSignal);
            }
        }, 256);
    }

    public static int getSignal() {
        int networkState = getNetworkState();
        if (networkState == 0) {
            return 0;
        }
        if (networkState == 1) {
            int rssi = ((WifiManager) mContext.getSystemService("wifi")).getConnectionInfo().getRssi();
            SdkMgr.Logd("wifi强度 rssi:" + rssi);
            if (rssi > -50) {
                return 4;
            }
            if (rssi > -70) {
                return 3;
            }
            if (rssi > -80) {
                return 2;
            }
            return rssi > -90 ? 1 : 0;
        }
        return gsmSignal;
    }

    public static int getNetworkState() {
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
            return 0;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo != null && (state = networkInfo.getState()) != null && (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            return 1;
        }
        switch (((TelephonyManager) mContext.getSystemService("phone")).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 2;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return 3;
            case 13:
                return 4;
            default:
                return 5;
        }
    }
}
