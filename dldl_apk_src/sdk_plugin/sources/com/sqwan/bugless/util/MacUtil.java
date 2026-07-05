package com.sqwan.bugless.util;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MacUtil {
    public static String getMac(Context context) {
        return "";
    }

    public static String getLocalMacAddressFromWifiInfo(Context context) {
        return ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }

    public static String getMacAddress(Context context) {
        String strTrim;
        LineNumberReader lineNumberReader;
        String line;
        if (Build.VERSION.SDK_INT < 23) {
            String macAddress0 = getMacAddress0(context);
            if (!TextUtils.isEmpty(macAddress0)) {
                return macAddress0;
            }
        }
        try {
            lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address").getInputStream()));
            line = "";
        } catch (Exception e) {
            LogUtil.i("NetInfoManager:getMacAddress:" + e.toString());
        }
        while (line != null) {
            line = lineNumberReader.readLine();
            if (line != null) {
                strTrim = line.trim();
                break;
            }
            strTrim = "";
        }
        strTrim = "";
        if ("".equals(strTrim)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
            } catch (Exception e2) {
                e2.printStackTrace();
                LogUtil.i("NetInfoManagergetMacAddress:" + e2.toString());
            }
        }
        return strTrim;
    }

    private static String getMacAddress0(Context context) {
        if (!isAccessWifiStateAuthorized(context)) {
            return "";
        }
        try {
            return ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            LogUtil.i("NetInfoManagergetMacAddress0:" + e.toString());
            return "";
        }
    }

    private static boolean isAccessWifiStateAuthorized(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
            return false;
        }
        LogUtil.i("NetInfoManagerisAccessWifiStateAuthorized:access wifi state is enabled");
        return true;
    }

    private static String loadFileAsString(String fileName) throws Exception {
        FileReader fileReader = new FileReader(fileName);
        String strLoadReaderAsString = loadReaderAsString(fileReader);
        fileReader.close();
        return strLoadReaderAsString;
    }

    private static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[4096];
        int i = reader.read(cArr);
        while (i >= 0) {
            sb.append(cArr, 0, i);
            i = reader.read(cArr);
        }
        return sb.toString();
    }

    public static String getMacAddress() {
        try {
            byte[] hardwareAddress = NetworkInterface.getByInetAddress(getLocalInetAddress()).getHardwareAddress();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < hardwareAddress.length; i++) {
                if (i != 0) {
                    stringBuffer.append(AbstractJsonLexerKt.COLON);
                }
                String hexString = Integer.toHexString(hardwareAddress[i] & 255);
                if (hexString.length() == 1) {
                    hexString = 0 + hexString;
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Exception unused) {
            return null;
        }
    }

    private static InetAddress getLocalInetAddress() {
        InetAddress inetAddress;
        SocketException e;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            inetAddress = null;
            do {
                try {
                    if (!networkInterfaces.hasMoreElements()) {
                        break;
                    }
                    Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                    while (true) {
                        if (!inetAddresses.hasMoreElements()) {
                            break;
                        }
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        try {
                            if (!inetAddressNextElement.isLoopbackAddress() && !inetAddressNextElement.getHostAddress().contains(":")) {
                                inetAddress = inetAddressNextElement;
                                break;
                            }
                            inetAddress = null;
                        } catch (SocketException e2) {
                            e = e2;
                            inetAddress = inetAddressNextElement;
                            e.printStackTrace();
                        }
                    }
                } catch (SocketException e3) {
                    e = e3;
                }
            } while (inetAddress == null);
        } catch (SocketException e4) {
            inetAddress = null;
            e = e4;
        }
        return inetAddress;
    }

    private static String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress()) {
                        return inetAddressNextElement.getHostAddress().toString();
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMachineHardwareAddress() {
        Enumeration<NetworkInterface> networkInterfaces;
        String strBytesToString = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
            networkInterfaces = null;
        }
        if (networkInterfaces == null) {
            return null;
        }
        while (networkInterfaces.hasMoreElements()) {
            try {
                strBytesToString = bytesToString(networkInterfaces.nextElement().getHardwareAddress());
            } catch (SocketException e2) {
                e2.printStackTrace();
            }
            if (strBytesToString != null) {
                break;
            }
        }
        return strBytesToString;
    }

    private static String bytesToString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X:", Byte.valueOf(b)));
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String getLocalMacAddressFromBusybox() {
        String strCallCmd = callCmd("busybox ifconfig", "HWaddr");
        return strCallCmd == null ? "网络异常" : (strCallCmd.length() <= 0 || !strCallCmd.contains("HWaddr")) ? strCallCmd : strCallCmd.substring(strCallCmd.indexOf("HWaddr") + 6, strCallCmd.length() - 1);
    }

    private static String callCmd(String cmd, String filter) {
        String str = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(cmd).getInputStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null || line.contains(filter)) {
                    return line;
                }
                str = str + line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
}
