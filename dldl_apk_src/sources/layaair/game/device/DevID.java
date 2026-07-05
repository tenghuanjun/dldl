package layaair.game.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class DevID {
    protected Context m_Context;

    public DevID(Context context) {
        this.m_Context = context;
    }

    public static String getIMSI_1() {
        String numberFromParcel = getNumberFromParcel(runCommand("service call iphonesubinfo 3"));
        Log.d("laya", "IMSI_1:" + numberFromParcel);
        return numberFromParcel;
    }

    public static String getIMSI_2() {
        String numberFromParcel = getNumberFromParcel(runCommand("service call iphonesubinfo2 3"));
        Log.d("laya", "IMSI_2:" + numberFromParcel);
        return numberFromParcel;
    }

    @SuppressLint({"NewApi"})
    public static final String getMac() {
        byte[] hardwareAddress;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            hardwareAddress = null;
            while (networkInterfaces.hasMoreElements()) {
                try {
                    NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                    while (true) {
                        if (inetAddresses.hasMoreElements()) {
                            InetAddress inetAddressNextElement = inetAddresses.nextElement();
                            if (!inetAddressNextElement.isAnyLocalAddress() && (inetAddressNextElement instanceof Inet4Address) && !inetAddressNextElement.isLoopbackAddress()) {
                                if (!inetAddressNextElement.isSiteLocalAddress()) {
                                    if (!inetAddressNextElement.isLinkLocalAddress()) {
                                        hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                                        break;
                                    }
                                } else {
                                    hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                                }
                            }
                        }
                    }
                } catch (SocketException e) {
                    e = e;
                    e.printStackTrace();
                }
            }
        } catch (SocketException e2) {
            e = e2;
            hardwareAddress = null;
        }
        if (hardwareAddress == null) {
            return null;
        }
        for (byte b : hardwareAddress) {
            stringBuffer.append(parseByte(b));
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    public static String getNumberFromParcel(String str) {
        String str2 = "";
        if (str == null || str.length() <= 0) {
            return "NA";
        }
        for (String str3 : str.split("\n")) {
            if (str3 != null && str3.length() != 0) {
                String[] strArrSplit = str3.split("'");
                if (strArrSplit.length > 1) {
                    str2 = str2 + strArrSplit[1].replace(".", "");
                }
            }
        }
        return str2;
    }

    private static String parseByte(byte b) {
        return ("00" + Integer.toHexString(b) + ":").substring(r2.length() - 3);
    }

    public static String runCommand(String str) {
        String str2;
        StringBuilder sb;
        String message;
        try {
            Process processExec = Runtime.getRuntime().exec(str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
            char[] cArr = new char[2048];
            StringBuilder sb2 = new StringBuilder();
            while (true) {
                int i = bufferedReader.read(cArr);
                if (i <= 0) {
                    bufferedReader.close();
                    processExec.waitFor();
                    return sb2.toString();
                }
                sb2.append(cArr, 0, i);
            }
        } catch (IOException e) {
            str2 = "apipas";
            sb = new StringBuilder("IOException:");
            message = e.getMessage();
            sb.append(message);
            Log.e(str2, sb.toString());
            return null;
        } catch (InterruptedException e2) {
            str2 = "apipas";
            sb = new StringBuilder("InterruptedException:");
            message = e2.getMessage();
            sb.append(message);
            Log.e(str2, sb.toString());
            return null;
        }
    }

    public String GenARandomID() {
        return "";
    }

    public String GetAnUniqueID() {
        String strGetDevSerial = GetDevSerial();
        if (strGetDevSerial != null && strGetDevSerial.length() > 0) {
            return strGetDevSerial;
        }
        String strGetWifiMac = GetWifiMac();
        if (strGetWifiMac != null && strGetWifiMac.length() > 0) {
            return strGetWifiMac.replace(":", "");
        }
        String strGetAndroidID = GetAndroidID();
        return (strGetAndroidID == null || strGetAndroidID.length() <= 0) ? GetMyUniqueID() : strGetAndroidID;
    }

    public String GetAndroidID() {
        return Settings.Secure.getString(this.m_Context.getContentResolver(), "android_id");
    }

    public String GetDevSerial() {
        return "";
    }

    public String GetIMEI() {
        String str;
        String string;
        TelephonyManager telephonyManager = (TelephonyManager) this.m_Context.getSystemService("phone");
        if (telephonyManager == null) {
            return "UnKnow";
        }
        try {
            return telephonyManager.getDeviceId();
        } catch (Exception e) {
            str = "LayaBox";
            string = e.toString();
            Log.e(str, string);
            return "UnKnow";
        } catch (Throwable th) {
            str = "LayaBox";
            string = th.toString();
            Log.e(str, string);
            return "UnKnow";
        }
    }

    public String GetIMSI() {
        TelephonyManager telephonyManager = (TelephonyManager) this.m_Context.getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        try {
            return telephonyManager.getSubscriberId();
        } catch (SecurityException e) {
            Log.e("LayaBox", e.toString());
            return null;
        }
    }

    public String GetMyUniqueID() {
        return "UNKNOWN";
    }

    public String GetOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public String GetPhoneModelAndSDK() {
        return Build.MODEL;
    }

    public int[] GetResolutionArray() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.m_Context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels, displayMetrics.densityDpi};
    }

    public String GetWifiMac() {
        return getMac();
    }
}
