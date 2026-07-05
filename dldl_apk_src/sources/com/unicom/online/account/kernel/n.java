package com.unicom.online.account.kernel;

import android.content.Context;
import com.unionpay.tsmservice.mini.data.Constant;
import java.security.Security;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class n {
    private static volatile n c;
    public Context a;
    public ExecutorService b = Executors.newSingleThreadExecutor();

    private n() {
    }

    static /* synthetic */ int a(int i) {
        return Math.abs(new Random().nextInt() % i);
    }

    public static n a() {
        if (c == null) {
            synchronized (n.class) {
                if (c == null) {
                    c = new n();
                }
            }
        }
        return c;
    }

    public static String a(Context context, String str, String str2) {
        return ac.a(context, str, str2);
    }

    public static void a(m mVar, String str) {
        c.d(str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constant.KEY_RESULT_CODE, 1);
            jSONObject.put("resultMsg", str);
            jSONObject.put("resultData", "");
            jSONObject.put("seq", "");
            jSONObject.put("operatorType", "CU");
            mVar.onResult(jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(boolean z) {
        c.a(z);
    }

    public static boolean a(Context context) {
        return ad.a(context) && ad.c(context);
    }

    public static boolean a(Context context, String str, String str2, String str3) {
        return ac.a(context, str, str2, str3);
    }

    public static boolean a(String str) {
        if (!str.equalsIgnoreCase("ali.wosms.cn") && !str.equalsIgnoreCase("msv6.wosms.cn") && !str.equalsIgnoreCase("m.zzx.cnklog.com")) {
            return false;
        }
        ab.e = str;
        return true;
    }

    public static String b() {
        return ab.a();
    }

    public static void b(Context context) {
        ad.b(context);
    }

    public static String c() {
        return ab.b();
    }

    public static void c(Context context) {
        ad.d(context);
    }

    public static String d() {
        if (Security.getProvider("BC") == null) {
            return "Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) is null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Security.getProvider("BC").getVersion());
        return sb.toString();
    }

    public static boolean d(Context context) {
        int iB = e.b(context);
        return iB == 0 || iB == 1;
    }

    public static String e() {
        return ab.e;
    }

    public static void e(Context context) {
        ac.a(context);
    }

    public static String f() {
        return "auth.wosms.cn";
    }

    public static String g() {
        ab.e = "msv6.wosms.cn";
        return "msv6.wosms.cn";
    }

    public static void h() {
        ae.a().b();
    }
}
