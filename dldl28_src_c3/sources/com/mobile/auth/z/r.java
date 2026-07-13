package com.mobile.auth.z;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class r {
    public static boolean a(Context context, String str) {
        try {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager == null) {
                    return false;
                }
                NetworkInfo.State state = connectivityManager.getNetworkInfo(5).getState();
                t.c("TYPE_MOBILE_HIPRI network state: ".concat(String.valueOf(state)));
                if (state.compareTo(NetworkInfo.State.CONNECTED) != 0 && state.compareTo(NetworkInfo.State.CONNECTING) != 0) {
                    Method method = ConnectivityManager.class.getMethod("startUsingNetworkFeature", Integer.TYPE, String.class);
                    method.setAccessible(true);
                    int iIntValue = ((Integer) method.invoke(connectivityManager, 0, "enableHIPRI")).intValue();
                    t.c("startUsingNetworkFeature for enableHIPRI result: ".concat(String.valueOf(iIntValue)));
                    if (-1 == iIntValue) {
                        t.c("Wrong result of startUsingNetworkFeature, maybe problems");
                        return false;
                    }
                    if (iIntValue == 0) {
                        t.c("No need to perform additional network settings");
                        return true;
                    }
                    int iB = v.b(str);
                    if (-1 == iB) {
                        t.c("Wrong host address transformation, result was -1");
                        return false;
                    }
                    for (int i = 0; i < 5; i++) {
                        try {
                            if (connectivityManager.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                                break;
                            }
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                    Class cls = Integer.TYPE;
                    Method method2 = ConnectivityManager.class.getMethod("requestRouteToHost", cls, cls);
                    method2.setAccessible(true);
                    boolean zBooleanValue = ((Boolean) method2.invoke(connectivityManager, 5, Integer.valueOf(iB))).booleanValue();
                    t.c("requestRouteToHost result: ".concat(String.valueOf(zBooleanValue)));
                    return zBooleanValue;
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }
}
