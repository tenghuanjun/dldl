package cn.thinkingdata.android;

import android.app.ActivityManager;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.LocaleList;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import cn.thinkingdata.android.g;
import cn.thinkingdata.android.utils.TDLog;
import cn.thinkingdata.android.utils.r;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class k {
    private static String k = "Android";
    private static String l = "2.8.3.5";
    private static k m;
    private static final Object n = new Object();
    private boolean a;
    private long b;
    private final TimeZone c;
    private String d;
    private final Map<String, Object> e;
    private final Context f;
    private final boolean g;
    private String h;
    private String i;
    private boolean j = false;

    class a extends ConnectivityManager.NetworkCallback {
        a() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            k kVar = k.this;
            kVar.i = kVar.f();
            k.this.j = true;
            super.onAvailable(network);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            k.this.i = "NULL";
            super.onLost(network);
        }
    }

    class b implements g.a {
        b() {
        }

        @Override // cn.thinkingdata.android.g.a
        public void a() {
            k kVar = k.this;
            kVar.i = kVar.f();
            k.this.j = true;
        }
    }

    class c extends HashMap<String, String> {
        c() {
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
    }

    private k(Context context, TimeZone timeZone) {
        Context applicationContext = context.getApplicationContext();
        this.f = applicationContext;
        this.c = timeZone;
        this.g = a(applicationContext, "android.permission.ACCESS_NETWORK_STATE");
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (!TDPresetProperties.disableList.contains("#app_version")) {
                this.d = packageInfo.versionName;
            }
            long j = packageInfo.firstInstallTime;
            this.b = j;
            this.a = j == packageInfo.lastUpdateTime;
            TDLog.d("ThinkingAnalytics.SystemInformation", "First Install Time: " + packageInfo.firstInstallTime);
            TDLog.d("ThinkingAnalytics.SystemInformation", "Last Update Time: " + packageInfo.lastUpdateTime);
        } catch (Exception unused) {
            TDLog.d("ThinkingAnalytics.SystemInformation", "Exception occurred in getting app version");
        }
        this.e = f(context);
        l();
    }

    private static int a(int i, int i2, int i3) {
        return (i == 0 || i == 2) ? i3 : i2;
    }

    public static k a(Context context, TimeZone timeZone) {
        k kVar;
        synchronized (n) {
            if (m == null) {
                m = new k(context, timeZone);
            }
            kVar = m;
        }
        return kVar;
    }

    private String a(Context context, TelephonyManager telephonyManager, ConnectivityManager connectivityManager) {
        int networkType;
        NetworkInfo activeNetworkInfo;
        if (telephonyManager != null) {
            try {
                networkType = (Build.VERSION.SDK_INT < 30 || !a(context, "android.permission.READ_PHONE_STATE")) ? telephonyManager.getNetworkType() : telephonyManager.getDataNetworkType();
            } catch (Exception unused) {
                networkType = 0;
            }
        } else {
            networkType = 0;
        }
        if (networkType == 0 && connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
            networkType = activeNetworkInfo.getSubtype();
        }
        switch (networkType) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return "3G";
            case 13:
            case 18:
            case 19:
                return "4G";
            case 20:
                return com.sq.tools.network.Network.MOBILE_5G;
            default:
                return "NULL";
        }
    }

    static void a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            k = str;
            TDLog.d("ThinkingAnalytics.SystemInformation", "#lib has been changed to: " + str);
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        l = str2;
        TDLog.d("ThinkingAnalytics.SystemInformation", "#lib_version has been changed to: " + str2);
    }

    private boolean a(Context context, String str) {
        Class<?> cls;
        try {
            cls = Class.forName("android.support.v4.content.ContextCompat");
        } catch (Exception unused) {
            cls = null;
        }
        if (cls == null) {
            try {
                cls = Class.forName("androidx.core.content.ContextCompat");
            } catch (Exception unused2) {
            }
        }
        if (cls == null) {
            return true;
        }
        try {
            if (((Integer) cls.getMethod("checkSelfPermission", Context.class, String.class).invoke(null, context, str)).intValue() == 0) {
                return true;
            }
            TDLog.w("ThinkingAnalytics.SystemInformation", "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"" + str + "\" />");
            return false;
        } catch (Exception e) {
            TDLog.w("ThinkingAnalytics.SystemInformation", e.toString());
            return true;
        }
    }

    private static int b(int i, int i2, int i3) {
        return (i == 0 || i == 2) ? i2 : i3;
    }

    private static String b(Context context, boolean z) {
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            Method method2 = Build.VERSION.SDK_INT < 30 ? cls.getMethod("getPath", new Class[0]) : cls.getMethod("getDirectory", new Class[0]);
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Object objInvoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(objInvoke);
            for (int i = 0; i < length; i++) {
                Object obj = Array.get(objInvoke, i);
                String absolutePath = Build.VERSION.SDK_INT < 30 ? (String) method2.invoke(obj, new Object[0]) : ((File) method2.invoke(obj, new Object[0])).getAbsolutePath();
                if (z == ((Boolean) method3.invoke(obj, new Object[0])).booleanValue()) {
                    return absolutePath;
                }
            }
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return null;
        } catch (Exception e5) {
            e5.printStackTrace();
            return null;
        }
    }

    private static String c(Context context) {
        c cVar = new c();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String simOperator = telephonyManager.getSimOperator();
            if (!TextUtils.isEmpty(simOperator) && cVar.containsKey(simOperator)) {
                return (String) cVar.get(simOperator);
            }
            String simOperatorName = telephonyManager.getSimOperatorName();
            return !TextUtils.isEmpty(simOperatorName) ? simOperatorName : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int[] d(Context context) {
        Display defaultDisplay;
        int rotation;
        Point point;
        int height;
        int i;
        int[] iArr = new int[2];
        try {
            defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            rotation = defaultDisplay.getRotation();
            point = new Point();
        } catch (Exception unused) {
            if (context.getResources() != null) {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                iArr[0] = displayMetrics.widthPixels;
                iArr[1] = displayMetrics.heightPixels;
            }
        }
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            if (Build.VERSION.SDK_INT < 13) {
                int width = defaultDisplay.getWidth();
                height = defaultDisplay.getHeight();
                i = width;
                iArr[0] = b(rotation, i, height);
                iArr[1] = a(rotation, i, height);
                return iArr;
            }
            defaultDisplay.getSize(point);
        }
        i = point.x;
        height = point.y;
        iArr[0] = b(rotation, i, height);
        iArr[1] = a(rotation, i, height);
        return iArr;
    }

    public static k e(Context context) {
        k kVar;
        synchronized (n) {
            if (m == null) {
                m = new k(context, null);
            }
            kVar = m;
        }
        return kVar;
    }

    private Map<String, Object> f(Context context) {
        HashMap map = new HashMap();
        if (!TDPresetProperties.disableList.contains("#lib")) {
            map.put("#lib", k);
        }
        if (!TDPresetProperties.disableList.contains("#lib_version")) {
            map.put("#lib_version", l);
        }
        if (this.c != null && !TDPresetProperties.disableList.contains("#install_time")) {
            map.put("#install_time", new cn.thinkingdata.android.utils.o(new Date(this.b), this.c).b());
        }
        String strB = r.b();
        if (!TDPresetProperties.disableList.contains("#os")) {
            map.put("#os", TextUtils.isEmpty(strB) ? "Android" : "HarmonyOS");
        }
        if (!TDPresetProperties.disableList.contains("#os_version")) {
            if (TextUtils.isEmpty(strB)) {
                strB = Build.VERSION.RELEASE;
            }
            map.put("#os_version", strB);
        }
        if (!TDPresetProperties.disableList.contains("#bundle_id")) {
            map.put("#bundle_id", r.b(context));
        }
        if (!TDPresetProperties.disableList.contains("#manufacturer")) {
            map.put("#manufacturer", Build.MANUFACTURER);
        }
        if (!TDPresetProperties.disableList.contains("#device_model")) {
            map.put("#device_model", Build.MODEL);
        }
        int[] iArrD = d(context);
        if (!TDPresetProperties.disableList.contains("#screen_width")) {
            map.put("#screen_width", Integer.valueOf(iArrD[0]));
        }
        if (!TDPresetProperties.disableList.contains("#screen_height")) {
            map.put("#screen_height", Integer.valueOf(iArrD[1]));
        }
        if (!TDPresetProperties.disableList.contains("#carrier")) {
            map.put("#carrier", c(context));
        }
        if (!TDPresetProperties.disableList.contains("#device_id")) {
            map.put("#device_id", a(context));
        }
        if (!TDPresetProperties.disableList.contains("#system_language")) {
            map.put("#system_language", k());
        }
        if (!TextUtils.isEmpty(this.d)) {
            map.put("#app_version", this.d);
        }
        if (!TDPresetProperties.disableList.contains("#simulator")) {
            map.put("#simulator", Boolean.valueOf(cn.thinkingdata.android.utils.c.a()));
        }
        return Collections.unmodifiableMap(map);
    }

    static String i() {
        return k;
    }

    static String j() {
        return l;
    }

    private String k() {
        return (Build.VERSION.SDK_INT >= 24 ? LocaleList.getDefault().get(0) : Locale.getDefault()).getLanguage();
    }

    private void l() {
        if (Build.VERSION.SDK_INT >= 24) {
            ((ConnectivityManager) this.f.getSystemService("connectivity")).registerDefaultNetworkCallback(new a());
        } else {
            this.f.registerReceiver(new g(new b()), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    String a(Context context) {
        String strD = cn.thinkingdata.android.q.e.a(context).d();
        if (TextUtils.isEmpty(strD)) {
            Object objA = cn.thinkingdata.android.utils.j.a(cn.thinkingdata.android.utils.j.a("cn.thinkingdata.android.utils.TASensitiveInfo"), "getAndroidID", new Object[]{context}, Context.class);
            strD = objA == null ? "" : String.valueOf(objA);
            if (TextUtils.isEmpty(strD)) {
                strD = r.a(16);
            }
            try {
                if (Integer.parseInt(strD) == 0) {
                    strD = r.a(16);
                }
            } catch (Exception unused) {
            }
            cn.thinkingdata.android.q.e.a(context).a(strD);
        }
        return strD;
    }

    public String a(Context context, boolean z) {
        if (TextUtils.isEmpty(this.h)) {
            this.h = b(context, z);
        }
        if (TextUtils.isEmpty(this.h)) {
            return "0";
        }
        File file = new File(this.h);
        if (!file.exists()) {
            return "0";
        }
        StatFs statFs = new StatFs(file.getPath());
        if (Build.VERSION.SDK_INT < 18) {
            return "0";
        }
        long blockCountLong = statFs.getBlockCountLong();
        long availableBlocksLong = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        double dA = r.a((((blockCountLong * r1) / 1024.0d) / 1024.0d) / 1024.0d);
        return r.a(((availableBlocksLong / 1024.0d) / 1024.0d) / 1024.0d) + "/" + dA;
    }

    public JSONObject a() {
        if (this.e == null) {
            return new JSONObject();
        }
        JSONObject jSONObject = new JSONObject(this.e);
        jSONObject.remove("#lib");
        jSONObject.remove("#lib_version");
        return jSONObject;
    }

    String b() {
        return this.d;
    }

    public String b(Context context) {
        if (Build.VERSION.SDK_INT < 16) {
            return "0";
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        long j = memoryInfo.totalMem;
        long j2 = memoryInfo.availMem;
        double dA = r.a(((j / 1024.0d) / 1024.0d) / 1024.0d);
        return r.a(((j2 / 1024.0d) / 1024.0d) / 1024.0d) + "/" + dA;
    }

    String c() {
        if ((this.j && "NULL".equals(this.i)) || this.i == null) {
            String strF = f();
            this.i = strF;
            if (!"NULL".equals(strF)) {
                this.j = true;
            }
        }
        return this.i;
    }

    public Map<String, Object> d() {
        return this.e;
    }

    public long e() {
        return this.b;
    }

    String f() {
        NetworkInfo networkInfo;
        try {
            if (!this.g) {
                return "NULL";
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f.getSystemService("connectivity");
            return (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(1)) == null || !networkInfo.isConnectedOrConnecting()) ? a(this.f, (TelephonyManager) this.f.getSystemService("phone"), connectivityManager) : "WIFI";
        } catch (Exception unused) {
            return "NULL";
        }
    }

    public boolean g() {
        return this.a;
    }

    boolean h() {
        if (!this.g) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
