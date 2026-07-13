package com.bytedance.dns;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes2.dex */
public final class b {
    private static long a(String str) {
        String[] strArrSplit = str.split("\\.");
        return (((long) Integer.parseInt(strArrSplit[0])) << 24) + (((long) Integer.parseInt(strArrSplit[1])) << 16) + (((long) Integer.parseInt(strArrSplit[2])) << 8) + ((long) Integer.parseInt(strArrSplit[3]));
    }

    public static String a() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement.isUp() && !networkInterfaceNextElement.isVirtual() && !networkInterfaceNextElement.isLoopback()) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        if (inetAddressNextElement instanceof Inet4Address) {
                            return inetAddressNextElement.getHostAddress();
                        }
                    }
                }
            }
            return "0.0.0.0";
        } catch (Exception e) {
            a.c(e.getMessage());
            return "0.0.0.0";
        }
    }

    private static boolean a(long j, long j2, long j3) {
        return j >= j2 && j <= j3;
    }

    public static boolean a(InetAddress inetAddress) {
        long jA = a(inetAddress.getHostAddress());
        return a(jA, a("10.0.0.0"), a("10.255.255.255")) || a(jA, a("172.16.0.0"), a("172.31.255.255")) || a(jA, a("192.168.0.0"), a("192.168.255.255")) || a(jA, a("127.0.0.0"), a("127.255.255.255"));
    }
}
