package com.sq.tools.network.httpdns.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.sq.tools.network.httpdns.SqHttpDns;
import com.sq.tools.network.httpdns.log.HttpDnsLog;
import java.io.UnsupportedEncodingException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class HttpDnsUtil {
    private static final Pattern VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    private static final Pattern VERIFY_IPV4 = Pattern.compile("([\\d.]+)");
    private static final Pattern VERIFY_IPV6 = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)");

    private HttpDnsUtil() {
    }

    public static String Md5(String string) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b : bArrDigest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Huh, MD5 should be supported?", e2);
        }
    }

    public static String randomData(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        Random random2 = new Random();
        for (int i = 0; i < length; i++) {
            int iNextInt = random.nextInt(3);
            if (iNextInt == 0) {
                sb.append(random2.nextInt(10));
            } else if (iNextInt == 1) {
                sb.append((char) (random2.nextInt(26) + 65));
            } else if (iNextInt == 2) {
                sb.append((char) (random2.nextInt(26) + 97));
            }
        }
        return sb.toString();
    }

    public static String getHost(String url) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        try {
            return Uri.parse(url).getHost();
        } catch (Exception e) {
            HttpDnsLog.e("Trying to get host from Illegal url " + url, e);
            return "";
        }
    }

    public static String getProtocol(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        try {
            return new URL(url).getProtocol();
        } catch (Exception e) {
            HttpDnsLog.e("Trying to get host from Illegal url %s" + url, e);
            return "";
        }
    }

    public static boolean isIpAddress(String address) {
        if (TextUtils.isEmpty(address)) {
            return false;
        }
        return VERIFY_AS_IP_ADDRESS.matcher(address).matches();
    }

    public static boolean isIpV4Address(String address) {
        if (TextUtils.isEmpty(address)) {
            return false;
        }
        return VERIFY_IPV4.matcher(address).matches();
    }

    public static boolean isIpV6Address(String address) {
        return !TextUtils.isEmpty(address) && VERIFY_IPV6.matcher(address).matches() && address.startsWith("2");
    }

    private static boolean isIpV6Address(InetAddress inetAddress) {
        String hostAddress;
        return (inetAddress instanceof Inet6Address) && (hostAddress = inetAddress.getHostAddress()) != null && hostAddress.startsWith("2");
    }

    public static boolean isSupportIpV6() {
        Map<String, Boolean> userConsent;
        ConnectivityManager connectivityManager;
        Network activeNetwork;
        LinkProperties linkProperties;
        List<LinkAddress> linkAddresses;
        Context context = SqHttpDns.getInstance().getContext();
        if (context != null && (userConsent = SqHttpDns.getInstance().getUserConsent()) != null && !userConsent.isEmpty() && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null && Build.VERSION.SDK_INT >= 23 && (activeNetwork = connectivityManager.getActiveNetwork()) != null && (linkProperties = connectivityManager.getLinkProperties(activeNetwork)) != null && (linkAddresses = linkProperties.getLinkAddresses()) != null && !linkAddresses.isEmpty()) {
            Iterator<LinkAddress> it = linkAddresses.iterator();
            while (it.hasNext()) {
                if (isIpV6Address(it.next().getAddress())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getActiveIp(Context context) {
        ConnectivityManager connectivityManager;
        Network activeNetwork;
        LinkProperties linkProperties;
        List<LinkAddress> linkAddresses;
        Map<String, Boolean> userConsent = SqHttpDns.getInstance().getUserConsent();
        if (userConsent != null && !userConsent.isEmpty() && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null && Build.VERSION.SDK_INT >= 23 && (activeNetwork = connectivityManager.getActiveNetwork()) != null && (linkProperties = connectivityManager.getLinkProperties(activeNetwork)) != null && (linkAddresses = linkProperties.getLinkAddresses()) != null && !linkAddresses.isEmpty()) {
            Iterator<LinkAddress> it = linkAddresses.iterator();
            while (it.hasNext()) {
                InetAddress address = it.next().getAddress();
                if (isIpV6Address(address)) {
                    return address.getHostAddress();
                }
            }
            Iterator<LinkAddress> it2 = linkAddresses.iterator();
            while (it2.hasNext()) {
                String hostAddress = it2.next().getAddress().getHostAddress();
                if (isIpV4Address(hostAddress) && !"0.0.0.0".equals(hostAddress) && !"127.0.0.1".equals(hostAddress)) {
                    return hostAddress;
                }
            }
        }
        return "";
    }
}
