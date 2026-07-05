package com.alipay.sdk.tid;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.alipay.sdk.util.c;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class TidHelper {
    public static Tid loadTID(Context context) {
        a(context);
        Tid tidA = a(context, b.a(context));
        if (tidA == null) {
            c.b("TidHelper.loadTID", "TidHelper:::loadTID > null");
        } else {
            c.b("TidHelper.loadTID", "TidHelper:::loadTID > " + tidA.toString());
        }
        return tidA;
    }

    public static synchronized Tid loadOrCreateTID(Context context) {
        c.b("TidHelper", "TidHelper.loadOrCreateTID");
        if (context == null) {
            com.alipay.sdk.app.statistic.a.a(context, "tid", com.alipay.sdk.app.statistic.c.aa, "");
        }
        a(context);
        Tid tidLoadTID = loadTID(context);
        if (Tid.isEmpty(tidLoadTID)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                return null;
            }
            try {
                tidLoadTID = b(context);
            } catch (Throwable unused) {
            }
        }
        return tidLoadTID;
    }

    public static synchronized String getTIDValue(Context context) {
        Tid tidLoadOrCreateTID;
        tidLoadOrCreateTID = loadOrCreateTID(context);
        return Tid.isEmpty(tidLoadOrCreateTID) ? "" : tidLoadOrCreateTID.getTid();
    }

    public static boolean resetTID(Context context) throws Exception {
        c.b("TidHelper.resetTID", "resetTID");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new Exception("不能在主线程中调用此方法");
        }
        a(context);
        clearTID(context);
        Tid tidB = null;
        try {
            tidB = b(context);
        } catch (Throwable unused) {
        }
        return !Tid.isEmpty(tidB);
    }

    public static void clearTID(Context context) {
        b.a(context).g();
    }

    public static String getIMEI(Context context) {
        a(context);
        return com.alipay.sdk.util.a.a(context).b();
    }

    public static String getIMSI(Context context) {
        a(context);
        return com.alipay.sdk.util.a.a(context).a();
    }

    public static String getVirtualImei(Context context) {
        a(context);
        return com.alipay.sdk.data.c.b().c();
    }

    public static String getVirtualImsi(Context context) {
        a(context);
        return com.alipay.sdk.data.c.b().d();
    }

    private static void a(Context context) {
        if (context == null) {
            return;
        }
        com.alipay.sdk.sys.b.a().a(context, com.alipay.sdk.data.c.b());
    }

    private static Tid b(Context context) throws Exception {
        try {
            com.alipay.sdk.packet.b bVarA = new com.alipay.sdk.packet.impl.c().a(context);
            if (bVarA != null) {
                JSONObject jSONObject = new JSONObject(bVarA.b());
                b bVarA2 = b.a(context);
                String strOptString = jSONObject.optString("tid");
                String string = jSONObject.getString(b.e);
                if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(string)) {
                    bVarA2.a(strOptString, string);
                }
                return a(context, bVarA2);
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    private static Tid a(Context context, b bVar) {
        if (bVar == null || bVar.e()) {
            return null;
        }
        return new Tid(bVar.a(), bVar.b(), bVar.i().longValue());
    }

    public static Tid loadLocalTid(Context context) {
        b bVarA = b.a(context);
        if (bVarA.h()) {
            return null;
        }
        return new Tid(bVarA.a(), bVarA.b(), bVarA.i().longValue());
    }
}
