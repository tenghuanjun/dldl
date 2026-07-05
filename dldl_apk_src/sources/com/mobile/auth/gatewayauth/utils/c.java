package com.mobile.auth.gatewayauth.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.jiguang.h5.PermissionUtils;
import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.nirvana.tools.core.SupportJarUtils;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class c {
    private static String a = "";
    private static volatile String b;
    private static volatile long c;

    public static int a(Context context) {
        try {
            return com.mobile.auth.s.e.a(context, 4);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return -1;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return -1;
            }
        }
    }

    public static int a(String str) {
        if (str == null) {
            return 4;
        }
        byte b2 = -1;
        try {
            int iHashCode = str.hashCode();
            if (iHashCode != -1350608857) {
                if (iHashCode != 95009260) {
                    if (iHashCode == 880617272 && str.equals(Constant.VENDOR_CMCC)) {
                        b2 = 0;
                    }
                } else if (str.equals(Constant.VENDOR_CUCC)) {
                    b2 = 1;
                }
            } else if (str.equals(Constant.VENDOR_CTCC)) {
                b2 = 2;
            }
            if (b2 == 0) {
                return 1;
            }
            if (b2 != 1) {
                return b2 != 2 ? 4 : 3;
            }
            return 2;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return -1;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return -1;
            }
        }
    }

    public static String a() {
        try {
            return a;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static String a(Context context, boolean z) {
        try {
            if (System.currentTimeMillis() - c > 500 || b == null || !z) {
                b = k(context);
                c = System.currentTimeMillis();
            }
            return b;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static String b() {
        Enumeration<NetworkInterface> networkInterfaces;
        try {
            try {
                networkInterfaces = NetworkInterface.getNetworkInterfaces();
            } catch (Exception e) {
                i.a(e);
                return "";
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
            if (!networkInterfaceNextElement.getDisplayName().contains("wlan") && !networkInterfaceNextElement.getDisplayName().equals("eth0")) {
                Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address)) {
                        return inetAddressNextElement.getHostAddress();
                    }
                    ExceptionProcessor.processException(th);
                    return null;
                }
            }
        }
        return "";
    }

    public static String b(Context context) {
        try {
            int iA = a(context);
            return iA == 4 ? "unknown" : iA != 1 ? iA != 2 ? iA != 3 ? "unknown" : Constant.VENDOR_CTCC : Constant.VENDOR_CUCC : Constant.VENDOR_CMCC;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static String b(String str) {
        if (str == null) {
            return "unknown";
        }
        byte b2 = -1;
        try {
            int iHashCode = str.hashCode();
            if (iHashCode != -1350608857) {
                if (iHashCode != 95009260) {
                    if (iHashCode == 880617272 && str.equals(Constant.VENDOR_CMCC)) {
                        b2 = 0;
                    }
                } else if (str.equals(Constant.VENDOR_CUCC)) {
                    b2 = 1;
                }
            } else if (str.equals(Constant.VENDOR_CTCC)) {
                b2 = 2;
            }
            return b2 != 0 ? b2 != 1 ? b2 != 2 ? "unknown" : Constant.CTCC : Constant.CUCC : Constant.CMCC;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static String c(Context context) {
        try {
            int iA = a(context);
            return iA == 4 ? "unknown" : iA != 1 ? iA != 2 ? iA != 3 ? "unknown" : Constant.CTCC : Constant.CUCC : Constant.CMCC;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static String c(String str) {
        if (str == null) {
            return "unknown";
        }
        byte b2 = -1;
        try {
            int iHashCode = str.hashCode();
            if (iHashCode != -1350608857) {
                if (iHashCode != 95009260) {
                    if (iHashCode == 880617272 && str.equals(Constant.VENDOR_CMCC)) {
                        b2 = 0;
                    }
                } else if (str.equals(Constant.VENDOR_CUCC)) {
                    b2 = 1;
                }
            } else if (str.equals(Constant.VENDOR_CTCC)) {
                b2 = 2;
            }
            return b2 != 0 ? b2 != 1 ? b2 != 2 ? "unknown" : "46003" : "46001" : "46000";
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static boolean d(Context context) {
        NetworkInfo activeNetworkInfo;
        NetworkInfo networkInfo;
        NetworkInfo networkInfo2;
        NetworkInfo.State state;
        NetworkInfo.State state2;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable() && (((networkInfo = connectivityManager.getNetworkInfo(1)) == null || (state2 = networkInfo.getState()) == null || (state2 != NetworkInfo.State.CONNECTED && state2 != NetworkInfo.State.CONNECTING)) && (networkInfo2 = connectivityManager.getNetworkInfo(0)) != null && (state = networkInfo2.getState()) != null)) {
                if (state != NetworkInfo.State.CONNECTED) {
                    if (state == NetworkInfo.State.CONNECTING) {
                    }
                }
                return false;
            }
            return true;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
    }

    public static boolean e(Context context) {
        try {
            if (j(context)) {
                return false;
            }
            if (i(context)) {
                return true;
            }
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
                Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
                declaredMethod.setAccessible(true);
                return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
            } catch (Exception e) {
                i.a(e);
                return true;
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
    }

    public static boolean f(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimState() == 5;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
    }

    public static void g(Context context) {
        try {
            a = h(context);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004e A[Catch: all -> 0x0063, Exception -> 0x0065, TryCatch #2 {Exception -> 0x0065, blocks: (B:2:0x0000, B:3:0x0004, B:5:0x000a, B:7:0x0016, B:9:0x001c, B:17:0x0044, B:18:0x0048, B:20:0x004e, B:22:0x005a, B:24:0x005e, B:12:0x002d, B:14:0x0033), top: B:40:0x0000, outer: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String h(android.content.Context r4) {
        /*
            java.util.Enumeration r0 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
        L4:
            boolean r1 = r0.hasMoreElements()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            if (r1 == 0) goto L69
            java.lang.Object r1 = r0.nextElement()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            java.net.NetworkInterface r1 = (java.net.NetworkInterface) r1     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            boolean r2 = d(r4)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            if (r2 == 0) goto L2d
            java.lang.String r2 = r1.getName()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            if (r2 == 0) goto L44
            java.lang.String r2 = r1.getName()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            java.lang.String r2 = r2.toLowerCase()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            java.lang.String r3 = "wlan"
            boolean r2 = r2.contains(r3)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            if (r2 != 0) goto L44
            goto L4
        L2d:
            java.lang.String r2 = r1.getName()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            if (r2 == 0) goto L44
            java.lang.String r2 = r1.getName()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            java.lang.String r2 = r2.toLowerCase()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            java.lang.String r3 = "rmnet"
            boolean r2 = r2.contains(r3)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            if (r2 != 0) goto L44
            goto L4
        L44:
            java.util.Enumeration r1 = r1.getInetAddresses()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
        L48:
            boolean r2 = r1.hasMoreElements()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            if (r2 == 0) goto L4
            java.lang.Object r2 = r1.nextElement()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            java.net.InetAddress r2 = (java.net.InetAddress) r2     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            boolean r3 = r2.isLoopbackAddress()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            if (r3 != 0) goto L48
            boolean r3 = r2 instanceof java.net.Inet4Address     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            if (r3 == 0) goto L48
            java.lang.String r4 = r2.getHostAddress()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L65
            return r4
        L63:
            r4 = move-exception
            goto L6c
        L65:
            r4 = move-exception
            com.mobile.auth.gatewayauth.utils.i.a(r4)     // Catch: java.lang.Throwable -> L63
        L69:
            java.lang.String r4 = ""
            return r4
        L6c:
            r0 = 0
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r4)     // Catch: java.lang.Throwable -> L71
            return r0
        L71:
            r4 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.gatewayauth.utils.c.h(android.content.Context):java.lang.String");
    }

    public static boolean i(Context context) {
        if (context != null) {
            try {
                NetworkInfo networkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getNetworkInfo(0);
                if (networkInfo == null || !networkInfo.isAvailable()) {
                    return false;
                }
                return networkInfo.isConnected();
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return false;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                }
            }
        }
        return false;
    }

    public static boolean j(Context context) {
        boolean z = false;
        try {
            if (Build.VERSION.SDK_INT >= 17 ? Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1 : Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1) {
                z = true;
            }
        } catch (Throwable th) {
            try {
                i.a(th);
            } catch (Throwable th2) {
                try {
                    ExceptionProcessor.processException(th2);
                    return z;
                } catch (Throwable th3) {
                    ExceptionProcessor.processException(th3);
                    return z;
                }
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String k(android.content.Context r7) {
        /*
            r0 = 0
            java.lang.String r1 = "NoInternet"
            android.content.Context r2 = r7.getApplicationContext()     // Catch: java.lang.Throwable -> L84
            java.lang.String r3 = "connectivity"
            java.lang.Object r2 = r2.getSystemService(r3)     // Catch: java.lang.Throwable -> L84
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2     // Catch: java.lang.Throwable -> L84
            java.lang.String r3 = "android.permission.ACCESS_NETWORK_STATE"
            int r3 = com.nirvana.tools.core.SupportJarUtils.checkSelfPermission(r7, r3)     // Catch: java.lang.Throwable -> L84
            if (r3 != 0) goto L83
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L84
            r4 = 23
            if (r3 < r4) goto L28
            android.net.Network r3 = r2.getActiveNetwork()     // Catch: java.lang.Throwable -> L84
            if (r3 == 0) goto L47
            android.net.NetworkInfo r2 = r2.getNetworkInfo(r3)     // Catch: java.lang.Throwable -> L84
            goto L48
        L28:
            android.net.NetworkInfo[] r2 = r2.getAllNetworkInfo()     // Catch: java.lang.Throwable -> L84
            if (r2 == 0) goto L47
            int r3 = r2.length     // Catch: java.lang.Throwable -> L84
            r4 = 0
        L30:
            if (r4 >= r3) goto L47
            r5 = r2[r4]     // Catch: java.lang.Throwable -> L84
            if (r5 == 0) goto L44
            boolean r6 = r5.isAvailable()     // Catch: java.lang.Throwable -> L84
            if (r6 == 0) goto L44
            boolean r6 = r5.isConnected()     // Catch: java.lang.Throwable -> L84
            if (r6 == 0) goto L44
            r2 = r5
            goto L48
        L44:
            int r4 = r4 + 1
            goto L30
        L47:
            r2 = r0
        L48:
            if (r2 == 0) goto L83
            boolean r3 = r2.isConnected()     // Catch: java.lang.Throwable -> L84
            if (r3 == 0) goto L83
            java.lang.String r2 = r2.getTypeName()     // Catch: java.lang.Throwable -> L84
            java.lang.String r3 = "WIFI"
            boolean r3 = r3.equalsIgnoreCase(r2)     // Catch: java.lang.Throwable -> L84
            if (r3 == 0) goto L69
            boolean r7 = e(r7)     // Catch: java.lang.Throwable -> L84
            if (r7 == 0) goto L65
            java.lang.String r7 = "wifi+mobile"
            goto L67
        L65:
            java.lang.String r7 = "wifi"
        L67:
            r1 = r7
            goto L83
        L69:
            java.lang.String r3 = "MOBILE"
            boolean r2 = r3.equalsIgnoreCase(r2)     // Catch: java.lang.Throwable -> L84
            if (r2 == 0) goto L83
            java.lang.String r1 = android.net.Proxy.getDefaultHost()     // Catch: java.lang.Throwable -> L84
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L84
            if (r1 == 0) goto L80
            java.lang.String r7 = l(r7)     // Catch: java.lang.Throwable -> L84
            goto L67
        L80:
            java.lang.String r7 = "wap"
            goto L67
        L83:
            return r1
        L84:
            r7 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r7)     // Catch: java.lang.Throwable -> L89
            return r0
        L89:
            r7 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.gatewayauth.utils.c.k(android.content.Context):java.lang.String");
    }

    private static String l(Context context) {
        try {
            if (SupportJarUtils.checkSelfPermission(context.getApplicationContext(), PermissionUtils.PERMISSION_READ_PHONE_STATE) != 0) {
                return "unknow";
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
            int dataNetworkType = Build.VERSION.SDK_INT >= 30 ? telephonyManager.getDataNetworkType() : telephonyManager.getNetworkType();
            if (dataNetworkType == 20) {
                return "5g";
            }
            switch (dataNetworkType) {
            }
            return "unknow";
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }
}
