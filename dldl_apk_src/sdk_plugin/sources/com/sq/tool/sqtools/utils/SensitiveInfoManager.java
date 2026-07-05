package com.sq.tool.sqtools.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.sqwan.bugless.util.FileUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class SensitiveInfoManager {
    private static volatile SensitiveInfoManager sInstance;
    private String mAndroidId;
    public boolean mAuthCheck = false;
    private String mIMEI;
    private String mIMSI;
    private String mIpAddress;
    private String mMacAddress;
    private String mPhoneNumber;
    private List<String> mSensorList;

    public static SensitiveInfoManager getInstance() {
        if (sInstance == null) {
            synchronized (SensitiveInfoManager.class) {
                if (sInstance == null) {
                    sInstance = new SensitiveInfoManager();
                }
            }
        }
        return sInstance;
    }

    private SensitiveInfoManager() {
    }

    public boolean isAuthCheck() {
        return this.mAuthCheck;
    }

    public void setAuthCheck(boolean z) {
        this.mAuthCheck = z;
    }

    public String getIMEI(Context context) {
        String str = this.mIMEI;
        if (str != null) {
            return str;
        }
        if (Build.VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return "";
        }
        try {
            this.mIMEI = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.mIMEI == null) {
            this.mIMEI = "";
        }
        return this.mIMEI;
    }

    public String getIMSI(Context context) {
        if (!isAuthCheck()) {
            return "";
        }
        String str = this.mIMSI;
        if (str != null) {
            return str;
        }
        if (Build.VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return "";
        }
        try {
            this.mIMSI = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.mIMSI == null) {
            this.mIMSI = "";
        }
        return this.mIMSI;
    }

    public String getPhoneNumber(Context context) {
        if (!isAuthCheck()) {
            return "";
        }
        String str = this.mPhoneNumber;
        if (str != null) {
            return str;
        }
        if (Build.VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return "";
        }
        try {
            this.mPhoneNumber = ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.mPhoneNumber == null) {
            this.mPhoneNumber = "";
        }
        return this.mPhoneNumber;
    }

    public String getMacAddress(Context context) {
        String str = this.mMacAddress;
        if (str != null) {
            return str;
        }
        if (Build.VERSION.SDK_INT < 23) {
            this.mMacAddress = getMacDefault(context);
        } else if (Build.VERSION.SDK_INT < 24) {
            this.mMacAddress = getMacFromFile();
        } else {
            this.mMacAddress = getMacFromHardware();
        }
        return this.mMacAddress;
    }

    private static String getMacDefault(Context context) {
        WifiManager wifiManager;
        if (context == null || (wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi")) == null) {
            return "";
        }
        WifiInfo connectionInfo = null;
        try {
            connectionInfo = wifiManager.getConnectionInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (connectionInfo == null) {
            return "";
        }
        String macAddress = connectionInfo.getMacAddress();
        return !TextUtils.isEmpty(macAddress) ? macAddress.toUpperCase(Locale.ENGLISH) : macAddress;
    }

    private static String getMacFromFile() {
        try {
            return new BufferedReader(new FileReader(new File("/sys/class/net/wlan0/address"))).readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getMacFromHardware() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            if ("wlan0".equalsIgnoreCase(networkInterface.getName())) {
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress == null) {
                    return "";
                }
                StringBuilder sb = new StringBuilder();
                for (byte b : hardwareAddress) {
                    sb.append(String.format("%02X:", Byte.valueOf(b)));
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
            }
            return "";
        }
        return "";
    }

    public String getAndroidId(Context context) {
        String str = this.mAndroidId;
        if (str != null) {
            return str;
        }
        try {
            this.mAndroidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.mAndroidId == null) {
            this.mAndroidId = "";
        }
        return this.mAndroidId;
    }

    public String getIpAddress(Context context) {
        NetworkInfo activeNetworkInfo;
        if (!isAuthCheck()) {
            return "";
        }
        String str = this.mIpAddress;
        if (str != null) {
            return str;
        }
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            int type = activeNetworkInfo.getType();
            if (type == 0) {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                    while (true) {
                        if (inetAddresses.hasMoreElements()) {
                            InetAddress inetAddressNextElement = inetAddresses.nextElement();
                            if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address)) {
                                this.mIpAddress = inetAddressNextElement.getHostAddress();
                                break;
                            }
                        }
                    }
                }
            } else if (type == 1) {
                WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
                if (connectionInfo != null) {
                    this.mIpAddress = intIpToStringIP(connectionInfo.getIpAddress());
                }
            } else if (type == 9) {
                Enumeration<NetworkInterface> networkInterfaces2 = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces2.hasMoreElements()) {
                    Enumeration<InetAddress> inetAddresses2 = networkInterfaces2.nextElement().getInetAddresses();
                    while (inetAddresses2.hasMoreElements()) {
                        InetAddress inetAddressNextElement2 = inetAddresses2.nextElement();
                        if (!inetAddressNextElement2.isLoopbackAddress() && (inetAddressNextElement2 instanceof Inet4Address)) {
                            this.mIpAddress = inetAddressNextElement2.getHostAddress();
                        }
                    }
                }
                if (this.mIpAddress == null) {
                    this.mIpAddress = "0.0.0.0";
                }
            }
            if (this.mIpAddress == null) {
                this.mIpAddress = "";
            }
            return this.mIpAddress;
        }
        return "";
    }

    private static String intIpToStringIP(int i) {
        return (i & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((i >> 8) & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((i >> 16) & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((i >> 24) & 255);
    }

    public List<String> getSensorList(Context context) {
        if (!isAuthCheck()) {
            return new ArrayList();
        }
        List<String> list = this.mSensorList;
        if (list != null) {
            return list;
        }
        this.mSensorList = new ArrayList();
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        if (sensorManager != null) {
            Iterator<Sensor> it = sensorManager.getSensorList(-1).iterator();
            while (it.hasNext()) {
                this.mSensorList.add(it.next().getName());
            }
        }
        return this.mSensorList;
    }
}
