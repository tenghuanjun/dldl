package com.volcengine.j;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import kotlin.KotlinVersion;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class m {
    public static Map<String, Object> a(String str) throws Throwable {
        Object obj;
        long jCurrentTimeMillis;
        InetAddress[] allByName;
        HashMap map = new HashMap();
        Object obj2 = null;
        try {
            try {
                jCurrentTimeMillis = System.currentTimeMillis();
            } catch (UnknownHostException e) {
                e = e;
                jCurrentTimeMillis = 0;
            }
            try {
                allByName = InetAddress.getAllByName(str);
                if (allByName != null) {
                    try {
                        try {
                            obj2 = (System.currentTimeMillis() - jCurrentTimeMillis) + "";
                        } catch (UnknownHostException e2) {
                            e = e2;
                            obj2 = (System.currentTimeMillis() - jCurrentTimeMillis) + "";
                            e.printStackTrace();
                            map.put("remoteInet", allByName);
                        }
                    } catch (Throwable th) {
                        th = th;
                        Object obj3 = obj2;
                        obj2 = allByName;
                        obj = obj3;
                        map.put("remoteInet", obj2);
                        map.put("useTime", obj);
                        throw th;
                    }
                }
                map.put("remoteInet", allByName);
            } catch (UnknownHostException e3) {
                e = e3;
                allByName = null;
                obj2 = (System.currentTimeMillis() - jCurrentTimeMillis) + "";
                e.printStackTrace();
                map.put("remoteInet", allByName);
                map.put("useTime", obj2);
                return map;
            }
            map.put("useTime", obj2);
            return map;
        } catch (Throwable th2) {
            th = th2;
            obj = null;
            map.put("remoteInet", obj2);
            map.put("useTime", obj);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String[] a() {
        /*
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Exception -> L85
            java.lang.String r2 = "getprop"
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Exception -> L85
            java.io.InputStream r1 = r1.getInputStream()     // Catch: java.lang.Exception -> L85
            java.io.LineNumberReader r2 = new java.io.LineNumberReader     // Catch: java.lang.Exception -> L85
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> L85
            r3.<init>(r1)     // Catch: java.lang.Exception -> L85
            r2.<init>(r3)     // Catch: java.lang.Exception -> L85
        L1d:
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Exception -> L85
            if (r3 == 0) goto L7e
            java.lang.String r4 = "]: ["
            int r4 = r3.indexOf(r4)     // Catch: java.lang.Exception -> L85
            r5 = -1
            if (r4 != r5) goto L2d
            goto L1d
        L2d:
            r5 = 1
            java.lang.String r6 = r3.substring(r5, r4)     // Catch: java.lang.Exception -> L85
            int r4 = r4 + 4
            int r7 = r3.length()     // Catch: java.lang.Exception -> L85
            int r7 = r7 - r5
            java.lang.String r3 = r3.substring(r4, r7)     // Catch: java.lang.Exception -> L85
            java.lang.String r4 = ".dns"
            boolean r4 = r6.endsWith(r4)     // Catch: java.lang.Exception -> L85
            if (r4 != 0) goto L65
            java.lang.String r4 = ".dns1"
            boolean r4 = r6.endsWith(r4)     // Catch: java.lang.Exception -> L85
            if (r4 != 0) goto L65
            java.lang.String r4 = ".dns2"
            boolean r4 = r6.endsWith(r4)     // Catch: java.lang.Exception -> L85
            if (r4 != 0) goto L65
            java.lang.String r4 = ".dns3"
            boolean r4 = r6.endsWith(r4)     // Catch: java.lang.Exception -> L85
            if (r4 != 0) goto L65
            java.lang.String r4 = ".dns4"
            boolean r4 = r6.endsWith(r4)     // Catch: java.lang.Exception -> L85
            if (r4 == 0) goto L1d
        L65:
            java.net.InetAddress r3 = java.net.InetAddress.getByName(r3)     // Catch: java.lang.Exception -> L85
            if (r3 != 0) goto L6c
            goto L1d
        L6c:
            java.lang.String r3 = r3.getHostAddress()     // Catch: java.lang.Exception -> L85
            if (r3 != 0) goto L73
            goto L1d
        L73:
            int r4 = r3.length()     // Catch: java.lang.Exception -> L85
            if (r4 != 0) goto L7a
            goto L1d
        L7a:
            r0.add(r3)     // Catch: java.lang.Exception -> L85
            goto L1d
        L7e:
            r1.close()     // Catch: java.lang.Exception -> L85
            r2.close()     // Catch: java.lang.Exception -> L85
            goto L89
        L85:
            r1 = move-exception
            r1.printStackTrace()
        L89:
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L93
            r0 = 0
            java.lang.String[] r0 = new java.lang.String[r0]
            goto L9f
        L93:
            int r1 = r0.size()
            java.lang.String[] r1 = new java.lang.String[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            java.lang.String[] r0 = (java.lang.String[]) r0
        L9f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.volcengine.j.m.a():java.lang.String[]");
    }

    private static String[] a(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        LinkedList linkedList = new LinkedList();
        if (context != null && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
            for (Network network : connectivityManager.getAllNetworks()) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                if (networkInfo != null && networkInfo.getType() == activeNetworkInfo.getType()) {
                    Iterator<InetAddress> it = connectivityManager.getLinkProperties(network).getDnsServers().iterator();
                    while (it.hasNext()) {
                        linkedList.add(it.next().getHostAddress());
                    }
                }
            }
        }
        return linkedList.isEmpty() ? new String[0] : (String[]) linkedList.toArray(new String[linkedList.size()]);
    }

    public static String b() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address)) {
                        return inetAddressNextElement.getHostAddress();
                    }
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String b(Context context) {
        DhcpInfo dhcpInfo;
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager == null || (dhcpInfo = wifiManager.getDhcpInfo()) == null) {
            return "";
        }
        int i = dhcpInfo.gateway;
        return String.format("%d.%d.%d.%d", Integer.valueOf(i & KotlinVersion.MAX_COMPONENT_VALUE), Integer.valueOf((i >> 8) & KotlinVersion.MAX_COMPONENT_VALUE), Integer.valueOf((i >> 16) & KotlinVersion.MAX_COMPONENT_VALUE), Integer.valueOf((i >> 24) & KotlinVersion.MAX_COMPONENT_VALUE));
    }

    public static String c(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return telephonyManager != null ? telephonyManager.getNetworkCountryIso() : "";
    }

    public static String[] d(Context context) {
        String[] strArrA = a();
        return (strArrA == null || strArrA.length == 0) ? a(context) : strArrA;
    }

    public static String e(Context context) {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
                return "";
            }
            int ipAddress = connectionInfo.getIpAddress();
            return String.format("%d.%d.%d.%d", Integer.valueOf(ipAddress & KotlinVersion.MAX_COMPONENT_VALUE), Integer.valueOf((ipAddress >> 8) & KotlinVersion.MAX_COMPONENT_VALUE), Integer.valueOf((ipAddress >> 16) & KotlinVersion.MAX_COMPONENT_VALUE), Integer.valueOf((ipAddress >> 24) & KotlinVersion.MAX_COMPONENT_VALUE));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String f(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        String networkOperator = telephonyManager.getNetworkOperator();
        return (TextUtils.isEmpty(networkOperator) || networkOperator.length() < 3) ? "" : networkOperator.substring(0, 3);
    }

    public static String g(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        String networkOperator = telephonyManager.getNetworkOperator();
        return (TextUtils.isEmpty(networkOperator) || networkOperator.length() < 5) ? "" : networkOperator.substring(3, 5);
    }

    public static String h(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return telephonyManager != null ? telephonyManager.getSimOperator() : "";
    }

    public static String i(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return Constants.APP_VERSION_UNKNOWN;
        }
        String typeName = activeNetworkInfo.getTypeName();
        if (typeName.equalsIgnoreCase("WIFI")) {
            return "WIFI";
        }
        if (typeName.equalsIgnoreCase("MOBILE")) {
            return TextUtils.isEmpty(Proxy.getDefaultHost()) ? k(context) : "WAP";
        }
        return null;
    }

    public static boolean j(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    private static String k(Context context) {
        TelephonyManager telephonyManager;
        try {
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        if (telephonyManager == null) {
            return "";
        }
        switch (telephonyManager.getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return Integer.toString(telephonyManager.getNetworkType());
        }
        e.printStackTrace();
        return "";
    }
}
