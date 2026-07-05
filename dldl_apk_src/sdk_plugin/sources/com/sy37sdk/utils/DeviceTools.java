package com.sy37sdk.utils;

import android.content.Context;
import com.sqwan.common.util.EnvironmentUtils;
import com.sy37sdk.bean.DeviceInfo;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class DeviceTools {
    private static final String DEVICEDIR = "systemgameinfo";
    private static final String DEVICEFILE = "devinfo.txt";

    private static String getDir(Context context) {
        File file = new File(getSDPath(context) + "/" + DEVICEDIR);
        if (!file.exists()) {
            file.mkdir();
        }
        return getSDPath(context) + "/" + DEVICEDIR + "/";
    }

    private static File getDeviceFile(Context context) {
        try {
            File file = new File(getDir(context) + "/" + DEVICEFILE);
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (Exception e) {
            System.err.println("无SDCard，获取AF失败");
            e.printStackTrace();
            return new File("");
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|2|(3:45|3|4)|(7:48|5|(1:7)(1:51)|41|9|21|(1:23)(5:24|46|25|(3:27|(3:30|31|28)|52)|34))|8|41|9|21|(0)(0)|(1:(0))) */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0087: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:36:0x0087 */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0044 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.sy37sdk.bean.DeviceInfo> getDeviceFromFile(android.content.Context r5) throws java.lang.Throwable {
        /*
            java.lang.String r0 = ""
            java.io.File r5 = getDeviceFile(r5)
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            r5 = r0
        L12:
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L86
            if (r3 == 0) goto L28
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L86
            r4.<init>()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L86
            r4.append(r5)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L86
            r4.append(r3)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L86
            java.lang.String r5 = r4.toString()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L86
            goto L12
        L28:
            r2.close()     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> L86
        L2b:
            r2.close()     // Catch: java.lang.Exception -> L2f
            goto L3e
        L2f:
            goto L3e
        L31:
            r3 = move-exception
            goto L38
        L33:
            r5 = move-exception
            goto L88
        L35:
            r3 = move-exception
            r5 = r0
            r2 = r1
        L38:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L86
            if (r2 == 0) goto L3e
            goto L2b
        L3e:
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L45
            return r1
        L45:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r5 = com.sy37sdk.utils.ZipString.zipString2Json(r5)     // Catch: java.lang.Exception -> L81
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Exception -> L81
            r1.<init>(r5)     // Catch: java.lang.Exception -> L81
            int r5 = r1.length()     // Catch: java.lang.Exception -> L81
            if (r5 <= 0) goto L85
            r5 = 0
        L5a:
            int r2 = r1.length()     // Catch: java.lang.Exception -> L81
            if (r5 >= r2) goto L85
            org.json.JSONObject r2 = r1.getJSONObject(r5)     // Catch: java.lang.Exception -> L81
            com.sy37sdk.bean.DeviceInfo r3 = new com.sy37sdk.bean.DeviceInfo     // Catch: java.lang.Exception -> L81
            r3.<init>()     // Catch: java.lang.Exception -> L81
            java.lang.String r4 = "dev_mac"
            java.lang.String r4 = r2.optString(r4)     // Catch: java.lang.Exception -> L81
            r3.setDevMac(r4)     // Catch: java.lang.Exception -> L81
            java.lang.String r4 = "dev_imei"
            java.lang.String r2 = r2.optString(r4)     // Catch: java.lang.Exception -> L81
            r3.setDevImei(r2)     // Catch: java.lang.Exception -> L81
            r0.add(r3)     // Catch: java.lang.Exception -> L81
            int r5 = r5 + 1
            goto L5a
        L81:
            r5 = move-exception
            r5.printStackTrace()
        L85:
            return r0
        L86:
            r5 = move-exception
            r1 = r2
        L88:
            if (r1 == 0) goto L8d
            r1.close()     // Catch: java.lang.Exception -> L8d
        L8d:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy37sdk.utils.DeviceTools.getDeviceFromFile(android.content.Context):java.util.List");
    }

    public static void setDeviceToFile(Context context, DeviceInfo deviceInfo) throws Throwable {
        List<DeviceInfo> deviceFromFile = getDeviceFromFile(context);
        File deviceFile = getDeviceFile(context);
        if (deviceFromFile == null) {
            ArrayList<DeviceInfo> arrayList = new ArrayList();
            arrayList.add(deviceInfo);
            try {
                JSONArray jSONArray = new JSONArray();
                for (DeviceInfo deviceInfo2 : arrayList) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("dev_mac", deviceInfo2.getDevMac());
                    jSONObject.put("dev_imei", deviceInfo2.getDevImei());
                    jSONArray.put(jSONObject);
                }
                String string = jSONArray.toString();
                FileWriter fileWriter = new FileWriter(deviceFile.getAbsolutePath(), false);
                fileWriter.write(ZipString.json2ZipString(string));
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getSDPath(Context context) {
        return EnvironmentUtils.getCommonDirPathEndWithSprit(context);
    }
}
