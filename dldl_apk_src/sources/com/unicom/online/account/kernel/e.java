package com.unicom.online.account.kernel;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.HashSet;
import kotlin.UByte;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class e {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static int b = 1;
    private static int c = 0;

    public static String a() {
        return d.d();
    }

    public static String a(int i) {
        return c.a(i);
    }

    public static String a(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
    }

    public static String a(Context context, String str) {
        try {
            return b(b(context, str));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(Context context, String str, String str2) {
        try {
            return a(b(context, str), str2.toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < bArrDigest.length; i++) {
                int i2 = bArrDigest[i];
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(String str, String str2, String str3) {
        return p.b(str, str2, str3);
    }

    private static String a(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            if (i != 0) {
                str = str + ":";
            }
            String hexString = Integer.toHexString(bArr[i] & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                str = str + "0";
            }
            str = str + hexString;
        }
        return str;
    }

    private static String a(byte[] bArr, String str) {
        try {
            return a(MessageDigest.getInstance(str).digest(bArr));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static boolean a(ConnectivityManager connectivityManager) {
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static int b(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            c.b("android Build.VERSION:" + Build.VERSION.SDK_INT);
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            if (activeNetworkInfo.getType() == 1) {
                if (a(connectivityManager)) {
                    c.b("Data and WIFI");
                    return 1;
                }
                c.b("Only WIFI");
                return 2;
            }
            if (activeNetworkInfo.getType() == 0) {
                c.b("Only Data");
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (TextUtils.isEmpty(extraInfo)) {
                    return 0;
                }
                d.d(extraInfo);
                d.a(d.e(extraInfo));
                return 0;
            }
            return -1;
        }
        return -1;
    }

    public static String b(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < bArrDigest.length; i++) {
                int i2 = bArrDigest[i];
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String b(String str, String str2, String str3) {
        return p.a(str, str2, str3);
    }

    private static String b(byte[] bArr) {
        try {
            SM3Digest sM3Digest = new SM3Digest();
            sM3Digest.update(bArr, 0, bArr.length);
            byte[] bArr2 = new byte[sM3Digest.getDigestSize()];
            sM3Digest.doFinal(bArr2, 0);
            return a(bArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void b() {
        c.a();
    }

    private static byte[] b(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo.packageName.equals(str)) {
                return packageInfo.signatures[0].toByteArray();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int c() {
        c = 0;
        b = 1;
        return 1;
    }

    public static String c(Context context) {
        try {
            return (String) context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String c(String str) {
        SM3Digest sM3Digest = new SM3Digest();
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        sM3Digest.update(bytes, 0, bytes.length);
        byte[] bArr = new byte[sM3Digest.getDigestSize()];
        sM3Digest.doFinal(bArr, 0);
        return u.a(bArr);
    }

    public static int d() {
        return b;
    }

    public static int d(String str) {
        try {
            byte[] address = InetAddress.getByName(str).getAddress();
            return (address[0] & UByte.MAX_VALUE) | ((address[3] & UByte.MAX_VALUE) << 24) | ((address[2] & UByte.MAX_VALUE) << 16) | ((address[1] & UByte.MAX_VALUE) << 8);
        } catch (UnknownHostException unused) {
            return -1;
        }
    }

    public static int e() {
        return c;
    }

    public static void e(String str) {
        c.a(0, str);
    }

    public static int f() {
        int i = c;
        if (i < 0 || i > b) {
            return b;
        }
        int i2 = i + 1;
        c = i2;
        return i2;
    }

    public static String g() {
        HashSet hashSet;
        HashSet hashSet2;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Enumeration<NetworkInterface> networkInterfaces;
        int i;
        if (d.c != null) {
            l lVar = d.c;
            return l.a();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            hashSet = new HashSet();
            hashSet2 = new HashSet();
            sb = new StringBuilder();
            sb2 = new StringBuilder();
            sb3 = new StringBuilder();
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (Exception unused) {
        }
        while (true) {
            if (!networkInterfaces.hasMoreElements()) {
                break;
            }
            NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
            if (!networkInterfaceNextElement.isVirtual() && networkInterfaceNextElement.isUp()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && !inetAddressNextElement.isLinkLocalAddress() && !inetAddressNextElement.isMulticastAddress() && !inetAddressNextElement.isAnyLocalAddress()) {
                        if (inetAddressNextElement instanceof Inet4Address) {
                            hashSet.add(inetAddressNextElement.getHostAddress());
                        }
                        if (inetAddressNextElement instanceof Inet6Address) {
                            String hostAddress = inetAddressNextElement.getHostAddress();
                            if (hostAddress.contains("%")) {
                                hostAddress = hostAddress.substring(0, hostAddress.indexOf("%"));
                            }
                            hashSet2.add(hostAddress);
                        }
                    }
                }
            }
            return "{\"privateIp\":\"0.0.0.0\"}";
        }
        if (hashSet.size() > 0) {
            Object[] array = hashSet.toArray();
            int iMin = Math.min(array.length, 5);
            for (int i2 = 0; i2 < iMin; i2++) {
                sb.append((String) array[i2]);
                if (i2 < iMin - 1) {
                    sb.append("-");
                }
            }
            sb3.append("&private_ip=".concat(String.valueOf(sb)));
            jSONObject.put("privateIp", sb.toString());
        }
        if (hashSet2.size() > 0) {
            Object[] array2 = hashSet2.toArray();
            int iMin2 = Math.min(array2.length, 5);
            for (i = 0; i < iMin2; i++) {
                sb2.append((String) array2[i]);
                if (i < iMin2 - 1) {
                    sb2.append("-");
                }
            }
            sb3.append("&private_ip_v6=".concat(String.valueOf(sb2)));
            jSONObject.put("privateIp_v6", sb2.toString());
        }
        if (sb.length() != 0) {
            return jSONObject.toString();
        }
        return "{\"privateIp\":\"0.0.0.0\"}";
    }
}
