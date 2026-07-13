package a.a.a.g;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;

/* JADX INFO: compiled from: NetUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static String a(Context context) {
        boolean z;
        char c = 0;
        if (context != null) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
                if (allNetworkInfo != null) {
                    z = false;
                    for (NetworkInfo networkInfo : allNetworkInfo) {
                        if (networkInfo.isConnected()) {
                            z = true;
                        }
                    }
                } else {
                    z = false;
                }
                if (!z) {
                    c = 1;
                } else if (!connectivityManager.getNetworkInfo(1).isConnected()) {
                    switch (((TelephonyManager) context.getApplicationContext().getSystemService(HintConstants.AUTOFILL_HINT_PHONE)).getNetworkType()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                        case 16:
                            c = 2;
                            break;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            c = 3;
                            break;
                        case 13:
                            c = 4;
                            break;
                    }
                } else {
                    c = 5;
                }
            } catch (Exception e) {
                b.c("c", "isConnected exception:" + e.toString());
            }
        }
        return c != 1 ? c != 2 ? c != 3 ? c != 4 ? c != 5 ? "unknown" : "wifi" : "4G" : "3G" : "2G" : "disconnect";
    }
}
