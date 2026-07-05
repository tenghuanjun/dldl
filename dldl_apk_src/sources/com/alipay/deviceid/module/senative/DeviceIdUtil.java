package com.alipay.deviceid.module.senative;

import android.content.Context;
import com.alipay.deviceid.module.x.br;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class DeviceIdUtil {
    private static DeviceIdUtil _instance;
    private static boolean isLoad;
    private Context mContext = null;
    private int netType = -1;

    static {
        try {
            System.loadLibrary("deviceid_607");
            isLoad = true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private DeviceIdUtil() {
    }

    public static synchronized DeviceIdUtil getInstance(Context context) {
        if (_instance == null) {
            DeviceIdUtil deviceIdUtil = new DeviceIdUtil();
            _instance = deviceIdUtil;
            deviceIdUtil.loadSo(context);
            _instance.mContext = context;
        }
        return _instance;
    }

    private native String getMappedIpAddressNative(String str, String str2, int i);

    private String getPhoneIp() {
        String str;
        String string;
        String str2 = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            str = null;
            while (networkInterfaces.hasMoreElements()) {
                try {
                    NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                    String name = networkInterfaceNextElement.getName();
                    if (name != null && networkInterfaceNextElement.isUp() && !name.startsWith("ppp") && !name.startsWith("p2p") && !name.startsWith("lo")) {
                        Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress inetAddressNextElement = inetAddresses.nextElement();
                            if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address) && (string = inetAddressNextElement.getHostAddress().toString()) != null && string.length() > 0) {
                                if (name != null && name.startsWith("rmnet")) {
                                    str = string;
                                } else if (name != null && name.startsWith("wlan")) {
                                    str2 = string;
                                }
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            str = null;
        }
        if (str2 != null && str2.length() > 0) {
            this.netType = 2;
            return str2;
        }
        if (str == null || str.length() <= 0) {
            return "";
        }
        this.netType = 1;
        return str;
    }

    private native int init(Object obj);

    private void loadSo(Context context) {
        try {
            if (isLoad) {
                return;
            }
            br brVar = new br(context);
            br.a = "607";
            brVar.a("deviceid");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public native String getErrorCode();

    public String getRealIpAddress(String str, int i) {
        String phoneIp;
        try {
            phoneIp = getPhoneIp();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (str != null && str.length() != 0) {
            if (phoneIp != null && phoneIp.length() > 0 && this.netType == 1) {
                return getMappedIpAddressNative(phoneIp, str, i);
            }
            if (phoneIp != null && phoneIp.length() > 0 && this.netType == 2) {
                return getMappedIpAddressNative("0.0.0.0", str, i);
            }
            return "";
        }
        return "";
    }

    public native int getVersion();

    public int initialize() {
        try {
            return init(this.mContext);
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public byte[] packageDevideData(byte[] bArr) {
        try {
            return zipAndEncryptData(this.mContext, bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    public native byte[] zipAndEncryptData(Object obj, byte[] bArr);
}
