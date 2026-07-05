package com.alipay.sdk.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.data.a;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class n {
    static final String a = "com.eg.android.AlipayGphone";
    static final int b = 125;
    private static final String c = "com.alipay.android.app";
    private static final String d = "com.eg.android.AlipayGphoneRC";
    private static final int e = 99;
    private static final String[] f = {"10.1.5.1013151", "10.1.5.1013148"};

    public static String g(Context context) {
        return "-1;-1";
    }

    static String a() {
        if (EnvUtils.isSandBox()) {
            return d;
        }
        try {
            return com.alipay.sdk.app.i.a.get(0).a;
        } catch (Throwable unused) {
            return a;
        }
    }

    static String a(String str) {
        return (EnvUtils.isSandBox() && TextUtils.equals(str, d)) ? "com.eg.android.AlipayGphoneRC.IAlixPay" : "com.eg.android.AlipayGphone.IAlixPay";
    }

    public static Map<String, String> b(String str) {
        HashMap map = new HashMap();
        for (String str2 : str.split(com.alipay.sdk.sys.a.b)) {
            int iIndexOf = str2.indexOf("=", 1);
            if (-1 != iIndexOf) {
                map.put(str2.substring(0, iIndexOf), URLDecoder.decode(str2.substring(iIndexOf + 1)));
            }
        }
        return map;
    }

    public static Map<String, String> c(String str) {
        HashMap map = new HashMap(4);
        int iIndexOf = str.indexOf(63);
        if (iIndexOf != -1 && iIndexOf < str.length() - 1) {
            for (String str2 : str.substring(iIndexOf + 1).split(com.alipay.sdk.sys.a.b)) {
                int iIndexOf2 = str2.indexOf(61, 1);
                if (iIndexOf2 != -1 && iIndexOf2 < str2.length() - 1) {
                    map.put(str2.substring(0, iIndexOf2), e(str2.substring(iIndexOf2 + 1)));
                }
            }
        }
        return map;
    }

    public static JSONObject d(String str) {
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }

    public static String e(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.u, e2);
            return "";
        }
    }

    public static String a(String str, String str2, String str3) {
        try {
            int iIndexOf = str3.indexOf(str) + str.length();
            if (iIndexOf <= str.length()) {
                return "";
            }
            int iIndexOf2 = TextUtils.isEmpty(str2) ? 0 : str3.indexOf(str2, iIndexOf);
            if (iIndexOf2 < 1) {
                return str3.substring(iIndexOf);
            }
            return str3.substring(iIndexOf, iIndexOf2);
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(byte[] bArr) {
        BigInteger modulus;
        try {
            PublicKey publicKey = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey();
            if (!(publicKey instanceof RSAPublicKey) || (modulus = ((RSAPublicKey) publicKey).getModulus()) == null) {
                return null;
            }
            return modulus.toString(16);
        } catch (Exception e2) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.d, com.alipay.sdk.app.statistic.c.o, e2);
            return null;
        }
    }

    public static a a(Context context, List<a.C0008a> list) {
        a aVarA;
        if (list == null) {
            return null;
        }
        for (a.C0008a c0008a : list) {
            if (c0008a != null && (aVarA = a(context, c0008a.a, c0008a.b, c0008a.c)) != null && !aVarA.a() && !aVarA.b()) {
                return aVarA;
            }
        }
        return null;
    }

    private static a a(Context context, String str, int i, String str2) {
        PackageInfo packageInfoB;
        if (EnvUtils.isSandBox() && a.equals(str)) {
            str = d;
        }
        try {
            packageInfoB = b(context, str);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.d, com.alipay.sdk.app.statistic.c.l, th);
            packageInfoB = null;
        }
        if (b(packageInfoB)) {
            return a(packageInfoB, i, str2);
        }
        return null;
    }

    private static boolean b(PackageInfo packageInfo) {
        String str = "";
        boolean z = false;
        if (packageInfo == null) {
            str = "info == null";
        } else if (packageInfo.signatures == null) {
            str = "info.signatures == null";
        } else if (packageInfo.signatures.length <= 0) {
            str = "info.signatures.length <= 0";
        } else {
            z = true;
        }
        if (!z) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.d, com.alipay.sdk.app.statistic.c.m, str);
        }
        return z;
    }

    private static PackageInfo b(Context context, String str) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, 192);
    }

    private static a a(PackageInfo packageInfo, int i, String str) {
        if (packageInfo == null) {
            return null;
        }
        return new a(packageInfo, i, str);
    }

    public static final class a {
        public final PackageInfo a;
        public final int b;
        public final String c;

        public a(PackageInfo packageInfo, int i, String str) {
            this.a = packageInfo;
            this.b = i;
            this.c = str;
        }

        public boolean a() {
            Signature[] signatureArr = this.a.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return false;
            }
            for (Signature signature : signatureArr) {
                String strB = n.b(signature.toByteArray());
                if (strB != null && !TextUtils.equals(strB, this.c)) {
                    com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.w, String.format("Got %s, expected %s", strB, this.c));
                    return true;
                }
            }
            return false;
        }

        public boolean b() {
            return this.a.versionCode < this.b;
        }
    }

    public static boolean a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(c, 128) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean b(Context context, List<a.C0008a> list) {
        try {
            for (a.C0008a c0008a : list) {
                if (c0008a != null) {
                    String str = c0008a.a;
                    if (EnvUtils.isSandBox() && a.equals(str)) {
                        str = d;
                    }
                    try {
                        if (context.getPackageManager().getPackageInfo(str, 128) != null) {
                            return true;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        continue;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.K, th);
            return false;
        }
    }

    static boolean a(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        try {
            String str = packageInfo.versionName;
            if (!TextUtils.equals(str, f[0])) {
                if (!TextUtils.equals(str, f[1])) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a(), 128);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode < 99;
        } catch (Throwable th) {
            c.a(th);
            return false;
        }
    }

    public static String c(Context context) {
        return " (" + b() + i.b + c() + i.b + d(context) + i.b + i.b + e(context) + ")(sdk android)";
    }

    public static String b() {
        return "Android " + Build.VERSION.RELEASE;
    }

    public static String c() {
        String strD = d();
        int iIndexOf = strD.indexOf("-");
        if (iIndexOf != -1) {
            strD = strD.substring(0, iIndexOf);
        }
        int iIndexOf2 = strD.indexOf("\n");
        if (iIndexOf2 != -1) {
            strD = strD.substring(0, iIndexOf2);
        }
        return "Linux " + strD;
    }

    private static String d() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            try {
                String line = bufferedReader.readLine();
                bufferedReader.close();
                Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(line);
                if (!matcher.matches() || matcher.groupCount() < 4) {
                    return "Unavailable";
                }
                return matcher.group(1) + "\n" + matcher.group(2) + " " + matcher.group(3) + "\n" + matcher.group(4);
            } catch (Throwable th) {
                bufferedReader.close();
                throw th;
            }
        } catch (IOException unused) {
            return "Unavailable";
        }
    }

    public static String d(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static String e(Context context) {
        DisplayMetrics displayMetricsI = i(context);
        return displayMetricsI.widthPixels + "*" + displayMetricsI.heightPixels;
    }

    private static DisplayMetrics i(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String f(Context context) {
        String strA = m.a(context);
        return strA.substring(0, strA.indexOf("://"));
    }

    public static String a(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            int iNextInt = random.nextInt(3);
            if (iNextInt == 0) {
                sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 65.0d)));
            } else if (iNextInt == 1) {
                sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 97.0d)));
            } else if (iNextInt == 2) {
                sb.append(String.valueOf(new Random().nextInt(10)));
            }
        }
        return sb.toString();
    }

    public static boolean f(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    static String a(Context context, String str) {
        String strSubstring = "";
        try {
            String string = "";
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    string = string + "#M";
                } else {
                    if (runningAppProcessInfo.processName.startsWith(str + ":")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(string);
                        sb.append("#");
                        sb.append(runningAppProcessInfo.processName.replace(str + ":", ""));
                        string = sb.toString();
                    }
                }
            }
            strSubstring = string;
        } catch (Throwable unused) {
        }
        if (strSubstring.length() > 0) {
            strSubstring = strSubstring.substring(1);
        }
        return strSubstring.length() == 0 ? "N" : strSubstring;
    }

    public static boolean a(WebView webView, String str, Activity activity) {
        String strSubstring;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (activity == null) {
            return false;
        }
        if (str.toLowerCase().startsWith(com.alipay.sdk.cons.a.j.toLowerCase()) || str.toLowerCase().startsWith(com.alipay.sdk.cons.a.k.toLowerCase())) {
            try {
                a aVarA = a(activity, com.alipay.sdk.app.i.a);
                if (aVarA != null && !aVarA.b() && !aVarA.a()) {
                    if (str.startsWith("intent://platformapi/startapp")) {
                        str = str.replaceFirst("intent://platformapi/startapp\\?", com.alipay.sdk.cons.a.j);
                    }
                    activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            } catch (Throwable unused) {
            }
            return true;
        }
        if (TextUtils.equals(str, com.alipay.sdk.cons.a.m) || TextUtils.equals(str, com.alipay.sdk.cons.a.n)) {
            com.alipay.sdk.app.j.a(com.alipay.sdk.app.j.c());
            activity.finish();
            return true;
        }
        if (!str.startsWith(com.alipay.sdk.cons.a.l)) {
            return false;
        }
        try {
            String strSubstring2 = str.substring(str.indexOf(com.alipay.sdk.cons.a.l) + 24);
            int i = Integer.parseInt(strSubstring2.substring(strSubstring2.lastIndexOf(com.alipay.sdk.cons.a.o) + 10));
            if (i == com.alipay.sdk.app.k.SUCCEEDED.a() || i == com.alipay.sdk.app.k.PAY_WAITTING.a()) {
                if (com.alipay.sdk.cons.a.s) {
                    StringBuilder sb = new StringBuilder();
                    String strDecode = URLDecoder.decode(str);
                    String strDecode2 = URLDecoder.decode(strDecode);
                    String str2 = strDecode2.substring(strDecode2.indexOf(com.alipay.sdk.cons.a.l) + 24, strDecode2.lastIndexOf(com.alipay.sdk.cons.a.o)).split(com.alipay.sdk.cons.a.q)[0];
                    int iIndexOf = strDecode.indexOf(com.alipay.sdk.cons.a.q) + 12;
                    sb.append(str2);
                    sb.append(com.alipay.sdk.cons.a.q);
                    sb.append(strDecode.substring(iIndexOf, strDecode.indexOf(com.alipay.sdk.sys.a.b, iIndexOf)));
                    sb.append(strDecode.substring(strDecode.indexOf(com.alipay.sdk.sys.a.b, iIndexOf)));
                    strSubstring = sb.toString();
                } else {
                    String strDecode3 = URLDecoder.decode(str);
                    strSubstring = strDecode3.substring(strDecode3.indexOf(com.alipay.sdk.cons.a.l) + 24, strDecode3.lastIndexOf(com.alipay.sdk.cons.a.o));
                }
                com.alipay.sdk.app.k kVarB = com.alipay.sdk.app.k.b(i);
                com.alipay.sdk.app.j.a(com.alipay.sdk.app.j.a(kVarB.a(), kVarB.b(), strSubstring));
            } else {
                com.alipay.sdk.app.k kVarB2 = com.alipay.sdk.app.k.b(com.alipay.sdk.app.k.FAILED.a());
                com.alipay.sdk.app.j.a(com.alipay.sdk.app.j.a(kVarB2.a(), kVarB2.b(), ""));
            }
        } catch (Exception unused2) {
            com.alipay.sdk.app.j.a(com.alipay.sdk.app.j.e());
        }
        activity.runOnUiThread(new o(activity));
        return true;
    }

    public static String h(Context context) {
        return c(context, context.getPackageName());
    }

    private static String c(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 128).versionName;
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.l, th);
            return "";
        }
    }
}
