package com.huya.security.hydeviceid;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyDeviceUtil {
    public static int nH;
    public static int nW;

    private interface CarrierType {
        public static final int NET_CLOSE = 1;
        public static final int NET_LAN = 3;
        public static final int NET_MOBILE_2G = 8;
        public static final int NET_MOBILE_3G = 9;
        public static final int NET_MOBILE_4G = 10;
        public static final int NET_TELECOM_2G = 11;
        public static final int NET_TELECOM_3G = 12;
        public static final int NET_TELECOM_4G = 13;
        public static final int NET_UNION_2G = 5;
        public static final int NET_UNION_3G = 6;
        public static final int NET_UNION_4G = 7;
        public static final int NET_UNKNOW = 2;
        public static final int NET_WIFI = 4;
    }

    public static String getMacFromHardware() {
        return "02:00:00:00:00:00";
    }

    public static long getTotalMemory(Context context) {
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            return memoryInfo.totalMem / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        } catch (Exception | NoSuchFieldError unused) {
            return 0L;
        }
    }

    public static long getInternalToatalSpace(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSizeLong = Build.VERSION.SDK_INT >= 18 ? statFs.getBlockSizeLong() : 0L;
        long blockCountLong = Build.VERSION.SDK_INT >= 18 ? statFs.getBlockCountLong() : 0L;
        if (Build.VERSION.SDK_INT >= 18) {
            statFs.getAvailableBlocksLong();
        }
        return (blockCountLong * blockSizeLong) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static long getAvailableInternalToatalSpace(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSizeLong = Build.VERSION.SDK_INT >= 18 ? statFs.getBlockSizeLong() : 0L;
        if (Build.VERSION.SDK_INT >= 18) {
            statFs.getBlockCountLong();
        }
        return ((Build.VERSION.SDK_INT >= 18 ? statFs.getAvailableBlocksLong() : 0L) * blockSizeLong) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static long getExternalMemorySize(Context context) {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return 0L;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((Build.VERSION.SDK_INT >= 18 ? statFs.getBlockCountLong() : 0L) * (Build.VERSION.SDK_INT >= 18 ? statFs.getBlockSizeLong() : 0L)) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static long getAvailableExternalMemorySize(Context context) {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return 0L;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((Build.VERSION.SDK_INT >= 18 ? statFs.getAvailableBlocksLong() : 0L) * (Build.VERSION.SDK_INT >= 18 ? statFs.getBlockSizeLong() : 0L)) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static String getScreen(Context context) {
        try {
            if (nW == 0 && nH == 0) {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                nW = displayMetrics.widthPixels;
                nH = displayMetrics.heightPixels;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "W:" + nW + "; H:" + nH;
    }

    public static String getIMSI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (context.checkPermission("android.permission.READ_PHONE_STATE", Process.myPid(), Process.myUid()) != 0) {
                return "";
            }
            String subscriberId = telephonyManager.getSubscriberId();
            return subscriberId == null ? "" : subscriberId;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getNetInfo(Context context) {
        HyDeviceNetInfo hyDeviceNetInfo = new HyDeviceNetInfo();
        hyDeviceNetInfo.setNetMap(getMacNet());
        hyDeviceNetInfo.setNetCarry(getCarrierType(context));
        HyExtendInfo.init(context);
        hyDeviceNetInfo.setIccid(HyExtendInfo.getICCID(context));
        hyDeviceNetInfo.setSimOperatorName(HyExtendInfo.getSimOperatorName(context));
        hyDeviceNetInfo.setNetworkOperatorName(HyExtendInfo.getNetworkOperatorName(context));
        hyDeviceNetInfo.setVoiceMailNumber(HyExtendInfo.getVoiceMailNumber(context));
        hyDeviceNetInfo.setTelephoneNumber(HyExtendInfo.getTelphoneNumber(context));
        hyDeviceNetInfo.setMultiCardInfo(HyExtendInfo.getMultiCardInfo(context));
        hyDeviceNetInfo.setWifiList(JSON.toJSONString(HyExtendInfo.getWifiList(context)));
        return JSON.toJSONString(hyDeviceNetInfo);
    }

    private static Map<String, String> getMacNet() {
        return new HashMap();
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            if (!activeNetworkInfo.isConnected()) {
                if (!activeNetworkInfo.isAvailable()) {
                    return false;
                }
                if (!activeNetworkInfo.isConnectedOrConnecting()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int getCarrierType(Context context) {
        if (context == null) {
            return 2;
        }
        if (!isNetworkAvailable(context)) {
            return 1;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager != null && wifiManager.getWifiState() == 3) {
            return 4;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager.getActiveNetworkInfo().getType() == 9) {
            return 3;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String simOperator = telephonyManager.getSimOperator();
        if (telephonyManager.getSimState() == 5) {
            if (simOperator.equals("46000") || simOperator.equals("46002") || simOperator.equals("46007") || simOperator.equals("46020")) {
                int netSpeedType = getNetSpeedType(connectivityManager);
                if (netSpeedType == 1) {
                    return 8;
                }
                if (netSpeedType == 2) {
                    return 9;
                }
                if (netSpeedType == 3) {
                    return 10;
                }
            } else {
                if (!simOperator.equals("46001") && !simOperator.equals("46006")) {
                    if (simOperator.equals("46003") || simOperator.equals("46005")) {
                        int netSpeedType2 = getNetSpeedType(connectivityManager);
                        if (netSpeedType2 == 1) {
                            return 11;
                        }
                        if (netSpeedType2 == 2) {
                            return 12;
                        }
                        return netSpeedType2 == 3 ? 13 : 2;
                    }
                }
                int netSpeedType3 = getNetSpeedType(connectivityManager);
                if (netSpeedType3 == 1) {
                    return 5;
                }
                if (netSpeedType3 == 2) {
                    return 6;
                }
                return netSpeedType3 == 3 ? 7 : 2;
            }
        }
        return 2;
    }

    public static int getNetSpeedType(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetworkInfo;
        NetworkInfo networkInfo;
        try {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            networkInfo = connectivityManager.getNetworkInfo(0);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        if (networkInfo == null) {
            return 0;
        }
        NetworkInfo.State state = networkInfo.getState();
        String subtypeName = networkInfo.getSubtypeName();
        if (state == null) {
            return 0;
        }
        if (state != NetworkInfo.State.CONNECTED && state != NetworkInfo.State.CONNECTING) {
            return 0;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 1;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return 2;
            case 13:
                return 3;
            default:
                if (!subtypeName.equalsIgnoreCase("TD-SCDMA") && !subtypeName.equalsIgnoreCase("WCDMA")) {
                    if (!subtypeName.equalsIgnoreCase("CDMA2000")) {
                        return 0;
                    }
                }
                return 2;
        }
        e.printStackTrace();
        return 0;
    }

    public static String getSensor(Context context) {
        SensorEvent.getInstance().init(context);
        return SensorEvent.getInstance().getRotation();
    }
}
