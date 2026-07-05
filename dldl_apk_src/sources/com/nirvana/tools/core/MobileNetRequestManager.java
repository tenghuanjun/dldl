package com.nirvana.tools.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;
import java.net.InetAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class MobileNetRequestManager {
    public static final int CODE_SWITCH_EXCEPTION = 80801;
    public static final int CODE_SWITCH_TIMEOUT = 80800;
    private static final int DELAY_CHECK = 2500;
    public static final String MSG_SWITCH_EXCEPTION = "WIFI切换异常";
    public static final String MSG_SWITCH_TIMEOUT = "WIFI切换超时";
    private static final String TAG = MobileNetRequestManager.class.getSimpleName();
    CountDownLatch countDownLatch;
    private SwitchToMobileListener mSwitchToMobileListener;
    private boolean switchState = false;
    private int mExpiredTime = 2500;
    private ConnectivityManager connectivityManager = null;
    private ConnectivityManager.NetworkCallback myNetCallback = null;
    private long expendTime = 0;
    private long startTime = 0;

    public interface SwitchToMobileListener {
        void onFail(int i, String str, long j);

        void onSuccess(Network network, long j);
    }

    private void destry() {
        ConnectivityManager connectivityManager;
        ConnectivityManager.NetworkCallback networkCallback;
        if (Build.VERSION.SDK_INT < 21 || (connectivityManager = this.connectivityManager) == null || (networkCallback = this.myNetCallback) == null) {
            return;
        }
        try {
            connectivityManager.unregisterNetworkCallback(networkCallback);
        } catch (Throwable th) {
            Log.w(TAG, "unregisterNetworkCallback", th);
        }
        this.connectivityManager = null;
    }

    public static String extractAddressFromUrl(String str) {
        int iIndexOf = str.indexOf("://");
        if (iIndexOf > 0) {
            str = str.substring(iIndexOf + 3);
        }
        int iIndexOf2 = str.indexOf(58);
        if (iIndexOf2 >= 0) {
            str = str.substring(0, iIndexOf2);
        }
        int iIndexOf3 = str.indexOf(47);
        if (iIndexOf3 >= 0) {
            str = str.substring(0, iIndexOf3);
        }
        int iIndexOf4 = str.indexOf(63);
        return iIndexOf4 >= 0 ? str.substring(0, iIndexOf4) : str;
    }

    public static int lookupHost(String str) {
        try {
            byte[] address = InetAddress.getByName(str).getAddress();
            return (address[0] & UByte.MAX_VALUE) | ((address[3] & UByte.MAX_VALUE) << 24) | ((address[2] & UByte.MAX_VALUE) << 16) | ((address[1] & UByte.MAX_VALUE) << 8);
        } catch (Throwable th) {
            Log.w(TAG, "When InetAddress.getByName(),throws exception", th);
            return -1;
        }
    }

    private void switchToMobileForAboveL(Context context) {
        this.expendTime = 0L;
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.startTime = System.currentTimeMillis();
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        builder.addCapability(12);
        builder.addTransportType(0);
        NetworkRequest networkRequestBuild = builder.build();
        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.nirvana.tools.core.MobileNetRequestManager.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                MobileNetRequestManager mobileNetRequestManager = MobileNetRequestManager.this;
                mobileNetRequestManager.expendTime = jCurrentTimeMillis - mobileNetRequestManager.startTime;
                MobileNetRequestManager.this.switchState = true;
                if (MobileNetRequestManager.this.mSwitchToMobileListener != null) {
                    MobileNetRequestManager.this.mSwitchToMobileListener.onSuccess(network, MobileNetRequestManager.this.expendTime);
                }
                if (MobileNetRequestManager.this.connectivityManager != null) {
                    try {
                        MobileNetRequestManager.this.connectivityManager.unregisterNetworkCallback(this);
                        MobileNetRequestManager.this.connectivityManager = null;
                    } catch (Throwable th) {
                        Log.w(MobileNetRequestManager.TAG, "switchToMobileForAboveL", th);
                    }
                }
            }
        };
        this.myNetCallback = networkCallback;
        this.connectivityManager.requestNetwork(networkRequestBuild, networkCallback);
    }

    private boolean switchToMobileForUnderL(Context context, String str) {
        boolean zBooleanValue = false;
        try {
            Class<?> cls = Class.forName("android.net.ConnectivityManager");
            this.expendTime = 0L;
            this.startTime = System.currentTimeMillis();
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            this.connectivityManager = connectivityManager;
            if (connectivityManager.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) != 0) {
                cls.getMethod("startUsingNetworkFeature", Integer.TYPE, String.class).invoke(this.connectivityManager, 0, "enableHIPRI");
                for (int i = 0; i < 5; i++) {
                    try {
                        if (this.connectivityManager.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                            break;
                        }
                        Thread.sleep(500L);
                    } catch (Throwable th) {
                        Log.w(TAG, "switchToMobileForUnderL", th);
                    }
                }
            }
            zBooleanValue = ((Boolean) cls.getMethod("requestRouteToHost", Integer.TYPE, Integer.TYPE).invoke(this.connectivityManager, 5, Integer.valueOf(lookupHost(extractAddressFromUrl(str))))).booleanValue();
            this.expendTime = System.currentTimeMillis() - this.startTime;
            Log.i(TAG, "Switch network result ： " + zBooleanValue + " (4.x) , expendTime ：" + this.expendTime);
            return zBooleanValue;
        } catch (Throwable th2) {
            Log.w(TAG, "4.x网络切换异常", th2);
            return zBooleanValue;
        }
    }

    public boolean switchToMobile_4x(Context context, String str) {
        return switchToMobileForUnderL(context, str);
    }

    public void switchToMobile_L(Context context, int i, SwitchToMobileListener switchToMobileListener) {
        if (i < 2500) {
            this.mExpiredTime = 2500;
        } else {
            this.mExpiredTime = i;
        }
        this.mSwitchToMobileListener = switchToMobileListener;
        this.countDownLatch = new CountDownLatch(1);
        try {
            switchToMobileForAboveL(context);
            this.countDownLatch.await(this.mExpiredTime, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            Log.w(TAG, "switchToMobileForAboveL", th);
            this.countDownLatch.countDown();
            SwitchToMobileListener switchToMobileListener2 = this.mSwitchToMobileListener;
            if (switchToMobileListener2 != null) {
                switchToMobileListener2.onFail(CODE_SWITCH_EXCEPTION, MSG_SWITCH_EXCEPTION, -1L);
            }
        }
        if (this.switchState) {
            return;
        }
        SwitchToMobileListener switchToMobileListener3 = this.mSwitchToMobileListener;
        if (switchToMobileListener3 != null) {
            switchToMobileListener3.onFail(CODE_SWITCH_TIMEOUT, MSG_SWITCH_TIMEOUT, 2500L);
        }
        destry();
    }
}
