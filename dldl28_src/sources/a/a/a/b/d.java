package a.a.a.b;

import a.a.a.b.e;
import a.a.a.c.k;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebSettings;
import com.hjq.permissions.Permission;
import com.volcengine.common.contant.CommonConstants;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.InvocationTargetException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: TurboReporter.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f70a;
    public static String b;
    public static String c;
    public static SharedPreferences d;

    public static void a(Context context) {
        if (d == null && context != null) {
            d = context.getSharedPreferences("ks_turbo_sdk_pref", 0);
        }
    }

    public static /* synthetic */ boolean a() {
        return true;
    }

    public static boolean a(long j, long j2) {
        return ((1 & j) == 0 || (j & j2) == 0) ? false : true;
    }

    public static synchronized boolean b() {
        return a(e.c.INSTANCE.f74a.c, "ks_upload_origin_oaid", true);
    }

    public static boolean c(String str) {
        return str == null || str.length() == 0 || "null".equalsIgnoreCase(str);
    }

    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            int length = bArrDigest.length;
            for (int i = 0; i < length; i++) {
                int i2 = bArrDigest[i];
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i2));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(String str, String str2) {
        try {
            return a((str + "nte64QXH").replaceAll("X", "M").replaceAll("L", "N"), str2);
        } catch (Throwable unused) {
            return str2;
        }
    }

    public static String a(Context context, String str) {
        a(context);
        SharedPreferences sharedPreferences = d;
        return sharedPreferences != null ? sharedPreferences.getString(str, "") : "";
    }

    public static void a(JSONObject jSONObject, String str, double d2) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            jSONObject.put(str, d2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String b(String str) throws Throwable {
        String line;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            line = (String) cls.getDeclaredMethod("get", String.class).invoke(cls, str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            line = null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            line = null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            line = null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            line = null;
        }
        try {
            if (TextUtils.isEmpty(line)) {
                try {
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
                    } catch (IOException e5) {
                        e = e5;
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    line = bufferedReader.readLine();
                    bufferedReader.close();
                } catch (IOException e6) {
                    e = e6;
                    bufferedReader2 = bufferedReader;
                    e.printStackTrace();
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    return line;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException unused) {
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException unused2) {
        }
        return line;
    }

    public static void a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        a(context);
        SharedPreferences sharedPreferences = d;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(str, str2).apply();
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, String str, long j) {
        a(context);
        SharedPreferences sharedPreferences = d;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong(str, j).apply();
        }
    }

    public static void a(JSONObject jSONObject) throws Throwable {
        int i;
        int i2;
        int i3;
        int offset;
        String strA;
        String str;
        String strA2;
        Enumeration<NetworkInterface> networkInterfaces;
        String strA3;
        WifiInfo connectionInfo;
        String strA4;
        a(jSONObject, "eventTimestamp", System.currentTimeMillis());
        a(jSONObject, "sdkVersion", -1.0d);
        a(jSONObject, "sdkVersionName", "1.0.15");
        a(jSONObject, CommonConstants.key_appId, e.c.INSTANCE.f74a.d);
        a(jSONObject, "appName", e.c.INSTANCE.f74a.e);
        a(jSONObject, "appChannel", e.c.INSTANCE.f74a.f);
        a(jSONObject, "packageName", e.c.INSTANCE.f74a.g);
        a(jSONObject, "osType", "Android");
        a(jSONObject, "osVersion", Build.VERSION.RELEASE);
        a(jSONObject, "deviceModel", Build.MODEL);
        a(jSONObject, "deviceBrand", Build.BRAND);
        a(jSONObject, "deviceManufacturer", Build.MANUFACTURER);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (b == null) {
            a("");
        }
        sb.append(b);
        sb.append(StringUtils.SPACE);
        if (c == null) {
            a("");
        }
        sb.append(c);
        a(jSONObject, "rom", sb.toString());
        a(jSONObject, "cpuAbi", Build.CPU_ABI);
        Context context = e.c.INSTANCE.f74a.c;
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.densityDpi;
        } catch (Exception unused) {
            i = 0;
        }
        a(jSONObject, "densityDpi", i);
        Context context2 = e.c.INSTANCE.f74a.c;
        try {
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            ((WindowManager) context2.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics2);
            i2 = displayMetrics2.heightPixels;
        } catch (Exception unused2) {
            i2 = 0;
        }
        a(jSONObject, "displayH", i2);
        Context context3 = e.c.INSTANCE.f74a.c;
        try {
            DisplayMetrics displayMetrics3 = new DisplayMetrics();
            ((WindowManager) context3.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics3);
            i3 = displayMetrics3.widthPixels;
        } catch (Exception unused3) {
            i3 = 0;
        }
        a(jSONObject, "displayW", i3);
        a(jSONObject, IjkMediaMeta.IJKM_KEY_LANGUAGE, Locale.getDefault().getLanguage());
        try {
            offset = TimeZone.getDefault().getOffset(15L) / 3600000;
        } catch (Exception unused4) {
            offset = 0;
        }
        a(jSONObject, "timezone", offset);
        a(jSONObject, "region", Locale.getDefault().getCountry());
        Context context4 = e.c.INSTANCE.f74a.c;
        if (context4 != null && TextUtils.isEmpty(a.a.a.g.d.b)) {
            String strD = d(a.a.a.g.d.a(context4));
            a.a.a.g.d.b = strD;
            strA = a.a.a.g.d.a(strD);
        } else {
            strA = a.a.a.g.d.b;
        }
        a(jSONObject, "imei", strA);
        a(jSONObject, "globalId", e.i());
        Context context5 = e.c.INSTANCE.f74a.c;
        if (context5 != null && TextUtils.isEmpty(a.a.a.g.d.e)) {
            try {
                strA4 = a(context5, "ks_system_ua");
                a.a.a.g.d.e = strA4;
            } catch (Exception unused5) {
                a.a.a.g.b.a("SystemUtils", "getSysUa fail");
            }
            if (!TextUtils.isEmpty(strA4)) {
                str = a.a.a.g.d.e;
            } else {
                a.a.a.g.d.e = WebSettings.getDefaultUserAgent(context5);
                a(context5, "ks_system_ua", a.a.a.g.d.e);
                str = a.a.a.g.d.e;
            }
        } else {
            str = a.a.a.g.d.e;
        }
        a(jSONObject, "ua", str);
        if (b()) {
            a(jSONObject, "oaid", e.j());
        }
        a(jSONObject, "oaidMd5", d(e.j()));
        Context context6 = e.c.INSTANCE.f74a.c;
        if (context6 != null && TextUtils.isEmpty(a.a.a.g.d.c)) {
            try {
                if (context6.checkPermission(Permission.READ_PHONE_STATE, Process.myPid(), Process.myUid()) == 0 && (connectionInfo = ((WifiManager) context6.getApplicationContext().getSystemService("wifi")).getConnectionInfo()) != null) {
                    a.a.a.g.d.c = connectionInfo.getMacAddress();
                }
                if (TextUtils.isEmpty(a.a.a.g.d.c) || a.a.a.g.d.c.equals("02:00:00:00:00:00")) {
                    Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        NetworkInterface networkInterface = (NetworkInterface) it.next();
                        if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                            byte[] hardwareAddress = networkInterface.getHardwareAddress();
                            if (hardwareAddress != null && hardwareAddress.length != 0) {
                                StringBuilder sb2 = new StringBuilder();
                                for (byte b2 : hardwareAddress) {
                                    sb2.append(String.format("%02X:", Byte.valueOf(b2)));
                                }
                                if (sb2.length() > 0) {
                                    sb2.deleteCharAt(sb2.length() - 1);
                                }
                                a.a.a.g.d.c = sb2.toString();
                            }
                        }
                    }
                }
                if (TextUtils.isEmpty(a.a.a.g.d.c) || a.a.a.g.d.c.equals("02:00:00:00:00:00")) {
                    a.a.a.g.d.c = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ").getInputStream())).readLine();
                }
                a.a.a.g.d.c = d(a.a.a.g.d.c.toUpperCase(Locale.US));
            } catch (Exception e) {
                a.a.a.g.b.c("SystemUtils", "getMacAddress exception:" + e.toString());
            }
            strA2 = a.a.a.g.d.a(a.a.a.g.d.c);
        } else {
            strA2 = a.a.a.g.d.c;
        }
        a(jSONObject, "mac", strA2);
        String hostAddress = a.a.a.g.d.f;
        if (hostAddress != null) {
            str2 = hostAddress;
            break loop1;
        }
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (Throwable unused6) {
        }
        loop1: while (networkInterfaces.hasMoreElements()) {
            Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddressNextElement = inetAddresses.nextElement();
                if ((inetAddressNextElement instanceof Inet4Address) && !inetAddressNextElement.isLoopbackAddress()) {
                    hostAddress = inetAddressNextElement.getHostAddress();
                    a.a.a.g.d.f = hostAddress;
                    str2 = hostAddress;
                    break loop1;
                }
                a.a.a.g.d.f = "";
            }
        }
        a.a.a.g.d.f = "";
        a(jSONObject, "clientIp", str2);
        a(jSONObject, "netType", a.a.a.g.c.a(e.c.INSTANCE.f74a.c));
        Context context7 = e.c.INSTANCE.f74a.c;
        if (context7 != null && TextUtils.isEmpty(a.a.a.g.d.d)) {
            try {
                a.a.a.g.d.d = d(Settings.Secure.getString(context7.getContentResolver(), "android_id"));
            } catch (Exception unused7) {
            }
            strA3 = a.a.a.g.d.a(a.a.a.g.d.d);
        } else {
            strA3 = a.a.a.g.d.d;
        }
        a(jSONObject, "androidId", strA3);
        Context context8 = e.c.INSTANCE.f74a.c;
        JSONArray jSONArray = a.a.a.g.d.g;
        if (jSONArray == null) {
            jSONArray = new JSONArray();
            if (context8 != null) {
                try {
                    PackageManager packageManager = context8.getPackageManager();
                    Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
                    intent.addCategory("android.intent.category.LAUNCHER");
                    for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 32)) {
                        if (!TextUtils.isEmpty(resolveInfo.activityInfo.packageName)) {
                            JSONObject jSONObject2 = new JSONObject();
                            a(jSONObject2, "pkgName", resolveInfo.activityInfo.packageName);
                            if (resolveInfo.activityInfo.applicationInfo != null) {
                                if ((resolveInfo.activityInfo.applicationInfo.flags & 1) == 0 && (resolveInfo.activityInfo.applicationInfo.flags & 128) == 0) {
                                    a(jSONObject2, "systemApp", 0.0d);
                                } else {
                                    a(jSONObject2, "systemApp", 1.0d);
                                }
                            }
                            a(jSONObject2, "appVersion", packageManager.getPackageInfo(resolveInfo.activityInfo.packageName, 0).versionName);
                            jSONArray.put(jSONObject2);
                        }
                    }
                } catch (Exception unused8) {
                }
                a.a.a.g.d.g = jSONArray;
            }
        }
        if (jSONObject != null && !TextUtils.isEmpty("appList")) {
            try {
                jSONObject.put("appList", jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        a(jSONObject, "callback", e.h());
    }

    public static boolean a(Context context, String str, boolean z) {
        a(context);
        SharedPreferences sharedPreferences = d;
        return sharedPreferences != null ? sharedPreferences.getBoolean(str, z) : z;
    }

    public static synchronized void a(boolean z) {
        a(e.c.INSTANCE.f74a.c);
        SharedPreferences sharedPreferences = d;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean("ks_upload_origin_oaid", z).apply();
        }
    }

    public static boolean a(String str) throws Throwable {
        String str2 = b;
        if (str2 != null) {
            return str2.contains(str);
        }
        String strB = b("ro.miui.ui.version.name");
        c = strB;
        if (!TextUtils.isEmpty(strB)) {
            b = "MIUI";
        } else {
            String strB2 = b("ro.build.version.emui");
            c = strB2;
            if (!TextUtils.isEmpty(strB2)) {
                b = "EMUI";
            } else {
                String strB3 = b("ro.build.version.opporom");
                c = strB3;
                if (!TextUtils.isEmpty(strB3)) {
                    b = "OPPO";
                } else {
                    String strB4 = b("ro.vivo.os.version");
                    c = strB4;
                    if (!TextUtils.isEmpty(strB4)) {
                        b = "VIVO";
                    } else {
                        String strB5 = b("ro.smartisan.version");
                        c = strB5;
                        if (!TextUtils.isEmpty(strB5)) {
                            b = "SMARTISAN";
                        } else {
                            String str3 = Build.DISPLAY;
                            c = str3;
                            if (str3.toUpperCase().contains("FLYME")) {
                                b = "FLYME";
                            } else {
                                c = "unknown";
                                b = Build.MANUFACTURER.toUpperCase();
                            }
                        }
                    }
                }
            }
        }
        return b.contains(str);
    }

    public static /* synthetic */ boolean a(a.a.a.c.m.b bVar) {
        if (!TextUtils.isEmpty(bVar.e)) {
            String strB = b(bVar.f, bVar.e);
            if (!TextUtils.isEmpty(strB)) {
                try {
                    Class.forName(strB);
                    return true;
                } catch (ClassNotFoundException unused) {
                }
            }
        }
        return false;
    }

    public static /* synthetic */ boolean a(Context context, a.a.a.c.m.b bVar) {
        if (!a(bVar.f93a, 16L)) {
            try {
                if ((context.getApplicationInfo().flags & 2) != 0) {
                    a.a.a.c.d.a("Manager", "debug return");
                    return true;
                }
            } catch (Throwable th) {
                a.a.a.c.d.d("Manager", "debug check \n" + Log.getStackTraceString(th));
                return true;
            }
        }
        return false;
    }

    public static String a(String str, String str2) {
        byte[] bArrDoFinal;
        try {
            byte[] bArrA = a.a.a.c.n.b.a().a(str2.getBytes());
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(StandardCharsets.UTF_8), "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(2, secretKeySpec);
                bArrDoFinal = cipher.doFinal(bArrA);
            } catch (Exception e) {
                a.a.a.c.d.b("Utils", Log.getStackTraceString(e));
                bArrDoFinal = new byte[0];
            }
            return new String(a(bArrDoFinal), StandardCharsets.UTF_8);
        } catch (Exception e2) {
            a.a.a.c.d.b(str, Log.getStackTraceString(e2));
            return str2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r8v5 */
    public static byte[] a(byte[] bArr) throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        GZIPInputStream gZIPInputStream;
        Throwable th2;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
        } catch (Throwable th3) {
            byteArrayInputStream = null;
            th = th3;
            bArr = 0;
        }
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                    try {
                        byte[] bArr2 = new byte[1024];
                        while (true) {
                            int i = gZIPInputStream.read(bArr2, 0, 1024);
                            if (i != -1) {
                                byteArrayOutputStream.write(bArr2, 0, i);
                            } else {
                                a(gZIPInputStream);
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                byteArrayOutputStream.flush();
                                a(byteArrayOutputStream);
                                a(byteArrayInputStream);
                                return byteArray;
                            }
                        }
                    } catch (Throwable th4) {
                        th2 = th4;
                        a(gZIPInputStream);
                        throw th2;
                    }
                } catch (Throwable th5) {
                    gZIPInputStream = null;
                    th2 = th5;
                }
            } catch (Throwable th6) {
                th = th6;
                a((Closeable) bArr);
                a(byteArrayInputStream);
                throw th;
            }
        } catch (Throwable th7) {
            th = th7;
            bArr = 0;
            a((Closeable) bArr);
            a(byteArrayInputStream);
            throw th;
        }
    }

    public static /* synthetic */ void a(Application application, a.a.a.c.m.b bVar, a.a.a.a aVar) {
        if (a.a.a.c.h.a("manager start")) {
            return;
        }
        if (new Random().nextFloat() >= bVar.b) {
            a.a.a.c.d.a("Manager", "config.sampleRate：" + bVar.b + " return");
            return;
        }
        if (a(bVar.f93a, 2L)) {
            try {
                new k().a(application, bVar, new a.a.a.c.g(aVar));
            } catch (Exception e) {
                a.a.a.c.d.d("Manager", Log.getStackTraceString(e));
            }
        }
        if (a(bVar.f93a, 4L)) {
            try {
                new a.a.a.c.c().a(application, bVar, new a.a.a.c.f(aVar));
            } catch (Throwable th) {
                a.a.a.c.d.b("Manager", Log.getStackTraceString(th));
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}
