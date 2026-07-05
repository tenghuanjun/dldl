package com.tencent.open.b;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.alipay.zoloz.toyger.ToygerBaseService;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.j;
import com.tencent.open.utils.k;
import com.unionpay.tsmservice.mini.data.Constant;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import kotlin.text.Typography;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class h {
    protected static h a;
    protected HandlerThread e;
    protected Handler f;
    protected Random b = new Random();
    protected List<Serializable> d = Collections.synchronizedList(new ArrayList());
    protected List<Serializable> c = Collections.synchronizedList(new ArrayList());
    protected Executor g = j.b();
    protected Executor h = j.b();

    public static synchronized h a() {
        if (a == null) {
            a = new h();
        }
        return a;
    }

    private h() {
        this.e = null;
        if (this.e == null) {
            HandlerThread handlerThread = new HandlerThread("opensdk.report.handlerthread", 10);
            this.e = handlerThread;
            handlerThread.start();
        }
        if (!this.e.isAlive() || this.e.getLooper() == null) {
            return;
        }
        this.f = new Handler(this.e.getLooper()) { // from class: com.tencent.open.b.h.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1000) {
                    h.this.b();
                } else if (i == 1001) {
                    h.this.e();
                }
                super.handleMessage(message);
            }
        };
    }

    public void a(final Bundle bundle, String str, final boolean z) {
        if (bundle == null) {
            return;
        }
        SLog.v("openSDK_LOG.ReportManager", "-->reportVia, bundle: " + bundle.toString());
        if (a("report_via", str) || z) {
            this.g.execute(new Runnable() { // from class: com.tencent.open.b.h.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String strK = k.k(d.b(com.tencent.open.utils.f.a()));
                        String strK2 = k.k(d.c(com.tencent.open.utils.f.a()));
                        String strK3 = k.k(d.a());
                        String strK4 = k.k(d.d(com.tencent.open.utils.f.a()));
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("uin", Constants.DEFAULT_UIN);
                        bundle2.putString("imei", strK);
                        bundle2.putString("imsi", strK2);
                        bundle2.putString("android_id", strK4);
                        bundle2.putString("mac", strK3);
                        bundle2.putString(Constants.PARAM_PLATFORM, "1");
                        bundle2.putString("os_ver", Build.VERSION.RELEASE);
                        bundle2.putString(RequestParameters.POSITION, "");
                        bundle2.putString("network", a.a(com.tencent.open.utils.f.a()));
                        bundle2.putString("language", d.b());
                        bundle2.putString("resolution", d.a(com.tencent.open.utils.f.a()));
                        bundle2.putString("apn", a.b(com.tencent.open.utils.f.a()));
                        bundle2.putString(Constants.PARAM_MODEL_NAME, Build.MODEL);
                        bundle2.putString("timezone", TimeZone.getDefault().getID());
                        bundle2.putString(Constants.PARAM_SDK_VER, Constants.SDK_VERSION);
                        bundle2.putString("qz_ver", k.d(com.tencent.open.utils.f.a(), Constants.PACKAGE_QZONE));
                        bundle2.putString(Constants.PARAM_QQ_VER, k.c(com.tencent.open.utils.f.a(), "com.tencent.mobileqq"));
                        bundle2.putString("qua", k.e(com.tencent.open.utils.f.a(), com.tencent.open.utils.f.b()));
                        bundle2.putString("packagename", com.tencent.open.utils.f.b());
                        bundle2.putString(Constants.PARAM_APP_VER, k.d(com.tencent.open.utils.f.a(), com.tencent.open.utils.f.b()));
                        if (bundle != null) {
                            bundle2.putAll(bundle);
                        }
                        h.this.d.add(new c(bundle2));
                        int size = h.this.d.size();
                        int iA = com.tencent.open.utils.g.a(com.tencent.open.utils.f.a(), (String) null).a("Agent_ReportTimeInterval");
                        if (iA == 0) {
                            iA = 10000;
                        }
                        if (!h.this.a("report_via", size) && !z) {
                            if (h.this.f.hasMessages(1001)) {
                                return;
                            }
                            Message messageObtain = Message.obtain();
                            messageObtain.what = 1001;
                            h.this.f.sendMessageDelayed(messageObtain, iA);
                            return;
                        }
                        h.this.e();
                        h.this.f.removeMessages(1001);
                    } catch (Exception e) {
                        SLog.e("openSDK_LOG.ReportManager", "--> reporVia, exception in sub thread.", e);
                    }
                }
            });
        }
    }

    public void a(String str, long j, long j2, long j3, int i) {
        a(str, j, j2, j3, i, "", false);
    }

    public void a(final String str, final long j, final long j2, final long j3, final int i, final String str2, final boolean z) {
        SLog.v("openSDK_LOG.ReportManager", "-->reportCgi, command: " + str + " | startTime: " + j + " | reqSize:" + j2 + " | rspSize: " + j3 + " | responseCode: " + i + " | detail: " + str2);
        if (a("report_cgi", "" + i) || z) {
            this.h.execute(new Runnable() { // from class: com.tencent.open.b.h.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        long jElapsedRealtime = SystemClock.elapsedRealtime() - j;
                        Bundle bundle = new Bundle();
                        String strA = a.a(com.tencent.open.utils.f.a());
                        bundle.putString("apn", strA);
                        bundle.putString("appid", "1000067");
                        bundle.putString("commandid", str);
                        bundle.putString("detail", str2);
                        StringBuilder sb = new StringBuilder();
                        sb.append("network=");
                        sb.append(strA);
                        sb.append(Typography.amp);
                        sb.append("sdcard=");
                        int i2 = 1;
                        sb.append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0);
                        sb.append(Typography.amp);
                        sb.append("wifi=");
                        sb.append(a.e(com.tencent.open.utils.f.a()));
                        bundle.putString("deviceInfo", sb.toString());
                        int iA = 100 / h.this.a(i);
                        if (iA > 0) {
                            i2 = iA > 100 ? 100 : iA;
                        }
                        bundle.putString("frequency", i2 + "");
                        bundle.putString("reqSize", j2 + "");
                        bundle.putString(Constant.KEY_RESULT_CODE, i + "");
                        bundle.putString("rspSize", j3 + "");
                        bundle.putString("timeCost", jElapsedRealtime + "");
                        bundle.putString("uin", Constants.DEFAULT_UIN);
                        h.this.c.add(new c(bundle));
                        int size = h.this.c.size();
                        int iA2 = com.tencent.open.utils.g.a(com.tencent.open.utils.f.a(), (String) null).a("Agent_ReportTimeInterval");
                        if (iA2 == 0) {
                            iA2 = 10000;
                        }
                        if (h.this.a("report_cgi", size) || z) {
                            h.this.b();
                            h.this.f.removeMessages(1000);
                        } else if (!h.this.f.hasMessages(1000)) {
                            Message messageObtain = Message.obtain();
                            messageObtain.what = 1000;
                            h.this.f.sendMessageDelayed(messageObtain, iA2);
                        }
                    } catch (Exception e) {
                        SLog.e("openSDK_LOG.ReportManager", "--> reportCGI, exception in sub thread.", e);
                    }
                }
            });
        }
    }

    protected void b() {
        if (k.b(com.tencent.open.utils.f.a())) {
            this.h.execute(new Runnable() { // from class: com.tencent.open.b.h.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Map<String, String> mapC = h.this.c();
                        if (mapC != null && !mapC.isEmpty()) {
                            int iA = com.tencent.open.utils.g.a(com.tencent.open.utils.f.a(), (String) null).a("Common_HttpRetryCount");
                            if (iA == 0) {
                                iA = 3;
                            }
                            SLog.d("openSDK_LOG.ReportManager", "-->doReportCgi, retryCount: " + iA);
                            boolean z = false;
                            int i = 0;
                            while (true) {
                                i++;
                                try {
                                    try {
                                        int iD = com.tencent.open.a.a.a().b("https://wspeed.qq.com/w.cgi", mapC).d();
                                        SLog.i("openSDK_LOG.ReportManager", "-->doReportCgi, statusCode: " + iD);
                                        if (iD != 200) {
                                            break;
                                        }
                                        g.a().b("report_cgi");
                                        z = true;
                                        break;
                                    } catch (Exception e) {
                                        SLog.e("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", e);
                                    }
                                } catch (SocketTimeoutException e2) {
                                    SLog.e("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", e2);
                                    if (i >= iA) {
                                        break;
                                    }
                                }
                            }
                            if (!z) {
                                g.a().a("report_cgi", h.this.c);
                            }
                            h.this.c.clear();
                        }
                    } catch (Exception e3) {
                        SLog.e("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception out.", e3);
                    }
                }
            });
        }
    }

    protected boolean a(String str, String str2) {
        int iA;
        SLog.d("openSDK_LOG.ReportManager", "-->availableFrequency, report: " + str + " | ext: " + str2);
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int i = 100;
        if (str.equals("report_cgi")) {
            try {
                iA = a(Integer.parseInt(str2));
                if (this.b.nextInt(100) < iA) {
                    z = true;
                }
            } catch (Exception unused) {
                return false;
            }
        } else {
            if (str.equals("report_via")) {
                iA = f.a(str2);
                if (this.b.nextInt(100) < iA) {
                    i = iA;
                    z = true;
                }
            }
            SLog.d("openSDK_LOG.ReportManager", "-->availableFrequency, result: " + z + " | frequency: " + i);
            return z;
        }
        i = iA;
        SLog.d("openSDK_LOG.ReportManager", "-->availableFrequency, result: " + z + " | frequency: " + i);
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001c A[PHI: r0
  0x001c: PHI (r0v9 int) = (r0v6 int), (r0v12 int) binds: [B:11:0x0034, B:5:0x0019] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean a(java.lang.String r5, int r6) {
        /*
            r4 = this;
            java.lang.String r0 = "report_cgi"
            boolean r0 = r5.equals(r0)
            r1 = 5
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L1e
            android.content.Context r0 = com.tencent.open.utils.f.a()
            com.tencent.open.utils.g r0 = com.tencent.open.utils.g.a(r0, r2)
            java.lang.String r2 = "Common_CGIReportMaxcount"
            int r0 = r0.a(r2)
            if (r0 != 0) goto L1c
            goto L38
        L1c:
            r1 = r0
            goto L38
        L1e:
            java.lang.String r0 = "report_via"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L37
            android.content.Context r0 = com.tencent.open.utils.f.a()
            com.tencent.open.utils.g r0 = com.tencent.open.utils.g.a(r0, r2)
            java.lang.String r2 = "Agent_ReportBatchCount"
            int r0 = r0.a(r2)
            if (r0 != 0) goto L1c
            goto L38
        L37:
            r1 = 0
        L38:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "-->availableCount, report: "
            r0.append(r2)
            r0.append(r5)
            java.lang.String r5 = " | dataSize: "
            r0.append(r5)
            r0.append(r6)
            java.lang.String r5 = " | maxcount: "
            r0.append(r5)
            r0.append(r1)
            java.lang.String r5 = r0.toString()
            java.lang.String r0 = "openSDK_LOG.ReportManager"
            com.tencent.open.log.SLog.d(r0, r5)
            if (r6 < r1) goto L62
            r5 = 1
            return r5
        L62:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.h.a(java.lang.String, int):boolean");
    }

    protected int a(int i) {
        if (i == 0) {
            int iA = com.tencent.open.utils.g.a(com.tencent.open.utils.f.a(), (String) null).a("Common_CGIReportFrequencySuccess");
            if (iA == 0) {
                return 10;
            }
            return iA;
        }
        int iA2 = com.tencent.open.utils.g.a(com.tencent.open.utils.f.a(), (String) null).a("Common_CGIReportFrequencyFailed");
        if (iA2 == 0) {
            return 100;
        }
        return iA2;
    }

    protected Map<String, String> c() {
        if (this.c.size() == 0) {
            return null;
        }
        c cVar = (c) this.c.get(0);
        if (cVar == null) {
            SLog.d("openSDK_LOG.ReportManager", "-->prepareCgiData, the 0th cgireportitem is null.");
            return null;
        }
        String str = cVar.a.get("appid");
        List<Serializable> listA = g.a().a("report_cgi");
        if (listA != null) {
            this.c.addAll(listA);
        }
        SLog.d("openSDK_LOG.ReportManager", "-->prepareCgiData, mCgiList size: " + this.c.size());
        if (this.c.size() == 0) {
            return null;
        }
        HashMap map = new HashMap();
        try {
            map.put("appid", str);
            map.put("releaseversion", Constants.SDK_VERSION_REPORT);
            map.put(com.alipay.sdk.packet.e.n, Build.DEVICE);
            map.put("qua", Constants.SDK_QUA);
            map.put(ToygerBaseService.KEY_RES_9_KEY, "apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize,detail,touin,deviceinfo");
            for (int i = 0; i < this.c.size(); i++) {
                c cVar2 = (c) this.c.get(i);
                map.put(i + "_1", cVar2.a.get("apn"));
                map.put(i + "_2", cVar2.a.get("frequency"));
                map.put(i + "_3", cVar2.a.get("commandid"));
                map.put(i + "_4", cVar2.a.get(Constant.KEY_RESULT_CODE));
                map.put(i + "_5", cVar2.a.get("timeCost"));
                map.put(i + "_6", cVar2.a.get("reqSize"));
                map.put(i + "_7", cVar2.a.get("rspSize"));
                map.put(i + "_8", cVar2.a.get("detail"));
                map.put(i + "_9", cVar2.a.get("uin"));
                map.put(i + "_10", d.e(com.tencent.open.utils.f.a()) + com.alipay.sdk.sys.a.b + cVar2.a.get("deviceInfo"));
            }
            SLog.v("openSDK_LOG.ReportManager", "-->prepareCgiData, end. params: " + map.toString());
            return map;
        } catch (Exception e) {
            SLog.e("openSDK_LOG.ReportManager", "-->prepareCgiData, exception.", e);
            return null;
        }
    }

    protected Map<String, String> d() {
        List<Serializable> listA = g.a().a("report_via");
        if (listA != null) {
            this.d.addAll(listA);
        }
        SLog.d("openSDK_LOG.ReportManager", "-->prepareViaData, mViaList size: " + this.d.size());
        if (this.d.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Serializable serializable : this.d) {
            JSONObject jSONObject = new JSONObject();
            c cVar = (c) serializable;
            for (String str : cVar.a.keySet()) {
                try {
                    String str2 = cVar.a.get(str);
                    if (str2 == null) {
                        str2 = "";
                    }
                    jSONObject.put(str, str2);
                } catch (JSONException e) {
                    SLog.e("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e);
                }
            }
            jSONArray.put(jSONObject);
        }
        SLog.v("openSDK_LOG.ReportManager", "-->prepareViaData, JSONArray array: " + jSONArray.toString());
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(com.alipay.sdk.packet.e.k, jSONArray);
            HashMap map = new HashMap();
            map.put(com.alipay.sdk.packet.e.k, jSONObject2.toString());
            return map;
        } catch (JSONException e2) {
            SLog.e("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e2);
            return null;
        }
    }

    protected void e() {
        if (k.b(com.tencent.open.utils.f.a())) {
            this.g.execute(new Runnable() { // from class: com.tencent.open.b.h.5
                /* JADX WARN: Code restructure failed: missing block: B:23:0x008e, code lost:
                
                    r18 = r5;
                    r22 = r9;
                    r20 = r14;
                    r7 = true;
                 */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void run() {
                    /*
                        Method dump skipped, instruction units count: 279
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.h.AnonymousClass5.run():void");
                }
            });
        }
    }

    public void a(final String str, final Map<String, String> map) {
        if (k.b(com.tencent.open.utils.f.a())) {
            j.b(new Runnable() { // from class: com.tencent.open.b.h.6
                @Override // java.lang.Runnable
                public void run() {
                    int i = 0;
                    try {
                        int iA = f.a();
                        if (iA == 0) {
                            iA = 3;
                        }
                        SLog.d("openSDK_LOG.ReportManager", "-->httpRequest, retryCount: " + iA);
                        do {
                            i++;
                            try {
                                SLog.i("openSDK_LOG.ReportManager", "-->httpRequest, statusCode: " + com.tencent.open.a.a.a().a(str, map).d());
                            } catch (SocketTimeoutException e) {
                                SLog.e("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest SocketTimeoutException:", e);
                            } catch (Exception e2) {
                                SLog.e("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Exception:", e2);
                            }
                        } while (i < iA);
                    } catch (Exception e3) {
                        SLog.e("openSDK_LOG.ReportManager", "-->httpRequest, exception in serial executor:", e3);
                    }
                }
            });
        }
    }
}
