package com.unicom.online.account.kernel;

import android.content.Context;
import android.content.SharedPreferences;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class ac {
    private static final String a = ac.class.getSimpleName();
    private static Boolean b = Boolean.TRUE;

    private static String a() {
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
            return null;
        } catch (SocketException unused) {
            return null;
        }
    }

    public static String a(Context context, String str, String str2) {
        try {
            String strB = b(context, str, str2);
            if (h.a(strB).booleanValue()) {
                return f.a(context, strB);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("cuAuthCacheName", 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        for (String str : sharedPreferences.getAll().keySet()) {
            if (str.startsWith("accessCode")) {
                editorEdit.remove(str);
            }
        }
        editorEdit.commit();
    }

    public static boolean a(Context context, String str, String str2, String str3) {
        if (!h.a(str3).booleanValue()) {
            return false;
        }
        String strB = b(context, str, str2);
        if (h.a(strB).booleanValue()) {
            return f.a(context, strB, str3);
        }
        return false;
    }

    private static String b(Context context, String str, String str2) {
        String strA = e.a(context);
        String strA2 = a();
        if (!h.a(strA2).booleanValue()) {
            return null;
        }
        return "accessCode" + strA + strA2 + str + str2;
    }
}
