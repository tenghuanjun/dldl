package com.sqwan.common.cache;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import com.sqwan.common.util.EnvironmentUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ZipString;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DeviceSdCache {
    private static final String DEVICE_DIR = "systemgameinfo";
    private static final String DEVICE_FILE = "devinfo2.txt";

    public static String getValue(Context context, String str) throws Throwable {
        LogUtil.i("get value key --> " + str);
        String value = null;
        if (ActivityCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            Map<String, String> infoFormFile = getInfoFormFile(context);
            if (infoFormFile != null && !infoFormFile.isEmpty()) {
                for (Map.Entry<String, String> entry : infoFormFile.entrySet()) {
                    if (entry.getKey().equals(str)) {
                        value = entry.getValue();
                    }
                }
            }
            LogUtil.i("return value --> " + value);
            return value;
        }
        LogUtil.i("return value --> " + ((Object) null));
        return null;
    }

    public static boolean saveValue(Context context, String str, String str2) throws Throwable {
        LogUtil.i("save value key --> " + str + ", value --> " + str2);
        if (ActivityCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            return false;
        }
        Map infoFormFile = getInfoFormFile(context);
        if (infoFormFile == null) {
            infoFormFile = new HashMap();
        }
        infoFormFile.put(str, str2);
        return saveInfoToFile(context, infoFormFile);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:5|(2:50|6)|(7:43|7|(1:9)(1:52)|45|11|23|(1:25)(6:26|48|27|(2:30|28)|53|34))|10|45|11|23|(0)(0)) */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.Map<java.lang.String, java.lang.String> getInfoFormFile(android.content.Context r4) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "get info from file"
            com.sqwan.common.util.LogUtil.i(r0)
            java.io.File r4 = getDeviceFile(r4)
            r0 = 0
            if (r4 != 0) goto Ld
            return r0
        Ld:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
        L1c:
            java.lang.String r4 = r2.readLine()     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L89
            if (r4 == 0) goto L26
            r1.append(r4)     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L89
            goto L1c
        L26:
            r2.close()     // Catch: java.lang.Exception -> L2f java.lang.Throwable -> L89
        L29:
            r2.close()     // Catch: java.lang.Exception -> L2d
            goto L3b
        L2d:
            goto L3b
        L2f:
            r4 = move-exception
            goto L35
        L31:
            r4 = move-exception
            goto L8b
        L33:
            r4 = move-exception
            r2 = r0
        L35:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L89
            if (r2 == 0) goto L3b
            goto L29
        L3b:
            java.lang.String r4 = r1.toString()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L46
            return r0
        L46:
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Exception -> L84
            java.lang.String r0 = com.sqwan.common.util.ZipString.zipString2Json(r0)     // Catch: java.lang.Exception -> L84
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L84
            r1.<init>()     // Catch: java.lang.Exception -> L84
            java.lang.String r2 = "json str --> "
            r1.append(r2)     // Catch: java.lang.Exception -> L84
            r1.append(r0)     // Catch: java.lang.Exception -> L84
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L84
            com.sqwan.common.util.LogUtil.i(r1)     // Catch: java.lang.Exception -> L84
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> L84
            r1.<init>(r0)     // Catch: java.lang.Exception -> L84
            java.util.Iterator r0 = r1.keys()     // Catch: java.lang.Exception -> L84
        L70:
            boolean r2 = r0.hasNext()     // Catch: java.lang.Exception -> L84
            if (r2 == 0) goto L88
            java.lang.Object r2 = r0.next()     // Catch: java.lang.Exception -> L84
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Exception -> L84
            java.lang.String r3 = r1.optString(r2)     // Catch: java.lang.Exception -> L84
            r4.put(r2, r3)     // Catch: java.lang.Exception -> L84
            goto L70
        L84:
            r0 = move-exception
            r0.printStackTrace()
        L88:
            return r4
        L89:
            r4 = move-exception
            r0 = r2
        L8b:
            if (r0 == 0) goto L90
            r0.close()     // Catch: java.lang.Exception -> L90
        L90:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.common.cache.DeviceSdCache.getInfoFormFile(android.content.Context):java.util.Map");
    }

    private static boolean saveInfoToFile(Context context, Map<String, String> map) {
        LogUtil.i("save info to file");
        File deviceFile = getDeviceFile(context);
        if (deviceFile == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(map);
            LogUtil.i("json str --> " + jSONObject.toString());
            FileWriter fileWriter = new FileWriter(deviceFile.getAbsolutePath(), false);
            fileWriter.write(ZipString.json2ZipString(jSONObject.toString()));
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static File getDeviceFile(Context context) {
        try {
            File file = new File(getSDPath(context) + File.separator + DEVICE_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (Exception e) {
            LogUtil.i("无SDCard，获取AF失败");
            e.printStackTrace();
            return null;
        }
    }

    private static String getSDPath(Context context) {
        return EnvironmentUtils.getCommonSubDirPath(context, DEVICE_DIR);
    }
}
