package com.alipay.deviceid.module.x;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.alipay.zoloz.toyger.blob.BlobManager;
import com.aliyun.aliyunface.utils.MobileUtil;
import com.mobile.auth.gatewayauth.Constant;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class k {
    private static k a = new k();
    private static long b = 0;

    class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public final boolean accept(File file) {
            return Pattern.matches("cpu[0-9]+", file.getName());
        }
    }

    private k() {
    }

    public static k a() {
        return a;
    }

    public static String a(Context context) {
        String deviceId = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                }
            } catch (Exception unused) {
            }
        }
        return deviceId == null ? "" : deviceId;
    }

    public static String b() {
        HashSet<String> hashSet = new HashSet();
        String[] strArr = {"CPU_ABI", "CPU_ABI2", "SUPPORTED_ABIS", "SUPPORTED_32_BIT_ABIS", "SUPPORTED_64_BIT_ABIS"};
        for (int i = 0; i < 5; i++) {
            try {
                String str = (String) Build.class.getField(strArr[i]).get(null);
                if (str != null && str.length() > 0 && !hashSet.contains(str)) {
                    hashSet.add(str);
                }
            } catch (Exception unused) {
            }
        }
        JSONArray jSONArray = new JSONArray();
        for (String str2 : hashSet) {
            if (str2 != null) {
                try {
                    if (str2.length() > 0) {
                        jSONArray.put(str2);
                    }
                } catch (Exception unused2) {
                }
            }
        }
        try {
            return jSONArray.toString();
        } catch (Exception unused3) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b(android.content.Context r2) {
        /*
            java.lang.String r0 = ""
            if (r2 == 0) goto L13
            java.lang.String r1 = "phone"
            java.lang.Object r2 = r2.getSystemService(r1)     // Catch: java.lang.Exception -> L13
            android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2     // Catch: java.lang.Exception -> L13
            if (r2 == 0) goto L13
            java.lang.String r2 = r2.getSubscriberId()     // Catch: java.lang.Exception -> L13
            goto L14
        L13:
            r2 = r0
        L14:
            if (r2 != 0) goto L17
            goto L18
        L17:
            r0 = r2
        L18:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.k.b(android.content.Context):java.lang.String");
    }

    public static String c() {
        long availableBlocks;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            availableBlocks = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Exception unused) {
            availableBlocks = 0;
        }
        return String.valueOf(availableBlocks);
    }

    public static String c(Context context) {
        int i = 0;
        try {
            i = Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Exception unused) {
        }
        return i == 1 ? "1" : "0";
    }

    public static String d() {
        long blockSize = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(e.a().getPath());
                blockSize = ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
            }
        } catch (Exception unused) {
        }
        return String.valueOf(blockSize);
    }

    public static String d(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            int i = audioManager.getRingerMode() == 0 ? 1 : 0;
            int streamVolume = audioManager.getStreamVolume(0);
            int streamVolume2 = audioManager.getStreamVolume(1);
            int streamVolume3 = audioManager.getStreamVolume(2);
            int streamVolume4 = audioManager.getStreamVolume(3);
            int streamVolume5 = audioManager.getStreamVolume(4);
            jSONObject.put("ringermode", String.valueOf(i));
            jSONObject.put("call", String.valueOf(streamVolume));
            jSONObject.put("system", String.valueOf(streamVolume2));
            jSONObject.put("ring", String.valueOf(streamVolume3));
            jSONObject.put("music", String.valueOf(streamVolume4));
            jSONObject.put("alarm", String.valueOf(streamVolume5));
        } catch (Exception unused) {
        }
        return jSONObject.toString();
    }

    public static String e() {
        return "";
    }

    public static String e(Context context) {
        String networkOperatorName = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    networkOperatorName = telephonyManager.getNetworkOperatorName();
                }
            } catch (Exception unused) {
            }
        }
        return (networkOperatorName == null || "null".equals(networkOperatorName)) ? "" : networkOperatorName;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
    
        r0 = r6.substring(r6.indexOf(":") + 1, r6.length()).trim();
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x008a A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:87:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String f() throws java.lang.Throwable {
        /*
            java.lang.String r0 = "0000000000000000"
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L75
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L75
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L75
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L75
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
            java.io.LineNumberReader r4 = new java.io.LineNumberReader     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L57
            r1 = 1
            r5 = 1
        L1b:
            r6 = 100
            if (r5 >= r6) goto L49
            java.lang.String r6 = r4.readLine()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            if (r6 == 0) goto L49
            java.lang.String r7 = "Serial"
            int r7 = r6.indexOf(r7)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            if (r7 < 0) goto L41
            java.lang.String r5 = ":"
            int r5 = r6.indexOf(r5)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            int r5 = r5 + r1
            int r1 = r6.length()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            java.lang.String r1 = r6.substring(r5, r1)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            java.lang.String r0 = r1.trim()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            goto L49
        L41:
            int r5 = r5 + 1
            goto L1b
        L44:
            r0 = move-exception
            r1 = r4
            goto L61
        L47:
            r1 = r4
            goto L77
        L49:
            r4.close()     // Catch: java.lang.Exception -> L4c
        L4c:
            r3.close()     // Catch: java.lang.Exception -> L4f
        L4f:
            r2.close()     // Catch: java.lang.Exception -> L53
            goto L88
        L53:
            goto L88
        L55:
            r0 = move-exception
            goto L61
        L57:
            goto L77
        L59:
            r0 = move-exception
            r3 = r1
            goto L61
        L5c:
            r3 = r1
            goto L77
        L5e:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L61:
            if (r1 == 0) goto L68
            r1.close()     // Catch: java.lang.Exception -> L67
            goto L68
        L67:
        L68:
            if (r3 == 0) goto L6f
            r3.close()     // Catch: java.lang.Exception -> L6e
            goto L6f
        L6e:
        L6f:
            if (r2 == 0) goto L74
            r2.close()     // Catch: java.lang.Exception -> L74
        L74:
            throw r0
        L75:
            r2 = r1
            r3 = r2
        L77:
            if (r1 == 0) goto L7e
            r1.close()     // Catch: java.lang.Exception -> L7d
            goto L7e
        L7d:
        L7e:
            if (r3 == 0) goto L85
            r3.close()     // Catch: java.lang.Exception -> L84
            goto L85
        L84:
        L85:
            if (r2 == 0) goto L88
            goto L4f
        L88:
            if (r0 != 0) goto L8c
            java.lang.String r0 = ""
        L8c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.k.f():java.lang.String");
    }

    public static String f(Context context) {
        List<Sensor> sensorList;
        String strD = null;
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService(BlobManager.BLOB_ELEM_TYPE_SENSOR);
                if (sensorManager != null && (sensorList = sensorManager.getSensorList(-1)) != null && sensorList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (Sensor sensor : sensorList) {
                        sb.append(sensor.getName());
                        sb.append(sensor.getVersion());
                        sb.append(sensor.getVendor());
                    }
                    strD = e.d(sb.toString());
                }
            } catch (Exception unused) {
            }
        }
        return strD == null ? "" : strD;
    }

    public static String g(Context context) {
        List<Sensor> sensorList;
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService(BlobManager.BLOB_ELEM_TYPE_SENSOR);
                if (sensorManager != null && (sensorList = sensorManager.getSensorList(-1)) != null && sensorList.size() > 0) {
                    for (Sensor sensor : sensorList) {
                        if (sensor != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("name", sensor.getName());
                            jSONObject.put("version", sensor.getVersion());
                            jSONObject.put(Constant.LOGIN_ACTIVITY_VENDOR_KEY, sensor.getVendor());
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return jSONArray.toString();
    }

    public static String h() {
        return "";
    }

    public static String h(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + "*" + Integer.toString(displayMetrics.heightPixels);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String i() throws Throwable {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        FileReader fileReader2 = null;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 8192);
                try {
                    j = bufferedReader.readLine() != null ? Integer.parseInt(r1.split("\\s+")[1]) : 0L;
                    try {
                        fileReader.close();
                    } catch (Exception unused) {
                    }
                } catch (Exception unused2) {
                    fileReader2 = fileReader;
                    if (fileReader2 != null) {
                        try {
                            fileReader2.close();
                        } catch (Exception unused3) {
                        }
                    }
                    if (bufferedReader != null) {
                    }
                    return String.valueOf(j);
                } catch (Throwable th2) {
                    th = th2;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Exception unused4) {
                        }
                    }
                    if (bufferedReader == null) {
                        throw th;
                    }
                    try {
                        bufferedReader.close();
                        throw th;
                    } catch (Exception unused5) {
                        throw th;
                    }
                }
            } catch (Exception unused6) {
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        } catch (Exception unused7) {
            bufferedReader = null;
        } catch (Throwable th4) {
            fileReader = null;
            th = th4;
            bufferedReader = null;
        }
        try {
            bufferedReader.close();
        } catch (Exception unused8) {
        }
        return String.valueOf(j);
    }

    public static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.widthPixels);
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String j() {
        long blockCount;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            blockCount = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Exception unused) {
            blockCount = 0;
        }
        return String.valueOf(blockCount);
    }

    public static String j(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.heightPixels);
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String k() {
        long blockSize = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                blockSize = ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
            }
        } catch (Exception unused) {
        }
        return String.valueOf(blockSize);
    }

    public static String k(Context context) {
        try {
            String simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            if (simSerialNumber == null) {
                return "";
            }
            if (simSerialNumber != null) {
                try {
                    if (simSerialNumber.length() == 0) {
                        return "";
                    }
                } catch (Exception unused) {
                }
            }
            return simSerialNumber;
        } catch (Exception unused2) {
            return "";
        }
    }

    public static String l() {
        String str;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
        } catch (Exception unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    public static String l(Context context) {
        String string;
        try {
            string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception unused) {
            string = "";
        }
        return string == null ? "" : string;
    }

    public static String m() {
        return "";
    }

    public static String m(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? String.valueOf(telephonyManager.getNetworkType()) : "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String n() {
        String str;
        try {
            str = Build.SERIAL;
        } catch (Exception unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    public static String n(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            String str = null;
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    str = MobileUtil.NETWORK_WIFI;
                } else if (activeNetworkInfo.getType() == 0) {
                    int subtype = activeNetworkInfo.getSubtype();
                    str = (subtype == 4 || subtype == 1 || subtype == 2 || subtype == 7 || subtype == 11) ? MobileUtil.NETWORK_2G : (subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14 || subtype == 15) ? MobileUtil.NETWORK_3G : subtype == 13 ? MobileUtil.NETWORK_4G : "UNKNOW";
                }
            }
            String strX = x();
            if (!e.b(str) || !e.b(strX)) {
                return "";
            }
            return str + ":" + x();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String o() {
        String string;
        try {
            string = Locale.getDefault().toString();
        } catch (Exception unused) {
            string = "";
        }
        return string == null ? "" : string;
    }

    public static String o(Context context) {
        if (!((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure()) {
            return "0:0";
        }
        String[] strArr = {"/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key"};
        long jMax = 0;
        for (int i = 0; i < 5; i++) {
            long jLastModified = -1;
            try {
                jLastModified = new File(strArr[i]).lastModified();
            } catch (Exception unused) {
            }
            jMax = Math.max(jLastModified, jMax);
        }
        return "1:" + jMax;
    }

    public static String p() {
        String displayName;
        try {
            displayName = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Exception unused) {
            displayName = "";
        }
        return displayName == null ? "" : displayName;
    }

    public static String p(Context context) {
        try {
            Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = intentRegisterReceiver.getIntExtra(DBHelpTool.RecordEntry.COLUMN_NAME_LEVEL, -1);
            int intExtra2 = intentRegisterReceiver.getIntExtra("status", -1);
            boolean z = intExtra2 == 2 || intExtra2 == 5;
            StringBuilder sb = new StringBuilder();
            sb.append(z ? "1" : "0");
            sb.append(":");
            sb.append(intExtra);
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static long q() {
        try {
            if (b == 0) {
                b = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            }
        } catch (Exception unused) {
        }
        return b;
    }

    public static String r() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SystemClock.elapsedRealtime());
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String s() {
        try {
            StringBuilder sb = new StringBuilder();
            String[] strArr = {"/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd"};
            sb.append("00:");
            for (int i = 0; i < 7; i++) {
                sb.append(new File(strArr[i]).exists() ? "1" : "0");
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String t() {
        String[] strArr = {"dalvik.system.Taint"};
        StringBuilder sb = new StringBuilder();
        sb.append("00");
        sb.append(":");
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                sb.append("1");
            } catch (Exception unused) {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    public static String u() throws Throwable {
        StringBuilder sb = new StringBuilder();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("/system/build.prop", "ro.product.name=sdk");
        linkedHashMap.put("/proc/tty/drivers", "goldfish");
        linkedHashMap.put("/proc/cpuinfo", "goldfish");
        sb.append("00:");
        for (String str : linkedHashMap.keySet()) {
            LineNumberReader lineNumberReader = null;
            char c = '0';
            try {
                LineNumberReader lineNumberReader2 = new LineNumberReader(new InputStreamReader(new FileInputStream(str)));
                while (true) {
                    try {
                        String line = lineNumberReader2.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.toLowerCase().contains((CharSequence) linkedHashMap.get(str))) {
                            c = '1';
                            break;
                        }
                    } catch (Exception unused) {
                        lineNumberReader = lineNumberReader2;
                        sb.append('0');
                        if (lineNumberReader != null) {
                            lineNumberReader.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        lineNumberReader = lineNumberReader2;
                        sb.append('0');
                        if (lineNumberReader != null) {
                            try {
                                lineNumberReader.close();
                            } catch (Exception unused2) {
                            }
                        }
                        throw th;
                    }
                }
                sb.append(c);
                try {
                    lineNumberReader2.close();
                } catch (Exception unused3) {
                }
            } catch (Exception unused4) {
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return sb.toString();
    }

    public static String v() {
        StringBuilder sb = new StringBuilder();
        sb.append("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("BRAND", "generic");
        linkedHashMap.put("BOARD", "unknown");
        linkedHashMap.put("DEVICE", "generic");
        linkedHashMap.put("HARDWARE", "goldfish");
        linkedHashMap.put("PRODUCT", "sdk");
        linkedHashMap.put("MODEL", "sdk");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            try {
                String str2 = (String) Build.class.getField(str).get(null);
                String str3 = (String) linkedHashMap.get(str);
                String lowerCase = str2 != null ? str2.toLowerCase() : null;
                if (lowerCase != null && lowerCase.contains(str3)) {
                    c = '1';
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                sb.append('0');
                throw th;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String w() {
        StringBuilder sb = new StringBuilder();
        sb.append("00:");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ro.hardware", "goldfish");
        linkedHashMap.put("ro.kernel.qemu", "1");
        linkedHashMap.put("ro.product.device", "generic");
        linkedHashMap.put("ro.product.model", "sdk");
        linkedHashMap.put("ro.product.brand", "generic");
        linkedHashMap.put("ro.product.name", "sdk");
        linkedHashMap.put("ro.build.fingerprint", "test-keys");
        linkedHashMap.put("ro.product.manufacturer", "unknow");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            String str2 = (String) linkedHashMap.get(str);
            String strB = e.b(str, "");
            if (strB != null && strB.contains(str2)) {
                c = '1';
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static String x() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address)) {
                        return inetAddressNextElement.getHostAddress().toString();
                    }
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public final String g() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new a()).length);
        } catch (Exception unused) {
            return "1";
        }
    }
}
