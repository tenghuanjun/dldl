package com.sq.tools.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sq.tools.Logger;
import com.sq.tools.manager.SensitiveInfoManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class SoftwareUtils {
    private static final Pattern PATTERN_IS_IP_ADDRESS = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

    public static String getADSdkVersion() {
        return "";
    }

    public static String getGWVersion() {
        return "";
    }

    public static String getSQSdkVersion() {
        return "";
    }

    public static int getAndroidVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getOsVersion() {
        return TextUtils.isEmpty(Build.VERSION.RELEASE) ? "" : Build.VERSION.RELEASE;
    }

    public static String getCountry(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null || TextUtils.isEmpty(telephonyManager.getNetworkCountryIso())) {
            return context.getResources().getConfiguration().locale.getCountry().toUpperCase();
        }
        return telephonyManager.getNetworkCountryIso().toUpperCase();
    }

    public static String getIpAddress(Context context) {
        return SensitiveInfoManager.getInstance().getIpAddress(context);
    }

    public static String getStackTrace(Throwable th) {
        return getStackTrace(th, 2097152, "");
    }

    private static String getStackTrace(Throwable th, int i, String str) {
        if (i <= 0 || th == null) {
            return "";
        }
        if (th instanceof UnknownHostException) {
            return deduplicate(th.toString());
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder();
        String strDeduplicate = deduplicate(th.toString());
        if (strDeduplicate.length() < i) {
            sb.append(strDeduplicate);
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (sb.length() >= i) {
                break;
            }
            if (stackTraceElement != null) {
                if (sb.toString().contains(stackTraceElement.toString())) {
                    int iIndexOf = sb.toString().indexOf(stackTraceElement.toString());
                    int length = stackTraceElement.toString().length() + iIndexOf;
                    if (sb.toString().contains(stackTraceElement.toString() + " *")) {
                        int i2 = length + 2;
                        int i3 = i2;
                        while (Character.isDigit(sb.charAt(i3))) {
                            i3++;
                        }
                        if (i3 != i2) {
                            sb.replace(iIndexOf, i3, stackTraceElement.toString() + " *" + (Integer.parseInt(sb.substring(i2, i3)) + 1));
                        }
                    } else {
                        sb.replace(iIndexOf, length, stackTraceElement.toString() + " *2");
                    }
                } else if (!str.contains(stackTraceElement.toString())) {
                    sb.append("\tat ");
                    sb.append(stackTraceElement.toString());
                    sb.append(ShellAdbUtils.COMMAND_LINE_END);
                }
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            for (Throwable th2 : th.getSuppressed()) {
                sb.append(getStackTrace(th2, i - sb.length(), sb.toString() + str));
            }
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            sb.append("Caused by: ");
            sb.append(getStackTrace(cause, i - sb.length(), sb.toString() + str));
        }
        return sb.toString();
    }

    public static String deduplicate(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (String str2 : str.split(ShellAdbUtils.COMMAND_LINE_END)) {
            if (!sb.toString().contains(str2)) {
                sb.append(str2);
                sb.append(ShellAdbUtils.COMMAND_LINE_END);
            } else if (!z) {
                sb.append("CLEAN DUPLICATE INFO");
                sb.append(ShellAdbUtils.COMMAND_LINE_END);
                z = true;
            }
        }
        return sb.toString();
    }

    public static String getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo;
        TelephonyManager telephonyManager;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return "unknown";
            }
            if (activeNetworkInfo.getType() == 1) {
                return "wifi";
            }
            if (activeNetworkInfo.getType() != 0 || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
                return "unknown";
            }
            switch (telephonyManager.getNetworkType()) {
                case 0:
                    break;
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                case 16:
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
                case 17:
                    break;
                case 13:
                    break;
                case 18:
                    break;
                default:
                    String subtypeName = activeNetworkInfo.getSubtypeName();
                    if (!subtypeName.equalsIgnoreCase("TD-SCDMA") && !subtypeName.equalsIgnoreCase("WCDMA")) {
                        if (subtypeName.equalsIgnoreCase("CDMA2000")) {
                        }
                    }
                    break;
            }
            return "unknown";
        } catch (SecurityException e) {
            Logger.warning("ACCESS_NETWORK_STATE permission may miss or it's some weird device", e);
        } catch (Exception e2) {
            Logger.warning("Get network info in weird device", e2);
        }
        return "unknown";
    }

    public static long getBootTime() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public static long getBuildTime() {
        return Build.TIME;
    }

    public static int getVersionCode(Context context) {
        int longVersionCode;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                longVersionCode = (int) context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).getLongVersionCode();
            } else {
                longVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).versionCode;
            }
            return longVersionCode;
        } catch (Throwable th) {
            Logger.warning("Exception happen when trying to get version code", th);
            return -1;
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).versionName;
        } catch (Throwable th) {
            Logger.warning("Exception happen when trying to get version name", th);
            return "-1";
        }
    }

    public static String getApkName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int i = applicationInfo.labelRes;
        return i == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(i);
    }

    public static int getTargetSdkVersion(Context context) {
        return context.getApplicationContext().getApplicationInfo().targetSdkVersion;
    }

    public static boolean isDebugBuild(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static String getHost(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return new URI(str).getHost();
        } catch (URISyntaxException e) {
            Logger.error("Trying to get host from Illegal url %s", str, e);
            return "";
        }
    }

    public static String getProtocol(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new URL(str).getProtocol();
        } catch (Exception e) {
            Logger.error("Trying to get host from Illegal url %s", str, e);
            return "";
        }
    }

    public static boolean isIpv4Url(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String host = getHost(str);
        if (!TextUtils.isEmpty(host)) {
            str = host;
        }
        String[] strArrSplit = str.split("\\.", -1);
        if (strArrSplit.length != 4) {
            return false;
        }
        try {
            for (String str2 : strArrSplit) {
                int i = Integer.parseInt(str2);
                if (i < 0 || i > 255 || !str2.equals(String.valueOf(i))) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isIpAddress(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return PATTERN_IS_IP_ADDRESS.matcher(str).matches();
    }

    private SoftwareUtils() {
    }
}
