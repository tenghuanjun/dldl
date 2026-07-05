package com.tencent.open.a;

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
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.i;
import com.tencent.open.utils.k;
import com.unionpay.tsmservice.mini.data.Constant;
import java.io.IOException;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import kotlin.text.Typography;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class g {
    protected static g a;
    protected HandlerThread e;
    protected Handler f;
    protected Random b = new Random();
    protected List<Serializable> d = Collections.synchronizedList(new ArrayList());
    protected List<Serializable> c = Collections.synchronizedList(new ArrayList());
    protected Executor g = i.b();
    protected Executor h = i.b();

    public static synchronized g a() {
        if (a == null) {
            a = new g();
        }
        return a;
    }

    private g() {
        this.e = null;
        if (this.e == null) {
            this.e = new HandlerThread("opensdk.report.handlerthread", 10);
            this.e.start();
        }
        if (!this.e.isAlive() || this.e.getLooper() == null) {
            return;
        }
        this.f = new Handler(this.e.getLooper()) { // from class: com.tencent.open.a.g.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1000:
                        g.this.b();
                        break;
                    case 1001:
                        g.this.e();
                        break;
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
            this.g.execute(new Runnable() { // from class: com.tencent.open.a.g.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String strK = k.k(c.b(com.tencent.open.utils.e.a()));
                        String strK2 = k.k(c.c(com.tencent.open.utils.e.a()));
                        String strK3 = k.k(c.a());
                        SLog.i("openSDK_LOG.ReportManager", String.format("-->reporVia:imei[%s,%s],imsi[%s,%s],mac[%s,%s]", c.b(com.tencent.open.utils.e.a()), strK, c.c(com.tencent.open.utils.e.a()), strK2, c.a(), strK3));
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("uin", Constants.DEFAULT_UIN);
                        bundle2.putString("imei", strK);
                        bundle2.putString("imsi", strK2);
                        bundle2.putString("android_id", c.d(com.tencent.open.utils.e.a()));
                        bundle2.putString("mac", strK3);
                        bundle2.putString(Constants.PARAM_PLATFORM, "1");
                        bundle2.putString("os_ver", Build.VERSION.RELEASE);
                        bundle2.putString(RequestParameters.POSITION, k.c(com.tencent.open.utils.e.a()));
                        bundle2.putString("network", a.a(com.tencent.open.utils.e.a()));
                        bundle2.putString("language", c.b());
                        bundle2.putString("resolution", c.a(com.tencent.open.utils.e.a()));
                        bundle2.putString("apn", a.b(com.tencent.open.utils.e.a()));
                        bundle2.putString(Constants.PARAM_MODEL_NAME, Build.MODEL);
                        bundle2.putString("timezone", TimeZone.getDefault().getID());
                        bundle2.putString(Constants.PARAM_SDK_VER, "3.3.7.lite");
                        bundle2.putString("qz_ver", k.d(com.tencent.open.utils.e.a(), Constants.PACKAGE_QZONE));
                        bundle2.putString(Constants.PARAM_QQ_VER, k.c(com.tencent.open.utils.e.a(), "com.tencent.mobileqq"));
                        bundle2.putString("qua", k.e(com.tencent.open.utils.e.a(), com.tencent.open.utils.e.b()));
                        bundle2.putString("packagename", com.tencent.open.utils.e.b());
                        bundle2.putString(Constants.PARAM_APP_VER, k.d(com.tencent.open.utils.e.a(), com.tencent.open.utils.e.b()));
                        if (bundle != null) {
                            bundle2.putAll(bundle);
                        }
                        g.this.d.add(new b(bundle2));
                        int size = g.this.d.size();
                        int iA = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Agent_ReportTimeInterval");
                        if (iA == 0) {
                            iA = 10000;
                        }
                        if (!g.this.a("report_via", size) && !z) {
                            if (g.this.f.hasMessages(1001)) {
                                return;
                            }
                            Message messageObtain = Message.obtain();
                            messageObtain.what = 1001;
                            g.this.f.sendMessageDelayed(messageObtain, iA);
                            return;
                        }
                        g.this.e();
                        g.this.f.removeMessages(1001);
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
            this.h.execute(new Runnable() { // from class: com.tencent.open.a.g.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        long jElapsedRealtime = SystemClock.elapsedRealtime() - j;
                        Bundle bundle = new Bundle();
                        String strA = a.a(com.tencent.open.utils.e.a());
                        bundle.putString("apn", strA);
                        bundle.putString("appid", "1000067");
                        bundle.putString("commandid", str);
                        bundle.putString("detail", str2);
                        StringBuilder sb = new StringBuilder();
                        sb.append("network=");
                        sb.append(strA);
                        sb.append(Typography.amp);
                        sb.append("sdcard=");
                        sb.append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0);
                        sb.append(Typography.amp);
                        sb.append("wifi=");
                        sb.append(a.e(com.tencent.open.utils.e.a()));
                        bundle.putString("deviceInfo", sb.toString());
                        int iA = 100 / g.this.a(i);
                        if (iA <= 0) {
                            iA = 1;
                        } else if (iA > 100) {
                            iA = 100;
                        }
                        bundle.putString("frequency", iA + "");
                        bundle.putString("reqSize", j2 + "");
                        bundle.putString(Constant.KEY_RESULT_CODE, i + "");
                        bundle.putString("rspSize", j3 + "");
                        bundle.putString("timeCost", jElapsedRealtime + "");
                        bundle.putString("uin", Constants.DEFAULT_UIN);
                        g.this.c.add(new b(bundle));
                        int size = g.this.c.size();
                        int iA2 = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Agent_ReportTimeInterval");
                        if (iA2 == 0) {
                            iA2 = 10000;
                        }
                        if (g.this.a("report_cgi", size) || z) {
                            g.this.b();
                            g.this.f.removeMessages(1000);
                        } else if (!g.this.f.hasMessages(1000)) {
                            Message messageObtain = Message.obtain();
                            messageObtain.what = 1000;
                            g.this.f.sendMessageDelayed(messageObtain, iA2);
                        }
                    } catch (Exception e) {
                        SLog.e("openSDK_LOG.ReportManager", "--> reportCGI, exception in sub thread.", e);
                    }
                }
            });
        }
    }

    protected void b() {
        this.h.execute(new Runnable() { // from class: com.tencent.open.a.g.4
            /* JADX WARN: Removed duplicated region for block: B:33:0x00b1 A[EDGE_INSN: B:33:0x00b1->B:22:0x00b1 BREAK  A[LOOP:0: B:10:0x0033->B:38:?], SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:38:? A[LOOP:0: B:10:0x0033->B:38:?, LOOP_END, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 209
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.a.g.AnonymousClass4.run():void");
            }
        });
    }

    protected boolean a(String str, String str2) {
        int iA;
        SLog.d("openSDK_LOG.ReportManager", "-->availableFrequency, report: " + str + " | ext: " + str2);
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.equals("report_cgi")) {
            try {
                iA = a(Integer.parseInt(str2));
                if (this.b.nextInt(100) < iA) {
                    z = true;
                }
            } catch (Exception unused) {
                return false;
            }
        } else if (str.equals("report_via")) {
            iA = e.a(str2);
            if (this.b.nextInt(100) < iA) {
                z = true;
            }
        } else {
            iA = 100;
        }
        SLog.d("openSDK_LOG.ReportManager", "-->availableFrequency, result: " + z + " | frequency: " + iA);
        return z;
    }

    protected boolean a(String str, int i) {
        int iA;
        if (str.equals("report_cgi")) {
            iA = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Common_CGIReportMaxcount");
            if (iA == 0) {
                iA = 5;
            }
        } else if (str.equals("report_via")) {
            iA = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Agent_ReportBatchCount");
            if (iA == 0) {
                iA = 5;
            }
        } else {
            iA = 0;
        }
        SLog.d("openSDK_LOG.ReportManager", "-->availableCount, report: " + str + " | dataSize: " + i + " | maxcount: " + iA);
        return i >= iA;
    }

    protected int a(int i) {
        if (i == 0) {
            int iA = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Common_CGIReportFrequencySuccess");
            if (iA == 0) {
                return 10;
            }
            return iA;
        }
        int iA2 = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Common_CGIReportFrequencyFailed");
        if (iA2 == 0) {
            return 100;
        }
        return iA2;
    }

    protected Bundle c() {
        if (this.c.size() == 0) {
            return null;
        }
        b bVar = (b) this.c.get(0);
        if (bVar == null) {
            SLog.d("openSDK_LOG.ReportManager", "-->prepareCgiData, the 0th cgireportitem is null.");
            return null;
        }
        String str = (String) bVar.a.get("appid");
        List<Serializable> listA = f.a().a("report_cgi");
        if (listA != null) {
            this.c.addAll(listA);
        }
        SLog.d("openSDK_LOG.ReportManager", "-->prepareCgiData, mCgiList size: " + this.c.size());
        if (this.c.size() == 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        try {
            bundle.putString("appid", str);
            bundle.putString("releaseversion", "OpenSdk_3.3.7.lite");
            bundle.putString(com.alipay.sdk.packet.e.n, Build.DEVICE);
            bundle.putString("qua", "V1_AND_OpenSDK_3.3.7.lite_1077_RDM_B");
            bundle.putString(ToygerBaseService.KEY_RES_9_KEY, "apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize,detail,touin,deviceinfo");
            for (int i = 0; i < this.c.size(); i++) {
                b bVar2 = (b) this.c.get(i);
                bundle.putString(i + "_1", (String) bVar2.a.get("apn"));
                bundle.putString(i + "_2", (String) bVar2.a.get("frequency"));
                bundle.putString(i + "_3", (String) bVar2.a.get("commandid"));
                bundle.putString(i + "_4", (String) bVar2.a.get(Constant.KEY_RESULT_CODE));
                bundle.putString(i + "_5", (String) bVar2.a.get("timeCost"));
                bundle.putString(i + "_6", (String) bVar2.a.get("reqSize"));
                bundle.putString(i + "_7", (String) bVar2.a.get("rspSize"));
                bundle.putString(i + "_8", (String) bVar2.a.get("detail"));
                bundle.putString(i + "_9", (String) bVar2.a.get("uin"));
                bundle.putString(i + "_10", c.e(com.tencent.open.utils.e.a()) + com.alipay.sdk.sys.a.b + ((String) bVar2.a.get("deviceInfo")));
            }
            SLog.v("openSDK_LOG.ReportManager", "-->prepareCgiData, end. params: " + bundle.toString());
            return bundle;
        } catch (Exception e) {
            SLog.e("openSDK_LOG.ReportManager", "-->prepareCgiData, exception.", e);
            return null;
        }
    }

    protected Bundle d() {
        List<Serializable> listA = f.a().a("report_via");
        if (listA != null) {
            this.d.addAll(listA);
        }
        SLog.d("openSDK_LOG.ReportManager", "-->prepareViaData, mViaList size: " + this.d.size());
        if (this.d.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object obj : this.d) {
            JSONObject jSONObject = new JSONObject();
            b bVar = (b) obj;
            for (String str : bVar.a.keySet()) {
                try {
                    String str2 = (String) bVar.a.get(str);
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
        Bundle bundle = new Bundle();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(com.alipay.sdk.packet.e.k, jSONArray);
            bundle.putString(com.alipay.sdk.packet.e.k, jSONObject2.toString());
            return bundle;
        } catch (JSONException e2) {
            SLog.e("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e2);
            return null;
        }
    }

    protected void e() {
        this.g.execute(new Runnable() { // from class: com.tencent.open.a.g.5
            @Override // java.lang.Runnable
            public void run() {
                int i;
                long j;
                long j2;
                long j3;
                int i2;
                try {
                    Bundle bundleD = g.this.d();
                    if (bundleD == null) {
                        return;
                    }
                    SLog.v("openSDK_LOG.ReportManager", "-->doReportVia, params: " + bundleD.toString());
                    int iA = e.a();
                    int i3 = 0;
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    boolean z = false;
                    int errorCodeFromException = 0;
                    long j4 = 0;
                    long j5 = 0;
                    do {
                        int i4 = i3 + 1;
                        try {
                            try {
                                try {
                                    try {
                                        try {
                                            k.a aVarOpenUrl2 = HttpUtils.openUrl2(com.tencent.open.utils.e.a(), "https://appsupport.qq.com/cgi-bin/appstage/mstats_batch_report", "POST", bundleD);
                                            try {
                                                i2 = k.d(aVarOpenUrl2.a).getInt("ret");
                                            } catch (JSONException unused) {
                                                i2 = -4;
                                            }
                                            if (i2 == 0 || !TextUtils.isEmpty(aVarOpenUrl2.a)) {
                                                i4 = iA;
                                                z = true;
                                            }
                                            j4 = aVarOpenUrl2.b;
                                            j5 = aVarOpenUrl2.c;
                                            i3 = i4;
                                        } catch (ConnectTimeoutException unused2) {
                                            jElapsedRealtime = SystemClock.elapsedRealtime();
                                            i3 = i4;
                                            errorCodeFromException = -7;
                                            j4 = 0;
                                            j5 = 0;
                                        }
                                    } catch (IOException e) {
                                        errorCodeFromException = HttpUtils.getErrorCodeFromException(e);
                                        i3 = i4;
                                        j4 = 0;
                                        j5 = 0;
                                    }
                                } catch (HttpUtils.HttpStatusException e2) {
                                    try {
                                        errorCodeFromException = Integer.parseInt(e2.getMessage().replace(HttpUtils.HttpStatusException.ERROR_INFO, ""));
                                    } catch (Exception unused3) {
                                    }
                                    i = errorCodeFromException;
                                    j = jElapsedRealtime;
                                    j2 = j4;
                                    j3 = j5;
                                } catch (HttpUtils.NetworkUnavailableException unused4) {
                                    g.this.d.clear();
                                    SLog.d("openSDK_LOG.ReportManager", "doReportVia, NetworkUnavailableException.");
                                    return;
                                }
                            } catch (SocketTimeoutException unused5) {
                                jElapsedRealtime = SystemClock.elapsedRealtime();
                                i3 = i4;
                                errorCodeFromException = -8;
                                j4 = 0;
                                j5 = 0;
                            } catch (Exception unused6) {
                                i3 = iA;
                                errorCodeFromException = -6;
                                j4 = 0;
                                j5 = 0;
                            }
                        } catch (JSONException unused7) {
                            i3 = i4;
                            errorCodeFromException = -4;
                            j4 = 0;
                            j5 = 0;
                        }
                    } while (i3 < iA);
                    i = errorCodeFromException;
                    j = jElapsedRealtime;
                    j2 = j4;
                    j3 = j5;
                    g.this.a("mapp_apptrace_sdk", j, j2, j3, i, null, false);
                    if (z) {
                        f.a().b("report_via");
                    } else {
                        f.a().a("report_via", g.this.d);
                    }
                    g.this.d.clear();
                    SLog.d("openSDK_LOG.ReportManager", "-->doReportVia, uploadSuccess: " + z);
                } catch (Exception e3) {
                    SLog.e("openSDK_LOG.ReportManager", "-->doReportVia, exception in serial executor.", e3);
                }
            }
        });
    }

    public void a(final String str, final String str2, final Bundle bundle, final boolean z) {
        i.a(new Runnable() { // from class: com.tencent.open.a.g.6
            /* JADX WARN: Removed duplicated region for block: B:49:0x00ee A[EDGE_INSN: B:49:0x00ee->B:34:0x00ee BREAK  A[LOOP:0: B:20:0x0096->B:54:?], SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:54:? A[LOOP:0: B:20:0x0096->B:54:?, LOOP_END, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 272
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.a.g.AnonymousClass6.run():void");
            }
        });
    }
}
