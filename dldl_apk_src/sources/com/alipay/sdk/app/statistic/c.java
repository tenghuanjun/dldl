package com.alipay.sdk.app.statistic;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class c {
    public static final String A = "ClientBindException";
    public static final String B = "SaveTradeTokenError";
    public static final String C = "ClientBindServiceFailed";
    public static final String D = "BindWaitTimeoutEx";
    public static final String E = "CheckClientExistEx";
    public static final String F = "CheckClientSignEx";
    public static final String G = "GetInstalledAppEx";
    public static final String H = "ParserTidClientKeyEx";
    public static final String I = "GetInstalledAppEx";
    public static final String J = "StartLaunchAppTransEx";
    public static final String K = "CheckLaunchAppExistEx";
    public static final String L = "LogCurrentAppLaunchSwitch";
    public static final String M = "LogCurrentQueryTime";
    public static final String N = "LogCalledPackage";
    public static final String O = "LogBindCalledH5";
    public static final String P = "LogCalledH5";
    public static final String Q = "LogHkLoginByIntent";
    public static final String R = "SchemePayWrongHashEx";
    public static final String S = "LogAppLaunchSwitchEnabled";
    public static final String T = "H5CbUrlEmpty";
    public static final String U = "H5CbEx";
    public static final String V = "BuildSchemePayUriError";
    public static final String W = "StartActivityEx";
    public static final String X = "JSONEx";
    public static final String Y = "ParseBundleSerializableError";
    public static final String Z = "ParseSchemeQueryError";
    public static final String a = "net";
    public static final String aa = "tid_context_null";
    public static final String ab = "partner";
    public static final String ac = "out_trade_no";
    public static final String ad = "trade_no";
    public static final String b = "biz";
    public static final String c = "cp";
    public static final String d = "auth";
    public static final String e = "third";
    public static final String f = "tid";
    public static final String g = "FormatResultEx";
    public static final String h = "GetApdidEx";
    public static final String i = "GetApdidNull";
    public static final String j = "GetApdidTimeout";
    public static final String k = "GetUtdidEx";
    public static final String l = "GetPackageInfoEx";
    public static final String m = "NotIncludeSignatures";
    public static final String n = "GetInstalledPackagesEx";
    public static final String o = "GetPublicKeyFromSignEx";
    public static final String p = "H5PayNetworkError";
    public static final String q = "H5AuthNetworkError";
    public static final String r = "SSLError";
    public static final String s = "SSLProceed";
    public static final String t = "SSLDenied";
    public static final String u = "H5PayDataAnalysisError";
    public static final String v = "H5AuthDataAnalysisError";
    public static final String w = "PublicKeyUnmatch";
    public static final String x = "ClientBindFailed";
    public static final String y = "TriDesEncryptError";
    public static final String z = "TriDesDecryptError";
    private String ae;
    private String af;
    private String ag;
    private String ah;
    private String ai;
    private String aj;
    private String ak;
    private String al;
    private String am = "";
    private String an;

    public c(Context context) {
        context = context != null ? context.getApplicationContext() : context;
        this.ae = b();
        this.ag = a(context);
        this.ah = c();
        this.ai = d();
        this.aj = b(context);
        this.ak = "-";
        this.al = "-";
        this.an = "-";
    }

    public boolean a() {
        return TextUtils.isEmpty(this.am);
    }

    public void a(String str, String str2, Throwable th) {
        a(str, str2, a(th));
    }

    public void a(String str, String str2, Throwable th, String str3) {
        a(str, str2, a(th), str3);
    }

    public void a(String str, String str2, String str3, String str4) {
        this.am += ((!TextUtils.isEmpty(this.am) ? "^" : "") + String.format("%s,%s,%s,%s", str, str2, b(str3), str4));
    }

    public void a(String str, String str2, String str3) {
        a(str, str2, str3, "-");
    }

    private String b(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(com.igexin.push.core.b.aj, "，").replace("-", "=").replace("^", "~");
    }

    private String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName());
            stringBuffer.append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString() + " 》 ");
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }

    public String a(String str) {
        if (a()) {
            return "";
        }
        String strC = c(str);
        this.af = strC;
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", this.ae, strC, this.ag, this.ah, this.ai, this.aj, this.ak, this.al, this.am, this.an);
    }

    private String b() {
        return String.format("123456789,%s", new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
    }

    private String c(String str) {
        String str2;
        String[] strArrSplit = str.split(com.alipay.sdk.sys.a.b);
        String strReplace = null;
        if (strArrSplit != null) {
            String strReplace2 = null;
            for (String str3 : strArrSplit) {
                String[] strArrSplit2 = str3.split("=");
                if (strArrSplit2 != null && strArrSplit2.length == 2) {
                    if (strArrSplit2[0].equalsIgnoreCase(ab)) {
                        strArrSplit2[1].replace("\"", "");
                    } else if (strArrSplit2[0].equalsIgnoreCase(ac)) {
                        strReplace = strArrSplit2[1].replace("\"", "");
                    } else if (strArrSplit2[0].equalsIgnoreCase(ad)) {
                        strReplace2 = strArrSplit2[1].replace("\"", "");
                    }
                }
            }
            str2 = strReplace;
            strReplace = strReplace2;
        } else {
            str2 = null;
        }
        String strB = b(strReplace);
        String strB2 = b(str2);
        return String.format("%s,%s,-,%s,-,-,-", strB, strB2, b(strB2));
    }

    private String a(Context context) {
        String packageName;
        String str;
        String str2 = "-";
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                packageName = applicationContext.getPackageName();
                try {
                    str2 = applicationContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
                packageName = "-";
            }
            str = str2;
            str2 = packageName;
        } else {
            str = "-";
        }
        return String.format("%s,%s,-,-,-", str2, str);
    }

    private String c() {
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", b("15.6.5"), b("h.a.3.6.5"));
    }

    private String d() {
        return String.format("%s,%s,-,-,-", b(com.alipay.sdk.tid.b.a(com.alipay.sdk.sys.b.a().b()).a()), b(com.alipay.sdk.sys.b.a().e()));
    }

    private String b(Context context) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", b(com.alipay.sdk.util.a.d(context)), "android", b(Build.VERSION.RELEASE), b(Build.MODEL), "-", b(com.alipay.sdk.util.a.a(context).a()), b(com.alipay.sdk.util.a.b(context).b()), "gw", b(com.alipay.sdk.util.a.a(context).b()));
    }
}
