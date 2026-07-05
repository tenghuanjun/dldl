package com.sqwan.common.util;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sq.tools.Logger;
import com.sq.tools.manager.SensitiveInfoManager;
import com.sqwan.bugless.core.Constant;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.dev.ImeiLogic;
import com.sqwan.common.dev.MacLogic;
import com.taptap.sdk.db.constant.Common;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DeviceUtils {
    private static final String ANDROID = "android";
    private static final String DEFAULT_MAC = "02:00:00:00:00:00";
    private static final String HARMONY = "harmony";
    private static final String IDENTIFY = "identify";
    private static final String SQ_PREFS = "sq_prefs";
    private static String cpuInfo = "";
    private static boolean hasReadCpu;
    private static boolean hasReadRoot;
    private static boolean isFirstLaunch;
    private static boolean isRoot;
    private static String sMaxCpuFreq;
    private static boolean valuatedLaunch;

    public static String getAaid(Context context) {
        return "";
    }

    public static String getCountryCode() {
        return "";
    }

    public static String getVaid(Context context) {
        return "";
    }

    public static String getOs() {
        return isHarmony() ? HARMONY : "android";
    }

    public static boolean isHarmony() {
        try {
            try {
                Class.forName("ohos.app.Application");
                return true;
            } catch (Throwable unused) {
                Class.forName("ohos.system.version.SystemVersion");
                return true;
            }
        } catch (Throwable unused2) {
            try {
                Class<?> cls = Class.forName("com.huawei.system.BuildEx");
                Method method = cls.getMethod("getOsBrand", new Class[0]);
                ClassLoader classLoader = cls.getClassLoader();
                if (classLoader == null || classLoader.getParent() == null) {
                    return false;
                }
                return HARMONY.equals(method.invoke(cls, new Object[0]));
            } catch (Throwable unused3) {
                return false;
            }
        }
    }

    public static String getMac(Context context) {
        return MacLogic.getInstance(context).getValue();
    }

    private static String getBlueToothAddress() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (Build.VERSION.SDK_INT <= 23) {
            return defaultAdapter.getAddress();
        }
        try {
            Field declaredField = defaultAdapter.getClass().getDeclaredField("mService");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(defaultAdapter);
            if (obj == null) {
                return null;
            }
            Object objInvoke = obj.getClass().getMethod("getAddress", new Class[0]).invoke(obj, new Object[0]);
            return (objInvoke == null || !(objInvoke instanceof String)) ? DEFAULT_MAC : (String) objInvoke;
        } catch (Exception unused) {
            return DEFAULT_MAC;
        }
    }

    public static int getScreenBrightness(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), SqTrackCommonKey.screen_brightness);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getBuildTime() {
        return Build.TIME;
    }

    public static String getDeviceSDKVersion() {
        return Build.VERSION.SDK_INT + "";
    }

    public static String getTargetVersion(Context context) {
        return context.getApplicationInfo().targetSdkVersion + "";
    }

    public static int availableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static boolean isFirstLaunch(Context context) {
        if (valuatedLaunch) {
            return isFirstLaunch;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(SQ_PREFS, 0);
        boolean z = sharedPreferences.getBoolean(SqTrackCommonKey.is_first_launch, true);
        isFirstLaunch = z;
        if (z) {
            sharedPreferences.edit().putBoolean(SqTrackCommonKey.is_first_launch, false).apply();
        }
        valuatedLaunch = true;
        return isFirstLaunch;
    }

    public static boolean isFirstInvokeLogin(Context context) {
        return SpUtils.get(context).getBoolean("is_first_invoke_login", true);
    }

    public static void setLoginInvoked(Context context) {
        SpUtils.get(context).put("is_first_invoke_login", false);
    }

    public static List<String> getInputMethodList(Context context) {
        ArrayList arrayList = new ArrayList();
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            Iterator<InputMethodInfo> it = inputMethodManager.getInputMethodList().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().loadLabel(context.getPackageManager()).toString());
            }
        }
        return arrayList;
    }

    public static long getRamTotal(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        if (activityManager == null) {
            return 0L;
        }
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem;
    }

    public static List<String> sensorList(Context context) {
        return SensitiveInfoManager.getInstance().getSensorList(context);
    }

    public static boolean enableAdb(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) > 0;
    }

    public static int simState(Context context) {
        return TelephonyInfoUtils.getSimState(context);
    }

    public static String localeInfo() {
        return Locale.getDefault().getCountry() + "_" + Locale.getDefault().getLanguage();
    }

    private static boolean isWifiProxy(Context context) {
        try {
            String property = System.getProperty("http.proxyHost");
            String property2 = System.getProperty("http.proxyPort");
            if (property2 == null) {
                property2 = "-1";
            }
            return (TextUtils.isEmpty(property) || Integer.parseInt(property2) == -1) ? false : true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isSIMCardAvailable(Context context) {
        return TelephonyInfoUtils.isSIMCardAvailable(context);
    }

    public static boolean isRoot() {
        return (new File("/system/bin/su").exists() && isExecutable("/system/bin/su")) || (new File("/system/xbin/su").exists() && isExecutable("/system/xbin/su"));
    }

    private static boolean isExecutable(String str) {
        Process processExec = null;
        try {
            try {
                processExec = Runtime.getRuntime().exec("ls -l " + str);
                String line = new BufferedReader(new InputStreamReader(processExec.getInputStream())).readLine();
                if (line != null && line.length() >= 4) {
                    char cCharAt = line.charAt(3);
                    if (cCharAt == 's' || cCharAt == 'x') {
                        if (processExec != null) {
                            processExec.destroy();
                        }
                        return true;
                    }
                }
                if (processExec == null) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (processExec == null) {
                    return false;
                }
            }
            processExec.destroy();
            return false;
        } catch (Throwable th) {
            if (processExec != null) {
                processExec.destroy();
            }
            throw th;
        }
    }

    public static int getBatteryLevel(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            return 0;
        }
        return intentRegisterReceiver.getIntExtra("level", 0);
    }

    public static int getBatteryStatus(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            return 1;
        }
        return intentRegisterReceiver.getIntExtra("status", 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getWifiSSID(android.content.Context r4) {
        /*
            boolean r0 = isWifi(r4)
            java.lang.String r1 = ""
            if (r0 != 0) goto L9
            return r1
        L9:
            android.content.Context r4 = r4.getApplicationContext()
            java.lang.String r0 = "wifi"
            java.lang.Object r4 = r4.getSystemService(r0)
            android.net.wifi.WifiManager r4 = (android.net.wifi.WifiManager) r4
            if (r4 == 0) goto L4a
            android.net.wifi.WifiInfo r0 = r4.getConnectionInfo()
            if (r0 != 0) goto L1e
            return r1
        L1e:
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 26
            if (r2 > r3) goto L29
            java.lang.String r4 = r0.getSSID()
            goto L4b
        L29:
            int r0 = r0.getNetworkId()
            java.util.List r4 = r4.getConfiguredNetworks()
            if (r4 == 0) goto L4a
            java.util.Iterator r4 = r4.iterator()
        L37:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L4a
            java.lang.Object r2 = r4.next()
            android.net.wifi.WifiConfiguration r2 = (android.net.wifi.WifiConfiguration) r2
            int r3 = r2.networkId
            if (r3 != r0) goto L37
            java.lang.String r4 = r2.SSID
            goto L4b
        L4a:
            r4 = r1
        L4b:
            java.lang.String r0 = "\""
            boolean r2 = r4.contains(r0)
            if (r2 == 0) goto L57
            java.lang.String r4 = r4.replaceAll(r0, r1)
        L57:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.common.util.DeviceUtils.getWifiSSID(android.content.Context):java.lang.String");
    }

    public static String getWifiBSSID(Context context) {
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        return (!isWifi(context) || (wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi")) == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) ? "" : connectionInfo.getBSSID();
    }

    private static boolean isWifi(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getCountry() {
        return Locale.getDefault().getCountry();
    }

    public static String getNetWorkType(Context context) {
        return NetWorkUtils.getNetworkType(context);
    }

    public static String getIpAddress(Context context) {
        return NetWorkUtils.getIpAddress(context);
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static Map<String, String> getDisplayMetrics(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        HashMap map = new HashMap();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
            map.put("height", displayMetrics.heightPixels + "");
            map.put(Common.Predefined.SUB_WIDTH, displayMetrics.widthPixels + "");
            map.put("dpi", displayMetrics.densityDpi + "");
        }
        return map;
    }

    public static String getTotalRam() {
        String str = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            str = bufferedReader.readLine().split("\\s+")[1];
            bufferedReader.close();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static boolean isSDCardEnable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static long getTotalExternalMemorySize() {
        try {
            if (!isSDCardEnable()) {
                return 0L;
            }
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static int getCpuCore() {
        try {
            return Runtime.getRuntime().availableProcessors();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getCpuHardware() {
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
            do {
                line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    return null;
                }
            } while (!line.contains("Hardware"));
            return line.split(":")[1];
        } catch (IOException unused) {
            return null;
        }
    }

    public static String getMaxCpuFreq() {
        String str;
        String str2 = sMaxCpuFreq;
        if (str2 != null) {
            return str2;
        }
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start().getInputStream();
            byte[] bArr = new byte[24];
            str = "";
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            str = "N/A";
        }
        String strTrim = str.trim();
        sMaxCpuFreq = strTrim;
        return strTrim;
    }

    public static String getMinCpuFreq() {
        String str;
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq").start().getInputStream();
            byte[] bArr = new byte[24];
            str = "";
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            str = "N/A";
        }
        return str.trim();
    }

    public static String getCurCpuFreq() {
        try {
            return new BufferedReader(new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq")).readLine().trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "N/A";
        } catch (IOException e2) {
            e2.printStackTrace();
            return "N/A";
        }
    }

    public static String getCpuName() {
        try {
            return new BufferedReader(new FileReader("/proc/cpuinfo")).readLine().split(":\\s+", 2)[1];
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getIMEI(Context context) {
        return ImeiLogic.getInstance(context).getValue();
    }

    public static String getDev(Context context) {
        return DevLogic.getInstance(context).getValue();
    }

    public static String getOaid(Context context) {
        return context.getSharedPreferences(SQ_PREFS, 0).getString(IDENTIFY, "");
    }

    public static String getIMSI(Context context) {
        return TelephonyInfoUtils.getSubscriberId(context);
    }

    public static String getAndroidId(Context context) {
        return SensitiveInfoManager.getInstance().getAndroidId(context);
    }

    public static long getBootTime() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public static String getSIM(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                return ((TelephonyManager) context.getSystemService("phone")).getPhoneCount() + "";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static boolean isSimulator(Context context) {
        String networkOperatorName;
        try {
            if (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT)) {
                return true;
            }
            String str = "";
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null && (networkOperatorName = telephonyManager.getNetworkOperatorName()) != null) {
                str = networkOperatorName;
            }
            if (str.equalsIgnoreCase("android")) {
                return true;
            }
            Intent intent = new Intent();
            intent.setData(Uri.parse("tel:123456"));
            intent.setAction("android.intent.action.DIAL");
            if (intent.resolveActivity(context.getPackageManager()) == null) {
                return true;
            }
            String cpuInfo2 = readCpuInfo();
            if (!cpuInfo2.contains("intel")) {
                if (cpuInfo2.contains("amd")) {
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String readCpuInfo() {
        String lowerCase;
        StringBuilder sb;
        BufferedReader bufferedReader;
        if (hasReadCpu) {
            return cpuInfo;
        }
        try {
            Process processStart = new ProcessBuilder("/system/bin/cat", "/proc/cpuinfo").start();
            sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(processStart.getInputStream(), "utf-8"));
        } catch (IOException e) {
            Logger.info("readCpuInfo " + e.toString(), new Object[0]);
            lowerCase = "";
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
            hasReadCpu = true;
            cpuInfo = lowerCase;
            return lowerCase;
        }
        bufferedReader.close();
        lowerCase = sb.toString().toLowerCase();
        hasReadCpu = true;
        cpuInfo = lowerCase;
        return lowerCase;
    }

    public static boolean isRootDevice() {
        if (hasReadRoot) {
            return isRoot;
        }
        String[] strArr = {"/system/xbin/", "/system/bin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/"};
        for (int i = 0; i < 6; i++) {
            try {
                String str = strArr[i] + ShellAdbUtils.COMMAND_SU;
                if (new File(str).exists()) {
                    String fileInfo = readFileInfo(new String[]{"ls", "-l", str});
                    isRoot = (TextUtils.isEmpty(fileInfo) || fileInfo.indexOf("root") == fileInfo.lastIndexOf("root")) ? false : true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        hasReadRoot = true;
        return isRoot;
    }

    private static String readFileInfo(String[] strArr) {
        Process processStart;
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        try {
            processStart = new ProcessBuilder(strArr).start();
            bufferedReader = new BufferedReader(new InputStreamReader(processStart.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
            return sb.toString();
        }
        processStart.getInputStream().close();
        processStart.destroy();
        return sb.toString();
    }

    public static String getCarrier(Context context) {
        HashMap<String, String> map = new HashMap<String, String>() { // from class: com.sqwan.common.util.DeviceUtils.1
            {
                put("46000", "中国移动");
                put("46002", "中国移动");
                put("46007", "中国移动");
                put("46008", "中国移动");
                put("46001", "中国联通");
                put("46006", "中国联通");
                put("46009", "中国联通");
                put("46003", "中国电信");
                put("46005", "中国电信");
                put("46011", "中国电信");
                put("46004", "中国卫通");
                put("46020", "中国铁通");
            }
        };
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String simOperator = telephonyManager.getSimOperator();
            if (!TextUtils.isEmpty(simOperator) && map.containsKey(simOperator)) {
                return map.get(simOperator);
            }
            String simOperatorName = telephonyManager.getSimOperatorName();
            return !TextUtils.isEmpty(simOperatorName) ? simOperatorName : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void printInfo(Context context) {
        LogUtil.i((((((((((((((((((((((((((((((((((("产品Product: " + Build.PRODUCT + ShellAdbUtils.COMMAND_LINE_END) + ", CPU_ABI: " + Build.CPU_ABI + ShellAdbUtils.COMMAND_LINE_END) + ", CPU_ABI2: " + Build.CPU_ABI2 + ShellAdbUtils.COMMAND_LINE_END) + ", 标签TAGS: " + Build.TAGS + ShellAdbUtils.COMMAND_LINE_END) + ", VERSION_CODES.BASE: 1\n") + ", 型号MODEL: " + Build.MODEL + ShellAdbUtils.COMMAND_LINE_END) + ", Android 版本 VERSION.RELEASE: " + Build.VERSION.RELEASE + ShellAdbUtils.COMMAND_LINE_END) + ", 驱动 DEVICE: " + Build.DEVICE + ShellAdbUtils.COMMAND_LINE_END) + ", DISPLAY: " + Build.DISPLAY + ShellAdbUtils.COMMAND_LINE_END) + ", 品牌 BRAND: " + Build.BRAND + ShellAdbUtils.COMMAND_LINE_END) + ", 基板 BOARD: " + Build.BOARD + ShellAdbUtils.COMMAND_LINE_END) + ", 设备标识 FINGERPRINT: " + Build.FINGERPRINT + ShellAdbUtils.COMMAND_LINE_END) + ", 版本号 ID: " + Build.ID + ShellAdbUtils.COMMAND_LINE_END) + ", 制造商 MANUFACTURER: " + Build.MANUFACTURER + ShellAdbUtils.COMMAND_LINE_END) + ", 用户 USER: " + Build.USER + ShellAdbUtils.COMMAND_LINE_END) + ", BOOTLOADER: " + Build.BOOTLOADER + ShellAdbUtils.COMMAND_LINE_END) + ", 主机地址 :" + Build.HOST + ShellAdbUtils.COMMAND_LINE_END) + ", 硬件 HARDWARE: " + Build.HARDWARE + ShellAdbUtils.COMMAND_LINE_END) + ", INCREMENTAL: " + Build.VERSION.INCREMENTAL + ShellAdbUtils.COMMAND_LINE_END) + ", CODENAME: " + Build.VERSION.CODENAME + ShellAdbUtils.COMMAND_LINE_END) + ", SDK: " + Build.VERSION.SDK_INT + ShellAdbUtils.COMMAND_LINE_END) + ", Android id: " + getAndroidId(context) + ShellAdbUtils.COMMAND_LINE_END) + ", 手机启动时间: " + getBootTime() + ShellAdbUtils.COMMAND_LINE_END) + ", 手机屏幕亮度: " + getScreenBrightness(context) + ShellAdbUtils.COMMAND_LINE_END) + ", cpu 核心数: " + availableProcessors() + ShellAdbUtils.COMMAND_LINE_END) + ", 是否第一次启动: " + isFirstLaunch(context) + ShellAdbUtils.COMMAND_LINE_END) + ", 输入法列表: " + getInputMethodList(context) + ShellAdbUtils.COMMAND_LINE_END) + ", 总内存: " + getRamTotal(context) + ShellAdbUtils.COMMAND_LINE_END) + ", 屏幕属性: " + getDisplayMetrics(context).toString() + ShellAdbUtils.COMMAND_LINE_END) + ", 传感器列表: " + sensorList(context) + ShellAdbUtils.COMMAND_LINE_END) + ", 设备是否开启调试模式: " + enableAdb(context) + ShellAdbUtils.COMMAND_LINE_END) + ", sim卡状态: " + simState(context) + ShellAdbUtils.COMMAND_LINE_END) + ", 国家和语言: " + localeInfo() + ShellAdbUtils.COMMAND_LINE_END) + ", 是否连接代理: " + isWifiProxy(context) + ShellAdbUtils.COMMAND_LINE_END) + ", 是否有root权限: " + isRoot() + ShellAdbUtils.COMMAND_LINE_END);
    }

    public static String loadInfo(Context context) {
        HashMap map = new HashMap();
        map.put("product", Build.PRODUCT);
        map.put("cpu_abi", Build.CPU_ABI);
        map.put("cpu_abi2", Build.CPU_ABI2);
        map.put(SqTrackCommonKey.tags, Build.TAGS);
        map.put("version_codes.base", 1);
        map.put(Constant.DEV_MODEL, Build.MODEL);
        map.put("version.release", Build.VERSION.RELEASE);
        map.put("device", Build.DEVICE);
        map.put(SqTrackCommonKey.display, Build.DISPLAY);
        map.put("brand", Build.BRAND);
        map.put(SqTrackCommonKey.board, Build.BOARD);
        map.put(SqTrackCommonKey.fingerprint, Build.FINGERPRINT);
        map.put(SqTrackCommonKey.id, Build.ID);
        map.put(SqTrackCommonKey.manufacturer, Build.MANUFACTURER);
        map.put(SqTrackCommonKey.user, Build.USER);
        map.put(SqTrackCommonKey.bootloader, Build.BOOTLOADER);
        map.put("host", Build.HOST);
        map.put(SqTrackCommonKey.hardware, Build.HARDWARE);
        map.put(SqTrackCommonKey.incremental, Build.VERSION.INCREMENTAL);
        map.put(SqTrackCommonKey.codename, Build.VERSION.CODENAME);
        map.put(SqTrackCommonKey.sdk, Integer.valueOf(Build.VERSION.SDK_INT));
        map.put("adid", getAndroidId(context));
        map.put(SqTrackCommonKey.boot_time, Long.valueOf(getBootTime()));
        map.put(SqTrackCommonKey.screen_brightness, Integer.valueOf(getScreenBrightness(context)));
        map.put(SqTrackCommonKey.cpu_count, Integer.valueOf(availableProcessors()));
        map.put(SqTrackCommonKey.is_first_launch, Boolean.valueOf(isFirstLaunch(context)));
        map.put(SqTrackCommonKey.input_method_list, getInputMethodList(context));
        map.put(SqTrackCommonKey.ram_total, Long.valueOf(getRamTotal(context)));
        map.put(SqTrackCommonKey.display_metrics, getDisplayMetrics(context));
        map.put(SqTrackCommonKey.sensor_list, sensorList(context));
        map.put(SqTrackCommonKey.enable_adb, Boolean.valueOf(enableAdb(context)));
        map.put("sim_state", Integer.valueOf(simState(context)));
        map.put("country", Locale.getDefault().getCountry());
        map.put("language", Locale.getDefault().getLanguage());
        map.put(SqTrackCommonKey.is_wifi_proxy, Boolean.valueOf(isWifiProxy(context)));
        map.put(SqConstants.IS_ROOT, Boolean.valueOf(isRoot()));
        return new JSONObject(map).toString();
    }
}
