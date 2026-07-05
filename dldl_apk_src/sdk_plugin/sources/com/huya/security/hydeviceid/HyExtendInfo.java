package com.huya.security.hydeviceid;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Process;
import android.telephony.TelephonyManager;
import com.j256.ormlite.field.FieldType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyExtendInfo {
    static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static String getICCID(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (context.checkPermission("android.permission.READ_PHONE_STATE", Process.myPid(), Process.myUid()) != 0 || Build.VERSION.SDK_INT >= 29) {
                return "";
            }
            String simSerialNumber = telephonyManager.getSimSerialNumber();
            return simSerialNumber == null ? "" : simSerialNumber;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getTelphoneNumber(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (context.checkPermission("android.permission.READ_PHONE_STATE", Process.myPid(), Process.myUid()) != 0) {
                return "";
            }
            String line1Number = telephonyManager.getLine1Number();
            return line1Number == null ? "" : line1Number;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getSimOperatorName(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (context.checkPermission("android.permission.READ_PHONE_STATE", Process.myPid(), Process.myUid()) != 0) {
                return "";
            }
            String simOperatorName = telephonyManager.getSimOperatorName();
            return simOperatorName == null ? "" : simOperatorName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getNetworkOperatorName(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (context.checkPermission("android.permission.READ_PHONE_STATE", Process.myPid(), Process.myUid()) != 0) {
                return "";
            }
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            return networkOperatorName == null ? "" : networkOperatorName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getVoiceMailNumber(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (context.checkPermission("android.permission.READ_PHONE_STATE", Process.myPid(), Process.myUid()) != 0) {
                return "";
            }
            String voiceMailNumber = telephonyManager.getVoiceMailNumber();
            return voiceMailNumber == null ? "" : voiceMailNumber;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getMultiCardInfo(Context context) {
        try {
            StringBuilder sb = new StringBuilder();
            Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://telephony/siminfo"), new String[]{FieldType.FOREIGN_ID_FIELD_SUFFIX, "sim_id", "icc_id", "display_name"}, "0=0", new String[0], null);
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex("icc_id"));
                    String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("display_name"));
                    cursorQuery.getInt(cursorQuery.getColumnIndex("sim_id"));
                    cursorQuery.getInt(cursorQuery.getColumnIndex(FieldType.FOREIGN_ID_FIELD_SUFFIX));
                    sb.append(string);
                    sb.append(",");
                    sb.append(string2);
                    if (!cursorQuery.isLast()) {
                        sb.append(",");
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.getStackTrace();
            return "";
        }
    }

    public static Object getWifiList(Context context) {
        List<ScanResult> scanResults = ((WifiManager) context.getSystemService("wifi")).getScanResults();
        ArrayList arrayList = new ArrayList();
        if (scanResults != null && scanResults.size() > 0) {
            HashMap map = new HashMap();
            for (int i = 0; i < scanResults.size(); i++) {
                ScanResult scanResult = scanResults.get(i);
                if (!scanResult.SSID.isEmpty()) {
                    String str = scanResult.SSID + " " + scanResult.capabilities;
                    if (!map.containsKey(str)) {
                        map.put(str, Integer.valueOf(i));
                        HashMap map2 = new HashMap();
                        map2.put("BSSID", scanResult.BSSID);
                        map2.put("SSID", scanResult.SSID);
                        map2.put("capabilities", scanResult.capabilities);
                        if (Build.VERSION.SDK_INT >= 23) {
                            map2.put("centerFreq0", String.valueOf(scanResult.centerFreq0));
                            map2.put("centerFreq1", String.valueOf(scanResult.centerFreq1));
                            map2.put("channelWidth", String.valueOf(scanResult.channelWidth));
                            if (scanResult.operatorFriendlyName != null) {
                                map2.put("operatorFriendlyName", scanResult.operatorFriendlyName.toString());
                            }
                        }
                        map2.put("frequency", String.valueOf(scanResult.frequency));
                        map2.put("level", String.valueOf(scanResult.level));
                        arrayList.add(map2);
                    }
                }
            }
        }
        return arrayList;
    }
}
