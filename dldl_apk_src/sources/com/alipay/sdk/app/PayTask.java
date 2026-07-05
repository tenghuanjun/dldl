package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.app.PayResultActivity;
import com.alipay.sdk.data.a;
import com.alipay.sdk.util.H5PayResultModel;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.l;
import com.alipay.sdk.util.n;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class PayTask {
    private static final long i = 3000;
    private Activity b;
    private com.alipay.sdk.widget.a c;
    private String d = "wappaygw.alipay.com/service/rest.htm";
    private String e = "mclient.alipay.com/service/rest.htm";
    private String f = "mclient.alipay.com/home/exterfaceAssign.htm";
    private Map<String, a> g = new HashMap();
    static final Object a = com.alipay.sdk.util.e.class;
    private static long h = 0;
    private static long j = -1;

    public String getVersion() {
        return "15.6.5";
    }

    public PayTask(Activity activity) {
        this.b = activity;
        com.alipay.sdk.sys.b.a().a(this.b, com.alipay.sdk.data.c.b());
        com.alipay.sdk.app.statistic.a.a(activity);
        this.c = new com.alipay.sdk.widget.a(activity, com.alipay.sdk.widget.a.b);
    }

    public synchronized String pay(String str, boolean z) {
        String strA;
        if (b()) {
            return j.d();
        }
        if (z) {
            showLoading();
        }
        if (str.contains("payment_inst=")) {
            String strSubstring = str.substring(str.indexOf("payment_inst=") + 13);
            int iIndexOf = strSubstring.indexOf(38);
            if (iIndexOf > 0) {
                strSubstring = strSubstring.substring(0, iIndexOf);
            }
            i.a(strSubstring.replaceAll("\"", "").toLowerCase(Locale.getDefault()).replaceAll("alipay", ""));
        } else {
            i.a("");
        }
        if (str.contains(com.alipay.sdk.cons.a.r)) {
            com.alipay.sdk.cons.a.s = true;
        }
        if (com.alipay.sdk.cons.a.s) {
            if (str.startsWith(com.alipay.sdk.cons.a.t)) {
                str = str.substring(str.indexOf(com.alipay.sdk.cons.a.t) + 53);
            } else if (str.startsWith(com.alipay.sdk.cons.a.u)) {
                str = str.substring(str.indexOf(com.alipay.sdk.cons.a.u) + 52);
            }
        }
        try {
            strA = a(str);
            com.alipay.sdk.util.i.a(this.b.getApplicationContext(), strA);
        } catch (Throwable th) {
            try {
                String strC = j.c();
                com.alipay.sdk.util.c.a(th);
                com.alipay.sdk.data.a.g().a(this.b.getApplicationContext());
                dismissLoading();
                com.alipay.sdk.app.statistic.a.b(this.b.getApplicationContext(), str);
                strA = strC;
            } finally {
                com.alipay.sdk.data.a.g().a(this.b.getApplicationContext());
                dismissLoading();
                com.alipay.sdk.app.statistic.a.b(this.b.getApplicationContext(), str);
            }
        }
        return strA;
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        return l.a(pay(str, z));
    }

    public synchronized String fetchTradeToken() {
        return com.alipay.sdk.util.i.a(this.b.getApplicationContext());
    }

    public synchronized boolean payInterceptorWithUrl(String str, boolean z, H5PayCallback h5PayCallback) {
        String strFetchOrderInfoFromH5PayUrl;
        strFetchOrderInfoFromH5PayUrl = fetchOrderInfoFromH5PayUrl(str);
        if (!TextUtils.isEmpty(strFetchOrderInfoFromH5PayUrl)) {
            new Thread(new g(this, strFetchOrderInfoFromH5PayUrl, z, h5PayCallback)).start();
        }
        return !TextUtils.isEmpty(strFetchOrderInfoFromH5PayUrl);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0041 A[Catch: all -> 0x04ac, TryCatch #2 {all -> 0x04ac, blocks: (B:4:0x0005, B:6:0x000b, B:8:0x0028, B:15:0x00ac, B:17:0x00c5, B:24:0x0149, B:26:0x0162, B:39:0x01ec, B:41:0x01fd, B:43:0x020b, B:45:0x022a, B:47:0x0257, B:57:0x028c, B:63:0x02c5, B:50:0x0268, B:52:0x026e, B:54:0x027c, B:66:0x0332, B:68:0x033c, B:70:0x0342, B:73:0x034c, B:75:0x0356, B:77:0x0366, B:81:0x03ce, B:85:0x03e1, B:89:0x03f4, B:91:0x0434, B:93:0x043a, B:95:0x0440, B:98:0x047e, B:28:0x017b, B:30:0x0183, B:32:0x018b, B:38:0x01e9, B:19:0x00de, B:21:0x0106, B:10:0x0041, B:12:0x0069, B:34:0x01b3), top: B:113:0x0005, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00de A[Catch: all -> 0x04ac, TryCatch #2 {all -> 0x04ac, blocks: (B:4:0x0005, B:6:0x000b, B:8:0x0028, B:15:0x00ac, B:17:0x00c5, B:24:0x0149, B:26:0x0162, B:39:0x01ec, B:41:0x01fd, B:43:0x020b, B:45:0x022a, B:47:0x0257, B:57:0x028c, B:63:0x02c5, B:50:0x0268, B:52:0x026e, B:54:0x027c, B:66:0x0332, B:68:0x033c, B:70:0x0342, B:73:0x034c, B:75:0x0356, B:77:0x0366, B:81:0x03ce, B:85:0x03e1, B:89:0x03f4, B:91:0x0434, B:93:0x043a, B:95:0x0440, B:98:0x047e, B:28:0x017b, B:30:0x0183, B:32:0x018b, B:38:0x01e9, B:19:0x00de, B:21:0x0106, B:10:0x0041, B:12:0x0069, B:34:0x01b3), top: B:113:0x0005, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x017b A[Catch: all -> 0x04ac, TryCatch #2 {all -> 0x04ac, blocks: (B:4:0x0005, B:6:0x000b, B:8:0x0028, B:15:0x00ac, B:17:0x00c5, B:24:0x0149, B:26:0x0162, B:39:0x01ec, B:41:0x01fd, B:43:0x020b, B:45:0x022a, B:47:0x0257, B:57:0x028c, B:63:0x02c5, B:50:0x0268, B:52:0x026e, B:54:0x027c, B:66:0x0332, B:68:0x033c, B:70:0x0342, B:73:0x034c, B:75:0x0356, B:77:0x0366, B:81:0x03ce, B:85:0x03e1, B:89:0x03f4, B:91:0x0434, B:93:0x043a, B:95:0x0440, B:98:0x047e, B:28:0x017b, B:30:0x0183, B:32:0x018b, B:38:0x01e9, B:19:0x00de, B:21:0x0106, B:10:0x0041, B:12:0x0069, B:34:0x01b3), top: B:113:0x0005, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.lang.String fetchOrderInfoFromH5PayUrl(java.lang.String r17) {
        /*
            Method dump skipped, instruction units count: 1207
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.fetchOrderInfoFromH5PayUrl(java.lang.String):java.lang.String");
    }

    private static final String a(String... strArr) {
        if (strArr == null) {
            return "";
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    public static synchronized boolean fetchSdkConfig(Context context) {
        try {
            com.alipay.sdk.sys.b.a().a(context, com.alipay.sdk.data.c.b());
            long jElapsedRealtime = SystemClock.elapsedRealtime() / 1000;
            if (jElapsedRealtime - h < com.alipay.sdk.data.a.g().e()) {
                return false;
            }
            h = jElapsedRealtime;
            com.alipay.sdk.data.a.g().a(context.getApplicationContext());
            return true;
        } catch (Exception e) {
            com.alipay.sdk.util.c.a(e);
            return false;
        }
    }

    private class a {
        private String b;
        private String c;
        private String d;
        private String e;

        private a() {
            this.b = "";
            this.c = "";
            this.d = "";
            this.e = "";
        }

        /* synthetic */ a(PayTask payTask, g gVar) {
            this();
        }

        public String a() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
        }

        public String b() {
            return this.d;
        }

        public void b(String str) {
            this.d = str;
        }

        public String c() {
            return this.c;
        }

        public void c(String str) {
            this.c = str;
        }

        public String d() {
            return this.e;
        }

        public void d(String str) {
            this.e = str;
        }
    }

    private boolean a(boolean z, boolean z2, String str, StringBuilder sb, Map<String, String> map, String... strArr) {
        String str2;
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                str2 = "";
                break;
            }
            String str3 = strArr[i2];
            if (!TextUtils.isEmpty(map.get(str3))) {
                str2 = map.get(str3);
                break;
            }
            i2++;
        }
        if (TextUtils.isEmpty(str2)) {
            return !z2;
        }
        if (z) {
            sb.append(com.alipay.sdk.sys.a.b);
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        }
        sb.append(str);
        sb.append("=\"");
        sb.append(str2);
        sb.append("\"");
        return true;
    }

    public synchronized H5PayResultModel h5Pay(String str, boolean z) {
        H5PayResultModel h5PayResultModel;
        h5PayResultModel = new H5PayResultModel();
        try {
            String[] strArrSplit = pay(str, z).split(com.alipay.sdk.util.i.b);
            HashMap map = new HashMap();
            for (String str2 : strArrSplit) {
                int iIndexOf = str2.indexOf("={");
                if (iIndexOf >= 0) {
                    String strSubstring = str2.substring(0, iIndexOf);
                    map.put(strSubstring, a(str2, strSubstring));
                }
            }
            if (map.containsKey(l.a)) {
                h5PayResultModel.setResultCode(map.get(l.a));
            }
            h5PayResultModel.setReturnUrl(a(str, map));
            if (TextUtils.isEmpty(h5PayResultModel.getReturnUrl())) {
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.T, "");
            }
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.U, th);
            com.alipay.sdk.util.c.a(th);
        }
        return h5PayResultModel;
    }

    private String a(String str, Map<String, String> map) throws UnsupportedEncodingException {
        boolean zEquals = "9000".equals(map.get(l.a));
        String str2 = map.get("result");
        a aVarRemove = this.g.remove(str);
        String[] strArr = new String[2];
        strArr[0] = aVarRemove != null ? aVarRemove.b() : "";
        strArr[1] = aVarRemove != null ? aVarRemove.d() : "";
        a(strArr);
        if (map.containsKey("callBackUrl")) {
            return map.get("callBackUrl");
        }
        if (str2.length() > 15) {
            String strA = a(n.a("&callBackUrl=\"", "\"", str2), n.a("&call_back_url=\"", "\"", str2), n.a(com.alipay.sdk.cons.a.p, "\"", str2), URLDecoder.decode(n.a(com.alipay.sdk.cons.a.q, com.alipay.sdk.sys.a.b, str2), "utf-8"), URLDecoder.decode(n.a("&callBackUrl=", com.alipay.sdk.sys.a.b, str2), "utf-8"), n.a("call_back_url=\"", "\"", str2));
            if (!TextUtils.isEmpty(strA)) {
                return strA;
            }
        }
        if (aVarRemove != null) {
            String strA2 = zEquals ? aVarRemove.a() : aVarRemove.c();
            if (!TextUtils.isEmpty(strA2)) {
                return strA2;
            }
        }
        return aVarRemove != null ? com.alipay.sdk.data.a.g().d() : "";
    }

    private String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(com.alipay.sdk.util.i.d));
    }

    private e.a a() {
        return new h(this);
    }

    public void showLoading() {
        com.alipay.sdk.widget.a aVar = this.c;
        if (aVar != null) {
            aVar.b();
        }
    }

    public void dismissLoading() {
        com.alipay.sdk.widget.a aVar = this.c;
        if (aVar != null) {
            aVar.c();
            this.c = null;
        }
    }

    private String a(String str) {
        String strA = new com.alipay.sdk.sys.a(this.b).a(str);
        if (strA.contains("paymethod=\"expressGateway\"")) {
            return b(strA);
        }
        List<a.C0008a> listF = com.alipay.sdk.data.a.g().f();
        if (!com.alipay.sdk.data.a.g().q || listF == null) {
            listF = i.a;
        }
        if (n.b(this.b, listF)) {
            com.alipay.sdk.util.e eVar = new com.alipay.sdk.util.e(this.b, a());
            String strA2 = eVar.a(strA);
            eVar.a();
            if (TextUtils.equals(strA2, com.alipay.sdk.util.e.a) || TextUtils.equals(strA2, com.alipay.sdk.util.e.b)) {
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.O, "");
                return b(strA);
            }
            if (TextUtils.isEmpty(strA2)) {
                return j.c();
            }
            if (!strA2.contains(PayResultActivity.a)) {
                return strA2;
            }
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.Q, "");
            return a(strA, listF, strA2, this.b);
        }
        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.P, "");
        return b(strA);
    }

    private static String a(String str, List<a.C0008a> list, String str2, Activity activity) {
        n.a aVarA = n.a(activity, list);
        if (aVarA == null || aVarA.a() || aVarA.b() || !TextUtils.equals(aVarA.a.packageName, PayResultActivity.c)) {
            return str2;
        }
        com.alipay.sdk.util.c.b("msp", "PayTask:payResult: NOT_LOGIN");
        String strValueOf = String.valueOf(str.hashCode());
        PayResultActivity.b.put(strValueOf, new Object());
        Intent intent = new Intent(activity, (Class<?>) PayResultActivity.class);
        intent.putExtra(PayResultActivity.e, str);
        intent.putExtra(PayResultActivity.f, activity.getPackageName());
        intent.putExtra(PayResultActivity.d, strValueOf);
        activity.startActivity(intent);
        synchronized (PayResultActivity.b.get(strValueOf)) {
            try {
                com.alipay.sdk.util.c.b("msp", "PayTask:payResult: wait");
                PayResultActivity.b.get(strValueOf).wait();
            } catch (InterruptedException e) {
                com.alipay.sdk.util.c.b("msp", "PayTask:payResult: InterruptedException:" + e);
                return j.c();
            }
        }
        String str3 = PayResultActivity.a.b;
        com.alipay.sdk.util.c.b("msp", "PayTask:payResult: result:" + str3);
        return str3;
    }

    private String b(String str) {
        showLoading();
        k kVarB = null;
        try {
            try {
                JSONObject jSONObjectC = new com.alipay.sdk.packet.impl.e().a(this.b.getApplicationContext(), str).c();
                String strOptString = jSONObjectC.optString("end_code", null);
                List<com.alipay.sdk.protocol.b> listA = com.alipay.sdk.protocol.b.a(jSONObjectC.optJSONObject(com.alipay.sdk.cons.c.c).optJSONObject(com.alipay.sdk.cons.c.d));
                for (int i2 = 0; i2 < listA.size(); i2++) {
                    if (listA.get(i2).b() == com.alipay.sdk.protocol.a.Update) {
                        com.alipay.sdk.protocol.b.a(listA.get(i2));
                    }
                }
                a(jSONObjectC);
                dismissLoading();
                for (int i3 = 0; i3 < listA.size(); i3++) {
                    com.alipay.sdk.protocol.b bVar = listA.get(i3);
                    if (bVar.b() == com.alipay.sdk.protocol.a.WapPay) {
                        return a(bVar);
                    }
                    if (bVar.b() == com.alipay.sdk.protocol.a.OpenWeb) {
                        return a(bVar, strOptString);
                    }
                }
            } finally {
                dismissLoading();
            }
        } catch (IOException e) {
            kVarB = k.b(k.NETWORK_ERROR.a());
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.a, e);
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a(th);
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.u, th);
        }
        if (kVarB == null) {
            kVarB = k.b(k.FAILED.a());
        }
        return j.a(kVarB.a(), kVarB.b(), "");
    }

    private void a(JSONObject jSONObject) {
        try {
            String strOptString = jSONObject.optString("tid");
            String strOptString2 = jSONObject.optString(com.alipay.sdk.tid.b.e);
            if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2)) {
                return;
            }
            com.alipay.sdk.tid.b.a(com.alipay.sdk.sys.b.a().b()).a(strOptString, strOptString2);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.H, th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x008e, code lost:
    
        r0 = r6.c();
        r10 = com.alipay.sdk.app.j.a(java.lang.Integer.valueOf(r0[1]).intValue(), r0[0], com.alipay.sdk.util.n.e(r0[2]));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(com.alipay.sdk.protocol.b r10, java.lang.String r11) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.a(com.alipay.sdk.protocol.b, java.lang.String):java.lang.String");
    }

    private String a(com.alipay.sdk.protocol.b bVar) {
        String[] strArrC = bVar.c();
        Intent intent = new Intent(this.b, (Class<?>) H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", strArrC[0]);
        if (strArrC.length == 2) {
            bundle.putString("cookie", strArrC[1]);
        }
        intent.putExtras(bundle);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                com.alipay.sdk.util.c.a(e);
                return j.c();
            }
        }
        String strA = j.a();
        return TextUtils.isEmpty(strA) ? j.c() : strA;
    }

    private static boolean b() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - j < i) {
            return true;
        }
        j = jElapsedRealtime;
        return false;
    }
}
