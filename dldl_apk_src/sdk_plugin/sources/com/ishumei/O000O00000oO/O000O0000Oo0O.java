package com.ishumei.O000O00000oO;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import com.youme.voiceengine.YouMeConst;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O0000Oo0O {
    private static O000O0000Oo0O O000O00000oO;
    private Object O0000O000000oO;
    private Context O000O00000OoO;
    private Object O000O00000o0O;

    private O000O0000Oo0O() {
        this.O0000O000000oO = null;
        this.O000O00000OoO = null;
        this.O000O00000o0O = null;
        try {
            Context context = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
            this.O000O00000OoO = context;
            if (context != null) {
                Object objO0000O000000oO = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O000O00000OoO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bac868c8b9a92ac9a8d89969c9a")).O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("88969996"));
                this.O0000O000000oO = objO0000O000000oO;
                if (objO0000O000000oO != null) {
                    this.O000O00000o0O = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O0000O000000oO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bbc9091919a9c8b969091b6919990")).O0000O000000oO(new Object[0]);
                }
            }
        } catch (Exception unused) {
        }
    }

    public static O000O0000Oo0O O0000O000000oO() {
        if (O000O00000oO == null) {
            synchronized (O000O0000Oo0O.class) {
                if (O000O00000oO == null) {
                    O000O00000oO = new O000O0000Oo0O();
                }
            }
        }
        return O000O00000oO;
    }

    public static String O0000O000000oO(int i) {
        if (i == -101) {
            return "wifi";
        }
        switch (i) {
            case -1:
                return "nil";
            case 0:
                return "unknown";
            case 1:
                return "2g.gprs";
            case 2:
                return "2g.edge";
            case 3:
                return "3g.umts";
            case 4:
                return "2g.cdma";
            case 5:
                return "3g.evdo_0";
            case 6:
                return "3g.evdo_a";
            case 7:
                return "2g.1xrtt";
            case 8:
                return "3g.hsdpa";
            case 9:
                return "3g.hsupa";
            case 10:
                return "3g.hspa";
            case 11:
                return "2g.iden";
            case 12:
                return "3g.evdo_b";
            case 13:
                return "4g.lte";
            case 14:
                return "3g.ehrpd";
            case 15:
                return "3g.hspap";
            default:
                return String.format("%d", Integer.valueOf(i));
        }
    }

    private String O00O0000OooO() {
        int networkType = 0;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.O000O00000OoO.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
                int type = activeNetworkInfo.getType();
                if (type == 1) {
                    networkType = YouMeConst.YouMeErrorCode.YOUME_ERROR_START_FAILED;
                } else if (type == 0) {
                    networkType = ((TelephonyManager) this.O000O00000OoO.getSystemService("phone")).getNetworkType();
                }
            } else {
                networkType = -1;
            }
        } catch (Exception e) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e);
        }
        return O0000O000000oO(networkType);
    }

    public String O000O00000OoO() {
        try {
            if (this.O000O00000o0O == null) {
                return "";
            }
            String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O000O00000o0O).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bacacb6bb")).O0000O000000oO();
            return str == null ? "" : str;
        } catch (Exception unused) {
            return "";
        }
    }

    public String O000O00000o0O() {
        try {
            if (this.O000O00000o0O == null) {
                return "";
            }
            String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O000O00000o0O).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bbdacacb6bb")).O0000O000000oO();
            return str == null ? "" : str;
        } catch (Exception unused) {
            return "";
        }
    }

    public String O000O00000oO() {
        try {
            if (this.O000O00000o0O == null) {
                return "";
            }
            String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O000O00000o0O).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bb29e9cbe9b9b8d9a8c8c")).O0000O000000oO();
            return str == null ? "" : str;
        } catch (Exception unused) {
            return "";
        }
    }

    public String O000O0000O0oO() {
        try {
            if (this.O000O00000o0O == null) {
                return "";
            }
            String ipAddress = Formatter.formatIpAddress(((Integer) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O000O00000o0O).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bb68fbe9b9b8d9a8c8c")).O0000O000000oO()).intValue());
            return ipAddress == null ? "" : ipAddress;
        } catch (Exception unused) {
            return "";
        }
    }

    public List<String> O000O0000OOoO() {
        ArrayList arrayList = new ArrayList();
        try {
            int iCheckCallingPermission = this.O000O00000OoO.checkCallingPermission("android.permission.ACCESS_FINE_LOCATION");
            int iCheckCallingPermission2 = this.O000O00000OoO.checkCallingPermission("android.permission.ACCESS_COARSE_LOCATION");
            if ((iCheckCallingPermission == 0 || iCheckCallingPermission2 == 0) && this.O0000O000000oO != null) {
                for (ScanResult scanResult : (List) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O0000O000000oO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bac9c9e91ad9a8c8a938b8c")).O0000O000000oO()) {
                    String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(scanResult).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("bdacacb6bb"));
                    arrayList.add(com.ishumei.O000O0000OOoO.O000O0000OoO.O000O00000oO(str) + "," + ((Integer) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(scanResult).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("939a899a93"))).intValue());
                }
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public String O000O0000Oo0O() {
        try {
            if (this.O000O00000OoO == null) {
                return "";
            }
            String strO00O0000OooO = O00O0000OooO();
            return strO00O0000OooO == null ? "" : strO00O0000OooO;
        } catch (Exception unused) {
            return "";
        }
    }

    public List<String> O000O0000OoO() {
        ArrayList arrayList = new ArrayList();
        try {
            Object objO0000O000000oO = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O000O00000OoO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("959e899ed1919a8bd1b19a8b88908d94b6918b9a8d999e9c9a")).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bb19a8b88908d94b6918b9a8d999e9c9a8c")).O0000O000000oO();
            Method methodO0000O000000oO = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(Enumeration.class).O000O00000OoO().O000O00000OoO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("979e8cb2908d9aba939a929a918b8c")).O0000O000000oO();
            Method methodO0000O000000oO2 = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(Enumeration.class).O000O00000OoO().O000O00000OoO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("919a878bba939a929a918b")).O0000O000000oO();
            while (((Boolean) methodO0000O000000oO.invoke(objO0000O000000oO, new Object[0])).booleanValue()) {
                NetworkInterface networkInterface = (NetworkInterface) methodO0000O000000oO2.invoke(objO0000O000000oO, new Object[0]);
                if (!networkInterface.isLoopback()) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    String str = "";
                    String strO000O00000oO = (hardwareAddress == null || hardwareAddress.length <= 0) ? "" : com.ishumei.O000O0000OOoO.O000O0000OoO.O000O00000oO(com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO(hardwareAddress));
                    if (!strO000O00000oO.isEmpty() && !strO000O00000oO.equals("000000000000")) {
                        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                        String str2 = "";
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress inetAddressNextElement = inetAddresses.nextElement();
                            if (!inetAddressNextElement.isLoopbackAddress()) {
                                String hostAddress = inetAddressNextElement.getHostAddress();
                                if (hostAddress.trim().length() < 17) {
                                    str = hostAddress;
                                } else {
                                    str2 = hostAddress;
                                }
                            }
                        }
                        arrayList.add(networkInterface.getDisplayName() + "," + str + "," + strO000O00000oO + "," + str2);
                    }
                }
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }
}
